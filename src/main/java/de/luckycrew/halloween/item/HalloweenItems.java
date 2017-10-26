package de.luckycrew.halloween.item;

import de.luckycrew.halloween.tab.HalloweenTabs;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class HalloweenItems {
	
	public ArmorMaterial scarecrow_material;
	public ArmorMaterial slender_material;
	
	public static ItemArmorBase[] scarecrow, slender;
	
	public HalloweenItems() {
		amormaterial();
		armor();
	}
	
	private void amormaterial() {
		scarecrow_material = EnumHelper.addArmorMaterial("scarecrow", "", 200, new int[] { 3, 8, 6, 3 }, 20);
		slender_material = EnumHelper.addArmorMaterial("slender", "", 200, new int[] { 6, 16, 12, 6 }, 20);
	}
	
	private void armor() {
		scarecrow = createArmor(scarecrow_material, "scarecrow");
		slender = createArmor(slender_material, "slender");
	}
	
	private String[] armor = new String[] { "helmet", "chestplate", "leggings", "boots" };
	
	private ItemArmorBase[] createArmor(ArmorMaterial material, String name) {
		ItemArmorBase[] basearmor = new ItemArmorBase[4];
		
		for (int i = 0; i < basearmor.length; i++) {
			basearmor[i] = new ItemArmorBase(material, i, name, armor[i], HalloweenTabs.tab);
		}
		return basearmor;
	}
	
}
