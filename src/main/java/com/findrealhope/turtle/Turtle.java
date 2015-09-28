package com.findrealhope.turtle;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

/**
 * Akin to "turtle graphics"
 * https://en.wikipedia.org/wiki/Turtle_graphics
 */
public interface Turtle {
    // Motion
    Turtle forward(int blocks);
    Turtle back(int blocks);
    Turtle up(int blocks);
    Turtle down(int blocks);
    Turtle jumpTo(BlockPos position);
    BlockPos position();

    // Facing
    EnumFacing facing();
    Turtle face(EnumFacing facing);
    Turtle turnLeft();
    Turtle turnRight();

    // Pen
    Turtle penDown();
    Turtle penUp();
    Turtle penType(Block type);
    boolean isPenDown();
}
