/*      */ package com.bioxx.tfc.Core;
/*      */ 
/*      */ import com.bioxx.tfc.Chunkdata.ChunkData;
/*      */ import com.bioxx.tfc.Chunkdata.ChunkDataManager;
/*      */ import com.bioxx.tfc.Core.Player.BodyTempStats;
/*      */ import com.bioxx.tfc.Core.Player.FoodStatsTFC;
/*      */ import com.bioxx.tfc.Core.Player.InventoryPlayerTFC;
/*      */ import com.bioxx.tfc.Core.Player.SkillStats;
/*      */ import com.bioxx.tfc.Food.ItemFoodTFC;
/*      */ import com.bioxx.tfc.Items.ItemBlocks.ItemTerraBlock;
/*      */ import com.bioxx.tfc.Items.ItemOre;
/*      */ import com.bioxx.tfc.Items.ItemTerra;
/*      */ import com.bioxx.tfc.TerraFirmaCraft;
/*      */ import com.bioxx.tfc.TileEntities.TEIngotPile;
/*      */ import com.bioxx.tfc.TileEntities.TEMetalSheet;
/*      */ import com.bioxx.tfc.WorldGen.TFCBiome;
/*      */ import com.bioxx.tfc.api.Constant.Global;
/*      */ import com.bioxx.tfc.api.Entities.IAnimal;
/*      */ import com.bioxx.tfc.api.Enums.EnumFuelMaterial;
/*      */ import com.bioxx.tfc.api.Enums.EnumSize;
/*      */ import com.bioxx.tfc.api.Food;
/*      */ import com.bioxx.tfc.api.Interfaces.IFood;
/*      */ import com.bioxx.tfc.api.Interfaces.ISize;
/*      */ import com.bioxx.tfc.api.TFCBlocks;
/*      */ import com.bioxx.tfc.api.TFCItems;
/*      */ import com.bioxx.tfc.api.TFCOptions;
/*      */ import com.bioxx.tfc.api.TFC_ItemHeat;
/*      */ import cpw.mods.fml.common.FMLCommonHandler;
/*      */ import cpw.mods.fml.common.registry.GameData;
/*      */ import cpw.mods.fml.relauncher.ReflectionHelper;
/*      */ import cpw.mods.fml.relauncher.Side;
/*      */ import cpw.mods.fml.relauncher.SideOnly;
/*      */ import growthcraft.rice.GrowthCraftRice;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Random;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.gui.ScaledResolution;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.SharedMonsterAttributes;
/*      */ import net.minecraft.entity.item.EntityItem;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.entity.player.InventoryPlayer;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.inventory.IInventory;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTBase;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.util.EnumChatFormatting;
/*      */ import net.minecraft.util.IChatComponent;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.StatCollector;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.IBlockAccess;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.biome.BiomeGenBase;
/*      */ import net.minecraft.world.storage.WorldInfo;
/*      */ import net.minecraftforge.common.util.ForgeDirection;
/*      */ import org.lwjgl.input.Keyboard;
/*      */ import org.lwjgl.input.Mouse;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class TFC_Core
/*      */ {
/*   86 */   private static Map<Integer, ChunkDataManager> cdmMap = new HashMap<>();
/*      */   public static boolean preventEntityDataUpdate;
/*      */   public static boolean hasItemChanged;
/*      */   
/*      */   public static ChunkDataManager getCDM(World world) {
/*   91 */     int key = world.field_72995_K ? (0x80 | world.field_73011_w.field_76574_g) : world.field_73011_w.field_76574_g;
/*   92 */     return cdmMap.get(Integer.valueOf(key));
/*      */   }
/*      */ 
/*      */   
/*      */   public static ChunkDataManager addCDM(World world) {
/*   97 */     int key = world.field_72995_K ? (0x80 | world.field_73011_w.field_76574_g) : world.field_73011_w.field_76574_g;
/*   98 */     if (!cdmMap.containsKey(Integer.valueOf(key)))
/*   99 */       return cdmMap.put(Integer.valueOf(key), new ChunkDataManager(world)); 
/*  100 */     return cdmMap.get(Integer.valueOf(key));
/*      */   }
/*      */ 
/*      */   
/*      */   public static ChunkDataManager removeCDM(World world) {
/*  105 */     int key = world.field_72995_K ? (0x80 | world.field_73011_w.field_76574_g) : world.field_73011_w.field_76574_g;
/*  106 */     return cdmMap.remove(Integer.valueOf(key));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public static int getMouseX() {
/*  113 */     ScaledResolution scaledresolution = new ScaledResolution(Minecraft.func_71410_x(), (Minecraft.func_71410_x()).field_71443_c, (Minecraft.func_71410_x()).field_71440_d);
/*  114 */     int i = scaledresolution.func_78326_a();
/*  115 */     int k = Mouse.getX() * i / (Minecraft.func_71410_x()).field_71443_c;
/*      */     
/*  117 */     return k;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public static int getMouseY() {
/*  124 */     ScaledResolution scaledresolution = new ScaledResolution(Minecraft.func_71410_x(), (Minecraft.func_71410_x()).field_71443_c, (Minecraft.func_71410_x()).field_71440_d);
/*  125 */     int j = scaledresolution.func_78328_b();
/*  126 */     int l = j - Mouse.getY() * j / (Minecraft.func_71410_x()).field_71440_d - 1;
/*      */     
/*  128 */     return l;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Boolean isBlockAboveSolid(IBlockAccess blockAccess, int i, int j, int k) {
/*  133 */     if (TerraFirmaCraft.proxy.getCurrentWorld().func_147439_a(i, j + 1, k).func_149662_c())
/*  134 */       return Boolean.valueOf(true); 
/*  135 */     return Boolean.valueOf(false);
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getExtraEquipInventorySize() {
/*  140 */     return 1;
/*      */   }
/*      */ 
/*      */   
/*      */   public static InventoryPlayer getNewInventory(EntityPlayer player) {
/*  145 */     InventoryPlayer ip = player.field_71071_by;
/*  146 */     NBTTagList nbt = new NBTTagList();
/*  147 */     nbt = player.field_71071_by.func_70442_a(nbt);
/*  148 */     InventoryPlayerTFC inventoryPlayerTFC = new InventoryPlayerTFC(player);
/*  149 */     inventoryPlayerTFC.func_70443_b(nbt);
/*  150 */     return (InventoryPlayer)inventoryPlayerTFC;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static ItemStack randomGem(Random random, int rockType) {
/*  156 */     Item[] gems = { TFCItems.gemAgate, TFCItems.gemAmethyst, TFCItems.gemBeryl, TFCItems.gemEmerald, TFCItems.gemGarnet, TFCItems.gemJade, TFCItems.gemJasper, TFCItems.gemOpal, TFCItems.gemRuby, TFCItems.gemSapphire, TFCItems.gemTourmaline, TFCItems.gemTopaz };
/*      */     
/*  158 */     for (int i = 500, z = 0; z < 5; i *= 2, z++) {
/*  159 */       if (random.nextInt(i) == 0) return new ItemStack(gems[random.nextInt(gems.length)], 1, z); 
/*  160 */     }  return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void surroundWithLeaves(World world, int i, int j, int k, int meta, Random r) {
/*  165 */     for (int y = 2; y >= -2; y--) {
/*      */       
/*  167 */       for (int x = 2; x >= -2; x--) {
/*      */         
/*  169 */         for (int z = 2; z >= -2; z--) {
/*      */           
/*  171 */           if (world.func_147437_c(i + x, j + y, k + z)) {
/*  172 */             world.func_147465_d(i + x, j + y, k + z, TFCBlocks.leaves, meta, 2);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void setupWorld(World world) {
/*  180 */     long seed = world.func_72905_C();
/*  181 */     Random r = new Random(seed);
/*  182 */     world.field_73011_w.func_76558_a(world);
/*  183 */     Recipes.registerAnvilRecipes(r, world);
/*  184 */     TFC_Time.updateTime(world);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setupWorld(World w, long seed) {
/*      */     try {
/*  194 */       ReflectionHelper.setPrivateValue(WorldInfo.class, w.func_72912_H(), Long.valueOf(seed), 0);
/*  195 */       setupWorld(w);
/*      */     }
/*  197 */     catch (Exception exception) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isRawStone(World world, int x, int y, int z) {
/*  204 */     Block block = world.func_147439_a(x, y, z);
/*  205 */     return (block == TFCBlocks.stoneIgEx || block == TFCBlocks.stoneIgIn || block == TFCBlocks.stoneSed || block == TFCBlocks.stoneMM);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isSmoothStone(World world, int x, int y, int z) {
/*  213 */     Block block = world.func_147439_a(x, y, z);
/*  214 */     return (block == TFCBlocks.stoneIgExSmooth || block == TFCBlocks.stoneIgInSmooth || block == TFCBlocks.stoneSedSmooth || block == TFCBlocks.stoneMMSmooth);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isSmoothStone(Block block) {
/*  222 */     return (block == TFCBlocks.stoneIgExSmooth || block == TFCBlocks.stoneIgInSmooth || block == TFCBlocks.stoneSedSmooth || block == TFCBlocks.stoneMMSmooth);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isBrickStone(Block block) {
/*  230 */     return (block == TFCBlocks.stoneIgExBrick || block == TFCBlocks.stoneIgInBrick || block == TFCBlocks.stoneSedBrick || block == TFCBlocks.stoneMMBrick);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isRawStone(Block block) {
/*  238 */     return (block == TFCBlocks.stoneIgEx || block == TFCBlocks.stoneIgIn || block == TFCBlocks.stoneSed || block == TFCBlocks.stoneMM);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isOreStone(Block block) {
/*  246 */     return (block == TFCBlocks.ore || block == TFCBlocks.ore2 || block == TFCBlocks.ore3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isNaturalStone(Block block) {
/*  253 */     return (isRawStone(block) || isOreStone(block));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isCobbleStone(Block block) {
/*  258 */     return (block == TFCBlocks.stoneIgExCobble || block == TFCBlocks.stoneIgInCobble || block == TFCBlocks.stoneSedCobble || block == TFCBlocks.stoneMMCobble);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isStoneIgEx(Block block) {
/*  266 */     return (block == TFCBlocks.stoneIgEx || block == TFCBlocks.stoneIgExCobble || block == TFCBlocks.stoneIgExSmooth || block == TFCBlocks.stoneIgExBrick || block == TFCBlocks.wallRawIgEx || block == TFCBlocks.wallCobbleIgEx || block == TFCBlocks.wallBrickIgEx || block == TFCBlocks.wallSmoothIgEx);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isStoneIgIn(Block block) {
/*  278 */     return (block == TFCBlocks.stoneIgIn || block == TFCBlocks.stoneIgInCobble || block == TFCBlocks.stoneIgInSmooth || block == TFCBlocks.stoneIgInBrick || block == TFCBlocks.wallRawIgIn || block == TFCBlocks.wallCobbleIgIn || block == TFCBlocks.wallBrickIgIn || block == TFCBlocks.wallSmoothIgIn);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isStoneSed(Block block) {
/*  290 */     return (block == TFCBlocks.stoneSed || block == TFCBlocks.stoneSedCobble || block == TFCBlocks.stoneSedSmooth || block == TFCBlocks.stoneSedBrick || block == TFCBlocks.wallRawSed || block == TFCBlocks.wallCobbleSed || block == TFCBlocks.wallBrickSed || block == TFCBlocks.wallSmoothSed);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isStoneMM(Block block) {
/*  302 */     return (block == TFCBlocks.stoneMM || block == TFCBlocks.stoneMMCobble || block == TFCBlocks.stoneMMSmooth || block == TFCBlocks.stoneMMBrick || block == TFCBlocks.wallRawMM || block == TFCBlocks.wallCobbleMM || block == TFCBlocks.wallBrickMM || block == TFCBlocks.wallSmoothMM);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isDirt(Block block) {
/*  314 */     return (block == TFCBlocks.dirt || block == TFCBlocks.dirt2);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isFarmland(Block block) {
/*  320 */     return (block == TFCBlocks.tilledSoil || block == TFCBlocks.tilledSoil2 || block == GrowthCraftRice.blocks.paddyField
/*      */       
/*  322 */       .getBlock());
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isGrass(Block block) {
/*  327 */     return (block == TFCBlocks.grass || block == TFCBlocks.grass2 || block == TFCBlocks.clayGrass || block == TFCBlocks.clayGrass2 || block == TFCBlocks.peatGrass || block == TFCBlocks.dryGrass || block == TFCBlocks.dryGrass2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isGrassNormal(Block block) {
/*  338 */     return (block == TFCBlocks.grass || block == TFCBlocks.grass2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isLushGrass(Block block) {
/*  345 */     return (block == TFCBlocks.grass || block == TFCBlocks.grass2 || block == TFCBlocks.clayGrass || block == TFCBlocks.clayGrass2 || block == TFCBlocks.peatGrass);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isClayGrass(Block block) {
/*  354 */     return (block == TFCBlocks.clayGrass || block == TFCBlocks.clayGrass2);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isPeatGrass(Block block) {
/*  360 */     return (block == TFCBlocks.peatGrass);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isDryGrass(Block block) {
/*  365 */     return (block == TFCBlocks.dryGrass || block == TFCBlocks.dryGrass2);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isGrassType1(Block block) {
/*  371 */     return (block == TFCBlocks.grass || block == TFCBlocks.clayGrass || block == TFCBlocks.dryGrass);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isGrassType2(Block block) {
/*  378 */     return (block == TFCBlocks.grass2 || block == TFCBlocks.clayGrass2 || block == TFCBlocks.dryGrass2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isClay(Block block) {
/*  385 */     return (block == TFCBlocks.clay || block == TFCBlocks.clay2);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isSand(Block block) {
/*  390 */     return (block == TFCBlocks.sand || block == TFCBlocks.sand2);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isPeat(Block block) {
/*  396 */     return (block == TFCBlocks.peat);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isHotWater(Block block) {
/*  401 */     return (block == TFCBlocks.hotWater || block == TFCBlocks.hotWaterStationary);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isWater(Block block) {
/*  406 */     return (isSaltWater(block) || 
/*  407 */       isFreshWater(block));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isWaterFlowing(Block block) {
/*  412 */     return (block == TFCBlocks.saltWater || block == TFCBlocks.freshWater);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isSaltWater(Block block) {
/*  417 */     return (block == TFCBlocks.saltWater || block == TFCBlocks.saltWaterStationary);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isSaltWaterIncludeIce(Block block, int meta, Material mat) {
/*  422 */     return (block == TFCBlocks.saltWater || block == TFCBlocks.saltWaterStationary || (mat == Material.field_151588_w && meta == 0));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isFreshWater(Block block) {
/*  428 */     return (block == TFCBlocks.freshWater || block == TFCBlocks.freshWaterStationary);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isFreshWaterIncludeIce(Block block, int meta) {
/*  433 */     return (block == TFCBlocks.freshWater || block == TFCBlocks.freshWaterStationary || (block == TFCBlocks.ice && meta != 0));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isFreshWaterIncludeIce(Block block, int meta, Material mat) {
/*  439 */     return (block == TFCBlocks.freshWater || block == TFCBlocks.freshWaterStationary || (mat == Material.field_151588_w && meta != 0));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isSoil(Block block) {
/*  445 */     return (isGrass(block) || 
/*  446 */       isDirt(block) || 
/*  447 */       isClay(block) || 
/*  448 */       isPeat(block));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isSoilOrGravel(Block block) {
/*  453 */     return (isGrass(block) || 
/*  454 */       isDirt(block) || 
/*  455 */       isClay(block) || 
/*  456 */       isPeat(block) || 
/*  457 */       isGravel(block));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isGravel(Block block) {
/*  462 */     return (block == TFCBlocks.gravel || block == TFCBlocks.gravel2);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isGround(Block block) {
/*  467 */     return (isSoilOrGravel(block) || 
/*  468 */       isRawStone(block) || 
/*  469 */       isSand(block));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isGroundType1(Block block) {
/*  474 */     return (isGrassType1(block) || block == TFCBlocks.dirt || block == TFCBlocks.gravel || block == TFCBlocks.sand);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isSoilWAILA(Block block) {
/*  479 */     return (isDirt(block) || isGravel(block) || isSand(block) || isGrassNormal(block) || isDryGrass(block));
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getSoilMetaFromStone(Block inBlock, int inMeta) {
/*  484 */     if (inBlock == TFCBlocks.stoneIgIn)
/*  485 */       return inMeta; 
/*  486 */     if (inBlock == TFCBlocks.stoneSed)
/*  487 */       return inMeta + 3; 
/*  488 */     if (inBlock == TFCBlocks.stoneIgEx) {
/*  489 */       return inMeta + 11;
/*      */     }
/*      */     
/*  492 */     if (inMeta == 0)
/*  493 */       return inMeta + 15; 
/*  494 */     return inMeta - 1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSoilMeta(int inMeta) {
/*  500 */     return inMeta & 0xF;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getItemMetaFromStone(Block inBlock, int inMeta) {
/*  505 */     if (inBlock == TFCBlocks.stoneIgIn)
/*  506 */       return inMeta; 
/*  507 */     if (inBlock == TFCBlocks.stoneSed)
/*  508 */       return inMeta + 3; 
/*  509 */     if (inBlock == TFCBlocks.stoneIgEx)
/*  510 */       return inMeta + 11; 
/*  511 */     if (inBlock == TFCBlocks.stoneMM) {
/*  512 */       return inMeta + 15;
/*      */     }
/*  514 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForGrassWithRain(int inMeta, float rain) {
/*  519 */     if (rain >= 500.0F)
/*  520 */       return getTypeForGrass(inMeta); 
/*  521 */     return getTypeForDryGrass(inMeta);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForGrassWithRainByBlock(Block block, float rain) {
/*  526 */     if (rain >= 500.0F)
/*  527 */       return getTypeForGrassFromSoil(block); 
/*  528 */     return getTypeForDryGrassFromSoil(block);
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForGrass(int inMeta) {
/*  533 */     if (inMeta < 16)
/*  534 */       return TFCBlocks.grass; 
/*  535 */     return TFCBlocks.grass2;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForGrassFromDirt(Block block) {
/*  540 */     if (block == TFCBlocks.dirt)
/*  541 */       return TFCBlocks.grass; 
/*  542 */     return TFCBlocks.grass2;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForDryGrass(int inMeta) {
/*  547 */     if (inMeta < 16)
/*  548 */       return TFCBlocks.dryGrass; 
/*  549 */     return TFCBlocks.dryGrass2;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForDryGrassFromSoil(Block block) {
/*  554 */     if (block == TFCBlocks.grass)
/*  555 */       return TFCBlocks.dryGrass; 
/*  556 */     if (block == TFCBlocks.dirt)
/*  557 */       return TFCBlocks.dryGrass; 
/*  558 */     return TFCBlocks.dryGrass2;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForGrassFromSoil(Block block) {
/*  563 */     if (block == TFCBlocks.dryGrass)
/*  564 */       return TFCBlocks.grass; 
/*  565 */     if (block == TFCBlocks.dryGrass2)
/*  566 */       return TFCBlocks.grass2; 
/*  567 */     if (block == TFCBlocks.dirt)
/*  568 */       return TFCBlocks.grass; 
/*  569 */     return TFCBlocks.grass2;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForClayGrass(int inMeta) {
/*  574 */     if (inMeta < 16)
/*  575 */       return TFCBlocks.clayGrass; 
/*  576 */     return TFCBlocks.clayGrass2;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForClayGrass(Block block) {
/*  581 */     if (isGroundType1(block))
/*  582 */       return TFCBlocks.clayGrass; 
/*  583 */     return TFCBlocks.clayGrass2;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForDirt(int inMeta) {
/*  588 */     if (inMeta < 16)
/*  589 */       return TFCBlocks.dirt; 
/*  590 */     return TFCBlocks.dirt2;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForDirtFromGrass(Block block) {
/*  595 */     if (isDirt(block))
/*  596 */       return block; 
/*  597 */     if (block == TFCBlocks.grass || block == TFCBlocks.dryGrass)
/*  598 */       return TFCBlocks.dirt; 
/*  599 */     return TFCBlocks.dirt2;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForSoil(Block block) {
/*  604 */     if (isGrass(block)) {
/*      */       
/*  606 */       if (isGrassType1(block))
/*  607 */         return TFCBlocks.dirt; 
/*  608 */       if (isGrassType2(block))
/*  609 */         return TFCBlocks.dirt2; 
/*  610 */       if (isPeatGrass(block)) {
/*  611 */         return TFCBlocks.peat;
/*      */       }
/*      */     } 
/*  614 */     return block;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForClay(int inMeta) {
/*  619 */     if (inMeta < 16)
/*  620 */       return TFCBlocks.clay; 
/*  621 */     return TFCBlocks.clay2;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForClay(Block block) {
/*  626 */     if (isGroundType1(block))
/*  627 */       return TFCBlocks.clay; 
/*  628 */     return TFCBlocks.clay2;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForSand(int inMeta) {
/*  633 */     if (inMeta < 16)
/*  634 */       return TFCBlocks.sand; 
/*  635 */     return TFCBlocks.sand2;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Block getTypeForGravel(int inMeta) {
/*  640 */     if (inMeta < 16)
/*  641 */       return TFCBlocks.gravel; 
/*  642 */     return TFCBlocks.gravel2;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getRockLayerFromHeight(World world, int x, int y, int z) {
/*  647 */     ChunkData cd = getCDM(world).getData(x >> 4, z >> 4);
/*  648 */     if (cd != null) {
/*      */       
/*  650 */       int[] hm = cd.heightmap;
/*  651 */       int localX = x & 0xF;
/*  652 */       int localZ = z & 0xF;
/*  653 */       int localY = localX + localZ * 16;
/*  654 */       if (y <= TFCOptions.rockLayer3Height + hm[localY])
/*  655 */         return 2; 
/*  656 */       if (y <= TFCOptions.rockLayer2Height + hm[localY]) {
/*  657 */         return 1;
/*      */       }
/*  659 */       return 0;
/*      */     } 
/*  661 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean convertGrassToDirt(World world, int i, int j, int k) {
/*  666 */     Block block = world.func_147439_a(i, j, k);
/*  667 */     int meta = world.func_72805_g(i, j, k);
/*  668 */     if (isGrass(block)) {
/*      */       
/*  670 */       if (isGrassType1(block)) {
/*      */         
/*  672 */         world.func_147465_d(i, j, k, TFCBlocks.dirt, meta, 2);
/*  673 */         return true;
/*      */       } 
/*  675 */       if (isGrassType2(block)) {
/*      */         
/*  677 */         world.func_147465_d(i, j, k, TFCBlocks.dirt2, meta, 2);
/*  678 */         return true;
/*      */       } 
/*      */     } 
/*  681 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static EnumFuelMaterial getFuelMaterial(ItemStack is) {
/*  688 */     if (is.func_77973_b() == Item.func_150898_a(TFCBlocks.peat))
/*  689 */       return EnumFuelMaterial.PEAT; 
/*  690 */     if (is.func_77973_b() == TFCItems.coal && is.func_77960_j() == 0)
/*  691 */       return EnumFuelMaterial.COAL; 
/*  692 */     if (is.func_77973_b() == TFCItems.coal && is.func_77960_j() == 1)
/*  693 */       return EnumFuelMaterial.CHARCOAL; 
/*  694 */     if (is.func_77960_j() == 0)
/*  695 */       return EnumFuelMaterial.ASH; 
/*  696 */     if (is.func_77960_j() == 1)
/*  697 */       return EnumFuelMaterial.ASPEN; 
/*  698 */     if (is.func_77960_j() == 2)
/*  699 */       return EnumFuelMaterial.BIRCH; 
/*  700 */     if (is.func_77960_j() == 3)
/*  701 */       return EnumFuelMaterial.CHESTNUT; 
/*  702 */     if (is.func_77960_j() == 4)
/*  703 */       return EnumFuelMaterial.DOUGLASFIR; 
/*  704 */     if (is.func_77960_j() == 5)
/*  705 */       return EnumFuelMaterial.HICKORY; 
/*  706 */     if (is.func_77960_j() == 6)
/*  707 */       return EnumFuelMaterial.MAPLE; 
/*  708 */     if (is.func_77960_j() == 7)
/*  709 */       return EnumFuelMaterial.OAK; 
/*  710 */     if (is.func_77960_j() == 8)
/*  711 */       return EnumFuelMaterial.PINE; 
/*  712 */     if (is.func_77960_j() == 9)
/*  713 */       return EnumFuelMaterial.REDWOOD; 
/*  714 */     if (is.func_77960_j() == 10)
/*  715 */       return EnumFuelMaterial.SPRUCE; 
/*  716 */     if (is.func_77960_j() == 11)
/*  717 */       return EnumFuelMaterial.SYCAMORE; 
/*  718 */     if (is.func_77960_j() == 12)
/*  719 */       return EnumFuelMaterial.WHITECEDAR; 
/*  720 */     if (is.func_77960_j() == 13)
/*  721 */       return EnumFuelMaterial.WHITEELM; 
/*  722 */     if (is.func_77960_j() == 14)
/*  723 */       return EnumFuelMaterial.WILLOW; 
/*  724 */     if (is.func_77960_j() == 15)
/*  725 */       return EnumFuelMaterial.KAPOK; 
/*  726 */     if (is.func_77960_j() == 16)
/*  727 */       return EnumFuelMaterial.ACACIA; 
/*  728 */     return EnumFuelMaterial.ASPEN;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean showShiftInformation() {
/*  733 */     return (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT && Keyboard.isKeyDown(42));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean showCtrlInformation() {
/*  738 */     return (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT && Keyboard.isKeyDown(29));
/*      */   }
/*      */ 
/*      */   
/*      */   public static FoodStatsTFC getPlayerFoodStats(EntityPlayer player) {
/*  743 */     FoodStatsTFC foodstats = new FoodStatsTFC(player);
/*  744 */     foodstats.readNBT(player.getEntityData());
/*  745 */     return foodstats;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setPlayerFoodStats(EntityPlayer player, FoodStatsTFC foodstats) {
/*  750 */     foodstats.writeNBT(player.getEntityData());
/*      */   }
/*      */ 
/*      */   
/*      */   public static BodyTempStats getBodyTempStats(EntityPlayer player) {
/*  755 */     BodyTempStats body = new BodyTempStats();
/*  756 */     body.readNBT(player.getEntityData());
/*  757 */     return body;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setBodyTempStats(EntityPlayer player, BodyTempStats tempStats) {
/*  762 */     tempStats.writeNBT(player.getEntityData());
/*      */   }
/*      */ 
/*      */   
/*      */   public static SkillStats getSkillStats(EntityPlayer player) {
/*  767 */     SkillStats skills = new SkillStats(player);
/*  768 */     skills.readNBT(player.getEntityData());
/*  769 */     return skills;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setSkillStats(EntityPlayer player, SkillStats skills) {
/*  774 */     skills.writeNBT(player.getEntityData());
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isTopFaceSolid(World world, int x, int y, int z) {
/*  779 */     if (world.func_147439_a(x, y, z).func_149721_r()) return true; 
/*  780 */     if (world.func_147439_a(x, y, z) == TFCBlocks.metalSheet) {
/*      */       
/*  782 */       TEMetalSheet te = (TEMetalSheet)world.func_147438_o(x, y, z);
/*  783 */       if (te.topExists()) return true; 
/*      */     } 
/*  785 */     return world.func_147439_a(x, y, z).isSideSolid((IBlockAccess)world, x, y, z, ForgeDirection.UP);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isBottomFaceSolid(World world, int x, int y, int z) {
/*  790 */     if (world.func_147439_a(x, y, z).func_149721_r()) return true; 
/*  791 */     if (world.func_147439_a(x, y, z) == TFCBlocks.metalSheet) {
/*      */       
/*  793 */       TEMetalSheet te = (TEMetalSheet)world.func_147438_o(x, y, z);
/*  794 */       if (te.bottomExists()) return true; 
/*      */     } 
/*  796 */     return world.func_147439_a(x, y, z).isSideSolid((IBlockAccess)world, x, y, z, ForgeDirection.DOWN);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isNorthFaceSolid(World world, int x, int y, int z) {
/*  801 */     Block bid = world.func_147439_a(x, y, z);
/*  802 */     if (bid.func_149721_r()) return true; 
/*  803 */     if (bid == TFCBlocks.metalSheet) {
/*      */       
/*  805 */       TEMetalSheet te = (TEMetalSheet)world.func_147438_o(x, y, z);
/*  806 */       if (te.northExists()) return true; 
/*      */     } 
/*  808 */     return world.func_147439_a(x, y, z).isSideSolid((IBlockAccess)world, x, y, z, ForgeDirection.NORTH);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isSouthFaceSolid(World world, int x, int y, int z) {
/*  813 */     if (world.func_147439_a(x, y, z).func_149721_r()) return true; 
/*  814 */     if (world.func_147439_a(x, y, z) == TFCBlocks.metalSheet) {
/*      */       
/*  816 */       TEMetalSheet te = (TEMetalSheet)world.func_147438_o(x, y, z);
/*  817 */       if (te.southExists()) return true; 
/*      */     } 
/*  819 */     return world.func_147439_a(x, y, z).isSideSolid((IBlockAccess)world, x, y, z, ForgeDirection.SOUTH);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isEastFaceSolid(World world, int x, int y, int z) {
/*  824 */     if (world.func_147439_a(x, y, z).func_149721_r()) return true; 
/*  825 */     if (world.func_147439_a(x, y, z) == TFCBlocks.metalSheet) {
/*      */       
/*  827 */       TEMetalSheet te = (TEMetalSheet)world.func_147438_o(x, y, z);
/*  828 */       if (te.eastExists()) return true; 
/*      */     } 
/*  830 */     return world.func_147439_a(x, y, z).isSideSolid((IBlockAccess)world, x, y, z, ForgeDirection.EAST);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isWestFaceSolid(World world, int x, int y, int z) {
/*  835 */     if (world.func_147439_a(x, y, z).func_149721_r()) return true; 
/*  836 */     if (world.func_147439_a(x, y, z) == TFCBlocks.metalSheet) {
/*      */       
/*  838 */       TEMetalSheet te = (TEMetalSheet)world.func_147438_o(x, y, z);
/*  839 */       if (te.westExists()) return true; 
/*      */     } 
/*  841 */     return world.func_147439_a(x, y, z).isSideSolid((IBlockAccess)world, x, y, z, ForgeDirection.WEST);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isSurroundedSolid(World world, int x, int y, int z) {
/*  846 */     return (isNorthFaceSolid(world, x, y, z + 1) && 
/*  847 */       isSouthFaceSolid(world, x, y, z - 1) && 
/*  848 */       isEastFaceSolid(world, x - 1, y, z) && 
/*  849 */       isWestFaceSolid(world, x + 1, y, z) && 
/*  850 */       isTopFaceSolid(world, x, y - 1, z));
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isSurroundedStone(World world, int x, int y, int z) {
/*  855 */     return (world.func_147439_a(x, y, z + 1).func_149688_o() == Material.field_151576_e && world
/*  856 */       .func_147439_a(x, y, z - 1).func_149688_o() == Material.field_151576_e && world
/*  857 */       .func_147439_a(x - 1, y, z).func_149688_o() == Material.field_151576_e && world
/*  858 */       .func_147439_a(x + 1, y, z).func_149688_o() == Material.field_151576_e && world
/*  859 */       .func_147439_a(x, y - 1, z).func_149688_o() == Material.field_151576_e);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isOreIron(ItemStack is) {
/*  864 */     return (is.func_77973_b() instanceof ItemOre && ((ItemOre)is.func_77973_b()).getMetalType(is) == Global.PIGIRON);
/*      */   }
/*      */ 
/*      */   
/*      */   public static float getEntityMaxHealth(EntityLivingBase entity) {
/*  869 */     return (float)entity.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111126_e();
/*      */   }
/*      */ 
/*      */   
/*      */   public static float getPercentGrown(IAnimal animal) {
/*  874 */     float birth = animal.getBirthDay();
/*  875 */     float time = TFC_Time.getTotalDays();
/*  876 */     float percent = (time - birth) / animal.getNumberOfDaysToAdult();
/*  877 */     return Math.min(percent, 1.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void bindTexture(ResourceLocation texture) {
/*  882 */     Minecraft.func_71410_x().func_110434_K().func_110577_a(texture);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isPlayerInDebugMode(EntityPlayer player) {
/*  887 */     return TFCOptions.enableDebugMode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void addPlayerExhaustion(EntityPlayer player, float exhaustion) {
/*  895 */     FoodStatsTFC foodstats = getPlayerFoodStats(player);
/*  896 */     foodstats.addFoodExhaustion(exhaustion);
/*      */     
/*  898 */     setPlayerFoodStats(player, foodstats);
/*      */   }
/*      */ 
/*      */   
/*      */   public static float getEnvironmentalDecay(float temp) {
/*  903 */     if (temp > 0.0F) {
/*      */       
/*  905 */       float tempFactor = 1.0F - 15.0F / (15.0F + temp);
/*  906 */       return tempFactor * 2.0F;
/*      */     } 
/*      */     
/*  909 */     return 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void handleItemTicking(IInventory iinv, World world, int x, int y, int z) {
/*  918 */     handleItemTicking(iinv, world, x, y, z, 1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void handleItemTicking(ItemStack[] iinv, World world, int x, int y, int z) {
/*  927 */     handleItemTicking(iinv, world, x, y, z, 1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static ItemStack[] getIsFromNbt(ItemStack is) {
/*  933 */     if (is.func_77978_p().func_74764_b("Items")) {
/*  934 */       NBTTagList nbttaglist = is.func_77978_p().func_150295_c("Items", 10);
/*  935 */       if (nbttaglist != null) {
/*  936 */         int numItems = nbttaglist.func_74745_c();
/*  937 */         ItemStack[] barell_items = new ItemStack[numItems];
/*  938 */         Arrays.fill((Object[])barell_items, (Object)null);
/*  939 */         int count = 0;
/*  940 */         for (int i = 0; i < numItems; i++) {
/*  941 */           NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/*  942 */           if (nbttagcompound1 != null) {
/*  943 */             ItemStack onlyItem = ItemStack.func_77949_a(nbttagcompound1);
/*  944 */             if (onlyItem != null) {
/*  945 */               barell_items[count++] = onlyItem;
/*      */             }
/*      */           } 
/*      */         } 
/*  949 */         return barell_items;
/*      */       } 
/*      */     } 
/*  952 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void writeToNBT(NBTTagCompound nbt, ItemStack[] storage) {
/*  957 */     NBTTagList nbttaglist = new NBTTagList();
/*  958 */     for (int i = 0; i < storage.length; i++) {
/*      */       
/*  960 */       if (storage[i] != null) {
/*      */         
/*  962 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*  963 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/*  964 */         storage[i].func_77955_b(nbttagcompound1);
/*  965 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*      */       } 
/*      */     } 
/*  968 */     nbt.func_74782_a("Items", (NBTBase)nbttaglist);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void handleItemTicking(IInventory iinv, World world, int x, int y, int z, float environmentalDecayFactor) {
/*  980 */     for (int i = 0; !world.field_72995_K && i < iinv.func_70302_i_(); i++) {
/*      */       
/*  982 */       ItemStack is = iinv.func_70301_a(i);
/*  983 */       if (is != null && is.field_77994_a <= 0) { iinv.func_70299_a(i, null); }
/*      */       
/*  985 */       else if (is != null)
/*      */       
/*  987 */       { if (is.field_77994_a == 0) {
/*      */           
/*  989 */           iinv.func_70299_a(i, null);
/*      */         
/*      */         }
/*  992 */         else if ((!(is.func_77973_b() instanceof ItemTerra) || !((ItemTerra)is.func_77973_b()).onUpdate(is, world, x, y, z)) && (
/*  993 */           !(is.func_77973_b() instanceof ItemTerraBlock) || !((ItemTerraBlock)is.func_77973_b()).onUpdate(is, world, x, y, z))) {
/*      */ 
/*      */           
/*  996 */           if (is != null && (is.func_77973_b() instanceof com.bioxx.tfc.Items.ItemBlocks.ItemBarrels || is.func_77973_b() instanceof com.bioxx.tfc.Items.ItemBlocks.ItemLargeVessel || is.func_77973_b() instanceof com.zerren.extrafirma.items.ItemBag)) {
/*  997 */             if (is.func_77942_o()) {
/*  998 */               ItemStack[] inv = getIsFromNbt(is);
/*  999 */               if (inv != null) {
/* 1000 */                 handleItemTicking(inv, world, x, y, z, environmentalDecayFactor);
/* 1001 */                 NBTTagCompound nbt = is.func_77978_p();
/* 1002 */                 nbt.func_82580_o("Items");
/* 1003 */                 writeToNBT(nbt, inv);
/*      */               } 
/*      */             } 
/*      */           } else {
/* 1007 */             hasItemChanged = false;
/* 1008 */             is = tickDecay(is, world, x, y, z, environmentalDecayFactor, 1.0F);
/* 1009 */             if (is != null) hasItemChanged |= TFC_ItemHeat.handleItemHeat(is); 
/* 1010 */             if (hasItemChanged) iinv.func_70299_a(i, is);
/*      */           
/*      */           } 
/*      */         }  }
/*      */     
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static byte getByteFromSmallFloat(float f) {
/* 1020 */     MathHelper.func_76131_a(f, 0.5F, 1.5F);
/* 1021 */     return (byte)(Float.floatToIntBits(f) >> 16 & 0xFF);
/*      */   }
/*      */ 
/*      */   
/*      */   public static float getSmallFloatFromByte(byte b) {
/* 1026 */     return ByteBuffer.wrap(new byte[] { 63, b, 0, 0 }).getFloat();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void handleItemTicking(IInventory iinv, World world, int x, int y, int z, float environmentalDecayFactor, float baseDecayMod) {
/* 1035 */     for (int i = 0; !world.field_72995_K && i < iinv.func_70302_i_(); i++) {
/*      */       
/* 1037 */       ItemStack is = iinv.func_70301_a(i);
/* 1038 */       if (is != null && is.field_77994_a <= 0) { iinv.func_70299_a(i, null); }
/*      */       
/* 1040 */       else if (is != null)
/*      */       
/* 1042 */       { if ((!(is.func_77973_b() instanceof ItemTerra) || !((ItemTerra)is.func_77973_b()).onUpdate(is, world, x, y, z)) && (
/* 1043 */           !(is.func_77973_b() instanceof ItemTerraBlock) || !((ItemTerraBlock)is.func_77973_b()).onUpdate(is, world, x, y, z))) {
/* 1044 */           hasItemChanged = false;
/* 1045 */           is = tickDecay(is, world, x, y, z, environmentalDecayFactor, baseDecayMod);
/* 1046 */           if (is != null) hasItemChanged |= TFC_ItemHeat.handleItemHeat(is); 
/* 1047 */           if (hasItemChanged) iinv.func_70299_a(i, is);
/*      */         
/*      */         }  }
/*      */     
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void handleItemTicking(ItemStack[] iinv, World world, int x, int y, int z, float environmentalDecayFactor) {
/* 1058 */     for (int i = 0; !world.field_72995_K && i < iinv.length; i++) {
/*      */       
/* 1060 */       ItemStack is = iinv[i];
/* 1061 */       if (is != null && is.field_77994_a <= 0) { iinv[i] = null; }
/*      */       
/* 1063 */       else if (is != null)
/*      */       
/* 1065 */       { if ((!(is.func_77973_b() instanceof ItemTerra) || !((ItemTerra)is.func_77973_b()).onUpdate(is, world, x, y, z)) && (
/* 1066 */           !(is.func_77973_b() instanceof ItemTerraBlock) || !((ItemTerraBlock)is.func_77973_b()).onUpdate(is, world, x, y, z))) {
/* 1067 */           is = tickDecay(is, world, x, y, z, environmentalDecayFactor, 1.0F);
/* 1068 */           if (is != null) TFC_ItemHeat.handleItemHeat(is); 
/* 1069 */           iinv[i] = is;
/*      */         }  }
/*      */     
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ItemStack tickDecay(ItemStack is, World world, int x, int y, int z, float environmentalDecayFactor, float baseDecayMod) {
/* 1082 */     NBTTagCompound nbt = is.func_77978_p();
/* 1083 */     if (nbt == null || !nbt.func_74764_b("foodWeight") || !nbt.func_74764_b("foodDecay")) return is;
/*      */     
/* 1085 */     int decayTimer = Food.getDecayTimer(is);
/*      */     
/* 1087 */     if (decayTimer < TFC_Time.getTotalHours()) {
/*      */       
/* 1089 */       int timeDiff = (int)(TFC_Time.getTotalHours() - decayTimer);
/* 1090 */       float protMult = 1.0F;
/*      */       
/* 1092 */       if (TFCOptions.useDecayProtection)
/*      */       {
/* 1094 */         if (timeDiff > TFCOptions.decayProtectionDays * 24) {
/*      */           
/* 1096 */           decayTimer = (int)TFC_Time.getTotalHours() - 24;
/*      */         }
/* 1098 */         else if (timeDiff > 24) {
/*      */           
/* 1100 */           protMult = (1 - timeDiff / TFCOptions.decayProtectionDays * 24);
/*      */         } 
/*      */       }
/*      */       
/* 1104 */       float decay = Food.getDecay(is);
/* 1105 */       float thisDecayRate = 1.0F;
/*      */       
/* 1107 */       if (is.func_77973_b() instanceof IFood) {
/* 1108 */         thisDecayRate = ((IFood)is.func_77973_b()).getDecayRate(is);
/*      */       } else {
/*      */         
/* 1111 */         thisDecayRate = Food.getDecayRate(is);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1120 */       float temp = getCachedTemp(world, x, y, z, decayTimer);
/* 1121 */       float environmentalDecay = getEnvironmentalDecay(temp) * environmentalDecayFactor;
/*      */       
/* 1123 */       if (decay < 0.0F)
/*      */       
/* 1125 */       { float d = 1.0F * thisDecayRate * baseDecayMod * environmentalDecay;
/* 1126 */         if (decay + d < 0.0F) { decay += d; }
/* 1127 */         else { decay = 0.0F; }
/*      */          }
/* 1129 */       else if (decay == 0.0F) { decay = Food.getWeight(is) * world.field_73012_v.nextFloat() * 0.005F * TFCOptions.decayMultiplier; }
/*      */       else
/*      */       
/* 1132 */       { double fdr = (TFCOptions.foodDecayRate - 1.0F);
/* 1133 */         fdr *= (thisDecayRate * baseDecayMod * environmentalDecay * protMult * TFCOptions.decayMultiplier);
/* 1134 */         decay = (float)(decay * (1.0D + fdr)); }
/*      */       
/* 1136 */       Food.setDecayTimer(is, decayTimer + 1);
/* 1137 */       Food.setDecay(is, decay);
/* 1138 */       hasItemChanged = true;
/*      */     } 
/*      */     
/* 1141 */     if (Food.getDecay(is) / Food.getWeight(is) > 0.9F) {
/*      */       
/* 1143 */       if (is.func_77973_b() instanceof IFood) { is = ((IFood)is.func_77973_b()).onDecayed(is, world, x, y, z); }
/* 1144 */       else { is.field_77994_a = 0; }
/* 1145 */        hasItemChanged = true;
/*      */     } 
/* 1147 */     if (is != null && 
/* 1148 */       Food.getWeight(is) <= 0.1D) is.field_77994_a = 0;
/*      */     
/* 1150 */     return is;
/*      */   }
/*      */ 
/*      */   
/*      */   public static float getCachedTemp(World world, int x, int y, int z, int th) {
/* 1155 */     float cacheTemp = TFC_Climate.getCacheManager(world).getTemp(x, z, th);
/* 1156 */     if (cacheTemp != Float.MIN_VALUE)
/*      */     {
/* 1158 */       return cacheTemp;
/*      */     }
/* 1160 */     float temp = TFC_Climate.getHeightAdjustedTempSpecificDay(world, TFC_Time.getDayFromTotalHours(th), TFC_Time.getHourOfDayFromTotalHours(th), x, y, z);
/* 1161 */     addCachedTemp(world, x, z, th, temp);
/* 1162 */     return temp;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void addCachedTemp(World world, int x, int z, int th, float temp) {
/* 1167 */     TFC_Climate.getCacheManager(world).addTemp(x, z, th, temp);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void animalDropMeat(Entity e, Item i, float foodWeight) {
/* 1173 */     ItemStack is = ItemFoodTFC.createTag(new ItemStack(i, 1), foodWeight);
/* 1174 */     Random r = new Random(e.func_110124_au().getLeastSignificantBits() + e.func_110124_au().getMostSignificantBits());
/* 1175 */     Food.adjustFlavor(is, r);
/* 1176 */     e.capturedDrops.add(new EntityItem(e.field_70170_p, e.field_70165_t, e.field_70163_u, e.field_70161_v, is));
/*      */   }
/*      */ 
/*      */   
/*      */   public static Vec3 getEntityPos(Entity e) {
/* 1181 */     return Vec3.func_72443_a(e.field_70165_t, e.field_70163_u, e.field_70161_v);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void giveItemToPlayer(ItemStack is, EntityPlayer player) {
/* 1186 */     if (player.field_70170_p.field_72995_K)
/* 1187 */       return;  EntityItem ei = player.func_70099_a(is, 1.0F);
/* 1188 */     ei.field_145804_b = 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isFence(Block b) {
/* 1193 */     return (b == TFCBlocks.fence || b == TFCBlocks.fence2);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isVertSupport(Block b) {
/* 1198 */     return (b == TFCBlocks.woodSupportV || b == TFCBlocks.woodSupportV2);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isHorizSupport(Block b) {
/* 1203 */     return (b == TFCBlocks.woodSupportH || b == TFCBlocks.woodSupportH2);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isOceanicBiome(int id) {
/* 1208 */     return (id == TFCBiome.OCEAN.field_76756_M || id == TFCBiome.DEEP_OCEAN.field_76756_M);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isMountainBiome(int id) {
/* 1213 */     return (id == TFCBiome.MOUNTAINS.field_76756_M || id == TFCBiome.MOUNTAINS_EDGE.field_76756_M);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isBeachBiome(int id) {
/* 1218 */     return (id == TFCBiome.BEACH.field_76756_M || id == TFCBiome.GRAVEL_BEACH.field_76756_M);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isValidCharcoalPitCover(Block block) {
/* 1223 */     if (Blocks.field_150480_ab.getFlammability(block) > 0 && block != TFCBlocks.logPile) return false;
/*      */     
/* 1225 */     return (block == TFCBlocks.logPile || 
/* 1226 */       isCobbleStone(block) || 
/* 1227 */       isBrickStone(block) || 
/* 1228 */       isSmoothStone(block) || 
/* 1229 */       isGround(block) || block == Blocks.field_150359_w || block == Blocks.field_150399_cn || block == TFCBlocks.metalTrapDoor || block == Blocks.field_150454_av || block
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1234 */       .func_149662_c());
/*      */   }
/*      */ 
/*      */   
/*      */   public static void writeInventoryToNBT(NBTTagCompound nbt, ItemStack[] storage) {
/* 1239 */     writeInventoryToNBT(nbt, storage, "Items");
/*      */   }
/*      */ 
/*      */   
/*      */   public static void writeInventoryToNBT(NBTTagCompound nbt, ItemStack[] storage, String name) {
/* 1244 */     NBTTagList nbttaglist = new NBTTagList();
/* 1245 */     for (int i = 0; i < storage.length; i++) {
/*      */       
/* 1247 */       if (storage[i] != null) {
/*      */         
/* 1249 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 1250 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 1251 */         storage[i].func_77955_b(nbttagcompound1);
/* 1252 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*      */       } 
/*      */     } 
/* 1255 */     nbt.func_74782_a(name, (NBTBase)nbttaglist);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void readInventoryFromNBT(NBTTagCompound nbt, ItemStack[] storage) {
/* 1260 */     readInventoryFromNBT(nbt, storage, "Items");
/*      */   }
/*      */ 
/*      */   
/*      */   public static void readInventoryFromNBT(NBTTagCompound nbt, ItemStack[] storage, String name) {
/* 1265 */     NBTTagList nbttaglist = nbt.func_150295_c(name, 10);
/* 1266 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*      */       
/* 1268 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 1269 */       byte byte0 = nbttagcompound1.func_74771_c("Slot");
/* 1270 */       if (byte0 >= 0 && byte0 < storage.length) {
/* 1271 */         storage[byte0] = ItemStack.func_77949_a(nbttagcompound1);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public static ItemStack getItemInInventory(Item item, IInventory iinv) {
/* 1277 */     for (int i = 0; i < iinv.func_70302_i_(); i++) {
/*      */       
/* 1279 */       ItemStack is = iinv.func_70301_a(i);
/* 1280 */       if (is != null && is.func_77973_b() == item) return is; 
/*      */     } 
/* 1282 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void destroyBlock(World world, int x, int y, int z) {
/* 1287 */     if (world.func_147439_a(x, y, z) != Blocks.field_150350_a) {
/*      */       
/* 1289 */       world.func_147439_a(x, y, z).func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
/* 1290 */       world.func_147468_f(x, y, z);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean areItemsEqual(ItemStack is1, ItemStack is2) {
/* 1296 */     Item i1 = null; int d1 = 0;
/* 1297 */     Item i2 = null; int d2 = 0;
/* 1298 */     if (is1 != null) {
/*      */       
/* 1300 */       i1 = is1.func_77973_b(); d1 = is1.func_77960_j();
/*      */     } 
/* 1302 */     if (is2 != null) {
/*      */       
/* 1304 */       i2 = is2.func_77973_b(); d2 = is2.func_77960_j();
/*      */     } 
/* 1306 */     return (i1 == i2 && d1 == d2);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean setBlockWithDrops(World world, int x, int y, int z, Block b, int meta) {
/* 1311 */     Block block = world.func_147439_a(x, y, z);
/*      */     
/* 1313 */     if (block.func_149688_o() != Material.field_151579_a) {
/*      */       
/* 1315 */       int l = world.func_72805_g(x, y, z);
/* 1316 */       world.func_72926_e(2001, x, y, z, Block.func_149682_b(block) + (l << 12));
/* 1317 */       block.func_149697_b(world, x, y, z, l, 0);
/*      */     } 
/* 1319 */     return world.func_147465_d(x, y, z, b, meta, 3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean setBlockToAirWithDrops(World world, int x, int y, int z) {
/* 1327 */     return world.func_147480_a(x, y, z, true);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isWaterBiome(BiomeGenBase b) {
/* 1332 */     return (isBeachBiome(b.field_76756_M) || isOceanicBiome(b.field_76756_M) || b == TFCBiome.LAKE || b == TFCBiome.RIVER);
/*      */   }
/*      */ 
/*      */   
/*      */   public static String translate(String s) {
/* 1337 */     return StatCollector.func_74838_a(s);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void sendInfoMessage(EntityPlayer player, IChatComponent text) {
/* 1342 */     text.func_150256_b().func_150238_a(EnumChatFormatting.GRAY).func_150217_b(Boolean.valueOf(true));
/* 1343 */     player.func_146105_b(text);
/*      */   }
/*      */ 
/*      */   
/*      */   public static long getSuperSeed(World w) {
/* 1348 */     return w.func_72905_C() + w.func_72912_H().func_76066_a().func_74763_f("superseed");
/*      */   }
/*      */   
/*      */   public static boolean isExposedToRain(World world, int x, int y, int z) {
/* 1352 */     if (world.func_72896_J()) {
/*      */       
/* 1354 */       int highestY = world.func_72874_g(x, z) - 1;
/* 1355 */       Block checkfire = world.func_147439_a(x, y, z);
/* 1356 */       boolean isfire = false;
/* 1357 */       if (checkfire instanceof com.bioxx.tfc.Blocks.BlockTerraContainer)
/* 1358 */         isfire = true; 
/* 1359 */       boolean isExposed = true;
/* 1360 */       if (world.func_72937_j(x, y + 1, z)) {
/*      */ 
/*      */         
/* 1363 */         if (world.func_147439_a(x, highestY, z) instanceof net.minecraft.block.BlockGlass || world
/* 1364 */           .func_147439_a(x, highestY, z) instanceof net.minecraft.block.BlockStainedGlass || (world
/*      */           
/* 1366 */           .isSideSolid(x, highestY, z, ForgeDirection.UP) && !isfire) || (world
/* 1367 */           .isSideSolid(x, highestY, z, ForgeDirection.DOWN) && !isfire))
/*      */         {
/* 1369 */           isExposed = false;
/*      */         }
/*      */       } else {
/* 1372 */         isExposed = false;
/*      */       } 
/* 1374 */       return isExposed;
/*      */     } 
/* 1376 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isItemHopperValid(ItemStack stackInSlot) {
/* 1382 */     return isItemChestValid(stackInSlot);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean isItemChestValid(ItemStack itemstack) {
/* 1387 */     if ((itemstack.func_77973_b() instanceof net.minecraft.item.ItemTool || itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemTerraTool || itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemWeapon || itemstack
/* 1388 */       .func_77973_b() instanceof net.minecraft.item.ItemHoe) && itemstack.func_77973_b() instanceof ISize && 
/* 1389 */       (((ISize)itemstack.func_77973_b()).getSize(itemstack)).stackSize < EnumSize.SMALL.stackSize) {
/* 1390 */       return false;
/*      */     }
/*      */     
/* 1393 */     boolean except = getExceptions().contains(itemstack.func_77973_b());
/* 1394 */     if (itemstack.func_77973_b() instanceof ISize && (((ISize)itemstack.func_77973_b()).getSize(itemstack)).stackSize >= EnumSize.LARGE.stackSize && !except)
/*      */     {
/* 1396 */       return true;
/*      */     }
/* 1398 */     if (!(itemstack.func_77973_b() instanceof ISize) && !except) {
/* 1399 */       return true;
/*      */     }
/*      */     
/* 1402 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static List<Item> getExceptions() {
/* 1407 */     List<Item> exceptions = new ArrayList<>();
/* 1408 */     for (Item ingot : TEIngotPile.getIngots())
/*      */     {
/* 1410 */       exceptions.add(ingot);
/*      */     }
/* 1412 */     exceptions.add(TFCItems.logs);
/* 1413 */     exceptions.add(Item.func_150898_a(TFCBlocks.barrel));
/* 1414 */     exceptions.add(Item.func_150898_a(TFCBlocks.vessel));
/*      */     
/* 1416 */     Item fromcook = (Item)GameData.getItemRegistry().func_82594_a("cookingwithtfc:item.Log");
/* 1417 */     if (fromcook != null) exceptions.add(fromcook); 
/* 1418 */     return exceptions;
/*      */   }
/*      */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\TFC_Core.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */