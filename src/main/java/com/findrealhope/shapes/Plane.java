package com.findrealhope.shapes;

import com.findrealhope.util.Turtle;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

import java.util.List;

public class Plane implements Shape, ShapeUtil {

    Rectangle rectangle = new Rectangle();

    @Override
    public void draw(Turtle turtle, List<String> params) {
        int width = param(params, 0, 4);
        int depth = param(params, 1, width);
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
