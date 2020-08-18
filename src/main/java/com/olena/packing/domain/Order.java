package com.olena.packing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author ginger1998
 */
@Data
@Entity
@Table(name="orders")
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer numberOfProducts;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="productId")
    private Product product;


    public Order(int numberOfProducts, Product product) {
        this.product=product;
        this.numberOfProducts=numberOfProducts;
    }
}
