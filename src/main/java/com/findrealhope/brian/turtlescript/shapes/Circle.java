package com.findrealhope.brian.turtlescript.shapes;

import com.findrealhope.brian.turtlescript.Script;
import com.findrealhope.turtle.Turtle;

public class Circle implements Script {

    @Override
    public void draw(Turtle turtle, Context context) {
        int radius = param(context, 0, 5);
        draw(turtle, radius);
    }

    /**
     * From http://rosettacode.org/wiki/Bitmap/Midpoint_circle_algorithm#Java
     */
    public void draw(Turtle turtle, int radius) {
        Marker marker = new Marker(turtle.up(radius));
        int d = (5 - radius * 4) / 4;
        int x = 0;
        int y = radius;

        do {
            marker.reset().turnRight().forward(x).up(y).penDown();
            marker.reset().turnRight().forward(x).down(y).penDown();
            marker.reset().turnLeft().forward(x).up(y).penDown();
            marker.reset().turnLeft().forward(x).down(y).penDown();
            marker.reset().turnRight().forward(y).up(x).penDown();
            marker.reset().turnRight().forward(y).down(x).penDown();
            marker.reset().turnLeft().forward(y).up(x).penDown();
            marker.reset().turnLeft().forward(y).down(x).penDown();
            if (d < 0) {
                d += 2 * x + 1;
            } else {
                d += 2 * (x - y) + 1;
                y--;
            }
            x++;
        } while (x <= y);

    }

    @Override
    public String parameters() {
        return "radius";
    }
}
