package info.u_team.halloween_luckyblock.event;

import java.util.*;

import info.u_team.halloween_luckyblock.core.LuckyEvent;
import info.u_team.halloween_luckyblock.init.*;
import info.u_team.halloween_luckyblock.util.*;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class LuckyEventChest extends LuckyEvent {
	
	private ArrayList<ItemStackEntry> stacks;
	
	public LuckyEventChest() {
		super("Chest", 3);
		stacks = new ArrayList<ItemStackEntry>();
		additems();
	}
	
	private void additems() {
		for (Item item : HalloweenLuckyBlockItems.SCARECROW_SET.getArray()) {
			add(new ItemStackEntry(new ItemStack(item)), 5);
		}
		for (Item item : HalloweenLuckyBlockItems.SLENDER_SET.getArray()) {
			add(new ItemStackEntry(new ItemStack(item)), 5);
		}
		for (Item item : HalloweenLuckyBlockItems.WITCH_SET.getArray()) {
			add(new ItemStackEntry(new ItemStack(item)), 5);
		}
		for (Item item : HalloweenLuckyBlockItems.ZOMBIE_SET.getArray()) {
			add(new ItemStackEntry(new ItemStack(item)), 5);
		}
		for (Item item : HalloweenLuckyBlockItems.CLOWN_SET.getArray()) {
			add(new ItemStackEntry(new ItemStack(item)), 5);
		}
		
		Iterator<Item> it = ForgeRegistries.ITEMS.iterator();
		while (it.hasNext()) {
			add(new ItemStackEntry(new ItemStack(it.next()), 1, 2), 1);
		}
	}
	
	private void add(ItemStackEntry entry, int count) {
		for (int i = 0; i < count; i++) {
			stacks.add(entry);
		}
	}
	
	@Override
	public void execute(ServerPlayerEntity player, World world, BlockPos pos) {
		world.setBlockState(pos.add(0, 1, 0), Blocks.CHEST.getDefaultState());
		TileEntity tile = world.getTileEntity(pos.add(0, 1, 0));
		if (tile instanceof ChestTileEntity) {
			ChestTileEntity chest = (ChestTileEntity) tile;
			for (int i = 0; i < chest.getSizeInventory(); i++) {
				if (MathUtil.getRandomNumberInRange(0, MathUtil.getRandomNumberInRange(0, 3)) == 0) {
					chest.setInventorySlotContents(i, stacks.get(MathUtil.getRandomNumberInRange(0, stacks.size() - 1)).getItemStack());
				}
			}
			chest.tick();
		}
		world.playSound(null, pos, HalloweenLuckyBlockSounds.tension, HalloweenLuckyBlockSounds.category, 0.2F, 1.0F);
	}
}
