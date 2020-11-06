package info.u_team.halloween_luckyblock.entity;

import info.u_team.halloween_luckyblock.init.*;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap.MutableAttribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.passive.AmbientEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.*;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityVampire extends AmbientEntity {
	
	private static final DataParameter<Byte> HANGING = EntityDataManager.<Byte> createKey(EntityVampire.class, DataSerializers.BYTE);
	private static final EntityPredicate field_213813_c = (new EntityPredicate()).setDistance(4.0D).allowFriendlyFire();
	private BlockPos spawnPosition;
	
	public EntityVampire(World worldIn) {
		this(HalloweenLuckyBlockEntityTypes.VAMPIRE.get(), worldIn);
	}
	
	public EntityVampire(EntityType<? extends EntityVampire> type, World world) {
		super(type, world);
		this.setIsBatHanging(true);
	}
	
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(2, new AIAttack());
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, e -> true));
	}
	
	public static MutableAttribute registerAttributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 6.0D);
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
		return HalloweenLuckyBlockSounds.SCUBI_DABI.get();
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return HalloweenLuckyBlockSounds.WIND.get();
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
			this.setMotion(Vector3d.ZERO);
			setRawPosition(getPosX(), (double) MathHelper.floor(getPosY()) + 1.0D - (double) this.getHeight(), getPosZ());
		} else {
			this.setMotion(this.getMotion().mul(1.0D, 0.6D, 1.0D));
		}
	}
	
	@Override
	protected void updateAITasks() {
		super.updateAITasks();
		BlockPos blockpos = getPosition();
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
				this.spawnPosition = new BlockPos(this.getPosX() + (double) this.rand.nextInt(7) - (double) this.rand.nextInt(7), this.getPosY() + (double) this.rand.nextInt(6) - 2.0D, this.getPosZ() + (double) this.rand.nextInt(7) - (double) this.rand.nextInt(7));
			}
			
			double d0 = (double) this.spawnPosition.getX() + 0.5D - this.getPosX();
			double d1 = (double) this.spawnPosition.getY() + 0.1D - this.getPosY();
			double d2 = (double) this.spawnPosition.getZ() + 0.5D - this.getPosZ();
			Vector3d vec3d = this.getMotion();
			Vector3d vec3d1 = vec3d.add((Math.signum(d0) * 0.5D - vec3d.x) * (double) 0.1F, (Math.signum(d1) * (double) 0.7F - vec3d.y) * (double) 0.1F, (Math.signum(d2) * 0.5D - vec3d.z) * (double) 0.1F);
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
	public boolean onLivingFall(float distance, float damageMultiplier) {
		return false;
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
		BlockPos blockpos = new BlockPos(this.getPosX(), this.getBoundingBox().minY, this.getPosZ());
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
				World world = this.vampire.world;
				++this.ticks;
				if (this.ticks == 390) {
					world.playSound(null, vampire.getPosition(), HalloweenLuckyBlockSounds.HAPPY_HALLOWEEN.get(), SoundCategory.NEUTRAL, 1.0F, ((world.rand.nextFloat() * 0.8F) + 0.6F));
					FallingBlockEntity falling = new FallingBlockEntity(world, entitylivingbase.getPosX(), entitylivingbase.getPosY() + 5, entitylivingbase.getPosZ(), HalloweenLuckyBlockBlocks.PUMPKINBOMB.get().getDefaultState());
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