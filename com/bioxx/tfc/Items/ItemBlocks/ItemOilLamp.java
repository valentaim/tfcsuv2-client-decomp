/*     */ package com.bioxx.tfc.Items.ItemBlocks;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.Enums.EnumSize;
/*     */ import com.bioxx.tfc.api.Enums.EnumWeight;
/*     */ import com.bioxx.tfc.api.Interfaces.ISmeltable;
/*     */ import com.bioxx.tfc.api.Metal;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCFluids;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import com.bioxx.tfc.api.Util.Helper;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.IFluidContainerItem;
/*     */ 
/*     */ 
/*     */ public class ItemOilLamp
/*     */   extends ItemTerraBlock
/*     */   implements ISmeltable, IFluidContainerItem
/*     */ {
/*     */   public ItemOilLamp(Block par1) {
/*  29 */     super(par1);
/*  30 */     this.metaNames = new String[] { "Gold", "Platinum", "RoseGold", "Silver", "SterlingSilver", "BlueSteel" };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDisplayDamage(ItemStack is) {
/*  36 */     FluidStack fuel = FluidStack.loadFluidStackFromNBT(is.func_77978_p());
/*  37 */     int amt = (fuel != null) ? fuel.amount : 0;
/*  38 */     return getMaxDamage(is) - amt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDamaged(ItemStack is) {
/*  44 */     return is.func_77942_o();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxDamage(ItemStack is) {
/*  50 */     return 250;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumSize getSize(ItemStack is) {
/*  55 */     return EnumSize.SMALL;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumWeight getWeight(ItemStack is) {
/*  60 */     return EnumWeight.LIGHT;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getItemStackLimit(ItemStack is) {
/*  66 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public short getMetalReturnAmount(ItemStack is) {
/*  72 */     return 100;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSmeltable(ItemStack is) {
/*  77 */     FluidStack fuel = FluidStack.loadFluidStackFromNBT(is.func_77978_p());
/*  78 */     return (fuel == null || fuel.amount == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ISmeltable.EnumTier getSmeltTier(ItemStack is) {
/*  84 */     return ISmeltable.EnumTier.TierI;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Metal getMetalType(ItemStack is) {
/*  90 */     int meta = is.func_77960_j();
/*  91 */     switch (meta) {
/*     */       case 0:
/*  93 */         return Global.GOLD;
/*  94 */       case 1: return Global.PLATINUM;
/*  95 */       case 2: return Global.ROSEGOLD;
/*  96 */       case 3: return Global.SILVER;
/*  97 */       case 4: return Global.STERLINGSILVER;
/*  98 */       case 5: return Global.BLUESTEEL;
/*  99 */     }  return Global.UNKNOWN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_77648_a(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
/* 106 */     MovingObjectPosition mop = func_77621_a(world, player, !is.func_77942_o());
/* 107 */     if (mop != null && is.func_77960_j() == 5 && world.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) == TFCBlocks.lavaStationary) {
/*     */       
/* 109 */       if (!is.func_77942_o()) {
/*     */         
/* 111 */         FluidStack fs = new FluidStack(TFCFluids.LAVA, 250);
/* 112 */         is.func_77982_d(fs.writeToNBT(new NBTTagCompound()));
/*     */       } 
/* 114 */       return false;
/*     */     } 
/*     */     
/* 117 */     int yCoord = y;
/* 118 */     if (side == 0) {
/* 119 */       yCoord--;
/* 120 */     } else if (side == 1) {
/* 121 */       yCoord++;
/*     */     } else {
/* 123 */       return false;
/*     */     } 
/* 125 */     int xCoord = x;
/* 126 */     int zCoord = z;
/*     */     
/* 128 */     if (world.func_147437_c(xCoord, yCoord, zCoord))
/*     */     {
/* 130 */       return super.func_77648_a(is, player, world, xCoord, yCoord, zCoord, side, hitX, hitY, hitZ);
/*     */     }
/*     */     
/* 133 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
/* 139 */     FluidStack fs = FluidStack.loadFluidStackFromNBT(stack.func_77978_p());
/* 140 */     if (fs == null || (fs.getFluid() != TFCFluids.OLIVEOIL && fs.getFluid() != TFCFluids.LAVA))
/*     */     {
/* 142 */       metadata += 8;
/*     */     }
/*     */     
/* 145 */     return super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
/* 151 */     super.func_77624_a(is, player, arraylist, flag);
/* 152 */     if (is.func_77942_o()) {
/*     */       
/* 154 */       FluidStack fs = FluidStack.loadFluidStackFromNBT(is.func_77978_p());
/* 155 */       if (fs != null && fs.getFluid() == TFCFluids.OLIVEOIL)
/* 156 */         arraylist.add((fs.amount * TFCOptions.oilLampFuelMult) + " " + TFC_Core.translate("gui.hoursRemaining") + " (" + Helper.roundNumber(fs.amount / 250.0F * 100.0F, 10.0F) + "%)"); 
/* 157 */       if (fs != null && fs.getFluid() == TFCFluids.LAVA) {
/* 158 */         arraylist.add(TFC_Core.translate("gui.infinite") + " " + TFC_Core.translate("gui.hoursRemaining"));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static ItemStack getFullLamp(int meta) {
/* 164 */     ItemStack is = new ItemStack(TFCBlocks.oilLamp, 1, meta);
/* 165 */     FluidStack fs = new FluidStack(TFCFluids.OLIVEOIL, 250);
/* 166 */     is.func_77982_d(fs.writeToNBT(new NBTTagCompound()));
/* 167 */     return is;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public FluidStack getFluid(ItemStack container) {
/* 173 */     return FluidStack.loadFluidStackFromNBT(container.func_77978_p());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCapacity(ItemStack container) {
/* 179 */     return 250;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int fill(ItemStack container, FluidStack resource, boolean doSim) {
/* 185 */     FluidStack fs = getFluid(container);
/* 186 */     int inAmt = 0;
/* 187 */     if (fs != null) {
/*     */       
/* 189 */       int max = getCapacity(container) - fs.amount;
/* 190 */       if (max > 0 && fs.isFluidEqual(resource)) {
/*     */         
/* 192 */         inAmt = Math.min(max, resource.amount);
/* 193 */         if (doSim)
/*     */         {
/* 195 */           fs.amount += inAmt;
/* 196 */           if (container.func_77978_p() == null)
/* 197 */             container.func_77982_d(new NBTTagCompound()); 
/* 198 */           fs.writeToNBT(container.func_77978_p());
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 204 */       inAmt = Math.min(getCapacity(container), resource.amount);
/* 205 */       if (doSim) {
/*     */         
/* 207 */         fs = resource.copy();
/* 208 */         fs.amount = inAmt;
/* 209 */         if (container.func_77978_p() == null)
/* 210 */           container.func_77982_d(new NBTTagCompound()); 
/* 211 */         fs.writeToNBT(container.func_77978_p());
/*     */       } 
/*     */     } 
/* 214 */     return inAmt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public FluidStack drain(ItemStack container, int maxDrain, boolean doSim) {
/* 220 */     FluidStack fs = getFluid(container);
/* 221 */     FluidStack fsOut = fs.copy();
/* 222 */     fsOut.amount = Math.min(maxDrain, fs.amount);
/*     */     
/* 224 */     if (doSim)
/*     */     {
/* 226 */       if (fs.amount - fsOut.amount <= 0) {
/*     */         
/* 228 */         container.field_77990_d = null;
/*     */       }
/*     */       else {
/*     */         
/* 232 */         fs.amount -= fsOut.amount;
/* 233 */         if (container.func_77978_p() == null)
/* 234 */           container.func_77982_d(new NBTTagCompound()); 
/* 235 */         fs.writeToNBT(container.func_77978_p());
/*     */       } 
/*     */     }
/* 238 */     return fsOut;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemOilLamp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */