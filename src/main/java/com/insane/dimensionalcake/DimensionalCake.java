package com.insane.dimensionalcake;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid=DimensionalCake.MODID, name="Dimensional Cake", version=DimensionalCake.VERSION)
public class DimensionalCake {
	
	public static final String MODID = "dimensionalcake";
	public static final String VERSION = "0.0.1";
	
	@Instance(MODID)
	public static DimensionalCake instance;
	
	@SidedProxy(clientSide="com.insane.dimensionalcake.ClientProxy", serverSide="com.insane.dimensionalcake.CommonProxy")
	public static CommonProxy proxy;
	
	public static Configuration config;
	public static boolean eatCakeWhenFull;
	
	public static Block blockEndCake;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		config = new Configuration(event.getSuggestedConfigurationFile());
		eatCakeWhenFull = config.get("general","eatWhenFull", true).getBoolean();
		if (config.hasChanged())
			config.save();
		
		blockEndCake = new BlockEndCake();
		GameRegistry.registerBlock(blockEndCake, "blockEndCake");
		
		GameRegistry.addRecipe(new ItemStack(blockEndCake), new Object[]{"aaa","aba","aaa",'a',Items.ender_eye,'b',Items.cake});
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}

}
