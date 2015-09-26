package com.findrealhope.brian.turtlescript.shapes;

import com.findrealhope.brian.turtlescript.Script;
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

        turtle.turnLeft().forward(width / 2).penDown() // start in SW corner
                .turnRight().forward(depth - 1) // left side
                .turnRight().forward(width - 1) // back side
                .turnRight().forward(depth - 1) // right side
                .turnRight().forward(width - 2); // front side
    }

    @Override
    public String parameters() {
        return "width depth";
    }
}
