package com.bioxx.tfc.Core;

import com.bioxx.tfc.Chunkdata.ChunkData;
import com.bioxx.tfc.Chunkdata.ChunkDataManager;
import com.bioxx.tfc.Core.Player.BodyTempStats;
import com.bioxx.tfc.Core.Player.FoodStatsTFC;
import com.bioxx.tfc.Core.Player.InventoryPlayerTFC;
import com.bioxx.tfc.Core.Player.SkillStats;
import com.bioxx.tfc.Food.ItemFoodTFC;
import com.bioxx.tfc.Items.ItemBlocks.ItemTerraBlock;
import com.bioxx.tfc.Items.ItemOre;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.TileEntities.TEIngotPile;
import com.bioxx.tfc.TileEntities.TEMetalSheet;
import com.bioxx.tfc.WorldGen.TFCBiome;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Entities.IAnimal;
import com.bioxx.tfc.api.Enums.EnumFuelMaterial;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Food;
import com.bioxx.tfc.api.Interfaces.IFood;
import com.bioxx.tfc.api.Interfaces.ISize;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFCOptions;
import com.bioxx.tfc.api.TFC_ItemHeat;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import growthcraft.rice.GrowthCraftRice;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;













public class TFC_Core
{
  private static Map<Integer, ChunkDataManager> cdmMap = new HashMap<>();
  public static boolean preventEntityDataUpdate;
  public static boolean hasItemChanged;

  public static ChunkDataManager getCDM(World world) {
    int key = world.field_72995_K ? (0x80 | world.field_73011_w.field_76574_g) : world.field_73011_w.field_76574_g;
    return cdmMap.get(Integer.valueOf(key));
  }


  public static ChunkDataManager addCDM(World world) {
    int key = world.field_72995_K ? (0x80 | world.field_73011_w.field_76574_g) : world.field_73011_w.field_76574_g;
    if (!cdmMap.containsKey(Integer.valueOf(key)))
      return cdmMap.put(Integer.valueOf(key), new ChunkDataManager(world));
    return cdmMap.get(Integer.valueOf(key));
  }


  public static ChunkDataManager removeCDM(World world) {
    int key = world.field_72995_K ? (0x80 | world.field_73011_w.field_76574_g) : world.field_73011_w.field_76574_g;
    return cdmMap.remove(Integer.valueOf(key));
  }



  @SideOnly(Side.CLIENT)
  public static int getMouseX() {
    ScaledResolution scaledresolution = new ScaledResolution(Minecraft.func_71410_x(), (Minecraft.func_71410_x()).field_71443_c, (Minecraft.func_71410_x()).field_71440_d);
    int i = scaledresolution.func_78326_a();
    int k = Mouse.getX() * i / (Minecraft.func_71410_x()).field_71443_c;

    return k;
  }



  @SideOnly(Side.CLIENT)
  public static int getMouseY() {
    ScaledResolution scaledresolution = new ScaledResolution(Minecraft.func_71410_x(), (Minecraft.func_71410_x()).field_71443_c, (Minecraft.func_71410_x()).field_71440_d);
    int j = scaledresolution.func_78328_b();
    int l = j - Mouse.getY() * j / (Minecraft.func_71410_x()).field_71440_d - 1;

    return l;
  }


  public static Boolean isBlockAboveSolid(IBlockAccess blockAccess, int i, int j, int k) {
    if (TerraFirmaCraft.proxy.getCurrentWorld().func_147439_a(i, j + 1, k).func_149662_c())
      return Boolean.valueOf(true);
    return Boolean.valueOf(false);
  }


  public static int getExtraEquipInventorySize() {
    return 1;
  }


  public static InventoryPlayer getNewInventory(EntityPlayer player) {
    InventoryPlayer ip = player.field_71071_by;
    NBTTagList nbt = new NBTTagList();
    nbt = player.field_71071_by.func_70442_a(nbt);
    InventoryPlayerTFC inventoryPlayerTFC = new InventoryPlayerTFC(player);
    inventoryPlayerTFC.func_70443_b(nbt);
    return (InventoryPlayer)inventoryPlayerTFC;
  }



  public static ItemStack randomGem(Random random, int rockType) {
    Item[] gems = { TFCItems.gemAgate, TFCItems.gemAmethyst, TFCItems.gemBeryl, TFCItems.gemEmerald, TFCItems.gemGarnet, TFCItems.gemJade, TFCItems.gemJasper, TFCItems.gemOpal, TFCItems.gemRuby, TFCItems.gemSapphire, TFCItems.gemTourmaline, TFCItems.gemTopaz };

    for (int i = 500, z = 0; z < 5; i *= 2, z++) {
      if (random.nextInt(i) == 0) return new ItemStack(gems[random.nextInt(gems.length)], 1, z);
    }  return null;
  }


  public static void surroundWithLeaves(World world, int i, int j, int k, int meta, Random r) {
    for (int y = 2; y >= -2; y--) {

      for (int x = 2; x >= -2; x--) {

        for (int z = 2; z >= -2; z--) {

          if (world.func_147437_c(i + x, j + y, k + z)) {
            world.func_147465_d(i + x, j + y, k + z, TFCBlocks.leaves, meta, 2);
          }
        }
      }
    }
  }

  public static void setupWorld(World world) {
    long seed = world.func_72905_C();
    Random r = new Random(seed);
    world.field_73011_w.func_76558_a(world);
    Recipes.registerAnvilRecipes(r, world);
    TFC_Time.updateTime(world);
  }






  public static void setupWorld(World w, long seed) {
    try {
      ReflectionHelper.setPrivateValue(WorldInfo.class, w.func_72912_H(), Long.valueOf(seed), 0);
      setupWorld(w);
    }
    catch (Exception exception) {}
  }




