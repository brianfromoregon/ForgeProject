package com.findrealhope.brian.turtlescript.shapes;

import com.findrealhope.turtle.Turtle;
import org.junit.Test;

public class BoxTest extends PlaneTest {
    Box shape = new Box();
    
    Plane asPlane = new Plane() {
        @Override
        public void draw(Turtle turtle, int width, int depth) {
            shape.draw(turtle, width, depth, 1);
        }
    };

    @Override
    Plane plane() {
        return asPlane;
    }

    @Test
    public void _3x3x2() {
        shape.draw(turtle, 3, 3, 2);
        verifyOnlyMarked(
                xyz(3, 0, 0), xyz(4, 0, 0), xyz(5, 0, 0),
                xyz(3, 1, 0), xyz(4, 1, 0), xyz(5, 1, 0),
                xyz(3, 2, 0), xyz(4, 2, 0), xyz(5, 2, 0),
                xyz(3, 0, 1), xyz(4, 0, 1), xyz(5, 0, 1),
                xyz(3, 1, 1), xyz(4, 1, 1), xyz(5, 1, 1),
                xyz(3, 2, 1), xyz(4, 2, 1), xyz(5, 2, 1));
    }

    @Test
    public void _3x3x3() {
        shape.draw(turtle, 3, 3, 3);
        verifyOnlyMarked(
                xyz(3, 0, 0), xyz(4, 0, 0), xyz(5, 0, 0),
                xyz(3, 1, 0), xyz(4, 1, 0), xyz(5, 1, 0),
                xyz(3, 2, 0), xyz(4, 2, 0), xyz(5, 2, 0),
                xyz(3, 0, 1), xyz(4, 0, 1), xyz(5, 0, 1),
                xyz(3, 1, 1), xyz(5, 1, 1),
                xyz(3, 2, 1), xyz(4, 2, 1), xyz(5, 2, 1),
                xyz(3, 0, 2), xyz(4, 0, 2), xyz(5, 0, 2),
                xyz(3, 1, 2), xyz(4, 1, 2), xyz(5, 1, 2),
                xyz(3, 2, 2), xyz(4, 2, 2), xyz(5, 2, 2));
    }

    @Test
    public void _3x3x4() {
        shape.draw(turtle, 3, 3, 4);
        verifyOnlyMarked(
                xyz(3, 0, 0), xyz(4, 0, 0), xyz(5, 0, 0),
                xyz(3, 1, 0), xyz(4, 1, 0), xyz(5, 1, 0),
                xyz(3, 2, 0), xyz(4, 2, 0), xyz(5, 2, 0),
                xyz(3, 0, 1), xyz(4, 0, 1), xyz(5, 0, 1),
                xyz(3, 1, 1), xyz(5, 1, 1),
                xyz(3, 2, 1), xyz(4, 2, 1), xyz(5, 2, 1),
                xyz(3, 0, 2), xyz(4, 0, 2), xyz(5, 0, 2),
                xyz(3, 1, 2), xyz(5, 1, 2),
                xyz(3, 2, 2), xyz(4, 2, 2), xyz(5, 2, 2),
                xyz(3, 0, 3), xyz(4, 0, 3), xyz(5, 0, 3),
                xyz(3, 1, 3), xyz(4, 1, 3), xyz(5, 1, 3),
                xyz(3, 2, 3), xyz(4, 2, 3), xyz(5, 2, 3));
    }

    @Test
    public void _5x5x3() {
        shape.draw(turtle, 5, 5, 3);
        verifyOnlyMarked(
                xyz(2, 0, 0), xyz(3, 0, 0),  xyz(4, 0, 0), xyz(5, 0, 0), xyz(6, 0, 0),
                xyz(2, 1, 0), xyz(3, 1, 0),  xyz(4, 1, 0), xyz(5, 1, 0), xyz(6, 1, 0),
                xyz(2, 2, 0), xyz(3, 2, 0),  xyz(4, 2, 0), xyz(5, 2, 0), xyz(6, 2, 0),
                xyz(2, 3, 0), xyz(3, 3, 0),  xyz(4, 3, 0), xyz(5, 3, 0), xyz(6, 3, 0),
                xyz(2, 4, 0), xyz(3, 4, 0),  xyz(4, 4, 0), xyz(5, 4, 0), xyz(6, 4, 0),

                xyz(2, 0, 1), xyz(3, 0, 1),  xyz(4, 0, 1), xyz(5, 0, 1), xyz(6, 0, 1),
                xyz(2, 1, 1), xyz(6, 1, 1),
                xyz(2, 2, 1), xyz(6, 2, 1),
                xyz(2, 3, 1), xyz(6, 3, 1),
                xyz(2, 4, 1), xyz(3, 4, 1),  xyz(4, 4, 1), xyz(5, 4, 1), xyz(6, 4, 1),

                xyz(2, 0, 2), xyz(3, 0, 2),  xyz(4, 0, 2), xyz(5, 0, 2), xyz(6, 0, 2),
                xyz(2, 1, 2), xyz(3, 1, 2),  xyz(4, 1, 2), xyz(5, 1, 2), xyz(6, 1, 2),
                xyz(2, 2, 2), xyz(3, 2, 2),  xyz(4, 2, 2), xyz(5, 2, 2), xyz(6, 2, 2),
                xyz(2, 3, 2), xyz(3, 3, 2),  xyz(4, 3, 2), xyz(5, 3, 2), xyz(6, 3, 2),
                xyz(2, 4, 2), xyz(3, 4, 2),  xyz(4, 4, 2), xyz(5, 4, 2), xyz(6, 4, 2));
    }
}