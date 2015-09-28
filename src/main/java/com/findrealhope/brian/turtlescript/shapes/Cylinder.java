package com.findrealhope.brian.turtlescript.shapes;

import com.findrealhope.brian.turtlescript.Script;
import com.findrealhope.turtle.Turtle;

public class Cylinder implements Script {

    Circle circle = new Circle();
    Disc disc = new Disc();

    @Override
    public void draw(Turtle turtle, Context context) {
        int radius = param(context, 0, 4);
        int depth = param(context, 1, 8);
        draw(turtle, radius, depth);
    }

    public void draw(Turtle turtle, int radius, int depth) {
        if (radius == 0 || depth == 0)
            return;

        Marker marker = new Marker(turtle);
        disc.draw(turtle, radius);
        for (int i = 0; i < depth - 2; i++) {
            marker.reset().forward(i + 1);
            circle.draw(turtle, radius);
        }
        marker.reset().forward(depth - 1);
        disc.draw(turtle, radius);
    }

    @Override
    public String parameters() {
        return "radius depth";
    }
}
