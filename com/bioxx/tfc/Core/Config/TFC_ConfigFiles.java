package com.bioxx.tfc.Core.Config;

import com.bioxx.tfc.Core.Util.CaseInsensitiveHashMap;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.WorldGen.Generators.OreSpawnData;
import com.bioxx.tfc.WorldGen.Generators.WorldGenOre;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Crafting.CraftingManagerTFC;
import com.bioxx.tfc.api.Enums.EnumOreGen;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCCrafting;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFCOptions;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ObjectArrays;
import com.google.common.collect.UnmodifiableIterator;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class TFC_ConfigFiles
{
  public static final String GENERAL = "general";
  public static final String TIME = "time";
  public static final String FOOD_DECAY = "food decay";
  public static final String CAVEIN_OPTIONS = "cave-ins";
  public static final String WORLD_GEN = "world generation";
  public static final String CROPS = "crops";
  public static final String PROTECTION = "spawn protection";
  public static final String PLAYER = "player";
  public static final String MATERIALS = "materials";
  public static final String SERVER = "server";
  public static final String OVERWORKED = "overworked chunks";
  public static final String COLORS = "colors";
  public static final String COLOR_NUTRIENT_A = "color nutrient a";
  public static final String COLOR_NUTRIENT_B = "color nutrient b";
  public static final String COLOR_NUTRIENT_C = "color nutrient c";
  public static final String CROP_FERTILIZER_COLOR = "color fertilizer";
  public static final String ANVIL_RULE_COLOR0 = "color anvil rule 0";
  public static final String ANVIL_RULE_COLOR1 = "color anvil rule 1";
  public static final String ANVIL_RULE_COLOR2 = "color anvil rule 2";
  public static final String CONVERSION = "Conversion";
  public static final String ENABLE_VANILLA_RECIPES = "Enable Vanilla Recipes";
  public static final String CRAFTING_OPTIONS = "Crafting Options";
  private static final String[] COLOR_CATEGORIES = new String[] { "color nutrient a", "color nutrient b", "color nutrient c", "color fertilizer", "color anvil rule 0", "color anvil rule 1", "color anvil rule 2" };


  private static final String[] ALLOWED_TYPES = new String[] { EnumOreGen.Area.name(), EnumOreGen.Vein.name(), EnumOreGen.Lens.name() };

  private static final String[] ALLOWED_BASE_ROCKS = (String[])ObjectArrays.concat((Object[])Global.STONE_ALL, (Object[])new String[] { "igneous intrusive", "igneous extrusive", "sedimentary", "metamorphic" }, String.class);

  public static final Map<String, SyncingOption> SYNCING_OPTION_MAP = (Map<String, SyncingOption>)new CaseInsensitiveHashMap();

  private static Configuration generalConfig;

  private static Configuration craftingConfig;
  private static Configuration oresConfig;

  public static Configuration getGeneralConfig() {
    return generalConfig;
  }


  public static Configuration getCraftingConfig() {
    return craftingConfig;
  }


  public static Configuration getOresConfig() {
    return oresConfig;
  }





  public static void preInit(File configFolder) {
    if (generalConfig != null) throw new IllegalStateException("Preinit can't be called twice.");

    File generalConfigFile = new File(configFolder, "TFCConfig.cfg");
    if (!generalConfigFile.exists()) {

      File oldConfigFile = new File(generalConfigFile, "cfg");
      if (oldConfigFile.exists() && !oldConfigFile.isDirectory())
      {

        oldConfigFile.delete();
      }
    }
    generalConfig = new TFC_Configuration(generalConfigFile);
    craftingConfig = new Configuration(new File(configFolder, "TFCCrafting.cfg"));
    oresConfig = new Configuration(new File(configFolder, "TFCOre.cfg"));
  }






  public static void reloadAll() {
    reloadGeneral();
    reloadOres();
    TerraFirmaCraft.LOG.info("Reloading TFCCrafting");

    try {
      for (SyncingOption option : SYNCING_OPTION_MAP.values())
      {
        option.loadFromConfig();
      }
    }
    catch (IllegalAccessException e) {

      TerraFirmaCraft.LOG.fatal("Fatal error reloading TFCCrafting", e);
      Throwables.propagate(e);
    }
    if (craftingConfig.hasChanged()) craftingConfig.save();

  }

  public static void firstLoadCrafting() {
    if (craftingConfig == null) throw new IllegalStateException("Config reload attempt before preinit.");
    TerraFirmaCraft.LOG.info("Loading TFCCrafting");

    if (craftingConfig.hasCategory("nei hiding"))
    {
      craftingConfig.removeCategory(craftingConfig.getCategory("nei hiding"));
    }

    craftingConfig.setCategoryLanguageKey("Conversion", "config.gui.TFCCrafting.conversion");
    craftingConfig.setCategoryLanguageKey("Enable Vanilla Recipes", "config.gui.TFCCrafting.vanilla");
    craftingConfig.setCategoryLanguageKey("Crafting Options", "config.gui.TFCCrafting.options");


    try {
      new ConversionOption("appleConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack(Items.field_151034_e, 1), new Object[] { new ItemStack(TFCItems.redApple, 1) }) });
      new ConversionOption("arrowConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack(Items.field_151032_g, 1), new Object[] { new ItemStack(TFCItems.arrow, 1)
              }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.arrow, 1), new Object[] { new ItemStack(Items.field_151032_g, 1) }) });
      new ConversionOption("bowConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack((Item)Items.field_151031_f, 1), new Object[] { new ItemStack(TFCItems.bow, 1, 0)
              }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.bow, 1, 0), new Object[] { new ItemStack((Item)Items.field_151031_f, 1) }) });
      new ConversionOption("coalConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack(Items.field_151044_h, 1), new Object[] { new ItemStack(TFCItems.coal, 1)
              }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.coal, 1), new Object[] { new ItemStack(Items.field_151044_h, 1) }) });
      new ConversionOption("diamondConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack(Items.field_151045_i, 1), new Object[] { new ItemStack(TFCItems.gemDiamond, 1, 2)
              }), (IRecipe)getAsShapeless(new ItemStack(Items.field_151045_i, 2), new Object[] { new ItemStack(TFCItems.gemDiamond, 1, 3)
              }), (IRecipe)getAsShapeless(new ItemStack(Items.field_151045_i, 3), new Object[] { new ItemStack(TFCItems.gemDiamond, 1, 4)
              }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.gemDiamond, 1, 2), new Object[] { new ItemStack(Items.field_151045_i)
              }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.gemDiamond, 1, 3), new Object[] { new ItemStack(Items.field_151045_i), new ItemStack(Items.field_151045_i)
              }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.gemDiamond, 1, 4), new Object[] { new ItemStack(Items.field_151045_i), new ItemStack(Items.field_151045_i), new ItemStack(Items.field_151045_i) }) });
      new ConversionOption("emeraldConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack(Items.field_151166_bC, 1), new Object[] { new ItemStack(TFCItems.gemEmerald, 1, 2)
              }), (IRecipe)getAsShapeless(new ItemStack(Items.field_151166_bC, 2), new Object[] { new ItemStack(TFCItems.gemEmerald, 1, 3)
              }), (IRecipe)getAsShapeless(new ItemStack(Items.field_151166_bC, 3), new Object[] { new ItemStack(TFCItems.gemEmerald, 1, 4)
              }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.gemEmerald, 1, 2), new Object[] { new ItemStack(Items.field_151166_bC)
              }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.gemEmerald, 1, 3), new Object[] { new ItemStack(Items.field_151166_bC), new ItemStack(Items.field_151166_bC)
              }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.gemEmerald, 1, 4), new Object[] { new ItemStack(Items.field_151166_bC), new ItemStack(Items.field_151166_bC), new ItemStack(Items.field_151166_bC) }) });
      new ConversionOption("fishConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack(Items.field_151115_aP, 1), new Object[] { new ItemStack(TFCItems.fishRaw, 1) }) });
      new ConversionOption("fishingRodConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack((Item)Items.field_151112_aM, 1), new Object[] { new ItemStack(TFCItems.fishingRod, 1, 0)
              }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.fishingRod, 1, 0), new Object[] { new ItemStack((Item)Items.field_151112_aM, 1) }) });
      new ConversionOption("flintSteelConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack(Items.field_151033_d, 1, 0), new Object[] { new ItemStack(TFCItems.flintSteel, 1, 0)
              }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.flintSteel, 1, 0), new Object[] { new ItemStack(Items.field_151033_d, 1, 0) }) });
      new ConversionOption("leatherArmorConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack((Item)Items.field_151024_Q, 1, 0), new Object[] { new ItemStack(TFCItems.leatherHelmet, 1, 0)
              }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.leatherHelmet, 1, 0), new Object[] { new ItemStack((Item)Items.field_151024_Q, 1, 0)
              }), (IRecipe)getAsShapeless(new ItemStack((Item)Items.field_151027_R, 1, 0), new Object[] { new ItemStack(TFCItems.leatherChestplate, 1, 0)
              }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.leatherChestplate, 1, 0), new Object[] { new ItemStack((Item)Items.field_151027_R, 1, 0)
              }), (IRecipe)getAsShapeless(new ItemStack((Item)Items.field_151026_S, 1, 0), new Object[] { new ItemStack(TFCItems.leatherLeggings, 1, 0)
              }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.leatherLeggings, 1, 0), new Object[] { new ItemStack((Item)Items.field_151026_S, 1, 0)
              }), (IRecipe)getAsShapeless(new ItemStack((Item)Items.field_151021_T, 1, 0), new Object[] { new ItemStack(TFCItems.leatherBoots, 1, 0)
              }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.leatherBoots, 1, 0), new Object[] { new ItemStack((Item)Items.field_151021_T, 1, 0) }) });
      new ConversionOption("leatherConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack(Items.field_151116_aA, 1), new Object[] { new ItemStack(TFCItems.leather, 1)
              }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.leather, 1), new Object[] { new ItemStack(Items.field_151116_aA, 1) }) });
      new ConversionOption("stoneAxeConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack(Items.field_151049_t, 1, 0), new Object[] { TFCItems.igInAxe
              }), (IRecipe)getAsShapeless(new ItemStack(Items.field_151049_t, 1, 0), new Object[] { TFCItems.igExAxe
              }), (IRecipe)getAsShapeless(new ItemStack(Items.field_151049_t, 1, 0), new Object[] { TFCItems.sedAxe
              }), (IRecipe)getAsShapeless(new ItemStack(Items.field_151049_t, 1, 0), new Object[] { TFCItems.mMAxe
              }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.igExAxe, 1, 0), new Object[] { Items.field_151049_t }) });
      new ConversionOption("stoneHoeConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack(Items.field_151018_J, 1, 0), new Object[] { TFCItems.igInHoe
              }), (IRecipe)getAsShapeless(new ItemStack(Items.field_151018_J, 1, 0), new Object[] { TFCItems.igExHoe
              }), (IRecipe)getAsShapeless(new ItemStack(Items.field_151018_J, 1, 0), new Object[] { TFCItems.sedHoe
              }), (IRecipe)getAsShapeless(new ItemStack(Items.field_151018_J, 1, 0), new Object[] { TFCItems.mMHoe
              }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.igExHoe, 1, 0), new Object[] { Items.field_151018_J }) });
      new ConversionOption("stoneShovelConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack(Items.field_151051_r, 1, 0), new Object[] { TFCItems.igInShovel
              }), (IRecipe)getAsShapeless(new ItemStack(Items.field_151051_r, 1, 0), new Object[] { TFCItems.igExShovel
              }), (IRecipe)getAsShapeless(new ItemStack(Items.field_151051_r, 1, 0), new Object[] { TFCItems.sedShovel
              }), (IRecipe)getAsShapeless(new ItemStack(Items.field_151051_r, 1, 0), new Object[] { TFCItems.mMShovel
              }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.igExShovel, 1, 0), new Object[] { Items.field_151051_r }) });
      new ConversionOption("woodButtonConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack(Blocks.field_150471_bO, 1), new Object[] { new ItemStack(TFCBlocks.buttonWood, 1)
              }), (IRecipe)getAsShapeless(new ItemStack(TFCBlocks.buttonWood, 1), new Object[] { new ItemStack(Blocks.field_150471_bO, 1) }) });
      new ConversionOption("workbenchConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack(Blocks.field_150462_ai, 1), new Object[] { new ItemStack(TFCBlocks.workbench, 1)
              }), (IRecipe)getAsShapeless(new ItemStack(TFCBlocks.workbench, 1), new Object[] { new ItemStack(Blocks.field_150462_ai, 1) }) });

      new VanillaRecipeOption("anvilRecipe", new ItemStack[] { new ItemStack(Blocks.field_150467_bQ) });
      new VanillaRecipeOption("arrowsRecipe", new ItemStack[] { new ItemStack(Items.field_151032_g, 4) });
      new VanillaRecipeOption("bedRecipe", new ItemStack[] { new ItemStack(Items.field_151104_aV) });
      new VanillaRecipeOption("bonemealRecipe", new ItemStack[] { new ItemStack(Items.field_151100_aR, 3, 15) });
      new VanillaRecipeOption("bowlRecipe", new ItemStack[] { new ItemStack(Items.field_151054_z, 4) });
      new VanillaRecipeOption("brewingRecipe", new ItemStack[] { new ItemStack(Items.field_151067_bt) });
      new VanillaRecipeOption("bucketRecipe", new ItemStack[] { new ItemStack(Items.field_151133_ar) });
      new VanillaRecipeOption("cauldronRecipe", new ItemStack[] { new ItemStack(Items.field_151066_bu) });
      new VanillaRecipeOption("chestRecipe", new ItemStack[] { new ItemStack((Block)Blocks.field_150486_ae) });
      new VanillaRecipeOption("clockRecipe", new ItemStack[] { new ItemStack(Items.field_151113_aN) });
      new VanillaRecipeOption("compassRecipe", new ItemStack[] { new ItemStack(Items.field_151111_aL) });
      new VanillaRecipeOption("dandelionYellowRecipe", new ItemStack[] { new ItemStack(Items.field_151100_aR, 1, 11), new ItemStack(Items.field_151100_aR, 2, 11) });
      new VanillaRecipeOption("diamondArmorRecipe", new ItemStack[] { new ItemStack((Item)Items.field_151161_ac), new ItemStack((Item)Items.field_151163_ad), new ItemStack((Item)Items.field_151173_ae), new ItemStack((Item)Items.field_151175_af) });
      new VanillaRecipeOption("diamondBlockRecipe", new ItemStack[] { new ItemStack(Blocks.field_150484_ah) });
      new VanillaRecipeOption("diamondToolsRecipe", new ItemStack[] { new ItemStack(Items.field_151046_w), new ItemStack(Items.field_151056_x), new ItemStack(Items.field_151047_v), new ItemStack(Items.field_151012_L), new ItemStack(Items.field_151048_u) });
      new VanillaRecipeOption("dispenserRecipe", new ItemStack[] { new ItemStack(Blocks.field_150367_z) });
      new VanillaRecipeOption("dropperRecipe", new ItemStack[] { new ItemStack(Blocks.field_150409_cd) });
      new VanillaRecipeOption("enchantTableRecipe", new ItemStack[] { new ItemStack(Blocks.field_150381_bn) });
      new VanillaRecipeOption("fenceGateRecipe", new ItemStack[] { new ItemStack(Blocks.field_150396_be) });
      new VanillaRecipeOption("fenceRecipe", new ItemStack[] { new ItemStack(Blocks.field_150422_aJ, 2) });
      new VanillaRecipeOption("furnaceRecipe", new ItemStack[] { new ItemStack(Blocks.field_150460_al) });
      new VanillaRecipeOption("goldAppleRecipe", new ItemStack[] { new ItemStack(Items.field_151153_ao) });
      new VanillaRecipeOption("goldArmorRecipe", new ItemStack[] { new ItemStack((Item)Items.field_151169_ag), new ItemStack((Item)Items.field_151171_ah), new ItemStack((Item)Items.field_151149_ai), new ItemStack((Item)Items.field_151151_aj) });
      new VanillaRecipeOption("goldBlockRecipe", new ItemStack[] { new ItemStack(Blocks.field_150340_R) });
      new VanillaRecipeOption("goldNuggetRecipe", new ItemStack[] { new ItemStack(Items.field_151074_bl, 9) });
      new VanillaRecipeOption("goldPlateRecipe", new ItemStack[] { new ItemStack(Blocks.field_150445_bS) });
      new VanillaRecipeOption("goldToolsRecipe", new ItemStack[] { new ItemStack(Items.field_151005_D), new ItemStack(Items.field_151006_E), new ItemStack(Items.field_151011_C), new ItemStack(Items.field_151013_M), new ItemStack(Items.field_151010_B) });
      new VanillaRecipeOption("hopperRecipe", new ItemStack[] { new ItemStack((Block)Blocks.field_150438_bZ) });
      new VanillaRecipeOption("ironArmorRecipe", new ItemStack[] { new ItemStack((Item)Items.field_151028_Y), new ItemStack((Item)Items.field_151030_Z), new ItemStack((Item)Items.field_151165_aa), new ItemStack((Item)Items.field_151167_ab) });
      new VanillaRecipeOption("ironBarsRecipe", new ItemStack[] { new ItemStack(Blocks.field_150411_aY, 16) });
      new VanillaRecipeOption("ironBlockRecipe", new ItemStack[] { new ItemStack(Blocks.field_150339_S) });
      new VanillaRecipeOption("ironDoorRecipe", new ItemStack[] { new ItemStack(Items.field_151139_aw) });
      new VanillaRecipeOption("ironPlateRecipe", new ItemStack[] { new ItemStack(Blocks.field_150443_bT) });
      new VanillaRecipeOption("ironToolsRecipe", new ItemStack[] { new ItemStack(Items.field_151035_b), new ItemStack(Items.field_151036_c), new ItemStack(Items.field_151037_a), new ItemStack(Items.field_151019_K), new ItemStack(Items.field_151040_l) });
      new VanillaRecipeOption("jukeboxRecipe", new ItemStack[] { new ItemStack(Blocks.field_150421_aI) });
      new VanillaRecipeOption("leatherArmorRecipe", new ItemStack[] { new ItemStack((Item)Items.field_151024_Q), new ItemStack((Item)Items.field_151027_R), new ItemStack((Item)Items.field_151026_S), new ItemStack((Item)Items.field_151021_T) });
      new VanillaRecipeOption("leverRecipe", new ItemStack[] { new ItemStack(Blocks.field_150442_at) });
      new VanillaRecipeOption("minecartChestRecipe", new ItemStack[] { new ItemStack(Items.field_151108_aI) });
      new VanillaRecipeOption("minecartRecipe", new ItemStack[] { new ItemStack(Items.field_151143_au) });
      new VanillaRecipeOption("pistonRecipe", new ItemStack[] { new ItemStack((Block)Blocks.field_150331_J) });
      new VanillaRecipeOption("plankBlockRecipe", new ItemStack[] { new ItemStack(Blocks.field_150344_f, 4, 0), new ItemStack(Blocks.field_150344_f, 4, 1), new ItemStack(Blocks.field_150344_f, 4, 2), new ItemStack(Blocks.field_150344_f, 4, 3), new ItemStack(Blocks.field_150344_f, 4, 4), new ItemStack(Blocks.field_150344_f, 4, 5) });
      new VanillaRecipeOption("poweredRailsRecipe", new ItemStack[] { new ItemStack(Blocks.field_150318_D, 6) });
      new VanillaRecipeOption("railsRecipe", new ItemStack[] { new ItemStack(Blocks.field_150448_aq, 16) });
      new VanillaRecipeOption("repeaterRecipe", new ItemStack[] { new ItemStack(Items.field_151107_aW) });
      new VanillaRecipeOption("roseRedRecipe", new ItemStack[] { new ItemStack(Items.field_151100_aR, 1, 1), new ItemStack(Items.field_151100_aR, 2, 1) });
      new VanillaRecipeOption("shearsRecipe", new ItemStack[] { new ItemStack((Item)Items.field_151097_aZ) });
      new VanillaRecipeOption("signRecipe", new ItemStack[] { new ItemStack(Items.field_151155_ap, 3) });
      new VanillaRecipeOption("stickRecipe", new ItemStack[] { new ItemStack(Items.field_151055_y, 4) });
      new VanillaRecipeOption("stoneSlabsRecipe", new ItemStack[] { new ItemStack((Block)Blocks.field_150333_U, 6), new ItemStack((Block)Blocks.field_150333_U, 6, 3) });
      new VanillaRecipeOption("stoneStairsRecipe", new ItemStack[] { new ItemStack(Blocks.field_150446_ar, 4) });
      new VanillaRecipeOption("stoneToolsRecipe", new ItemStack[] { new ItemStack(Items.field_151050_s), new ItemStack(Items.field_151049_t), new ItemStack(Items.field_151051_r), new ItemStack(Items.field_151018_J), new ItemStack(Items.field_151052_q) });
      new VanillaRecipeOption("torchRecipe", new ItemStack[] { new ItemStack(Blocks.field_150478_aa, 4) });
      new VanillaRecipeOption("trapDoorRecipe", new ItemStack[] { new ItemStack(Blocks.field_150415_aT, 2) });
      new VanillaRecipeOption("tripwireRecipe", new ItemStack[] { new ItemStack((Block)Blocks.field_150479_bC, 2) });
      new VanillaRecipeOption("woodDoorRecipe", new ItemStack[] { new ItemStack(Items.field_151135_aq) });
      new VanillaRecipeOption("woodSlabsRecipe", new ItemStack[] { new ItemStack((Block)Blocks.field_150376_bx, 6, 0), new ItemStack((Block)Blocks.field_150376_bx, 6, 1), new ItemStack((Block)Blocks.field_150376_bx, 6, 2), new ItemStack((Block)Blocks.field_150376_bx, 6, 3), new ItemStack((Block)Blocks.field_150376_bx, 6, 4), new ItemStack((Block)Blocks.field_150376_bx, 6, 5) });
      new VanillaRecipeOption("woodStairsRecipe", new ItemStack[] { new ItemStack(Blocks.field_150487_bG, 4), new ItemStack(Blocks.field_150481_bH, 4), new ItemStack(Blocks.field_150476_ad, 4), new ItemStack(Blocks.field_150485_bF, 4), new ItemStack(Blocks.field_150400_ck, 4), new ItemStack(Blocks.field_150401_cl, 4) });
      new VanillaRecipeOption("woodToolsRecipe", new ItemStack[] { new ItemStack(Items.field_151039_o), new ItemStack(Items.field_151053_p), new ItemStack(Items.field_151038_n), new ItemStack(Items.field_151017_I), new ItemStack(Items.field_151041_m) });
      new VanillaRecipeOption("woolRecipe", new ItemStack[] { new ItemStack(Blocks.field_150325_L) });





      new SyncingOption("enableBowlsAlwaysBreak", TFCCrafting.class, craftingConfig, "Crafting Options")
        {
          private IRecipe recipesTFC = (IRecipe)CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.potteryBowl, 2), new Object[] { "#####", "#####", "#####", " ### ", "#   #",




                Character.valueOf('#'), new ItemStack(TFCItems.flatClay, 1, 1) });



          public void apply(boolean enabled) throws IllegalAccessException {
            if (this.currentValue != enabled) {

              (this.recipesTFC.func_77571_b()).field_77994_a = enabled ? 4 : 2;
              if (TFCOptions.enableDebugMode)
                TerraFirmaCraft.LOG.info("Crafting option {} changed from {} to {}. Stacksize {}", new Object[] { this.name, Boolean.valueOf(this.currentValue), Boolean.valueOf(enabled), Integer.valueOf((this.recipesTFC.func_77571_b()).field_77994_a) });
              this.field.setBoolean(null, enabled);
              this.currentValue = enabled;
            }
          }



          public ImmutableList<IRecipe> getRecipes() {
            return ImmutableList.of(this.recipesTFC);
          }



          public String toString() {
            return this.name + "[default:" + this.defaultValue + " current:" + isAplied() + " config:" + inConfig() + " #ofRecipes: 1]";
          }
        };

      for (SyncingOption option : SYNCING_OPTION_MAP.values())
      {
        option.loadFromConfig();
      }
    }
    catch (NoSuchFieldException e) {

      TerraFirmaCraft.LOG.fatal("Fatal error loading TFCCrafting", e);
      Throwables.propagate(e);
    }
    catch (IllegalAccessException e) {

      TerraFirmaCraft.LOG.fatal("Fatal error loading TFCCrafting ", e);
      Throwables.propagate(e);
    }

    if (craftingConfig.hasChanged()) craftingConfig.save();

  }

  private static ShapelessRecipes getAsShapeless(ItemStack out, Object... in) {
    for (int i = 0; i < in.length; i++) {

      if (!(in[i] instanceof ItemStack))
        if (in[i] instanceof Item) { in[i] = new ItemStack((Item)in[i]); }
        else if (in[i] instanceof Block) { in[i] = new ItemStack((Block)in[i]); }
        else { throw new IllegalArgumentException("Type of " + in[i] + " (arg #" + i + ") not Itemstack, Item or Block."); }

    }  return new ShapelessRecipes(out, Arrays.asList(in));
  }






  public static void reloadGeneral() {
    if (generalConfig == null) throw new IllegalStateException("Config reload attempt before preinit.");
    TerraFirmaCraft.LOG.info("Loading TFCConfig");

    generalConfig.setCategoryLanguageKey("general", "config.gui.TFCConfig.general");

    if (craftingConfig.hasCategory("nei hiding")) generalConfig.get("general", "enableNEIHiding", TFCOptions.enableNEIHiding).set(craftingConfig.getBoolean("enableNEIHiding", "nei hiding", TFCOptions.enableNEIHiding, ""));

    TFCOptions.enableNEIHiding = generalConfig.getBoolean("enableNEIHiding", "general", TFCOptions.enableNEIHiding, "Set to false to show hidden items in NEI. Note that most of these items were hidden to prevent players from cheating them in and crashing their game.");
    TFCOptions.enablePowderKegs = generalConfig.getBoolean("enablePowderKegs", "general", TFCOptions.enablePowderKegs, "Set this to false to disable powder keg explosions.", "config.gui.TFCConfig.general.enablePowderKegs");
    TFCOptions.enableBetterGrass = generalConfig.getBoolean("enableBetterGrass", "general", TFCOptions.enableBetterGrass, "If true, then the side of a grass block which has another grass block adjacent and one block lower than it will show as completely grass.", "config.gui.TFCConfig.general.enableBetterGrass");
    TFCOptions.enableSaplingDrops = generalConfig.getBoolean("enableSaplingDrops", "general", TFCOptions.enableSaplingDrops, "Set this to false to disable saplings dropping from harvested leaf blocks.", "config.gui.TFCConfig.general.enableSaplingDrops");
    TFCOptions.enableSeedDrops = generalConfig.getBoolean("enableSeedDrops", "general", TFCOptions.enableSeedDrops, "Set this to false to disable seeds being placed on the ground when a crop on farmland dies from natural causes such as freezing temperatures, old age, or not enough sunlight.", "config.gui.TFCConfig.general.enableSeedDrops");
    TFCOptions.enableDebugMode = generalConfig.getBoolean("enableDebugMode", "general", TFCOptions.enableDebugMode, "Set this to true if you want to turn on debug mode which is useful for bug hunting.", "config.gui.TFCConfig.general.enableDebugMode");
    TFCOptions.enableFiniteWater = generalConfig.getBoolean("enableFiniteWater", "general", TFCOptions.enableFiniteWater, "Set this to true to enable finite water. Two adjacent source water blocks will not create a third.", "config.gui.TFCConfig.general.enableFiniteWater");
    TFCOptions.onionsAreGross = generalConfig.getBoolean("onionsAreGross", "general", TFCOptions.onionsAreGross, "Set this to true if you don't like onions.", "config.gui.TFCConfig.general.onionsAreGross");
    TFCOptions.quiverHUDPosition = generalConfig.getString("quiverHUDPosition", "general", TFCOptions.quiverHUDPosition, "Valid position strings are: bottomleft, left, topleft, bottomright, right, topright", new String[] { "bottomleft", "left", "topleft", "bottomright", "right", "topright" }, "config.gui.TFCConfig.general.quiverHUDPosition");
    TFCOptions.enableSolidDetailed = generalConfig.getBoolean("enableSolidDetailed", "general", TFCOptions.enableSolidDetailed, "Should sides of detailed blocks be considered solid?", "config.gui.TFCConfig.general.enableSolidDetailed");
    TFCOptions.maxRemovedSolidDetailed = generalConfig.getInt("maxRemovedSolidDetailed", "general", TFCOptions.maxRemovedSolidDetailed, 0, 64, "Maximum count of removed sub-blocks on one side for the detailed block side to still be solid.", "config.gui.TFCConfig.general.maxRemovedSolidDetailed");
    TFCOptions.enableToolModeIndicator = generalConfig.getBoolean("enableToolModeIndicator", "general", TFCOptions.enableToolModeIndicator, "Set to false to hide the tool mode indicator that is displayed next to the hotbar for tools such as chisels and hoes.", "config.gui.TFCConfig.general.enableToolModeIndicator");

    generalConfig.setCategoryLanguageKey("time", "config.gui.TFCConfig.time");

    TFCOptions.yearLength = generalConfig.getInt("yearLength", "time", TFCOptions.yearLength, 96, 12000, "This is how many days are in a year. Keep this to multiples of 12.", "config.gui.TFCConfig.time.yearLength");
    if (TFCOptions.yearLength % 12 != 0) {

      Property prop = generalConfig.get("time", "yearLength", 96);
      TerraFirmaCraft.LOG.warn("Invalid yearLength in the config file. Changing to the next multiple of 12.");
      TFCOptions.yearLength = 12 + 12 * TFCOptions.yearLength / 12;
      prop.set(TFCOptions.yearLength);
    }
    TFCOptions.pitKilnBurnTime = generalConfig.getFloat("pitKilnBurnTime", "time", TFCOptions.pitKilnBurnTime, 1.0F, 2304.0F, "This is the number of hours that the pit kiln should burn before being completed.", "config.gui.TFCConfig.time.pitKilnBurnTime");
    TFCOptions.bloomeryBurnTime = generalConfig.getFloat("bloomeryBurnTime", "time", TFCOptions.bloomeryBurnTime, 1.0F, 2304.0F, "This is the number of hours that the bloomery should burn before being completed.", "config.gui.TFCConfig.time.bloomeryBurnTime");
    TFCOptions.charcoalPitBurnTime = generalConfig.getFloat("charcoalPitBurnTime", "time", TFCOptions.charcoalPitBurnTime, 1.0F, 2304.0F, "This is the number of hours that the charcoal pit should burn before being completed.", "config.gui.TFCConfig.time.charcoalPitBurnTime");
    TFCOptions.torchBurnTime = generalConfig.getInt("torchBurnTime", "time", TFCOptions.torchBurnTime, 0, 2304, "This is how many in-game hours torches will last before burning out. Set to 0 for infinitely burning torches.", "config.gui.TFCConfig.time.torchBurnTime");
    TFCOptions.saplingTimerMultiplier = generalConfig.getFloat("saplingTimerMultiplier", "time", TFCOptions.saplingTimerMultiplier, 0.01F, 100.0F, "This is a global multiplier for the number of days required before a sapling can grow into a tree. Decrease for faster sapling growth.", "config.gui.TFCConfig.time.saplingTimerMultiplier");
    TFCOptions.tempIncreaseMultiplier = generalConfig.getFloat("tempIncreaseMultiplier", "time", TFCOptions.tempIncreaseMultiplier, 0.01F, 100.0F, "This is a global multiplier for the rate at which items heat up. Increase to make items heat up faster.", "config.gui.TFCConfig.time.tempIncreaseMultiplier");
    TFCOptions.tempDecreaseMultiplier = generalConfig.getFloat("tempDecreaseMultiplier", "time", TFCOptions.tempDecreaseMultiplier, 0.01F, 100.0F, "This is a global multiplier for the rate at which items cool down. Increase to make items cool down faster.", "config.gui.TFCConfig.time.tempDecreaseMultiplier");
    TFCOptions.oilLampFuelMult = generalConfig.getInt("oilLampFuelMult", "time", TFCOptions.oilLampFuelMult, 1, 50, "This determines how much fuel is used over time from oil lamps. Setting this higher will make fuel last longer. A mult of 1 = 250 hours, 4 = 1000 hours, 8 = 2000 hours.", "config.gui.TFCConfig.time.oilLampFuelMult");
    TFCOptions.animalTimeMultiplier = generalConfig.getFloat("animalTimeMultiplier", "time", TFCOptions.animalTimeMultiplier, 0.01F, 100.0F, "This is a global multiplier for the gestation period of animals, as well as how long it takes for them to reach adulthood. Decrease for faster times.", "config.gui.TFCConfig.time.animalTimeMultiplier");

    generalConfig.setCategoryLanguageKey("food decay", "config.gui.TFCConfig.fooddecay");

    TFCOptions.foodDecayRate = generalConfig.getFloat("foodDecayRate", "food decay", TFCOptions.foodDecayRate, 1.0F, 2.0F, "This number causes base decay to equal 50% gain per day. If you wish to change, I recommend you look up a y-root calculator 1.0170378966055869517978300569768^24 = 1.5", "config.gui.TFCConfig.fooddecay.foodDecayRate");
    TFCOptions.useDecayProtection = generalConfig.getBoolean("useDecayProtection", "food decay", TFCOptions.useDecayProtection, "Set this to false if you want food to auto decay when a chunk is loaded instead of limiting decay when a chunk has been unloaded for a long period.", "config.gui.TFCConfig.fooddecay.useDecayProtection");
    TFCOptions.decayProtectionDays = generalConfig.getInt("decayProtectionDays", "food decay", TFCOptions.decayProtectionDays, 1, 12000, "If a food item has not been ticked for >= this number of days than when it is ticked for the first time, only a small amount of decay will occur.", "config.gui.TFCConfig.fooddecay.decayProtectionDays");
    TFCOptions.decayMultiplier = generalConfig.getFloat("foodDecayMultiplier", "food decay", TFCOptions.decayMultiplier, 0.0F, 100.0F, "This is a global multiplier for food decay. Unlike FoodDecayRate which only modifies the base decay and not the environmental effect upon decay, this multiplier will multiply against the entire amount. Set to 0 to turn decay off.", "config.gui.TFCConfig.fooddecay.foodDecayMultiplier");

    generalConfig.setCategoryLanguageKey("cave-ins", "config.gui.TFCConfig.caveins");

    TFCOptions.minimumRockLoad = generalConfig.getInt("minimumRockLoad", "cave-ins", TFCOptions.minimumRockLoad, 0, 256, "This is the minimum number of solid blocks that must be over a section in order for it to collapse.", "config.gui.TFCConfig.caveins.minimumRockLoad");
    TFCOptions.initialCollapseRatio = generalConfig.getInt("initialCollapseRatio", "cave-ins", TFCOptions.initialCollapseRatio, 1, 1000, "This number is a 1 in X chance that when you mine a block, a collapse will occur.", "config.gui.TFCConfig.caveins.initialCollapseRatio");
    TFCOptions.propogateCollapseChance = generalConfig.getInt("propogateCollapseChance", "cave-ins", TFCOptions.propogateCollapseChance, 1, 100, "This number is the likelihood for each block to propagate the collapse farther.", "config.gui.TFCConfig.caveins.propogateCollapseChance");
    TFCOptions.enableCaveIns = generalConfig.getBoolean("enableCaveIns", "cave-ins", TFCOptions.enableCaveIns, "Set this to false to disable cave-ins.", "config.gui.TFCConfig.caveins.enableCaveIns");
    TFCOptions.enableCaveInsDestroyOre = generalConfig.getBoolean("enableCaveInsDestroyOre", "cave-ins", TFCOptions.enableCaveInsDestroyOre, "Set this to false to make cave-ins drop the ore item instead of destroy it.", "config.gui.TFCConfig.caveins.enableCaveInsDestroyOre");

    generalConfig.setCategoryLanguageKey("world generation", "config.gui.TFCConfig.worldgen");

    TFCOptions.ravineRarity = generalConfig.getInt("ravineRarity", "world generation", TFCOptions.ravineRarity, 0, 1000, "Controls the chance of a ravine generating, smaller value is higher chance, more ravines. Set to 0 to disable ravines.", "config.gui.TFCConfig.worldgen.ravineRarity");
    TFCOptions.lavaFissureRarity = generalConfig.getInt("lavaFissureRarity", "world generation", TFCOptions.lavaFissureRarity, 0, 1000, "Controls the chance of a lava fissure generating, smaller value is higher chance, more fissures. Set to 0 to disable lava fissures.", "config.gui.TFCConfig.worldgen.lavaFissureRarity");
    TFCOptions.waterFissureRarity = generalConfig.getInt("waterFissureRarity", "world generation", TFCOptions.waterFissureRarity, 0, 1000, "Controls the chance of a water fissure generating, smaller value is higher chance, more fissures. Set to 0 to disable water fissures.", "config.gui.TFCConfig.worldgen.waterFissureRarity");

    generalConfig.setCategoryLanguageKey("crops", "config.gui.TFCConfig.crops");

    TFCOptions.enableCropsDie = generalConfig.getBoolean("enableCropsDie", "crops", TFCOptions.enableCropsDie, "Set to true to enable crop death from old age.", "config.gui.TFCConfig.crops.enableCropsDie");
    TFCOptions.cropGrowthMultiplier = generalConfig.getFloat("cropGrowthModifier", "crops", TFCOptions.cropGrowthMultiplier, 0.01F, 100.0F, "This is a global multiplier for the rate at which crops will grow. Increase to make crops grow faster.", "config.gui.TFCConfig.crops.cropGrowthModifier");

    generalConfig.setCategoryLanguageKey("spawn protection", "config.gui.TFCConfig.protection");

    TFCOptions.maxProtectionMonths = generalConfig.getInt("maxProtectionMonths", "spawn protection", TFCOptions.maxProtectionMonths, 0, 120, "The maximum number of months of spawn protection that can accumulate.", "config.gui.TFCConfig.protection.maxProtectionMonths");
    TFCOptions.protectionGain = generalConfig.getInt("protectionGain", "spawn protection", TFCOptions.protectionGain, 0, 24, "The number of hours of protection gained in the 5x5 chunk area for spending 1 hour in that chunk.", "config.gui.TFCConfig.protection.protectionGain");
    TFCOptions.protectionBuffer = generalConfig.getInt("protectionBuffer", "spawn protection", TFCOptions.protectionBuffer, 0, 2304, "The minimum number of hours of protection that must be accumulated in a chunk in order to bypass the buffer and prevent hostile mob spawning.", "config.gui.TFCConfig.protection.protectionBuffer");

    generalConfig.setCategoryLanguageKey("player", "config.gui.TFCConfig.player");

    TFCOptions.healthGainRate = generalConfig.getInt("healthGainRate", "player", TFCOptions.healthGainRate, 0, 100, "The rate of Health Gain per experience level. Set to 0 to turn off.", "config.gui.TFCConfig.player.healthGainRate");
    TFCOptions.healthGainCap = generalConfig.getInt("healthGainCap", "player", TFCOptions.healthGainCap, 1000, 50000, "The maximum achievable health pool total.", "config.gui.TFCConfig.player.healthGainCap");

    generalConfig.setCategoryLanguageKey("materials", "config.gui.TFCConfig.materials");

    TFCOptions.smallOreUnits = generalConfig.getInt("smallOreUnits", "materials", TFCOptions.smallOreUnits, 1, 100, "The metal units provided by a single piece of small ore or nugget.", "config.gui.TFCConfig.materials.smallOreUnits");
    TFCOptions.poorOreUnits = generalConfig.getInt("poorOreUnits", "materials", TFCOptions.poorOreUnits, 1, 150, "The metal units provided by a single piece of poor ore.", "config.gui.TFCConfig.materials.poorOreUnits");
    TFCOptions.normalOreUnits = generalConfig.getInt("normalOreUnits", "materials", TFCOptions.normalOreUnits, 1, 250, "The metal units provided by a single piece of normal ore.", "config.gui.TFCConfig.materials.normalOreUnits");
    TFCOptions.richOreUnits = generalConfig.getInt("richOreUnits", "materials", TFCOptions.richOreUnits, 1, 350, "The metal units provided by a single piece of rich ore", "config.gui.TFCConfig.materials.richOreUnits");

    generalConfig.setCategoryLanguageKey("server", "config.gui.TFCConfig.server");

    TFCOptions.simSpeedNoPlayers = generalConfig.getInt("simSpeedNoPlayers", "server", TFCOptions.simSpeedNoPlayers, 0, 2147483647, "For every X number of ticks provided here, when there are no players online the server will only progress by 1 tick. Time advances 100 times slower than normal. Setting this to 0 will turn this feature off.", "config.gui.TFCConfig.server.simSpeedNoPlayers");

    generalConfig.setCategoryLanguageKey("overworked chunks", "config.gui.TFCConfig.overworked");

    TFCOptions.enableOverworkingChunks = generalConfig.getBoolean("enableOverworkingChunks", "overworked chunks", TFCOptions.enableOverworkingChunks, "Set this to false to disable the overworking of chunks when using a gold pan or sluice.", "config.gui.TFCConfig.overworked.enableOverworkingChunks");
    TFCOptions.goldPanLimit = generalConfig.getInt("goldPanLimit", "overworked chunks", TFCOptions.goldPanLimit, 1, 500, "The overworked cap for filling a gold pan in a specific chunk. Both filling a gold pan or using a sluice in the chunk count towards this value.", "config.gui.TFCConfig.overworked.goldPanLimit");
    TFCOptions.sluiceLimit = generalConfig.getInt("sluiceLimit", "overworked chunks", TFCOptions.sluiceLimit, 1, 3000, "The overworked cap for a sluice scanning one soil unit in a specific chunk. Both filling a gold pan or using a sluice in the chunk count towards this value", "config.gui.TFCConfig.overworked.sluiceLimit");

    if (!generalConfig.hasCategory("colors"))
    {
      for (String catName : COLOR_CATEGORIES) {

        ConfigCategory cat = generalConfig.getCategory(catName);
        for (UnmodifiableIterator<String> unmodifiableIterator = ImmutableSet.copyOf(cat.keySet()).iterator(); unmodifiableIterator.hasNext(); ) { String propName = unmodifiableIterator.next();

          generalConfig.moveProperty(catName, propName, "colors." + catName); }

        generalConfig.removeCategory(cat);
      }
    }

    generalConfig.setCategoryLanguageKey("colors", "config.gui.TFCConfig.colors");

    getColor(generalConfig, "color nutrient a", TFCOptions.cropNutrientAColor, "config.gui.TFCConfig.colors.nutrient_a");
    getColor(generalConfig, "color nutrient b", TFCOptions.cropNutrientBColor, "config.gui.TFCConfig.colors.nutrient_b");
    getColor(generalConfig, "color nutrient c", TFCOptions.cropNutrientCColor, "config.gui.TFCConfig.colors.nutrient_c");
    getColor(generalConfig, "color fertilizer", TFCOptions.cropFertilizerColor, "config.gui.TFCConfig.colors.fertilizer");
    getColor(generalConfig, "color anvil rule 0", TFCOptions.anvilRuleColor0, "config.gui.TFCConfig.colors.anvil.0");
    getColor(generalConfig, "color anvil rule 1", TFCOptions.anvilRuleColor1, "config.gui.TFCConfig.colors.anvil.1");
    getColor(generalConfig, "color anvil rule 2", TFCOptions.anvilRuleColor2, "config.gui.TFCConfig.colors.anvil.2");


    Global.foodDecayRate = TFCOptions.foodDecayRate;

    if (generalConfig.hasChanged()) generalConfig.save();

  }





  private static byte[] getColor(Configuration cfg, String subcat, byte[] def, String langKey) {
    String cat = "colors." + subcat;
    cfg.setCategoryLanguageKey(cat, langKey);
    cfg.setCategoryPropertyOrder(cat, (List)ImmutableList.of("Red", "Green", "Blue", "Alpha"));

    def[0] = (byte)cfg.getInt("Red", cat, def[0] & 0xFF, 0, 255, "", "config.gui.TFCConfig.colors.r");
    def[1] = (byte)cfg.getInt("Green", cat, def[1] & 0xFF, 0, 255, "", "config.gui.TFCConfig.colors.g");
    def[2] = (byte)cfg.getInt("Blue", cat, def[2] & 0xFF, 0, 255, "", "config.gui.TFCConfig.colors.b");
    if (def.length == 4) def[3] = (byte)cfg.getInt("Alpha", cat, def[3] & 0xFF, 0, 255, "", "config.gui.TFCConfig.colors.a");
    return def;
  }


  public static void reloadOres() {
    if (oresConfig == null) throw new IllegalStateException("Config reload attempt before preinit.");
    TerraFirmaCraft.LOG.info("Loading TFCOres");

    WorldGenOre.oreList.put("Native Copper", getOreData("Native Copper", "veins", "large", "terrafirmacraft:Ore1", 0, 120, new String[] { "igneous extrusive" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
    WorldGenOre.oreList.put("Native Gold", getOreData("Native Gold", "area", "large", "terrafirmacraft:Ore1", 1, 120, new String[] { "igneous extrusive", "igneous intrusive" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
    WorldGenOre.oreList.put("Platinum", getOreData("Platinum", "lens", "small", "terrafirmacraft:Ore1", 2, 150, new String[] { "sedimentary" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
    WorldGenOre.oreList.put("Hematite", getOreData("Hematite", "veins", "medium", "terrafirmacraft:Ore1", 3, 125, new String[] { "igneous extrusive" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
    WorldGenOre.oreList.put("Silver", getOreData("Silver", "veins", "medium", "terrafirmacraft:Ore1", 4, 100, new String[] { "granite", "gneiss" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
    WorldGenOre.oreList.put("Cassiterite", getOreData("Cassiterite", "veins", "medium", "terrafirmacraft:Ore1", 5, 100, new String[] { "igneous intrusive" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
    WorldGenOre.oreList.put("Galena", getOreData("Galena", "veins", "medium", "terrafirmacraft:Ore1", 6, 100, new String[] { "igneous extrusive", "metamorphic", "granite", "limestone" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
    WorldGenOre.oreList.put("Bismuthinite", getOreData("Bismuthinite", "veins", "medium", "terrafirmacraft:Ore1", 7, 100, new String[] { "igneous extrusive", "sedimentary" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
    WorldGenOre.oreList.put("Garnierite", getOreData("Garnierite", "veins", "medium", "terrafirmacraft:Ore1", 8, 150, new String[] { "gabbro" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
    WorldGenOre.oreList.put("Malachite", getOreData("Malachite", "veins", "large", "terrafirmacraft:Ore1", 9, 100, new String[] { "limestone", "marble" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
    WorldGenOre.oreList.put("Magnetite", getOreData("Magnetite", "veins", "medium", "terrafirmacraft:Ore1", 10, 150, new String[] { "sedimentary" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
    WorldGenOre.oreList.put("Limonite", getOreData("Limonite", "veins", "medium", "terrafirmacraft:Ore1", 11, 150, new String[] { "sedimentary" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
    WorldGenOre.oreList.put("Sphalerite", getOreData("Sphalerite", "veins", "medium", "terrafirmacraft:Ore1", 12, 100, new String[] { "metamorphic" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
    WorldGenOre.oreList.put("Tetrahedrite", getOreData("Tetrahedrite", "veins", "medium", "terrafirmacraft:Ore1", 13, 120, new String[] { "metamorphic" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
    WorldGenOre.oreList.put("Bituminous Coal", getOreData("Bituminous Coal", "default", "large", "terrafirmacraft:Ore1", 14, 100, new String[] { "sedimentary" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
    WorldGenOre.oreList.put("Lignite", getOreData("Lignite", "default", "medium", "terrafirmacraft:Ore1", 15, 100, new String[] { "sedimentary" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));

    WorldGenOre.oreList.put("Kaolinite", getOreData("Kaolinite", "default", "medium", "terrafirmacraft:Ore2", 0, 90, new String[] { "sedimentary" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
    WorldGenOre.oreList.put("Gypsum", getOreData("Gypsum", "veins", "large", "terrafirmacraft:Ore2", 1, 120, new String[] { "sedimentary" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));


    WorldGenOre.oreList.put("Graphite", getOreData("Graphite", "veins", "medium", "terrafirmacraft:Ore2", 4, 100, new String[] { "marble", "gneiss", "quartzite", "schist" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
    WorldGenOre.oreList.put("Kimberlite", getOreData("Kimberlite", "veins", "medium", "terrafirmacraft:Ore2", 5, 200, new String[] { "gabbro" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));


    WorldGenOre.oreList.put("Jet", getOreData("Jet", "veins", "large", "terrafirmacraft:Ore2", 8, 110, new String[] { "sedimentary" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));

    WorldGenOre.oreList.put("Pitchblende", getOreData("Pitchblende", "veins", "small", "terrafirmacraft:Ore2", 10, 150, new String[] { "granite" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
    WorldGenOre.oreList.put("Cinnabar", getOreData("Cinnabar", "veins", "small", "terrafirmacraft:Ore2", 11, 150, new String[] { "igneous extrusive", "shale", "quartzite" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
    WorldGenOre.oreList.put("Cryolite", getOreData("Cryolite", "veins", "small", "terrafirmacraft:Ore2", 12, 100, new String[] { "granite" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
    WorldGenOre.oreList.put("Saltpeter", getOreData("Saltpeter", "veins", "medium", "terrafirmacraft:Ore2", 13, 120, new String[] { "sedimentary" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));

    WorldGenOre.oreList.put("Sylvite", getOreData("Sylvite", "veins", "medium", "terrafirmacraft:Ore2", 15, 100, new String[] { "rock salt" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));

    WorldGenOre.oreList.put("Borax", getOreData("Borax", "veins", "large", "terrafirmacraft:Ore3", 0, 120, new String[] { "rock salt" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
    WorldGenOre.oreList.put("Lapis Lazuli", getOreData("Lapis Lazuli", "veins", "large", "terrafirmacraft:Ore3", 2, 120, new String[] { "marble" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));



    WorldGenOre.oreList.put("Native Copper Surface", getOreData("Native Copper Surface", "veins", "small", "terrafirmacraft:Ore1", 0, 35, new String[] { "igneous extrusive" }, 128, 240, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
    WorldGenOre.oreList.put("Cassiterite Surface", getOreData("Cassiterite Surface", "veins", "small", "terrafirmacraft:Ore1", 5, 35, new String[] { "granite" }, 128, 240, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
    WorldGenOre.oreList.put("Bismuthinite Surface", getOreData("Bismuthinite Surface", "veins", "small", "terrafirmacraft:Ore1", 7, 35, new String[] { "igneous extrusive", "sedimentary" }, 128, 240, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
    WorldGenOre.oreList.put("Sphalerite Surface", getOreData("Sphalerite Surface", "veins", "small", "terrafirmacraft:Ore1", 12, 35, new String[] { "metamorphic" }, 128, 240, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
    WorldGenOre.oreList.put("Tetrahedrite Surface", getOreData("Tetrahedrite Surface", "veins", "small", "terrafirmacraft:Ore1", 13, 35, new String[] { "metamorphic" }, 128, 240, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));


    for (String s : oresConfig.getCategoryNames()) {


      if (!WorldGenOre.oreList.containsKey(s)) WorldGenOre.oreList.put(s, getOreData(s, "default", "small", "Ore", 0, 100, new String[] { "granite", "basalt", "sedimentary" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));

    }
    if (oresConfig.hasChanged()) oresConfig.save();

  }


  private static OreSpawnData getOreData(String category, String type, String size, String blockName, int meta, int rarity, String[] rocks, int min, int max, int r, int sxs, int sys, int szs, int vw, int vbh, int vdf, int an, int amd, int cs) {
    return new OreSpawnData(oresConfig
        .get(category, "type", type).setValidValues(ALLOWED_TYPES).getString(), "medium", oresConfig


        .get(category, "oreName", blockName).getString(), oresConfig
        .get(category, "oreMeta", meta).getInt(), oresConfig
        .get(category, "rarity", rarity).getInt(), oresConfig
        .get(category, "baseRocks", rocks).setValidValues(ALLOWED_BASE_ROCKS).getStringList(), oresConfig
        .get(category, "Minimum Height", min).getInt(), oresConfig
        .get(category, "Maximum Height", max).getInt(), oresConfig
        .get(category, "Random Size", r).getInt(), oresConfig
        .get(category, "Sphere Size X", sxs).getInt(), oresConfig
        .get(category, "Sphere Size Y", sys).getInt(), oresConfig
        .get(category, "Sphere Size Z", szs).getInt(), oresConfig
        .get(category, "Vein Width", vw).getInt(), oresConfig
        .get(category, "Vein Base Height", vbh).getInt(), oresConfig
        .get(category, "Vein Down Factor", vdf).getInt(), oresConfig
        .get(category, "Area Number", an).getInt(), oresConfig
        .get(category, "Area Max Distance", amd).getInt(), oresConfig
        .get(category, "Cell Size", cs).getInt());
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Config\TFC_ConfigFiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */