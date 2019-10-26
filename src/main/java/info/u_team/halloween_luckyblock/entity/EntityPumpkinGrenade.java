package info.u_team.halloween_luckyblock.entity;

import info.u_team.halloween_luckyblock.init.*;
import info.u_team.halloween_luckyblock.util.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.*;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.*;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityPumpkinGrenade extends ProjectileItemEntity {
	
	public EntityPumpkinGrenade(EntityType<? extends EntityPumpkinGrenade> type, World world) {
		super(type, world);
	}
	
	public EntityPumpkinGrenade(World world, LivingEntity throwerIn) {
		super(HalloweenLuckyBlockEntityTypes.PUMPKINGRENADE, throwerIn, world);
	}
	
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	@Override
	protected void onImpact(RayTraceResult result) {
		if (!world.isRemote) {
			if (result.getType() == Type.ENTITY) {
				((EntityRayTraceResult) result).getEntity().attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), 10F);
			} else if (result.getType() == Type.BLOCK) {
				BlockPos pos = ((BlockRayTraceResult) result).getPos();
				if (pos == null) {
					pos = new BlockPos(posX, posY, posZ);
				}
				explode(pos);
				remove();
			}
		}
	}
	
	private void explode(BlockPos pos) {
		world.createExplosion(this, pos.getX(), pos.getY(), pos.getZ(), 7, true, Explosion.Mode.BREAK);
		
		BlockPos highpos = new BlockPos(pos.getX(), pos.getY() + 5, pos.getZ());
		for (int i = 0; i < 20; i++) {
			BlockPos newpos = highpos.add(MathUtil.getRandomNumberInRange(-6, 6), MathUtil.getRandomNumberInRange(-5, 2), MathUtil.getRandomNumberInRange(-6, 6));
			FallingBlockEntity falling = new FallingBlockEntity(world, newpos.getX() + .5, newpos.getY(), newpos.getZ() + .5, PumpkinUtil.getRandomPumpkin());
			falling.fallTime = 100;
			falling.shouldDropItem = false;
			falling.setHurtEntities(true);
			world.addEntity(falling);
		}
	}
	
	@Override
	public float getGravityVelocity() {
		return 0.03F;
	}
	
	@Override
	protected Item func_213885_i() {
		return HalloweenLuckyBlockItems.PUMPKINGRENADE;
	}
}