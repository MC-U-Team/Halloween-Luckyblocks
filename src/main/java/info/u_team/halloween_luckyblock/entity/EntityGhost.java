package info.u_team.halloween_luckyblock.entity;

import java.util.Random;

import info.u_team.halloween_luckyblock.init.*;
import info.u_team.halloween_luckyblock.network.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class EntityGhost extends EntityFlying implements IMob {
	
	public EntityGhost(World world) {
		super(world);
		this.setSize(1.5F, 1.5F);
		this.experienceValue = 12;
		this.moveHelper = new EntityGhost.GhostMoveHelper(this);
		
	}
	
	@Override
	protected void initEntityAI() {
		this.tasks.addTask(5, new EntityGhost.AIRandomFly());
		this.tasks.addTask(7, new EntityGhost.AILookAround());
		this.tasks.addTask(7, new EntityGhost.AIAttack());
		this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		if (!world.isRemote && world.getDifficulty() == EnumDifficulty.PEACEFUL) {
			setDead();
		}
	}
	
	@Override
	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(100.0D);
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
	public Item getDropItem() {
		return Items.GUNPOWDER;
	}
	
	@Override
	public float getSoundVolume() {
		return 2.0F;
	}
	
	@Override
	public boolean getCanSpawnHere() {
		return super.getCanSpawnHere() && world.getDifficulty() != EnumDifficulty.PEACEFUL;
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
			
			if (entitylivingbase.getDistanceSq(this.ghost) < d0 * d0 && this.ghost.canEntityBeSeen(entitylivingbase)) {
				World world = ghost.world;
				++this.ticks;
				if (this.ticks == 90) {
					world.playSound(null, ghost.getPosition(), HalloweenLuckyBlockSounds.ringle, SoundCategory.HOSTILE, 1.0F, ((world.rand.nextFloat() * 0.8F) + 0.6F));
					entitylivingbase.attackEntityFrom(DamageSource.MAGIC, 4.0F);
					if (!world.isRemote && entitylivingbase instanceof EntityPlayerMP) {
						HalloweenLuckyBlockNetwork.network.sendTo(new MessageGhostFlash(), (EntityPlayerMP) entitylivingbase);
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
				
				if (entitylivingbase.getDistanceSq(this.ghost) < d0 * d0) {
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
		
		public void startExecuting() {
			Random random = this.ghost.getRNG();
			double d0 = this.ghost.posX + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
			double d1 = this.ghost.posY + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
			double d2 = this.ghost.posZ + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
			this.ghost.getMoveHelper().setMoveTo(d0, d1, d2, 1.0D);
		}
	}
	
	static class GhostMoveHelper extends EntityMoveHelper {
		
		private final double speed = 0.25D;
		
		private final EntityGhost parentEntity;
		private int courseChangeCooldown;
		
		public GhostMoveHelper(EntityGhost ghost) {
			super(ghost);
			this.parentEntity = ghost;
		}
		
		public void onUpdateMoveHelper() {
			if (this.action == EntityMoveHelper.Action.MOVE_TO) {
				double d0 = this.posX - this.parentEntity.posX;
				double d1 = this.posY - this.parentEntity.posY;
				double d2 = this.posZ - this.parentEntity.posZ;
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				
				if (this.courseChangeCooldown-- <= 0) {
					this.courseChangeCooldown += this.parentEntity.getRNG().nextInt(5) + 2;
					d3 = (double) MathHelper.sqrt(d3);
					
					if (this.isNotColliding(this.posX, this.posY, this.posZ, d3)) {
						this.parentEntity.motionX += d0 / d3 * speed;
						this.parentEntity.motionY += d1 / d3 * speed;
						this.parentEntity.motionZ += d2 / d3 * speed;
					} else {
						this.action = EntityMoveHelper.Action.WAIT;
					}
				}
			}
		}
		
		/**
		 * Checks if entity bounding box is not colliding with terrain
		 */
		private boolean isNotColliding(double x, double y, double z, double p_179926_7_) {
			double d0 = (x - this.parentEntity.posX) / p_179926_7_;
			double d1 = (y - this.parentEntity.posY) / p_179926_7_;
			double d2 = (z - this.parentEntity.posZ) / p_179926_7_;
			AxisAlignedBB axisalignedbb = this.parentEntity.getEntityBoundingBox();
			
			for (int i = 1; (double) i < p_179926_7_; ++i) {
				axisalignedbb = axisalignedbb.offset(d0, d1, d2);
				
				if (!this.parentEntity.world.getCollisionBoxes(this.parentEntity, axisalignedbb).isEmpty()) {
					return false;
				}
			}
			
			return true;
		}
	}
}