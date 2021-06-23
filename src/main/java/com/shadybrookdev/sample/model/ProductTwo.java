package com.shadybrookdev.sample.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "P2")
public class ProductTwo {
    @Column(name = "someOtherField")
    String someOtherField;
}
