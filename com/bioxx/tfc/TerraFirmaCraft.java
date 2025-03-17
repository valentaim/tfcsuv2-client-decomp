/*     */ package com.bioxx.tfc;
/*     */ 
/*     */ import com.bioxx.tfc.Commands.ClearChunkCommand;
/*     */ import com.bioxx.tfc.Commands.CommandTime;
/*     */ import com.bioxx.tfc.Commands.CommandTransferTamed;
/*     */ import com.bioxx.tfc.Commands.DebugModeCommand;
/*     */ import com.bioxx.tfc.Commands.GSPVisualCommand;
/*     */ import com.bioxx.tfc.Commands.GenCommand;
/*     */ import com.bioxx.tfc.Commands.GetBioTempCommand;
/*     */ import com.bioxx.tfc.Commands.GetBodyTemp;
/*     */ import com.bioxx.tfc.Commands.GetRocksCommand;
/*     */ import com.bioxx.tfc.Commands.GetSpawnProtectionCommand;
/*     */ import com.bioxx.tfc.Commands.GetTreesCommand;
/*     */ import com.bioxx.tfc.Commands.GiveSkillCommand;
/*     */ import com.bioxx.tfc.Commands.PrintImageMapCommand;
/*     */ import com.bioxx.tfc.Commands.RemoveAreaCommand;
/*     */ import com.bioxx.tfc.Commands.RemoveChunkCommand;
/*     */ import com.bioxx.tfc.Commands.RestoreFoodCommand;
/*     */ import com.bioxx.tfc.Commands.SetPlayerStatsCommand;
/*     */ import com.bioxx.tfc.Commands.StripChunkCommand;
/*     */ import com.bioxx.tfc.Core.Config.TFC_ConfigFiles;
/*     */ import com.bioxx.tfc.Core.ItemHeat;
/*     */ import com.bioxx.tfc.Core.Player.PlayerTracker;
/*     */ import com.bioxx.tfc.Core.Recipes;
/*     */ import com.bioxx.tfc.Core.TFC_Achievements;
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_OreDictionary;
/*     */ import com.bioxx.tfc.Food.TFCPotion;
/*     */ import com.bioxx.tfc.Handlers.AnvilCraftingHandler;
/*     */ import com.bioxx.tfc.Handlers.ChatListenerTFC;
/*     */ import com.bioxx.tfc.Handlers.ChunkEventHandler;
/*     */ import com.bioxx.tfc.Handlers.CraftingHandler;
/*     */ import com.bioxx.tfc.Handlers.EnteringChunkHandler;
/*     */ import com.bioxx.tfc.Handlers.EntityDamageHandler;
/*     */ import com.bioxx.tfc.Handlers.EntityLivingHandler;
/*     */ import com.bioxx.tfc.Handlers.EntitySpawnHandler;
/*     */ import com.bioxx.tfc.Handlers.FoodCraftingHandler;
/*     */ import com.bioxx.tfc.Handlers.Network.PacketPipeline;
/*     */ import com.bioxx.tfc.Handlers.PlayerInteractHandler;
/*     */ import com.bioxx.tfc.Handlers.TFCFuelHandler;
/*     */ import com.bioxx.tfc.WorldGen.Generators.WorldGenCaveDecor;
/*     */ import com.bioxx.tfc.WorldGen.Generators.WorldGenFissure;
/*     */ import com.bioxx.tfc.WorldGen.Generators.WorldGenFissureCluster;
/*     */ import com.bioxx.tfc.WorldGen.Generators.WorldGenForests;
/*     */ import com.bioxx.tfc.WorldGen.Generators.WorldGenLargeRock;
/*     */ import com.bioxx.tfc.WorldGen.Generators.WorldGenLooseRocks;
/*     */ import com.bioxx.tfc.WorldGen.Generators.WorldGenOre;
/*     */ import com.bioxx.tfc.WorldGen.Generators.WorldGenPlants;
/*     */ import com.bioxx.tfc.WorldGen.Generators.WorldGenSoilPits;
/*     */ import com.bioxx.tfc.WorldGen.TFCProvider;
/*     */ import com.bioxx.tfc.WorldGen.TFCProviderHell;
/*     */ import com.bioxx.tfc.WorldGen.TFCWorldType;
/*     */ import com.bioxx.tfc.api.SkillsManager;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import cpw.mods.fml.client.event.ConfigChangedEvent;
/*     */ import cpw.mods.fml.common.FMLCommonHandler;
/*     */ import cpw.mods.fml.common.IFuelHandler;
/*     */ import cpw.mods.fml.common.IWorldGenerator;
/*     */ import cpw.mods.fml.common.Mod;
/*     */ import cpw.mods.fml.common.Mod.EventHandler;
/*     */ import cpw.mods.fml.common.Mod.Instance;
/*     */ import cpw.mods.fml.common.SidedProxy;
/*     */ import cpw.mods.fml.common.event.FMLInitializationEvent;
/*     */ import cpw.mods.fml.common.event.FMLPostInitializationEvent;
/*     */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/*     */ import cpw.mods.fml.common.event.FMLServerStartingEvent;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.common.registry.GameRegistry;
/*     */ import net.minecraft.command.ICommand;
/*     */ import net.minecraft.world.WorldType;
/*     */ import net.minecraftforge.common.DimensionManager;
/*     */ import net.minecraftforge.common.ForgeModContainer;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Mod(modid = "terrafirmacraft", name = "TerraFirmaCraft", version = "0.79.29", dependencies = "required-after:tfc_coremod", guiFactory = "com.bioxx.tfc.Core.Config.TFC_GuiFactory")
/*     */ public class TerraFirmaCraft
/*     */ {
/*  84 */   public static final Logger LOG = LogManager.getLogger("TerraFirmaCraft");
/*     */ 
/*     */   
/*     */   @Instance("terrafirmacraft")
/*     */   public static TerraFirmaCraft instance;
/*     */   
/*     */   @SidedProxy(clientSide = "com.bioxx.tfc.ClientProxy", serverSide = "com.bioxx.tfc.CommonProxy")
/*     */   public static CommonProxy proxy;
/*     */   
/*  93 */   public static final PacketPipeline PACKET_PIPELINE = new PacketPipeline();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int renderfoodprep;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean ShipsExist = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void preInit(FMLPreInitializationEvent event) {
/* 111 */     TFC_ConfigFiles.preInit(event.getModConfigurationDirectory());
/* 112 */     TFC_ConfigFiles.reloadGeneral();
/*     */ 
/*     */     
/* 115 */     proxy.registerTickHandler();
/*     */     
/* 117 */     proxy.registerFluids();
/*     */     
/* 119 */     BlockSetup.loadBlocks();
/* 120 */     BlockSetup.registerBlocks();
/* 121 */     BlockSetup.setupFire();
/*     */ 
/*     */ 
/*     */     
/* 125 */     proxy.registerKeys();
/*     */     
/* 127 */     proxy.registerKeyBindingHandler();
/*     */     
/* 129 */     proxy.registerHandlers();
/*     */     
/* 131 */     proxy.registerTileEntities(true);
/*     */     
/* 133 */     proxy.registerSoundHandler();
/*     */     
/* 135 */     proxy.registerPlayerRenderEventHandler();
/*     */     
/* 137 */     SkillsManager.instance.registerSkill("skill.gensmith", 250);
/* 138 */     SkillsManager.instance.registerSkill("skill.toolsmith", 100);
/* 139 */     SkillsManager.instance.registerSkill("skill.armorsmith", 100);
/* 140 */     SkillsManager.instance.registerSkill("skill.weaponsmith", 100);
/* 141 */     SkillsManager.instance.registerSkill("skill.agriculture", 300);
/* 142 */     SkillsManager.instance.registerSkill("skill.cooking", 200);
/* 143 */     SkillsManager.instance.registerSkill("skill.prospecting", 1500);
/* 144 */     SkillsManager.instance.registerSkill("skill.butchering", 100);
/*     */ 
/*     */     
/* 147 */     ItemSetup.setup();
/*     */ 
/*     */     
/* 150 */     proxy.registerGuiHandler();
/*     */ 
/*     */ 
/*     */     
/* 154 */     GameRegistry.registerWorldGenerator((IWorldGenerator)(new WorldGenFissure(TFCBlocks.lava, 2, true, TFCOptions.lavaFissureRarity)).setUnderground(true, 20).setSeed(1), 0);
/* 155 */     GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGenFissure(TFCBlocks.freshWaterStationary, 2, false, TFCOptions.waterFissureRarity), 0);
/*     */     
/* 157 */     GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGenFissureCluster(), 1);
/* 158 */     GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGenOre(), 2);
/* 159 */     GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGenCaveDecor(), 3);
/* 160 */     GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGenForests(), 4);
/* 161 */     GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGenLooseRocks(), 5);
/* 162 */     GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGenSoilPits(), 6);
/* 163 */     GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGenLargeRock(), 7);
/* 164 */     GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGenPlants(), 8);
/*     */     
/* 166 */     WorldType.field_77137_b = (WorldType)new TFCWorldType(0, "TFCDefault");
/* 167 */     WorldType.field_77138_c = (WorldType)new TFCWorldType(1, "TFCFlat");
/* 168 */     WorldType.field_77135_d = (WorldType)new TFCWorldType(2, "TFCLargeBiomes");
/* 169 */     WorldType.field_151360_e = (WorldType)new TFCWorldType(3, "TFCAmplified");
/*     */     
/* 171 */     DimensionManager.unregisterDimension(-1);
/* 172 */     DimensionManager.unregisterDimension(0);
/* 173 */     DimensionManager.unregisterDimension(1);
/*     */     
/* 175 */     DimensionManager.unregisterProviderType(-1);
/* 176 */     DimensionManager.unregisterProviderType(0);
/* 177 */     DimensionManager.unregisterProviderType(1);
/* 178 */     DimensionManager.registerProviderType(-1, TFCProviderHell.class, false);
/* 179 */     DimensionManager.registerProviderType(0, TFCProvider.class, true);
/* 180 */     DimensionManager.registerProviderType(1, TFCProvider.class, false);
/*     */     
/* 182 */     DimensionManager.registerDimension(-1, -1);
/* 183 */     DimensionManager.registerDimension(0, 0);
/* 184 */     DimensionManager.registerDimension(1, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void initialize(FMLInitializationEvent event) {
/* 192 */     PACKET_PIPELINE.initalise();
/*     */ 
/*     */     
/* 195 */     FMLCommonHandler.instance().bus().register(new PlayerTracker());
/*     */ 
/*     */     
/* 198 */     proxy.registerToolClasses();
/*     */ 
/*     */     
/* 201 */     TFC_Achievements.init();
/*     */ 
/*     */     
/* 204 */     FMLCommonHandler.instance().bus().register(new CraftingHandler());
/* 205 */     FMLCommonHandler.instance().bus().register(new FoodCraftingHandler());
/* 206 */     FMLCommonHandler.instance().bus().register(instance);
/*     */ 
/*     */     
/* 209 */     MinecraftForge.EVENT_BUS.register(new PlayerInteractHandler());
/*     */ 
/*     */     
/* 212 */     MinecraftForge.EVENT_BUS.register(new EntitySpawnHandler());
/*     */ 
/*     */     
/* 215 */     MinecraftForge.EVENT_BUS.register(new EntityDamageHandler());
/*     */ 
/*     */     
/* 218 */     MinecraftForge.EVENT_BUS.register(new ChatListenerTFC());
/*     */ 
/*     */     
/* 221 */     MinecraftForge.EVENT_BUS.register(new ChunkEventHandler());
/*     */ 
/*     */     
/* 224 */     MinecraftForge.EVENT_BUS.register(new EnteringChunkHandler());
/*     */ 
/*     */     
/* 227 */     MinecraftForge.EVENT_BUS.register(new AnvilCraftingHandler());
/*     */ 
/*     */     
/* 230 */     MinecraftForge.EVENT_BUS.register(new EntityLivingHandler());
/*     */ 
/*     */     
/* 233 */     proxy.registerRenderInformation();
/*     */     
/* 235 */     proxy.registerBiomeEventHandler();
/* 236 */     proxy.setupGuiIngameForge();
/*     */ 
/*     */     
/* 239 */     proxy.setupFluids();
/* 240 */     proxy.registerFluidIcons();
/*     */ 
/*     */     
/* 243 */     TFCPotion.setup();
/*     */ 
/*     */     
/* 246 */     TFC_OreDictionary.registerOreDict();
/* 247 */     Recipes.registerRecipes();
/*     */     
/* 249 */     ItemHeat.setupItemHeat();
/*     */     
/* 251 */     TFC_Climate.initCache();
/*     */ 
/*     */ 
/*     */     
/* 255 */     ItemSetup.registerFurnaceFuel();
/* 256 */     GameRegistry.registerFuelHandler((IFuelHandler)new TFCFuelHandler());
/*     */ 
/*     */     
/* 259 */     proxy.registerChiselModes();
/*     */ 
/*     */     
/* 262 */     proxy.registerWailaClasses();
/* 263 */     proxy.hideNEIItems();
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void postInit(FMLPostInitializationEvent evt) {
/* 269 */     PACKET_PIPELINE.postInitialise();
/*     */ 
/*     */     
/* 272 */     TFC_ConfigFiles.reloadOres();
/*     */     
/* 274 */     ForgeModContainer.zombieBabyChance = 0.0F;
/*     */ 
/*     */     
/* 277 */     this.ShipsExist = isShipsAvailable();
/* 278 */     if (this.ShipsExist) LOG.info("Ships mod detected!");
/*     */   
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void serverStarting(FMLServerStartingEvent evt) throws Exception {
/* 284 */     evt.registerServerCommand((ICommand)new GetBioTempCommand());
/* 285 */     evt.registerServerCommand((ICommand)new GetTreesCommand());
/* 286 */     evt.registerServerCommand((ICommand)new GetRocksCommand());
/* 287 */     evt.registerServerCommand((ICommand)new GetSpawnProtectionCommand());
/* 288 */     evt.registerServerCommand((ICommand)new SetPlayerStatsCommand());
/* 289 */     evt.registerServerCommand((ICommand)new GetBodyTemp());
/* 290 */     evt.registerServerCommand((ICommand)new RemoveChunkCommand());
/* 291 */     evt.registerServerCommand((ICommand)new StripChunkCommand());
/* 292 */     evt.registerServerCommand((ICommand)new ClearChunkCommand());
/* 293 */     evt.registerServerCommand((ICommand)new GSPVisualCommand());
/* 294 */     evt.registerServerCommand((ICommand)new RemoveAreaCommand());
/* 295 */     evt.registerServerCommand((ICommand)new DebugModeCommand());
/* 296 */     evt.registerServerCommand((ICommand)new CommandTime());
/* 297 */     evt.registerServerCommand((ICommand)new GenCommand());
/* 298 */     evt.registerServerCommand((ICommand)new PrintImageMapCommand());
/* 299 */     evt.registerServerCommand((ICommand)new GiveSkillCommand());
/* 300 */     evt.registerServerCommand((ICommand)new CommandTransferTamed());
/* 301 */     evt.registerServerCommand((ICommand)new RestoreFoodCommand());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
/* 311 */     if (eventArgs.modID.equals("terrafirmacraft")) TFC_ConfigFiles.reloadAll();
/*     */   
/*     */   }
/*     */   
/*     */   public static boolean isShipsAvailable() {
/*     */     boolean clazzExists;
/*     */     try {
/* 318 */       Class.forName("cuchaz.ships.ShipWorld", false, instance.getClass().getClassLoader());
/* 319 */       clazzExists = true;
/* 320 */     } catch (ClassNotFoundException e) {
/* 321 */       clazzExists = false;
/*     */     } 
/* 323 */     return clazzExists;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TerraFirmaCraft.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */