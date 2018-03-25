package de.luckycrew.halloween.item;

import info.u_team.u_team_core.creativetab.UCreativeTab;
import info.u_team.u_team_core.item.tool.UItemSword;
import net.minecraft.item.*;

public class ItemKillerKnive extends UItemSword {
	
	public ItemKillerKnive(ToolMaterial material, String name, UCreativeTab tab) {
		super(name, tab, material);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}
}
