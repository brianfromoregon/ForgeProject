package com.findrealhope.turtle.shapes;

import com.findrealhope.turtle.Script;
import com.findrealhope.turtle.Turtle;
import net.minecraft.util.BlockPos;

import static java.lang.Math.abs;

public class Sphere implements Script {
    Circle circle = new Circle();

    @Override
    public void draw(Turtle turtle, Context context) {
        int radius = param(context, 0, 5);
        draw(turtle, radius);
    }

    // lame circle, we can do better!
    public void draw(Turtle turtle, int radius) {
        BlockPos start = turtle.position();
        for (int i = -radius; i <= radius; i++) {
            turtle.reset(start, null).forward(i + radius).up(abs(i));
            circle.draw(turtle, radius - abs(i));
        }
    }

    @Override
    public String parameters() {
        return "radius";
    }
}
