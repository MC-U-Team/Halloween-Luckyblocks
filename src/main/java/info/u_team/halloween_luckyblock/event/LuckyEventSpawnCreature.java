package info.u_team.halloween_luckyblock.event;

import info.u_team.halloween_luckyblock.entity.*;
import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockItems;
import info.u_team.halloween_luckyblock.sound.HalloweenSounds;
import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
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
			
			zombie.setCurrentItemOrArmor(0, new ItemStack(HalloweenLuckyBlockItems.killerknive));
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
