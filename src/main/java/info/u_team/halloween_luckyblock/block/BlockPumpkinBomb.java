package info.u_team.halloween_luckyblock.block;

import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockItemGroups;
import info.u_team.halloween_luckyblock.tileentity.TileEntityPumpkinBomb;
import info.u_team.u_team_core.block.UTileEntityBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPumpkinBomb extends UTileEntityBlock {
	
	public BlockPumpkinBomb(String name) {
		super(name, Material.ROCK, HalloweenLuckyBlockItemGroups.GROUP, new UTileEntityProvider(new ResourceLocation(HalloweenLuckyBlockConstants.MODID, name + "_tile"), TileEntityPumpkinBomb.class));
		setBlockUnbreakable();
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		if (placer instanceof EntityPlayer && !world.isRemote) {
			placer.sendMessage(new TextComponentTranslation("tile.pumpkinbomb.place"));
		}
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
}
