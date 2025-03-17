/*     */ package com.bioxx.tfc.Render.TESR;
/*     */ 
/*     */ import com.bioxx.tfc.TileEntities.TEQuern;
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TESRQuern
/*     */   extends TESRBase
/*     */   implements ISimpleBlockRenderingHandler
/*     */ {
/*  23 */   private static final ResourceLocation BASE_TEXTURE = new ResourceLocation("terrafirmacraft:textures/blocks/devices/Quern Base.png");
/*  24 */   private static final ResourceLocation TOP1_TEXTURE = new ResourceLocation("terrafirmacraft:textures/blocks/devices/Quern Top 1.png");
/*  25 */   private static final ResourceLocation TOP2_TEXTURE = new ResourceLocation("terrafirmacraft:textures/blocks/devices/Quern Top 2.png");
/*  26 */   private static final ResourceLocation WOOD_TEXTURE = new ResourceLocation("terrafirmacraft:textures/blocks/wood/Oak Plank.png");
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_147500_a(TileEntity te, double xDis, double yDis, double zDis, float f) {
/*  31 */     TEQuern teq = (TEQuern)te;
/*     */     
/*  33 */     Tessellator tess = Tessellator.field_78398_a;
/*  34 */     GL11.glPushMatrix();
/*     */     
/*  36 */     GL11.glTranslatef((float)xDis, (float)yDis, (float)zDis);
/*  37 */     renderBase(tess);
/*  38 */     if (teq.hasQuern) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  44 */       renderRoundTop(tess, teq.rotatetimer, (teq.func_145831_w()).field_73012_v, 0.8D, Boolean.valueOf(true));
/*     */ 
/*     */       
/*  47 */       renderWoodHandle(tess, teq.rotatetimer, (teq.func_145831_w()).field_73012_v, 0.8D);
/*     */       
/*  49 */       if (teq.storage[0] != null) renderItem(teq.storage[0]);
/*     */     
/*     */     } 
/*  52 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   private void renderItem(ItemStack is) {
/*  57 */     EntityItem customitem = new EntityItem(this.field_147501_a.field_147550_f);
/*  58 */     float blockScale = 0.7F;
/*  59 */     float timeD = (float)-(360.0D * (System.currentTimeMillis() & 0x3FFFL) / 16383.0D);
/*     */     
/*  61 */     func_147499_a(TextureMap.field_110576_c);
/*  62 */     customitem.func_92058_a(is);
/*  63 */     customitem.field_70290_d = 0.0F;
/*     */     
/*  65 */     GL11.glTranslatef(0.5F, 0.83F, 0.5F);
/*  66 */     GL11.glRotatef(timeD, 0.0F, 1.0F, 0.0F);
/*  67 */     GL11.glScalef(blockScale, blockScale, blockScale);
/*  68 */     itemRenderer.func_76986_a(customitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   private void renderRoundTop(Tessellator t, int pos, Random rand, double angle, Boolean renderSides) {
/*  73 */     int sides = 4;
/*  74 */     double speed = (pos * 4);
/*  75 */     double i = 0.625D;
/*  76 */     double j = i + 0.2D;
/*  77 */     if (!renderSides.booleanValue()) j = i + 0.201D;
/*     */     
/*  79 */     double center = 0.5D;
/*  80 */     double rad = 0.5D;
/*     */ 
/*     */     
/*  83 */     if (pos > 0) {
/*  84 */       center = 0.494D + rand.nextDouble() * 0.006D + 0.003D;
/*     */     }
/*  86 */     for (int l = 0; l < sides; l++) {
/*     */       
/*  88 */       double a = ((l * 360 / sides) + speed + (4 * pos)) * Math.PI / 180.0D;
/*  89 */       double b = (((1 + l) * 360 / sides) + speed + (4 * pos)) * Math.PI / 180.0D;
/*  90 */       double x1 = Math.cos(a + angle) * rad + center;
/*  91 */       double y1 = Math.sin(a + angle) * rad + center;
/*  92 */       double x2 = Math.cos(b + angle) * rad + center;
/*  93 */       double y2 = Math.sin(b + angle) * rad + center;
/*     */ 
/*     */       
/*  96 */       a = (l * 360 / sides) * Math.PI / 180.0D;
/*  97 */       b = ((1 + l) * 360 / sides) * Math.PI / 180.0D;
/*  98 */       double xx1 = Math.cos(a + angle) * rad + center;
/*  99 */       double yy1 = Math.sin(a + angle) * rad + center;
/* 100 */       double xx2 = Math.cos(b + angle) * rad + center;
/* 101 */       double yy2 = Math.sin(b + angle) * rad + center;
/*     */       
/* 103 */       func_147499_a(TOP2_TEXTURE);
/* 104 */       t.func_78371_b(4);
/* 105 */       t.func_78374_a(x1, j, y1, xx1, yy1);
/* 106 */       t.func_78374_a(center, j, center, center, center);
/* 107 */       t.func_78374_a(x2, j, y2, xx2, yy2);
/* 108 */       t.func_78381_a();
/*     */       
/* 110 */       if (renderSides.booleanValue()) {
/*     */         
/* 112 */         func_147499_a(BASE_TEXTURE);
/* 113 */         t.func_78382_b();
/* 114 */         t.func_78374_a(x1, i, y1, 0.73D, 1.0D - j);
/* 115 */         t.func_78374_a(x1, j, y1, 0.73D, 0.0D);
/* 116 */         t.func_78374_a(x2, j, y2, 0.0D, 0.0D);
/* 117 */         t.func_78374_a(x2, i, y2, 0.0D, 1.0D - j);
/* 118 */         t.func_78381_a();
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
/*     */   private void renderWoodHandle(Tessellator t, int pos, Random rand, double angle) {
/* 162 */     double speed = (pos * 4);
/* 163 */     double j = 0.825D;
/* 164 */     double k = j + 0.175D;
/* 165 */     double center = 0.5D;
/* 166 */     double rad = 0.5D;
/*     */ 
/*     */     
/* 169 */     double a = ((pos * 4 - 5) + speed) * Math.PI / 180.0D;
/* 170 */     double a1 = ((pos * 4) - 5.7D + speed) * Math.PI / 180.0D;
/* 171 */     double b = ((pos * 4 + 5) + speed) * Math.PI / 180.0D;
/* 172 */     double b1 = ((pos * 4) + 5.7D + speed) * Math.PI / 180.0D;
/* 173 */     double x1 = Math.cos(a + angle) * (rad - 0.05D) + center;
/* 174 */     double y1 = Math.sin(a + angle) * (rad - 0.05D) + center;
/* 175 */     double xx1 = Math.cos(a1 + angle) * (rad - 0.125D) + center;
/* 176 */     double yy1 = Math.sin(a1 + angle) * (rad - 0.125D) + center;
/* 177 */     double x2 = Math.cos(b + angle) * (rad - 0.05D) + center;
/* 178 */     double y2 = Math.sin(b + angle) * (rad - 0.05D) + center;
/* 179 */     double xx2 = Math.cos(b1 + angle) * (rad - 0.125D) + center;
/* 180 */     double yy2 = Math.sin(b1 + angle) * (rad - 0.125D) + center;
/*     */     
/* 182 */     func_147499_a(WOOD_TEXTURE);
/*     */     
/* 184 */     t.func_78382_b();
/* 185 */     t.func_78374_a(x1, j, y1, 0.0D, 0.0D);
/* 186 */     t.func_78374_a(xx1, j, yy1, 0.0D, 0.2D);
/* 187 */     t.func_78374_a(xx1, k, yy1, 0.2D, 0.2D);
/* 188 */     t.func_78374_a(x1, k, y1, 0.2D, 0.0D);
/* 189 */     t.func_78381_a();
/*     */     
/* 191 */     t.func_78382_b();
/* 192 */     t.func_78374_a(xx1, j, yy1, 0.0D, 0.0D);
/* 193 */     t.func_78374_a(xx2, j, yy2, 0.0D, 0.2D);
/* 194 */     t.func_78374_a(xx2, k, yy2, 0.2D, 0.2D);
/* 195 */     t.func_78374_a(xx1, k, yy1, 0.2D, 0.0D);
/* 196 */     t.func_78381_a();
/*     */     
/* 198 */     t.func_78382_b();
/* 199 */     t.func_78374_a(xx2, j, yy2, 0.0D, 0.0D);
/* 200 */     t.func_78374_a(x2, j, y2, 0.0D, 0.2D);
/* 201 */     t.func_78374_a(x2, k, y2, 0.2D, 0.2D);
/* 202 */     t.func_78374_a(xx2, k, yy2, 0.2D, 0.0D);
/* 203 */     t.func_78381_a();
/*     */     
/* 205 */     t.func_78382_b();
/* 206 */     t.func_78374_a(x2, j, y2, 0.0D, 0.0D);
/* 207 */     t.func_78374_a(x1, j, y1, 0.0D, 0.2D);
/* 208 */     t.func_78374_a(x1, k, y1, 0.2D, 0.2D);
/* 209 */     t.func_78374_a(x2, k, y2, 0.2D, 0.0D);
/* 210 */     t.func_78381_a();
/*     */     
/* 212 */     t.func_78382_b();
/* 213 */     t.func_78374_a(x1, k, y1, 0.0D, 0.0D);
/* 214 */     t.func_78374_a(xx1, k, yy1, 0.0D, 0.2D);
/* 215 */     t.func_78374_a(xx2, k, yy2, 0.2D, 0.2D);
/* 216 */     t.func_78374_a(x2, k, y2, 0.2D, 0.0D);
/* 217 */     t.func_78381_a();
/*     */   }
/*     */ 
/*     */   
/*     */   private void renderBase(Tessellator t) {
/* 222 */     double i = 0.625D;
/* 223 */     func_147499_a(BASE_TEXTURE);
/* 224 */     t.func_78382_b();
/*     */     
/* 226 */     t.func_78374_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D);
/* 227 */     t.func_78374_a(0.0D, i, 0.0D, 1.0D, 1.0D - i);
/* 228 */     t.func_78374_a(1.0D, i, 0.0D, 0.0D, 1.0D - i);
/* 229 */     t.func_78374_a(1.0D, 0.0D, 0.0D, 0.0D, 1.0D);
/*     */     
/* 231 */     t.func_78374_a(1.0D, 0.0D, 0.0D, 1.0D, 1.0D);
/* 232 */     t.func_78374_a(1.0D, i, 0.0D, 1.0D, 1.0D - i);
/* 233 */     t.func_78374_a(1.0D, i, 1.0D, 0.0D, 1.0D - i);
/* 234 */     t.func_78374_a(1.0D, 0.0D, 1.0D, 0.0D, 1.0D);
/*     */     
/* 236 */     t.func_78374_a(1.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 237 */     t.func_78374_a(1.0D, i, 1.0D, 1.0D, 1.0D - i);
/* 238 */     t.func_78374_a(0.0D, i, 1.0D, 0.0D, 1.0D - i);
/* 239 */     t.func_78374_a(0.0D, 0.0D, 1.0D, 0.0D, 1.0D);
/*     */     
/* 241 */     t.func_78374_a(0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 242 */     t.func_78374_a(0.0D, i, 1.0D, 1.0D, 1.0D - i);
/* 243 */     t.func_78374_a(0.0D, i, 0.0D, 0.0D, 1.0D - i);
/* 244 */     t.func_78374_a(0.0D, 0.0D, 0.0D, 0.0D, 1.0D);
/* 245 */     t.func_78381_a();
/*     */     
/* 247 */     func_147499_a(TOP2_TEXTURE);
/* 248 */     t.func_78382_b();
/* 249 */     t.func_78374_a(0.0D, i, 0.0D, 0.0D, 0.0D);
/* 250 */     t.func_78374_a(0.0D, i, 1.0D, 0.0D, 1.0D);
/* 251 */     t.func_78374_a(1.0D, i, 1.0D, 1.0D, 1.0D);
/* 252 */     t.func_78374_a(1.0D, i, 0.0D, 1.0D, 0.0D);
/* 253 */     t.func_78381_a();
/*     */     
/* 255 */     func_147499_a(TOP1_TEXTURE);
/* 256 */     t.func_78382_b();
/* 257 */     t.func_78374_a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/* 258 */     t.func_78374_a(1.0D, 0.0D, 0.0D, 0.0D, 1.0D);
/* 259 */     t.func_78374_a(1.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 260 */     t.func_78374_a(0.0D, 0.0D, 1.0D, 1.0D, 0.0D);
/* 261 */     t.func_78381_a();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean renderWorldBlock(IBlockAccess world, int i, int j, int k, Block block, int modelId, RenderBlocks renderblocks) {
/* 267 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
/* 273 */     renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D);
/* 274 */     Tessellator var14 = Tessellator.field_78398_a;
/* 275 */     var14.func_78382_b();
/* 276 */     var14.func_78375_b(0.0F, 1.0F, 0.0F);
/* 277 */     renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, 1));
/* 278 */     var14.func_78381_a();
/* 279 */     var14.func_78382_b();
/* 280 */     var14.func_78375_b(0.0F, 0.0F, -1.0F);
/* 281 */     renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, 0));
/* 282 */     var14.func_78381_a();
/* 283 */     var14.func_78382_b();
/* 284 */     var14.func_78375_b(0.0F, 0.0F, 1.0F);
/* 285 */     renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, 0));
/* 286 */     var14.func_78381_a();
/* 287 */     var14.func_78382_b();
/* 288 */     var14.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 289 */     renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, 0));
/* 290 */     var14.func_78381_a();
/* 291 */     var14.func_78382_b();
/* 292 */     var14.func_78375_b(1.0F, 0.0F, 0.0F);
/* 293 */     renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, 0));
/* 294 */     var14.func_78381_a();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId) {
/* 300 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderId() {
/* 306 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\TESR\TESRQuern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */