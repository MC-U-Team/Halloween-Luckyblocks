package info.u_team.halloween_luckyblock.event;

import info.u_team.halloween_luckyblock.core.LuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LuckyEventDropInventory extends LuckyEvent {
	
	public LuckyEventDropInventory() {
		super("Drop Inventory", 1);
	}
	
	@Override
	public void execute(EntityPlayerMP player, World world, BlockPos pos) {
		player.inventory.dropAllItems();
	}
	
}
