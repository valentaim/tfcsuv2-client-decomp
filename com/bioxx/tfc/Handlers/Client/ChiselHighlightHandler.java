/*     */ package com.bioxx.tfc.Handlers.Client;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
/*     */ import com.bioxx.tfc.api.Tools.ChiselManager;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.event.DrawBlockHighlightEvent;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChiselHighlightHandler
/*     */ {
/*     */   @SubscribeEvent
/*     */   public void drawBlockHighlightEvent(DrawBlockHighlightEvent evt) {
/*  24 */     EntityPlayer player = evt.player;
/*  25 */     World world = player.field_70170_p;
/*     */     
/*  27 */     if (evt.currentItem != null && evt.currentItem.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemChisel) {
/*     */       
/*  29 */       boolean hasHammer = false;
/*  30 */       for (int i = 0; i < 9; i++) {
/*     */         
/*  32 */         if (player.field_71071_by.field_70462_a[i] != null && player.field_71071_by.field_70462_a[i].func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemHammer) {
/*     */           
/*  34 */           hasHammer = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*  39 */       if (hasHammer) {
/*     */         
/*  41 */         MovingObjectPosition target = evt.target;
/*  42 */         Block id = world.func_147439_a(target.field_72311_b, target.field_72312_c, target.field_72309_d);
/*  43 */         int mode = (PlayerManagerTFC.getInstance().getClientPlayer()).chiselMode;
/*     */ 
/*     */         
/*  46 */         if (mode > -1) {
/*     */ 
/*     */           
/*  49 */           double hitX = Math.round((target.field_72307_f.field_72450_a - target.field_72311_b) * 100.0D) / 100.0D;
/*  50 */           double hitY = Math.round((target.field_72307_f.field_72448_b - target.field_72312_c) * 100.0D) / 100.0D;
/*  51 */           double hitZ = Math.round((target.field_72307_f.field_72449_c - target.field_72309_d) * 100.0D) / 100.0D;
/*     */           
/*  53 */           ChiselManager.getInstance().setDivision(mode, target.field_72310_e);
/*  54 */           int divX = ChiselManager.getInstance().getDivX(mode, id);
/*  55 */           int divY = ChiselManager.getInstance().getDivY(mode, id);
/*  56 */           int divZ = ChiselManager.getInstance().getDivZ(mode, id);
/*     */           
/*  58 */           if (divX > 0) {
/*     */ 
/*     */ 
/*     */             
/*  62 */             double subX = (int)(hitX * divX) / divX;
/*  63 */             double subY = (int)(hitY * divY) / divY;
/*  64 */             double subZ = (int)(hitZ * divZ) / divZ;
/*     */             
/*  66 */             switch (target.field_72310_e) {
/*     */               
/*     */               case 1:
/*  69 */                 subY -= 1.0D / divY;
/*  70 */                 if (hitX == 1.0D)
/*  71 */                   subX -= 1.0D / divX; 
/*  72 */                 if (hitZ == 1.0D)
/*  73 */                   subZ -= 1.0D / divZ; 
/*     */                 break;
/*     */               case 3:
/*  76 */                 subZ -= 1.0D / divZ;
/*  77 */                 if (hitX == 1.0D)
/*  78 */                   subX -= 1.0D / divX; 
/*  79 */                 if (hitY == 1.0D)
/*  80 */                   subY -= 1.0D / divY; 
/*     */                 break;
/*     */               case 5:
/*  83 */                 subX -= 1.0D / divX;
/*  84 */                 if (hitY == 1.0D)
/*  85 */                   subY -= 1.0D / divY; 
/*  86 */                 if (hitZ == 1.0D) {
/*  87 */                   subZ -= 1.0D / divZ;
/*     */                 }
/*     */                 break;
/*     */             } 
/*     */             
/*  92 */             double minX = target.field_72311_b + subX;
/*  93 */             double minY = target.field_72312_c + subY;
/*  94 */             double minZ = target.field_72309_d + subZ;
/*  95 */             double maxX = minX + 1.0D / divX;
/*  96 */             double maxY = minY + 1.0D / divY;
/*  97 */             double maxZ = minZ + 1.0D / divZ;
/*     */             
/*  99 */             double var8 = player.field_70142_S + (player.field_70165_t - player.field_70142_S) * evt.partialTicks;
/* 100 */             double var10 = player.field_70137_T + (player.field_70163_u - player.field_70137_T) * evt.partialTicks;
/* 101 */             double var12 = player.field_70136_U + (player.field_70161_v - player.field_70136_U) * evt.partialTicks;
/*     */ 
/*     */             
/* 104 */             GL11.glEnable(3042);
/* 105 */             GL11.glBlendFunc(1, 1);
/* 106 */             GL11.glDisable(3553);
/* 107 */             GL11.glDepthMask(false);
/*     */ 
/*     */             
/* 110 */             GL11.glBlendFunc(770, 771);
/* 111 */             GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.4F);
/* 112 */             GL11.glLineWidth(4.0F);
/* 113 */             GL11.glDepthMask(true);
/*     */             
/* 115 */             drawOutlinedBoundingBox(AxisAlignedBB.func_72330_a(minX, minY, minZ, maxX, maxY, maxZ).func_72314_b(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).func_72325_c(-var8, -var10, -var12));
/*     */             
/* 117 */             GL11.glDepthMask(true);
/* 118 */             GL11.glEnable(3553);
/* 119 */             GL11.glDisable(3042);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawOutlinedBoundingBox(AxisAlignedBB par1AxisAlignedBB) {
/* 192 */     Tessellator var2 = Tessellator.field_78398_a;
/* 193 */     var2.func_78371_b(3);
/* 194 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 195 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 196 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/* 197 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/* 198 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 199 */     var2.func_78381_a();
/* 200 */     var2.func_78371_b(3);
/* 201 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 202 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 203 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 204 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 205 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 206 */     var2.func_78381_a();
/* 207 */     var2.func_78371_b(1);
/* 208 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 209 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 210 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 211 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 212 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/* 213 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 214 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/* 215 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 216 */     var2.func_78381_a();
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Client\ChiselHighlightHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */