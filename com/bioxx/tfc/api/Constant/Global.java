package com.bioxx.tfc.api.Constant;
import com.bioxx.tfc.api.Metal;
import com.bioxx.tfc.api.TFCOptions;
import com.google.common.collect.ObjectArrays;
import java.util.Arrays;
public class Global
{
  public static final String[] FRUIT_META_NAMES = new String[] { "Red Apple", "Banana", "Orange", "Green Apple", "Lemon", "Olive", "Cherry", "Peach", "Plum" };
  
  public static final String[] FLOWER_META_NAMES = new String[] { "flower_rose", "flower_blue_orchid", "flower_allium", "flower_houstonia", "flower_tulip_red", "flower_tulip_orange", "flower_tulip_white", "flower_tulip_pink", "flower_oxeye_daisy", "flower_dandelion", "flower_nasturtium", "flower_meads_milkweed", "flower_tropical_milkweed", "flower_butterfly_milkweed", "flower_calendula" };
  
  public static final String[] FUNGI_META_NAMES = new String[] { "mushroom_brown", "mushroom_red" };
  
  public static final String[] POWDER = new String[] { "Flux", "Kaolinite Powder", "Graphite Powder", "Sulfur Powder", "Saltpeter Powder", "Hematite Powder", "Lapis Lazuli Powder", "Limonite Powder", "Malachite Powder", "Salt" };
  
  public static final String[] DYE_NAMES = new String[] { "dyeWhite", "dyeOrange", "dyeMagenta", "dyeLightBlue", "dyeYellow", "dyeLime", "dyePink", "dyeGray", "dyeLightGray", "dyeCyan", "dyePurple", "dyeBlue", "dyeBrown", "dyeGreen", "dyeRed", "dyeBlack" };
  
  public static final String[] STONE_IGIN = new String[] { "Granite", "Diorite", "Gabbro" };
  public static final String[] STONE_SED = new String[] { "Shale", "Claystone", "Rock Salt", "Limestone", "Conglomerate", "Dolomite", "Chert", "Chalk" };
  public static final String[] STONE_IGEX = new String[] { "Rhyolite", "Basalt", "Andesite", "Dacite" };
  public static final String[] STONE_MM = new String[] { "Quartzite", "Slate", "Phyllite", "Schist", "Gneiss", "Marble" };
  
  public static final int STONE_IGIN_START = 0;
  
  public static final int STONE_SED_START = 0 + STONE_IGIN.length;
  public static final int STONE_IGEX_START = STONE_SED_START + STONE_SED.length;
  public static final int STONE_MM_START = STONE_IGEX_START + STONE_IGEX.length;
  public static final String[] STONE_ALL = (String[])ObjectArrays.concat(ObjectArrays.concat((Object[])STONE_IGIN, (Object[])STONE_SED, String.class), ObjectArrays.concat((Object[])STONE_IGEX, (Object[])STONE_MM, String.class), String.class);
  
  public static final int[] STONE_FLUXINDEX = new int[] {
	  
      Arrays.asList((T[])STONE_ALL).indexOf("Limestone"), 
      Arrays.asList((T[])STONE_ALL).indexOf("Dolomite"), 
      Arrays.asList((T[])STONE_ALL).indexOf("Chalk"), 
	  Arrays.asList((T[])STONE_ALL).indexOf("Marble")
    };
  
  public static final String[] ORE_METAL = new String[] { "Native Copper", "Native Gold", "Native Platinum", "Hematite", "Native Silver", "Cassiterite", "Galena", "Bismuthinite", "Garnierite", "Malachite", "Magnetite", "Limonite", "Sphalerite", "Tetrahedrite", "Bituminous Coal", "Lignite" };
  public static final String[] ORE_MINERAL = new String[] { "Kaolinite", "Gypsum", "Satinspar", "Selenite", "Graphite", "Kimberlite", "Petrified Wood", "Sulfur", "Jet", "Microcline", "Pitchblende", "Cinnabar", "Cryolite", "Saltpeter", "Serpentine", "Sylvite" };
  public static final String[] ORE_MINERAL2 = new String[] { "Borax", "Olivine", "Lapis Lazuli" };
  public static final String[] WOOD_ALL = new String[] { "Oak", "Aspen", "Birch", "Chestnut", "Douglas Fir", "Hickory", "Maple", "Ash", "Pine", "Sequoia", "Spruce", "Sycamore", "White Cedar", "White Elm", "Willow", "Kapok", "Acacia" };
  
  
  
  public static final String SKILL_GENERAL_SMITHING = "skill.gensmith";  
  public static final String SKILL_TOOLSMITH = "skill.toolsmith";  
  public static final String SKILL_WEAPONSMITH = "skill.weaponsmith";  
  public static final String SKILL_ARMORSMITH = "skill.armorsmith";  
  public static final String SKILL_AGRICULTURE = "skill.agriculture";  
  public static final String SKILL_COOKING = "skill.cooking";  
  public static final String SKILL_PROSPECTING = "skill.prospecting";  
  public static final String SKILL_BUTCHERING = "skill.butchering";
  
  public static Metal BISMUTH;  
  public static Metal BISMUTHBRONZE;  
  public static Metal BLACKBRONZE;
  public static Metal BLACKSTEEL;
  public static Metal BLUESTEEL;
  public static Metal BRASS;
  public static Metal BRONZE;
  public static Metal COPPER;
  public static Metal GOLD;
  public static Metal WROUGHTIRON;
  public static Metal LEAD;
  public static Metal NICKEL;
  public static Metal PIGIRON;
  public static Metal PLATINUM;
  public static Metal REDSTEEL;
  public static Metal ROSEGOLD;
  public static Metal SILVER;
  public static Metal STEEL;
  public static Metal STERLINGSILVER;
  public static Metal TIN;
  public static Metal ZINC;
  public static Metal WEAKSTEEL;
  public static Metal HCBLACKSTEEL;
  public static Metal WEAKREDSTEEL;
  public static Metal HCREDSTEEL;
  public static Metal WEAKBLUESTEEL;
  public static Metal HCBLUESTEEL;
  public static Metal UNKNOWN;
  
  @Deprecated
  public static double foodDecayRate = TFCOptions.foodDecayRate;
  public static final float FOOD_MAX_WEIGHT = 160.0F;
  public static final float FOOD_MIN_DROP_WEIGHT = 0.1F;
  public static final int SEALEVEL = 144;
  public static final int HOT_LIQUID_TEMP = 385;
}