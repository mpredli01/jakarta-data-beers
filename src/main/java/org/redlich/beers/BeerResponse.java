package org.redlich.beers;

public record BeerResponse(int id, String name, BeerType type, int brewerId, double abv) {

    static BeerResponse of(Beer beer) {
        return new BeerResponse(beer.getId(), beer.getName(), beer.getType(), beer.getBrewerId(), beer.getAbv());
    }

}