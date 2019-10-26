package info.u_team.halloween_luckyblock.entity;

import java.util.*;

import info.u_team.halloween_luckyblock.init.*;
import info.u_team.halloween_luckyblock.network.MessageGhostFlash;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraftforge.fml.network.PacketDistributor;

public class EntityGhost extends FlyingEntity implements IMob {
	
	public EntityGhost(EntityType<? extends EntityGhost> type, World world) {
		super(type, world);
		this.experienceValue = 12;
		this.moveController = new EntityGhost.GhostMoveHelper(this);
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(5, new EntityGhost.AIRandomFly());
		this.goalSelector.addGoal(7, new EntityGhost.AILookAround());
		this.goalSelector.addGoal(7, new EntityGhost.AIAttack());
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, (p_213812_1_) -> true));
	}
	
	@Override
	public void tick() {
		super.tick();
		if (!world.isRemote && world.getDifficulty() == Difficulty.PEACEFUL) {
			remove();
		}
	}
	
	@Override
	public void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(100.0D);
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return HalloweenLuckyBlockSounds.snivelling;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return HalloweenLuckyBlockSounds.strange_laughing;
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return HalloweenLuckyBlockSounds.thunder;
	}
	
	@Override
	public float getSoundVolume() {
		return 2.0F;
	}
	
	@Override
	public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {
		return super.canSpawn(worldIn, spawnReasonIn) && world.getDifficulty() != Difficulty.PEACEFUL;
	}
	
	@Override
	public int getMaxSpawnedInChunk() {
		return 2;
	}
	
	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.6F;
	}
	
	class AIAttack extends Goal {
		
		private EntityGhost ghost = EntityGhost.this;
		public int ticks;
		
		@Override
		public boolean shouldExecute() {
			return this.ghost.getAttackTarget() != null;
		}
		
		@Override
		public void startExecuting() {
			this.ticks = 0;
		}
		
		@Override
		public void resetTask() {
		}
		
		@Override
		public void tick() {
			LivingEntity entitylivingbase = this.ghost.getAttackTarget();
			double d0 = 64.0D;
			
			if (entitylivingbase.getDistanceSq(this.ghost) < d0 * d0 && this.ghost.canEntityBeSeen(entitylivingbase)) {
				World world = ghost.world;
				++this.ticks;
				if (this.ticks == 90) {
					world.playSound(null, entitylivingbase.getPosition(), HalloweenLuckyBlockSounds.ringle, SoundCategory.HOSTILE, 1.0F, ((world.rand.nextFloat() * 0.8F) + 0.6F));
					entitylivingbase.attackEntityFrom(DamageSource.MAGIC, 4.0F);
					if (!world.isRemote && entitylivingbase instanceof ServerPlayerEntity) {
						HalloweenLuckyBlockNetwork.NETWORK.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) entitylivingbase), new MessageGhostFlash());
					}
					this.ticks = -10;
				}
			} else if (this.ticks > 0) {
				--this.ticks;
			}
		}
	}
	
	class AILookAround extends Goal {
		
		private EntityGhost ghost = EntityGhost.this;
		
		public AILookAround() {
			this.setMutexFlags(EnumSet.of(Goal.Flag.LOOK));
		}
		
		@Override
		public boolean shouldExecute() {
			return true;
		}
		
		@Override
		public void tick() {
			if (this.ghost.getAttackTarget() == null) {
				this.ghost.renderYawOffset = this.ghost.rotationYaw = -((float) Math.atan2(this.ghost.getMotion().getX(), this.ghost.getMotion().getZ())) * 180.0F / (float) Math.PI;
			} else {
				LivingEntity entitylivingbase = this.ghost.getAttackTarget();
				double d0 = 64.0D;
				
				if (entitylivingbase.getDistanceSq(this.ghost) < d0 * d0) {
					double d1 = entitylivingbase.posX - this.ghost.posX;
					double d2 = entitylivingbase.posZ - this.ghost.posZ;
					this.ghost.renderYawOffset = this.ghost.rotationYaw = -((float) Math.atan2(d1, d2)) * 180.0F / (float) Math.PI;
				}
			}
		}
	}
	
	class AIRandomFly extends Goal {
		
		private EntityGhost ghost = EntityGhost.this;
		
		public AIRandomFly() {
			this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
		}
		
		@Override
		public boolean shouldExecute() {
			MovementController entitymovehelper = this.ghost.getMoveHelper();
			
			if (!entitymovehelper.isUpdating()) {
				return true;
			} else {
				double d0 = entitymovehelper.getX() - this.ghost.posX;
				double d1 = entitymovehelper.getY() - this.ghost.posY;
				double d2 = entitymovehelper.getZ() - this.ghost.posZ;
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				return d3 < 1.0D || d3 > 3600.0D;
			}
		}
		
		public boolean continueExecuting() {
			return false;
		}
		
		@Override
		public void startExecuting() {
			Random random = this.ghost.getRNG();
			double d0 = this.ghost.posX + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
			double d1 = this.ghost.posY + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
			double d2 = this.ghost.posZ + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
			this.ghost.getMoveHelper().setMoveTo(d0, d1, d2, 1.0D);
		}
	}
	
	static class GhostMoveHelper extends MovementController {
		
		private final double speed = 0.25D;
		
		private final EntityGhost parentEntity;
		private int courseChangeCooldown;
		
		public GhostMoveHelper(EntityGhost ghost) {
			super(ghost);
			this.parentEntity = ghost;
		}
		
		@Override
		public void tick() {
			if (this.action == MovementController.Action.MOVE_TO) {
				if (this.courseChangeCooldown-- <= 0) {
					this.courseChangeCooldown += this.parentEntity.getRNG().nextInt(5) + 2;
					Vec3d vec3d = new Vec3d(this.posX - this.parentEntity.posX, this.posY - this.parentEntity.posY, this.posZ - this.parentEntity.posZ);
					double d0 = vec3d.length();
					vec3d = vec3d.normalize();
					if (this.func_220673_a(vec3d, MathHelper.ceil(d0))) {
						this.parentEntity.setMotion(this.parentEntity.getMotion().add(vec3d.scale(speed)));
					} else {
						this.action = MovementController.Action.WAIT;
					}
				}
				
			}
		}
		
		private boolean func_220673_a(Vec3d p_220673_1_, int p_220673_2_) {
			AxisAlignedBB axisalignedbb = this.parentEntity.getBoundingBox();
			
			for (int i = 1; i < p_220673_2_; ++i) {
				axisalignedbb = axisalignedbb.offset(p_220673_1_);
				if (!this.parentEntity.world.isCollisionBoxesEmpty(this.parentEntity, axisalignedbb)) {
					return false;
				}
			}
			
			return true;
		}
	}
}