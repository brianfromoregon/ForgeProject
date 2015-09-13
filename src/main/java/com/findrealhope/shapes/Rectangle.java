package com.findrealhope.shapes;

import com.findrealhope.util.Turtle;

import java.util.List;

public class Rectangle implements Shape, ShapeUtil {

    @Override
    public void draw(Turtle turtle, List<String> params) {
        int width = param(params, 0, 4);
        int depth = param(params, 1, width);
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
