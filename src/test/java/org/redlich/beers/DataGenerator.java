package org.redlich.beers;

import net.datafaker.Faker;
import net.datafaker.providers.base.Address;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataGenerator {

    private final static Faker faker = new Faker();

    private static final AtomicInteger beerIds = new AtomicInteger(0);

    private static final AtomicInteger brewerIds = new AtomicInteger(0);

    static void reset() {
        brewerIds.set(0);
        beerIds.set(0);
    }

    static Beer createBeer(List<Brewer> validBrewers) {
        return Beer.builder()
                .id(beerIds.incrementAndGet())
                .name(faker.beer().name())
                .type(BeerType.values()[faker.random().nextInt(0, BeerType.values().length - 1)])
                .brewerId(validBrewers.get(faker.random().nextInt(0, validBrewers.size() - 1)).getId())
                .abv(faker.random().nextDouble(0.01, 100.0))
                .build();
    }

    static List<Beer> createBeers(int count, List<Brewer> validBrewers) {
        return IntStream.range(0, count)
                .boxed()
                .map(i -> createBeer(validBrewers))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    static List<Brewer> createBrewers(int count) {
        return IntStream.range(0, count)
                .boxed()
                .map(i -> createBrewer())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    static List<Brewer> createBrewers(int count, Function<Integer, String> nameFunction) {
        return IntStream.range(0, count)
                .boxed()
                .map(i -> createBrewer(() -> nameFunction.apply(i)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    static Brewer createBrewer() {
        Address address = faker.address();
        return Brewer.builder()
                .id(brewerIds.incrementAndGet())
                .city(address.cityName())
                .state(address.state())
                .name(faker.beer().brand())
                .build();
    }

    static Brewer createBrewer(Supplier<String> name,
                               Supplier<String> city,
                               Supplier<String> state) {
        return Brewer.builder()
                .id(brewerIds.incrementAndGet())
                .city(city.get())
                .state(state.get())
                .name(name.get())
                .build();
    }

    static Brewer createBrewer(Supplier<String> name) {
        Address address = faker.address();
        return createBrewer(name, address::cityName, address::state);
    }

    static List<Beer> invalidBeers() {
        return List.of(createBeerWithoutNameAndType(), createBeerWithoutName(), createBeerWithoutType());
    }

    static Beer createBeerWithoutNameAndType() {
        return Beer.builder().name(null).type(null).build();
    }

    static Beer createBeerWithoutName() {
        return Beer.builder().name(null).type(BeerType.APA).build();
    }

    static Beer createBeerWithoutType() {
        return Beer.builder().name(faker.beer().name()).type(null).build();
    }

    static Beer createBeerWithoutId() {
        return Beer.builder().name(faker.beer().name()).type(BeerType.APA).build();
    }

    static List<Brewer> invalidBrewers() {
        return List.of(createBrewerWithoutName());
    }

    static Brewer createBrewerWithoutName() {
        Address address = faker.address();
        return Brewer.builder().name(null).city(address.cityName()).state(address.state()).build();
    }


}
