package de.luckycrew.halloween.event;

import de.luckycrew.halloween.block.HalloweenBlocks;
import de.luckycrew.halloween.sound.HalloweenSounds;
import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.*;
import net.minecraft.world.*;

public class LuckyEventSound extends LuckyEventCustom {
	
	public LuckyEventSound() {
		super("Sound", 3);
	}
	
	@Override
	public void execute(EntityPlayerMP player, World world, BlockPos pos) {
		world.playSoundAtEntity(player, HalloweenSounds.sounds.get(MathUtil.getRandomNumberInRange(0, HalloweenSounds.sounds.size() - 1)), 1.0F, 1.0F);
		IThreadListener mainthread = (WorldServer) player.worldObj;
		new Thread(() -> {
			synchronized (this) {
				try {
					wait(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			mainthread.addScheduledTask(() -> {
				EntityFallingBlock falling = new EntityFallingBlock(world, pos.getX(), pos.getY() + 15, pos.getZ(), HalloweenBlocks.luckyblock.getDefaultState());
				falling.fallTime = 100;
				falling.shouldDropItem = false;
				falling.setHurtEntities(true);
				world.spawnEntityInWorld(falling);
			});
		}).start();
	}
	
}
