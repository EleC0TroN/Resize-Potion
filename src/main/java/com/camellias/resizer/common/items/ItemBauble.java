package com.camellias.resizer.common.items;

import java.util.List;

import com.camellias.resizer.Main;
import com.camellias.resizer.init.ModItems;
import com.camellias.resizer.util.IHasModel;

import baubles.api.render.IRenderBauble;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBauble extends Item implements IHasModel, IRenderBauble
{
	//@SideOnly(Side.CLIENT)
	//private static Model model;
	
	public ItemBauble(String name)
	{
		this.setTranslationKey(name);
		this.setRegistryName(name);
		this.setCreativeTab(CreativeTabs.TOOLS);
		this.maxStackSize = 1;
		
		ModItems.ITEMS.add(this);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag)
	{
		String shift = TextFormatting.YELLOW + I18n.format("shift.prompt");
		String info1 = TextFormatting.YELLOW + I18n.format(this.getTranslationKey() + ".info1");
		String info2 = TextFormatting.YELLOW + I18n.format(this.getTranslationKey() + ".info2");

		if(GuiScreen.isShiftKeyDown())
		{
			tooltip.add(info1);
			tooltip.add(info2);
		}
		else
		{
			tooltip.add(shift);
		}

	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		PotionEffect shrinking = player.getActivePotionEffect(Main.SHRINKING);
		PotionEffect growth = player.getActivePotionEffect(Main.GROWTH);
		
		if(!(stack.getItem() instanceof ItemShrinkingBauble) || !(stack.getItem() instanceof ItemGrowthBauble))
		{
			if(player.isPotionActive(Main.SHRINKING) && shrinking.getIsAmbient() == false)
			{
				if(!world.isRemote)
				{
					player.addItemStackToInventory(new ItemStack(ModItems.SHRINKING_BAUBLE));
					stack.shrink(1);
				}
				
				player.removePotionEffect(Main.SHRINKING);
			}
			
			if(player.isPotionActive(Main.GROWTH) && growth.getIsAmbient() == false)
			{
				if(!world.isRemote)
				{
					player.addItemStackToInventory(new ItemStack(ModItems.GROWTH_BAUBLE));
					stack.shrink(1);
				}
				
				player.removePotionEffect(Main.GROWTH);
			}
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}
	
	@Override
	public void onPlayerBaubleRender(ItemStack stack, EntityPlayer player, RenderType type, float ticks)
	{
		if(stack.getItem() instanceof ItemShrinkingBauble)
		{
			
		}
		else if(stack.getItem() instanceof ItemGrowthBauble)
		{
			
		}
		else
		{
			
		}
	}
	
	@Override
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
