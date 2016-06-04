package com.insane.dimensionalcake;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {

	@Override
	public void initModels()
	{
		//DimensionalCake.blockEndCake.initModel();

		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(DimensionalCake.blockEndCake), 0, new ModelResourceLocation(new ResourceLocation("dimensionalcake:blockEndCake"), "bites=0"));
	}

}
