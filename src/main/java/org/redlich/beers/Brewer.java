package org.redlich.beers;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * <p>Brewer class.</p>
 *
 * @author mpredli01
 * @version $Id: $Id
 */
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

    /**
     * <p>Constructor for Brewer.</p>
     */
    public Brewer() {
        this.id = 0;
        this.name = "{ brewer name }";
        this.city = "{ brewer city }";
        this.state = "{ brewer state }";
    }

    private Brewer(int id, String name, String city, String state) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.state = state;
    }

    /**
     * <p>Getter for the field <code>id</code>.</p>
     *
     * @return a int
     */
    public int getId() {
        return id;
    }

    /**
     * <p>Getter for the field <code>name</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getName() {
        return name;
    }

    /**
     * <p>Getter for the field <code>city</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getCity() {
        return city;
    }

    /**
     * <p>Getter for the field <code>state</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getState() {
        return state;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Brewer { " +
                "id = '" + getId() + '\'' +
                ", name = '" + getName() + '\'' +
                ", city = '" + getCity() + '\'' +
                ", state = '" + getState() + '\'' +
                " }\n";
    }

    /** {@inheritDoc} */
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

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, city, state);
    }

    /**
     * <p>builder.</p>
     *
     * @return a {@link org.redlich.beers.Brewer.BrewerBuilder} object
     */
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
