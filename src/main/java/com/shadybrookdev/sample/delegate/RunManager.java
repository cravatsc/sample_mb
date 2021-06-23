package com.shadybrookdev.sample.delegate;

import java.time.LocalDate;
import java.util.function.Function;
import java.util.stream.Stream;

public interface RunManager<T> {
    Stream<T> getData(LocalDate effectiveDate);

    String getBucketName();

    Function<T, String> writeCSVLine();
}
