package de.luckycrew.halloween.item;

import info.u_team.u_team_core.item.UItemBlock;
import net.minecraft.block.Block;
import net.minecraft.item.*;

public class ItemBlockEpic extends UItemBlock {
	
	public ItemBlockEpic(Block block) {
		super(block);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}
	
}
