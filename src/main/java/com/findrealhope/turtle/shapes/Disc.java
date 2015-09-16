package com.findrealhope.turtle.shapes;

import com.findrealhope.turtle.Script;
import com.findrealhope.turtle.Turtle;
import net.minecraft.util.BlockPos;

public class Disc implements Script {
    Circle circle = new Circle();

    @Override
    public void draw(Turtle turtle, Context context) {
        int radius = param(context, 0, 5);
        draw(turtle, radius);
    }

    public void draw(Turtle turtle, int radius) {
        BlockPos start = turtle.position();
        for (int i = 0; i <= radius; i++) {
            turtle.reset(start, null).up(radius - i);
            circle.draw(turtle, i);
        }
    }

    @Override
    public String parameters() {
        return "radius";
    }
}
