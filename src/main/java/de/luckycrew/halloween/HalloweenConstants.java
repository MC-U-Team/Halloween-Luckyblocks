package de.luckycrew.halloween;

import org.apache.logging.log4j.*;

public class HalloweenConstants {
	
	public static final String MODID = "luckyblock_halloween";
	public static final String NAME = "Halloween LuckyBlocks";
	public static final String VERSION = "2.0.0";
	public static final String MCVERSION = "1.8";
	
	public static final String UPDATEURL = "https://api.u-team.info/update/luckyblocks/halloween.json";
	
	public static final String COMMONPROXY = "de.luckycrew.halloween.proxy.CommonProxy";
	public static final String CLIENTPROXY = "de.luckycrew.halloween.proxy.ClientProxy";
	
	public static final Logger LOGGER = LogManager.getLogger(NAME);
	
	private HalloweenConstants() {
	}
	
}
