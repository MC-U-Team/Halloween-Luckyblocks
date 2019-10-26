package info.u_team.halloween_luckyblock.block;

import info.u_team.halloween_luckyblock.init.*;
import info.u_team.u_team_core.block.UTileEntityBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.*;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.*;

public class BlockPumpkinBomb extends UTileEntityBlock {
	
	private static final VoxelShape SHAPE = makeCuboidShape(4, 4, 4, 12, 12, 12);
	
	public BlockPumpkinBomb(String name) {
		super(name, HalloweenLuckyBlockItemGroups.GROUP, Properties.create(Material.ROCK).hardnessAndResistance(-1, 100000).noDrops(), () -> HalloweenLuckyBlockTileEntityTypes.PUMPKINBOMB);
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		if (placer instanceof PlayerEntity && !world.isRemote) {
			placer.sendMessage(new TranslationTextComponent("tile.pumpkinbomb.place"));
		}
	}
	
	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}
	
	@Override
	public boolean isSolid(BlockState state) {
		return false;
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}
}
