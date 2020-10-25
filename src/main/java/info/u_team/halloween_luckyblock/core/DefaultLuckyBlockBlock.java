package info.u_team.halloween_luckyblock.core;

import info.u_team.u_team_core.block.UBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;

public class DefaultLuckyBlockBlock extends UBlock {
	
	public DefaultLuckyBlockBlock(ItemGroup group) {
		super(group, Properties.create(Material.SPONGE).hardnessAndResistance(.45F, 100).setLightLevel(state -> 4).noDrops());
	}
}
