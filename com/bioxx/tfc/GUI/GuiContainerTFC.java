/*     */ package com.bioxx.tfc.GUI;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Player.PlayerInventory;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiContainerTFC
/*     */   extends GuiContainer
/*     */ {
/*     */   protected boolean drawInventory = true;
/*     */   protected Slot activeSlot;
/*     */   
/*     */   public GuiContainerTFC(Container container, int xsize, int ysize) {
/*  27 */     super(container);
/*  28 */     this.field_146999_f = xsize;
/*  29 */     this.field_147000_g = ysize + PlayerInventory.invYSize;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setDrawInventory(boolean b) {
/*  34 */     if (!this.drawInventory && b) {
/*  35 */       this.field_147000_g += PlayerInventory.invYSize;
/*  36 */     } else if (this.drawInventory && !b) {
/*  37 */       this.field_147000_g -= PlayerInventory.invYSize;
/*  38 */     }  this.drawInventory = b;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73863_a(int par1, int par2, float par3) {
/*     */     try {
/*  45 */       super.func_73863_a(par1, par2, par3);
/*  46 */       for (int j1 = 0; j1 < this.field_147002_h.field_75151_b.size(); j1++) {
/*     */         
/*  48 */         Slot slot = this.field_147002_h.field_75151_b.get(j1);
/*  49 */         if (func_146981_a(slot, par1, par2) && slot.func_111238_b())
/*  50 */           this.activeSlot = slot; 
/*     */       } 
/*  52 */     } catch (NullPointerException e) {
/*  53 */       this.field_146297_k.field_71439_g.func_71053_j();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_146981_a(Slot par1Slot, int par2, int par3) {
/*  59 */     return func_146978_c(par1Slot.field_75223_e, par1Slot.field_75221_f, 16, 16, par2, par3);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146976_a(float f, int i, int j) {
/*  65 */     drawGui((ResourceLocation)null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawGui(ResourceLocation rl) {
/*  70 */     if (rl != null) {
/*     */       
/*  72 */       bindTexture(rl);
/*  73 */       this.field_147003_i = (this.field_146294_l - this.field_146999_f) / 2;
/*  74 */       this.field_147009_r = (this.field_146295_m - this.field_147000_g) / 2;
/*  75 */       int height = this.drawInventory ? getShiftedYSize() : this.field_147000_g;
/*     */       
/*  77 */       func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, this.field_146999_f, height);
/*     */       
/*  79 */       drawForeground(this.field_147003_i, this.field_147009_r);
/*     */     } 
/*  81 */     if (this.drawInventory) {
/*  82 */       PlayerInventory.drawInventory(this, this.field_146294_l, this.field_146295_m, getShiftedYSize());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawForeground(int guiLeft, int guiTop) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean mouseInRegion(int x, int y, int width, int height, int mouseX, int mouseY) {
/*  96 */     mouseX -= this.field_147003_i;
/*  97 */     mouseY -= this.field_147009_r;
/*  98 */     return (mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void bindTexture(ResourceLocation rl) {
/* 103 */     TFC_Core.bindTexture(rl);
/* 104 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawTooltip(int mx, int my, String text) {
/* 109 */     List<String> list = new ArrayList<>();
/* 110 */     list.add(text);
/* 111 */     drawHoveringText(list, mx, my + 15, this.field_146289_q);
/* 112 */     RenderHelper.func_74518_a();
/* 113 */     GL11.glDisable(2896);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawHoveringTextZLevel(List<String> par1List, int par2, int par3, FontRenderer font, float z) {
/* 119 */     if (!par1List.isEmpty()) {
/*     */       
/* 121 */       GL11.glDisable(32826);
/* 122 */       RenderHelper.func_74518_a();
/* 123 */       GL11.glDisable(2896);
/*     */       
/* 125 */       int k = 0;
/* 126 */       Iterator<String> iterator = par1List.iterator();
/*     */       
/* 128 */       while (iterator.hasNext()) {
/*     */         
/* 130 */         String s = iterator.next();
/* 131 */         int l = font.func_78256_a(s);
/* 132 */         if (l > k) {
/* 133 */           k = l;
/*     */         }
/*     */       } 
/* 136 */       int i1 = par2 + 12;
/* 137 */       int j1 = par3 - 12;
/* 138 */       int k1 = 8;
/*     */       
/* 140 */       if (par1List.size() > 1) {
/* 141 */         k1 += 2 + (par1List.size() - 1) * 10;
/*     */       }
/* 143 */       if (i1 + k > this.field_146294_l) {
/* 144 */         i1 -= 28 + k;
/*     */       }
/* 146 */       if (j1 + k1 + 6 > this.field_146295_m) {
/* 147 */         j1 = this.field_146295_m - k1 - 6;
/*     */       }
/* 149 */       this.field_73735_i = z;
/* 150 */       field_146296_j.field_77023_b = 300.0F;
/* 151 */       int l1 = -267386864;
/* 152 */       func_73733_a(i1 - 3, j1 - 4, i1 + k + 3, j1 - 3, l1, l1);
/* 153 */       func_73733_a(i1 - 3, j1 + k1 + 3, i1 + k + 3, j1 + k1 + 4, l1, l1);
/* 154 */       func_73733_a(i1 - 3, j1 - 3, i1 + k + 3, j1 + k1 + 3, l1, l1);
/* 155 */       func_73733_a(i1 - 4, j1 - 3, i1 - 3, j1 + k1 + 3, l1, l1);
/* 156 */       func_73733_a(i1 + k + 3, j1 - 3, i1 + k + 4, j1 + k1 + 3, l1, l1);
/* 157 */       int i2 = 1347420415;
/* 158 */       int j2 = (i2 & 0xFEFEFE) >> 1 | i2 & 0xFF000000;
/* 159 */       func_73733_a(i1 - 3, j1 - 3 + 1, i1 - 3 + 1, j1 + k1 + 3 - 1, i2, j2);
/* 160 */       func_73733_a(i1 + k + 2, j1 - 3 + 1, i1 + k + 3, j1 + k1 + 3 - 1, i2, j2);
/* 161 */       func_73733_a(i1 - 3, j1 - 3, i1 + k + 3, j1 - 3 + 1, i2, i2);
/* 162 */       func_73733_a(i1 - 3, j1 + k1 + 2, i1 + k + 3, j1 + k1 + 3, j2, j2);
/*     */       
/* 164 */       for (int k2 = 0; k2 < par1List.size(); k2++) {
/*     */         
/* 166 */         String s1 = par1List.get(k2);
/* 167 */         font.func_78261_a(s1, i1, j1, -1);
/*     */         
/* 169 */         if (k2 == 0)
/* 170 */           j1 += 2; 
/* 171 */         j1 += 10;
/*     */       } 
/*     */       
/* 174 */       this.field_73735_i = 0.0F;
/* 175 */       field_146296_j.field_77023_b = 0.0F;
/* 176 */       GL11.glEnable(2896);
/* 177 */       RenderHelper.func_74519_b();
/* 178 */       GL11.glEnable(32826);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getShiftedYSize() {
/* 184 */     return this.field_147000_g - PlayerInventory.invYSize;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiContainerTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */