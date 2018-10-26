package info.u_team.halloween_luckyblock.event;

import java.util.*;

import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockItems;
import info.u_team.halloween_luckyblock.sound.HalloweenSounds;
import info.u_team.halloween_luckyblock.util.ItemStackEntry;
import info.u_team.u_team_core.item.armor.UItemArmor;
import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LuckyEventChest extends LuckyEventCustom {
	
	private ArrayList<ItemStackEntry> stacks;
	
	public LuckyEventChest() {
		super("Chest", 3);
		stacks = new ArrayList<ItemStackEntry>();
		additems();
	}
	
	private void additems() {
		for (UItemArmor item : HalloweenLuckyBlockItems.scarecrow) {
			add(new ItemStackEntry(new ItemStack(item)), 5);
		}
		for (UItemArmor item : HalloweenLuckyBlockItems.slender) {
			add(new ItemStackEntry(new ItemStack(item)), 5);
		}
		for (UItemArmor item : HalloweenLuckyBlockItems.witch) {
			add(new ItemStackEntry(new ItemStack(item)), 5);
		}
		for (UItemArmor item : HalloweenLuckyBlockItems.zombie) {
			add(new ItemStackEntry(new ItemStack(item)), 5);
		}
		for (UItemArmor item : HalloweenLuckyBlockItems.clown) {
			add(new ItemStackEntry(new ItemStack(item)), 5);
		}
		
		@SuppressWarnings("unchecked")
		Iterator<Item> it = Item.itemRegistry.iterator();
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
	public void execute(EntityPlayerMP player, World world, BlockPos pos) {
		world.setBlockState(pos.add(0, 1, 0), Blocks.chest.getDefaultState());
		TileEntity tile = world.getTileEntity(pos.add(0, 1, 0));
		if (tile instanceof TileEntityChest) {
			TileEntityChest chest = (TileEntityChest) tile;
			for (int i = 0; i < chest.getSizeInventory(); i++) {
				if (MathUtil.getRandomNumberInRange(0, MathUtil.getRandomNumberInRange(0, 3)) == 0) {
					chest.setInventorySlotContents(i, stacks.get(MathUtil.getRandomNumberInRange(0, stacks.size() - 1)).getItemStack());
				}
			}
			chest.update();
		}
		world.playSoundAtEntity(player, HalloweenLuckyBlockSounds.tension, 0.2F, 1.0F);
	}
}
