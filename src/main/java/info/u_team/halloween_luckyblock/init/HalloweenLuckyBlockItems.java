package info.u_team.halloween_luckyblock.init;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockConstants;
import info.u_team.halloween_luckyblock.item.*;
import info.u_team.halloween_luckyblock.util.RegistryUtilArmor;
import info.u_team.u_team_core.item.armor.*;
import info.u_team.u_team_core.registry.ItemRegistry;
import info.u_team.u_team_core.util.RegistryUtil;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class HalloweenLuckyBlockItems {
	
	public static final ArmorMaterial scarecrow_material = EnumHelper.addArmorMaterial("scarecrow", "", 200, new int[] { 3, 8, 6, 3 }, 20, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F);
	public static final ArmorMaterial slender_material = EnumHelper.addArmorMaterial("slender", "", 200, new int[] { 6, 16, 12, 6 }, 20, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F);
	public static final ArmorMaterial witch_material = EnumHelper.addArmorMaterial("witch", "", 200, new int[] { 3, 10, 6, 1 }, 20, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F);
	public static final ArmorMaterial zombie_material = EnumHelper.addArmorMaterial("zombie", "", 200, new int[] { 2, 8, 7, 3 }, 20, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F);
	public static final ArmorMaterial clown_material = EnumHelper.addArmorMaterial("clown", "", 50, new int[] { 20, 20, 20, 20 }, 20, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F);
	
	public static final ToolMaterial killerknive_material = EnumHelper.addToolMaterial("killerknive", 1, 120, 1, 7, 20);
	
	public static final Item candy = new ItemCandy("candy");
	public static final Item teleporter = new ItemTeleporter("teleporter");
	public static final Item candybag = new ItemCandyBag("candybag");
	public static final Item witchsbroomstick = new ItemWitchsBroomstick("witchsbroomstick");
	public static final Item pumpkingrenade = new ItemPumpkinGrenade("pumpkingrenade");
	
	public static final Item killerknive = new ItemKillerKnive(killerknive_material, "killerknive");
	
	public static final UItemArmor[] scarecrow = createArmor(scarecrow_material, "scarecrow");
	public static final UItemArmor[] slender = createArmor(slender_material, "slender");
	public static final UItemArmor[] witch = createArmor(witch_material, "witch");
	public static final UItemArmor[] zombie = createArmor(zombie_material, "zombie");
	public static final UItemArmor[] clown = createArmor(clown_material, "clown");
	
	private static UItemArmor[] createArmor(ArmorMaterial material, String name) {
		UItemArmor[] armor = new UItemArmor[4];
		armor[0] = new UItemHelmet(name, HalloweenLuckyBlockCreativeTabs.tab, material);
		armor[1] = new UItemChestplate(name, HalloweenLuckyBlockCreativeTabs.tab, material);
		armor[2] = new UItemLeggings(name, HalloweenLuckyBlockCreativeTabs.tab, material);
		armor[3] = new UItemBoots(name, HalloweenLuckyBlockCreativeTabs.tab, material);
		return armor;
	}
	
	public static void preinit() {
		ItemRegistry.register(HalloweenLuckyBlockConstants.MODID, RegistryUtil.getRegistryEntries(Item.class, HalloweenLuckyBlockItems.class));
		ItemRegistry.register(HalloweenLuckyBlockConstants.MODID, RegistryUtilArmor.getArmorRegistryEntries(HalloweenLuckyBlockItems.class));
	}
	
}
