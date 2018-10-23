package info.u_team.halloween_luckyblock.init;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockConstants;
import info.u_team.halloween_luckyblock.block.BlockPumpkinBomb;
import info.u_team.halloween_luckyblock.core.BlockDefaultLuckyBlock;
import info.u_team.u_team_core.registry.BlockRegistry;
import info.u_team.u_team_core.util.RegistryUtil;
import net.minecraft.block.Block;

public class HalloweenLuckyBlockBlocks {
	
	public static final BlockDefaultLuckyBlock luckyblock = new BlockDefaultLuckyBlock("luckyblock");
	
	public static final BlockPumpkinBomb pumpkinbomb = new BlockPumpkinBomb("pumpkinbomb");
	
	public static void preinit() {
		BlockRegistry.register(HalloweenLuckyBlockConstants.MODID, RegistryUtil.getRegistryEntries(Block.class, HalloweenLuckyBlockBlocks.class));
	}
	
}
