package info.u_team.halloween_luckyblock.listener;

import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockItems;
import net.minecraft.entity.*;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class ListenerKillerKniveAttack {
	
	private static void onLivingAttack(LivingAttackEvent event) {
		final Entity entity = event.getSource().getImmediateSource();
		if (entity == null || !(entity instanceof ServerPlayerEntity)) {
			return;
		}
		final ServerPlayerEntity player = (ServerPlayerEntity) entity;
		final ItemStack stack = player.inventory.getCurrentItem();
		if (stack == null || stack.getItem() == null) {
			return;
		}
		final Item item = stack.getItem();
		if (item == HalloweenLuckyBlockItems.KILLERKNIFE.get()) {
			final LivingEntity base = event.getEntityLiving();
			
			final Vector3d lookplayer = player.getLookVec();
			final Vector3d lookbase = base.getLookVec();
			
			final double differx = Math.abs(lookbase.x - lookplayer.x);
			final double differz = Math.abs(lookbase.z - lookplayer.z);
			
			if (differx < 0.4 && differz < 0.4) {
				base.onKillCommand();
				stack.damageItem(40, player, x -> {
					x.setHeldItem(Hand.MAIN_HAND, ItemStack.EMPTY);
				});
			}
		}
	}
	
	public static void registerForge(IEventBus bus) {
		bus.addListener(ListenerKillerKniveAttack::onLivingAttack);
	}
}
