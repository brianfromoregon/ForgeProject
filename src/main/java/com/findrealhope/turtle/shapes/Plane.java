package com.findrealhope.turtle.shapes;

import com.findrealhope.turtle.Script;
import com.findrealhope.turtle.Turtle;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class Plane implements Script {

    Rectangle rectangle = new Rectangle();

    @Override
    public void draw(Turtle turtle, Context context) {
        int width = param(context, 0, 4);
        int depth = param(context, 1, width);
        draw(turtle, width, depth);
    }

    public void draw(Turtle turtle, int width, int depth) {
        BlockPos startP = turtle.position();
        EnumFacing startF = turtle.facing();

        for (int i = 0; width > 0 && depth > 0; width -= 2, depth -= 2) {
            rectangle.draw(turtle, width, depth);
            turtle.reset(startP, startF).forward(++i);
        }
    }

    @Override
    public String parameters() {
        return "width depth";
    }
}
