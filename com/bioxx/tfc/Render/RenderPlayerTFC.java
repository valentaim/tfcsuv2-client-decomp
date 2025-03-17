/*     */ package com.bioxx.tfc.Render;
/*     */ 
/*     */ import com.bioxx.tfc.Entities.EntityStand;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.entity.AbstractClientPlayer;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelBiped;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.entity.RenderBiped;
/*     */ import net.minecraft.client.renderer.entity.RenderPlayer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.client.ForgeHooksClient;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class RenderPlayerTFC
/*     */   extends RenderPlayer
/*     */ {
/*     */   private ModelBiped modelArmorChestplate;
/*     */   private ModelBiped modelArmor;
/*  31 */   public static String[] armorFilenamePrefix = new String[] { "cloth", "chain", "iron", "diamond", "gold" };
/*     */   
/*     */   public static final float NAME_TAG_RANGE = 64.0F;
/*     */   
/*     */   public static final float NAME_TAG_RANGE_SNEAK = 32.0F;
/*     */   
/*     */   private ModelRenderer plume;
/*     */   private ModelRenderer plume2;
/*     */   private ModelRenderer hornR1;
/*     */   private ModelRenderer hornL1;
/*     */   private ModelRenderer hornR2;
/*     */   private ModelRenderer hornL2;
/*     */   
/*     */   public RenderPlayerTFC() {
/*  45 */     this.field_77109_a = (ModelBiped)this.field_77045_g;
/*  46 */     this.modelArmorChestplate = new ModelBiped(1.0F);
/*  47 */     this.modelArmor = new ModelBiped(0.5F);
/*     */ 
/*     */     
/*  50 */     this.plume = new ModelRenderer((ModelBase)this.modelArmorChestplate, 40, 0);
/*  51 */     this.plume2 = new ModelRenderer((ModelBase)this.modelArmorChestplate, 40, 0);
/*  52 */     this.plume.func_78790_a(-1.0F, -6.0F, -10.0F, 2, 6, 10, 0.5F);
/*  53 */     this.plume2.func_78789_a(-1.0F, -6.0F, -10.0F, 2, 6, 10);
/*  54 */     this.plume.func_78793_a(0.0F, -8.0F, 2.0F);
/*  55 */     this.plume2.func_78793_a(0.0F, -2.0F, 4.0F);
/*  56 */     this.plume2.field_78795_f = -1.0471976F;
/*     */     
/*  58 */     this.hornR1 = new ModelRenderer((ModelBase)this.modelArmorChestplate, 40, 0);
/*  59 */     this.hornR1.func_78789_a(-6.0F, -1.5F, -1.5F, 3, 3, 6);
/*  60 */     this.hornL1 = new ModelRenderer((ModelBase)this.modelArmorChestplate, 40, 0);
/*  61 */     this.hornL1.func_78789_a(6.0F, -1.5F, -1.5F, 3, 3, 6);
/*  62 */     this.hornR1.func_78793_a(-6.0F, -6.0F, 5.0F);
/*  63 */     this.hornL1.func_78793_a(6.0F, -6.0F, 8.0F);
/*  64 */     this.hornR1.field_78796_g = -1.5707964F;
/*  65 */     this.hornR1.field_78795_f = -0.2617994F;
/*  66 */     this.hornL1.field_78796_g = 1.5707964F;
/*  67 */     this.hornL1.field_78795_f = -0.2617994F;
/*  68 */     this.hornR2 = new ModelRenderer((ModelBase)this.modelArmorChestplate, 40, 9);
/*  69 */     this.hornR2.func_78789_a(0.0F, 0.0F, -5.0F, 2, 2, 5);
/*  70 */     this.hornR2.func_78793_a(-6.0F, 0.0F, 2.0F);
/*  71 */     this.hornR2.field_78795_f = 1.5707964F;
/*  72 */     this.hornR2.field_78808_h = 0.5235988F;
/*  73 */     this.hornL2 = new ModelRenderer((ModelBase)this.modelArmorChestplate, 40, 9);
/*  74 */     this.hornL2.func_78789_a(0.0F, 0.0F, -5.0F, 2, 2, 5);
/*  75 */     this.hornL2.func_78793_a(7.0F, 0.0F, 2.0F);
/*  76 */     this.hornL2.field_78795_f = 1.5707964F;
/*  77 */     this.hornL2.field_78808_h = -0.5235988F;
/*     */     
/*  79 */     this.modelArmorChestplate.field_78116_c.func_78792_a(this.plume);
/*  80 */     this.modelArmorChestplate.field_78116_c.func_78792_a(this.plume2);
/*  81 */     this.modelArmorChestplate.field_78116_c.func_78792_a(this.hornR1);
/*  82 */     this.modelArmorChestplate.field_78116_c.func_78792_a(this.hornL1);
/*  83 */     this.hornR1.func_78792_a(this.hornR2);
/*  84 */     this.hornL1.func_78792_a(this.hornL2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int func_77032_a(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
/*  90 */     if (par1EntityLivingBase instanceof EntityStand)
/*     */     {
/*  92 */       return setArmorModelTFC((EntityStand)par1EntityLivingBase, par2, par3);
/*     */     }
/*  94 */     return func_77032_a((AbstractClientPlayer)par1EntityLivingBase, par2, par3);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int setArmorModelTFC(EntityStand stand, int par2, float par3) {
/*  99 */     ItemStack itemstack = stand.func_71124_b(3 - par2);
/*     */     
/* 101 */     if (itemstack != null) {
/*     */       
/* 103 */       Item item = itemstack.func_77973_b();
/*     */       
/* 105 */       if (item instanceof ItemArmor) {
/*     */         
/* 107 */         ItemArmor itemarmor = (ItemArmor)item;
/* 108 */         func_110776_a(RenderBiped.getArmorResource((Entity)stand, itemstack, par2, null));
/* 109 */         ModelBiped modelbiped = (par2 == 2) ? this.modelArmor : this.modelArmorChestplate;
/* 110 */         modelbiped.field_78116_c.field_78806_j = (par2 == 0);
/* 111 */         modelbiped.field_78114_d.field_78806_j = (par2 == 0);
/* 112 */         modelbiped.field_78115_e.field_78806_j = (par2 == 1 || par2 == 2);
/* 113 */         modelbiped.field_78112_f.field_78806_j = (par2 == 1);
/* 114 */         modelbiped.field_78113_g.field_78806_j = (par2 == 1);
/* 115 */         modelbiped.field_78123_h.field_78806_j = (par2 == 2 || par2 == 3);
/* 116 */         modelbiped.field_78124_i.field_78806_j = (par2 == 2 || par2 == 3);
/* 117 */         modelbiped = ForgeHooksClient.getArmorModel((EntityLivingBase)stand, itemstack, par2, modelbiped);
/* 118 */         func_77042_a((ModelBase)modelbiped);
/* 119 */         modelbiped.field_78095_p = this.field_77045_g.field_78095_p;
/* 120 */         modelbiped.field_78093_q = this.field_77045_g.field_78093_q;
/* 121 */         modelbiped.field_78091_s = this.field_77045_g.field_78091_s;
/* 122 */         float f1 = 1.0F;
/*     */ 
/*     */         
/* 125 */         int j = itemarmor.func_82814_b(itemstack);
/* 126 */         if (j != -1) {
/*     */           
/* 128 */           float f2 = (j >> 16 & 0xFF) / 255.0F;
/* 129 */           float f3 = (j >> 8 & 0xFF) / 255.0F;
/* 130 */           float f4 = (j & 0xFF) / 255.0F;
/* 131 */           GL11.glColor3f(f1 * f2, f1 * f3, f1 * f4);
/*     */           
/* 133 */           if (itemstack.func_77948_v())
/*     */           {
/* 135 */             return 31;
/*     */           }
/*     */           
/* 138 */           return 16;
/*     */         } 
/*     */         
/* 141 */         GL11.glColor3f(f1, f1, f1);
/*     */         
/* 143 */         if (itemstack.func_77948_v())
/*     */         {
/* 145 */           return 15;
/*     */         }
/*     */         
/* 148 */         return 1;
/*     */       } 
/*     */     } 
/* 151 */     return -1;
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
/*     */   protected int func_77032_a(AbstractClientPlayer par1AbstractClientPlayer, int slotIndex, float partialTick) {
/* 186 */     ItemStack itemstack = par1AbstractClientPlayer.field_71071_by.func_70440_f(3 - slotIndex);
/*     */     
/* 188 */     this.plume.field_78806_j = false;
/* 189 */     this.plume2.field_78806_j = false;
/* 190 */     this.hornR1.field_78806_j = false;
/* 191 */     this.hornL1.field_78806_j = false;
/* 192 */     if (itemstack != null) {
/*     */       
/* 194 */       Item item = itemstack.func_77973_b();
/*     */       
/* 196 */       if (item instanceof ItemArmor) {
/*     */         
/* 198 */         ItemArmor itemarmor = (ItemArmor)item;
/* 199 */         func_110776_a(RenderBiped.getArmorResource((Entity)par1AbstractClientPlayer, itemstack, slotIndex, null));
/* 200 */         ModelBiped modelbiped = (slotIndex == 2) ? this.modelArmor : this.modelArmorChestplate;
/* 201 */         modelbiped.field_78116_c.field_78806_j = (slotIndex == 0);
/* 202 */         this.plume.field_78806_j = false;
/* 203 */         this.plume2.field_78806_j = false;
/* 204 */         this.hornR1.field_78806_j = false;
/* 205 */         this.hornL1.field_78806_j = false;
/* 206 */         modelbiped.field_78114_d
/* 207 */           .field_78806_j = (slotIndex == 0 && itemstack.func_77973_b() != TFCItems.bronzeHelmet && itemstack.func_77973_b() != TFCItems.wroughtIronHelmet);
/* 208 */         modelbiped.field_78115_e.field_78806_j = (slotIndex == 1 || slotIndex == 2);
/* 209 */         modelbiped.field_78112_f.field_78806_j = (slotIndex == 1);
/* 210 */         modelbiped.field_78113_g.field_78806_j = (slotIndex == 1);
/* 211 */         modelbiped.field_78123_h.field_78806_j = (slotIndex == 2 || slotIndex == 3);
/* 212 */         modelbiped.field_78124_i.field_78806_j = (slotIndex == 2 || slotIndex == 3);
/* 213 */         func_77042_a((ModelBase)modelbiped);
/*     */         
/* 215 */         modelbiped.field_78095_p = this.field_77045_g.field_78095_p;
/* 216 */         modelbiped.field_78093_q = this.field_77045_g.field_78093_q;
/* 217 */         modelbiped.field_78091_s = this.field_77045_g.field_78091_s;
/*     */         
/* 219 */         float f1 = 1.0F;
/*     */         
/* 221 */         if (itemarmor.func_82812_d() == ItemArmor.ArmorMaterial.CLOTH) {
/*     */           
/* 223 */           int j = itemarmor.func_82814_b(itemstack);
/* 224 */           float f2 = (j >> 16 & 0xFF) / 255.0F;
/* 225 */           float f3 = (j >> 8 & 0xFF) / 255.0F;
/* 226 */           float f4 = (j & 0xFF) / 255.0F;
/* 227 */           GL11.glColor3f(f1 * f2, f1 * f3, f1 * f4);
/*     */           
/* 229 */           if (itemstack.func_77948_v())
/*     */           {
/* 231 */             return 31;
/*     */           }
/*     */           
/* 234 */           return 16;
/*     */         } 
/*     */         
/* 237 */         GL11.glColor3f(f1, f1, f1);
/*     */         
/* 239 */         if (itemstack.func_77948_v())
/*     */         {
/* 241 */           return 15;
/*     */         }
/*     */         
/* 244 */         return 1;
/*     */       } 
/*     */     } 
/* 247 */     return -1;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\RenderPlayerTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */