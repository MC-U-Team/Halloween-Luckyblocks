package de.luckycrew.halloween.item;

import de.luckycrew.halloween.entity.EntityPumpkinGrenade;
import info.u_team.u_team_core.creativetab.UCreativeTab;
import info.u_team.u_team_core.item.UItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.world.World;

public class ItemPumpkinGrenade extends UItem {
	
	public ItemPumpkinGrenade(String name, UCreativeTab tab) {
		super(name, tab);
		setMaxStackSize(16);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		if (!player.capabilities.isCreativeMode) {
			--itemstack.stackSize;
		}
		
		world.playSoundAtEntity(player, "random.fizz", 0.6F, 0.4F / (itemRand.nextFloat() * 3F + 5F));
		
		if (!world.isRemote) {
			world.spawnEntityInWorld(new EntityPumpkinGrenade(world, player));
		}
		return itemstack;
	}
}
