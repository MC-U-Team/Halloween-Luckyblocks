package info.u_team.halloween_luckyblock.entity;

import info.u_team.halloween_luckyblock.init.*;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.passive.AmbientEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class EntityVampire extends AmbientEntity {
	
	private static final DataParameter<Byte> HANGING = EntityDataManager.<Byte> createKey(EntityVampire.class, DataSerializers.BYTE);
	private static final EntityPredicate field_213813_c = (new EntityPredicate()).setDistance(4.0D).allowFriendlyFire();
	private BlockPos spawnPosition;
	
	public EntityVampire(EntityType<? extends EntityVampire> type, World world) {
		super(type, world);
		// this.setSize(2.0F, 2.0F); TODO
		this.setIsBatHanging(true);
		this.goalSelector.addGoal(2, new AIAttack());
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, e -> true));
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(HANGING, (byte) 0);
	}
	
	@Override
	protected float getSoundVolume() {
		return 0.5F;
	}
	
	@Override
	protected float getSoundPitch() {
		return super.getSoundPitch() * 0.95F;
	}
	
	@Override
	public SoundEvent getAmbientSound() {
		return this.getIsBatHanging() && this.rand.nextInt(4) != 0 ? null : SoundEvents.ENTITY_BAT_AMBIENT;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return HalloweenLuckyBlockSounds.scubi_dabi;
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return HalloweenLuckyBlockSounds.wind;
	}
	
	@Override
	public boolean canBePushed() {
		return false;
	}
	
	@Override
	protected void collideWithEntity(Entity p_82167_1_) {
	}
	
	@Override
	protected void collideWithNearbyEntities() {
	}
	
	public boolean getIsBatHanging() {
		return (this.dataManager.get(HANGING).byteValue() & 1) != 0;
	}
	
	public void setIsBatHanging(boolean isHanging) {
		byte b0 = this.dataManager.get(HANGING).byteValue();
		
		if (isHanging) {
			this.dataManager.set(HANGING, Byte.valueOf((byte) (b0 | 1)));
		} else {
			this.dataManager.set(HANGING, Byte.valueOf((byte) (b0 & -2)));
		}
	}
	
	@Override
	public void tick() {
		super.tick();
		
		if (this.getIsBatHanging()) {
			this.setMotion(Vec3d.ZERO);
			this.posY = (double) MathHelper.floor(this.posY) + 1.0D - (double) this.getHeight();
		} else {
			this.setMotion(this.getMotion().mul(1.0D, 0.6D, 1.0D));
		}
	}
	
	@Override
	protected void updateAITasks() {
		super.updateAITasks();
		BlockPos blockpos = new BlockPos(this);
		BlockPos blockpos1 = blockpos.up();
		if (this.getIsBatHanging()) {
			if (this.world.getBlockState(blockpos1).isNormalCube(this.world, blockpos)) {
				if (this.rand.nextInt(200) == 0) {
					this.rotationYawHead = (float) this.rand.nextInt(360);
				}
				
				if (this.world.getClosestPlayer(field_213813_c, this) != null) {
					this.setIsBatHanging(false);
					this.world.playEvent((PlayerEntity) null, 1025, blockpos, 0);
				}
			} else {
				this.setIsBatHanging(false);
				this.world.playEvent((PlayerEntity) null, 1025, blockpos, 0);
			}
		} else {
			if (this.spawnPosition != null && (!this.world.isAirBlock(this.spawnPosition) || this.spawnPosition.getY() < 1)) {
				this.spawnPosition = null;
			}
			
			if (this.spawnPosition == null || this.rand.nextInt(30) == 0 || this.spawnPosition.withinDistance(this.getPositionVec(), 2.0D)) {
				this.spawnPosition = new BlockPos(this.posX + (double) this.rand.nextInt(7) - (double) this.rand.nextInt(7), this.posY + (double) this.rand.nextInt(6) - 2.0D, this.posZ + (double) this.rand.nextInt(7) - (double) this.rand.nextInt(7));
			}
			
			double d0 = (double) this.spawnPosition.getX() + 0.5D - this.posX;
			double d1 = (double) this.spawnPosition.getY() + 0.1D - this.posY;
			double d2 = (double) this.spawnPosition.getZ() + 0.5D - this.posZ;
			Vec3d vec3d = this.getMotion();
			Vec3d vec3d1 = vec3d.add((Math.signum(d0) * 0.5D - vec3d.x) * (double) 0.1F, (Math.signum(d1) * (double) 0.7F - vec3d.y) * (double) 0.1F, (Math.signum(d2) * 0.5D - vec3d.z) * (double) 0.1F);
			this.setMotion(vec3d1);
			float f = (float) (MathHelper.atan2(vec3d1.z, vec3d1.x) * (double) (180F / (float) Math.PI)) - 90.0F;
			float f1 = MathHelper.wrapDegrees(f - this.rotationYaw);
			this.moveForward = 0.5F;
			this.rotationYaw += f1;
			if (this.rand.nextInt(100) == 0 && this.world.getBlockState(blockpos1).isNormalCube(this.world, blockpos1)) {
				this.setIsBatHanging(true);
			}
		}
		
	}
	
	@Override
	protected boolean canTriggerWalking() {
		return false;
	}
	
	@Override
	public void fall(float distance, float damageMultiplier) {
	}
	
	@Override
	protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
	}
	
	@Override
	public boolean doesEntityNotTriggerPressurePlate() {
		return true;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (this.isInvulnerableTo(source)) {
			return false;
		} else {
			if (!this.world.isRemote && this.getIsBatHanging()) {
				this.setIsBatHanging(false);
			}
			
			return super.attackEntityFrom(source, amount);
		}
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.dataManager.set(HANGING, compound.getByte("BatFlags"));
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putByte("BatFlags", this.dataManager.get(HANGING));
	}
	
	@Override
	public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {
		BlockPos blockpos = new BlockPos(this.posX, this.getBoundingBox().minY, this.posZ);
		if (blockpos.getY() >= this.world.getSeaLevel()) {
			return false;
		} else {
			int i = this.world.getLight(blockpos);
			int j = 7;
			return i > this.rand.nextInt(j) ? false : super.canSpawn(worldIn, spawnReasonIn);
		}
	}
	
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return sizeIn.height / 2.0F;
	}
	
	public class AIAttack extends Goal {
		
		private EntityVampire vampire = EntityVampire.this;
		public int ticks;
		
		@Override
		public boolean shouldExecute() {
			return this.vampire.getAttackTarget() != null;
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
			LivingEntity entitylivingbase = this.vampire.getAttackTarget();
			double d0 = 64.0D;
			
			if (entitylivingbase.getDistanceSq(this.vampire) < d0 * d0 && this.vampire.canEntityBeSeen(entitylivingbase)) {
				System.out.println(ticks);
				World world = this.vampire.world;
				++this.ticks;
				if (this.ticks == 390) {
					world.playSound(null, vampire.getPosition(), HalloweenLuckyBlockSounds.happy_halloween, SoundCategory.NEUTRAL, 1.0F, ((world.rand.nextFloat() * 0.8F) + 0.6F));
					FallingBlockEntity falling = new FallingBlockEntity(world, entitylivingbase.posX, entitylivingbase.posY + 5, entitylivingbase.posZ, HalloweenLuckyBlockBlocks.PUMPKINBOMB.getDefaultState());
					falling.fallTime = 100;
					falling.shouldDropItem = false;
					falling.setHurtEntities(false);
					world.addEntity(falling);
					vampire.damageEntity(DamageSource.causeMobDamage(entitylivingbase), 4.0F);
					this.ticks = -10;
				}
			} else if (this.ticks > 0) {
				--this.ticks;
			}
			
		}
		
	}
}