package com.findrealhope.brian.turtlescript.shapes;

import com.findrealhope.brian.turtlescript.Script;
import com.findrealhope.turtle.Turtle;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class Circle implements Script {

    @Override
    public void draw(Turtle turtle, Context context) {
        int radius = param(context, 0, 5);
        draw(turtle, radius);
    }

    /**
     * From http://rosettacode.org/wiki/Bitmap/Midpoint_circle_algorithm#Java
     */
    public void draw(Turtle turtle, int radius) {
        BlockPos center = turtle.position().up(radius);
        EnumFacing facing = turtle.facing();
        int d = (5 - radius * 4) / 4;
        int x = 0;
        int y = radius;

        do {
            turtle.reset(center, facing).turnRight().forward(x).up(y).penDown();
            turtle.reset(center, facing).turnRight().forward(x).down(y).penDown();
            turtle.reset(center, facing).turnLeft().forward(x).up(y).penDown();
            turtle.reset(center, facing).turnLeft().forward(x).down(y).penDown();
            turtle.reset(center, facing).turnRight().forward(y).up(x).penDown();
            turtle.reset(center, facing).turnRight().forward(y).down(x).penDown();
            turtle.reset(center, facing).turnLeft().forward(y).up(x).penDown();
            turtle.reset(center, facing).turnLeft().forward(y).down(x).penDown();
            if (d < 0) {
                d += 2 * x + 1;
            } else {
                d += 2 * (x - y) + 1;
                y--;
            }
            x++;
        } while (x <= y);

    }

    @Override
    public String parameters() {
        return "radius";
    }
}
