/*     */ package com.bioxx.tfc;
/*     */ import com.bioxx.tfc.Core.ColorizerFoliageTFC;
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.Entities.EntityBarrel;
/*     */ import com.bioxx.tfc.Entities.EntityCustomMinecart;
/*     */ import com.bioxx.tfc.Entities.EntityFallingBlockTFC;
/*     */ import com.bioxx.tfc.Entities.EntityFishHookTFC;
/*     */ import com.bioxx.tfc.Entities.EntityJavelin;
/*     */ import com.bioxx.tfc.Entities.EntityProjectileTFC;
/*     */ import com.bioxx.tfc.Entities.EntityStand;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityBear;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityBlazeTFC;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityCaveSpiderTFC;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityChickenTFC;
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
/*     */ import com.bioxx.tfc.Handlers.BiomeEventHandler;
/*     */ import com.bioxx.tfc.Handlers.Client.ArmourStandHighlightHandler;
/*     */ import com.bioxx.tfc.Handlers.Client.BlockRenderHandler;
/*     */ import com.bioxx.tfc.Handlers.Client.ChiselHighlightHandler;
/*     */ import com.bioxx.tfc.Handlers.Client.ClientTickHandler;
/*     */ import com.bioxx.tfc.Handlers.Client.FMLClientEventHandler;
/*     */ import com.bioxx.tfc.Handlers.Client.FamiliarityHighlightHandler;
/*     */ import com.bioxx.tfc.Handlers.Client.FarmlandHighlightHandler;
/*     */ import com.bioxx.tfc.Handlers.Client.FogHandler;
/*     */ import com.bioxx.tfc.Handlers.Client.GuiHandler;
/*     */ import com.bioxx.tfc.Handlers.Client.KeyBindingHandler;
/*     */ import com.bioxx.tfc.Handlers.Client.PlankHighlightHandler;
/*     */ import com.bioxx.tfc.Handlers.Client.PlayerRenderHandler;
/*     */ import com.bioxx.tfc.Handlers.Client.RenderOverlayHandler;
/*     */ import com.bioxx.tfc.Handlers.Client.SoundHandler;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderAnvil;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderBarrel;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderBellows;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderBloomery;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderChest;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderCrucible;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderFence;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderFenceGate;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderFlowerPot;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderGrill;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderHopper;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderLeatherRack;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderLoom;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderMetalSheet;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderMetalTrapDoor;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderNestBox;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderOilLamp;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderOre;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderPottery;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderSmoke;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderSmokeRack;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderStand;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderSupportBeam;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderToolRack;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderTorch;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderTuyere;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderVessel;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderWall;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderWattle;
/*     */ import com.bioxx.tfc.Render.Blocks.RenderWoodConstruct;
/*     */ import com.bioxx.tfc.Render.EntityRendererTFC;
/*     */ import com.bioxx.tfc.Render.FoliageColorReloadListener;
/*     */ import com.bioxx.tfc.Render.GrassColorReloadListener;
/*     */ import com.bioxx.tfc.Render.Models.ModelBass;
/*     */ import com.bioxx.tfc.Render.Models.ModelBear;
/*     */ import com.bioxx.tfc.Render.Models.ModelChickenTFC;
/*     */ import com.bioxx.tfc.Render.Models.ModelDeer;
/*     */ import com.bioxx.tfc.Render.Models.ModelHorseTFC;
/*     */ import com.bioxx.tfc.Render.Models.ModelPheasant;
/*     */ import com.bioxx.tfc.Render.Models.ModelSquidTFC;
/*     */ import com.bioxx.tfc.Render.Models.ModelWolfTFC;
/*     */ import com.bioxx.tfc.Render.RenderBarrelEntity;
/*     */ import com.bioxx.tfc.Render.RenderBear;
/*     */ import com.bioxx.tfc.Render.RenderChickenTFC;
/*     */ import com.bioxx.tfc.Render.RenderDeer;
/*     */ import com.bioxx.tfc.Render.RenderEntityStand;
/*     */ import com.bioxx.tfc.Render.RenderFallingBlock;
/*     */ import com.bioxx.tfc.Render.RenderFishTFC;
/*     */ import com.bioxx.tfc.Render.RenderHorseTFC;
/*     */ import com.bioxx.tfc.Render.RenderPheasantTFC;
/*     */ import com.bioxx.tfc.Render.RenderSkeletonTFC;
/*     */ import com.bioxx.tfc.Render.RenderSquidTFC;
/*     */ import com.bioxx.tfc.Render.RenderTerraJavelin;
/*     */ import com.bioxx.tfc.Render.RenderWolfTFC;
/*     */ import com.bioxx.tfc.Render.TESR.TESRAnvil;
/*     */ import com.bioxx.tfc.Render.TESR.TESRBellows;
/*     */ import com.bioxx.tfc.Render.TESR.TESRChest;
/*     */ import com.bioxx.tfc.Render.TESR.TESRFirepit;
/*     */ import com.bioxx.tfc.Render.TESR.TESRFoodPrep;
/*     */ import com.bioxx.tfc.Render.TESR.TESRGrill;
/*     */ import com.bioxx.tfc.Render.TESR.TESRHopper;
/*     */ import com.bioxx.tfc.Render.TESR.TESRIngotPile;
/*     */ import com.bioxx.tfc.Render.TESR.TESRLoom;
/*     */ import com.bioxx.tfc.Render.TESR.TESRPottery;
/*     */ import com.bioxx.tfc.Render.TESR.TESRQuern;
/*     */ import com.bioxx.tfc.Render.TESR.TESRSmokeRack;
/*     */ import com.bioxx.tfc.Render.TESR.TESRToolrack;
/*     */ import com.bioxx.tfc.Render.TESR.TESRWorldItem;
/*     */ import com.bioxx.tfc.TileEntities.TEAnvil;
/*     */ import com.bioxx.tfc.TileEntities.TEBellows;
/*     */ import com.bioxx.tfc.TileEntities.TEChest;
/*     */ import com.bioxx.tfc.TileEntities.TEFirepit;
/*     */ import com.bioxx.tfc.TileEntities.TEFoodPrep;
/*     */ import com.bioxx.tfc.TileEntities.TEGrill;
/*     */ import com.bioxx.tfc.TileEntities.TEHopper;
/*     */ import com.bioxx.tfc.TileEntities.TEIngotPile;
/*     */ import com.bioxx.tfc.TileEntities.TELoom;
/*     */ import com.bioxx.tfc.TileEntities.TEPottery;
/*     */ import com.bioxx.tfc.TileEntities.TEQuern;
/*     */ import com.bioxx.tfc.TileEntities.TESmokeRack;
/*     */ import com.bioxx.tfc.TileEntities.TEToolRack;
/*     */ import com.bioxx.tfc.TileEntities.TEWorldItem;
/*     */ import com.bioxx.tfc.api.Enums.EnumTree;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCFluids;
/*     */ import com.bioxx.tfc.api.Util.KeyBindings;
/*     */ import cpw.mods.fml.client.registry.ClientRegistry;
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import cpw.mods.fml.client.registry.RenderingRegistry;
/*     */ import cpw.mods.fml.common.FMLCommonHandler;
/*     */ import cpw.mods.fml.common.Loader;
/*     */ import cpw.mods.fml.common.network.IGuiHandler;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelSlime;
/*     */ import net.minecraft.client.renderer.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderArrow;
/*     */ import net.minecraft.client.renderer.entity.RenderBlaze;
/*     */ import net.minecraft.client.renderer.entity.RenderEnderman;
/*     */ import net.minecraft.client.renderer.entity.RenderFish;
/*     */ import net.minecraft.client.renderer.entity.RenderGhast;
/*     */ import net.minecraft.client.renderer.entity.RenderIronGolem;
/*     */ import net.minecraft.client.renderer.entity.RenderMinecart;
/*     */ import net.minecraft.client.renderer.entity.RenderSilverfish;
/*     */ import net.minecraft.client.renderer.entity.RenderSlime;
/*     */ import net.minecraft.client.renderer.entity.RenderSpider;
/*     */ import net.minecraft.client.renderer.entity.RenderZombie;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.client.resources.IReloadableResourceManager;
/*     */ import net.minecraft.client.resources.IResourceManagerReloadListener;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.GuiIngameForge;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ 
/*     */ public class ClientProxy extends CommonProxy {
/*     */   public void registerFluidIcons() {
/* 169 */     TFCFluids.LAVA.setIcons(TFCBlocks.lava.func_149691_a(0, 0), TFCBlocks.lava.func_149691_a(2, 0));
/* 170 */     TFCFluids.SALTWATER.setIcons(TFCBlocks.saltWater.func_149691_a(0, 0), TFCBlocks.saltWater.func_149691_a(2, 0));
/* 171 */     TFCFluids.FRESHWATER.setIcons(TFCBlocks.freshWater.func_149691_a(0, 0), TFCBlocks.freshWater.func_149691_a(2, 0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void registerRenderInformation() {
/* 178 */     (Minecraft.func_71410_x()).field_71460_t = (EntityRenderer)new EntityRendererTFC(Minecraft.func_71410_x(), Minecraft.func_71410_x().func_110442_L());
/* 179 */     IReloadableResourceManager iRRM = (IReloadableResourceManager)Minecraft.func_71410_x().func_110442_L();
/* 180 */     iRRM.func_110542_a((IResourceManagerReloadListener)new GrassColorReloadListener());
/* 181 */     iRRM.func_110542_a((IResourceManagerReloadListener)new FoliageColorReloadListener());
/* 182 */     iRRM.func_110542_a((IResourceManagerReloadListener)(Minecraft.func_71410_x()).field_71460_t);
/*     */ 
/*     */ 
/*     */     
/* 186 */     RenderingRegistry.registerEntityRenderingHandler(EntityJavelin.class, (Render)new RenderTerraJavelin());
/* 187 */     RenderingRegistry.registerEntityRenderingHandler(EntitySquidTFC.class, (Render)new RenderSquidTFC((ModelBase)new ModelSquidTFC(), 0.7F));
/*     */ 
/*     */     
/* 190 */     RenderingRegistry.registerEntityRenderingHandler(EntityWolfTFC.class, (Render)new RenderWolfTFC((ModelBase)new ModelWolfTFC(), (ModelBase)new ModelWolfTFC(), 0.5F));
/* 191 */     RenderingRegistry.registerEntityRenderingHandler(EntityBear.class, (Render)new RenderBear((ModelBase)new ModelBear(), 0.9F));
/* 192 */     RenderingRegistry.registerEntityRenderingHandler(EntityPheasantTFC.class, (Render)new RenderPheasantTFC((ModelBase)new ModelPheasant(), 0.3F));
/* 193 */     RenderingRegistry.registerEntityRenderingHandler(EntityChickenTFC.class, (Render)new RenderChickenTFC((ModelBase)new ModelChickenTFC(), 0.3F));
/*     */     
/* 195 */     RenderingRegistry.registerEntityRenderingHandler(EntityDeer.class, (Render)new RenderDeer((ModelBase)new ModelDeer(), 0.9F));
/* 196 */     RenderingRegistry.registerEntityRenderingHandler(EntityHorseTFC.class, (Render)new RenderHorseTFC((ModelBase)new ModelHorseTFC(), 0.9F));
/* 197 */     RenderingRegistry.registerEntityRenderingHandler(EntityCustomMinecart.class, (Render)new RenderMinecart());
/* 198 */     RenderingRegistry.registerEntityRenderingHandler(EntityStand.class, (Render)new RenderEntityStand());
/* 199 */     RenderingRegistry.registerEntityRenderingHandler(EntityFishTFC.class, (Render)new RenderFishTFC((ModelBase)new ModelBass(), 0.7F));
/* 200 */     RenderingRegistry.registerEntityRenderingHandler(EntitySkeletonTFC.class, (Render)new RenderSkeletonTFC());
/* 201 */     RenderingRegistry.registerEntityRenderingHandler(EntityZombieTFC.class, (Render)new RenderZombie());
/* 202 */     RenderingRegistry.registerEntityRenderingHandler(EntitySpiderTFC.class, (Render)new RenderSpider());
/* 203 */     RenderingRegistry.registerEntityRenderingHandler(EntitySlimeTFC.class, (Render)new RenderSlime((ModelBase)new ModelSlime(16), (ModelBase)new ModelSlime(0), 0.25F));
/* 204 */     RenderingRegistry.registerEntityRenderingHandler(EntitySilverfishTFC.class, (Render)new RenderSilverfish());
/* 205 */     RenderingRegistry.registerEntityRenderingHandler(EntityGhastTFC.class, (Render)new RenderGhast());
/* 206 */     RenderingRegistry.registerEntityRenderingHandler(EntityCaveSpiderTFC.class, (Render)new RenderSpider());
/* 207 */     RenderingRegistry.registerEntityRenderingHandler(EntityBlazeTFC.class, (Render)new RenderBlaze());
/* 208 */     RenderingRegistry.registerEntityRenderingHandler(EntityEndermanTFC.class, (Render)new RenderEnderman());
/* 209 */     RenderingRegistry.registerEntityRenderingHandler(EntityPigZombieTFC.class, (Render)new RenderZombie());
/* 210 */     RenderingRegistry.registerEntityRenderingHandler(EntityIronGolemTFC.class, (Render)new RenderIronGolem());
/*     */     
/* 212 */     RenderingRegistry.registerEntityRenderingHandler(EntityProjectileTFC.class, (Render)new RenderArrow());
/* 213 */     RenderingRegistry.registerEntityRenderingHandler(EntityFishHookTFC.class, (Render)new RenderFish());
/* 214 */     RenderingRegistry.registerEntityRenderingHandler(EntityBarrel.class, (Render)new RenderBarrelEntity());
/* 215 */     RenderingRegistry.registerEntityRenderingHandler(EntityFallingBlockTFC.class, (Render)new RenderFallingBlock());
/*     */     
/* 217 */     RenderingRegistry.registerBlockHandler(TFCBlocks.chestRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderChest());
/* 218 */     RenderingRegistry.registerBlockHandler(TFCBlocks.clayGrassRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 219 */     RenderingRegistry.registerBlockHandler(TFCBlocks.peatGrassRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 220 */     RenderingRegistry.registerBlockHandler(TFCBlocks.sulfurRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 221 */     RenderingRegistry.registerBlockHandler(TFCBlocks.woodSupportRenderIdH = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderSupportBeam());
/* 222 */     RenderingRegistry.registerBlockHandler(TFCBlocks.woodSupportRenderIdV = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderSupportBeam());
/* 223 */     RenderingRegistry.registerBlockHandler(TFCBlocks.grassRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 224 */     RenderingRegistry.registerBlockHandler(TFCBlocks.oreRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderOre());
/* 225 */     RenderingRegistry.registerBlockHandler(TFCBlocks.moltenRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 226 */     RenderingRegistry.registerBlockHandler(TFCBlocks.looseRockRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 227 */     RenderingRegistry.registerBlockHandler(TFCBlocks.firepitRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 228 */     RenderingRegistry.registerBlockHandler(TFCBlocks.anvilRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderAnvil());
/* 229 */     RenderingRegistry.registerBlockHandler(TFCBlocks.bellowsRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderBellows());
/* 230 */     RenderingRegistry.registerBlockHandler(TFCBlocks.forgeRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 231 */     RenderingRegistry.registerBlockHandler(TFCBlocks.sluiceRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 232 */     RenderingRegistry.registerBlockHandler(TFCBlocks.woodFruitRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 233 */     RenderingRegistry.registerBlockHandler(TFCBlocks.leavesFruitRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 234 */     RenderingRegistry.registerBlockHandler(TFCBlocks.stairRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 235 */     RenderingRegistry.registerBlockHandler(TFCBlocks.slabRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 236 */     RenderingRegistry.registerBlockHandler(TFCBlocks.cropRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 237 */     RenderingRegistry.registerBlockHandler(TFCBlocks.cookingPitRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 238 */     RenderingRegistry.registerBlockHandler(TFCBlocks.leavesRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 239 */     RenderingRegistry.registerBlockHandler(TFCBlocks.detailedRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/* 240 */     RenderingRegistry.registerBlockHandler(TFCBlocks.wallRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderWall());
/* 241 */     RenderingRegistry.registerBlockHandler(TFCBlocks.fenceRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderFence());
/* 242 */     RenderingRegistry.registerBlockHandler(TFCBlocks.fenceGateRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderFenceGate());
/* 243 */     RenderingRegistry.registerBlockHandler(TFCBlocks.toolRackRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderToolRack());
/*     */     
/* 245 */     RenderingRegistry.registerBlockHandler(TFCBlocks.quernRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new TESRQuern());
/* 246 */     RenderingRegistry.registerBlockHandler(TFCBlocks.woodConstructRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderWoodConstruct());
/* 247 */     RenderingRegistry.registerBlockHandler(TFCBlocks.barrelRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderBarrel());
/* 248 */     RenderingRegistry.registerBlockHandler(TFCBlocks.loomRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderLoom());
/* 249 */     RenderingRegistry.registerBlockHandler(TFCBlocks.standRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderStand());
/* 250 */     RenderingRegistry.registerBlockHandler(TFCBlocks.nestBoxRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderNestBox());
/* 251 */     RenderingRegistry.registerBlockHandler(TFCBlocks.potteryRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderPottery());
/* 252 */     RenderingRegistry.registerBlockHandler(TFCBlocks.tuyereRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderTuyere());
/* 253 */     RenderingRegistry.registerBlockHandler(TFCBlocks.crucibleRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderCrucible());
/* 254 */     RenderingRegistry.registerBlockHandler(TFCBlocks.waterPlantRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BlockRenderHandler());
/*     */     
/* 256 */     RenderingRegistry.registerBlockHandler(TFCBlocks.bloomeryRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderBloomery());
/* 257 */     RenderingRegistry.registerBlockHandler(TFCBlocks.metalsheetRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderMetalSheet());
/* 258 */     RenderingRegistry.registerBlockHandler(TFCBlocks.leatherRackRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderLeatherRack());
/* 259 */     RenderingRegistry.registerBlockHandler(TFCBlocks.grillRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderGrill());
/* 260 */     RenderingRegistry.registerBlockHandler(TFCBlocks.metalTrapDoorRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderMetalTrapDoor());
/* 261 */     RenderingRegistry.registerBlockHandler(TFCBlocks.vesselRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderVessel());
/* 262 */     RenderingRegistry.registerBlockHandler(TFCBlocks.torchRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderTorch());
/* 263 */     RenderingRegistry.registerBlockHandler(TFCBlocks.smokeRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderSmoke());
/* 264 */     RenderingRegistry.registerBlockHandler(TFCBlocks.smokeRackRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderSmokeRack());
/* 265 */     RenderingRegistry.registerBlockHandler(TFCBlocks.oilLampRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderOilLamp());
/* 266 */     RenderingRegistry.registerBlockHandler(TFCBlocks.hopperRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderHopper());
/* 267 */     RenderingRegistry.registerBlockHandler(TFCBlocks.flowerPotRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderFlowerPot());
/*     */     
/* 269 */     RenderingRegistry.registerBlockHandler(TFCBlocks.wattleRenderId = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new RenderWattle());
/*     */     
/* 271 */     MinecraftForge.EVENT_BUS.register(new RenderOverlayHandler());
/*     */ 
/*     */     
/* 274 */     RenderingRegistry.registerBlockHandler(TerraFirmaCraft.renderfoodprep = RenderingRegistry.getNextAvailableRenderId(), new RenderFoodPrep());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void registerBiomeEventHandler() {
/* 282 */     MinecraftForge.EVENT_BUS.register(new BiomeEventHandler());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void setupGuiIngameForge() {
/* 289 */     GuiIngameForge.renderHealth = false;
/* 290 */     GuiIngameForge.renderArmor = false;
/* 291 */     GuiIngameForge.renderExperiance = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerTileEntities(boolean b) {
/* 298 */     super.registerTileEntities(false);
/* 299 */     ClientRegistry.registerTileEntity(TEChest.class, "chest", (TileEntitySpecialRenderer)new TESRChest());
/* 300 */     ClientRegistry.registerTileEntity(TEIngotPile.class, "ingotPile", (TileEntitySpecialRenderer)new TESRIngotPile());
/* 301 */     ClientRegistry.registerTileEntity(TEFirepit.class, "TerraFirepit", (TileEntitySpecialRenderer)new TESRFirepit());
/* 302 */     ClientRegistry.registerTileEntity(TELoom.class, "Loom", (TileEntitySpecialRenderer)new TESRLoom());
/*     */     
/* 304 */     ClientRegistry.registerTileEntity(TEPottery.class, "Pottery", (TileEntitySpecialRenderer)new TESRPottery());
/* 305 */     ClientRegistry.registerTileEntity(TEFoodPrep.class, "FoodPrep", (TileEntitySpecialRenderer)new TESRFoodPrep());
/* 306 */     ClientRegistry.registerTileEntity(TEBellows.class, "Bellows", (TileEntitySpecialRenderer)new TESRBellows());
/* 307 */     ClientRegistry.registerTileEntity(TEToolRack.class, "ToolRack", (TileEntitySpecialRenderer)new TESRToolrack());
/* 308 */     ClientRegistry.registerTileEntity(TEAnvil.class, "TerraAnvil", (TileEntitySpecialRenderer)new TESRAnvil());
/* 309 */     ClientRegistry.registerTileEntity(TEWorldItem.class, "worldItem", (TileEntitySpecialRenderer)new TESRWorldItem());
/* 310 */     ClientRegistry.registerTileEntity(TEQuern.class, "Quern", (TileEntitySpecialRenderer)new TESRQuern());
/* 311 */     ClientRegistry.registerTileEntity(TEGrill.class, "GrillTESR", (TileEntitySpecialRenderer)new TESRGrill());
/* 312 */     ClientRegistry.registerTileEntity(TESmokeRack.class, "SmokeRackTESR", (TileEntitySpecialRenderer)new TESRSmokeRack());
/* 313 */     ClientRegistry.registerTileEntity(TEHopper.class, "HopperTESR", (TileEntitySpecialRenderer)new TESRHopper());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onClientLogin() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public World getCurrentWorld() {
/* 327 */     return (World)(Minecraft.func_71410_x()).field_71441_e;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRemote() {
/* 333 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int waterColorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 339 */     return 3493173;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int grassColorMultiplier(IBlockAccess par1IBlockAccess, int i, int j, int k) {
/* 345 */     int var5 = 0;
/* 346 */     int var6 = 0;
/* 347 */     int var7 = 0;
/*     */     
/* 349 */     for (int z = -1; z <= 1; z++) {
/*     */       
/* 351 */       for (int x = -1; x <= 1; x++) {
/*     */         
/* 353 */         int var10 = TFC_Climate.getGrassColor(getCurrentWorld(), i + x, j, k + z);
/* 354 */         var5 += (var10 & 0xFF0000) >> 16;
/* 355 */         var6 += (var10 & 0xFF00) >> 8;
/* 356 */         var7 += var10 & 0xFF;
/*     */       } 
/*     */     } 
/* 359 */     return (var5 / 9 & 0xFF) << 16 | (var6 / 9 & 0xFF) << 8 | var7 / 9 & 0xFF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int foliageColorMultiplier(IBlockAccess par1IBlockAccess, int i, int j, int k) {
/* 368 */     int[] rgb = { 0, 0, 0 };
/* 369 */     float temperature = TFC_Climate.getHeightAdjustedTempSpecificDay(getCurrentWorld(), TFC_Time.getDayOfYear(), i, j, k);
/*     */ 
/*     */     
/* 372 */     int meta = par1IBlockAccess.func_72805_g(i, j, k);
/* 373 */     if (par1IBlockAccess.func_147439_a(i, j, k) == TFCBlocks.fruitTreeLeaves) {
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
/* 384 */       for (int m = -1; m <= 1; m++) {
/*     */         
/* 386 */         for (int var9 = -1; var9 <= 1; var9++) {
/*     */           
/* 388 */           int var10 = TFC_Climate.getFoliageColor(getCurrentWorld(), i + m, j, k + var9);
/* 389 */           rgb = applyColor(var10, rgb);
/*     */         } 
/*     */       } 
/* 392 */       return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
/*     */     } 
/* 394 */     if (par1IBlockAccess.func_147439_a(i, j, k) == TFCBlocks.vine) {
/*     */       
/* 396 */       if (TFC_Time.getSeasonAdjustedMonth(k) >= 6 && TFC_Time.getSeasonAdjustedMonth(k) < 9 && 
/* 397 */         (TFC_Climate.getCacheManager(getCurrentWorld()).getEVTLayerAt(i, k)).floatdata1 >= 0.8D && 
/* 398 */         TFC_Climate.getHeightAdjustedTemp(getCurrentWorld(), i, j, k) < 30.0F) {
/*     */         
/* 400 */         int color = 0;
/* 401 */         for (int n = -1; n <= 1; n++) {
/*     */           
/* 403 */           for (int var9 = -1; var9 <= 1; var9++) {
/*     */             
/* 405 */             color = TFC_Climate.getFoliageColor(getCurrentWorld(), i + n, j, k + var9);
/* 406 */             rgb = applyColor(color, rgb);
/*     */           } 
/*     */         } 
/* 409 */         return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
/*     */       } 
/* 411 */       if (TFC_Time.getSeasonAdjustedMonth(k) >= 11 || (TFC_Time.getSeasonAdjustedMonth(k) <= 0 && 
/* 412 */         (TFC_Climate.getCacheManager(getCurrentWorld()).getEVTLayerAt(i, k)).floatdata1 >= 0.8D && 
/* 413 */         TFC_Climate.getHeightAdjustedTemp(getCurrentWorld(), i, j, k) < 30.0F)) {
/*     */         
/* 415 */         for (int n = -1; n <= 1; n++) {
/*     */           
/* 417 */           for (int var9 = -1; var9 <= 1; var9++) {
/*     */             
/* 419 */             int color = TFC_Climate.getFoliageColor(getCurrentWorld(), i + n, j, k + var9);
/* 420 */             rgb = applyColor(color, rgb);
/*     */           } 
/*     */         } 
/* 423 */         return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
/*     */       } 
/* 425 */       if (TFC_Time.getSeasonAdjustedMonth(k) >= 9 && 
/* 426 */         (TFC_Climate.getCacheManager(getCurrentWorld()).getEVTLayerAt(i, k)).floatdata1 >= 0.8D && 
/* 427 */         TFC_Climate.getHeightAdjustedTemp(getCurrentWorld(), i, j, k) < 30.0F) {
/*     */         
/* 429 */         for (int n = -1; n <= 1; n++) {
/*     */           
/* 431 */           for (int var9 = -1; var9 <= 1; var9++) {
/*     */             
/* 433 */             int color = ColorizerFoliageTFC.getFoliageDead();
/* 434 */             rgb = applyColor(color, rgb);
/*     */           } 
/*     */         } 
/* 437 */         return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
/*     */       } 
/*     */ 
/*     */       
/* 441 */       for (int m = -1; m <= 1; m++) {
/*     */         
/* 443 */         for (int var9 = -1; var9 <= 1; var9++) {
/*     */           
/* 445 */           int color = TFC_Climate.getFoliageColor(getCurrentWorld(), i + m, j, k + var9);
/* 446 */           rgb = applyColor(color, rgb);
/*     */         } 
/*     */       } 
/* 449 */       return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
/*     */     } 
/*     */     
/* 452 */     if (TFC_Time.getSeasonAdjustedMonth(k) >= 6 && (EnumTree.values()[meta]).isEvergreen) {
/*     */       
/* 454 */       for (int m = -1; m <= 1; m++) {
/*     */         
/* 456 */         for (int var9 = -1; var9 <= 1; var9++) {
/*     */           
/* 458 */           int var10 = TFC_Climate.getFoliageColorEvergreen(getCurrentWorld(), i + m, j, k + var9);
/* 459 */           rgb = applyColor(var10, rgb);
/*     */         } 
/*     */       } 
/* 462 */       return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
/*     */     } 
/* 464 */     if (temperature <= 10.0F && TFC_Time.getSeasonAdjustedMonth(k) >= 6 && TFC_Time.getSeasonAdjustedMonth(k) < 9 && (meta == 4 || meta == 7 || meta == 5 || meta == 14)) {
/*     */       
/* 466 */       int color = 0;
/*     */       
/* 468 */       for (int m = -1; m <= 1; m++) {
/*     */         
/* 470 */         for (int var9 = -1; var9 <= 1; var9++) {
/*     */           
/* 472 */           color = ColorizerFoliageTFC.getFoliageYellow();
/* 473 */           rgb = applyColor(color, rgb);
/*     */         } 
/*     */       } 
/* 476 */       return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
/*     */     } 
/* 478 */     if (temperature <= 10.0F && TFC_Time.getSeasonAdjustedMonth(k) >= 6 && TFC_Time.getSeasonAdjustedMonth(k) < 9 && meta == 6) {
/*     */       
/* 480 */       for (int m = -1; m <= 1; m++) {
/*     */         
/* 482 */         for (int var9 = -1; var9 <= 1; var9++) {
/*     */           
/* 484 */           int var10 = ColorizerFoliageTFC.getFoliageRed();
/* 485 */           rgb = applyColor(var10, rgb);
/*     */         } 
/*     */       } 
/* 488 */       return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
/*     */     } 
/* 490 */     if (temperature <= 10.0F && TFC_Time.getSeasonAdjustedMonth(k) >= 6 && TFC_Time.getSeasonAdjustedMonth(k) < 9 && meta != 15) {
/*     */       
/* 492 */       for (int m = -1; m <= 1; m++) {
/*     */         
/* 494 */         for (int var9 = -1; var9 <= 1; var9++) {
/*     */           
/* 496 */           int var10 = ColorizerFoliageTFC.getFoliageOrange();
/* 497 */           rgb = applyColor(var10, rgb);
/*     */         } 
/*     */       } 
/* 500 */       return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
/*     */     } 
/* 502 */     if (temperature <= 8.0F && TFC_Time.getSeasonAdjustedMonth(k) >= 6 && meta != 15) {
/*     */       
/* 504 */       for (int m = -1; m <= 1; m++) {
/*     */         
/* 506 */         for (int var9 = -1; var9 <= 1; var9++) {
/*     */           
/* 508 */           int var10 = ColorizerFoliageTFC.getFoliageDead();
/* 509 */           rgb = applyColor(var10, rgb);
/*     */         } 
/*     */       } 
/* 512 */       return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
/*     */     } 
/*     */ 
/*     */     
/* 516 */     for (int var8 = -1; var8 <= 1; var8++) {
/*     */       
/* 518 */       for (int var9 = -1; var9 <= 1; var9++) {
/*     */         
/* 520 */         int var10 = TFC_Climate.getFoliageColor(getCurrentWorld(), i + var8, j, k + var9);
/* 521 */         rgb = applyColor(var10, rgb);
/*     */       } 
/*     */     } 
/* 524 */     return (rgb[0] / 9 & 0xFF) << 16 | (rgb[1] / 9 & 0xFF) << 8 | rgb[2] / 9 & 0xFF;
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
/*     */   private int[] applyColor(int c, int[] rgb) {
/* 536 */     rgb[0] = rgb[0] + ((c & 0xFF0000) >> 16);
/* 537 */     rgb[1] = rgb[1] + ((c & 0xFF00) >> 8);
/* 538 */     rgb[2] = rgb[2] + (c & 0xFF);
/* 539 */     return rgb;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getArmorRenderID(String name) {
/* 545 */     return RenderingRegistry.addNewArmourRendererPrefix(name);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerKeys() {
/* 551 */     KeyBindings.addKeyBinding(KeyBindingHandler.keyToolMode);
/* 552 */     KeyBindings.addIsRepeating(false);
/* 553 */     KeyBindings.addKeyBinding(KeyBindingHandler.keyLockTool);
/* 554 */     KeyBindings.addIsRepeating(false);
/*     */ 
/*     */     
/* 557 */     uploadKeyBindingsToGame();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerKeyBindingHandler() {
/* 563 */     FMLCommonHandler.instance().bus().register(new KeyBindingHandler());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void uploadKeyBindingsToGame() {
/* 569 */     GameSettings settings = (Minecraft.func_71410_x()).field_71474_y;
/* 570 */     KeyBinding[] tfcKeyBindings = KeyBindings.gatherKeyBindings();
/* 571 */     KeyBinding[] allKeys = new KeyBinding[settings.field_74324_K.length + tfcKeyBindings.length];
/* 572 */     System.arraycopy(settings.field_74324_K, 0, allKeys, 0, settings.field_74324_K.length);
/* 573 */     System.arraycopy(tfcKeyBindings, 0, allKeys, settings.field_74324_K.length, tfcKeyBindings.length);
/* 574 */     settings.field_74324_K = allKeys;
/* 575 */     settings.func_74300_a();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerHandlers() {
/* 581 */     MinecraftForge.EVENT_BUS.register(new ChiselHighlightHandler());
/* 582 */     MinecraftForge.EVENT_BUS.register(new FarmlandHighlightHandler());
/* 583 */     MinecraftForge.EVENT_BUS.register(new PlankHighlightHandler());
/* 584 */     MinecraftForge.EVENT_BUS.register(new ArmourStandHighlightHandler());
/* 585 */     MinecraftForge.EVENT_BUS.register(new FamiliarityHighlightHandler());
/* 586 */     MinecraftForge.EVENT_BUS.register(new FogHandler());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerPlayerRenderEventHandler() {
/* 593 */     PlayerRenderHandler pRHandler = new PlayerRenderHandler();
/* 594 */     MinecraftForge.EVENT_BUS.register(pRHandler);
/* 595 */     FMLCommonHandler.instance().bus().register(pRHandler);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerSoundHandler() {
/* 601 */     MinecraftForge.EVENT_BUS.register(new SoundHandler());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerTickHandler() {
/* 607 */     super.registerTickHandler();
/* 608 */     FMLCommonHandler.instance().bus().register(new ClientTickHandler());
/* 609 */     FMLCommonHandler.instance().bus().register(new FMLClientEventHandler());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerGuiHandler() {
/* 615 */     NetworkRegistry.INSTANCE.registerGuiHandler(TerraFirmaCraft.instance, (IGuiHandler)new GuiHandler());
/*     */     
/* 617 */     MinecraftForge.EVENT_BUS.register(new GuiHandler());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCurrentLanguage() {
/* 623 */     return Minecraft.func_71410_x().func_135016_M().func_135041_c().func_135034_a();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getGraphicsLevel() {
/* 629 */     Minecraft.func_71410_x();
/* 630 */     return Minecraft.func_71375_t();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void hideNEIItems() {
/* 636 */     if (Loader.isModLoaded("NotEnoughItems")) NEIIntegration.hideNEIItems(); 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */