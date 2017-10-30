package de.luckycrew.halloween.item;

import java.util.List;

import info.u_team.u_team_core.creativetab.UCreativeTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.potion.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.*;

public class ItemCandyBag extends ItemFood {
	
	private String name;
	
	public ItemCandyBag(String name, UCreativeTab tab) {
		super(4, 2.0F, false);
		this.name = name;
		setCreativeTab(tab);
		register();
		setAlwaysEdible();
		setHasSubtypes(true);
	}
	
	private void register() {
		setUnlocalizedName(name);
		GameRegistry.registerItem(this, name);
	}
	
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return stack.getMetadata() > 0;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 1));
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return stack.getMetadata() == 0 ? EnumRarity.RARE : EnumRarity.EPIC;
	}
	
	@Override
	public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		if (!worldIn.isRemote) {
			if (stack.getMetadata() == 0) {
				player.addPotionEffect(new PotionEffect(Potion.absorption.id, 1200, 1));
			} else {
				player.addPotionEffect(new PotionEffect(Potion.absorption.id, 2400, 4));
				player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 1200, 1));
				player.addPotionEffect(new PotionEffect(Potion.resistance.id, 600, 2));
				player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 1200, 0));
				player.addPotionEffect(new PotionEffect(Potion.jump.id, 1200, 0));
			}
		}
	}
}
