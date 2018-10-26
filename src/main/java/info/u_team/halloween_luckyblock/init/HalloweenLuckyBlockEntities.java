package info.u_team.halloween_luckyblock.init;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockConstants;
import info.u_team.halloween_luckyblock.entity.*;
import info.u_team.u_team_core.entity.UEntityEntry;
import info.u_team.u_team_core.registry.EntityRegistry;
import info.u_team.u_team_core.util.RegistryUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

public class HalloweenLuckyBlockEntities {
	
	public static final UEntityEntry pumpkin_grenade = new UEntityEntry(EntityEntryBuilder.create().entity(EntityPumpkinGrenade.class).id(new ResourceLocation(HalloweenLuckyBlockConstants.MODID, "pumpkingrenade"), 0).name("pumpkingrenade").tracker(64, 1, true));
	
	public static final UEntityEntry creepy_zombie = new UEntityEntry(EntityEntryBuilder.create().entity(EntityCreepyZombie.class).id(new ResourceLocation(HalloweenLuckyBlockConstants.MODID, "creepyzombie"), 1).name("creepyzombie").tracker(64, 3, true).egg(0x2ecc71, 0xe67e22));
	
	public static final UEntityEntry ghost = new UEntityEntry(EntityEntryBuilder.create().entity(EntityGhost.class).id(new ResourceLocation(HalloweenLuckyBlockConstants.MODID, "ghost"), 2).name("ghost").tracker(64, 3, true).egg(0x797a74, 0xffffff));
	
	public static final UEntityEntry vampire = new UEntityEntry(EntityEntryBuilder.create().entity(EntityVampire.class).id(new ResourceLocation(HalloweenLuckyBlockConstants.MODID, "vampire"), 3).name("vampire").tracker(64, 3, true).egg(0x686c72, 0x000000));
	
	public static void preinit() {
		EntityRegistry.register(HalloweenLuckyBlockConstants.MODID, RegistryUtil.getEntityRegistryEntries(HalloweenLuckyBlockEntities.class));
	}
	
}
