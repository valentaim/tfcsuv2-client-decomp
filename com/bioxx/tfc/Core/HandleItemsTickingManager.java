/*     */ package com.bioxx.tfc.Core;
/*     */ 
/*     */ import com.bioxx.tfc.Items.ItemBlocks.ItemTerraBlock;
/*     */ import com.bioxx.tfc.Items.ItemTerra;
/*     */ import com.bioxx.tfc.api.TFC_ItemHeat;
/*     */ import com.google.common.collect.Queues;
/*     */ import java.util.concurrent.ConcurrentLinkedQueue;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.world.World;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HandleItemsTickingManager
/*     */   extends Thread
/*     */ {
/*  23 */   private static final Logger logger = LogManager.getLogger();
/*     */   
/*     */   private final MinecraftServer ms;
/*     */   StringBuilder message;
/*  27 */   private final ConcurrentLinkedQueue<ItemTickingSlot> slots_base = Queues.newConcurrentLinkedQueue();
/*     */   
/*     */   public HandleItemsTickingManager(MinecraftServer serv) {
/*  30 */     this.ms = serv;
/*  31 */     this.message = (new StringBuilder()).append("[HITM] ");
/*     */   }
/*     */   
/*     */   public void add_handleItemTicking(Object iinv, World world, int x, int y, int z) {
/*  35 */     add_handleItemTicking(iinv, world, x, y, z, 1.0F);
/*     */   }
/*     */   
/*     */   public void add_handleItemTicking(Object iinv, World world, int x, int y, int z, float environmentalDecayFactor) {
/*  39 */     ItemTickingSlot slot = new ItemTickingSlot(6, iinv, world, x, y, z, environmentalDecayFactor, -1.0F);
/*  40 */     this.slots_base.add(slot);
/*     */   }
/*     */   
/*     */   public void add_handleItemTicking(IInventory iinv, World world, int x, int y, int z, float environmentalDecayFactor, float baseDecayMod) {
/*  44 */     ItemTickingSlot slot = new ItemTickingSlot(7, iinv, world, x, y, z, environmentalDecayFactor, baseDecayMod);
/*  45 */     this.slots_base.add(slot);
/*     */   }
/*     */   
/*     */   private void run_slot(ItemTickingSlot slot) {
/*  49 */     if (slot.ItemList instanceof IInventory) { handleItemTicking((IInventory)slot.ItemList, slot.world, slot.x, slot.y, slot.z, slot.environmentalDecayFactor); }
/*  50 */     else { handleItemTicking((ItemStack[])slot.ItemList, slot.world, slot.x, slot.y, slot.z, slot.environmentalDecayFactor); }
/*     */   
/*     */   }
/*     */   private boolean process() {
/*  54 */     if (!this.slots_base.isEmpty())
/*  55 */     { ItemTickingSlot slot = this.slots_base.poll();
/*  56 */       if (slot != null)
/*  57 */         if (slot.args == 6) { run_slot(slot); }
/*  58 */         else { handleItemTicking((IInventory)slot.ItemList, slot.world, slot.x, slot.y, slot.z, slot.environmentalDecayFactor, slot.baseDecayMod); }
/*     */           }
/*     */     else { 
/*  61 */       try { Thread.sleep(5L); } catch (InterruptedException e) { return true; }  }
/*  62 */      return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*  68 */     log("Items Spawner Thread Started");
/*  69 */     boolean interrupted = false;
/*     */     
/*  71 */     while (!interrupted && !Thread.currentThread().isInterrupted() && this.ms.func_71278_l() && !this.ms.func_71241_aa()) {
/*     */       
/*     */       try {
/*  74 */         interrupted = process();
/*     */       }
/*  76 */       catch (Exception e) {
/*  77 */         this.slots_base.clear();
/*  78 */         log("ERROR ERROR ERROR ERROR ERROR ERROR");
/*  79 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */     
/*  83 */     if (isInterrupted() || interrupted) {
/*  84 */       log("Interrupted by manager.");
/*     */       return;
/*     */     } 
/*  87 */     if (!this.ms.func_71278_l() || this.ms.func_71241_aa()) {
/*  88 */       log("Game stopped ! Shutting down thread.");
/*     */       
/*     */       return;
/*     */     } 
/*  92 */     log("UNKNOWN. Thread Stopped.");
/*     */   }
/*     */ 
/*     */   
/*     */   private void log(String str) {
/*  97 */     logger.warn(this.message + str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleItemTicking(IInventory iinv, World world, int x, int y, int z, float environmentalDecayFactor) {
/* 107 */     for (int i = 0; !world.field_72995_K && i < iinv.func_70302_i_(); i++) {
/*     */       
/* 109 */       ItemStack is = iinv.func_70301_a(i);
/* 110 */       if (is != null && is.field_77994_a <= 0) { iinv.func_70299_a(i, null); }
/*     */       
/* 112 */       else if (is != null)
/*     */       
/* 114 */       { if (is.field_77994_a == 0) {
/*     */           
/* 116 */           iinv.func_70299_a(i, null);
/*     */         
/*     */         }
/* 119 */         else if ((!(is.func_77973_b() instanceof ItemTerra) || !((ItemTerra)is.func_77973_b()).onUpdate(is, world, x, y, z)) && (
/* 120 */           !(is.func_77973_b() instanceof ItemTerraBlock) || !((ItemTerraBlock)is.func_77973_b()).onUpdate(is, world, x, y, z))) {
/*     */ 
/*     */           
/* 123 */           if (is != null && (is.func_77973_b() instanceof com.bioxx.tfc.Items.ItemBlocks.ItemBarrels || is.func_77973_b() instanceof com.bioxx.tfc.Items.ItemBlocks.ItemLargeVessel)) {
/* 124 */             if (is.func_77942_o()) {
/* 125 */               ItemStack[] inv = TFC_Core.getIsFromNbt(is);
/* 126 */               if (inv != null) {
/* 127 */                 TFC_Core.handleItemTicking(inv, world, x, y, z, environmentalDecayFactor);
/* 128 */                 NBTTagCompound nbt = is.func_77978_p();
/* 129 */                 nbt.func_82580_o("Items");
/* 130 */                 TFC_Core.writeToNBT(nbt, inv);
/*     */               } 
/*     */             } 
/*     */           } else {
/* 134 */             is = TFC_Core.tickDecay(is, world, x, y, z, environmentalDecayFactor, 1.0F);
/* 135 */             if (is != null) TFC_ItemHeat.handleItemHeat(is); 
/* 136 */             iinv.func_70299_a(i, is);
/*     */           } 
/*     */         }  }
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleItemTicking(ItemStack[] iinv, World world, int x, int y, int z, float environmentalDecayFactor) {
/* 150 */     for (int i = 0; !world.field_72995_K && i < iinv.length; i++) {
/*     */       
/* 152 */       ItemStack is = iinv[i];
/* 153 */       if (is != null && is.field_77994_a <= 0) { iinv[i] = null; }
/*     */       
/* 155 */       else if (is != null)
/*     */       
/* 157 */       { if ((!(is.func_77973_b() instanceof ItemTerra) || !((ItemTerra)is.func_77973_b()).onUpdate(is, world, x, y, z)) && (
/* 158 */           !(is.func_77973_b() instanceof ItemTerraBlock) || !((ItemTerraBlock)is.func_77973_b()).onUpdate(is, world, x, y, z))) {
/* 159 */           is = TFC_Core.tickDecay(is, world, x, y, z, environmentalDecayFactor, 1.0F);
/* 160 */           if (is != null) TFC_ItemHeat.handleItemHeat(is); 
/* 161 */           iinv[i] = is;
/*     */         }  }
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleItemTicking(IInventory iinv, World world, int x, int y, int z, float environmentalDecayFactor, float baseDecayMod) {
/* 171 */     for (int i = 0; !world.field_72995_K && i < iinv.func_70302_i_(); i++) {
/*     */       
/* 173 */       ItemStack is = iinv.func_70301_a(i);
/* 174 */       if (is != null && is.field_77994_a <= 0) { iinv.func_70299_a(i, null); }
/*     */       
/* 176 */       else if (is != null)
/*     */       
/* 178 */       { if ((!(is.func_77973_b() instanceof ItemTerra) || !((ItemTerra)is.func_77973_b()).onUpdate(is, world, x, y, z)) && (
/* 179 */           !(is.func_77973_b() instanceof ItemTerraBlock) || !((ItemTerraBlock)is.func_77973_b()).onUpdate(is, world, x, y, z))) {
/* 180 */           is = TFC_Core.tickDecay(is, world, x, y, z, environmentalDecayFactor, baseDecayMod);
/* 181 */           if (is != null) TFC_ItemHeat.handleItemHeat(is); 
/* 182 */           iinv.func_70299_a(i, is);
/*     */         }  }
/*     */     
/*     */     } 
/*     */   }
/*     */   class ItemTickingSlot { int args;
/*     */     Object ItemList;
/*     */     World world;
/*     */     int x;
/*     */     int y;
/*     */     int z;
/*     */     float environmentalDecayFactor;
/*     */     float baseDecayMod;
/*     */     
/*     */     public ItemTickingSlot(int args, Object ItemList, World w, int x, int y, int z, float environmentalDecayFactor, float baseDecayMod) {
/* 197 */       this.args = args;
/* 198 */       this.ItemList = ItemList;
/* 199 */       this.world = w;
/* 200 */       this.x = x;
/* 201 */       this.y = y;
/* 202 */       this.z = z;
/* 203 */       this.environmentalDecayFactor = environmentalDecayFactor;
/* 204 */       this.baseDecayMod = baseDecayMod;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\HandleItemsTickingManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */