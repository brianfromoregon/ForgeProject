package com.findrealhope.turtle;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

/**
 * Akin to "turtle graphics"
 * https://en.wikipedia.org/wiki/Turtle_graphics
 */
public interface Turtle {
    // State
    BlockPos position();
    EnumFacing facing();
    Turtle reset(BlockPos position, EnumFacing facing);

    // Motion
    Turtle forward(int blocks);
    Turtle up(int blocks);
    Turtle down(int blocks);

    // Facing
    Turtle left();
    Turtle right();

    // Pen
    Turtle penDown();
    Turtle penUp();
}