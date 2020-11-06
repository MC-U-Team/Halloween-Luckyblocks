package info.u_team.halloween_luckyblock.util;

import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.block.*;
import net.minecraft.util.Direction;

public class PumpkinUtil {
	
	public static BlockState getRandomPumpkin() {
		final int random = MathUtil.getRandomNumberInRange(0, 2);
		if (random == 0) {
			return Blocks.PUMPKIN.getDefaultState();
		} else {
			return (random == 1 ? Blocks.CARVED_PUMPKIN : Blocks.JACK_O_LANTERN).getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, Direction.byHorizontalIndex(MathUtil.getRandomNumberInRange(0, 3)));
		}
	}
	
}
