package com.bioxx.tfc;
import com.bioxx.tfc.Core.ColorizerFoliageTFC;
import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Entities.EntityBarrel;
import com.bioxx.tfc.Entities.EntityCustomMinecart;
import com.bioxx.tfc.Entities.EntityFallingBlockTFC;
import com.bioxx.tfc.Entities.EntityFishHookTFC;
import com.bioxx.tfc.Entities.EntityJavelin;
import com.bioxx.tfc.Entities.EntityProjectileTFC;
import com.bioxx.tfc.Entities.EntityStand;
import com.bioxx.tfc.Entities.Mobs.EntityBear;
import com.bioxx.tfc.Entities.Mobs.EntityBlazeTFC;
import com.bioxx.tfc.Entities.Mobs.EntityCaveSpiderTFC;
import com.bioxx.tfc.Entities.Mobs.EntityChickenTFC;
import com.bioxx.tfc.Entities.Mobs.EntityDeer;
import com.bioxx.tfc.Entities.Mobs.EntityEndermanTFC;
import com.bioxx.tfc.Entities.Mobs.EntityFishTFC;
import com.bioxx.tfc.Entities.Mobs.EntityGhastTFC;
import com.bioxx.tfc.Entities.Mobs.EntityHorseTFC;
import com.bioxx.tfc.Entities.Mobs.EntityIronGolemTFC;
import com.bioxx.tfc.Entities.Mobs.EntityPheasantTFC;
import com.bioxx.tfc.Entities.Mobs.EntityPigZombieTFC;
import com.bioxx.tfc.Entities.Mobs.EntitySilverfishTFC;
import com.bioxx.tfc.Entities.Mobs.EntitySkeletonTFC;
import com.bioxx.tfc.Entities.Mobs.EntitySlimeTFC;
import com.bioxx.tfc.Entities.Mobs.EntitySpiderTFC;
import com.bioxx.tfc.Entities.Mobs.EntitySquidTFC;
import com.bioxx.tfc.Entities.Mobs.EntityWolfTFC;
import com.bioxx.tfc.Entities.Mobs.EntityZombieTFC;
import com.bioxx.tfc.Handlers.BiomeEventHandler;
import com.bioxx.tfc.Handlers.Client.ArmourStandHighlightHandler;
import com.bioxx.tfc.Handlers.Client.BlockRenderHandler;
import com.bioxx.tfc.Handlers.Client.ChiselHighlightHandler;
import com.bioxx.tfc.Handlers.Client.ClientTickHandler;
import com.bioxx.tfc.Handlers.Client.FMLClientEventHandler;
import com.bioxx.tfc.Handlers.Client.FamiliarityHighlightHandler;
import com.bioxx.tfc.Handlers.Client.FarmlandHighlightHandler;
import com.bioxx.tfc.Handlers.Client.FogHandler;
import com.bioxx.tfc.Handlers.Client.GuiHandler;
import com.bioxx.tfc.Handlers.Client.KeyBindingHandler;
import com.bioxx.tfc.Handlers.Client.PlankHighlightHandler;
import com.bioxx.tfc.Handlers.Client.PlayerRenderHandler;
import com.bioxx.tfc.Handlers.Client.RenderOverlayHandler;
import com.bioxx.tfc.Handlers.Client.SoundHandler;
import com.bioxx.tfc.Render.Blocks.RenderAnvil;
import com.bioxx.tfc.Render.Blocks.RenderBarrel;
import com.bioxx.tfc.Render.Blocks.RenderBellows;
import com.bioxx.tfc.Render.Blocks.RenderBloomery;
import com.bioxx.tfc.Render.Blocks.RenderChest;
import com.bioxx.tfc.Render.Blocks.RenderCrucible;
import com.bioxx.tfc.Render.Blocks.RenderFence;
import com.bioxx.tfc.Render.Blocks.RenderFenceGate;
import com.bioxx.tfc.Render.Blocks.RenderFlowerPot;
import com.bioxx.tfc.Render.Blocks.RenderGrill;
import com.bioxx.tfc.Render.Blocks.RenderHopper;
import com.bioxx.tfc.Render.Blocks.RenderLeatherRack;
import com.bioxx.tfc.Render.Blocks.RenderLoom;
import com.bioxx.tfc.Render.Blocks.RenderMetalSheet;
import com.bioxx.tfc.Render.Blocks.RenderMetalTrapDoor;
import com.bioxx.tfc.Render.Blocks.RenderNestBox;
import com.bioxx.tfc.Render.Blocks.RenderOilLamp;
import com.bioxx.tfc.Render.Blocks.RenderOre;
import com.bioxx.tfc.Render.Blocks.RenderPottery;
import com.bioxx.tfc.Render.Blocks.RenderSmoke;
import com.bioxx.tfc.Render.Blocks.RenderSmokeRack;
import com.bioxx.tfc.Render.Blocks.RenderStand;
import com.bioxx.tfc.Render.Blocks.RenderSupportBeam;
import com.bioxx.tfc.Render.Blocks.RenderToolRack;
import com.bioxx.tfc.Render.Blocks.RenderTorch;
import com.bioxx.tfc.Render.Blocks.RenderTuyere;
import com.bioxx.tfc.Render.Blocks.RenderVessel;
import com.bioxx.tfc.Render.Blocks.RenderWall;
import com.bioxx.tfc.Render.Blocks.RenderWattle;
import com.bioxx.tfc.Render.Blocks.RenderWoodConstruct;
import com.bioxx.tfc.Render.EntityRendererTFC;
import com.bioxx.tfc.Render.FoliageColorReloadListener;
import com.bioxx.tfc.Render.GrassColorReloadListener;
import com.bioxx.tfc.Render.Models.ModelBass;
import com.bioxx.tfc.Render.Models.ModelBear;
import com.bioxx.tfc.Render.Models.ModelChickenTFC;
import com.bioxx.tfc.Render.Models.ModelDeer;
import com.bioxx.tfc.Render.Models.ModelHorseTFC;
import com.bioxx.tfc.Render.Models.ModelPheasant;
import com.bioxx.tfc.Render.Models.ModelSquidTFC;
import com.bioxx.tfc.Render.Models.ModelWolfTFC;
import com.bioxx.tfc.Render.RenderBarrelEntity;
import com.bioxx.tfc.Render.RenderBear;
import com.bioxx.tfc.Render.RenderChickenTFC;
import com.bioxx.tfc.Render.RenderDeer;
import com.bioxx.tfc.Render.RenderEntityStand;
import com.bioxx.tfc.Render.RenderFallingBlock;
import com.bioxx.tfc.Render.RenderFishTFC;
import com.bioxx.tfc.Render.RenderHorseTFC;
import com.bioxx.tfc.Render.RenderPheasantTFC;
import com.bioxx.tfc.Render.RenderSkeletonTFC;
import com.bioxx.tfc.Render.RenderSquidTFC;
import com.bioxx.tfc.Render.RenderTerraJavelin;
import com.bioxx.tfc.Render.RenderWolfTFC;
import com.bioxx.tfc.Render.TESR.TESRAnvil;
import com.bioxx.tfc.Render.TESR.TESRBellows;
import com.bioxx.tfc.Render.TESR.TESRChest;
import com.bioxx.tfc.Render.TESR.TESRFirepit;
import com.bioxx.tfc.Render.TESR.TESRFoodPrep;
import com.bioxx.tfc.Render.TESR.TESRGrill;
import com.bioxx.tfc.Render.TESR.TESRHopper;
import com.bioxx.tfc.Render.TESR.TESRIngotPile;
import com.bioxx.tfc.Render.TESR.TESRLoom;
import com.bioxx.tfc.Render.TESR.TESRPottery;
import com.bioxx.tfc.Render.TESR.TESRQuern;
import com.bioxx.tfc.Render.TESR.TESRSmokeRack;
import com.bioxx.tfc.Render.TESR.TESRToolrack;
import com.bioxx.tfc.Render.TESR.TESRWorldItem;
import com.bioxx.tfc.TileEntities.TEAnvil;
import com.bioxx.tfc.TileEntities.TEBellows;
import com.bioxx.tfc.TileEntities.TEChest;
import com.bioxx.tfc.TileEntities.TEFirepit;
import com.bioxx.tfc.TileEntities.TEFoodPrep;
import com.bioxx.tfc.TileEntities.TEGrill;
import com.bioxx.tfc.TileEntities.TEHopper;
import com.bioxx.tfc.TileEntities.TEIngotPile;
import com.bioxx.tfc.TileEntities.TELoom;
import com.bioxx.tfc.TileEntities.TEPottery;
import com.bioxx.tfc.TileEntities.TEQuern;
import com.bioxx.tfc.TileEntities.TESmokeRack;
import com.bioxx.tfc.TileEntities.TEToolRack;
import com.bioxx.tfc.TileEntities.TEWorldItem;
import com.bioxx.tfc.api.Enums.EnumTree;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCFluids;
import com.bioxx.tfc.api.Util.KeyBindings;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderBlaze;
import net.minecraft.client.renderer.entity.RenderEnderman;
import net.minecraft.client.renderer.entity.RenderFish;
import net.minecraft.client.renderer.entity.RenderGhast;
import net.minecraft.client.renderer.entity.RenderIronGolem;
import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.client.renderer.entity.RenderSilverfish;
import net.minecraft.client.renderer.entity.RenderSlime;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
  public void registerFluidIcons() {
    TFCFluids.LAVA.setIcons(TFCBlocks.lava.func_149691_a(0, 0), TFCBlocks.lava.func_149691_a(2, 0));
    TFCFluids.SALTWATER.setIcons(TFCBlocks.saltWater.func_149691_a(0, 0), TFCBlocks.saltWater.func_149691_a(2, 0));
    TFCFluids.FRESHWATER.setIcons(TFCBlocks.freshWater.func_149691_a(0, 0), TFCBlocks.freshWater.func_149691_a(2, 0));
  }



  @SideOnly(Side.CLIENT)
  public void registerRenderInformation() {
    (Minecraft.func_71410_x()).field_71460_t = (EntityRenderer)new EntityRendererTFC(Minecraft.func_71410_x(), Minecraft.func_71410_x().func_110442_L());
    IReloadableResourceManager iRRM = (IReloadableResourceManager)Minecraft.func_71410_x().func_110442_L();
    iRRM.func_110542_a((IResourceManagerReloadListener)new GrassColorReloadListener());
    iRRM.func_110542_a((IResourceManagerReloadListener)new FoliageColorReloadListener());
    iRRM.func_110542_a((IResourceManagerReloadListener)(Minecraft.func_71410_x()).field_71460_t);



    RenderingRegistry.registerEntityRenderingHandler(EntityJavelin.class, (Render)new RenderTerraJavelin());
    RenderingRegistry.registerEntityRenderingHandler(EntitySquidTFC.class, (Render)new RenderSquidTFC((ModelBase)new ModelSquidTFC(), 0.7F));


    RenderingRegistry.registerEntityRenderingHandler(EntityWolfTFC.class, (Render)new RenderWolfTFC((ModelBase)new ModelWolfTFC(), (ModelBase)new ModelWolfTFC(), 0.5F));
    RenderingRegistry.registerEntityRenderingHandler(EntityBear.class, (Render)new RenderBear((ModelBase)new ModelBear(), 0.9F));
    RenderingRegistry.registerEntityRenderingHandler(EntityPheasantTFC.class, (Render)new RenderPheasantTFC((ModelBase)new ModelPheasant(), 0.3F));
    RenderingRegistry.registerEntityRenderingHandler(EntityChickenTFC.class, (Render)new RenderChickenTFC((ModelBase)new ModelChickenTFC(), 0.3F));

    RenderingRegistry.registerEntityRenderingHandler(EntityDeer.class, (Render)new RenderDeer((ModelBase)new ModelDeer(), 0.9F));
    RenderingRegistry.registerEntityRenderingHandler(EntityHorseTFC.class, (Render)new RenderHorseTFC((ModelBase)new ModelHorseTFC(), 0.9F));
    RenderingRegistry.registerEntityRenderingHandler(EntityCustomMinecart.class, (Render)new RenderMinecart());
    RenderingRegistry.registerEntityRenderingHandler(EntityStand.class, (Render)new RenderEntityStand());
    RenderingRegistry.registerEntityRenderingHandler(EntityFishTFC.class, (Render)new RenderFishTFC((ModelBase)new ModelBass(), 0.7F));
    RenderingRegistry.registerEntityRenderingHandler(EntitySkeletonTFC.class, (Render)new RenderSkeletonTFC());
    RenderingRegistry.registerEntityRenderingHandler(EntityZombieTFC.class, (Render)new RenderZombie());
    RenderingRegistry.registerEntityRenderingHandler(EntitySpiderTFC.class, (Render)new RenderSpider());
    RenderingRegistry.registerEntityRenderingHandler(EntitySlimeTFC.class, (Render)new RenderSlime((ModelBase)new ModelSlime(16), (ModelBase)new ModelSlime(0), 0.25F));
    RenderingRegistry.registerEntityRenderingHandler(EntitySilverfishTFC.class, (Render)new RenderSilverfish());
    RenderingRegistry.registerEntityRenderingHandler(EntityGhastTFC.class, (Render)new RenderGhast());
    RenderingRegistry.registerEntityRenderingHandler(EntityCaveSpiderTFC.class, (Render)new RenderSpider());
    RenderingRegistry.registerEntityRenderingHandler(EntityBlazeTFC.class, (Render)new RenderBlaze());
    RenderingRegistry.registerEntityRenderingHandler(EntityEndermanTFC.class, (Render)new RenderEnderman());
    RenderingRegistry.registerEntityRenderingHandler(EntityPigZombieTFC.class, (Render)new RenderZombie());
    RenderingRegistry.registerEntityRenderingHandler(EntityIronGolemTFC.class, (Render)new RenderIronGolem());

    RenderingRegistry.registerEntityRenderingHandler(EntityProjectileTFC.class, (Render)new RenderArrow());
    RenderingRegistry.registerEntityRenderingHandler(EntityFishHookTFC.class, (Render)new RenderFish());
    RenderingRegistry.registerEntityRenderingHandler(EntityBarrel.class, (Render)new RenderBarrelEntity());
    RenderingRegistry.registerEntityRenderingHandler(EntityFallingBlockTFC.class, (Render)new RenderFallingBlock());

    RenderingRegistry.registerBlockHandler(TFCBlocks.chestRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderChest());
    RenderingRegistry.registerBlockHandler(TFCBlocks.clayGrassRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
    RenderingRegistry.registerBlockHandler(TFCBlocks.peatGrassRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
    RenderingRegistry.registerBlockHandler(TFCBlocks.sulfurRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
    RenderingRegistry.registerBlockHandler(TFCBlocks.woodSupportRenderIdH = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderSupportBeam());
    RenderingRegistry.registerBlockHandler(TFCBlocks.woodSupportRenderIdV = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderSupportBeam());
    RenderingRegistry.registerBlockHandler(TFCBlocks.grassRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
    RenderingRegistry.registerBlockHandler(TFCBlocks.oreRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderOre());
    RenderingRegistry.registerBlockHandler(TFCBlocks.moltenRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
    RenderingRegistry.registerBlockHandler(TFCBlocks.looseRockRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
    RenderingRegistry.registerBlockHandler(TFCBlocks.firepitRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
    RenderingRegistry.registerBlockHandler(TFCBlocks.anvilRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderAnvil());
    RenderingRegistry.registerBlockHandler(TFCBlocks.bellowsRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderBellows());
    RenderingRegistry.registerBlockHandler(TFCBlocks.forgeRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
    RenderingRegistry.registerBlockHandler(TFCBlocks.sluiceRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
    RenderingRegistry.registerBlockHandler(TFCBlocks.woodFruitRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
    RenderingRegistry.registerBlockHandler(TFCBlocks.leavesFruitRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
    RenderingRegistry.registerBlockHandler(TFCBlocks.stairRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
    RenderingRegistry.registerBlockHandler(TFCBlocks.slabRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
    RenderingRegistry.registerBlockHandler(TFCBlocks.cropRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
    RenderingRegistry.registerBlockHandler(TFCBlocks.cookingPitRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
    RenderingRegistry.registerBlockHandler(TFCBlocks.leavesRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
    RenderingRegistry.registerBlockHandler(TFCBlocks.detailedRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
    RenderingRegistry.registerBlockHandler(TFCBlocks.wallRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderWall());
    RenderingRegistry.registerBlockHandler(TFCBlocks.fenceRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderFence());
    RenderingRegistry.registerBlockHandler(TFCBlocks.fenceGateRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderFenceGate());
    RenderingRegistry.registerBlockHandler(TFCBlocks.toolRackRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderToolRack());

    RenderingRegistry.registerBlockHandler(TFCBlocks.quernRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new TESRQuern());
    RenderingRegistry.registerBlockHandler(TFCBlocks.woodConstructRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderWoodConstruct());
    RenderingRegistry.registerBlockHandler(TFCBlocks.barrelRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderBarrel());
    RenderingRegistry.registerBlockHandler(TFCBlocks.loomRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderLoom());
    RenderingRegistry.registerBlockHandler(TFCBlocks.standRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderStand());
    RenderingRegistry.registerBlockHandler(TFCBlocks.nestBoxRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderNestBox());
    RenderingRegistry.registerBlockHandler(TFCBlocks.potteryRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderPottery());
    RenderingRegistry.registerBlockHandler(TFCBlocks.tuyereRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderTuyere());
    RenderingRegistry.registerBlockHandler(TFCBlocks.crucibleRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderCrucible());
    RenderingRegistry.registerBlockHandler(TFCBlocks.waterPlantRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());

    RenderingRegistry.registerBlockHandler(TFCBlocks.bloomeryRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderBloomery());
    RenderingRegistry.registerBlockHandler(TFCBlocks.metalsheetRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderMetalSheet());
    RenderingRegistry.registerBlockHandler(TFCBlocks.leatherRackRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderLeatherRack());
    RenderingRegistry.registerBlockHandler(TFCBlocks.grillRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderGrill());
    RenderingRegistry.registerBlockHandler(TFCBlocks.metalTrapDoorRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderMetalTrapDoor());
    RenderingRegistry.registerBlockHandler(TFCBlocks.vesselRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderVessel());
    RenderingRegistry.registerBlockHandler(TFCBlocks.torchRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderTorch());
    RenderingRegistry.registerBlockHandler(TFCBlocks.smokeRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderSmoke());
    RenderingRegistry.registerBlockHandler(TFCBlocks.smokeRackRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderSmokeRack());
    RenderingRegistry.registerBlockHandler(TFCBlocks.oilLampRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderOilLamp());
    RenderingRegistry.registerBlockHandler(TFCBlocks.hopperRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderHopper());
    RenderingRegistry.registerBlockHandler(TFCBlocks.flowerPotRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderFlowerPot());

    RenderingRegistry.registerBlockHandler(TFCBlocks.wattleRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderWattle());

    MinecraftForge.EVENT_BUS.register(new RenderOverlayHandler());


    RenderingRegistry.registerBlockHandler(TerraFirmaCraft.renderfoodprep = RenderingRegistry.getNextAvailableRenderId(), new RenderFoodPrep());
  }




  @SideOnly(Side.CLIENT)
  public void registerBiomeEventHandler() {
    MinecraftForge.EVENT_BUS.register(new BiomeEventHandler());
  }



  @SideOnly(Side.CLIENT)
  public void setupGuiIngameForge() {
    GuiIngameForge.renderHealth = false;
    GuiIngameForge.renderArmor = false;
    GuiIngameForge.renderExperiance = false;
  }




  public void registerTileEntities(boolean b) {
    super.registerTileEntities(false);
    ClientRegistry.registerTileEntity(TEChest.class, "chest", (TileEntitySpecialRenderer)new TESRChest());
    ClientRegistry.registerTileEntity(TEIngotPile.class, "ingotPile", (TileEntitySpecialRenderer)new TESRIngotPile());
    ClientRegistry.registerTileEntity(TEFirepit.class, "TerraFirepit", (TileEntitySpecialRenderer)new TESRFirepit());
    ClientRegistry.registerTileEntity(TELoom.class, "Loom", (TileEntitySpecialRenderer)new TESRLoom());

    ClientRegistry.registerTileEntity(TEPottery.class, "Pottery", (TileEntitySpecialRenderer)new TESRPottery());
    ClientRegistry.registerTileEntity(TEFoodPrep.class, "FoodPrep", (TileEntitySpecialRenderer)new TESRFoodPrep());
    ClientRegistry.registerTileEntity(TEBellows.class, "Bellows", (TileEntitySpecialRenderer)new TESRBellows());
    ClientRegistry.registerTileEntity(TEToolRack.class, "ToolRack", (TileEntitySpecialRenderer)new TESRToolrack());
    ClientRegistry.registerTileEntity(TEAnvil.class, "TerraAnvil", (TileEntitySpecialRenderer)new TESRAnvil());
    ClientRegistry.registerTileEntity(TEWorldItem.class, "worldItem", (TileEntitySpecialRenderer)new TESRWorldItem());
    ClientRegistry.registerTileEntity(TEQuern.class, "Quern", (TileEntitySpecialRenderer)new TESRQuern());
    ClientRegistry.registerTileEntity(TEGrill.class, "GrillTESR", (TileEntitySpecialRenderer)new TESRGrill());
    ClientRegistry.registerTileEntity(TESmokeRack.class, "SmokeRackTESR", (TileEntitySpecialRenderer)new TESRSmokeRack());
    ClientRegistry.registerTileEntity(TEHopper.class, "HopperTESR", (TileEntitySpecialRenderer)new TESRHopper());
  }





  public void onClientLogin() {}





  public World getCurrentWorld() {
    return (World)(Minecraft.func_71410_x()).field_71441_e;
  }



  public boolean isRemote() {
    return true;
  }



  public int waterColorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    return 3493173;
  }



  public int grassColorMultiplier(IBlockAccess par1IBlockAccess, int i, int j, int k) {
    int var5 = 0;
    int var6 = 0;
    int var7 = 0;

    for (int z = -1; z <= 1; z++) {

      for (int x = -1; x <= 1; x++) {

        int var10 = TFC_Climate.getGrassColor(getCurrentWorld(), i + x, j, k + z);
        var5 += (var10 & 0xFF0000) >> 16;
        var6 += (var10 & 0xFF00) >> 8;
        var7 += var10 & 0xFF;
      }
    }
    return (var5 / 9 & 0xFF) << 16 | (var6 / 9 & 0xFF) << 8 | var7 / 9 & 0xFF;
  }






  public int foliageColorMultiplier(IBlockAccess par1IBlockAccess, int i, int j, int k) {
    int[] rgb = { 0, 0, 0 };
    float temperature = TFC_Climate.getHeightAdjustedTempSpecificDay(getCurrentWorld(), TFC_Time.getDayOfYear(), i, j, k);


    int meta = par1IBlockAccess.func_72805_g(i, j, k);
    if (par1IBlockAccess.func_147439_a(i, j, k) == TFCBlocks.fruitTreeLeaves) {










      for (int m = -1; m <= 1; m++) {

        for (int var9 = -1; var9 <= 1; var9++) {

          int var10 = TFC_Climate.getFoliageColor(getCurrentWorld(), i + m, j, k + var9);
          rgb = applyColor(var10, rgb);
        }
      }
      return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
    }
    if (par1IBlockAccess.func_147439_a(i, j, k) == TFCBlocks.vine) {

      if (TFC_Time.getSeasonAdjustedMonth(k) >= 6 && TFC_Time.getSeasonAdjustedMonth(k) < 9 &&
        (TFC_Climate.getCacheManager(getCurrentWorld()).getEVTLayerAt(i, k)).floatdata1 >= 0.8D &&
        TFC_Climate.getHeightAdjustedTemp(getCurrentWorld(), i, j, k) < 30.0F) {

        int color = 0;
        for (int n = -1; n <= 1; n++) {

          for (int var9 = -1; var9 <= 1; var9++) {

            color = TFC_Climate.getFoliageColor(getCurrentWorld(), i + n, j, k + var9);
            rgb = applyColor(color, rgb);
          }
        }
        return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
      }
      if (TFC_Time.getSeasonAdjustedMonth(k) >= 11 || (TFC_Time.getSeasonAdjustedMonth(k) <= 0 &&
        (TFC_Climate.getCacheManager(getCurrentWorld()).getEVTLayerAt(i, k)).floatdata1 >= 0.8D &&
        TFC_Climate.getHeightAdjustedTemp(getCurrentWorld(), i, j, k) < 30.0F)) {

        for (int n = -1; n <= 1; n++) {

          for (int var9 = -1; var9 <= 1; var9++) {

            int color = TFC_Climate.getFoliageColor(getCurrentWorld(), i + n, j, k + var9);
            rgb = applyColor(color, rgb);
          }
        }
        return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
      }
      if (TFC_Time.getSeasonAdjustedMonth(k) >= 9 &&
        (TFC_Climate.getCacheManager(getCurrentWorld()).getEVTLayerAt(i, k)).floatdata1 >= 0.8D &&
        TFC_Climate.getHeightAdjustedTemp(getCurrentWorld(), i, j, k) < 30.0F) {

        for (int n = -1; n <= 1; n++) {

          for (int var9 = -1; var9 <= 1; var9++) {

            int color = ColorizerFoliageTFC.getFoliageDead();
            rgb = applyColor(color, rgb);
          }
        }
        return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
      }


      for (int m = -1; m <= 1; m++) {

        for (int var9 = -1; var9 <= 1; var9++) {

          int color = TFC_Climate.getFoliageColor(getCurrentWorld(), i + m, j, k + var9);
          rgb = applyColor(color, rgb);
        }
      }
      return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
    }

    if (TFC_Time.getSeasonAdjustedMonth(k) >= 6 && (EnumTree.values()[meta]).isEvergreen) {

      for (int m = -1; m <= 1; m++) {

        for (int var9 = -1; var9 <= 1; var9++) {

          int var10 = TFC_Climate.getFoliageColorEvergreen(getCurrentWorld(), i + m, j, k + var9);
          rgb = applyColor(var10, rgb);
        }
      }
      return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
    }
    if (temperature <= 10.0F && TFC_Time.getSeasonAdjustedMonth(k) >= 6 && TFC_Time.getSeasonAdjustedMonth(k) < 9 && (meta == 4 || meta == 7 || meta == 5 || meta == 14)) {

      int color = 0;

      for (int m = -1; m <= 1; m++) {

        for (int var9 = -1; var9 <= 1; var9++) {

          color = ColorizerFoliageTFC.getFoliageYellow();
          rgb = applyColor(color, rgb);
        }
      }
      return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
    }
    if (temperature <= 10.0F && TFC_Time.getSeasonAdjustedMonth(k) >= 6 && TFC_Time.getSeasonAdjustedMonth(k) < 9 && meta == 6) {

      for (int m = -1; m <= 1; m++) {

        for (int var9 = -1; var9 <= 1; var9++) {

          int var10 = ColorizerFoliageTFC.getFoliageRed();
          rgb = applyColor(var10, rgb);
        }
      }
      return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
    }
    if (temperature <= 10.0F && TFC_Time.getSeasonAdjustedMonth(k) >= 6 && TFC_Time.getSeasonAdjustedMonth(k) < 9 && meta != 15) {

      for (int m = -1; m <= 1; m++) {

        for (int var9 = -1; var9 <= 1; var9++) {

          int var10 = ColorizerFoliageTFC.getFoliageOrange();
          rgb = applyColor(var10, rgb);
        }
      }
      return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
    }
    if (temperature <= 8.0F && TFC_Time.getSeasonAdjustedMonth(k) >= 6 && meta != 15) {

      for (int m = -1; m <= 1; m++) {

        for (int var9 = -1; var9 <= 1; var9++) {

          int var10 = ColorizerFoliageTFC.getFoliageDead();
          rgb = applyColor(var10, rgb);
        }
      }
      return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
    }


    for (int var8 = -1; var8 <= 1; var8++) {

      for (int var9 = -1; var9 <= 1; var9++) {

        int var10 = TFC_Climate.getFoliageColor(getCurrentWorld(), i + var8, j, k + var9);
        rgb = applyColor(var10, rgb);
      }
    }
    return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
  }









  private int[] applyColor(int c, int[] rgb) {
    rgb[0] = rgb[0] + ((c & 0xFF0000) >> 16);
    rgb[1] = rgb[1] + ((c & 0xFF00) >> 8);
    rgb[2] = rgb[2] + (c & 0xFF);
    return rgb;
  }



  public int getArmorRenderID(String name) {
    return RenderingRegistry.addNewArmourRendererPrefix(name);
  }



  public void registerKeys() {
    KeyBindings.addKeyBinding(KeyBindingHandler.keyToolMode);
    KeyBindings.addIsRepeating(false);
    KeyBindings.addKeyBinding(KeyBindingHandler.keyLockTool);
    KeyBindings.addIsRepeating(false);


    uploadKeyBindingsToGame();
  }



  public void registerKeyBindingHandler() {
    FMLCommonHandler.instance().bus().register(new KeyBindingHandler());
  }



  public void uploadKeyBindingsToGame() {
    GameSettings settings = (Minecraft.func_71410_x()).field_71474_y;
    KeyBinding[] tfcKeyBindings = KeyBindings.gatherKeyBindings();
    KeyBinding[] allKeys = new KeyBinding[settings.field_74324_K.length + tfcKeyBindings.length];
    System.arraycopy(settings.field_74324_K, 0, allKeys, 0, settings.field_74324_K.length);
    System.arraycopy(tfcKeyBindings, 0, allKeys, settings.field_74324_K.length, tfcKeyBindings.length);
    settings.field_74324_K = allKeys;
    settings.func_74300_a();
  }



  public void registerHandlers() {
    MinecraftForge.EVENT_BUS.register(new ChiselHighlightHandler());
    MinecraftForge.EVENT_BUS.register(new FarmlandHighlightHandler());
    MinecraftForge.EVENT_BUS.register(new PlankHighlightHandler());
    MinecraftForge.EVENT_BUS.register(new ArmourStandHighlightHandler());
    MinecraftForge.EVENT_BUS.register(new FamiliarityHighlightHandler());
    MinecraftForge.EVENT_BUS.register(new FogHandler());
  }




  public void registerPlayerRenderEventHandler() {
    PlayerRenderHandler pRHandler = new PlayerRenderHandler();
    MinecraftForge.EVENT_BUS.register(pRHandler);
    FMLCommonHandler.instance().bus().register(pRHandler);
  }



  public void registerSoundHandler() {
    MinecraftForge.EVENT_BUS.register(new SoundHandler());
  }



  public void registerTickHandler() {
    super.registerTickHandler();
    FMLCommonHandler.instance().bus().register(new ClientTickHandler());
    FMLCommonHandler.instance().bus().register(new FMLClientEventHandler());
  }



  public void registerGuiHandler() {
    NetworkRegistry.INSTANCE.registerGuiHandler(TerraFirmaCraft.instance, (IGuiHandler)new GuiHandler());

    MinecraftForge.EVENT_BUS.register(new GuiHandler());
  }



  public String getCurrentLanguage() {
    return Minecraft.func_71410_x().func_135016_M().func_135041_c().func_135034_a();
  }



  public boolean getGraphicsLevel() {
    Minecraft.func_71410_x();
    return Minecraft.func_71375_t();
  }



  public void hideNEIItems() {
    if (Loader.isModLoaded("NotEnoughItems")) NEIIntegration.hideNEIItems();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */