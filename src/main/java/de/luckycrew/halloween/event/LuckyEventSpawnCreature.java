package de.luckycrew.halloween.event;

import de.luckycrew.halloween.entity.*;
import de.luckycrew.halloween.item.HalloweenItems;
import de.luckycrew.halloween.sound.HalloweenSounds;
import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class LuckyEventSpawnCreature extends LuckyEventCustom {
	
	public LuckyEventSpawnCreature() {
		super("Spawn Creature", 1);
	}
	
	@Override
	public void execute(EntityPlayerMP player, World world, BlockPos pos) {
		int i = MathUtil.getRandomNumberInRange(0, 2);
		
		Entity entity = null;
		
		if (i == 0) {
			EntityCreepyZombie zombie = new EntityCreepyZombie(world);
			zombie.setPosition(pos.getX(), pos.getY(), pos.getZ());
			
			zombie.setCurrentItemOrArmor(0, new ItemStack(HalloweenItems.killerknive));
			zombie.setCurrentItemOrArmor(2, new ItemStack(Items.diamond_chestplate));
			zombie.setEquipmentDropChance(2, 0.25F);
			zombie.setEquipmentDropChance(0, 0.1F);
			
			entity = zombie;
		} else if (i == 1) {
			EntityGhost ghost = new EntityGhost(world);
			ghost.setPosition(pos.getX(), pos.getY() + 2, pos.getZ());
			
			entity = ghost;
		} else if (i == 2) {
			EntityVampire vampire = new EntityVampire(world);
			vampire.setPosition(pos.getX(), pos.getY() + 2, pos.getZ());
			
			entity = vampire;
		}
		
		if (entity != null) {
			world.spawnEntityInWorld(entity);
		}
		
		world.playSoundAtEntity(player, HalloweenSounds.organ, 1.0F, 1.0F);
	}
}
