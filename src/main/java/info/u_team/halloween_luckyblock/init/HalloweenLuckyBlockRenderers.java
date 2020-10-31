package info.u_team.halloween_luckyblock.init;

import info.u_team.halloween_luckyblock.entity.render.*;
import info.u_team.u_team_core.util.registry.ClientRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class HalloweenLuckyBlockRenderers {
	
	private static void setup(FMLClientSetupEvent event) {
		ClientRegistry.registerEntityRenderer(HalloweenLuckyBlockEntityTypes.CREEPY_ZOMBIE, RenderCreepyZombie::new);
		ClientRegistry.registerEntityRenderer(HalloweenLuckyBlockEntityTypes.GHOST, RenderGhost::new);
		ClientRegistry.registerEntityRenderer(HalloweenLuckyBlockEntityTypes.VAMPIRE, RenderVampire::new);
		ClientRegistry.registerEntityRenderer(HalloweenLuckyBlockEntityTypes.PUMPKIN_GRENADE, manager -> {
			return new SpriteRenderer<>(manager, Minecraft.getInstance().getItemRenderer());
		});
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(HalloweenLuckyBlockRenderers::setup);
	}
	
}
