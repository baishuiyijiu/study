package com.lilihuang;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RectangleTest {

    private Rectangle rectangle;

    @Before
    public void setup() {
        rectangle = new Rectangle(5, 10);
    }


    @Test
    public void should_get_rectangle_area() {
        //Given
        //When
        double area = rectangle.getArea();
        //Then
        Assert.assertEquals(50, area, 0.0);
    }

    @Test
    public void should_get_rectangle_perimeter() {
        //Given
        //When
        double perimeter = rectangle.getPerimeter();
        //Then
        Assert.assertEquals(30, perimeter, 0.00);
    }
}
