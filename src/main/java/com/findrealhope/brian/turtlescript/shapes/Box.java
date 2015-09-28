package com.findrealhope.brian.turtlescript.shapes;

import com.findrealhope.brian.turtlescript.Script;
import com.findrealhope.turtle.Turtle;

public class Box implements Script {
    Rectangle rectangle = new Rectangle();
    Plane plane = new Plane();

    @Override
    public void draw(Turtle turtle, Context context) {
        int width = param(context, 0, 4);
        int depth = param(context, 1, width);
        int height = param(context, 2, width);

        draw(turtle, width, depth, height);
    }

    public void draw(Turtle turtle, int width, int depth, int height) {
        Marker marker = new Marker(turtle);

        if (width <= 0 || depth <= 0 || height <= 0)
            return;

        plane.draw(turtle, width, depth);

        for (int level = 1; level < height - 1; level++) {
            marker.reset().up(level);
            rectangle.draw(turtle, width, depth);
        }

        if (height > 1) {
            marker.reset().up(height - 1);
            plane.draw(turtle, width, depth);
        }
    }

    @Override
    public String parameters() {
        return "width depth height";
    }
}
