package com.findrealhope;

import com.findrealhope.shapes.Box;
import com.findrealhope.shapes.Plane;
import com.findrealhope.shapes.Rectangle;
import com.findrealhope.shapes.ShapeUtil;
import com.findrealhope.util.MinecraftTurtle;
import com.findrealhope.util.Turtle;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;
import java.util.List;

public class ShapeChat implements ShapeUtil {
    
    @SubscribeEvent
    public void chatEvent(ServerChatEvent event) {
        EntityPlayerMP player = event.player;
        Direction dir = Direction.fromFacing(player.getHorizontalFacing());
        BlockPos playerPos = player.getPosition();
        List<String> words = Arrays.asList(event.message.split(" "));
        Block type = Blocks.glass;
        ItemStack current = player.inventory.getCurrentItem();
        if (current != null && current.getItem() != null) {
            Block currentBlock = Block.getBlockFromItem(current.getItem());
            // Making box of sand crashes minecraft, I guess because it's a falling type?
            if (currentBlock != null && false == currentBlock instanceof BlockFalling) {
                type = currentBlock;
            }
        }

        if (!words.isEmpty() && words.get(0).equalsIgnoreCase("erase")) {
            type = Blocks.air;
            words = words.subList(1, words.size());
        }

        if (words.isEmpty()) {
            return;
        }

        String shape = words.get(0);
        List<String> params = words.subList(1, words.size());
        
        World world = player.worldObj;
        Turtle turtle = new MinecraftTurtle(world, type.getBlockState().getBaseState());
        turtle.reset(playerPos, player.getHorizontalFacing());
        turtle.forward(1);
        
        if (shape.equalsIgnoreCase("cylinder")) {
            int radius = param(params, 0, 4);
            int length = param(params, 1, 8);
            drawCylinder(world, type, playerPos, radius, length);
        } else if (shape.equalsIgnoreCase("circle")) {
            int radius = param(params, 0, 4);
            drawCircle(world, type, playerPos, radius);
        } else if (shape.equalsIgnoreCase("prism")) {
            int width = param(params, 0, 4);
            int depth = param(params, 0, 8);
            drawPrism(world, type, playerPos, width, depth);
        } else if (shape.equalsIgnoreCase("line")) {
            int length = param(params, 0, 8);
            Vec3 look = player.getLookVec();
            BlockPos end = playerPos.add(Math.round(look.xCoord * length), Math.round(look.yCoord * length), Math.round(look.zCoord * length));
            drawLine(world, type, playerPos, end);
        } else if (shape.equalsIgnoreCase("sphere")) {
            int radius = param(params, 0, 4);
            drawSphere(world, type, playerPos, radius);
        } else if (shape.equalsIgnoreCase("box")) {
            new Box().draw(turtle, params);
        } else if (shape.equalsIgnoreCase("plane")) {
            new Plane().draw(turtle, params);
        } else if (shape.equalsIgnoreCase("rectangle")) {
            new Rectangle().draw(turtle, params);
        }
    }

    void drawPrism(World world, Block type, BlockPos center, int width, int depth) {
//        BlockPos step = center;
//        for (int i = 0; i <= radius; i++) {
//            drawTriangle(world, type, step, i);
//        }
//        for (int i = 0; i < length-2; i++) {
//            step = step.add(0, 0, 1);
//            drawTriangle(world, type, step, radius);
//        }
//        step = step.add(0, 0, 1);
//        for (int i = 0; i <= radius; i++) {
//            drawTriangle(world, type, step, i);
//        }
    }

    private void drawTriangle(World world, Block type, BlockPos center, int height) {
//        BlockPos step = center;
//        for (int i = 0; i < height; i++) {
//            step = center.add(-(i/2), 0, i + 1);
//            for (int j = 0; j < height - i; j++) {
//                step = step.add()
//            }
//        }
    }

    /**
     * This sphere is lame... fix it!
     */
    void drawSphere(World world, Block type, BlockPos center, int radius) {
        BlockPos step = center.add(0, 0, -radius);
        for (int i = -radius; i < radius; i++) {
            step = step.add(0, 0, 1);
            drawCircle(world, type, step, radius - Math.abs(i));
        }
    }

    void drawCylinder(World world, Block type, BlockPos center, int radius, int length) {
        BlockPos step = center;
        for (int i = 0; i <= radius; i++) {
            drawCircle(world, type, step, i);
        }
        for (int i = 0; i < length - 2; i++) {
            step = step.add(0, 0, 1);
            drawCircle(world, type, step, radius);
        }
        step = step.add(0, 0, 1);
        for (int i = 0; i <= radius; i++) {
            drawCircle(world, type, step, i);
        }
    }

    /**
     * From http://rosettacode.org/wiki/Bitmap/Midpoint_circle_algorithm#Java
     */
    void drawCircle(World world, Block type, BlockPos center, int radius) {
        IBlockState state = type.getBlockState().getBaseState();
        int d = (5 - radius * 4) / 4;
        int x = 0;
        int y = radius;

        do {
            world.setBlockState(center.add(x, y, 0), state);
            world.setBlockState(center.add(x, -y, 0), state);
            world.setBlockState(center.add(-x, y, 0), state);
            world.setBlockState(center.add(-x, -y, 0), state);
            world.setBlockState(center.add(y, x, 0), state);
            world.setBlockState(center.add(y, -x, 0), state);
            world.setBlockState(center.add(-y, x, 0), state);
            world.setBlockState(center.add(-y, -x, 0), state);
            if (d < 0) {
                d += 2 * x + 1;
            } else {
                d += 2 * (x - y) + 1;
                y--;
            }
            x++;
        } while (x <= y);

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
    enum Direction {
        NORTH, EAST, SOUTH, WEST;

        public BlockPos move(BlockPos pos, int distance) {
            if (this == NORTH) {
                return pos.north(distance);
            } else if (this == EAST) {
                return pos.east(distance);
            } else if (this == SOUTH) {
                return pos.south(distance);
            } else {
                return pos.west(distance);
            }
        }

        public Direction left() {
            if (this == NORTH) {
                return WEST;
            } else if (this == EAST) {
                return NORTH;
            } else if (this == SOUTH) {
                return EAST;
            } else {
                return SOUTH;
            }
        }

        public Direction right() {
            if (this == NORTH) {
                return EAST;
            } else if (this == EAST) {
                return SOUTH;
            } else if (this == SOUTH) {
                return WEST;
            } else {
                return NORTH;
            }
        }

        public static Direction fromFacing(EnumFacing facing) {
            if (facing == EnumFacing.NORTH) {
                return NORTH;
            } else if (facing == EnumFacing.EAST) {
                return EAST;
            } else if (facing == EnumFacing.SOUTH) {
                return SOUTH;
            } else {
                return WEST;
            }
        }
    }

}