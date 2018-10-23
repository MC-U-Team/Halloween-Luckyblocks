package info.u_team.halloween_luckyblock.item;

import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockCreativeTabs;
import info.u_team.u_team_core.item.tool.UItemSword;
import net.minecraft.item.*;

public class ItemKillerKnive extends UItemSword {
	
	public ItemKillerKnive(ToolMaterial material, String name) {
		super(name, HalloweenLuckyBlockCreativeTabs.tab, material);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}
}
