package com.findrealhope.brian.turtlescript;

import com.findrealhope.turtle.*;
import com.findrealhope.brian.turtlescript.plot.XYPlot;
import com.findrealhope.brian.turtlescript.plot.XYZPlot;
import com.findrealhope.brian.turtlescript.shapes.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TurtleChatMod {

    final Map<String, Script> scripts = new LinkedHashMap<>();
    final Executor scriptRunner = Executors.newSingleThreadExecutor(r -> {
        Thread t = new Thread(r);
        t.setName("TurtleChatMod.ScriptRunner");
        t.setDaemon(true);
        return t;
    });
    final QueueingTurtleFactory turtleFactory = new QueueingTurtleFactory();

    public TurtleChatMod() {
        scripts.put("box", new Box());
        scripts.put("cube", new Cube());
        scripts.put("circle", new Circle());
        scripts.put("cylinder", new Cylinder());
        scripts.put("disc", new Disc());
        scripts.put("line", new Line());
        scripts.put("plane", new Plane());
        scripts.put("rectangle", new Rectangle());
        scripts.put("sphere", new Sphere());
        scripts.put("triangle", new Triangle());
        scripts.put("prism", new Prism());
        scripts.put("vector", new Vector());
        scripts.put("xyplot", new XYPlot());
        scripts.put("xyzplot", new XYZPlot());
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
        } else if (words.get(0).equals("?")) {
            showUsage(player);
            return;
        }
        if (words.isEmpty()) return;

        Script script = scripts.get(words.get(0).toLowerCase());
        if (script == null) return;

        List<String> args = words.subList(1, words.size());

        Script.Context context = new Script.Context() {
            @Override
            public List<String> arguments() {
                return args;
            }

            @Override
            public Vec3 playerFacing() {
                return player.getLookVec();
            }
        };

        Turtle turtle = turtleFactory.createTurtle(event.player);
        if (erase) {
            turtle.penType(Blocks.air);
        }

        scriptRunner.execute(() -> {
            script.draw(turtle, context);
        });
    }

    private void showUsage(EntityPlayer player) {
        StringBuilder sb = new StringBuilder();
        sb.append("Turtle scripts usage:\n")
                .append("Start a script with 'erase' and the blocks will be erased. Like: erase cube 5\n");
        for (Map.Entry<String, Script> script : scripts.entrySet()) {
            sb.append("\n").append(script.getKey()).append(" ").append(script.getValue().parameters());
        }
        player.addChatMessage(new ChatComponentText(sb.toString()));
    }
}