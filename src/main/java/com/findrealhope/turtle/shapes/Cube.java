package com.findrealhope.turtle.shapes;

import com.findrealhope.turtle.Script;
import com.findrealhope.turtle.Turtle;
import net.minecraft.util.BlockPos;

import static java.lang.Math.min;

public class Cube implements Script {
    Box box = new Box();

    @Override
    public void draw(Turtle turtle, Context context) {
        int width = param(context, 0, 4);
        int depth = param(context, 1, width);
        int height = param(context, 2, width);

        draw(turtle, width, depth, height);
    }

    public void draw(Turtle turtle, int width, int depth, int height) {
        BlockPos start = turtle.position();
        for (int i = 0; i < min(min(width, depth), height); i++) {
            turtle.reset(start, null).up(i).forward(i);
            box.draw(turtle, width - i, depth - i, height - i);
        }
    }

    @Override
    public String parameters() {
        return "width depth height";
    }
}
