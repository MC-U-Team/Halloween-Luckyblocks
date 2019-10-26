package info.u_team.halloween_luckyblock.init;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockMod;
import info.u_team.halloween_luckyblock.tileentity.TileEntityPumpkinBomb;
import info.u_team.u_team_core.tileentitytype.UTileEntityType.UBuilder;
import info.u_team.u_team_core.util.registry.BaseRegistryUtil;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = HalloweenLuckyBlockMod.MODID, bus = Bus.MOD)
public class HalloweenLuckyBlockTileEntityTypes {
	
	public static final TileEntityType<TileEntityPumpkinBomb> PUMPKINBOMB = UBuilder.create("pumpkinbomb", TileEntityPumpkinBomb::new, HalloweenLuckyBlockBlocks.PUMPKINBOMB).build();
	
	@SubscribeEvent
	public static void register(Register<TileEntityType<?>> event) {
		BaseRegistryUtil.getAllGenericRegistryEntriesAndApplyNames(HalloweenLuckyBlockMod.MODID, TileEntityType.class).forEach(event.getRegistry()::register);
	}
}
