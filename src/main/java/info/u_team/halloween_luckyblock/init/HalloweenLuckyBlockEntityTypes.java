package info.u_team.halloween_luckyblock.init;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockMod;
import info.u_team.halloween_luckyblock.entity.*;
import info.u_team.u_team_core.util.registry.EntityTypeDeferredRegister;
import net.minecraft.entity.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;

public class HalloweenLuckyBlockEntityTypes {
	
	public static final EntityTypeDeferredRegister ENTITY_TYPES = EntityTypeDeferredRegister.create(HalloweenLuckyBlockMod.MODID);
	
	public static final RegistryObject<EntityType<EntityPumpkinGrenade>> PUMPKIN_GRENADE = ENTITY_TYPES.register("pumpkin_grenade", () -> EntityType.Builder.<EntityPumpkinGrenade> create(EntityPumpkinGrenade::new, EntityClassification.MISC).size(0.25F, 0.25F).setTrackingRange(128).setUpdateInterval(20).setShouldReceiveVelocityUpdates(true));
	
	public static final RegistryObject<EntityType<EntityVampire>> VAMPIRE = ENTITY_TYPES.register("vampire", () -> EntityType.Builder.<EntityVampire> create(EntityVampire::new, EntityClassification.MONSTER).size(2F, 2F).setTrackingRange(64).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true));
	
	public static final RegistryObject<EntityType<EntityCreepyZombie>> CREEPY_ZOMBIE = ENTITY_TYPES.register("creepy_zombie", () -> EntityType.Builder.<EntityCreepyZombie> create(EntityCreepyZombie::new, EntityClassification.MONSTER).size(0.6F, 1.95F).setTrackingRange(64).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true));
	
	public static final RegistryObject<EntityType<EntityGhost>> GHOST = ENTITY_TYPES.register("ghost", () -> EntityType.Builder.<EntityGhost> create(EntityGhost::new, EntityClassification.MONSTER).size(1.5F, 1.5F).setTrackingRange(64).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true));
	
	public static void registerMod(IEventBus bus) {
		ENTITY_TYPES.register(bus);
	}
	
}
