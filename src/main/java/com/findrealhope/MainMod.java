package com.findrealhope;

import com.findrealhope.examples.ExampleTurtleMod;
import com.findrealhope.examples.PurpleBlock;
import com.findrealhope.examples.PurpleSword;
import com.findrealhope.examples.TurtleTips;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = MainMod.MODID, version = MainMod.VERSION)
public class MainMod {
    public static final String MODID = "abccoders";
    public static final String VERSION = "1.0";

    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        PurpleSword.register();
        PurpleBlock.register();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ExampleTurtleMod());
        MinecraftForge.EVENT_BUS.register(new TurtleTips());

        if (event.getSide() == Side.CLIENT) {
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            renderItem.getItemModelMesher().register(PurpleSword.instance, 0, new ModelResourceLocation(MODID + ":" + PurpleSword.instance.name, "inventory"));
            renderItem.getItemModelMesher().register(Item.getItemFromBlock(PurpleBlock.instance), 0, new ModelResourceLocation(MODID + ":" + PurpleBlock.instance.name, "inventory"));
        }
    }
}
