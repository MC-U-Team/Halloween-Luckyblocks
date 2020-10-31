package info.u_team.halloween_luckyblock.init;

import info.u_team.halloween_luckyblock.entity.*;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class HalloweenLuckyBlockGlobalEntityTypeAttributes {
	
	private static void setup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			GlobalEntityTypeAttributes.put(HalloweenLuckyBlockEntityTypes.CREEPY_ZOMBIE.get(), EntityCreepyZombie.registerAttributes().create());
			GlobalEntityTypeAttributes.put(HalloweenLuckyBlockEntityTypes.GHOST.get(), EntityGhost.registerAttributes().create());
			GlobalEntityTypeAttributes.put(HalloweenLuckyBlockEntityTypes.VAMPIRE.get(), EntityVampire.registerAttributes().create());
		});
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(HalloweenLuckyBlockGlobalEntityTypeAttributes::setup);
	}
	
}
