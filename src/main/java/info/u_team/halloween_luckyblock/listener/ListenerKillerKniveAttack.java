package info.u_team.halloween_luckyblock.listener;

import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockItems;
import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.*;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ListenerKillerKniveAttack {
	
	@SubscribeEvent
	public static void on(LivingAttackEvent event) {
		Entity entity = event.getSource().getImmediateSource();
		if (entity == null || !(entity instanceof EntityPlayerMP)) {
			return;
		}
		EntityPlayerMP player = (EntityPlayerMP) entity;
		ItemStack stack = player.inventory.getCurrentItem();
		if (stack == null || stack.getItem() == null) {
			return;
		}
		Item item = stack.getItem();
		if (item == HalloweenLuckyBlockItems.killerknive) {
			EntityLivingBase base = event.getEntityLiving();
			
			Vec3d lookplayer = player.getLookVec();
			Vec3d lookbase = base.getLookVec();
			
			double differx = Math.abs(lookbase.x - lookplayer.x);
			double differz = Math.abs(lookbase.z - lookplayer.z);
			
			if (differx < 0.4 && differz < 0.4) {
				base.onKillCommand();
				stack.damageItem(40, player);
				if (stack.getItemDamage() >= stack.getMaxDamage() || stack.getMaxStackSize() == 0) {
					player.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
				}
			}
		}
	}
}
