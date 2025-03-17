/*     */ package com.bioxx.tfc;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.BlockBloom;
/*     */ import com.bioxx.tfc.Blocks.BlockCharcoal;
/*     */ import com.bioxx.tfc.Blocks.BlockCrop;
/*     */ import com.bioxx.tfc.Blocks.BlockDetailed;
/*     */ import com.bioxx.tfc.Blocks.BlockFarmland;
/*     */ import com.bioxx.tfc.Blocks.BlockFireBrick;
/*     */ import com.bioxx.tfc.Blocks.BlockIngotPile;
/*     */ import com.bioxx.tfc.Blocks.BlockLogPile;
/*     */ import com.bioxx.tfc.Blocks.BlockMetalSheet;
/*     */ import com.bioxx.tfc.Blocks.BlockMetalTrapDoor;
/*     */ import com.bioxx.tfc.Blocks.BlockMolten;
/*     */ import com.bioxx.tfc.Blocks.BlockPlanks;
/*     */ import com.bioxx.tfc.Blocks.BlockPlanks2;
/*     */ import com.bioxx.tfc.Blocks.BlockSlab;
/*     */ import com.bioxx.tfc.Blocks.BlockSmoke;
/*     */ import com.bioxx.tfc.Blocks.BlockSmokeRack;
/*     */ import com.bioxx.tfc.Blocks.BlockStair;
/*     */ import com.bioxx.tfc.Blocks.BlockStalactite;
/*     */ import com.bioxx.tfc.Blocks.BlockSulfur;
/*     */ import com.bioxx.tfc.Blocks.BlockThatch;
/*     */ import com.bioxx.tfc.Blocks.BlockWattle;
/*     */ import com.bioxx.tfc.Blocks.BlockWoodConstruct;
/*     */ import com.bioxx.tfc.Blocks.BlockWoodSupport;
/*     */ import com.bioxx.tfc.Blocks.BlockWoodSupport2;
/*     */ import com.bioxx.tfc.Blocks.BlockWorldItem;
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockAnvil;
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockBarrel;
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockBellows;
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockBlastFurnace;
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockChestTFC;
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockCrucible;
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockEarlyBloomery;
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockFirepit;
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockFoodPrep;
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockForge;
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockGrill;
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockHopper;
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockLargeVessel;
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockLeatherRack;
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockLoom;
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockNestBox;
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockOilLamp;
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockPottery;
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockQuern;
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockSluice;
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockSpawnMeter;
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockStand;
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockStand2;
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockToolRack;
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockWorkbench;
/*     */ import com.bioxx.tfc.Blocks.Flora.BlockBerryBush;
/*     */ import com.bioxx.tfc.Blocks.Flora.BlockFlora;
/*     */ import com.bioxx.tfc.Blocks.Flora.BlockFlower;
/*     */ import com.bioxx.tfc.Blocks.Flora.BlockFlower2;
/*     */ import com.bioxx.tfc.Blocks.Flora.BlockFruitLeaves;
/*     */ import com.bioxx.tfc.Blocks.Flora.BlockFruitWood;
/*     */ import com.bioxx.tfc.Blocks.Flora.BlockLogHoriz;
/*     */ import com.bioxx.tfc.Blocks.Flora.BlockLogHoriz2;
/*     */ import com.bioxx.tfc.Blocks.Flora.BlockLogNatural;
/*     */ import com.bioxx.tfc.Blocks.Flora.BlockLogNatural2;
/*     */ import com.bioxx.tfc.Blocks.Flora.BlockLogVert;
/*     */ import com.bioxx.tfc.Blocks.Flora.BlockLogVert2;
/*     */ import com.bioxx.tfc.Blocks.Flora.BlockSapling;
/*     */ import com.bioxx.tfc.Blocks.Flora.BlockSapling2;
/*     */ import com.bioxx.tfc.Blocks.Flora.BlockWaterPlant;
/*     */ import com.bioxx.tfc.Blocks.Liquids.BlockFreshWater;
/*     */ import com.bioxx.tfc.Blocks.Liquids.BlockHotWater;
/*     */ import com.bioxx.tfc.Blocks.Liquids.BlockHotWaterStatic;
/*     */ import com.bioxx.tfc.Blocks.Liquids.BlockLava;
/*     */ import com.bioxx.tfc.Blocks.Liquids.BlockLiquidStatic;
/*     */ import com.bioxx.tfc.Blocks.Liquids.BlockSaltWater;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockClay;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockClayGrass;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockDirt;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockDryGrass;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockFungi;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockGrass;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockGravel;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockIgEx;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockIgExBrick;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockIgExCobble;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockIgExSmooth;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockIgIn;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockIgInBrick;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockIgInCobble;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockIgInSmooth;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockMM;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockMMBrick;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockMMCobble;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockMMSmooth;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockMoss;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockOre;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockOre2;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockOre3;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockPeat;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockPeatGrass;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockSand;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockSed;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockSedBrick;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockSedCobble;
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockSedSmooth;
/*     */ import com.bioxx.tfc.Blocks.Vanilla.BlockBed;
/*     */ import com.bioxx.tfc.Blocks.Vanilla.BlockCustomBookshelf;
/*     */ import com.bioxx.tfc.Blocks.Vanilla.BlockCustomButtonWood;
/*     */ import com.bioxx.tfc.Blocks.Vanilla.BlockCustomCactus;
/*     */ import com.bioxx.tfc.Blocks.Vanilla.BlockCustomDoor;
/*     */ import com.bioxx.tfc.Blocks.Vanilla.BlockCustomFenceGate;
/*     */ import com.bioxx.tfc.Blocks.Vanilla.BlockCustomFenceGate2;
/*     */ import com.bioxx.tfc.Blocks.Vanilla.BlockCustomFlowerPot;
/*     */ import com.bioxx.tfc.Blocks.Vanilla.BlockCustomIce;
/*     */ import com.bioxx.tfc.Blocks.Vanilla.BlockCustomLeaves;
/*     */ import com.bioxx.tfc.Blocks.Vanilla.BlockCustomLeaves2;
/*     */ import com.bioxx.tfc.Blocks.Vanilla.BlockCustomLilyPad;
/*     */ import com.bioxx.tfc.Blocks.Vanilla.BlockCustomPumpkin;
/*     */ import com.bioxx.tfc.Blocks.Vanilla.BlockCustomReed;
/*     */ import com.bioxx.tfc.Blocks.Vanilla.BlockCustomSnow;
/*     */ import com.bioxx.tfc.Blocks.Vanilla.BlockCustomTallGrass;
/*     */ import com.bioxx.tfc.Blocks.Vanilla.BlockCustomVine;
/*     */ import com.bioxx.tfc.Blocks.Vanilla.BlockCustomWall;
/*     */ import com.bioxx.tfc.Blocks.Vanilla.BlockCustomWattleDoor;
/*     */ import com.bioxx.tfc.Blocks.Vanilla.BlockTFCFence;
/*     */ import com.bioxx.tfc.Blocks.Vanilla.BlockTFCFence2;
/*     */ import com.bioxx.tfc.Blocks.Vanilla.BlockTorch;
/*     */ import com.bioxx.tfc.Blocks.Vanilla.BlockTorchOff;
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemAnvil1;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemAnvil2;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemArmourStand;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemArmourStand2;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemBarrels;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemBellows;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemBerryBush;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemChest;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemCrucible;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemCustomLilyPad;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemCustomTallGrass;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemCustomWood;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemCustomWood2;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemCustomWoodH;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemCustomWoodH2;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemCustomWoodH3;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemFence;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemFence2;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemFenceGate;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemFenceGate2;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemFlora;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemFlowers;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemFlowers2;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemFungi;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemGrill;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemLargeVessel;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemLooms;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemMetalTrapDoor;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemOilLamp;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemSapling;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemSapling2;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemSoil;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemStone;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemTerraBlock;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemToolRack;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemTorch;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemVine;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemWoodSupport;
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemWoodSupport2;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCFluids;
/*     */ import cpw.mods.fml.common.registry.GameRegistry;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.init.Blocks;
/*     */ 
/*     */ 
/*     */ public class BlockSetup
/*     */   extends TFCBlocks
/*     */ {
/*     */   public static void registerBlocks() {
/* 180 */     GameRegistry.registerBlock(ore, "Ore1");
/* 181 */     GameRegistry.registerBlock(ore2, "Ore2");
/* 182 */     GameRegistry.registerBlock(ore3, "Ore3");
/* 183 */     GameRegistry.registerBlock(stoneIgIn, ItemStone.class, "StoneIgIn");
/* 184 */     GameRegistry.registerBlock(stoneIgEx, ItemStone.class, "StoneIgEx");
/* 185 */     GameRegistry.registerBlock(stoneSed, ItemStone.class, "StoneSed");
/* 186 */     GameRegistry.registerBlock(stoneMM, ItemStone.class, "StoneMM");
/*     */     
/* 188 */     GameRegistry.registerBlock(stoneIgInCobble, ItemStone.class, "StoneIgInCobble");
/* 189 */     GameRegistry.registerBlock(stoneIgExCobble, ItemStone.class, "StoneIgExCobble");
/* 190 */     GameRegistry.registerBlock(stoneSedCobble, ItemStone.class, "StoneSedCobble");
/* 191 */     GameRegistry.registerBlock(stoneMMCobble, ItemStone.class, "StoneMMCobble");
/* 192 */     GameRegistry.registerBlock(stoneIgInSmooth, ItemStone.class, "StoneIgInSmooth");
/* 193 */     GameRegistry.registerBlock(stoneIgExSmooth, ItemStone.class, "StoneIgExSmooth");
/* 194 */     GameRegistry.registerBlock(stoneSedSmooth, ItemStone.class, "StoneSedSmooth");
/* 195 */     GameRegistry.registerBlock(stoneMMSmooth, ItemStone.class, "StoneMMSmooth");
/* 196 */     GameRegistry.registerBlock(stoneIgInBrick, ItemStone.class, "StoneIgInBrick");
/* 197 */     GameRegistry.registerBlock(stoneIgExBrick, ItemStone.class, "StoneIgExBrick");
/* 198 */     GameRegistry.registerBlock(stoneSedBrick, ItemStone.class, "StoneSedBrick");
/* 199 */     GameRegistry.registerBlock(stoneMMBrick, ItemStone.class, "StoneMMBrick");
/*     */     
/* 201 */     GameRegistry.registerBlock(dirt, ItemSoil.class, "Dirt");
/* 202 */     GameRegistry.registerBlock(dirt2, ItemSoil.class, "Dirt2");
/* 203 */     GameRegistry.registerBlock(sand, ItemSoil.class, "Sand");
/* 204 */     GameRegistry.registerBlock(sand2, ItemSoil.class, "Sand2");
/* 205 */     GameRegistry.registerBlock(clay, ItemSoil.class, "Clay");
/* 206 */     GameRegistry.registerBlock(clay2, ItemSoil.class, "Clay2");
/* 207 */     GameRegistry.registerBlock(grass, ItemSoil.class, "Grass");
/* 208 */     GameRegistry.registerBlock(grass2, ItemSoil.class, "Grass2");
/* 209 */     GameRegistry.registerBlock(clayGrass, ItemSoil.class, "ClayGrass");
/* 210 */     GameRegistry.registerBlock(clayGrass2, ItemSoil.class, "ClayGrass2");
/* 211 */     GameRegistry.registerBlock(peatGrass, ItemSoil.class, "PeatGrass");
/* 212 */     GameRegistry.registerBlock(peat, ItemSoil.class, "Peat");
/* 213 */     GameRegistry.registerBlock(dryGrass, ItemSoil.class, "DryGrass");
/* 214 */     GameRegistry.registerBlock(dryGrass2, ItemSoil.class, "DryGrass2");
/* 215 */     GameRegistry.registerBlock(tallGrass, ItemCustomTallGrass.class, "TallGrass");
/* 216 */     GameRegistry.registerBlock(worldItem, "LooseRock");
/* 217 */     GameRegistry.registerBlock(logPile, "LogPile");
/* 218 */     GameRegistry.registerBlock(charcoal, "Charcoal");
/* 219 */     GameRegistry.registerBlock(detailed, "Detailed");
/*     */     
/* 221 */     GameRegistry.registerBlock(tilledSoil, ItemSoil.class, "tilledSoil");
/* 222 */     GameRegistry.registerBlock(tilledSoil2, ItemSoil.class, "tilledSoil2");
/*     */     
/* 224 */     GameRegistry.registerBlock(woodSupportV, ItemWoodSupport.class, "WoodSupportV");
/* 225 */     GameRegistry.registerBlock(woodSupportH, ItemWoodSupport.class, "WoodSupportH");
/* 226 */     GameRegistry.registerBlock(woodSupportV2, ItemWoodSupport2.class, "WoodSupportV2");
/* 227 */     GameRegistry.registerBlock(woodSupportH2, ItemWoodSupport2.class, "WoodSupportH2");
/* 228 */     GameRegistry.registerBlock(sulfur, "Sulfur");
/* 229 */     GameRegistry.registerBlock(logNatural, ItemCustomWood.class, "log");
/* 230 */     GameRegistry.registerBlock(logNatural2, ItemCustomWood2.class, "log2");
/* 231 */     GameRegistry.registerBlock(leaves, ItemCustomWood.class, "leaves");
/* 232 */     GameRegistry.registerBlock(leaves2, ItemCustomWood2.class, "leaves2");
/* 233 */     GameRegistry.registerBlock(sapling, ItemSapling.class, "sapling");
/* 234 */     GameRegistry.registerBlock(sapling2, ItemSapling2.class, "sapling2");
/* 235 */     GameRegistry.registerBlock(planks, ItemCustomWood.class, "planks");
/* 236 */     GameRegistry.registerBlock(planks2, ItemCustomWood2.class, "planks2");
/*     */     
/* 238 */     GameRegistry.registerBlock(firepit, "Firepit");
/* 239 */     GameRegistry.registerBlock(bellows, ItemBellows.class, "Bellows");
/* 240 */     GameRegistry.registerBlock(anvil, ItemAnvil1.class, "Anvil");
/* 241 */     GameRegistry.registerBlock(anvil2, ItemAnvil2.class, "Anvil2");
/* 242 */     GameRegistry.registerBlock(forge, "Forge");
/*     */     
/* 244 */     GameRegistry.registerBlock(molten, "Molten");
/* 245 */     GameRegistry.registerBlock(blastFurnace, ItemTerraBlock.class, "Bloomery");
/* 246 */     GameRegistry.registerBlock(bloomery, ItemTerraBlock.class, "EarlyBloomery");
/* 247 */     GameRegistry.registerBlock(sluice, "Sluice");
/* 248 */     GameRegistry.registerBlock(bloom, "Bloom");
/*     */     
/* 250 */     GameRegistry.registerBlock(fruitTreeWood, "fruitTreeWood");
/* 251 */     GameRegistry.registerBlock(fruitTreeLeaves, "fruitTreeLeaves");
/* 252 */     GameRegistry.registerBlock(fruitTreeLeaves2, "fruitTreeLeaves2");
/*     */     
/* 254 */     GameRegistry.registerBlock(stoneStairs, "stoneStairs");
/* 255 */     GameRegistry.registerBlock(stoneSlabs, "stoneSlabs");
/* 256 */     GameRegistry.registerBlock(stoneStalac, "stoneStalac");
/*     */     
/* 258 */     GameRegistry.registerBlock(woodConstruct, "WoodConstruct");
/* 259 */     GameRegistry.registerBlock(woodVert, ItemCustomWood.class, "WoodVert");
/* 260 */     GameRegistry.registerBlock(woodVert2, ItemCustomWood2.class, "WoodVert2");
/* 261 */     GameRegistry.registerBlock(woodHoriz, ItemCustomWoodH.class, "WoodHoriz");
/* 262 */     GameRegistry.registerBlock(woodHoriz2, ItemCustomWoodH2.class, "WoodHoriz2");
/* 263 */     GameRegistry.registerBlock(woodHoriz3, ItemCustomWoodH3.class, "WoodHoriz3");
/* 264 */     GameRegistry.registerBlock(woodHoriz4, "WoodHoriz4");
/*     */     
/* 266 */     GameRegistry.registerBlock(toolRack, ItemToolRack.class, "ToolRack");
/* 267 */     GameRegistry.registerBlock(spawnMeter, ItemTerraBlock.class, "SpawnMeter");
/* 268 */     GameRegistry.registerBlock(foodPrep, "FoodPrep");
/* 269 */     GameRegistry.registerBlock(quern, ItemTerraBlock.class, "Quern");
/* 270 */     GameRegistry.registerBlock(wallCobbleIgIn, ItemStone.class, "WallCobbleIgIn");
/* 271 */     GameRegistry.registerBlock(wallCobbleIgEx, ItemStone.class, "WallCobbleIgEx");
/* 272 */     GameRegistry.registerBlock(wallCobbleSed, ItemStone.class, "WallCobbleSed");
/* 273 */     GameRegistry.registerBlock(wallCobbleMM, ItemStone.class, "WallCobbleMM");
/* 274 */     GameRegistry.registerBlock(wallRawIgIn, ItemStone.class, "WallRawIgIn");
/* 275 */     GameRegistry.registerBlock(wallRawIgEx, ItemStone.class, "WallRawIgEx");
/* 276 */     GameRegistry.registerBlock(wallRawSed, ItemStone.class, "WallRawSed");
/* 277 */     GameRegistry.registerBlock(wallRawMM, ItemStone.class, "WallRawMM");
/* 278 */     GameRegistry.registerBlock(wallBrickIgIn, ItemStone.class, "WallBrickIgIn");
/* 279 */     GameRegistry.registerBlock(wallBrickIgEx, ItemStone.class, "WallBrickIgEx");
/* 280 */     GameRegistry.registerBlock(wallBrickSed, ItemStone.class, "WallBrickSed");
/* 281 */     GameRegistry.registerBlock(wallBrickMM, ItemStone.class, "WallBrickMM");
/* 282 */     GameRegistry.registerBlock(wallSmoothIgIn, ItemStone.class, "WallSmoothIgIn");
/* 283 */     GameRegistry.registerBlock(wallSmoothIgEx, ItemStone.class, "WallSmoothIgEx");
/* 284 */     GameRegistry.registerBlock(wallSmoothSed, ItemStone.class, "WallSmoothSed");
/* 285 */     GameRegistry.registerBlock(wallSmoothMM, ItemStone.class, "WallSmoothMM");
/*     */     
/* 287 */     GameRegistry.registerBlock(saltWater, "SaltWater");
/* 288 */     GameRegistry.registerBlock(saltWaterStationary, "SaltWaterStationary");
/* 289 */     GameRegistry.registerBlock(freshWater, "FreshWater");
/* 290 */     GameRegistry.registerBlock(freshWaterStationary, "FreshWaterStationary");
/* 291 */     GameRegistry.registerBlock(hotWater, "HotWater");
/* 292 */     GameRegistry.registerBlock(hotWaterStationary, "HotWaterStationary");
/* 293 */     GameRegistry.registerBlock(lava, "Lava");
/* 294 */     GameRegistry.registerBlock(lavaStationary, "LavaStationary");
/* 295 */     GameRegistry.registerBlock(ice, "Ice");
/*     */     
/* 297 */     GameRegistry.registerBlock(waterPlant, "SeaGrassStill");
/*     */     
/* 299 */     GameRegistry.registerBlock(fireBrick, "FireBrick");
/* 300 */     GameRegistry.registerBlock(metalSheet, "MetalSheet");
/*     */ 
/*     */     
/* 303 */     for (int i = 0; i < Global.WOOD_ALL.length; i++) {
/* 304 */       GameRegistry.registerBlock(doors[i], "Door" + Global.WOOD_ALL[i].replaceAll(" ", ""));
/*     */     }
/*     */     
/* 307 */     GameRegistry.registerBlock(wattleDoor, "DoorWattle");
/*     */     
/* 309 */     GameRegistry.registerBlock(ingotPile, "IngotPile");
/* 310 */     GameRegistry.registerBlock(barrel, ItemBarrels.class, "Barrel");
/* 311 */     GameRegistry.registerBlock(loom, ItemLooms.class, "Loom");
/* 312 */     GameRegistry.registerBlock(moss, "Moss");
/*     */     
/* 314 */     GameRegistry.registerBlock(flora, ItemFlora.class, "Flora");
/* 315 */     GameRegistry.registerBlock(pottery, "ClayPottery");
/* 316 */     GameRegistry.registerBlock(thatch, ItemTerraBlock.class, "Thatch");
/* 317 */     GameRegistry.registerBlock(crucible, ItemCrucible.class, "Crucible");
/* 318 */     GameRegistry.registerBlock(nestBox, ItemTerraBlock.class, "NestBox");
/*     */     
/* 320 */     GameRegistry.registerBlock(wattle, "Wattle Wall");
/* 321 */     GameRegistry.registerBlock(fence, ItemFence.class, "Fence");
/* 322 */     GameRegistry.registerBlock(fence2, ItemFence2.class, "Fence2");
/* 323 */     GameRegistry.registerBlock(fenceGate, ItemFenceGate.class, "FenceGate");
/* 324 */     GameRegistry.registerBlock(fenceGate2, ItemFenceGate2.class, "FenceGate2");
/* 325 */     GameRegistry.registerBlock(strawHideBed, "StrawHideBed");
/* 326 */     GameRegistry.registerBlock(armorStand, ItemArmourStand.class, "ArmourStand");
/* 327 */     GameRegistry.registerBlock(armorStand2, ItemArmourStand2.class, "ArmourStand2");
/* 328 */     GameRegistry.registerBlock(berryBush, ItemBerryBush.class, "BerryBush");
/* 329 */     GameRegistry.registerBlock(crops, "Crops");
/* 330 */     GameRegistry.registerBlock(lilyPad, ItemCustomLilyPad.class, "LilyPad");
/* 331 */     GameRegistry.registerBlock(flowers, ItemFlowers.class, "Flowers");
/* 332 */     GameRegistry.registerBlock(flowers2, ItemFlowers2.class, "Flowers2");
/* 333 */     GameRegistry.registerBlock(fungi, ItemFungi.class, "Fungi");
/* 334 */     GameRegistry.registerBlock(bookshelf, ItemTerraBlock.class, "Bookshelf");
/* 335 */     GameRegistry.registerBlock(torch, ItemTorch.class, "Torch");
/* 336 */     GameRegistry.registerBlock(torchOff, "TorchOff");
/* 337 */     GameRegistry.registerBlock(chest, ItemChest.class, "Chest TFC");
/* 338 */     GameRegistry.registerBlock(workbench, ItemTerraBlock.class, "Workbench");
/* 339 */     GameRegistry.registerBlock(cactus, ItemTerraBlock.class, "Cactus");
/* 340 */     GameRegistry.registerBlock(reeds, "Reeds");
/* 341 */     GameRegistry.registerBlock(pumpkin, ItemTerraBlock.class, "Pumpkin");
/* 342 */     GameRegistry.registerBlock(litPumpkin, ItemTerraBlock.class, "LitPumpkin");
/* 343 */     GameRegistry.registerBlock(buttonWood, "ButtonWood");
/* 344 */     GameRegistry.registerBlock(vine, ItemVine.class, "Vine");
/* 345 */     GameRegistry.registerBlock(leatherRack, "LeatherRack");
/* 346 */     GameRegistry.registerBlock(gravel, ItemSoil.class, "Gravel");
/* 347 */     GameRegistry.registerBlock(gravel2, ItemSoil.class, "Gravel2");
/*     */     
/* 349 */     GameRegistry.registerBlock(grill, ItemGrill.class, "Grill");
/* 350 */     GameRegistry.registerBlock(metalTrapDoor, ItemMetalTrapDoor.class, "MetalTrapDoor");
/* 351 */     GameRegistry.registerBlock(vessel, ItemLargeVessel.class, "Vessel");
/* 352 */     GameRegistry.registerBlock(smoke, "Smoke");
/* 353 */     GameRegistry.registerBlock(smokeRack, "SmokeRack");
/* 354 */     GameRegistry.registerBlock(snow, "Snow");
/* 355 */     GameRegistry.registerBlock(oilLamp, ItemOilLamp.class, "OilLamp");
/* 356 */     GameRegistry.registerBlock(hopper, "Hopper");
/* 357 */     GameRegistry.registerBlock(flowerPot, "FlowerPot");
/*     */   }
/*     */ 
/*     */   
/*     */   public static void loadBlocks() {
/* 362 */     TerraFirmaCraft.LOG.info("Loading Blocks");
/*     */ 
/*     */     
/* 365 */     Blocks.field_150373_bw.func_149647_a(null);
/* 366 */     Blocks.field_150376_bx.func_149647_a(null);
/* 367 */     Blocks.field_150485_bF.func_149647_a(null);
/* 368 */     Blocks.field_150487_bG.func_149647_a(null);
/* 369 */     Blocks.field_150481_bH.func_149647_a(null);
/* 370 */     Blocks.field_150392_bi.func_149647_a(null);
/* 371 */     Blocks.field_150329_H.func_149647_a(null);
/* 372 */     Blocks.field_150327_N.func_149647_a(null);
/* 373 */     Blocks.field_150328_O.func_149647_a(null);
/* 374 */     Blocks.field_150338_P.func_149647_a(null);
/* 375 */     Blocks.field_150337_Q.func_149647_a(null);
/* 376 */     Blocks.field_150342_X.func_149647_a(null);
/* 377 */     Blocks.field_150478_aa.func_149647_a(null);
/* 378 */     Blocks.field_150486_ae.func_149647_a(null);
/* 379 */     Blocks.field_150344_f.func_149647_a(null);
/* 380 */     Blocks.field_150462_ai.func_149647_a(null);
/* 381 */     Blocks.field_150434_aF.func_149647_a(null);
/* 382 */     Blocks.field_150436_aH.func_149647_a(null);
/* 383 */     Blocks.field_150423_aK.func_149647_a(null);
/* 384 */     Blocks.field_150428_aP.func_149647_a(null);
/* 385 */     Blocks.field_150471_bO.func_149647_a(null);
/* 386 */     Blocks.field_150432_aD.func_149647_a(null);
/* 387 */     Blocks.field_150395_bd.func_149647_a(null);
/* 388 */     Blocks.field_150457_bL.func_149647_a(null);
/*     */     
/* 390 */     bookshelf = (new BlockCustomBookshelf()).func_149711_c(1.5F).func_149672_a(Block.field_149766_f).func_149663_c("Bookshelf").func_149658_d("bookshelf");
/* 391 */     torch = (new BlockTorch()).func_149711_c(0.0F).func_149672_a(Block.field_149766_f).func_149663_c("Torch").func_149658_d("torch_on");
/* 392 */     torchOff = (new BlockTorchOff()).func_149711_c(0.0F).func_149672_a(Block.field_149766_f).func_149663_c("TorchOff").func_149658_d("torch_on");
/* 393 */     chest = (new BlockChestTFC()).func_149711_c(2.5F).func_149672_a(Block.field_149766_f).func_149663_c("Chest");
/* 394 */     workbench = (new BlockWorkbench()).func_149711_c(2.5F).func_149672_a(Block.field_149766_f).func_149663_c("Workbench").func_149658_d("crafting_table");
/* 395 */     cactus = (new BlockCustomCactus()).func_149711_c(0.4F).func_149672_a(Block.field_149775_l).func_149663_c("Cactus").func_149658_d("cactus");
/* 396 */     reeds = (new BlockCustomReed()).func_149711_c(0.0F).func_149672_a(Block.field_149779_h).func_149663_c("Reeds").func_149658_d("reeds");
/* 397 */     pumpkin = (new BlockCustomPumpkin(false)).func_149711_c(1.0F).func_149672_a(Block.field_149766_f).func_149663_c("Pumpkin").func_149658_d("pumpkin");
/* 398 */     litPumpkin = (new BlockCustomPumpkin(true)).func_149711_c(1.0F).func_149672_a(Block.field_149766_f).func_149715_a(1.0F).func_149663_c("LitPumpkin").func_149658_d("pumpkin");
/* 399 */     buttonWood = (new BlockCustomButtonWood()).func_149711_c(0.5F).func_149672_a(Block.field_149766_f).func_149663_c("ButtonWood");
/* 400 */     vine = (new BlockCustomVine()).func_149711_c(0.2F).func_149672_a(Block.field_149779_h).func_149663_c("Vine").func_149658_d("vine");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 408 */     snow = (new BlockCustomSnow()).func_149711_c(0.1F).func_149672_a(Block.field_149773_n).func_149663_c("snow").func_149713_g(0).func_149658_d("snow");
/* 409 */     Blocks.field_150431_aC = snow;
/* 410 */     stoneIgInCobble = (new BlockIgInCobble(Material.field_151576_e)).func_149711_c(16.0F).func_149663_c("IgInRockCobble");
/* 411 */     stoneIgIn = (new BlockIgIn(Material.field_151576_e)).func_149711_c(8.0F).func_149663_c("IgInRock");
/* 412 */     stoneIgInSmooth = (new BlockIgInSmooth()).func_149711_c(16.0F).func_149663_c("IgInRockSmooth");
/* 413 */     stoneIgInBrick = (new BlockIgInBrick()).func_149711_c(16.0F).func_149663_c("IgInRockBrick");
/*     */     
/* 415 */     stoneSedCobble = (new BlockSedCobble(Material.field_151576_e)).func_149711_c(14.0F).func_149663_c("SedRockCobble");
/* 416 */     stoneSed = (new BlockSed(Material.field_151576_e)).func_149711_c(7.0F).func_149663_c("SedRock");
/* 417 */     stoneSedSmooth = (new BlockSedSmooth()).func_149711_c(14.0F).func_149663_c("SedRockSmooth");
/* 418 */     stoneSedBrick = (new BlockSedBrick()).func_149711_c(14.0F).func_149663_c("SedRockBrick");
/*     */     
/* 420 */     stoneIgExCobble = (new BlockIgExCobble(Material.field_151576_e)).func_149711_c(16.0F).func_149663_c("IgExRockCobble");
/* 421 */     stoneIgEx = (new BlockIgEx(Material.field_151576_e)).func_149711_c(8.0F).func_149663_c("IgExRock");
/* 422 */     stoneIgExSmooth = (new BlockIgExSmooth()).func_149711_c(16.0F).func_149663_c("IgExRockSmooth");
/* 423 */     stoneIgExBrick = (new BlockIgExBrick()).func_149711_c(16.0F).func_149663_c("IgExRockBrick");
/*     */     
/* 425 */     stoneMMCobble = (new BlockMMCobble(Material.field_151576_e)).func_149711_c(15.0F).func_149663_c("MMRockCobble");
/* 426 */     stoneMM = (new BlockMM(Material.field_151576_e)).func_149711_c(8.0F).func_149663_c("MMRock");
/* 427 */     stoneMMSmooth = (new BlockMMSmooth()).func_149711_c(15.0F).func_149663_c("MMRockSmooth");
/* 428 */     stoneMMBrick = (new BlockMMBrick()).func_149711_c(15.0F).func_149663_c("MMRockBrick");
/*     */     
/* 430 */     dirt = (new BlockDirt(0)).func_149711_c(2.0F).func_149672_a(Block.field_149767_g).func_149663_c("dirt");
/*     */     
/* 432 */     dirt2 = (new BlockDirt(16)).func_149711_c(2.0F).func_149672_a(Block.field_149767_g).func_149663_c("dirt");
/* 433 */     clay = (new BlockClay(0)).func_149711_c(3.0F).func_149672_a(Block.field_149767_g).func_149663_c("clay");
/* 434 */     clay2 = (new BlockClay(16)).func_149711_c(3.0F).func_149672_a(Block.field_149767_g).func_149663_c("clay");
/* 435 */     clayGrass = (new BlockClayGrass(0)).func_149711_c(3.0F).func_149672_a(Block.field_149779_h).func_149663_c("ClayGrass");
/* 436 */     clayGrass2 = (new BlockClayGrass(16)).func_149711_c(3.0F).func_149672_a(Block.field_149779_h).func_149663_c("ClayGrass");
/* 437 */     grass = (new BlockGrass()).func_149711_c(3.0F).func_149672_a(Block.field_149779_h).func_149663_c("Grass");
/* 438 */     grass2 = (new BlockGrass(16)).func_149711_c(3.0F).func_149672_a(Block.field_149779_h).func_149663_c("Grass");
/* 439 */     peat = (new BlockPeat()).func_149711_c(3.0F).func_149672_a(Block.field_149767_g).func_149663_c("Peat");
/* 440 */     peatGrass = (new BlockPeatGrass()).func_149711_c(3.0F).func_149672_a(Block.field_149779_h).func_149663_c("PeatGrass");
/* 441 */     dryGrass = (new BlockDryGrass(0)).func_149711_c(3.0F).func_149672_a(Block.field_149779_h).func_149663_c("DryGrass");
/* 442 */     dryGrass2 = (new BlockDryGrass(16)).func_149711_c(3.0F).func_149672_a(Block.field_149779_h).func_149663_c("DryGrass");
/* 443 */     tallGrass = (new BlockCustomTallGrass()).func_149711_c(0.0F).func_149672_a(Block.field_149779_h).func_149663_c("TallGrass");
/* 444 */     sand = (new BlockSand(0)).func_149711_c(0.5F).func_149672_a(Block.field_149776_m).func_149663_c("sand");
/* 445 */     sand2 = (new BlockSand(16)).func_149711_c(0.5F).func_149672_a(Block.field_149776_m).func_149663_c("sand");
/*     */     
/* 447 */     ore = (new BlockOre(Material.field_151576_e)).func_149711_c(10.0F).func_149752_b(10.0F).func_149663_c("Ore");
/* 448 */     ore2 = (new BlockOre2(Material.field_151576_e)).func_149711_c(10.0F).func_149752_b(10.0F).func_149663_c("Ore");
/* 449 */     ore3 = (new BlockOre3(Material.field_151576_e)).func_149711_c(10.0F).func_149752_b(10.0F).func_149663_c("Ore");
/* 450 */     worldItem = (new BlockWorldItem()).func_149711_c(0.05F).func_149752_b(1.0F).func_149663_c("LooseRock");
/* 451 */     sulfur = (new BlockSulfur(Material.field_151576_e)).func_149663_c("Sulfur").func_149711_c(0.5F).func_149752_b(1.0F);
/*     */     
/* 453 */     logPile = (new BlockLogPile()).func_149711_c(10.0F).func_149752_b(1.0F).func_149663_c("LogPile");
/* 454 */     woodSupportV = (new BlockWoodSupport(Material.field_151575_d)).func_149663_c("WoodSupportV").func_149711_c(0.5F).func_149752_b(1.0F);
/* 455 */     woodSupportH = (new BlockWoodSupport(Material.field_151575_d)).func_149663_c("WoodSupportH").func_149711_c(0.5F).func_149752_b(1.0F);
/* 456 */     woodSupportV2 = (new BlockWoodSupport2(Material.field_151575_d)).func_149663_c("WoodSupportV2").func_149711_c(0.5F).func_149752_b(1.0F);
/* 457 */     woodSupportH2 = (new BlockWoodSupport2(Material.field_151575_d)).func_149663_c("WoodSupportH2").func_149711_c(0.5F).func_149752_b(1.0F);
/*     */     
/* 459 */     tilledSoil = (new BlockFarmland(TFCBlocks.dirt, 0)).func_149711_c(2.0F).func_149672_a(Block.field_149767_g).func_149663_c("tilledSoil");
/* 460 */     tilledSoil2 = (new BlockFarmland(TFCBlocks.dirt2, 16)).func_149711_c(2.0F).func_149672_a(Block.field_149767_g).func_149663_c("tilledSoil");
/*     */     
/* 462 */     fruitTreeWood = (new BlockFruitWood()).func_149663_c("fruitTreeWood").func_149711_c(5.5F).func_149752_b(2.0F);
/* 463 */     fruitTreeLeaves = (new BlockFruitLeaves(0)).func_149663_c("fruitTreeLeaves").func_149711_c(0.5F).func_149752_b(1.0F).func_149672_a(Block.field_149779_h);
/* 464 */     fruitTreeLeaves2 = (new BlockFruitLeaves(8)).func_149663_c("fruitTreeLeaves2").func_149711_c(0.5F).func_149752_b(1.0F).func_149672_a(Block.field_149779_h);
/*     */     
/* 466 */     woodConstruct = (new BlockWoodConstruct()).func_149711_c(4.0F).func_149672_a(Block.field_149766_f).func_149663_c("WoodConstruct");
/*     */     
/* 468 */     firepit = (new BlockFirepit()).func_149663_c("Firepit").func_149711_c(1.0F).func_149715_a(0.0F);
/* 469 */     bellows = (new BlockBellows(Material.field_151575_d)).func_149663_c("Bellows").func_149711_c(2.0F);
/* 470 */     forge = (new BlockForge()).func_149663_c("Forge").func_149711_c(20.0F).func_149715_a(0.0F);
/* 471 */     anvil = (new BlockAnvil()).func_149663_c("Anvil").func_149711_c(3.0F).func_149752_b(100.0F);
/* 472 */     anvil2 = (new BlockAnvil(8)).func_149663_c("Anvil2").func_149711_c(3.0F).func_149752_b(100.0F);
/*     */     
/* 474 */     molten = (new BlockMolten()).func_149663_c("Molten").func_149711_c(20.0F);
/* 475 */     blastFurnace = (new BlockBlastFurnace()).func_149663_c("BlastFurnace").func_149711_c(20.0F).func_149715_a(0.0F);
/* 476 */     bloomery = (new BlockEarlyBloomery()).func_149663_c("EarlyBloomery").func_149711_c(20.0F).func_149715_a(0.0F);
/* 477 */     bloom = (new BlockBloom()).func_149663_c("Bloom").func_149711_c(20.0F).func_149715_a(0.0F);
/* 478 */     sluice = (new BlockSluice()).func_149663_c("Sluice").func_149711_c(2.0F).func_149752_b(20.0F);
/*     */     
/* 480 */     stoneStairs = (new BlockStair(Material.field_151576_e)).func_149663_c("stoneStairs").func_149711_c(10.0F);
/* 481 */     stoneSlabs = (new BlockSlab()).func_149663_c("stoneSlabs").func_149711_c(10.0F);
/* 482 */     stoneStalac = (new BlockStalactite()).func_149663_c("stoneStalac").func_149711_c(5.0F);
/*     */     
/* 484 */     charcoal = (new BlockCharcoal()).func_149711_c(3.0F).func_149752_b(10.0F).func_149663_c("Charcoal");
/*     */     
/* 486 */     detailed = (new BlockDetailed()).func_149663_c("StoneDetailed").func_149711_c(10.0F);
/*     */     
/* 488 */     planks = (new BlockPlanks(Material.field_151575_d)).func_149711_c(4.0F).func_149752_b(5.0F).func_149672_a(Block.field_149766_f).func_149663_c("wood");
/* 489 */     planks2 = (new BlockPlanks2(Material.field_151575_d)).func_149711_c(4.0F).func_149752_b(5.0F).func_149672_a(Block.field_149766_f).func_149663_c("wood2");
/* 490 */     leaves = (new BlockCustomLeaves()).func_149711_c(0.2F).func_149713_g(1).func_149672_a(Block.field_149779_h).func_149663_c("leaves").func_149647_a(TFCTabs.TFC_DECORATION);
/* 491 */     leaves2 = (new BlockCustomLeaves2()).func_149711_c(0.2F).func_149713_g(1).func_149672_a(Block.field_149779_h).func_149663_c("leaves2");
/* 492 */     sapling = (new BlockSapling()).func_149711_c(0.0F).func_149672_a(Block.field_149779_h).func_149663_c("sapling");
/* 493 */     sapling2 = (new BlockSapling2()).func_149711_c(0.0F).func_149672_a(Block.field_149779_h).func_149663_c("sapling2");
/*     */     
/* 495 */     logNatural = (new BlockLogNatural()).func_149711_c(50.0F).func_149672_a(Block.field_149766_f).func_149663_c("log");
/* 496 */     logNatural2 = (new BlockLogNatural2()).func_149711_c(50.0F).func_149672_a(Block.field_149766_f).func_149663_c("log2");
/* 497 */     woodVert = (new BlockLogVert()).func_149663_c("WoodVert").func_149711_c(20.0F).func_149752_b(15.0F).func_149672_a(Block.field_149766_f);
/* 498 */     woodVert2 = (new BlockLogVert2()).func_149663_c("WoodVert2").func_149711_c(20.0F).func_149752_b(15.0F).func_149672_a(Block.field_149766_f);
/* 499 */     woodHoriz = (new BlockLogHoriz(0)).func_149663_c("WoodHoriz").func_149711_c(20.0F).func_149752_b(15.0F).func_149672_a(Block.field_149766_f);
/* 500 */     woodHoriz2 = (new BlockLogHoriz(8)).func_149663_c("WoodHoriz2").func_149711_c(20.0F).func_149752_b(15.0F).func_149672_a(Block.field_149766_f);
/* 501 */     woodHoriz3 = (new BlockLogHoriz2(0)).func_149663_c("WoodHoriz3").func_149711_c(20.0F).func_149752_b(15.0F).func_149672_a(Block.field_149766_f);
/*     */     
/* 503 */     woodHoriz4 = (new BlockLogHoriz2(0)).func_149663_c("WoodHoriz4").func_149711_c(20.0F).func_149752_b(15.0F).func_149672_a(Block.field_149766_f);
/*     */     
/* 505 */     toolRack = (new BlockToolRack()).func_149711_c(3.0F).func_149663_c("Toolrack");
/* 506 */     spawnMeter = (new BlockSpawnMeter()).func_149711_c(3.0F).func_149663_c("SpawnMeter");
/* 507 */     foodPrep = (new BlockFoodPrep()).func_149711_c(1.0F).func_149663_c("FoodPrep");
/* 508 */     quern = (new BlockQuern()).func_149711_c(3.0F).func_149663_c("Quern");
/*     */ 
/*     */     
/* 511 */     wattle = (new BlockWattle(Material.field_151575_d)).func_149711_c(0.5F).func_149663_c("Wattle Wall");
/*     */     
/* 513 */     wallCobbleIgIn = (new BlockCustomWall(stoneIgInCobble, 3)).func_149663_c("WallCobble");
/* 514 */     wallCobbleIgEx = (new BlockCustomWall(stoneIgExCobble, 4)).func_149663_c("WallCobble");
/* 515 */     wallCobbleSed = (new BlockCustomWall(stoneSedCobble, 8)).func_149663_c("WallCobble");
/* 516 */     wallCobbleMM = (new BlockCustomWall(stoneMMCobble, 6)).func_149663_c("WallCobble");
/* 517 */     wallRawIgIn = (new BlockCustomWall(stoneIgIn, 3)).func_149663_c("WallRaw");
/* 518 */     wallRawIgEx = (new BlockCustomWall(stoneIgEx, 4)).func_149663_c("WallRaw");
/* 519 */     wallRawSed = (new BlockCustomWall(stoneSed, 8)).func_149663_c("WallRaw");
/* 520 */     wallRawMM = (new BlockCustomWall(stoneMM, 6)).func_149663_c("WallRaw");
/* 521 */     wallBrickIgIn = (new BlockCustomWall(stoneIgInBrick, 3)).func_149663_c("WallBrick");
/* 522 */     wallBrickIgEx = (new BlockCustomWall(stoneIgExBrick, 4)).func_149663_c("WallBrick");
/* 523 */     wallBrickSed = (new BlockCustomWall(stoneSedBrick, 8)).func_149663_c("WallBrick");
/* 524 */     wallBrickMM = (new BlockCustomWall(stoneMMBrick, 6)).func_149663_c("WallBrick");
/* 525 */     wallSmoothIgIn = (new BlockCustomWall(stoneIgInSmooth, 3)).func_149663_c("WallSmooth");
/* 526 */     wallSmoothIgEx = (new BlockCustomWall(stoneIgExSmooth, 4)).func_149663_c("WallSmooth");
/* 527 */     wallSmoothSed = (new BlockCustomWall(stoneSedSmooth, 8)).func_149663_c("WallSmooth");
/* 528 */     wallSmoothMM = (new BlockCustomWall(stoneMMSmooth, 6)).func_149663_c("WallSmooth");
/*     */ 
/*     */     
/* 531 */     for (int i = 0; i < Global.WOOD_ALL.length; i++) {
/* 532 */       doors[i] = (new BlockCustomDoor(i * 2)).func_149663_c("Door " + Global.WOOD_ALL[i]);
/*     */     }
/*     */     
/* 535 */     wattleDoor = (new BlockCustomWattleDoor(0)).func_149663_c("Door Wattle");
/*     */     
/* 537 */     ingotPile = (new BlockIngotPile()).func_149663_c("ingotpile").func_149711_c(3.0F);
/*     */     
/* 539 */     barrel = (new BlockBarrel()).func_149663_c("Barrel").func_149711_c(2.0F);
/* 540 */     loom = (new BlockLoom()).func_149663_c("Loom").func_149711_c(2.0F);
/* 541 */     thatch = (new BlockThatch()).func_149663_c("Thatch").func_149711_c(1.0F).func_149672_a(Block.field_149779_h).func_149647_a(TFCTabs.TFC_BUILDING);
/* 542 */     moss = (new BlockMoss()).func_149663_c("Moss").func_149711_c(1.0F).func_149672_a(Block.field_149779_h);
/*     */     
/* 544 */     flora = (new BlockFlora()).func_149663_c("Flora").func_149711_c(0.1F).func_149672_a(Block.field_149779_h);
/* 545 */     pottery = (new BlockPottery()).func_149663_c("Pottery").func_149711_c(1.0F);
/*     */     
/* 547 */     crucible = (new BlockCrucible()).func_149663_c("Crucible").func_149711_c(4.0F);
/*     */     
/* 549 */     nestBox = (new BlockNestBox()).func_149663_c("NestBox").func_149711_c(1.0F);
/*     */     
/* 551 */     fence = (new BlockTFCFence("Fence", Material.field_151575_d)).func_149663_c("FenceTFC").func_149711_c(2.0F);
/* 552 */     fenceGate = (new BlockCustomFenceGate()).func_149663_c("FenceGateTFC").func_149711_c(2.0F);
/* 553 */     fence2 = (new BlockTFCFence2("Fence2", Material.field_151575_d)).func_149663_c("FenceTFC").func_149711_c(2.0F);
/* 554 */     fenceGate2 = (new BlockCustomFenceGate2()).func_149663_c("FenceGateTFC").func_149711_c(2.0F);
/* 555 */     strawHideBed = (new BlockBed()).func_149663_c("StrawHideBed").func_149711_c(1.0F).func_149647_a(null);
/* 556 */     armorStand = (new BlockStand()).func_149663_c("ArmourStand").func_149711_c(2.0F);
/* 557 */     armorStand2 = (new BlockStand2()).func_149663_c("ArmourStand").func_149711_c(2.0F);
/*     */     
/* 559 */     berryBush = (new BlockBerryBush()).func_149663_c("BerryBush").func_149711_c(0.3F).func_149672_a(Block.field_149779_h);
/* 560 */     crops = (new BlockCrop()).func_149663_c("crops").func_149711_c(0.3F).func_149672_a(Block.field_149779_h);
/* 561 */     lilyPad = (new BlockCustomLilyPad()).func_149711_c(0.0F).func_149672_a(Block.field_149779_h).func_149663_c("LilyPad").func_149658_d("waterlily");
/* 562 */     flowers = (new BlockFlower()).func_149711_c(0.0F).func_149672_a(Block.field_149779_h).func_149663_c("Flowers");
/* 563 */     flowers2 = (new BlockFlower2()).func_149711_c(0.0F).func_149672_a(Block.field_149779_h).func_149663_c("Flowers2");
/* 564 */     fungi = (new BlockFungi()).func_149711_c(0.0F).func_149672_a(Block.field_149779_h).func_149663_c("Fungi");
/*     */     
/* 566 */     saltWater = (new BlockSaltWater(TFCFluids.SALTWATER)).func_149711_c(100.0F).func_149713_g(3).func_149663_c("SaltWater");
/* 567 */     saltWaterStationary = (new BlockLiquidStatic(TFCFluids.SALTWATER, Material.field_151586_h, saltWater)).func_149711_c(100.0F).func_149713_g(3).func_149663_c("SaltWaterStationary");
/*     */     
/* 569 */     freshWater = (new BlockFreshWater(TFCFluids.FRESHWATER)).func_149711_c(100.0F).func_149713_g(3).func_149663_c("FreshWater");
/* 570 */     freshWaterStationary = (new BlockLiquidStatic(TFCFluids.FRESHWATER, Material.field_151586_h, freshWater)).func_149711_c(100.0F).func_149713_g(3).func_149663_c("FreshWaterStationary");
/*     */     
/* 572 */     hotWater = (new BlockHotWater(TFCFluids.HOTWATER)).func_149711_c(100.0F).func_149713_g(3).func_149663_c("HotWater");
/* 573 */     hotWaterStationary = (new BlockHotWaterStatic(TFCFluids.HOTWATER, Material.field_151586_h, hotWater)).func_149711_c(100.0F).func_149713_g(3).func_149663_c("HotWaterStationary");
/*     */ 
/*     */     
/* 576 */     lava = (new BlockLava()).func_149711_c(0.0F).func_149715_a(1.0F).func_149713_g(255).func_149663_c("Lava");
/* 577 */     lavaStationary = (new BlockLiquidStatic(TFCFluids.LAVA, Material.field_151587_i, lava)).func_149711_c(0.0F).func_149715_a(1.0F).func_149713_g(255).func_149663_c("LavaStationary");
/* 578 */     ice = (new BlockCustomIce()).func_149711_c(0.5F).func_149713_g(3).func_149672_a(Block.field_149778_k).func_149663_c("Ice").func_149658_d("ice");
/*     */     
/* 580 */     waterPlant = (new BlockWaterPlant(0)).func_149663_c("SeaGrassStill").func_149711_c(0.5F).func_149672_a(Block.field_149767_g);
/*     */     
/* 582 */     fireBrick = (new BlockFireBrick()).func_149663_c("FireBrick").func_149711_c(8.0F);
/* 583 */     metalSheet = (new BlockMetalSheet()).func_149663_c("MetalSheet").func_149711_c(80.0F);
/* 584 */     leatherRack = (new BlockLeatherRack()).func_149663_c("LeatherRack").func_149711_c(1.0F);
/*     */     
/* 586 */     gravel = (new BlockGravel(0)).func_149711_c(2.0F).func_149672_a(Block.field_149767_g).func_149663_c("gravel");
/* 587 */     gravel2 = (new BlockGravel(16)).func_149711_c(2.0F).func_149672_a(Block.field_149767_g).func_149663_c("gravel");
/*     */     
/* 589 */     grill = (new BlockGrill()).func_149711_c(2.0F).func_149663_c("Grill");
/* 590 */     metalTrapDoor = (new BlockMetalTrapDoor()).func_149711_c(2.0F).func_149663_c("MetalTrapDoor");
/* 591 */     vessel = (new BlockLargeVessel()).func_149711_c(1.0F).func_149663_c("Vessel");
/* 592 */     smoke = (new BlockSmoke()).func_149711_c(0.0F).func_149663_c("Smoke");
/* 593 */     smokeRack = (new BlockSmokeRack()).func_149711_c(0.0F).func_149663_c("SmokeRack");
/*     */     
/* 595 */     oilLamp = (new BlockOilLamp()).func_149711_c(1.0F).func_149663_c("OilLamp");
/* 596 */     hopper = (new BlockHopper()).func_149711_c(2.0F).func_149663_c("Hopper");
/* 597 */     flowerPot = (new BlockCustomFlowerPot()).func_149711_c(0.0F).func_149672_a(Block.field_149769_e).func_149663_c("FlowerPot").func_149658_d("flower_pot");
/*     */     
/* 599 */     stoneIgIn.setHarvestLevel("pickaxe", 0);
/* 600 */     stoneIgEx.setHarvestLevel("pickaxe", 0);
/* 601 */     stoneSed.setHarvestLevel("pickaxe", 0);
/* 602 */     stoneMM.setHarvestLevel("pickaxe", 0);
/* 603 */     stoneStalac.setHarvestLevel("pickaxe", 0);
/* 604 */     detailed.setHarvestLevel("pickaxe", 0);
/* 605 */     ore.setHarvestLevel("pickaxe", 1);
/* 606 */     ore2.setHarvestLevel("pickaxe", 1);
/* 607 */     ore3.setHarvestLevel("pickaxe", 1);
/*     */     
/* 609 */     dirt.setHarvestLevel("shovel", 0);
/* 610 */     dirt2.setHarvestLevel("shovel", 0);
/* 611 */     grass.setHarvestLevel("shovel", 0);
/* 612 */     grass2.setHarvestLevel("shovel", 0);
/* 613 */     dryGrass.setHarvestLevel("shovel", 0);
/* 614 */     dryGrass2.setHarvestLevel("shovel", 0);
/* 615 */     clay.setHarvestLevel("shovel", 0);
/* 616 */     clay2.setHarvestLevel("shovel", 0);
/* 617 */     clayGrass.setHarvestLevel("shovel", 0);
/* 618 */     clayGrass2.setHarvestLevel("shovel", 0);
/* 619 */     peat.setHarvestLevel("shovel", 0);
/* 620 */     peatGrass.setHarvestLevel("shovel", 0);
/* 621 */     sand.setHarvestLevel("shovel", 0);
/* 622 */     sand2.setHarvestLevel("shovel", 0);
/* 623 */     charcoal.setHarvestLevel("shovel", 0);
/* 624 */     gravel.setHarvestLevel("shovel", 0);
/* 625 */     gravel2.setHarvestLevel("shovel", 0);
/* 626 */     waterPlant.setHarvestLevel("shovel", 0);
/* 627 */     tilledSoil.setHarvestLevel("shovel", 0);
/* 628 */     tilledSoil2.setHarvestLevel("shovel", 0);
/*     */     
/* 630 */     detailed.setHarvestLevel("axe", 0);
/* 631 */     Blocks.field_150476_ad.setHarvestLevel("axe", 0);
/* 632 */     woodConstruct.setHarvestLevel("axe", 0);
/* 633 */     logNatural.setHarvestLevel("axe", 1);
/* 634 */     logNatural2.setHarvestLevel("axe", 1);
/* 635 */     woodHoriz.setHarvestLevel("axe", 1);
/* 636 */     woodHoriz2.setHarvestLevel("axe", 1);
/* 637 */     woodHoriz3.setHarvestLevel("axe", 1);
/* 638 */     woodHoriz4.setHarvestLevel("axe", 1);
/* 639 */     woodVert.setHarvestLevel("axe", 1);
/* 640 */     woodVert2.setHarvestLevel("axe", 1);
/*     */     
/* 642 */     logNatural.setHarvestLevel("hammer", 1);
/* 643 */     logNatural2.setHarvestLevel("hammer", 1);
/* 644 */     woodHoriz.setHarvestLevel("hammer", 1);
/* 645 */     woodHoriz2.setHarvestLevel("hammer", 1);
/* 646 */     woodHoriz3.setHarvestLevel("hammer", 1);
/* 647 */     woodHoriz4.setHarvestLevel("hammer", 1);
/* 648 */     woodVert.setHarvestLevel("hammer", 1);
/* 649 */     woodVert2.setHarvestLevel("hammer", 1);
/*     */     
/* 651 */     stoneSlabs.setHarvestLevel("axe", 4);
/* 652 */     stoneStairs.setHarvestLevel("axe", 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setupFire() {
/* 659 */     Blocks.field_150480_ab.setFireInfo(logNatural, 5, 5);
/* 660 */     Blocks.field_150480_ab.setFireInfo(logNatural2, 5, 5);
/* 661 */     Blocks.field_150480_ab.setFireInfo(woodSupportV, 5, 20);
/* 662 */     Blocks.field_150480_ab.setFireInfo(woodSupportV2, 5, 20);
/* 663 */     Blocks.field_150480_ab.setFireInfo(woodSupportH, 5, 20);
/* 664 */     Blocks.field_150480_ab.setFireInfo(woodSupportH2, 5, 20);
/* 665 */     Blocks.field_150480_ab.setFireInfo(leaves, 20, 20);
/* 666 */     Blocks.field_150480_ab.setFireInfo(leaves2, 20, 20);
/* 667 */     Blocks.field_150480_ab.setFireInfo(fruitTreeWood, 5, 20);
/* 668 */     Blocks.field_150480_ab.setFireInfo(fruitTreeLeaves, 20, 20);
/* 669 */     Blocks.field_150480_ab.setFireInfo(fruitTreeLeaves2, 20, 20);
/* 670 */     Blocks.field_150480_ab.setFireInfo(fence, 5, 20);
/* 671 */     Blocks.field_150480_ab.setFireInfo(fence2, 5, 20);
/* 672 */     Blocks.field_150480_ab.setFireInfo(fenceGate, 5, 20);
/* 673 */     Blocks.field_150480_ab.setFireInfo(fenceGate2, 5, 20);
/* 674 */     Blocks.field_150480_ab.setFireInfo(chest, 5, 20);
/* 675 */     Blocks.field_150480_ab.setFireInfo(strawHideBed, 20, 20);
/* 676 */     Blocks.field_150480_ab.setFireInfo(thatch, 20, 20);
/* 677 */     Blocks.field_150480_ab.setFireInfo(woodVert, 5, 5);
/* 678 */     Blocks.field_150480_ab.setFireInfo(woodVert2, 5, 5);
/* 679 */     Blocks.field_150480_ab.setFireInfo(woodHoriz, 5, 5);
/* 680 */     Blocks.field_150480_ab.setFireInfo(woodHoriz2, 5, 5);
/* 681 */     Blocks.field_150480_ab.setFireInfo(woodHoriz3, 5, 5);
/* 682 */     Blocks.field_150480_ab.setFireInfo(woodHoriz4, 5, 5);
/* 683 */     Blocks.field_150480_ab.setFireInfo(planks, 5, 20);
/* 684 */     Blocks.field_150480_ab.setFireInfo(planks2, 5, 20);
/* 685 */     Blocks.field_150480_ab.setFireInfo(woodConstruct, 5, 20);
/* 686 */     Blocks.field_150480_ab.setFireInfo(berryBush, 20, 20);
/* 687 */     Blocks.field_150480_ab.setFireInfo(barrel, 5, 20);
/* 688 */     Blocks.field_150480_ab.setFireInfo(crops, 20, 20);
/* 689 */     Blocks.field_150480_ab.setFireInfo(logPile, 10, 10);
/*     */     
/* 691 */     for (int i = 0; i < Global.WOOD_ALL.length; i++) {
/* 692 */       Blocks.field_150480_ab.setFireInfo(doors[i], 5, 20);
/*     */     }
/* 694 */     Blocks.field_150480_ab.setFireInfo(wattle, 5, 20);
/* 695 */     Blocks.field_150480_ab.setFireInfo(wattleDoor, 20, 20);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\BlockSetup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */