package info.u_team.halloween_luckyblock.item;

import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockCreativeTabs;
import info.u_team.u_team_core.item.UItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemWitchsBroomstick extends UItem {
	
	public ItemWitchsBroomstick(String name) {
		super(name, HalloweenLuckyBlockCreativeTabs.tab);
		setMaxDamage(100);
		setMaxStackSize(1);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		
		player.motionY = 0.6;
		
		float f = player.rotationYaw * 0.017453292F;
		player.motionX -= (double) (MathHelper.sin(f) * 0.42F);
		player.motionZ += (double) (MathHelper.cos(f) * 0.42F);
		
		player.fallDistance = 0;
		
		stack.damageItem(1, player);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return !areItemsEqual(oldStack, newStack);
	}
	
	public boolean areItemsEqual(ItemStack a, ItemStack b) {
		return a == null && b == null ? true : (a != null && b != null ? isItemEqualWithoutDamage(a, b) : false);
	}
	
	public boolean isItemEqualWithoutDamage(ItemStack a, ItemStack b) {
		return b != null && a.getItem() == b.getItem();
	}
}
