package org.redlich.beers;

import jakarta.data.Order;
import jakarta.data.page.Page;
import jakarta.data.page.PageRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * <p>BeerService class.</p>
 *
 * @author mpredli01
 * @version $Id: $Id
 */
@ApplicationScoped
public class BeerService {

    @Inject
    BeerRepository beerRepository;

    @Inject
    BrewerRepository brewerRepository;

    /**
     * <p>findById.</p>
     *
     * @param id a int
     * @return a {@link java.util.Optional} object
     */
    public Optional<Beer> findById(int id) {
        return beerRepository.findById(id);
        }

    /**
     * <p>listBeers.</p>
     *
     * @return a {@link java.util.stream.Stream} object
     */
    public Stream<Beer> listBeers() {
        return beerRepository.findAll();
        }

    /**
     * <p>listBeersByBrewer.</p>
     *
     * @param brewerName a {@link java.lang.String} object
     * @return a {@link java.util.stream.Stream} object
     */
    public Stream<Beer> listBeersByBrewer(String brewerName) {
        return beerRepository.findByBrewerIdIn(
                brewerRepository.findByNameLike(brewerName)
                        .map(Brewer::getId)
                        .toList());
        }

    /**
     * <p>listBeersByBrewer.</p>
     *
     * @param brewerName a {@link java.lang.String} object
     * @param pageRequest a {@link jakarta.data.page.PageRequest} object
     * @param order a {@link jakarta.data.Order} object
     * @return a {@link jakarta.data.page.Page} object
     */
    public Page<Beer> listBeersByBrewer(String brewerName, PageRequest pageRequest) {
        return beerRepository.findByBrewerIdIn(
                brewerRepository.findByNameLike(brewerName)
                        .map(Brewer::getId)
                        .toList(), pageRequest);
        }


    /**
     * <p>listBeersByBrewer.</p>
     *
     * @param brewerName a {@link java.lang.String} object
     * @param pageRequest a {@link jakarta.data.page.PageRequest} object
     * @param order a {@link jakarta.data.Order} object
     * @return a {@link jakarta.data.page.Page} object
     */
    public Page<Beer> listBeersByBrewer(String brewerName, PageRequest pageRequest, Order<Beer> order) {
        return beerRepository.findByBrewerIdIn(
                brewerRepository.findByNameLike(brewerName)
                        .map(Brewer::getId)
                        .toList(), pageRequest, order);
        }

    /**
     * <p>add.</p>
     *
     * @param beer a {@link org.redlich.beers.Beer} object
     * @return a {@link org.redlich.beers.Beer} object
     */
    public Beer add(Beer beer) {
        return beerRepository.save(beer);
        }

    /**
     * <p>remove.</p>
     *
     * @param id a int
     */
    public void remove(int id) {
        beerRepository.findById(id)
                .ifPresent(beerRepository::remove);
        }

    /**
     * <p>removeAll.</p>
     */
    public void removeAll() {
        beerRepository.deleteAll();
        }
    }
