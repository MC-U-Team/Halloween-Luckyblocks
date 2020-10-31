package info.u_team.halloween_luckyblock.init;

import java.util.List;
import java.util.stream.*;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockMod;
import info.u_team.u_team_core.util.registry.CommonDeferredRegister;
import net.minecraft.util.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class HalloweenLuckyBlockSounds {
	
	public static final SoundCategory CATEGORY = SoundCategory.HOSTILE;// EnumHelperSoundCategory.addSoundCategory("halloween_sounds");
	
	public static final CommonDeferredRegister<SoundEvent> SOUND_EVENTS = CommonDeferredRegister.create(ForgeRegistries.SOUND_EVENTS, HalloweenLuckyBlockMod.MODID);
	
	public static final RegistryObject<SoundEvent> BASTA = SOUND_EVENTS.register("basta", SoundEvent::new);
	
	public static final RegistryObject<SoundEvent> GAME_DEATH = SOUND_EVENTS.register("game_death", SoundEvent::new);
	public static final RegistryObject<SoundEvent> HAHA = SOUND_EVENTS.register("haha", SoundEvent::new);
	public static final RegistryObject<SoundEvent> HAPPY_HALLOWEEN = SOUND_EVENTS.register("happy_halloween", SoundEvent::new);
	public static final RegistryObject<SoundEvent> HI_IM_CHUCKY = SOUND_EVENTS.register("hi_im_chucky", SoundEvent::new);
	public static final RegistryObject<SoundEvent> ONE_TWO = SOUND_EVENTS.register("one_two", SoundEvent::new);
	public static final RegistryObject<SoundEvent> ORGAN = SOUND_EVENTS.register("organ", SoundEvent::new);
	public static final RegistryObject<SoundEvent> RINGLE = SOUND_EVENTS.register("ringle", SoundEvent::new);
	public static final RegistryObject<SoundEvent> SCUBI_DABI = SOUND_EVENTS.register("scubi_dabi", SoundEvent::new);
	public static final RegistryObject<SoundEvent> SNIVELLING = SOUND_EVENTS.register("snivelling", SoundEvent::new);
	public static final RegistryObject<SoundEvent> STRANGE_LAUGHING = SOUND_EVENTS.register("strange_laughing", SoundEvent::new);
	public static final RegistryObject<SoundEvent> STRANGE = SOUND_EVENTS.register("strange", SoundEvent::new);
	public static final RegistryObject<SoundEvent> TENSION = SOUND_EVENTS.register("tension", SoundEvent::new);
	public static final RegistryObject<SoundEvent> THUNDER = SOUND_EVENTS.register("thunder", SoundEvent::new);
	public static final RegistryObject<SoundEvent> WIND = SOUND_EVENTS.register("wind", SoundEvent::new);
	public static final RegistryObject<SoundEvent> WUHU = SOUND_EVENTS.register("wuhu", SoundEvent::new);
	public static final RegistryObject<SoundEvent> YOU_HAVE = SOUND_EVENTS.register("you_have", SoundEvent::new);
	
	public static final List<RegistryObject<SoundEvent>> COMMON_SOUNDS = StreamSupport.stream(SOUND_EVENTS.spliterator(), false).filter(sound -> {
		final String name = sound.getId().getPath();
		return (name != "organ" && name != "game_death");
	}).collect(Collectors.toList());
	
	public static void registerMod(IEventBus bus) {
		SOUND_EVENTS.register(bus);
	}
}
