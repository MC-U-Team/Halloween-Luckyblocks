package info.u_team.halloween_luckyblock.render;

import static info.u_team.u_team_core.util.registry.ClientRegistry.registerModel;

import info.u_team.halloween_luckyblock.init.*;
import info.u_team.u_team_core.item.armor.UItemArmor;

public class HalloweenModelRegistry {
	
	public HalloweenModelRegistry() {
		item();
		block();
	}
	
	private void block() {
		registerModel(HalloweenLuckyBlockBlocks.luckyblock);
		registerModel(HalloweenLuckyBlockBlocks.pumpkinbomb);
	}
	
	private void item() {
		registerModel(HalloweenLuckyBlockItems.candy);
		registerModel(HalloweenLuckyBlockItems.teleporter);
		registerModel(HalloweenLuckyBlockItems.candybag);
		registerModel(HalloweenLuckyBlockItems.candybag, 1);
		registerModel(HalloweenLuckyBlockItems.witchsbroomstick);
		registerModel(HalloweenLuckyBlockItems.pumpkingrenade);
		
		for (UItemArmor item : HalloweenLuckyBlockItems.scarecrow) {
			registerModel(item);
		}
		
		for (UItemArmor item : HalloweenLuckyBlockItems.slender) {
			registerModel(item);
		}
		
		for (UItemArmor item : HalloweenLuckyBlockItems.witch) {
			registerModel(item);
		}
		
		for (UItemArmor item : HalloweenLuckyBlockItems.zombie) {
			registerModel(item);
		}
		
		for (UItemArmor item : HalloweenLuckyBlockItems.clown) {
			registerModel(item);
		}
		
		registerModel(HalloweenLuckyBlockItems.killerknive);
	}
	
}
