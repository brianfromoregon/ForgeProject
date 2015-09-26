package com.findrealhope.brian.turtlescript.shapes;

import com.findrealhope.brian.turtlescript.Script;
import com.findrealhope.turtle.Turtle;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class Box implements Script {
    Rectangle rectangle = new Rectangle();
    Plane plane = new Plane();

    @Override
    public void draw(Turtle turtle, Context context) {
        int width = param(context, 0, 4);
        int depth = param(context, 1, width);
        int height = param(context, 2, width);

        draw(turtle, width, depth, height);
    }

    public void draw(Turtle turtle, int width, int depth, int height) {
        BlockPos startP = turtle.position();
        EnumFacing startF = turtle.facing();

        if (width <= 0 || depth <= 0 || height <= 0)
            return;

        plane.draw(turtle, width, depth);

        for (int level = 1; level < height - 1; level++) {
            turtle.reset(startP, startF).up(level);
            rectangle.draw(turtle, width, depth);
        }

        if (height > 1) {
            turtle.reset(startP, startF).up(height - 1);
            plane.draw(turtle, width, depth);
        }
    }

    @Override
    public String parameters() {
        return "width depth height";
    }
}
