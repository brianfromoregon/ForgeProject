package com.findrealhope.shapes;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PlaneTest extends ShapeTest {
    private Plane shape = new Plane();

    Plane plane() {
        return shape;
    }

    @Test
    public void _0x1() {
        plane().draw(turtle, 0, 1);
        verifyOnlyMarked();
    }

    @Test
    public void _1x0() {
        plane().draw(turtle, 1, 0);
        verifyOnlyMarked();
    }

    @Test
    public void _1x1() {
        plane().draw(turtle, 1, 1);
        verifyOnlyMarked(xyz(4, 0, 0));
    }

    @Test
    public void _1x2() {
        turtle.assertNoDups = false;
        plane().draw(turtle, 1, 2);
        verifyOnlyMarked(xyz(4, 0, 0), xyz(4, 1, 0));
    }

    @Test
    public void _2x1() {
        plane().draw(turtle, 2, 1);
        verifyOnlyMarked(xyz(3, 0, 0), xyz(4, 0, 0));
    }

    @Test
    public void _2x2() {
        plane().draw(turtle, 2, 2);
        verifyOnlyMarked(xyz(3, 0, 0), xyz(4, 0, 0), xyz(3, 1, 0), xyz(4, 1, 0));
    }

    @Test
    public void _3x3() {
        plane().draw(turtle, 3, 3);
        verifyOnlyMarked(
                xyz(3, 0, 0), xyz(4, 0, 0), xyz(5, 0, 0),
                xyz(3, 1, 0), xyz(4, 1, 0), xyz(5, 1, 0),
                xyz(3, 2, 0), xyz(4, 2, 0), xyz(5, 2, 0));
    }

    @Test
    public void _3x4() {
        turtle.assertNoDups = false;
        plane().draw(turtle, 3, 4);
        verifyOnlyMarked(
                xyz(3, 0, 0), xyz(4, 0, 0), xyz(5, 0, 0),
                xyz(3, 1, 0), xyz(4, 1, 0), xyz(5, 1, 0),
                xyz(3, 2, 0), xyz(4, 2, 0), xyz(5, 2, 0),
                xyz(3, 3, 0), xyz(4, 3, 0), xyz(5, 3, 0));
    }

    @Test
    public void _4x4() {
        plane().draw(turtle, 4, 4);
        verifyOnlyMarked(
                xyz(2, 0, 0), xyz(3, 0, 0), xyz(4, 0, 0), xyz(5, 0, 0),
                xyz(2, 1, 0), xyz(3, 1, 0), xyz(4, 1, 0), xyz(5, 1, 0),
                xyz(2, 2, 0), xyz(3, 2, 0), xyz(4, 2, 0), xyz(5, 2, 0),
                xyz(2, 3, 0), xyz(3, 3, 0), xyz(4, 3, 0), xyz(5, 3, 0));
    }
}