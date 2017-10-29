package de.luckycrew.halloween.entity;

import info.u_team.u_team_core.sub.USub;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class HalloweenEntities {
	
	public HalloweenEntities() {
		throwable();
	}
	
	private void throwable() {
		EntityRegistry.registerGlobalEntityID(EntityPumpkinGrenade.class, "pumpkingrenade", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityPumpkinGrenade.class, "pumpkingrenade", 0, USub.getID(), 64, 1, true);
	}
	
}
