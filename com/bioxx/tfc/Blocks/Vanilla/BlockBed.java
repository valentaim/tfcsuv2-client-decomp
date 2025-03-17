/*     */ package com.bioxx.tfc.Blocks.Vanilla;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.WorldGen.TFCBiome;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockDirectional;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockBed
/*     */   extends BlockDirectional
/*     */ {
/*  36 */   public static final int[][] FOOT_HEAD_BLOCKMAP = new int[][] { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon[] bedEndIcons;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon[] bedSideIcons;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon[] bedTopIcons;
/*     */   
/*     */   public BlockBed() {
/*  46 */     super(Material.field_151577_b);
/*  47 */     setBounds();
/*  48 */     func_149647_a(TFCTabs.TFC_DEVICES);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
/*  57 */     if (world.field_72995_K)
/*     */     {
/*  59 */       return true;
/*     */     }
/*     */ 
/*     */     
/*  63 */     int i1 = world.func_72805_g(x, y, z);
/*     */     
/*  65 */     if (!isBlockHeadOfBed(i1)) {
/*     */       
/*  67 */       int j1 = func_149895_l(i1);
/*  68 */       x += FOOT_HEAD_BLOCKMAP[j1][0];
/*  69 */       z += FOOT_HEAD_BLOCKMAP[j1][1];
/*     */       
/*  71 */       if (world.func_147439_a(x, y, z) != this) {
/*  72 */         return true;
/*     */       }
/*  74 */       i1 = world.func_72805_g(x, y, z);
/*     */     } 
/*     */     
/*  77 */     if (world.field_73011_w.func_76567_e() && world.func_72807_a(x, z) != TFCBiome.HELL) {
/*     */       
/*  79 */       if (isBedOccupied(i1)) {
/*     */         
/*  81 */         EntityPlayer entityplayer1 = null;
/*  82 */         Iterator<EntityPlayer> iterator = world.field_73010_i.iterator();
/*     */         
/*  84 */         while (iterator.hasNext()) {
/*     */           
/*  86 */           EntityPlayer entityplayer2 = iterator.next();
/*     */           
/*  88 */           if (entityplayer2.func_70608_bn()) {
/*     */             
/*  90 */             ChunkCoordinates chunkcoordinates = entityplayer2.field_71081_bT;
/*     */             
/*  92 */             if (chunkcoordinates.field_71574_a == x && chunkcoordinates.field_71572_b == y && chunkcoordinates.field_71573_c == z)
/*     */             {
/*  94 */               entityplayer1 = entityplayer2;
/*     */             }
/*     */           } 
/*     */         } 
/*     */         
/*  99 */         if (entityplayer1 != null) {
/*     */           
/* 101 */           TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("tile.bed.occupied", new Object[0]));
/* 102 */           return true;
/*     */         } 
/*     */         
/* 105 */         setBedOccupied(world, x, y, z, false);
/*     */       } 
/*     */ 
/*     */       
/* 109 */       EntityPlayer.EnumStatus enumstatus = player.func_71018_a(x, y, z);
/*     */ 
/*     */       
/* 112 */       if (enumstatus == EntityPlayer.EnumStatus.OK) {
/*     */ 
/*     */         
/* 115 */         setBedOccupied(world, x, y, z, true);
/* 116 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 120 */       if (enumstatus == EntityPlayer.EnumStatus.NOT_POSSIBLE_NOW) {
/* 121 */         TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("tile.bed.noSleep", new Object[0]));
/* 122 */       } else if (enumstatus == EntityPlayer.EnumStatus.NOT_SAFE) {
/* 123 */         TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("tile.bed.notSafe", new Object[0]));
/*     */       } 
/* 125 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 130 */     double d0 = x + 0.5D;
/* 131 */     double d1 = y + 0.5D;
/* 132 */     double d2 = z + 0.5D;
/* 133 */     world.func_147468_f(x, y, z);
/* 134 */     int k1 = func_149895_l(i1);
/* 135 */     x += FOOT_HEAD_BLOCKMAP[k1][0];
/* 136 */     z += FOOT_HEAD_BLOCKMAP[k1][1];
/*     */     
/* 138 */     if (world.func_147439_a(x, y, z) == this) {
/*     */       
/* 140 */       world.func_147468_f(x, y, z);
/* 141 */       d0 = (d0 + x + 0.5D) / 2.0D;
/* 142 */       d1 = (d1 + y + 0.5D) / 2.0D;
/* 143 */       d2 = (d2 + z + 0.5D) / 2.0D;
/*     */     } 
/*     */     
/* 146 */     world.func_72885_a((Entity)null, (x + 0.5F), (y + 0.5F), (z + 0.5F), 5.0F, true, true);
/* 147 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBed(IBlockAccess world, int x, int y, int z, EntityLivingBase player) {
/* 155 */     World w = (World)world;
/* 156 */     if (!w.field_72995_K && player != null) {
/* 157 */       ((EntityPlayer)player).field_71076_b = 50;
/*     */     }
/* 159 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int par2) {
/* 171 */     if (par1 == 0)
/*     */     {
/* 173 */       return TFCBlocks.planks.func_149733_h(par1);
/*     */     }
/*     */ 
/*     */     
/* 177 */     int k = func_149895_l(par2);
/* 178 */     int l = Direction.field_71584_h[k][par1];
/* 179 */     int i1 = isBlockHeadOfBed(par2) ? 1 : 0;
/* 180 */     return ((i1 != 1 || l != 2) && (i1 != 0 || l != 3)) ? ((l != 5 && l != 4) ? this.bedTopIcons[i1] : this.bedSideIcons[i1]) : this.bedEndIcons[i1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister par1IconRegister) {
/* 193 */     this.bedTopIcons = new IIcon[] { par1IconRegister.func_94245_a("terrafirmacraft:straw bed_feet_top"), par1IconRegister.func_94245_a("terrafirmacraft:straw bed_head_top") };
/* 194 */     this.bedEndIcons = new IIcon[] { par1IconRegister.func_94245_a("terrafirmacraft:straw bed_feet_end"), par1IconRegister.func_94245_a("terrafirmacraft:straw bed_head_end") };
/* 195 */     this.bedSideIcons = new IIcon[] { par1IconRegister.func_94245_a("terrafirmacraft:straw bed_feet_side"), par1IconRegister.func_94245_a("terrafirmacraft:straw bed_head_side") };
/*     */   }
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
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149645_b() {
/* 212 */     return 14;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149686_d() {
/* 221 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/* 231 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149719_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 240 */     setBounds();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5) {
/* 250 */     int i1 = par1World.func_72805_g(par2, par3, par4);
/* 251 */     int j1 = func_149895_l(i1);
/*     */     
/* 253 */     if (isBlockHeadOfBed(i1)) {
/*     */       
/* 255 */       if (par1World.func_147439_a(par2 - FOOT_HEAD_BLOCKMAP[j1][0], par3, par4 - FOOT_HEAD_BLOCKMAP[j1][1]) != this)
/*     */       {
/* 257 */         par1World.func_147468_f(par2, par3, par4);
/*     */       }
/*     */     }
/* 260 */     else if (par1World.func_147439_a(par2 + FOOT_HEAD_BLOCKMAP[j1][0], par3, par4 + FOOT_HEAD_BLOCKMAP[j1][1]) != this) {
/*     */       
/* 262 */       par1World.func_147468_f(par2, par3, par4);
/*     */       
/* 264 */       if (!par1World.field_72995_K)
/*     */       {
/* 266 */         func_149697_b(par1World, par2, par3, par4, i1, 0);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item func_149650_a(int par1, Random par2Random, int par3) {
/* 277 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setBounds() {
/* 285 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.5625F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isBlockHeadOfBed(int par0) {
/* 293 */     return ((par0 & 0x8) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isBedOccupied(int par0) {
/* 301 */     return ((par0 & 0x4) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setBedOccupied(World par0World, int par1, int par2, int par3, boolean par4) {
/* 309 */     int l = par0World.func_72805_g(par1, par2, par3);
/*     */     
/* 311 */     if (par4) {
/* 312 */       l |= 0x4;
/*     */     } else {
/* 314 */       l &= 0xFFFFFFFB;
/*     */     } 
/* 316 */     par0World.func_72921_c(par1, par2, par3, l, 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ChunkCoordinates getNearestEmptyChunkCoordinates(World par0World, int par1, int par2, int par3, int par4) {
/* 324 */     int i1 = par0World.func_72805_g(par1, par2, par3);
/* 325 */     int j1 = BlockDirectional.func_149895_l(i1);
/*     */     
/* 327 */     for (int k1 = 0; k1 <= 1; k1++) {
/*     */       
/* 329 */       int l1 = par1 - FOOT_HEAD_BLOCKMAP[j1][0] * k1 - 1;
/* 330 */       int i2 = par3 - FOOT_HEAD_BLOCKMAP[j1][1] * k1 - 1;
/* 331 */       int j2 = l1 + 2;
/* 332 */       int k2 = i2 + 2;
/*     */       
/* 334 */       for (int l2 = l1; l2 <= j2; l2++) {
/*     */         
/* 336 */         for (int i3 = i2; i3 <= k2; i3++) {
/*     */           
/* 338 */           if (World.func_147466_a((IBlockAccess)par0World, l2, par2 - 1, i3) && !par0World.func_147439_a(l2, par2, i3).func_149688_o().func_76218_k() && !par0World.func_147439_a(l2, par2 + 1, i3).func_149688_o().func_76218_k()) {
/*     */             
/* 340 */             if (par4 <= 0) {
/* 341 */               return new ChunkCoordinates(l2, par2, i3);
/*     */             }
/* 343 */             par4--;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 349 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149690_a(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {
/* 358 */     if (!isBlockHeadOfBed(par5)) {
/* 359 */       super.func_149690_a(par1World, par2, par3, par4, par5, par6, 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
/* 365 */     ArrayList<ItemStack> ret = new ArrayList<>();
/*     */     
/* 367 */     ret.add(new ItemStack(TFCItems.hide, 1, 2));
/* 368 */     ret.add(new ItemStack(TFCBlocks.thatch, 2, 0));
/* 369 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149656_h() {
/* 379 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149681_a(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {
/* 388 */     if (par6EntityPlayer.field_71075_bZ.field_75098_d && isBlockHeadOfBed(par5)) {
/*     */       
/* 390 */       int i1 = func_149895_l(par5);
/* 391 */       par2 -= FOOT_HEAD_BLOCKMAP[i1][0];
/* 392 */       par4 -= FOOT_HEAD_BLOCKMAP[i1][1];
/*     */       
/* 394 */       if (par1World.func_147439_a(par2, par3, par4) == this)
/* 395 */         par1World.func_147468_f(par2, par3, par4); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockBed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */