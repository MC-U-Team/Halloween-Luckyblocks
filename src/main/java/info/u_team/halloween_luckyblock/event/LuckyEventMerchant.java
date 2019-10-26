package info.u_team.halloween_luckyblock.event;

import java.util.ArrayList;

import info.u_team.halloween_luckyblock.core.LuckyEvent;
import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockItems;
import info.u_team.halloween_luckyblock.util.MathUtil;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class LuckyEventMerchant extends LuckyEvent {
	
	public static String[] playernames = new String[] { "Nightmare", "Halloween", "Devil", "Creep" };
	
	private ArrayList<Item> armor = new ArrayList<Item>();
	
	public LuckyEventMerchant() {
		super("Merchant", 2);
		for (Item item : HalloweenLuckyBlockItems.SCARECROW_SET.getArray()) {
			armor.add(item);
		}
		for (Item item : HalloweenLuckyBlockItems.SLENDER_SET.getArray()) {
			armor.add(item);
		}
		for (Item item : HalloweenLuckyBlockItems.WITCH_SET.getArray()) {
			armor.add(item);
		}
		for (Item item : HalloweenLuckyBlockItems.ZOMBIE_SET.getArray()) {
			armor.add(item);
		}
		for (Item item : HalloweenLuckyBlockItems.CLOWN_SET.getArray()) {
			armor.add(item);
		}
	}
	
	@Override
	public void execute(ServerPlayerEntity player, World world, BlockPos pos) {
		
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		
		VillagerEntity villager = new VillagerEntity(EntityType.VILLAGER, world);
		villager.setPosition(x, y, z);
		villager.setCustomNameVisible(true);
		villager.setCustomName(new StringTextComponent(playernames[MathUtil.getRandomNumberInRange(0, playernames.length - 1)]));
		
		MerchantOffers offers = villager.getOffers();
		offers.clear();
		offers.addAll(getRecipes());
		world.addEntity(villager);
		
		ItemStack diamond = new ItemStack(Items.DIAMOND);
		diamond.setCount(MathUtil.getRandomNumberInRange(9, 25));
		ItemStack goldingot = new ItemStack(Items.GOLD_INGOT);
		goldingot.setCount(MathUtil.getRandomNumberInRange(15, 64));
		
		ItemEntity diamondentity = new ItemEntity(world, x, y, z, diamond);
		ItemEntity goldingotentity = new ItemEntity(world, x, y, z, goldingot);
		
		world.addEntity(diamondentity);
		world.addEntity(goldingotentity);
		
	}
	
	private ArrayList<MerchantOffer> getRecipes() {
		ArrayList<MerchantOffer> list = new ArrayList<MerchantOffer>();
		
		for (int i = 0; i < MathUtil.getRandomNumberInRange(5, 15); i++) {
			int r = MathUtil.getRandomNumberInRange(0, 19);
			MerchantOffer re = null;
			
			ItemStack diamond = new ItemStack(Items.DIAMOND);
			ItemStack goldingot = new ItemStack(Items.GOLD_INGOT);
			
			ItemStack ironsword = new ItemStack(Items.IRON_SWORD);
			ItemStack diamondsword = new ItemStack(Items.DIAMOND_SWORD);
			
			switch (r) {
			case 0:
				diamond.setCount(MathUtil.getRandomNumberInRange(30, 45));
				re = new MerchantOffer(diamond, new ItemStack(HalloweenLuckyBlockItems.KILLERKNIFE), 10, 2, 1);
				break;
			case 1:
				goldingot.setCount(MathUtil.getRandomNumberInRange(55, 64));
				re = new MerchantOffer(goldingot, goldingot, new ItemStack(HalloweenLuckyBlockItems.KILLERKNIFE), 10, 2, 1);
				break;
			case 2:
				goldingot.setCount(MathUtil.getRandomNumberInRange(1, 3));
				re = new MerchantOffer(goldingot, ironsword, 10, 2, 1);
				break;
			case 3:
			case 4:
				diamond.setCount(MathUtil.getRandomNumberInRange(8, 15));
				CompoundNBT comp34 = new CompoundNBT();
				comp34.putBoolean("Unbreakable", true);
				ironsword.setTag(comp34);
				ironsword.addEnchantment(Enchantments.SHARPNESS, MathUtil.getRandomNumberInRange(5, 16));
				if (MathUtil.getRandomNumberInRange(0, 4) == 0) {
					ironsword.addEnchantment(Enchantments.FIRE_ASPECT, MathUtil.getRandomNumberInRange(1, 2));
				}
				re = new MerchantOffer(diamond, ironsword, 10, 2, 1);
				break;
			case 5:
				goldingot.setCount(MathUtil.getRandomNumberInRange(30, 63));
				CompoundNBT comp5 = new CompoundNBT();
				comp5.putBoolean("Unbreakable", true);
				ironsword.setTag(comp5);
				ironsword.addEnchantment(Enchantments.SHARPNESS, MathUtil.getRandomNumberInRange(5, 16));
				if (MathUtil.getRandomNumberInRange(0, 4) == 0) {
					ironsword.addEnchantment(Enchantments.FIRE_ASPECT, MathUtil.getRandomNumberInRange(1, 2));
				}
				re = new MerchantOffer(goldingot, ironsword, 10, 2, 1);
				break;
			case 6:
				diamond.setCount(MathUtil.getRandomNumberInRange(15, 29));
				diamondsword.addEnchantment(Enchantments.UNBREAKING, MathUtil.getRandomNumberInRange(2, 5));
				diamondsword.addEnchantment(Enchantments.SHARPNESS, MathUtil.getRandomNumberInRange(10, 20));
				if (MathUtil.getRandomNumberInRange(0, 2) == 0) {
					diamondsword.addEnchantment(Enchantments.FIRE_ASPECT, MathUtil.getRandomNumberInRange(1, 2));
				}
				if (MathUtil.getRandomNumberInRange(0, 5) == 0) {
					diamondsword.addEnchantment(Enchantments.KNOCKBACK, MathUtil.getRandomNumberInRange(1, 2));
				}
				if (MathUtil.getRandomNumberInRange(0, 8) == 0) {
					diamondsword.addEnchantment(Enchantments.THORNS, MathUtil.getRandomNumberInRange(2, 4));
				}
				re = new MerchantOffer(diamond, diamondsword, 10, 2, 1);
				break;
			case 7:
			case 8:
			case 9:
			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
			case 15:
			case 16:
			case 17:
			case 18:
			case 19:
				ItemStack armor = new ItemStack(this.armor.get(MathUtil.getRandomNumberInRange(0, this.armor.size() - 1)));
				ItemStack inputstack;
				switch (MathUtil.getRandomNumberInRange(0, 1)) {
				case 0:
					inputstack = diamond;
					inputstack.setCount(MathUtil.getRandomNumberInRange(5, 21));
					break;
				default:
					inputstack = goldingot;
					inputstack.setCount(MathUtil.getRandomNumberInRange(45, 63));
					break;
				}
				re = new MerchantOffer(inputstack, armor, 11, 2, 1)s;
				break;
			default:
				break;
			}
			
			if (re != null) {
				list.add(re);
			}
		}
		return list;
	}
	
}
