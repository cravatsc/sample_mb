package com.shadybrookdev.sample.delegate;

import com.shadybrookdev.sample.model.ProductTwo;
import com.shadybrookdev.sample.repository.ProductTwoRepo;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.function.Function;
import java.util.stream.Stream;

@Component
public class ProductTwoManager implements RunManager<ProductTwo> {

    final ProductTwoRepo productTwoRepo;

    public ProductTwoManager(ProductTwoRepo productTwoRepo) {
        this.productTwoRepo = productTwoRepo;
    }

    @Override
    public Stream<ProductTwo> getData(LocalDate effectiveDate) {
        return productTwoRepo.getProductOneData(effectiveDate);
    }

    @Override
    public String getBucketName() {
        return null;
    }

    @Override
    public Function<ProductTwo, String> writeCSVLine() {
        return record -> {
            StringBuilder builder = new StringBuilder();
            builder.append("Product from other data: ")
                    .append(record.getSomeOtherField())
                    .append(System.lineSeparator());
            return builder.toString();
        };
    }
}
