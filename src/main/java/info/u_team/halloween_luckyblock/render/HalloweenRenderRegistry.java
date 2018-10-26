package info.u_team.halloween_luckyblock.render;

import info.u_team.halloween_luckyblock.entity.*;
import info.u_team.halloween_luckyblock.entity.render.*;
import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockItems;
import info.u_team.u_team_core.registry.ClientRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.*;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class HalloweenRenderRegistry {
	
	public static void preinit() {
		ClientRegistry.registerEntityRenderer(EntityPumpkinGrenade.class, new IRenderFactory<EntityPumpkinGrenade>() {
			
			@Override
			public Render<EntityPumpkinGrenade> createRenderFor(RenderManager manager) {
				return new RenderSnowball<>(manager, HalloweenLuckyBlockItems.pumpkingrenade, Minecraft.getMinecraft().getRenderItem());
			}
		});
		
		ClientRegistry.registerEntityRenderer(EntityCreepyZombie.class, RenderCreepyZombie::new);
		ClientRegistry.registerEntityRenderer(EntityGhost.class, RenderGhost::new);
		ClientRegistry.registerEntityRenderer(EntityVampire.class, RenderVampire::new);
	}
	
}
