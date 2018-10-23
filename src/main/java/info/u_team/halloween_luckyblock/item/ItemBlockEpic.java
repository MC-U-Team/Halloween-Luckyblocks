package info.u_team.halloween_luckyblock.item;

import info.u_team.u_team_core.block.UBlock;
import info.u_team.u_team_core.item.UItemBlock;
import net.minecraft.item.*;

public class ItemBlockEpic extends UItemBlock {
	
	public ItemBlockEpic(UBlock block) {
		super(block);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}
	
}
