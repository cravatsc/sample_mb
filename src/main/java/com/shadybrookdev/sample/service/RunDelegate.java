package com.shadybrookdev.sample.service;

import com.shadybrookdev.sample.delegate.ProductOneManager;
import com.shadybrookdev.sample.delegate.ProductTwoManager;
import com.shadybrookdev.sample.delegate.RunManager;
import com.shadybrookdev.sample.enums.RunType;
import org.springframework.stereotype.Component;

@Component
public class RunDelegate {
    final ProductOneManager productOneManager;
    final ProductTwoManager productTwoManager;

    public RunDelegate(ProductOneManager productOneManager, ProductTwoManager productTwoManager) {
        this.productOneManager = productOneManager;
        this.productTwoManager = productTwoManager;
    }

    public RunManager identifyRun(RunType runType){
        switch (runType){
            case PRODUCT_ONE_RUN:
                return productOneManager;
            case PRODUCT_TWO_RUN:
                return productTwoManager;
            default:
                return null;
        }
    }
}
