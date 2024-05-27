package org.redlich.beers;

import jakarta.data.Order;
import jakarta.data.page.Page;
import jakarta.data.page.PageRequest;
import jakarta.data.repository.Delete;
import jakarta.data.repository.Repository;
import jakarta.data.repository.DataRepository;
import jakarta.data.repository.Query;
import jakarta.data.repository.Param;
import jakarta.data.repository.Save;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

/**
 * <p>BeerRepository interface.</p>
 *
 * @author mpredli01
 * @version $Id: $Id
 */
@Repository
public interface BeerRepository extends DataRepository<Beer, Integer> {

    /**
     * <p>findAll.</p>
     *
     * @return a {@link java.util.stream.Stream} object
     */
    Stream<Beer> findAll();

    /**
     * <p>findById.</p>
     *
     * @param id a int
     * @return a {@link java.util.Optional} object
     */
    Optional<Beer> findById(int id);

    /**
     * <p>findByBrewerIdIn.</p>
     *
     * @param brewerIds a {@link java.util.List} object
     * @return a {@link java.util.stream.Stream} object
     */
    Stream<Beer> findByBrewerIdIn(List<Integer> brewerIds);

    /**
     * <p>findByBrewerIdIn.</p>
     *
     * @param brewerIds a {@link java.util.List} object
     * @param pageRequest a {@link jakarta.data.page.PageRequest} object
     * @return a {@link jakarta.data.page.Page} object
     */
    Page<Beer> findByBrewerIdIn(List<Integer> brewerIds, PageRequest pageRequest);

        /**
     * <p>findByBrewerIdIn.</p>
     *
     * @param brewerIds a {@link java.util.List} object
     * @param pageRequest a {@link jakarta.data.page.PageRequest} object
     * @param order a {@link jakarta.data.Order} object 
     * @return a {@link jakarta.data.page.Page} object
     */
    Page<Beer> findByBrewerIdIn(List<Integer> brewerIds, PageRequest pageRequest, Order<Beer> order);

    /**
     * <p>save.</p>
     *
     * @param beer a {@link org.redlich.beers.Beer} object
     * @return a {@link org.redlich.beers.Beer} object
     */
    @Save
    Beer save(@Valid Beer beer);

    /**
     * <p>remove.</p>
     *
     * @param beer a {@link org.redlich.beers.Beer} object
     */
    @Delete
    void remove(Beer beer);

    /**
     * <p>query.</p>
     *
     * @param name a {@link java.lang.String} object
     * @return a {@link java.util.Set} object
     */
    @Query("SELECT * FROM Beer WHERE name = :name")
    Set<Beer> query(@Param("name") String name);

    /**
     * <p>deleteAll.</p>
     */
    @Query("DELETE FROM Beer")
    void deleteAll();

    /**
     * <p>deleteByBrewerId.</p>
     *
     * @param brewerId a int
     */
    @Query("DELETE FROM Beer WHERE brewerId = :brewerId")
    void deleteByBrewerId(@Param("brewerId") int brewerId);
    }
