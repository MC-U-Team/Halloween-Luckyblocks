package de.luckycrew.halloween.entity;

import java.util.Random;

import de.luckycrew.halloween.network.HalloweenNetwork;
import de.luckycrew.halloween.network.message.MessageGhostFlash;
import de.luckycrew.halloween.sound.HalloweenSounds;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.*;
import net.minecraft.world.*;

public class EntityGhost extends EntityFlying implements IMob {
	
	public EntityGhost(World world) {
		super(world);
		this.setSize(1.5F, 1.5F);
		this.experienceValue = 12;
		this.moveHelper = new EntityGhost.GhostMoveHelper();
		this.tasks.addTask(5, new EntityGhost.AIRandomFly());
		this.tasks.addTask(7, new EntityGhost.AILookAround());
		this.tasks.addTask(7, new EntityGhost.AIAttack());
		this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		if (!this.worldObj.isRemote && this.worldObj.getDifficulty() == EnumDifficulty.PEACEFUL) {
			this.setDead();
		}
	}
	
	@Override
	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(100.0D);
	}
	
	@Override
	public String getLivingSound() {
		return HalloweenSounds.snivelling;
	}
	
	@Override
	public String getHurtSound() {
		return HalloweenSounds.strange_laughing;
	}
	
	@Override
	public String getDeathSound() {
		return HalloweenSounds.thunder;
	}
	
	@Override
	public Item getDropItem() {
		return Items.gunpowder;
	}
	
	@Override
	public float getSoundVolume() {
		return 2.0F;
	}
	
	@Override
	public boolean getCanSpawnHere() {
		return super.getCanSpawnHere() && this.worldObj.getDifficulty() != EnumDifficulty.PEACEFUL;
	}
	
	@Override
	public int getMaxSpawnedInChunk() {
		return 2;
	}
	
	@Override
	public float getEyeHeight() {
		return 1.6F;
	}
	
	class AIAttack extends EntityAIBase {
		
		private EntityGhost ghost = EntityGhost.this;
		public int ticks;
		
		public boolean shouldExecute() {
			return this.ghost.getAttackTarget() != null;
		}
		
		public void startExecuting() {
			this.ticks = 0;
		}
		
		public void resetTask() {
		}
		
		public void updateTask() {
			EntityLivingBase entitylivingbase = this.ghost.getAttackTarget();
			double d0 = 64.0D;
			
			if (entitylivingbase.getDistanceSqToEntity(this.ghost) < d0 * d0 && this.ghost.canEntityBeSeen(entitylivingbase)) {
				World world = this.ghost.worldObj;
				++this.ticks;
				if (this.ticks == 90) {
					world.playSoundAtEntity(entitylivingbase, HalloweenSounds.ringle, 1.0F, ((world.rand.nextFloat() * 0.8F) + 0.6F));
					entitylivingbase.attackEntityFrom(DamageSource.magic, 4.0F);
					if (!world.isRemote && entitylivingbase instanceof EntityPlayerMP) {
						HalloweenNetwork.network.sendTo(new MessageGhostFlash(), (EntityPlayerMP) entitylivingbase);
					}
					this.ticks = -10;
				}
			} else if (this.ticks > 0) {
				--this.ticks;
			}
			
		}
	}
	
	class AILookAround extends EntityAIBase {
		
		private EntityGhost ghost = EntityGhost.this;
		
		public AILookAround() {
			this.setMutexBits(2);
		}
		
		public boolean shouldExecute() {
			return true;
		}
		
		public void updateTask() {
			if (this.ghost.getAttackTarget() == null) {
				this.ghost.renderYawOffset = this.ghost.rotationYaw = -((float) Math.atan2(this.ghost.motionX, this.ghost.motionZ)) * 180.0F / (float) Math.PI;
			} else {
				EntityLivingBase entitylivingbase = this.ghost.getAttackTarget();
				double d0 = 64.0D;
				
				if (entitylivingbase.getDistanceSqToEntity(this.ghost) < d0 * d0) {
					double d1 = entitylivingbase.posX - this.ghost.posX;
					double d2 = entitylivingbase.posZ - this.ghost.posZ;
					this.ghost.renderYawOffset = this.ghost.rotationYaw = -((float) Math.atan2(d1, d2)) * 180.0F / (float) Math.PI;
				}
			}
		}
	}
	
	class AIRandomFly extends EntityAIBase {
		
		private EntityGhost ghost = EntityGhost.this;
		
		public AIRandomFly() {
			this.setMutexBits(1);
		}
		
		public boolean shouldExecute() {
			EntityMoveHelper entitymovehelper = this.ghost.getMoveHelper();
			
			if (!entitymovehelper.isUpdating()) {
				return true;
			} else {
				double d0 = entitymovehelper.func_179917_d() - this.ghost.posX;
				double d1 = entitymovehelper.func_179919_e() - this.ghost.posY;
				double d2 = entitymovehelper.func_179918_f() - this.ghost.posZ;
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				return d3 < 1.0D || d3 > 3600.0D;
			}
		}
		
		public boolean continueExecuting() {
			return false;
		}
		
		public void startExecuting() {
			Random random = this.ghost.getRNG();
			double d0 = this.ghost.posX + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
			double d1 = this.ghost.posY + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
			double d2 = this.ghost.posZ + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
			this.ghost.getMoveHelper().setMoveTo(d0, d1, d2, 1.0D);
		}
	}
	
	class GhostMoveHelper extends EntityMoveHelper {
		
		private final double speed = 0.25D;
		
		private EntityGhost ghost = EntityGhost.this;
		private int i;
		
		public GhostMoveHelper() {
			super(EntityGhost.this);
		}
		
		public void onUpdateMoveHelper() {
			if (this.update) {
				double d0 = this.posX - this.ghost.posX;
				double d1 = this.posY - this.ghost.posY;
				double d2 = this.posZ - this.ghost.posZ;
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				
				if (this.i-- <= 0) {
					this.i += this.ghost.getRNG().nextInt(5) + 2;
					d3 = (double) MathHelper.sqrt_double(d3);
					
					if (this.calculate(this.posX, this.posY, this.posZ, d3)) {
						this.ghost.motionX += d0 / d3 * speed;
						this.ghost.motionY += d1 / d3 * speed;
						this.ghost.motionZ += d2 / d3 * speed;
					} else {
						this.update = false;
					}
				}
			}
		}
		
		private boolean calculate(double x, double y, double z, double d) {
			double d4 = (x - this.ghost.posX) / d;
			double d5 = (y - this.ghost.posY) / d;
			double d6 = (z - this.ghost.posZ) / d;
			AxisAlignedBB axisalignedbb = this.ghost.getEntityBoundingBox();
			
			for (int i = 1; (double) i < d; ++i) {
				axisalignedbb = axisalignedbb.offset(d4, d5, d6);
				
				if (!this.ghost.worldObj.getCollidingBoundingBoxes(this.ghost, axisalignedbb).isEmpty()) {
					return false;
				}
			}
			
			return true;
		}
	}
}