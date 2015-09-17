package com.findrealhope.turtle;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.concurrent.LinkedBlockingQueue;

public class QueueDrainer {

    private final LinkedBlockingQueue<PosAndType> toMark = new LinkedBlockingQueue<>(1000);
    private final Minecraft minecraft;

    public QueueDrainer(Minecraft minecraft) {
        this.minecraft = minecraft;
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (toMark.isEmpty())
            return;

        long stopTime = minecraft.getSystemTime() + 15;
        while (minecraft.getSystemTime() < stopTime) {
            PosAndType next = toMark.poll();
            if (next == null)
                return;

            minecraft.theWorld.setBlockState(next.pos, next.type);
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
