package info.u_team.halloween_luckyblock.item;

import info.u_team.halloween_luckyblock.entity.EntityPumpkinGrenade;
import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockCreativeTabs;
import info.u_team.u_team_core.item.UItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class ItemPumpkinGrenade extends UItem {
	
	public ItemPumpkinGrenade(String name) {
		super(name, HalloweenLuckyBlockCreativeTabs.tab);
		setMaxStackSize(16);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (!player.capabilities.isCreativeMode) {
			stack.shrink(1);
		}
		
		world.playSound(null, player.getPosition(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.NEUTRAL, 0.6F, 0.4F / (itemRand.nextFloat() * 3F + 5F));
		
		if (!world.isRemote) {
			world.spawnEntity(new EntityPumpkinGrenade(world, player));
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
	}
	
}
