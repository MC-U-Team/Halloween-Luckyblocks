package info.u_team.halloween_luckyblock.init;

import info.u_team.u_team_core.item.armor.UArmorMaterialVanilla;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvents;

public class HalloweenLuckyBlockArmorMaterials {
	
	public static final IArmorMaterial SCARECROW = new UArmorMaterialVanilla(200, new int[] { 3, 8, 6, 3 }, 20, () -> SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0, 0, () -> Ingredient.EMPTY);
	public static final IArmorMaterial SLENDER = new UArmorMaterialVanilla(200, new int[] { 6, 16, 12, 6 }, 20, () -> SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0, 0, () -> Ingredient.EMPTY);
	public static final IArmorMaterial WITCH = new UArmorMaterialVanilla(200, new int[] { 3, 10, 6, 1 }, 20, () -> SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0, 0, () -> Ingredient.EMPTY);
	public static final IArmorMaterial ZOMBIE = new UArmorMaterialVanilla(200, new int[] { 2, 8, 7, 3 }, 20, () -> SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0, 0, () -> Ingredient.EMPTY);
	public static final IArmorMaterial CLOWN = new UArmorMaterialVanilla(50, new int[] { 20, 20, 20, 20 }, 20, () -> SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0, 0, () -> Ingredient.EMPTY);
	
}
