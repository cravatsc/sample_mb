package com.shadybrookdev.sample.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "P1")
public class ProductOne {
    @Column(name = "someField")
    String someField;
}
