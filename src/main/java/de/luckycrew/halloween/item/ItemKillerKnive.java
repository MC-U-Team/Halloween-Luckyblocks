package de.luckycrew.halloween.item;

import info.u_team.u_team_core.creativetab.UCreativeTab;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemKillerKnive extends ItemSword {
	
	private String name;
	
	public ItemKillerKnive(ToolMaterial material, String name, UCreativeTab tab) {
		super(material);
		this.name = name;
		this.setCreativeTab(tab);
		this.register();
	}
	
	private void register() {
		setUnlocalizedName(name);
		GameRegistry.registerItem(this, name);
	}
}
