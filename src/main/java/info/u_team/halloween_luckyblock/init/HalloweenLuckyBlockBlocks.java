package info.u_team.halloween_luckyblock.init;

import java.util.List;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockMod;
import info.u_team.halloween_luckyblock.block.BlockPumpkinBomb;
import info.u_team.halloween_luckyblock.core.DefaultLuckyBlockBlock;
import info.u_team.u_team_core.util.registry.BaseRegistryUtil;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = HalloweenLuckyBlockMod.MODID, bus = Bus.MOD)
public class HalloweenLuckyBlockBlocks {
	
	public static final DefaultLuckyBlockBlock LUCKYBLOCK = new DefaultLuckyBlockBlock("luckyblock", HalloweenLuckyBlockItemGroups.GROUP);
	
	public static final BlockPumpkinBomb PUMPKINBOMB = new BlockPumpkinBomb("pumpkinbomb");
	
	@SubscribeEvent
	public static void register(Register<Block> event) {
		entries = BaseRegistryUtil.getAllRegistryEntriesAndApplyNames(HalloweenLuckyBlockMod.MODID, Block.class);
		entries.forEach(event.getRegistry()::register);
	}
	
	@SubscribeEvent
	public static void registerBlockItem(Register<Item> event) {
		BaseRegistryUtil.getBlockItems(entries).forEach(event.getRegistry()::register);
		entries = null; // Dereference list as it is no longer needed
	}
	
	// Just a cache for the block item registry for performance
	private static List<Block> entries;
	
}
