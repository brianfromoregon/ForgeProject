package com.findrealhope;

import com.findrealhope.turtle.MinecraftTurtle;
import com.findrealhope.turtle.Script;
import com.findrealhope.turtle.Turtle;
import com.findrealhope.turtle.shapes.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TurtleScriptMod {

    Map<String, Script> scripts;

    public TurtleScriptMod() {
        this.scripts = new HashMap<>();
        scripts.put("box", new Box());
        scripts.put("cube", new Cube());
        scripts.put("circle", new Circle());
        scripts.put("cylinder", new Cylinder());
        scripts.put("disc", new Disc());
        scripts.put("line", new Line());
        scripts.put("plane", new Plane());
        scripts.put("rectangle", new Rectangle());
        scripts.put("sphere", new Sphere());
        scripts.put("vector", new Vector());
    }

    @SubscribeEvent
    public void chatEvent(ServerChatEvent event) {
        EntityPlayerMP player = event.player;
        List<String> words = Arrays.asList(event.message.split(" "));
        if (words.isEmpty()) return;

        boolean erase = false;
        if (words.get(0).equalsIgnoreCase("erase")) {
            erase = true;
            words = words.subList(1, words.size());
        }
        if (words.isEmpty()) return;

        Script script = scripts.get(words.get(0).toLowerCase());
        if (script == null) return;

        Block type;
        if (erase) {
            type = Blocks.air;
        } else {
            type = Blocks.glass;
            ItemStack current = player.inventory.getCurrentItem();
            if (current != null && current.getItem() != null) {
                Block currentBlock = Block.getBlockFromItem(current.getItem());
                // Making box of sand crashes minecraft, I guess because it's a falling type?
                if (currentBlock != null && false == currentBlock instanceof BlockFalling) {
                    type = currentBlock;
                }
            }
        }

        List<String> args = words.subList(1, words.size());

        Turtle turtle = new MinecraftTurtle(player.worldObj, type.getBlockState().getBaseState());
        turtle.reset(player.getPosition(), player.getHorizontalFacing());
        turtle.forward(1);

        script.draw(turtle, new Script.Context() {
            @Override
            public List<String> arguments() {
                return args;
            }

            @Override
            public Vec3 playerFacing() {
                return player.getLookVec();
            }
        });
    }
}