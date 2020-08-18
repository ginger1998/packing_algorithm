package com.olena.packing.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author ginger1998
 */

@Data
@Entity
@Table(name="cases")
@NoArgsConstructor
public class Case {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer sizeX;

    private Integer sizeY;

    private Integer sizeZ;

    public Case(int sizeX, int sizeY, int sizeZ) {
        this.sizeX=sizeX;
        this.sizeY=sizeY;
        this.sizeZ=sizeZ;
    }
}
