package org.redlich.beers;

import jakarta.data.page.Page;
import jakarta.data.page.Pageable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class BeerService {

    @Inject
    BeerRepository beerRepository;

    @Inject
    BrewerRepository brewerRepository;

    public Optional<Beer> findById(int id) {
        return beerRepository.findById(id);
        }

    public Stream<Beer> listBeers() {
        return beerRepository.findAll();
        }

    public Stream<Beer> listBeersByBrewer(String brewerName) {
        return beerRepository.findByBrewerIdIn(
                brewerRepository.findByNameLike(brewerName)
                        .map(Brewer::getId)
                        .toList());
        }

    public Page<Beer> listBeersByBrewer(String brewerName, Pageable pageable) {
        return beerRepository.findByBrewerIdIn(
                brewerRepository.findByNameLike(brewerName)
                        .map(Brewer::getId)
                        .toList(), pageable);
        }

    public Beer add(Beer beer) {
        return beerRepository.save(beer);
        }

    public void remove(int id) {
        beerRepository.findById(id)
                .ifPresent(beerRepository::remove);
        }

    public void removeAll() {
        beerRepository.deleteAll();
        }
    }
