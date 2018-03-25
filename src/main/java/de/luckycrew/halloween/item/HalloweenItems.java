package de.luckycrew.halloween.item;

import static de.luckycrew.halloween.tab.HalloweenTabs.tab;

import info.u_team.u_team_core.item.armor.UItemArmor;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class HalloweenItems {
	
	public static ArmorMaterial scarecrow_material, slender_material, witch_material, zombie_material, clown_material;
	
	public static ToolMaterial killerknive_material;
	
	public static Item candy, teleporter, candybag, witchsbroomstick, pumpkingrenade;
	
	public static UItemArmor[] scarecrow, slender, witch, zombie, clown;
	
	public static Item killerknive;
	
	public HalloweenItems() {
		amormaterial();
		toolmaterial();
		item();
	}
	
	private void amormaterial() {
		scarecrow_material = EnumHelper.addArmorMaterial("scarecrow", "", 200, new int[] { 3, 8, 6, 3 }, 20);
		slender_material = EnumHelper.addArmorMaterial("slender", "", 200, new int[] { 6, 16, 12, 6 }, 20);
		witch_material = EnumHelper.addArmorMaterial("witch", "", 200, new int[] { 3, 10, 6, 1 }, 20);
		zombie_material = EnumHelper.addArmorMaterial("zombie", "", 200, new int[] { 2, 8, 7, 3 }, 20);
		clown_material = EnumHelper.addArmorMaterial("clown", "", 50, new int[] { 20, 20, 20, 20 }, 20);
	}
	
	private void toolmaterial() {
		killerknive_material = EnumHelper.addToolMaterial("killerknive", 1, 120, 1, 7, 20);
	}
	
	private void item() {
		candy = new ItemCandy("candy", tab);
		teleporter = new ItemTeleporter("teleporter", tab);
		candybag = new ItemCandyBag("candybag", tab);
		witchsbroomstick = new ItemWitchsBroomstick("witchsbroomstick", tab);
		pumpkingrenade = new ItemPumpkinGrenade("pumpkingrenade", tab);
		armor();
		tool();
	}
	
	private void armor() {
		scarecrow = createArmor(scarecrow_material, "scarecrow");
		slender = createArmor(slender_material, "slender");
		witch = createArmor(witch_material, "witch");
		zombie = createArmor(zombie_material, "zombie");
		clown = createArmor(clown_material, "clown");
	}
	
	private void tool() {
		killerknive = new ItemKillerKnive(killerknive_material, "killerknive", tab);
	}
	
	// Methods
	private String[] armor = new String[] { "helmet", "chestplate", "leggings", "boots" };
	
	private UItemArmor[] createArmor(ArmorMaterial material, String name) {
		UItemArmor[] basearmor = new UItemArmor[4];
		
		for (int i = 0; i < basearmor.length; i++) {
			basearmor[i] = new UItemArmor(name, tab, material, i, armor[i]);
		}
		return basearmor;
	}
	
}
