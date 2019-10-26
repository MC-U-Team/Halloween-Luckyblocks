package info.u_team.halloween_luckyblock.util;

import info.u_team.u_team_core.api.registry.IURegistryType;
import net.minecraft.entity.EntityType;
import net.minecraft.item.SpawnEggItem;

public class USpawnEggItem extends SpawnEggItem implements IURegistryType {
	
	protected final String name;
	
	public USpawnEggItem(String name, EntityType<?> type, int primaryColor, int secondaryColor, Properties builder) {
		super(type, primaryColor, secondaryColor, builder);
		this.name = name;
	}
	
	@Override
	public String getEntryName() {
		return name;
	}
	
}
