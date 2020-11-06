package info.u_team.halloween_luckyblock.item;

import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockItemGroups;
import info.u_team.u_team_core.item.UItem;
import info.u_team.u_team_core.util.world.WorldUtil;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ItemTeleporter extends UItem {
	
	public ItemTeleporter() {
		super(HalloweenLuckyBlockItemGroups.GROUP, new Properties().maxDamage(5).rarity(Rarity.RARE));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		final BlockRayTraceResult result = (BlockRayTraceResult) WorldUtil.rayTraceServerSide(player, 50.0D);
		
		final ItemStack stack = player.getHeldItem(hand);
		
		if (result == null || result.getPos() == null || world.getBlockState(result.getPos()).getBlock() == Blocks.AIR) {
			if (!world.isRemote) {
				player.sendStatusMessage(new TranslationTextComponent("item.teleporter.nolocation"), true);
			}
			return new ActionResult<ItemStack>(ActionResultType.PASS, stack);
		}
		final BlockPos pos = result.getPos();
		player.setLocationAndAngles(pos.getX(), pos.getY() + 1, pos.getZ(), player.rotationYaw, player.rotationPitch);
		stack.damageItem(1, player, x -> {
		});
		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stack);
	}
	
}
