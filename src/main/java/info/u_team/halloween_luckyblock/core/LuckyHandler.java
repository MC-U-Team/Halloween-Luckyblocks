package info.u_team.halloween_luckyblock.core;

import java.util.ArrayList;

import info.u_team.halloween_luckyblock.event.*;
import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockSounds;
import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.entity.player.*;
import net.minecraft.network.play.server.SPlaySoundEffectPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

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
		
		final ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
		final World world = serverPlayer.getEntityWorld();
		
		if (world.isRemote || !(world instanceof ServerWorld)) {
			return;
		}
		
		final ServerWorld serverWorld = (ServerWorld) world;
		
		if (events.size() == 0) {
			return;
		}
		
		final int randomEntry = MathUtil.randomNumberInRange(player.getRNG(), 0, events.size() - 1);
		
		final LuckyEvent event = events.get(randomEntry);
		event.execute(serverPlayer, serverWorld, pos);
		
		if (!(event instanceof LuckyEventSound) && !(event instanceof LuckyEventDeath) && !(event instanceof LuckyEventThunder) && !(event instanceof LuckyEventChest)) {
			serverPlayer.connection.sendPacket(new SPlaySoundEffectPacket(HalloweenLuckyBlockSounds.COMMON_SOUNDS.get(MathUtil.randomNumberInRange(0, HalloweenLuckyBlockSounds.COMMON_SOUNDS.size() - 1)).get(), HalloweenLuckyBlockSounds.CATEGORY, player.getPosX(), player.getPosY(), player.getPosZ(), 0.15F, 1.0F));
		}
	}
	
}
