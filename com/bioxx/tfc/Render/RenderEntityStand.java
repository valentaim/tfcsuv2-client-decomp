/*     */ package com.bioxx.tfc.Render;
/*     */ 
/*     */ import com.bioxx.tfc.Entities.EntityStand;
/*     */ import com.bioxx.tfc.Render.Models.ModelStand;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.entity.RenderBiped;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.ForgeHooksClient;
/*     */ import net.minecraftforge.client.event.RenderPlayerEvent;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderEntityStand
/*     */   extends RenderBiped
/*     */ {
/*     */   private ModelBiped modelArmorChestplate;
/*     */   private ModelBiped modelArmor;
/*  31 */   private static final ResourceLocation TEXTURE = new ResourceLocation("terrafirmacraft", "textures/mob/stand.png");
/*     */   
/*     */   private ModelRenderer plume;
/*     */   
/*     */   private ModelRenderer plume2;
/*     */   private ModelRenderer hornR1;
/*     */   private ModelRenderer hornL1;
/*     */   private ModelRenderer hornR2;
/*     */   private ModelRenderer hornL2;
/*  40 */   private RenderLargeItem standBlockRenderer = new RenderLargeItem();
/*     */ 
/*     */   
/*     */   public RenderEntityStand() {
/*  44 */     super((ModelBiped)new ModelStand(), 0.5F);
/*  45 */     this.field_77071_a = (ModelBiped)this.field_77045_g;
/*  46 */     this.modelArmorChestplate = (ModelBiped)new ModelStand(1.0F);
/*  47 */     this.modelArmor = (ModelBiped)new ModelStand(0.5F);
/*     */     
/*  49 */     this.plume = new ModelRenderer((ModelBase)this.modelArmorChestplate, 40, 0);
/*  50 */     this.plume2 = new ModelRenderer((ModelBase)this.modelArmorChestplate, 40, 0);
/*  51 */     this.plume.func_78790_a(-1.0F, -6.0F, -10.0F, 2, 6, 10, 0.5F);
/*  52 */     this.plume2.func_78789_a(-1.0F, -6.0F, -10.0F, 2, 6, 10);
/*  53 */     this.plume.func_78793_a(0.0F, -8.0F, 2.0F);
/*  54 */     this.plume2.func_78793_a(0.0F, -2.0F, 4.0F);
/*  55 */     this.plume2.field_78795_f = -1.0471976F;
/*     */     
/*  57 */     this.hornR1 = new ModelRenderer((ModelBase)this.modelArmorChestplate, 40, 0);
/*  58 */     this.hornR1.func_78789_a(-6.0F, -1.5F, -1.5F, 3, 3, 6);
/*  59 */     this.hornL1 = new ModelRenderer((ModelBase)this.modelArmorChestplate, 40, 0);
/*  60 */     this.hornL1.func_78789_a(6.0F, -1.5F, -1.5F, 3, 3, 6);
/*  61 */     this.hornR1.func_78793_a(-6.0F, -6.0F, 5.0F);
/*  62 */     this.hornL1.func_78793_a(6.0F, -6.0F, 8.0F);
/*  63 */     this.hornR1.field_78796_g = -1.5707964F;
/*  64 */     this.hornR1.field_78795_f = -0.2617994F;
/*  65 */     this.hornL1.field_78796_g = 1.5707964F;
/*  66 */     this.hornL1.field_78795_f = -0.2617994F;
/*  67 */     this.hornR2 = new ModelRenderer((ModelBase)this.modelArmorChestplate, 40, 9);
/*  68 */     this.hornR2.func_78789_a(0.0F, 0.0F, -5.0F, 2, 2, 5);
/*  69 */     this.hornR2.func_78793_a(-6.0F, 0.0F, 2.0F);
/*  70 */     this.hornR2.field_78795_f = 1.5707964F;
/*  71 */     this.hornR2.field_78808_h = 0.5235988F;
/*  72 */     this.hornL2 = new ModelRenderer((ModelBase)this.modelArmorChestplate, 40, 9);
/*  73 */     this.hornL2.func_78789_a(0.0F, 0.0F, -5.0F, 2, 2, 5);
/*  74 */     this.hornL2.func_78793_a(7.0F, 0.0F, 2.0F);
/*  75 */     this.hornL2.field_78795_f = 1.5707964F;
/*  76 */     this.hornL2.field_78808_h = -0.5235988F;
/*     */     
/*  78 */     this.modelArmorChestplate.field_78116_c.func_78792_a(this.plume);
/*  79 */     this.modelArmorChestplate.field_78116_c.func_78792_a(this.plume2);
/*  80 */     this.modelArmorChestplate.field_78116_c.func_78792_a(this.hornR1);
/*  81 */     this.modelArmorChestplate.field_78116_c.func_78792_a(this.hornL1);
/*  82 */     this.hornR1.func_78792_a(this.hornR2);
/*  83 */     this.hornL1.func_78792_a(this.hornL2);
/*  84 */     this.hornR1.field_78806_j = false;
/*  85 */     this.hornL1.field_78806_j = false;
/*  86 */     this.plume.field_78806_j = false;
/*  87 */     this.plume2.field_78806_j = false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int func_77032_a(EntityLivingBase entity, int pass, float delta) {
/*  93 */     return setArmorModelTFC((EntityStand)entity, pass, delta);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_110775_a(EntityLiving entity) {
/*  99 */     return TEXTURE;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_76986_a(Entity e, double par2, double par3, double par4, float par5, float par6) {
/* 104 */     float rotation = (e instanceof EntityStand) ? ((EntityStand)e).getRotation() : 0.0F;
/* 105 */     GL11.glPushMatrix();
/*     */     
/* 107 */     super.func_76986_a(e, par2, par3, par4, rotation, 0.0F);
/* 108 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_77043_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
/* 114 */     super.func_77043_a(par1EntityLivingBase, par2, par3, par4);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_77041_b(EntityLivingBase entity, float par2) {
/* 120 */     GL11.glScalef(1.0F, 0.95F, 1.0F);
/*     */     
/* 122 */     int l = 0;
/* 123 */     if (entity instanceof EntityStand) {
/* 124 */       l = ((EntityStand)entity).woodType;
/*     */     }
/* 126 */     this.standBlockRenderer.render(entity, new ItemStack(TFCBlocks.armorStand, 1, l), (RenderPlayerEvent.Specials.Pre)null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int setArmorModelTFC(EntityStand stand, int par2, float par3) {
/* 131 */     ItemStack itemstack = stand.getArmorInSlot(3 - par2);
/*     */     
/* 133 */     if (itemstack != null) {
/*     */       
/* 135 */       Item item = itemstack.func_77973_b();
/*     */       
/* 137 */       if (item instanceof ItemArmor) {
/*     */         
/* 139 */         ItemArmor itemarmor = (ItemArmor)item;
/* 140 */         func_110776_a(RenderBiped.getArmorResource((Entity)stand, itemstack, par2, null));
/* 141 */         ModelBiped modelbiped = (par2 == 2) ? this.modelArmor : this.modelArmorChestplate;
/* 142 */         modelbiped.field_78116_c.field_78806_j = (par2 == 0);
/* 143 */         modelbiped.field_78114_d.field_78806_j = (par2 == 0);
/* 144 */         modelbiped.field_78115_e.field_78806_j = (par2 == 1 || par2 == 2);
/* 145 */         modelbiped.field_78112_f.field_78806_j = (par2 == 1);
/* 146 */         modelbiped.field_78113_g.field_78806_j = (par2 == 1);
/* 147 */         modelbiped.field_78123_h.field_78806_j = (par2 == 2 || par2 == 3);
/* 148 */         modelbiped.field_78124_i.field_78806_j = (par2 == 2 || par2 == 3);
/* 149 */         modelbiped = ForgeHooksClient.getArmorModel((EntityLivingBase)stand, itemstack, par2, modelbiped);
/* 150 */         func_77042_a((ModelBase)modelbiped);
/* 151 */         modelbiped.field_78095_p = this.field_77045_g.field_78095_p;
/* 152 */         modelbiped.field_78093_q = this.field_77045_g.field_78093_q;
/* 153 */         modelbiped.field_78091_s = this.field_77045_g.field_78091_s;
/* 154 */         float f1 = 1.0F;
/*     */ 
/*     */         
/* 157 */         int j = itemarmor.func_82814_b(itemstack);
/* 158 */         if (j != -1) {
/*     */           
/* 160 */           float f2 = (j >> 16 & 0xFF) / 255.0F;
/* 161 */           float f3 = (j >> 8 & 0xFF) / 255.0F;
/* 162 */           float f4 = (j & 0xFF) / 255.0F;
/* 163 */           GL11.glColor3f(f1 * f2, f1 * f3, f1 * f4);
/*     */           
/* 165 */           if (itemstack.func_77948_v())
/*     */           {
/* 167 */             return 31;
/*     */           }
/*     */           
/* 170 */           return 16;
/*     */         } 
/*     */         
/* 173 */         GL11.glColor3f(f1, f1, f1);
/*     */         
/* 175 */         if (itemstack.func_77948_v())
/*     */         {
/* 177 */           return 15;
/*     */         }
/*     */         
/* 180 */         return 1;
/*     */       } 
/*     */     } 
/*     */     
/* 184 */     return -1;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\RenderEntityStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */