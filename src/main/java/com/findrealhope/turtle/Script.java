package com.findrealhope.turtle;

import net.minecraft.util.Vec3;

import java.util.List;

/**
 * A turtle script is a set of instructions for the turtle
 */
public interface Script {

    interface Context {

        /**
         * @return The arguments passed in to this script
         */
        List<String> arguments();

        /**
         * @return The player's facing direction
         */
        Vec3 playerFacing();
    }

    /**
     *
     * @param turtle This turtle will be positioned and facing at the desired starting place.
     * @param context Additional inputs to this script
     */
    void draw(Turtle turtle, Context context);

    /**
     * @return The parameters this script accepts
     */
    String parameters();

    default int param(Context context, int idx, int def) {
        if (context.arguments().size() >= idx) {
            try {
                return Integer.parseInt(context.arguments().get(idx));
            } catch (Exception e) {
            }
        }
        return def;
    }
}
