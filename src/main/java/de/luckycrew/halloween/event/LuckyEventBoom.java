package de.luckycrew.halloween.event;

import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class LuckyEventBoom extends LuckyEventCustom {
	
	public LuckyEventBoom() {
		super("Boom", 2);
	}
	
	@Override
	public void execute(EntityPlayerMP player, World world, BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		
		float offx = MathUtil.getRandomNumberInRange(0.3F, 1F);
		float offy = MathUtil.getRandomNumberInRange(0.5F, 1.5F);
		float offz = MathUtil.getRandomNumberInRange(0.3F, 1F);
		
		world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, true, x, y, z, offx, offy, offz, new int[0]);
		world.spawnEntityInWorld(new EntityTNTPrimed(world, x, y, z, player));
	}
}
