package info.u_team.halloween_luckyblock.core;

import info.u_team.u_team_core.block.UBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;

public class DefaultLuckyBlockBlock extends UBlock {
	
	public DefaultLuckyBlockBlock(String name, ItemGroup group) {
		super(name, group, Properties.create(Material.SPONGE).hardnessAndResistance(.45F, 100).lightValue(4).noDrops());
	}
}
