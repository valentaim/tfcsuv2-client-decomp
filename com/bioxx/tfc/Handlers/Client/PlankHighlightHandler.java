/*     */ package com.bioxx.tfc.Handlers.Client;
/*     */ 
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
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
/*     */ public class PlankHighlightHandler
/*     */ {
/*     */   @SubscribeEvent
/*     */   public void drawBlockHighlightEvent(DrawBlockHighlightEvent evt) {
/*  19 */     World world = evt.player.field_70170_p;
/*  20 */     double var8 = evt.player.field_70142_S + (evt.player.field_70165_t - evt.player.field_70142_S) * evt.partialTicks;
/*  21 */     double var10 = evt.player.field_70137_T + (evt.player.field_70163_u - evt.player.field_70137_T) * evt.partialTicks;
/*  22 */     double var12 = evt.player.field_70136_U + (evt.player.field_70161_v - evt.player.field_70136_U) * evt.partialTicks;
/*     */     
/*  24 */     if (evt.currentItem != null && evt.currentItem.func_77973_b() instanceof com.bioxx.tfc.Items.ItemPlank) {
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
/*  50 */       GL11.glDisable(3553);
/*     */       
/*  52 */       boolean isConstruct = (world.func_147439_a(evt.target.field_72311_b, evt.target.field_72312_c, evt.target.field_72309_d) == TFCBlocks.woodConstruct);
/*     */ 
/*     */       
/*  55 */       double hitX = Math.round((evt.target.field_72307_f.field_72450_a - evt.target.field_72311_b) * 100.0D) / 100.0D;
/*  56 */       double hitY = Math.round((evt.target.field_72307_f.field_72448_b - evt.target.field_72312_c) * 100.0D) / 100.0D;
/*  57 */       double hitZ = Math.round((evt.target.field_72307_f.field_72449_c - evt.target.field_72309_d) * 100.0D) / 100.0D;
/*     */ 
/*     */       
/*  60 */       double subX = (int)(hitX * 8.0D) / 8.0D;
/*  61 */       double subY = (int)(hitY * 8.0D) / 8.0D;
/*  62 */       double subZ = (int)(hitZ * 8.0D) / 8.0D;
/*     */ 
/*     */       
/*  65 */       double minX = evt.target.field_72311_b + subX;
/*  66 */       double minY = evt.target.field_72312_c + subY;
/*  67 */       double minZ = evt.target.field_72309_d + subZ;
/*  68 */       double maxX = minX + 0.125D;
/*  69 */       double maxY = minY + 0.125D;
/*  70 */       double maxZ = minZ + 0.125D;
/*     */       
/*  72 */       if (isConstruct && hitY != 0.0D && hitY != 1.0D && hitZ != 0.0D && hitZ != 1.0D && hitX != 0.0D && hitX != 1.0D) {
/*     */         
/*  74 */         if (evt.target.field_72310_e == 0)
/*     */         {
/*  76 */           minY = evt.target.field_72312_c;
/*  77 */           maxY = (evt.target.field_72312_c + 1);
/*     */         }
/*  79 */         else if (evt.target.field_72310_e == 1)
/*     */         {
/*  81 */           minY = evt.target.field_72312_c;
/*  82 */           maxY = (evt.target.field_72312_c + 1);
/*     */         }
/*  84 */         else if (evt.target.field_72310_e == 2)
/*     */         {
/*  86 */           minZ = evt.target.field_72309_d;
/*  87 */           maxZ = (evt.target.field_72309_d + 1);
/*     */         }
/*  89 */         else if (evt.target.field_72310_e == 3)
/*     */         {
/*  91 */           minZ = evt.target.field_72309_d;
/*  92 */           maxZ = (evt.target.field_72309_d + 1);
/*     */         }
/*  94 */         else if (evt.target.field_72310_e == 4)
/*     */         {
/*  96 */           minX = evt.target.field_72311_b;
/*  97 */           maxX = (evt.target.field_72311_b + 1);
/*     */         }
/*  99 */         else if (evt.target.field_72310_e == 5)
/*     */         {
/* 101 */           minX = evt.target.field_72311_b;
/* 102 */           maxX = (evt.target.field_72311_b + 1);
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 107 */       else if (evt.target.field_72310_e == 0) {
/*     */         
/* 109 */         maxY = minY;
/* 110 */         minY--;
/*     */       }
/* 112 */       else if (evt.target.field_72310_e == 1) {
/*     */         
/* 114 */         maxY = minY + 1.0D;
/*     */       }
/* 116 */       else if (evt.target.field_72310_e == 2) {
/*     */         
/* 118 */         maxZ = minZ;
/* 119 */         minZ--;
/*     */       }
/* 121 */       else if (evt.target.field_72310_e == 3) {
/*     */         
/* 123 */         maxZ = minZ + 1.0D;
/*     */       }
/* 125 */       else if (evt.target.field_72310_e == 4) {
/*     */         
/* 127 */         maxX = minX;
/* 128 */         minX--;
/*     */       }
/* 130 */       else if (evt.target.field_72310_e == 5) {
/*     */         
/* 132 */         maxX = minX + 1.0D;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 137 */       GL11.glEnable(3042);
/*     */       
/* 139 */       GL11.glBlendFunc(770, 771);
/* 140 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.4F);
/* 141 */       GL11.glDisable(2884);
/* 142 */       GL11.glDepthMask(false);
/*     */       
/* 144 */       drawBox(AxisAlignedBB.func_72330_a(minX, minY, minZ, maxX, maxY, maxZ).func_72314_b(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).func_72325_c(-var8, -var10, -var12));
/*     */       
/* 146 */       GL11.glDepthMask(true);
/* 147 */       GL11.glEnable(3553);
/* 148 */       GL11.glDisable(3042);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawFaceUV(AxisAlignedBB par1AxisAlignedBB, int side) {
/* 154 */     Tessellator var2 = Tessellator.field_78398_a;
/*     */     
/* 156 */     var2.func_78369_a(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 158 */     var2.func_78371_b(7);
/*     */     
/* 160 */     if (side == 0) {
/*     */       
/* 162 */       var2.func_78374_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c, 0.0D, 0.0D);
/* 163 */       var2.func_78374_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c, 1.0D, 0.0D);
/* 164 */       var2.func_78374_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f, 1.0D, 1.0D);
/* 165 */       var2.func_78374_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f, 0.0D, 1.0D);
/*     */     }
/* 167 */     else if (side == 1) {
/*     */       
/* 169 */       var2.func_78374_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c, 0.0D, 0.0D);
/* 170 */       var2.func_78374_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c, 1.0D, 0.0D);
/* 171 */       var2.func_78374_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f, 1.0D, 1.0D);
/* 172 */       var2.func_78374_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f, 0.0D, 1.0D);
/*     */     }
/* 174 */     else if (side == 2) {
/*     */       
/* 176 */       var2.func_78374_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c, 0.0D, 0.0D);
/* 177 */       var2.func_78374_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c, 1.0D, 0.0D);
/* 178 */       var2.func_78374_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c, 1.0D, 1.0D);
/* 179 */       var2.func_78374_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c, 0.0D, 1.0D);
/*     */     }
/* 181 */     else if (side == 3) {
/*     */       
/* 183 */       var2.func_78374_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f, 0.0D, 0.0D);
/* 184 */       var2.func_78374_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f, 1.0D, 0.0D);
/* 185 */       var2.func_78374_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f, 1.0D, 1.0D);
/* 186 */       var2.func_78374_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f, 0.0D, 1.0D);
/*     */     }
/* 188 */     else if (side == 4) {
/*     */       
/* 190 */       var2.func_78374_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c, 0.0D, 0.0D);
/* 191 */       var2.func_78374_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f, 1.0D, 0.0D);
/* 192 */       var2.func_78374_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f, 1.0D, 1.0D);
/* 193 */       var2.func_78374_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c, 0.0D, 1.0D);
/*     */     }
/* 195 */     else if (side == 5) {
/*     */       
/* 197 */       var2.func_78374_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c, 0.0D, 0.0D);
/* 198 */       var2.func_78374_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f, 1.0D, 0.0D);
/* 199 */       var2.func_78374_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f, 1.0D, 1.0D);
/* 200 */       var2.func_78374_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c, 0.0D, 1.0D);
/*     */     } 
/* 202 */     var2.func_78381_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawFace(AxisAlignedBB par1AxisAlignedBB) {
/* 207 */     Tessellator var2 = Tessellator.field_78398_a;
/*     */ 
/*     */     
/* 210 */     var2.func_78371_b(7);
/* 211 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 212 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 213 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 214 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 215 */     var2.func_78381_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawBox(AxisAlignedBB par1AxisAlignedBB) {
/* 220 */     Tessellator var2 = Tessellator.field_78398_a;
/*     */ 
/*     */     
/* 223 */     var2.func_78371_b(7);
/* 224 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 225 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 226 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 227 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 228 */     var2.func_78381_a();
/*     */ 
/*     */     
/* 231 */     var2.func_78371_b(7);
/* 232 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 233 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 234 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/* 235 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/* 236 */     var2.func_78381_a();
/*     */ 
/*     */     
/* 239 */     var2.func_78371_b(7);
/* 240 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 241 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 242 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 243 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/* 244 */     var2.func_78381_a();
/*     */ 
/*     */     
/* 247 */     var2.func_78371_b(7);
/* 248 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/* 249 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 250 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 251 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 252 */     var2.func_78381_a();
/*     */ 
/*     */     
/* 255 */     var2.func_78371_b(7);
/* 256 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 257 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 258 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 259 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 260 */     var2.func_78381_a();
/*     */ 
/*     */     
/* 263 */     var2.func_78371_b(7);
/* 264 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/* 265 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 266 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 267 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/* 268 */     var2.func_78381_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawOutlinedBoundingBox(AxisAlignedBB par1AxisAlignedBB) {
/* 273 */     Tessellator var2 = Tessellator.field_78398_a;
/* 274 */     var2.func_78371_b(3);
/* 275 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 276 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 277 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/* 278 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/* 279 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 280 */     var2.func_78381_a();
/* 281 */     var2.func_78371_b(3);
/* 282 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 283 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 284 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 285 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 286 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 287 */     var2.func_78381_a();
/* 288 */     var2.func_78371_b(1);
/* 289 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 290 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 291 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72339_c);
/* 292 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72339_c);
/* 293 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/* 294 */     var2.func_78377_a(par1AxisAlignedBB.field_72336_d, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 295 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72338_b, par1AxisAlignedBB.field_72334_f);
/* 296 */     var2.func_78377_a(par1AxisAlignedBB.field_72340_a, par1AxisAlignedBB.field_72337_e, par1AxisAlignedBB.field_72334_f);
/* 297 */     var2.func_78381_a();
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Client\PlankHighlightHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */