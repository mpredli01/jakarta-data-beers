package org.redlich.beers;

public record BrewerRequest(String name, String city, String state) {

    public Brewer createBrewer(int id) {
        return Brewer.builder()
                .id(id)
                .name(name)
                .city(city)
                .state(state)
                .build();
    }

}