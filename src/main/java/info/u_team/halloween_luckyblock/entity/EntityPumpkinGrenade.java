package info.u_team.halloween_luckyblock.entity;

import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.*;
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
	protected void onImpact(RayTraceResult result) {
		if (result.entityHit != null) {
			result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), 10F);
		}
		if (!world.isRemote) {
			BlockPos pos = result.getBlockPos();
			if (pos == null) {
				pos = new BlockPos(posX, posY, posZ);
			}
			explode(pos);
			this.setDead();
		}
	}
	
	private void explode(BlockPos pos) {
		world.createExplosion(this, pos.getX(), pos.getY(), pos.getZ(), 7, true);
		
		BlockPos highpos = new BlockPos(pos.getX(), pos.getY() + 5, pos.getZ());
		for (int i = 0; i < 20; i++) {
			BlockPos newpos = highpos.add(MathUtil.getRandomNumberInRange(-6, 6), MathUtil.getRandomNumberInRange(-5, 2), MathUtil.getRandomNumberInRange(-6, 6));
			@SuppressWarnings("deprecation")
			EntityFallingBlock falling = new EntityFallingBlock(world, newpos.getX(), newpos.getY(), newpos.getZ(), (MathUtil.getRandomNumberInRange(0, 1) == 0 ? Blocks.PUMPKIN : Blocks.LIT_PUMPKIN).getStateFromMeta(MathUtil.getRandomNumberInRange(0, 3)));
			falling.fallTime = 100;
			falling.shouldDropItem = false;
			falling.setHurtEntities(true);
			world.spawnEntity(falling);
		}
	}
	
	@Override
	public float getGravityVelocity() {
		return 0.03F;
	}
}