package info.u_team.halloween_luckyblock.event;

import java.util.ArrayList;

import info.u_team.halloween_luckyblock.core.LuckyEvent;
import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockItems;
import info.u_team.u_team_core.util.MathUtil;
import info.u_team.u_team_core.util.stack.ItemStackUtil;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.*;
import net.minecraft.world.World;

public class LuckyEventMerchant extends LuckyEvent {
	
	public static String[] playernames = new String[] { "Nightmare", "Halloween", "Devil", "Creep" };
	
	private ArrayList<Item> armor = new ArrayList<Item>();
	
	public LuckyEventMerchant() {
		super("Merchant", 2);
		for (Item item : HalloweenLuckyBlockItems.scarecrow) {
			armor.add(item);
		}
		for (Item item : HalloweenLuckyBlockItems.slender) {
			armor.add(item);
		}
		for (Item item : HalloweenLuckyBlockItems.witch) {
			armor.add(item);
		}
		for (Item item : HalloweenLuckyBlockItems.zombie) {
			armor.add(item);
		}
		for (Item item : HalloweenLuckyBlockItems.clown) {
			armor.add(item);
		}
	}
	
	@Override
	public void execute(EntityPlayerMP player, World world, BlockPos pos) {
		
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		
		EntityVillager villager = new EntityVillager(world);
		villager.setPosition(x, y, z);
		villager.setAlwaysRenderNameTag(true);
		villager.setCustomNameTag(playernames[MathUtil.getRandomNumberInRange(0, playernames.length - 1)]);
		MerchantRecipeList list = villager.getRecipes(player);
		list.clear();
		list.addAll(getRecipes());
		world.spawnEntity(villager);
		
		ItemStack diamond = ItemStackUtil.from(Items.DIAMOND);
		diamond.setCount(MathUtil.getRandomNumberInRange(9, 25));
		ItemStack goldingot = ItemStackUtil.from(Items.GOLD_INGOT);
		goldingot.setCount(MathUtil.getRandomNumberInRange(15, 64));
		
		EntityItem diamondentity = new EntityItem(world, x, y, z, diamond);
		EntityItem goldingotentity = new EntityItem(world, x, y, z, goldingot);
		
		world.spawnEntity(diamondentity);
		world.spawnEntity(goldingotentity);
		
	}
	
	private ArrayList<MerchantRecipe> getRecipes() {
		ArrayList<MerchantRecipe> list = new ArrayList<MerchantRecipe>();
		
		for (int i = 0; i < MathUtil.getRandomNumberInRange(5, 15); i++) {
			int r = MathUtil.getRandomNumberInRange(0, 19);
			MerchantRecipe re = null;
			
			ItemStack diamond = ItemStackUtil.from(Items.DIAMOND);
			ItemStack goldingot = ItemStackUtil.from(Items.GOLD_INGOT);
			
			ItemStack ironsword = ItemStackUtil.from(Items.IRON_SWORD);
			ItemStack diamondsword = ItemStackUtil.from(Items.DIAMOND_SWORD);
			
			switch (r) {
			case 0:
				diamond.setCount(MathUtil.getRandomNumberInRange(30, 45));
				re = new MerchantRecipe(diamond, ItemStackUtil.from(HalloweenLuckyBlockItems.killerknive));
				break;
			case 1:
				goldingot.setCount(MathUtil.getRandomNumberInRange(55, 64));
				re = new MerchantRecipe(goldingot, goldingot, ItemStackUtil.from(HalloweenLuckyBlockItems.killerknive));
				break;
			case 2:
				goldingot.setCount(MathUtil.getRandomNumberInRange(1, 3));
				re = new MerchantRecipe(goldingot, ironsword);
				break;
			case 3:
			case 4:
				diamond.setCount(MathUtil.getRandomNumberInRange(8, 15));
				NBTTagCompound comp34 = new NBTTagCompound();
				comp34.setBoolean("Unbreakable", true);
				ironsword.setTagCompound(comp34);
				ironsword.addEnchantment(Enchantments.SHARPNESS, MathUtil.getRandomNumberInRange(5, 16));
				if (MathUtil.getRandomNumberInRange(0, 4) == 0) {
					ironsword.addEnchantment(Enchantments.FIRE_ASPECT, MathUtil.getRandomNumberInRange(1, 2));
				}
				re = new MerchantRecipe(diamond, ironsword);
				break;
			case 5:
				goldingot.setCount(MathUtil.getRandomNumberInRange(30, 63));
				NBTTagCompound comp5 = new NBTTagCompound();
				comp5.setBoolean("Unbreakable", true);
				ironsword.setTagCompound(comp5);
				ironsword.addEnchantment(Enchantments.SHARPNESS, MathUtil.getRandomNumberInRange(5, 16));
				if (MathUtil.getRandomNumberInRange(0, 4) == 0) {
					ironsword.addEnchantment(Enchantments.FIRE_ASPECT, MathUtil.getRandomNumberInRange(1, 2));
				}
				re = new MerchantRecipe(goldingot, ironsword);
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
				re = new MerchantRecipe(diamond, diamondsword);
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
				ItemStack armor = ItemStackUtil.from(this.armor.get(MathUtil.getRandomNumberInRange(0, this.armor.size() - 1)));
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
				re = new MerchantRecipe(inputstack, armor);
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
