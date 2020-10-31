package info.u_team.halloween_luckyblock.init;

import info.u_team.halloween_luckyblock.core.*;
import info.u_team.halloween_luckyblock.event.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class HalloweenLuckyBlockEvents {
	
	private static final LuckyHandler LUCKY_HANDLER = new LuckyHandler();
	
	private static void setup(FMLCommonSetupEvent event) {
		LUCKY_HANDLER.add(new LuckyEventBlocks());
		LUCKY_HANDLER.add(new LuckyEventBoom());
		LUCKY_HANDLER.add(new LuckyEventChest());
		LUCKY_HANDLER.add(new LuckyEventDeath());
		LUCKY_HANDLER.add(new LuckyEventDropInventory());
		LUCKY_HANDLER.add(new LuckyEventEnchant());
		LUCKY_HANDLER.add(new LuckyEventGeneration());
		LUCKY_HANDLER.add(new LuckyEventItems());
		LUCKY_HANDLER.add(new LuckyEventMerchant());
		LUCKY_HANDLER.add(new LuckyEventRainingPumkin());
		LUCKY_HANDLER.add(new LuckyEventSound());
		LUCKY_HANDLER.add(new LuckyEventSpawnCreature());
		LUCKY_HANDLER.add(new LuckyEventThunder());
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(HalloweenLuckyBlockEvents::setup);
	}
	
	public static void registerForge(IEventBus bus) {
		bus.register(new BreakBlockListener(HalloweenLuckyBlockBlocks.LUCKYBLOCK.get(), LUCKY_HANDLER));
	}
}
