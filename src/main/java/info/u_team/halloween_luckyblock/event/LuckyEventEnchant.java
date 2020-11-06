package info.u_team.halloween_luckyblock.event;

import java.util.ArrayList;

import info.u_team.halloween_luckyblock.core.LuckyEvent;
import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.*;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class LuckyEventEnchant extends LuckyEvent {
	
	public LuckyEventEnchant() {
		super("Enchant", 1);
	}
	
	@Override
	public void execute(ServerPlayerEntity player, ServerWorld world, BlockPos pos) {
		final ArrayList<BlockPos> positions = new ArrayList<BlockPos>();
		for (int x = -2; x <= 2; x++) {
			for (int y = 0; y <= 2; y++) {
				for (int z = -2; z <= 2; z++) {
					positions.add(new BlockPos(x, y, z));
				}
			}
		}
		for (int x = -1; x <= 1; x++) {
			for (int y = 0; y <= 2; y++) {
				for (int z = -1; z <= 1; z++) {
					final BlockPos p = new BlockPos(x, y, z);
					positions.remove(p);
				}
			}
		}
		for (final BlockPos p : positions) {
			world.setBlockState(pos.add(p), Blocks.BOOKSHELF.getDefaultState());
		}
		final FallingBlockEntity falling = new FallingBlockEntity(world, pos.getX() + .5, pos.getY() + 10, pos.getZ() + .5, Blocks.ENCHANTING_TABLE.getDefaultState());
		falling.fallTime = 1;
		world.addEntity(falling);
		final ItemEntity item = new ItemEntity(world, pos.getX(), pos.getY() + 10, pos.getZ(), new ItemStack(Items.LAPIS_LAZULI, MathUtil.randomNumberInRange(12, 54)));
		world.addEntity(item);
		for (int i = 0; i < 20; i++) {
			final ExperienceOrbEntity xp = new ExperienceOrbEntity(world, pos.getX(), pos.getY(), pos.getZ(), MathUtil.randomNumberInRange(1, 5));
			xp.setMotion(MathUtil.randomNumberInRange(-0.5, 0.5), MathUtil.randomNumberInRange(0.2, 0.6), MathUtil.randomNumberInRange(-0.5, 0.5));
			world.addEntity(xp);
		}
		player.addExperienceLevel(10);
	}
}
