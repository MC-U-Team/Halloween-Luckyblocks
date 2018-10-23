package info.u_team.halloween_luckyblock.item;

import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockCreativeTabs;
import info.u_team.u_team_core.creativetab.UCreativeTab;
import info.u_team.u_team_core.item.UItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemTeleporter extends UItem {
	
	public ItemTeleporter(String name) {
		super(name, HalloweenLuckyBlockCreativeTabs.tab);
		this.setMaxStackSize(1);
		this.setMaxDamage(5);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		MovingObjectPosition objectposition = rayTrace(player, 50.0D);
		
		if (objectposition == null || objectposition.getBlockPos() == null || world.getBlockState(objectposition.getBlockPos()).getBlock() == Blocks.air) {
			if (!world.isRemote) {
				player.addChatComponentMessage(new ChatComponentTranslation("item.teleporter.nolocation"));
			}
			return itemstack;
		}
		BlockPos pos = objectposition.getBlockPos();
		player.setLocationAndAngles(pos.getX(), pos.getY() + 1, pos.getZ(), player.rotationYaw, player.rotationPitch);
		itemstack.damageItem(1, player);
		return itemstack;
	}
	
	public MovingObjectPosition rayTrace(EntityPlayer player, double range) {
		Vec3 playervec = new Vec3(player.posX, player.posY + (double) player.getEyeHeight(), player.posZ);
		Vec3 lookvec = player.getLookVec();
		Vec3 finalvec = playervec.addVector(lookvec.xCoord * range, lookvec.yCoord * range, lookvec.zCoord * range);
		return player.worldObj.rayTraceBlocks(playervec, finalvec, true, false, true);
	}
}
