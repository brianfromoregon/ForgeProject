package com.findrealhope.turtle.shapes;

import com.findrealhope.turtle.Script;
import com.findrealhope.turtle.Turtle;

public class Rectangle implements Script {

    @Override
    public void draw(Turtle turtle, Context context) {
        int width = param(context, 0, 4);
        int depth = param(context, 1, width);
        draw(turtle, width, depth);
    }

    public void draw(Turtle turtle, int width, int depth) {
        if (width <= 0 || depth <= 0)
            return;

        turtle.left().forward(width / 2).penDown() // start in SW corner
                .right().forward(depth - 1) // left side
                .right().forward(width - 1) // back side
                .right().forward(depth - 1) // right side
                .right().forward(width - 2); // front side
    }

    @Override
    public String parameters() {
        return "width depth";
    }
}
