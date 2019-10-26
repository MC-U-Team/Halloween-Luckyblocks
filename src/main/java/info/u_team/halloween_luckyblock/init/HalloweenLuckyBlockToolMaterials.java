package info.u_team.halloween_luckyblock.init;

import info.u_team.u_team_core.api.IToolMaterial;
import info.u_team.u_team_core.item.tool.UToolMaterial;
import net.minecraft.item.crafting.Ingredient;

public class HalloweenLuckyBlockToolMaterials {
	
	public static final IToolMaterial KILLERKNIFE = new UToolMaterial(new float[] { 0, 0, 0, 0, -1 }, new float[] { 0, 0, 0, 0, -2.4000000953674316F }, 1, 120, 1, 7, 20, () -> Ingredient.EMPTY);
	
}
