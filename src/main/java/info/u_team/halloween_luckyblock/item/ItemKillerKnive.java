package info.u_team.halloween_luckyblock.item;

import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockItemGroups;
import info.u_team.u_team_core.api.IToolMaterial;
import info.u_team.u_team_core.item.tool.USwordItem;
import net.minecraft.item.Rarity;

public class ItemKillerKnive extends USwordItem {
	
	public ItemKillerKnive(IToolMaterial material) {
		super(HalloweenLuckyBlockItemGroups.GROUP, new Properties().rarity(Rarity.UNCOMMON), material);
	}
}
