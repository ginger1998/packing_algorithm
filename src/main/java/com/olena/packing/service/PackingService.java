package com.olena.packing.service;

import com.olena.packing.domain.Case;
import com.olena.packing.domain.Order;
import com.olena.packing.domain.Product;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * @author ginger1998
 */
public class PackingService {

    //приймає набір коробок та набір замовлень
    //повертає найоптимальніше запаковане замовлення та коробку, у яку його запакували
    //(найоптимальніше запаковане замовлення - це те замовлення, яке залишає найменший вільний об'єм у коробці, в яку його запакували)
    public Map.Entry<Case, Order> packing(Set<Case> cases, Set<Order> orders){
        //оптимальне значення об'єму вльного простору коробки
        int freeVolume=Integer.MAX_VALUE;
        Case targetCase=new Case();
        Order targetOrder=new Order();
        for (Order order:orders
        ) {
            Product product=order.getProduct();
            //створюємо масив з розмірами продукту та сортуємо його
            Integer[] productSize={product.getSizeX(),product.getSizeY(),product.getSizeZ()};
            Arrays.sort(productSize, Collections.reverseOrder());
            for (Case aCase:cases
            ) {
                //створюємо масив з розмірами поточної коробки та сортуємо його
                Integer[] caseSize={aCase.getSizeX(),aCase.getSizeY(),aCase.getSizeZ()};
                Arrays.sort(caseSize,Collections.reverseOrder());
                //оскільки замовлення складається з певної к-сті одного продукту,
                // то знаходимо, яка максимальна кількість цього товару вміститься в коробці
                int amount=1;
                // обчислюємо масив, що визначає скільки разів продукт вміщується по кожній стороні окремо
                int[] count=new int[caseSize.length];
                for(int i=0;i<caseSize.length;i++){
                    count[i]=caseSize[i]/productSize[i];
                    //добуток всіх елементів масиву - це і є максимальна к-сть продукту, що вміщується в коробці
                    amount*=count[i];
                }
                //знаходимо різницю між цією максимальною кількість і тією, що в замовленні
                int tempDiff=amount-order.getNumberOfProducts();
                //якщо різниця від'ємна, це означає, що замовлення у коробку не вміститься, тож ніяких дій не виконуємо
                if(tempDiff>=0){
                    //загальний об'єм вільного простору при даному замовленні
                    int tempFreeVolume=getVolume(caseSize)-order.getNumberOfProducts()*getVolume(productSize);
                    //якщо об'єм вільного простору менший за оптимальний
                    if(tempFreeVolume<freeVolume){
                        //встановлюємо поточний об'єм вільного простору як оптимальний
                        freeVolume=tempFreeVolume;
                        //встановлюєм поточну коробку як оптимальну
                        targetCase=aCase;
                        //встановлюєм поточне замовлення як оптимальне
                        targetOrder=order;
                    }
                }
            }
        }
        //повертаємо елемент відображення,що місить оптимальне замовлення та коробку
        return Map.entry(targetCase,targetOrder);
    }

    public int getVolume(Integer[] sizes){
        int volume=1;
        for (Integer size : sizes) volume *= size;
        return volume;
    }
}
