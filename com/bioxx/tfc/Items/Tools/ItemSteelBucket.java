/*     */ package com.bioxx.tfc.Items.Tools;
/*     */ 
/*     */ import com.bioxx.tfc.Items.ItemTerra;
/*     */ import com.bioxx.tfc.api.Enums.EnumItemReach;
/*     */ import com.bioxx.tfc.api.Enums.EnumSize;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.Util.Helper;
/*     */ import cpw.mods.fml.common.eventhandler.Event;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.player.FillBucketEvent;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.FluidContainerRegistry;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.IFluidBlock;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemSteelBucket
/*     */   extends ItemTerra
/*     */ {
/*     */   protected Block bucketContents;
/*     */   
/*     */   public ItemSteelBucket(Block par2) {
/*  32 */     this.bucketContents = par2;
/*  33 */     setFolder("tools/");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumSize getSize(ItemStack is) {
/*  39 */     return EnumSize.LARGE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canStack() {
/*  45 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack is, World world, EntityPlayer player) {
/*  55 */     boolean var11 = (this.bucketContents == Blocks.field_150350_a);
/*  56 */     MovingObjectPosition mop = Helper.getMovingObjectPositionFromPlayer(world, (EntityLivingBase)player, var11);
/*     */     
/*  58 */     if (mop == null)
/*     */     {
/*  60 */       return is;
/*     */     }
/*     */ 
/*     */     
/*  64 */     FillBucketEvent event = new FillBucketEvent(player, is, world, mop);
/*  65 */     if (MinecraftForge.EVENT_BUS.post((Event)event))
/*     */     {
/*  67 */       return is;
/*     */     }
/*     */     
/*  70 */     if (event.getResult() == Event.Result.ALLOW) {
/*     */       
/*  72 */       if (player.field_71075_bZ.field_75098_d)
/*     */       {
/*  74 */         return is;
/*     */       }
/*     */       
/*  77 */       if (--is.field_77994_a <= 0)
/*     */       {
/*  79 */         return event.result;
/*     */       }
/*     */       
/*  82 */       if (!player.field_71071_by.func_70441_a(event.result))
/*     */       {
/*  84 */         player.func_71019_a(event.result, false);
/*     */       }
/*     */       
/*  87 */       return is;
/*     */     } 
/*  89 */     if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/*     */       
/*  91 */       int i = mop.field_72311_b;
/*  92 */       int j = mop.field_72312_c;
/*  93 */       int k = mop.field_72309_d;
/*     */ 
/*     */ 
/*     */       
/*  97 */       if (!world.func_72962_a(player, i, j, k))
/*     */       {
/*  99 */         return is;
/*     */       }
/*     */       
/* 102 */       if (this.bucketContents == Blocks.field_150350_a) {
/*     */         
/* 104 */         if (!player.func_82247_a(i, j, k, mop.field_72310_e, is) || !(world.func_147439_a(i, j, k) instanceof IFluidBlock))
/*     */         {
/* 106 */           return is;
/*     */         }
/*     */         
/* 109 */         Fluid fluid = ((IFluidBlock)world.func_147439_a(i, j, k)).getFluid();
/* 110 */         if (fluid != null) {
/*     */           
/* 112 */           ItemStack filledIS = FluidContainerRegistry.fillFluidContainer(new FluidStack(fluid, 1000), is);
/* 113 */           if (filledIS != null)
/*     */           {
/* 115 */             world.func_147468_f(i, j, k);
/*     */             
/* 117 */             if (--is.field_77994_a <= 0)
/*     */             {
/* 119 */               return filledIS;
/*     */             }
/*     */             
/* 122 */             if (!player.field_71071_by.func_70441_a(filledIS))
/*     */             {
/* 124 */               player.func_70099_a(filledIS, 0.0F);
/*     */             }
/*     */             
/* 127 */             return is;
/*     */           }
/*     */         
/*     */         } 
/*     */       } else {
/*     */         
/* 133 */         if (this.bucketContents == Blocks.field_150350_a)
/*     */         {
/* 135 */           return getContainerItem(is);
/*     */         }
/*     */         
/* 138 */         if (mop.field_72310_e == 0) j--; 
/* 139 */         if (mop.field_72310_e == 1) j++; 
/* 140 */         if (mop.field_72310_e == 2) k--; 
/* 141 */         if (mop.field_72310_e == 3) k++; 
/* 142 */         if (mop.field_72310_e == 4) i--; 
/* 143 */         if (mop.field_72310_e == 5) i++;
/*     */         
/* 145 */         if (!player.func_82247_a(i, j, k, mop.field_72310_e, is))
/*     */         {
/* 147 */           return is;
/*     */         }
/*     */         
/* 150 */         if (tryPlaceContainedLiquid(world, i, j, k) && !player.field_71075_bZ.field_75098_d)
/*     */         {
/* 152 */           return getContainerItem(is);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 157 */     return is;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean tryPlaceContainedLiquid(World world, int x, int y, int z) {
/* 165 */     if (this.bucketContents == Blocks.field_150350_a)
/*     */     {
/* 167 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 171 */     Material material = world.func_147439_a(x, y, z).func_149688_o();
/* 172 */     boolean flag = !material.func_76220_a();
/*     */     
/* 174 */     if (!world.func_147437_c(x, y, z) && !flag)
/*     */     {
/* 176 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 180 */     if (world.field_73011_w.field_76575_d && (this.bucketContents == TFCBlocks.freshWater || this.bucketContents == TFCBlocks.saltWater)) {
/*     */       
/* 182 */       world.func_72908_a((x + 0.5F), (y + 0.5F), (z + 0.5F), "random.fizz", 0.5F, 2.6F + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.8F);
/*     */       
/* 184 */       for (int l = 0; l < 8; l++)
/*     */       {
/* 186 */         world.func_72869_a("largesmoke", x + Math.random(), y + Math.random(), z + Math.random(), 0.0D, 0.0D, 0.0D);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 191 */       if (!world.field_72995_K && flag && !material.func_76224_d())
/*     */       {
/* 193 */         world.func_147480_a(x, y, z, true);
/*     */       }
/*     */       
/* 196 */       world.func_147465_d(x, y, z, this.bucketContents, 0, 3);
/*     */     } 
/*     */     
/* 199 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumItemReach getReach(ItemStack is) {
/* 207 */     return EnumItemReach.SHORT;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemSteelBucket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */