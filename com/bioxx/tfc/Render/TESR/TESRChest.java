/*     */ package com.bioxx.tfc.Render.TESR;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockChestTFC;
/*     */ import com.bioxx.tfc.TileEntities.TEChest;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import cpw.mods.fml.common.FMLLog;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.model.ModelChest;
/*     */ import net.minecraft.client.model.ModelLargeChest;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TESRChest
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*     */   private static ResourceLocation[] texNormal;
/*     */   private static ResourceLocation[] texNormalDouble;
/*  24 */   private ModelChest chestModel = new ModelChest();
/*     */ 
/*     */   
/*  27 */   private ModelChest largeChestModel = (ModelChest)new ModelLargeChest();
/*     */ 
/*     */   
/*     */   public TESRChest() {
/*  31 */     if (texNormal == null) {
/*     */       
/*  33 */       texNormal = new ResourceLocation[Global.WOOD_ALL.length];
/*  34 */       texNormalDouble = new ResourceLocation[Global.WOOD_ALL.length];
/*  35 */       for (int i = 0; i < Global.WOOD_ALL.length; i++) {
/*     */         
/*  37 */         texNormal[i] = new ResourceLocation("terrafirmacraft:textures/models/chest/normal_" + Global.WOOD_ALL[i] + ".png");
/*  38 */         texNormalDouble[i] = new ResourceLocation("terrafirmacraft:textures/models/chest/normal_double_" + Global.WOOD_ALL[i] + ".png");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderTileEntityChestAt(TEChest te, double par2, double par4, double par6, float par8) {
/*     */     int i;
/*  50 */     if (!te.func_145830_o()) {
/*     */       
/*  52 */       i = 0;
/*     */     }
/*     */     else {
/*     */       
/*  56 */       Block block = te.func_145838_q();
/*  57 */       i = te.func_145832_p();
/*     */       
/*  59 */       if (block instanceof BlockChestTFC && i == 0) {
/*     */ 
/*     */         
/*     */         try {
/*  63 */           ((BlockChestTFC)block).unifyAdjacentChests(te.func_145831_w(), te.field_145851_c, te.field_145848_d, te.field_145849_e);
/*     */         }
/*  65 */         catch (ClassCastException e) {
/*     */           
/*  67 */           FMLLog.severe("Attempted to render a chest at %d,  %d, %d that was not a chest", new Object[] {
/*  68 */                 Integer.valueOf(te.field_145851_c), Integer.valueOf(te.field_145848_d), Integer.valueOf(te.field_145849_e) });
/*     */         } 
/*  70 */         i = te.func_145832_p();
/*     */       } 
/*     */       
/*  73 */       te.func_145979_i();
/*     */     } 
/*     */     
/*  76 */     if (te.field_145992_i == null && te.field_145991_k == null) {
/*     */       ModelChest modelchest;
/*     */ 
/*     */       
/*  80 */       if (te.field_145990_j == null && te.field_145988_l == null && !te.isDoubleChest) {
/*     */         
/*  82 */         modelchest = this.chestModel;
/*     */         
/*  84 */         func_147499_a(texNormal[te.type]);
/*     */       }
/*     */       else {
/*     */         
/*  88 */         modelchest = this.largeChestModel;
/*     */         
/*  90 */         func_147499_a(texNormalDouble[te.type]);
/*     */       } 
/*     */       
/*  93 */       GL11.glPushMatrix();
/*  94 */       GL11.glEnable(32826);
/*  95 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  96 */       GL11.glTranslatef((float)par2, (float)par4 + 1.0F, (float)par6 + 1.0F);
/*  97 */       GL11.glScalef(1.0F, -1.0F, -1.0F);
/*  98 */       GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*  99 */       short short1 = 0;
/*     */       
/* 101 */       if (i == 2)
/*     */       {
/* 103 */         short1 = 180;
/*     */       }
/*     */       
/* 106 */       if (i == 3)
/*     */       {
/* 108 */         short1 = 0;
/*     */       }
/*     */       
/* 111 */       if (i == 4)
/*     */       {
/* 113 */         short1 = 90;
/*     */       }
/*     */       
/* 116 */       if (i == 5)
/*     */       {
/* 118 */         short1 = -90;
/*     */       }
/*     */       
/* 121 */       if (i == 2 && te.field_145990_j != null)
/*     */       {
/* 123 */         GL11.glTranslatef(1.0F, 0.0F, 0.0F);
/*     */       }
/*     */       
/* 126 */       if (i == 5 && te.field_145988_l != null)
/*     */       {
/* 128 */         GL11.glTranslatef(0.0F, 0.0F, -1.0F);
/*     */       }
/*     */       
/* 131 */       GL11.glRotatef(short1, 0.0F, 1.0F, 0.0F);
/* 132 */       GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/*     */       
/* 134 */       if (te.isDoubleChest)
/*     */       {
/* 136 */         GL11.glScalef(0.5F, 0.5F, 0.5F);
/*     */       }
/*     */       
/* 139 */       float f1 = te.field_145986_n + (te.field_145989_m - te.field_145986_n) * par8;
/*     */ 
/*     */       
/* 142 */       if (te.field_145992_i != null) {
/*     */         
/* 144 */         float f2 = te.field_145992_i.field_145986_n + (te.field_145992_i.field_145989_m - te.field_145992_i.field_145986_n) * par8;
/*     */         
/* 146 */         if (f2 > f1)
/*     */         {
/* 148 */           f1 = f2;
/*     */         }
/*     */       } 
/*     */       
/* 152 */       if (te.field_145991_k != null) {
/*     */         
/* 154 */         float f2 = te.field_145991_k.field_145986_n + (te.field_145991_k.field_145989_m - te.field_145991_k.field_145986_n) * par8;
/*     */         
/* 156 */         if (f2 > f1)
/*     */         {
/* 158 */           f1 = f2;
/*     */         }
/*     */       } 
/*     */       
/* 162 */       f1 = 1.0F - f1;
/* 163 */       f1 = 1.0F - f1 * f1 * f1;
/* 164 */       modelchest.field_78234_a.field_78795_f = -(f1 * 3.1415927F / 2.0F);
/* 165 */       modelchest.func_78231_a();
/* 166 */       GL11.glDisable(32826);
/* 167 */       GL11.glPopMatrix();
/* 168 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
/* 175 */     renderTileEntityChestAt((TEChest)par1TileEntity, par2, par4, par6, par8);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\TESR\TESRChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */