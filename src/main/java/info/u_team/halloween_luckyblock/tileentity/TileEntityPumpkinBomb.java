package info.u_team.halloween_luckyblock.tileentity;

import info.u_team.u_team_core.tileentity.UTileEntity;
import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;

public class TileEntityPumpkinBomb extends UTileEntity implements ITickable {
	
	private int fuse = 120;
	
	@Override
	public void writeNBT(NBTTagCompound compound) {
		compound.setInteger("fuse", fuse);
	}
	
	@Override
	public void readNBT(NBTTagCompound compound) {
		fuse = compound.getInteger("fuse");
	}
	
	@Override
	public void update() {
		if (fuse <= 0) {
			if (!world.isRemote) {
				explode();
			}
		} else {
			world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.getX() + 0.5D, pos.getY() + 0.9D, pos.getZ() + 0.5D, 0.0D, 0.2D, 0.0D, new int[0]);
			if (fuse % 20 == 0) {
				world.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.8F, 0.4F / (world.rand.nextFloat() * 2F + 4F));
			}
			fuse--;
		}
		
	}
	
	private void explode() {
		world.setBlockToAir(pos);
		world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 8, true);
		
		BlockPos highpos = new BlockPos(pos.getX(), pos.getY() + 5, pos.getZ());
		for (int i = 0; i < 35; i++) {
			BlockPos newpos = highpos.add(MathUtil.getRandomNumberInRange(-8, 8), MathUtil.getRandomNumberInRange(1, 10), MathUtil.getRandomNumberInRange(-8, 8));
			@SuppressWarnings("deprecation")
			EntityFallingBlock falling = new EntityFallingBlock(world, newpos.getX(), newpos.getY(), newpos.getZ(), (MathUtil.getRandomNumberInRange(0, 1) == 0 ? Blocks.PUMPKIN : Blocks.LIT_PUMPKIN).getStateFromMeta(MathUtil.getRandomNumberInRange(0, 3)));
			falling.fallTime = 100;
			falling.shouldDropItem = false;
			falling.setHurtEntities(true);
			world.spawnEntity(falling);
		}
	}
	
}
