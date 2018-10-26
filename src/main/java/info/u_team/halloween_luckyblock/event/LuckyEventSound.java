package info.u_team.halloween_luckyblock.event;

import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockBlocks;
import info.u_team.halloween_luckyblock.sound.HalloweenSounds;
import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;

public class LuckyEventSound extends LuckyEventCustom {
	
	public LuckyEventSound() {
		super("Sound", 3);
	}
	
	@Override
	public void execute(EntityPlayerMP player, World world, BlockPos pos) {
		world.playSoundAtEntity(player, HalloweenLuckyBlockSounds.sounds.get(MathUtil.getRandomNumberInRange(0, HalloweenLuckyBlockSounds.sounds.size() - 1)), 1.0F, 1.0F);
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
				EntityFallingBlock falling = new EntityFallingBlock(world, pos.getX(), pos.getY() + 15, pos.getZ(), HalloweenLuckyBlockBlocks.luckyblock.getDefaultState());
				falling.fallTime = 100;
				falling.shouldDropItem = false;
				falling.setHurtEntities(true);
				world.spawnEntityInWorld(falling);
			});
		}).start();
	}
	
}
