package com.findrealhope.shapes;

import com.findrealhope.util.Turtle;
import net.minecraft.util.Vec3;

import java.util.List;

/**
 * A shape which can be drawn at an angle that is unaligned with minecraft's six cardinal directions
 */
public interface AngledShape extends Shape {

    /**
     * @param direction This vector gives a more precise direction than the turtle
     * @param turtle This turtle will be positioned and facing at the desired starting place.
     * @param params Optional parameters to customize the shape
     */
    void draw(Vec3 direction, Turtle turtle, List<String> params);
}
