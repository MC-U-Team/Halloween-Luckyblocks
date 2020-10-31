package info.u_team.halloween_luckyblock.init;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockMod;
import info.u_team.u_team_core.api.construct.*;
import info.u_team.u_team_core.util.registry.BusRegister;

@Construct(modid = HalloweenLuckyBlockMod.MODID)
public class HalloweenLuckyBlockCommonConstruct implements IModConstruct {
	
	@Override
	public void construct() {
		BusRegister.registerMod(HalloweenLuckyBlockBlocks::registerMod);
		BusRegister.registerMod(HalloweenLuckyBlockEntityTypes::registerMod);
		BusRegister.registerMod(HalloweenLuckyBlockItems::registerMod);
		BusRegister.registerMod(HalloweenLuckyBlockSounds::registerMod);
		BusRegister.registerMod(HalloweenLuckyBlockTileEntityTypes::registerMod);
	}
	
}
