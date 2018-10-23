package info.u_team.halloween_luckyblock.init;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockConstants;
import info.u_team.halloween_luckyblock.block.BlockPumpkinBomb;
import info.u_team.u_team_core.entity.UEntityEntry;
import info.u_team.u_team_core.registry.EntityRegistry;
import info.u_team.u_team_core.util.RegistryUtil;
import net.minecraftforge.fml.common.registry.EntityEntry;

public class HalloweenLuckyBlockEntities {
	
	
	public static final BlockPumpkinBomb pumpkinbomb = new BlockPumpkinBomb("pumpkinbomb");
	
	public static void preinit() {
		//BlockRegistry.register(HalloweenLuckyBlockConstants.MODID, RegistryUtil.getRegistryEntries(Block.class, HalloweenLuckyBlockBlocks.class));
		EntityRegistry.register(HalloweenLuckyBlockConstants.MODID, RegistryUtil.getRegistryEntries(UEntityEntry.class, HalloweenLuckyBlockEntities.class));
	}
	
//	public HalloweenLuckyBlockEntities() {
//		throwable();
//	}
//	
//	private void throwable() {
//		EntityRegistry.registerGlobalEntityID(EntityPumpkinGrenade.class, "pumpkingrenade", EntityRegistry.findGlobalUniqueEntityId());
//		EntityRegistry.registerModEntity(EntityPumpkinGrenade.class, "pumpkingrenade", 0, USub.getID(), 64, 1, true);
//		
//		EntityRegistry.registerGlobalEntityID(EntityCreepyZombie.class, "creepyzombie", EntityRegistry.findGlobalUniqueEntityId());
//		EntityRegistry.registerModEntity(EntityCreepyZombie.class, "creepyzombie", 1, USub.getID(), 64, 3, true, 0x2ecc71, 0xe67e22);
//		
//		EntityRegistry.registerGlobalEntityID(EntityGhost.class, "ghost", EntityRegistry.findGlobalUniqueEntityId());
//		EntityRegistry.registerModEntity(EntityGhost.class, "ghost", 2, USub.getID(), 64, 3, true, 0x797a74, 0xffffff);
//		
//		EntityRegistry.registerGlobalEntityID(EntityVampire.class, "vampire", EntityRegistry.findGlobalUniqueEntityId());
//		EntityRegistry.registerModEntity(EntityVampire.class, "vampire", 3, USub.getID(), 64, 3, true, 0x686c72, 0x000000);
//	}
	
}
