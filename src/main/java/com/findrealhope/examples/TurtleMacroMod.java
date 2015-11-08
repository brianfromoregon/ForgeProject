package com.findrealhope.examples;

import com.findrealhope.turtle.Turtle;
import com.findrealhope.turtle.TurtleFactory;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3i;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Record the player's actions for the turtle to replay later.
 */
public class TurtleMacroMod {

    TurtleFactory turtleFactory = new TurtleFactory();
    List<OffsetAndType> macro = new ArrayList<>();
    BlockPos recordingStartPos;
    boolean recording;

    @SubscribeEvent
    public void chatEvent(ServerChatEvent event) {

        if (event.message.contains("record")) {

            Turtle turtle = turtleFactory.createTurtle(event.player);
            recordingStartPos = turtle.position().offset(turtle.facing());
            macro.clear();
            recording = true;

        } else if (event.message.contains("stop")) {

            recording = false;

        } else if (event.message.contains("play")) {

            Turtle turtle = turtleFactory.createTurtle(event.player);

            BlockPos replayStartPos = turtle.position();
            for (OffsetAndType step : macro) {
                turtle
                        .jumpTo(replayStartPos.add(step.offset))
                        .penType(step.type)
                        .penDown()
                        .penUp();
            }
        }
    }

    @SubscribeEvent
    public void blockPlaced(BlockEvent.PlaceEvent event) {
        if (!recording)
            return;

        IBlockState blockType = event.placedBlock.getBlock().getBlockState().getBaseState();
        macro.add(new OffsetAndType(event.pos.subtract(recordingStartPos), blockType));
    }

    static class OffsetAndType {
        public final Vec3i offset;
        public final IBlockState type;

        public OffsetAndType(Vec3i offset, IBlockState type) {
            this.offset = offset;
            this.type = type;
        }
    }

}
