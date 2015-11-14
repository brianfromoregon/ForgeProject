package com.findrealhope.examples;

import com.findrealhope.MainMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class PurpleSword extends Item {
    public static final String name = "purple_sword";
    public static final PurpleSword instance = new PurpleSword();

    private PurpleSword() {
        setUnlocalizedName(MainMod.MODID + "_" + name);
        setCreativeTab(CreativeTabs.tabMisc);
    }

    // Call during FMLPreInitializationEvent
    public static void register() {
        GameRegistry.registerItem(instance, name);
    }
}