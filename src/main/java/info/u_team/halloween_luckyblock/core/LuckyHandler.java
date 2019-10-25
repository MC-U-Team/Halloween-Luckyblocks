package info.u_team.halloween_luckyblock.core;

import java.util.*;

import info.u_team.halloween_luckyblock.event.*;
import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockSounds;
import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.entity.player.*;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LuckyHandler {
	
	private ArrayList<LuckyEvent> events = new ArrayList<LuckyEvent>();
	
	public void add(LuckyEvent event) {
		addList(event);
	}
	
	private void addList(LuckyEvent event) {
		for (int i = 0; i < event.getCount(); i++) {
			events.add(event);
		}
	}
	
	public void post(PlayerEntity player, BlockPos pos) {
		if (!(player instanceof ServerPlayerEntity)) {
			return;
		}
		final ServerPlayerEntity playermp = (ServerPlayerEntity) player;
		final World world = playermp.getEntityWorld();
		if (world.isRemote) {
			return;
		}
		if (events.size() == 0) {
			return;
		}
		int r = getRandomNumberInRange(player.getRNG(), 0, events.size() - 1);
		
		LuckyEvent event = events.get(r);
		event.execute(playermp, world, pos);
		
		if (!(event instanceof LuckyEventSound) && !(event instanceof LuckyEventDeath) && !(event instanceof LuckyEventThunder) && !(event instanceof LuckyEventChest)) {
			playermp.connection.sendPacket(new SPacketSoundEffect(HalloweenLuckyBlockSounds.common_sounds.get(MathUtil.getRandomNumberInRange(0, HalloweenLuckyBlockSounds.common_sounds.size() - 1)), HalloweenLuckyBlockSounds.category, player.posX, player.posY, player.posZ, 0.15F, 1.0F));
		}
	}
	
	private int getRandomNumberInRange(Random random, int min, int max) {
		return random.nextInt(max - min + 1) + min;
	}
	
}
