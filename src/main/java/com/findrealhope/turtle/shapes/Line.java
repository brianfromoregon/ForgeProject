package com.findrealhope.turtle.shapes;

import com.findrealhope.turtle.Script;
import com.findrealhope.turtle.Turtle;

public class Line implements Script {

    @Override
    public void draw(Turtle turtle, Context context) {
        int depth = param(context, 0, 5);
        draw(turtle, depth);
    }

    public void draw(Turtle turtle, int depth) {
        turtle.penDown().forward(depth);
    }

    @Override
    public String parameters() {
        return "depth";
    }
}
