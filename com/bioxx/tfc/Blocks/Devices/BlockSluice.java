/*     */ package com.bioxx.tfc.Blocks.Devices;
/*     */ 
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.TileEntities.TESluice;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCFluids;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockContainer;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ public class BlockSluice
/*     */   extends BlockContainer
/*     */ {
/*  31 */   public static final int[][] HEAD_FOOT_BLOCKMAP = new int[][] { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
/*     */ 
/*     */   
/*     */   public BlockSluice() {
/*  35 */     super(Material.field_151575_d);
/*  36 */     this.field_149789_z = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side) {
/*  45 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/*  51 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
/*  57 */     int meta = world.func_72805_g(i, j, k);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  64 */     if (world.field_72995_K) {
/*  65 */       return true;
/*     */     }
/*     */     
/*  68 */     if (!isBlockFootOfBed(meta) && 
/*  69 */       (TESluice)world.func_147438_o(i, j, k) != null) {
/*     */ 
/*     */       
/*  72 */       TESluice tileentitysluice = (TESluice)world.func_147438_o(i, j, k);
/*  73 */       ItemStack is = entityplayer.func_71045_bC();
/*  74 */       if (is != null && is.func_77973_b() == TFCItems.goldPan && is.func_77960_j() != 0) {
/*     */         
/*  76 */         tileentitysluice.soilAmount += 7;
/*  77 */         tileentitysluice.soilType = (byte)is.func_77960_j();
/*  78 */         if (tileentitysluice.soilAmount > 50)
/*  79 */           tileentitysluice.soilAmount = 50; 
/*  80 */         is.func_77964_b(0);
/*  81 */         entityplayer.field_71071_by.func_70299_a(entityplayer.field_71071_by.field_70461_c, is);
/*     */ 
/*     */ 
/*     */         
/*  85 */         return true;
/*     */       } 
/*  87 */       entityplayer.openGui(TerraFirmaCraft.instance, 25, world, i, j, k);
/*     */     } 
/*     */     
/*  90 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon func_149691_a(int side, int meta) {
/*  97 */     if ((meta & 0x4) != 0 && side == 1) {
/*  98 */       return TFCFluids.SALTWATER.getFlowingIcon();
/*     */     }
/* 100 */     return TFCBlocks.woodSupportH.func_149691_a(side, 8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_149720_d(IBlockAccess world, int x, int y, int z) {
/* 111 */     if ((world.func_72805_g(x, y, z) & 0x4) == 0) {
/* 112 */       return 16777215;
/*     */     }
/* 114 */     return 3493173;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDirectionFromMetadata(int i) {
/* 119 */     return i & 0x3;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isBlockFootOfBed(int i) {
/* 124 */     return ((i & 0x8) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getIsRecievingWater(int i) {
/* 129 */     return ((i & 0x4) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getItemNameIS(ItemStack itemstack) {
/* 134 */     return "Sluice";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149645_b() {
/* 140 */     return TFCBlocks.sluiceRenderId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Item func_149650_a(int i, Random random, int j) {
/* 146 */     if (!isBlockFootOfBed(i)) {
/* 147 */       return TFCItems.sluiceItem;
/*     */     }
/* 149 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack is) {
/* 155 */     int l = MathHelper.func_76128_c((entityliving.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
/* 156 */     byte byte0 = 0;
/* 157 */     byte byte1 = 0;
/* 158 */     if (l == 0)
/* 159 */       byte1 = 1; 
/* 160 */     if (l == 1)
/* 161 */       byte0 = -1; 
/* 162 */     if (l == 2)
/* 163 */       byte1 = -1; 
/* 164 */     if (l == 3)
/* 165 */       byte0 = 1; 
/* 166 */     world.func_72921_c(i, j, k, l, 3);
/* 167 */     if (world.func_147439_a(i, j, k) == this) {
/* 168 */       world.func_147465_d(i + byte0, j, k + byte1, (Block)this, l + 8, 3);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
/* 177 */     int meta = par1IBlockAccess.func_72805_g(i, j, k);
/* 178 */     if (isBlockFootOfBed(meta)) {
/* 179 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
/*     */     } else {
/* 181 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
/* 187 */     int meta = world.func_72805_g(i, j, k);
/* 188 */     if (isBlockFootOfBed(meta))
/* 189 */       return AxisAlignedBB.func_72330_a(i, j, k, (i + 1), (j + 0.5F), (k + 1)); 
/* 190 */     return AxisAlignedBB.func_72330_a(i, j, k, (i + 1), (j + 1), (k + 1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149742_c(World world, int x, int y, int z) {
/* 196 */     Block block = world.func_147439_a(x, y, z);
/*     */     
/* 198 */     int dir = getDirectionFromMetadata(world.func_72805_g(x, y, z));
/* 199 */     int[] offset = HEAD_FOOT_BLOCKMAP[dir];
/*     */ 
/*     */     
/* 202 */     boolean stay = (canStay(world, x, y, z, false, dir) && canStay(world, x + offset[0], y, z + offset[1], true, dir) && (block.isAir((IBlockAccess)world, x, y, z) || block.func_149688_o().func_76222_j()));
/*     */     
/* 204 */     return stay;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(World world, int i, int j, int k, int dir) {
/* 209 */     int[] offset = HEAD_FOOT_BLOCKMAP[dir];
/* 210 */     Block topBlock = world.func_147439_a(i, j, k);
/* 211 */     Block footBlock = world.func_147439_a(i + offset[0], j, k + offset[1]);
/*     */ 
/*     */     
/* 214 */     boolean stay = (canStay(world, i, j, k, false, dir) && canStay(world, i + offset[0], j, k + offset[1], true, dir) && (topBlock.isAir((IBlockAccess)world, i, j, k) || topBlock.func_149688_o().func_76222_j()) && (footBlock.isAir((IBlockAccess)world, i + offset[0], j, k + offset[1]) || footBlock.func_149688_o().func_76222_j()));
/*     */     
/* 216 */     return stay;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean canStay(World world, int i, int j, int k, boolean foot, int dir) {
/* 221 */     int l = dir;
/* 222 */     if (l == 0) {
/* 223 */       if (!foot && (
/* 224 */         !world.func_147439_a(i + 1, j, k).func_149721_r() || 
/* 225 */         !world.func_147439_a(i - 1, j, k).func_149721_r() || 
/* 226 */         !world.func_147439_a(i, j, k - 1).func_149721_r() || 
/* 227 */         !world.func_147439_a(i, j - 1, k).func_149721_r() || world
/* 228 */         .func_147439_a(i, j + 2, k).func_149721_r()))
/* 229 */         return false; 
/* 230 */       if (foot && (
/* 231 */         !world.func_147439_a(i + 1, j, k).func_149721_r() || 
/* 232 */         !world.func_147439_a(i - 1, j, k).func_149721_r() || 
/* 233 */         !world.func_147439_a(i, j - 1, k).func_149721_r() || world
/* 234 */         .func_147439_a(i, j + 2, k).func_149721_r()))
/* 235 */         return false; 
/* 236 */     }  if (l == 1) {
/* 237 */       if (!foot && (
/* 238 */         !world.func_147439_a(i, j, k + 1).func_149721_r() || 
/* 239 */         !world.func_147439_a(i, j, k - 1).func_149721_r() || 
/* 240 */         !world.func_147439_a(i + 1, j, k).func_149721_r() || 
/* 241 */         !world.func_147439_a(i, j - 1, k).func_149721_r() || world
/* 242 */         .func_147439_a(i, j + 2, k).func_149721_r()))
/* 243 */         return false; 
/* 244 */       if (foot && (
/* 245 */         !world.func_147439_a(i, j, k + 1).func_149721_r() || 
/* 246 */         !world.func_147439_a(i, j, k - 1).func_149721_r() || 
/* 247 */         !world.func_147439_a(i, j - 1, k).func_149721_r() || world
/* 248 */         .func_147439_a(i, j + 2, k).func_149721_r()))
/* 249 */         return false; 
/* 250 */     }  if (l == 2) {
/* 251 */       if (!foot && (
/* 252 */         !world.func_147439_a(i + 1, j, k).func_149721_r() || 
/* 253 */         !world.func_147439_a(i - 1, j, k).func_149721_r() || 
/* 254 */         !world.func_147439_a(i, j, k + 1).func_149721_r() || 
/* 255 */         !world.func_147439_a(i, j - 1, k).func_149721_r() || world
/* 256 */         .func_147439_a(i, j + 2, k).func_149721_r()))
/* 257 */         return false; 
/* 258 */       if (foot && (
/* 259 */         !world.func_147439_a(i + 1, j, k).func_149721_r() || 
/* 260 */         !world.func_147439_a(i - 1, j, k).func_149721_r() || 
/* 261 */         !world.func_147439_a(i, j - 1, k).func_149721_r() || world
/* 262 */         .func_147439_a(i, j + 2, k).func_149721_r()))
/* 263 */         return false; 
/* 264 */     }  if (l == 3) {
/* 265 */       if (!foot && (
/* 266 */         !world.func_147439_a(i, j, k + 1).func_149721_r() || 
/* 267 */         !world.func_147439_a(i, j, k - 1).func_149721_r() || 
/* 268 */         !world.func_147439_a(i - 1, j, k).func_149721_r() || 
/* 269 */         !world.func_147439_a(i, j - 1, k).func_149721_r() || world
/* 270 */         .func_147439_a(i, j + 2, k).func_149721_r()))
/* 271 */         return false; 
/* 272 */       if (foot && (
/* 273 */         !world.func_147439_a(i, j, k + 1).func_149721_r() || 
/* 274 */         !world.func_147439_a(i, j, k - 1).func_149721_r() || 
/* 275 */         !world.func_147439_a(i, j - 1, k).func_149721_r() || world
/* 276 */         .func_147439_a(i, j + 2, k).func_149721_r()))
/* 277 */         return false; 
/* 278 */     }  return true;
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
/*     */   public void func_149695_a(World world, int i, int j, int k, Block block) {
/* 294 */     int i1 = world.func_72805_g(i, j, k);
/* 295 */     int j1 = getDirectionFromMetadata(i1);
/*     */     
/* 297 */     if (isBlockFootOfBed(i1)) {
/*     */       
/* 299 */       if (world.func_147439_a(i - HEAD_FOOT_BLOCKMAP[j1][0], j, k - HEAD_FOOT_BLOCKMAP[j1][1]) != this || !canStay(world, i, j, k, true, j1)) {
/* 300 */         world.func_147468_f(i, j, k);
/*     */       }
/* 302 */     } else if (world.func_147439_a(i + HEAD_FOOT_BLOCKMAP[j1][0], j, k + HEAD_FOOT_BLOCKMAP[j1][1]) != this || !canStay(world, i, j, k, false, j1)) {
/*     */       
/* 304 */       world.func_147468_f(i, j, k);
/* 305 */       if (!world.field_72995_K) {
/* 306 */         func_149697_b(world, i, j, k, i1, 0);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean func_149686_d() {
/* 312 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity func_149915_a(World var1, int var2) {
/* 318 */     return (TileEntity)new TESluice();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister iconRegisterer) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
/* 329 */     return new ItemStack(TFCItems.sluiceItem);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockSluice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */