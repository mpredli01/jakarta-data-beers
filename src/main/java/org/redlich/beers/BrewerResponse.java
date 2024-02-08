package org.redlich.beers;

public record BrewerResponse(int id, String name, String city, String state) {

    static BrewerResponse of(Brewer brewer) {
        return new BrewerResponse(
                brewer.getId(),
                brewer.getName(),
                brewer.getCity(),
                brewer.getState()
        );
    }

}