package info.u_team.halloween_luckyblock.event;

import info.u_team.halloween_luckyblock.core.LuckyEvent;
import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockSounds;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class LuckyEventThunder extends LuckyEvent {
	
	public LuckyEventThunder() {
		super("Thunder", 1);
	}
	
	@Override
	public void execute(ServerPlayerEntity player, ServerWorld world, BlockPos pos) {
		
		long time = world.getDayTime();
		PlayerEntity other = world.getClosestPlayer(player, -1);
		MinecraftServer mainthread = player.world.getServer();
		
		world.playSound(null, player.getPosition(), HalloweenLuckyBlockSounds.THUNDER, HalloweenLuckyBlockSounds.CATEGORY, 1.0F, 1.0F);
		if (other != null) {
			world.playSound(null, other.getPosition(), HalloweenLuckyBlockSounds.THUNDER, HalloweenLuckyBlockSounds.CATEGORY, 1.0F, 1.0F);
		}
		
		new Thread(() -> {
			mainthread.execute(() -> world.setDayTime(110000));
			for (int i = 0; i < 8; i++) {
				try {
					synchronized (this) {
						wait(500);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				mainthread.execute(() -> {
					LightningBoltEntity light1 = new LightningBoltEntity(world, player.getPosX(), player.getPosY(), player.getPosZ(), false);
					((ServerWorld) world).addLightningBolt(light1);
					if (other != null) {
						LightningBoltEntity light2 = new LightningBoltEntity(world, other.getPosX(), other.getPosY(), other.getPosZ(), false);
						((ServerWorld) world).addLightningBolt(light2);
					}
					world.playSound(null, player.getPosition(), HalloweenLuckyBlockSounds.WIND, HalloweenLuckyBlockSounds.CATEGORY, 1.0F, 1.0F);
					if (other != null) {
						world.playSound(null, other.getPosition(), HalloweenLuckyBlockSounds.WIND, HalloweenLuckyBlockSounds.CATEGORY, 1.0F, 1.0F);
					}
				});
			}
			mainthread.execute(() -> world.setDayTime(time));
		}).start();
	}
}
