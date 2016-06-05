package com.insane.dimensionalcake;

import java.util.Random;

import net.minecraft.block.BlockCake;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockEndCake extends BlockCake {


	public BlockEndCake()
	{
		super();
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		this.setUnlocalizedName("blockEndCake");
		this.setRegistryName("blockEndCake");
		GameRegistry.registerBlock(this);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack item, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		int meta = this.getMetaFromState(world.getBlockState(pos)) - 1;

		if (player.capabilities.isCreativeMode)
		{
			if (item != null && item.getItem() == Items.ENDER_EYE)
			{
				world.setBlockState(pos, this.getStateFromMeta(0), 2);
				return true;
			}
			else
			{
				player.changeDimension(1);
				return true;
			}
		}
		else
		{
			if (item != null && item.getItem() == Items.ENDER_EYE)
			{
				if (meta >= 0)
				{
					world.setBlockState(pos, this.getStateFromMeta(meta), 2);
					--item.stackSize;
					return true;
				}
			}
			else
			{
				nomEndCake(world, pos, player);
				return true;
			}
		}

		return false;
	}

	private void nomEndCake(World world, BlockPos pos, EntityPlayer player)
	{
		if (player.canEat(false) || DimensionalCake.eatCakeWhenFull)
		{
			int l = this.getMetaFromState(world.getBlockState(pos)) + 1;

			if (l >= 6)
			{
				return;
			}
			else
			{
				player.getFoodStats().addStats(2, 0.1F);
				world.setBlockState(pos, this.getStateFromMeta(l), 2);
				if (world.provider.getDimension() == 0)
				{
					player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("resistance"), 200, 1));					
					player.changeDimension(1);
				}
				if (world.provider.getDimension() == 1)
				{
					player.changeDimension(1);
				}
			}
		}
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		return this.getStateFromMeta(6);
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state)
	{
		if (world.provider.getDimension() == 1)
		{
			world.scheduleBlockUpdate(pos, this, 0, 12000);
		}
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if (world.provider.getDimension() == 1)
		{
			int meta = this.getMetaFromState(world.getBlockState(pos)) - 1;
			if (meta > 0) world.setBlockState(pos, this.getStateFromMeta(meta), 2);
			world.scheduleBlockUpdate(pos, this, 0, 12000);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel()
	{
		for (int i = 0; i <= 6; i++)
		{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), i, 
				new ModelResourceLocation("dimensionalcake:blockEndCake", "inventory"));
		}
	}
}
