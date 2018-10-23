package info.u_team.halloween_luckyblock.render;

import info.u_team.halloween_luckyblock.entity.*;
import info.u_team.halloween_luckyblock.entity.render.*;
import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.*;

public class HalloweenRenderRegistry {
	
	public HalloweenRenderRegistry() {
		entity();
	}
	
	private void entity() {
		
		Minecraft minecraft = Minecraft.getMinecraft();
		RenderManager manager = minecraft.getRenderManager();
		
		ClientRegistry.registerEntityRenderingHandler(EntityPumpkinGrenade.class, new RenderSnowball(manager, HalloweenLuckyBlockItems.pumpkingrenade, minecraft.getRenderItem()));
		ClientRegistry.registerEntityRenderingHandler(EntityCreepyZombie.class, new RenderCreepyZombie(manager));
		ClientRegistry.registerEntityRenderingHandler(EntityGhost.class, new RenderGhost(manager));
		ClientRegistry.registerEntityRenderingHandler(EntityVampire.class, new RenderVampire(manager));
	}
	
}
