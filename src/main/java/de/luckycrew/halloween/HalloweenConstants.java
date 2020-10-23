package de.luckycrew.halloween;

import org.apache.logging.log4j.*;

import net.minecraftforge.common.ForgeVersion;

public class HalloweenConstants {

	public static final String MODID = "luckyblock_halloween";
	public static final String NAME = "Halloween LuckyBlocks";
	public static final String VERSION = "@VERSION@";
	public static final String MCVERSION = ForgeVersion.mcVersion;
	public static final String DEPENDENCIES = "required-after:uteamcore@[1.3.0,);";

	public static final String UPDATEURL = "https://api.u-team.info/update/halloween_luckyblock.json";

	public static final String COMMONPROXY = "de.luckycrew.halloween.proxy.CommonProxy";
	public static final String CLIENTPROXY = "de.luckycrew.halloween.proxy.ClientProxy";

	public static final Logger LOGGER = LogManager.getLogger(NAME);

	private HalloweenConstants() {
	}

}
