package com.shadybrookdev.sample.delegate;

import com.shadybrookdev.sample.config.SampleConfig;
import com.shadybrookdev.sample.model.ProductOne;
import com.shadybrookdev.sample.repository.ProductOneRepo;

import java.time.LocalDate;
import java.util.function.Function;
import java.util.stream.Stream;

public class ProductOneManager implements RunManager<ProductOne> {
    final ProductOneRepo productOneRepo;

    final SampleConfig config;

    public ProductOneManager(ProductOneRepo productOneRepo, SampleConfig config) {
        this.productOneRepo = productOneRepo;
        this.config = config;
    }

    @Override
    public Stream<ProductOne> getData(LocalDate effectiveDate) {
        return productOneRepo.getProductOneData(effectiveDate);
    }

    @Override
    public String getBucketName() {
        return config.getBucketName();
    }

    @Override
    public Function<ProductOne, String> writeCSVLine() {
        return record -> {
            StringBuilder builder = new StringBuilder();
            builder.append("Product One Record: ")
                    .append(record.getSomeField())
                    .append(System.lineSeparator());
            return builder.toString();
        };
    }
}
