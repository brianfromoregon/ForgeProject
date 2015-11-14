package com.findrealhope;

import com.findrealhope.examples.ExampleTurtleMod;
import com.findrealhope.examples.TurtleTips;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = MainMod.MODID, version = MainMod.VERSION)
public class MainMod {
    public static final String MODID = "abccoders";
    public static final String VERSION = "1.0";

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ExampleTurtleMod());
        MinecraftForge.EVENT_BUS.register(new TurtleTips());
    }
}
