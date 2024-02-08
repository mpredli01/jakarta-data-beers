package org.redlich.beers;

import jakarta.data.page.Pageable;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.redlich.beers.DataGenerator.createBrewer;
import static org.redlich.beers.DataGenerator.createBrewerWithoutName;
import static org.redlich.beers.DataGenerator.createBrewers;

class BrewerServiceTest extends BaseTest {

    @Inject
    BrewerService brewerService;

    @Inject
    BeerRepository beerRepository;

    @Inject
    BrewerRepository brewerRepository;

    @BeforeEach
    @AfterEach
    void cleanDatabase() {
        beerRepository.deleteAll();
        brewerRepository.deleteAll();
    }

    private List<Brewer> validBrewers() {
        return validBrewers(5);
    }

    private List<Brewer> validBrewers(int count) {
        return createBrewers(count);
    }

    private List<Brewer> validPersistedBrewers() {
        return validPersistedBrewers(5);
    }

    private List<Brewer> validPersistedBrewers(int count) {
        return createBrewers(count).stream().map(brewerRepository::save).collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Brewer> validPersistedBrewers(int count, Function<Integer, String> nameFunc) {
        return createBrewers(count, nameFunc).stream().map(brewerRepository::save).collect(Collectors.toCollection(ArrayList::new));
    }

    @Test
    void shouldReturnValidationExceptionOnAdd() {

        assertSoftly(softly -> {

            softly.assertThatThrownBy(() -> brewerService.add(createBrewerWithoutName()))
                    .as("brewers without name should be invalid")
                    .isInstanceOf(ConstraintViolationException.class);
        });
    }

    @Test
    void shouldAdd() {
        var brewer = createBrewer();
        brewerService.add(brewer);
        assertThat(brewerRepository.findAll().count()).isEqualTo(1);
    }

    @Test
    void shouldListBeers() {
        var brewers = validPersistedBrewers();

        assertThat(brewerService.listBrewers().toList())
                .as("should list brewers")
                .containsAll(brewers);
    }

    @Test
    void shouldRemove() {
        var brewers = validPersistedBrewers();

        var removedBrewer = brewers.remove(0);
        brewerService.remove(removedBrewer.getId());
        assertThat(brewerRepository.findAll().count())
                .as("should remove brewer")
                .isEqualTo(brewers.size());
    }

    @Test
    void shouldRemoveAll() {
        validPersistedBrewers();

        brewerService.removeAll();

        assertThat(brewerRepository.findAll().count())
                .as("should remove all brewers")
                .isZero();
    }

    @Test
    void shouldListBrewersByNameLike() {
        List<Brewer> brewersAAAA = validPersistedBrewers(5, i -> "AAAAAA");
        List<Brewer> brewersBBBB = validPersistedBrewers(5, i -> "BBBBBB");


        assertThat(brewerService.listBrewersByNameLike(brewersAAAA.get(0).getName()).toList())
                .as("the returned list should contain all brewers with the given name")
                .containsAll(brewersAAAA);


        assertThat(brewerService.listBrewersByNameLike(brewersBBBB.get(0).getName()).toList())
                .as("the returned list should contain all brewers with the given name")
                .containsAll(brewersBBBB);
    }

    @Test
    void shouldListBeersByBrewerPageable() {

        List<Brewer> brewersAAAA = validPersistedBrewers(4, i -> "AAAAAA");
        validPersistedBrewers(3, i -> "BBBBBB");
        validPersistedBrewers(3, i -> "CCCCCC");


        assertSoftly(softly -> {

            String brewerName = brewersAAAA.get(0).getName();

            var page1 = brewerService.listBrewersByNameLike(brewerName, Pageable.ofSize(2));

            softly.assertThat(page1.stream().toList())
                    .as("the returned page 1 should contain the first 2 beers of brewer " + brewerName)
                    .containsAll(brewersAAAA.subList(0, 2));

            var page2 = brewerService.listBrewersByNameLike(brewerName, page1.pageable().next());

            softly.assertThat(page2.stream().toList())
                    .as("the returned page 2 should contain the last 2 beers of brewer " + brewerName)
                    .containsAll(brewersAAAA.subList(2, 4));

            var page3 = brewerService.listBrewersByNameLike(brewerName, page2.pageable().next());

            softly.assertThat(page3.stream().toList())
                    .as("the returned page 3 should be empty")
                    .isEmpty();


        });

    }

}