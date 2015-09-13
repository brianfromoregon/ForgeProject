package com.findrealhope.shapes;

import com.findrealhope.util.Turtle;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.List;

public class Line implements AngledShape, ShapeUtil {

    @Override
    public void draw(Vec3 direction, Turtle turtle, List<String> params) {

    }

    @Override
    public void draw(Turtle turtle, List<String> params) {
        int depth = param(params, 0, 5);
        draw(turtle, depth);
    }

    public void draw(Turtle turtle, int depth) {
        turtle.penDown().forward(depth);
    }

    @Override
    public String parameters() {
        return "depth";
    }


    /**
     * Bresenham 3d line from: https://gist.github.com/yamamushi/5823518
     */
    void drawLine(World world, Block type, BlockPos pos1, BlockPos pos2) {
        IBlockState state = type.getBlockState().getBaseState();

        int i, dx, dy, dz, l, m, n, x_inc, y_inc, z_inc, err_1, err_2, dx2, dy2, dz2;
        int[] point = new int[3];

        point[0] = pos1.getX();
        point[1] = pos1.getY();
        point[2] = pos1.getZ();
        dx = pos2.getX() - pos1.getX();
        dy = pos2.getY() - pos1.getY();
        dz = pos2.getZ() - pos1.getZ();
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
                world.setBlockState(new BlockPos(point[0], point[1], point[2]), state);
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
                world.setBlockState(new BlockPos(point[0], point[1], point[2]), state);
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
                world.setBlockState(new BlockPos(point[0], point[1], point[2]), state);
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
        world.setBlockState(new BlockPos(point[0], point[1], point[2]), state);
    }
}
