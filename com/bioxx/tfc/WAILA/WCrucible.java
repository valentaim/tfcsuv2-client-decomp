/*     */ package com.bioxx.tfc.WAILA;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Metal.Alloy;
/*     */ import com.bioxx.tfc.Core.Metal.AlloyManager;
/*     */ import com.bioxx.tfc.Core.Metal.AlloyMetal;
/*     */ import com.bioxx.tfc.Core.Metal.MetalPair;
/*     */ import com.bioxx.tfc.Core.Metal.MetalRegistry;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.TileEntities.TECrucible;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.Metal;
/*     */ import com.bioxx.tfc.api.TFC_ItemHeat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import mcp.mobius.waila.api.IWailaConfigHandler;
/*     */ import mcp.mobius.waila.api.IWailaDataAccessor;
/*     */ import mcp.mobius.waila.api.IWailaDataProvider;
/*     */ import mcp.mobius.waila.api.IWailaRegistrar;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ public class WCrucible
/*     */   implements IWailaDataProvider
/*     */ {
/*  35 */   private Map<String, MetalPair> metals = new HashMap<>();
/*     */   
/*     */   private Alloy currentAlloy;
/*     */ 
/*     */   
/*     */   public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  41 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  47 */     return currenttip;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/*  54 */     this.metals.clear();
/*  55 */     this.currentAlloy = null;
/*     */     
/*  57 */     if (accessor.getTileEntity() instanceof TECrucible) {
/*     */       
/*  59 */       NBTTagCompound tag = accessor.getNBTData();
/*  60 */       NBTTagList taglist = tag.func_150295_c("Metals", 10);
/*     */ 
/*     */       
/*  63 */       for (int i = 0; i < taglist.func_74745_c(); i++) {
/*     */         
/*  65 */         NBTTagCompound nbt = taglist.func_150305_b(i);
/*  66 */         int id = nbt.func_74762_e("ID");
/*  67 */         float amount = nbt.func_74765_d("Amount");
/*  68 */         float amountF = amount + nbt.func_74760_g("AmountF");
/*  69 */         Metal metal = MetalRegistry.instance.getMetalFromItem(Item.func_150899_d(id));
/*  70 */         addMetal(metal, amountF);
/*     */       } 
/*     */ 
/*     */       
/*  74 */       if (this.currentAlloy != null) {
/*     */         
/*  76 */         String metalTypeUnits = EnumChatFormatting.UNDERLINE + TFC_Core.translate("gui.metal.Unknown");
/*  77 */         if (this.currentAlloy.outputType != null)
/*     */         {
/*  79 */           metalTypeUnits = EnumChatFormatting.UNDERLINE + TFC_Core.translate("gui.metal." + this.currentAlloy.outputType.name.replace(" ", ""));
/*     */         }
/*     */         
/*  82 */         int output = Math.round(this.currentAlloy.outputAmount);
/*  83 */         metalTypeUnits = metalTypeUnits + "· " + TFC_Core.translate("gui.units") + " : " + output;
/*     */         
/*  85 */         currenttip.add(metalTypeUnits);
/*     */         
/*  87 */         for (int c = 0; c < this.currentAlloy.alloyIngred.size(); c++) {
/*     */           
/*  89 */           double m = ((AlloyMetal)this.currentAlloy.alloyIngred.get(c)).metal;
/*  90 */           m = Math.round(m * 100.0D) / 100.0D;
/*  91 */           if (((AlloyMetal)this.currentAlloy.alloyIngred.get(c)).metalType != null)
/*     */           {
/*  93 */             currenttip.add("· " + TFC_Core.translate("gui.metal." + ((AlloyMetal)this.currentAlloy.alloyIngred.get(c)).metalType.name.replace(" ", "")) + " : " + m + "%");
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 100 */       int temperature = tag.func_74762_e("temp");
/* 101 */       String temp = TFC_ItemHeat.getHeatColor(temperature, 2.14748365E9F);
/* 102 */       if (temperature > 0)
/*     */       {
/* 104 */         currenttip.add(temp);
/*     */       }
/*     */     } 
/*     */     
/* 108 */     return currenttip;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
/* 114 */     return currenttip;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, int x, int y, int z) {
/* 120 */     if (te != null)
/* 121 */       te.func_145841_b(tag); 
/* 122 */     return tag;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void callbackRegister(IWailaRegistrar reg) {
/* 127 */     reg.registerBodyProvider(new WCrucible(), TECrucible.class);
/* 128 */     reg.registerNBTProvider(new WCrucible(), TECrucible.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addMetal(Metal m, float amt) {
/* 133 */     if (getTotalMetal() + amt <= 3000.0F && !"Unknown".equals(m.name)) {
/*     */       
/* 135 */       if (this.metals.containsKey(m.name)) {
/* 136 */         ((MetalPair)this.metals.get(m.name)).amount += amt;
/*     */       } else {
/* 138 */         this.metals.put(m.name, new MetalPair(m, amt));
/*     */       } 
/* 140 */       updateCurrentAlloy();
/* 141 */       return true;
/*     */     } 
/* 143 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getTotalMetal() {
/* 148 */     Iterator<MetalPair> iter = this.metals.values().iterator();
/* 149 */     float totalAmount = 0.0F;
/* 150 */     while (iter.hasNext()) {
/*     */       
/* 152 */       MetalPair m = iter.next();
/* 153 */       if (m != null)
/* 154 */         totalAmount += m.amount; 
/*     */     } 
/* 156 */     return totalAmount;
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateCurrentAlloy() {
/* 161 */     List<AlloyMetal> a = new ArrayList<>();
/* 162 */     Iterator<MetalPair> iter = this.metals.values().iterator();
/* 163 */     float totalAmount = getTotalMetal();
/* 164 */     while (iter.hasNext()) {
/*     */       
/* 166 */       MetalPair m = iter.next();
/* 167 */       if (m != null) {
/* 168 */         a.add(new AlloyMetal(m.type, m.amount / totalAmount * 100.0F));
/*     */       }
/*     */     } 
/* 171 */     Metal match = AlloyManager.INSTANCE.matchesAlloy(a, Alloy.EnumTier.TierV);
/* 172 */     if (match != null) {
/*     */       
/* 174 */       this.currentAlloy = new Alloy(match, totalAmount);
/* 175 */       this.currentAlloy.alloyIngred = a;
/*     */     }
/*     */     else {
/*     */       
/* 179 */       this.currentAlloy = new Alloy(Global.UNKNOWN, totalAmount);
/* 180 */       this.currentAlloy.alloyIngred = a;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WAILA\WCrucible.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */