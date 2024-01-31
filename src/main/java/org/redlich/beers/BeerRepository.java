package org.redlich.beers;

import jakarta.data.page.Page;
import jakarta.data.page.Pageable;
import jakarta.data.repository.Repository;
import jakarta.data.repository.DataRepository;
import jakarta.data.repository.OrderBy;
import jakarta.data.repository.Query;
import jakarta.data.repository.Save;
import jakarta.data.repository.Param;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Repository
public interface BeerRepository extends DataRepository<Beer, Integer> {

    @OrderBy("name")
    Stream<Beer> findAll();

    Set<Beer> findByName(String beer);

    Set<Beer> findByBeerId(int beerId);

    List<Beer> findByBeer(String beer);

    Page<Beer> findByBeer(String beer, Pageable pageRequest);

    void deleteByBeerId(int beerId);

    @Query("select * from Beer where name = @name")
    Set<Beer> query(@Param("name") String name);

    @Save
    Beer save(@Valid Beer beer);

    void deleteAll();
    }

