package com.findrealhope.brian.turtlescript.shapes;

import com.findrealhope.brian.turtlescript.Script;
import com.findrealhope.turtle.Turtle;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

import static java.lang.Math.round;

public class Vector implements Script {

    @Override
    public void draw(Turtle turtle, Context context) {
        int length = param(context, 0, 5);
        BlockPos begin = turtle.position();
        Vec3 look = context.playerFacing();
        BlockPos end = turtle.position().add(round(look.xCoord * length), round(look.yCoord * length), round(look.zCoord * length));
        draw(turtle, begin, end);
    }

    /**
     * Bresenham 3d line from: https://gist.github.com/yamamushi/5823518
     */
    public void draw(Turtle turtle, BlockPos begin, BlockPos end) {
        int i, dx, dy, dz, l, m, n, x_inc, y_inc, z_inc, err_1, err_2, dx2, dy2, dz2;
        int[] point = new int[3];

        point[0] = begin.getX();
        point[1] = begin.getY();
        point[2] = begin.getZ();
        dx = end.getX() - begin.getX();
        dy = end.getY() - begin.getY();
        dz = end.getZ() - begin.getZ();
        x_inc = (dx < 0) ? -1 : 1;
        l = Math.abs(dx);
        y_inc = (dy < 0) ? -1 : 1;
        m = Math.abs(dy);
        z_inc = (dz < 0) ? -1 : 1;
        n = Math.abs(dz);
        dx2 = l << 1;
        dy2 = m << 1;
        dz2 = n << 1;

        if ((l >= m) && (l >= n)) {
            err_1 = dy2 - l;
            err_2 = dz2 - l;
            for (i = 0; i < l; i++) {
                turtle.jumpTo(new BlockPos(point[0], point[1], point[2])).penDown();
                if (err_1 > 0) {
                    point[1] += y_inc;
                    err_1 -= dx2;
                }
                if (err_2 > 0) {
                    point[2] += z_inc;
                    err_2 -= dx2;
                }
                err_1 += dy2;
                err_2 += dz2;
                point[0] += x_inc;
            }
        } else if ((m >= l) && (m >= n)) {
            err_1 = dx2 - m;
            err_2 = dz2 - m;
            for (i = 0; i < m; i++) {
                turtle.jumpTo(new BlockPos(point[0], point[1], point[2])).penDown();
                if (err_1 > 0) {
                    point[0] += x_inc;
                    err_1 -= dy2;
                }
                if (err_2 > 0) {
                    point[2] += z_inc;
                    err_2 -= dy2;
                }
                err_1 += dx2;
                err_2 += dz2;
                point[1] += y_inc;
            }
        } else {
            err_1 = dy2 - n;
            err_2 = dx2 - n;
            for (i = 0; i < n; i++) {
                turtle.jumpTo(new BlockPos(point[0], point[1], point[2])).penDown();
                if (err_1 > 0) {
                    point[1] += y_inc;
                    err_1 -= dz2;
                }
                if (err_2 > 0) {
                    point[0] += x_inc;
                    err_2 -= dz2;
                }
                err_1 += dy2;
                err_2 += dx2;
                point[2] += z_inc;
            }
        }
        turtle.jumpTo(new BlockPos(point[0], point[1], point[2])).penDown();
    }

    @Override
    public String parameters() {
        return "length";
    }
}
