package com.bioxx.tfc;

import com.bioxx.tfc.Commands.ClearChunkCommand;
import com.bioxx.tfc.Commands.CommandTime;
import com.bioxx.tfc.Commands.CommandTransferTamed;
import com.bioxx.tfc.Commands.DebugModeCommand;
import com.bioxx.tfc.Commands.GSPVisualCommand;
import com.bioxx.tfc.Commands.GenCommand;
import com.bioxx.tfc.Commands.GetBioTempCommand;
import com.bioxx.tfc.Commands.GetBodyTemp;
import com.bioxx.tfc.Commands.GetRocksCommand;
import com.bioxx.tfc.Commands.GetSpawnProtectionCommand;
import com.bioxx.tfc.Commands.GetTreesCommand;
import com.bioxx.tfc.Commands.GiveSkillCommand;
import com.bioxx.tfc.Commands.PrintImageMapCommand;
import com.bioxx.tfc.Commands.RemoveAreaCommand;
import com.bioxx.tfc.Commands.RemoveChunkCommand;
import com.bioxx.tfc.Commands.RestoreFoodCommand;
import com.bioxx.tfc.Commands.SetPlayerStatsCommand;
import com.bioxx.tfc.Commands.StripChunkCommand;
import com.bioxx.tfc.Core.Config.TFC_ConfigFiles;
import com.bioxx.tfc.Core.ItemHeat;
import com.bioxx.tfc.Core.Player.PlayerTracker;
import com.bioxx.tfc.Core.Recipes;
import com.bioxx.tfc.Core.TFC_Achievements;
import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_OreDictionary;
import com.bioxx.tfc.Food.TFCPotion;
import com.bioxx.tfc.Handlers.AnvilCraftingHandler;
import com.bioxx.tfc.Handlers.ChatListenerTFC;
import com.bioxx.tfc.Handlers.ChunkEventHandler;
import com.bioxx.tfc.Handlers.CraftingHandler;
import com.bioxx.tfc.Handlers.EnteringChunkHandler;
import com.bioxx.tfc.Handlers.EntityDamageHandler;
import com.bioxx.tfc.Handlers.EntityLivingHandler;
import com.bioxx.tfc.Handlers.EntitySpawnHandler;
import com.bioxx.tfc.Handlers.FoodCraftingHandler;
import com.bioxx.tfc.Handlers.Network.PacketPipeline;
import com.bioxx.tfc.Handlers.PlayerInteractHandler;
import com.bioxx.tfc.Handlers.TFCFuelHandler;
import com.bioxx.tfc.WorldGen.Generators.WorldGenCaveDecor;
import com.bioxx.tfc.WorldGen.Generators.WorldGenFissure;
import com.bioxx.tfc.WorldGen.Generators.WorldGenFissureCluster;
import com.bioxx.tfc.WorldGen.Generators.WorldGenForests;
import com.bioxx.tfc.WorldGen.Generators.WorldGenLargeRock;
import com.bioxx.tfc.WorldGen.Generators.WorldGenLooseRocks;
import com.bioxx.tfc.WorldGen.Generators.WorldGenOre;
import com.bioxx.tfc.WorldGen.Generators.WorldGenPlants;
import com.bioxx.tfc.WorldGen.Generators.WorldGenSoilPits;
import com.bioxx.tfc.WorldGen.TFCProvider;
import com.bioxx.tfc.WorldGen.TFCProviderHell;
import com.bioxx.tfc.WorldGen.TFCWorldType;
import com.bioxx.tfc.api.SkillsManager;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCOptions;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.command.ICommand;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




@Mod(modid = "terrafirmacraft", name = "TerraFirmaCraft", version = "0.79.29", dependencies = "required-after:tfc_coremod", guiFactory = "com.bioxx.tfc.Core.Config.TFC_GuiFactory")
public class TerraFirmaCraft
{
  public static final Logger LOG = LogManager.getLogger("TerraFirmaCraft");


  @Instance("terrafirmacraft")
  public static TerraFirmaCraft instance;

  @SidedProxy(clientSide = "com.bioxx.tfc.ClientProxy", serverSide = "com.bioxx.tfc.CommonProxy")
  public static CommonProxy proxy;

  public static final PacketPipeline PACKET_PIPELINE = new PacketPipeline();




  public static int renderfoodprep;




  public boolean ShipsExist = false;





  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    TFC_ConfigFiles.preInit(event.getModConfigurationDirectory());
    TFC_ConfigFiles.reloadGeneral();


    proxy.registerTickHandler();

    proxy.registerFluids();

    BlockSetup.loadBlocks();
    BlockSetup.registerBlocks();
    BlockSetup.setupFire();



    proxy.registerKeys();

    proxy.registerKeyBindingHandler();

    proxy.registerHandlers();

    proxy.registerTileEntities(true);

    proxy.registerSoundHandler();

    proxy.registerPlayerRenderEventHandler();

    SkillsManager.instance.registerSkill("skill.gensmith", 250);
    SkillsManager.instance.registerSkill("skill.toolsmith", 100);
    SkillsManager.instance.registerSkill("skill.armorsmith", 100);
    SkillsManager.instance.registerSkill("skill.weaponsmith", 100);
    SkillsManager.instance.registerSkill("skill.agriculture", 300);
    SkillsManager.instance.registerSkill("skill.cooking", 200);
    SkillsManager.instance.registerSkill("skill.prospecting", 1500);
    SkillsManager.instance.registerSkill("skill.butchering", 100);


    ItemSetup.setup();


    proxy.registerGuiHandler();



    GameRegistry.registerWorldGenerator((IWorldGenerator)(new WorldGenFissure(TFCBlocks.lava, 2, true, TFCOptions.lavaFissureRarity)).setUnderground(true, 20).setSeed(1), 0);
    GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGenFissure(TFCBlocks.freshWaterStationary, 2, false, TFCOptions.waterFissureRarity), 0);

    GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGenFissureCluster(), 1);
    GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGenOre(), 2);
    GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGenCaveDecor(), 3);
    GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGenForests(), 4);
    GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGenLooseRocks(), 5);
    GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGenSoilPits(), 6);
    GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGenLargeRock(), 7);
    GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGenPlants(), 8);

    WorldType.field_77137_b = (WorldType)new TFCWorldType(0, "TFCDefault");
    WorldType.field_77138_c = (WorldType)new TFCWorldType(1, "TFCFlat");
    WorldType.field_77135_d = (WorldType)new TFCWorldType(2, "TFCLargeBiomes");
    WorldType.field_151360_e = (WorldType)new TFCWorldType(3, "TFCAmplified");

    DimensionManager.unregisterDimension(-1);
    DimensionManager.unregisterDimension(0);
    DimensionManager.unregisterDimension(1);

    DimensionManager.unregisterProviderType(-1);
    DimensionManager.unregisterProviderType(0);
    DimensionManager.unregisterProviderType(1);
    DimensionManager.registerProviderType(-1, TFCProviderHell.class, false);
    DimensionManager.registerProviderType(0, TFCProvider.class, true);
    DimensionManager.registerProviderType(1, TFCProvider.class, false);

    DimensionManager.registerDimension(-1, -1);
    DimensionManager.registerDimension(0, 0);
    DimensionManager.registerDimension(1, 1);
  }




  @EventHandler
  public void initialize(FMLInitializationEvent event) {
    PACKET_PIPELINE.initalise();


    FMLCommonHandler.instance().bus().register(new PlayerTracker());


    proxy.registerToolClasses();


    TFC_Achievements.init();


    FMLCommonHandler.instance().bus().register(new CraftingHandler());
    FMLCommonHandler.instance().bus().register(new FoodCraftingHandler());
    FMLCommonHandler.instance().bus().register(instance);


    MinecraftForge.EVENT_BUS.register(new PlayerInteractHandler());


    MinecraftForge.EVENT_BUS.register(new EntitySpawnHandler());


    MinecraftForge.EVENT_BUS.register(new EntityDamageHandler());


    MinecraftForge.EVENT_BUS.register(new ChatListenerTFC());


    MinecraftForge.EVENT_BUS.register(new ChunkEventHandler());


    MinecraftForge.EVENT_BUS.register(new EnteringChunkHandler());


    MinecraftForge.EVENT_BUS.register(new AnvilCraftingHandler());


    MinecraftForge.EVENT_BUS.register(new EntityLivingHandler());


    proxy.registerRenderInformation();

    proxy.registerBiomeEventHandler();
    proxy.setupGuiIngameForge();


    proxy.setupFluids();
    proxy.registerFluidIcons();


    TFCPotion.setup();


    TFC_OreDictionary.registerOreDict();
    Recipes.registerRecipes();

    ItemHeat.setupItemHeat();

    TFC_Climate.initCache();



    ItemSetup.registerFurnaceFuel();
    GameRegistry.registerFuelHandler((IFuelHandler)new TFCFuelHandler());


    proxy.registerChiselModes();


    proxy.registerWailaClasses();
    proxy.hideNEIItems();
  }


  @EventHandler
  public void postInit(FMLPostInitializationEvent evt) {
    PACKET_PIPELINE.postInitialise();


    TFC_ConfigFiles.reloadOres();

    ForgeModContainer.zombieBabyChance = 0.0F;


    this.ShipsExist = isShipsAvailable();
    if (this.ShipsExist) LOG.info("Ships mod detected!");

  }

  @EventHandler
  public void serverStarting(FMLServerStartingEvent evt) throws Exception {
    evt.registerServerCommand((ICommand)new GetBioTempCommand());
    evt.registerServerCommand((ICommand)new GetTreesCommand());
    evt.registerServerCommand((ICommand)new GetRocksCommand());
    evt.registerServerCommand((ICommand)new GetSpawnProtectionCommand());
    evt.registerServerCommand((ICommand)new SetPlayerStatsCommand());
    evt.registerServerCommand((ICommand)new GetBodyTemp());
    evt.registerServerCommand((ICommand)new RemoveChunkCommand());
    evt.registerServerCommand((ICommand)new StripChunkCommand());
    evt.registerServerCommand((ICommand)new ClearChunkCommand());
    evt.registerServerCommand((ICommand)new GSPVisualCommand());
    evt.registerServerCommand((ICommand)new RemoveAreaCommand());
    evt.registerServerCommand((ICommand)new DebugModeCommand());
    evt.registerServerCommand((ICommand)new CommandTime());
    evt.registerServerCommand((ICommand)new GenCommand());
    evt.registerServerCommand((ICommand)new PrintImageMapCommand());
    evt.registerServerCommand((ICommand)new GiveSkillCommand());
    evt.registerServerCommand((ICommand)new CommandTransferTamed());
    evt.registerServerCommand((ICommand)new RestoreFoodCommand());
  }






  @SubscribeEvent
  public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
    if (eventArgs.modID.equals("terrafirmacraft")) TFC_ConfigFiles.reloadAll();

  }

  public static boolean isShipsAvailable() {
    boolean clazzExists;
    try {
      Class.forName("cuchaz.ships.ShipWorld", false, instance.getClass().getClassLoader());
      clazzExists = true;
    } catch (ClassNotFoundException e) {
      clazzExists = false;
    }
    return clazzExists;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TerraFirmaCraft.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */