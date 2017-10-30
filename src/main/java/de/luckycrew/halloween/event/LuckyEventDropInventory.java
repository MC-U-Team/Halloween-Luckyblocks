package de.luckycrew.halloween.event;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class LuckyEventDropInventory extends LuckyEventCustom {
	
	public LuckyEventDropInventory() {
		super("Drop Inventory", 1);
	}
	
	@Override
	public void execute(EntityPlayerMP player, World world, BlockPos pos) {
		player.inventory.dropAllItems();
	}
	
}
