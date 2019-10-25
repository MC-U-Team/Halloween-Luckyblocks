package info.u_team.halloween_luckyblock.tileentity;

import info.u_team.halloween_luckyblock.util.MathUtil;
import info.u_team.u_team_core.tileentity.UTileEntity;
import info.u_team.u_team_core.util.BlockProperties;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;

public class TileEntityPumpkinBomb extends UTileEntity implements ITickableTileEntity {
	
	private int fuse = 120;
	
	@Override
	public void writeNBT(CompoundNBT compound) {
		compound.putInt("fuse", fuse);
	}
	
	@Override
	public void readNBT(CompoundNBT compound) {
		fuse = compound.getInt("fuse");
	}
	
	@Override
	public void tick() {
		if (fuse <= 0) {
			if (!world.isRemote) {
				explode();
			}
		} else {
			world.addParticle(ParticleTypes.LARGE_SMOKE, pos.getX() + 0.5D, pos.getY() + 0.9D, pos.getZ() + 0.5D, 0.0D, 0.2D, 0.0D);
			if (fuse % 20 == 0) {
				world.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.8F, 0.4F / (world.rand.nextFloat() * 2F + 4F));
			}
			fuse--;
		}
		
	}
	
	private void explode() {
		world.setBlockState(pos, Blocks.AIR.getDefaultState());
		world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 8, true, Explosion.Mode.BREAK);
		
		BlockPos highpos = new BlockPos(pos.getX(), pos.getY() + 5, pos.getZ());
		for (int i = 0; i < 35; i++) {
			BlockPos newpos = highpos.add(MathUtil.getRandomNumberInRange(-8, 8), MathUtil.getRandomNumberInRange(1, 10), MathUtil.getRandomNumberInRange(-8, 8));
			@SuppressWarnings("deprecation")
			FallingBlockEntity falling = new FallingBlockEntity(world, newpos.getX() + 0.5, newpos.getY(), newpos.getZ() + 0.5, (MathUtil.getRandomNumberInRange(0, 1) == 0 ? Blocks.PUMPKIN : Blocks.JACK_O_LANTERN).getDefaultState().with(, value)(MathUtil.getRandomNumberInRange(0, 3))));
			falling.fallTime = 100;
			falling.shouldDropItem = false;
			falling.setHurtEntities(true);
			world.addEntity(falling);
		}
	}
	
}
