package info.u_team.halloween_luckyblock.core;

import java.util.ArrayList;

import info.u_team.halloween_luckyblock.event.*;
import info.u_team.halloween_luckyblock.sound.HalloweenSounds;
import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.entity.player.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;

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
	
	public void post(EntityPlayer player, BlockPos pos) {
		if (!(player instanceof EntityPlayerMP)) {
			return;
		}
		EntityPlayerMP playermp = (EntityPlayerMP) player;
		World world = player.getEntityWorld();
		if (world.isRemote) {
			return;
		}
		if (events.size() == 0) {
			return;
		}
		int r = MathUtil.getRandomNumberInRange(0, events.size() - 1);
		
		LuckyEvent event = events.get(r);
		event.execute((EntityPlayerMP) player, world, pos);
		
		if (!(event instanceof LuckyEventSound) && !(event instanceof LuckyEventDeath) && !(event instanceof LuckyEventThunder) && !(event instanceof LuckyEventChest)) {
//			((EntityPlayerMP) player).connection.sendPacket(new SPacketSoundEffect(HalloweenSounds.sounds.get(MathUtil.getRandomNumberInRange(0, HalloweenSounds.sounds.size() - 1)), SoundCategory.PLAYERS, player.posX, player.posY, player.posZ, 0.15F, 1.0F));
		}
	}
	
}
