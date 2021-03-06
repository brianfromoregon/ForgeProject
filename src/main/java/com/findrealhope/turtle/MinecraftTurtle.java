package com.findrealhope.turtle;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class MinecraftTurtle implements Turtle {
    World world;
    protected IBlockState blockType;
    BlockPos pos;
    EnumFacing facing;
    boolean penDown = false;

    public MinecraftTurtle(World world, IBlockState blockType) {
        this.world = world;
        this.blockType = blockType;
    }

    public MinecraftTurtle() {
    }

    @Override
    public Turtle penType(IBlockState type) {
        this.blockType = type;
        return this;
    }

    @Override
    public BlockPos position() {
        return pos;
    }

    @Override
    public EnumFacing facing() {
        return facing;
    }

    @Override
    public Turtle face(EnumFacing facing) {
        this.facing = facing;
        return this;
    }

    @Override
    public Turtle jumpTo(BlockPos position) {
        this.pos = mark(position);
        return this;
    }

    @Override
    public Turtle forward(int blocks) {
        for (int i = 0; i < blocks; i++) {
            pos = mark(shifted(facing));
        }
        return this;
    }

    @Override
    public Turtle back(int blocks) {
        for (int i = 0; i < blocks; i++) {
            pos = mark(shifted(facing.getOpposite()));
        }
        return this;
    }

    @Override
    public Turtle up(int blocks) {
        for (int i = 0; i < blocks; i++) {
            pos = mark(shifted(EnumFacing.UP));
        }
        return this;
    }

    @Override
    public Turtle down(int blocks) {
        for (int i = 0; i < blocks; i++) {
            pos = mark(shifted(EnumFacing.DOWN));
        }
        return this;
    }

    @Override
    public Turtle turnLeft() {
        facing = facing.rotateYCCW();
        return this;
    }

    @Override
    public Turtle turnRight() {
        facing = facing.rotateY();
        return this;
    }

    @Override
    public Turtle penDown() {
        penDown = true;
        mark(pos);
        return this;
    }

    @Override
    public Turtle penUp() {
        penDown = false;
        return this;
    }

    @Override
    public boolean isPenDown() {
        return penDown;
    }

    private BlockPos mark(BlockPos pos) {
        if (penDown) {
            _mark(pos);
        }
        return pos;
    }

    protected void _mark(BlockPos pos) {
        world.setBlockState(pos, blockType);
    }

    protected BlockPos shifted(EnumFacing direction) {
        if (direction == EnumFacing.NORTH) {
            return pos.add(0, 0, -1);
        } else if (direction == EnumFacing.EAST) {
            return pos.add(1, 0, 0);
        } else if (direction == EnumFacing.SOUTH) {
            return pos.add(0, 0, 1);
        } else if (direction == EnumFacing.WEST) {
            return pos.add(-1, 0, 0);
        } else if (direction == EnumFacing.DOWN) {
            return position().add(0, -1, 0);
        } else if (direction == EnumFacing.UP) {
            return position().add(0, 1, 0);
        } else {
            throw new IllegalStateException(direction.getName());
        }
    }
}
