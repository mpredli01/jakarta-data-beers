package org.redlich.beers;

import jakarta.data.page.Page;
import jakarta.data.page.Pageable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class BrewerService {

    @Inject
    BrewerRepository brewerRepository;

    @Inject
    BeerRepository beerRepository;

    public Optional<Brewer> findById(int id) {
        return brewerRepository.findById(id);
        }

    public Stream<Brewer> listBrewers() {
        return brewerRepository.findAll();
        }

    public Stream<Brewer> listBrewersByNameLike(String name) {
        return listBrewersByNameLike(name, Pageable.ofSize(Integer.MAX_VALUE)).stream();
        }

    public Page<Brewer> listBrewersByNameLike(String name, Pageable pageRequest) {
        return brewerRepository.findByNameLike(name, pageRequest);
        }

    public Brewer add(Brewer brewer) {
        return brewerRepository.save(brewer);
        }

    public void remove(int id) {
        brewerRepository.findById(id)
                .ifPresent(brewer -> {
                    beerRepository.deleteByBrewerId(brewer.getId());
                    brewerRepository.remove(brewer);
                    });
        }

    public void removeAll() {
        brewerRepository.deleteAll();
        }
    }
