package org.redlich.beers;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
public class Brewer {
    @Id
    private int id;

    @Column
    @NotBlank
    private String name;

    @Column
    private String city;

    @Column
    private String state;

    public Brewer() {
    }

    private Brewer(int id, String name, String city, String state) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Brewer { " +
                "id = '" + getId() + '\'' +
                ", name = '" + getName() + '\'' +
                ", city = '" + getCity() + '\'' +
                ", state = '" + getState() + '\'' +
                " }\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brewer brewer = (Brewer) o;
        return id == brewer.id
                && Objects.equals(name, brewer.name)
                && Objects.equals(city, brewer.city)
                && Objects.equals(state, brewer.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, city, state);
    }

    public static BrewerBuilder builder() {
        return new BrewerBuilder();
    }

    public static class BrewerBuilder {
        private int id;
        private String name;
        private String city;
        private String state;

        private BrewerBuilder() {
        }

        public BrewerBuilder id(int id) {
            this.id = id;
            return this;
        }

        public BrewerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public BrewerBuilder city(String city) {
            this.city = city;
            return this;
        }

        public BrewerBuilder state(String state) {
            this.state = state;
            return this;
        }

        public Brewer build() {
            return new Brewer(id, name, city, state);
        }
    }
}
