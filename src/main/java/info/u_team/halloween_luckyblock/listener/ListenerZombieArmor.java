package info.u_team.halloween_luckyblock.listener;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockMod;
import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockSounds;
import info.u_team.halloween_luckyblock.util.ArmorUtility;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.*;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = HalloweenLuckyBlockMod.MODID, bus = Bus.FORGE)
public class ListenerZombieArmor {
	
	@SubscribeEvent
	public static void on(LivingUpdateEvent event) {
		Entity entity = event.getEntityLiving();
		if (!(entity instanceof PlayerEntity)) {
			return;
		}
		PlayerEntity player = (PlayerEntity) entity;
		if (player.abilities.isCreativeMode) {
			return;
		}
		int count = ArmorUtility.getArmorBaseCount(player, "zombie");
		if (count > 0) {
			player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 2, 0, false, false));
			if (player.world.rand.nextInt(500) == 0) {
				player.world.playSound(null, player.getPosition(), SoundEvents.ENTITY_ZOMBIE_AMBIENT, HalloweenLuckyBlockSounds.category, 0.2F, 1.0F);
			}
		}
	}
}
