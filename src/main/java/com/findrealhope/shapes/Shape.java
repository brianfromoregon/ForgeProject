package com.findrealhope.shapes;

import com.findrealhope.util.Turtle;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.List;

public interface Shape {

    /**
     *
     * @param turtle This turtle will be positioned and facing at the desired starting place.
     * @param params Optional parameters to customize the shape
     */
    void draw(Turtle turtle, List<String> params);

    String parameters();
}
