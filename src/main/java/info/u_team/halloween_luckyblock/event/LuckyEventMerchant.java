package info.u_team.halloween_luckyblock.event;

import java.util.*;

import info.u_team.halloween_luckyblock.core.LuckyEvent;
import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockItems;
import info.u_team.u_team_core.item.armor.UArmorItem;
import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.villager.*;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.villager.VillagerType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.RegistryObject;

public class LuckyEventMerchant extends LuckyEvent {
	
	public static String[] playernames = new String[] { "Nightmare", "Halloween", "Devil", "Creep" };
	
	private final List<Item> armor = new ArrayList<Item>();
	
	private final List<VillagerType> types = new ArrayList<VillagerType>();
	
	public LuckyEventMerchant() {
		super("Merchant", 2);
		for (RegistryObject<? extends UArmorItem> item : HalloweenLuckyBlockItems.SCARECROW_SET) {
			armor.add(item.get());
		}
		for (RegistryObject<? extends UArmorItem> item : HalloweenLuckyBlockItems.SLENDER_SET) {
			armor.add(item.get());
		}
		for (RegistryObject<? extends UArmorItem> item : HalloweenLuckyBlockItems.WITCH_SET) {
			armor.add(item.get());
		}
		for (RegistryObject<? extends UArmorItem> item : HalloweenLuckyBlockItems.ZOMBIE_SET) {
			armor.add(item.get());
		}
		for (RegistryObject<? extends UArmorItem> item : HalloweenLuckyBlockItems.CLOWN_SET) {
			armor.add(item.get());
		}
		
		types.add(VillagerType.DESERT);
		types.add(VillagerType.JUNGLE);
		types.add(VillagerType.PLAINS);
		types.add(VillagerType.SAVANNA);
		types.add(VillagerType.SNOW);
		types.add(VillagerType.SWAMP);
		types.add(VillagerType.TAIGA);
	}
	
	@Override
	public void execute(ServerPlayerEntity player, ServerWorld world, BlockPos pos) {
		
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		
		VillagerEntity villager = new VillagerEntity(EntityType.VILLAGER, world);
		
		villager.setVillagerData(villager.getVillagerData().withType(types.get(MathUtil.randomNumberInRange(0, types.size() - 1))).withProfession(VillagerProfession.NITWIT));
		
		villager.setPosition(x, y, z);
		villager.setCustomNameVisible(true);
		villager.setCustomName(new StringTextComponent(playernames[MathUtil.randomNumberInRange(0, playernames.length - 1)]));
		
		MerchantOffers offers = villager.getOffers();
		offers.clear();
		offers.addAll(getRecipes());
		world.addEntity(villager);
		
		ItemStack diamond = new ItemStack(Items.DIAMOND);
		diamond.setCount(MathUtil.randomNumberInRange(9, 25));
		ItemStack goldingot = new ItemStack(Items.GOLD_INGOT);
		goldingot.setCount(MathUtil.randomNumberInRange(15, 64));
		
		ItemEntity diamondentity = new ItemEntity(world, x, y, z, diamond);
		ItemEntity goldingotentity = new ItemEntity(world, x, y, z, goldingot);
		
		world.addEntity(diamondentity);
		world.addEntity(goldingotentity);
		
	}
	
	private ArrayList<MerchantOffer> getRecipes() {
		ArrayList<MerchantOffer> list = new ArrayList<MerchantOffer>();
		
		for (int i = 0; i < MathUtil.randomNumberInRange(5, 15); i++) {
			int r = MathUtil.randomNumberInRange(0, 19);
			MerchantOffer re = null;
			
			ItemStack diamond = new ItemStack(Items.DIAMOND);
			ItemStack goldingot = new ItemStack(Items.GOLD_INGOT);
			
			ItemStack ironsword = new ItemStack(Items.IRON_SWORD);
			ItemStack diamondsword = new ItemStack(Items.DIAMOND_SWORD);
			
			switch (r) {
			case 0:
				diamond.setCount(MathUtil.randomNumberInRange(30, 45));
				re = new MerchantOffer(diamond, new ItemStack(HalloweenLuckyBlockItems.KILLERKNIFE.get()), 10, 2, 1);
				break;
			case 1:
				goldingot.setCount(MathUtil.randomNumberInRange(55, 64));
				re = new MerchantOffer(goldingot, goldingot, new ItemStack(HalloweenLuckyBlockItems.KILLERKNIFE.get()), 10, 2, 1);
				break;
			case 2:
				goldingot.setCount(MathUtil.randomNumberInRange(1, 3));
				re = new MerchantOffer(goldingot, ironsword, 10, 2, 1);
				break;
			case 3:
			case 4:
				diamond.setCount(MathUtil.randomNumberInRange(8, 15));
				CompoundNBT comp34 = new CompoundNBT();
				comp34.putBoolean("Unbreakable", true);
				ironsword.setTag(comp34);
				ironsword.addEnchantment(Enchantments.SHARPNESS, MathUtil.randomNumberInRange(5, 16));
				if (MathUtil.randomNumberInRange(0, 4) == 0) {
					ironsword.addEnchantment(Enchantments.FIRE_ASPECT, MathUtil.randomNumberInRange(1, 2));
				}
				re = new MerchantOffer(diamond, ironsword, 10, 2, 1);
				break;
			case 5:
				goldingot.setCount(MathUtil.randomNumberInRange(30, 63));
				CompoundNBT comp5 = new CompoundNBT();
				comp5.putBoolean("Unbreakable", true);
				ironsword.setTag(comp5);
				ironsword.addEnchantment(Enchantments.SHARPNESS, MathUtil.randomNumberInRange(5, 16));
				if (MathUtil.randomNumberInRange(0, 4) == 0) {
					ironsword.addEnchantment(Enchantments.FIRE_ASPECT, MathUtil.randomNumberInRange(1, 2));
				}
				re = new MerchantOffer(goldingot, ironsword, 10, 2, 1);
				break;
			case 6:
				diamond.setCount(MathUtil.randomNumberInRange(15, 29));
				diamondsword.addEnchantment(Enchantments.UNBREAKING, MathUtil.randomNumberInRange(2, 5));
				diamondsword.addEnchantment(Enchantments.SHARPNESS, MathUtil.randomNumberInRange(10, 20));
				if (MathUtil.randomNumberInRange(0, 2) == 0) {
					diamondsword.addEnchantment(Enchantments.FIRE_ASPECT, MathUtil.randomNumberInRange(1, 2));
				}
				if (MathUtil.randomNumberInRange(0, 5) == 0) {
					diamondsword.addEnchantment(Enchantments.KNOCKBACK, MathUtil.randomNumberInRange(1, 2));
				}
				if (MathUtil.randomNumberInRange(0, 8) == 0) {
					diamondsword.addEnchantment(Enchantments.THORNS, MathUtil.randomNumberInRange(2, 4));
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
				ItemStack armor = new ItemStack(this.armor.get(MathUtil.randomNumberInRange(0, this.armor.size() - 1)));
				ItemStack inputstack;
				switch (MathUtil.randomNumberInRange(0, 1)) {
				case 0:
					inputstack = diamond;
					inputstack.setCount(MathUtil.randomNumberInRange(5, 21));
					break;
				default:
					inputstack = goldingot;
					inputstack.setCount(MathUtil.randomNumberInRange(45, 63));
					break;
				}
				re = new MerchantOffer(inputstack, armor, 11, 2, 1);
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
