/*     */ package com.bioxx.tfc.Render.Models;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelPig;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ public class ModelPigTFC extends ModelPig {
/*     */   ModelRenderer Head;
/*     */   ModelRenderer Trout;
/*     */   ModelRenderer Tusks;
/*     */   ModelRenderer Jaw;
/*     */   ModelRenderer LeftEar;
/*     */   ModelRenderer RightEar;
/*     */   ModelRenderer HeadMane;
/*     */   ModelRenderer Body;
/*     */   ModelRenderer BodyMane;
/*     */   ModelRenderer Tail;
/*     */   ModelRenderer UpperLegRight;
/*     */   ModelRenderer LowerLegRight;
/*     */   ModelRenderer UpperLegLeft;
/*     */   ModelRenderer LowerLegLeft;
/*     */   ModelRenderer UpperHindLegRight;
/*     */   ModelRenderer LowerHindLegRight;
/*     */   ModelRenderer UpperHindLegLeft;
/*     */   ModelRenderer LowerHindLegLeft;
/*     */   
/*     */   public ModelPigTFC() {
/*  30 */     this(1.0F);
/*     */   }
/*     */   
/*     */   public ModelPigTFC(float j) {
/*  34 */     this.field_78090_t = 64;
/*  35 */     this.field_78089_u = 64;
/*  36 */     this.Head = new ModelRenderer((ModelBase)this, 0, 0);
/*  37 */     this.Head.func_78789_a(-3.0F, 0.0F, -5.0F, 6, 6, 5);
/*  38 */     this.Head.func_78793_a(0.0F, 11.0F, -5.0F);
/*  39 */     setRotation(this.Head, 0.2617994F, 0.0F, 0.0F);
/*  40 */     this.Trout = new ModelRenderer((ModelBase)this, 0, 11);
/*  41 */     this.Trout.func_78789_a(-1.5F, 1.5F, -9.5F, 3, 3, 5);
/*  42 */     this.Trout.func_78793_a(0.0F, 11.0F, -5.0F);
/*  43 */     setRotation(this.Trout, 0.3490659F, 0.0F, 0.0F);
/*  44 */     this.Tusks = new ModelRenderer((ModelBase)this, 0, 24);
/*  45 */     this.Tusks.func_78789_a(-2.0F, 3.0F, -8.0F, 4, 2, 1);
/*  46 */     this.Tusks.func_78793_a(0.0F, 11.0F, -5.0F);
/*  47 */     setRotation(this.Tusks, 0.3490659F, 0.0F, 0.0F);
/*  48 */     this.Jaw = new ModelRenderer((ModelBase)this, 0, 19);
/*  49 */     this.Jaw.func_78789_a(-1.0F, 4.9F, -8.5F, 2, 1, 4);
/*  50 */     this.Jaw.func_78793_a(0.0F, 11.0F, -5.0F);
/*  51 */     setRotation(this.Jaw, 0.2617994F, 0.0F, 0.0F);
/*  52 */     this.LeftEar = new ModelRenderer((ModelBase)this, 16, 11);
/*  53 */     this.LeftEar.func_78789_a(1.0F, -4.0F, -2.0F, 2, 4, 2);
/*  54 */     this.LeftEar.func_78793_a(0.0F, 11.0F, -5.0F);
/*  55 */     setRotation(this.LeftEar, 0.6981317F, 0.0F, 0.3490659F);
/*  56 */     this.RightEar = new ModelRenderer((ModelBase)this, 16, 17);
/*  57 */     this.RightEar.func_78789_a(-3.0F, -4.0F, -2.0F, 2, 4, 2);
/*  58 */     this.RightEar.func_78793_a(0.0F, 11.0F, -5.0F);
/*  59 */     setRotation(this.RightEar, 0.6981317F, 0.0F, -0.3490659F);
/*  60 */     this.HeadMane = new ModelRenderer((ModelBase)this, 23, 0);
/*  61 */     this.HeadMane.func_78789_a(-1.0F, -2.0F, -5.0F, 2, 2, 5);
/*  62 */     this.HeadMane.func_78793_a(0.0F, 11.0F, -5.0F);
/*  63 */     setRotation(this.HeadMane, 0.4363323F, 0.0F, 0.0F);
/*  64 */     this.Body = new ModelRenderer((ModelBase)this, 24, 0);
/*  65 */     this.Body.func_78789_a(-3.5F, 0.0F, 0.0F, 7, 8, 13);
/*  66 */     this.Body.func_78793_a(0.0F, 11.0F, -5.0F);
/*  67 */     setRotation(this.Body, -0.0872665F, 0.0F, 0.0F);
/*  68 */     this.BodyMane = new ModelRenderer((ModelBase)this, 0, 27);
/*  69 */     this.BodyMane.func_78789_a(-1.0F, -2.0F, -1.0F, 2, 2, 9);
/*  70 */     this.BodyMane.func_78793_a(0.0F, 11.3F, -4.0F);
/*  71 */     setRotation(this.BodyMane, -0.2617994F, 0.0F, 0.0F);
/*  72 */     this.Tail = new ModelRenderer((ModelBase)this, 60, 38);
/*  73 */     this.Tail.func_78789_a(-0.5F, 0.0F, 0.0F, 1, 5, 1);
/*  74 */     this.Tail.func_78793_a(0.0F, 13.0F, 7.5F);
/*  75 */     setRotation(this.Tail, 0.0872665F, 0.0F, 0.0F);
/*  76 */     this.UpperLegRight = new ModelRenderer((ModelBase)this, 32, 21);
/*  77 */     this.UpperLegRight.func_78789_a(-1.0F, -2.0F, -2.0F, 1, 5, 3);
/*  78 */     this.UpperLegRight.func_78793_a(-3.5F, 16.0F, -2.5F);
/*  79 */     setRotation(this.UpperLegRight, 0.1745329F, 0.0F, 0.0F);
/*  80 */     this.LowerLegRight = new ModelRenderer((ModelBase)this, 32, 29);
/*  81 */     this.LowerLegRight.func_78789_a(-0.5F, 2.0F, -1.0F, 2, 6, 2);
/*  82 */     this.LowerLegRight.func_78793_a(-3.5F, 16.0F, -2.5F);
/*  83 */     this.UpperLegLeft = new ModelRenderer((ModelBase)this, 24, 21);
/*  84 */     this.UpperLegLeft.func_78789_a(0.0F, -2.0F, -2.0F, 1, 5, 3);
/*  85 */     this.UpperLegLeft.func_78793_a(3.5F, 16.0F, -2.5F);
/*  86 */     setRotation(this.UpperLegLeft, 0.1745329F, 0.0F, 0.0F);
/*  87 */     this.LowerLegLeft = new ModelRenderer((ModelBase)this, 24, 29);
/*  88 */     this.LowerLegLeft.func_78789_a(-1.5F, 2.0F, -1.0F, 2, 6, 2);
/*  89 */     this.LowerLegLeft.func_78793_a(3.5F, 16.0F, -2.5F);
/*  90 */     this.UpperHindLegRight = new ModelRenderer((ModelBase)this, 44, 21);
/*  91 */     this.UpperHindLegRight.func_78789_a(-1.5F, -2.0F, -2.0F, 1, 5, 4);
/*  92 */     this.UpperHindLegRight.func_78793_a(-3.0F, 16.0F, 5.5F);
/*  93 */     setRotation(this.UpperHindLegRight, -0.2617994F, 0.0F, 0.0F);
/*  94 */     this.LowerHindLegRight = new ModelRenderer((ModelBase)this, 46, 30);
/*  95 */     this.LowerHindLegRight.func_78789_a(-1.0F, 2.0F, 0.0F, 2, 6, 2);
/*  96 */     this.LowerHindLegRight.func_78793_a(-3.0F, 16.0F, 5.5F);
/*  97 */     this.UpperHindLegLeft = new ModelRenderer((ModelBase)this, 54, 21);
/*  98 */     this.UpperHindLegLeft.func_78789_a(0.5F, -2.0F, -2.0F, 1, 5, 4);
/*  99 */     this.UpperHindLegLeft.func_78793_a(3.0F, 16.0F, 5.5F);
/* 100 */     setRotation(this.UpperHindLegLeft, -0.2617994F, 0.0F, 0.0F);
/* 101 */     this.LowerHindLegLeft = new ModelRenderer((ModelBase)this, 56, 30);
/* 102 */     this.LowerHindLegLeft.func_78789_a(-1.0F, 2.0F, 0.0F, 2, 6, 2);
/* 103 */     this.LowerHindLegLeft.func_78793_a(3.0F, 16.0F, 5.5F);
/*     */   }
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 107 */     super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
/* 108 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/* 109 */     this.Head.func_78785_a(f5);
/* 110 */     this.Trout.func_78785_a(f5);
/* 111 */     this.Tusks.func_78785_a(f5);
/* 112 */     this.Jaw.func_78785_a(f5);
/* 113 */     this.LeftEar.func_78785_a(f5);
/* 114 */     this.RightEar.func_78785_a(f5);
/* 115 */     this.HeadMane.func_78785_a(f5);
/* 116 */     this.Body.func_78785_a(f5);
/* 117 */     this.BodyMane.func_78785_a(f5);
/* 118 */     this.Tail.func_78785_a(f5);
/* 119 */     this.UpperLegRight.func_78785_a(f5);
/* 120 */     this.LowerLegRight.func_78785_a(f5);
/* 121 */     this.UpperLegLeft.func_78785_a(f5);
/* 122 */     this.LowerLegLeft.func_78785_a(f5);
/* 123 */     this.UpperHindLegRight.func_78785_a(f5);
/* 124 */     this.LowerHindLegRight.func_78785_a(f5);
/* 125 */     this.UpperHindLegLeft.func_78785_a(f5);
/* 126 */     this.LowerHindLegLeft.func_78785_a(f5);
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 130 */     model.field_78795_f = x;
/* 131 */     model.field_78796_g = y;
/* 132 */     model.field_78808_h = z;
/*     */   }
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/* 136 */     float XAngle = f4 / 57.29578F;
/* 137 */     float YAngle = f3 / 57.29578F;
/* 138 */     this.Head.field_78795_f = 0.2617994F + XAngle;
/* 139 */     this.Head.field_78796_g = YAngle;
/* 140 */     this.HeadMane.field_78795_f = 0.4363323F + XAngle;
/* 141 */     this.HeadMane.field_78796_g = YAngle;
/* 142 */     this.Trout.field_78795_f = 0.3490659F + XAngle;
/* 143 */     this.Trout.field_78796_g = YAngle;
/* 144 */     this.Jaw.field_78795_f = 0.2617994F + XAngle;
/* 145 */     this.Jaw.field_78796_g = YAngle;
/* 146 */     this.Tusks.field_78795_f = 0.3490659F + XAngle;
/* 147 */     this.Tusks.field_78796_g = YAngle;
/* 148 */     this.LeftEar.field_78795_f = 0.6981317F + XAngle;
/* 149 */     this.LeftEar.field_78796_g = YAngle;
/* 150 */     this.RightEar.field_78795_f = 0.6981317F + XAngle;
/* 151 */     this.RightEar.field_78796_g = YAngle;
/* 152 */     float LLegRotX = MathHelper.func_76134_b(f * 0.6662F) * 1.4F * f1;
/* 153 */     float RLegRotX = MathHelper.func_76134_b(f * 0.6662F + 3.141593F) * 1.4F * f1;
/* 154 */     this.UpperLegLeft.field_78795_f = LLegRotX;
/* 155 */     this.LowerLegLeft.field_78795_f = LLegRotX;
/* 156 */     this.UpperHindLegRight.field_78795_f = LLegRotX;
/* 157 */     this.LowerHindLegRight.field_78795_f = LLegRotX;
/* 158 */     this.UpperLegRight.field_78795_f = RLegRotX;
/* 159 */     this.LowerLegRight.field_78795_f = RLegRotX;
/* 160 */     this.UpperHindLegLeft.field_78795_f = RLegRotX;
/* 161 */     this.LowerHindLegLeft.field_78795_f = RLegRotX;
/* 162 */     this.Tail.field_78808_h = LLegRotX * 0.2F;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Models\ModelPigTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */