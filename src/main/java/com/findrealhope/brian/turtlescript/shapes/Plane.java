package com.findrealhope.brian.turtlescript.shapes;

import com.findrealhope.brian.turtlescript.Script;
import com.findrealhope.turtle.Turtle;

public class Plane implements Script {

    Rectangle rectangle = new Rectangle();

    @Override
    public void draw(Turtle turtle, Context context) {
        int width = param(context, 0, 4);
        int depth = param(context, 1, width);
        draw(turtle, width, depth);
    }

    public void draw(Turtle turtle, int width, int depth) {
        Marker marker = new Marker(turtle);

        for (int i = 0; width > 0 && depth > 0; width -= 2, depth -= 2) {
            rectangle.draw(turtle, width, depth);
            marker.reset().forward(++i);
        }
    }

    @Override
    public String parameters() {
        return "width depth";
    }
}
