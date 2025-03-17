/*     */ package com.bioxx.tfc.Items;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Player.FoodStatsTFC;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fluids.FluidContainerRegistry;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemAlcohol
/*     */   extends ItemTerra
/*     */ {
/*     */   public ItemAlcohol() {
/*  28 */     setFolder("food/");
/*  29 */     func_77642_a(TFCItems.glassBottle);
/*  30 */     func_77625_d(16);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumAction func_77661_b(ItemStack par1ItemStack) {
/*  36 */     return EnumAction.drink;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getItemStackLimit(ItemStack is) {
/*  42 */     return this.field_77777_bU;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
/*  49 */     par3EntityPlayer.func_71008_a(par1ItemStack, func_77626_a(par1ItemStack));
/*  50 */     return par1ItemStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public int func_77639_j() {
/*  59 */     return 4;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_77626_a(ItemStack par1ItemStack) {
/*  65 */     return 32;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister registerer) {
/*  72 */     this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:Glass Bottle Overlay");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon getIcon(ItemStack stack, int pass) {
/*  79 */     return (pass == 1) ? this.field_77791_bV : func_77668_q().getIcon(stack, pass);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_82790_a(ItemStack is, int pass) {
/*  86 */     return (pass == 1) ? FluidContainerRegistry.getFluidForFilledItem(is).getFluid().getColor() : super.func_82790_a(is, pass);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77623_v() {
/*  93 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_77654_b(ItemStack is, World world, EntityPlayer player) {
/* 100 */     if (!player.field_71075_bZ.field_75098_d)
/*     */     {
/* 102 */       is.field_77994_a--;
/*     */     }
/*     */     
/* 105 */     if (!world.field_72995_K) {
/*     */ 
/*     */       
/* 108 */       Random rand = new Random();
/* 109 */       FoodStatsTFC fs = TFC_Core.getPlayerFoodStats(player);
/* 110 */       fs.restoreWater(player, 800);
/* 111 */       int time = 400 + rand.nextInt(1000);
/* 112 */       fs.consumeAlcohol();
/* 113 */       if (rand.nextInt(100) == 0) {
/* 114 */         player.func_70690_d(new PotionEffect(8, time, 2));
/*     */       }
/* 116 */       if (rand.nextInt(100) == 0) {
/* 117 */         player.func_70690_d(new PotionEffect(5, time, 1));
/*     */       }
/* 119 */       if (rand.nextInt(100) == 0) {
/* 120 */         player.func_70690_d(new PotionEffect(8, time, 2));
/*     */       }
/* 122 */       if (rand.nextInt(200) == 0) {
/* 123 */         player.func_70690_d(new PotionEffect(10, time, 2));
/*     */       }
/* 125 */       if (rand.nextInt(150) == 0) {
/* 126 */         player.func_70690_d(new PotionEffect(12, time, 4));
/*     */       }
/* 128 */       if (rand.nextInt(180) == 0) {
/* 129 */         player.func_70690_d(new PotionEffect(13, time, 4));
/*     */       }
/* 131 */       int levelMod = 250 * player.field_71068_ca;
/* 132 */       if (fs.soberTime > TFC_Time.getTotalTicks() + 3000L + levelMod)
/*     */       {
/*     */ 
/*     */         
/* 136 */         if (fs.soberTime > TFC_Time.getTotalTicks() + 5000L + levelMod) {
/* 137 */           if (rand.nextInt(4) == 0) {
/* 138 */             player.func_70690_d(new PotionEffect(18, time, 4));
/*     */           }
/* 140 */           if (fs.soberTime > TFC_Time.getTotalTicks() + 7000L + levelMod) {
/* 141 */             if (rand.nextInt(2) == 0) {
/* 142 */               player.func_70690_d(new PotionEffect(15, time, 4));
/*     */             }
/* 144 */             if (fs.soberTime > TFC_Time.getTotalTicks() + 8000L + levelMod && 
/* 145 */               rand.nextInt(10) == 0) {
/* 146 */               player.func_70690_d(new PotionEffect(20, time, 4));
/*     */             }
/*     */             
/* 149 */             if (fs.soberTime > TFC_Time.getTotalTicks() + 10000L + levelMod && !player.field_71075_bZ.field_75098_d) {
/* 150 */               fs.soberTime = 0L;
/*     */               
/* 152 */               player.func_70097_a((new DamageSource("alcohol")).func_76348_h().func_151518_m(), player.func_110138_aP());
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/* 158 */       TFC_Core.setPlayerFoodStats(player, fs);
/*     */     } 
/*     */ 
/*     */     
/* 162 */     if (!player.field_71075_bZ.field_75098_d && !player.field_71071_by.func_70441_a(new ItemStack(TFCItems.glassBottle))) {
/*     */ 
/*     */       
/* 165 */       if (is.field_77994_a == 0) {
/* 166 */         return new ItemStack(TFCItems.glassBottle);
/*     */       }
/*     */       
/* 169 */       player.func_71019_a(new ItemStack(TFCItems.glassBottle), false);
/*     */     } 
/*     */     
/* 172 */     return is;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemAlcohol.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */