/*     */ package com.bioxx.tfc.Render;
/*     */ 
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityHorseTFC;
/*     */ import com.google.common.collect.Maps;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.renderer.entity.RenderHorse;
/*     */ import net.minecraft.client.renderer.texture.ITextureObject;
/*     */ import net.minecraft.client.renderer.texture.LayeredTexture;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.passive.EntityHorse;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderHorseTFC
/*     */   extends RenderHorse
/*     */ {
/*  24 */   private static final Map<String, ResourceLocation> TEXTURE_MAP = Maps.newHashMap();
/*  25 */   private static final ResourceLocation WHITE_TEXTURE = new ResourceLocation("textures/entity/horse/horse_white.png");
/*  26 */   private static final ResourceLocation MULE_TEXTURE = new ResourceLocation("textures/entity/horse/mule.png");
/*  27 */   private static final ResourceLocation DONKEY_TEXTURE = new ResourceLocation("textures/entity/horse/donkey.png");
/*  28 */   private static final ResourceLocation ZOMBIE_TEXTURE = new ResourceLocation("textures/entity/horse/horse_zombie.png");
/*  29 */   private static final ResourceLocation SKELETON_TEXTURE = new ResourceLocation("textures/entity/horse/horse_skeleton.png");
/*     */ 
/*     */   
/*     */   public RenderHorseTFC(ModelBase par1ModelBase, float par2) {
/*  33 */     super(par1ModelBase, par2);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void renderHorse(EntityHorse par1EntityHorse, float par2, float par3, float par4, float par5, float par6, float par7) {
/*  38 */     if (par1EntityHorse.func_82150_aj()) {
/*     */       
/*  40 */       this.field_77045_g.func_78087_a(par2, par3, par4, par5, par6, par7, (Entity)par1EntityHorse);
/*     */     }
/*     */     else {
/*     */       
/*  44 */       func_110777_b((Entity)par1EntityHorse);
/*  45 */       this.field_77045_g.func_78088_a((Entity)par1EntityHorse, par2, par3, par4, par5, par6, par7);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation getTextureLocation(EntityHorse par1EntityHorse) {
/*  51 */     if (!par1EntityHorse.func_110239_cn()) {
/*     */       
/*  53 */       switch (par1EntityHorse.func_110265_bP())
/*     */       
/*     */       { 
/*     */         default:
/*  57 */           return WHITE_TEXTURE;
/*     */         case 1:
/*  59 */           return DONKEY_TEXTURE;
/*     */         case 2:
/*  61 */           return MULE_TEXTURE;
/*     */         case 3:
/*  63 */           return ZOMBIE_TEXTURE;
/*     */         case 4:
/*  65 */           break; }  return SKELETON_TEXTURE;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  70 */     return getTexture(par1EntityHorse);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private ResourceLocation getTexture(EntityHorse par1EntityHorse) {
/*  76 */     String s = par1EntityHorse.func_110264_co();
/*  77 */     ResourceLocation resourcelocation = TEXTURE_MAP.get(s);
/*     */     
/*  79 */     if (resourcelocation == null) {
/*     */       
/*  81 */       resourcelocation = new ResourceLocation(s);
/*  82 */       Minecraft.func_71410_x().func_110434_K().func_110579_a(resourcelocation, (ITextureObject)new LayeredTexture(par1EntityHorse.func_110212_cp()));
/*  83 */       TEXTURE_MAP.put(s, resourcelocation);
/*     */     } 
/*     */     
/*  86 */     return resourcelocation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_77041_b(EntityLivingBase par1EntityLivingBase, float par2) {
/*  96 */     float scale = ((EntityHorseTFC)par1EntityLivingBase).getSizeMod();
/*  97 */     GL11.glScalef(scale, scale, scale);
/*  98 */     func_77041_b((EntityHorse)par1EntityLivingBase, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_77036_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4, float par5, float par6, float par7) {
/* 107 */     renderHorse((EntityHorse)par1EntityLivingBase, par2, par3, par4, par5, par6, par7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity par1Entity) {
/* 116 */     return getTextureLocation((EntityHorse)par1Entity);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\RenderHorseTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */