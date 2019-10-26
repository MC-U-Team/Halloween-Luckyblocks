package info.u_team.halloween_luckyblock.init;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockMod;
import info.u_team.halloween_luckyblock.entity.*;
import info.u_team.halloween_luckyblock.entity.render.*;
import info.u_team.u_team_core.util.registry.ClientRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = HalloweenLuckyBlockMod.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class HalloweenLuckyBlockRenderers {
	
	@SubscribeEvent
	public static void register(FMLClientSetupEvent event) {
		ClientRegistry.registerEntityRenderer(EntityCreepyZombie.class, RenderCreepyZombie::new);
		ClientRegistry.registerEntityRenderer(EntityGhost.class, RenderGhost::new);
		ClientRegistry.registerEntityRenderer(EntityVampire.class, RenderVampire::new);
		ClientRegistry.registerEntityRenderer(EntityPumpkinGrenade.class, manager -> {
			return new SpriteRenderer<>(manager, Minecraft.getInstance().getItemRenderer());
		});
	}
	
}
