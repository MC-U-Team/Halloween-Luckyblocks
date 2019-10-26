package info.u_team.halloween_luckyblock.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityCreepyZombie extends MonsterEntity {
	
	public EntityCreepyZombie(EntityType<? extends EntityCreepyZombie> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(2, new CustomZombieAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp(ZombiePigmanEntity.class));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, TurtleEntity.class, 10, true, false, TurtleEntity.TARGET_DRY_BABY));
	}
	
	@Override
	public void registerAttributes() {
		super.registerAttributes();
		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30D);
		getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.42D);
		getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
	}
	
	@Override
	public void livingTick() {
		if (this.isAlive()) {
			boolean flag = false;
			if (flag) {
				ItemStack itemstack = this.getItemStackFromSlot(EquipmentSlotType.HEAD);
				if (!itemstack.isEmpty()) {
					if (itemstack.isDamageable()) {
						itemstack.setDamage(itemstack.getDamage() + this.rand.nextInt(2));
						if (itemstack.getDamage() >= itemstack.getMaxDamage()) {
							this.sendBreakAnimation(EquipmentSlotType.HEAD);
							this.setItemStackToSlot(EquipmentSlotType.HEAD, ItemStack.EMPTY);
						}
					}
					
					flag = false;
				}
				
				if (flag) {
					this.setFire(8);
				}
			}
		}
		
		super.livingTick();
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		boolean flag = super.attackEntityAsMob(entityIn);
		
		if (flag) {
			float f = this.world.getDifficultyForLocation(new BlockPos(this)).getAdditionalDifficulty();
			
			if (this.getHeldItemMainhand().isEmpty() && this.isBurning() && this.rand.nextFloat() < f * 0.3F) {
				entityIn.setFire(2 * (int) f);
			}
		}
		return flag;
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_ZOMBIE_AMBIENT;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_ZOMBIE_HURT;
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_ZOMBIE_DEATH;
	}
	
	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(SoundEvents.ENTITY_ZOMBIE_STEP, 0.15F, 1.0F);
	}
	
	public class CustomZombieAttackGoal extends MeleeAttackGoal {
		
		private final EntityCreepyZombie zombie;
		private int raiseArmTicks;
		
		public CustomZombieAttackGoal(EntityCreepyZombie zombieIn, double speedIn, boolean longMemoryIn) {
			super(zombieIn, speedIn, longMemoryIn);
			this.zombie = zombieIn;
		}
		
		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		public void startExecuting() {
			super.startExecuting();
			this.raiseArmTicks = 0;
		}
		
		/**
		 * Reset the task's internal state. Called when this task is interrupted by another one
		 */
		public void resetTask() {
			super.resetTask();
			this.zombie.setAggroed(false);
		}
		
		/**
		 * Keep ticking a continuous task that has already been started
		 */
		public void tick() {
			super.tick();
			++this.raiseArmTicks;
			if (this.raiseArmTicks >= 5 && this.attackTick < 10) {
				this.zombie.setAggroed(true);
			} else {
				this.zombie.setAggroed(false);
			}
			
		}
	}
	
}
