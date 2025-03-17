/*     */ package com.bioxx.tfc.Render.Item;
/*     */ 
/*     */ import com.bioxx.tfc.Food.ItemFoodTFC;
/*     */ import com.bioxx.tfc.api.Food;
/*     */ import com.bioxx.tfc.api.Interfaces.IFood;
/*     */ import com.bioxx.tfc.api.TFC_ItemHeat;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraftforge.client.IItemRenderer;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FoodItemRenderer
/*     */   implements IItemRenderer
/*     */ {
/*     */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
/*  21 */     return (type == IItemRenderer.ItemRenderType.INVENTORY);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
/*  26 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack is, Object... data) {
/*  32 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  33 */     GL11.glPushAttrib(8192);
/*  34 */     GL11.glEnable(2929);
/*  35 */     GL11.glEnable(3042);
/*  36 */     GL11.glBlendFunc(770, 771);
/*  37 */     if (is.func_77973_b() instanceof IFood && is.func_77942_o()) {
/*     */       
/*  39 */       renderIcon(0, 0, is.func_77973_b().func_77650_f(is), 16, 16);
/*  40 */       float decayPerc = Math.max(Food.getDecay(is) / Food.getWeight(is), 0.0F);
/*  41 */       float cookPerc = Math.max(Math.min(Food.getCooked(is) / 600.0F, 1.0F), 0.0F);
/*  42 */       if (is.func_77973_b() instanceof ItemFoodTFC) {
/*     */         
/*  44 */         int color = Food.getCookedColorMultiplier(is);
/*  45 */         GL11.glColor4f(((color & 0xFF0000) >> 16) / 255.0F, ((color & 0xFF00) >> 8) / 255.0F, (color & 0xFF) / 255.0F, cookPerc);
/*  46 */         if (((ItemFoodTFC)is.func_77973_b()).cookedIcon != null) {
/*  47 */           renderIcon(0, 0, ((ItemFoodTFC)is.func_77973_b()).cookedIcon, 16, 16);
/*     */         } else {
/*  49 */           renderIcon(0, 0, is.func_77973_b().func_77650_f(is), 16, 16);
/*     */         } 
/*  51 */       }  GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  52 */       float decayTop = decayPerc * 13.0F;
/*     */       
/*  54 */       if (type == IItemRenderer.ItemRenderType.INVENTORY) {
/*     */         
/*  56 */         if (TFC_ItemHeat.hasTemp(is)) {
/*     */           
/*  58 */           float meltTemp = TFC_ItemHeat.isCookable(is);
/*  59 */           float temp = TFC_ItemHeat.getTemp(is);
/*  60 */           if (temp > 0.0F && temp < meltTemp) {
/*     */             
/*  62 */             renderQuad(1.0D, 1.0D, 13.0D, 1.0D, 0);
/*     */             
/*  64 */             float tempValue = 13.0F / meltTemp * temp;
/*  65 */             if (tempValue < 0.0F) tempValue = 0.0F; 
/*  66 */             if (tempValue > 13.0F) tempValue = 13.0F;
/*     */             
/*  68 */             if (temp < meltTemp * 0.1F) {
/*  69 */               renderQuad(1.0D, 1.0D, tempValue, 1.0D, 16777215);
/*  70 */             } else if (temp < meltTemp * 0.4F) {
/*  71 */               renderQuad(1.0D, 1.0D, tempValue, 1.0D, 16744448);
/*  72 */             } else if (temp < meltTemp * 0.8F) {
/*  73 */               renderQuad(1.0D, 1.0D, tempValue, 1.0D, 16736256);
/*     */             } else {
/*  75 */               renderQuad(1.0D, 1.0D, tempValue, 1.0D, 16711680);
/*     */             } 
/*     */           } 
/*     */         } 
/*  79 */         float weightPerc = Food.getWeight(is) / ((IFood)is.func_77973_b()).getFoodMaxWeight(is);
/*     */         
/*  81 */         if (weightPerc <= 1.0F)
/*     */         {
/*  83 */           if (((IFood)is.func_77973_b()).renderDecay())
/*     */           {
/*  85 */             if (decayPerc < 0.1D) {
/*     */               
/*  87 */               decayTop *= 10.0F;
/*  88 */               renderQuad(1.0D, 13.0D, (13.0F - decayTop), 1.0D, 65280);
/*     */             } else {
/*     */               
/*  91 */               renderQuad(1.0D, 13.0D, (13.0F - decayTop), 1.0D, 16711680);
/*     */             }  } 
/*  93 */           if (((IFood)is.func_77973_b()).renderWeight())
/*     */           {
/*  95 */             renderQuad(1.0D, 14.0D, 13.0D, 1.0D, 0);
/*  96 */             float weightTop = weightPerc * 13.0F;
/*     */             
/*  98 */             renderQuad(1.0D, 14.0D, weightTop, 1.0D, 16777215);
/*     */           }
/*     */         
/*     */         }
/*     */       
/*     */       } 
/* 104 */     } else if (is.func_77973_b() instanceof IFood) {
/*     */       
/* 106 */       renderIcon(0, 0, is.func_77973_b().func_77650_f(is), 16, 16);
/*     */     } 
/*     */     
/* 109 */     GL11.glPopAttrib();
/* 110 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderIcon(int x, int y, IIcon icon, int sizeX, int sizeY) {
/* 115 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 116 */     tessellator.func_78382_b();
/* 117 */     tessellator.func_78374_a((x + 0), (y + sizeY), 0.0D, icon.func_94209_e(), icon.func_94210_h());
/* 118 */     tessellator.func_78374_a((x + sizeX), (y + sizeY), 0.0D, icon.func_94212_f(), icon.func_94210_h());
/* 119 */     tessellator.func_78374_a((x + sizeX), (y + 0), 0.0D, icon.func_94212_f(), icon.func_94206_g());
/* 120 */     tessellator.func_78374_a((x + 0), (y + 0), 0.0D, icon.func_94209_e(), icon.func_94206_g());
/* 121 */     tessellator.func_78381_a();
/*     */   }
/*     */ 
/*     */   
/*     */   private static void renderQuad(double x, double y, double sizeX, double sizeY, int color) {
/* 126 */     GL11.glDisable(3553);
/* 127 */     Tessellator tess = Tessellator.field_78398_a;
/* 128 */     tess.func_78382_b();
/* 129 */     tess.func_78378_d(color);
/* 130 */     tess.func_78377_a(x + 0.0D, y + 0.0D, 0.0D);
/* 131 */     tess.func_78377_a(x + 0.0D, y + sizeY, 0.0D);
/* 132 */     tess.func_78377_a(x + sizeX, y + sizeY, 0.0D);
/* 133 */     tess.func_78377_a(x + sizeX, y + 0.0D, 0.0D);
/* 134 */     tess.func_78381_a();
/* 135 */     GL11.glEnable(3553);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Item\FoodItemRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */