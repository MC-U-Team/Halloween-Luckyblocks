package info.u_team.halloween_luckyblock.tileentity;

import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockTileEntityTypes;
import info.u_team.halloween_luckyblock.util.PumpkinUtil;
import info.u_team.u_team_core.tileentity.UTickableTileEntity;
import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.block.*;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;

public class TileEntityPumpkinBomb extends UTickableTileEntity {
	
	private int fuse = 120;
	
	public TileEntityPumpkinBomb() {
		super(HalloweenLuckyBlockTileEntityTypes.PUMPKIN_BOMB.get());
	}
	
	@Override
	public void writeNBT(CompoundNBT compound) {
		compound.putInt("fuse", fuse);
	}
	
	@Override
	public void readNBT(BlockState state, CompoundNBT compound) {
		fuse = compound.getInt("fuse");
	}
	
	@Override
	protected void tickServer() {
		if (fuse <= 0) {
			explode();
		}
		fuse--;
	}
	
	@Override
	protected void tickClient() {
		world.addParticle(ParticleTypes.LARGE_SMOKE, pos.getX() + 0.5, pos.getY() + 0.9, pos.getZ() + 0.5, 0, 0.2, 0);
		if (fuse % 20 == 0) {
			world.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.8F, 0.4F / (world.rand.nextFloat() * 2 + 4));
		}
		fuse--;
	}
	
	private void explode() {
		world.setBlockState(pos, Blocks.AIR.getDefaultState());
		
		world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 8, true, Explosion.Mode.BREAK);
		
		final BlockPos highPos = new BlockPos(pos.getX(), pos.getY() + 5, pos.getZ());
		for (int index = 0; index < 35; index++) {
			final BlockPos dropPos = highPos.add(MathUtil.randomNumberInRange(-8, 8), MathUtil.randomNumberInRange(1, 10), MathUtil.randomNumberInRange(-8, 8));
			final FallingBlockEntity falling = new FallingBlockEntity(world, dropPos.getX() + 0.5, dropPos.getY(), dropPos.getZ() + 0.5, PumpkinUtil.getRandomPumpkin());
			falling.fallTime = 100;
			falling.shouldDropItem = false;
			falling.setHurtEntities(true);
			world.addEntity(falling);
		}
	}
}
