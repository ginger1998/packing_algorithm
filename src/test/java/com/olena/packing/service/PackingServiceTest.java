package com.olena.packing.service;

import com.olena.packing.domain.Case;
import com.olena.packing.domain.Order;
import com.olena.packing.domain.Product;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author ginger1998
 */
class PackingServiceTest {

    Set<Case> cases=new HashSet<>();;
    Set<Order> orders=new HashSet<>();
    Case aCase = new Case();
    Order order = new Order();


    @BeforeEach
    public void setUp() {
        Product p1 = new Product(5, 4, 2);
        Product p2 = new Product(3, 2, 4);
        Product p3 = new Product(8, 3, 5);
        order.setNumberOfProducts(8);
        order.setProduct(p1);
        aCase.setSizeX(10);
        aCase.setSizeY(10);
        aCase.setSizeZ(5);
        orders.add(new Order(12, p2));
        orders.add(order);
        orders.add(new Order(2, p3));
        cases.add(new Case(20, 10, 15));
        cases.add(aCase);
    }

    @Test
    void testSuccessPacking() {
        Assert.assertEquals(Map.entry(aCase,order),new PackingService().packing(cases,orders));
    }

    @Test
    void testLargePacking() {
        Set<Order> ordersLargeProductSize=new HashSet<>();
        ordersLargeProductSize.add(new Order(2,new Product(25,20,5)));
        ordersLargeProductSize.add(new Order(4,new Product(11,21,7)));
        Assert.assertEquals(new Case(),new PackingService().packing(cases,ordersLargeProductSize).getKey());
        Assert.assertEquals(new Order(),new PackingService().packing(cases,ordersLargeProductSize).getValue());
    }

    @Test
    void testHugePacking() {
        Set<Order> ordersHugeProductAmount=new HashSet<>();
        ordersHugeProductAmount.add(new Order(100,new Product(5,7,3)));
        ordersHugeProductAmount.add(new Order(50,new Product(6,10,4)));
        Assert.assertEquals(new Case(),new PackingService().packing(cases,ordersHugeProductAmount).getKey());
        Assert.assertEquals(new Order(),new PackingService().packing(cases,ordersHugeProductAmount).getValue());
    }

    @Test
    void testEmptyOrderPacking() {
        Set<Order> ordersEmpty=new HashSet<>();
        Assert.assertEquals(new Case(),new PackingService().packing(cases,ordersEmpty).getKey());
        Assert.assertEquals(new Order(),new PackingService().packing(cases,ordersEmpty).getValue());
    }

    @Test
    void testEmptyCasePacking() {
        Set<Case> casesEmpty=new HashSet<>();
        Assert.assertEquals(new Case(),new PackingService().packing(casesEmpty,orders).getKey());
        Assert.assertEquals(new Order(),new PackingService().packing(casesEmpty,orders).getValue());
    }

    @Test
    void testNullCasesPacking() {
        Set<Case> casesEmpty=null;
        assertThrows(NullPointerException.class,
                ()->{ new PackingService().packing(casesEmpty,orders);});
    }

    @Test
    void testNullOrdersPacking() {
        Set<Order> ordersEmpty=null;
        assertThrows(NullPointerException.class,
                ()->{ new PackingService().packing(cases,ordersEmpty);});
    }
}
