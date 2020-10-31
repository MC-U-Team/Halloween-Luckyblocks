package info.u_team.halloween_luckyblock.init;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockMod;
import info.u_team.halloween_luckyblock.tileentity.TileEntityPumpkinBomb;
import info.u_team.u_team_core.util.registry.TileEntityTypeDeferredRegister;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;

public class HalloweenLuckyBlockTileEntityTypes {
	
	public static final TileEntityTypeDeferredRegister TILE_ENTITY_TYPES = TileEntityTypeDeferredRegister.create(HalloweenLuckyBlockMod.MODID);
	
	public static final RegistryObject<TileEntityType<TileEntityPumpkinBomb>> PUMPKIN_BOMB = TILE_ENTITY_TYPES.register("pumpkin_bomb", () -> TileEntityType.Builder.create(TileEntityPumpkinBomb::new, HalloweenLuckyBlockBlocks.PUMPKINBOMB.get()));
	
	public static void registerMod(IEventBus bus) {
		TILE_ENTITY_TYPES.register(bus);
	}
}
