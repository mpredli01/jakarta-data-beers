package org.redlich.beers;

import jakarta.data.Order;
import jakarta.data.page.Page;
import jakarta.data.page.PageRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * <p>BrewerService class.</p>
 *
 * @author mpredli01
 * @version $Id: $Id
 */
@ApplicationScoped
public class BrewerService {

    @Inject
    BrewerRepository brewerRepository;

    @Inject
    BeerRepository beerRepository;

    /**
     * <p>findById.</p>
     *
     * @param id a int
     * @return a {@link java.util.Optional} object
     */
    public Optional<Brewer> findById(int id) {
        return brewerRepository.findById(id);
        }

    /**
     * <p>listBrewers.</p>
     *
     * @return a {@link java.util.stream.Stream} object
     */
    public Stream<Brewer> listBrewers() {
        return brewerRepository.findAll();
        }

    /**
     * <p>listBrewersByNameLike.</p>
     *
     * @param name a {@link java.lang.String} object
     * @return a {@link java.util.stream.Stream} object
     */
    public Stream<Brewer> listBrewersByNameLike(String name) {
        return listBrewersByNameLike(name, PageRequest.ofSize(Integer.MAX_VALUE), Order.by(_Brewer.name.asc())).stream();
        }

    /**
     * <p>listBrewersByNameLike.</p>
     *
     * @param name a {@link java.lang.String} object
     * @param pageRequest a {@link jakarta.data.page.PageRequest} object
     * @return a {@link jakarta.data.page.Page} object
     */
    public Page<Brewer> listBrewersByNameLike(String name, PageRequest pageRequest) {
        return brewerRepository.findByNameLike(name, pageRequest);
        }
    
    /**
     * <p>listBrewersByNameLike.</p>
     *
     * @param name a {@link java.lang.String} object
     * @param pageRequest a {@link jakarta.data.page.PageRequest} object
     * @param order a {@link jakarta.data.Order} object
     * @return a {@link jakarta.data.page.Page} object
     */
    public Page<Brewer> listBrewersByNameLike(String name, PageRequest pageRequest, Order<Brewer> order) {
        return brewerRepository.findByNameLike(name, pageRequest, order);
        }

    /**
     * <p>add.</p>
     *
     * @param brewer a {@link org.redlich.beers.Brewer} object
     * @return a {@link org.redlich.beers.Brewer} object
     */
    public Brewer add(Brewer brewer) {
        return brewerRepository.save(brewer);
        }

    /**
     * <p>remove.</p>
     *
     * @param id a int
     */
    public void remove(int id) {
        brewerRepository.findById(id)
                .ifPresent(brewer -> {
                    beerRepository.deleteByBrewerId(brewer.getId());
                    brewerRepository.remove(brewer);
                    });
        }

    /**
     * <p>removeAll.</p>
     */
    public void removeAll() {
        brewerRepository.deleteAll();
        }
    }
