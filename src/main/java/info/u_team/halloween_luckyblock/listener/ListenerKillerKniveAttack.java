package info.u_team.halloween_luckyblock.listener;

import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockItems;
import net.minecraft.entity.*;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ListenerKillerKniveAttack {
	
	@SubscribeEvent
	public static void on(LivingAttackEvent event) {
		Entity entity = event.getSource().getImmediateSource();
		if (entity == null || !(entity instanceof ServerPlayerEntity)) {
			return;
		}
		ServerPlayerEntity player = (ServerPlayerEntity) entity;
		ItemStack stack = player.inventory.getCurrentItem();
		if (stack == null || stack.getItem() == null) {
			return;
		}
		Item item = stack.getItem();
		if (item == HalloweenLuckyBlockItems.KILLERKNIFE) {
			LivingEntity base = event.getEntityLiving();
			
			Vec3d lookplayer = player.getLookVec();
			Vec3d lookbase = base.getLookVec();
			
			double differx = Math.abs(lookbase.x - lookplayer.x);
			double differz = Math.abs(lookbase.z - lookplayer.z);
			
			if (differx < 0.4 && differz < 0.4) {
				base.onKillCommand();
				stack.damageItem(40, player, x -> {
					x.setHeldItem(Hand.MAIN_HAND, ItemStack.EMPTY);
				});
			}
		}
	}
}
