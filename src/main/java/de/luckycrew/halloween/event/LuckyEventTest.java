package de.luckycrew.halloween.event;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class LuckyEventTest extends LuckyEventCustom {
	
	public LuckyEventTest() {
		super("test", 1);
	}
	
	@Override
	public void execute(EntityPlayerMP player, World world, BlockPos pos) {
		System.out.println(player + " - " + world + " - " + pos);
	}
	
}
