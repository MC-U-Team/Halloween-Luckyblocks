package de.luckycrew.halloween.render;

import static info.u_team.u_team_core.util.registry.ClientRegistry.registerModel;

import de.luckycrew.halloween.block.HalloweenBlocks;
import de.luckycrew.halloween.item.HalloweenItems;
import info.u_team.u_team_core.item.armor.UItemArmor;

public class HalloweenModelRegistry {
	
	public HalloweenModelRegistry() {
		item();
		block();
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
		
		for (UItemArmor item : HalloweenItems.scarecrow) {
			registerModel(item);
		}
		
		for (UItemArmor item : HalloweenItems.slender) {
			registerModel(item);
		}
		
		for (UItemArmor item : HalloweenItems.witch) {
			registerModel(item);
		}
		
		for (UItemArmor item : HalloweenItems.zombie) {
			registerModel(item);
		}
		
		for (UItemArmor item : HalloweenItems.clown) {
			registerModel(item);
		}
		
		registerModel(HalloweenItems.killerknive);
	}
	
}
