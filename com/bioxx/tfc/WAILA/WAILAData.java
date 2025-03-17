/*      */ package com.bioxx.tfc.WAILA;
/*      */ 
/*      */ import com.bioxx.tfc.Blocks.BlockCharcoal;
/*      */ import com.bioxx.tfc.Blocks.BlockMetalTrapDoor;
/*      */ import com.bioxx.tfc.Blocks.BlockPartial;
/*      */ import com.bioxx.tfc.Blocks.Devices.BlockAnvil;
/*      */ import com.bioxx.tfc.Blocks.Flora.BlockBerryBush;
/*      */ import com.bioxx.tfc.Blocks.Flora.BlockFruitLeaves;
/*      */ import com.bioxx.tfc.Blocks.Flora.BlockFruitWood;
/*      */ import com.bioxx.tfc.Blocks.Flora.BlockWaterPlant;
/*      */ import com.bioxx.tfc.Blocks.Terrain.BlockClay;
/*      */ import com.bioxx.tfc.Blocks.Terrain.BlockClayGrass;
/*      */ import com.bioxx.tfc.Blocks.Terrain.BlockCobble;
/*      */ import com.bioxx.tfc.Blocks.Terrain.BlockDirt;
/*      */ import com.bioxx.tfc.Blocks.Terrain.BlockGrass;
/*      */ import com.bioxx.tfc.Blocks.Terrain.BlockGravel;
/*      */ import com.bioxx.tfc.Blocks.Terrain.BlockSand;
/*      */ import com.bioxx.tfc.Blocks.Vanilla.BlockCustomDoor;
/*      */ import com.bioxx.tfc.Blocks.Vanilla.BlockTorch;
/*      */ import com.bioxx.tfc.Core.Player.SkillStats;
/*      */ import com.bioxx.tfc.Core.Recipes;
/*      */ import com.bioxx.tfc.Core.TFC_Core;
/*      */ import com.bioxx.tfc.Core.TFC_Time;
/*      */ import com.bioxx.tfc.Food.CropIndex;
/*      */ import com.bioxx.tfc.Food.CropManager;
/*      */ import com.bioxx.tfc.Food.FloraIndex;
/*      */ import com.bioxx.tfc.Food.FloraManager;
/*      */ import com.bioxx.tfc.Food.ItemFoodTFC;
/*      */ import com.bioxx.tfc.TileEntities.TEAnvil;
/*      */ import com.bioxx.tfc.TileEntities.TEBarrel;
/*      */ import com.bioxx.tfc.TileEntities.TEBerryBush;
/*      */ import com.bioxx.tfc.TileEntities.TEBlastFurnace;
/*      */ import com.bioxx.tfc.TileEntities.TEBloom;
/*      */ import com.bioxx.tfc.TileEntities.TEBloomery;
/*      */ import com.bioxx.tfc.TileEntities.TECrop;
/*      */ import com.bioxx.tfc.TileEntities.TEFarmland;
/*      */ import com.bioxx.tfc.TileEntities.TEFirepit;
/*      */ import com.bioxx.tfc.TileEntities.TEForge;
/*      */ import com.bioxx.tfc.TileEntities.TEFruitLeaves;
/*      */ import com.bioxx.tfc.TileEntities.TEFruitTreeWood;
/*      */ import com.bioxx.tfc.TileEntities.TEIngotPile;
/*      */ import com.bioxx.tfc.TileEntities.TELogPile;
/*      */ import com.bioxx.tfc.TileEntities.TELoom;
/*      */ import com.bioxx.tfc.TileEntities.TEMetalSheet;
/*      */ import com.bioxx.tfc.TileEntities.TEMetalTrapDoor;
/*      */ import com.bioxx.tfc.TileEntities.TENestBox;
/*      */ import com.bioxx.tfc.TileEntities.TEOilLamp;
/*      */ import com.bioxx.tfc.TileEntities.TEOre;
/*      */ import com.bioxx.tfc.TileEntities.TEPottery;
/*      */ import com.bioxx.tfc.TileEntities.TESapling;
/*      */ import com.bioxx.tfc.TileEntities.TESluice;
/*      */ import com.bioxx.tfc.TileEntities.TESmokeRack;
/*      */ import com.bioxx.tfc.TileEntities.TESpawnMeter;
/*      */ import com.bioxx.tfc.TileEntities.TEWorldItem;
/*      */ import com.bioxx.tfc.api.Constant.Global;
/*      */ import com.bioxx.tfc.api.Crafting.BarrelManager;
/*      */ import com.bioxx.tfc.api.Crafting.BarrelPreservativeRecipe;
/*      */ import com.bioxx.tfc.api.Crafting.BarrelRecipe;
/*      */ import com.bioxx.tfc.api.Crafting.LoomManager;
/*      */ import com.bioxx.tfc.api.Crafting.LoomRecipe;
/*      */ import com.bioxx.tfc.api.Enums.EnumFoodGroup;
/*      */ import com.bioxx.tfc.api.Food;
/*      */ import com.bioxx.tfc.api.HeatIndex;
/*      */ import com.bioxx.tfc.api.HeatRegistry;
/*      */ import com.bioxx.tfc.api.Interfaces.IFood;
/*      */ import com.bioxx.tfc.api.TFCBlocks;
/*      */ import com.bioxx.tfc.api.TFCFluids;
/*      */ import com.bioxx.tfc.api.TFCItems;
/*      */ import com.bioxx.tfc.api.TFCOptions;
/*      */ import com.bioxx.tfc.api.TFC_ItemHeat;
/*      */ import com.bioxx.tfc.api.Util.Helper;
/*      */ import java.util.List;
/*      */ import mcp.mobius.waila.api.IWailaConfigHandler;
/*      */ import mcp.mobius.waila.api.IWailaDataAccessor;
/*      */ import mcp.mobius.waila.api.IWailaDataProvider;
/*      */ import mcp.mobius.waila.api.IWailaRegistrar;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.entity.player.EntityPlayerMP;
/*      */ import net.minecraft.inventory.IInventory;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.tileentity.TileEntity;
/*      */ import net.minecraft.util.EnumChatFormatting;
/*      */ import net.minecraft.util.MovingObjectPosition;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraftforge.fluids.FluidStack;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class WAILAData
/*      */   implements IWailaDataProvider
/*      */ {
/*      */   public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  101 */     Block block = accessor.getBlock();
/*  102 */     TileEntity tileEntity = accessor.getTileEntity();
/*      */     
/*  104 */     World world = accessor.getWorld();
/*  105 */     MovingObjectPosition pos = accessor.getPosition();
/*      */     
/*  107 */     if (block instanceof BlockAnvil) {
/*  108 */       return anvilStack(accessor, config);
/*      */     }
/*  110 */     if (tileEntity instanceof TEBerryBush) {
/*  111 */       return berryBushStack(accessor, config);
/*      */     }
/*  113 */     if (tileEntity instanceof TEBloom) {
/*  114 */       return new ItemStack(TFCItems.rawBloom);
/*      */     }
/*  116 */     if (block instanceof BlockCharcoal) {
/*  117 */       return new ItemStack(TFCItems.coal, accessor.getMetadata(), 1);
/*      */     }
/*  119 */     if (TFC_Core.isClay(block) || TFC_Core.isClayGrass(block)) {
/*  120 */       return new ItemStack(TFCItems.clayBall);
/*      */     }
/*  122 */     if (block instanceof BlockCobble) {
/*  123 */       return new ItemStack(block, 1, accessor.getMetadata() % 8);
/*      */     }
/*  125 */     if (tileEntity instanceof TECrop) {
/*  126 */       return cropStack(accessor, config);
/*      */     }
/*  128 */     if (block instanceof BlockCustomDoor) {
/*  129 */       return new ItemStack(Recipes.doors[((BlockCustomDoor)block).getWoodType() / 2]);
/*      */     }
/*  131 */     if (tileEntity instanceof TEFruitLeaves) {
/*  132 */       return fruitLeavesStack(accessor, config);
/*      */     }
/*  134 */     if (tileEntity instanceof TEFruitTreeWood) {
/*  135 */       return fruitTreeWoodStack(accessor, config);
/*      */     }
/*  137 */     if (tileEntity instanceof TEIngotPile) {
/*  138 */       return ingotPileStack(accessor, config);
/*      */     }
/*  140 */     if (tileEntity instanceof TELoom) {
/*  141 */       return loomStack(accessor, config);
/*      */     }
/*  143 */     if (tileEntity instanceof TEMetalSheet) {
/*  144 */       return metalSheetStack(accessor, config);
/*      */     }
/*  146 */     if (tileEntity instanceof TEMetalTrapDoor) {
/*  147 */       return metalTrapDoorStack(accessor, config);
/*      */     }
/*  149 */     if (tileEntity instanceof TEOilLamp) {
/*  150 */       return oilLampStack(accessor, config);
/*      */     }
/*  152 */     if (tileEntity instanceof TEOre) {
/*  153 */       return oreStack(accessor, config);
/*      */     }
/*  155 */     if (block instanceof BlockPartial) {
/*  156 */       return partialStack(accessor, config);
/*      */     }
/*  158 */     if (block instanceof BlockWaterPlant && TFC_Core.isSaltWater(world.func_147439_a(pos.field_72311_b, pos.field_72312_c + 1, pos.field_72309_d))) {
/*  159 */       return ItemFoodTFC.createTag(new ItemStack(TFCItems.seaWeed, 1, 0));
/*      */     }
/*  161 */     if (tileEntity instanceof TEWorldItem) {
/*  162 */       return worldItemStack(accessor, config);
/*      */     }
/*  164 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  170 */     Block block = accessor.getBlock();
/*  171 */     TileEntity tileEntity = accessor.getTileEntity();
/*      */     
/*  173 */     if (tileEntity instanceof TEBarrel) {
/*  174 */       currenttip = barrelHead(itemStack, currenttip, accessor, config);
/*      */     }
/*  176 */     else if (tileEntity instanceof TEFruitLeaves) {
/*  177 */       currenttip = fruitLeavesHead(itemStack, currenttip, accessor, config);
/*      */     }
/*  179 */     else if (tileEntity instanceof TEFruitTreeWood) {
/*  180 */       currenttip = fruitTreeWoodHead(itemStack, currenttip, accessor, config);
/*      */     }
/*  182 */     else if (tileEntity instanceof TEOre) {
/*  183 */       currenttip = oreHead(itemStack, currenttip, accessor, config);
/*      */     }
/*  185 */     else if (tileEntity instanceof TESmokeRack) {
/*  186 */       currenttip.set(0, EnumChatFormatting.WHITE.toString() + TFC_Core.translate("tile.SmokeRack.name"));
/*      */     }
/*  188 */     else if (block instanceof BlockWaterPlant) {
/*  189 */       currenttip = waterPlantHead(itemStack, currenttip, accessor, config);
/*      */     } 
/*  191 */     return currenttip;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  197 */     Block block = accessor.getBlock();
/*  198 */     TileEntity tileEntity = accessor.getTileEntity();
/*  199 */     if (tileEntity instanceof TEAnvil) {
/*  200 */       currenttip = anvilBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  202 */     else if (tileEntity instanceof TEBarrel) {
/*  203 */       currenttip = barrelBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  205 */     else if (tileEntity instanceof TEBerryBush) {
/*  206 */       currenttip = berryBushBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  208 */     else if (tileEntity instanceof TEBlastFurnace) {
/*  209 */       currenttip = blastFurnaceBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  211 */     else if (tileEntity instanceof TEBloom) {
/*  212 */       currenttip = bloomBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  214 */     else if (tileEntity instanceof TEBloomery) {
/*  215 */       currenttip = bloomeryBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  217 */     else if (tileEntity instanceof TECrop) {
/*  218 */       currenttip = cropBody(itemStack, currenttip, accessor, config);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  224 */     else if (tileEntity instanceof TEFirepit) {
/*  225 */       currenttip = firepitBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  227 */     else if (tileEntity instanceof TEForge) {
/*  228 */       currenttip = forgeBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  230 */     else if (tileEntity instanceof TEFruitLeaves) {
/*  231 */       currenttip = fruitLeavesBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  233 */     else if (tileEntity instanceof TELogPile) {
/*  234 */       currenttip = logPileBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  236 */     else if (tileEntity instanceof TELoom) {
/*  237 */       currenttip = loomBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  239 */     else if (tileEntity instanceof TEMetalTrapDoor) {
/*  240 */       currenttip = metalTrapDoorBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  242 */     else if (tileEntity instanceof TENestBox) {
/*  243 */       currenttip = nestBoxBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  245 */     else if (tileEntity instanceof TEOilLamp) {
/*  246 */       currenttip = oilLampBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  248 */     else if (tileEntity instanceof TEOre) {
/*  249 */       currenttip = oreBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  251 */     else if (tileEntity instanceof TEPottery) {
/*  252 */       currenttip = potteryBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  254 */     else if (tileEntity instanceof TESapling) {
/*  255 */       currenttip = saplingBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  257 */     else if (tileEntity instanceof TESluice) {
/*  258 */       currenttip = sluiceBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  260 */     else if (tileEntity instanceof TESmokeRack) {
/*  261 */       currenttip = smokeRackBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  263 */     else if (TFC_Core.isSoilWAILA(block)) {
/*  264 */       currenttip = soilBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  266 */     else if (tileEntity instanceof TESpawnMeter) {
/*  267 */       currenttip = spawnMeterBody(itemStack, currenttip, accessor, config);
/*      */     }
/*  269 */     else if (block == TFCBlocks.torch) {
/*  270 */       currenttip = torchBody(itemStack, currenttip, accessor, config);
/*      */     } 
/*  272 */     return currenttip;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  278 */     return currenttip;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, int x, int y, int z) {
/*  284 */     if (te != null)
/*  285 */       te.func_145841_b(tag); 
/*  286 */     return tag;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void callbackRegister(IWailaRegistrar reg) {
/*  291 */     reg.addConfig("TerraFirmaCraft", "tfc.oreQuality");
/*      */     
/*  293 */     reg.registerStackProvider(new WAILAData(), BlockAnvil.class);
/*  294 */     reg.registerBodyProvider(new WAILAData(), TEAnvil.class);
/*  295 */     reg.registerNBTProvider(new WAILAData(), TEAnvil.class);
/*      */     
/*  297 */     reg.registerHeadProvider(new WAILAData(), TEBarrel.class);
/*  298 */     reg.registerBodyProvider(new WAILAData(), TEBarrel.class);
/*  299 */     reg.registerNBTProvider(new WAILAData(), TEBarrel.class);
/*      */     
/*  301 */     reg.registerStackProvider(new WAILAData(), TEBerryBush.class);
/*  302 */     reg.registerBodyProvider(new WAILAData(), TEBerryBush.class);
/*  303 */     reg.registerNBTProvider(new WAILAData(), TEBerryBush.class);
/*      */     
/*  305 */     reg.registerBodyProvider(new WAILAData(), TEBlastFurnace.class);
/*  306 */     reg.registerNBTProvider(new WAILAData(), TEBlastFurnace.class);
/*      */     
/*  308 */     reg.registerStackProvider(new WAILAData(), TEBloom.class);
/*  309 */     reg.registerBodyProvider(new WAILAData(), TEBloom.class);
/*  310 */     reg.registerNBTProvider(new WAILAData(), TEBloom.class);
/*      */     
/*  312 */     reg.registerBodyProvider(new WAILAData(), TEBloomery.class);
/*  313 */     reg.registerNBTProvider(new WAILAData(), TEBloomery.class);
/*      */     
/*  315 */     reg.registerStackProvider(new WAILAData(), BlockCharcoal.class);
/*  316 */     reg.registerStackProvider(new WAILAData(), BlockClay.class);
/*  317 */     reg.registerStackProvider(new WAILAData(), BlockClayGrass.class);
/*  318 */     reg.registerStackProvider(new WAILAData(), BlockCobble.class);
/*      */     
/*  320 */     reg.registerStackProvider(new WAILAData(), TECrop.class);
/*  321 */     reg.registerBodyProvider(new WAILAData(), TECrop.class);
/*  322 */     reg.registerNBTProvider(new WAILAData(), TECrop.class);
/*      */     
/*  324 */     reg.registerStackProvider(new WAILAData(), BlockCustomDoor.class);
/*      */     
/*  326 */     reg.registerBodyProvider(new WAILAData(), TEFarmland.class);
/*  327 */     reg.registerNBTProvider(new WAILAData(), TEFarmland.class);
/*      */     
/*  329 */     reg.registerBodyProvider(new WAILAData(), TEFirepit.class);
/*  330 */     reg.registerNBTProvider(new WAILAData(), TEFirepit.class);
/*      */     
/*  332 */     reg.registerBodyProvider(new WAILAData(), TEForge.class);
/*  333 */     reg.registerNBTProvider(new WAILAData(), TEForge.class);
/*      */     
/*  335 */     reg.registerStackProvider(new WAILAData(), TEFruitLeaves.class);
/*  336 */     reg.registerHeadProvider(new WAILAData(), TEFruitLeaves.class);
/*  337 */     reg.registerBodyProvider(new WAILAData(), TEFruitLeaves.class);
/*  338 */     reg.registerNBTProvider(new WAILAData(), TEFruitLeaves.class);
/*      */     
/*  340 */     reg.registerStackProvider(new WAILAData(), TEFruitTreeWood.class);
/*  341 */     reg.registerHeadProvider(new WAILAData(), TEFruitTreeWood.class);
/*      */     
/*  343 */     reg.registerStackProvider(new WAILAData(), TEIngotPile.class);
/*  344 */     reg.registerHeadProvider(new WAILAData(), TEIngotPile.class);
/*  345 */     reg.registerNBTProvider(new WAILAData(), TEIngotPile.class);
/*      */     
/*  347 */     reg.registerBodyProvider(new WAILAData(), TELogPile.class);
/*  348 */     reg.registerNBTProvider(new WAILAData(), TELogPile.class);
/*      */     
/*  350 */     reg.registerStackProvider(new WAILAData(), TELoom.class);
/*  351 */     reg.registerBodyProvider(new WAILAData(), TELoom.class);
/*  352 */     reg.registerNBTProvider(new WAILAData(), TELoom.class);
/*      */     
/*  354 */     reg.registerStackProvider(new WAILAData(), TEMetalSheet.class);
/*  355 */     reg.registerNBTProvider(new WAILAData(), TEMetalSheet.class);
/*      */     
/*  357 */     reg.registerStackProvider(new WAILAData(), TEMetalTrapDoor.class);
/*  358 */     reg.registerBodyProvider(new WAILAData(), TEMetalTrapDoor.class);
/*  359 */     reg.registerNBTProvider(new WAILAData(), TEMetalTrapDoor.class);
/*      */     
/*  361 */     reg.registerBodyProvider(new WAILAData(), TENestBox.class);
/*  362 */     reg.registerNBTProvider(new WAILAData(), TENestBox.class);
/*      */     
/*  364 */     reg.registerStackProvider(new WAILAData(), TEOilLamp.class);
/*  365 */     reg.registerBodyProvider(new WAILAData(), TEOilLamp.class);
/*  366 */     reg.registerNBTProvider(new WAILAData(), TEOilLamp.class);
/*      */     
/*  368 */     reg.registerStackProvider(new WAILAData(), TEOre.class);
/*  369 */     reg.registerHeadProvider(new WAILAData(), TEOre.class);
/*  370 */     reg.registerBodyProvider(new WAILAData(), TEOre.class);
/*      */     
/*  372 */     reg.registerStackProvider(new WAILAData(), BlockPartial.class);
/*  373 */     reg.registerNBTProvider(new WAILAData(), BlockPartial.class);
/*      */     
/*  375 */     reg.registerBodyProvider(new WAILAData(), TEPottery.class);
/*  376 */     reg.registerNBTProvider(new WAILAData(), TEPottery.class);
/*      */     
/*  378 */     reg.registerBodyProvider(new WAILAData(), TESapling.class);
/*  379 */     reg.registerNBTProvider(new WAILAData(), TESapling.class);
/*      */     
/*  381 */     reg.registerBodyProvider(new WAILAData(), TESluice.class);
/*  382 */     reg.registerNBTProvider(new WAILAData(), TESluice.class);
/*      */     
/*  384 */     reg.registerHeadProvider(new WAILAData(), TESmokeRack.class);
/*  385 */     reg.registerBodyProvider(new WAILAData(), TESmokeRack.class);
/*  386 */     reg.registerNBTProvider(new WAILAData(), TESmokeRack.class);
/*      */     
/*  388 */     reg.registerBodyProvider(new WAILAData(), TESpawnMeter.class);
/*  389 */     reg.registerNBTProvider(new WAILAData(), TESpawnMeter.class);
/*      */ 
/*      */     
/*  392 */     reg.registerBodyProvider(new WAILAData(), BlockDirt.class);
/*  393 */     reg.registerBodyProvider(new WAILAData(), BlockSand.class);
/*  394 */     reg.registerBodyProvider(new WAILAData(), BlockGrass.class);
/*  395 */     reg.registerBodyProvider(new WAILAData(), BlockGravel.class);
/*      */     
/*  397 */     reg.registerBodyProvider(new WAILAData(), BlockTorch.class);
/*  398 */     reg.registerNBTProvider(new WAILAData(), BlockTorch.class);
/*      */     
/*  400 */     reg.registerStackProvider(new WAILAData(), BlockWaterPlant.class);
/*  401 */     reg.registerHeadProvider(new WAILAData(), BlockWaterPlant.class);
/*      */     
/*  403 */     reg.registerStackProvider(new WAILAData(), TEWorldItem.class);
/*  404 */     reg.registerNBTProvider(new WAILAData(), TEWorldItem.class);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack anvilStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  410 */     Block block = accessor.getBlock();
/*  411 */     int meta = accessor.getMetadata();
/*  412 */     int type = BlockAnvil.getAnvilTypeFromMeta(meta);
/*      */     
/*  414 */     return new ItemStack(block, 1, type);
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack berryBushStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  419 */     NBTTagCompound tag = accessor.getNBTData();
/*  420 */     boolean hasFruit = tag.func_74767_n("hasFruit");
/*  421 */     FloraIndex index = FloraManager.getInstance().findMatchingIndex(BlockBerryBush.metaNames[accessor.getMetadata()]);
/*      */     
/*  423 */     if (hasFruit) {
/*  424 */       return ItemFoodTFC.createTag(index.getOutput());
/*      */     }
/*  426 */     return null;
/*      */   }
/*      */   
/*      */   public ItemStack cropStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*      */     ItemStack itemstack;
/*  431 */     NBTTagCompound tag = accessor.getNBTData();
/*  432 */     int cropId = tag.func_74762_e("cropId");
/*      */     
/*  434 */     CropIndex crop = CropManager.getInstance().getCropFromId(cropId);
/*      */ 
/*      */     
/*  437 */     if (crop.output2 != null) {
/*  438 */       itemstack = new ItemStack(crop.output2);
/*      */     } else {
/*  440 */       itemstack = new ItemStack(crop.output1);
/*      */     } 
/*  442 */     ItemFoodTFC.createTag(itemstack);
/*  443 */     return itemstack;
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack fruitLeavesStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  448 */     FloraIndex index = FloraManager.getInstance().findMatchingIndex(BlockFruitLeaves.getType(accessor.getBlock(), accessor.getMetadata() % 8));
/*      */     
/*  450 */     if (index != null) {
/*  451 */       return ItemFoodTFC.createTag(index.getOutput());
/*      */     }
/*  453 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack fruitTreeWoodStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  458 */     FloraIndex index = FloraManager.getInstance().findMatchingIndex(BlockFruitWood.getType(accessor.getMetadata()));
/*      */     
/*  460 */     if (index != null) {
/*  461 */       return ItemFoodTFC.createTag(index.getOutput());
/*      */     }
/*  463 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack ingotPileStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  468 */     NBTTagCompound tag = accessor.getNBTData();
/*  469 */     ItemStack[] storage = getStorage(tag, accessor.getTileEntity());
/*      */     
/*  471 */     if (storage[0] != null) {
/*  472 */       return storage[0];
/*      */     }
/*  474 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack loomStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  479 */     NBTTagCompound tag = accessor.getNBTData();
/*  480 */     boolean finished = tag.func_74767_n("finished");
/*  481 */     ItemStack[] storage = getStorage(tag, accessor.getTileEntity());
/*      */     
/*  483 */     if (finished && storage[1] != null)
/*      */     {
/*  485 */       return storage[1];
/*      */     }
/*  487 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack metalSheetStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  492 */     NBTTagCompound tag = accessor.getNBTData();
/*  493 */     return ItemStack.func_77949_a(tag.func_74775_l("sheetType"));
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack metalTrapDoorStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  498 */     NBTTagCompound tag = accessor.getNBTData();
/*  499 */     return ItemStack.func_77949_a(tag.func_74775_l("sheetType"));
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack oilLampStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  504 */     int meta = accessor.getMetadata();
/*  505 */     if ((meta & 0x8) != 0) {
/*  506 */       meta -= 8;
/*      */     }
/*  508 */     return new ItemStack(TFCBlocks.oilLamp, 1, meta);
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack oreStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  513 */     int meta = accessor.getMetadata();
/*  514 */     TEOre te = (TEOre)accessor.getTileEntity();
/*  515 */     ItemStack itemstack = null;
/*      */     
/*  517 */     if (accessor.getBlock() == TFCBlocks.ore) {
/*      */       
/*  519 */       if (config.getConfig("tfc.oreQuality")) {
/*  520 */         itemstack = new ItemStack(TFCItems.oreChunk, 1, getOreGrade(te, meta));
/*      */       } else {
/*  522 */         itemstack = new ItemStack(TFCItems.oreChunk, 1, meta);
/*      */       } 
/*  524 */       if (meta == 14 || meta == 15) {
/*  525 */         itemstack = new ItemStack(TFCItems.coal);
/*      */       }
/*  527 */       return itemstack;
/*      */     } 
/*  529 */     if (accessor.getBlock() == TFCBlocks.ore2) {
/*      */       
/*  531 */       itemstack = new ItemStack(TFCItems.oreChunk, 1, meta + Global.ORE_METAL.length);
/*  532 */       if (meta == 5) {
/*  533 */         itemstack = new ItemStack(TFCItems.gemDiamond);
/*  534 */       } else if (meta == 13) {
/*  535 */         itemstack = new ItemStack(TFCItems.powder, 1, 4);
/*      */       } 
/*  537 */       return itemstack;
/*      */     } 
/*  539 */     if (accessor.getBlock() == TFCBlocks.ore3) {
/*      */       
/*  541 */       itemstack = new ItemStack(TFCItems.oreChunk, 1, meta + Global.ORE_METAL.length + Global.ORE_MINERAL.length);
/*  542 */       return itemstack;
/*      */     } 
/*      */     
/*  545 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack partialStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  550 */     NBTTagCompound tag = accessor.getNBTData();
/*  551 */     byte metaID = tag.func_74771_c("metaID");
/*  552 */     int typeID = tag.func_74765_d("typeID");
/*      */     
/*  554 */     return new ItemStack(Block.func_149729_e(typeID), 1, metaID);
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack worldItemStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  559 */     NBTTagCompound tag = accessor.getNBTData();
/*  560 */     ItemStack[] storage = getStorage(tag, accessor.getTileEntity());
/*  561 */     return storage[0];
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List<String> barrelHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  567 */     String head = currenttip.get(0);
/*  568 */     NBTTagCompound tag = accessor.getNBTData();
/*  569 */     FluidStack fluid = FluidStack.loadFluidStackFromNBT(tag.func_74775_l("fluidNBT"));
/*      */     
/*  571 */     if (fluid != null) {
/*      */       
/*  573 */       head = head + " (" + fluid.getLocalizedName() + ")";
/*  574 */       currenttip.set(0, head);
/*      */     } 
/*  576 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> fruitLeavesHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  581 */     NBTTagCompound tag = accessor.getNBTData();
/*  582 */     boolean hasFruit = tag.func_74767_n("hasFruit");
/*  583 */     String type = BlockFruitLeaves.getType(accessor.getBlock(), accessor.getMetadata());
/*      */     
/*  585 */     if (!hasFruit) {
/*  586 */       currenttip.set(0, EnumChatFormatting.WHITE.toString() + TFC_Core.translate("gui." + type));
/*      */     }
/*  588 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> fruitTreeWoodHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  593 */     String type = BlockFruitWood.getType(accessor.getMetadata());
/*      */     
/*  595 */     currenttip.set(0, EnumChatFormatting.WHITE.toString() + TFC_Core.translate("gui." + type));
/*      */     
/*  597 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> ingotPileHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  602 */     String head = currenttip.get(0);
/*  603 */     currenttip.set(0, head + " " + TFC_Core.translate("gui.pile"));
/*  604 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> oreHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  609 */     int meta = accessor.getMetadata();
/*      */     
/*  611 */     if (accessor.getBlock() == TFCBlocks.ore) {
/*      */       
/*  613 */       if (meta == 14)
/*      */       {
/*  615 */         currenttip.set(0, EnumChatFormatting.WHITE.toString() + TFC_Core.translate("item.Ore.Bituminous Coal.name"));
/*      */       }
/*  617 */       else if (meta == 15)
/*      */       {
/*  619 */         currenttip.set(0, EnumChatFormatting.WHITE.toString() + TFC_Core.translate("item.Ore.Lignite.name"));
/*      */       }
/*      */     
/*  622 */     } else if (accessor.getBlock() == TFCBlocks.ore2) {
/*      */       
/*  624 */       if (meta == 5) {
/*      */         
/*  626 */         currenttip.set(0, EnumChatFormatting.WHITE.toString() + TFC_Core.translate("item.Ore.Kimberlite.name"));
/*      */       }
/*  628 */       else if (meta == 13) {
/*      */         
/*  630 */         currenttip.set(0, EnumChatFormatting.WHITE.toString() + TFC_Core.translate("item.Ore.Saltpeter.name"));
/*      */       } 
/*      */     } 
/*      */     
/*  634 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> waterPlantHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  639 */     MovingObjectPosition pos = accessor.getPosition();
/*  640 */     World world = accessor.getWorld();
/*  641 */     Block blockDirectlyAbove = world.func_147439_a(pos.field_72311_b, pos.field_72312_c + 1, pos.field_72309_d);
/*  642 */     boolean airTwoAbove = world.func_147437_c(pos.field_72311_b, pos.field_72312_c + 2, pos.field_72309_d);
/*      */     
/*  644 */     if (TFC_Core.isFreshWater(blockDirectlyAbove))
/*      */     {
/*  646 */       if (airTwoAbove) {
/*      */         
/*  648 */         currenttip.set(0, EnumChatFormatting.WHITE.toString() + TFC_Core.translate("tile.Flora.Cat Tails.name"));
/*      */       }
/*      */       else {
/*      */         
/*  652 */         currenttip.set(0, EnumChatFormatting.WHITE.toString() + TFC_Core.translate("tile.Flora.Pond Weed.name"));
/*      */       } 
/*      */     }
/*      */     
/*  656 */     return currenttip;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List<String> anvilBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  662 */     NBTTagCompound tag = accessor.getNBTData();
/*      */     
/*  664 */     int tier = tag.func_74762_e("Tier");
/*  665 */     currenttip.add(TFC_Core.translate("gui.tier") + " : " + tier);
/*      */     
/*  667 */     ItemStack[] storage = getStorage(tag, accessor.getTileEntity());
/*  668 */     ItemStack flux = storage[6];
/*      */     
/*  670 */     if (flux != null && flux.func_77973_b() == TFCItems.powder && flux.func_77960_j() == 0 && flux.field_77994_a > 0) {
/*  671 */       currenttip.add(TFC_Core.translate("item.Powder.Flux.name") + " : " + flux.field_77994_a);
/*      */     }
/*  673 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> barrelBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  678 */     TEBarrel te = (TEBarrel)accessor.getTileEntity();
/*  679 */     NBTTagCompound tag = accessor.getNBTData();
/*  680 */     ItemStack[] storage = getStorage(tag, (TileEntity)te);
/*  681 */     ItemStack inStack = storage[0];
/*      */     
/*  683 */     Boolean sealed = Boolean.valueOf(te.getSealed());
/*  684 */     int sealTime = accessor.getNBTInteger(tag, "SealTime");
/*  685 */     FluidStack fluid = FluidStack.loadFluidStackFromNBT(tag.func_74775_l("fluidNBT"));
/*  686 */     BarrelRecipe recipe = BarrelManager.getInstance().findMatchingRecipe(inStack, fluid, sealed.booleanValue(), te.getTechLevel());
/*      */     
/*  688 */     if (sealed.booleanValue() && fluid != null && fluid.getFluid() == TFCFluids.MILKCURDLED && (inStack == null || (inStack
/*  689 */       .func_77973_b() instanceof IFood && ((IFood)inStack.func_77973_b()).getFoodGroup() != EnumFoodGroup.Dairy && ((IFood)inStack.func_77973_b()).isEdible(inStack) && Food.getWeight(inStack) <= 20.0F))) {
/*  690 */       recipe = (new BarrelRecipe(null, new FluidStack(TFCFluids.MILKCURDLED, 10000), ItemFoodTFC.createTag(new ItemStack(TFCItems.cheese, 1), 160.0F), null)).setMinTechLevel(0);
/*      */     }
/*      */     
/*  693 */     if (fluid != null)
/*      */     {
/*  695 */       currenttip.add(fluid.amount + "/" + te.getMaxLiquid() + " mB");
/*      */     }
/*      */ 
/*      */     
/*  699 */     if (sealed.booleanValue() && sealTime != 0)
/*      */     {
/*  701 */       currenttip.add(TFC_Core.translate("gui.Barrel.SealedOn") + " : " + TFC_Time.getDateStringFromHours(sealTime));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  706 */     BarrelPreservativeRecipe preservative = BarrelManager.getInstance().findMatchingPreservativeRepice(te, inStack, fluid, sealed.booleanValue());
/*      */ 
/*      */     
/*  709 */     if (recipe != null) {
/*      */       
/*  711 */       if (!(recipe instanceof com.bioxx.tfc.api.Crafting.BarrelBriningRecipe))
/*      */       {
/*  713 */         currenttip.add(TFC_Core.translate("gui.Output") + " : " + recipe.getRecipeName());
/*      */       }
/*  715 */       else if (sealed.booleanValue() && fluid != null && fluid.getFluid() == TFCFluids.BRINE)
/*      */       {
/*  717 */         if (inStack != null && inStack.func_77973_b() instanceof IFood && (((IFood)inStack.func_77973_b()).getFoodGroup() == EnumFoodGroup.Fruit || ((IFood)inStack.func_77973_b()).getFoodGroup() == EnumFoodGroup.Vegetable || ((IFood)inStack.func_77973_b()).getFoodGroup() == EnumFoodGroup.Protein || (IFood)inStack.func_77973_b() == TFCItems.cheese) && !Food.isBrined(inStack))
/*      */         {
/*  719 */           currenttip.add(TFC_Core.translate("gui.barrel.brining"));
/*      */         }
/*      */       }
/*      */     
/*  723 */     } else if (sealed.booleanValue() && fluid != null && inStack != null && inStack.func_77973_b() instanceof IFood && fluid.getFluid() == TFCFluids.VINEGAR) {
/*      */       
/*  725 */       if (!Food.isPickled(inStack) && Food.getWeight(inStack) / fluid.amount <= 160.0F / te.getMaxLiquid()) {
/*      */         
/*  727 */         if ((((IFood)inStack.func_77973_b()).getFoodGroup() == EnumFoodGroup.Fruit || ((IFood)inStack.func_77973_b()).getFoodGroup() == EnumFoodGroup.Vegetable || ((IFood)inStack.func_77973_b()).getFoodGroup() == EnumFoodGroup.Protein || (IFood)inStack.func_77973_b() == TFCItems.cheese) && Food.isBrined(inStack))
/*      */         {
/*  729 */           currenttip.add(TFC_Core.translate("gui.barrel.pickling"));
/*      */         }
/*      */       }
/*  732 */       else if (Food.isPickled(inStack) && Food.getWeight(inStack) / fluid.amount <= 160.0F / te.getMaxLiquid() * 2.0F) {
/*      */         
/*  734 */         currenttip.add(TFC_Core.translate("gui.barrel.preserving"));
/*      */       } 
/*  736 */     } else if (preservative != null) {
/*  737 */       currenttip.add(TFC_Core.translate(preservative.getPreservingString()));
/*      */     } 
/*      */     
/*  740 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> berryBushBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  745 */     FloraIndex index = FloraManager.getInstance().findMatchingIndex(BlockBerryBush.metaNames[accessor.getMetadata()]);
/*  746 */     currenttip.add(TFC_Time.SEASONS[index.harvestStart] + " - " + TFC_Time.SEASONS[index.harvestFinish]);
/*  747 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> blastFurnaceBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  752 */     TEBlastFurnace te = (TEBlastFurnace)accessor.getTileEntity();
/*  753 */     NBTTagCompound tag = accessor.getNBTData();
/*      */     
/*  755 */     int charcoalCount = tag.func_74762_e("charcoalCount");
/*  756 */     int oreCount = tag.func_74771_c("oreCount");
/*  757 */     int stackSize = tag.func_74762_e("maxValidStackSize");
/*  758 */     float temperature = 0.0F;
/*      */     
/*  760 */     ItemStack[] storage = getStorage(tag, (TileEntity)te);
/*  761 */     ItemStack oreStack = storage[0];
/*      */     
/*  763 */     HeatRegistry manager = HeatRegistry.getInstance();
/*      */     
/*  765 */     if (oreStack != null) {
/*      */       
/*  767 */       HeatIndex index = manager.findMatchingIndex(oreStack);
/*  768 */       if (index != null)
/*      */       {
/*  770 */         temperature = TFC_ItemHeat.getTemp(oreStack);
/*      */       }
/*      */     } 
/*  773 */     String temp = TFC_ItemHeat.getHeatColor(temperature, te.maxFireTempScale);
/*      */     
/*  775 */     currenttip.add(TFC_Core.translate("gui.Bloomery.Charcoal") + " : " + charcoalCount + "/" + (stackSize * 4));
/*  776 */     currenttip.add(TFC_Core.translate("gui.Bloomery.Ore") + " : " + oreCount + "/" + (stackSize * 4));
/*      */     
/*  778 */     if (te.storage[1] != null) {
/*  779 */       currenttip.add(TFC_Core.translate("gui.plans.tuyere") + EnumChatFormatting.GREEN.toString() + " ✔");
/*      */     } else {
/*  781 */       currenttip.add(TFC_Core.translate("gui.plans.tuyere") + EnumChatFormatting.RED.toString() + " ✘");
/*      */     } 
/*  783 */     if (temperature > 0.0F)
/*      */     {
/*  785 */       currenttip.add(temp);
/*      */     }
/*      */     
/*  788 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> bloomBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  793 */     NBTTagCompound tag = accessor.getNBTData();
/*  794 */     int size = tag.func_74762_e("size");
/*      */     
/*  796 */     currenttip.add(TFC_Core.translate("gui.units") + " : " + size);
/*  797 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> bloomeryBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  802 */     NBTTagCompound tag = accessor.getNBTData();
/*  803 */     boolean isLit = tag.func_74767_n("isLit");
/*  804 */     int charcoalCount = tag.func_74762_e("charcoalCount");
/*  805 */     int oreCount = tag.func_74762_e("oreCount");
/*      */     
/*  807 */     currenttip.add(TFC_Core.translate("gui.Blast.Charcoal") + " : " + charcoalCount);
/*      */     
/*  809 */     currenttip.add(TFC_Core.translate("gui.Bloomery.Ore") + " : " + oreCount);
/*      */     
/*  811 */     if (isLit) {
/*      */       
/*  813 */       long hours = tag.func_74763_f("fuelTimeLeft") / 1000L - TFC_Time.getTotalHours();
/*      */       
/*  815 */       if (hours > 0L) {
/*      */         
/*  817 */         float percent = Helper.roundNumber(Math.min(100.0F - (float)hours / TFCOptions.bloomeryBurnTime * 100.0F, 100.0F), 10.0F);
/*  818 */         currenttip.add(hours + " " + TFC_Core.translate("gui.hoursRemaining") + " (" + percent + "%)");
/*      */       } 
/*      */     } 
/*      */     
/*  822 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> cropBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  827 */     NBTTagCompound tag = accessor.getNBTData();
/*  828 */     float growth = tag.func_74760_g("growth");
/*  829 */     int cropId = tag.func_74762_e("cropId");
/*      */     
/*  831 */     CropIndex crop = CropManager.getInstance().getCropFromId(cropId);
/*  832 */     int percentGrowth = (int)Math.min(growth / crop.numGrowthStages * 100.0F, 100.0F);
/*      */     
/*  834 */     if (percentGrowth < 100) {
/*  835 */       currenttip.add(TFC_Core.translate("gui.growth") + " : " + percentGrowth + "%");
/*      */     } else {
/*  837 */       currenttip.add(TFC_Core.translate("gui.growth") + " : " + TFC_Core.translate("gui.mature"));
/*      */     } 
/*  839 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> farmlandBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  844 */     SkillStats.SkillRank rank = TFC_Core.getSkillStats(accessor.getPlayer()).getSkillRank("skill.agriculture");
/*  845 */     if (rank == SkillStats.SkillRank.Expert || rank == SkillStats.SkillRank.Master) {
/*      */       
/*  847 */       TEFarmland te = (TEFarmland)accessor.getTileEntity();
/*  848 */       NBTTagCompound tag = accessor.getNBTData();
/*      */       
/*  850 */       int[] nutrients = tag.func_74759_k("nutrients");
/*  851 */       int soilMax = te.getSoilMax();
/*      */       
/*  853 */       for (int i = 0; i < nutrients.length; i++) {
/*      */         
/*  855 */         int percent = Math.max(nutrients[i] * 100 / soilMax, 0);
/*      */         
/*  857 */         if (i == 0) {
/*  858 */           currenttip.add(EnumChatFormatting.RED + TFC_Core.translate("gui.Nutrient.A") + " : " + percent + "%");
/*  859 */         } else if (i == 1) {
/*  860 */           currenttip.add(EnumChatFormatting.GOLD + TFC_Core.translate("gui.Nutrient.B") + " : " + percent + "%");
/*  861 */         } else if (i == 2) {
/*  862 */           currenttip.add(EnumChatFormatting.YELLOW + TFC_Core.translate("gui.Nutrient.C") + " : " + percent + "%");
/*  863 */         } else if (i == 3 && percent != 0) {
/*  864 */           currenttip.add(EnumChatFormatting.WHITE + TFC_Core.translate("item.Fertilizer.name") + " : " + percent + "%");
/*      */         } 
/*      */       } 
/*      */     } 
/*  868 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> firepitBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  873 */     NBTTagCompound tag = accessor.getNBTData();
/*  874 */     ItemStack[] storage = getStorage(tag, accessor.getTileEntity());
/*      */     
/*  876 */     if (storage != null) {
/*      */       
/*  878 */       int fuelCount = 0;
/*  879 */       for (ItemStack is : storage) {
/*      */         
/*  881 */         if (is != null && is.func_77973_b() != null && (is.func_77973_b() == TFCItems.logs || is.func_77973_b() == Item.func_150898_a(TFCBlocks.peat))) {
/*  882 */           fuelCount++;
/*      */         }
/*      */       } 
/*  885 */       if (fuelCount > 0) {
/*  886 */         currenttip.add(TFC_Core.translate("gui.fuel") + " : " + fuelCount + "/4");
/*      */       }
/*      */     } 
/*  889 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> forgeBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  894 */     NBTTagCompound tag = accessor.getNBTData();
/*  895 */     ItemStack[] storage = getStorage(tag, accessor.getTileEntity());
/*      */     
/*  897 */     if (storage != null) {
/*      */       
/*  899 */       int fuelCount = 0;
/*  900 */       boolean hasMold = false;
/*      */       
/*  902 */       for (int i = 5; i <= 9; i++) {
/*      */         
/*  904 */         if (storage[i] != null && storage[i].func_77973_b() != null && storage[i].func_77973_b() instanceof com.bioxx.tfc.Items.ItemCoal) {
/*  905 */           fuelCount++;
/*      */         }
/*      */       } 
/*  908 */       if (fuelCount > 0) {
/*  909 */         currenttip.add(TFC_Core.translate("gui.fuel") + " : " + fuelCount + "/5");
/*      */       }
/*  911 */       for (int j = 10; j <= 13; j++) {
/*      */         
/*  913 */         if (storage[j] != null && storage[j].func_77973_b() == TFCItems.ceramicMold && (storage[j]).field_77994_a > 0)
/*  914 */           hasMold = true; 
/*      */       } 
/*  916 */       if (hasMold) {
/*  917 */         currenttip.add(TFC_Core.translate("item.Mold.Ceramic Mold.name") + EnumChatFormatting.GREEN.toString() + " ✔");
/*      */       } else {
/*  919 */         currenttip.add(TFC_Core.translate("item.Mold.Ceramic Mold.name") + EnumChatFormatting.RED.toString() + " ✘");
/*      */       } 
/*      */     } 
/*  922 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> fruitLeavesBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  927 */     FloraIndex index = FloraManager.getInstance().findMatchingIndex(BlockFruitLeaves.getType(accessor.getBlock(), accessor.getMetadata()));
/*  928 */     if (index != null)
/*  929 */       currenttip.add(TFC_Time.SEASONS[index.harvestStart] + " - " + TFC_Time.SEASONS[index.harvestFinish]); 
/*  930 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> logPileBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  935 */     NBTTagCompound tag = accessor.getNBTData();
/*  936 */     Boolean isOnFire = Boolean.valueOf(tag.func_74767_n("isOnFire"));
/*      */     
/*  938 */     if (isOnFire.booleanValue()) {
/*      */       
/*  940 */       int fireTimer = tag.func_74762_e("fireTimer");
/*  941 */       int hours = (int)(TFCOptions.charcoalPitBurnTime - (float)(TFC_Time.getTotalHours() - fireTimer));
/*  942 */       currenttip.add(hours + " " + TFC_Core.translate("gui.hoursRemaining") + " (" + Helper.roundNumber(100.0F - hours / TFCOptions.charcoalPitBurnTime * 100.0F, 10.0F) + "%)");
/*      */     }
/*      */     else {
/*      */       
/*  946 */       ItemStack[] storage = getStorage(tag, accessor.getTileEntity());
/*  947 */       boolean[] counted = { false, false, false, false };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  954 */       for (int j = 0; j < storage.length; j++) {
/*      */         
/*  956 */         if (storage[j] != null && !counted[j]) {
/*      */           
/*  958 */           String log = storage[j].func_82833_r() + " : ";
/*  959 */           int count = (storage[j]).field_77994_a;
/*  960 */           for (int k = j + 1; k < storage.length; k++) {
/*      */             
/*  962 */             if (storage[k] != null && storage[j].func_77969_a(storage[k])) {
/*      */               
/*  964 */               count += (storage[k]).field_77994_a;
/*  965 */               counted[k] = true;
/*      */             } 
/*      */           } 
/*  968 */           currenttip.add(log + count);
/*  969 */           counted[j] = true;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  974 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> loomBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  979 */     NBTTagCompound tag = accessor.getNBTData();
/*  980 */     boolean finished = tag.func_74767_n("finished");
/*  981 */     int wovenStrings = tag.func_74762_e("cloth");
/*  982 */     ItemStack[] storage = getStorage(tag, accessor.getTileEntity());
/*      */     
/*  984 */     if (!finished && storage[0] != null) {
/*      */       
/*  986 */       LoomRecipe recipe = LoomManager.getInstance().findPotentialRecipes(storage[0]);
/*  987 */       int maxStrings = recipe.getReqSize();
/*      */       
/*  989 */       if ((storage[0]).field_77994_a < maxStrings) {
/*      */         
/*  991 */         String name = storage[0].func_82833_r() + " : ";
/*  992 */         currenttip.add(name + (storage[0]).field_77994_a + "/" + maxStrings);
/*      */       }
/*      */       else {
/*      */         
/*  996 */         String name = recipe.getOutItemStack().func_82833_r() + " : ";
/*  997 */         int percent = (int)(100.0D * wovenStrings / maxStrings);
/*  998 */         currenttip.add(TFC_Core.translate("gui.weaving") + " " + name + percent + "%");
/*      */       } 
/*      */     } 
/*      */     
/* 1002 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> metalTrapDoorBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/* 1007 */     NBTTagCompound tag = accessor.getNBTData();
/* 1008 */     ItemStack sheetStack = ItemStack.func_77949_a(tag.func_74775_l("sheetType"));
/*      */     
/* 1010 */     String metalType = BlockMetalTrapDoor.metalNames[sheetStack.func_77960_j() & 0x1F];
/* 1011 */     currenttip.add(TFC_Core.translate("gui.metal." + metalType.replaceAll("\\s", "")));
/* 1012 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> nestBoxBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/* 1017 */     NBTTagCompound tag = accessor.getNBTData();
/* 1018 */     ItemStack[] storage = getStorage(tag, accessor.getTileEntity());
/* 1019 */     int eggCount = 0, fertEggCount = 0;
/*      */     
/* 1021 */     for (ItemStack is : storage) {
/*      */       
/* 1023 */       if (is != null && is.func_77973_b() == TFCItems.egg)
/*      */       {
/* 1025 */         if (is.func_77942_o() && is.func_77978_p().func_74764_b("Fertilized")) {
/* 1026 */           fertEggCount++;
/*      */         } else {
/* 1028 */           eggCount++;
/*      */         } 
/*      */       }
/*      */     } 
/* 1032 */     if (eggCount > 0)
/* 1033 */       currenttip.add(TFC_Core.translate("gui.eggs") + " : " + eggCount); 
/* 1034 */     if (fertEggCount > 0) {
/* 1035 */       currenttip.add(TFC_Core.translate("gui.fertEggs") + " : " + fertEggCount);
/*      */     }
/* 1037 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> oilLampBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/* 1042 */     NBTTagCompound tag = accessor.getNBTData();
/* 1043 */     if (tag.func_74764_b("Fuel")) {
/*      */       
/* 1045 */       FluidStack fuel = FluidStack.loadFluidStackFromNBT(tag.func_74775_l("Fuel"));
/* 1046 */       int hours = fuel.amount * TFCOptions.oilLampFuelMult / 8;
/* 1047 */       if (fuel.getFluid() == TFCFluids.OLIVEOIL) {
/* 1048 */         currenttip.add(hours + " " + TFC_Core.translate("gui.hoursRemaining") + " (" + Helper.roundNumber(hours / 250.0F * TFCOptions.oilLampFuelMult * 100.0F, 10.0F) + "%)");
/* 1049 */       } else if (fuel.getFluid() == TFCFluids.LAVA) {
/* 1050 */         currenttip.add(TFC_Core.translate("gui.infinite") + " " + TFC_Core.translate("gui.hoursRemaining"));
/*      */       } 
/* 1052 */     }  return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> oreBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/* 1057 */     int meta = accessor.getMetadata();
/*      */     
/* 1059 */     if (accessor.getBlock() == TFCBlocks.ore) {
/*      */       
/* 1061 */       switch (meta) {
/*      */         
/*      */         case 0:
/*      */         case 9:
/*      */         case 13:
/* 1066 */           currenttip.add(TFC_Core.translate("gui.metal.Copper"));
/*      */           break;
/*      */         case 1:
/* 1069 */           currenttip.add(TFC_Core.translate("gui.metal.Gold"));
/*      */           break;
/*      */         case 2:
/* 1072 */           currenttip.add(TFC_Core.translate("gui.metal.Platinum") + " - " + TFC_Core.translate("gui.useless"));
/*      */           break;
/*      */         case 3:
/*      */         case 10:
/*      */         case 11:
/* 1077 */           currenttip.add(TFC_Core.translate("gui.metal.Iron"));
/*      */           break;
/*      */         case 4:
/* 1080 */           currenttip.add(TFC_Core.translate("gui.metal.Silver"));
/*      */           break;
/*      */         case 5:
/* 1083 */           currenttip.add(TFC_Core.translate("gui.metal.Tin"));
/*      */           break;
/*      */         case 6:
/* 1086 */           currenttip.add(TFC_Core.translate("gui.metal.Lead") + " - " + TFC_Core.translate("gui.useless"));
/*      */           break;
/*      */         case 7:
/* 1089 */           currenttip.add(TFC_Core.translate("gui.metal.Bismuth"));
/*      */           break;
/*      */         case 8:
/* 1092 */           currenttip.add(TFC_Core.translate("gui.metal.Nickel"));
/*      */           break;
/*      */         case 12:
/* 1095 */           currenttip.add(TFC_Core.translate("gui.metal.Zinc"));
/*      */           break;
/*      */         case 14:
/*      */         case 15:
/* 1099 */           currenttip.add(TFC_Core.translate("item.coal.coal.name"));
/* 1100 */           return currenttip;
/*      */       } 
/*      */       
/* 1103 */       if (config.getConfig("tfc.oreQuality"))
/*      */       {
/* 1105 */         TEOre te = (TEOre)accessor.getTileEntity();
/*      */         
/* 1107 */         int ore = getOreGrade(te, meta);
/*      */         
/* 1109 */         int units = (ore < 14) ? TFCOptions.normalOreUnits : ((ore < 49) ? TFCOptions.richOreUnits : ((ore < 63) ? TFCOptions.poorOreUnits : 0));
/* 1110 */         if (units > 0) {
/* 1111 */           currenttip.add(TFC_Core.translate("gui.units") + " : " + units);
/*      */         }
/*      */       }
/*      */     
/* 1115 */     } else if (accessor.getBlock() == TFCBlocks.ore2) {
/*      */       
/* 1117 */       switch (meta) {
/*      */         
/*      */         case 1:
/*      */         case 2:
/*      */         case 3:
/*      */         case 6:
/*      */         case 8:
/*      */         case 9:
/*      */         case 10:
/*      */         case 14:
/* 1127 */           currenttip.add(TFC_Core.translate("gui.useless"));
/*      */           break;
/*      */         case 5:
/* 1130 */           currenttip.add(TFC_Core.translate("item.Diamond.Normal.name"));
/*      */           break;
/*      */         case 11:
/*      */         case 12:
/* 1134 */           currenttip.add(TFC_Core.translate("item.redstone.name"));
/*      */           break;
/*      */         case 15:
/* 1137 */           currenttip.add(TFC_Core.translate("item.Fertilizer.name"));
/*      */           break;
/*      */       } 
/*      */     
/* 1141 */     } else if (accessor.getBlock() == TFCBlocks.ore3) {
/*      */       
/* 1143 */       switch (meta) {
/*      */         
/*      */         case 0:
/* 1146 */           currenttip.add(TFC_Core.translate("item.Powder.Flux.name"));
/*      */           break;
/*      */         case 1:
/* 1149 */           currenttip.add(TFC_Core.translate("gui.useless"));
/*      */           break;
/*      */       } 
/*      */     
/*      */     } 
/* 1154 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> potteryBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/* 1159 */     NBTTagCompound tag = accessor.getNBTData();
/* 1160 */     long burnStart = tag.func_74763_f("burnStart");
/* 1161 */     int wood = tag.func_74762_e("wood");
/* 1162 */     int straw = tag.func_74762_e("straw");
/*      */     
/* 1164 */     if (straw > 0 && straw < 8) {
/* 1165 */       currenttip.add(TFC_Core.translate("item.Straw.name") + " : " + straw + "/8");
/* 1166 */     } else if (wood > 0 && wood < 8) {
/* 1167 */       currenttip.add(TFC_Core.translate("gui.logs") + " : " + wood + "/8");
/* 1168 */     } else if (burnStart > 0L) {
/*      */       
/* 1170 */       int hours = (int)(TFCOptions.pitKilnBurnTime - (float)(TFC_Time.getTotalHours() - burnStart / 1000L));
/* 1171 */       currenttip.add(hours + " " + TFC_Core.translate("gui.hoursRemaining") + " (" + Helper.roundNumber(100.0F - hours / TFCOptions.pitKilnBurnTime * 100.0F, 10.0F) + "%)");
/*      */     } 
/*      */     
/* 1174 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> saplingBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/* 1179 */     NBTTagCompound tag = accessor.getNBTData();
/* 1180 */     boolean enoughSpace = tag.func_74767_n("enoughSpace");
/* 1181 */     long growTime = tag.func_74763_f("growTime");
/* 1182 */     int days = (int)((growTime - TFC_Time.getTotalTicks()) / 24000L);
/* 1183 */     if (days > 0) {
/*      */       
/* 1185 */       currenttip.add(days + " " + TFC_Core.translate("gui.daysRemaining"));
/*      */     }
/* 1187 */     else if (!enoughSpace) {
/*      */       
/* 1189 */       currenttip.add(TFC_Core.translate("gui.enoughSpace"));
/*      */     } 
/*      */     
/* 1192 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> sluiceBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/* 1197 */     NBTTagCompound tag = accessor.getNBTData();
/* 1198 */     ItemStack[] storage = getStorage(tag, accessor.getTileEntity());
/* 1199 */     int soilAmount = tag.func_74762_e("soilAmount");
/*      */     
/* 1201 */     if (soilAmount == -1) {
/* 1202 */       currenttip.add(TFC_Core.translate("gui.Sluice.Overworked"));
/* 1203 */     } else if (soilAmount > 0) {
/*      */       
/* 1205 */       currenttip.add(TFC_Core.translate("gui.Sluice.Soil") + " : " + soilAmount + "/50");
/*      */     } 
/*      */     
/* 1208 */     int gemCount = 0, oreCount = 0;
/* 1209 */     for (ItemStack is : storage) {
/*      */       
/* 1211 */       if (is != null)
/*      */       {
/* 1213 */         if (is.func_77973_b() instanceof com.bioxx.tfc.Items.ItemGem) {
/* 1214 */           gemCount++;
/* 1215 */         } else if (is.func_77973_b() instanceof com.bioxx.tfc.Items.ItemOre) {
/* 1216 */           oreCount++;
/*      */         } 
/*      */       }
/*      */     } 
/* 1220 */     if (gemCount > 0)
/* 1221 */       currenttip.add(TFC_Core.translate("gui.gems") + " : " + gemCount); 
/* 1222 */     if (oreCount > 0) {
/* 1223 */       currenttip.add(TFC_Core.translate("gui.Bloomery.Ore") + " : " + oreCount);
/*      */     }
/* 1225 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> smokeRackBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/* 1230 */     NBTTagCompound tag = accessor.getNBTData();
/* 1231 */     ItemStack[] storage = getStorage(tag, accessor.getTileEntity());
/*      */     
/* 1233 */     for (int i = 0; i < storage.length; i++) {
/*      */       
/* 1235 */       ItemStack is = storage[i];
/* 1236 */       if (is != null) {
/*      */         
/* 1238 */         int dryHours = 4 - Food.getDried(is);
/* 1239 */         int smokeHours = 12 - Food.getSmokeCounter(is);
/*      */         
/* 1241 */         if (smokeHours < 12 && !Food.isSmoked(is)) {
/*      */           
/* 1243 */           smokeHours++;
/* 1244 */           float percent = Helper.roundNumber(100.0F - 100.0F * smokeHours / 12.0F, 10.0F);
/* 1245 */           currenttip.add(TFC_Core.translate("word.smoking") + " " + is.func_82833_r());
/* 1246 */           currenttip.add("· " + smokeHours + " " + TFC_Core.translate("gui.hoursRemaining") + " (" + percent + "%)");
/*      */         }
/* 1248 */         else if (dryHours < 4 && !Food.isDried(is)) {
/*      */           
/* 1250 */           float percent = Helper.roundNumber(100.0F - 100.0F * dryHours / 4.0F, 10.0F);
/* 1251 */           currenttip.add(TFC_Core.translate("word.drying") + " " + is.func_82833_r());
/* 1252 */           currenttip.add("· " + dryHours + " " + TFC_Core.translate("gui.hoursRemaining") + " (" + percent + "%)");
/*      */         } else {
/*      */           
/* 1255 */           currenttip.add(is.func_82833_r());
/*      */         } 
/*      */       } 
/*      */     } 
/* 1259 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> spawnMeterBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/* 1264 */     NBTTagCompound tag = accessor.getNBTData();
/* 1265 */     int hours = tag.func_74762_e("protectionHours");
/*      */     
/* 1267 */     if (hours > 0)
/*      */     {
/* 1269 */       currenttip.add(hours + " " + TFC_Core.translate("gui.hoursRemaining"));
/*      */     }
/*      */     
/* 1272 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> soilBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/* 1277 */     Block b = accessor.getBlock();
/* 1278 */     int dam = itemStack.func_77960_j();
/* 1279 */     if (b == TFCBlocks.dirt2 || b == TFCBlocks.sand2 || TFC_Core.isGrassType2(b) || b == TFCBlocks.gravel2)
/*      */     {
/* 1281 */       dam += 16;
/*      */     }
/*      */     
/* 1284 */     if (dam < Global.STONE_ALL.length) {
/* 1285 */       currenttip.add(Global.STONE_ALL[dam]);
/*      */     } else {
/* 1287 */       currenttip.add(EnumChatFormatting.DARK_RED + "Unknown");
/*      */     } 
/* 1289 */     return currenttip;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> torchBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/* 1294 */     if (accessor.getMetadata() < 8 && TFCOptions.torchBurnTime != 0) {
/*      */       
/* 1296 */       NBTTagCompound tag = accessor.getNBTData();
/* 1297 */       long hours = TFCOptions.torchBurnTime - TFC_Time.getTotalHours() - tag.func_74762_e("hourPlaced");
/*      */       
/* 1299 */       if (hours > 0L)
/* 1300 */         currenttip.add(hours + " " + TFC_Core.translate("gui.hoursRemaining") + " (" + Helper.roundNumber(100.0F * (float)hours / TFCOptions.torchBurnTime, 10.0F) + "%)"); 
/*      */     } 
/* 1302 */     return currenttip;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private ItemStack[] getStorage(NBTTagCompound tag, TileEntity te) {
/* 1308 */     if (te instanceof IInventory) {
/*      */       
/* 1310 */       IInventory inv = (IInventory)te;
/* 1311 */       NBTTagList tagList = tag.func_150295_c("Items", 10);
/* 1312 */       ItemStack[] storage = new ItemStack[inv.func_70302_i_()];
/* 1313 */       for (int i = 0; i < tagList.func_74745_c(); i++) {
/*      */         
/* 1315 */         NBTTagCompound itemTag = tagList.func_150305_b(i);
/* 1316 */         byte slot = itemTag.func_74771_c("Slot");
/* 1317 */         if (slot >= 0 && slot < storage.length) {
/* 1318 */           storage[slot] = ItemStack.func_77949_a(itemTag);
/*      */         }
/*      */       } 
/* 1321 */       return storage;
/*      */     } 
/*      */     
/* 1324 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   private int getOreGrade(TEOre te, int ore) {
/* 1329 */     if (te != null) {
/*      */       
/* 1331 */       int grade = te.extraData & 0x7;
/* 1332 */       if (grade == 1) {
/* 1333 */         ore += 35;
/* 1334 */       } else if (grade == 2) {
/* 1335 */         ore += 49;
/*      */       } 
/* 1337 */     }  return ore;
/*      */   }
/*      */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WAILA\WAILAData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */