package info.u_team.halloween_luckyblock.item;

import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockItemGroups;
import info.u_team.u_team_core.item.UItem;
import info.u_team.u_team_core.util.world.WorldUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemTeleporter extends UItem {
	
	public ItemTeleporter(String name) {
		super(name, HalloweenLuckyBlockItemGroups.GROUP);
		this.setMaxStackSize(1);
		this.setMaxDamage(5);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		RayTraceResult result = WorldUtil.rayTraceServerSide(player, 50.0D);
		
		ItemStack stack = player.getHeldItem(hand);
		
		if (result == null || result.getBlockPos() == null || world.getBlockState(result.getBlockPos()).getBlock() == Blocks.AIR) {
			if (!world.isRemote) {
				player.sendStatusMessage(new TextComponentTranslation("item.teleporter.nolocation"), true);
			}
			return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
		}
		BlockPos pos = result.getBlockPos();
		player.setLocationAndAngles(pos.getX(), pos.getY() + 1, pos.getZ(), player.rotationYaw, player.rotationPitch);
		stack.damageItem(1, player);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}
	
}
