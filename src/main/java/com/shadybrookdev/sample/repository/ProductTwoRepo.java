package com.shadybrookdev.sample.repository;

import com.shadybrookdev.sample.model.ProductTwo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.QueryHint;
import java.time.LocalDate;
import java.util.stream.Stream;

import static org.hibernate.jpa.QueryHints.*;

public interface ProductTwoRepo extends JpaRepository<ProductTwo, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM P2 WHERE DATE= :effectiveDate")
    @QueryHints(value = {
            @QueryHint(name = HINT_FETCH_SIZE, value="1000"),
            @QueryHint(name = HINT_READONLY, value = "true"),
            @QueryHint(name = HINT_CACHEABLE, value = "false")
    })
    Stream<ProductTwo> getProductOneData(@Param("effectiveDate")LocalDate effectiveDate);
}
