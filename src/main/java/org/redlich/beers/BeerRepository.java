package org.redlich.beers;

import jakarta.data.page.Page;
import jakarta.data.page.Pageable;
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

@Repository
public interface BeerRepository extends DataRepository<Beer, Integer> {

    Stream<Beer> findAll();

    Optional<Beer> findById(int id);

    Stream<Beer> findByBrewerIdIn(List<Integer> brewerIds);

    Page<Beer> findByBrewerIdIn(List<Integer> brewerIds, Pageable pageable);

    @Save
    Beer save(@Valid Beer beer);

    @Delete
    void remove(Beer beer);

    @Query("select * from Beer where name = @name")
    Set<Beer> query(@Param("name") String name);

    @Query("delete from Beer")
    void deleteAll();

    @Query("delete from Beer where brewerId = @brewerId")
    void deleteByBrewerId(@Param("brewerId") int brewerId);
    }