  public static boolean isRawStone(World world, int x, int y, int z) {
    Block block = world.func_147439_a(x, y, z);
    return (block == TFCBlocks.stoneIgEx || block == TFCBlocks.stoneIgIn || block == TFCBlocks.stoneSed || block == TFCBlocks.stoneMM);
  }





  public static boolean isSmoothStone(World world, int x, int y, int z) {
    Block block = world.func_147439_a(x, y, z);
    return (block == TFCBlocks.stoneIgExSmooth || block == TFCBlocks.stoneIgInSmooth || block == TFCBlocks.stoneSedSmooth || block == TFCBlocks.stoneMMSmooth);
  }





  public static boolean isSmoothStone(Block block) {
    return (block == TFCBlocks.stoneIgExSmooth || block == TFCBlocks.stoneIgInSmooth || block == TFCBlocks.stoneSedSmooth || block == TFCBlocks.stoneMMSmooth);
  }





  public static boolean isBrickStone(Block block) {
    return (block == TFCBlocks.stoneIgExBrick || block == TFCBlocks.stoneIgInBrick || block == TFCBlocks.stoneSedBrick || block == TFCBlocks.stoneMMBrick);
  }





  public static boolean isRawStone(Block block) {
    return (block == TFCBlocks.stoneIgEx || block == TFCBlocks.stoneIgIn || block == TFCBlocks.stoneSed || block == TFCBlocks.stoneMM);
  }





  public static boolean isOreStone(Block block) {
    return (block == TFCBlocks.ore || block == TFCBlocks.ore2 || block == TFCBlocks.ore3);
  }




  public static boolean isNaturalStone(Block block) {
    return (isRawStone(block) || isOreStone(block));
  }


  public static boolean isCobbleStone(Block block) {
    return (block == TFCBlocks.stoneIgExCobble || block == TFCBlocks.stoneIgInCobble || block == TFCBlocks.stoneSedCobble || block == TFCBlocks.stoneMMCobble);
  }





  public static boolean isStoneIgEx(Block block) {
    return (block == TFCBlocks.stoneIgEx || block == TFCBlocks.stoneIgExCobble || block == TFCBlocks.stoneIgExSmooth || block == TFCBlocks.stoneIgExBrick || block == TFCBlocks.wallRawIgEx || block == TFCBlocks.wallCobbleIgEx || block == TFCBlocks.wallBrickIgEx || block == TFCBlocks.wallSmoothIgEx);
  }









  public static boolean isStoneIgIn(Block block) {
    return (block == TFCBlocks.stoneIgIn || block == TFCBlocks.stoneIgInCobble || block == TFCBlocks.stoneIgInSmooth || block == TFCBlocks.stoneIgInBrick || block == TFCBlocks.wallRawIgIn || block == TFCBlocks.wallCobbleIgIn || block == TFCBlocks.wallBrickIgIn || block == TFCBlocks.wallSmoothIgIn);
  }









  public static boolean isStoneSed(Block block) {
    return (block == TFCBlocks.stoneSed || block == TFCBlocks.stoneSedCobble || block == TFCBlocks.stoneSedSmooth || block == TFCBlocks.stoneSedBrick || block == TFCBlocks.wallRawSed || block == TFCBlocks.wallCobbleSed || block == TFCBlocks.wallBrickSed || block == TFCBlocks.wallSmoothSed);
  }









  public static boolean isStoneMM(Block block) {
    return (block == TFCBlocks.stoneMM || block == TFCBlocks.stoneMMCobble || block == TFCBlocks.stoneMMSmooth || block == TFCBlocks.stoneMMBrick || block == TFCBlocks.wallRawMM || block == TFCBlocks.wallCobbleMM || block == TFCBlocks.wallBrickMM || block == TFCBlocks.wallSmoothMM);
  }









  public static boolean isDirt(Block block) {
    return (block == TFCBlocks.dirt || block == TFCBlocks.dirt2);
  }



  public static boolean isFarmland(Block block) {
    return (block == TFCBlocks.tilledSoil || block == TFCBlocks.tilledSoil2 || block == GrowthCraftRice.blocks.paddyField

      .getBlock());
  }


  public static boolean isGrass(Block block) {
    return (block == TFCBlocks.grass || block == TFCBlocks.grass2 || block == TFCBlocks.clayGrass || block == TFCBlocks.clayGrass2 || block == TFCBlocks.peatGrass || block == TFCBlocks.dryGrass || block == TFCBlocks.dryGrass2);
  }








  public static boolean isGrassNormal(Block block) {
    return (block == TFCBlocks.grass || block == TFCBlocks.grass2);
  }




  public static boolean isLushGrass(Block block) {
    return (block == TFCBlocks.grass || block == TFCBlocks.grass2 || block == TFCBlocks.clayGrass || block == TFCBlocks.clayGrass2 || block == TFCBlocks.peatGrass);
  }






  public static boolean isClayGrass(Block block) {
    return (block == TFCBlocks.clayGrass || block == TFCBlocks.clayGrass2);
  }



  public static boolean isPeatGrass(Block block) {
    return (block == TFCBlocks.peatGrass);
  }


  public static boolean isDryGrass(Block block) {
    return (block == TFCBlocks.dryGrass || block == TFCBlocks.dryGrass2);
  }



  public static boolean isGrassType1(Block block) {
    return (block == TFCBlocks.grass || block == TFCBlocks.clayGrass || block == TFCBlocks.dryGrass);
  }




  public static boolean isGrassType2(Block block) {
    return (block == TFCBlocks.grass2 || block == TFCBlocks.clayGrass2 || block == TFCBlocks.dryGrass2);
  }




  public static boolean isClay(Block block) {
    return (block == TFCBlocks.clay || block == TFCBlocks.clay2);
  }


  public static boolean isSand(Block block) {
    return (block == TFCBlocks.sand || block == TFCBlocks.sand2);
  }



  public static boolean isPeat(Block block) {
    return (block == TFCBlocks.peat);
  }


  public static boolean isHotWater(Block block) {
    return (block == TFCBlocks.hotWater || block == TFCBlocks.hotWaterStationary);
  }


  public static boolean isWater(Block block) {
    return (isSaltWater(block) ||
      isFreshWater(block));
  }


  public static boolean isWaterFlowing(Block block) {
    return (block == TFCBlocks.saltWater || block == TFCBlocks.freshWater);
  }


  public static boolean isSaltWater(Block block) {
    return (block == TFCBlocks.saltWater || block == TFCBlocks.saltWaterStationary);
  }


  public static boolean isSaltWaterIncludeIce(Block block, int meta, Material mat) {
    return (block == TFCBlocks.saltWater || block == TFCBlocks.saltWaterStationary || (mat == Material.field_151588_w && meta == 0));
  }



  public static boolean isFreshWater(Block block) {
    return (block == TFCBlocks.freshWater || block == TFCBlocks.freshWaterStationary);
  }


  public static boolean isFreshWaterIncludeIce(Block block, int meta) {
    return (block == TFCBlocks.freshWater || block == TFCBlocks.freshWaterStationary || (block == TFCBlocks.ice && meta != 0));
  }



  public static boolean isFreshWaterIncludeIce(Block block, int meta, Material mat) {
    return (block == TFCBlocks.freshWater || block == TFCBlocks.freshWaterStationary || (mat == Material.field_151588_w && meta != 0));
  }



  public static boolean isSoil(Block block) {
    return (isGrass(block) ||
      isDirt(block) ||
      isClay(block) ||
      isPeat(block));
  }


  public static boolean isSoilOrGravel(Block block) {
    return (isGrass(block) ||
      isDirt(block) ||
      isClay(block) ||
      isPeat(block) ||
      isGravel(block));
  }


  public static boolean isGravel(Block block) {
    return (block == TFCBlocks.gravel || block == TFCBlocks.gravel2);
  }


  public static boolean isGround(Block block) {
    return (isSoilOrGravel(block) ||
      isRawStone(block) ||
      isSand(block));
  }


  public static boolean isGroundType1(Block block) {
    return (isGrassType1(block) || block == TFCBlocks.dirt || block == TFCBlocks.gravel || block == TFCBlocks.sand);
  }


  public static boolean isSoilWAILA(Block block) {
    return (isDirt(block) || isGravel(block) || isSand(block) || isGrassNormal(block) || isDryGrass(block));
  }


  public static int getSoilMetaFromStone(Block inBlock, int inMeta) {
    if (inBlock == TFCBlocks.stoneIgIn)
      return inMeta;
    if (inBlock == TFCBlocks.stoneSed)
      return inMeta + 3;
    if (inBlock == TFCBlocks.stoneIgEx) {
      return inMeta + 11;
    }

    if (inMeta == 0)
      return inMeta + 15;
    return inMeta - 1;
  }



  public static int getSoilMeta(int inMeta) {
    return inMeta & 0xF;
  }


  public static int getItemMetaFromStone(Block inBlock, int inMeta) {
    if (inBlock == TFCBlocks.stoneIgIn)
      return inMeta;
    if (inBlock == TFCBlocks.stoneSed)
      return inMeta + 3;
    if (inBlock == TFCBlocks.stoneIgEx)
      return inMeta + 11;
    if (inBlock == TFCBlocks.stoneMM) {
      return inMeta + 15;
    }
    return 0;
  }


  public static Block getTypeForGrassWithRain(int inMeta, float rain) {
    if (rain >= 500.0F)
      return getTypeForGrass(inMeta);
    return getTypeForDryGrass(inMeta);
  }


  public static Block getTypeForGrassWithRainByBlock(Block block, float rain) {
    if (rain >= 500.0F)
      return getTypeForGrassFromSoil(block);
    return getTypeForDryGrassFromSoil(block);
  }


  public static Block getTypeForGrass(int inMeta) {
    if (inMeta < 16)
      return TFCBlocks.grass;
    return TFCBlocks.grass2;
  }


  public static Block getTypeForGrassFromDirt(Block block) {
    if (block == TFCBlocks.dirt)
      return TFCBlocks.grass;
    return TFCBlocks.grass2;
  }


  public static Block getTypeForDryGrass(int inMeta) {
    if (inMeta < 16)
      return TFCBlocks.dryGrass;
    return TFCBlocks.dryGrass2;
  }


  public static Block getTypeForDryGrassFromSoil(Block block) {
    if (block == TFCBlocks.grass)
      return TFCBlocks.dryGrass;
    if (block == TFCBlocks.dirt)
      return TFCBlocks.dryGrass;
    return TFCBlocks.dryGrass2;
  }


  public static Block getTypeForGrassFromSoil(Block block) {
    if (block == TFCBlocks.dryGrass)
      return TFCBlocks.grass;
    if (block == TFCBlocks.dryGrass2)
      return TFCBlocks.grass2;
    if (block == TFCBlocks.dirt)
      return TFCBlocks.grass;
    return TFCBlocks.grass2;
  }


  public static Block getTypeForClayGrass(int inMeta) {
    if (inMeta < 16)
      return TFCBlocks.clayGrass;
    return TFCBlocks.clayGrass2;
  }


  public static Block getTypeForClayGrass(Block block) {
    if (isGroundType1(block))
      return TFCBlocks.clayGrass;
    return TFCBlocks.clayGrass2;
  }


  public static Block getTypeForDirt(int inMeta) {
    if (inMeta < 16)
      return TFCBlocks.dirt;
    return TFCBlocks.dirt2;
  }


