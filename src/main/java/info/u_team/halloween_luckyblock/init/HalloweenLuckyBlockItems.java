package info.u_team.halloween_luckyblock.init;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockMod;
import info.u_team.halloween_luckyblock.item.*;
import info.u_team.to_u_team_core.LazySpawnEggItem;
import info.u_team.u_team_core.item.armor.*;
import info.u_team.u_team_core.util.registry.CommonDeferredRegister;
import net.minecraft.item.*;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class HalloweenLuckyBlockItems {
	
	public static final CommonDeferredRegister<Item> ITEMS = CommonDeferredRegister.create(ForgeRegistries.ITEMS, HalloweenLuckyBlockMod.MODID);
	
	public static final RegistryObject<Item> CANDY = ITEMS.register("candy", ItemCandy::new);
	public static final RegistryObject<Item> TELEPORTER = ITEMS.register("teleporter", ItemTeleporter::new);
	public static final RegistryObject<Item> CANDYBAG = ITEMS.register("candybag", () -> new ItemCandyBag(false));
	public static final RegistryObject<Item> CANDYBAG_OP = ITEMS.register("candybag_op", () -> new ItemCandyBag(true));
	public static final RegistryObject<Item> WITCHBROOMSTICK = ITEMS.register("witchsbroomstick", ItemWitchsBroomstick::new);
	public static final RegistryObject<Item> PUMPKINGRENADE = ITEMS.register("pumpkingrenade", ItemPumpkinGrenade::new);
	
	public static final RegistryObject<Item> KILLERKNIFE = ITEMS.register("killerknive", () -> new ItemKillerKnive(HalloweenLuckyBlockToolMaterials.KILLERKNIFE));
	
	public static final ArmorSet SCARECROW_SET = ArmorSetCreator.create(ITEMS, "scarecrow", HalloweenLuckyBlockItemGroups.GROUP, new Properties(), HalloweenLuckyBlockArmorMaterials.SCARECROW);
	public static final ArmorSet SLENDER_SET = ArmorSetCreator.create(ITEMS, "slender", HalloweenLuckyBlockItemGroups.GROUP, new Properties(), HalloweenLuckyBlockArmorMaterials.SLENDER);
	public static final ArmorSet WITCH_SET = ArmorSetCreator.create(ITEMS, "witch", HalloweenLuckyBlockItemGroups.GROUP, new Properties(), HalloweenLuckyBlockArmorMaterials.WITCH);
	public static final ArmorSet ZOMBIE_SET = ArmorSetCreator.create(ITEMS, "zombie", HalloweenLuckyBlockItemGroups.GROUP, new Properties(), HalloweenLuckyBlockArmorMaterials.ZOMBIE);
	public static final ArmorSet CLOWN_SET = ArmorSetCreator.create(ITEMS, "clown", HalloweenLuckyBlockItemGroups.GROUP, new Properties(), HalloweenLuckyBlockArmorMaterials.CLOWN);
	
	public static final RegistryObject<SpawnEggItem> CREEPY_ZOMBIE_SPAWN_EGG = ITEMS.register("creepy_zombie_spawn_egg", () -> new LazySpawnEggItem(HalloweenLuckyBlockEntityTypes.CREEPY_ZOMBIE, 0x2ecc71, 0xe67e22, new Properties().group(HalloweenLuckyBlockItemGroups.GROUP)));
	public static final RegistryObject<SpawnEggItem> VAMPIRE_SPAWN_EGG = ITEMS.register("vampire_spawn_egg", () -> new LazySpawnEggItem(HalloweenLuckyBlockEntityTypes.VAMPIRE, 0x686c72, 0x000000, new Properties().group(HalloweenLuckyBlockItemGroups.GROUP)));
	public static final RegistryObject<SpawnEggItem> GHOST_SPAWN_EGG = ITEMS.register("ghost_spawn_egg", () -> new LazySpawnEggItem(HalloweenLuckyBlockEntityTypes.GHOST, 0x797a74, 0xffffff, new Properties().group(HalloweenLuckyBlockItemGroups.GROUP)));
	
	public static void registerMod(IEventBus bus) {
		ITEMS.register(bus);
	}
	
}
