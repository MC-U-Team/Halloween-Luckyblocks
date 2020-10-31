package info.u_team.to_u_team_core;

import java.util.Map;
import java.util.function.Supplier;

import com.google.common.collect.Maps;

import net.minecraft.entity.EntityType;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.LazyValue;

public class LazySpawnEggItem extends SpawnEggItem {
	
	private static final Map<LazyValue<? extends EntityType<?>>, LazySpawnEggItem> LAZY_EGGS = Maps.newIdentityHashMap();
	
	public LazySpawnEggItem(Supplier<? extends EntityType<?>> entityType, int primaryColor, int secondaryColor, Properties builder) {
		super(null, primaryColor, secondaryColor, builder);
		EGGS.remove(null);
		LAZY_EGGS.put(new LazyValue<>(entityType), this);
	}
	
	public static Map<LazyValue<? extends EntityType<?>>, LazySpawnEggItem> getLazyEggs() {
		return LAZY_EGGS;
	}
	
}
