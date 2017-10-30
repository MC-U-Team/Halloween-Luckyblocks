package de.luckycrew.halloween.render;

import static info.u_team.u_team_core.util.registry.ClientRegistry.registerModel;

import de.luckycrew.halloween.block.HalloweenBlocks;
import de.luckycrew.halloween.entity.*;
import de.luckycrew.halloween.entity.render.*;
import de.luckycrew.halloween.item.*;
import info.u_team.u_team_core.util.registry.ClientRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.*;

public class HalloweenRenderRegistry {
	
	private Minecraft minecraft = Minecraft.getMinecraft();
	
	public HalloweenRenderRegistry() {
		item();
		block();
		entity();
	}
	
	private void block() {
		registerModel(HalloweenBlocks.luckyblock);
		registerModel(HalloweenBlocks.pumpkinbomb);
	}
	
	private void item() {
		registerModel(HalloweenItems.candy);
		registerModel(HalloweenItems.teleporter);
		registerModel(HalloweenItems.candybag);
		registerModel(HalloweenItems.candybag, 1);
		registerModel(HalloweenItems.witchsbroomstick);
		registerModel(HalloweenItems.pumpkingrenade);
		
		for (ItemArmorBase item : HalloweenItems.scarecrow) {
			registerModel(item);
		}
		
		for (ItemArmorBase item : HalloweenItems.slender) {
			registerModel(item);
		}
		
		for (ItemArmorBase item : HalloweenItems.witch) {
			registerModel(item);
		}
		
		for (ItemArmorBase item : HalloweenItems.zombie) {
			registerModel(item);
		}
		
		for (ItemArmorBase item : HalloweenItems.clown) {
			registerModel(item);
		}
		
		registerModel(HalloweenItems.killerknive);
	}
	
	private void entity() {
		RenderManager manager = minecraft.getRenderManager();
		
		ClientRegistry.registerEntityRenderingHandler(EntityPumpkinGrenade.class, new RenderSnowball(manager, HalloweenItems.pumpkingrenade, minecraft.getRenderItem()));
		ClientRegistry.registerEntityRenderingHandler(EntityCreepyZombie.class, new RenderCreepyZombie(manager));
		ClientRegistry.registerEntityRenderingHandler(EntityGhost.class, new RenderGhost(manager));
		ClientRegistry.registerEntityRenderingHandler(EntityVampire.class, new RenderVampire(manager));
	}
}
