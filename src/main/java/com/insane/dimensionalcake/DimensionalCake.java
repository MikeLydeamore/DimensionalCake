package com.insane.dimensionalcake;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

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
	
	public static BlockEndCake blockEndCake;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		config = new Configuration(event.getSuggestedConfigurationFile());
		eatCakeWhenFull = config.get("general","eatWhenFull", true).getBoolean();
		if (config.hasChanged())
			config.save();
		
		blockEndCake = new BlockEndCake();
		
		proxy.initModels();
		
		
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
