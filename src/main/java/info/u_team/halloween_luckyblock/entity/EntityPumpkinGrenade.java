package info.u_team.halloween_luckyblock.entity;

import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityPumpkinGrenade extends EntityThrowable {
	
	public EntityPumpkinGrenade(World world) {
		super(world);
	}
	
	public EntityPumpkinGrenade(World world, EntityLivingBase entity) {
		super(world, entity);
	}
	
	public EntityPumpkinGrenade(World world, double x, double y, double z) {
		super(world, x, y, z);
	}
	
	@Override
	public void onImpact(MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), 10F);
		}
		if (!this.worldObj.isRemote) {
			BlockPos pos = movingobjectposition.getBlockPos();
			if (pos == null) {
				pos = new BlockPos(posX, posY, posZ);
			}
			explode(pos);
			this.setDead();
		}
	}
	
	private void explode(BlockPos pos) {
		worldObj.createExplosion(this, pos.getX(), pos.getY(), pos.getZ(), 7, true);
		
		BlockPos highpos = new BlockPos(pos.getX(), pos.getY() + 5, pos.getZ());
		for (int i = 0; i < 20; i++) {
			BlockPos newpos = highpos.add(MathUtil.getRandomNumberInRange(-6, 6), MathUtil.getRandomNumberInRange(-5, 2), MathUtil.getRandomNumberInRange(-6, 6));
			EntityFallingBlock falling = new EntityFallingBlock(worldObj, newpos.getX(), newpos.getY(), newpos.getZ(), (MathUtil.getRandomNumberInRange(0, 1) == 0 ? Blocks.pumpkin : Blocks.lit_pumpkin).getStateFromMeta(MathUtil.getRandomNumberInRange(0, 3)));
			falling.fallTime = 100;
			falling.shouldDropItem = false;
			falling.setHurtEntities(true);
			worldObj.spawnEntityInWorld(falling);
		}
	}
	
	@Override
	public float getGravityVelocity() {
		return 0.03F;
	}
	
	@Override
	public float getVelocity() {
		return 3.0F;
	}
	
	@Override
	public float getInaccuracy() {
		return 0.5F;
	}
}