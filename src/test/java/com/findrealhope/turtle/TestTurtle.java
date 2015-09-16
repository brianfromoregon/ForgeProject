package com.findrealhope.turtle;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

import static org.junit.Assert.assertEquals;

public class TestTurtle extends MinecraftTurtle {
    public byte[][][] space = new byte[9][9][9];
    public boolean assertNoDups = true;

    @Override
    protected BlockPos shifted(EnumFacing direction) {
        if (direction == EnumFacing.NORTH) {
            return position().add(0, 1, 0);
        } else if (direction == EnumFacing.EAST) {
            return position().add(1, 0, 0);
        } else if (direction == EnumFacing.SOUTH) {
            return position().add(0, -1, 0);
        } else if (direction == EnumFacing.WEST) {
            return position().add(-1, 0, 0);
        } else if (direction == EnumFacing.DOWN) {
            return position().add(0, 0, -1);
        } else if (direction == EnumFacing.UP) {
            return position().add(0, 0, 1);
        } else {
            throw new IllegalStateException(direction.getName());
        }
    }
    
    @Override
    protected void _mark(BlockPos pos) {
        if (assertNoDups) {
            assertEquals("dup " + pos, 0, space[pos.getX()][pos.getY()][pos.getZ()]);
        }
        System.out.println("Marking " + pos);
        space[pos.getX()][pos.getY()][pos.getZ()] = 1;
    }
}
