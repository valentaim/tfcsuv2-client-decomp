/*     */ package com.bioxx.tfc.Items;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Player.PlayerInfo;
/*     */ import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.api.Enums.EnumSize;
/*     */ import com.bioxx.tfc.api.Enums.EnumWeight;
/*     */ import com.bioxx.tfc.api.TFC_ItemHeat;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemMeltedMetal
/*     */   extends ItemTerra
/*     */ {
/*     */   public ItemMeltedMetal() {
/*  30 */     func_77656_e(101);
/*  31 */     func_77637_a(TFCTabs.TFC_MATERIALS);
/*  32 */     setFolder("ingots/");
/*  33 */     setWeight(EnumWeight.MEDIUM);
/*  34 */     setSize(EnumSize.SMALL);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_94581_a(IIconRegister registerer) {
/*  40 */     this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + func_77658_a().replace("item.", "").replace("Weak ", "").replace("HC ", ""));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getItemStackLimit(ItemStack is) {
/*  47 */     if (isDamaged(is) || (is.func_77942_o() && TFC_ItemHeat.hasTemp(is)))
/*     */     {
/*  49 */       return 1;
/*     */     }
/*     */     
/*  52 */     return super.getItemStackLimit(is);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItemInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
/*  58 */     if (is.func_77960_j() > 1)
/*     */     {
/*  60 */       arraylist.add(TFC_Core.translate("gui.units") + ": " + (100 - is.func_77960_j()) + " / 100");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77663_a(ItemStack is, World world, Entity entity, int i, boolean isSelected) {
/*  67 */     super.func_77663_a(is, world, entity, i, isSelected);
/*  68 */     if (is.func_77942_o()) {
/*     */ 
/*     */       
/*  71 */       if (TFC_ItemHeat.hasTemp(is) && TFC_ItemHeat.getTemp(is) >= TFC_ItemHeat.isCookable(is)) {
/*     */         
/*  73 */         if (is.func_77960_j() == 0) {
/*  74 */           is.func_77964_b(1);
/*     */         }
/*     */       }
/*  77 */       else if (is.func_77960_j() == 1) {
/*  78 */         is.func_77964_b(0);
/*     */       }
/*     */     
/*     */     }
/*  82 */     else if (is.func_77960_j() == 1) {
/*  83 */       is.func_77964_b(0);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDamaged(ItemStack stack) {
/*  90 */     return (stack.func_77960_j() > 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addExtraInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
/*  95 */     if (TFC_Core.showShiftInformation()) {
/*     */       
/*  97 */       arraylist.add(TFC_Core.translate("gui.Help"));
/*  98 */       arraylist.add(TFC_Core.translate("gui.MeltedMetal.Inst0"));
/*     */     }
/*     */     else {
/*     */       
/* 102 */       arraylist.add(TFC_Core.translate("gui.ShowHelp"));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer entityplayer) {
/* 109 */     if (itemstack.field_77994_a <= 0) {
/* 110 */       itemstack.field_77994_a = 1;
/*     */     }
/*     */     
/* 113 */     PlayerInfo pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(entityplayer);
/* 114 */     pi.specialCraftingType = itemstack.func_77946_l();
/*     */     
/* 116 */     entityplayer.field_71071_by.func_70299_a(entityplayer.field_71071_by.field_70461_c, null);
/* 117 */     entityplayer.openGui(TerraFirmaCraft.instance, 38, world, (int)entityplayer.field_70165_t, (int)entityplayer.field_70163_u, (int)entityplayer.field_70161_v);
/*     */     
/* 119 */     return itemstack;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onEntityItemUpdate(EntityItem entityItem) {
/* 124 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasCustomEntity(ItemStack stack) {
/* 130 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity createEntity(World world, Entity entity, ItemStack itemstack) {
/* 135 */     EntityTossMeltedIngotItem customIngotEntity = null;
/* 136 */     List<EntityPlayer> players = world.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a(entity.field_70165_t - 1.0D, entity.field_70163_u - 1.0D, entity.field_70161_v - 1.0D, entity.field_70165_t + 1.0D, entity.field_70163_u + 1.0D, entity.field_70161_v + 1.0D));
/*     */     
/* 138 */     if (players.size() > 0 && players.get(0) != null) {
/* 139 */       EntityPlayer p = players.get(0);
/* 140 */       customIngotEntity = new EntityTossMeltedIngotItem(world, p.field_70165_t, p.field_70163_u - 0.30000001192092896D + p.func_70047_e(), p.field_70161_v, itemstack);
/* 141 */       customIngotEntity.field_145804_b = 40;
/*     */       
/* 143 */       float var5 = 0.1F;
/*     */       
/* 145 */       var5 = 0.3F;
/* 146 */       customIngotEntity.field_70159_w = (-MathHelper.func_76126_a(p.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(p.field_70125_A / 180.0F * 3.1415927F) * var5);
/* 147 */       customIngotEntity.field_70179_y = (MathHelper.func_76134_b(p.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(p.field_70125_A / 180.0F * 3.1415927F) * var5);
/* 148 */       customIngotEntity.field_70181_x = (-MathHelper.func_76126_a(p.field_70125_A / 180.0F * 3.1415927F) * var5 + 0.1F);
/* 149 */       var5 = 0.02F;
/* 150 */       float var6 = world.field_73012_v.nextFloat() * 3.1415927F * 2.0F;
/* 151 */       var5 *= world.field_73012_v.nextFloat();
/* 152 */       customIngotEntity.field_70159_w += Math.cos(var6) * var5;
/* 153 */       customIngotEntity.field_70181_x += ((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.1F);
/* 154 */       customIngotEntity.field_70179_y += Math.sin(var6) * var5;
/*     */     } else {
/* 156 */       customIngotEntity = new EntityTossMeltedIngotItem(world, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, itemstack);
/* 157 */       customIngotEntity.field_145804_b = 40;
/*     */     } 
/*     */     
/* 160 */     return (Entity)customIngotEntity;
/*     */   }
/*     */   
/*     */   private class EntityTossMeltedIngotItem
/*     */     extends EntityItem {
/*     */     public EntityTossMeltedIngotItem(World p_i1711_1_) {
/* 166 */       super(p_i1711_1_);
/*     */     }
/*     */     
/*     */     public EntityTossMeltedIngotItem(World world, double posX, double posY, double posZ, ItemStack itemStack) {
/* 170 */       super(world, posX, posY, posZ, itemStack);
/*     */     }
/*     */     
/*     */     public void func_70071_h_() {
/* 174 */       if (this.field_70170_p.func_82737_E() % 10L == 0L) {
/* 175 */         ItemStack ingot = func_92059_d();
/* 176 */         if (TFC_ItemHeat.hasTemp(ingot)) {
/* 177 */           Block block = this.field_70170_p.func_147439_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v));
/* 178 */           if (TFC_Core.isWater(block)) {
/* 179 */             TFC_ItemHeat.removeTempTag(ingot);
/* 180 */             func_85030_a("random.fizz", 0.4F, 2.0F + this.field_70146_Z.nextFloat() * 0.4F);
/* 181 */             func_92058_a(ingot);
/*     */           } 
/*     */         } 
/*     */       } 
/* 185 */       super.func_70071_h_();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemMeltedMetal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */