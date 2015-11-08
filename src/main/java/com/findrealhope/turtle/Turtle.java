package com.findrealhope.turtle;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

/**
 * Akin to "turtle graphics"
 * https://en.wikipedia.org/wiki/Turtle_graphics
 */
public interface Turtle {

    // Simple methods
    Turtle forward(int blocks);
    Turtle back(int blocks);
    Turtle up(int blocks);
    Turtle down(int blocks);
    Turtle turnLeft();
    Turtle turnRight();
    Turtle penDown();
    Turtle penUp();


    // Advanced methods
    Turtle jumpTo(BlockPos position);
    BlockPos position();
    EnumFacing facing();
    Turtle face(EnumFacing facing);
    Turtle penType(IBlockState type);
    boolean isPenDown();
    default Turtle penType(Block type) {
        return penType(type.getBlockState().getBaseState());
    }
}
