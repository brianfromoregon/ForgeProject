package com.findrealhope.examples;

import com.findrealhope.turtle.Turtle;
import com.findrealhope.turtle.TurtleFactory;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TurtleTips {

    TurtleFactory turtleFactory = new TurtleFactory();

    @SubscribeEvent
    public void drawWithTips(ServerChatEvent event) {
        if (event.message.equalsIgnoreCase("tips")) {
            Turtle turtle = turtleFactory.createTurtle(event.player);


            ///////////////////////////////////////////////////////////////////////
            ////////////               FIRST TIP                      /////////////
            ///////////////////////////////////////////////////////////////////////


            /*
            The turtle draws with whatever type of block you select in your inventory. If nothing is selected
            then it draws with glass. But you can also change the block type in code as shown below.

            Try putting your cursor right between "Blocks." and "tnt" below, then press CTRL+Space to see
            all of the block types.
             */
            turtle.penType(Blocks.tnt);


            ///////////////////////////////////////////////////////////////////////
            ////////////               NEXT TIP                      //////////////
            ///////////////////////////////////////////////////////////////////////


            /*
            You can chain many turtle instructions on a single line.
             */

            turtle.penDown().forward(3).turnLeft().forward(4).turnRight().up(2).penUp();


            ///////////////////////////////////////////////////////////////////////
            ////////////               NEXT TIP                      //////////////
            ///////////////////////////////////////////////////////////////////////


            /*
            You can organize your code into functions however you want and pass the turtle around.

            Below we call some functions which are defined even further down.
             */

            drawLine(turtle, 4);
            drawSkyscraper(turtle, 42);
            drawLine(turtle, 6);
        }
    }

    void drawLine(Turtle turtle, int length) {
        turtle.penDown();
        turtle.forward(length - 1);
        turtle.penUp();
    }

    void drawSkyscraper(Turtle turtle, int stories) {
        // I guess this is your homework!
    }
}
