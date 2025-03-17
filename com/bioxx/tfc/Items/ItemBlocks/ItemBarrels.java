/*     */ package com.bioxx.tfc.Items.ItemBlocks;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.Items.ItemTerra;
/*     */ import com.bioxx.tfc.TileEntities.TEBarrel;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.Enums.EnumSize;
/*     */ import com.bioxx.tfc.api.Enums.EnumWeight;
/*     */ import com.bioxx.tfc.api.Interfaces.IEquipable;
/*     */ import com.bioxx.tfc.api.TFCFluids;
/*     */ import com.bioxx.tfc.api.Util.Helper;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.IFluidBlock;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ public class ItemBarrels
/*     */   extends ItemTerraBlock
/*     */   implements IEquipable
/*     */ {
/*     */   private static final int MAX_LIQUID = 10000;
/*     */   
/*     */   public ItemBarrels(Block par1) {
/*  40 */     super(par1);
/*  41 */     func_77656_e(0);
/*  42 */     func_77627_a(true);
/*  43 */     func_77637_a(TFCTabs.TFC_DEVICES);
/*  44 */     this.metaNames = Global.WOOD_ALL;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumSize getSize(ItemStack is) {
/*  50 */     return EnumSize.LARGE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumWeight getWeight(ItemStack is) {
/*  56 */     return EnumWeight.HEAVY;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getItemStackLimit(ItemStack is) {
/*  62 */     if (is.func_77942_o())
/*  63 */       return 1; 
/*  64 */     return super.getItemStackLimit(is);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readFromItemNBT(NBTTagCompound nbt, List<String> arraylist) {
/*  69 */     if (nbt != null) {
/*     */       
/*  71 */       boolean addFluid = false;
/*  72 */       if (nbt.func_74764_b("fluidNBT")) {
/*     */         
/*  74 */         FluidStack fluid = FluidStack.loadFluidStackFromNBT(nbt.func_74775_l("fluidNBT"));
/*  75 */         if (fluid != null) {
/*     */           
/*  77 */           addFluid = true;
/*  78 */           arraylist.add(EnumChatFormatting.BLUE + fluid.getFluid().getLocalizedName(fluid));
/*     */         } 
/*     */       } 
/*     */       
/*  82 */       if (!addFluid && nbt.func_74764_b("Items")) {
/*     */         
/*  84 */         NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
/*  85 */         if (nbttaglist != null) {
/*     */           
/*  87 */           int numItems = nbttaglist.func_74745_c();
/*  88 */           boolean showMoreText = false;
/*  89 */           if (numItems > 4 && !TFC_Core.showShiftInformation()) {
/*     */             
/*  91 */             numItems = 3;
/*  92 */             showMoreText = true;
/*     */           } 
/*  94 */           for (int i = 0; i < numItems; i++) {
/*     */             
/*  96 */             NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/*  97 */             if (nbttagcompound1 != null) {
/*     */               
/*  99 */               ItemStack onlyItem = ItemStack.func_77949_a(nbttagcompound1);
/* 100 */               if (onlyItem != null)
/*     */               {
/* 102 */                 arraylist.add(EnumChatFormatting.GOLD + Integer.toString(onlyItem.field_77994_a) + "x " + onlyItem.func_82833_r());
/*     */               }
/*     */             } 
/*     */           } 
/* 106 */           if (showMoreText)
/*     */           {
/* 108 */             arraylist.add(TFC_Core.translate("gui.Barrel.MoreItems"));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
/* 118 */     ItemTerra.addSizeInformation(is, arraylist);
/* 119 */     readFromItemNBT(is.func_77978_p(), arraylist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_77648_a(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
/* 125 */     MovingObjectPosition mop = Helper.getMovingObjectPositionFromPlayer(world, (EntityLivingBase)player, true);
/*     */     
/* 127 */     if (mop == null)
/*     */     {
/* 129 */       return super.func_77648_a(is, player, world, x, y, z, side, hitX, hitY, hitZ);
/*     */     }
/*     */ 
/*     */     
/* 133 */     if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/*     */       
/* 135 */       int i = mop.field_72311_b;
/* 136 */       int j = mop.field_72312_c;
/* 137 */       int k = mop.field_72309_d;
/*     */       
/* 139 */       if (!player.func_82247_a(i, j, k, mop.field_72310_e, is) || !(world.func_147439_a(i, j, k) instanceof IFluidBlock) || is.func_77942_o())
/*     */       {
/* 141 */         return super.func_77648_a(is, player, world, x, y, z, side, hitX, hitY, hitZ);
/*     */       }
/*     */       
/* 144 */       Fluid fluid = ((IFluidBlock)world.func_147439_a(i, j, k)).getFluid();
/* 145 */       int temp = fluid.getTemperature();
/* 146 */       int volume = 0;
/* 147 */       if (temp < 385 && fluid != TFCFluids.HOTWATER) {
/*     */         
/* 149 */         world.func_147468_f(i, j, k);
/*     */         
/* 151 */         if (fluid == TFCFluids.FRESHWATER || fluid == TFCFluids.SALTWATER) {
/*     */           
/* 153 */           volume = 10000;
/*     */         }
/*     */         else {
/*     */           
/* 157 */           volume = 1000;
/*     */         } 
/*     */         
/* 160 */         if (is.field_77994_a == 1) {
/*     */           
/* 162 */           fillItemBarrel(is, new FluidStack(fluid, volume), 10000);
/*     */         }
/*     */         else {
/*     */           
/* 166 */           is.field_77994_a--;
/* 167 */           ItemStack outIS = is.func_77946_l();
/* 168 */           outIS.field_77994_a = 1;
/* 169 */           fillItemBarrel(outIS, new FluidStack(fluid, volume), 10000);
/* 170 */           if (!player.field_71071_by.func_70441_a(outIS))
/*     */           {
/* 172 */             player.func_70099_a(outIS, 0.0F);
/*     */           }
/*     */         } 
/*     */       } 
/* 176 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 180 */     return super.func_77648_a(is, player, world, x, y, z, side, hitX, hitY, hitZ);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
/* 187 */     if (!world.func_147465_d(x, y, z, this.field_150939_a, metadata & 0xF, 3))
/*     */     {
/* 189 */       return false;
/*     */     }
/*     */     
/* 192 */     if (world.func_147439_a(x, y, z) == this.field_150939_a) {
/*     */       
/* 194 */       this.field_150939_a.func_149689_a(world, x, y, z, (EntityLivingBase)player, stack);
/* 195 */       this.field_150939_a.func_149714_e(world, x, y, z, 0);
/*     */       
/* 197 */       TEBarrel te = (TEBarrel)world.func_147438_o(x, y, z);
/* 198 */       te.barrelType = metadata;
/*     */     } 
/*     */     
/* 201 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
/* 207 */     for (int i = 0; i < this.metaNames.length; i++) {
/* 208 */       list.add(new ItemStack((Item)this, 1, i));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IEquipable.EquipType getEquipType(ItemStack is) {
/* 215 */     return IEquipable.EquipType.BACK;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEquippedRender() {
/* 221 */     GL11.glTranslatef(0.0F, -0.3F, -0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getTooHeavyToCarry(ItemStack is) {
/* 227 */     return (is.func_77942_o() && is.func_77978_p().func_74764_b("Sealed"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack fillItemBarrel(ItemStack is, FluidStack fs, int maxFluid) {
/* 232 */     NBTTagCompound nbt = new NBTTagCompound();
/* 233 */     if (is.func_77942_o())
/*     */     {
/* 235 */       nbt = is.func_77978_p();
/*     */     }
/*     */     
/* 238 */     if (nbt.func_74764_b("Sealed")) {
/* 239 */       return is;
/*     */     }
/*     */     
/* 242 */     if (nbt.func_74764_b("fluidNBT")) {
/*     */       
/* 244 */       FluidStack ifs = FluidStack.loadFluidStackFromNBT(nbt.func_74775_l("fluidNBT"));
/* 245 */       if (ifs.isFluidEqual(fs) && ifs.amount < maxFluid) {
/*     */         
/* 247 */         ifs.amount += fs.amount;
/* 248 */         ifs.amount %= maxFluid;
/* 249 */         ifs.amount = Math.min(ifs.amount, maxFluid);
/* 250 */         nbt.func_74782_a("fluidNBT", (NBTBase)ifs.writeToNBT(new NBTTagCompound()));
/* 251 */         nbt.func_74757_a("Sealed", true);
/* 252 */         nbt.func_74768_a("SealTime", (int)TFC_Time.getTotalHours());
/*     */       } else {
/* 254 */         return is;
/*     */       } 
/*     */     } else {
/*     */       
/* 258 */       nbt.func_74782_a("fluidNBT", (NBTBase)fs.writeToNBT(new NBTTagCompound()));
/* 259 */       nbt.func_74757_a("Sealed", true);
/* 260 */       nbt.func_74768_a("SealTime", (int)TFC_Time.getTotalHours());
/*     */     } 
/*     */     
/* 263 */     is.func_77982_d(nbt);
/* 264 */     return is;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemBarrels.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */