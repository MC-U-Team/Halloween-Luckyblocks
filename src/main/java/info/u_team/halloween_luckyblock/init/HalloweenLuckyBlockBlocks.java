package info.u_team.halloween_luckyblock.init;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockMod;
import info.u_team.halloween_luckyblock.block.BlockPumpkinBomb;
import info.u_team.halloween_luckyblock.core.DefaultLuckyBlockBlock;
import info.u_team.u_team_core.util.registry.*;
import net.minecraft.item.BlockItem;
import net.minecraftforge.eventbus.api.IEventBus;

public class HalloweenLuckyBlockBlocks {
	
	public static final BlockDeferredRegister BLOCKS = BlockDeferredRegister.create(HalloweenLuckyBlockMod.MODID);
	
	public static final BlockRegistryObject<DefaultLuckyBlockBlock, BlockItem> LUCKYBLOCK = BLOCKS.register("luckyblock", () -> new DefaultLuckyBlockBlock(HalloweenLuckyBlockItemGroups.GROUP));
	
	public static final BlockRegistryObject<BlockPumpkinBomb, BlockItem> PUMPKINBOMB = BLOCKS.register("pumpkinbomb", BlockPumpkinBomb::new);
	
	public static void registerMod(IEventBus bus) {
		BLOCKS.register(bus);
	}
	
}
