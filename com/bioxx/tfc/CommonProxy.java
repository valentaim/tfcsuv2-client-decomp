/*     */ package com.bioxx.tfc;
/*     */ 
/*     */ import com.bioxx.tfc.Entities.EntityBarrel;
/*     */ import com.bioxx.tfc.Entities.EntityCustomMinecart;
/*     */ import com.bioxx.tfc.Entities.EntityFallingBlockTFC;
/*     */ import com.bioxx.tfc.Entities.EntityJavelin;
/*     */ import com.bioxx.tfc.Entities.EntityProjectileTFC;
/*     */ import com.bioxx.tfc.Entities.EntityStand;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityBear;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityBlazeTFC;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityCaveSpiderTFC;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityChickenTFC;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityCreeperTFC;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityDeer;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityEndermanTFC;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityFishTFC;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityGhastTFC;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityHorseTFC;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityIronGolemTFC;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityPheasantTFC;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityPigZombieTFC;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntitySilverfishTFC;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntitySkeletonTFC;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntitySlimeTFC;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntitySpiderTFC;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntitySquidTFC;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityWolfTFC;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityZombieTFC;
/*     */ import com.bioxx.tfc.Handlers.GuiHandler;
/*     */ import com.bioxx.tfc.Handlers.ServerTickHandler;
/*     */ import com.bioxx.tfc.TileEntities.TEAnvil;
/*     */ import com.bioxx.tfc.TileEntities.TEBarrel;
/*     */ import com.bioxx.tfc.TileEntities.TEBellows;
/*     */ import com.bioxx.tfc.TileEntities.TEBerryBush;
/*     */ import com.bioxx.tfc.TileEntities.TEBlastFurnace;
/*     */ import com.bioxx.tfc.TileEntities.TEBloom;
/*     */ import com.bioxx.tfc.TileEntities.TEBloomery;
/*     */ import com.bioxx.tfc.TileEntities.TEChest;
/*     */ import com.bioxx.tfc.TileEntities.TECrop;
/*     */ import com.bioxx.tfc.TileEntities.TECrucible;
/*     */ import com.bioxx.tfc.TileEntities.TEDetailed;
/*     */ import com.bioxx.tfc.TileEntities.TEFarmland;
/*     */ import com.bioxx.tfc.TileEntities.TEFenceGate;
/*     */ import com.bioxx.tfc.TileEntities.TEFirepit;
/*     */ import com.bioxx.tfc.TileEntities.TEFoodPrep;
/*     */ import com.bioxx.tfc.TileEntities.TEForge;
/*     */ import com.bioxx.tfc.TileEntities.TEFruitLeaves;
/*     */ import com.bioxx.tfc.TileEntities.TEFruitTreeWood;
/*     */ import com.bioxx.tfc.TileEntities.TEGrill;
/*     */ import com.bioxx.tfc.TileEntities.TEHopper;
/*     */ import com.bioxx.tfc.TileEntities.TEIngotPile;
/*     */ import com.bioxx.tfc.TileEntities.TELeatherRack;
/*     */ import com.bioxx.tfc.TileEntities.TELightEmitter;
/*     */ import com.bioxx.tfc.TileEntities.TELogPile;
/*     */ import com.bioxx.tfc.TileEntities.TELoom;
/*     */ import com.bioxx.tfc.TileEntities.TEMetalSheet;
/*     */ import com.bioxx.tfc.TileEntities.TEMetalTrapDoor;
/*     */ import com.bioxx.tfc.TileEntities.TENestBox;
/*     */ import com.bioxx.tfc.TileEntities.TEOilLamp;
/*     */ import com.bioxx.tfc.TileEntities.TEOre;
/*     */ import com.bioxx.tfc.TileEntities.TEPartial;
/*     */ import com.bioxx.tfc.TileEntities.TEPottery;
/*     */ import com.bioxx.tfc.TileEntities.TEQuern;
/*     */ import com.bioxx.tfc.TileEntities.TESapling;
/*     */ import com.bioxx.tfc.TileEntities.TESluice;
/*     */ import com.bioxx.tfc.TileEntities.TESmokeRack;
/*     */ import com.bioxx.tfc.TileEntities.TESpawnMeter;
/*     */ import com.bioxx.tfc.TileEntities.TEStand;
/*     */ import com.bioxx.tfc.TileEntities.TEToolRack;
/*     */ import com.bioxx.tfc.TileEntities.TEVessel;
/*     */ import com.bioxx.tfc.TileEntities.TEWaterPlant;
/*     */ import com.bioxx.tfc.TileEntities.TEWoodConstruct;
/*     */ import com.bioxx.tfc.TileEntities.TEWorkbench;
/*     */ import com.bioxx.tfc.TileEntities.TEWorldItem;
/*     */ import com.bioxx.tfc.Tools.ChiselMode_Detailed;
/*     */ import com.bioxx.tfc.Tools.ChiselMode_Slab;
/*     */ import com.bioxx.tfc.Tools.ChiselMode_Smooth;
/*     */ import com.bioxx.tfc.Tools.ChiselMode_Stair;
/*     */ import com.bioxx.tfc.WorldGen.TFCProvider;
/*     */ import com.bioxx.tfc.api.TFCFluids;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.Tools.ChiselManager;
/*     */ import com.bioxx.tfc.api.Tools.ChiselMode;
/*     */ import cpw.mods.fml.common.FMLCommonHandler;
/*     */ import cpw.mods.fml.common.event.FMLInterModComms;
/*     */ import cpw.mods.fml.common.network.IGuiHandler;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry;
/*     */ import cpw.mods.fml.common.registry.EntityRegistry;
/*     */ import cpw.mods.fml.common.registry.GameRegistry;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fluids.FluidContainerRegistry;
/*     */ import net.minecraftforge.fluids.FluidRegistry;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommonProxy
/*     */ {
/*     */   public void registerFluidIcons() {}
/*     */   
/*     */   public void registerRenderInformation() {}
/*     */   
/*     */   public void registerBiomeEventHandler() {}
/*     */   
/*     */   public void registerPlayerRenderEventHandler() {}
/*     */   
/*     */   public void setupGuiIngameForge() {}
/*     */   
/*     */   public String getCurrentLanguage() {
/* 128 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerTileEntities(boolean b) {
/* 133 */     GameRegistry.registerTileEntity(TELogPile.class, "TerraLogPile");
/* 134 */     GameRegistry.registerTileEntity(TEWorkbench.class, "TerraWorkbench");
/* 135 */     GameRegistry.registerTileEntity(TEForge.class, "TerraForge");
/* 136 */     GameRegistry.registerTileEntity(TEBlastFurnace.class, "TerraBloomery");
/* 137 */     GameRegistry.registerTileEntity(TEBloomery.class, "TerraEarlyBloomery");
/* 138 */     GameRegistry.registerTileEntity(TESluice.class, "TerraSluice");
/* 139 */     GameRegistry.registerTileEntity(TEFarmland.class, "TileEntityFarmland");
/* 140 */     GameRegistry.registerTileEntity(TECrop.class, "TileEntityCrop");
/* 141 */     GameRegistry.registerTileEntity(TEFruitTreeWood.class, "FruitTreeWood");
/* 142 */     GameRegistry.registerTileEntity(TEPartial.class, "Partial");
/* 143 */     GameRegistry.registerTileEntity(TEDetailed.class, "Detailed");
/* 144 */     GameRegistry.registerTileEntity(TESpawnMeter.class, "SpawnMeter");
/* 145 */     GameRegistry.registerTileEntity(TESapling.class, "Sapling");
/* 146 */     GameRegistry.registerTileEntity(TEWoodConstruct.class, "WoodConstruct");
/* 147 */     GameRegistry.registerTileEntity(TEBarrel.class, "Barrel");
/* 148 */     GameRegistry.registerTileEntity(TEFenceGate.class, "FenceGate");
/* 149 */     GameRegistry.registerTileEntity(TEBloom.class, "IronBloom");
/* 150 */     GameRegistry.registerTileEntity(TECrucible.class, "Crucible");
/* 151 */     GameRegistry.registerTileEntity(TENestBox.class, "Nest Box");
/* 152 */     GameRegistry.registerTileEntity(TEStand.class, "Armour Stand");
/* 153 */     GameRegistry.registerTileEntity(TEBerryBush.class, "Berry Bush");
/* 154 */     GameRegistry.registerTileEntity(TEFruitLeaves.class, "Fruit Leaves");
/* 155 */     GameRegistry.registerTileEntity(TEMetalSheet.class, "Metal Sheet");
/* 156 */     GameRegistry.registerTileEntity(TEOre.class, "ore");
/* 157 */     GameRegistry.registerTileEntity(TELeatherRack.class, "leatherRack");
/* 158 */     GameRegistry.registerTileEntity(TEMetalTrapDoor.class, "MetalTrapDoor");
/* 159 */     GameRegistry.registerTileEntity(TEWaterPlant.class, "Sea Weed");
/* 160 */     GameRegistry.registerTileEntity(TEVessel.class, "Vessel");
/* 161 */     GameRegistry.registerTileEntity(TELightEmitter.class, "LightEmitter");
/* 162 */     GameRegistry.registerTileEntity(TESmokeRack.class, "Smoke Rack");
/* 163 */     GameRegistry.registerTileEntity(TEOilLamp.class, "Oil Lamp");
/*     */ 
/*     */     
/* 166 */     if (b) {
/*     */       
/* 168 */       GameRegistry.registerTileEntity(TEFirepit.class, "TerraFirepit");
/* 169 */       GameRegistry.registerTileEntity(TEIngotPile.class, "ingotPile");
/* 170 */       GameRegistry.registerTileEntity(TEPottery.class, "Pottery");
/* 171 */       GameRegistry.registerTileEntity(TEChest.class, "chest");
/* 172 */       GameRegistry.registerTileEntity(TEFoodPrep.class, "FoodPrep");
/* 173 */       GameRegistry.registerTileEntity(TEBellows.class, "Bellows");
/* 174 */       GameRegistry.registerTileEntity(TEToolRack.class, "ToolRack");
/* 175 */       GameRegistry.registerTileEntity(TEAnvil.class, "TerraAnvil");
/* 176 */       GameRegistry.registerTileEntity(TEWorldItem.class, "worldItem");
/* 177 */       GameRegistry.registerTileEntity(TEQuern.class, "Quern");
/* 178 */       GameRegistry.registerTileEntity(TELoom.class, "Loom");
/* 179 */       GameRegistry.registerTileEntity(TEGrill.class, "grill");
/* 180 */       GameRegistry.registerTileEntity(TEHopper.class, "HopperTFC");
/*     */     } 
/*     */     
/* 183 */     EntityRegistry.registerGlobalEntityID(EntitySquidTFC.class, "squidTFC", EntityRegistry.findGlobalUniqueEntityId(), 3953766, 2490406);
/* 184 */     EntityRegistry.registerGlobalEntityID(EntityFishTFC.class, "fishTFC", EntityRegistry.findGlobalUniqueEntityId(), 5460529, 2490406);
/*     */     
/* 186 */     EntityRegistry.registerGlobalEntityID(EntityWolfTFC.class, "wolfTFC", EntityRegistry.findGlobalUniqueEntityId(), 9670540, 2490406);
/*     */     
/* 188 */     EntityRegistry.registerGlobalEntityID(EntityChickenTFC.class, "chickenTFC", EntityRegistry.findGlobalUniqueEntityId(), 15987806, 2490406);
/*     */     
/* 190 */     EntityRegistry.registerGlobalEntityID(EntityDeer.class, "deerTFC", EntityRegistry.findGlobalUniqueEntityId(), 8151628, 2490406);
/*     */     
/* 192 */     EntityRegistry.registerGlobalEntityID(EntitySkeletonTFC.class, "skeletonTFC", EntityRegistry.findGlobalUniqueEntityId(), 9934743, 2490406);
/* 193 */     EntityRegistry.registerGlobalEntityID(EntityZombieTFC.class, "zombieTFC", EntityRegistry.findGlobalUniqueEntityId(), 4352563, 2490406);
/* 194 */     EntityRegistry.registerGlobalEntityID(EntitySpiderTFC.class, "spiderTFC", EntityRegistry.findGlobalUniqueEntityId(), 3287844, 2490406);
/* 195 */     EntityRegistry.registerGlobalEntityID(EntitySlimeTFC.class, "slimeTFC", EntityRegistry.findGlobalUniqueEntityId(), 7254876, 2490406);
/* 196 */     EntityRegistry.registerGlobalEntityID(EntitySilverfishTFC.class, "silverfishTFC", EntityRegistry.findGlobalUniqueEntityId(), 8751239, 2490406);
/* 197 */     EntityRegistry.registerGlobalEntityID(EntityGhastTFC.class, "ghastTFC", EntityRegistry.findGlobalUniqueEntityId(), 15461355, 2490406);
/* 198 */     EntityRegistry.registerGlobalEntityID(EntityCaveSpiderTFC.class, "caveSpiderTFC", EntityRegistry.findGlobalUniqueEntityId(), 1192502, 2490406);
/* 199 */     EntityRegistry.registerGlobalEntityID(EntityBlazeTFC.class, "blazeTFC", EntityRegistry.findGlobalUniqueEntityId(), 11365643, 2490406);
/* 200 */     EntityRegistry.registerGlobalEntityID(EntityEndermanTFC.class, "endermanTFC", EntityRegistry.findGlobalUniqueEntityId(), 855309, 2490406);
/* 201 */     EntityRegistry.registerGlobalEntityID(EntityPigZombieTFC.class, "pigZombieTFC", EntityRegistry.findGlobalUniqueEntityId(), 11957087, 2490406);
/* 202 */     EntityRegistry.registerGlobalEntityID(EntityIronGolemTFC.class, "irongolemTFC", EntityRegistry.findGlobalUniqueEntityId(), 12564890, 2490406);
/* 203 */     EntityRegistry.registerGlobalEntityID(EntityCreeperTFC.class, "creeperTFC", EntityRegistry.findGlobalUniqueEntityId(), 6735196, 2490406);
/*     */ 
/*     */     
/* 206 */     EntityRegistry.registerGlobalEntityID(EntityPheasantTFC.class, "pheasantTFC", EntityRegistry.findGlobalUniqueEntityId(), 8530972, 2490406);
/*     */     
/* 208 */     EntityRegistry.registerGlobalEntityID(EntityHorseTFC.class, "horseTFC", EntityRegistry.findGlobalUniqueEntityId(), 9857334, 2490406);
/*     */     
/* 210 */     EntityRegistry.registerGlobalEntityID(EntityCustomMinecart.class, "minecartTFC", EntityRegistry.findGlobalUniqueEntityId());
/* 211 */     EntityRegistry.registerGlobalEntityID(EntityProjectileTFC.class, "arrowTFC", EntityRegistry.findGlobalUniqueEntityId());
/* 212 */     EntityRegistry.registerGlobalEntityID(EntityStand.class, "standTFC", EntityRegistry.findGlobalUniqueEntityId());
/*     */     
/* 214 */     EntityRegistry.registerGlobalEntityID(EntityFallingBlockTFC.class, "fallingBlock", EntityRegistry.findGlobalUniqueEntityId());
/* 215 */     EntityRegistry.registerGlobalEntityID(EntityBarrel.class, "barrel", EntityRegistry.findGlobalUniqueEntityId());
/*     */     
/* 217 */     EntityRegistry.registerModEntity(EntityJavelin.class, "javelin", 1, TerraFirmaCraft.instance, 64, 5, true);
/* 218 */     EntityRegistry.registerModEntity(EntitySquidTFC.class, "squidTFC", 2, TerraFirmaCraft.instance, 64, 5, true);
/*     */     
/* 220 */     EntityRegistry.registerModEntity(EntityWolfTFC.class, "wolfTFC", 7, TerraFirmaCraft.instance, 160, 5, true);
/* 221 */     EntityRegistry.registerModEntity(EntityBear.class, "bearTFC", 8, TerraFirmaCraft.instance, 160, 5, true);
/* 222 */     EntityRegistry.registerModEntity(EntityChickenTFC.class, "chickenTFC", 9, TerraFirmaCraft.instance, 160, 5, true);
/*     */     
/* 224 */     EntityRegistry.registerModEntity(EntityDeer.class, "deerTFC", 11, TerraFirmaCraft.instance, 160, 5, true);
/* 225 */     EntityRegistry.registerModEntity(EntityCustomMinecart.class, "minecartTFC", 12, TerraFirmaCraft.instance, 80, 5, true);
/* 226 */     EntityRegistry.registerModEntity(EntitySkeletonTFC.class, "skeletonTFC", 13, TerraFirmaCraft.instance, 160, 5, true);
/* 227 */     EntityRegistry.registerModEntity(EntityZombieTFC.class, "zombieTFC", 14, TerraFirmaCraft.instance, 160, 5, true);
/* 228 */     EntityRegistry.registerModEntity(EntitySpiderTFC.class, "spiderTFC", 15, TerraFirmaCraft.instance, 160, 5, true);
/* 229 */     EntityRegistry.registerModEntity(EntitySlimeTFC.class, "slimeTFC", 16, TerraFirmaCraft.instance, 160, 5, true);
/* 230 */     EntityRegistry.registerModEntity(EntitySilverfishTFC.class, "silverFishTFC", 17, TerraFirmaCraft.instance, 160, 5, true);
/* 231 */     EntityRegistry.registerModEntity(EntityGhastTFC.class, "ghastTFC", 18, TerraFirmaCraft.instance, 160, 5, true);
/* 232 */     EntityRegistry.registerModEntity(EntityCaveSpiderTFC.class, "caveSpiderTFC", 19, TerraFirmaCraft.instance, 160, 5, true);
/* 233 */     EntityRegistry.registerModEntity(EntityBlazeTFC.class, "blazeTFC", 20, TerraFirmaCraft.instance, 160, 5, true);
/* 234 */     EntityRegistry.registerModEntity(EntityEndermanTFC.class, "endermanTFC", 21, TerraFirmaCraft.instance, 160, 5, true);
/* 235 */     EntityRegistry.registerModEntity(EntityPigZombieTFC.class, "pigZombieTFC", 22, TerraFirmaCraft.instance, 160, 5, true);
/* 236 */     EntityRegistry.registerModEntity(EntityIronGolemTFC.class, "irongolemTFC", 23, TerraFirmaCraft.instance, 160, 5, true);
/* 237 */     EntityRegistry.registerModEntity(EntityCreeperTFC.class, "creeperTFC", 24, TerraFirmaCraft.instance, 160, 5, true);
/* 238 */     EntityRegistry.registerModEntity(EntityStand.class, "standTFC", 25, TerraFirmaCraft.instance, 64, 20, false);
/* 239 */     EntityRegistry.registerModEntity(EntityPheasantTFC.class, "PheasantTFC", 26, TerraFirmaCraft.instance, 160, 5, true);
/* 240 */     EntityRegistry.registerModEntity(EntityFishTFC.class, "fishTFC", 27, TerraFirmaCraft.instance, 64, 5, true);
/* 241 */     EntityRegistry.registerModEntity(EntityFallingBlockTFC.class, "fallingBlock", 28, TerraFirmaCraft.instance, 160, 20, true);
/* 242 */     EntityRegistry.registerModEntity(EntityBarrel.class, "barrel", 29, TerraFirmaCraft.instance, 64, 20, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerFluids() {
/* 259 */     FluidRegistry.registerFluid(TFCFluids.LAVA);
/* 260 */     FluidRegistry.registerFluid(TFCFluids.SALTWATER);
/* 261 */     FluidRegistry.registerFluid(TFCFluids.FRESHWATER);
/* 262 */     FluidRegistry.registerFluid(TFCFluids.HOTWATER);
/* 263 */     FluidRegistry.registerFluid(TFCFluids.RUM);
/* 264 */     FluidRegistry.registerFluid(TFCFluids.BEER);
/* 265 */     FluidRegistry.registerFluid(TFCFluids.RYEWHISKEY);
/* 266 */     FluidRegistry.registerFluid(TFCFluids.CORNWHISKEY);
/* 267 */     FluidRegistry.registerFluid(TFCFluids.WHISKEY);
/* 268 */     FluidRegistry.registerFluid(TFCFluids.SAKE);
/* 269 */     FluidRegistry.registerFluid(TFCFluids.VODKA);
/* 270 */     FluidRegistry.registerFluid(TFCFluids.CIDER);
/* 271 */     FluidRegistry.registerFluid(TFCFluids.TANNIN);
/* 272 */     FluidRegistry.registerFluid(TFCFluids.VINEGAR);
/* 273 */     FluidRegistry.registerFluid(TFCFluids.BRINE);
/* 274 */     FluidRegistry.registerFluid(TFCFluids.LIMEWATER);
/* 275 */     FluidRegistry.registerFluid(TFCFluids.MILK);
/*     */     
/* 277 */     FluidRegistry.registerFluid(TFCFluids.MILKVINEGAR);
/* 278 */     FluidRegistry.registerFluid(TFCFluids.OLIVEOIL);
/*     */     
/* 280 */     FluidRegistry.registerFluid(TFCFluids.HONEY);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setupFluids() {
/* 285 */     FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluid(TFCFluids.LAVA.getName()), new ItemStack(TFCItems.blueSteelBucketLava), new ItemStack(TFCItems.blueSteelBucketEmpty));
/* 286 */     FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluid(TFCFluids.FRESHWATER.getName()), new ItemStack(TFCItems.redSteelBucketWater), new ItemStack(TFCItems.redSteelBucketEmpty));
/* 287 */     FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluid(TFCFluids.SALTWATER.getName()), new ItemStack(TFCItems.redSteelBucketSaltWater), new ItemStack(TFCItems.redSteelBucketEmpty));
/* 288 */     FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluid(TFCFluids.FRESHWATER.getName()), new ItemStack(TFCItems.woodenBucketWater), new ItemStack(TFCItems.woodenBucketEmpty));
/* 289 */     FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluid(TFCFluids.SALTWATER.getName()), new ItemStack(TFCItems.woodenBucketSaltWater), new ItemStack(TFCItems.woodenBucketEmpty));
/* 290 */     FluidContainerRegistry.registerFluidContainer(new FluidStack(TFCFluids.FRESHWATER, 1000), new ItemStack(TFCItems.potteryJug, 1, 2), new ItemStack(TFCItems.potteryJug, 1, 1));
/* 291 */     FluidContainerRegistry.registerFluidContainer(new FluidStack(TFCFluids.RUM, 250), new ItemStack(TFCItems.rum), new ItemStack(TFCItems.glassBottle));
/* 292 */     FluidContainerRegistry.registerFluidContainer(new FluidStack(TFCFluids.BEER, 250), new ItemStack(TFCItems.beer), new ItemStack(TFCItems.glassBottle));
/* 293 */     FluidContainerRegistry.registerFluidContainer(new FluidStack(TFCFluids.RYEWHISKEY, 250), new ItemStack(TFCItems.ryeWhiskey), new ItemStack(TFCItems.glassBottle));
/* 294 */     FluidContainerRegistry.registerFluidContainer(new FluidStack(TFCFluids.WHISKEY, 250), new ItemStack(TFCItems.whiskey), new ItemStack(TFCItems.glassBottle));
/* 295 */     FluidContainerRegistry.registerFluidContainer(new FluidStack(TFCFluids.CORNWHISKEY, 250), new ItemStack(TFCItems.cornWhiskey), new ItemStack(TFCItems.glassBottle));
/* 296 */     FluidContainerRegistry.registerFluidContainer(new FluidStack(TFCFluids.SAKE, 250), new ItemStack(TFCItems.sake), new ItemStack(TFCItems.glassBottle));
/* 297 */     FluidContainerRegistry.registerFluidContainer(new FluidStack(TFCFluids.CIDER, 250), new ItemStack(TFCItems.cider), new ItemStack(TFCItems.glassBottle));
/* 298 */     FluidContainerRegistry.registerFluidContainer(new FluidStack(TFCFluids.VODKA, 250), new ItemStack(TFCItems.vodka), new ItemStack(TFCItems.glassBottle));
/* 299 */     FluidContainerRegistry.registerFluidContainer(new FluidStack(TFCFluids.MILK, 1000), new ItemStack(TFCItems.woodenBucketMilk), new ItemStack(TFCItems.woodenBucketEmpty));
/* 300 */     FluidContainerRegistry.registerFluidContainer(new FluidStack(TFCFluids.VINEGAR, 1000), new ItemStack(TFCItems.vinegar), new ItemStack(TFCItems.woodenBucketEmpty));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerToolClasses() {
/* 313 */     TFCItems.bismuthBronzePick.setHarvestLevel("pickaxe", 2);
/* 314 */     TFCItems.bismuthBronzePick.setHarvestLevel("pickaxe", 2);
/* 315 */     TFCItems.blackBronzePick.setHarvestLevel("pickaxe", 2);
/* 316 */     TFCItems.blackSteelPick.setHarvestLevel("pickaxe", 5);
/* 317 */     TFCItems.blueSteelPick.setHarvestLevel("pickaxe", 6);
/* 318 */     TFCItems.bronzePick.setHarvestLevel("pickaxe", 2);
/* 319 */     TFCItems.copperPick.setHarvestLevel("pickaxe", 1);
/* 320 */     TFCItems.wroughtIronPick.setHarvestLevel("pickaxe", 3);
/* 321 */     TFCItems.redSteelPick.setHarvestLevel("pickaxe", 6);
/* 322 */     TFCItems.steelPick.setHarvestLevel("pickaxe", 4);
/*     */     
/* 324 */     TFCItems.igInShovel.setHarvestLevel("shovel", 1);
/* 325 */     TFCItems.igExShovel.setHarvestLevel("shovel", 1);
/* 326 */     TFCItems.sedShovel.setHarvestLevel("shovel", 1);
/* 327 */     TFCItems.mMShovel.setHarvestLevel("shovel", 1);
/* 328 */     TFCItems.bismuthBronzeShovel.setHarvestLevel("shovel", 2);
/* 329 */     TFCItems.blackBronzeShovel.setHarvestLevel("shovel", 2);
/* 330 */     TFCItems.blackSteelShovel.setHarvestLevel("shovel", 5);
/* 331 */     TFCItems.blueSteelShovel.setHarvestLevel("shovel", 6);
/* 332 */     TFCItems.bronzeShovel.setHarvestLevel("shovel", 2);
/* 333 */     TFCItems.copperShovel.setHarvestLevel("shovel", 1);
/* 334 */     TFCItems.wroughtIronShovel.setHarvestLevel("shovel", 3);
/* 335 */     TFCItems.redSteelShovel.setHarvestLevel("shovel", 6);
/* 336 */     TFCItems.steelShovel.setHarvestLevel("shovel", 4);
/*     */     
/* 338 */     TFCItems.igInAxe.setHarvestLevel("axe", 1);
/* 339 */     TFCItems.igExAxe.setHarvestLevel("axe", 1);
/* 340 */     TFCItems.sedAxe.setHarvestLevel("axe", 1);
/* 341 */     TFCItems.mMAxe.setHarvestLevel("axe", 1);
/* 342 */     TFCItems.bismuthBronzeAxe.setHarvestLevel("axe", 2);
/* 343 */     TFCItems.blackBronzeAxe.setHarvestLevel("axe", 2);
/* 344 */     TFCItems.blackSteelAxe.setHarvestLevel("axe", 5);
/* 345 */     TFCItems.blueSteelAxe.setHarvestLevel("axe", 6);
/* 346 */     TFCItems.bronzeAxe.setHarvestLevel("axe", 2);
/* 347 */     TFCItems.copperAxe.setHarvestLevel("axe", 1);
/* 348 */     TFCItems.wroughtIronAxe.setHarvestLevel("axe", 3);
/* 349 */     TFCItems.redSteelAxe.setHarvestLevel("axe", 6);
/* 350 */     TFCItems.steelAxe.setHarvestLevel("axe", 4);
/*     */     
/* 352 */     TFCItems.bismuthBronzeSaw.setHarvestLevel("axe", 2);
/* 353 */     TFCItems.blackBronzeSaw.setHarvestLevel("axe", 2);
/* 354 */     TFCItems.blackSteelSaw.setHarvestLevel("axe", 5);
/* 355 */     TFCItems.blueSteelSaw.setHarvestLevel("axe", 6);
/* 356 */     TFCItems.bronzeSaw.setHarvestLevel("axe", 2);
/* 357 */     TFCItems.copperSaw.setHarvestLevel("axe", 1);
/* 358 */     TFCItems.wroughtIronSaw.setHarvestLevel("axe", 3);
/* 359 */     TFCItems.redSteelSaw.setHarvestLevel("axe", 6);
/* 360 */     TFCItems.steelSaw.setHarvestLevel("axe", 4);
/*     */     
/* 362 */     TFCItems.stoneHammer.setHarvestLevel("hammer", 1);
/* 363 */     TFCItems.bismuthBronzeHammer.setHarvestLevel("hammer", 2);
/* 364 */     TFCItems.blackBronzeHammer.setHarvestLevel("hammer", 2);
/* 365 */     TFCItems.blackSteelHammer.setHarvestLevel("hammer", 5);
/* 366 */     TFCItems.blueSteelHammer.setHarvestLevel("hammer", 6);
/* 367 */     TFCItems.bronzeHammer.setHarvestLevel("hammer", 2);
/* 368 */     TFCItems.copperHammer.setHarvestLevel("hammer", 1);
/* 369 */     TFCItems.wroughtIronHammer.setHarvestLevel("hammer", 3);
/* 370 */     TFCItems.redSteelHammer.setHarvestLevel("hammer", 6);
/* 371 */     TFCItems.steelHammer.setHarvestLevel("hammer", 4);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onClientLogin() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerSkyProvider(TFCProvider p) {}
/*     */ 
/*     */   
/*     */   public boolean isRemote() {
/* 384 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public World getCurrentWorld() {
/* 389 */     return MinecraftServer.func_71276_C().func_130014_f_();
/*     */   }
/*     */ 
/*     */   
/*     */   public int waterColorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 394 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int grassColorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 399 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int foliageColorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 404 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void takenFromCrafting(EntityPlayer entityplayer, ItemStack itemstack, IInventory iinventory) {
/* 409 */     FMLCommonHandler.instance().firePlayerCraftingEvent(entityplayer, itemstack, iinventory);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getArmorRenderID(String name) {
/* 414 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getGraphicsLevel() {
/* 419 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerKeys() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerKeyBindingHandler() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void uploadKeyBindingsToGame() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerHandlers() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerSoundHandler() {}
/*     */ 
/*     */   
/*     */   public void registerTickHandler() {
/* 444 */     FMLCommonHandler.instance().bus().register(new ServerTickHandler());
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerGuiHandler() {
/* 449 */     NetworkRegistry.INSTANCE.registerGuiHandler(TerraFirmaCraft.instance, (IGuiHandler)new GuiHandler());
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerWailaClasses() {
/* 454 */     FMLInterModComms.sendMessage("Waila", "register", "com.bioxx.tfc.WAILA.WAILAData.callbackRegister");
/* 455 */     FMLInterModComms.sendMessage("Waila", "register", "com.bioxx.tfc.WAILA.WMobs.callbackRegister");
/* 456 */     FMLInterModComms.sendMessage("Waila", "register", "com.bioxx.tfc.WAILA.WCrucible.callbackRegister");
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerChiselModes() {
/* 461 */     ChiselManager.getInstance().addChiselMode((ChiselMode)new ChiselMode_Smooth("Smooth"));
/* 462 */     ChiselManager.getInstance().addChiselMode((ChiselMode)new ChiselMode_Stair("Stairs"));
/* 463 */     ChiselManager.getInstance().addChiselMode((ChiselMode)new ChiselMode_Slab("Slabs"));
/* 464 */     ChiselManager.getInstance().addChiselMode((ChiselMode)new ChiselMode_Detailed("Detailed"));
/*     */   }
/*     */   
/*     */   public void hideNEIItems() {}
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */