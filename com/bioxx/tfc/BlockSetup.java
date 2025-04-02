package com.bioxx.tfc;

import com.bioxx.tfc.Blocks.BlockBloom;
import com.bioxx.tfc.Blocks.BlockCharcoal;
import com.bioxx.tfc.Blocks.BlockCrop;
import com.bioxx.tfc.Blocks.BlockDetailed;
import com.bioxx.tfc.Blocks.BlockFarmland;
import com.bioxx.tfc.Blocks.BlockFireBrick;
import com.bioxx.tfc.Blocks.BlockIngotPile;
import com.bioxx.tfc.Blocks.BlockLogPile;
import com.bioxx.tfc.Blocks.BlockMetalSheet;
import com.bioxx.tfc.Blocks.BlockMetalTrapDoor;
import com.bioxx.tfc.Blocks.BlockMolten;
import com.bioxx.tfc.Blocks.BlockPlanks;
import com.bioxx.tfc.Blocks.BlockPlanks2;
import com.bioxx.tfc.Blocks.BlockSlab;
import com.bioxx.tfc.Blocks.BlockSmoke;
import com.bioxx.tfc.Blocks.BlockSmokeRack;
import com.bioxx.tfc.Blocks.BlockStair;
import com.bioxx.tfc.Blocks.BlockStalactite;
import com.bioxx.tfc.Blocks.BlockSulfur;
import com.bioxx.tfc.Blocks.BlockThatch;
import com.bioxx.tfc.Blocks.BlockWattle;
import com.bioxx.tfc.Blocks.BlockWoodConstruct;
import com.bioxx.tfc.Blocks.BlockWoodSupport;
import com.bioxx.tfc.Blocks.BlockWoodSupport2;
import com.bioxx.tfc.Blocks.BlockWorldItem;
import com.bioxx.tfc.Blocks.Devices.BlockAnvil;
import com.bioxx.tfc.Blocks.Devices.BlockBarrel;
import com.bioxx.tfc.Blocks.Devices.BlockBellows;
import com.bioxx.tfc.Blocks.Devices.BlockBlastFurnace;
import com.bioxx.tfc.Blocks.Devices.BlockChestTFC;
import com.bioxx.tfc.Blocks.Devices.BlockCrucible;
import com.bioxx.tfc.Blocks.Devices.BlockEarlyBloomery;
import com.bioxx.tfc.Blocks.Devices.BlockFirepit;
import com.bioxx.tfc.Blocks.Devices.BlockFoodPrep;
import com.bioxx.tfc.Blocks.Devices.BlockForge;
import com.bioxx.tfc.Blocks.Devices.BlockGrill;
import com.bioxx.tfc.Blocks.Devices.BlockHopper;
import com.bioxx.tfc.Blocks.Devices.BlockLargeVessel;
import com.bioxx.tfc.Blocks.Devices.BlockLeatherRack;
import com.bioxx.tfc.Blocks.Devices.BlockLoom;
import com.bioxx.tfc.Blocks.Devices.BlockNestBox;
import com.bioxx.tfc.Blocks.Devices.BlockOilLamp;
import com.bioxx.tfc.Blocks.Devices.BlockPottery;
import com.bioxx.tfc.Blocks.Devices.BlockQuern;
import com.bioxx.tfc.Blocks.Devices.BlockSluice;
import com.bioxx.tfc.Blocks.Devices.BlockSpawnMeter;
import com.bioxx.tfc.Blocks.Devices.BlockStand;
import com.bioxx.tfc.Blocks.Devices.BlockStand2;
import com.bioxx.tfc.Blocks.Devices.BlockToolRack;
import com.bioxx.tfc.Blocks.Devices.BlockWorkbench;
import com.bioxx.tfc.Blocks.Flora.BlockBerryBush;
import com.bioxx.tfc.Blocks.Flora.BlockFlora;
import com.bioxx.tfc.Blocks.Flora.BlockFlower;
import com.bioxx.tfc.Blocks.Flora.BlockFlower2;
import com.bioxx.tfc.Blocks.Flora.BlockFruitLeaves;
import com.bioxx.tfc.Blocks.Flora.BlockFruitWood;
import com.bioxx.tfc.Blocks.Flora.BlockLogHoriz;
import com.bioxx.tfc.Blocks.Flora.BlockLogHoriz2;
import com.bioxx.tfc.Blocks.Flora.BlockLogNatural;
import com.bioxx.tfc.Blocks.Flora.BlockLogNatural2;
import com.bioxx.tfc.Blocks.Flora.BlockLogVert;
import com.bioxx.tfc.Blocks.Flora.BlockLogVert2;
import com.bioxx.tfc.Blocks.Flora.BlockSapling;
import com.bioxx.tfc.Blocks.Flora.BlockSapling2;
import com.bioxx.tfc.Blocks.Flora.BlockWaterPlant;
import com.bioxx.tfc.Blocks.Liquids.BlockFreshWater;
import com.bioxx.tfc.Blocks.Liquids.BlockHotWater;
import com.bioxx.tfc.Blocks.Liquids.BlockHotWaterStatic;
import com.bioxx.tfc.Blocks.Liquids.BlockLava;
import com.bioxx.tfc.Blocks.Liquids.BlockLiquidStatic;
import com.bioxx.tfc.Blocks.Liquids.BlockSaltWater;
import com.bioxx.tfc.Blocks.Terrain.BlockClay;
import com.bioxx.tfc.Blocks.Terrain.BlockClayGrass;
import com.bioxx.tfc.Blocks.Terrain.BlockDirt;
import com.bioxx.tfc.Blocks.Terrain.BlockDryGrass;
import com.bioxx.tfc.Blocks.Terrain.BlockFungi;
import com.bioxx.tfc.Blocks.Terrain.BlockGrass;
import com.bioxx.tfc.Blocks.Terrain.BlockGravel;
import com.bioxx.tfc.Blocks.Terrain.BlockIgEx;
import com.bioxx.tfc.Blocks.Terrain.BlockIgExBrick;
import com.bioxx.tfc.Blocks.Terrain.BlockIgExCobble;
import com.bioxx.tfc.Blocks.Terrain.BlockIgExSmooth;
import com.bioxx.tfc.Blocks.Terrain.BlockIgIn;
import com.bioxx.tfc.Blocks.Terrain.BlockIgInBrick;
import com.bioxx.tfc.Blocks.Terrain.BlockIgInCobble;
import com.bioxx.tfc.Blocks.Terrain.BlockIgInSmooth;
import com.bioxx.tfc.Blocks.Terrain.BlockMM;
import com.bioxx.tfc.Blocks.Terrain.BlockMMBrick;
import com.bioxx.tfc.Blocks.Terrain.BlockMMCobble;
import com.bioxx.tfc.Blocks.Terrain.BlockMMSmooth;
import com.bioxx.tfc.Blocks.Terrain.BlockMoss;
import com.bioxx.tfc.Blocks.Terrain.BlockOre;
import com.bioxx.tfc.Blocks.Terrain.BlockOre2;
import com.bioxx.tfc.Blocks.Terrain.BlockOre3;
import com.bioxx.tfc.Blocks.Terrain.BlockPeat;
import com.bioxx.tfc.Blocks.Terrain.BlockPeatGrass;
import com.bioxx.tfc.Blocks.Terrain.BlockSand;
import com.bioxx.tfc.Blocks.Terrain.BlockSed;
import com.bioxx.tfc.Blocks.Terrain.BlockSedBrick;
import com.bioxx.tfc.Blocks.Terrain.BlockSedCobble;
import com.bioxx.tfc.Blocks.Terrain.BlockSedSmooth;
import com.bioxx.tfc.Blocks.Vanilla.BlockBed;
import com.bioxx.tfc.Blocks.Vanilla.BlockCustomBookshelf;
import com.bioxx.tfc.Blocks.Vanilla.BlockCustomButtonWood;
import com.bioxx.tfc.Blocks.Vanilla.BlockCustomCactus;
import com.bioxx.tfc.Blocks.Vanilla.BlockCustomDoor;
import com.bioxx.tfc.Blocks.Vanilla.BlockCustomFenceGate;
import com.bioxx.tfc.Blocks.Vanilla.BlockCustomFenceGate2;
import com.bioxx.tfc.Blocks.Vanilla.BlockCustomFlowerPot;
import com.bioxx.tfc.Blocks.Vanilla.BlockCustomIce;
import com.bioxx.tfc.Blocks.Vanilla.BlockCustomLeaves;
import com.bioxx.tfc.Blocks.Vanilla.BlockCustomLeaves2;
import com.bioxx.tfc.Blocks.Vanilla.BlockCustomLilyPad;
import com.bioxx.tfc.Blocks.Vanilla.BlockCustomPumpkin;
import com.bioxx.tfc.Blocks.Vanilla.BlockCustomReed;
import com.bioxx.tfc.Blocks.Vanilla.BlockCustomSnow;
import com.bioxx.tfc.Blocks.Vanilla.BlockCustomTallGrass;
import com.bioxx.tfc.Blocks.Vanilla.BlockCustomVine;
import com.bioxx.tfc.Blocks.Vanilla.BlockCustomWall;
import com.bioxx.tfc.Blocks.Vanilla.BlockCustomWattleDoor;
import com.bioxx.tfc.Blocks.Vanilla.BlockTFCFence;
import com.bioxx.tfc.Blocks.Vanilla.BlockTFCFence2;
import com.bioxx.tfc.Blocks.Vanilla.BlockTorch;
import com.bioxx.tfc.Blocks.Vanilla.BlockTorchOff;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Items.ItemBlocks.ItemAnvil1;
import com.bioxx.tfc.Items.ItemBlocks.ItemAnvil2;
import com.bioxx.tfc.Items.ItemBlocks.ItemArmourStand;
import com.bioxx.tfc.Items.ItemBlocks.ItemArmourStand2;
import com.bioxx.tfc.Items.ItemBlocks.ItemBarrels;
import com.bioxx.tfc.Items.ItemBlocks.ItemBellows;
import com.bioxx.tfc.Items.ItemBlocks.ItemBerryBush;
import com.bioxx.tfc.Items.ItemBlocks.ItemChest;
import com.bioxx.tfc.Items.ItemBlocks.ItemCrucible;
import com.bioxx.tfc.Items.ItemBlocks.ItemCustomLilyPad;
import com.bioxx.tfc.Items.ItemBlocks.ItemCustomTallGrass;
import com.bioxx.tfc.Items.ItemBlocks.ItemCustomWood;
import com.bioxx.tfc.Items.ItemBlocks.ItemCustomWood2;
import com.bioxx.tfc.Items.ItemBlocks.ItemCustomWoodH;
import com.bioxx.tfc.Items.ItemBlocks.ItemCustomWoodH2;
import com.bioxx.tfc.Items.ItemBlocks.ItemCustomWoodH3;
import com.bioxx.tfc.Items.ItemBlocks.ItemFence;
import com.bioxx.tfc.Items.ItemBlocks.ItemFence2;
import com.bioxx.tfc.Items.ItemBlocks.ItemFenceGate;
import com.bioxx.tfc.Items.ItemBlocks.ItemFenceGate2;
import com.bioxx.tfc.Items.ItemBlocks.ItemFlora;
import com.bioxx.tfc.Items.ItemBlocks.ItemFlowers;
import com.bioxx.tfc.Items.ItemBlocks.ItemFlowers2;
import com.bioxx.tfc.Items.ItemBlocks.ItemFungi;
import com.bioxx.tfc.Items.ItemBlocks.ItemGrill;
import com.bioxx.tfc.Items.ItemBlocks.ItemLargeVessel;
import com.bioxx.tfc.Items.ItemBlocks.ItemLooms;
import com.bioxx.tfc.Items.ItemBlocks.ItemMetalTrapDoor;
import com.bioxx.tfc.Items.ItemBlocks.ItemOilLamp;
import com.bioxx.tfc.Items.ItemBlocks.ItemSapling;
import com.bioxx.tfc.Items.ItemBlocks.ItemSapling2;
import com.bioxx.tfc.Items.ItemBlocks.ItemSoil;
import com.bioxx.tfc.Items.ItemBlocks.ItemStone;
import com.bioxx.tfc.Items.ItemBlocks.ItemTerraBlock;
import com.bioxx.tfc.Items.ItemBlocks.ItemToolRack;
import com.bioxx.tfc.Items.ItemBlocks.ItemTorch;
import com.bioxx.tfc.Items.ItemBlocks.ItemVine;
import com.bioxx.tfc.Items.ItemBlocks.ItemWoodSupport;
import com.bioxx.tfc.Items.ItemBlocks.ItemWoodSupport2;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCFluids;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;


public class BlockSetup
  extends TFCBlocks
{
  public static void registerBlocks() {
    GameRegistry.registerBlock(ore, "Ore1");
    GameRegistry.registerBlock(ore2, "Ore2");
    GameRegistry.registerBlock(ore3, "Ore3");
    GameRegistry.registerBlock(stoneIgIn, ItemStone.class, "StoneIgIn");
    GameRegistry.registerBlock(stoneIgEx, ItemStone.class, "StoneIgEx");
    GameRegistry.registerBlock(stoneSed, ItemStone.class, "StoneSed");
    GameRegistry.registerBlock(stoneMM, ItemStone.class, "StoneMM");

    GameRegistry.registerBlock(stoneIgInCobble, ItemStone.class, "StoneIgInCobble");
    GameRegistry.registerBlock(stoneIgExCobble, ItemStone.class, "StoneIgExCobble");
    GameRegistry.registerBlock(stoneSedCobble, ItemStone.class, "StoneSedCobble");
    GameRegistry.registerBlock(stoneMMCobble, ItemStone.class, "StoneMMCobble");
    GameRegistry.registerBlock(stoneIgInSmooth, ItemStone.class, "StoneIgInSmooth");
    GameRegistry.registerBlock(stoneIgExSmooth, ItemStone.class, "StoneIgExSmooth");
    GameRegistry.registerBlock(stoneSedSmooth, ItemStone.class, "StoneSedSmooth");
    GameRegistry.registerBlock(stoneMMSmooth, ItemStone.class, "StoneMMSmooth");
    GameRegistry.registerBlock(stoneIgInBrick, ItemStone.class, "StoneIgInBrick");
    GameRegistry.registerBlock(stoneIgExBrick, ItemStone.class, "StoneIgExBrick");
    GameRegistry.registerBlock(stoneSedBrick, ItemStone.class, "StoneSedBrick");
    GameRegistry.registerBlock(stoneMMBrick, ItemStone.class, "StoneMMBrick");

    GameRegistry.registerBlock(dirt, ItemSoil.class, "Dirt");
    GameRegistry.registerBlock(dirt2, ItemSoil.class, "Dirt2");
    GameRegistry.registerBlock(sand, ItemSoil.class, "Sand");
    GameRegistry.registerBlock(sand2, ItemSoil.class, "Sand2");
    GameRegistry.registerBlock(clay, ItemSoil.class, "Clay");
    GameRegistry.registerBlock(clay2, ItemSoil.class, "Clay2");
    GameRegistry.registerBlock(grass, ItemSoil.class, "Grass");
    GameRegistry.registerBlock(grass2, ItemSoil.class, "Grass2");
    GameRegistry.registerBlock(clayGrass, ItemSoil.class, "ClayGrass");
    GameRegistry.registerBlock(clayGrass2, ItemSoil.class, "ClayGrass2");
    GameRegistry.registerBlock(peatGrass, ItemSoil.class, "PeatGrass");
    GameRegistry.registerBlock(peat, ItemSoil.class, "Peat");
    GameRegistry.registerBlock(dryGrass, ItemSoil.class, "DryGrass");
    GameRegistry.registerBlock(dryGrass2, ItemSoil.class, "DryGrass2");
    GameRegistry.registerBlock(tallGrass, ItemCustomTallGrass.class, "TallGrass");
    GameRegistry.registerBlock(worldItem, "LooseRock");
    GameRegistry.registerBlock(logPile, "LogPile");
    GameRegistry.registerBlock(charcoal, "Charcoal");
    GameRegistry.registerBlock(detailed, "Detailed");

    GameRegistry.registerBlock(tilledSoil, ItemSoil.class, "tilledSoil");
    GameRegistry.registerBlock(tilledSoil2, ItemSoil.class, "tilledSoil2");

    GameRegistry.registerBlock(woodSupportV, ItemWoodSupport.class, "WoodSupportV");
    GameRegistry.registerBlock(woodSupportH, ItemWoodSupport.class, "WoodSupportH");
    GameRegistry.registerBlock(woodSupportV2, ItemWoodSupport2.class, "WoodSupportV2");
    GameRegistry.registerBlock(woodSupportH2, ItemWoodSupport2.class, "WoodSupportH2");
    GameRegistry.registerBlock(sulfur, "Sulfur");
    GameRegistry.registerBlock(logNatural, ItemCustomWood.class, "log");
    GameRegistry.registerBlock(logNatural2, ItemCustomWood2.class, "log2");
    GameRegistry.registerBlock(leaves, ItemCustomWood.class, "leaves");
    GameRegistry.registerBlock(leaves2, ItemCustomWood2.class, "leaves2");
    GameRegistry.registerBlock(sapling, ItemSapling.class, "sapling");
    GameRegistry.registerBlock(sapling2, ItemSapling2.class, "sapling2");
    GameRegistry.registerBlock(planks, ItemCustomWood.class, "planks");
    GameRegistry.registerBlock(planks2, ItemCustomWood2.class, "planks2");

    GameRegistry.registerBlock(firepit, "Firepit");
    GameRegistry.registerBlock(bellows, ItemBellows.class, "Bellows");
    GameRegistry.registerBlock(anvil, ItemAnvil1.class, "Anvil");
    GameRegistry.registerBlock(anvil2, ItemAnvil2.class, "Anvil2");
    GameRegistry.registerBlock(forge, "Forge");

    GameRegistry.registerBlock(molten, "Molten");
    GameRegistry.registerBlock(blastFurnace, ItemTerraBlock.class, "Bloomery");
    GameRegistry.registerBlock(bloomery, ItemTerraBlock.class, "EarlyBloomery");
    GameRegistry.registerBlock(sluice, "Sluice");
    GameRegistry.registerBlock(bloom, "Bloom");

    GameRegistry.registerBlock(fruitTreeWood, "fruitTreeWood");
    GameRegistry.registerBlock(fruitTreeLeaves, "fruitTreeLeaves");
    GameRegistry.registerBlock(fruitTreeLeaves2, "fruitTreeLeaves2");

    GameRegistry.registerBlock(stoneStairs, "stoneStairs");
    GameRegistry.registerBlock(stoneSlabs, "stoneSlabs");
    GameRegistry.registerBlock(stoneStalac, "stoneStalac");

    GameRegistry.registerBlock(woodConstruct, "WoodConstruct");
    GameRegistry.registerBlock(woodVert, ItemCustomWood.class, "WoodVert");
    GameRegistry.registerBlock(woodVert2, ItemCustomWood2.class, "WoodVert2");
    GameRegistry.registerBlock(woodHoriz, ItemCustomWoodH.class, "WoodHoriz");
    GameRegistry.registerBlock(woodHoriz2, ItemCustomWoodH2.class, "WoodHoriz2");
    GameRegistry.registerBlock(woodHoriz3, ItemCustomWoodH3.class, "WoodHoriz3");
    GameRegistry.registerBlock(woodHoriz4, "WoodHoriz4");

    GameRegistry.registerBlock(toolRack, ItemToolRack.class, "ToolRack");
    GameRegistry.registerBlock(spawnMeter, ItemTerraBlock.class, "SpawnMeter");
    GameRegistry.registerBlock(foodPrep, "FoodPrep");
    GameRegistry.registerBlock(quern, ItemTerraBlock.class, "Quern");
    GameRegistry.registerBlock(wallCobbleIgIn, ItemStone.class, "WallCobbleIgIn");
    GameRegistry.registerBlock(wallCobbleIgEx, ItemStone.class, "WallCobbleIgEx");
    GameRegistry.registerBlock(wallCobbleSed, ItemStone.class, "WallCobbleSed");
    GameRegistry.registerBlock(wallCobbleMM, ItemStone.class, "WallCobbleMM");
    GameRegistry.registerBlock(wallRawIgIn, ItemStone.class, "WallRawIgIn");
    GameRegistry.registerBlock(wallRawIgEx, ItemStone.class, "WallRawIgEx");
    GameRegistry.registerBlock(wallRawSed, ItemStone.class, "WallRawSed");
    GameRegistry.registerBlock(wallRawMM, ItemStone.class, "WallRawMM");
    GameRegistry.registerBlock(wallBrickIgIn, ItemStone.class, "WallBrickIgIn");
    GameRegistry.registerBlock(wallBrickIgEx, ItemStone.class, "WallBrickIgEx");
    GameRegistry.registerBlock(wallBrickSed, ItemStone.class, "WallBrickSed");
    GameRegistry.registerBlock(wallBrickMM, ItemStone.class, "WallBrickMM");
    GameRegistry.registerBlock(wallSmoothIgIn, ItemStone.class, "WallSmoothIgIn");
    GameRegistry.registerBlock(wallSmoothIgEx, ItemStone.class, "WallSmoothIgEx");
    GameRegistry.registerBlock(wallSmoothSed, ItemStone.class, "WallSmoothSed");
    GameRegistry.registerBlock(wallSmoothMM, ItemStone.class, "WallSmoothMM");

    GameRegistry.registerBlock(saltWater, "SaltWater");
    GameRegistry.registerBlock(saltWaterStationary, "SaltWaterStationary");
    GameRegistry.registerBlock(freshWater, "FreshWater");
    GameRegistry.registerBlock(freshWaterStationary, "FreshWaterStationary");
    GameRegistry.registerBlock(hotWater, "HotWater");
    GameRegistry.registerBlock(hotWaterStationary, "HotWaterStationary");
    GameRegistry.registerBlock(lava, "Lava");
    GameRegistry.registerBlock(lavaStationary, "LavaStationary");
    GameRegistry.registerBlock(ice, "Ice");

    GameRegistry.registerBlock(waterPlant, "SeaGrassStill");

    GameRegistry.registerBlock(fireBrick, "FireBrick");
    GameRegistry.registerBlock(metalSheet, "MetalSheet");


    for (int i = 0; i < Global.WOOD_ALL.length; i++) {
      GameRegistry.registerBlock(doors[i], "Door" + Global.WOOD_ALL[i].replaceAll(" ", ""));
    }

    GameRegistry.registerBlock(wattleDoor, "DoorWattle");

    GameRegistry.registerBlock(ingotPile, "IngotPile");
    GameRegistry.registerBlock(barrel, ItemBarrels.class, "Barrel");
    GameRegistry.registerBlock(loom, ItemLooms.class, "Loom");
    GameRegistry.registerBlock(moss, "Moss");

    GameRegistry.registerBlock(flora, ItemFlora.class, "Flora");
    GameRegistry.registerBlock(pottery, "ClayPottery");
    GameRegistry.registerBlock(thatch, ItemTerraBlock.class, "Thatch");
    GameRegistry.registerBlock(crucible, ItemCrucible.class, "Crucible");
    GameRegistry.registerBlock(nestBox, ItemTerraBlock.class, "NestBox");

    GameRegistry.registerBlock(wattle, "Wattle Wall");
    GameRegistry.registerBlock(fence, ItemFence.class, "Fence");
    GameRegistry.registerBlock(fence2, ItemFence2.class, "Fence2");
    GameRegistry.registerBlock(fenceGate, ItemFenceGate.class, "FenceGate");
    GameRegistry.registerBlock(fenceGate2, ItemFenceGate2.class, "FenceGate2");
    GameRegistry.registerBlock(strawHideBed, "StrawHideBed");
    GameRegistry.registerBlock(armorStand, ItemArmourStand.class, "ArmourStand");
    GameRegistry.registerBlock(armorStand2, ItemArmourStand2.class, "ArmourStand2");
    GameRegistry.registerBlock(berryBush, ItemBerryBush.class, "BerryBush");
    GameRegistry.registerBlock(crops, "Crops");
    GameRegistry.registerBlock(lilyPad, ItemCustomLilyPad.class, "LilyPad");
    GameRegistry.registerBlock(flowers, ItemFlowers.class, "Flowers");
    GameRegistry.registerBlock(flowers2, ItemFlowers2.class, "Flowers2");
    GameRegistry.registerBlock(fungi, ItemFungi.class, "Fungi");
    GameRegistry.registerBlock(bookshelf, ItemTerraBlock.class, "Bookshelf");
    GameRegistry.registerBlock(torch, ItemTorch.class, "Torch");
    GameRegistry.registerBlock(torchOff, "TorchOff");
    GameRegistry.registerBlock(chest, ItemChest.class, "Chest TFC");
    GameRegistry.registerBlock(workbench, ItemTerraBlock.class, "Workbench");
    GameRegistry.registerBlock(cactus, ItemTerraBlock.class, "Cactus");
    GameRegistry.registerBlock(reeds, "Reeds");
    GameRegistry.registerBlock(pumpkin, ItemTerraBlock.class, "Pumpkin");
    GameRegistry.registerBlock(litPumpkin, ItemTerraBlock.class, "LitPumpkin");
    GameRegistry.registerBlock(buttonWood, "ButtonWood");
    GameRegistry.registerBlock(vine, ItemVine.class, "Vine");
    GameRegistry.registerBlock(leatherRack, "LeatherRack");
    GameRegistry.registerBlock(gravel, ItemSoil.class, "Gravel");
    GameRegistry.registerBlock(gravel2, ItemSoil.class, "Gravel2");

    GameRegistry.registerBlock(grill, ItemGrill.class, "Grill");
    GameRegistry.registerBlock(metalTrapDoor, ItemMetalTrapDoor.class, "MetalTrapDoor");
    GameRegistry.registerBlock(vessel, ItemLargeVessel.class, "Vessel");
    GameRegistry.registerBlock(smoke, "Smoke");
    GameRegistry.registerBlock(smokeRack, "SmokeRack");
    GameRegistry.registerBlock(snow, "Snow");
    GameRegistry.registerBlock(oilLamp, ItemOilLamp.class, "OilLamp");
    GameRegistry.registerBlock(hopper, "Hopper");
    GameRegistry.registerBlock(flowerPot, "FlowerPot");
  }


  public static void loadBlocks() {
    TerraFirmaCraft.LOG.info("Loading Blocks");


    Blocks.field_150373_bw.func_149647_a(null);
    Blocks.field_150376_bx.func_149647_a(null);
    Blocks.field_150485_bF.func_149647_a(null);
    Blocks.field_150487_bG.func_149647_a(null);
    Blocks.field_150481_bH.func_149647_a(null);
    Blocks.field_150392_bi.func_149647_a(null);
    Blocks.field_150329_H.func_149647_a(null);
    Blocks.field_150327_N.func_149647_a(null);
    Blocks.field_150328_O.func_149647_a(null);
    Blocks.field_150338_P.func_149647_a(null);
    Blocks.field_150337_Q.func_149647_a(null);
    Blocks.field_150342_X.func_149647_a(null);
    Blocks.field_150478_aa.func_149647_a(null);
    Blocks.field_150486_ae.func_149647_a(null);
    Blocks.field_150344_f.func_149647_a(null);
    Blocks.field_150462_ai.func_149647_a(null);
    Blocks.field_150434_aF.func_149647_a(null);
    Blocks.field_150436_aH.func_149647_a(null);
    Blocks.field_150423_aK.func_149647_a(null);
    Blocks.field_150428_aP.func_149647_a(null);
    Blocks.field_150471_bO.func_149647_a(null);
    Blocks.field_150432_aD.func_149647_a(null);
    Blocks.field_150395_bd.func_149647_a(null);
    Blocks.field_150457_bL.func_149647_a(null);

    bookshelf = (new BlockCustomBookshelf()).func_149711_c(1.5F).func_149672_a(Block.field_149766_f).func_149663_c("Bookshelf").func_149658_d("bookshelf");
    torch = (new BlockTorch()).func_149711_c(0.0F).func_149672_a(Block.field_149766_f).func_149663_c("Torch").func_149658_d("torch_on");
    torchOff = (new BlockTorchOff()).func_149711_c(0.0F).func_149672_a(Block.field_149766_f).func_149663_c("TorchOff").func_149658_d("torch_on");
    chest = (new BlockChestTFC()).func_149711_c(2.5F).func_149672_a(Block.field_149766_f).func_149663_c("Chest");
    workbench = (new BlockWorkbench()).func_149711_c(2.5F).func_149672_a(Block.field_149766_f).func_149663_c("Workbench").func_149658_d("crafting_table");
    cactus = (new BlockCustomCactus()).func_149711_c(0.4F).func_149672_a(Block.field_149775_l).func_149663_c("Cactus").func_149658_d("cactus");
    reeds = (new BlockCustomReed()).func_149711_c(0.0F).func_149672_a(Block.field_149779_h).func_149663_c("Reeds").func_149658_d("reeds");
    pumpkin = (new BlockCustomPumpkin(false)).func_149711_c(1.0F).func_149672_a(Block.field_149766_f).func_149663_c("Pumpkin").func_149658_d("pumpkin");
    litPumpkin = (new BlockCustomPumpkin(true)).func_149711_c(1.0F).func_149672_a(Block.field_149766_f).func_149715_a(1.0F).func_149663_c("LitPumpkin").func_149658_d("pumpkin");
    buttonWood = (new BlockCustomButtonWood()).func_149711_c(0.5F).func_149672_a(Block.field_149766_f).func_149663_c("ButtonWood");
    vine = (new BlockCustomVine()).func_149711_c(0.2F).func_149672_a(Block.field_149779_h).func_149663_c("Vine").func_149658_d("vine");







    snow = (new BlockCustomSnow()).func_149711_c(0.1F).func_149672_a(Block.field_149773_n).func_149663_c("snow").func_149713_g(0).func_149658_d("snow");
    Blocks.field_150431_aC = snow;
    stoneIgInCobble = (new BlockIgInCobble(Material.field_151576_e)).func_149711_c(16.0F).func_149663_c("IgInRockCobble");
    stoneIgIn = (new BlockIgIn(Material.field_151576_e)).func_149711_c(8.0F).func_149663_c("IgInRock");
    stoneIgInSmooth = (new BlockIgInSmooth()).func_149711_c(16.0F).func_149663_c("IgInRockSmooth");
    stoneIgInBrick = (new BlockIgInBrick()).func_149711_c(16.0F).func_149663_c("IgInRockBrick");

    stoneSedCobble = (new BlockSedCobble(Material.field_151576_e)).func_149711_c(14.0F).func_149663_c("SedRockCobble");
    stoneSed = (new BlockSed(Material.field_151576_e)).func_149711_c(7.0F).func_149663_c("SedRock");
    stoneSedSmooth = (new BlockSedSmooth()).func_149711_c(14.0F).func_149663_c("SedRockSmooth");
    stoneSedBrick = (new BlockSedBrick()).func_149711_c(14.0F).func_149663_c("SedRockBrick");

    stoneIgExCobble = (new BlockIgExCobble(Material.field_151576_e)).func_149711_c(16.0F).func_149663_c("IgExRockCobble");
    stoneIgEx = (new BlockIgEx(Material.field_151576_e)).func_149711_c(8.0F).func_149663_c("IgExRock");
    stoneIgExSmooth = (new BlockIgExSmooth()).func_149711_c(16.0F).func_149663_c("IgExRockSmooth");
    stoneIgExBrick = (new BlockIgExBrick()).func_149711_c(16.0F).func_149663_c("IgExRockBrick");

    stoneMMCobble = (new BlockMMCobble(Material.field_151576_e)).func_149711_c(15.0F).func_149663_c("MMRockCobble");
    stoneMM = (new BlockMM(Material.field_151576_e)).func_149711_c(8.0F).func_149663_c("MMRock");
    stoneMMSmooth = (new BlockMMSmooth()).func_149711_c(15.0F).func_149663_c("MMRockSmooth");
    stoneMMBrick = (new BlockMMBrick()).func_149711_c(15.0F).func_149663_c("MMRockBrick");

    dirt = (new BlockDirt(0)).func_149711_c(2.0F).func_149672_a(Block.field_149767_g).func_149663_c("dirt");

    dirt2 = (new BlockDirt(16)).func_149711_c(2.0F).func_149672_a(Block.field_149767_g).func_149663_c("dirt");
    clay = (new BlockClay(0)).func_149711_c(3.0F).func_149672_a(Block.field_149767_g).func_149663_c("clay");
    clay2 = (new BlockClay(16)).func_149711_c(3.0F).func_149672_a(Block.field_149767_g).func_149663_c("clay");
    clayGrass = (new BlockClayGrass(0)).func_149711_c(3.0F).func_149672_a(Block.field_149779_h).func_149663_c("ClayGrass");
    clayGrass2 = (new BlockClayGrass(16)).func_149711_c(3.0F).func_149672_a(Block.field_149779_h).func_149663_c("ClayGrass");
    grass = (new BlockGrass()).func_149711_c(3.0F).func_149672_a(Block.field_149779_h).func_149663_c("Grass");
    grass2 = (new BlockGrass(16)).func_149711_c(3.0F).func_149672_a(Block.field_149779_h).func_149663_c("Grass");
    peat = (new BlockPeat()).func_149711_c(3.0F).func_149672_a(Block.field_149767_g).func_149663_c("Peat");
    peatGrass = (new BlockPeatGrass()).func_149711_c(3.0F).func_149672_a(Block.field_149779_h).func_149663_c("PeatGrass");
    dryGrass = (new BlockDryGrass(0)).func_149711_c(3.0F).func_149672_a(Block.field_149779_h).func_149663_c("DryGrass");
    dryGrass2 = (new BlockDryGrass(16)).func_149711_c(3.0F).func_149672_a(Block.field_149779_h).func_149663_c("DryGrass");
    tallGrass = (new BlockCustomTallGrass()).func_149711_c(0.0F).func_149672_a(Block.field_149779_h).func_149663_c("TallGrass");
    sand = (new BlockSand(0)).func_149711_c(0.5F).func_149672_a(Block.field_149776_m).func_149663_c("sand");
    sand2 = (new BlockSand(16)).func_149711_c(0.5F).func_149672_a(Block.field_149776_m).func_149663_c("sand");

    ore = (new BlockOre(Material.field_151576_e)).func_149711_c(10.0F).func_149752_b(10.0F).func_149663_c("Ore");
    ore2 = (new BlockOre2(Material.field_151576_e)).func_149711_c(10.0F).func_149752_b(10.0F).func_149663_c("Ore");
    ore3 = (new BlockOre3(Material.field_151576_e)).func_149711_c(10.0F).func_149752_b(10.0F).func_149663_c("Ore");
    worldItem = (new BlockWorldItem()).func_149711_c(0.05F).func_149752_b(1.0F).func_149663_c("LooseRock");
    sulfur = (new BlockSulfur(Material.field_151576_e)).func_149663_c("Sulfur").func_149711_c(0.5F).func_149752_b(1.0F);

    logPile = (new BlockLogPile()).func_149711_c(10.0F).func_149752_b(1.0F).func_149663_c("LogPile");
    woodSupportV = (new BlockWoodSupport(Material.field_151575_d)).func_149663_c("WoodSupportV").func_149711_c(0.5F).func_149752_b(1.0F);
    woodSupportH = (new BlockWoodSupport(Material.field_151575_d)).func_149663_c("WoodSupportH").func_149711_c(0.5F).func_149752_b(1.0F);
    woodSupportV2 = (new BlockWoodSupport2(Material.field_151575_d)).func_149663_c("WoodSupportV2").func_149711_c(0.5F).func_149752_b(1.0F);
    woodSupportH2 = (new BlockWoodSupport2(Material.field_151575_d)).func_149663_c("WoodSupportH2").func_149711_c(0.5F).func_149752_b(1.0F);

    tilledSoil = (new BlockFarmland(TFCBlocks.dirt, 0)).func_149711_c(2.0F).func_149672_a(Block.field_149767_g).func_149663_c("tilledSoil");
    tilledSoil2 = (new BlockFarmland(TFCBlocks.dirt2, 16)).func_149711_c(2.0F).func_149672_a(Block.field_149767_g).func_149663_c("tilledSoil");

    fruitTreeWood = (new BlockFruitWood()).func_149663_c("fruitTreeWood").func_149711_c(5.5F).func_149752_b(2.0F);
    fruitTreeLeaves = (new BlockFruitLeaves(0)).func_149663_c("fruitTreeLeaves").func_149711_c(0.5F).func_149752_b(1.0F).func_149672_a(Block.field_149779_h);
    fruitTreeLeaves2 = (new BlockFruitLeaves(8)).func_149663_c("fruitTreeLeaves2").func_149711_c(0.5F).func_149752_b(1.0F).func_149672_a(Block.field_149779_h);

    woodConstruct = (new BlockWoodConstruct()).func_149711_c(4.0F).func_149672_a(Block.field_149766_f).func_149663_c("WoodConstruct");

    firepit = (new BlockFirepit()).func_149663_c("Firepit").func_149711_c(1.0F).func_149715_a(0.0F);
    bellows = (new BlockBellows(Material.field_151575_d)).func_149663_c("Bellows").func_149711_c(2.0F);
    forge = (new BlockForge()).func_149663_c("Forge").func_149711_c(20.0F).func_149715_a(0.0F);
    anvil = (new BlockAnvil()).func_149663_c("Anvil").func_149711_c(3.0F).func_149752_b(100.0F);
    anvil2 = (new BlockAnvil(8)).func_149663_c("Anvil2").func_149711_c(3.0F).func_149752_b(100.0F);

    molten = (new BlockMolten()).func_149663_c("Molten").func_149711_c(20.0F);
    blastFurnace = (new BlockBlastFurnace()).func_149663_c("BlastFurnace").func_149711_c(20.0F).func_149715_a(0.0F);
    bloomery = (new BlockEarlyBloomery()).func_149663_c("EarlyBloomery").func_149711_c(20.0F).func_149715_a(0.0F);
    bloom = (new BlockBloom()).func_149663_c("Bloom").func_149711_c(20.0F).func_149715_a(0.0F);
    sluice = (new BlockSluice()).func_149663_c("Sluice").func_149711_c(2.0F).func_149752_b(20.0F);

    stoneStairs = (new BlockStair(Material.field_151576_e)).func_149663_c("stoneStairs").func_149711_c(10.0F);
    stoneSlabs = (new BlockSlab()).func_149663_c("stoneSlabs").func_149711_c(10.0F);
    stoneStalac = (new BlockStalactite()).func_149663_c("stoneStalac").func_149711_c(5.0F);

    charcoal = (new BlockCharcoal()).func_149711_c(3.0F).func_149752_b(10.0F).func_149663_c("Charcoal");

    detailed = (new BlockDetailed()).func_149663_c("StoneDetailed").func_149711_c(10.0F);

    planks = (new BlockPlanks(Material.field_151575_d)).func_149711_c(4.0F).func_149752_b(5.0F).func_149672_a(Block.field_149766_f).func_149663_c("wood");
    planks2 = (new BlockPlanks2(Material.field_151575_d)).func_149711_c(4.0F).func_149752_b(5.0F).func_149672_a(Block.field_149766_f).func_149663_c("wood2");
    leaves = (new BlockCustomLeaves()).func_149711_c(0.2F).func_149713_g(1).func_149672_a(Block.field_149779_h).func_149663_c("leaves").func_149647_a(TFCTabs.TFC_DECORATION);
    leaves2 = (new BlockCustomLeaves2()).func_149711_c(0.2F).func_149713_g(1).func_149672_a(Block.field_149779_h).func_149663_c("leaves2");
    sapling = (new BlockSapling()).func_149711_c(0.0F).func_149672_a(Block.field_149779_h).func_149663_c("sapling");
    sapling2 = (new BlockSapling2()).func_149711_c(0.0F).func_149672_a(Block.field_149779_h).func_149663_c("sapling2");

    logNatural = (new BlockLogNatural()).func_149711_c(50.0F).func_149672_a(Block.field_149766_f).func_149663_c("log");
    logNatural2 = (new BlockLogNatural2()).func_149711_c(50.0F).func_149672_a(Block.field_149766_f).func_149663_c("log2");
    woodVert = (new BlockLogVert()).func_149663_c("WoodVert").func_149711_c(20.0F).func_149752_b(15.0F).func_149672_a(Block.field_149766_f);
    woodVert2 = (new BlockLogVert2()).func_149663_c("WoodVert2").func_149711_c(20.0F).func_149752_b(15.0F).func_149672_a(Block.field_149766_f);
    woodHoriz = (new BlockLogHoriz(0)).func_149663_c("WoodHoriz").func_149711_c(20.0F).func_149752_b(15.0F).func_149672_a(Block.field_149766_f);
    woodHoriz2 = (new BlockLogHoriz(8)).func_149663_c("WoodHoriz2").func_149711_c(20.0F).func_149752_b(15.0F).func_149672_a(Block.field_149766_f);
    woodHoriz3 = (new BlockLogHoriz2(0)).func_149663_c("WoodHoriz3").func_149711_c(20.0F).func_149752_b(15.0F).func_149672_a(Block.field_149766_f);

    woodHoriz4 = (new BlockLogHoriz2(0)).func_149663_c("WoodHoriz4").func_149711_c(20.0F).func_149752_b(15.0F).func_149672_a(Block.field_149766_f);

    toolRack = (new BlockToolRack()).func_149711_c(3.0F).func_149663_c("Toolrack");
    spawnMeter = (new BlockSpawnMeter()).func_149711_c(3.0F).func_149663_c("SpawnMeter");
    foodPrep = (new BlockFoodPrep()).func_149711_c(1.0F).func_149663_c("FoodPrep");
    quern = (new BlockQuern()).func_149711_c(3.0F).func_149663_c("Quern");


    wattle = (new BlockWattle(Material.field_151575_d)).func_149711_c(0.5F).func_149663_c("Wattle Wall");

    wallCobbleIgIn = (new BlockCustomWall(stoneIgInCobble, 3)).func_149663_c("WallCobble");
    wallCobbleIgEx = (new BlockCustomWall(stoneIgExCobble, 4)).func_149663_c("WallCobble");
    wallCobbleSed = (new BlockCustomWall(stoneSedCobble, 8)).func_149663_c("WallCobble");
    wallCobbleMM = (new BlockCustomWall(stoneMMCobble, 6)).func_149663_c("WallCobble");
    wallRawIgIn = (new BlockCustomWall(stoneIgIn, 3)).func_149663_c("WallRaw");
    wallRawIgEx = (new BlockCustomWall(stoneIgEx, 4)).func_149663_c("WallRaw");
    wallRawSed = (new BlockCustomWall(stoneSed, 8)).func_149663_c("WallRaw");
    wallRawMM = (new BlockCustomWall(stoneMM, 6)).func_149663_c("WallRaw");
    wallBrickIgIn = (new BlockCustomWall(stoneIgInBrick, 3)).func_149663_c("WallBrick");
    wallBrickIgEx = (new BlockCustomWall(stoneIgExBrick, 4)).func_149663_c("WallBrick");
    wallBrickSed = (new BlockCustomWall(stoneSedBrick, 8)).func_149663_c("WallBrick");
    wallBrickMM = (new BlockCustomWall(stoneMMBrick, 6)).func_149663_c("WallBrick");
    wallSmoothIgIn = (new BlockCustomWall(stoneIgInSmooth, 3)).func_149663_c("WallSmooth");
    wallSmoothIgEx = (new BlockCustomWall(stoneIgExSmooth, 4)).func_149663_c("WallSmooth");
    wallSmoothSed = (new BlockCustomWall(stoneSedSmooth, 8)).func_149663_c("WallSmooth");
    wallSmoothMM = (new BlockCustomWall(stoneMMSmooth, 6)).func_149663_c("WallSmooth");


    for (int i = 0; i < Global.WOOD_ALL.length; i++) {
      doors[i] = (new BlockCustomDoor(i * 2)).func_149663_c("Door " + Global.WOOD_ALL[i]);
    }

    wattleDoor = (new BlockCustomWattleDoor(0)).func_149663_c("Door Wattle");

    ingotPile = (new BlockIngotPile()).func_149663_c("ingotpile").func_149711_c(3.0F);

    barrel = (new BlockBarrel()).func_149663_c("Barrel").func_149711_c(2.0F);
    loom = (new BlockLoom()).func_149663_c("Loom").func_149711_c(2.0F);
    thatch = (new BlockThatch()).func_149663_c("Thatch").func_149711_c(1.0F).func_149672_a(Block.field_149779_h).func_149647_a(TFCTabs.TFC_BUILDING);
    moss = (new BlockMoss()).func_149663_c("Moss").func_149711_c(1.0F).func_149672_a(Block.field_149779_h);

    flora = (new BlockFlora()).func_149663_c("Flora").func_149711_c(0.1F).func_149672_a(Block.field_149779_h);
    pottery = (new BlockPottery()).func_149663_c("Pottery").func_149711_c(1.0F);

    crucible = (new BlockCrucible()).func_149663_c("Crucible").func_149711_c(4.0F);

    nestBox = (new BlockNestBox()).func_149663_c("NestBox").func_149711_c(1.0F);

    fence = (new BlockTFCFence("Fence", Material.field_151575_d)).func_149663_c("FenceTFC").func_149711_c(2.0F);
    fenceGate = (new BlockCustomFenceGate()).func_149663_c("FenceGateTFC").func_149711_c(2.0F);
    fence2 = (new BlockTFCFence2("Fence2", Material.field_151575_d)).func_149663_c("FenceTFC").func_149711_c(2.0F);
    fenceGate2 = (new BlockCustomFenceGate2()).func_149663_c("FenceGateTFC").func_149711_c(2.0F);
    strawHideBed = (new BlockBed()).func_149663_c("StrawHideBed").func_149711_c(1.0F).func_149647_a(null);
    armorStand = (new BlockStand()).func_149663_c("ArmourStand").func_149711_c(2.0F);
    armorStand2 = (new BlockStand2()).func_149663_c("ArmourStand").func_149711_c(2.0F);

    berryBush = (new BlockBerryBush()).func_149663_c("BerryBush").func_149711_c(0.3F).func_149672_a(Block.field_149779_h);
    crops = (new BlockCrop()).func_149663_c("crops").func_149711_c(0.3F).func_149672_a(Block.field_149779_h);
    lilyPad = (new BlockCustomLilyPad()).func_149711_c(0.0F).func_149672_a(Block.field_149779_h).func_149663_c("LilyPad").func_149658_d("waterlily");
    flowers = (new BlockFlower()).func_149711_c(0.0F).func_149672_a(Block.field_149779_h).func_149663_c("Flowers");
    flowers2 = (new BlockFlower2()).func_149711_c(0.0F).func_149672_a(Block.field_149779_h).func_149663_c("Flowers2");
    fungi = (new BlockFungi()).func_149711_c(0.0F).func_149672_a(Block.field_149779_h).func_149663_c("Fungi");

    saltWater = (new BlockSaltWater(TFCFluids.SALTWATER)).func_149711_c(100.0F).func_149713_g(3).func_149663_c("SaltWater");
    saltWaterStationary = (new BlockLiquidStatic(TFCFluids.SALTWATER, Material.field_151586_h, saltWater)).func_149711_c(100.0F).func_149713_g(3).func_149663_c("SaltWaterStationary");

    freshWater = (new BlockFreshWater(TFCFluids.FRESHWATER)).func_149711_c(100.0F).func_149713_g(3).func_149663_c("FreshWater");
    freshWaterStationary = (new BlockLiquidStatic(TFCFluids.FRESHWATER, Material.field_151586_h, freshWater)).func_149711_c(100.0F).func_149713_g(3).func_149663_c("FreshWaterStationary");

    hotWater = (new BlockHotWater(TFCFluids.HOTWATER)).func_149711_c(100.0F).func_149713_g(3).func_149663_c("HotWater");
    hotWaterStationary = (new BlockHotWaterStatic(TFCFluids.HOTWATER, Material.field_151586_h, hotWater)).func_149711_c(100.0F).func_149713_g(3).func_149663_c("HotWaterStationary");


    lava = (new BlockLava()).func_149711_c(0.0F).func_149715_a(1.0F).func_149713_g(255).func_149663_c("Lava");
    lavaStationary = (new BlockLiquidStatic(TFCFluids.LAVA, Material.field_151587_i, lava)).func_149711_c(0.0F).func_149715_a(1.0F).func_149713_g(255).func_149663_c("LavaStationary");
    ice = (new BlockCustomIce()).func_149711_c(0.5F).func_149713_g(3).func_149672_a(Block.field_149778_k).func_149663_c("Ice").func_149658_d("ice");

    waterPlant = (new BlockWaterPlant(0)).func_149663_c("SeaGrassStill").func_149711_c(0.5F).func_149672_a(Block.field_149767_g);

    fireBrick = (new BlockFireBrick()).func_149663_c("FireBrick").func_149711_c(8.0F);
    metalSheet = (new BlockMetalSheet()).func_149663_c("MetalSheet").func_149711_c(80.0F);
    leatherRack = (new BlockLeatherRack()).func_149663_c("LeatherRack").func_149711_c(1.0F);

    gravel = (new BlockGravel(0)).func_149711_c(2.0F).func_149672_a(Block.field_149767_g).func_149663_c("gravel");
    gravel2 = (new BlockGravel(16)).func_149711_c(2.0F).func_149672_a(Block.field_149767_g).func_149663_c("gravel");

    grill = (new BlockGrill()).func_149711_c(2.0F).func_149663_c("Grill");
    metalTrapDoor = (new BlockMetalTrapDoor()).func_149711_c(2.0F).func_149663_c("MetalTrapDoor");
    vessel = (new BlockLargeVessel()).func_149711_c(1.0F).func_149663_c("Vessel");
    smoke = (new BlockSmoke()).func_149711_c(0.0F).func_149663_c("Smoke");
    smokeRack = (new BlockSmokeRack()).func_149711_c(0.0F).func_149663_c("SmokeRack");

    oilLamp = (new BlockOilLamp()).func_149711_c(1.0F).func_149663_c("OilLamp");
    hopper = (new BlockHopper()).func_149711_c(2.0F).func_149663_c("Hopper");
    flowerPot = (new BlockCustomFlowerPot()).func_149711_c(0.0F).func_149672_a(Block.field_149769_e).func_149663_c("FlowerPot").func_149658_d("flower_pot");

    stoneIgIn.setHarvestLevel("pickaxe", 0);
    stoneIgEx.setHarvestLevel("pickaxe", 0);
    stoneSed.setHarvestLevel("pickaxe", 0);
    stoneMM.setHarvestLevel("pickaxe", 0);
    stoneStalac.setHarvestLevel("pickaxe", 0);
    detailed.setHarvestLevel("pickaxe", 0);
    ore.setHarvestLevel("pickaxe", 1);
    ore2.setHarvestLevel("pickaxe", 1);
    ore3.setHarvestLevel("pickaxe", 1);

    dirt.setHarvestLevel("shovel", 0);
    dirt2.setHarvestLevel("shovel", 0);
    grass.setHarvestLevel("shovel", 0);
    grass2.setHarvestLevel("shovel", 0);
    dryGrass.setHarvestLevel("shovel", 0);
    dryGrass2.setHarvestLevel("shovel", 0);
    clay.setHarvestLevel("shovel", 0);
    clay2.setHarvestLevel("shovel", 0);
    clayGrass.setHarvestLevel("shovel", 0);
    clayGrass2.setHarvestLevel("shovel", 0);
    peat.setHarvestLevel("shovel", 0);
    peatGrass.setHarvestLevel("shovel", 0);
    sand.setHarvestLevel("shovel", 0);
    sand2.setHarvestLevel("shovel", 0);
    charcoal.setHarvestLevel("shovel", 0);
    gravel.setHarvestLevel("shovel", 0);
    gravel2.setHarvestLevel("shovel", 0);
    waterPlant.setHarvestLevel("shovel", 0);
    tilledSoil.setHarvestLevel("shovel", 0);
    tilledSoil2.setHarvestLevel("shovel", 0);

    detailed.setHarvestLevel("axe", 0);
    Blocks.field_150476_ad.setHarvestLevel("axe", 0);
    woodConstruct.setHarvestLevel("axe", 0);
    logNatural.setHarvestLevel("axe", 1);
    logNatural2.setHarvestLevel("axe", 1);
    woodHoriz.setHarvestLevel("axe", 1);
    woodHoriz2.setHarvestLevel("axe", 1);
    woodHoriz3.setHarvestLevel("axe", 1);
    woodHoriz4.setHarvestLevel("axe", 1);
    woodVert.setHarvestLevel("axe", 1);
    woodVert2.setHarvestLevel("axe", 1);

    logNatural.setHarvestLevel("hammer", 1);
    logNatural2.setHarvestLevel("hammer", 1);
    woodHoriz.setHarvestLevel("hammer", 1);
    woodHoriz2.setHarvestLevel("hammer", 1);
    woodHoriz3.setHarvestLevel("hammer", 1);
    woodHoriz4.setHarvestLevel("hammer", 1);
    woodVert.setHarvestLevel("hammer", 1);
    woodVert2.setHarvestLevel("hammer", 1);

    stoneSlabs.setHarvestLevel("axe", 4);
    stoneStairs.setHarvestLevel("axe", 4);
  }




  public static void setupFire() {
    Blocks.field_150480_ab.setFireInfo(logNatural, 5, 5);
    Blocks.field_150480_ab.setFireInfo(logNatural2, 5, 5);
    Blocks.field_150480_ab.setFireInfo(woodSupportV, 5, 20);
    Blocks.field_150480_ab.setFireInfo(woodSupportV2, 5, 20);
    Blocks.field_150480_ab.setFireInfo(woodSupportH, 5, 20);
    Blocks.field_150480_ab.setFireInfo(woodSupportH2, 5, 20);
    Blocks.field_150480_ab.setFireInfo(leaves, 20, 20);
    Blocks.field_150480_ab.setFireInfo(leaves2, 20, 20);
    Blocks.field_150480_ab.setFireInfo(fruitTreeWood, 5, 20);
    Blocks.field_150480_ab.setFireInfo(fruitTreeLeaves, 20, 20);
    Blocks.field_150480_ab.setFireInfo(fruitTreeLeaves2, 20, 20);
    Blocks.field_150480_ab.setFireInfo(fence, 5, 20);
    Blocks.field_150480_ab.setFireInfo(fence2, 5, 20);
    Blocks.field_150480_ab.setFireInfo(fenceGate, 5, 20);
    Blocks.field_150480_ab.setFireInfo(fenceGate2, 5, 20);
    Blocks.field_150480_ab.setFireInfo(chest, 5, 20);
    Blocks.field_150480_ab.setFireInfo(strawHideBed, 20, 20);
    Blocks.field_150480_ab.setFireInfo(thatch, 20, 20);
    Blocks.field_150480_ab.setFireInfo(woodVert, 5, 5);
    Blocks.field_150480_ab.setFireInfo(woodVert2, 5, 5);
    Blocks.field_150480_ab.setFireInfo(woodHoriz, 5, 5);
    Blocks.field_150480_ab.setFireInfo(woodHoriz2, 5, 5);
    Blocks.field_150480_ab.setFireInfo(woodHoriz3, 5, 5);
    Blocks.field_150480_ab.setFireInfo(woodHoriz4, 5, 5);
    Blocks.field_150480_ab.setFireInfo(planks, 5, 20);
    Blocks.field_150480_ab.setFireInfo(planks2, 5, 20);
    Blocks.field_150480_ab.setFireInfo(woodConstruct, 5, 20);
    Blocks.field_150480_ab.setFireInfo(berryBush, 20, 20);
    Blocks.field_150480_ab.setFireInfo(barrel, 5, 20);
    Blocks.field_150480_ab.setFireInfo(crops, 20, 20);
    Blocks.field_150480_ab.setFireInfo(logPile, 10, 10);

    for (int i = 0; i < Global.WOOD_ALL.length; i++) {
      Blocks.field_150480_ab.setFireInfo(doors[i], 5, 20);
    }
    Blocks.field_150480_ab.setFireInfo(wattle, 5, 20);
    Blocks.field_150480_ab.setFireInfo(wattleDoor, 20, 20);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\BlockSetup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */