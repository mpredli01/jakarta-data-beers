package org.redlich.beers;

import jakarta.data.page.Page;
import jakarta.data.page.Pageable;
import jakarta.data.repository.Delete;
import jakarta.data.repository.Repository;
import jakarta.data.repository.DataRepository;
import jakarta.data.repository.Query;
import jakarta.data.repository.Save;
import jakarta.validation.Valid;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface BrewerRepository extends DataRepository<Brewer, Integer> {
    @Save
    Brewer save(@Valid Brewer brewer);

    Stream<Brewer> findAll();

    Optional<Brewer> findById(int id);

    @Delete
    void remove(Brewer brewer);

    @Query("delete from Brewer")
    void deleteAll();

    Stream<Brewer> findByNameLike(String city);

    Page<Brewer> findByNameLike(String name, Pageable pageable);

}

