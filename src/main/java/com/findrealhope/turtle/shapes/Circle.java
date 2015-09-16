package com.findrealhope.turtle.shapes;

import com.findrealhope.turtle.Script;
import com.findrealhope.turtle.Turtle;
import net.minecraft.util.BlockPos;

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
        int d = (5 - radius * 4) / 4;
        int x = 0;
        int y = radius;

        do {
            turtle.reset(center.add(x, y, 0), null).penDown();
            turtle.reset(center.add(x, -y, 0), null).penDown();
            turtle.reset(center.add(-x, y, 0), null).penDown();
            turtle.reset(center.add(-x, -y, 0), null).penDown();
            turtle.reset(center.add(y, x, 0), null).penDown();
            turtle.reset(center.add(y, -x, 0), null).penDown();
            turtle.reset(center.add(-y, x, 0), null).penDown();
            turtle.reset(center.add(-y, -x, 0), null).penDown();
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
