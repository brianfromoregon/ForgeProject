package com.findrealhope.brian.turtlescript;

import com.findrealhope.turtle.Turtle;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
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
     * @param turtle  This turtle will be positioned and facing at the desired starting place.
     * @param context Additional inputs to this script
     */
    void draw(Turtle turtle, Context context);

    /**
     * @return The parameters this script accepts
     */
    String parameters();

    default int param(Context context, int idx, int def) {
        if (context.arguments().size() > idx) {
            try {
                return Integer.parseInt(context.arguments().get(idx));
            } catch (Exception e) {
            }
        }
        return def;
    }

    default String param(Context context, int idx, String def) {
        if (context.arguments().size() > idx) {
            return context.arguments().get(idx);
        }
        return def;
    }

    class Marker {
        final Turtle turtle;
        final BlockPos startPos;
        final EnumFacing startFacing;
        final boolean penDown;

        public Marker(Turtle turtle) {
            this.turtle = turtle;
            this.startPos = turtle.position();
            this.startFacing = turtle.facing();
            this.penDown = turtle.isPenDown();
        }

        public Turtle reset() {
            if (penDown) {
                turtle.penDown();
            } else {
                turtle.penUp();
            }
            return turtle.jumpTo(startPos).face(startFacing);
        }
    }
}
