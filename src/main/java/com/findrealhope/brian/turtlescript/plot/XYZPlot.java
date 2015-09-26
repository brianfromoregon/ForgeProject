package com.findrealhope.brian.turtlescript.plot;

import com.findrealhope.brian.turtlescript.Script;
import com.findrealhope.turtle.Turtle;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class XYZPlot implements Script {
    ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

    @Override
    public void draw(Turtle turtle, Context context) {
        String equation = param(context, 0, "sin(x)+cos(y)+1");
        int x1 = param(context, 1, 0);
        int x2 = param(context, 2, 15);
        int y1 = param(context, 3, 0);
        int y2 = param(context, 4, 15);
        draw(turtle, equation, x1, x2, y1, y2);
    }

    public void draw(Turtle turtle, String equation, int x1, int x2, int y1, int y2) {
        try {
            BlockPos sPos = turtle.position();
            EnumFacing sFacing = turtle.facing();
            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    engine.put("x", x);
                    engine.put("y", y);
                    Object o = engine.eval("with (Math) {" + equation + "}");
                    if (o instanceof Number) {
                        double d = ((Number) o).doubleValue();
                        int z = (int) Math.round(d);
                        turtle.reset(sPos, sFacing).forward(x - x1).up(z).turnRight().forward(y - y1).penDown();
                    }
                }
            }
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String parameters() {
        return "equation x1 x2 y1 y2";
    }
}
