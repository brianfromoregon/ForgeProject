package com.findrealhope.brian.turtlescript.shapes;

import com.findrealhope.brian.turtlescript.Script;
import com.findrealhope.turtle.Turtle;

public class Prism implements Script {

    Triangle triangle = new Triangle();

    @Override
    public void draw(Turtle turtle, Context context) {
        int width = param(context, 0, 7);
        int depth = param(context, 1, 5);
        int height = param(context, 2, 4);
        draw(turtle, width, depth, height);
    }

    public void draw(Turtle turtle, int width, int depth, int height) {
        if (height == 0 || width == 0)
            return;

        Marker marker = new Marker(turtle);
        triangle.drawSolid(turtle, width, height);
        for (int i = 0; i < depth - 2; i++) {
            marker.reset().forward(i + 1);
            triangle.draw(turtle, width, height);
        }
        marker.reset().forward(depth - 1);
        triangle.drawSolid(turtle, width, height);
    }

    @Override
    public String parameters() {
        return "width depth height";
    }
}
