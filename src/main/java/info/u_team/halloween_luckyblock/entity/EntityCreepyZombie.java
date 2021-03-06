package info.u_team.halloween_luckyblock.entity;

import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap.MutableAttribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityCreepyZombie extends MonsterEntity {
	
	public EntityCreepyZombie(World worldIn) {
		this(HalloweenLuckyBlockEntityTypes.CREEPY_ZOMBIE.get(), worldIn);
	}
	
	public EntityCreepyZombie(EntityType<? extends EntityCreepyZombie> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(2, new CustomZombieAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setCallsForHelp(ZombifiedPiglinEntity.class));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, TurtleEntity.class, 10, true, false, TurtleEntity.TARGET_DRY_BABY));
	}
	
	public static MutableAttribute registerAttributes() {
		return MonsterEntity.func_234295_eP_() //
				.createMutableAttribute(Attributes.MAX_HEALTH, 30) //
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.42) //
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 6) //
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 35) //
				.createMutableAttribute(Attributes.ARMOR, 2);
	}
	
	@Override
	public void livingTick() {
		if (this.isAlive()) {
			boolean flag = false;
			if (flag) {
				final ItemStack itemstack = this.getItemStackFromSlot(EquipmentSlotType.HEAD);
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
		final boolean flag = super.attackEntityAsMob(entityIn);
		
		if (flag) {
			final float f = this.world.getDifficultyForLocation(getPosition()).getAdditionalDifficulty();
			
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
		@Override
		public void startExecuting() {
			super.startExecuting();
			this.raiseArmTicks = 0;
		}
		
		/**
		 * Reset the task's internal state. Called when this task is interrupted by another one
		 */
		@Override
		public void resetTask() {
			super.resetTask();
			this.zombie.setAggroed(false);
		}
		
		/**
		 * Keep ticking a continuous task that has already been started
		 */
		@Override
		public void tick() {
			super.tick();
			++this.raiseArmTicks;
			if (this.raiseArmTicks >= 5 && this.func_234041_j_() < this.func_234042_k_() / 2) {
				this.zombie.setAggroed(true);
			} else {
				this.zombie.setAggroed(false);
			}
			
		}
	}
	
}
