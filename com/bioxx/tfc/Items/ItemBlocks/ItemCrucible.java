/*     */ package com.bioxx.tfc.Items.ItemBlocks;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Metal.Alloy;
/*     */ import com.bioxx.tfc.Core.Metal.AlloyManager;
/*     */ import com.bioxx.tfc.Core.Metal.AlloyMetal;
/*     */ import com.bioxx.tfc.Core.Metal.MetalPair;
/*     */ import com.bioxx.tfc.Core.Metal.MetalRegistry;
/*     */ import com.bioxx.tfc.Items.ItemTerra;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.Enums.EnumSize;
/*     */ import com.bioxx.tfc.api.Enums.EnumWeight;
/*     */ import com.bioxx.tfc.api.Interfaces.ISize;
/*     */ import com.bioxx.tfc.api.Metal;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ 
/*     */ public class ItemCrucible
/*     */   extends ItemTerraBlock
/*     */   implements ISize
/*     */ {
/*  31 */   public Map<String, MetalPair> metals = new HashMap<>();
/*     */   private Alloy currentAlloy;
/*     */   
/*     */   public ItemCrucible(Block par1) {
/*  35 */     super(par1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
/*  41 */     ItemTerra.addSizeInformation(is, arraylist);
/*     */     
/*  43 */     readFromItemNBT(is.func_77978_p(), arraylist);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readFromItemNBT(NBTTagCompound nbt, List<String> arraylist) {
/*  48 */     this.currentAlloy = null;
/*  49 */     this.metals = new HashMap<>();
/*  50 */     if (nbt != null && nbt.func_74764_b("Metals")) {
/*     */       
/*  52 */       NBTTagList nbttaglist = nbt.func_150295_c("Metals", 9);
/*     */       
/*  54 */       for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */         
/*  56 */         NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/*  57 */         int id = nbttagcompound1.func_74762_e("ID");
/*  58 */         float amount = nbttagcompound1.func_74760_g("AmountF");
/*     */         
/*  60 */         Metal m = MetalRegistry.instance.getMetalFromItem(Item.func_150899_d(id));
/*  61 */         addMetal(m, amount);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  66 */     if (this.currentAlloy != null)
/*     */     {
/*  68 */       for (int c = 0; c < this.currentAlloy.alloyIngred.size(); c++) {
/*     */         
/*  70 */         double m = ((AlloyMetal)this.currentAlloy.alloyIngred.get(c)).metal;
/*  71 */         m = Math.round(m * 100.0D) / 100.0D;
/*  72 */         if (((AlloyMetal)this.currentAlloy.alloyIngred.get(c)).metalType != null)
/*     */         {
/*  74 */           arraylist.add(EnumChatFormatting.DARK_GRAY + ((AlloyMetal)this.currentAlloy.alloyIngred.get(c)).metalType.name + " " + EnumChatFormatting.DARK_GREEN + m + "%");
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addMetal(Metal m, float amt) {
/*  82 */     if (getTotalMetal() + amt <= 3000.0F && !"Unknown".equals(m.name)) {
/*     */       
/*  84 */       if (this.metals.containsKey(m.name)) {
/*     */         
/*  86 */         ((MetalPair)this.metals.get(m.name)).amount += amt;
/*     */       }
/*     */       else {
/*     */         
/*  90 */         this.metals.put(m.name, new MetalPair(m, amt));
/*     */       } 
/*     */       
/*  93 */       updateCurrentAlloy();
/*     */       
/*  95 */       return true;
/*     */     } 
/*  97 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getTotalMetal() {
/* 102 */     Iterator<MetalPair> iter = this.metals.values().iterator();
/* 103 */     float totalAmount = 0.0F;
/* 104 */     while (iter.hasNext()) {
/*     */       
/* 106 */       MetalPair m = iter.next();
/* 107 */       if (m != null)
/*     */       {
/* 109 */         totalAmount += m.amount;
/*     */       }
/*     */     } 
/* 112 */     return totalAmount;
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateCurrentAlloy() {
/* 117 */     List<AlloyMetal> a = new ArrayList<>();
/*     */     
/* 119 */     Iterator<MetalPair> iter = this.metals.values().iterator();
/* 120 */     float totalAmount = getTotalMetal();
/*     */     
/* 122 */     iter = this.metals.values().iterator();
/* 123 */     while (iter.hasNext()) {
/*     */       
/* 125 */       MetalPair m = iter.next();
/* 126 */       if (m != null)
/*     */       {
/* 128 */         a.add(new AlloyMetal(m.type, m.amount / totalAmount * 100.0F));
/*     */       }
/*     */     } 
/*     */     
/* 132 */     Metal match = AlloyManager.INSTANCE.matchesAlloy(a, Alloy.EnumTier.TierV);
/* 133 */     if (match != null) {
/*     */       
/* 135 */       this.currentAlloy = new Alloy(match, totalAmount);
/* 136 */       this.currentAlloy.alloyIngred = a;
/*     */     }
/*     */     else {
/*     */       
/* 140 */       this.currentAlloy = new Alloy(Global.UNKNOWN, totalAmount);
/* 141 */       this.currentAlloy.alloyIngred = a;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumSize getSize(ItemStack is) {
/* 149 */     return EnumSize.HUGE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canStack() {
/* 155 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumWeight getWeight(ItemStack is) {
/* 161 */     return EnumWeight.HEAVY;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemCrucible.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */