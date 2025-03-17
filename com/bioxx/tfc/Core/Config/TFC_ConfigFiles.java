/*     */ package com.bioxx.tfc.Core.Config;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Util.CaseInsensitiveHashMap;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.WorldGen.Generators.OreSpawnData;
/*     */ import com.bioxx.tfc.WorldGen.Generators.WorldGenOre;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.Crafting.CraftingManagerTFC;
/*     */ import com.bioxx.tfc.api.Enums.EnumOreGen;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCCrafting;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import com.google.common.base.Throwables;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.ObjectArrays;
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import java.io.File;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.IRecipe;
/*     */ import net.minecraft.item.crafting.ShapelessRecipes;
/*     */ import net.minecraftforge.common.config.ConfigCategory;
/*     */ import net.minecraftforge.common.config.Configuration;
/*     */ import net.minecraftforge.common.config.Property;

/*     */ public class TFC_ConfigFiles
/*     */ {
/*     */   public static final String GENERAL = "general";
/*     */   public static final String TIME = "time";
/*     */   public static final String FOOD_DECAY = "food decay";
/*     */   public static final String CAVEIN_OPTIONS = "cave-ins";
/*     */   public static final String WORLD_GEN = "world generation";
/*     */   public static final String CROPS = "crops";
/*     */   public static final String PROTECTION = "spawn protection";
/*     */   public static final String PLAYER = "player";
/*     */   public static final String MATERIALS = "materials";
/*     */   public static final String SERVER = "server";
/*     */   public static final String OVERWORKED = "overworked chunks";
/*     */   public static final String COLORS = "colors";
/*     */   public static final String COLOR_NUTRIENT_A = "color nutrient a";
/*     */   public static final String COLOR_NUTRIENT_B = "color nutrient b";
/*     */   public static final String COLOR_NUTRIENT_C = "color nutrient c";
/*     */   public static final String CROP_FERTILIZER_COLOR = "color fertilizer";
/*     */   public static final String ANVIL_RULE_COLOR0 = "color anvil rule 0";
/*     */   public static final String ANVIL_RULE_COLOR1 = "color anvil rule 1";
/*     */   public static final String ANVIL_RULE_COLOR2 = "color anvil rule 2";
/*     */   public static final String CONVERSION = "Conversion";
/*     */   public static final String ENABLE_VANILLA_RECIPES = "Enable Vanilla Recipes";
/*     */   public static final String CRAFTING_OPTIONS = "Crafting Options";
/* 122 */   private static final String[] COLOR_CATEGORIES = new String[] { "color nutrient a", "color nutrient b", "color nutrient c", "color fertilizer", "color anvil rule 0", "color anvil rule 1", "color anvil rule 2" };
/*     */ 
/*     */   
/* 125 */   private static final String[] ALLOWED_TYPES = new String[] { EnumOreGen.Area.name(), EnumOreGen.Vein.name(), EnumOreGen.Lens.name() };
/*     */   
/* 127 */   private static final String[] ALLOWED_BASE_ROCKS = (String[])ObjectArrays.concat((Object[])Global.STONE_ALL, (Object[])new String[] { "igneous intrusive", "igneous extrusive", "sedimentary", "metamorphic" }, String.class);
/*     */   
/* 129 */   public static final Map<String, SyncingOption> SYNCING_OPTION_MAP = (Map<String, SyncingOption>)new CaseInsensitiveHashMap();
/*     */   
/*     */   private static Configuration generalConfig;
/*     */   
/*     */   private static Configuration craftingConfig;
/*     */   private static Configuration oresConfig;
/*     */   
/*     */   public static Configuration getGeneralConfig() {
/* 137 */     return generalConfig;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Configuration getCraftingConfig() {
/* 142 */     return craftingConfig;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Configuration getOresConfig() {
/* 147 */     return oresConfig;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preInit(File configFolder) {
/* 155 */     if (generalConfig != null) throw new IllegalStateException("Preinit can't be called twice.");
/*     */     
/* 157 */     File generalConfigFile = new File(configFolder, "TFCConfig.cfg");
/* 158 */     if (!generalConfigFile.exists()) {
/*     */       
/* 160 */       File oldConfigFile = new File(generalConfigFile, "cfg");
/* 161 */       if (oldConfigFile.exists() && !oldConfigFile.isDirectory())
/*     */       {
/*     */         
/* 164 */         oldConfigFile.delete();
/*     */       }
/*     */     } 
/* 167 */     generalConfig = new TFC_Configuration(generalConfigFile);
/* 168 */     craftingConfig = new Configuration(new File(configFolder, "TFCCrafting.cfg"));
/* 169 */     oresConfig = new Configuration(new File(configFolder, "TFCOre.cfg"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void reloadAll() {
/* 178 */     reloadGeneral();
/* 179 */     reloadOres();
/* 180 */     TerraFirmaCraft.LOG.info("Reloading TFCCrafting");
/*     */     
/*     */     try {
/* 183 */       for (SyncingOption option : SYNCING_OPTION_MAP.values())
/*     */       {
/* 185 */         option.loadFromConfig();
/*     */       }
/*     */     }
/* 188 */     catch (IllegalAccessException e) {
/*     */       
/* 190 */       TerraFirmaCraft.LOG.fatal("Fatal error reloading TFCCrafting", e);
/* 191 */       Throwables.propagate(e);
/*     */     } 
/* 193 */     if (craftingConfig.hasChanged()) craftingConfig.save();
/*     */   
/*     */   }
/*     */   
/*     */   public static void firstLoadCrafting() {
/* 198 */     if (craftingConfig == null) throw new IllegalStateException("Config reload attempt before preinit."); 
/* 199 */     TerraFirmaCraft.LOG.info("Loading TFCCrafting");
/*     */     
/* 201 */     if (craftingConfig.hasCategory("nei hiding"))
/*     */     {
/* 203 */       craftingConfig.removeCategory(craftingConfig.getCategory("nei hiding"));
/*     */     }
/*     */     
/* 206 */     craftingConfig.setCategoryLanguageKey("Conversion", "config.gui.TFCCrafting.conversion");
/* 207 */     craftingConfig.setCategoryLanguageKey("Enable Vanilla Recipes", "config.gui.TFCCrafting.vanilla");
/* 208 */     craftingConfig.setCategoryLanguageKey("Crafting Options", "config.gui.TFCCrafting.options");
/*     */ 
/*     */     
/*     */     try {
/* 212 */       new ConversionOption("appleConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack(Items.field_151034_e, 1), new Object[] { new ItemStack(TFCItems.redApple, 1) }) });
/* 213 */       new ConversionOption("arrowConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack(Items.field_151032_g, 1), new Object[] { new ItemStack(TFCItems.arrow, 1)
/* 214 */               }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.arrow, 1), new Object[] { new ItemStack(Items.field_151032_g, 1) }) });
/* 215 */       new ConversionOption("bowConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack((Item)Items.field_151031_f, 1), new Object[] { new ItemStack(TFCItems.bow, 1, 0)
/* 216 */               }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.bow, 1, 0), new Object[] { new ItemStack((Item)Items.field_151031_f, 1) }) });
/* 217 */       new ConversionOption("coalConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack(Items.field_151044_h, 1), new Object[] { new ItemStack(TFCItems.coal, 1)
/* 218 */               }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.coal, 1), new Object[] { new ItemStack(Items.field_151044_h, 1) }) });
/* 219 */       new ConversionOption("diamondConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack(Items.field_151045_i, 1), new Object[] { new ItemStack(TFCItems.gemDiamond, 1, 2)
/* 220 */               }), (IRecipe)getAsShapeless(new ItemStack(Items.field_151045_i, 2), new Object[] { new ItemStack(TFCItems.gemDiamond, 1, 3)
/* 221 */               }), (IRecipe)getAsShapeless(new ItemStack(Items.field_151045_i, 3), new Object[] { new ItemStack(TFCItems.gemDiamond, 1, 4)
/* 222 */               }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.gemDiamond, 1, 2), new Object[] { new ItemStack(Items.field_151045_i)
/* 223 */               }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.gemDiamond, 1, 3), new Object[] { new ItemStack(Items.field_151045_i), new ItemStack(Items.field_151045_i)
/* 224 */               }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.gemDiamond, 1, 4), new Object[] { new ItemStack(Items.field_151045_i), new ItemStack(Items.field_151045_i), new ItemStack(Items.field_151045_i) }) });
/* 225 */       new ConversionOption("emeraldConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack(Items.field_151166_bC, 1), new Object[] { new ItemStack(TFCItems.gemEmerald, 1, 2)
/* 226 */               }), (IRecipe)getAsShapeless(new ItemStack(Items.field_151166_bC, 2), new Object[] { new ItemStack(TFCItems.gemEmerald, 1, 3)
/* 227 */               }), (IRecipe)getAsShapeless(new ItemStack(Items.field_151166_bC, 3), new Object[] { new ItemStack(TFCItems.gemEmerald, 1, 4)
/* 228 */               }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.gemEmerald, 1, 2), new Object[] { new ItemStack(Items.field_151166_bC)
/* 229 */               }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.gemEmerald, 1, 3), new Object[] { new ItemStack(Items.field_151166_bC), new ItemStack(Items.field_151166_bC)
/* 230 */               }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.gemEmerald, 1, 4), new Object[] { new ItemStack(Items.field_151166_bC), new ItemStack(Items.field_151166_bC), new ItemStack(Items.field_151166_bC) }) });
/* 231 */       new ConversionOption("fishConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack(Items.field_151115_aP, 1), new Object[] { new ItemStack(TFCItems.fishRaw, 1) }) });
/* 232 */       new ConversionOption("fishingRodConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack((Item)Items.field_151112_aM, 1), new Object[] { new ItemStack(TFCItems.fishingRod, 1, 0)
/* 233 */               }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.fishingRod, 1, 0), new Object[] { new ItemStack((Item)Items.field_151112_aM, 1) }) });
/* 234 */       new ConversionOption("flintSteelConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack(Items.field_151033_d, 1, 0), new Object[] { new ItemStack(TFCItems.flintSteel, 1, 0)
/* 235 */               }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.flintSteel, 1, 0), new Object[] { new ItemStack(Items.field_151033_d, 1, 0) }) });
/* 236 */       new ConversionOption("leatherArmorConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack((Item)Items.field_151024_Q, 1, 0), new Object[] { new ItemStack(TFCItems.leatherHelmet, 1, 0)
/* 237 */               }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.leatherHelmet, 1, 0), new Object[] { new ItemStack((Item)Items.field_151024_Q, 1, 0)
/* 238 */               }), (IRecipe)getAsShapeless(new ItemStack((Item)Items.field_151027_R, 1, 0), new Object[] { new ItemStack(TFCItems.leatherChestplate, 1, 0)
/* 239 */               }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.leatherChestplate, 1, 0), new Object[] { new ItemStack((Item)Items.field_151027_R, 1, 0)
/* 240 */               }), (IRecipe)getAsShapeless(new ItemStack((Item)Items.field_151026_S, 1, 0), new Object[] { new ItemStack(TFCItems.leatherLeggings, 1, 0)
/* 241 */               }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.leatherLeggings, 1, 0), new Object[] { new ItemStack((Item)Items.field_151026_S, 1, 0)
/* 242 */               }), (IRecipe)getAsShapeless(new ItemStack((Item)Items.field_151021_T, 1, 0), new Object[] { new ItemStack(TFCItems.leatherBoots, 1, 0)
/* 243 */               }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.leatherBoots, 1, 0), new Object[] { new ItemStack((Item)Items.field_151021_T, 1, 0) }) });
/* 244 */       new ConversionOption("leatherConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack(Items.field_151116_aA, 1), new Object[] { new ItemStack(TFCItems.leather, 1)
/* 245 */               }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.leather, 1), new Object[] { new ItemStack(Items.field_151116_aA, 1) }) });
/* 246 */       new ConversionOption("stoneAxeConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack(Items.field_151049_t, 1, 0), new Object[] { TFCItems.igInAxe
/* 247 */               }), (IRecipe)getAsShapeless(new ItemStack(Items.field_151049_t, 1, 0), new Object[] { TFCItems.igExAxe
/* 248 */               }), (IRecipe)getAsShapeless(new ItemStack(Items.field_151049_t, 1, 0), new Object[] { TFCItems.sedAxe
/* 249 */               }), (IRecipe)getAsShapeless(new ItemStack(Items.field_151049_t, 1, 0), new Object[] { TFCItems.mMAxe
/* 250 */               }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.igExAxe, 1, 0), new Object[] { Items.field_151049_t }) });
/* 251 */       new ConversionOption("stoneHoeConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack(Items.field_151018_J, 1, 0), new Object[] { TFCItems.igInHoe
/* 252 */               }), (IRecipe)getAsShapeless(new ItemStack(Items.field_151018_J, 1, 0), new Object[] { TFCItems.igExHoe
/* 253 */               }), (IRecipe)getAsShapeless(new ItemStack(Items.field_151018_J, 1, 0), new Object[] { TFCItems.sedHoe
/* 254 */               }), (IRecipe)getAsShapeless(new ItemStack(Items.field_151018_J, 1, 0), new Object[] { TFCItems.mMHoe
/* 255 */               }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.igExHoe, 1, 0), new Object[] { Items.field_151018_J }) });
/* 256 */       new ConversionOption("stoneShovelConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack(Items.field_151051_r, 1, 0), new Object[] { TFCItems.igInShovel
/* 257 */               }), (IRecipe)getAsShapeless(new ItemStack(Items.field_151051_r, 1, 0), new Object[] { TFCItems.igExShovel
/* 258 */               }), (IRecipe)getAsShapeless(new ItemStack(Items.field_151051_r, 1, 0), new Object[] { TFCItems.sedShovel
/* 259 */               }), (IRecipe)getAsShapeless(new ItemStack(Items.field_151051_r, 1, 0), new Object[] { TFCItems.mMShovel
/* 260 */               }), (IRecipe)getAsShapeless(new ItemStack(TFCItems.igExShovel, 1, 0), new Object[] { Items.field_151051_r }) });
/* 261 */       new ConversionOption("woodButtonConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack(Blocks.field_150471_bO, 1), new Object[] { new ItemStack(TFCBlocks.buttonWood, 1)
/* 262 */               }), (IRecipe)getAsShapeless(new ItemStack(TFCBlocks.buttonWood, 1), new Object[] { new ItemStack(Blocks.field_150471_bO, 1) }) });
/* 263 */       new ConversionOption("workbenchConversion", new IRecipe[] { (IRecipe)getAsShapeless(new ItemStack(Blocks.field_150462_ai, 1), new Object[] { new ItemStack(TFCBlocks.workbench, 1)
/* 264 */               }), (IRecipe)getAsShapeless(new ItemStack(TFCBlocks.workbench, 1), new Object[] { new ItemStack(Blocks.field_150462_ai, 1) }) });
/*     */       
/* 266 */       new VanillaRecipeOption("anvilRecipe", new ItemStack[] { new ItemStack(Blocks.field_150467_bQ) });
/* 267 */       new VanillaRecipeOption("arrowsRecipe", new ItemStack[] { new ItemStack(Items.field_151032_g, 4) });
/* 268 */       new VanillaRecipeOption("bedRecipe", new ItemStack[] { new ItemStack(Items.field_151104_aV) });
/* 269 */       new VanillaRecipeOption("bonemealRecipe", new ItemStack[] { new ItemStack(Items.field_151100_aR, 3, 15) });
/* 270 */       new VanillaRecipeOption("bowlRecipe", new ItemStack[] { new ItemStack(Items.field_151054_z, 4) });
/* 271 */       new VanillaRecipeOption("brewingRecipe", new ItemStack[] { new ItemStack(Items.field_151067_bt) });
/* 272 */       new VanillaRecipeOption("bucketRecipe", new ItemStack[] { new ItemStack(Items.field_151133_ar) });
/* 273 */       new VanillaRecipeOption("cauldronRecipe", new ItemStack[] { new ItemStack(Items.field_151066_bu) });
/* 274 */       new VanillaRecipeOption("chestRecipe", new ItemStack[] { new ItemStack((Block)Blocks.field_150486_ae) });
/* 275 */       new VanillaRecipeOption("clockRecipe", new ItemStack[] { new ItemStack(Items.field_151113_aN) });
/* 276 */       new VanillaRecipeOption("compassRecipe", new ItemStack[] { new ItemStack(Items.field_151111_aL) });
/* 277 */       new VanillaRecipeOption("dandelionYellowRecipe", new ItemStack[] { new ItemStack(Items.field_151100_aR, 1, 11), new ItemStack(Items.field_151100_aR, 2, 11) });
/* 278 */       new VanillaRecipeOption("diamondArmorRecipe", new ItemStack[] { new ItemStack((Item)Items.field_151161_ac), new ItemStack((Item)Items.field_151163_ad), new ItemStack((Item)Items.field_151173_ae), new ItemStack((Item)Items.field_151175_af) });
/* 279 */       new VanillaRecipeOption("diamondBlockRecipe", new ItemStack[] { new ItemStack(Blocks.field_150484_ah) });
/* 280 */       new VanillaRecipeOption("diamondToolsRecipe", new ItemStack[] { new ItemStack(Items.field_151046_w), new ItemStack(Items.field_151056_x), new ItemStack(Items.field_151047_v), new ItemStack(Items.field_151012_L), new ItemStack(Items.field_151048_u) });
/* 281 */       new VanillaRecipeOption("dispenserRecipe", new ItemStack[] { new ItemStack(Blocks.field_150367_z) });
/* 282 */       new VanillaRecipeOption("dropperRecipe", new ItemStack[] { new ItemStack(Blocks.field_150409_cd) });
/* 283 */       new VanillaRecipeOption("enchantTableRecipe", new ItemStack[] { new ItemStack(Blocks.field_150381_bn) });
/* 284 */       new VanillaRecipeOption("fenceGateRecipe", new ItemStack[] { new ItemStack(Blocks.field_150396_be) });
/* 285 */       new VanillaRecipeOption("fenceRecipe", new ItemStack[] { new ItemStack(Blocks.field_150422_aJ, 2) });
/* 286 */       new VanillaRecipeOption("furnaceRecipe", new ItemStack[] { new ItemStack(Blocks.field_150460_al) });
/* 287 */       new VanillaRecipeOption("goldAppleRecipe", new ItemStack[] { new ItemStack(Items.field_151153_ao) });
/* 288 */       new VanillaRecipeOption("goldArmorRecipe", new ItemStack[] { new ItemStack((Item)Items.field_151169_ag), new ItemStack((Item)Items.field_151171_ah), new ItemStack((Item)Items.field_151149_ai), new ItemStack((Item)Items.field_151151_aj) });
/* 289 */       new VanillaRecipeOption("goldBlockRecipe", new ItemStack[] { new ItemStack(Blocks.field_150340_R) });
/* 290 */       new VanillaRecipeOption("goldNuggetRecipe", new ItemStack[] { new ItemStack(Items.field_151074_bl, 9) });
/* 291 */       new VanillaRecipeOption("goldPlateRecipe", new ItemStack[] { new ItemStack(Blocks.field_150445_bS) });
/* 292 */       new VanillaRecipeOption("goldToolsRecipe", new ItemStack[] { new ItemStack(Items.field_151005_D), new ItemStack(Items.field_151006_E), new ItemStack(Items.field_151011_C), new ItemStack(Items.field_151013_M), new ItemStack(Items.field_151010_B) });
/* 293 */       new VanillaRecipeOption("hopperRecipe", new ItemStack[] { new ItemStack((Block)Blocks.field_150438_bZ) });
/* 294 */       new VanillaRecipeOption("ironArmorRecipe", new ItemStack[] { new ItemStack((Item)Items.field_151028_Y), new ItemStack((Item)Items.field_151030_Z), new ItemStack((Item)Items.field_151165_aa), new ItemStack((Item)Items.field_151167_ab) });
/* 295 */       new VanillaRecipeOption("ironBarsRecipe", new ItemStack[] { new ItemStack(Blocks.field_150411_aY, 16) });
/* 296 */       new VanillaRecipeOption("ironBlockRecipe", new ItemStack[] { new ItemStack(Blocks.field_150339_S) });
/* 297 */       new VanillaRecipeOption("ironDoorRecipe", new ItemStack[] { new ItemStack(Items.field_151139_aw) });
/* 298 */       new VanillaRecipeOption("ironPlateRecipe", new ItemStack[] { new ItemStack(Blocks.field_150443_bT) });
/* 299 */       new VanillaRecipeOption("ironToolsRecipe", new ItemStack[] { new ItemStack(Items.field_151035_b), new ItemStack(Items.field_151036_c), new ItemStack(Items.field_151037_a), new ItemStack(Items.field_151019_K), new ItemStack(Items.field_151040_l) });
/* 300 */       new VanillaRecipeOption("jukeboxRecipe", new ItemStack[] { new ItemStack(Blocks.field_150421_aI) });
/* 301 */       new VanillaRecipeOption("leatherArmorRecipe", new ItemStack[] { new ItemStack((Item)Items.field_151024_Q), new ItemStack((Item)Items.field_151027_R), new ItemStack((Item)Items.field_151026_S), new ItemStack((Item)Items.field_151021_T) });
/* 302 */       new VanillaRecipeOption("leverRecipe", new ItemStack[] { new ItemStack(Blocks.field_150442_at) });
/* 303 */       new VanillaRecipeOption("minecartChestRecipe", new ItemStack[] { new ItemStack(Items.field_151108_aI) });
/* 304 */       new VanillaRecipeOption("minecartRecipe", new ItemStack[] { new ItemStack(Items.field_151143_au) });
/* 305 */       new VanillaRecipeOption("pistonRecipe", new ItemStack[] { new ItemStack((Block)Blocks.field_150331_J) });
/* 306 */       new VanillaRecipeOption("plankBlockRecipe", new ItemStack[] { new ItemStack(Blocks.field_150344_f, 4, 0), new ItemStack(Blocks.field_150344_f, 4, 1), new ItemStack(Blocks.field_150344_f, 4, 2), new ItemStack(Blocks.field_150344_f, 4, 3), new ItemStack(Blocks.field_150344_f, 4, 4), new ItemStack(Blocks.field_150344_f, 4, 5) });
/* 307 */       new VanillaRecipeOption("poweredRailsRecipe", new ItemStack[] { new ItemStack(Blocks.field_150318_D, 6) });
/* 308 */       new VanillaRecipeOption("railsRecipe", new ItemStack[] { new ItemStack(Blocks.field_150448_aq, 16) });
/* 309 */       new VanillaRecipeOption("repeaterRecipe", new ItemStack[] { new ItemStack(Items.field_151107_aW) });
/* 310 */       new VanillaRecipeOption("roseRedRecipe", new ItemStack[] { new ItemStack(Items.field_151100_aR, 1, 1), new ItemStack(Items.field_151100_aR, 2, 1) });
/* 311 */       new VanillaRecipeOption("shearsRecipe", new ItemStack[] { new ItemStack((Item)Items.field_151097_aZ) });
/* 312 */       new VanillaRecipeOption("signRecipe", new ItemStack[] { new ItemStack(Items.field_151155_ap, 3) });
/* 313 */       new VanillaRecipeOption("stickRecipe", new ItemStack[] { new ItemStack(Items.field_151055_y, 4) });
/* 314 */       new VanillaRecipeOption("stoneSlabsRecipe", new ItemStack[] { new ItemStack((Block)Blocks.field_150333_U, 6), new ItemStack((Block)Blocks.field_150333_U, 6, 3) });
/* 315 */       new VanillaRecipeOption("stoneStairsRecipe", new ItemStack[] { new ItemStack(Blocks.field_150446_ar, 4) });
/* 316 */       new VanillaRecipeOption("stoneToolsRecipe", new ItemStack[] { new ItemStack(Items.field_151050_s), new ItemStack(Items.field_151049_t), new ItemStack(Items.field_151051_r), new ItemStack(Items.field_151018_J), new ItemStack(Items.field_151052_q) });
/* 317 */       new VanillaRecipeOption("torchRecipe", new ItemStack[] { new ItemStack(Blocks.field_150478_aa, 4) });
/* 318 */       new VanillaRecipeOption("trapDoorRecipe", new ItemStack[] { new ItemStack(Blocks.field_150415_aT, 2) });
/* 319 */       new VanillaRecipeOption("tripwireRecipe", new ItemStack[] { new ItemStack((Block)Blocks.field_150479_bC, 2) });
/* 320 */       new VanillaRecipeOption("woodDoorRecipe", new ItemStack[] { new ItemStack(Items.field_151135_aq) });
/* 321 */       new VanillaRecipeOption("woodSlabsRecipe", new ItemStack[] { new ItemStack((Block)Blocks.field_150376_bx, 6, 0), new ItemStack((Block)Blocks.field_150376_bx, 6, 1), new ItemStack((Block)Blocks.field_150376_bx, 6, 2), new ItemStack((Block)Blocks.field_150376_bx, 6, 3), new ItemStack((Block)Blocks.field_150376_bx, 6, 4), new ItemStack((Block)Blocks.field_150376_bx, 6, 5) });
/* 322 */       new VanillaRecipeOption("woodStairsRecipe", new ItemStack[] { new ItemStack(Blocks.field_150487_bG, 4), new ItemStack(Blocks.field_150481_bH, 4), new ItemStack(Blocks.field_150476_ad, 4), new ItemStack(Blocks.field_150485_bF, 4), new ItemStack(Blocks.field_150400_ck, 4), new ItemStack(Blocks.field_150401_cl, 4) });
/* 323 */       new VanillaRecipeOption("woodToolsRecipe", new ItemStack[] { new ItemStack(Items.field_151039_o), new ItemStack(Items.field_151053_p), new ItemStack(Items.field_151038_n), new ItemStack(Items.field_151017_I), new ItemStack(Items.field_151041_m) });
/* 324 */       new VanillaRecipeOption("woolRecipe", new ItemStack[] { new ItemStack(Blocks.field_150325_L) });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 330 */       new SyncingOption("enableBowlsAlwaysBreak", TFCCrafting.class, craftingConfig, "Crafting Options")
/*     */         {
/* 332 */           private IRecipe recipesTFC = (IRecipe)CraftingManagerTFC.getInstance().addRecipe(new ItemStack(TFCItems.potteryBowl, 2), new Object[] { "#####", "#####", "#####", " ### ", "#   #", 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 337 */                 Character.valueOf('#'), new ItemStack(TFCItems.flatClay, 1, 1) });
/*     */ 
/*     */ 
/*     */           
/*     */           public void apply(boolean enabled) throws IllegalAccessException {
/* 342 */             if (this.currentValue != enabled) {
/*     */               
/* 344 */               (this.recipesTFC.func_77571_b()).field_77994_a = enabled ? 4 : 2;
/* 345 */               if (TFCOptions.enableDebugMode)
/* 346 */                 TerraFirmaCraft.LOG.info("Crafting option {} changed from {} to {}. Stacksize {}", new Object[] { this.name, Boolean.valueOf(this.currentValue), Boolean.valueOf(enabled), Integer.valueOf((this.recipesTFC.func_77571_b()).field_77994_a) }); 
/* 347 */               this.field.setBoolean(null, enabled);
/* 348 */               this.currentValue = enabled;
/*     */             } 
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public ImmutableList<IRecipe> getRecipes() {
/* 355 */             return ImmutableList.of(this.recipesTFC);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public String toString() {
/* 361 */             return this.name + "[default:" + this.defaultValue + " current:" + isAplied() + " config:" + inConfig() + " #ofRecipes: 1]";
/*     */           }
/*     */         };
/*     */       
/* 365 */       for (SyncingOption option : SYNCING_OPTION_MAP.values())
/*     */       {
/* 367 */         option.loadFromConfig();
/*     */       }
/*     */     }
/* 370 */     catch (NoSuchFieldException e) {
/*     */       
/* 372 */       TerraFirmaCraft.LOG.fatal("Fatal error loading TFCCrafting", e);
/* 373 */       Throwables.propagate(e);
/*     */     }
/* 375 */     catch (IllegalAccessException e) {
/*     */       
/* 377 */       TerraFirmaCraft.LOG.fatal("Fatal error loading TFCCrafting ", e);
/* 378 */       Throwables.propagate(e);
/*     */     } 
/*     */     
/* 381 */     if (craftingConfig.hasChanged()) craftingConfig.save();
/*     */   
/*     */   }
/*     */   
/*     */   private static ShapelessRecipes getAsShapeless(ItemStack out, Object... in) {
/* 386 */     for (int i = 0; i < in.length; i++) {
/*     */       
/* 388 */       if (!(in[i] instanceof ItemStack))
/* 389 */         if (in[i] instanceof Item) { in[i] = new ItemStack((Item)in[i]); }
/* 390 */         else if (in[i] instanceof Block) { in[i] = new ItemStack((Block)in[i]); }
/* 391 */         else { throw new IllegalArgumentException("Type of " + in[i] + " (arg #" + i + ") not Itemstack, Item or Block."); }
/*     */          
/* 393 */     }  return new ShapelessRecipes(out, Arrays.asList(in));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void reloadGeneral() {
/* 402 */     if (generalConfig == null) throw new IllegalStateException("Config reload attempt before preinit."); 
/* 403 */     TerraFirmaCraft.LOG.info("Loading TFCConfig");
/*     */     
/* 405 */     generalConfig.setCategoryLanguageKey("general", "config.gui.TFCConfig.general");
/*     */     
/* 407 */     if (craftingConfig.hasCategory("nei hiding")) generalConfig.get("general", "enableNEIHiding", TFCOptions.enableNEIHiding).set(craftingConfig.getBoolean("enableNEIHiding", "nei hiding", TFCOptions.enableNEIHiding, ""));
/*     */     
/* 409 */     TFCOptions.enableNEIHiding = generalConfig.getBoolean("enableNEIHiding", "general", TFCOptions.enableNEIHiding, "Set to false to show hidden items in NEI. Note that most of these items were hidden to prevent players from cheating them in and crashing their game.");
/* 410 */     TFCOptions.enablePowderKegs = generalConfig.getBoolean("enablePowderKegs", "general", TFCOptions.enablePowderKegs, "Set this to false to disable powder keg explosions.", "config.gui.TFCConfig.general.enablePowderKegs");
/* 411 */     TFCOptions.enableBetterGrass = generalConfig.getBoolean("enableBetterGrass", "general", TFCOptions.enableBetterGrass, "If true, then the side of a grass block which has another grass block adjacent and one block lower than it will show as completely grass.", "config.gui.TFCConfig.general.enableBetterGrass");
/* 412 */     TFCOptions.enableSaplingDrops = generalConfig.getBoolean("enableSaplingDrops", "general", TFCOptions.enableSaplingDrops, "Set this to false to disable saplings dropping from harvested leaf blocks.", "config.gui.TFCConfig.general.enableSaplingDrops");
/* 413 */     TFCOptions.enableSeedDrops = generalConfig.getBoolean("enableSeedDrops", "general", TFCOptions.enableSeedDrops, "Set this to false to disable seeds being placed on the ground when a crop on farmland dies from natural causes such as freezing temperatures, old age, or not enough sunlight.", "config.gui.TFCConfig.general.enableSeedDrops");
/* 414 */     TFCOptions.enableDebugMode = generalConfig.getBoolean("enableDebugMode", "general", TFCOptions.enableDebugMode, "Set this to true if you want to turn on debug mode which is useful for bug hunting.", "config.gui.TFCConfig.general.enableDebugMode");
/* 415 */     TFCOptions.enableFiniteWater = generalConfig.getBoolean("enableFiniteWater", "general", TFCOptions.enableFiniteWater, "Set this to true to enable finite water. Two adjacent source water blocks will not create a third.", "config.gui.TFCConfig.general.enableFiniteWater");
/* 416 */     TFCOptions.onionsAreGross = generalConfig.getBoolean("onionsAreGross", "general", TFCOptions.onionsAreGross, "Set this to true if you don't like onions.", "config.gui.TFCConfig.general.onionsAreGross");
/* 417 */     TFCOptions.quiverHUDPosition = generalConfig.getString("quiverHUDPosition", "general", TFCOptions.quiverHUDPosition, "Valid position strings are: bottomleft, left, topleft, bottomright, right, topright", new String[] { "bottomleft", "left", "topleft", "bottomright", "right", "topright" }, "config.gui.TFCConfig.general.quiverHUDPosition");
/* 418 */     TFCOptions.enableSolidDetailed = generalConfig.getBoolean("enableSolidDetailed", "general", TFCOptions.enableSolidDetailed, "Should sides of detailed blocks be considered solid?", "config.gui.TFCConfig.general.enableSolidDetailed");
/* 419 */     TFCOptions.maxRemovedSolidDetailed = generalConfig.getInt("maxRemovedSolidDetailed", "general", TFCOptions.maxRemovedSolidDetailed, 0, 64, "Maximum count of removed sub-blocks on one side for the detailed block side to still be solid.", "config.gui.TFCConfig.general.maxRemovedSolidDetailed");
/* 420 */     TFCOptions.enableToolModeIndicator = generalConfig.getBoolean("enableToolModeIndicator", "general", TFCOptions.enableToolModeIndicator, "Set to false to hide the tool mode indicator that is displayed next to the hotbar for tools such as chisels and hoes.", "config.gui.TFCConfig.general.enableToolModeIndicator");
/*     */     
/* 422 */     generalConfig.setCategoryLanguageKey("time", "config.gui.TFCConfig.time");
/*     */     
/* 424 */     TFCOptions.yearLength = generalConfig.getInt("yearLength", "time", TFCOptions.yearLength, 96, 12000, "This is how many days are in a year. Keep this to multiples of 12.", "config.gui.TFCConfig.time.yearLength");
/* 425 */     if (TFCOptions.yearLength % 12 != 0) {
/*     */       
/* 427 */       Property prop = generalConfig.get("time", "yearLength", 96);
/* 428 */       TerraFirmaCraft.LOG.warn("Invalid yearLength in the config file. Changing to the next multiple of 12.");
/* 429 */       TFCOptions.yearLength = 12 + 12 * TFCOptions.yearLength / 12;
/* 430 */       prop.set(TFCOptions.yearLength);
/*     */     } 
/* 432 */     TFCOptions.pitKilnBurnTime = generalConfig.getFloat("pitKilnBurnTime", "time", TFCOptions.pitKilnBurnTime, 1.0F, 2304.0F, "This is the number of hours that the pit kiln should burn before being completed.", "config.gui.TFCConfig.time.pitKilnBurnTime");
/* 433 */     TFCOptions.bloomeryBurnTime = generalConfig.getFloat("bloomeryBurnTime", "time", TFCOptions.bloomeryBurnTime, 1.0F, 2304.0F, "This is the number of hours that the bloomery should burn before being completed.", "config.gui.TFCConfig.time.bloomeryBurnTime");
/* 434 */     TFCOptions.charcoalPitBurnTime = generalConfig.getFloat("charcoalPitBurnTime", "time", TFCOptions.charcoalPitBurnTime, 1.0F, 2304.0F, "This is the number of hours that the charcoal pit should burn before being completed.", "config.gui.TFCConfig.time.charcoalPitBurnTime");
/* 435 */     TFCOptions.torchBurnTime = generalConfig.getInt("torchBurnTime", "time", TFCOptions.torchBurnTime, 0, 2304, "This is how many in-game hours torches will last before burning out. Set to 0 for infinitely burning torches.", "config.gui.TFCConfig.time.torchBurnTime");
/* 436 */     TFCOptions.saplingTimerMultiplier = generalConfig.getFloat("saplingTimerMultiplier", "time", TFCOptions.saplingTimerMultiplier, 0.01F, 100.0F, "This is a global multiplier for the number of days required before a sapling can grow into a tree. Decrease for faster sapling growth.", "config.gui.TFCConfig.time.saplingTimerMultiplier");
/* 437 */     TFCOptions.tempIncreaseMultiplier = generalConfig.getFloat("tempIncreaseMultiplier", "time", TFCOptions.tempIncreaseMultiplier, 0.01F, 100.0F, "This is a global multiplier for the rate at which items heat up. Increase to make items heat up faster.", "config.gui.TFCConfig.time.tempIncreaseMultiplier");
/* 438 */     TFCOptions.tempDecreaseMultiplier = generalConfig.getFloat("tempDecreaseMultiplier", "time", TFCOptions.tempDecreaseMultiplier, 0.01F, 100.0F, "This is a global multiplier for the rate at which items cool down. Increase to make items cool down faster.", "config.gui.TFCConfig.time.tempDecreaseMultiplier");
/* 439 */     TFCOptions.oilLampFuelMult = generalConfig.getInt("oilLampFuelMult", "time", TFCOptions.oilLampFuelMult, 1, 50, "This determines how much fuel is used over time from oil lamps. Setting this higher will make fuel last longer. A mult of 1 = 250 hours, 4 = 1000 hours, 8 = 2000 hours.", "config.gui.TFCConfig.time.oilLampFuelMult");
/* 440 */     TFCOptions.animalTimeMultiplier = generalConfig.getFloat("animalTimeMultiplier", "time", TFCOptions.animalTimeMultiplier, 0.01F, 100.0F, "This is a global multiplier for the gestation period of animals, as well as how long it takes for them to reach adulthood. Decrease for faster times.", "config.gui.TFCConfig.time.animalTimeMultiplier");
/*     */     
/* 442 */     generalConfig.setCategoryLanguageKey("food decay", "config.gui.TFCConfig.fooddecay");
/*     */     
/* 444 */     TFCOptions.foodDecayRate = generalConfig.getFloat("foodDecayRate", "food decay", TFCOptions.foodDecayRate, 1.0F, 2.0F, "This number causes base decay to equal 50% gain per day. If you wish to change, I recommend you look up a y-root calculator 1.0170378966055869517978300569768^24 = 1.5", "config.gui.TFCConfig.fooddecay.foodDecayRate");
/* 445 */     TFCOptions.useDecayProtection = generalConfig.getBoolean("useDecayProtection", "food decay", TFCOptions.useDecayProtection, "Set this to false if you want food to auto decay when a chunk is loaded instead of limiting decay when a chunk has been unloaded for a long period.", "config.gui.TFCConfig.fooddecay.useDecayProtection");
/* 446 */     TFCOptions.decayProtectionDays = generalConfig.getInt("decayProtectionDays", "food decay", TFCOptions.decayProtectionDays, 1, 12000, "If a food item has not been ticked for >= this number of days than when it is ticked for the first time, only a small amount of decay will occur.", "config.gui.TFCConfig.fooddecay.decayProtectionDays");
/* 447 */     TFCOptions.decayMultiplier = generalConfig.getFloat("foodDecayMultiplier", "food decay", TFCOptions.decayMultiplier, 0.0F, 100.0F, "This is a global multiplier for food decay. Unlike FoodDecayRate which only modifies the base decay and not the environmental effect upon decay, this multiplier will multiply against the entire amount. Set to 0 to turn decay off.", "config.gui.TFCConfig.fooddecay.foodDecayMultiplier");
/*     */     
/* 449 */     generalConfig.setCategoryLanguageKey("cave-ins", "config.gui.TFCConfig.caveins");
/*     */     
/* 451 */     TFCOptions.minimumRockLoad = generalConfig.getInt("minimumRockLoad", "cave-ins", TFCOptions.minimumRockLoad, 0, 256, "This is the minimum number of solid blocks that must be over a section in order for it to collapse.", "config.gui.TFCConfig.caveins.minimumRockLoad");
/* 452 */     TFCOptions.initialCollapseRatio = generalConfig.getInt("initialCollapseRatio", "cave-ins", TFCOptions.initialCollapseRatio, 1, 1000, "This number is a 1 in X chance that when you mine a block, a collapse will occur.", "config.gui.TFCConfig.caveins.initialCollapseRatio");
/* 453 */     TFCOptions.propogateCollapseChance = generalConfig.getInt("propogateCollapseChance", "cave-ins", TFCOptions.propogateCollapseChance, 1, 100, "This number is the likelihood for each block to propagate the collapse farther.", "config.gui.TFCConfig.caveins.propogateCollapseChance");
/* 454 */     TFCOptions.enableCaveIns = generalConfig.getBoolean("enableCaveIns", "cave-ins", TFCOptions.enableCaveIns, "Set this to false to disable cave-ins.", "config.gui.TFCConfig.caveins.enableCaveIns");
/* 455 */     TFCOptions.enableCaveInsDestroyOre = generalConfig.getBoolean("enableCaveInsDestroyOre", "cave-ins", TFCOptions.enableCaveInsDestroyOre, "Set this to false to make cave-ins drop the ore item instead of destroy it.", "config.gui.TFCConfig.caveins.enableCaveInsDestroyOre");
/*     */     
/* 457 */     generalConfig.setCategoryLanguageKey("world generation", "config.gui.TFCConfig.worldgen");
/*     */     
/* 459 */     TFCOptions.ravineRarity = generalConfig.getInt("ravineRarity", "world generation", TFCOptions.ravineRarity, 0, 1000, "Controls the chance of a ravine generating, smaller value is higher chance, more ravines. Set to 0 to disable ravines.", "config.gui.TFCConfig.worldgen.ravineRarity");
/* 460 */     TFCOptions.lavaFissureRarity = generalConfig.getInt("lavaFissureRarity", "world generation", TFCOptions.lavaFissureRarity, 0, 1000, "Controls the chance of a lava fissure generating, smaller value is higher chance, more fissures. Set to 0 to disable lava fissures.", "config.gui.TFCConfig.worldgen.lavaFissureRarity");
/* 461 */     TFCOptions.waterFissureRarity = generalConfig.getInt("waterFissureRarity", "world generation", TFCOptions.waterFissureRarity, 0, 1000, "Controls the chance of a water fissure generating, smaller value is higher chance, more fissures. Set to 0 to disable water fissures.", "config.gui.TFCConfig.worldgen.waterFissureRarity");
/*     */     
/* 463 */     generalConfig.setCategoryLanguageKey("crops", "config.gui.TFCConfig.crops");
/*     */     
/* 465 */     TFCOptions.enableCropsDie = generalConfig.getBoolean("enableCropsDie", "crops", TFCOptions.enableCropsDie, "Set to true to enable crop death from old age.", "config.gui.TFCConfig.crops.enableCropsDie");
/* 466 */     TFCOptions.cropGrowthMultiplier = generalConfig.getFloat("cropGrowthModifier", "crops", TFCOptions.cropGrowthMultiplier, 0.01F, 100.0F, "This is a global multiplier for the rate at which crops will grow. Increase to make crops grow faster.", "config.gui.TFCConfig.crops.cropGrowthModifier");
/*     */     
/* 468 */     generalConfig.setCategoryLanguageKey("spawn protection", "config.gui.TFCConfig.protection");
/*     */     
/* 470 */     TFCOptions.maxProtectionMonths = generalConfig.getInt("maxProtectionMonths", "spawn protection", TFCOptions.maxProtectionMonths, 0, 120, "The maximum number of months of spawn protection that can accumulate.", "config.gui.TFCConfig.protection.maxProtectionMonths");
/* 471 */     TFCOptions.protectionGain = generalConfig.getInt("protectionGain", "spawn protection", TFCOptions.protectionGain, 0, 24, "The number of hours of protection gained in the 5x5 chunk area for spending 1 hour in that chunk.", "config.gui.TFCConfig.protection.protectionGain");
/* 472 */     TFCOptions.protectionBuffer = generalConfig.getInt("protectionBuffer", "spawn protection", TFCOptions.protectionBuffer, 0, 2304, "The minimum number of hours of protection that must be accumulated in a chunk in order to bypass the buffer and prevent hostile mob spawning.", "config.gui.TFCConfig.protection.protectionBuffer");
/*     */     
/* 474 */     generalConfig.setCategoryLanguageKey("player", "config.gui.TFCConfig.player");
/*     */     
/* 476 */     TFCOptions.healthGainRate = generalConfig.getInt("healthGainRate", "player", TFCOptions.healthGainRate, 0, 100, "The rate of Health Gain per experience level. Set to 0 to turn off.", "config.gui.TFCConfig.player.healthGainRate");
/* 477 */     TFCOptions.healthGainCap = generalConfig.getInt("healthGainCap", "player", TFCOptions.healthGainCap, 1000, 50000, "The maximum achievable health pool total.", "config.gui.TFCConfig.player.healthGainCap");
/*     */     
/* 479 */     generalConfig.setCategoryLanguageKey("materials", "config.gui.TFCConfig.materials");
/*     */     
/* 481 */     TFCOptions.smallOreUnits = generalConfig.getInt("smallOreUnits", "materials", TFCOptions.smallOreUnits, 1, 100, "The metal units provided by a single piece of small ore or nugget.", "config.gui.TFCConfig.materials.smallOreUnits");
/* 482 */     TFCOptions.poorOreUnits = generalConfig.getInt("poorOreUnits", "materials", TFCOptions.poorOreUnits, 1, 150, "The metal units provided by a single piece of poor ore.", "config.gui.TFCConfig.materials.poorOreUnits");
/* 483 */     TFCOptions.normalOreUnits = generalConfig.getInt("normalOreUnits", "materials", TFCOptions.normalOreUnits, 1, 250, "The metal units provided by a single piece of normal ore.", "config.gui.TFCConfig.materials.normalOreUnits");
/* 484 */     TFCOptions.richOreUnits = generalConfig.getInt("richOreUnits", "materials", TFCOptions.richOreUnits, 1, 350, "The metal units provided by a single piece of rich ore", "config.gui.TFCConfig.materials.richOreUnits");
/*     */     
/* 486 */     generalConfig.setCategoryLanguageKey("server", "config.gui.TFCConfig.server");
/*     */     
/* 488 */     TFCOptions.simSpeedNoPlayers = generalConfig.getInt("simSpeedNoPlayers", "server", TFCOptions.simSpeedNoPlayers, 0, 2147483647, "For every X number of ticks provided here, when there are no players online the server will only progress by 1 tick. Time advances 100 times slower than normal. Setting this to 0 will turn this feature off.", "config.gui.TFCConfig.server.simSpeedNoPlayers");
/*     */     
/* 490 */     generalConfig.setCategoryLanguageKey("overworked chunks", "config.gui.TFCConfig.overworked");
/*     */     
/* 492 */     TFCOptions.enableOverworkingChunks = generalConfig.getBoolean("enableOverworkingChunks", "overworked chunks", TFCOptions.enableOverworkingChunks, "Set this to false to disable the overworking of chunks when using a gold pan or sluice.", "config.gui.TFCConfig.overworked.enableOverworkingChunks");
/* 493 */     TFCOptions.goldPanLimit = generalConfig.getInt("goldPanLimit", "overworked chunks", TFCOptions.goldPanLimit, 1, 500, "The overworked cap for filling a gold pan in a specific chunk. Both filling a gold pan or using a sluice in the chunk count towards this value.", "config.gui.TFCConfig.overworked.goldPanLimit");
/* 494 */     TFCOptions.sluiceLimit = generalConfig.getInt("sluiceLimit", "overworked chunks", TFCOptions.sluiceLimit, 1, 3000, "The overworked cap for a sluice scanning one soil unit in a specific chunk. Both filling a gold pan or using a sluice in the chunk count towards this value", "config.gui.TFCConfig.overworked.sluiceLimit");
/*     */     
/* 496 */     if (!generalConfig.hasCategory("colors"))
/*     */     {
/* 498 */       for (String catName : COLOR_CATEGORIES) {
/*     */         
/* 500 */         ConfigCategory cat = generalConfig.getCategory(catName);
/* 501 */         for (UnmodifiableIterator<String> unmodifiableIterator = ImmutableSet.copyOf(cat.keySet()).iterator(); unmodifiableIterator.hasNext(); ) { String propName = unmodifiableIterator.next();
/*     */           
/* 503 */           generalConfig.moveProperty(catName, propName, "colors." + catName); }
/*     */         
/* 505 */         generalConfig.removeCategory(cat);
/*     */       } 
/*     */     }
/*     */     
/* 509 */     generalConfig.setCategoryLanguageKey("colors", "config.gui.TFCConfig.colors");
/*     */     
/* 511 */     getColor(generalConfig, "color nutrient a", TFCOptions.cropNutrientAColor, "config.gui.TFCConfig.colors.nutrient_a");
/* 512 */     getColor(generalConfig, "color nutrient b", TFCOptions.cropNutrientBColor, "config.gui.TFCConfig.colors.nutrient_b");
/* 513 */     getColor(generalConfig, "color nutrient c", TFCOptions.cropNutrientCColor, "config.gui.TFCConfig.colors.nutrient_c");
/* 514 */     getColor(generalConfig, "color fertilizer", TFCOptions.cropFertilizerColor, "config.gui.TFCConfig.colors.fertilizer");
/* 515 */     getColor(generalConfig, "color anvil rule 0", TFCOptions.anvilRuleColor0, "config.gui.TFCConfig.colors.anvil.0");
/* 516 */     getColor(generalConfig, "color anvil rule 1", TFCOptions.anvilRuleColor1, "config.gui.TFCConfig.colors.anvil.1");
/* 517 */     getColor(generalConfig, "color anvil rule 2", TFCOptions.anvilRuleColor2, "config.gui.TFCConfig.colors.anvil.2");
/*     */ 
/*     */     
/* 520 */     Global.foodDecayRate = TFCOptions.foodDecayRate;
/*     */     
/* 522 */     if (generalConfig.hasChanged()) generalConfig.save();
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static byte[] getColor(Configuration cfg, String subcat, byte[] def, String langKey) {
/* 531 */     String cat = "colors." + subcat;
/* 532 */     cfg.setCategoryLanguageKey(cat, langKey);
/* 533 */     cfg.setCategoryPropertyOrder(cat, (List)ImmutableList.of("Red", "Green", "Blue", "Alpha"));
/*     */     
/* 535 */     def[0] = (byte)cfg.getInt("Red", cat, def[0] & 0xFF, 0, 255, "", "config.gui.TFCConfig.colors.r");
/* 536 */     def[1] = (byte)cfg.getInt("Green", cat, def[1] & 0xFF, 0, 255, "", "config.gui.TFCConfig.colors.g");
/* 537 */     def[2] = (byte)cfg.getInt("Blue", cat, def[2] & 0xFF, 0, 255, "", "config.gui.TFCConfig.colors.b");
/* 538 */     if (def.length == 4) def[3] = (byte)cfg.getInt("Alpha", cat, def[3] & 0xFF, 0, 255, "", "config.gui.TFCConfig.colors.a"); 
/* 539 */     return def;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void reloadOres() {
/* 544 */     if (oresConfig == null) throw new IllegalStateException("Config reload attempt before preinit."); 
/* 545 */     TerraFirmaCraft.LOG.info("Loading TFCOres");
/*     */     
/* 547 */     WorldGenOre.oreList.put("Native Copper", getOreData("Native Copper", "veins", "large", "terrafirmacraft:Ore1", 0, 120, new String[] { "igneous extrusive" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/* 548 */     WorldGenOre.oreList.put("Native Gold", getOreData("Native Gold", "area", "large", "terrafirmacraft:Ore1", 1, 120, new String[] { "igneous extrusive", "igneous intrusive" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/* 549 */     WorldGenOre.oreList.put("Platinum", getOreData("Platinum", "lens", "small", "terrafirmacraft:Ore1", 2, 150, new String[] { "sedimentary" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/* 550 */     WorldGenOre.oreList.put("Hematite", getOreData("Hematite", "veins", "medium", "terrafirmacraft:Ore1", 3, 125, new String[] { "igneous extrusive" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/* 551 */     WorldGenOre.oreList.put("Silver", getOreData("Silver", "veins", "medium", "terrafirmacraft:Ore1", 4, 100, new String[] { "granite", "gneiss" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/* 552 */     WorldGenOre.oreList.put("Cassiterite", getOreData("Cassiterite", "veins", "medium", "terrafirmacraft:Ore1", 5, 100, new String[] { "igneous intrusive" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/* 553 */     WorldGenOre.oreList.put("Galena", getOreData("Galena", "veins", "medium", "terrafirmacraft:Ore1", 6, 100, new String[] { "igneous extrusive", "metamorphic", "granite", "limestone" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/* 554 */     WorldGenOre.oreList.put("Bismuthinite", getOreData("Bismuthinite", "veins", "medium", "terrafirmacraft:Ore1", 7, 100, new String[] { "igneous extrusive", "sedimentary" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/* 555 */     WorldGenOre.oreList.put("Garnierite", getOreData("Garnierite", "veins", "medium", "terrafirmacraft:Ore1", 8, 150, new String[] { "gabbro" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/* 556 */     WorldGenOre.oreList.put("Malachite", getOreData("Malachite", "veins", "large", "terrafirmacraft:Ore1", 9, 100, new String[] { "limestone", "marble" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/* 557 */     WorldGenOre.oreList.put("Magnetite", getOreData("Magnetite", "veins", "medium", "terrafirmacraft:Ore1", 10, 150, new String[] { "sedimentary" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/* 558 */     WorldGenOre.oreList.put("Limonite", getOreData("Limonite", "veins", "medium", "terrafirmacraft:Ore1", 11, 150, new String[] { "sedimentary" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/* 559 */     WorldGenOre.oreList.put("Sphalerite", getOreData("Sphalerite", "veins", "medium", "terrafirmacraft:Ore1", 12, 100, new String[] { "metamorphic" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/* 560 */     WorldGenOre.oreList.put("Tetrahedrite", getOreData("Tetrahedrite", "veins", "medium", "terrafirmacraft:Ore1", 13, 120, new String[] { "metamorphic" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/* 561 */     WorldGenOre.oreList.put("Bituminous Coal", getOreData("Bituminous Coal", "default", "large", "terrafirmacraft:Ore1", 14, 100, new String[] { "sedimentary" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/* 562 */     WorldGenOre.oreList.put("Lignite", getOreData("Lignite", "default", "medium", "terrafirmacraft:Ore1", 15, 100, new String[] { "sedimentary" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/*     */     
/* 564 */     WorldGenOre.oreList.put("Kaolinite", getOreData("Kaolinite", "default", "medium", "terrafirmacraft:Ore2", 0, 90, new String[] { "sedimentary" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/* 565 */     WorldGenOre.oreList.put("Gypsum", getOreData("Gypsum", "veins", "large", "terrafirmacraft:Ore2", 1, 120, new String[] { "sedimentary" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/*     */ 
/*     */     
/* 568 */     WorldGenOre.oreList.put("Graphite", getOreData("Graphite", "veins", "medium", "terrafirmacraft:Ore2", 4, 100, new String[] { "marble", "gneiss", "quartzite", "schist" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/* 569 */     WorldGenOre.oreList.put("Kimberlite", getOreData("Kimberlite", "veins", "medium", "terrafirmacraft:Ore2", 5, 200, new String[] { "gabbro" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/*     */ 
/*     */     
/* 572 */     WorldGenOre.oreList.put("Jet", getOreData("Jet", "veins", "large", "terrafirmacraft:Ore2", 8, 110, new String[] { "sedimentary" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/*     */     
/* 574 */     WorldGenOre.oreList.put("Pitchblende", getOreData("Pitchblende", "veins", "small", "terrafirmacraft:Ore2", 10, 150, new String[] { "granite" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/* 575 */     WorldGenOre.oreList.put("Cinnabar", getOreData("Cinnabar", "veins", "small", "terrafirmacraft:Ore2", 11, 150, new String[] { "igneous extrusive", "shale", "quartzite" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/* 576 */     WorldGenOre.oreList.put("Cryolite", getOreData("Cryolite", "veins", "small", "terrafirmacraft:Ore2", 12, 100, new String[] { "granite" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/* 577 */     WorldGenOre.oreList.put("Saltpeter", getOreData("Saltpeter", "veins", "medium", "terrafirmacraft:Ore2", 13, 120, new String[] { "sedimentary" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/*     */     
/* 579 */     WorldGenOre.oreList.put("Sylvite", getOreData("Sylvite", "veins", "medium", "terrafirmacraft:Ore2", 15, 100, new String[] { "rock salt" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/*     */     
/* 581 */     WorldGenOre.oreList.put("Borax", getOreData("Borax", "veins", "large", "terrafirmacraft:Ore3", 0, 120, new String[] { "rock salt" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/* 582 */     WorldGenOre.oreList.put("Lapis Lazuli", getOreData("Lapis Lazuli", "veins", "large", "terrafirmacraft:Ore3", 2, 120, new String[] { "marble" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/*     */ 
/*     */ 
/*     */     
/* 586 */     WorldGenOre.oreList.put("Native Copper Surface", getOreData("Native Copper Surface", "veins", "small", "terrafirmacraft:Ore1", 0, 35, new String[] { "igneous extrusive" }, 128, 240, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/* 587 */     WorldGenOre.oreList.put("Cassiterite Surface", getOreData("Cassiterite Surface", "veins", "small", "terrafirmacraft:Ore1", 5, 35, new String[] { "granite" }, 128, 240, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/* 588 */     WorldGenOre.oreList.put("Bismuthinite Surface", getOreData("Bismuthinite Surface", "veins", "small", "terrafirmacraft:Ore1", 7, 35, new String[] { "igneous extrusive", "sedimentary" }, 128, 240, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/* 589 */     WorldGenOre.oreList.put("Sphalerite Surface", getOreData("Sphalerite Surface", "veins", "small", "terrafirmacraft:Ore1", 12, 35, new String[] { "metamorphic" }, 128, 240, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/* 590 */     WorldGenOre.oreList.put("Tetrahedrite Surface", getOreData("Tetrahedrite Surface", "veins", "small", "terrafirmacraft:Ore1", 13, 35, new String[] { "metamorphic" }, 128, 240, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/*     */ 
/*     */     
/* 593 */     for (String s : oresConfig.getCategoryNames()) {
/*     */ 
/*     */       
/* 596 */       if (!WorldGenOre.oreList.containsKey(s)) WorldGenOre.oreList.put(s, getOreData(s, "default", "small", "Ore", 0, 100, new String[] { "granite", "basalt", "sedimentary" }, 5, 128, 20, 40, 15, 20, 20, 50, 10, 10, 50, 10));
/*     */     
/*     */     } 
/* 599 */     if (oresConfig.hasChanged()) oresConfig.save();
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   private static OreSpawnData getOreData(String category, String type, String size, String blockName, int meta, int rarity, String[] rocks, int min, int max, int r, int sxs, int sys, int szs, int vw, int vbh, int vdf, int an, int amd, int cs) {
/* 605 */     return new OreSpawnData(oresConfig
/* 606 */         .get(category, "type", type).setValidValues(ALLOWED_TYPES).getString(), "medium", oresConfig
/*     */ 
/*     */         
/* 609 */         .get(category, "oreName", blockName).getString(), oresConfig
/* 610 */         .get(category, "oreMeta", meta).getInt(), oresConfig
/* 611 */         .get(category, "rarity", rarity).getInt(), oresConfig
/* 612 */         .get(category, "baseRocks", rocks).setValidValues(ALLOWED_BASE_ROCKS).getStringList(), oresConfig
/* 613 */         .get(category, "Minimum Height", min).getInt(), oresConfig
/* 614 */         .get(category, "Maximum Height", max).getInt(), oresConfig
/* 615 */         .get(category, "Random Size", r).getInt(), oresConfig
/* 616 */         .get(category, "Sphere Size X", sxs).getInt(), oresConfig
/* 617 */         .get(category, "Sphere Size Y", sys).getInt(), oresConfig
/* 618 */         .get(category, "Sphere Size Z", szs).getInt(), oresConfig
/* 619 */         .get(category, "Vein Width", vw).getInt(), oresConfig
/* 620 */         .get(category, "Vein Base Height", vbh).getInt(), oresConfig
/* 621 */         .get(category, "Vein Down Factor", vdf).getInt(), oresConfig
/* 622 */         .get(category, "Area Number", an).getInt(), oresConfig
/* 623 */         .get(category, "Area Max Distance", amd).getInt(), oresConfig
/* 624 */         .get(category, "Cell Size", cs).getInt());
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Config\TFC_ConfigFiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */