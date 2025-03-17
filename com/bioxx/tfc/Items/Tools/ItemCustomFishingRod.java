/*     */ package com.bioxx.tfc.Items.Tools;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.Entities.EntityFishHookTFC;
/*     */ import com.bioxx.tfc.Items.ItemTerra;
/*     */ import com.bioxx.tfc.api.Enums.EnumItemReach;
/*     */ import com.bioxx.tfc.api.Enums.EnumSize;
/*     */ import com.bioxx.tfc.api.Enums.EnumWeight;
/*     */ import com.bioxx.tfc.api.Interfaces.ISize;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.ItemFishingRod;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemCustomFishingRod
/*     */   extends ItemFishingRod
/*     */   implements ISize
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon[] uncastIconArray;
/*     */   private IIcon[] castIconArray;
/*     */   
/*     */   public ItemCustomFishingRod() {
/*  36 */     func_77656_e(64);
/*  37 */     func_77625_d(1);
/*  38 */     func_77637_a(TFCTabs.TFC_TOOLS);
/*  39 */     setNoRepair();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77662_d() {
/*  50 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77629_n_() {
/*  62 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack is) {
/*  68 */     if (is.field_77990_d != null && is.field_77990_d.func_74764_b("swing") && is.field_77990_d.func_74767_n("swing")) {
/*  69 */       is.field_77990_d.func_74757_a("swing", false);
/*  70 */       return false;
/*     */     } 
/*  72 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack is, World world, EntityPlayer player) {
/*  81 */     if (is.field_77990_d != null && is.field_77990_d.func_74764_b("tickReeledIn")) {
/*     */       
/*  83 */       long tickReeledIn = is.field_77990_d.func_74763_f("tickReeledIn");
/*  84 */       if (TFC_Time.getTotalTicks() <= tickReeledIn + 20L) {
/*  85 */         return is;
/*     */       }
/*     */     } 
/*  88 */     if (player.field_71104_cf != null) {
/*     */ 
/*     */ 
/*     */       
/*  92 */       if (player.field_71104_cf instanceof EntityFishHookTFC)
/*     */       {
/*  94 */         ((EntityFishHookTFC)player.field_71104_cf).reelInBobber((Entity)player, is);
/*     */       }
/*     */       else
/*     */       {
/*  98 */         player.func_71008_a(is, 1);
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 104 */       if (is.field_77990_d == null) {
/* 105 */         is.func_77982_d(new NBTTagCompound());
/*     */       }
/* 107 */       player.func_71008_a(is, func_77626_a(is));
/*     */     } 
/*     */     
/* 110 */     return is;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_77648_a(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
/* 116 */     if (player.field_71104_cf instanceof EntityFishHookTFC)
/*     */     {
/* 118 */       ((EntityFishHookTFC)player.field_71104_cf).reelInBobber((Entity)player, is);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 125 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77615_a(ItemStack is, World world, EntityPlayer player, int inUseCount) {
/* 131 */     if (player.field_71104_cf == null && is.field_77990_d != null) {
/* 132 */       world.func_72956_a((Entity)player, "random.bow", 0.5F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
/* 133 */       if (!world.field_72995_K) {
/* 134 */         world.func_72838_d((Entity)new EntityFishHookTFC(world, player, is.func_77988_m() - inUseCount));
/*     */         
/* 136 */         is.field_77990_d.func_74757_a("fishing", true);
/*     */       } 
/*     */       
/* 139 */       is.field_77990_d.func_74757_a("swing", true);
/* 140 */       player.func_71038_i();
/* 141 */       is.field_77990_d.func_74757_a("fishing", true);
/* 142 */       is.field_77990_d.func_74768_a("usedUses", 0);
/*     */     }
/* 144 */     else if (is.field_77990_d != null) {
/* 145 */       is.field_77990_d.func_74757_a("fishing", true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_77626_a(ItemStack is) {
/* 155 */     return 72000;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister par1IconRegister) {
/* 163 */     this.uncastIconArray = new IIcon[3];
/* 164 */     this.castIconArray = new IIcon[8]; int i;
/* 165 */     for (i = 0; i < this.castIconArray.length; i++)
/* 166 */       this.castIconArray[i] = par1IconRegister.func_94245_a("terrafirmacraft:" + func_111208_A() + "_cast_" + i); 
/* 167 */     for (i = 0; i < this.uncastIconArray.length; i++)
/* 168 */       this.uncastIconArray[i] = par1IconRegister.func_94245_a("terrafirmacraft:" + func_111208_A() + "_uncast_" + i); 
/* 169 */     this.field_77791_bV = this.uncastIconArray[0];
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon getItemIconForUseDuration(int par1, boolean cast) {
/* 175 */     par1 = Math.min(Math.max(par1, 0), 7);
/* 176 */     if (cast)
/* 177 */       return this.castIconArray[par1]; 
/* 178 */     return this.uncastIconArray[par1];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon getIcon(ItemStack is, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
/* 184 */     boolean cast = (player.field_71104_cf != null);
/*     */     
/* 186 */     if (!is.func_77942_o()) {
/* 187 */       is.func_77982_d(new NBTTagCompound());
/*     */     }
/* 189 */     is.field_77990_d.func_74757_a("fishing", cast);
/* 190 */     if (usingItem == null) {
/* 191 */       useRemaining = func_77626_a(is);
/*     */     }
/*     */     
/* 194 */     if (!cast) {
/* 195 */       int j = Math.max(Math.min(func_77626_a(is) - useRemaining + 10, 60) / 20 - 1, 0);
/* 196 */       if (!is.func_77942_o()) {
/* 197 */         is.func_77982_d(new NBTTagCompound());
/*     */       }
/* 199 */       is.field_77990_d.func_74768_a("usedUses", func_77626_a(is) - useRemaining);
/* 200 */       return getItemIconForUseDuration(Math.min(j, this.uncastIconArray.length - 1), cast);
/*     */     } 
/*     */     
/* 203 */     int tension = 0;
/* 204 */     if (is.func_77942_o() && is.field_77990_d.func_74764_b("tension")) {
/* 205 */       tension = is.field_77990_d.func_74762_e("tension");
/*     */     }
/* 207 */     int originalTex = tension / 100;
/* 208 */     int texShift = (tension % 100 + 1) % 31;
/* 209 */     return getItemIconForUseDuration(Math.min(originalTex + ((texShift == 10) ? 1 : 0), this.castIconArray.length - 1), cast);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon getIcon(ItemStack is, int renderPass) {
/* 216 */     if (is.func_77942_o() && is.field_77990_d.func_74764_b("fishing") && is.field_77990_d.func_74767_n("fishing")) {
/* 217 */       return this.castIconArray[0];
/*     */     }
/* 219 */     return this.uncastIconArray[0];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77624_a(ItemStack is, EntityPlayer player, List arraylist, boolean flag) {
/* 225 */     ItemTerra.addSizeInformation(is, arraylist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumSize getSize(ItemStack is) {
/* 231 */     return EnumSize.TINY;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumWeight getWeight(ItemStack is) {
/* 237 */     return EnumWeight.LIGHT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumAction func_77661_b(ItemStack is) {
/* 246 */     if (is.field_77990_d != null && is.field_77990_d.func_74764_b("fishing") && is.field_77990_d.func_74767_n("fishing"))
/*     */     {
/* 248 */       return EnumAction.bow;
/*     */     }
/* 250 */     return EnumAction.none;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumItemReach getReach(ItemStack is) {
/* 256 */     return EnumItemReach.FAR;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canStack() {
/* 262 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemCustomFishingRod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */