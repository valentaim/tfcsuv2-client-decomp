/*     */ package com.bioxx.tfc.Handlers.Client;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.BlockFarmland;
/*     */ import com.bioxx.tfc.Core.Player.PlayerInfo;
/*     */ import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
/*     */ import com.bioxx.tfc.Core.Player.SkillStats;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.Food.CropIndex;
/*     */ import com.bioxx.tfc.Food.CropManager;
/*     */ import com.bioxx.tfc.TileEntities.TECrop;
/*     */ import com.bioxx.tfc.TileEntities.TEFarmland;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.event.DrawBlockHighlightEvent;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FarmlandHighlightHandler
/*     */ {
/*     */   @SubscribeEvent
/*     */   public void drawBlockHighlightEvent(DrawBlockHighlightEvent evt) {
/*  33 */     World world = evt.player.field_70170_p;
/*  34 */     double var8 = evt.player.field_70142_S + (evt.player.field_70165_t - evt.player.field_70142_S) * evt.partialTicks;
/*  35 */     double var10 = evt.player.field_70137_T + (evt.player.field_70163_u - evt.player.field_70137_T) * evt.partialTicks;
/*  36 */     double var12 = evt.player.field_70136_U + (evt.player.field_70161_v - evt.player.field_70136_U) * evt.partialTicks;
/*     */     
/*  38 */     boolean isMetalHoe = false;
/*     */     
/*  40 */     if (evt.currentItem != null && evt.currentItem
/*  41 */       .func_77973_b() != TFCItems.igInHoe && evt.currentItem
/*  42 */       .func_77973_b() != TFCItems.igExHoe && evt.currentItem
/*  43 */       .func_77973_b() != TFCItems.sedHoe && evt.currentItem
/*  44 */       .func_77973_b() != TFCItems.mMHoe)
/*     */     {
/*  46 */       isMetalHoe = true;
/*     */     }
/*     */     
/*  49 */     PlayerManagerTFC manager = PlayerManagerTFC.getInstance();
/*  50 */     PlayerInfo playerInfo = (manager != null) ? manager.getClientPlayer() : null;
/*  51 */     int hoeMode = (playerInfo != null) ? playerInfo.hoeMode : -1;
/*     */     
/*  53 */     if (evt.currentItem != null && evt.currentItem.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemCustomHoe && isMetalHoe && hoeMode == 1) {
/*     */       
/*  55 */       if (TFC_Core.getSkillStats(evt.player) != null) {
/*     */         
/*  57 */         SkillStats.SkillRank sr = TFC_Core.getSkillStats(evt.player).getSkillRank("skill.agriculture");
/*  58 */         if (sr != SkillStats.SkillRank.Expert && sr != SkillStats.SkillRank.Master) {
/*     */           return;
/*     */         }
/*     */       } 
/*  62 */       Block b = world.func_147439_a(evt.target.field_72311_b, evt.target.field_72312_c, evt.target.field_72309_d);
/*  63 */       int crop = 0;
/*  64 */       if (b == TFCBlocks.crops && (world
/*  65 */         .func_147439_a(evt.target.field_72311_b, evt.target.field_72312_c - 1, evt.target.field_72309_d) == TFCBlocks.tilledSoil || world
/*  66 */         .func_147439_a(evt.target.field_72311_b, evt.target.field_72312_c - 1, evt.target.field_72309_d) == TFCBlocks.tilledSoil2)) {
/*     */         
/*  68 */         b = TFCBlocks.tilledSoil;
/*  69 */         crop = 1;
/*     */       } 
/*     */       
/*  72 */       if (b == TFCBlocks.tilledSoil || b == TFCBlocks.tilledSoil2)
/*     */       {
/*  74 */         TEFarmland te = (TEFarmland)world.func_147438_o(evt.target.field_72311_b, evt.target.field_72312_c - crop, evt.target.field_72309_d);
/*  75 */         te.requestNutrientData();
/*     */         
/*  77 */         float timeMultiplier = TFC_Time.daysInYear / 360.0F;
/*  78 */         int soilMax = (int)(25000.0F * timeMultiplier);
/*     */ 
/*     */         
/*  81 */         GL11.glEnable(3042);
/*  82 */         GL11.glBlendFunc(770, 771);
/*     */         
/*  84 */         GL11.glDisable(2884);
/*     */         
/*  86 */         GL11.glDisable(3553);
/*  87 */         GL11.glDepthMask(false);
/*     */         
/*  89 */         double offset = 0.0D;
/*  90 */         double fertilizer = 1.02D + te.nutrients[3] / soilMax * 0.5D;
/*  91 */         GL11.glColor4ub(TFCOptions.cropFertilizerColor[0], TFCOptions.cropFertilizerColor[1], TFCOptions.cropFertilizerColor[2], TFCOptions.cropFertilizerColor[3]);
/*  92 */         drawBox(AxisAlignedBB.func_72330_a(evt.target.field_72311_b + offset, evt.target.field_72312_c + 1.01D - crop, evt.target.field_72309_d, (evt.target.field_72311_b + 1), evt.target.field_72312_c + fertilizer - crop, (evt.target.field_72309_d + 1))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*  99 */             .func_72314_b(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).func_72325_c(-var8, -var10, -var12));
/*     */         
/* 101 */         double nutrient = 1.02D + te.nutrients[0] / soilMax * 0.5D;
/* 102 */         GL11.glColor4ub(TFCOptions.cropNutrientAColor[0], TFCOptions.cropNutrientAColor[1], TFCOptions.cropNutrientAColor[2], TFCOptions.cropNutrientAColor[3]);
/* 103 */         drawBox(AxisAlignedBB.func_72330_a(evt.target.field_72311_b + offset, evt.target.field_72312_c + 1.01D - crop + fertilizer - 1.02D, evt.target.field_72309_d, evt.target.field_72311_b + offset + 0.3333D, evt.target.field_72312_c + nutrient - crop + fertilizer - 1.02D, (evt.target.field_72309_d + 1))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 110 */             .func_72314_b(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).func_72325_c(-var8, -var10, -var12));
/*     */         
/* 112 */         offset = 0.3333D;
/* 113 */         nutrient = 1.02D + te.nutrients[1] / soilMax * 0.5D;
/* 114 */         GL11.glColor4ub(TFCOptions.cropNutrientBColor[0], TFCOptions.cropNutrientBColor[1], TFCOptions.cropNutrientBColor[2], TFCOptions.cropNutrientBColor[3]);
/* 115 */         drawBox(AxisAlignedBB.func_72330_a(evt.target.field_72311_b + offset, evt.target.field_72312_c + 1.01D - crop + fertilizer - 1.02D, evt.target.field_72309_d, evt.target.field_72311_b + offset + 0.3333D, evt.target.field_72312_c + nutrient - crop + fertilizer - 1.02D, (evt.target.field_72309_d + 1))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 122 */             .func_72314_b(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).func_72325_c(-var8, -var10, -var12));
/*     */         
/* 124 */         offset = 0.6666D;
/* 125 */         nutrient = 1.02D + te.nutrients[2] / soilMax * 0.5D;
/* 126 */         GL11.glColor4ub(TFCOptions.cropNutrientCColor[0], TFCOptions.cropNutrientCColor[1], TFCOptions.cropNutrientCColor[2], TFCOptions.cropNutrientCColor[3]);
/* 127 */         drawBox(AxisAlignedBB.func_72330_a(evt.target.field_72311_b + offset, evt.target.field_72312_c + 1.01D - crop + fertilizer - 1.02D, evt.target.field_72309_d, evt.target.field_72311_b + offset + 0.3333D, evt.target.field_72312_c + nutrient - crop + fertilizer - 1.02D, (evt.target.field_72309_d + 1))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 134 */             .func_72314_b(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).func_72325_c(-var8, -var10, -var12));
/*     */         
/* 136 */         GL11.glEnable(2884);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 143 */         GL11.glColor4f(0.1F, 0.1F, 0.1F, 1.0F);
/* 144 */         GL11.glLineWidth(3.0F);
/* 145 */         GL11.glDepthMask(false);
/*     */         
/* 147 */         offset = 0.0D;
/*     */         
/* 149 */         drawOutlinedBoundingBox(AxisAlignedBB.func_72330_a(evt.target.field_72311_b + offset, evt.target.field_72312_c + 1.01D - crop, evt.target.field_72309_d, (evt.target.field_72311_b + 1), evt.target.field_72312_c + fertilizer - crop, (evt.target.field_72309_d + 1))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 156 */             .func_72314_b(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).func_72325_c(-var8, -var10, -var12));
/*     */         
/* 158 */         nutrient = 1.02D + te.nutrients[0] / soilMax * 0.5D;
/* 159 */         drawOutlinedBoundingBox(AxisAlignedBB.func_72330_a(evt.target.field_72311_b + offset, evt.target.field_72312_c + 1.01D - crop + fertilizer - 1.02D, evt.target.field_72309_d, evt.target.field_72311_b + offset + 0.3333D, evt.target.field_72312_c + nutrient - crop + fertilizer - 1.02D, (evt.target.field_72309_d + 1))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 166 */             .func_72314_b(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).func_72325_c(-var8, -var10, -var12));
/*     */         
/* 168 */         offset = 0.3333D;
/* 169 */         nutrient = 1.02D + te.nutrients[1] / soilMax * 0.5D;
/* 170 */         drawOutlinedBoundingBox(AxisAlignedBB.func_72330_a(evt.target.field_72311_b + offset, evt.target.field_72312_c + 1.01D - crop + fertilizer - 1.02D, evt.target.field_72309_d, evt.target.field_72311_b + offset + 0.3333D, evt.target.field_72312_c + nutrient - crop + fertilizer - 1.02D, (evt.target.field_72309_d + 1))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 177 */             .func_72314_b(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).func_72325_c(-var8, -var10, -var12));
/*     */         
/* 179 */         offset = 0.6666D;
/* 180 */         nutrient = 1.02D + te.nutrients[2] / soilMax * 0.5D;
/* 181 */         drawOutlinedBoundingBox(AxisAlignedBB.func_72330_a(evt.target.field_72311_b + offset, evt.target.field_72312_c + 1.01D - crop + fertilizer - 1.02D, evt.target.field_72309_d, evt.target.field_72311_b + offset + 0.3333D, evt.target.field_72312_c + nutrient - crop + fertilizer - 1.02D, (evt.target.field_72309_d + 1))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 188 */             .func_72314_b(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).func_72325_c(-var8, -var10, -var12));
/*     */       }
/*     */     
/* 191 */     } else if (evt.currentItem != null && evt.currentItem.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemCustomHoe && hoeMode == 2) {
/*     */       
/* 193 */       Block b = world.func_147439_a(evt.target.field_72311_b, evt.target.field_72312_c, evt.target.field_72309_d);
/* 194 */       int crop = 0;
/* 195 */       if (b == TFCBlocks.crops && (world
/* 196 */         .func_147439_a(evt.target.field_72311_b, evt.target.field_72312_c - 1, evt.target.field_72309_d) == TFCBlocks.tilledSoil || world
/* 197 */         .func_147439_a(evt.target.field_72311_b, evt.target.field_72312_c - 1, evt.target.field_72309_d) == TFCBlocks.tilledSoil2)) {
/*     */         
/* 199 */         b = TFCBlocks.tilledSoil;
/* 200 */         crop = 1;
/*     */       } 
/*     */       
/* 203 */       if (b == TFCBlocks.tilledSoil || b == TFCBlocks.tilledSoil2)
/*     */       {
/* 205 */         boolean water = BlockFarmland.isFreshWaterNearby(world, evt.target.field_72311_b, evt.target.field_72312_c - crop, evt.target.field_72309_d);
/*     */         
/* 207 */         GL11.glEnable(3042);
/* 208 */         GL11.glBlendFunc(770, 771);
/* 209 */         if (water) {
/* 210 */           GL11.glColor4ub((byte)14, (byte)23, (byte)-44, (byte)-56);
/*     */         } else {
/* 212 */           GL11.glColor4ub((byte)0, (byte)0, (byte)0, (byte)-56);
/* 213 */         }  GL11.glDisable(2884);
/*     */         
/* 215 */         GL11.glDisable(3553);
/* 216 */         GL11.glDepthMask(false);
/*     */         
/* 218 */         drawFace(AxisAlignedBB.func_72330_a(evt.target.field_72311_b, evt.target.field_72312_c + 1.01D - crop, evt.target.field_72309_d, (evt.target.field_72311_b + 1), evt.target.field_72312_c + 1.02D - crop, (evt.target.field_72309_d + 1))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 225 */             .func_72314_b(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).func_72325_c(-var8, -var10, -var12));
/*     */         
/* 227 */         GL11.glEnable(2884);
/*     */       }
/*     */     
/* 230 */     } else if (evt.currentItem != null && evt.currentItem.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemCustomHoe && hoeMode == 3) {
/*     */       
/* 232 */       Block b = world.func_147439_a(evt.target.field_72311_b, evt.target.field_72312_c, evt.target.field_72309_d);
/* 233 */       if (b == TFCBlocks.crops && (world
/* 234 */         .func_147439_a(evt.target.field_72311_b, evt.target.field_72312_c - 1, evt.target.field_72309_d) == TFCBlocks.tilledSoil || world
/* 235 */         .func_147439_a(evt.target.field_72311_b, evt.target.field_72312_c - 1, evt.target.field_72309_d) == TFCBlocks.tilledSoil2)) {
/*     */         
/* 237 */         TECrop te = (TECrop)world.func_147438_o(evt.target.field_72311_b, evt.target.field_72312_c, evt.target.field_72309_d);
/* 238 */         CropIndex index = CropManager.getInstance().getCropFromId(te.cropId);
/* 239 */         boolean fullyGrown = (index instanceof com.bioxx.tfc.Food.CropIndexPepper) ? ((te.growth >= (index.numGrowthStages - 1))) : ((te.growth >= index.numGrowthStages));
/*     */         
/* 241 */         GL11.glEnable(3042);
/* 242 */         GL11.glBlendFunc(770, 771);
/* 243 */         if (fullyGrown) {
/* 244 */           GL11.glColor4ub((byte)64, (byte)-56, (byte)37, (byte)-56);
/*     */         } else {
/* 246 */           GL11.glColor4ub((byte)-56, (byte)37, (byte)37, (byte)-56);
/* 247 */         }  GL11.glDisable(2884);
/*     */         
/* 249 */         GL11.glDisable(3553);
/* 250 */         GL11.glDepthMask(false);
/*     */         
/* 252 */         drawFace(AxisAlignedBB.func_72330_a(evt.target.field_72311_b, evt.target.field_72312_c + 0.01D, evt.target.field_72309_d, (evt.target.field_72311_b + 1), evt.target.field_72312_c + 0.02D, (evt.target.field_72309_d + 1))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 259 */             .func_72314_b(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).func_72325_c(-var8, -var10, -var12));
/*     */         
/* 261 */         GL11.glEnable(2884);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawFace(AxisAlignedBB par1AxisAlignedBB) {
/* 268 */     Tessellator var2 = Tessellator.field_78398_a;
/*     */ 
/*     */     
/* 271 */     var2.func_78371_b(7);
/* 272 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 273 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 274 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 275 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 276 */     var2.func_78381_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawBox(AxisAlignedBB par1AxisAlignedBB) {
/* 281 */     Tessellator var2 = Tessellator.field_78398_a;
/*     */ 
/*     */     
/* 284 */     var2.func_78371_b(7);
/* 285 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 286 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 287 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 288 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 289 */     var2.func_78381_a();
/*     */ 
/*     */     
/* 292 */     var2.func_78371_b(7);
/* 293 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 294 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 295 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 296 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/* 297 */     var2.func_78381_a();
/*     */ 
/*     */     
/* 300 */     var2.func_78371_b(7);
/* 301 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/* 302 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 303 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 304 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 305 */     var2.func_78381_a();
/*     */ 
/*     */     
/* 308 */     var2.func_78371_b(7);
/* 309 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 310 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 311 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 312 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 313 */     var2.func_78381_a();
/*     */ 
/*     */     
/* 316 */     var2.func_78371_b(7);
/* 317 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/* 318 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 319 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 320 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/* 321 */     var2.func_78381_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawOutlinedBoundingBox(AxisAlignedBB par1AxisAlignedBB) {
/* 326 */     Tessellator var2 = Tessellator.field_78398_a;
/* 327 */     var2.func_78371_b(3);
/* 328 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 329 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 330 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/* 331 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/* 332 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 333 */     var2.func_78381_a();
/* 334 */     var2.func_78371_b(3);
/* 335 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 336 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 337 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 338 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 339 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 340 */     var2.func_78381_a();
/* 341 */     var2.func_78371_b(1);
/* 342 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 343 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 344 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 345 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 346 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/* 347 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 348 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/* 349 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 350 */     var2.func_78381_a();
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Client\FarmlandHighlightHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */