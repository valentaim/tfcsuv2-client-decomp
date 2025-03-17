/*     */ package com.bioxx.tfc.Items.Tools;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Player.InventoryPlayerTFC;
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Entities.EntityProjectileTFC;
/*     */ import com.bioxx.tfc.Items.ItemQuiver;
/*     */ import com.bioxx.tfc.Items.ItemTerra;
/*     */ import com.bioxx.tfc.api.Enums.EnumAmmo;
/*     */ import com.bioxx.tfc.api.Enums.EnumItemReach;
/*     */ import com.bioxx.tfc.api.Enums.EnumSize;
/*     */ import com.bioxx.tfc.api.Enums.EnumWeight;
/*     */ import com.bioxx.tfc.api.Interfaces.ISize;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import cpw.mods.fml.common.eventhandler.Event;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemBow;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.player.ArrowLooseEvent;
/*     */ import net.minecraftforge.event.entity.player.ArrowNockEvent;
/*     */ 
/*     */ public class ItemCustomBow
/*     */   extends ItemBow implements ISize {
/*  34 */   private String[] bowPullIconNameArray = new String[] { "pulling_0", "pulling_1", "pulling_2", "pulling_3" };
/*     */   
/*     */   private IIcon[] iconArray;
/*     */ 
/*     */   
/*     */   public ItemCustomBow() {
/*  40 */     this.field_77777_bU = 1;
/*  41 */     func_77656_e(384);
/*  42 */     func_77637_a(TFCTabs.TFC_WEAPONS);
/*  43 */     setNoRepair();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean consumeArrowInQuiver(EntityPlayer player, boolean shouldConsume) {
/*  48 */     ItemStack quiver = ((InventoryPlayerTFC)player.field_71071_by).extraEquipInventory[0];
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
/*  59 */     if (quiver != null && quiver.func_77973_b() instanceof ItemQuiver && (
/*  60 */       (ItemQuiver)quiver.func_77973_b()).consumeAmmo(quiver, EnumAmmo.ARROW, shouldConsume) != null) {
/*  61 */       return true;
/*     */     }
/*  63 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack is, World world, EntityPlayer player) {
/*  69 */     ArrowNockEvent event = new ArrowNockEvent(player, is);
/*  70 */     MinecraftForge.EVENT_BUS.post((Event)event);
/*  71 */     if (event.isCanceled()) {
/*  72 */       return event.result;
/*     */     }
/*  74 */     if (player.field_71075_bZ.field_75098_d || player.field_71071_by.func_146028_b(TFCItems.arrow) || consumeArrowInQuiver(player, false)) {
/*  75 */       player.func_71008_a(is, func_77626_a(is));
/*     */     }
/*  77 */     return is;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77615_a(ItemStack is, World world, EntityPlayer player, int inUseCount) {
/*  83 */     int j = func_77626_a(is) - inUseCount;
/*     */     
/*  85 */     ArrowLooseEvent event = new ArrowLooseEvent(player, is, j);
/*  86 */     MinecraftForge.EVENT_BUS.post((Event)event);
/*  87 */     if (event.isCanceled())
/*     */       return; 
/*  89 */     j = event.charge;
/*     */     
/*  91 */     boolean flag = (player.field_71075_bZ.field_75098_d || EnchantmentHelper.func_77506_a(Enchantment.field_77342_w.field_77352_x, is) > 0);
/*     */ 
/*     */     
/*  94 */     boolean hasAmmo = (flag || player.field_71071_by.func_146028_b(TFCItems.arrow));
/*  95 */     boolean hasAmmoInQuiver = false;
/*     */     
/*  97 */     if (!hasAmmo) {
/*  98 */       hasAmmoInQuiver = consumeArrowInQuiver(player, false);
/*     */     }
/* 100 */     if (hasAmmo || hasAmmoInQuiver) {
/*     */       
/* 102 */       float forceMult = j / getUseSpeed(player);
/*     */ 
/*     */       
/* 105 */       if (forceMult < 0.25D) {
/*     */         return;
/*     */       }
/* 108 */       if (forceMult > 1.25F) {
/* 109 */         forceMult = 1.25F;
/*     */       }
/* 111 */       EntityProjectileTFC entityarrow = new EntityProjectileTFC(world, (EntityLivingBase)player, forceMult * 2.0F);
/* 112 */       entityarrow.func_70239_b(forceMult * 150.0D);
/* 113 */       if (forceMult == 1.25F) {
/* 114 */         entityarrow.func_70243_d(true);
/*     */       }
/* 116 */       int k = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, is);
/*     */       
/* 118 */       if (k > 0) {
/* 119 */         entityarrow.func_70239_b(entityarrow.func_70242_d() + k * 0.5D + 0.5D);
/*     */       }
/* 121 */       int l = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, is);
/*     */       
/* 123 */       if (l > 0) {
/* 124 */         entityarrow.func_70240_a(l);
/*     */       }
/* 126 */       if (EnchantmentHelper.func_77506_a(Enchantment.field_77343_v.field_77352_x, is) > 0) {
/* 127 */         entityarrow.func_70015_d(100);
/*     */       }
/* 129 */       is.func_77972_a(1, (EntityLivingBase)player);
/* 130 */       world.func_72956_a((Entity)player, "random.bow", 1.0F, 1.0F / (field_77697_d.nextFloat() * 0.4F + 1.2F) + forceMult * 0.5F);
/*     */       
/* 132 */       if (flag) {
/* 133 */         entityarrow.field_70251_a = 2;
/* 134 */       } else if (hasAmmo) {
/* 135 */         player.field_71071_by.func_146026_a(TFCItems.arrow);
/* 136 */       } else if (hasAmmoInQuiver) {
/* 137 */         consumeArrowInQuiver(player, true);
/*     */       } 
/* 139 */       if (!world.field_72995_K) {
/* 140 */         world.func_72838_d((Entity)entityarrow);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static float getUseSpeed(EntityPlayer player) {
/* 146 */     float speed = 60.0F;
/* 147 */     ItemStack[] armor = player.field_71071_by.field_70460_b;
/* 148 */     if (armor[0] != null && armor[0].func_77973_b() instanceof ISize)
/* 149 */       speed += 20.0F / (((ISize)armor[0].func_77973_b()).getWeight(armor[0])).multiplier; 
/* 150 */     if (armor[1] != null && armor[1].func_77973_b() instanceof ISize)
/* 151 */       speed += 20.0F / (((ISize)armor[1].func_77973_b()).getWeight(armor[1])).multiplier; 
/* 152 */     if (armor[2] != null && armor[2].func_77973_b() instanceof ISize)
/* 153 */       speed += 20.0F / (((ISize)armor[2].func_77973_b()).getWeight(armor[2])).multiplier; 
/* 154 */     if (armor[3] != null && armor[3].func_77973_b() instanceof ISize) {
/* 155 */       speed += 20.0F / (((ISize)armor[3].func_77973_b()).getWeight(armor[3])).multiplier;
/*     */     }
/* 157 */     return speed;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77624_a(ItemStack is, EntityPlayer player, List arraylist, boolean flag) {
/* 163 */     ItemTerra.addSizeInformation(is, arraylist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumSize getSize(ItemStack is) {
/* 169 */     return EnumSize.LARGE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumWeight getWeight(ItemStack is) {
/* 175 */     return EnumWeight.LIGHT;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canStack() {
/* 181 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister par1IconRegister) {
/* 188 */     this.field_77791_bV = par1IconRegister.func_94245_a("terrafirmacraft:" + func_111208_A() + "_standby");
/* 189 */     this.iconArray = new IIcon[this.bowPullIconNameArray.length];
/*     */     
/* 191 */     for (int i = 0; i < this.iconArray.length; i++) {
/* 192 */       this.iconArray[i] = par1IconRegister.func_94245_a("terrafirmacraft:" + func_111208_A() + "_" + this.bowPullIconNameArray[i]);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_94599_c(int par1) {
/* 200 */     return this.iconArray[par1];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
/* 206 */     if (usingItem != null && usingItem.func_77973_b() == this) {
/*     */       
/* 208 */       int j = usingItem.func_77988_m() - useRemaining;
/* 209 */       float force = j / getUseSpeed(player);
/*     */       
/* 211 */       if (force >= 1.25D)
/*     */       {
/* 213 */         return func_94599_c(3);
/*     */       }
/* 215 */       if (force > 0.75D)
/*     */       {
/* 217 */         return func_94599_c(2);
/*     */       }
/* 219 */       if (force > 0.25D)
/*     */       {
/* 221 */         return func_94599_c(1);
/*     */       }
/* 223 */       if (force > 0.0F)
/*     */       {
/* 225 */         return func_94599_c(0);
/*     */       }
/*     */     } 
/* 228 */     return getIcon(stack, renderPass);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumItemReach getReach(ItemStack is) {
/* 233 */     return EnumItemReach.SHORT;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemCustomBow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */