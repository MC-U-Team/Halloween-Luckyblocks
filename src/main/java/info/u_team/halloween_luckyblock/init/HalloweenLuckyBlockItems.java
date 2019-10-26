package info.u_team.halloween_luckyblock.init;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockMod;
import info.u_team.halloween_luckyblock.item.*;
import info.u_team.halloween_luckyblock.util.USpawnEggItem;
import info.u_team.u_team_core.item.armor.*;
import info.u_team.u_team_core.util.registry.BaseRegistryUtil;
import net.minecraft.item.*;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = HalloweenLuckyBlockMod.MODID, bus = Bus.MOD)
public class HalloweenLuckyBlockItems {
	
	public static final Item CANDY = new ItemCandy("candy");
	public static final Item TELEPORTER = new ItemTeleporter("teleporter");
	public static final Item CANDYBAG = new ItemCandyBag("candybag", false);
	public static final Item CANDYBAG_OP = new ItemCandyBag("candybag_op", true);
	public static final Item WITCHBROOMSTICK = new ItemWitchsBroomstick("witchsbroomstick");
	public static final Item PUMPKINGRENADE = new ItemPumpkinGrenade("pumpkingrenade");
	
	public static final Item KILLERKNIFE = new ItemKillerKnive(HalloweenLuckyBlockToolMaterials.KILLERKNIFE, "killerknive");
	
	public static final ArmorSet SCARECROW_SET = ArmorSetCreator.create("scarecrow", HalloweenLuckyBlockItemGroups.GROUP, new Properties(), HalloweenLuckyBlockArmorMaterials.SCARECROW);
	public static final ArmorSet SLENDER_SET = ArmorSetCreator.create("slender", HalloweenLuckyBlockItemGroups.GROUP, new Properties(), HalloweenLuckyBlockArmorMaterials.SLENDER);
	public static final ArmorSet WITCH_SET = ArmorSetCreator.create("witch", HalloweenLuckyBlockItemGroups.GROUP, new Properties(), HalloweenLuckyBlockArmorMaterials.WITCH);
	public static final ArmorSet ZOMBIE_SET = ArmorSetCreator.create("zombie", HalloweenLuckyBlockItemGroups.GROUP, new Properties(), HalloweenLuckyBlockArmorMaterials.ZOMBIE);
	public static final ArmorSet CLOWN_SET = ArmorSetCreator.create("clown", HalloweenLuckyBlockItemGroups.GROUP, new Properties(), HalloweenLuckyBlockArmorMaterials.CLOWN);
	
	public static final SpawnEggItem CREEPY_ZOMBIE_SPAWN_EGG = new USpawnEggItem("creepy_zombie_spawn_egg", HalloweenLuckyBlockEntityTypes.CREEPY_ZOMBIE, 0x2ecc71, 0xe67e22, new Properties().group(HalloweenLuckyBlockItemGroups.GROUP));
	public static final SpawnEggItem VAMPIRE_SPAWN_EGG = new USpawnEggItem("vampire_spawn_egg", HalloweenLuckyBlockEntityTypes.VAMPIRE, 0x686c72, 0x000000, new Properties().group(HalloweenLuckyBlockItemGroups.GROUP));
	public static final SpawnEggItem GHOST_SPAWN_EGG = new USpawnEggItem("ghost_spawn_egg", HalloweenLuckyBlockEntityTypes.GHOST, 0x797a74, 0xffffff, new Properties().group(HalloweenLuckyBlockItemGroups.GROUP));
	
	@SubscribeEvent
	public static void register(Register<Item> event) {
		BaseRegistryUtil.getAllRegistryEntriesAndApplyNames(HalloweenLuckyBlockMod.MODID, Item.class).forEach(event.getRegistry()::register);
	}
	
}
