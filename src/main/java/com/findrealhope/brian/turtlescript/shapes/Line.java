package com.findrealhope.brian.turtlescript.shapes;

import com.findrealhope.brian.turtlescript.Script;
import com.findrealhope.turtle.Turtle;

public class Line implements Script {

    @Override
    public void draw(Turtle turtle, Context context) {
        int depth = param(context, 0, 5);
        draw(turtle, depth);
    }

    public void draw(Turtle turtle, int depth) {
        if (depth == 0)
            return;

        turtle.penDown().forward(depth - 1);
    }

    @Override
    public String parameters() {
        return "depth";
    }
}
