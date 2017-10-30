package de.luckycrew.halloween.event;

import java.io.InputStream;

import de.luckycrew.halloween.HalloweenConstants;
import info.u_team.u_team_core.schematic.*;
import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class LuckyEventGeneration extends LuckyEventCustom {
	
	public LuckyEventGeneration() {
		super("Generation", 3);
	}
	
	@Override
	public void execute(EntityPlayerMP player, World world, BlockPos pos) {
		InputStream stream = this.getClass().getResourceAsStream("/assets/" + HalloweenConstants.MODID + "/uschematic/" + MathUtil.getRandomNumberInRange(0, 12) + ".uschematic");
		USchematicLoadRegion region = new USchematicLoadRegion(world, pos);
		region.rotate(USchematicRotation.values()[MathUtil.getRandomNumberInRange(0, USchematicRotation.values().length - 1)]);
		try {
			USchematicReader reader = new USchematicReader(region, stream);
			reader.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		new LuckyEventItems().execute(player, world, pos);
	}
	
}
