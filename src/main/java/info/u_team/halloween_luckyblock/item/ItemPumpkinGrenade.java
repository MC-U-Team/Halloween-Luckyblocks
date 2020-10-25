package info.u_team.halloween_luckyblock.item;

import info.u_team.halloween_luckyblock.entity.EntityPumpkinGrenade;
import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockItemGroups;
import info.u_team.u_team_core.item.UItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class ItemPumpkinGrenade extends UItem {
	
	public ItemPumpkinGrenade() {
		super(HalloweenLuckyBlockItemGroups.GROUP, new Properties().maxStackSize(16).rarity(Rarity.RARE));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (!player.abilities.isCreativeMode) {
			stack.shrink(1);
		}
		
		world.playSound(null, player.getPosition(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.NEUTRAL, 0.6F, 0.4F / (random.nextFloat() * 3F + 5F));
		
		if (!world.isRemote) {
			EntityPumpkinGrenade grenade = new EntityPumpkinGrenade(world, player);
			grenade.func_234612_a_(player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 0.5F);
			world.addEntity(grenade);
		}
		return new ActionResult<ItemStack>(ActionResultType.PASS, stack);
	}
}
