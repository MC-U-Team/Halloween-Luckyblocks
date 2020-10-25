package info.u_team.halloween_luckyblock.item;

import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockItemGroups;
import info.u_team.u_team_core.item.UItem;
import net.minecraft.item.*;
import net.minecraft.potion.*;
import net.minecraftforge.api.distmarker.*;

public class ItemCandyBag extends UItem {
	
	private static final Food normalFood = new Food.Builder() //
			.setAlwaysEdible() //
			.hunger(4) //
			.saturation(2) //
			.effect(new EffectInstance(Effects.ABSORPTION, 1200, 1), 1) //
			.build();
	
	private static final Food betterFood = new Food.Builder() //
			.setAlwaysEdible() //
			.hunger(4) //
			.saturation(10) //
			.effect(new EffectInstance(Effects.ABSORPTION, 2400, 4), 1) //
			.effect(new EffectInstance(Effects.REGENERATION, 1200, 1), 1) //
			.effect(new EffectInstance(Effects.RESISTANCE, 600, 2), 0.9F) //
			.effect(new EffectInstance(Effects.SPEED, 1200, 0), 1) //
			.effect(new EffectInstance(Effects.JUMP_BOOST, 1200, 0), 1) //
			.build();
	
	private final boolean betterItem;
	
	public ItemCandyBag(boolean betterItem) {
		super(HalloweenLuckyBlockItemGroups.GROUP, new Properties().food(betterItem ? betterFood : normalFood).rarity(betterItem ? Rarity.EPIC : Rarity.RARE));
		this.betterItem = betterItem;
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean hasEffect(ItemStack stack) {
		return betterItem;
	}
}
