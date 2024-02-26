package org.redlich.beers;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * A POJO representing the Beer collection in the MongoDB database.
 *
 * @author mpredli01
 * @version $Id: $Id
 */
@Entity
public class Beer {
    /**
     *
     */
    @Id
    private int id;
    /**
     *
     */
    @Column
    @NotBlank
    private String name;

    /**
     *
     */
    @Column
    @NotNull
    private BeerType type;

    /**
     *
     */
    @Column("brewer_id")
    private int brewerId;

    /**
     *
     */
    @Column
    private double abv;

    /**
     * <p>Constructor for Beer.</p>
     */
    public Beer() {
        id = 0;
        name = "{ beer name }";
        type = BeerType.ALE;
        brewerId = 0;
        abv = 10.0;
        }

    /**
     *
     * @param id
     * @param name
     * @param type
     * @param brewerId
     * @param abv
     */
    private Beer(int id, String name, BeerType type, int brewerId, double abv) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.brewerId = brewerId;
        this.abv = abv;
        }

    /**
     * <p>Getter for the field <code>id</code>.</p>
     *
     * @return id the primary key the Beer entity.
     */
    public int getId() {
        return id;
        }

    /**
     * <p>Getter for the field <code>name</code>.</p>
     *
     * @return name the name of the beer.
     */
    public String getName() {
        return name;
    }

    /**
     * <p>Getter for the field <code>type</code>.</p>
     *
     * @return the beer type.
     */
    public BeerType getType() {
        return type;
    }

    /**
     * <p>Getter for the field <code>brewerId</code>.</p>
     *
     * @return brewerId the value of `brewerId` from the Brewer entity.
     */
    public int getBrewerId() {
        return brewerId;
    }

    /**
     * <p>Getter for the field <code>abv</code>.</p>
     *
     * @return abv the value of `abv`.
     */
    public double getAbv() {
        return abv;
        }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Beer { " +
                "id = '" + getId() + '\'' +
                ", name = '" + getName() + '\'' +
                ", type = '" + getType() + '\'' +
                ", brewer_id = '" + getBrewerId() + '\'' +
                ", abv = '" + getAbv() + '\'' +
                " }\n";
        }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beer beer = (Beer) o;
        return id == beer.id
                && brewerId == beer.brewerId
                && Double.compare(abv, beer.abv) == 0
                && Objects.equals(name, beer.name)
                && type == beer.type;
        }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, brewerId, abv);
        }

    /**
     * <p>builder.</p>
     *
     * @return BeerBuilder a new instance of the BeerBuilder.
     */
    public static BeerBuilder builder() {
        return new BeerBuilder();
        }

    /**
     * A builder pattern for the Beer class.
     */
    public static class BeerBuilder {
        private int id;
        private String name;
        private BeerType type;
        private int brewerId;
        private double abv;

        private BeerBuilder() {
            }

        public BeerBuilder id(int id) {
            this.id = id;
            return this;
            }

        public BeerBuilder name(String name) {
            this.name = name;
            return this;
            }

        public BeerBuilder type(BeerType type) {
            this.type = type;
            return this;
            }

        public BeerBuilder brewerId(int brewerId) {
            this.brewerId = brewerId;
            return this;
            }

        public BeerBuilder abv(double abv) {
            this.abv = abv;
            return this;
            }

        public Beer build() {
            return new Beer(id, name, type, brewerId, abv);
            }
        }
    }