  public static Block getTypeForDirtFromGrass(Block block) {
    if (isDirt(block))
      return block;
    if (block == TFCBlocks.grass || block == TFCBlocks.dryGrass)
      return TFCBlocks.dirt;
    return TFCBlocks.dirt2;
  }


  public static Block getTypeForSoil(Block block) {
    if (isGrass(block)) {

      if (isGrassType1(block))
        return TFCBlocks.dirt;
      if (isGrassType2(block))
        return TFCBlocks.dirt2;
      if (isPeatGrass(block)) {
        return TFCBlocks.peat;
      }
    }
    return block;
  }


  public static Block getTypeForClay(int inMeta) {
    if (inMeta < 16)
      return TFCBlocks.clay;
    return TFCBlocks.clay2;
  }


  public static Block getTypeForClay(Block block) {
    if (isGroundType1(block))
      return TFCBlocks.clay;
    return TFCBlocks.clay2;
  }


  public static Block getTypeForSand(int inMeta) {
    if (inMeta < 16)
      return TFCBlocks.sand;
    return TFCBlocks.sand2;
  }


  public static Block getTypeForGravel(int inMeta) {
    if (inMeta < 16)
      return TFCBlocks.gravel;
    return TFCBlocks.gravel2;
  }


  public static int getRockLayerFromHeight(World world, int x, int y, int z) {
    ChunkData cd = getCDM(world).getData(x >> 4, z >> 4);
    if (cd != null) {

      int[] hm = cd.heightmap;
      int localX = x & 0xF;
      int localZ = z & 0xF;
      int localY = localX + localZ * 16;
      if (y <= TFCOptions.rockLayer3Height + hm[localY])
        return 2;
      if (y <= TFCOptions.rockLayer2Height + hm[localY]) {
        return 1;
      }
      return 0;
    }
    return 0;
  }


  public static boolean convertGrassToDirt(World world, int i, int j, int k) {
    Block block = world.func_147439_a(i, j, k);
    int meta = world.func_72805_g(i, j, k);
    if (isGrass(block)) {

      if (isGrassType1(block)) {

        world.func_147465_d(i, j, k, TFCBlocks.dirt, meta, 2);
        return true;
      }
      if (isGrassType2(block)) {

        world.func_147465_d(i, j, k, TFCBlocks.dirt2, meta, 2);
        return true;
      }
    }
    return false;
  }




  public static EnumFuelMaterial getFuelMaterial(ItemStack is) {
    if (is.func_77973_b() == Item.func_150898_a(TFCBlocks.peat))
      return EnumFuelMaterial.PEAT;
    if (is.func_77973_b() == TFCItems.coal && is.func_77960_j() == 0)
      return EnumFuelMaterial.COAL;
    if (is.func_77973_b() == TFCItems.coal && is.func_77960_j() == 1)
      return EnumFuelMaterial.CHARCOAL;
    if (is.func_77960_j() == 0)
      return EnumFuelMaterial.ASH;
    if (is.func_77960_j() == 1)
      return EnumFuelMaterial.ASPEN;
    if (is.func_77960_j() == 2)
      return EnumFuelMaterial.BIRCH;
    if (is.func_77960_j() == 3)
      return EnumFuelMaterial.CHESTNUT;
    if (is.func_77960_j() == 4)
      return EnumFuelMaterial.DOUGLASFIR;
    if (is.func_77960_j() == 5)
      return EnumFuelMaterial.HICKORY;
    if (is.func_77960_j() == 6)
      return EnumFuelMaterial.MAPLE;
    if (is.func_77960_j() == 7)
      return EnumFuelMaterial.OAK;
    if (is.func_77960_j() == 8)
      return EnumFuelMaterial.PINE;
    if (is.func_77960_j() == 9)
      return EnumFuelMaterial.REDWOOD;
    if (is.func_77960_j() == 10)
      return EnumFuelMaterial.SPRUCE;
    if (is.func_77960_j() == 11)
      return EnumFuelMaterial.SYCAMORE;
    if (is.func_77960_j() == 12)
      return EnumFuelMaterial.WHITECEDAR;
    if (is.func_77960_j() == 13)
      return EnumFuelMaterial.WHITEELM;
    if (is.func_77960_j() == 14)
      return EnumFuelMaterial.WILLOW;
    if (is.func_77960_j() == 15)
      return EnumFuelMaterial.KAPOK;
    if (is.func_77960_j() == 16)
      return EnumFuelMaterial.ACACIA;
    return EnumFuelMaterial.ASPEN;
  }


  public static boolean showShiftInformation() {
    return (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT && Keyboard.isKeyDown(42));
  }


  public static boolean showCtrlInformation() {
    return (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT && Keyboard.isKeyDown(29));
  }


  public static FoodStatsTFC getPlayerFoodStats(EntityPlayer player) {
    FoodStatsTFC foodstats = new FoodStatsTFC(player);
    foodstats.readNBT(player.getEntityData());
    return foodstats;
  }


  public static void setPlayerFoodStats(EntityPlayer player, FoodStatsTFC foodstats) {
    foodstats.writeNBT(player.getEntityData());
  }


  public static BodyTempStats getBodyTempStats(EntityPlayer player) {
    BodyTempStats body = new BodyTempStats();
    body.readNBT(player.getEntityData());
    return body;
  }


  public static void setBodyTempStats(EntityPlayer player, BodyTempStats tempStats) {
    tempStats.writeNBT(player.getEntityData());
  }


  public static SkillStats getSkillStats(EntityPlayer player) {
    SkillStats skills = new SkillStats(player);
    skills.readNBT(player.getEntityData());
    return skills;
  }


  public static void setSkillStats(EntityPlayer player, SkillStats skills) {
    skills.writeNBT(player.getEntityData());
  }


  public static boolean isTopFaceSolid(World world, int x, int y, int z) {
    if (world.func_147439_a(x, y, z).func_149721_r()) return true;
    if (world.func_147439_a(x, y, z) == TFCBlocks.metalSheet) {

      TEMetalSheet te = (TEMetalSheet)world.func_147438_o(x, y, z);
      if (te.topExists()) return true;
    }
    return world.func_147439_a(x, y, z).isSideSolid((IBlockAccess)world, x, y, z, ForgeDirection.UP);
  }


  public static boolean isBottomFaceSolid(World world, int x, int y, int z) {
    if (world.func_147439_a(x, y, z).func_149721_r()) return true;
    if (world.func_147439_a(x, y, z) == TFCBlocks.metalSheet) {

      TEMetalSheet te = (TEMetalSheet)world.func_147438_o(x, y, z);
      if (te.bottomExists()) return true;
    }
    return world.func_147439_a(x, y, z).isSideSolid((IBlockAccess)world, x, y, z, ForgeDirection.DOWN);
  }


  public static boolean isNorthFaceSolid(World world, int x, int y, int z) {
    Block bid = world.func_147439_a(x, y, z);
    if (bid.func_149721_r()) return true;
    if (bid == TFCBlocks.metalSheet) {

      TEMetalSheet te = (TEMetalSheet)world.func_147438_o(x, y, z);
      if (te.northExists()) return true;
    }
    return world.func_147439_a(x, y, z).isSideSolid((IBlockAccess)world, x, y, z, ForgeDirection.NORTH);
  }


  public static boolean isSouthFaceSolid(World world, int x, int y, int z) {
    if (world.func_147439_a(x, y, z).func_149721_r()) return true;
    if (world.func_147439_a(x, y, z) == TFCBlocks.metalSheet) {

      TEMetalSheet te = (TEMetalSheet)world.func_147438_o(x, y, z);
      if (te.southExists()) return true;
    }
    return world.func_147439_a(x, y, z).isSideSolid((IBlockAccess)world, x, y, z, ForgeDirection.SOUTH);
  }


  public static boolean isEastFaceSolid(World world, int x, int y, int z) {
    if (world.func_147439_a(x, y, z).func_149721_r()) return true;
    if (world.func_147439_a(x, y, z) == TFCBlocks.metalSheet) {

      TEMetalSheet te = (TEMetalSheet)world.func_147438_o(x, y, z);
      if (te.eastExists()) return true;
    }
    return world.func_147439_a(x, y, z).isSideSolid((IBlockAccess)world, x, y, z, ForgeDirection.EAST);
  }


  public static boolean isWestFaceSolid(World world, int x, int y, int z) {
    if (world.func_147439_a(x, y, z).func_149721_r()) return true;
    if (world.func_147439_a(x, y, z) == TFCBlocks.metalSheet) {

      TEMetalSheet te = (TEMetalSheet)world.func_147438_o(x, y, z);
      if (te.westExists()) return true;
    }
    return world.func_147439_a(x, y, z).isSideSolid((IBlockAccess)world, x, y, z, ForgeDirection.WEST);
  }


  public static boolean isSurroundedSolid(World world, int x, int y, int z) {
    return (isNorthFaceSolid(world, x, y, z + 1) &&
      isSouthFaceSolid(world, x, y, z - 1) &&
      isEastFaceSolid(world, x - 1, y, z) &&
      isWestFaceSolid(world, x + 1, y, z) &&
      isTopFaceSolid(world, x, y - 1, z));
  }


  public static boolean isSurroundedStone(World world, int x, int y, int z) {
    return (world.func_147439_a(x, y, z + 1).func_149688_o() == Material.field_151576_e && world
      .func_147439_a(x, y, z - 1).func_149688_o() == Material.field_151576_e && world
      .func_147439_a(x - 1, y, z).func_149688_o() == Material.field_151576_e && world
      .func_147439_a(x + 1, y, z).func_149688_o() == Material.field_151576_e && world
      .func_147439_a(x, y - 1, z).func_149688_o() == Material.field_151576_e);
  }


  public static boolean isOreIron(ItemStack is) {
    return (is.func_77973_b() instanceof ItemOre && ((ItemOre)is.func_77973_b()).getMetalType(is) == Global.PIGIRON);
  }


  public static float getEntityMaxHealth(EntityLivingBase entity) {
    return (float)entity.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111126_e();
  }


  public static float getPercentGrown(IAnimal animal) {
    float birth = animal.getBirthDay();
    float time = TFC_Time.getTotalDays();
    float percent = (time - birth) / animal.getNumberOfDaysToAdult();
    return Math.min(percent, 1.0F);
  }


  public static void bindTexture(ResourceLocation texture) {
    Minecraft.func_71410_x().func_110434_K().func_110577_a(texture);
  }


  public static boolean isPlayerInDebugMode(EntityPlayer player) {
    return TFCOptions.enableDebugMode;
  }





  public static void addPlayerExhaustion(EntityPlayer player, float exhaustion) {
    FoodStatsTFC foodstats = getPlayerFoodStats(player);
    foodstats.addFoodExhaustion(exhaustion);

    setPlayerFoodStats(player, foodstats);
  }


  public static float getEnvironmentalDecay(float temp) {
    if (temp > 0.0F) {

      float tempFactor = 1.0F - 15.0F / (15.0F + temp);
      return tempFactor * 2.0F;
    }

    return 0.0F;
  }






  public static void handleItemTicking(IInventory iinv, World world, int x, int y, int z) {
    handleItemTicking(iinv, world, x, y, z, 1.0F);
  }






  public static void handleItemTicking(ItemStack[] iinv, World world, int x, int y, int z) {
    handleItemTicking(iinv, world, x, y, z, 1.0F);
  }



  public static ItemStack[] getIsFromNbt(ItemStack is) {
    if (is.func_77978_p().func_74764_b("Items")) {
      NBTTagList nbttaglist = is.func_77978_p().func_150295_c("Items", 10);
      if (nbttaglist != null) {
        int numItems = nbttaglist.func_74745_c();
        ItemStack[] barell_items = new ItemStack[numItems];
        Arrays.fill((Object[])barell_items, (Object)null);
        int count = 0;
        for (int i = 0; i < numItems; i++) {
          NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
          if (nbttagcompound1 != null) {
            ItemStack onlyItem = ItemStack.func_77949_a(nbttagcompound1);
            if (onlyItem != null) {
              barell_items[count++] = onlyItem;
            }
          }
        }
        return barell_items;
      }
    }
    return null;
  }


  public static void writeToNBT(NBTTagCompound nbt, ItemStack[] storage) {
    NBTTagList nbttaglist = new NBTTagList();
    for (int i = 0; i < storage.length; i++) {

      if (storage[i] != null) {

        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.func_74774_a("Slot", (byte)i);
        storage[i].func_77955_b(nbttagcompound1);
        nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
      }
    }
    nbt.func_74782_a("Items", (NBTBase)nbttaglist);
  }









  public static void handleItemTicking(IInventory iinv, World world, int x, int y, int z, float environmentalDecayFactor) {
    for (int i = 0; !world.field_72995_K && i < iinv.func_70302_i_(); i++) {

      ItemStack is = iinv.func_70301_a(i);
      if (is != null && is.field_77994_a <= 0) { iinv.func_70299_a(i, null); }

      else if (is != null)

      { if (is.field_77994_a == 0) {

          iinv.func_70299_a(i, null);

        }
        else if ((!(is.func_77973_b() instanceof ItemTerra) || !((ItemTerra)is.func_77973_b()).onUpdate(is, world, x, y, z)) && (
          !(is.func_77973_b() instanceof ItemTerraBlock) || !((ItemTerraBlock)is.func_77973_b()).onUpdate(is, world, x, y, z))) {


          if (is != null && (is.func_77973_b() instanceof com.bioxx.tfc.Items.ItemBlocks.ItemBarrels || is.func_77973_b() instanceof com.bioxx.tfc.Items.ItemBlocks.ItemLargeVessel || is.func_77973_b() instanceof com.zerren.extrafirma.items.ItemBag)) {
            if (is.func_77942_o()) {
              ItemStack[] inv = getIsFromNbt(is);
              if (inv != null) {
                handleItemTicking(inv, world, x, y, z, environmentalDecayFactor);
                NBTTagCompound nbt = is.func_77978_p();
                nbt.func_82580_o("Items");
                writeToNBT(nbt, inv);
              }
            }
          } else {
            hasItemChanged = false;
            is = tickDecay(is, world, x, y, z, environmentalDecayFactor, 1.0F);
            if (is != null) hasItemChanged |= TFC_ItemHeat.handleItemHeat(is);
            if (hasItemChanged) iinv.func_70299_a(i, is);

          }
        }  }

    }
  }


  public static byte getByteFromSmallFloat(float f) {
    MathHelper.func_76131_a(f, 0.5F, 1.5F);
    return (byte)(Float.floatToIntBits(f) >> 16 & 0xFF);
  }


  public static float getSmallFloatFromByte(byte b) {
    return ByteBuffer.wrap(new byte[] { 63, b, 0, 0 }).getFloat();
  }






  public static void handleItemTicking(IInventory iinv, World world, int x, int y, int z, float environmentalDecayFactor, float baseDecayMod) {
    for (int i = 0; !world.field_72995_K && i < iinv.func_70302_i_(); i++) {

      ItemStack is = iinv.func_70301_a(i);
      if (is != null && is.field_77994_a <= 0) { iinv.func_70299_a(i, null); }

      else if (is != null)

      { if ((!(is.func_77973_b() instanceof ItemTerra) || !((ItemTerra)is.func_77973_b()).onUpdate(is, world, x, y, z)) && (
          !(is.func_77973_b() instanceof ItemTerraBlock) || !((ItemTerraBlock)is.func_77973_b()).onUpdate(is, world, x, y, z))) {
          hasItemChanged = false;
          is = tickDecay(is, world, x, y, z, environmentalDecayFactor, baseDecayMod);
          if (is != null) hasItemChanged |= TFC_ItemHeat.handleItemHeat(is);
          if (hasItemChanged) iinv.func_70299_a(i, is);

        }  }

    }
  }




  public static void handleItemTicking(ItemStack[] iinv, World world, int x, int y, int z, float environmentalDecayFactor) {
    for (int i = 0; !world.field_72995_K && i < iinv.length; i++) {

      ItemStack is = iinv[i];
      if (is != null && is.field_77994_a <= 0) { iinv[i] = null; }

      else if (is != null)

      { if ((!(is.func_77973_b() instanceof ItemTerra) || !((ItemTerra)is.func_77973_b()).onUpdate(is, world, x, y, z)) && (
          !(is.func_77973_b() instanceof ItemTerraBlock) || !((ItemTerraBlock)is.func_77973_b()).onUpdate(is, world, x, y, z))) {
          is = tickDecay(is, world, x, y, z, environmentalDecayFactor, 1.0F);
          if (is != null) TFC_ItemHeat.handleItemHeat(is);
          iinv[i] = is;
        }  }

    }
  }







  public static ItemStack tickDecay(ItemStack is, World world, int x, int y, int z, float environmentalDecayFactor, float baseDecayMod) {
    NBTTagCompound nbt = is.func_77978_p();
    if (nbt == null || !nbt.func_74764_b("foodWeight") || !nbt.func_74764_b("foodDecay")) return is;

    int decayTimer = Food.getDecayTimer(is);

    if (decayTimer < TFC_Time.getTotalHours()) {

      int timeDiff = (int)(TFC_Time.getTotalHours() - decayTimer);
      float protMult = 1.0F;

      if (TFCOptions.useDecayProtection)
      {
        if (timeDiff > TFCOptions.decayProtectionDays * 24) {

          decayTimer = (int)TFC_Time.getTotalHours() - 24;
        }
        else if (timeDiff > 24) {

          protMult = (1 - timeDiff / TFCOptions.decayProtectionDays * 24);
        }
      }

      float decay = Food.getDecay(is);
      float thisDecayRate = 1.0F;

      if (is.func_77973_b() instanceof IFood) {
        thisDecayRate = ((IFood)is.func_77973_b()).getDecayRate(is);
      } else {

        thisDecayRate = Food.getDecayRate(is);
      }







      float temp = getCachedTemp(world, x, y, z, decayTimer);
      float environmentalDecay = getEnvironmentalDecay(temp) * environmentalDecayFactor;

      if (decay < 0.0F)

      { float d = 1.0F * thisDecayRate * baseDecayMod * environmentalDecay;
        if (decay + d < 0.0F) { decay += d; }
        else { decay = 0.0F; }
         }
      else if (decay == 0.0F) { decay = Food.getWeight(is) * world.field_73012_v.nextFloat() * 0.005F * TFCOptions.decayMultiplier; }
      else

      { double fdr = (TFCOptions.foodDecayRate - 1.0F);
        fdr *= (thisDecayRate * baseDecayMod * environmentalDecay * protMult * TFCOptions.decayMultiplier);
        decay = (float)(decay * (1.0D + fdr)); }

      Food.setDecayTimer(is, decayTimer + 1);
      Food.setDecay(is, decay);
      hasItemChanged = true;
    }

    if (Food.getDecay(is) / Food.getWeight(is) > 0.9F) {

      if (is.func_77973_b() instanceof IFood) { is = ((IFood)is.func_77973_b()).onDecayed(is, world, x, y, z); }
      else { is.field_77994_a = 0; }
       hasItemChanged = true;
    }
    if (is != null &&
      Food.getWeight(is) <= 0.1D) is.field_77994_a = 0;

    return is;
  }


  public static float getCachedTemp(World world, int x, int y, int z, int th) {
    float cacheTemp = TFC_Climate.getCacheManager(world).getTemp(x, z, th);
    if (cacheTemp != Float.MIN_VALUE)
    {
      return cacheTemp;
    }
    float temp = TFC_Climate.getHeightAdjustedTempSpecificDay(world, TFC_Time.getDayFromTotalHours(th), TFC_Time.getHourOfDayFromTotalHours(th), x, y, z);
    addCachedTemp(world, x, z, th, temp);
    return temp;
  }


  public static void addCachedTemp(World world, int x, int z, int th, float temp) {
    TFC_Climate.getCacheManager(world).addTemp(x, z, th, temp);
  }



  public static void animalDropMeat(Entity e, Item i, float foodWeight) {
    ItemStack is = ItemFoodTFC.createTag(new ItemStack(i, 1), foodWeight);
    Random r = new Random(e.func_110124_au().getLeastSignificantBits() + e.func_110124_au().getMostSignificantBits());
    Food.adjustFlavor(is, r);
    e.capturedDrops.add(new EntityItem(e.field_70170_p, e.field_70165_t, e.field_70163_u, e.field_70161_v, is));
  }


  public static Vec3 getEntityPos(Entity e) {
    return Vec3.func_72443_a(e.field_70165_t, e.field_70163_u, e.field_70161_v);
  }


  public static void giveItemToPlayer(ItemStack is, EntityPlayer player) {
    if (player.field_70170_p.field_72995_K)
      return;  EntityItem ei = player.func_70099_a(is, 1.0F);
    ei.field_145804_b = 0;
  }


  public static boolean isFence(Block b) {
    return (b == TFCBlocks.fence || b == TFCBlocks.fence2);
  }


  public static boolean isVertSupport(Block b) {
    return (b == TFCBlocks.woodSupportV || b == TFCBlocks.woodSupportV2);
  }


  public static boolean isHorizSupport(Block b) {
    return (b == TFCBlocks.woodSupportH || b == TFCBlocks.woodSupportH2);
  }


  public static boolean isOceanicBiome(int id) {
    return (id == TFCBiome.OCEAN.field_76756_M || id == TFCBiome.DEEP_OCEAN.field_76756_M);
  }


  public static boolean isMountainBiome(int id) {
    return (id == TFCBiome.MOUNTAINS.field_76756_M || id == TFCBiome.MOUNTAINS_EDGE.field_76756_M);
  }


  public static boolean isBeachBiome(int id) {
    return (id == TFCBiome.BEACH.field_76756_M || id == TFCBiome.GRAVEL_BEACH.field_76756_M);
  }


  public static boolean isValidCharcoalPitCover(Block block) {
    if (Blocks.field_150480_ab.getFlammability(block) > 0 && block != TFCBlocks.logPile) return false;

    return (block == TFCBlocks.logPile ||
      isCobbleStone(block) ||
      isBrickStone(block) ||
      isSmoothStone(block) ||
      isGround(block) || block == Blocks.field_150359_w || block == Blocks.field_150399_cn || block == TFCBlocks.metalTrapDoor || block == Blocks.field_150454_av || block




      .func_149662_c());
  }


  public static void writeInventoryToNBT(NBTTagCompound nbt, ItemStack[] storage) {
    writeInventoryToNBT(nbt, storage, "Items");
  }


  public static void writeInventoryToNBT(NBTTagCompound nbt, ItemStack[] storage, String name) {
    NBTTagList nbttaglist = new NBTTagList();
    for (int i = 0; i < storage.length; i++) {

      if (storage[i] != null) {

        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.func_74774_a("Slot", (byte)i);
        storage[i].func_77955_b(nbttagcompound1);
        nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
      }
    }
    nbt.func_74782_a(name, (NBTBase)nbttaglist);
  }


  public static void readInventoryFromNBT(NBTTagCompound nbt, ItemStack[] storage) {
    readInventoryFromNBT(nbt, storage, "Items");
  }


  public static void readInventoryFromNBT(NBTTagCompound nbt, ItemStack[] storage, String name) {
    NBTTagList nbttaglist = nbt.func_150295_c(name, 10);
    for (int i = 0; i < nbttaglist.func_74745_c(); i++) {

      NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
      byte byte0 = nbttagcompound1.func_74771_c("Slot");
      if (byte0 >= 0 && byte0 < storage.length) {
        storage[byte0] = ItemStack.func_77949_a(nbttagcompound1);
      }
    }
  }

  public static ItemStack getItemInInventory(Item item, IInventory iinv) {
    for (int i = 0; i < iinv.func_70302_i_(); i++) {

      ItemStack is = iinv.func_70301_a(i);
      if (is != null && is.func_77973_b() == item) return is;
    }
    return null;
  }


  public static void destroyBlock(World world, int x, int y, int z) {
    if (world.func_147439_a(x, y, z) != Blocks.field_150350_a) {

      world.func_147439_a(x, y, z).func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
      world.func_147468_f(x, y, z);
    }
  }


  public static boolean areItemsEqual(ItemStack is1, ItemStack is2) {
    Item i1 = null; int d1 = 0;
    Item i2 = null; int d2 = 0;
    if (is1 != null) {

      i1 = is1.func_77973_b(); d1 = is1.func_77960_j();
    }
    if (is2 != null) {

      i2 = is2.func_77973_b(); d2 = is2.func_77960_j();
    }
    return (i1 == i2 && d1 == d2);
  }


  public static boolean setBlockWithDrops(World world, int x, int y, int z, Block b, int meta) {
    Block block = world.func_147439_a(x, y, z);

    if (block.func_149688_o() != Material.field_151579_a) {

      int l = world.func_72805_g(x, y, z);
      world.func_72926_e(2001, x, y, z, Block.func_149682_b(block) + (l << 12));
      block.func_149697_b(world, x, y, z, l, 0);
    }
    return world.func_147465_d(x, y, z, b, meta, 3);
  }





  public static boolean setBlockToAirWithDrops(World world, int x, int y, int z) {
    return world.func_147480_a(x, y, z, true);
  }


  public static boolean isWaterBiome(BiomeGenBase b) {
    return (isBeachBiome(b.field_76756_M) || isOceanicBiome(b.field_76756_M) || b == TFCBiome.LAKE || b == TFCBiome.RIVER);
  }


  public static String translate(String s) {
    return StatCollector.func_74838_a(s);
  }


  public static void sendInfoMessage(EntityPlayer player, IChatComponent text) {
    text.func_150256_b().func_150238_a(EnumChatFormatting.GRAY).func_150217_b(Boolean.valueOf(true));
    player.func_146105_b(text);
  }


  public static long getSuperSeed(World w) {
    return w.func_72905_C() + w.func_72912_H().func_76066_a().func_74763_f("superseed");
  }

  public static boolean isExposedToRain(World world, int x, int y, int z) {
    if (world.func_72896_J()) {

      int highestY = world.func_72874_g(x, z) - 1;
      Block checkfire = world.func_147439_a(x, y, z);
      boolean isfire = false;
      if (checkfire instanceof com.bioxx.tfc.Blocks.BlockTerraContainer)
        isfire = true;
      boolean isExposed = true;
      if (world.func_72937_j(x, y + 1, z)) {


        if (world.func_147439_a(x, highestY, z) instanceof net.minecraft.block.BlockGlass || world
          .func_147439_a(x, highestY, z) instanceof net.minecraft.block.BlockStainedGlass || (world

          .isSideSolid(x, highestY, z, ForgeDirection.UP) && !isfire) || (world
          .isSideSolid(x, highestY, z, ForgeDirection.DOWN) && !isfire))
        {
          isExposed = false;
        }
      } else {
        isExposed = false;
      }
      return isExposed;
    }
    return false;
  }



  public static boolean isItemHopperValid(ItemStack stackInSlot) {
    return isItemChestValid(stackInSlot);
  }


  public static boolean isItemChestValid(ItemStack itemstack) {
    if ((itemstack.func_77973_b() instanceof net.minecraft.item.ItemTool || itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemTerraTool || itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemWeapon || itemstack
      .func_77973_b() instanceof net.minecraft.item.ItemHoe) && itemstack.func_77973_b() instanceof ISize &&
      (((ISize)itemstack.func_77973_b()).getSize(itemstack)).stackSize < EnumSize.SMALL.stackSize) {
      return false;
    }

    boolean except = getExceptions().contains(itemstack.func_77973_b());
    if (itemstack.func_77973_b() instanceof ISize && (((ISize)itemstack.func_77973_b()).getSize(itemstack)).stackSize >= EnumSize.LARGE.stackSize && !except)
    {
      return true;
    }
    if (!(itemstack.func_77973_b() instanceof ISize) && !except) {
      return true;
    }

    return false;
  }


  public static List<Item> getExceptions() {
    List<Item> exceptions = new ArrayList<>();
    for (Item ingot : TEIngotPile.getIngots())
    {
      exceptions.add(ingot);
    }
    exceptions.add(TFCItems.logs);
    exceptions.add(Item.func_150898_a(TFCBlocks.barrel));
    exceptions.add(Item.func_150898_a(TFCBlocks.vessel));

    Item fromcook = (Item)GameData.getItemRegistry().func_82594_a("cookingwithtfc:item.Log");
    if (fromcook != null) exceptions.add(fromcook);
    return exceptions;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\TFC_Core.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */