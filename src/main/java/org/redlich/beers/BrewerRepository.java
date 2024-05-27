package org.redlich.beers;

import jakarta.data.Order;
import jakarta.data.page.Page;
import jakarta.data.page.PageRequest;
import jakarta.data.repository.Delete;
import jakarta.data.repository.Repository;
import jakarta.data.repository.DataRepository;
import jakarta.data.repository.Query;
import jakarta.data.repository.Save;
import jakarta.validation.Valid;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * <p>BrewerRepository interface.</p>
 *
 * @author mpredli01
 * @version $Id: $Id
 */
@Repository
public interface BrewerRepository extends DataRepository<Brewer, Integer> {

    /**
     * <p>findAll.</p>
     *
     * @return a {@link java.util.stream.Stream} object
     */
    Stream<Brewer> findAll();

    /**
     * <p>findById.</p>
     *
     * @param id a int
     * @return a {@link java.util.Optional} object
     */
    Optional<Brewer> findById(int id);

    /**
     * <p>findByNameLike.</p>
     *
     * @param city a {@link java.lang.String} object
     * @return a {@link java.util.stream.Stream} object
     */
    Stream<Brewer> findByNameLike(String city);

    /**
     * <p>findByNameLike.</p>
     *
     * @param name a {@link java.lang.String} object
     * @param pageRequest a {@link jakarta.data.page.PageRequest} object
     * @return a {@link jakarta.data.page.Page} object
     */
    Page<Brewer> findByNameLike(String name, PageRequest pageRequest);

    /**
     * <p>findByNameLike.</p>
     *
     * @param name a {@link java.lang.String} object
     * @param pageRequest a {@link jakarta.data.page.PageRequest} object
     * @param order a {@link jakarta.data.page.Order} object
     * @return a {@link jakarta.data.page.Page} object
     */
    Page<Brewer> findByNameLike(String name, PageRequest pageRequest, Order<Brewer> order);

    /**
     * <p>save.</p>
     *
     * @param brewer a {@link org.redlich.beers.Brewer} object
     * @return a {@link org.redlich.beers.Brewer} object
     */
    @Save
    Brewer save(@Valid Brewer brewer);

    /**
     * <p>remove.</p>
     *
     * @param brewer a {@link org.redlich.beers.Brewer} object
     */
    @Delete
    void remove(Brewer brewer);

    /**
     * <p>deleteAll.</p>
     */
    @Query("DELETE FROM Brewer")
    void deleteAll();
    }

