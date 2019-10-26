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
public class HalloweenLuckyBlockEntities {
	
	// public static final UEntityEntry pumpkin_grenade = new
	// UEntityEntry(EntityEntryBuilder.create().entity(EntityPumpkinGrenade.class).id(new
	// ResourceLocation(HalloweenLuckyBlockConstants.MODID, "pumpkingrenade"), 0).name("pumpkingrenade").tracker(64, 1,
	// true));
	//
	// public static final UEntityEntry creepy_zombie = new
	// UEntityEntry(EntityEntryBuilder.create().entity(EntityCreepyZombie.class).id(new
	// ResourceLocation(HalloweenLuckyBlockConstants.MODID, "creepyzombie"), 1).name("creepyzombie").tracker(64, 3,
	// true).egg(0x2ecc71, 0xe67e22));
	//
	// public static final UEntityEntry ghost = new
	// UEntityEntry(EntityEntryBuilder.create().entity(EntityGhost.class).id(new
	// ResourceLocation(HalloweenLuckyBlockConstants.MODID, "ghost"), 2).name("ghost").tracker(64, 3, true).egg(0x797a74,0xffffff));
	//
	// public static final UEntityEntry vampire = new
	// UEntityEntry(EntityEntryBuilder.create().entity(EntityVampire.class).id(new
	// ResourceLocation(HalloweenLuckyBlockConstants.MODID, "vampire"), 3).name("vampire").tracker(64, 3,
	// true).egg(0x686c72, 0x000000));
	//
	
	public static final EntityType<EntityPumpkinGrenade> PUMPKINGRENADE = UBuilder.<EntityPumpkinGrenade> create("pumpkingrenade", EntityPumpkinGrenade::new, EntityClassification.MISC).setCustomClientFactory(EntityPumpkinGrenade::new).size(0.25F, 0.25F).setTrackingRange(128).setUpdateInterval(20).setShouldReceiveVelocityUpdates(true).build();
	
	public static final EntityType<EntityVampire> VAMPIRE = UBuilder.<EntityVampire> create("vampire", EntityVampire::new, EntityClassification.MONSTER).size(2F, 2F).setTrackingRange(64).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true).build();

	public static final EntityType<EntityCreepyZombie> CREEPY_ZOMBIE = UBuilder.<EntityCreepyZombie> create("creepy_zombie", EntityCreepyZombie::new, EntityClassification.MONSTER).size(0.6F, 1.95F).setTrackingRange(64).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true).build();

	public static final EntityType<EntityGhost> GHOST = UBuilder.<EntityGhost> create("ghost", EntityGhost::new, EntityClassification.MONSTER).size(1.5F, 1.5F).setTrackingRange(64).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true).build();

	
	@SubscribeEvent
	public static void register(Register<EntityType<?>> event) {
		BaseRegistryUtil.getAllGenericRegistryEntriesAndApplyNames(HalloweenLuckyBlockMod.MODID, EntityType.class).forEach(event.getRegistry()::register);
	}
}
