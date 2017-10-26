package de.luckycrew.halloween.models;

import de.luckycrew.halloween.block.HalloweenBlocks;
import de.luckycrew.halloween.item.*;
import info.u_team.u_team_core.util.registry.ClientRegistry;

public class HalloweenModelRegistry {
	
	public HalloweenModelRegistry() {
		item();
		block();
	}
	
	private void block() {
		ClientRegistry.registerModel(HalloweenBlocks.luckyblock);
	}
	
	private void item() {
		for (ItemArmorBase item : HalloweenItems.scarecrow) {
			ClientRegistry.registerModel(item);
		}
		
		for (ItemArmorBase item : HalloweenItems.slender) {
			ClientRegistry.registerModel(item);
		}
	}
	
}
