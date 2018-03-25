package de.luckycrew.halloween.render;

import de.luckycrew.halloween.entity.*;
import de.luckycrew.halloween.entity.render.*;
import de.luckycrew.halloween.item.HalloweenItems;
import info.u_team.u_team_core.util.registry.ClientRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.*;

public class HalloweenRenderRegistry {
	
	public HalloweenRenderRegistry() {
		entity();
	}
	
	private void entity() {
		
		Minecraft minecraft = Minecraft.getMinecraft();
		RenderManager manager = minecraft.getRenderManager();
		
		ClientRegistry.registerEntityRenderingHandler(EntityPumpkinGrenade.class, new RenderSnowball(manager, HalloweenItems.pumpkingrenade, minecraft.getRenderItem()));
		ClientRegistry.registerEntityRenderingHandler(EntityCreepyZombie.class, new RenderCreepyZombie(manager));
		ClientRegistry.registerEntityRenderingHandler(EntityGhost.class, new RenderGhost(manager));
		ClientRegistry.registerEntityRenderingHandler(EntityVampire.class, new RenderVampire(manager));
	}
	
}
