package com.findrealhope.turtle.shapes;

import com.findrealhope.turtle.Script;
import com.findrealhope.turtle.Turtle;
import net.minecraft.util.BlockPos;

public class Cylinder implements Script {

    Circle circle = new Circle();
    Disc disc = new Disc();

    @Override
    public void draw(Turtle turtle, Context context) {
        int radius = param(context, 0, 4);
        int depth = param(context, 1, 8);
        draw(turtle, radius, depth);
    }

    public void draw(Turtle turtle, int radius, int depth) {
        BlockPos start = turtle.position();
        disc.draw(turtle, radius);
        for (int i = 0; i < depth - 2; i++) {
            turtle.reset(start, null).forward(i + 1);
            circle.draw(turtle, radius);
        }
        turtle.reset(start, null).forward(depth - 1);
        disc.draw(turtle, radius);
    }

    @Override
    public String parameters() {
        return "radius depth";
    }
}
