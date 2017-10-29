package de.luckycrew.halloween.block;

import de.luckycrew.halloween.item.ItemBlockEpic;
import de.luckycrew.halloween.tileentity.TileEntityPumpkinBomb;
import info.u_team.u_team_core.block.UBlockTileEntity;
import info.u_team.u_team_core.creativetab.UCreativeTab;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class BlockPumpkinBomb extends UBlockTileEntity {
	
	public BlockPumpkinBomb(String name, UCreativeTab tab) {
		super(TileEntityPumpkinBomb.class, Material.rock, name, tab, ItemBlockEpic.class);
		setBlockUnbreakable();
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		if (placer instanceof EntityPlayer && !world.isRemote) {
			placer.addChatMessage(new ChatComponentTranslation("tile.pumpkinbomb.place"));
		}
	}
	
	@Override
	public boolean isFullCube() {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
}
