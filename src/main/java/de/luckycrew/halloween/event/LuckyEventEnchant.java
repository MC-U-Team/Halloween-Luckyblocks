package de.luckycrew.halloween.event;

import java.util.ArrayList;

import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.entity.item.*;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class LuckyEventEnchant extends LuckyEventCustom {
	
	public LuckyEventEnchant() {
		super("Enchant", 1);
	}
	
	@Override
	public void execute(EntityPlayerMP player, final World world, final BlockPos pos) {
		ArrayList<BlockPos> positions = new ArrayList<BlockPos>();
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
					BlockPos p = new BlockPos(x, y, z);
					positions.remove(p);
				}
			}
		}
		for (BlockPos p : positions) {
			world.setBlockState(pos.add(p), Blocks.bookshelf.getDefaultState());
		}
		EntityFallingBlock falling = new EntityFallingBlock(world, pos.getX(), pos.getY() + 10, pos.getZ(), Blocks.enchanting_table.getDefaultState());
		falling.fallTime = 1;
		world.spawnEntityInWorld(falling);
		EntityItem item = new EntityItem(world, pos.getX(), pos.getY() + 10, pos.getZ(), new ItemStack(Items.dye, MathUtil.getRandomNumberInRange(12, 54), 4));
		world.spawnEntityInWorld(item);
		for (int i = 0; i < 20; i++) {
			EntityXPOrb xp = new EntityXPOrb(world, pos.getX(), pos.getY(), pos.getZ(), MathUtil.getRandomNumberInRange(1, 5));
			xp.motionX = MathUtil.getRandomNumberInRange(-0.5, 0.5);
			xp.motionY = MathUtil.getRandomNumberInRange(0.2, 0.6);
			xp.motionZ = MathUtil.getRandomNumberInRange(-0.5, 0.5);
			world.spawnEntityInWorld(xp);
		}
		player.addExperienceLevel(10);
	}
}
