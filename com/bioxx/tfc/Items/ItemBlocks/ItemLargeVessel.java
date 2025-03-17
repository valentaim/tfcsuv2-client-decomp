/*     */ package com.bioxx.tfc.Items.ItemBlocks;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Items.ItemTerra;
/*     */ import com.bioxx.tfc.TileEntities.TEPottery;
/*     */ import com.bioxx.tfc.TileEntities.TEVessel;
/*     */ import com.bioxx.tfc.api.Enums.EnumSize;
/*     */ import com.bioxx.tfc.api.Enums.EnumWeight;
/*     */ import com.bioxx.tfc.api.Interfaces.IEquipable;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCFluids;
/*     */ import com.bioxx.tfc.api.Util.Helper;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.IFluidBlock;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemLargeVessel
/*     */   extends ItemTerraBlock
/*     */   implements IEquipable
/*     */ {
/*     */   private static final int MAX_LIQUID = 5000;
/*     */   
/*     */   public ItemLargeVessel(Block block) {
/*  40 */     super(block);
/*  41 */     func_77656_e(0);
/*  42 */     func_77627_a(true);
/*  43 */     func_77637_a(TFCTabs.TFC_POTTERY);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumSize getSize(ItemStack is) {
/*  49 */     return EnumSize.LARGE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumWeight getWeight(ItemStack is) {
/*  55 */     return EnumWeight.HEAVY;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getItemStackLimit(ItemStack is) {
/*  61 */     if (is.func_77942_o())
/*  62 */       return 1; 
/*  63 */     return super.getItemStackLimit(is);
/*     */   }
/*     */ 
/*     */   
/*     */   public void createTooltip(NBTTagCompound nbt, List<String> arraylist) {
/*  68 */     if (nbt != null) {
/*     */       
/*  70 */       boolean addFluid = false;
/*  71 */       if (nbt.func_74764_b("fluidNBT")) {
/*     */         
/*  73 */         FluidStack fluid = FluidStack.loadFluidStackFromNBT(nbt.func_74775_l("fluidNBT"));
/*  74 */         if (fluid != null) {
/*     */           
/*  76 */           addFluid = true;
/*  77 */           arraylist.add(EnumChatFormatting.BLUE + fluid.getFluid().getLocalizedName(fluid));
/*     */         } 
/*     */       } 
/*     */       
/*  81 */       if (!addFluid && nbt.func_74764_b("Items")) {
/*     */         
/*  83 */         NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
/*  84 */         if (nbttaglist != null) {
/*     */           
/*  86 */           int numItems = nbttaglist.func_74745_c();
/*  87 */           boolean showMoreText = false;
/*  88 */           if (numItems > 4 && !TFC_Core.showShiftInformation()) {
/*     */             
/*  90 */             numItems = 3;
/*  91 */             showMoreText = true;
/*     */           } 
/*  93 */           for (int i = 0; i < numItems; i++) {
/*     */             
/*  95 */             NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/*  96 */             if (nbttagcompound1 != null) {
/*     */               
/*  98 */               ItemStack onlyItem = ItemStack.func_77949_a(nbttagcompound1);
/*  99 */               if (onlyItem != null)
/*     */               {
/* 101 */                 arraylist.add(EnumChatFormatting.GOLD + Integer.toString(onlyItem.field_77994_a) + "x " + onlyItem.func_82833_r());
/*     */               }
/*     */             } 
/*     */           } 
/* 105 */           if (showMoreText)
/*     */           {
/* 107 */             arraylist.add(TFC_Core.translate("gui.Barrel.MoreItems"));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
/* 118 */     ItemTerra.addSizeInformation(is, arraylist);
/* 119 */     createTooltip(is.func_77978_p(), arraylist);
/* 120 */     if (TFC_Core.showShiftInformation()) {
/*     */       
/* 122 */       arraylist.add(TFC_Core.translate("gui.Help"));
/* 123 */       arraylist.add(TFC_Core.translate("gui.PotteryBase.Inst0"));
/*     */     } else {
/*     */       
/* 126 */       arraylist.add(TFC_Core.translate("gui.ShowHelp"));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_77648_a(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
/* 132 */     MovingObjectPosition mop = Helper.getMovingObjectPositionFromPlayer(world, (EntityLivingBase)player, true);
/*     */     
/* 134 */     if (mop == null)
/*     */     {
/* 136 */       return super.func_77648_a(is, player, world, x, y, z, side, hitX, hitY, hitZ);
/*     */     }
/*     */ 
/*     */     
/* 140 */     if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/*     */       
/* 142 */       int i = mop.field_72311_b;
/* 143 */       int j = mop.field_72312_c;
/* 144 */       int k = mop.field_72309_d;
/*     */       
/* 146 */       if (!player.func_82247_a(i, j, k, mop.field_72310_e, is) || !(world.func_147439_a(i, j, k) instanceof IFluidBlock) || is.func_77942_o() || is.func_77960_j() == 0)
/*     */       {
/* 148 */         return super.func_77648_a(is, player, world, x, y, z, side, hitX, hitY, hitZ);
/*     */       }
/*     */       
/* 151 */       Fluid fluid = ((IFluidBlock)world.func_147439_a(i, j, k)).getFluid();
/* 152 */       int temp = fluid.getTemperature();
/* 153 */       int volume = 0;
/*     */       
/* 155 */       if (temp < 385 && fluid != TFCFluids.HOTWATER) {
/*     */         
/* 157 */         world.func_147468_f(i, j, k);
/* 158 */         if (fluid == TFCFluids.FRESHWATER || fluid == TFCFluids.SALTWATER) {
/*     */           
/* 160 */           volume = 5000;
/*     */         }
/*     */         else {
/*     */           
/* 164 */           volume = 1000;
/*     */         } 
/*     */         
/* 167 */         if (is.field_77994_a == 1) {
/*     */           
/* 169 */           ItemBarrels.fillItemBarrel(is, new FluidStack(fluid, volume), 5000);
/*     */         }
/*     */         else {
/*     */           
/* 173 */           is.field_77994_a--;
/* 174 */           ItemStack outIS = is.func_77946_l();
/* 175 */           outIS.field_77994_a = 1;
/* 176 */           ItemBarrels.fillItemBarrel(outIS, new FluidStack(fluid, volume), 5000);
/* 177 */           if (!player.field_71071_by.func_70441_a(outIS))
/*     */           {
/* 179 */             player.func_70099_a(outIS, 0.0F);
/*     */           }
/*     */         } 
/*     */       } 
/* 183 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 187 */     return super.func_77648_a(is, player, world, x, y, z, side, hitX, hitY, hitZ);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
/* 193 */     if (metadata > 0) {
/*     */       
/* 195 */       if (!world.func_147465_d(x, y, z, this.field_150939_a, metadata & 0xF, 3))
/*     */       {
/* 197 */         return false;
/*     */       }
/*     */       
/* 200 */       if (world.func_147439_a(x, y, z) == this.field_150939_a)
/*     */       {
/* 202 */         this.field_150939_a.func_149689_a(world, x, y, z, (EntityLivingBase)player, stack);
/* 203 */         this.field_150939_a.func_149714_e(world, x, y, z, 0);
/*     */         
/* 205 */         TEVessel te = (TEVessel)world.func_147438_o(x, y, z);
/* 206 */         te.barrelType = metadata;
/* 207 */         return true;
/*     */       }
/*     */     
/* 210 */     } else if (metadata == 0 && side == 1 && player.func_70093_af()) {
/*     */       
/* 212 */       Block base = world.func_147439_a(x, y - 1, z);
/* 213 */       if (base != TFCBlocks.pottery && world.func_147437_c(x, y, z)) {
/*     */ 
/*     */         
/* 216 */         if (!world.isSideSolid(x, y - 1, z, ForgeDirection.UP))
/* 217 */           return false; 
/* 218 */         world.func_147449_b(x, y, z, TFCBlocks.pottery);
/*     */       }
/*     */       else {
/*     */         
/* 222 */         return false;
/*     */       } 
/*     */       
/* 225 */       if (world.func_147438_o(x, y, z) instanceof TEPottery) {
/*     */         
/* 227 */         TEPottery te = (TEPottery)world.func_147438_o(x, y, z);
/* 228 */         if (te.canAddItem(0)) {
/*     */           
/* 230 */           te.inventory[0] = stack.func_77946_l();
/* 231 */           (te.inventory[0]).field_77994_a = 1;
/* 232 */           world.func_147471_g(x, y, z);
/* 233 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 238 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IEquipable.EquipType getEquipType(ItemStack is) {
/* 244 */     return IEquipable.EquipType.BACK;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEquippedRender() {
/* 250 */     GL11.glTranslatef(0.0F, 0.0F, -0.2F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getTooHeavyToCarry(ItemStack is) {
/* 256 */     return is.func_77942_o();
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemLargeVessel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */