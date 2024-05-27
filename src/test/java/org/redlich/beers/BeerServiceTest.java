package org.redlich.beers;

import jakarta.data.page.PageRequest;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.redlich.beers.DataGenerator.createBeer;
import static org.redlich.beers.DataGenerator.createBeerWithoutName;
import static org.redlich.beers.DataGenerator.createBeerWithoutNameAndType;
import static org.redlich.beers.DataGenerator.createBeerWithoutType;
import static org.redlich.beers.DataGenerator.createBeers;
import static org.redlich.beers.DataGenerator.createBrewers;

class BeerServiceTest extends BaseTest {

    @Inject
    BeerService beerService;

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

    private List<Brewer> validPersistedBrewers() {
        return validPersistedBrewers(5);
    }

    private List<Brewer> validPersistedBrewers(int count) {
        return createBrewers(count).stream().map(brewerRepository::save).toList();
    }

    @Test
    void shouldReturnValidationExceptionOnAdd() {

        assertSoftly(softly -> {

            softly.assertThatThrownBy(() -> beerService.add(createBeerWithoutNameAndType()))
                    .as("beers without name and type should be invalid")
                    .isInstanceOf(ConstraintViolationException.class);

            softly.assertThatThrownBy(() -> beerService.add(createBeerWithoutNameAndType()))
                    .as("beers without name and type should be invalid")
                    .isInstanceOf(ConstraintViolationException.class);

            softly.assertThatThrownBy(() -> beerService.add(createBeerWithoutName()))
                    .as("beers without name should be invalid")
                    .isInstanceOf(ConstraintViolationException.class);

            softly.assertThatThrownBy(() -> beerService.add(createBeerWithoutType()))
                    .as("beers without type should be invalid")
                    .isInstanceOf(ConstraintViolationException.class);
        });
    }

    @Test
    void shouldAdd() {
        var brewers = validPersistedBrewers();
        var beer = createBeer(brewers);
        beerService.add(beer);
        assertThat(beerRepository.findAll().count()).isEqualTo(1);
    }

    @Test
    void shouldListBeers() {
        var beers = createBeers(5, validPersistedBrewers());
        beers.forEach(beerRepository::save);
        assertThat(beerService.listBeers().toList())
                .as("should list beers")
                .containsAll(beers);
    }

    @Test
    void shouldRemove() {
        var beers = createBeers(5, validPersistedBrewers(2));
        beers.forEach(beerRepository::save);

        var removedBeer = beers.remove(0);
        beerService.remove(removedBeer.getId());
        assertThat(beerRepository.findAll().count())
                .as("should remove beer")
                .isEqualTo(beers.size());
    }

    @Test
    void shouldRemoveAll() {
        var beers = createBeers(5, validPersistedBrewers(2));
        beers.forEach(beerRepository::save);
        beerService.removeAll();
        assertThat(beerRepository.findAll().count())
                .as("should remove all beers")
                .isZero();
    }

    @Test
    void shouldListBeersByBrewer() {
        List<Brewer> brewers = validPersistedBrewers(3);
        var beersOfBrewer0 = createBeers(10, List.of(brewers.get(0)))
                .stream().map(beerRepository::save).toList();
        createBeers(6, List.of(brewers.get(1), brewers.get(2)))
                .forEach(beerRepository::save);

        assertThat(beerService.listBeersByBrewer(brewers.get(0).getName()).toList())
                .as("should list beers of brewer 0")
                .containsAll(beersOfBrewer0);

    }

    @Test
    void shouldListBeersByBrewerPageable() {
        List<Brewer> brewers = validPersistedBrewers(3);
        var beersOfBrewer0 = createBeers(4, List.of(brewers.get(0)))
                .stream().map(beerRepository::save).toList();
        createBeers(6, List.of(brewers.get(1), brewers.get(2)))
                .forEach(beerRepository::save);

        assertSoftly(softly -> {

            var page1 = beerService.listBeersByBrewer(brewers.get(0).getName(), PageRequest.ofSize(2));

            softly.assertThat(page1.stream().toList())
                    .as("the returned page 1 should contain the first 2 beers of brewer 0")
                    .containsAll(beersOfBrewer0.subList(0, 2));

            var page2 = beerService.listBeersByBrewer(brewers.get(0).getName(), page1.nextPageRequest());

            softly.assertThat(page2.stream().toList())
                    .as("the returned page 2 should contain the last 2 beers of brewer 0")
                    .containsAll(beersOfBrewer0.subList(2, 4));

            var page3 = beerService.listBeersByBrewer(brewers.get(0).getName(), page2.nextPageRequest());

            softly.assertThat(page3.stream().toList())
                    .as("the returned page 3 should be empty")
                    .isEmpty();


        });

    }

}