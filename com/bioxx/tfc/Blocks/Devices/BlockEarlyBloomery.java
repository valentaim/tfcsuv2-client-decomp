/*     */ package com.bioxx.tfc.Blocks.Devices;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.BlockTerraContainer;
/*     */ import com.bioxx.tfc.Core.CollisionRayTraceStandard;
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.TileEntities.TEBloomery;
/*     */ import com.bioxx.tfc.api.Interfaces.ICustomCollision;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ public class BlockEarlyBloomery
/*     */   extends BlockTerraContainer
/*     */   implements ICustomCollision
/*     */ {
/*     */   private IIcon textureOn;
/*     */   private IIcon textureOff;
/*  38 */   public static final int[][] BLOOMERY_TO_STACK_MAP = new int[][] { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
/*  39 */   public static final int[][] SIDES_MAP = new int[][] { { 1, 0 }, { 0, 1 }, { 1, 0 }, { 0, 1 } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockEarlyBloomery() {
/*  47 */     super(Material.field_151576_e);
/*  48 */     func_149647_a(TFCTabs.TFC_DEVICES);
/*  49 */     func_149676_a(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLightValue(IBlockAccess world, int x, int y, int z) {
/*  55 */     int meta = world.func_72805_g(x, y, z) & 0x4;
/*  56 */     if (meta == 0) {
/*  57 */       return 0;
/*     */     }
/*  59 */     return 15;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
/*  65 */     if (!func_149718_j(world, x, y, z)) {
/*     */       
/*  67 */       world.func_147468_f(x, y, z);
/*  68 */       world.func_72838_d((Entity)new EntityItem(world, x, y, z, new ItemStack((Block)this, 1)));
/*     */     }
/*  70 */     else if ((TEBloomery)world.func_147438_o(x, y, z) != null) {
/*     */       
/*  72 */       TEBloomery te = (TEBloomery)world.func_147438_o(x, y, z);
/*  73 */       ItemStack is = entityplayer.func_71045_bC();
/*     */       
/*  75 */       if (is != null && (is.func_77973_b() == TFCItems.fireStarter || is.func_77973_b() == TFCItems.flintSteel)) {
/*     */         
/*  77 */         if (te.canLight()) {
/*  78 */           entityplayer.func_71045_bC().func_77972_a(1, (EntityLivingBase)entityplayer);
/*     */         }
/*     */       } else {
/*     */         
/*  82 */         world.func_72889_a(entityplayer, 1003, x, y, z, 0);
/*  83 */         if (isOpen(world.func_72805_g(x, y, z))) {
/*  84 */           world.func_72921_c(x, y, z, world.func_72805_g(x, y, z) - 8, 3);
/*     */         } else {
/*  86 */           world.func_72921_c(x, y, z, world.func_72805_g(x, y, z) + 8, 3);
/*     */         } 
/*     */       } 
/*  89 */     }  return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149718_j(World world, int x, int y, int z) {
/*  95 */     if (world.func_147437_c(x, y, z)) {
/*  96 */       return true;
/*     */     }
/*  98 */     if (world.func_147438_o(x, y, z) instanceof TEBloomery) {
/*     */       
/* 100 */       boolean flipped = false;
/* 101 */       int dir = world.func_72805_g(x, y, z) & 0x3;
/* 102 */       TEBloomery te = (TEBloomery)world.func_147438_o(x, y, z);
/*     */       
/* 104 */       if (te != null) {
/* 105 */         flipped = te.isFlipped;
/*     */       }
/* 107 */       if (checkStack(world, x, y, z, dir))
/*     */       {
/* 109 */         if (checkVertical(world, x, y, z, flipped)) {
/*     */           
/* 111 */           if (checkHorizontal(world, x, y, z, flipped)) {
/* 112 */             return true;
/*     */           }
/* 114 */         } else if (te != null && !flipped) {
/*     */           
/* 116 */           tryFlip(world, x, y, z);
/* 117 */           flipped = te.isFlipped;
/* 118 */           if (checkVertical(world, x, y, z, flipped))
/*     */           {
/* 120 */             if (checkHorizontal(world, x, y, z, flipped))
/* 121 */               return true; 
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/* 126 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean checkStack(World world, int x, int y, int z, int dir) {
/* 131 */     int[] map = BLOOMERY_TO_STACK_MAP[dir];
/* 132 */     int centerX = x + map[0];
/* 133 */     int centerZ = z + map[1];
/* 134 */     if (isNorthStackValid(world, centerX, y, centerZ - 1) || (centerX == x && centerZ - 1 == z))
/*     */     {
/* 136 */       if (isSouthStackValid(world, centerX, y, centerZ + 1) || (centerX == x && centerZ + 1 == z))
/*     */       {
/* 138 */         if (isEastStackValid(world, centerX - 1, y, centerZ) || (centerX - 1 == x && centerZ == z))
/*     */         {
/* 140 */           if (isWestStackValid(world, centerX + 1, y, centerZ) || (centerX + 1 == x && centerZ == z))
/*     */           {
/* 142 */             return true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 147 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isNorthStackValid(World world, int x, int y, int z) {
/* 152 */     return (((world.func_147439_a(x, y, z).func_149688_o() == Material.field_151576_e || world
/* 153 */       .func_147439_a(x, y, z).func_149688_o() == Material.field_151573_f) && world
/* 154 */       .func_147439_a(x, y, z).func_149721_r()) || 
/* 155 */       TFC_Core.isSouthFaceSolid(world, x, y, z));
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isSouthStackValid(World world, int x, int y, int z) {
/* 160 */     return (((world.func_147439_a(x, y, z).func_149688_o() == Material.field_151576_e || world
/* 161 */       .func_147439_a(x, y, z).func_149688_o() == Material.field_151573_f) && world
/* 162 */       .func_147439_a(x, y, z).func_149721_r()) || 
/* 163 */       TFC_Core.isNorthFaceSolid(world, x, y, z));
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isEastStackValid(World world, int x, int y, int z) {
/* 168 */     return (((world.func_147439_a(x, y, z).func_149688_o() == Material.field_151576_e || world
/* 169 */       .func_147439_a(x, y, z).func_149688_o() == Material.field_151573_f) && world
/* 170 */       .func_147439_a(x, y, z).func_149721_r()) || 
/* 171 */       TFC_Core.isWestFaceSolid(world, x, y, z));
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isWestStackValid(World world, int x, int y, int z) {
/* 176 */     return (((world.func_147439_a(x, y, z).func_149688_o() == Material.field_151576_e || world
/* 177 */       .func_147439_a(x, y, z).func_149688_o() == Material.field_151573_f) && world
/* 178 */       .func_147439_a(x, y, z).func_149721_r()) || 
/* 179 */       TFC_Core.isEastFaceSolid(world, x, y, z));
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean checkHorizontal(World world, int x, int y, int z, boolean flip) {
/* 184 */     int dir = world.func_72805_g(x, y, z) & 0x3;
/*     */     
/* 186 */     if (flip) {
/* 187 */       dir = flipDir(dir);
/*     */     }
/* 189 */     int[] map = SIDES_MAP[dir];
/*     */     
/* 191 */     boolean l = false;
/* 192 */     boolean r = false;
/* 193 */     if ((world.func_147439_a(x - map[0], y, z - map[1]).func_149688_o() == Material.field_151576_e || world.func_147439_a(x - map[0], y, z - map[1]).func_149688_o() == Material.field_151573_f) && world.func_147439_a(x - map[0], y, z - map[1]).func_149662_c()) {
/* 194 */       l = true;
/*     */     }
/* 196 */     if ((!l && world.func_147439_a(x - map[0], y, z - map[1]) == TFCBlocks.detailed) || world.func_147439_a(x - map[0], y, z - map[1]) == TFCBlocks.stoneSlabs) {
/*     */       
/* 198 */       switch (dir) {
/*     */         
/*     */         case 0:
/* 201 */           if (TFC_Core.isNorthFaceSolid(world, x - map[0], y, z - map[1]) && TFC_Core.isEastFaceSolid(world, x - map[0], y, z - map[1]))
/* 202 */             l = true; 
/*     */           break;
/*     */         case 1:
/* 205 */           if (TFC_Core.isEastFaceSolid(world, x - map[0], y, z - map[1]) && TFC_Core.isSouthFaceSolid(world, x - map[0], y, z - map[1]))
/* 206 */             l = true; 
/*     */           break;
/*     */         case 2:
/* 209 */           if (TFC_Core.isSouthFaceSolid(world, x - map[0], y, z - map[1]) && TFC_Core.isEastFaceSolid(world, x - map[0], y, z - map[1]))
/* 210 */             l = true; 
/*     */           break;
/*     */         case 3:
/* 213 */           if (TFC_Core.isWestFaceSolid(world, x - map[0], y, z - map[1]) && TFC_Core.isSouthFaceSolid(world, x - map[0], y, z - map[1])) {
/* 214 */             l = true;
/*     */           }
/*     */           break;
/*     */       } 
/* 218 */       if (!TFC_Core.isBottomFaceSolid(world, x - map[0], y, z - map[1]))
/* 219 */         l = false; 
/* 220 */       if (!TFC_Core.isTopFaceSolid(world, x - map[0], y, z - map[1])) {
/* 221 */         l = false;
/*     */       }
/*     */     } 
/* 224 */     if ((world.func_147439_a(x + map[0], y, z + map[1]).func_149688_o() == Material.field_151576_e || world.func_147439_a(x + map[0], y, z + map[1]).func_149688_o() == Material.field_151573_f) && world.func_147439_a(x + map[0], y, z + map[1]).func_149662_c()) {
/* 225 */       r = true;
/*     */     }
/* 227 */     if ((!r && world.func_147439_a(x + map[0], y, z + map[1]) == TFCBlocks.detailed) || world.func_147439_a(x + map[0], y, z + map[1]) == TFCBlocks.stoneSlabs)
/*     */     {
/* 229 */       switch (dir) {
/*     */         
/*     */         case 0:
/* 232 */           if (TFC_Core.isNorthFaceSolid(world, x + map[0], y, z + map[1]) && TFC_Core.isWestFaceSolid(world, x + map[0], y, z + map[1]))
/* 233 */             r = true; 
/*     */           break;
/*     */         case 1:
/* 236 */           if (TFC_Core.isEastFaceSolid(world, x + map[0], y, z + map[1]) && TFC_Core.isNorthFaceSolid(world, x + map[0], y, z + map[1]))
/* 237 */             r = true; 
/*     */           break;
/*     */         case 2:
/* 240 */           if (TFC_Core.isSouthFaceSolid(world, x + map[0], y, z + map[1]) && TFC_Core.isWestFaceSolid(world, x + map[0], y, z + map[1]))
/* 241 */             r = true; 
/*     */           break;
/*     */         case 3:
/* 244 */           if (TFC_Core.isWestFaceSolid(world, x + map[0], y, z + map[1]) && TFC_Core.isNorthFaceSolid(world, x + map[0], y, z + map[1])) {
/* 245 */             r = true;
/*     */           }
/*     */           break;
/*     */       } 
/*     */     }
/* 250 */     if (!TFC_Core.isBottomFaceSolid(world, x + map[0], y, z + map[1]))
/* 251 */       r = false; 
/* 252 */     if (!TFC_Core.isTopFaceSolid(world, x + map[0], y, z + map[1])) {
/* 253 */       r = false;
/*     */     }
/* 255 */     return (l && r);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean checkVertical(World world, int x, int y, int z, boolean flip) {
/* 261 */     int dir = world.func_72805_g(x, y, z) & 0x3;
/*     */     
/* 263 */     if (flip) {
/* 264 */       dir = flipDir(dir);
/*     */     }
/* 266 */     boolean b = false;
/* 267 */     boolean t = false;
/* 268 */     if ((world.func_147439_a(x, y - 1, z).func_149688_o() == Material.field_151576_e || world.func_147439_a(x, y - 1, z).func_149688_o() == Material.field_151573_f) && world.func_147439_a(x, y - 1, z).func_149662_c()) {
/* 269 */       b = true;
/*     */     }
/* 271 */     if ((!b && world.func_147439_a(x, y - 1, z) == TFCBlocks.detailed) || world.func_147439_a(x, y - 1, z) == TFCBlocks.stoneSlabs) {
/*     */       
/* 273 */       switch (dir) {
/*     */         
/*     */         case 0:
/* 276 */           if (TFC_Core.isNorthFaceSolid(world, x, y - 1, z) && TFC_Core.isEastFaceSolid(world, x, y - 1, z) && TFC_Core.isWestFaceSolid(world, x, y - 1, z))
/* 277 */             b = true; 
/*     */           break;
/*     */         case 1:
/* 280 */           if (TFC_Core.isEastFaceSolid(world, x, y - 1, z) && TFC_Core.isNorthFaceSolid(world, x, y - 1, z) && TFC_Core.isSouthFaceSolid(world, x, y - 1, z))
/* 281 */             b = true; 
/*     */           break;
/*     */         case 2:
/* 284 */           if (TFC_Core.isSouthFaceSolid(world, x, y - 1, z) && TFC_Core.isEastFaceSolid(world, x, y - 1, z) && TFC_Core.isWestFaceSolid(world, x, y - 1, z))
/* 285 */             b = true; 
/*     */           break;
/*     */         case 3:
/* 288 */           if (TFC_Core.isWestFaceSolid(world, x, y - 1, z) && TFC_Core.isNorthFaceSolid(world, x, y - 1, z) && TFC_Core.isSouthFaceSolid(world, x, y - 1, z)) {
/* 289 */             b = true;
/*     */           }
/*     */           break;
/*     */       } 
/* 293 */       if (!TFC_Core.isTopFaceSolid(world, x, y - 1, z)) {
/* 294 */         b = false;
/*     */       }
/*     */     } 
/* 297 */     if ((world.func_147439_a(x, y + 1, z).func_149688_o() == Material.field_151576_e || world.func_147439_a(x, y + 1, z).func_149688_o() == Material.field_151573_f) && world.func_147439_a(x, y + 1, z).func_149662_c()) {
/* 298 */       t = true;
/*     */     }
/* 300 */     if ((!t && world.func_147439_a(x, y + 1, z) == TFCBlocks.detailed) || world.func_147439_a(x, y + 1, z) == TFCBlocks.stoneSlabs) {
/*     */       
/* 302 */       switch (dir) {
/*     */         
/*     */         case 0:
/* 305 */           if (TFC_Core.isNorthFaceSolid(world, x, y + 1, z) && TFC_Core.isEastFaceSolid(world, x, y + 1, z) && TFC_Core.isWestFaceSolid(world, x, y + 1, z))
/* 306 */             t = true; 
/*     */           break;
/*     */         case 1:
/* 309 */           if (TFC_Core.isEastFaceSolid(world, x, y + 1, z) && TFC_Core.isNorthFaceSolid(world, x, y + 1, z) && TFC_Core.isSouthFaceSolid(world, x, y + 1, z))
/* 310 */             t = true; 
/*     */           break;
/*     */         case 2:
/* 313 */           if (TFC_Core.isSouthFaceSolid(world, x, y + 1, z) && TFC_Core.isEastFaceSolid(world, x, y + 1, z) && TFC_Core.isWestFaceSolid(world, x, y + 1, z))
/* 314 */             t = true; 
/*     */           break;
/*     */         case 3:
/* 317 */           if (TFC_Core.isWestFaceSolid(world, x, y + 1, z) && TFC_Core.isNorthFaceSolid(world, x, y + 1, z) && TFC_Core.isSouthFaceSolid(world, x, y + 1, z)) {
/* 318 */             t = true;
/*     */           }
/*     */           break;
/*     */       } 
/* 322 */       if (!TFC_Core.isBottomFaceSolid(world, x, y + 1, z) || !TFC_Core.isTopFaceSolid(world, x, y + 1, z)) {
/* 323 */         t = false;
/*     */       }
/*     */     } 
/* 326 */     return (b && t);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149742_c(World world, int x, int y, int z) {
/* 332 */     return func_149718_j(world, x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon func_149691_a(int i, int j) {
/* 338 */     int lit = ((j & 0x4) == 4) ? 1 : 0;
/* 339 */     if (!isOpen(j))
/*     */     {
/* 341 */       if (lit == 1)
/* 342 */         return this.textureOn; 
/*     */     }
/* 344 */     return this.textureOff;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister iconRegisterer) {
/* 350 */     this.textureOn = iconRegisterer.func_94245_a("terrafirmacraft:devices/Bloomery On");
/* 351 */     this.textureOff = iconRegisterer.func_94245_a("terrafirmacraft:devices/Bloomery Off");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack is) {
/* 357 */     if (!world.field_72995_K) {
/*     */       
/* 359 */       int dir = MathHelper.func_76128_c((entityliving.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
/*     */       
/* 361 */       world.func_72921_c(x, y, z, dir, 2);
/* 362 */       if (!func_149718_j(world, x, y, z))
/*     */       {
/* 364 */         func_149642_a(world, x, y, z, new ItemStack((Block)this, 1));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z) {
/* 372 */     clearStack(world, x, y, z);
/* 373 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearStack(World world, int x, int y, int z) {
/* 378 */     if (!world.field_72995_K) {
/*     */       
/* 380 */       world.func_147468_f(x, y, z);
/* 381 */       int meta = world.func_72805_g(x, y, z);
/* 382 */       int[] dir = BLOOMERY_TO_STACK_MAP[meta & 0x3];
/* 383 */       if (world.func_147439_a(x + dir[0], y, z + dir[1]) == TFCBlocks.molten)
/* 384 */         world.func_147468_f(x + dir[0], y, z + dir[1]); 
/* 385 */       if (world.func_147439_a(x + dir[0], y + 1, z + dir[1]) == TFCBlocks.molten)
/* 386 */         world.func_147468_f(x + dir[0], y + 1, z + dir[1]); 
/* 387 */       if (world.func_147439_a(x + dir[0], y + 2, z + dir[1]) == TFCBlocks.molten)
/* 388 */         world.func_147468_f(x + dir[0], y + 2, z + dir[1]); 
/* 389 */       if (world.func_147439_a(x + dir[0], y + 3, z + dir[1]) == TFCBlocks.molten) {
/* 390 */         world.func_147468_f(x + dir[0], y + 3, z + dir[1]);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block block) {
/* 397 */     if (!func_149718_j(world, x, y, z))
/*     */     {
/* 399 */       if (!tryFlip(world, x, y, z)) {
/*     */         
/* 401 */         world.func_147468_f(x, y, z);
/* 402 */         func_149642_a(world, x, y, z, new ItemStack((Block)this, 1));
/*     */       }
/* 404 */       else if (!func_149718_j(world, x, y, z)) {
/*     */         
/* 406 */         world.func_147468_f(x, y, z);
/* 407 */         func_149642_a(world, x, y, z, new ItemStack((Block)this, 1));
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static int flipDir(int dir) {
/* 413 */     int out = 0;
/* 414 */     if (dir - 2 >= 0) {
/* 415 */       out = dir - 2;
/* 416 */     } else if (dir + 2 < 4) {
/* 417 */       out = dir + 2;
/* 418 */     }  return out;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean tryFlip(World world, int x, int y, int z) {
/* 423 */     TEBloomery te = (TEBloomery)world.func_147438_o(x, y, z);
/* 424 */     te.swapFlipped();
/* 425 */     return func_149718_j(world, x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity func_149915_a(World world, int meta) {
/* 431 */     return (TileEntity)new TEBloomery();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
/* 468 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isOpen(int par0) {
/* 473 */     return ((par0 & 0x8) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149645_b() {
/* 479 */     return TFCBlocks.bloomeryRenderId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/* 485 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149686_d() {
/* 491 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCollisionBoxesToList(World world, int x, int y, int z, List<Object[]> list) {
/* 498 */     int meta = world.func_72805_g(x, y, z);
/* 499 */     int dir = meta & 0x3;
/* 500 */     if (world.func_147438_o(x, y, z) instanceof TEBloomery) {
/*     */       
/* 502 */       TEBloomery te = (TEBloomery)world.func_147438_o(x, y, z);
/* 503 */       if (te.isFlipped)
/* 504 */         dir = flipDir(dir); 
/*     */     } 
/* 506 */     float f = 0.125F;
/*     */     
/* 508 */     if (!isOpen(meta)) {
/*     */       
/* 510 */       if (dir == 0) {
/* 511 */         list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, f) });
/* 512 */       } else if (dir == 1) {
/* 513 */         list.add(new Object[] { AxisAlignedBB.func_72330_a((1.0F - f), 0.0D, 0.0D, 1.0D, 1.0D, 1.0D) });
/* 514 */       } else if (dir == 2) {
/* 515 */         list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.0D, (1.0F - f), 1.0D, 1.0D, 1.0D) });
/* 516 */       } else if (dir == 3) {
/* 517 */         list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.0D, f, 1.0D, 1.0D) });
/*     */       }
/*     */     
/*     */     }
/* 521 */     else if (dir == 0) {
/*     */       
/* 523 */       list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.0D, f, 1.0D, 0.5D) });
/* 524 */       list.add(new Object[] { AxisAlignedBB.func_72330_a((1.0F - f), 0.0D, 0.0D, 1.0D, 1.0D, 0.5D) });
/*     */     }
/* 526 */     else if (dir == 1) {
/*     */       
/* 528 */       list.add(new Object[] { AxisAlignedBB.func_72330_a(0.5D, 0.0D, 0.0D, 1.0D, 1.0D, f) });
/* 529 */       list.add(new Object[] { AxisAlignedBB.func_72330_a(0.5D, 0.0D, (1.0F - f), 1.0D, 1.0D, 1.0D) });
/*     */     }
/* 531 */     else if (dir == 2) {
/*     */       
/* 533 */       list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.5D, f, 1.0D, 1.0D) });
/* 534 */       list.add(new Object[] { AxisAlignedBB.func_72330_a((1.0F - f), 0.0D, 0.5D, 1.0D, 1.0D, 1.0D) });
/*     */     }
/* 536 */     else if (dir == 3) {
/*     */       
/* 538 */       list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.0D, 0.5D, 1.0D, f) });
/* 539 */       list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.0D, (1.0F - f), 0.5D, 1.0D, 1.0D) });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MovingObjectPosition func_149731_a(World world, int x, int y, int z, Vec3 player, Vec3 view) {
/* 547 */     return CollisionRayTraceStandard.collisionRayTrace(this, world, x, y, z, player, view);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess bAccess, int x, int y, int z, int side) {
/* 554 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_149642_a(World world, int x, int y, int z, ItemStack is) {
/* 560 */     if (!world.field_72995_K && world.func_82736_K().func_82766_b("doTileDrops")) {
/*     */       
/* 562 */       clearStack(world, x, y, z);
/* 563 */       EntityItem ei = new EntityItem(world, x + 0.5D, y + 0.5D, z + 0.5D, is);
/* 564 */       ei.field_70159_w = 0.0D;
/* 565 */       ei.field_70181_x = 0.0D;
/* 566 */       ei.field_70179_y = 0.0D;
/* 567 */       ei.field_145804_b = 10;
/* 568 */       world.func_72838_d((Entity)ei);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockEarlyBloomery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */