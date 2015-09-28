package com.findrealhope.brian.turtlescript.shapes;

import com.findrealhope.brian.turtlescript.Script;
import com.findrealhope.turtle.Turtle;

import static java.lang.Math.abs;

public class Sphere implements Script {
    Circle circle = new Circle();

    @Override
    public void draw(Turtle turtle, Context context) {
        int radius = param(context, 0, 5);
        draw(turtle, radius);
    }

    // lame circle, we can do better!
    public void draw(Turtle turtle, int radius) {
        Marker marker = new Marker(turtle);
        for (int i = -radius; i <= radius; i++) {
            marker.reset().forward(i + radius).up(abs(i));
            circle.draw(turtle, radius - abs(i));
        }
    }

    @Override
    public String parameters() {
        return "radius";
    }
}
