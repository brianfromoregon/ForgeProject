package com.findrealhope.brian.turtlescript.shapes;

import com.findrealhope.brian.turtlescript.Script;
import com.findrealhope.turtle.Turtle;

public class Disc implements Script {
    Circle circle = new Circle();

    @Override
    public void draw(Turtle turtle, Context context) {
        int radius = param(context, 0, 5);
        draw(turtle, radius);
    }

    public void draw(Turtle turtle, int radius) {
        Marker marker = new Marker(turtle);
        for (int i = 0; i <= radius; i++) {
            marker.reset().up(radius - i);
            circle.draw(turtle, i);
        }
    }

    @Override
    public String parameters() {
        return "radius";
    }
}
