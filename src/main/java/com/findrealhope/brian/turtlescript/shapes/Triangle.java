package com.findrealhope.brian.turtlescript.shapes;

import com.findrealhope.brian.turtlescript.Script;
import com.findrealhope.turtle.Turtle;
import net.minecraft.util.BlockPos;

public class Triangle implements Script {

    Vector vector = new Vector();

    @Override
    public void draw(Turtle turtle, Context context) {
        int width = param(context, 0, 7);
        int height = param(context, 1, 4);
        draw(turtle, width, height);
    }

    public void drawSolid(Turtle turtle, int width, int height) {
        Marker marker = new Marker(turtle);
        for (int i = 0; i < height; i++) {
            draw(marker.reset(), width, height - i);
        }
    }

    public void draw(Turtle turtle, int width, int height) {
        if (height == 0 || width == 0)
            return;

        BlockPos top = turtle.up(height - 1).position();
        BlockPos left = turtle.down(height - 1).turnLeft().forward(width / 2).position();
        BlockPos right = turtle.turnLeft().turnLeft().forward(width - 1).position();

        vector.draw(turtle, left, top);
        vector.draw(turtle, left, right);
        vector.draw(turtle, right, top);
    }

    @Override
    public String parameters() {
        return "width height";
    }
}
