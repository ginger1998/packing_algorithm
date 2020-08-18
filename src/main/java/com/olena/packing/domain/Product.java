package com.olena.packing.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * @author ginger1998
 */

@Data
@Entity
@Table(name="products")
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer sizeX;

    private Integer sizeY;

    private Integer sizeZ;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    Set<Order> orders;

    public Product(int sizeX, int sizeY, int sizeZ) {
        this.sizeX=sizeX;
        this.sizeY=sizeY;
        this.sizeZ=sizeZ;
    }
}

