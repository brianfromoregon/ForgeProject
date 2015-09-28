package com.findrealhope.brian.turtlescript.plot;

import com.findrealhope.brian.turtlescript.Script;
import com.findrealhope.turtle.Turtle;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class XYPlot implements Script {
    ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

    @Override
    public void draw(Turtle turtle, Context context) {
        String equation = param(context, 0, "sin(x/2)*2+2");
        int x1 = param(context, 1, 0);
        int x2 = param(context, 2, 15);
        draw(turtle, equation, x1, x2);
    }

    public void draw(Turtle turtle, String equation, int x1, int x2) {
        try {
            Marker marker = new Marker(turtle);
            for (int x = x1; x <= x2; x++) {
                engine.put("x", x);
                Object o = engine.eval("with (Math) {" + equation + "}");
                if (o instanceof Number) {
                    double d = ((Number) o).doubleValue();
                    int y = (int) Math.round(d);
                    marker.reset().forward(x - x1).up(y).penDown();
                } else {
                    return;
                }
            }
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String parameters() {
        return "equation x1 x2";
    }
}
