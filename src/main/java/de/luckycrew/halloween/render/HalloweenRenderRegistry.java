package de.luckycrew.halloween.render;

import de.luckycrew.halloween.entity.*;
import de.luckycrew.halloween.entity.render.*;
import de.luckycrew.halloween.item.HalloweenItems;
import info.u_team.u_team_core.util.registry.ClientRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;

public class HalloweenRenderRegistry {
	
	public HalloweenRenderRegistry() {
		entity();
	}
	
	private void entity() {
		ClientRegistry.registerEntityRenderer(EntityPumpkinGrenade.class, manager -> new RenderSnowball<>(manager, HalloweenItems.pumpkingrenade, Minecraft.getMinecraft().getRenderItem()));
		ClientRegistry.registerEntityRenderer(EntityCreepyZombie.class, RenderCreepyZombie::new);
		ClientRegistry.registerEntityRenderer(EntityGhost.class, RenderGhost::new);
		ClientRegistry.registerEntityRenderer(EntityVampire.class, RenderVampire::new);
	}
	
}
