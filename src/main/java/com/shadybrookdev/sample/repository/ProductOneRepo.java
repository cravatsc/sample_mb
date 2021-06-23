package com.shadybrookdev.sample.repository;

import com.shadybrookdev.sample.model.ProductOne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.QueryHint;
import java.time.LocalDate;
import java.util.stream.Stream;

import static org.hibernate.jpa.QueryHints.*;

public interface ProductOneRepo extends JpaRepository<ProductOne, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM P1 WHERE DATE= :effectiveDate")
    @QueryHints(value = {
            @QueryHint(name = HINT_FETCH_SIZE, value="1000"),
            @QueryHint(name = HINT_READONLY, value = "true"),
            @QueryHint(name = HINT_CACHEABLE, value = "false")
    })
    Stream<ProductOne> getProductOneData(@Param("effectiveDate")LocalDate effectiveDate);
}
