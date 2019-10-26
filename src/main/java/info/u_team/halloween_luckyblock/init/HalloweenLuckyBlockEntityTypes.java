package info.u_team.halloween_luckyblock.init;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockMod;
import info.u_team.halloween_luckyblock.entity.*;
import info.u_team.u_team_core.entitytype.UEntityType.UBuilder;
import info.u_team.u_team_core.util.registry.BaseRegistryUtil;
import net.minecraft.entity.*;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = HalloweenLuckyBlockMod.MODID, bus = Bus.MOD)
public class HalloweenLuckyBlockEntityTypes {
	
	public static final EntityType<EntityPumpkinGrenade> PUMPKINGRENADE = UBuilder.<EntityPumpkinGrenade> create("pumpkingrenade", EntityPumpkinGrenade::new, EntityClassification.MISC).size(0.25F, 0.25F).setTrackingRange(128).setUpdateInterval(20).setShouldReceiveVelocityUpdates(true).build();
	
	public static final EntityType<EntityVampire> VAMPIRE = UBuilder.<EntityVampire> create("vampire", EntityVampire::new, EntityClassification.MONSTER).size(2F, 2F).setTrackingRange(64).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true).build();
	
	public static final EntityType<EntityCreepyZombie> CREEPY_ZOMBIE = UBuilder.<EntityCreepyZombie> create("creepy_zombie", EntityCreepyZombie::new, EntityClassification.MONSTER).size(0.6F, 1.95F).setTrackingRange(64).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true).build();
	
	public static final EntityType<EntityGhost> GHOST = UBuilder.<EntityGhost> create("ghost", EntityGhost::new, EntityClassification.MONSTER).size(1.5F, 1.5F).setTrackingRange(64).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true).build();
	
	@SubscribeEvent
	public static void register(Register<EntityType<?>> event) {
		BaseRegistryUtil.getAllGenericRegistryEntriesAndApplyNames(HalloweenLuckyBlockMod.MODID, EntityType.class).forEach(event.getRegistry()::register);
	}
}
