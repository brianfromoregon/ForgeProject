package com.findrealhope.examples;

import com.findrealhope.MainMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class PurpleBlock extends Block {
    public static final String name = "purple_block";
    public static final PurpleBlock instance = new PurpleBlock(Material.ground);

    private PurpleBlock(Material material) {
        super(material);
        setUnlocalizedName(name);
        setCreativeTab(CreativeTabs.tabMisc);
    }

    // Call during FMLPreInitializationEvent
    public static void register() {
        GameRegistry.registerBlock(instance, name);
    }
}