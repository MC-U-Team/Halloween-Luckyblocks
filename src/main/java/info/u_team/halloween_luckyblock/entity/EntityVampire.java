package info.u_team.halloween_luckyblock.entity;

import info.u_team.halloween_luckyblock.init.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

public class EntityVampire extends EntityAmbientCreature {
	
	private static final DataParameter<Byte> HANGING = EntityDataManager.<Byte> createKey(EntityBat.class, DataSerializers.BYTE);
	private BlockPos spawnPosition;
	
	public EntityVampire(World worldIn) {
		super(worldIn);
		this.setSize(2.0F, 2.0F);
		this.setIsBatHanging(true);
		this.tasks.addTask(7, new EntityVampire.AIAttack());
		this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
	}
	
	@Override
	public void entityInit() {
		super.entityInit();
		this.dataManager.register(HANGING, Byte.valueOf((byte) 0));
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
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
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
	public void onUpdate() {
		super.onUpdate();
		
		if (this.getIsBatHanging()) {
			this.motionX = 0.0D;
			this.motionY = 0.0D;
			this.motionZ = 0.0D;
			this.posY = MathHelper.floor(this.posY) + 1.0D - this.height;
		} else {
			this.motionY *= 0.6000000238418579D;
		}
	}
	
	@Override
	protected void updateAITasks() {
		super.updateAITasks();
		BlockPos blockpos = new BlockPos(this);
		BlockPos blockpos1 = blockpos.up();
		
		if (this.getIsBatHanging()) {
			if (this.world.getBlockState(blockpos1).isNormalCube()) {
				if (this.rand.nextInt(200) == 0) {
					this.rotationYawHead = this.rand.nextInt(360);
				}
				
				if (this.world.getNearestPlayerNotCreative(this, 4.0D) != null) {
					this.setIsBatHanging(false);
					this.world.playEvent((EntityPlayer) null, 1025, blockpos, 0);
				}
			} else {
				this.setIsBatHanging(false);
				this.world.playEvent((EntityPlayer) null, 1025, blockpos, 0);
			}
		} else {
			if (this.spawnPosition != null && (!this.world.isAirBlock(this.spawnPosition) || this.spawnPosition.getY() < 1)) {
				this.spawnPosition = null;
			}
			
			if (this.spawnPosition == null || this.rand.nextInt(30) == 0 || this.spawnPosition.distanceSq(((int) this.posX), ((int) this.posY), ((int) this.posZ)) < 4.0D) {
				this.spawnPosition = new BlockPos((int) this.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int) this.posY + this.rand.nextInt(6) - 2, (int) this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
			}
			
			double d0 = this.spawnPosition.getX() + 0.5D - this.posX;
			double d1 = this.spawnPosition.getY() + 0.1D - this.posY;
			double d2 = this.spawnPosition.getZ() + 0.5D - this.posZ;
			this.motionX += (Math.signum(d0) * 0.5D - this.motionX) * 0.10000000149011612D;
			this.motionY += (Math.signum(d1) * 0.699999988079071D - this.motionY) * 0.10000000149011612D;
			this.motionZ += (Math.signum(d2) * 0.5D - this.motionZ) * 0.10000000149011612D;
			float f = (float) (MathHelper.atan2(this.motionZ, this.motionX) * (180D / Math.PI)) - 90.0F;
			float f1 = MathHelper.wrapDegrees(f - this.rotationYaw);
			this.moveForward = 0.5F;
			this.rotationYaw += f1;
			
			if (this.rand.nextInt(100) == 0 && this.world.getBlockState(blockpos1).isNormalCube()) {
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
	protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
	}
	
	@Override
	public boolean doesEntityNotTriggerPressurePlate() {
		return true;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (this.isEntityInvulnerable(source)) {
			return false;
		} else {
			if (!this.world.isRemote && this.getIsBatHanging()) {
				this.setIsBatHanging(false);
			}
			
			return super.attackEntityFrom(source, amount);
		}
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		this.dataManager.set(HANGING, Byte.valueOf(compound.getByte("BatFlags")));
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setByte("BatFlags", this.dataManager.get(HANGING).byteValue());
	}
	
	@Override
	public boolean getCanSpawnHere() {
		BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);
		
		if (blockpos.getY() >= this.world.getSeaLevel()) {
			return false;
		} else {
			int i = this.world.getLightFromNeighbors(blockpos);
			int j = 7;
			return i > this.rand.nextInt(j) ? false : super.getCanSpawnHere();
		}
	}
	
	@Override
	public float getEyeHeight() {
		return this.height / 2.0F;
	}
	
	public class AIAttack extends EntityAIBase {
		
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
		public void updateTask() {
			EntityLivingBase entitylivingbase = this.vampire.getAttackTarget();
			double d0 = 64.0D;
			
			if (entitylivingbase.getDistanceSq(this.vampire) < d0 * d0 && this.vampire.canEntityBeSeen(entitylivingbase)) {
				World world = this.vampire.world;
				++this.ticks;
				if (this.ticks == 390) {
					world.playSound(null, vampire.getPosition(), HalloweenLuckyBlockSounds.happy_halloween, SoundCategory.NEUTRAL, 1.0F, ((world.rand.nextFloat() * 0.8F) + 0.6F));
					EntityFallingBlock falling = new EntityFallingBlock(world, entitylivingbase.posX, entitylivingbase.posY + 5, entitylivingbase.posZ, HalloweenLuckyBlockBlocks.pumpkinbomb.getDefaultState());
					falling.fallTime = 100;
					falling.shouldDropItem = false;
					falling.setHurtEntities(false);
					world.spawnEntity(falling);
					vampire.damageEntity(DamageSource.causeMobDamage(entitylivingbase), 4.0F);
					this.ticks = -10;
				}
			} else if (this.ticks > 0) {
				--this.ticks;
			}
			
		}
		
	}
}