package info.u_team.halloween_luckyblock.core;

import java.util.Random;

import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockCreativeTabs;
import info.u_team.u_team_core.block.UBlock;
import net.minecraft.block.material.Material;

public class BlockDefaultLuckyBlock extends UBlock {
	
	public BlockDefaultLuckyBlock(String name) {
		super(name, Material.SPONGE, HalloweenLuckyBlockCreativeTabs.tab);
		this.setHardness(0.45F);
		this.setResistance(100.0F);
		this.setLightLevel(0.3F);
	}
	
	@Override
	public int quantityDropped(Random random) {
		return 0;
	}
}
