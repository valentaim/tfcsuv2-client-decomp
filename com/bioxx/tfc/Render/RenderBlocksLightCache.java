/*     */ package com.bioxx.tfc.Render;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class RenderBlocksLightCache
/*     */   extends RenderBlocksFixUV
/*     */ {
/*  13 */   private EnumSet<ForgeDirection> facesToDraw = EnumSet.allOf(ForgeDirection.class);
/*     */   
/*  15 */   private RenderFaceData[] sides = new RenderFaceData[6];
/*     */ 
/*     */   
/*     */   private static class RenderPointData
/*     */   {
/*     */     private int brightness;
/*     */     private float r;
/*     */     private float g;
/*     */     private float b;
/*     */     
/*     */     private RenderPointData() {}
/*     */   }
/*     */   
/*     */   private static class RenderFaceData
/*     */   {
/*     */     private int cacheBrightnessTopLeft;
/*     */     private int cacheBrightnessBottomLeft;
/*     */     private int cacheBrightnessBottomRight;
/*     */     private int cacheBrightnessTopRight;
/*     */     private float cacheColorRedTopLeft;
/*     */     private float cacheColorRedBottomLeft;
/*     */     private float cacheColorRedBottomRight;
/*     */     private float cacheColorRedTopRight;
/*     */     private float cacheColorGreenTopLeft;
/*     */     private float cacheColorGreenBottomLeft;
/*     */     private float cacheColorGreenBottomRight;
/*     */     private float cacheColorGreenTopRight;
/*     */     private float cacheColorBlueTopLeft;
/*     */     private float cacheColorBlueBottomLeft;
/*     */     private float cacheColorBlueBottomRight;
/*     */     private float cacheColorBlueTopRight;
/*     */     
/*     */     public RenderFaceData(RenderBlocks rb) {
/*  48 */       this.cacheBrightnessTopLeft = rb.field_147864_al;
/*  49 */       this.cacheBrightnessBottomLeft = rb.field_147874_am;
/*  50 */       this.cacheBrightnessBottomRight = rb.field_147876_an;
/*  51 */       this.cacheBrightnessTopRight = rb.field_147870_ao;
/*     */       
/*  53 */       this.cacheColorRedTopLeft = rb.field_147872_ap;
/*  54 */       this.cacheColorRedBottomLeft = rb.field_147852_aq;
/*  55 */       this.cacheColorRedBottomRight = rb.field_147850_ar;
/*  56 */       this.cacheColorRedTopRight = rb.field_147848_as;
/*     */       
/*  58 */       this.cacheColorGreenTopLeft = rb.field_147846_at;
/*  59 */       this.cacheColorGreenBottomLeft = rb.field_147860_au;
/*  60 */       this.cacheColorGreenBottomRight = rb.field_147858_av;
/*  61 */       this.cacheColorGreenTopRight = rb.field_147856_aw;
/*     */       
/*  63 */       this.cacheColorBlueTopLeft = rb.field_147854_ax;
/*  64 */       this.cacheColorBlueBottomLeft = rb.field_147841_ay;
/*  65 */       this.cacheColorBlueBottomRight = rb.field_147839_az;
/*  66 */       this.cacheColorBlueTopRight = rb.field_147833_aA;
/*     */     }
/*     */ 
/*     */     
/*     */     public RenderBlocksLightCache.RenderPointData getCachedValue(double leftRight, double topBottom) {
/*  71 */       RenderBlocksLightCache.RenderPointData o = new RenderBlocksLightCache.RenderPointData();
/*     */ 
/*     */       
/*  74 */       double rTop = this.cacheColorRedTopLeft * leftRight + (1.0D - leftRight) * this.cacheColorRedTopRight;
/*  75 */       double rBottom = this.cacheColorRedBottomLeft * leftRight + (1.0D - leftRight) * this.cacheColorRedBottomRight;
/*  76 */       o.r = (float)(rTop * topBottom + rBottom * (1.0D - topBottom));
/*     */       
/*  78 */       double gTop = this.cacheColorGreenTopLeft * leftRight + (1.0D - leftRight) * this.cacheColorGreenTopRight;
/*  79 */       double gBottom = this.cacheColorGreenBottomLeft * leftRight + (1.0D - leftRight) * this.cacheColorGreenBottomRight;
/*  80 */       o.g = (float)(gTop * topBottom + gBottom * (1.0D - topBottom));
/*     */       
/*  82 */       double bTop = this.cacheColorBlueTopLeft * leftRight + (1.0D - leftRight) * this.cacheColorBlueTopRight;
/*  83 */       double bBottom = this.cacheColorBlueBottomLeft * leftRight + (1.0D - leftRight) * this.cacheColorBlueBottomRight;
/*  84 */       o.b = (float)(bTop * topBottom + bBottom * (1.0D - topBottom));
/*     */ 
/*     */       
/*  87 */       double highTop = (this.cacheBrightnessTopLeft >> 16 & 0xFF) * leftRight + (1.0D - leftRight) * (this.cacheBrightnessTopRight >> 16 & 0xFF);
/*  88 */       double highBottom = (this.cacheBrightnessBottomLeft >> 16 & 0xFF) * leftRight + (1.0D - leftRight) * (this.cacheBrightnessBottomRight >> 16 & 0xFF);
/*  89 */       int high = (int)(highTop * topBottom + highBottom * (1.0D - topBottom)) & 0xFF;
/*     */       
/*  91 */       double lowTop = (this.cacheBrightnessTopLeft & 0xFF) * leftRight + (1.0D - leftRight) * (this.cacheBrightnessTopRight & 0xFF);
/*  92 */       double lowBottom = (this.cacheBrightnessBottomLeft & 0xFF) * leftRight + (1.0D - leftRight) * (this.cacheBrightnessBottomRight & 0xFF);
/*  93 */       int low = (int)(lowTop * topBottom + lowBottom * (1.0D - topBottom)) & 0xFF;
/*     */ 
/*     */       
/*  96 */       o.brightness = high << 16 | low;
/*     */       
/*  98 */       return o;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderBlocksLightCache(RenderBlocks old) {
/* 105 */     super(old);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void disableRender() {
/* 113 */     this.facesToDraw = EnumSet.noneOf(ForgeDirection.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public void enableRender() {
/* 118 */     this.facesToDraw = EnumSet.allOf(ForgeDirection.class);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_147761_c(Block a, double b, double c, double d, IIcon e) {
/* 128 */     if (!this.facesToDraw.contains(ForgeDirection.NORTH)) {
/*     */       
/* 130 */       saveData(ForgeDirection.NORTH);
/*     */       
/*     */       return;
/*     */     } 
/* 134 */     super.func_147761_c(a, b, c, d, e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_147764_f(Block a, double b, double c, double d, IIcon e) {
/* 140 */     if (!this.facesToDraw.contains(ForgeDirection.EAST)) {
/*     */       
/* 142 */       saveData(ForgeDirection.EAST);
/*     */       
/*     */       return;
/*     */     } 
/* 146 */     super.func_147764_f(a, b, c, d, e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_147798_e(Block a, double b, double c, double d, IIcon e) {
/* 153 */     if (!this.facesToDraw.contains(ForgeDirection.WEST)) {
/*     */       
/* 155 */       saveData(ForgeDirection.WEST);
/*     */       
/*     */       return;
/*     */     } 
/* 159 */     super.func_147798_e(a, b, c, d, e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_147768_a(Block a, double b, double c, double d, IIcon e) {
/* 165 */     if (!this.facesToDraw.contains(ForgeDirection.DOWN)) {
/*     */       
/* 167 */       saveData(ForgeDirection.DOWN);
/*     */       
/*     */       return;
/*     */     } 
/* 171 */     super.func_147768_a(a, b, c, d, e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_147806_b(Block a, double b, double c, double d, IIcon e) {
/* 177 */     if (!this.facesToDraw.contains(ForgeDirection.UP)) {
/*     */       
/* 179 */       saveData(ForgeDirection.UP);
/*     */       
/*     */       return;
/*     */     } 
/* 183 */     super.func_147806_b(a, b, c, d, e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_147734_d(Block a, double b, double c, double d, IIcon e) {
/* 189 */     if (!this.facesToDraw.contains(ForgeDirection.SOUTH)) {
/*     */       
/* 191 */       saveData(ForgeDirection.SOUTH);
/*     */       
/*     */       return;
/*     */     } 
/* 195 */     super.func_147734_d(a, b, c, d, e);
/*     */   }
/*     */ 
/*     */   
/*     */   private void saveData(ForgeDirection side) {
/* 200 */     this.sides[side.ordinal()] = new RenderFaceData(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderCachedBlock(Block block, int i, int j, int k, IIcon myTexture) {
/* 205 */     this.field_147863_w = Minecraft.func_71379_u();
/*     */ 
/*     */ 
/*     */     
/* 209 */     RenderPointData rpd = this.sides[ForgeDirection.EAST.ordinal()].getCachedValue(1.0D - this.field_147857_k, this.field_147853_m);
/* 210 */     this.field_147872_ap = rpd.r;
/* 211 */     this.field_147846_at = rpd.g;
/* 212 */     this.field_147854_ax = rpd.b;
/* 213 */     this.field_147864_al = rpd.brightness;
/*     */     
/* 215 */     rpd = this.sides[ForgeDirection.EAST.ordinal()].getCachedValue(1.0D - this.field_147855_j, this.field_147853_m);
/* 216 */     this.field_147848_as = rpd.r;
/* 217 */     this.field_147856_aw = rpd.g;
/* 218 */     this.field_147833_aA = rpd.b;
/* 219 */     this.field_147870_ao = rpd.brightness;
/*     */     
/* 221 */     rpd = this.sides[ForgeDirection.EAST.ordinal()].getCachedValue(1.0D - this.field_147855_j, this.field_147851_l);
/* 222 */     this.field_147850_ar = rpd.r;
/* 223 */     this.field_147858_av = rpd.g;
/* 224 */     this.field_147839_az = rpd.b;
/* 225 */     this.field_147876_an = rpd.brightness;
/*     */     
/* 227 */     rpd = this.sides[ForgeDirection.EAST.ordinal()].getCachedValue(1.0D - this.field_147857_k, this.field_147851_l);
/* 228 */     this.field_147852_aq = rpd.r;
/* 229 */     this.field_147860_au = rpd.g;
/* 230 */     this.field_147841_ay = rpd.b;
/* 231 */     this.field_147874_am = rpd.brightness;
/*     */     
/* 233 */     func_147764_f(block, i, j, k, myTexture);
/*     */ 
/*     */     
/* 236 */     rpd = this.sides[ForgeDirection.WEST.ordinal()].getCachedValue(this.field_147857_k, this.field_147853_m);
/* 237 */     this.field_147872_ap = rpd.r;
/* 238 */     this.field_147846_at = rpd.g;
/* 239 */     this.field_147854_ax = rpd.b;
/* 240 */     this.field_147864_al = rpd.brightness;
/*     */     
/* 242 */     rpd = this.sides[ForgeDirection.WEST.ordinal()].getCachedValue(this.field_147857_k, this.field_147851_l);
/* 243 */     this.field_147852_aq = rpd.r;
/* 244 */     this.field_147860_au = rpd.g;
/* 245 */     this.field_147841_ay = rpd.b;
/* 246 */     this.field_147874_am = rpd.brightness;
/*     */     
/* 248 */     rpd = this.sides[ForgeDirection.WEST.ordinal()].getCachedValue(this.field_147855_j, this.field_147851_l);
/* 249 */     this.field_147850_ar = rpd.r;
/* 250 */     this.field_147858_av = rpd.g;
/* 251 */     this.field_147839_az = rpd.b;
/* 252 */     this.field_147876_an = rpd.brightness;
/*     */     
/* 254 */     rpd = this.sides[ForgeDirection.WEST.ordinal()].getCachedValue(this.field_147855_j, this.field_147853_m);
/* 255 */     this.field_147848_as = rpd.r;
/* 256 */     this.field_147856_aw = rpd.g;
/* 257 */     this.field_147833_aA = rpd.b;
/* 258 */     this.field_147870_ao = rpd.brightness;
/*     */     
/* 260 */     func_147798_e(block, i, j, k, myTexture);
/*     */ 
/*     */     
/* 263 */     rpd = this.sides[ForgeDirection.SOUTH.ordinal()].getCachedValue(1.0D - this.field_147859_h, this.field_147857_k);
/* 264 */     this.field_147872_ap = rpd.r;
/* 265 */     this.field_147846_at = rpd.g;
/* 266 */     this.field_147854_ax = rpd.b;
/* 267 */     this.field_147864_al = rpd.brightness;
/*     */     
/* 269 */     rpd = this.sides[ForgeDirection.SOUTH.ordinal()].getCachedValue(1.0D - this.field_147859_h, this.field_147855_j);
/* 270 */     this.field_147852_aq = rpd.r;
/* 271 */     this.field_147860_au = rpd.g;
/* 272 */     this.field_147841_ay = rpd.b;
/* 273 */     this.field_147874_am = rpd.brightness;
/*     */     
/* 275 */     rpd = this.sides[ForgeDirection.SOUTH.ordinal()].getCachedValue(1.0D - this.field_147861_i, this.field_147855_j);
/* 276 */     this.field_147850_ar = rpd.r;
/* 277 */     this.field_147858_av = rpd.g;
/* 278 */     this.field_147839_az = rpd.b;
/* 279 */     this.field_147876_an = rpd.brightness;
/*     */     
/* 281 */     rpd = this.sides[ForgeDirection.SOUTH.ordinal()].getCachedValue(1.0D - this.field_147861_i, this.field_147857_k);
/* 282 */     this.field_147848_as = rpd.r;
/* 283 */     this.field_147856_aw = rpd.g;
/* 284 */     this.field_147833_aA = rpd.b;
/* 285 */     this.field_147870_ao = rpd.brightness;
/*     */     
/* 287 */     func_147734_d(block, i, j, k, myTexture);
/*     */ 
/*     */     
/* 290 */     rpd = this.sides[ForgeDirection.NORTH.ordinal()].getCachedValue(this.field_147857_k, 1.0D - this.field_147859_h);
/* 291 */     this.field_147872_ap = rpd.r;
/* 292 */     this.field_147846_at = rpd.g;
/* 293 */     this.field_147854_ax = rpd.b;
/* 294 */     this.field_147864_al = rpd.brightness;
/*     */     
/* 296 */     rpd = this.sides[ForgeDirection.NORTH.ordinal()].getCachedValue(this.field_147857_k, 1.0D - this.field_147861_i);
/* 297 */     this.field_147848_as = rpd.r;
/* 298 */     this.field_147856_aw = rpd.g;
/* 299 */     this.field_147833_aA = rpd.b;
/* 300 */     this.field_147870_ao = rpd.brightness;
/*     */     
/* 302 */     rpd = this.sides[ForgeDirection.NORTH.ordinal()].getCachedValue(this.field_147855_j, 1.0D - this.field_147861_i);
/* 303 */     this.field_147850_ar = rpd.r;
/* 304 */     this.field_147858_av = rpd.g;
/* 305 */     this.field_147839_az = rpd.b;
/* 306 */     this.field_147876_an = rpd.brightness;
/*     */     
/* 308 */     rpd = this.sides[ForgeDirection.NORTH.ordinal()].getCachedValue(this.field_147855_j, 1.0D - this.field_147859_h);
/* 309 */     this.field_147852_aq = rpd.r;
/* 310 */     this.field_147860_au = rpd.g;
/* 311 */     this.field_147841_ay = rpd.b;
/* 312 */     this.field_147874_am = rpd.brightness;
/*     */     
/* 314 */     func_147761_c(block, i, j, k, myTexture);
/*     */ 
/*     */     
/* 317 */     rpd = this.sides[ForgeDirection.UP.ordinal()].getCachedValue(this.field_147861_i, this.field_147853_m);
/* 318 */     this.field_147872_ap = rpd.r;
/* 319 */     this.field_147846_at = rpd.g;
/* 320 */     this.field_147854_ax = rpd.b;
/* 321 */     this.field_147864_al = rpd.brightness;
/*     */     
/* 323 */     rpd = this.sides[ForgeDirection.UP.ordinal()].getCachedValue(this.field_147861_i, this.field_147851_l);
/* 324 */     this.field_147852_aq = rpd.r;
/* 325 */     this.field_147860_au = rpd.g;
/* 326 */     this.field_147841_ay = rpd.b;
/* 327 */     this.field_147874_am = rpd.brightness;
/*     */     
/* 329 */     rpd = this.sides[ForgeDirection.UP.ordinal()].getCachedValue(this.field_147859_h, this.field_147851_l);
/* 330 */     this.field_147850_ar = rpd.r;
/* 331 */     this.field_147858_av = rpd.g;
/* 332 */     this.field_147839_az = rpd.b;
/* 333 */     this.field_147876_an = rpd.brightness;
/*     */     
/* 335 */     rpd = this.sides[ForgeDirection.UP.ordinal()].getCachedValue(this.field_147859_h, this.field_147853_m);
/* 336 */     this.field_147848_as = rpd.r;
/* 337 */     this.field_147856_aw = rpd.g;
/* 338 */     this.field_147833_aA = rpd.b;
/* 339 */     this.field_147870_ao = rpd.brightness;
/*     */     
/* 341 */     func_147806_b(block, i, j, k, myTexture);
/*     */ 
/*     */     
/* 344 */     rpd = this.sides[ForgeDirection.DOWN.ordinal()].getCachedValue(1.0D - this.field_147859_h, this.field_147853_m);
/* 345 */     this.field_147872_ap = rpd.r;
/* 346 */     this.field_147846_at = rpd.g;
/* 347 */     this.field_147854_ax = rpd.b;
/* 348 */     this.field_147864_al = rpd.brightness;
/*     */     
/* 350 */     rpd = this.sides[ForgeDirection.DOWN.ordinal()].getCachedValue(1.0D - this.field_147859_h, this.field_147851_l);
/* 351 */     this.field_147852_aq = rpd.r;
/* 352 */     this.field_147860_au = rpd.g;
/* 353 */     this.field_147841_ay = rpd.b;
/* 354 */     this.field_147874_am = rpd.brightness;
/*     */     
/* 356 */     rpd = this.sides[ForgeDirection.DOWN.ordinal()].getCachedValue(1.0D - this.field_147861_i, this.field_147851_l);
/* 357 */     this.field_147850_ar = rpd.r;
/* 358 */     this.field_147858_av = rpd.g;
/* 359 */     this.field_147839_az = rpd.b;
/* 360 */     this.field_147876_an = rpd.brightness;
/*     */     
/* 362 */     rpd = this.sides[ForgeDirection.DOWN.ordinal()].getCachedValue(1.0D - this.field_147861_i, this.field_147853_m);
/* 363 */     this.field_147848_as = rpd.r;
/* 364 */     this.field_147856_aw = rpd.g;
/* 365 */     this.field_147833_aA = rpd.b;
/* 366 */     this.field_147870_ao = rpd.brightness;
/*     */     
/* 368 */     func_147768_a(block, i, j, k, myTexture);
/*     */ 
/*     */     
/* 371 */     this.field_147863_w = false;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\RenderBlocksLightCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */