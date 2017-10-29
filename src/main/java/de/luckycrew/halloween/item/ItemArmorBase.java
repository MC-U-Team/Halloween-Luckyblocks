package de.luckycrew.halloween.item;

import de.luckycrew.halloween.HalloweenConstants;
import info.u_team.u_team_core.creativetab.UCreativeTab;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.*;

public class ItemArmorBase extends ItemArmor {
	
	private String name;
	private String type;
	
	public ItemArmorBase(ArmorMaterial material, int armorType, String name, String type, UCreativeTab tab) {
		super(material, 0, armorType);
		this.name = name;
		this.type = type;
		this.setCreativeTab(tab);
		this.register();
	}
	
	private void register() {
		setUnlocalizedName(name + "_" + type);
		GameRegistry.registerItem(this, name + "_" + type);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		String base = HalloweenConstants.MODID + ":textures/models/armor/" + name;
		if (slot == 0 || slot == 1 || slot == 3) {
			return base + "_1.png";
		} else if (slot == 2) {
			return base + "_2.png";
		} else {
			return null;
		}
	}
	
	public String getName() {
		return name;
	}
	
	public static int getArmorBaseCount(EntityPlayer player, String name) {
		int count = 0;
		ItemStack[] inventory = player.inventory.armorInventory;
		for (int i = 0; i < inventory.length; ++i) {
			if (inventory[i] != null && inventory[i].getItem() instanceof ItemArmorBase) {
				ItemArmorBase item = (ItemArmorBase) inventory[i].getItem();
				if (item.name.equals(name)) {
					count++;
				}
			}
		}
		return count;
	}
}
