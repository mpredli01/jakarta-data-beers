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
public interface BrewerRepository extends DataRepository<Brewer, Integer> {

    Set<Brewer> findByName(String brewer);

    Set<Brewer> findByBrewerId(int brewer_id);

    List<Brewer> findByBrewer(String brewer);

    void deleteById(int id);

    @Query("select * from Brewer where name = @name")
    Set<Brewer> query(@Param("name") String name);

    @Save
    Brewer save(@Valid Brewer brewer);

    Page<Brewer> findByBrewer(String brewer, Pageable pageRequest);

    @OrderBy("name")
    Stream<Brewer> findAll();

    void deleteByBrewerId(int brewerId);

    void deleteAll();
    }

