package de.luckycrew.halloween.models;

import static info.u_team.u_team_core.util.registry.ClientRegistry.registerModel;

import de.luckycrew.halloween.block.HalloweenBlocks;
import de.luckycrew.halloween.item.*;

public class HalloweenModelRegistry {
	
	public HalloweenModelRegistry() {
		item();
		block();
	}
	
	private void block() {
		registerModel(HalloweenBlocks.luckyblock);
	}
	
	private void item() {
		for (ItemArmorBase item : HalloweenItems.scarecrow) {
			registerModel(item);
		}
		
		for (ItemArmorBase item : HalloweenItems.slender) {
			registerModel(item);
		}
		
		registerModel(HalloweenItems.candy);
		registerModel(HalloweenItems.teleporter);
		
		registerModel(HalloweenItems.killerknive);
	}
	
}
