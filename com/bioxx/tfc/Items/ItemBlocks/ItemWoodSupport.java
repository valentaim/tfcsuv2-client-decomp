/*     */ package com.bioxx.tfc.Items.ItemBlocks;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.BlockWoodSupport;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.Enums.EnumSize;
/*     */ import com.bioxx.tfc.api.Enums.EnumWeight;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class ItemWoodSupport extends ItemTerraBlock {
/*     */   public ItemWoodSupport(Block par1) {
/*  19 */     super(par1);
/*  20 */     this.field_77787_bX = true;
/*  21 */     func_77656_e(0);
/*  22 */     this.metaNames = new String[16];
/*  23 */     System.arraycopy(Global.WOOD_ALL, 0, this.metaNames, 0, 16);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumSize getSize(ItemStack is) {
/*  28 */     return EnumSize.SMALL;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumWeight getWeight(ItemStack is) {
/*  34 */     return EnumWeight.MEDIUM;
/*     */   }
/*     */ 
/*     */   
/*     */   public Block getBlock() {
/*  39 */     return this.field_150939_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidUnder(World world, int x, int y, int z, int side) {
/*  44 */     if (side == 0) {
/*  45 */       y--;
/*  46 */     } else if (side == 1) {
/*  47 */       y++;
/*  48 */     } else if (side == 2) {
/*  49 */       z--;
/*  50 */     } else if (side == 3) {
/*  51 */       z++;
/*  52 */     } else if (side == 4) {
/*  53 */       x--;
/*  54 */     } else if (side == 5) {
/*  55 */       x++;
/*     */     } 
/*  57 */     Block b = world.func_147439_a(x, y - 1, z);
/*  58 */     return (b == TFCBlocks.woodSupportV || b == TFCBlocks.woodSupportV2 || b.isSideSolid((IBlockAccess)world, x, y - 1, z, ForgeDirection.UP));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_77648_a(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
/*  64 */     if (y < 250 && y > 0 && side == 1 && isValidUnder(world, x, y, z, side)) {
/*     */       
/*  66 */       if (!player.func_70093_af() && world.func_147437_c(x, y + 1, z) && world.func_147437_c(x, y + 2, z) && world.func_147437_c(x, y + 3, z) && itemstack.field_77994_a >= 3 && world
/*  67 */         .func_147439_a(x, y, z) != TFCBlocks.woodSupportV && world.func_147439_a(x, y, z) != TFCBlocks.woodSupportV2) {
/*     */         
/*  69 */         placeBlockAt(getBlock(), itemstack, player, world, x, y + 1, z, itemstack.func_77960_j(), 2);
/*  70 */         placeBlockAt(getBlock(), itemstack, player, world, x, y + 2, z, itemstack.func_77960_j(), 2);
/*  71 */         placeBlockAt(getBlock(), itemstack, player, world, x, y + 3, z, itemstack.func_77960_j(), 2);
/*  72 */         itemstack.field_77994_a -= 3;
/*  73 */         return true;
/*     */       } 
/*     */ 
/*     */       
/*  77 */       placeBlockAt(getBlock(), itemstack, player, world, x, y + 1, z, itemstack.func_77960_j(), 3);
/*  78 */       itemstack.field_77994_a--;
/*  79 */       return true;
/*     */     } 
/*     */     
/*  82 */     if (y < 255 && y > 0 && side == 0) {
/*     */       
/*  84 */       boolean shouldGen = false;
/*  85 */       int dist = 0;
/*  86 */       for (dist = 1; dist <= 20 && !shouldGen; dist++) {
/*     */         
/*  88 */         if (!world.func_147439_a(x, y - dist, z).isReplaceable((IBlockAccess)world, x, y - dist, z)) {
/*     */ 
/*     */ 
/*     */           
/*  92 */           if (world.func_147439_a(x, y - dist, z).isSideSolid((IBlockAccess)world, x, y - dist, z, ForgeDirection.UP))
/*     */           {
/*  94 */             shouldGen = true;
/*     */           }
/*     */ 
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 103 */       if (itemstack.field_77994_a >= dist - 1) {
/* 104 */         for (int j = dist - 1; j >= 1 && shouldGen;) {
/*     */           
/* 106 */           if (world.func_147439_a(x, y - j, z).isReplaceable((IBlockAccess)world, x, y - j, z)) {
/*     */             
/* 108 */             placeBlockAt(getBlock(), itemstack, player, world, x, y - j, z, itemstack.func_77960_j(), 3);
/* 109 */             itemstack.field_77994_a--;
/* 110 */             world.func_147471_g(x, y - j, z);
/*     */             
/*     */             j--;
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } else {
/* 117 */       Block b = TFCBlocks.woodSupportH;
/* 118 */       if (getBlock() == TFCBlocks.woodSupportV2) {
/* 119 */         b = TFCBlocks.woodSupportH2;
/*     */       }
/* 121 */       if (side == 0) {
/* 122 */         y--;
/* 123 */       } else if (side == 1) {
/* 124 */         y++;
/* 125 */       } else if (side == 2) {
/* 126 */         z--;
/* 127 */       } else if (side == 3) {
/* 128 */         z++;
/* 129 */       } else if (side == 4) {
/* 130 */         x--;
/* 131 */       } else if (side == 5) {
/* 132 */         x++;
/*     */       } 
/* 134 */       if (y == 255 && b.func_149688_o().func_76220_a())
/*     */       {
/* 136 */         return false;
/*     */       }
/* 138 */       if (world.func_147472_a(b, x, y, z, false, side, (Entity)player, itemstack)) {
/*     */         
/* 140 */         ForgeDirection dir = BlockWoodSupport.getSupportDirection(world, x, y, z);
/*     */         
/* 142 */         int[] dist = BlockWoodSupport.getSupportsInRangeDir(world, x, y, z, 5, false);
/* 143 */         int total = BlockWoodSupport.getDistanceFromDirection(dir, dist);
/* 144 */         if (total == Integer.MAX_VALUE) {
/*     */           
/* 146 */           total = 1;
/* 147 */           dir = ForgeDirection.getOrientation(side);
/*     */         } 
/* 149 */         if (itemstack.field_77994_a < total)
/* 150 */           return false; 
/* 151 */         int i1 = func_77647_b(itemstack.func_77960_j());
/* 152 */         int count = 0;
/*     */         
/*     */         do {
/* 155 */           int j1 = b.func_149660_a(world, x + dir.offsetX * count, y, z + dir.offsetZ * count, side, hitX, hitY, hitZ, i1);
/* 156 */           if (!placeBlockAt(b, itemstack, player, world, x + dir.offsetX * count, y, z + dir.offsetZ * count, j1, 2))
/*     */             continue; 
/* 158 */           world.func_72908_a((x + (dir.offsetX * count) + 0.5F), (y + 0.5F), (z + (dir.offsetZ * count) + 0.5F), b.field_149762_H.func_150496_b(), (b.field_149762_H.func_150497_c() + 1.0F) / 2.0F, b.field_149762_H.func_150494_d() * 0.8F);
/* 159 */           itemstack.field_77994_a--;
/*     */           
/* 161 */           ++count;
/* 162 */         } while (count < total);
/*     */ 
/*     */ 
/*     */         
/* 166 */         return true;
/*     */       } 
/*     */     } 
/* 169 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean placeBlockAt(Block b, ItemStack is, EntityPlayer player, World world, int x, int y, int z, int metadata, int flag) {
/* 174 */     if (!world.func_147465_d(x, y, z, b, metadata, flag))
/*     */     {
/* 176 */       return false;
/*     */     }
/*     */     
/* 179 */     if (world.func_147439_a(x, y, z) == b) {
/*     */       
/* 181 */       b.func_149689_a(world, x, y, z, (EntityLivingBase)player, is);
/* 182 */       b.func_149714_e(world, x, y, z, metadata);
/*     */     } 
/*     */     
/* 185 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemWoodSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */