package info.u_team.halloween_luckyblock.event;

import info.u_team.halloween_luckyblock.core.LuckyEvent;
import info.u_team.halloween_luckyblock.entity.*;
import info.u_team.halloween_luckyblock.init.*;
import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class LuckyEventSpawnCreature extends LuckyEvent {
	
	public LuckyEventSpawnCreature() {
		super("Spawn Creature", 1);
	}
	
	@Override
	public void execute(ServerPlayerEntity player, ServerWorld world, BlockPos pos) {
		final int i = MathUtil.randomNumberInRange(0, 2);
		
		Entity entity = null;
		
		if (i == 0) {
			final EntityCreepyZombie zombie = new EntityCreepyZombie(world);
			zombie.setPosition(pos.getX(), pos.getY(), pos.getZ());
			
			zombie.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(HalloweenLuckyBlockItems.KILLERKNIFE.get()));
			zombie.setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack(Items.DIAMOND_CHESTPLATE));
			zombie.setDropChance(EquipmentSlotType.MAINHAND, 0.1F);
			zombie.setDropChance(EquipmentSlotType.CHEST, 0.25F);
			
			entity = zombie;
		} else if (i == 1) {
			final EntityGhost ghost = new EntityGhost(world);
			ghost.setPosition(pos.getX(), pos.getY() + 2, pos.getZ());
			
			entity = ghost;
		} else if (i == 2) {
			final EntityVampire vampire = new EntityVampire(world);
			vampire.setPosition(pos.getX(), pos.getY() + 2, pos.getZ());
			
			entity = vampire;
		}
		
		if (entity != null) {
			world.addEntity(entity);
		}
		world.playSound(null, pos, HalloweenLuckyBlockSounds.ORGAN.get(), HalloweenLuckyBlockSounds.CATEGORY, 1.0F, 1.0F);
	}
}
