package com.findrealhope.turtle;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Provides turtles which queue their drawing instructions in memory, to be applied async across ServerTickEvents.
 * Two advantages for long running turtle scripts:
 * 1. You get to see incremental progress
 * 1. Server thread is not "hung" while the script executes
 */
public class QueueingTurtleProvider {

    private final LinkedBlockingQueue<PosAndType> toMark = new LinkedBlockingQueue<>(1000);
    private final World world;

    public QueueingTurtleProvider(World world) {
        this.world = world;
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (toMark.isEmpty())
            return;

        long stopTime = System.currentTimeMillis() + 15;
        while (System.currentTimeMillis() < stopTime) {
            PosAndType next = toMark.poll();
            if (next == null)
                return;

            world.setBlockState(next.pos, next.type);
        }
    }

    public Turtle newTurtle(World world, IBlockState blockType) {
        return new MinecraftTurtle(world, blockType) {
            @Override
            protected void _mark(BlockPos pos) {
                try {
                    toMark.put(new PosAndType(pos, blockType));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        };
    }

    private static class PosAndType {
        final BlockPos pos;
        final IBlockState type;

        public PosAndType(BlockPos pos, IBlockState type) {
            this.pos = pos;
            this.type = type;
        }
    }
}
