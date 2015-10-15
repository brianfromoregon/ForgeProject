package com.findrealhope.examples;

import com.findrealhope.turtle.Turtle;
import com.findrealhope.turtle.TurtleFactory;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Chat the word 'draw' to trigger the below event handler.
 */
public class ExampleTurtleMod {

    TurtleFactory turtleFactory = new TurtleFactory();

    @SubscribeEvent
    public void drawSomethingCool(ServerChatEvent event) {
        if (event.message.equalsIgnoreCase("draw")) {
            Turtle turtle = turtleFactory.createTurtle(event.player);

            turtle.penDown();

            for (int i = 0; i < 10; i++) {
                turtle.forward(2);
            }
        }
    }
}
