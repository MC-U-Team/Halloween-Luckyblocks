package info.u_team.halloween_luckyblock.item;

import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockCreativeTabs;
import info.u_team.u_team_core.item.UItemFood;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.*;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.*;

public class ItemCandyBag extends UItemFood {
	
	public ItemCandyBag(String name) {
		super(name, HalloweenLuckyBlockCreativeTabs.tab, 4, 2.0F);
		setAlwaysEdible();
		setHasSubtypes(true);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean hasEffect(ItemStack stack) {
		return stack.getMetadata() > 0;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 0));
			items.add(new ItemStack(this, 1, 1));
		}
		
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerModel() {
		super.registerModel();
		setModel(this, 1, getRegistryName());
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return stack.getMetadata() == 0 ? EnumRarity.RARE : EnumRarity.EPIC;
	}
	
	@Override
	public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		if (!worldIn.isRemote) {
			if (stack.getMetadata() == 0) {
				player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 1200, 1));
			} else {
				player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 2400, 4));
				player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 1200, 1));
				player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 2));
				player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 1200, 0));
				player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 1200, 0));
			}
		}
	}
}
