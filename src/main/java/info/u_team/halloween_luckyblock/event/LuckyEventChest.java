package info.u_team.halloween_luckyblock.event;

import java.util.*;

import info.u_team.halloween_luckyblock.core.LuckyEvent;
import info.u_team.halloween_luckyblock.init.*;
import info.u_team.halloween_luckyblock.util.ItemStackEntry;
import info.u_team.u_team_core.item.armor.UArmorItem;
import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class LuckyEventChest extends LuckyEvent {
	
	private ArrayList<ItemStackEntry> stacks;
	
	public LuckyEventChest() {
		super("Chest", 3);
		stacks = new ArrayList<ItemStackEntry>();
		additems();
	}
	
	private void additems() {
		for (RegistryObject<? extends UArmorItem> item : HalloweenLuckyBlockItems.SCARECROW_SET) {
			add(new ItemStackEntry(new ItemStack(item.get())), 5);
		}
		for (RegistryObject<? extends UArmorItem> item : HalloweenLuckyBlockItems.SLENDER_SET) {
			add(new ItemStackEntry(new ItemStack(item.get())), 5);
		}
		for (RegistryObject<? extends UArmorItem> item : HalloweenLuckyBlockItems.WITCH_SET) {
			add(new ItemStackEntry(new ItemStack(item.get())), 5);
		}
		for (RegistryObject<? extends UArmorItem> item : HalloweenLuckyBlockItems.ZOMBIE_SET) {
			add(new ItemStackEntry(new ItemStack(item.get())), 5);
		}
		for (RegistryObject<? extends UArmorItem> item : HalloweenLuckyBlockItems.CLOWN_SET) {
			add(new ItemStackEntry(new ItemStack(item.get())), 5);
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
	public void execute(ServerPlayerEntity player, ServerWorld world, BlockPos pos) {
		world.setBlockState(pos.add(0, 1, 0), Blocks.CHEST.getDefaultState());
		TileEntity tile = world.getTileEntity(pos.add(0, 1, 0));
		if (tile instanceof ChestTileEntity) {
			ChestTileEntity chest = (ChestTileEntity) tile;
			for (int i = 0; i < chest.getSizeInventory(); i++) {
				if (MathUtil.randomNumberInRange(0, MathUtil.randomNumberInRange(0, 3)) == 0) {
					chest.setInventorySlotContents(i, stacks.get(MathUtil.randomNumberInRange(0, stacks.size() - 1)).getItemStack());
				}
			}
			chest.tick();
		}
		world.playSound(null, pos, HalloweenLuckyBlockSounds.TENSION.get(), HalloweenLuckyBlockSounds.CATEGORY, 0.2F, 1.0F);
	}
}
