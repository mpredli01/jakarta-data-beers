package org.redlich.beers;

public record BeerRequest(String name, BeerType type, int brewerId, double abv) {

    public Beer createBeer(int id) {
        return Beer.builder()
                .id(id)
                .name(name)
                .type(type)
                .brewerId(brewerId)
                .abv(abv)
                .build();
    }

}
