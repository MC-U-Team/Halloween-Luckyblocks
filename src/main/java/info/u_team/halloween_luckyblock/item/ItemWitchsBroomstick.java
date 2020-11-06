package info.u_team.halloween_luckyblock.item;

import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockItemGroups;
import info.u_team.u_team_core.item.UItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemWitchsBroomstick extends UItem {
	
	public ItemWitchsBroomstick() {
		super(HalloweenLuckyBlockItemGroups.GROUP, new Properties().maxDamage(100).rarity(Rarity.EPIC));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getHeldItem(hand);
		float f = player.rotationYaw * 0.017453292F;
		
		player.setMotion(player.getMotion().getX() - (MathHelper.sin(f) * 0.42F), 0.6, player.getMotion().getZ() + MathHelper.cos(f) * 0.42F);
		
		player.fallDistance = 0;
		
		stack.damageItem(1, player, x -> {
		});
		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
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
