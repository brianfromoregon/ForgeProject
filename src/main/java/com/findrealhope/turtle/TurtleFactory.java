package com.findrealhope.turtle;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class TurtleFactory {

    QueueingTurtleProvider turtleProvider;

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

        if (turtleProvider == null) {
            turtleProvider = new QueueingTurtleProvider(player.worldObj);
            FMLCommonHandler.instance().bus().register(turtleProvider);
        }
        Turtle turtle = turtleProvider.newTurtle(player.worldObj, type.getBlockState().getBaseState());
        return turtle.jumpTo(player.getPosition()).face(player.getHorizontalFacing()).forward(1);
    }
}
