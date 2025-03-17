/*     */ package com.bioxx.tfc.TileEntities;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.api.TFCFluids;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraftforge.fluids.FluidStack;
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
/*     */ public class TEOilLamp
/*     */   extends TELightEmitter
/*     */ {
/*     */   private FluidStack fuel;
/*     */   
/*     */   public FluidStack getFuel() {
/*  26 */     if (this.fuel == null)
/*  27 */       return null; 
/*  28 */     FluidStack f = this.fuel.copy();
/*  29 */     f.amount /= TFCOptions.oilLampFuelMult;
/*  30 */     return f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateLampFuel(Boolean burn) {
/*  39 */     if ((int)TFC_Time.getTotalHours() - TFCOptions.oilLampFuelMult >= this.hourPlaced) {
/*     */       
/*  41 */       int diff = burn.booleanValue() ? ((int)TFC_Time.getTotalHours() - this.hourPlaced) : 0;
/*  42 */       this.hourPlaced = (int)TFC_Time.getTotalHours();
/*     */       
/*  44 */       if (this.fuel != null && getFuel().getFluid() != TFCFluids.LAVA && getFuelAmount() > 0) {
/*     */         
/*  46 */         this.fuel.amount -= diff;
/*  47 */         if (this.fuel.amount <= 0) {
/*  48 */           this.fuel = null;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setFuelFromStack(FluidStack fs) {
/*  55 */     this.fuel = fs;
/*  56 */     this.fuel.amount *= TFCOptions.oilLampFuelMult;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFuelValid() {
/*  61 */     int meta = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  62 */     if (getFuel() != null && getFuel().getFluid() == TFCFluids.OLIVEOIL)
/*     */     {
/*  64 */       return true;
/*     */     }
/*  66 */     if ((meta & 0x7) == 5 && getFuel() != null && getFuel().getFluid() == TFCFluids.LAVA)
/*     */     {
/*  68 */       return true;
/*     */     }
/*  70 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canUpdate() {
/*  76 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbt) {
/*  82 */     super.func_145839_a(nbt);
/*  83 */     if (nbt.func_74764_b("Fuel")) {
/*  84 */       this.fuel = FluidStack.loadFluidStackFromNBT(nbt.func_74775_l("Fuel"));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbt) {
/*  90 */     super.func_145841_b(nbt);
/*  91 */     if (this.fuel != null) {
/*  92 */       nbt.func_74782_a("Fuel", (NBTBase)this.fuel.writeToNBT(new NBTTagCompound()));
/*     */     }
/*     */   }
/*     */   
/*     */   public int getFuelAmount() {
/*  97 */     if (this.fuel == null)
/*  98 */       return 0; 
/*  99 */     return this.fuel.amount;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFuelTimeLeft() {
/* 104 */     int f = getFuelAmount();
/* 105 */     float perc = f / 250.0F;
/* 106 */     return (int)((TFC_Time.daysInYear * 24) * perc);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEOilLamp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */