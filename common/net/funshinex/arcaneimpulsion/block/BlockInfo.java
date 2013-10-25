package net.funshinex.arcaneimpulsion.block;

public class BlockInfo {

	public static final String TEXTURE_LOCATION = "arcaneimpulsion";
	
	public static int IMPULSION_DRIVE_BASIC_ID;
    public static final String IMPULSION_DRIVE_BASIC_KEY = "BasicImpulsionDrive";
    public static final int IMPULSION_DRIVE_BASIC_DEFAULT = 3151;

    public static final String IMPULSION_DRIVE_BASIC_UNLOCALIZED_NAME = "basicImpulsionDrive";
    public static final String IMPULSION_DRIVE_BASIC_NAME = "Basic Impulsion Drive";

    public static final String IMPULSION_DRIVE_BASIC_TOP = "impulsion_drive_basic_top";
    public static final String IMPULSION_DRIVE_BASIC_TOP_ACTIVE = "impulsion_drive_basic_top_active";
    public static final String IMPULSION_DRIVE_BASIC_SIDE = "impulsion_drive_basic_side";
    public static final String IMPULSION_DRIVE_BASIC_BOTTOM = "impulsion_drive_basic_bottom";
    
    public static int IMPULSION_DRIVE_ADVANCED_ID;
    public static final String IMPULSION_DRIVE_ADVANCED_KEY = "AdvancedImpulsionDrive";
    public static final int IMPULSION_DRIVE_ADVANCED_DEFAULT = 3152;

    public static final String IMPULSION_DRIVE_ADVANCED_UNLOCALIZED_NAME = "advancedImpulsionDrive";
    public static final String IMPULSION_DRIVE_ADVANCED_NAME = "Advanced Impulsion Drive";

    public static final String IMPULSION_DRIVE_ADVANCED_TOP = "impulsion_drive_advanced_top";
    public static final String IMPULSION_DRIVE_ADVANCED_SIDE = "impulsion_drive_advanced_side";
    public static final String IMPULSION_DRIVE_ADVANCED_BOTTOM = "impulsion_drive_advanced_bottom";

	public static int ARCANE_COLLECTOR_ID;
	public static final String ARCANE_COLLECTOR_KEY = "ArcaneCollector";
    public static final int ARCANE_COLLECTOR_DEFAULT = 3153;

    public static final String ARCANE_COLLECTOR_UNLOCALIZED_NAME = "arcaneCollector";
    public static final String ARCANE_COLLECTOR_NAME = "Arcane Collector";

    public static final String ARCANE_COLLECTOR_TOP = "arcane_collector_top";
    public static final String ARCANE_COLLECTOR_SIDE = "arcane_collector_side";
    public static final String ARCANE_COLLECTOR_BOTTOM = "arcane_collector_bottom";
    
    public static int ARCANE_EXTRACTOR_ID;
	public static final String ARCANE_EXTRACTOR_KEY = "ArcaneExtractor";
    public static final int ARCANE_EXTRACTOR_DEFAULT = 3154;

    public static final String ARCANE_EXTRACTOR_UNLOCALIZED_NAME = "arcaneExtractor";
    public static final String ARCANE_EXTRACTOR_NAME = "Arcane Extractor";

    public static final String ARCANE_EXTRACTOR_TOP = "arcane_extractor_top";
    public static final String ARCANE_EXTRACTOR_SIDE = "arcane_extractor_side";
    public static final String ARCANE_EXTRACTOR_BOTTOM = "arcane_extractor_bottom";
    
    public static int ARCANE_CONDENSER_ID;
	public static final String ARCANE_CONDENSER_KEY = "ArcaneCondenser";
    public static final int ARCANE_CONDENSER_DEFAULT = 3155;

    public static final String ARCANE_CONDENSER_UNLOCALIZED_NAME = "arcaneCondenser";
    public static final String ARCANE_CONDENSER_NAME = "Arcane Condenser";

    public static final String ARCANE_CONDENSER_TOP = "arcane_condenser_top";
    public static final String ARCANE_CONDENSER_SIDE = "arcane_condenser_side";
    public static final String ARCANE_CONDENSER_BOTTOM = "arcane_condenser_bottom";
    
    public static int IM_STORAGE_ID;
	public static final String IM_STORAGE_KEY = "IMStorage";
    public static final int IM_STORAGE_DEFAULT = 3156;

    public static final String IM_STORAGE_UNLOCALIZED_NAME = "imStorage";
    public static final String IM_STORAGE_NAME = "IM Storage";

    public static final String IM_STORAGE_TOP = "im_storage_top";
    public static final String IM_STORAGE_SIDE = "im_storage_side";
    public static final String IM_STORAGE_BOTTOM = "im_storage_bottom";
    
    
    public static boolean isAttuneableBlock(int blockId) {
    	return (blockId == IMPULSION_DRIVE_BASIC_ID ||
    			blockId == IMPULSION_DRIVE_ADVANCED_ID ||
    			blockId == ARCANE_COLLECTOR_ID ||
    			blockId == ARCANE_EXTRACTOR_ID ||
    			blockId == ARCANE_CONDENSER_ID ||
    			blockId == IM_STORAGE_ID);
    }
	
}
