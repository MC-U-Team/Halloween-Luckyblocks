package de.luckycrew.halloween.tileentity;

import info.u_team.u_team_core.tileentity.UTileEntity;
import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.util.*;

public class TileEntityPumpkinBomb extends UTileEntity implements IUpdatePlayerListBox {
	
	private int fuse = 120;
	
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("fuse", fuse);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		fuse = compound.getInteger("fuse");
	}
	
	@Override
	public void update() {
		if (fuse <= 0) {
			if (!worldObj.isRemote) {
				explode();
			}
		} else {
			worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.getX() + 0.5D, pos.getY() + 0.9D, pos.getZ() + 0.5D, 0.0D, 0.2D, 0.0D, new int[0]);
			fuse--;
		}
		
	}
	
	private void explode() {
		worldObj.setBlockToAir(pos);
		worldObj.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 8, true);
		
		BlockPos highpos = new BlockPos(pos.getX(), pos.getY() + 5, pos.getZ());
		for (int i = 0; i < 35; i++) {
			BlockPos newpos = highpos.add(MathUtil.getRandomNumberInRange(-8, 8), MathUtil.getRandomNumberInRange(1, 10), MathUtil.getRandomNumberInRange(-8, 8));
			EntityFallingBlock falling = new EntityFallingBlock(worldObj, newpos.getX(), newpos.getY(), newpos.getZ(), (MathUtil.getRandomNumberInRange(0, 1) == 0 ? Blocks.pumpkin : Blocks.lit_pumpkin).getDefaultState());
			falling.fallTime = 100;
			falling.shouldDropItem = false;
			falling.setHurtEntities(true);
			worldObj.spawnEntityInWorld(falling);
		}
	}
}
