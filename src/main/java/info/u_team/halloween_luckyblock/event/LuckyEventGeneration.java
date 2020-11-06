package info.u_team.halloween_luckyblock.event;

import java.io.InputStream;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockMod;
import info.u_team.halloween_luckyblock.core.LuckyEvent;
import info.u_team.u_team_core.schematic.*;
import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class LuckyEventGeneration extends LuckyEvent {
	
	public LuckyEventGeneration() {
		super("Generation", 3);
	}
	
	// TODO Replace with vanilla things, as this is not working well anymore because of the flattening
	@Override
	public void execute(ServerPlayerEntity player, ServerWorld world, BlockPos pos) {
		final InputStream stream = this.getClass().getResourceAsStream("/assets/" + HalloweenLuckyBlockMod.MODID + "/uschematic/" + MathUtil.randomNumberInRange(0, 12) + ".uschematic");
		final USchematicLoadRegion region = new USchematicLoadRegion(world, pos);
		region.rotate(USchematicRotation.values()[MathUtil.randomNumberInRange(0, USchematicRotation.values().length - 1)]);
		try {
			final USchematicReader reader = new USchematicReader(region, stream);
			reader.start();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		new LuckyEventItems().execute(player, world, pos);
	}
	
}
