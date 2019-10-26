package info.u_team.halloween_luckyblock.init;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockMod;
import info.u_team.halloween_luckyblock.core.*;
import info.u_team.halloween_luckyblock.event.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@EventBusSubscriber(modid = HalloweenLuckyBlockMod.MODID, bus = Bus.MOD)
public class HalloweenLuckyBlockEvents {
	
	@SubscribeEvent
	public static void register(FMLCommonSetupEvent event) {
		final LuckyHandler handler = new LuckyHandler();
		
		handler.add(new LuckyEventBlocks());
		handler.add(new LuckyEventBoom());
		handler.add(new LuckyEventChest());
		handler.add(new LuckyEventDeath());
		handler.add(new LuckyEventDropInventory());
		handler.add(new LuckyEventEnchant());
		handler.add(new LuckyEventGeneration());
		handler.add(new LuckyEventItems());
		handler.add(new LuckyEventMerchant());
		handler.add(new LuckyEventRainingPumkin());
		handler.add(new LuckyEventSound());
		handler.add(new LuckyEventSpawnCreature());
		handler.add(new LuckyEventThunder());
		
		MinecraftForge.EVENT_BUS.register(new BreakBlockListener(HalloweenLuckyBlockBlocks.LUCKYBLOCK, handler));
	}
}
