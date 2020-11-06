package info.u_team.halloween_luckyblock.event;

import java.util.ArrayList;

import info.u_team.halloween_luckyblock.core.LuckyEvent;
import info.u_team.halloween_luckyblock.init.*;
import info.u_team.halloween_luckyblock.util.ItemStackEntry;
import info.u_team.u_team_core.item.armor.UArmorItem;
import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.RegistryObject;

public class LuckyEventItems extends LuckyEvent {
	
	private final ArrayList<ItemStackEntry> stacks;
	
	public LuckyEventItems() {
		super("Items", 6);
		stacks = new ArrayList<>();
		additems();
	}
	
	private void additems() {
		add(new ItemStackEntry(new ItemStack(HalloweenLuckyBlockItems.CANDY.get())), 4);
		add(new ItemStackEntry(new ItemStack(HalloweenLuckyBlockItems.CANDYBAG.get()), 1, 2), 8);
		add(new ItemStackEntry(new ItemStack(HalloweenLuckyBlockItems.CANDYBAG_OP.get())), 1);
		add(new ItemStackEntry(new ItemStack(HalloweenLuckyBlockItems.KILLERKNIFE.get())), 5);
		add(new ItemStackEntry(new ItemStack(HalloweenLuckyBlockItems.PUMPKINGRENADE.get()), 1, 5), 3);
		add(new ItemStackEntry(new ItemStack(HalloweenLuckyBlockItems.TELEPORTER.get())), 3);
		add(new ItemStackEntry(new ItemStack(HalloweenLuckyBlockItems.WITCHBROOMSTICK.get())), 1);
		add(new ItemStackEntry(new ItemStack(HalloweenLuckyBlockBlocks.PUMPKINBOMB.get()), 1, 2), 2);
		
		for (final RegistryObject<? extends UArmorItem> item : HalloweenLuckyBlockItems.SCARECROW_SET) {
			add(new ItemStackEntry(new ItemStack(item.get())), 2);
		}
		for (final RegistryObject<? extends UArmorItem> item : HalloweenLuckyBlockItems.SLENDER_SET) {
			add(new ItemStackEntry(new ItemStack(item.get())), 2);
		}
		for (final RegistryObject<? extends UArmorItem> item : HalloweenLuckyBlockItems.WITCH_SET) {
			add(new ItemStackEntry(new ItemStack(item.get())), 2);
		}
		for (final RegistryObject<? extends UArmorItem> item : HalloweenLuckyBlockItems.ZOMBIE_SET) {
			add(new ItemStackEntry(new ItemStack(item.get())), 2);
		}
		for (final RegistryObject<? extends UArmorItem> item : HalloweenLuckyBlockItems.CLOWN_SET) {
			add(new ItemStackEntry(new ItemStack(item.get())), 2);
		}
		
		add(new ItemStackEntry(new ItemStack(Blocks.STONE), 10, 64), 3);
		add(new ItemStackEntry(new ItemStack(Blocks.COBBLESTONE), 10, 64), 3);
		
		add(new ItemStackEntry(new ItemStack(Blocks.WHITE_CONCRETE), 10, 64), 1);
		add(new ItemStackEntry(new ItemStack(Blocks.ORANGE_CONCRETE), 10, 64), 1);
		add(new ItemStackEntry(new ItemStack(Blocks.MAGENTA_CONCRETE), 10, 64), 1);
		add(new ItemStackEntry(new ItemStack(Blocks.LIGHT_BLUE_CONCRETE), 10, 64), 1);
		add(new ItemStackEntry(new ItemStack(Blocks.YELLOW_CONCRETE), 10, 64), 1);
		add(new ItemStackEntry(new ItemStack(Blocks.LIME_CONCRETE), 10, 64), 1);
		add(new ItemStackEntry(new ItemStack(Blocks.PINK_CONCRETE), 10, 64), 1);
		add(new ItemStackEntry(new ItemStack(Blocks.GRAY_CONCRETE), 10, 64), 1);
		add(new ItemStackEntry(new ItemStack(Blocks.LIGHT_GRAY_CONCRETE), 10, 64), 1);
		add(new ItemStackEntry(new ItemStack(Blocks.CYAN_CONCRETE), 10, 64), 1);
		add(new ItemStackEntry(new ItemStack(Blocks.PURPLE_CONCRETE), 10, 64), 1);
		add(new ItemStackEntry(new ItemStack(Blocks.BLUE_CONCRETE), 10, 64), 1);
		add(new ItemStackEntry(new ItemStack(Blocks.BROWN_CONCRETE), 10, 64), 1);
		add(new ItemStackEntry(new ItemStack(Blocks.GREEN_CONCRETE), 10, 64), 1);
		add(new ItemStackEntry(new ItemStack(Blocks.RED_CONCRETE), 10, 64), 1);
		add(new ItemStackEntry(new ItemStack(Blocks.BLACK_CONCRETE), 10, 64), 1);
	}
	
	private void add(ItemStackEntry entry, int count) {
		for (int i = 0; i < count; i++) {
			stacks.add(entry);
		}
	}
	
	@Override
	public void execute(ServerPlayerEntity player, ServerWorld world, BlockPos pos) {
		for (int i = 0; i < MathUtil.randomNumberInRange(1, 3); i++) {
			final ItemEntity item = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stacks.get(MathUtil.randomNumberInRange(0, stacks.size() - 1)).getItemStack());
			world.addEntity(item);
		}
	}
	
}
