package info.u_team.halloween_luckyblock.entity;

import java.util.Calendar;

import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockBlocks;
import info.u_team.halloween_luckyblock.sound.HalloweenSounds;
import net.minecraft.block.Block;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

public class EntityVampire extends EntityAmbientCreature {
	
	private BlockPos spawnPosition;
	
	public EntityVampire(World worldIn) {
		super(worldIn);
		this.setSize(2.0F, 2.0F);
		this.setIsBatHanging(true);
		this.tasks.addTask(7, new EntityVampire.AIAttack());
		this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
	}
	
	public void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(16, new Byte((byte) 0));
	}
	
	protected float getSoundVolume() {
		return 0.5F;
	}
	
	protected float getSoundPitch() {
		return super.getSoundPitch() * 0.95F;
	}
	
	protected String getLivingSound() {
		return this.getIsBatHanging() && this.rand.nextInt(4) != 0 ? null : "mob.bat.idle";
	}
	
	protected String getHurtSound() {
		return HalloweenSounds.scubi_dabi;
	}
	
	protected String getDeathSound() {
		return HalloweenSounds.wind;
	}
	
	public boolean canBePushed() {
		return false;
	}
	
	protected void collideWithEntity(Entity p_82167_1_) {
	}
	
	protected void collideWithNearbyEntities() {
	}
	
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
	}
	
	public boolean getIsBatHanging() {
		return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
	}
	
	public void setIsBatHanging(boolean p_82236_1_) {
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);
		
		if (p_82236_1_) {
			this.dataWatcher.updateObject(16, Byte.valueOf((byte) (b0 | 1)));
		} else {
			this.dataWatcher.updateObject(16, Byte.valueOf((byte) (b0 & -2)));
		}
	}
	
	public void onUpdate() {
		super.onUpdate();
		
		if (this.getIsBatHanging()) {
			this.motionX = this.motionY = this.motionZ = 0.0D;
			this.posY = (double) MathHelper.floor_double(this.posY) + 1.0D - (double) this.height;
		} else {
			this.motionY *= 0.6000000238418579D;
		}
	}
	
	protected void updateAITasks() {
		super.updateAITasks();
		BlockPos blockpos = new BlockPos(this);
		BlockPos blockpos1 = blockpos.up();
		
		if (this.getIsBatHanging()) {
			if (!this.worldObj.getBlockState(blockpos1).getBlock().isNormalCube()) {
				this.setIsBatHanging(false);
				this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1015, blockpos, 0);
			} else {
				if (this.rand.nextInt(200) == 0) {
					this.rotationYawHead = (float) this.rand.nextInt(360);
				}
				
				if (this.worldObj.getClosestPlayerToEntity(this, 4.0D) != null) {
					this.setIsBatHanging(false);
					this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1015, blockpos, 0);
				}
			}
		} else {
			if (this.spawnPosition != null && (!this.worldObj.isAirBlock(this.spawnPosition) || this.spawnPosition.getY() < 1)) {
				this.spawnPosition = null;
			}
			
			if (this.spawnPosition == null || this.rand.nextInt(30) == 0 || this.spawnPosition.distanceSq((double) ((int) this.posX), (double) ((int) this.posY), (double) ((int) this.posZ)) < 4.0D) {
				this.spawnPosition = new BlockPos((int) this.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int) this.posY + this.rand.nextInt(6) - 2, (int) this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
			}
			
			double d0 = (double) this.spawnPosition.getX() + 0.5D - this.posX;
			double d1 = (double) this.spawnPosition.getY() + 0.1D - this.posY;
			double d2 = (double) this.spawnPosition.getZ() + 0.5D - this.posZ;
			this.motionX += (Math.signum(d0) * 0.5D - this.motionX) * 0.10000000149011612D;
			this.motionY += (Math.signum(d1) * 0.699999988079071D - this.motionY) * 0.10000000149011612D;
			this.motionZ += (Math.signum(d2) * 0.5D - this.motionZ) * 0.10000000149011612D;
			float f = (float) (Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
			float f1 = MathHelper.wrapAngleTo180_float(f - this.rotationYaw);
			this.moveForward = 0.5F;
			this.rotationYaw += f1;
			
			if (this.rand.nextInt(100) == 0 && this.worldObj.getBlockState(blockpos1).getBlock().isNormalCube()) {
				this.setIsBatHanging(true);
			}
		}
	}
	
	protected boolean canTriggerWalking() {
		return false;
	}
	
	public void fall(float distance, float damageMultiplier) {
	}
	
	protected void func_180433_a(double p_180433_1_, boolean p_180433_3_, Block p_180433_4_, BlockPos p_180433_5_) {
	}
	
	public boolean doesEntityNotTriggerPressurePlate() {
		return true;
	}
	
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (this.isEntityInvulnerable(source)) {
			return false;
		} else {
			if (!this.worldObj.isRemote && this.getIsBatHanging()) {
				this.setIsBatHanging(false);
			}
			
			return super.attackEntityFrom(source, amount);
		}
	}
	
	public void readEntityFromNBT(NBTTagCompound tagCompund) {
		super.readEntityFromNBT(tagCompund);
		this.dataWatcher.updateObject(16, Byte.valueOf(tagCompund.getByte("BatFlags")));
	}
	
	public void writeEntityToNBT(NBTTagCompound tagCompound) {
		super.writeEntityToNBT(tagCompound);
		tagCompound.setByte("BatFlags", this.dataWatcher.getWatchableObjectByte(16));
	}
	
	public boolean getCanSpawnHere() {
		BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);
		
		if (blockpos.getY() >= 63) {
			return false;
		} else {
			int i = this.worldObj.getLightFromNeighbors(blockpos);
			byte b0 = 4;
			
			if (this.func_175569_a(this.worldObj.getCurrentDate())) {
				b0 = 7;
			} else if (this.rand.nextBoolean()) {
				return false;
			}
			
			return i > this.rand.nextInt(b0) ? false : super.getCanSpawnHere();
		}
	}
	
	private boolean func_175569_a(Calendar p_175569_1_) {
		return p_175569_1_.get(2) + 1 == 10 && p_175569_1_.get(5) >= 20 || p_175569_1_.get(2) + 1 == 11 && p_175569_1_.get(5) <= 3;
	}
	
	public float getEyeHeight() {
		return this.height / 2.0F;
	}
	
	public class AIAttack extends EntityAIBase {
		
		private EntityVampire vampire = EntityVampire.this;
		public int ticks;
		
		public boolean shouldExecute() {
			return this.vampire.getAttackTarget() != null;
		}
		
		public void startExecuting() {
			this.ticks = 0;
		}
		
		public void resetTask() {
		}
		
		public void updateTask() {
			EntityLivingBase entitylivingbase = this.vampire.getAttackTarget();
			double d0 = 64.0D;
			
			if (entitylivingbase.getDistanceSqToEntity(this.vampire) < d0 * d0 && this.vampire.canEntityBeSeen(entitylivingbase)) {
				World world = this.vampire.worldObj;
				++this.ticks;
				if (this.ticks == 390) {
					world.playSoundAtEntity(entitylivingbase, HalloweenSounds.happy_halloween, 1.0F, ((world.rand.nextFloat() * 0.8F) + 0.6F));
					EntityFallingBlock falling = new EntityFallingBlock(worldObj, entitylivingbase.posX, entitylivingbase.posY + 5, entitylivingbase.posZ, HalloweenLuckyBlockBlocks.pumpkinbomb.getDefaultState());
					falling.fallTime = 100;
					falling.shouldDropItem = false;
					falling.setHurtEntities(false);
					world.spawnEntityInWorld(falling);
					vampire.damageEntity(DamageSource.causeMobDamage(entitylivingbase), 4.0F);
					this.ticks = -10;
				}
			} else if (this.ticks > 0) {
				--this.ticks;
			}
			
		}
		
	}
}