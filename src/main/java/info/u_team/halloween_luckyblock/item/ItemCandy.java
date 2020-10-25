package info.u_team.halloween_luckyblock.item;

import java.util.List;

import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockItemGroups;
import info.u_team.u_team_core.item.UItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.*;

public class ItemCandy extends UItem {
	
	public ItemCandy() {
		super(HalloweenLuckyBlockItemGroups.GROUP, new Properties().maxDamage(20).rarity(Rarity.RARE));
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!stack.hasTag()) {
			CompoundNBT compound = new CompoundNBT();
			compound.putInt("cooldown", 0);
			stack.setTag(compound);
		} else {
			CompoundNBT compound = stack.getTag();
			int i = compound.getInt("cooldown");
			if (i > 0) {
				compound.putInt("cooldown", i - 1);
			}
			stack.setTag(compound);
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getHeldItem(hand);
		CompoundNBT compound = stack.getTag();
		if (compound.getInt("cooldown") == 0) {
			compound.putInt("cooldown", 200);
			float f = player.rotationYaw * 0.017453292F;
			player.setMotion(player.getMotion().getX() - MathHelper.sin(f) * 2.2F, 0.88D, player.getMotion().getZ() + MathHelper.cos(f) * 2.2F);
			stack.damageItem(1, player, x -> {
			});
			return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
		}
		return new ActionResult<ItemStack>(ActionResultType.PASS, stack);
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return !ItemStack.areItemsEqual(oldStack, newStack);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if (stack.hasTag()) {
			CompoundNBT compound = stack.getTag();
			int seconds = compound.getInt("cooldown") / 20;
			if (seconds == 0) {
				tooltip.add(new TranslationTextComponent("item.candy.cooldown.no"));
			} else {
				tooltip.add(new TranslationTextComponent("item.candy.cooldown.yes", seconds));
			}
		}
	}
}
