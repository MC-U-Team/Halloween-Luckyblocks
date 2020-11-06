package info.u_team.halloween_luckyblock.listener;

import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockSounds;
import info.u_team.halloween_luckyblock.util.ArmorUtility;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.*;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class ListenerZombieArmor {
	
	private static void onLivingUpdate(LivingUpdateEvent event) {
		final Entity entity = event.getEntityLiving();
		if (!(entity instanceof PlayerEntity)) {
			return;
		}
		final PlayerEntity player = (PlayerEntity) entity;
		if (player.abilities.isCreativeMode) {
			return;
		}
		final int count = ArmorUtility.getArmorBaseCount(player, "zombie");
		if (count > 0) {
			player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 2, 0, false, false));
			if (player.world.rand.nextInt(500) == 0) {
				player.world.playSound(null, player.getPosition(), SoundEvents.ENTITY_ZOMBIE_AMBIENT, HalloweenLuckyBlockSounds.CATEGORY, 0.2F, 1.0F);
			}
		}
	}
	
	public static void registerForge(IEventBus bus) {
		bus.addListener(ListenerZombieArmor::onLivingUpdate);
	}
}
