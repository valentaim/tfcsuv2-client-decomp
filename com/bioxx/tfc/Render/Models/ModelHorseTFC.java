/*     */ package com.bioxx.tfc.Render.Models;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityHorseTFC;
/*     */ import com.bioxx.tfc.api.Entities.IAnimal;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
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
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ModelHorseTFC
/*     */   extends ModelBase
/*     */ {
/*     */   private ModelRenderer head;
/*     */   private ModelRenderer mouthTop;
/*     */   private ModelRenderer mouthBottom;
/*     */   private ModelRenderer horseLeftEar;
/*     */   private ModelRenderer horseRightEar;
/*     */   private ModelRenderer muleLeftEar;
/*     */   private ModelRenderer muleRightEar;
/*     */   private ModelRenderer neck;
/*     */   private ModelRenderer horseFaceRopes;
/*     */   private ModelRenderer mane;
/*     */   private ModelRenderer body;
/*     */   private ModelRenderer tailBase;
/*     */   private ModelRenderer tailMiddle;
/*     */   private ModelRenderer tailTip;
/*     */   private ModelRenderer backLeftLeg;
/*     */   private ModelRenderer backLeftShin;
/*     */   private ModelRenderer backLeftHoof;
/*     */   private ModelRenderer backRightLeg;
/*     */   private ModelRenderer backRightShin;
/*     */   private ModelRenderer backRightHoof;
/*     */   private ModelRenderer frontLeftLeg;
/*     */   private ModelRenderer frontLeftShin;
/*     */   private ModelRenderer frontLeftHoof;
/*     */   private ModelRenderer frontRightLeg;
/*     */   private ModelRenderer frontRightShin;
/*     */   private ModelRenderer frontRightHoof;
/*     */   private ModelRenderer muleLeftChest;
/*     */   private ModelRenderer muleRightChest;
/*     */   private ModelRenderer horseSaddleBottom;
/*     */   private ModelRenderer horseSaddleFront;
/*     */   private ModelRenderer horseSaddleBack;
/*     */   private ModelRenderer horseLeftSaddleRope;
/*     */   private ModelRenderer horseLeftSaddleMetal;
/*     */   private ModelRenderer horseRightSaddleRope;
/*     */   private ModelRenderer horseRightSaddleMetal;
/*     */   private ModelRenderer horseLeftFaceMetal;
/*     */   private ModelRenderer horseRightFaceMetal;
/*     */   private ModelRenderer horseLeftRein;
/*     */   private ModelRenderer horseRightRein;
/*     */   
/*     */   public ModelHorseTFC() {
/*  71 */     this.field_78090_t = 128;
/*  72 */     this.field_78089_u = 128;
/*  73 */     this.body = new ModelRenderer(this, 0, 34);
/*  74 */     this.body.func_78789_a(-5.0F, -8.0F, -19.0F, 10, 10, 24);
/*  75 */     this.body.func_78793_a(0.0F, 11.0F, 9.0F);
/*  76 */     this.tailBase = new ModelRenderer(this, 44, 0);
/*  77 */     this.tailBase.func_78789_a(-1.0F, -1.0F, 0.0F, 2, 2, 3);
/*  78 */     this.tailBase.func_78793_a(0.0F, 3.0F, 14.0F);
/*  79 */     setBoxRotation(this.tailBase, -1.134464F, 0.0F, 0.0F);
/*  80 */     this.tailMiddle = new ModelRenderer(this, 38, 7);
/*  81 */     this.tailMiddle.func_78789_a(-1.5F, -2.0F, 3.0F, 3, 4, 7);
/*  82 */     this.tailMiddle.func_78793_a(0.0F, 3.0F, 14.0F);
/*  83 */     setBoxRotation(this.tailMiddle, -1.134464F, 0.0F, 0.0F);
/*  84 */     this.tailTip = new ModelRenderer(this, 24, 3);
/*  85 */     this.tailTip.func_78789_a(-1.5F, -4.5F, 9.0F, 3, 4, 7);
/*  86 */     this.tailTip.func_78793_a(0.0F, 3.0F, 14.0F);
/*  87 */     setBoxRotation(this.tailTip, -1.40215F, 0.0F, 0.0F);
/*  88 */     this.backLeftLeg = new ModelRenderer(this, 78, 29);
/*  89 */     this.backLeftLeg.func_78789_a(-2.5F, -2.0F, -2.5F, 4, 9, 5);
/*  90 */     this.backLeftLeg.func_78793_a(4.0F, 9.0F, 11.0F);
/*  91 */     this.backLeftShin = new ModelRenderer(this, 78, 43);
/*  92 */     this.backLeftShin.func_78789_a(-2.0F, 0.0F, -1.5F, 3, 5, 3);
/*  93 */     this.backLeftShin.func_78793_a(4.0F, 16.0F, 11.0F);
/*  94 */     this.backLeftHoof = new ModelRenderer(this, 78, 51);
/*  95 */     this.backLeftHoof.func_78789_a(-2.5F, 5.1F, -2.0F, 4, 3, 4);
/*  96 */     this.backLeftHoof.func_78793_a(4.0F, 16.0F, 11.0F);
/*  97 */     this.backRightLeg = new ModelRenderer(this, 96, 29);
/*  98 */     this.backRightLeg.func_78789_a(-1.5F, -2.0F, -2.5F, 4, 9, 5);
/*  99 */     this.backRightLeg.func_78793_a(-4.0F, 9.0F, 11.0F);
/* 100 */     this.backRightShin = new ModelRenderer(this, 96, 43);
/* 101 */     this.backRightShin.func_78789_a(-1.0F, 0.0F, -1.5F, 3, 5, 3);
/* 102 */     this.backRightShin.func_78793_a(-4.0F, 16.0F, 11.0F);
/* 103 */     this.backRightHoof = new ModelRenderer(this, 96, 51);
/* 104 */     this.backRightHoof.func_78789_a(-1.5F, 5.1F, -2.0F, 4, 3, 4);
/* 105 */     this.backRightHoof.func_78793_a(-4.0F, 16.0F, 11.0F);
/* 106 */     this.frontLeftLeg = new ModelRenderer(this, 44, 29);
/* 107 */     this.frontLeftLeg.func_78789_a(-1.9F, -1.0F, -2.1F, 3, 8, 4);
/* 108 */     this.frontLeftLeg.func_78793_a(4.0F, 9.0F, -8.0F);
/* 109 */     this.frontLeftShin = new ModelRenderer(this, 44, 41);
/* 110 */     this.frontLeftShin.func_78789_a(-1.9F, 0.0F, -1.6F, 3, 5, 3);
/* 111 */     this.frontLeftShin.func_78793_a(4.0F, 16.0F, -8.0F);
/* 112 */     this.frontLeftHoof = new ModelRenderer(this, 44, 51);
/* 113 */     this.frontLeftHoof.func_78789_a(-2.4F, 5.1F, -2.1F, 4, 3, 4);
/* 114 */     this.frontLeftHoof.func_78793_a(4.0F, 16.0F, -8.0F);
/* 115 */     this.frontRightLeg = new ModelRenderer(this, 60, 29);
/* 116 */     this.frontRightLeg.func_78789_a(-1.1F, -1.0F, -2.1F, 3, 8, 4);
/* 117 */     this.frontRightLeg.func_78793_a(-4.0F, 9.0F, -8.0F);
/* 118 */     this.frontRightShin = new ModelRenderer(this, 60, 41);
/* 119 */     this.frontRightShin.func_78789_a(-1.1F, 0.0F, -1.6F, 3, 5, 3);
/* 120 */     this.frontRightShin.func_78793_a(-4.0F, 16.0F, -8.0F);
/* 121 */     this.frontRightHoof = new ModelRenderer(this, 60, 51);
/* 122 */     this.frontRightHoof.func_78789_a(-1.6F, 5.1F, -2.1F, 4, 3, 4);
/* 123 */     this.frontRightHoof.func_78793_a(-4.0F, 16.0F, -8.0F);
/* 124 */     this.head = new ModelRenderer(this, 0, 0);
/* 125 */     this.head.func_78789_a(-2.5F, -10.0F, -1.5F, 5, 5, 7);
/* 126 */     this.head.func_78793_a(0.0F, 4.0F, -10.0F);
/* 127 */     setBoxRotation(this.head, 0.5235988F, 0.0F, 0.0F);
/* 128 */     this.mouthTop = new ModelRenderer(this, 24, 18);
/* 129 */     this.mouthTop.func_78789_a(-2.0F, -10.0F, -7.0F, 4, 3, 6);
/* 130 */     this.mouthTop.func_78793_a(0.0F, 3.95F, -10.0F);
/* 131 */     setBoxRotation(this.mouthTop, 0.5235988F, 0.0F, 0.0F);
/* 132 */     this.mouthBottom = new ModelRenderer(this, 24, 27);
/* 133 */     this.mouthBottom.func_78789_a(-2.0F, -7.0F, -6.5F, 4, 2, 5);
/* 134 */     this.mouthBottom.func_78793_a(0.0F, 4.0F, -10.0F);
/* 135 */     setBoxRotation(this.mouthBottom, 0.5235988F, 0.0F, 0.0F);
/* 136 */     this.head.func_78792_a(this.mouthTop);
/* 137 */     this.head.func_78792_a(this.mouthBottom);
/* 138 */     this.horseLeftEar = new ModelRenderer(this, 0, 0);
/* 139 */     this.horseLeftEar.func_78789_a(0.45F, -12.0F, 4.0F, 2, 3, 1);
/* 140 */     this.horseLeftEar.func_78793_a(0.0F, 4.0F, -10.0F);
/* 141 */     setBoxRotation(this.horseLeftEar, 0.5235988F, 0.0F, 0.0F);
/* 142 */     this.horseRightEar = new ModelRenderer(this, 0, 0);
/* 143 */     this.horseRightEar.func_78789_a(-2.45F, -12.0F, 4.0F, 2, 3, 1);
/* 144 */     this.horseRightEar.func_78793_a(0.0F, 4.0F, -10.0F);
/* 145 */     setBoxRotation(this.horseRightEar, 0.5235988F, 0.0F, 0.0F);
/* 146 */     this.muleLeftEar = new ModelRenderer(this, 0, 12);
/* 147 */     this.muleLeftEar.func_78789_a(-2.0F, -16.0F, 4.0F, 2, 7, 1);
/* 148 */     this.muleLeftEar.func_78793_a(0.0F, 4.0F, -10.0F);
/* 149 */     setBoxRotation(this.muleLeftEar, 0.5235988F, 0.0F, 0.2617994F);
/* 150 */     this.muleRightEar = new ModelRenderer(this, 0, 12);
/* 151 */     this.muleRightEar.func_78789_a(0.0F, -16.0F, 4.0F, 2, 7, 1);
/* 152 */     this.muleRightEar.func_78793_a(0.0F, 4.0F, -10.0F);
/* 153 */     setBoxRotation(this.muleRightEar, 0.5235988F, 0.0F, -0.2617994F);
/* 154 */     this.neck = new ModelRenderer(this, 0, 12);
/* 155 */     this.neck.func_78789_a(-2.05F, -9.8F, -2.0F, 4, 14, 8);
/* 156 */     this.neck.func_78793_a(0.0F, 4.0F, -10.0F);
/* 157 */     setBoxRotation(this.neck, 0.5235988F, 0.0F, 0.0F);
/* 158 */     this.muleLeftChest = new ModelRenderer(this, 0, 34);
/* 159 */     this.muleLeftChest.func_78789_a(-3.0F, 0.0F, 0.0F, 8, 8, 3);
/* 160 */     this.muleLeftChest.func_78793_a(-7.5F, 3.0F, 10.0F);
/* 161 */     setBoxRotation(this.muleLeftChest, 0.0F, 1.5707964F, 0.0F);
/* 162 */     this.muleRightChest = new ModelRenderer(this, 0, 47);
/* 163 */     this.muleRightChest.func_78789_a(-3.0F, 0.0F, 0.0F, 8, 8, 3);
/* 164 */     this.muleRightChest.func_78793_a(4.5F, 3.0F, 10.0F);
/* 165 */     setBoxRotation(this.muleRightChest, 0.0F, 1.5707964F, 0.0F);
/* 166 */     this.horseSaddleBottom = new ModelRenderer(this, 80, 0);
/* 167 */     this.horseSaddleBottom.func_78789_a(-5.0F, 0.0F, -3.0F, 10, 1, 8);
/* 168 */     this.horseSaddleBottom.func_78793_a(0.0F, 2.0F, 2.0F);
/* 169 */     this.horseSaddleFront = new ModelRenderer(this, 106, 9);
/* 170 */     this.horseSaddleFront.func_78789_a(-1.5F, -1.0F, -3.0F, 3, 1, 2);
/* 171 */     this.horseSaddleFront.func_78793_a(0.0F, 2.0F, 2.0F);
/* 172 */     this.horseSaddleBack = new ModelRenderer(this, 80, 9);
/* 173 */     this.horseSaddleBack.func_78789_a(-4.0F, -1.0F, 3.0F, 8, 1, 2);
/* 174 */     this.horseSaddleBack.func_78793_a(0.0F, 2.0F, 2.0F);
/* 175 */     this.horseLeftSaddleMetal = new ModelRenderer(this, 74, 0);
/* 176 */     this.horseLeftSaddleMetal.func_78789_a(-0.5F, 6.0F, -1.0F, 1, 2, 2);
/* 177 */     this.horseLeftSaddleMetal.func_78793_a(5.0F, 3.0F, 2.0F);
/* 178 */     this.horseLeftSaddleRope = new ModelRenderer(this, 70, 0);
/* 179 */     this.horseLeftSaddleRope.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 6, 1);
/* 180 */     this.horseLeftSaddleRope.func_78793_a(5.0F, 3.0F, 2.0F);
/* 181 */     this.horseRightSaddleMetal = new ModelRenderer(this, 74, 4);
/* 182 */     this.horseRightSaddleMetal.func_78789_a(-0.5F, 6.0F, -1.0F, 1, 2, 2);
/* 183 */     this.horseRightSaddleMetal.func_78793_a(-5.0F, 3.0F, 2.0F);
/* 184 */     this.horseRightSaddleRope = new ModelRenderer(this, 80, 0);
/* 185 */     this.horseRightSaddleRope.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 6, 1);
/* 186 */     this.horseRightSaddleRope.func_78793_a(-5.0F, 3.0F, 2.0F);
/* 187 */     this.horseLeftFaceMetal = new ModelRenderer(this, 74, 13);
/* 188 */     this.horseLeftFaceMetal.func_78789_a(1.5F, -8.0F, -4.0F, 1, 2, 2);
/* 189 */     this.horseLeftFaceMetal.func_78793_a(0.0F, 4.0F, -10.0F);
/* 190 */     setBoxRotation(this.horseLeftFaceMetal, 0.5235988F, 0.0F, 0.0F);
/* 191 */     this.horseRightFaceMetal = new ModelRenderer(this, 74, 13);
/* 192 */     this.horseRightFaceMetal.func_78789_a(-2.5F, -8.0F, -4.0F, 1, 2, 2);
/* 193 */     this.horseRightFaceMetal.func_78793_a(0.0F, 4.0F, -10.0F);
/* 194 */     setBoxRotation(this.horseRightFaceMetal, 0.5235988F, 0.0F, 0.0F);
/* 195 */     this.horseLeftRein = new ModelRenderer(this, 44, 10);
/* 196 */     this.horseLeftRein.func_78789_a(2.6F, -6.0F, -6.0F, 0, 3, 16);
/* 197 */     this.horseLeftRein.func_78793_a(0.0F, 4.0F, -10.0F);
/* 198 */     this.horseRightRein = new ModelRenderer(this, 44, 5);
/* 199 */     this.horseRightRein.func_78789_a(-2.6F, -6.0F, -6.0F, 0, 3, 16);
/* 200 */     this.horseRightRein.func_78793_a(0.0F, 4.0F, -10.0F);
/* 201 */     this.mane = new ModelRenderer(this, 58, 0);
/* 202 */     this.mane.func_78789_a(-1.0F, -11.5F, 5.0F, 2, 16, 4);
/* 203 */     this.mane.func_78793_a(0.0F, 4.0F, -10.0F);
/* 204 */     setBoxRotation(this.mane, 0.5235988F, 0.0F, 0.0F);
/* 205 */     this.horseFaceRopes = new ModelRenderer(this, 80, 12);
/* 206 */     this.horseFaceRopes.func_78790_a(-2.5F, -10.1F, -7.0F, 5, 5, 12, 0.2F);
/* 207 */     this.horseFaceRopes.func_78793_a(0.0F, 4.0F, -10.0F);
/* 208 */     setBoxRotation(this.horseFaceRopes, 0.5235988F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_78088_a(Entity entity, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
/* 217 */     EntityHorseTFC entityhorse = (EntityHorseTFC)entity;
/* 218 */     int i = entityhorse.func_110265_bP();
/*     */     
/* 220 */     boolean flag = entityhorse.func_110228_bR();
/* 221 */     boolean flag1 = (flag && entityhorse.func_110257_ck());
/* 222 */     boolean flag2 = (flag && entityhorse.func_110261_ca());
/* 223 */     boolean flag3 = (i == 1 || i == 2);
/* 224 */     float f7 = entityhorse.func_110254_bY();
/* 225 */     boolean flag4 = (entityhorse.field_70153_n != null);
/*     */     
/* 227 */     if (flag1) {
/*     */       
/* 229 */       this.horseFaceRopes.func_78785_a(maxZ);
/* 230 */       this.horseSaddleBottom.func_78785_a(maxZ);
/* 231 */       this.horseSaddleFront.func_78785_a(maxZ);
/* 232 */       this.horseSaddleBack.func_78785_a(maxZ);
/* 233 */       this.horseLeftSaddleRope.func_78785_a(maxZ);
/* 234 */       this.horseLeftSaddleMetal.func_78785_a(maxZ);
/* 235 */       this.horseRightSaddleRope.func_78785_a(maxZ);
/* 236 */       this.horseRightSaddleMetal.func_78785_a(maxZ);
/* 237 */       this.horseLeftFaceMetal.func_78785_a(maxZ);
/* 238 */       this.horseRightFaceMetal.func_78785_a(maxZ);
/*     */       
/* 240 */       if (flag4) {
/*     */         
/* 242 */         this.horseLeftRein.func_78785_a(maxZ);
/* 243 */         this.horseRightRein.func_78785_a(maxZ);
/*     */       } 
/*     */     } 
/*     */     
/* 247 */     float percent = TFC_Core.getPercentGrown((IAnimal)entity);
/* 248 */     float ageScale = 2.0F - percent;
/* 249 */     float ageHeadScale = (float)Math.pow((1.0F / ageScale), 0.66D);
/*     */ 
/*     */     
/* 252 */     GL11.glPushMatrix();
/* 253 */     GL11.glTranslatef(0.0F, 0.3F - 0.3F * percent, 0.0F);
/* 254 */     GL11.glPushMatrix();
/* 255 */     GL11.glScalef(1.0F / ageScale, 1.0F / ageScale + 0.25F * (1.0F - percent), 1.0F / ageScale);
/* 256 */     GL11.glTranslatef(0.0F, 0.95F * (1.0F - f7) * (1.0F - percent), 0.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 265 */     this.backLeftLeg.func_78785_a(maxZ);
/* 266 */     this.backLeftShin.func_78785_a(maxZ);
/* 267 */     this.backLeftHoof.func_78785_a(maxZ);
/* 268 */     this.backRightLeg.func_78785_a(maxZ);
/* 269 */     this.backRightShin.func_78785_a(maxZ);
/* 270 */     this.backRightHoof.func_78785_a(maxZ);
/* 271 */     this.frontLeftLeg.func_78785_a(maxZ);
/* 272 */     this.frontLeftShin.func_78785_a(maxZ);
/* 273 */     this.frontLeftHoof.func_78785_a(maxZ);
/* 274 */     this.frontRightLeg.func_78785_a(maxZ);
/* 275 */     this.frontRightShin.func_78785_a(maxZ);
/* 276 */     this.frontRightHoof.func_78785_a(maxZ);
/* 277 */     GL11.glPopMatrix();
/* 278 */     GL11.glPushMatrix();
/* 279 */     GL11.glScalef(1.0F / ageScale, 1.0F / ageScale, 1.0F / ageScale);
/* 280 */     GL11.glTranslatef(0.0F, 1.35F * (1.0F - f7) * (1.0F - percent), 0.0F);
/*     */ 
/*     */     
/* 283 */     this.body.func_78785_a(maxZ);
/* 284 */     this.tailBase.func_78785_a(maxZ);
/* 285 */     this.tailMiddle.func_78785_a(maxZ);
/* 286 */     this.tailTip.func_78785_a(maxZ);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 294 */     this.neck.func_78785_a(maxZ);
/* 295 */     this.mane.func_78785_a(maxZ);
/*     */     
/* 297 */     GL11.glPopMatrix();
/*     */     
/* 299 */     GL11.glPushMatrix();
/*     */ 
/*     */     
/* 302 */     GL11.glScalef(ageHeadScale, ageHeadScale, ageHeadScale);
/* 303 */     GL11.glTranslatef(0.0F, 0.0F, 0.1875F - 0.1875F * percent);
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
/* 322 */     if (flag3) {
/*     */       
/* 324 */       this.muleLeftEar.func_78785_a(maxZ);
/* 325 */       this.muleRightEar.func_78785_a(maxZ);
/*     */     }
/*     */     else {
/*     */       
/* 329 */       this.horseLeftEar.func_78785_a(maxZ);
/* 330 */       this.horseRightEar.func_78785_a(maxZ);
/*     */     } 
/*     */     
/* 333 */     this.head.func_78785_a(maxZ);
/*     */     
/* 335 */     if (flag2) {
/*     */       
/* 337 */       this.muleLeftChest.func_78785_a(maxZ);
/* 338 */       this.muleRightChest.func_78785_a(maxZ);
/*     */     } 
/* 340 */     GL11.glPopMatrix();
/* 341 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setBoxRotation(ModelRenderer renderer, float xAngle, float yAngle, float zAngle) {
/* 349 */     renderer.field_78795_f = xAngle;
/* 350 */     renderer.field_78796_g = yAngle;
/* 351 */     renderer.field_78808_h = zAngle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float updateHorseRotation(float xOffset, float yOffset, float zOffset) {
/*     */     float f3;
/* 361 */     for (f3 = yOffset - xOffset; f3 < -180.0F; f3 += 360.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 366 */     while (f3 >= 180.0F)
/*     */     {
/* 368 */       f3 -= 360.0F;
/*     */     }
/*     */     
/* 371 */     return xOffset + zOffset * f3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_78086_a(EntityLivingBase entity, float x, float y, float z) {
/* 381 */     super.func_78086_a(entity, x, y, z);
/* 382 */     float f3 = updateHorseRotation(entity.field_70760_ar, entity.field_70761_aq, z);
/* 383 */     float f4 = updateHorseRotation(entity.field_70758_at, entity.field_70759_as, z);
/* 384 */     float f5 = entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * z;
/* 385 */     float f6 = f4 - f3;
/* 386 */     float f7 = f5 / 57.295776F;
/*     */     
/* 388 */     if (f6 > 20.0F)
/*     */     {
/* 390 */       f6 = 20.0F;
/*     */     }
/*     */     
/* 393 */     if (f6 < -20.0F)
/*     */     {
/* 395 */       f6 = -20.0F;
/*     */     }
/*     */     
/* 398 */     if (y > 0.2F)
/*     */     {
/* 400 */       f7 += MathHelper.func_76134_b(x * 0.4F) * 0.15F * y;
/*     */     }
/*     */     
/* 403 */     EntityHorseTFC entityhorse = (EntityHorseTFC)entity;
/* 404 */     float f8 = entityhorse.func_110258_o(z);
/* 405 */     float f9 = entityhorse.func_110223_p(z);
/* 406 */     float f10 = 1.0F - f9;
/* 407 */     float f11 = entityhorse.func_110201_q(z);
/* 408 */     boolean flag = (entityhorse.field_110278_bp != 0);
/* 409 */     boolean flag1 = entityhorse.func_110257_ck();
/* 410 */     boolean flag2 = (entityhorse.field_70153_n != null);
/* 411 */     float f12 = entity.field_70173_aa + z;
/* 412 */     float f13 = MathHelper.func_76134_b(x * 0.6662F + 3.1415927F);
/* 413 */     float f14 = f13 * 0.8F * y;
/* 414 */     this.head.field_78797_d = 4.0F;
/* 415 */     this.head.field_78798_e = -10.0F;
/* 416 */     this.tailBase.field_78797_d = 3.0F;
/* 417 */     this.tailMiddle.field_78798_e = 14.0F;
/* 418 */     this.muleRightChest.field_78797_d = 3.0F;
/* 419 */     this.muleRightChest.field_78798_e = 10.0F;
/* 420 */     this.body.field_78795_f = 0.0F;
/* 421 */     this.head.field_78795_f = 0.5235988F + f7;
/* 422 */     this.head.field_78796_g = f6 / 57.295776F;
/* 423 */     this.head.field_78795_f = f9 * (0.2617994F + f7) + f8 * 2.18166F + (1.0F - Math.max(f9, f8)) * this.head.field_78795_f;
/* 424 */     this.head.field_78796_g = f9 * f6 / 57.295776F + (1.0F - Math.max(f9, f8)) * this.head.field_78796_g;
/* 425 */     this.head.field_78797_d = f9 * -6.0F + f8 * 11.0F + (1.0F - Math.max(f9, f8)) * this.head.field_78797_d;
/* 426 */     this.head.field_78798_e = f9 * -1.0F + f8 * -10.0F + (1.0F - Math.max(f9, f8)) * this.head.field_78798_e;
/* 427 */     this.tailBase.field_78797_d = f9 * 9.0F + f10 * this.tailBase.field_78797_d;
/* 428 */     this.tailMiddle.field_78798_e = f9 * 18.0F + f10 * this.tailMiddle.field_78798_e;
/* 429 */     this.muleRightChest.field_78797_d = f9 * 5.5F + f10 * this.muleRightChest.field_78797_d;
/* 430 */     this.muleRightChest.field_78798_e = f9 * 15.0F + f10 * this.muleRightChest.field_78798_e;
/* 431 */     this.body.field_78795_f = f9 * -0.7853982F + f10 * this.body.field_78795_f;
/* 432 */     this.horseLeftEar.field_78797_d = this.head.field_78797_d;
/* 433 */     this.horseRightEar.field_78797_d = this.head.field_78797_d;
/* 434 */     this.muleLeftEar.field_78797_d = this.head.field_78797_d;
/* 435 */     this.muleRightEar.field_78797_d = this.head.field_78797_d;
/* 436 */     this.neck.field_78797_d = this.head.field_78797_d;
/* 437 */     this.mouthTop.field_78797_d = 0.02F;
/* 438 */     this.mouthBottom.field_78797_d = 0.0F;
/* 439 */     this.mane.field_78797_d = this.head.field_78797_d;
/* 440 */     this.horseLeftEar.field_78798_e = this.head.field_78798_e;
/* 441 */     this.horseRightEar.field_78798_e = this.head.field_78798_e;
/* 442 */     this.muleLeftEar.field_78798_e = this.head.field_78798_e;
/* 443 */     this.muleRightEar.field_78798_e = this.head.field_78798_e;
/* 444 */     this.neck.field_78798_e = this.head.field_78798_e;
/* 445 */     this.mouthTop.field_78798_e = 0.02F - f11 * 1.0F;
/* 446 */     this.mouthBottom.field_78798_e = 0.0F + f11 * 1.0F;
/* 447 */     this.mane.field_78798_e = this.head.field_78798_e;
/* 448 */     this.horseLeftEar.field_78795_f = this.head.field_78795_f;
/* 449 */     this.horseRightEar.field_78795_f = this.head.field_78795_f;
/* 450 */     this.muleLeftEar.field_78795_f = this.head.field_78795_f;
/* 451 */     this.muleRightEar.field_78795_f = this.head.field_78795_f;
/* 452 */     this.neck.field_78795_f = this.head.field_78795_f;
/* 453 */     this.mouthTop.field_78795_f = 0.0F - 0.09424778F * f11;
/* 454 */     this.mouthBottom.field_78795_f = 0.0F + 0.15707964F * f11;
/* 455 */     this.mane.field_78795_f = this.head.field_78795_f;
/* 456 */     this.horseLeftEar.field_78796_g = this.head.field_78796_g;
/* 457 */     this.horseRightEar.field_78796_g = this.head.field_78796_g;
/* 458 */     this.muleLeftEar.field_78796_g = this.head.field_78796_g;
/* 459 */     this.muleRightEar.field_78796_g = this.head.field_78796_g;
/* 460 */     this.neck.field_78796_g = this.head.field_78796_g;
/* 461 */     this.mouthTop.field_78796_g = 0.0F;
/* 462 */     this.mouthBottom.field_78796_g = 0.0F;
/* 463 */     this.mane.field_78796_g = this.head.field_78796_g;
/* 464 */     this.muleLeftChest.field_78795_f = f14 / 5.0F;
/* 465 */     this.muleRightChest.field_78795_f = -f14 / 5.0F;
/* 466 */     float f15 = 1.5707964F;
/*     */ 
/*     */     
/* 469 */     float f18 = 0.2617994F * f9;
/* 470 */     float f19 = MathHelper.func_76134_b(f12 * 0.6F + 3.1415927F);
/* 471 */     this.frontLeftLeg.field_78797_d = -2.0F * f9 + 9.0F * f10;
/* 472 */     this.frontLeftLeg.field_78798_e = -2.0F * f9 + -8.0F * f10;
/* 473 */     this.frontRightLeg.field_78797_d = this.frontLeftLeg.field_78797_d;
/* 474 */     this.frontRightLeg.field_78798_e = this.frontLeftLeg.field_78798_e;
/* 475 */     this.backLeftLeg.field_78797_d += MathHelper.func_76126_a(1.5707964F + f18 + f10 * -f13 * 0.5F * y) * 7.0F;
/* 476 */     this.backLeftLeg.field_78798_e += MathHelper.func_76134_b(4.712389F + f18 + f10 * -f13 * 0.5F * y) * 7.0F;
/* 477 */     this.backRightLeg.field_78797_d += MathHelper.func_76126_a(1.5707964F + f18 + f10 * f13 * 0.5F * y) * 7.0F;
/* 478 */     this.backRightLeg.field_78798_e += MathHelper.func_76134_b(4.712389F + f18 + f10 * f13 * 0.5F * y) * 7.0F;
/* 479 */     float f20 = (-1.0471976F + f19) * f9 + f14 * f10;
/* 480 */     float f21 = (-1.0471976F + -f19) * f9 + -f14 * f10;
/* 481 */     this.frontLeftLeg.field_78797_d += MathHelper.func_76126_a(1.5707964F + f20) * 7.0F;
/* 482 */     this.frontLeftLeg.field_78798_e += MathHelper.func_76134_b(4.712389F + f20) * 7.0F;
/* 483 */     this.frontRightLeg.field_78797_d += MathHelper.func_76126_a(1.5707964F + f21) * 7.0F;
/* 484 */     this.frontRightLeg.field_78798_e += MathHelper.func_76134_b(4.712389F + f21) * 7.0F;
/* 485 */     this.backLeftLeg.field_78795_f = f18 + -f13 * 0.5F * y * f10;
/* 486 */     this.backLeftShin.field_78795_f = -0.08726646F * f9 + (-f13 * 0.5F * y - Math.max(0.0F, f13 * 0.5F * y)) * f10;
/* 487 */     this.backLeftHoof.field_78795_f = this.backLeftShin.field_78795_f;
/* 488 */     this.backRightLeg.field_78795_f = f18 + f13 * 0.5F * y * f10;
/* 489 */     this.backRightShin.field_78795_f = -0.08726646F * f9 + (f13 * 0.5F * y - Math.max(0.0F, -f13 * 0.5F * y)) * f10;
/* 490 */     this.backRightHoof.field_78795_f = this.backRightShin.field_78795_f;
/* 491 */     this.frontLeftLeg.field_78795_f = f20;
/* 492 */     this.frontLeftShin.field_78795_f = (this.frontLeftLeg.field_78795_f + 3.1415927F * Math.max(0.0F, 0.2F + f19 * 0.2F)) * f9 + (f14 + Math.max(0.0F, f13 * 0.5F * y)) * f10;
/* 493 */     this.frontLeftHoof.field_78795_f = this.frontLeftShin.field_78795_f;
/* 494 */     this.frontRightLeg.field_78795_f = f21;
/* 495 */     this.frontRightShin.field_78795_f = (this.frontRightLeg.field_78795_f + 3.1415927F * Math.max(0.0F, 0.2F - f19 * 0.2F)) * f9 + (-f14 + Math.max(0.0F, -f13 * 0.5F * y)) * f10;
/* 496 */     this.frontRightHoof.field_78795_f = this.frontRightShin.field_78795_f;
/* 497 */     this.backLeftHoof.field_78797_d = this.backLeftShin.field_78797_d;
/* 498 */     this.backLeftHoof.field_78798_e = this.backLeftShin.field_78798_e;
/* 499 */     this.backRightHoof.field_78797_d = this.backRightShin.field_78797_d;
/* 500 */     this.backRightHoof.field_78798_e = this.backRightShin.field_78798_e;
/* 501 */     this.frontLeftHoof.field_78797_d = this.frontLeftShin.field_78797_d;
/* 502 */     this.frontLeftHoof.field_78798_e = this.frontLeftShin.field_78798_e;
/* 503 */     this.frontRightHoof.field_78797_d = this.frontRightShin.field_78797_d;
/* 504 */     this.frontRightHoof.field_78798_e = this.frontRightShin.field_78798_e;
/*     */     
/* 506 */     if (flag1) {
/*     */       
/* 508 */       this.horseSaddleBottom.field_78797_d = f9 * 0.5F + f10 * 2.0F;
/* 509 */       this.horseSaddleBottom.field_78798_e = f9 * 11.0F + f10 * 2.0F;
/* 510 */       this.horseSaddleFront.field_78797_d = this.horseSaddleBottom.field_78797_d;
/* 511 */       this.horseSaddleBack.field_78797_d = this.horseSaddleBottom.field_78797_d;
/* 512 */       this.horseLeftSaddleRope.field_78797_d = this.horseSaddleBottom.field_78797_d;
/* 513 */       this.horseRightSaddleRope.field_78797_d = this.horseSaddleBottom.field_78797_d;
/* 514 */       this.horseLeftSaddleMetal.field_78797_d = this.horseSaddleBottom.field_78797_d;
/* 515 */       this.horseRightSaddleMetal.field_78797_d = this.horseSaddleBottom.field_78797_d;
/* 516 */       this.muleLeftChest.field_78797_d = this.muleRightChest.field_78797_d;
/* 517 */       this.horseSaddleFront.field_78798_e = this.horseSaddleBottom.field_78798_e;
/* 518 */       this.horseSaddleBack.field_78798_e = this.horseSaddleBottom.field_78798_e;
/* 519 */       this.horseLeftSaddleRope.field_78798_e = this.horseSaddleBottom.field_78798_e;
/* 520 */       this.horseRightSaddleRope.field_78798_e = this.horseSaddleBottom.field_78798_e;
/* 521 */       this.horseLeftSaddleMetal.field_78798_e = this.horseSaddleBottom.field_78798_e;
/* 522 */       this.horseRightSaddleMetal.field_78798_e = this.horseSaddleBottom.field_78798_e;
/* 523 */       this.muleLeftChest.field_78798_e = this.muleRightChest.field_78798_e;
/* 524 */       this.horseSaddleBottom.field_78795_f = this.body.field_78795_f;
/* 525 */       this.horseSaddleFront.field_78795_f = this.body.field_78795_f;
/* 526 */       this.horseSaddleBack.field_78795_f = this.body.field_78795_f;
/* 527 */       this.horseLeftRein.field_78797_d = this.head.field_78797_d;
/* 528 */       this.horseRightRein.field_78797_d = this.head.field_78797_d;
/* 529 */       this.horseFaceRopes.field_78797_d = this.head.field_78797_d;
/* 530 */       this.horseLeftFaceMetal.field_78797_d = this.head.field_78797_d;
/* 531 */       this.horseRightFaceMetal.field_78797_d = this.head.field_78797_d;
/* 532 */       this.horseLeftRein.field_78798_e = this.head.field_78798_e;
/* 533 */       this.horseRightRein.field_78798_e = this.head.field_78798_e;
/* 534 */       this.horseFaceRopes.field_78798_e = this.head.field_78798_e;
/* 535 */       this.horseLeftFaceMetal.field_78798_e = this.head.field_78798_e;
/* 536 */       this.horseRightFaceMetal.field_78798_e = this.head.field_78798_e;
/* 537 */       this.horseLeftRein.field_78795_f = f7;
/* 538 */       this.horseRightRein.field_78795_f = f7;
/* 539 */       this.horseFaceRopes.field_78795_f = this.head.field_78795_f;
/* 540 */       this.horseLeftFaceMetal.field_78795_f = this.head.field_78795_f;
/* 541 */       this.horseRightFaceMetal.field_78795_f = this.head.field_78795_f;
/* 542 */       this.horseFaceRopes.field_78796_g = this.head.field_78796_g;
/* 543 */       this.horseLeftFaceMetal.field_78796_g = this.head.field_78796_g;
/* 544 */       this.horseLeftRein.field_78796_g = this.head.field_78796_g;
/* 545 */       this.horseRightFaceMetal.field_78796_g = this.head.field_78796_g;
/* 546 */       this.horseRightRein.field_78796_g = this.head.field_78796_g;
/*     */       
/* 548 */       if (flag2) {
/*     */         
/* 550 */         this.horseLeftSaddleRope.field_78795_f = -1.0471976F;
/* 551 */         this.horseLeftSaddleMetal.field_78795_f = -1.0471976F;
/* 552 */         this.horseRightSaddleRope.field_78795_f = -1.0471976F;
/* 553 */         this.horseRightSaddleMetal.field_78795_f = -1.0471976F;
/* 554 */         this.horseLeftSaddleRope.field_78808_h = 0.0F;
/* 555 */         this.horseLeftSaddleMetal.field_78808_h = 0.0F;
/* 556 */         this.horseRightSaddleRope.field_78808_h = 0.0F;
/* 557 */         this.horseRightSaddleMetal.field_78808_h = 0.0F;
/*     */       }
/*     */       else {
/*     */         
/* 561 */         this.horseLeftSaddleRope.field_78795_f = f14 / 3.0F;
/* 562 */         this.horseLeftSaddleMetal.field_78795_f = f14 / 3.0F;
/* 563 */         this.horseRightSaddleRope.field_78795_f = f14 / 3.0F;
/* 564 */         this.horseRightSaddleMetal.field_78795_f = f14 / 3.0F;
/* 565 */         this.horseLeftSaddleRope.field_78808_h = f14 / 5.0F;
/* 566 */         this.horseLeftSaddleMetal.field_78808_h = f14 / 5.0F;
/* 567 */         this.horseRightSaddleRope.field_78808_h = -f14 / 5.0F;
/* 568 */         this.horseRightSaddleMetal.field_78808_h = -f14 / 5.0F;
/*     */       } 
/*     */     } 
/*     */     
/* 572 */     f15 = -1.3089F + y * 1.5F;
/*     */     
/* 574 */     if (f15 > 0.0F)
/*     */     {
/* 576 */       f15 = 0.0F;
/*     */     }
/*     */     
/* 579 */     if (flag) {
/*     */       
/* 581 */       this.tailBase.field_78796_g = MathHelper.func_76134_b(f12 * 0.7F);
/* 582 */       f15 = 0.0F;
/*     */     }
/*     */     else {
/*     */       
/* 586 */       this.tailBase.field_78796_g = 0.0F;
/*     */     } 
/*     */     
/* 589 */     this.tailMiddle.field_78796_g = this.tailBase.field_78796_g;
/* 590 */     this.tailTip.field_78796_g = this.tailBase.field_78796_g;
/* 591 */     this.tailMiddle.field_78797_d = this.tailBase.field_78797_d;
/* 592 */     this.tailTip.field_78797_d = this.tailBase.field_78797_d;
/* 593 */     this.tailMiddle.field_78798_e = this.tailBase.field_78798_e;
/* 594 */     this.tailTip.field_78798_e = this.tailBase.field_78798_e;
/* 595 */     this.tailBase.field_78795_f = f15;
/* 596 */     this.tailMiddle.field_78795_f = f15;
/* 597 */     this.tailTip.field_78795_f = -0.2618F + f15;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Models\ModelHorseTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */