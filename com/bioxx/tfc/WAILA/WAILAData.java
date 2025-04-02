package com.bioxx.tfc.WAILA;

import com.bioxx.tfc.Blocks.BlockCharcoal;
import com.bioxx.tfc.Blocks.BlockMetalTrapDoor;
import com.bioxx.tfc.Blocks.BlockPartial;
import com.bioxx.tfc.Blocks.Devices.BlockAnvil;
import com.bioxx.tfc.Blocks.Flora.BlockBerryBush;
import com.bioxx.tfc.Blocks.Flora.BlockFruitLeaves;
import com.bioxx.tfc.Blocks.Flora.BlockFruitWood;
import com.bioxx.tfc.Blocks.Flora.BlockWaterPlant;
import com.bioxx.tfc.Blocks.Terrain.BlockClay;
import com.bioxx.tfc.Blocks.Terrain.BlockClayGrass;
import com.bioxx.tfc.Blocks.Terrain.BlockCobble;
import com.bioxx.tfc.Blocks.Terrain.BlockDirt;
import com.bioxx.tfc.Blocks.Terrain.BlockGrass;
import com.bioxx.tfc.Blocks.Terrain.BlockGravel;
import com.bioxx.tfc.Blocks.Terrain.BlockSand;
import com.bioxx.tfc.Blocks.Vanilla.BlockCustomDoor;
import com.bioxx.tfc.Blocks.Vanilla.BlockTorch;
import com.bioxx.tfc.Core.Player.SkillStats;
import com.bioxx.tfc.Core.Recipes;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Food.CropIndex;
import com.bioxx.tfc.Food.CropManager;
import com.bioxx.tfc.Food.FloraIndex;
import com.bioxx.tfc.Food.FloraManager;
import com.bioxx.tfc.Food.ItemFoodTFC;
import com.bioxx.tfc.TileEntities.TEAnvil;
import com.bioxx.tfc.TileEntities.TEBarrel;
import com.bioxx.tfc.TileEntities.TEBerryBush;
import com.bioxx.tfc.TileEntities.TEBlastFurnace;
import com.bioxx.tfc.TileEntities.TEBloom;
import com.bioxx.tfc.TileEntities.TEBloomery;
import com.bioxx.tfc.TileEntities.TECrop;
import com.bioxx.tfc.TileEntities.TEFarmland;
import com.bioxx.tfc.TileEntities.TEFirepit;
import com.bioxx.tfc.TileEntities.TEForge;
import com.bioxx.tfc.TileEntities.TEFruitLeaves;
import com.bioxx.tfc.TileEntities.TEFruitTreeWood;
import com.bioxx.tfc.TileEntities.TEIngotPile;
import com.bioxx.tfc.TileEntities.TELogPile;
import com.bioxx.tfc.TileEntities.TELoom;
import com.bioxx.tfc.TileEntities.TEMetalSheet;
import com.bioxx.tfc.TileEntities.TEMetalTrapDoor;
import com.bioxx.tfc.TileEntities.TENestBox;
import com.bioxx.tfc.TileEntities.TEOilLamp;
import com.bioxx.tfc.TileEntities.TEOre;
import com.bioxx.tfc.TileEntities.TEPottery;
import com.bioxx.tfc.TileEntities.TESapling;
import com.bioxx.tfc.TileEntities.TESluice;
import com.bioxx.tfc.TileEntities.TESmokeRack;
import com.bioxx.tfc.TileEntities.TESpawnMeter;
import com.bioxx.tfc.TileEntities.TEWorldItem;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Crafting.BarrelManager;
import com.bioxx.tfc.api.Crafting.BarrelPreservativeRecipe;
import com.bioxx.tfc.api.Crafting.BarrelRecipe;
import com.bioxx.tfc.api.Crafting.LoomManager;
import com.bioxx.tfc.api.Crafting.LoomRecipe;
import com.bioxx.tfc.api.Enums.EnumFoodGroup;
import com.bioxx.tfc.api.Food;
import com.bioxx.tfc.api.HeatIndex;
import com.bioxx.tfc.api.HeatRegistry;
import com.bioxx.tfc.api.Interfaces.IFood;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCFluids;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFCOptions;
import com.bioxx.tfc.api.TFC_ItemHeat;
import com.bioxx.tfc.api.Util.Helper;
import java.util.List;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;








public class WAILAData
  implements IWailaDataProvider
{
  public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
    Block block = accessor.getBlock();
    TileEntity tileEntity = accessor.getTileEntity();

    World world = accessor.getWorld();
    MovingObjectPosition pos = accessor.getPosition();

    if (block instanceof BlockAnvil) {
      return anvilStack(accessor, config);
    }
    if (tileEntity instanceof TEBerryBush) {
      return berryBushStack(accessor, config);
    }
    if (tileEntity instanceof TEBloom) {
      return new ItemStack(TFCItems.rawBloom);
    }
    if (block instanceof BlockCharcoal) {
      return new ItemStack(TFCItems.coal, accessor.getMetadata(), 1);
    }
    if (TFC_Core.isClay(block) || TFC_Core.isClayGrass(block)) {
      return new ItemStack(TFCItems.clayBall);
    }
    if (block instanceof BlockCobble) {
      return new ItemStack(block, 1, accessor.getMetadata() % 8);
    }
    if (tileEntity instanceof TECrop) {
      return cropStack(accessor, config);
    }
    if (block instanceof BlockCustomDoor) {
      return new ItemStack(Recipes.doors[((BlockCustomDoor)block).getWoodType() / 2]);
    }
    if (tileEntity instanceof TEFruitLeaves) {
      return fruitLeavesStack(accessor, config);
    }
    if (tileEntity instanceof TEFruitTreeWood) {
      return fruitTreeWoodStack(accessor, config);
    }
    if (tileEntity instanceof TEIngotPile) {
      return ingotPileStack(accessor, config);
    }
    if (tileEntity instanceof TELoom) {
      return loomStack(accessor, config);
    }
    if (tileEntity instanceof TEMetalSheet) {
      return metalSheetStack(accessor, config);
    }
    if (tileEntity instanceof TEMetalTrapDoor) {
      return metalTrapDoorStack(accessor, config);
    }
    if (tileEntity instanceof TEOilLamp) {
      return oilLampStack(accessor, config);
    }
    if (tileEntity instanceof TEOre) {
      return oreStack(accessor, config);
    }
    if (block instanceof BlockPartial) {
      return partialStack(accessor, config);
    }
    if (block instanceof BlockWaterPlant && TFC_Core.isSaltWater(world.func_147439_a(pos.field_72311_b, pos.field_72312_c + 1, pos.field_72309_d))) {
      return ItemFoodTFC.createTag(new ItemStack(TFCItems.seaWeed, 1, 0));
    }
    if (tileEntity instanceof TEWorldItem) {
      return worldItemStack(accessor, config);
    }
    return null;
  }



  public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    Block block = accessor.getBlock();
    TileEntity tileEntity = accessor.getTileEntity();

    if (tileEntity instanceof TEBarrel) {
      currenttip = barrelHead(itemStack, currenttip, accessor, config);
    }
    else if (tileEntity instanceof TEFruitLeaves) {
      currenttip = fruitLeavesHead(itemStack, currenttip, accessor, config);
    }
    else if (tileEntity instanceof TEFruitTreeWood) {
      currenttip = fruitTreeWoodHead(itemStack, currenttip, accessor, config);
    }
    else if (tileEntity instanceof TEOre) {
      currenttip = oreHead(itemStack, currenttip, accessor, config);
    }
    else if (tileEntity instanceof TESmokeRack) {
      currenttip.set(0, EnumChatFormatting.WHITE.toString() + TFC_Core.translate("tile.SmokeRack.name"));
    }
    else if (block instanceof BlockWaterPlant) {
      currenttip = waterPlantHead(itemStack, currenttip, accessor, config);
    }
    return currenttip;
  }



  public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    Block block = accessor.getBlock();
    TileEntity tileEntity = accessor.getTileEntity();
    if (tileEntity instanceof TEAnvil) {
      currenttip = anvilBody(itemStack, currenttip, accessor, config);
    }
    else if (tileEntity instanceof TEBarrel) {
      currenttip = barrelBody(itemStack, currenttip, accessor, config);
    }
    else if (tileEntity instanceof TEBerryBush) {
      currenttip = berryBushBody(itemStack, currenttip, accessor, config);
    }
    else if (tileEntity instanceof TEBlastFurnace) {
      currenttip = blastFurnaceBody(itemStack, currenttip, accessor, config);
    }
    else if (tileEntity instanceof TEBloom) {
      currenttip = bloomBody(itemStack, currenttip, accessor, config);
    }
    else if (tileEntity instanceof TEBloomery) {
      currenttip = bloomeryBody(itemStack, currenttip, accessor, config);
    }
    else if (tileEntity instanceof TECrop) {
      currenttip = cropBody(itemStack, currenttip, accessor, config);




    }
    else if (tileEntity instanceof TEFirepit) {
      currenttip = firepitBody(itemStack, currenttip, accessor, config);
    }
    else if (tileEntity instanceof TEForge) {
      currenttip = forgeBody(itemStack, currenttip, accessor, config);
    }
    else if (tileEntity instanceof TEFruitLeaves) {
      currenttip = fruitLeavesBody(itemStack, currenttip, accessor, config);
    }
    else if (tileEntity instanceof TELogPile) {
      currenttip = logPileBody(itemStack, currenttip, accessor, config);
    }
    else if (tileEntity instanceof TELoom) {
      currenttip = loomBody(itemStack, currenttip, accessor, config);
    }
    else if (tileEntity instanceof TEMetalTrapDoor) {
      currenttip = metalTrapDoorBody(itemStack, currenttip, accessor, config);
    }
    else if (tileEntity instanceof TENestBox) {
      currenttip = nestBoxBody(itemStack, currenttip, accessor, config);
    }
    else if (tileEntity instanceof TEOilLamp) {
      currenttip = oilLampBody(itemStack, currenttip, accessor, config);
    }
    else if (tileEntity instanceof TEOre) {
      currenttip = oreBody(itemStack, currenttip, accessor, config);
    }
    else if (tileEntity instanceof TEPottery) {
      currenttip = potteryBody(itemStack, currenttip, accessor, config);
    }
    else if (tileEntity instanceof TESapling) {
      currenttip = saplingBody(itemStack, currenttip, accessor, config);
    }
    else if (tileEntity instanceof TESluice) {
      currenttip = sluiceBody(itemStack, currenttip, accessor, config);
    }
    else if (tileEntity instanceof TESmokeRack) {
      currenttip = smokeRackBody(itemStack, currenttip, accessor, config);
    }
    else if (TFC_Core.isSoilWAILA(block)) {
      currenttip = soilBody(itemStack, currenttip, accessor, config);
    }
    else if (tileEntity instanceof TESpawnMeter) {
      currenttip = spawnMeterBody(itemStack, currenttip, accessor, config);
    }
    else if (block == TFCBlocks.torch) {
      currenttip = torchBody(itemStack, currenttip, accessor, config);
    }
    return currenttip;
  }



  public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    return currenttip;
  }



  public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, int x, int y, int z) {
    if (te != null)
      te.func_145841_b(tag);
    return tag;
  }


  public static void callbackRegister(IWailaRegistrar reg) {
    reg.addConfig("TerraFirmaCraft", "tfc.oreQuality");

    reg.registerStackProvider(new WAILAData(), BlockAnvil.class);
    reg.registerBodyProvider(new WAILAData(), TEAnvil.class);
    reg.registerNBTProvider(new WAILAData(), TEAnvil.class);

    reg.registerHeadProvider(new WAILAData(), TEBarrel.class);
    reg.registerBodyProvider(new WAILAData(), TEBarrel.class);
    reg.registerNBTProvider(new WAILAData(), TEBarrel.class);

    reg.registerStackProvider(new WAILAData(), TEBerryBush.class);
    reg.registerBodyProvider(new WAILAData(), TEBerryBush.class);
    reg.registerNBTProvider(new WAILAData(), TEBerryBush.class);

    reg.registerBodyProvider(new WAILAData(), TEBlastFurnace.class);
    reg.registerNBTProvider(new WAILAData(), TEBlastFurnace.class);

    reg.registerStackProvider(new WAILAData(), TEBloom.class);
    reg.registerBodyProvider(new WAILAData(), TEBloom.class);
    reg.registerNBTProvider(new WAILAData(), TEBloom.class);

    reg.registerBodyProvider(new WAILAData(), TEBloomery.class);
    reg.registerNBTProvider(new WAILAData(), TEBloomery.class);

    reg.registerStackProvider(new WAILAData(), BlockCharcoal.class);
    reg.registerStackProvider(new WAILAData(), BlockClay.class);
    reg.registerStackProvider(new WAILAData(), BlockClayGrass.class);
    reg.registerStackProvider(new WAILAData(), BlockCobble.class);

    reg.registerStackProvider(new WAILAData(), TECrop.class);
    reg.registerBodyProvider(new WAILAData(), TECrop.class);
    reg.registerNBTProvider(new WAILAData(), TECrop.class);

    reg.registerStackProvider(new WAILAData(), BlockCustomDoor.class);

    reg.registerBodyProvider(new WAILAData(), TEFarmland.class);
    reg.registerNBTProvider(new WAILAData(), TEFarmland.class);

    reg.registerBodyProvider(new WAILAData(), TEFirepit.class);
    reg.registerNBTProvider(new WAILAData(), TEFirepit.class);

    reg.registerBodyProvider(new WAILAData(), TEForge.class);
    reg.registerNBTProvider(new WAILAData(), TEForge.class);

    reg.registerStackProvider(new WAILAData(), TEFruitLeaves.class);
    reg.registerHeadProvider(new WAILAData(), TEFruitLeaves.class);
    reg.registerBodyProvider(new WAILAData(), TEFruitLeaves.class);
    reg.registerNBTProvider(new WAILAData(), TEFruitLeaves.class);

    reg.registerStackProvider(new WAILAData(), TEFruitTreeWood.class);
    reg.registerHeadProvider(new WAILAData(), TEFruitTreeWood.class);

    reg.registerStackProvider(new WAILAData(), TEIngotPile.class);
    reg.registerHeadProvider(new WAILAData(), TEIngotPile.class);
    reg.registerNBTProvider(new WAILAData(), TEIngotPile.class);

    reg.registerBodyProvider(new WAILAData(), TELogPile.class);
    reg.registerNBTProvider(new WAILAData(), TELogPile.class);

    reg.registerStackProvider(new WAILAData(), TELoom.class);
    reg.registerBodyProvider(new WAILAData(), TELoom.class);
    reg.registerNBTProvider(new WAILAData(), TELoom.class);

    reg.registerStackProvider(new WAILAData(), TEMetalSheet.class);
    reg.registerNBTProvider(new WAILAData(), TEMetalSheet.class);

    reg.registerStackProvider(new WAILAData(), TEMetalTrapDoor.class);
    reg.registerBodyProvider(new WAILAData(), TEMetalTrapDoor.class);
    reg.registerNBTProvider(new WAILAData(), TEMetalTrapDoor.class);

    reg.registerBodyProvider(new WAILAData(), TENestBox.class);
    reg.registerNBTProvider(new WAILAData(), TENestBox.class);

    reg.registerStackProvider(new WAILAData(), TEOilLamp.class);
    reg.registerBodyProvider(new WAILAData(), TEOilLamp.class);
    reg.registerNBTProvider(new WAILAData(), TEOilLamp.class);

    reg.registerStackProvider(new WAILAData(), TEOre.class);
    reg.registerHeadProvider(new WAILAData(), TEOre.class);
    reg.registerBodyProvider(new WAILAData(), TEOre.class);

    reg.registerStackProvider(new WAILAData(), BlockPartial.class);
    reg.registerNBTProvider(new WAILAData(), BlockPartial.class);

    reg.registerBodyProvider(new WAILAData(), TEPottery.class);
    reg.registerNBTProvider(new WAILAData(), TEPottery.class);

    reg.registerBodyProvider(new WAILAData(), TESapling.class);
    reg.registerNBTProvider(new WAILAData(), TESapling.class);

    reg.registerBodyProvider(new WAILAData(), TESluice.class);
    reg.registerNBTProvider(new WAILAData(), TESluice.class);

    reg.registerHeadProvider(new WAILAData(), TESmokeRack.class);
    reg.registerBodyProvider(new WAILAData(), TESmokeRack.class);
    reg.registerNBTProvider(new WAILAData(), TESmokeRack.class);

    reg.registerBodyProvider(new WAILAData(), TESpawnMeter.class);
    reg.registerNBTProvider(new WAILAData(), TESpawnMeter.class);


    reg.registerBodyProvider(new WAILAData(), BlockDirt.class);
    reg.registerBodyProvider(new WAILAData(), BlockSand.class);
    reg.registerBodyProvider(new WAILAData(), BlockGrass.class);
    reg.registerBodyProvider(new WAILAData(), BlockGravel.class);

    reg.registerBodyProvider(new WAILAData(), BlockTorch.class);
    reg.registerNBTProvider(new WAILAData(), BlockTorch.class);

    reg.registerStackProvider(new WAILAData(), BlockWaterPlant.class);
    reg.registerHeadProvider(new WAILAData(), BlockWaterPlant.class);

    reg.registerStackProvider(new WAILAData(), TEWorldItem.class);
    reg.registerNBTProvider(new WAILAData(), TEWorldItem.class);
  }



  public ItemStack anvilStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
    Block block = accessor.getBlock();
    int meta = accessor.getMetadata();
    int type = BlockAnvil.getAnvilTypeFromMeta(meta);

    return new ItemStack(block, 1, type);
  }


  public ItemStack berryBushStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
    NBTTagCompound tag = accessor.getNBTData();
    boolean hasFruit = tag.func_74767_n("hasFruit");
    FloraIndex index = FloraManager.getInstance().findMatchingIndex(BlockBerryBush.metaNames[accessor.getMetadata()]);

    if (hasFruit) {
      return ItemFoodTFC.createTag(index.getOutput());
    }
    return null;
  }

  public ItemStack cropStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
    ItemStack itemstack;
    NBTTagCompound tag = accessor.getNBTData();
    int cropId = tag.func_74762_e("cropId");

    CropIndex crop = CropManager.getInstance().getCropFromId(cropId);


    if (crop.output2 != null) {
      itemstack = new ItemStack(crop.output2);
    } else {
      itemstack = new ItemStack(crop.output1);
    }
    ItemFoodTFC.createTag(itemstack);
    return itemstack;
  }


  public ItemStack fruitLeavesStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
    FloraIndex index = FloraManager.getInstance().findMatchingIndex(BlockFruitLeaves.getType(accessor.getBlock(), accessor.getMetadata() % 8));

    if (index != null) {
      return ItemFoodTFC.createTag(index.getOutput());
    }
    return null;
  }


  public ItemStack fruitTreeWoodStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
    FloraIndex index = FloraManager.getInstance().findMatchingIndex(BlockFruitWood.getType(accessor.getMetadata()));

    if (index != null) {
      return ItemFoodTFC.createTag(index.getOutput());
    }
    return null;
  }


  public ItemStack ingotPileStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
    NBTTagCompound tag = accessor.getNBTData();
    ItemStack[] storage = getStorage(tag, accessor.getTileEntity());

    if (storage[0] != null) {
      return storage[0];
    }
    return null;
  }


  public ItemStack loomStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
    NBTTagCompound tag = accessor.getNBTData();
    boolean finished = tag.func_74767_n("finished");
    ItemStack[] storage = getStorage(tag, accessor.getTileEntity());

    if (finished && storage[1] != null)
    {
      return storage[1];
    }
    return null;
  }


  public ItemStack metalSheetStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
    NBTTagCompound tag = accessor.getNBTData();
    return ItemStack.func_77949_a(tag.func_74775_l("sheetType"));
  }


  public ItemStack metalTrapDoorStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
    NBTTagCompound tag = accessor.getNBTData();
    return ItemStack.func_77949_a(tag.func_74775_l("sheetType"));
  }


  public ItemStack oilLampStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
    int meta = accessor.getMetadata();
    if ((meta & 0x8) != 0) {
      meta -= 8;
    }
    return new ItemStack(TFCBlocks.oilLamp, 1, meta);
  }


  public ItemStack oreStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
    int meta = accessor.getMetadata();
    TEOre te = (TEOre)accessor.getTileEntity();
    ItemStack itemstack = null;

    if (accessor.getBlock() == TFCBlocks.ore) {

      if (config.getConfig("tfc.oreQuality")) {
        itemstack = new ItemStack(TFCItems.oreChunk, 1, getOreGrade(te, meta));
      } else {
        itemstack = new ItemStack(TFCItems.oreChunk, 1, meta);
      }
      if (meta == 14 || meta == 15) {
        itemstack = new ItemStack(TFCItems.coal);
      }
      return itemstack;
    }
    if (accessor.getBlock() == TFCBlocks.ore2) {

      itemstack = new ItemStack(TFCItems.oreChunk, 1, meta + Global.ORE_METAL.length);
      if (meta == 5) {
        itemstack = new ItemStack(TFCItems.gemDiamond);
      } else if (meta == 13) {
        itemstack = new ItemStack(TFCItems.powder, 1, 4);
      }
      return itemstack;
    }
    if (accessor.getBlock() == TFCBlocks.ore3) {

      itemstack = new ItemStack(TFCItems.oreChunk, 1, meta + Global.ORE_METAL.length + Global.ORE_MINERAL.length);
      return itemstack;
    }

    return null;
  }


  public ItemStack partialStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
    NBTTagCompound tag = accessor.getNBTData();
    byte metaID = tag.func_74771_c("metaID");
    int typeID = tag.func_74765_d("typeID");

    return new ItemStack(Block.func_149729_e(typeID), 1, metaID);
  }


  public ItemStack worldItemStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
    NBTTagCompound tag = accessor.getNBTData();
    ItemStack[] storage = getStorage(tag, accessor.getTileEntity());
    return storage[0];
  }



  public List<String> barrelHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    String head = currenttip.get(0);
    NBTTagCompound tag = accessor.getNBTData();
    FluidStack fluid = FluidStack.loadFluidStackFromNBT(tag.func_74775_l("fluidNBT"));

    if (fluid != null) {

      head = head + " (" + fluid.getLocalizedName() + ")";
      currenttip.set(0, head);
    }
    return currenttip;
  }


  public List<String> fruitLeavesHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    NBTTagCompound tag = accessor.getNBTData();
    boolean hasFruit = tag.func_74767_n("hasFruit");
    String type = BlockFruitLeaves.getType(accessor.getBlock(), accessor.getMetadata());

    if (!hasFruit) {
      currenttip.set(0, EnumChatFormatting.WHITE.toString() + TFC_Core.translate("gui." + type));
    }
    return currenttip;
  }


  public List<String> fruitTreeWoodHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    String type = BlockFruitWood.getType(accessor.getMetadata());

    currenttip.set(0, EnumChatFormatting.WHITE.toString() + TFC_Core.translate("gui." + type));

    return currenttip;
  }


  public List<String> ingotPileHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    String head = currenttip.get(0);
    currenttip.set(0, head + " " + TFC_Core.translate("gui.pile"));
    return currenttip;
  }


  public List<String> oreHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    int meta = accessor.getMetadata();

    if (accessor.getBlock() == TFCBlocks.ore) {

      if (meta == 14)
      {
        currenttip.set(0, EnumChatFormatting.WHITE.toString() + TFC_Core.translate("item.Ore.Bituminous Coal.name"));
      }
      else if (meta == 15)
      {
        currenttip.set(0, EnumChatFormatting.WHITE.toString() + TFC_Core.translate("item.Ore.Lignite.name"));
      }

    } else if (accessor.getBlock() == TFCBlocks.ore2) {

      if (meta == 5) {

        currenttip.set(0, EnumChatFormatting.WHITE.toString() + TFC_Core.translate("item.Ore.Kimberlite.name"));
      }
      else if (meta == 13) {

        currenttip.set(0, EnumChatFormatting.WHITE.toString() + TFC_Core.translate("item.Ore.Saltpeter.name"));
      }
    }

    return currenttip;
  }


  public List<String> waterPlantHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    MovingObjectPosition pos = accessor.getPosition();
    World world = accessor.getWorld();
    Block blockDirectlyAbove = world.func_147439_a(pos.field_72311_b, pos.field_72312_c + 1, pos.field_72309_d);
    boolean airTwoAbove = world.func_147437_c(pos.field_72311_b, pos.field_72312_c + 2, pos.field_72309_d);

    if (TFC_Core.isFreshWater(blockDirectlyAbove))
    {
      if (airTwoAbove) {

        currenttip.set(0, EnumChatFormatting.WHITE.toString() + TFC_Core.translate("tile.Flora.Cat Tails.name"));
      }
      else {

        currenttip.set(0, EnumChatFormatting.WHITE.toString() + TFC_Core.translate("tile.Flora.Pond Weed.name"));
      }
    }

    return currenttip;
  }



  public List<String> anvilBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    NBTTagCompound tag = accessor.getNBTData();

    int tier = tag.func_74762_e("Tier");
    currenttip.add(TFC_Core.translate("gui.tier") + " : " + tier);

    ItemStack[] storage = getStorage(tag, accessor.getTileEntity());
    ItemStack flux = storage[6];

    if (flux != null && flux.func_77973_b() == TFCItems.powder && flux.func_77960_j() == 0 && flux.field_77994_a > 0) {
      currenttip.add(TFC_Core.translate("item.Powder.Flux.name") + " : " + flux.field_77994_a);
    }
    return currenttip;
  }


  public List<String> barrelBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    TEBarrel te = (TEBarrel)accessor.getTileEntity();
    NBTTagCompound tag = accessor.getNBTData();
    ItemStack[] storage = getStorage(tag, (TileEntity)te);
    ItemStack inStack = storage[0];

    Boolean sealed = Boolean.valueOf(te.getSealed());
    int sealTime = accessor.getNBTInteger(tag, "SealTime");
    FluidStack fluid = FluidStack.loadFluidStackFromNBT(tag.func_74775_l("fluidNBT"));
    BarrelRecipe recipe = BarrelManager.getInstance().findMatchingRecipe(inStack, fluid, sealed.booleanValue(), te.getTechLevel());

    if (sealed.booleanValue() && fluid != null && fluid.getFluid() == TFCFluids.MILKCURDLED && (inStack == null || (inStack
      .func_77973_b() instanceof IFood && ((IFood)inStack.func_77973_b()).getFoodGroup() != EnumFoodGroup.Dairy && ((IFood)inStack.func_77973_b()).isEdible(inStack) && Food.getWeight(inStack) <= 20.0F))) {
      recipe = (new BarrelRecipe(null, new FluidStack(TFCFluids.MILKCURDLED, 10000), ItemFoodTFC.createTag(new ItemStack(TFCItems.cheese, 1), 160.0F), null)).setMinTechLevel(0);
    }

    if (fluid != null)
    {
      currenttip.add(fluid.amount + "/" + te.getMaxLiquid() + " mB");
    }


    if (sealed.booleanValue() && sealTime != 0)
    {
      currenttip.add(TFC_Core.translate("gui.Barrel.SealedOn") + " : " + TFC_Time.getDateStringFromHours(sealTime));
    }



    BarrelPreservativeRecipe preservative = BarrelManager.getInstance().findMatchingPreservativeRepice(te, inStack, fluid, sealed.booleanValue());


    if (recipe != null) {

      if (!(recipe instanceof com.bioxx.tfc.api.Crafting.BarrelBriningRecipe))
      {
        currenttip.add(TFC_Core.translate("gui.Output") + " : " + recipe.getRecipeName());
      }
      else if (sealed.booleanValue() && fluid != null && fluid.getFluid() == TFCFluids.BRINE)
      {
        if (inStack != null && inStack.func_77973_b() instanceof IFood && (((IFood)inStack.func_77973_b()).getFoodGroup() == EnumFoodGroup.Fruit || ((IFood)inStack.func_77973_b()).getFoodGroup() == EnumFoodGroup.Vegetable || ((IFood)inStack.func_77973_b()).getFoodGroup() == EnumFoodGroup.Protein || (IFood)inStack.func_77973_b() == TFCItems.cheese) && !Food.isBrined(inStack))
        {
          currenttip.add(TFC_Core.translate("gui.barrel.brining"));
        }
      }

    } else if (sealed.booleanValue() && fluid != null && inStack != null && inStack.func_77973_b() instanceof IFood && fluid.getFluid() == TFCFluids.VINEGAR) {

      if (!Food.isPickled(inStack) && Food.getWeight(inStack) / fluid.amount <= 160.0F / te.getMaxLiquid()) {

        if ((((IFood)inStack.func_77973_b()).getFoodGroup() == EnumFoodGroup.Fruit || ((IFood)inStack.func_77973_b()).getFoodGroup() == EnumFoodGroup.Vegetable || ((IFood)inStack.func_77973_b()).getFoodGroup() == EnumFoodGroup.Protein || (IFood)inStack.func_77973_b() == TFCItems.cheese) && Food.isBrined(inStack))
        {
          currenttip.add(TFC_Core.translate("gui.barrel.pickling"));
        }
      }
      else if (Food.isPickled(inStack) && Food.getWeight(inStack) / fluid.amount <= 160.0F / te.getMaxLiquid() * 2.0F) {

        currenttip.add(TFC_Core.translate("gui.barrel.preserving"));
      }
    } else if (preservative != null) {
      currenttip.add(TFC_Core.translate(preservative.getPreservingString()));
    }

    return currenttip;
  }


  public List<String> berryBushBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    FloraIndex index = FloraManager.getInstance().findMatchingIndex(BlockBerryBush.metaNames[accessor.getMetadata()]);
    currenttip.add(TFC_Time.SEASONS[index.harvestStart] + " - " + TFC_Time.SEASONS[index.harvestFinish]);
    return currenttip;
  }


  public List<String> blastFurnaceBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    TEBlastFurnace te = (TEBlastFurnace)accessor.getTileEntity();
    NBTTagCompound tag = accessor.getNBTData();

    int charcoalCount = tag.func_74762_e("charcoalCount");
    int oreCount = tag.func_74771_c("oreCount");
    int stackSize = tag.func_74762_e("maxValidStackSize");
    float temperature = 0.0F;

    ItemStack[] storage = getStorage(tag, (TileEntity)te);
    ItemStack oreStack = storage[0];

    HeatRegistry manager = HeatRegistry.getInstance();

    if (oreStack != null) {

      HeatIndex index = manager.findMatchingIndex(oreStack);
      if (index != null)
      {
        temperature = TFC_ItemHeat.getTemp(oreStack);
      }
    }
    String temp = TFC_ItemHeat.getHeatColor(temperature, te.maxFireTempScale);

    currenttip.add(TFC_Core.translate("gui.Bloomery.Charcoal") + " : " + charcoalCount + "/" + (stackSize * 4));
    currenttip.add(TFC_Core.translate("gui.Bloomery.Ore") + " : " + oreCount + "/" + (stackSize * 4));

    if (te.storage[1] != null) {
      currenttip.add(TFC_Core.translate("gui.plans.tuyere") + EnumChatFormatting.GREEN.toString() + " ✔");
    } else {
      currenttip.add(TFC_Core.translate("gui.plans.tuyere") + EnumChatFormatting.RED.toString() + " ✘");
    }
    if (temperature > 0.0F)
    {
      currenttip.add(temp);
    }

    return currenttip;
  }


  public List<String> bloomBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    NBTTagCompound tag = accessor.getNBTData();
    int size = tag.func_74762_e("size");

    currenttip.add(TFC_Core.translate("gui.units") + " : " + size);
    return currenttip;
  }


  public List<String> bloomeryBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    NBTTagCompound tag = accessor.getNBTData();
    boolean isLit = tag.func_74767_n("isLit");
    int charcoalCount = tag.func_74762_e("charcoalCount");
    int oreCount = tag.func_74762_e("oreCount");

    currenttip.add(TFC_Core.translate("gui.Blast.Charcoal") + " : " + charcoalCount);

    currenttip.add(TFC_Core.translate("gui.Bloomery.Ore") + " : " + oreCount);

    if (isLit) {

      long hours = tag.func_74763_f("fuelTimeLeft") / 1000L - TFC_Time.getTotalHours();

      if (hours > 0L) {

        float percent = Helper.roundNumber(Math.min(100.0F - (float)hours / TFCOptions.bloomeryBurnTime * 100.0F, 100.0F), 10.0F);
        currenttip.add(hours + " " + TFC_Core.translate("gui.hoursRemaining") + " (" + percent + "%)");
      }
    }

    return currenttip;
  }


  public List<String> cropBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    NBTTagCompound tag = accessor.getNBTData();
    float growth = tag.func_74760_g("growth");
    int cropId = tag.func_74762_e("cropId");

    CropIndex crop = CropManager.getInstance().getCropFromId(cropId);
    int percentGrowth = (int)Math.min(growth / crop.numGrowthStages * 100.0F, 100.0F);

    if (percentGrowth < 100) {
      currenttip.add(TFC_Core.translate("gui.growth") + " : " + percentGrowth + "%");
    } else {
      currenttip.add(TFC_Core.translate("gui.growth") + " : " + TFC_Core.translate("gui.mature"));
    }
    return currenttip;
  }


  public List<String> farmlandBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    SkillStats.SkillRank rank = TFC_Core.getSkillStats(accessor.getPlayer()).getSkillRank("skill.agriculture");
    if (rank == SkillStats.SkillRank.Expert || rank == SkillStats.SkillRank.Master) {

      TEFarmland te = (TEFarmland)accessor.getTileEntity();
      NBTTagCompound tag = accessor.getNBTData();

      int[] nutrients = tag.func_74759_k("nutrients");
      int soilMax = te.getSoilMax();

      for (int i = 0; i < nutrients.length; i++) {

        int percent = Math.max(nutrients[i] * 100 / soilMax, 0);

        if (i == 0) {
          currenttip.add(EnumChatFormatting.RED + TFC_Core.translate("gui.Nutrient.A") + " : " + percent + "%");
        } else if (i == 1) {
          currenttip.add(EnumChatFormatting.GOLD + TFC_Core.translate("gui.Nutrient.B") + " : " + percent + "%");
        } else if (i == 2) {
          currenttip.add(EnumChatFormatting.YELLOW + TFC_Core.translate("gui.Nutrient.C") + " : " + percent + "%");
        } else if (i == 3 && percent != 0) {
          currenttip.add(EnumChatFormatting.WHITE + TFC_Core.translate("item.Fertilizer.name") + " : " + percent + "%");
        }
      }
    }
    return currenttip;
  }


  public List<String> firepitBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    NBTTagCompound tag = accessor.getNBTData();
    ItemStack[] storage = getStorage(tag, accessor.getTileEntity());

    if (storage != null) {

      int fuelCount = 0;
      for (ItemStack is : storage) {

        if (is != null && is.func_77973_b() != null && (is.func_77973_b() == TFCItems.logs || is.func_77973_b() == Item.func_150898_a(TFCBlocks.peat))) {
          fuelCount++;
        }
      }
      if (fuelCount > 0) {
        currenttip.add(TFC_Core.translate("gui.fuel") + " : " + fuelCount + "/4");
      }
    }
    return currenttip;
  }


  public List<String> forgeBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    NBTTagCompound tag = accessor.getNBTData();
    ItemStack[] storage = getStorage(tag, accessor.getTileEntity());

    if (storage != null) {

      int fuelCount = 0;
      boolean hasMold = false;

      for (int i = 5; i <= 9; i++) {

        if (storage[i] != null && storage[i].func_77973_b() != null && storage[i].func_77973_b() instanceof com.bioxx.tfc.Items.ItemCoal) {
          fuelCount++;
        }
      }
      if (fuelCount > 0) {
        currenttip.add(TFC_Core.translate("gui.fuel") + " : " + fuelCount + "/5");
      }
      for (int j = 10; j <= 13; j++) {

        if (storage[j] != null && storage[j].func_77973_b() == TFCItems.ceramicMold && (storage[j]).field_77994_a > 0)
          hasMold = true;
      }
      if (hasMold) {
        currenttip.add(TFC_Core.translate("item.Mold.Ceramic Mold.name") + EnumChatFormatting.GREEN.toString() + " ✔");
      } else {
        currenttip.add(TFC_Core.translate("item.Mold.Ceramic Mold.name") + EnumChatFormatting.RED.toString() + " ✘");
      }
    }
    return currenttip;
  }


  public List<String> fruitLeavesBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    FloraIndex index = FloraManager.getInstance().findMatchingIndex(BlockFruitLeaves.getType(accessor.getBlock(), accessor.getMetadata()));
    if (index != null)
      currenttip.add(TFC_Time.SEASONS[index.harvestStart] + " - " + TFC_Time.SEASONS[index.harvestFinish]);
    return currenttip;
  }


  public List<String> logPileBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    NBTTagCompound tag = accessor.getNBTData();
    Boolean isOnFire = Boolean.valueOf(tag.func_74767_n("isOnFire"));

    if (isOnFire.booleanValue()) {

      int fireTimer = tag.func_74762_e("fireTimer");
      int hours = (int)(TFCOptions.charcoalPitBurnTime - (float)(TFC_Time.getTotalHours() - fireTimer));
      currenttip.add(hours + " " + TFC_Core.translate("gui.hoursRemaining") + " (" + Helper.roundNumber(100.0F - hours / TFCOptions.charcoalPitBurnTime * 100.0F, 10.0F) + "%)");
    }
    else {

      ItemStack[] storage = getStorage(tag, accessor.getTileEntity());
      boolean[] counted = { false, false, false, false };






      for (int j = 0; j < storage.length; j++) {

        if (storage[j] != null && !counted[j]) {

          String log = storage[j].func_82833_r() + " : ";
          int count = (storage[j]).field_77994_a;
          for (int k = j + 1; k < storage.length; k++) {

            if (storage[k] != null && storage[j].func_77969_a(storage[k])) {

              count += (storage[k]).field_77994_a;
              counted[k] = true;
            }
          }
          currenttip.add(log + count);
          counted[j] = true;
        }
      }
    }

    return currenttip;
  }


  public List<String> loomBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    NBTTagCompound tag = accessor.getNBTData();
    boolean finished = tag.func_74767_n("finished");
    int wovenStrings = tag.func_74762_e("cloth");
    ItemStack[] storage = getStorage(tag, accessor.getTileEntity());

    if (!finished && storage[0] != null) {

      LoomRecipe recipe = LoomManager.getInstance().findPotentialRecipes(storage[0]);
      int maxStrings = recipe.getReqSize();

      if ((storage[0]).field_77994_a < maxStrings) {

        String name = storage[0].func_82833_r() + " : ";
        currenttip.add(name + (storage[0]).field_77994_a + "/" + maxStrings);
      }
      else {

        String name = recipe.getOutItemStack().func_82833_r() + " : ";
        int percent = (int)(100.0D * wovenStrings / maxStrings);
        currenttip.add(TFC_Core.translate("gui.weaving") + " " + name + percent + "%");
      }
    }

    return currenttip;
  }


  public List<String> metalTrapDoorBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    NBTTagCompound tag = accessor.getNBTData();
    ItemStack sheetStack = ItemStack.func_77949_a(tag.func_74775_l("sheetType"));

    String metalType = BlockMetalTrapDoor.metalNames[sheetStack.func_77960_j() & 0x1F];
    currenttip.add(TFC_Core.translate("gui.metal." + metalType.replaceAll("\\s", "")));
    return currenttip;
  }


  public List<String> nestBoxBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    NBTTagCompound tag = accessor.getNBTData();
    ItemStack[] storage = getStorage(tag, accessor.getTileEntity());
    int eggCount = 0, fertEggCount = 0;

    for (ItemStack is : storage) {

      if (is != null && is.func_77973_b() == TFCItems.egg)
      {
        if (is.func_77942_o() && is.func_77978_p().func_74764_b("Fertilized")) {
          fertEggCount++;
        } else {
          eggCount++;
        }
      }
    }
    if (eggCount > 0)
      currenttip.add(TFC_Core.translate("gui.eggs") + " : " + eggCount);
    if (fertEggCount > 0) {
      currenttip.add(TFC_Core.translate("gui.fertEggs") + " : " + fertEggCount);
    }
    return currenttip;
  }


  public List<String> oilLampBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    NBTTagCompound tag = accessor.getNBTData();
    if (tag.func_74764_b("Fuel")) {

      FluidStack fuel = FluidStack.loadFluidStackFromNBT(tag.func_74775_l("Fuel"));
      int hours = fuel.amount * TFCOptions.oilLampFuelMult / 8;
      if (fuel.getFluid() == TFCFluids.OLIVEOIL) {
        currenttip.add(hours + " " + TFC_Core.translate("gui.hoursRemaining") + " (" + Helper.roundNumber(hours / 250.0F * TFCOptions.oilLampFuelMult * 100.0F, 10.0F) + "%)");
      } else if (fuel.getFluid() == TFCFluids.LAVA) {
        currenttip.add(TFC_Core.translate("gui.infinite") + " " + TFC_Core.translate("gui.hoursRemaining"));
      }
    }  return currenttip;
  }


  public List<String> oreBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    int meta = accessor.getMetadata();

    if (accessor.getBlock() == TFCBlocks.ore) {

      switch (meta) {

        case 0:
        case 9:
        case 13:
          currenttip.add(TFC_Core.translate("gui.metal.Copper"));
          break;
        case 1:
          currenttip.add(TFC_Core.translate("gui.metal.Gold"));
          break;
        case 2:
          currenttip.add(TFC_Core.translate("gui.metal.Platinum") + " - " + TFC_Core.translate("gui.useless"));
          break;
        case 3:
        case 10:
        case 11:
          currenttip.add(TFC_Core.translate("gui.metal.Iron"));
          break;
        case 4:
          currenttip.add(TFC_Core.translate("gui.metal.Silver"));
          break;
        case 5:
          currenttip.add(TFC_Core.translate("gui.metal.Tin"));
          break;
        case 6:
          currenttip.add(TFC_Core.translate("gui.metal.Lead") + " - " + TFC_Core.translate("gui.useless"));
          break;
        case 7:
          currenttip.add(TFC_Core.translate("gui.metal.Bismuth"));
          break;
        case 8:
          currenttip.add(TFC_Core.translate("gui.metal.Nickel"));
          break;
        case 12:
          currenttip.add(TFC_Core.translate("gui.metal.Zinc"));
          break;
        case 14:
        case 15:
          currenttip.add(TFC_Core.translate("item.coal.coal.name"));
          return currenttip;
      }

      if (config.getConfig("tfc.oreQuality"))
      {
        TEOre te = (TEOre)accessor.getTileEntity();

        int ore = getOreGrade(te, meta);

        int units = (ore < 14) ? TFCOptions.normalOreUnits : ((ore < 49) ? TFCOptions.richOreUnits : ((ore < 63) ? TFCOptions.poorOreUnits : 0));
        if (units > 0) {
          currenttip.add(TFC_Core.translate("gui.units") + " : " + units);
        }
      }

    } else if (accessor.getBlock() == TFCBlocks.ore2) {

      switch (meta) {

        case 1:
        case 2:
        case 3:
        case 6:
        case 8:
        case 9:
        case 10:
        case 14:
          currenttip.add(TFC_Core.translate("gui.useless"));
          break;
        case 5:
          currenttip.add(TFC_Core.translate("item.Diamond.Normal.name"));
          break;
        case 11:
        case 12:
          currenttip.add(TFC_Core.translate("item.redstone.name"));
          break;
        case 15:
          currenttip.add(TFC_Core.translate("item.Fertilizer.name"));
          break;
      }

    } else if (accessor.getBlock() == TFCBlocks.ore3) {

      switch (meta) {

        case 0:
          currenttip.add(TFC_Core.translate("item.Powder.Flux.name"));
          break;
        case 1:
          currenttip.add(TFC_Core.translate("gui.useless"));
          break;
      }

    }
    return currenttip;
  }


  public List<String> potteryBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    NBTTagCompound tag = accessor.getNBTData();
    long burnStart = tag.func_74763_f("burnStart");
    int wood = tag.func_74762_e("wood");
    int straw = tag.func_74762_e("straw");

    if (straw > 0 && straw < 8) {
      currenttip.add(TFC_Core.translate("item.Straw.name") + " : " + straw + "/8");
    } else if (wood > 0 && wood < 8) {
      currenttip.add(TFC_Core.translate("gui.logs") + " : " + wood + "/8");
    } else if (burnStart > 0L) {

      int hours = (int)(TFCOptions.pitKilnBurnTime - (float)(TFC_Time.getTotalHours() - burnStart / 1000L));
      currenttip.add(hours + " " + TFC_Core.translate("gui.hoursRemaining") + " (" + Helper.roundNumber(100.0F - hours / TFCOptions.pitKilnBurnTime * 100.0F, 10.0F) + "%)");
    }

    return currenttip;
  }


  public List<String> saplingBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    NBTTagCompound tag = accessor.getNBTData();
    boolean enoughSpace = tag.func_74767_n("enoughSpace");
    long growTime = tag.func_74763_f("growTime");
    int days = (int)((growTime - TFC_Time.getTotalTicks()) / 24000L);
    if (days > 0) {

      currenttip.add(days + " " + TFC_Core.translate("gui.daysRemaining"));
    }
    else if (!enoughSpace) {

      currenttip.add(TFC_Core.translate("gui.enoughSpace"));
    }

    return currenttip;
  }


  public List<String> sluiceBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    NBTTagCompound tag = accessor.getNBTData();
    ItemStack[] storage = getStorage(tag, accessor.getTileEntity());
    int soilAmount = tag.func_74762_e("soilAmount");

    if (soilAmount == -1) {
      currenttip.add(TFC_Core.translate("gui.Sluice.Overworked"));
    } else if (soilAmount > 0) {

      currenttip.add(TFC_Core.translate("gui.Sluice.Soil") + " : " + soilAmount + "/50");
    }

    int gemCount = 0, oreCount = 0;
    for (ItemStack is : storage) {

      if (is != null)
      {
        if (is.func_77973_b() instanceof com.bioxx.tfc.Items.ItemGem) {
          gemCount++;
        } else if (is.func_77973_b() instanceof com.bioxx.tfc.Items.ItemOre) {
          oreCount++;
        }
      }
    }
    if (gemCount > 0)
      currenttip.add(TFC_Core.translate("gui.gems") + " : " + gemCount);
    if (oreCount > 0) {
      currenttip.add(TFC_Core.translate("gui.Bloomery.Ore") + " : " + oreCount);
    }
    return currenttip;
  }


  public List<String> smokeRackBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    NBTTagCompound tag = accessor.getNBTData();
    ItemStack[] storage = getStorage(tag, accessor.getTileEntity());

    for (int i = 0; i < storage.length; i++) {

      ItemStack is = storage[i];
      if (is != null) {

        int dryHours = 4 - Food.getDried(is);
        int smokeHours = 12 - Food.getSmokeCounter(is);

        if (smokeHours < 12 && !Food.isSmoked(is)) {

          smokeHours++;
          float percent = Helper.roundNumber(100.0F - 100.0F * smokeHours / 12.0F, 10.0F);
          currenttip.add(TFC_Core.translate("word.smoking") + " " + is.func_82833_r());
          currenttip.add("· " + smokeHours + " " + TFC_Core.translate("gui.hoursRemaining") + " (" + percent + "%)");
        }
        else if (dryHours < 4 && !Food.isDried(is)) {

          float percent = Helper.roundNumber(100.0F - 100.0F * dryHours / 4.0F, 10.0F);
          currenttip.add(TFC_Core.translate("word.drying") + " " + is.func_82833_r());
          currenttip.add("· " + dryHours + " " + TFC_Core.translate("gui.hoursRemaining") + " (" + percent + "%)");
        } else {

          currenttip.add(is.func_82833_r());
        }
      }
    }
    return currenttip;
  }


  public List<String> spawnMeterBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    NBTTagCompound tag = accessor.getNBTData();
    int hours = tag.func_74762_e("protectionHours");

    if (hours > 0)
    {
      currenttip.add(hours + " " + TFC_Core.translate("gui.hoursRemaining"));
    }

    return currenttip;
  }


  public List<String> soilBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    Block b = accessor.getBlock();
    int dam = itemStack.func_77960_j();
    if (b == TFCBlocks.dirt2 || b == TFCBlocks.sand2 || TFC_Core.isGrassType2(b) || b == TFCBlocks.gravel2)
    {
      dam += 16;
    }

    if (dam < Global.STONE_ALL.length) {
      currenttip.add(Global.STONE_ALL[dam]);
    } else {
      currenttip.add(EnumChatFormatting.DARK_RED + "Unknown");
    }
    return currenttip;
  }


  public List<String> torchBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
    if (accessor.getMetadata() < 8 && TFCOptions.torchBurnTime != 0) {

      NBTTagCompound tag = accessor.getNBTData();
      long hours = TFCOptions.torchBurnTime - TFC_Time.getTotalHours() - tag.func_74762_e("hourPlaced");

      if (hours > 0L)
        currenttip.add(hours + " " + TFC_Core.translate("gui.hoursRemaining") + " (" + Helper.roundNumber(100.0F * (float)hours / TFCOptions.torchBurnTime, 10.0F) + "%)");
    }
    return currenttip;
  }



  private ItemStack[] getStorage(NBTTagCompound tag, TileEntity te) {
    if (te instanceof IInventory) {

      IInventory inv = (IInventory)te;
      NBTTagList tagList = tag.func_150295_c("Items", 10);
      ItemStack[] storage = new ItemStack[inv.func_70302_i_()];
      for (int i = 0; i < tagList.func_74745_c(); i++) {

        NBTTagCompound itemTag = tagList.func_150305_b(i);
        byte slot = itemTag.func_74771_c("Slot");
        if (slot >= 0 && slot < storage.length) {
          storage[slot] = ItemStack.func_77949_a(itemTag);
        }
      }
      return storage;
    }

    return null;
  }


  private int getOreGrade(TEOre te, int ore) {
    if (te != null) {

      int grade = te.extraData & 0x7;
      if (grade == 1) {
        ore += 35;
      } else if (grade == 2) {
        ore += 49;
      }
    }  return ore;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WAILA\WAILAData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */