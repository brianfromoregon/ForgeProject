package com.findrealhope.brian.turtlescript;

import com.findrealhope.turtle.MinecraftTurtle;
import com.findrealhope.turtle.Turtle;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.concurrent.LinkedBlockingQueue;

public class QueueingTurtleFactory {
    private final LinkedBlockingQueue<PosAndType> toMark = new LinkedBlockingQueue<>(1000);
    private World world;

    /**
     * Create a turtle sitting in front of the player's feet
     */
    public Turtle createTurtle(EntityPlayerMP player) {

        Block type = Blocks.glass;
        ItemStack current = player.inventory.getCurrentItem();
        if (current != null && current.getItem() != null) {
            Block currentBlock = Block.getBlockFromItem(current.getItem());
            // Making box of sand crashes minecraft, I guess because it's a falling type?
            if (currentBlock != null && false == currentBlock instanceof BlockFalling) {
                type = currentBlock;
            }
        }

        if (world == null) {
            world = player.worldObj;
            FMLCommonHandler.instance().bus().register(this);
        }

        Turtle turtle = new MinecraftTurtle(world, type.getBlockState().getBaseState()) {
            @Override
            protected void _mark(BlockPos pos) {
                try {
                    toMark.put(new PosAndType(pos, blockType));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        turtle.reset(player.getPosition(), player.getHorizontalFacing());
        turtle.forward(1);

        return turtle;
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

    private static class PosAndType {
        final BlockPos pos;
        final IBlockState type;

        public PosAndType(BlockPos pos, IBlockState type) {
            this.pos = pos;
            this.type = type;
        }
    }
}
