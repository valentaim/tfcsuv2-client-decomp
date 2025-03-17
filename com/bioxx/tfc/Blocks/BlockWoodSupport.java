/*     */ package com.bioxx.tfc.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockWoodSupport
/*     */   extends BlockTerra
/*     */ {
/*     */   protected String[] woodNames;
/*     */   protected IIcon[] icons;
/*     */   
/*     */   public BlockWoodSupport(Material material) {
/*  34 */     super(Material.field_151575_d);
/*  35 */     func_149647_a(TFCTabs.TFC_BUILDING);
/*  36 */     this.woodNames = new String[16];
/*  37 */     System.arraycopy(Global.WOOD_ALL, 0, this.woodNames, 0, 16);
/*  38 */     this.icons = new IIcon[this.woodNames.length];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
/*  46 */     if (TFCBlocks.isBlockVSupport(this))
/*     */     {
/*  48 */       for (int i = 0; i < this.woodNames.length; i++) {
/*  49 */         list.add(new ItemStack(this, 1, i));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean hasSupportsInRange(World world, int x, int y, int z, int range) {
/*  55 */     return (getSupportsInRangeDir(world, x, y, z, range, false) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSupportConnected(World world, int x, int y, int z) {
/*  60 */     return (getSupportsInRangeDir(world, x, y, z, 5, true) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public static ForgeDirection getSupportDirection(World world, int x, int y, int z) {
/*  65 */     int[] r = getSupportsInRangeDir(world, x, y, z, 5, false);
/*  66 */     if (r != null) {
/*     */       
/*  68 */       if (r[2] > r[3])
/*  69 */         return ForgeDirection.NORTH; 
/*  70 */       if (r[3] > r[2])
/*  71 */         return ForgeDirection.SOUTH; 
/*  72 */       if (r[5] > r[4])
/*  73 */         return ForgeDirection.EAST; 
/*  74 */       if (r[4] > r[5]) {
/*  75 */         return ForgeDirection.WEST;
/*     */       }
/*     */     } 
/*  78 */     return ForgeDirection.UNKNOWN;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDistanceFromDirection(ForgeDirection dir, int[] dist) {
/*  83 */     switch (dir) {
/*     */       case NORTH:
/*  85 */         return dist[2];
/*  86 */       case SOUTH: return dist[3];
/*  87 */       case WEST: return dist[4];
/*  88 */       case EAST: return dist[5];
/*  89 */     }  return Integer.MAX_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int[] getSupportsInRangeDir(World world, int x, int y, int z, int range, boolean checkConnection) {
/*  96 */     int n = 0; boolean foundNV = false, foundNH = true;
/*  97 */     int s = 0; boolean foundSV = false, foundSH = true;
/*  98 */     int e = 0; boolean foundEV = false, foundEH = true;
/*  99 */     int w = 0; boolean foundWV = false, foundWH = true;
/* 100 */     boolean clearNorthPath = true;
/* 101 */     boolean clearSouthPath = true;
/* 102 */     boolean clearEastPath = true;
/* 103 */     boolean clearWestPath = true;
/*     */     
/* 105 */     for (int i = 1; i <= range; i++) {
/*     */       
/* 107 */       if (!foundEV) {
/*     */         
/* 109 */         if (!checkConnection) {
/*     */           
/* 111 */           if (world.func_147437_c(x + i, y, z) || TFCBlocks.isBlockVSupport(world.func_147439_a(x + i, y, z))) {
/* 112 */             e++;
/*     */           } else {
/* 114 */             clearEastPath = false;
/*     */           } 
/* 116 */         } else if (checkConnection && !TFCBlocks.isBlockHSupport(world.func_147439_a(x + i, y, z)) && !TFCBlocks.isBlockVSupport(world.func_147439_a(x + i, y, z))) {
/* 117 */           foundEH = false;
/*     */         } else {
/* 119 */           e++;
/* 120 */         }  if (clearEastPath && TFCBlocks.isBlockVSupport(world.func_147439_a(x + i, y, z)) && (e >= 0 || i == 1))
/*     */         {
/* 122 */           if (scanVert(world, x + i, y, z)) {
/* 123 */             foundEV = true;
/*     */           } else {
/* 125 */             e -= 50;
/*     */           }  } 
/*     */       } 
/* 128 */       if (!foundWV) {
/*     */         
/* 130 */         if (!checkConnection) {
/*     */           
/* 132 */           if (world.func_147437_c(x - i, y, z) || TFCBlocks.isBlockVSupport(world.func_147439_a(x - i, y, z))) {
/* 133 */             w++;
/*     */           } else {
/* 135 */             clearWestPath = false;
/*     */           } 
/* 137 */         } else if (checkConnection && !TFCBlocks.isBlockHSupport(world.func_147439_a(x - i, y, z)) && !TFCBlocks.isBlockVSupport(world.func_147439_a(x - i, y, z))) {
/* 138 */           foundWH = false;
/*     */         } else {
/* 140 */           w++;
/* 141 */         }  if (clearWestPath && TFCBlocks.isBlockVSupport(world.func_147439_a(x - i, y, z)) && (w >= 0 || i == 1))
/*     */         {
/* 143 */           if (scanVert(world, x - i, y, z)) {
/* 144 */             foundWV = true;
/*     */           } else {
/* 146 */             w -= 50;
/*     */           }  } 
/*     */       } 
/* 149 */       if (!foundSV) {
/*     */         
/* 151 */         if (!checkConnection) {
/*     */           
/* 153 */           if (world.func_147437_c(x, y, z + i) || TFCBlocks.isBlockVSupport(world.func_147439_a(x, y, z + i))) {
/* 154 */             s++;
/*     */           } else {
/* 156 */             clearSouthPath = false;
/*     */           }
/*     */         
/* 159 */         } else if (checkConnection && !TFCBlocks.isBlockHSupport(world.func_147439_a(x, y, z + i)) && !TFCBlocks.isBlockVSupport(world.func_147439_a(x, y, z + i))) {
/* 160 */           foundSH = false;
/*     */         } else {
/* 162 */           s++;
/* 163 */         }  if (clearSouthPath && TFCBlocks.isBlockVSupport(world.func_147439_a(x, y, z + i)) && (s >= 0 || i == 1))
/*     */         {
/* 165 */           if (scanVert(world, x, y, z + i))
/* 166 */             foundSV = true; 
/*     */         }
/*     */       } 
/* 169 */       if (!foundNV) {
/*     */         
/* 171 */         if (!checkConnection) {
/*     */           
/* 173 */           if (world.func_147437_c(x, y, z - i) || TFCBlocks.isBlockVSupport(world.func_147439_a(x, y, z - i))) {
/* 174 */             n++;
/*     */           } else {
/* 176 */             clearNorthPath = false;
/*     */           } 
/* 178 */         } else if (checkConnection && !TFCBlocks.isBlockHSupport(world.func_147439_a(x, y, z - i)) && !TFCBlocks.isBlockVSupport(world.func_147439_a(x, y, z - i))) {
/* 179 */           foundNH = false;
/*     */         } else {
/* 181 */           n++;
/* 182 */         }  if (clearNorthPath && TFCBlocks.isBlockVSupport(world.func_147439_a(x, y, z - i)) && (n >= 0 || i == 1))
/*     */         {
/* 184 */           if (scanVert(world, x, y, z - i)) {
/* 185 */             foundNV = true;
/*     */           }
/*     */         }
/*     */       } 
/*     */     } 
/* 190 */     if (foundEV && foundEH && foundWV && foundWH)
/* 191 */       return new int[] { 0, 0, 0, 0, w, e }; 
/* 192 */     if (foundSV && foundSH && foundNV && foundNH)
/* 193 */       return new int[] { 0, 0, n, s, 0, 0 }; 
/* 194 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean scanVert(World world, int x, int y, int z) {
/* 199 */     int out = 1;
/* 200 */     while (TFCBlocks.isBlockVSupport(world.func_147439_a(x, y - out, z))) {
/* 201 */       out++;
/*     */     }
/* 203 */     return (out > 2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149655_b(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 209 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int isNextToSupport(World world, int x, int y, int z) {
/* 214 */     if (TFCBlocks.isBlockVSupport(world.func_147439_a(x + 1, y, z)) || TFCBlocks.isBlockHSupport(world.func_147439_a(x + 1, y, z)))
/* 215 */       return 5; 
/* 216 */     if (TFCBlocks.isBlockVSupport(world.func_147439_a(x - 1, y, z)) || TFCBlocks.isBlockHSupport(world.func_147439_a(x - 1, y, z)))
/* 217 */       return 4; 
/* 218 */     if (TFCBlocks.isBlockVSupport(world.func_147439_a(x, y, z + 1)) || TFCBlocks.isBlockHSupport(world.func_147439_a(x, y, z + 1)))
/* 219 */       return 3; 
/* 220 */     if (TFCBlocks.isBlockVSupport(world.func_147439_a(x, y, z - 1)) || TFCBlocks.isBlockHSupport(world.func_147439_a(x, y, z - 1)))
/* 221 */       return 2; 
/* 222 */     return 0;
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
/*     */   public int func_149692_a(int j) {
/* 244 */     return j;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
/* 250 */     ArrayList<ItemStack> ret = new ArrayList<>();
/* 251 */     Block b = world.func_147439_a(x, y, z);
/* 252 */     if (b == TFCBlocks.woodSupportH || b == TFCBlocks.woodSupportV) {
/* 253 */       ret.add(new ItemStack(TFCBlocks.woodSupportV, 1, metadata));
/* 254 */     } else if (b == TFCBlocks.woodSupportH2 || b == TFCBlocks.woodSupportV2) {
/* 255 */       ret.add(new ItemStack(TFCBlocks.woodSupportV2, 1, metadata));
/* 256 */     }  return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon func_149691_a(int side, int meta) {
/* 262 */     if (meta < 0)
/* 263 */       return this.icons[0]; 
/* 264 */     if (meta < this.icons.length)
/* 265 */       return this.icons[meta]; 
/* 266 */     return TFCBlocks.woodSupportH2.func_149691_a(side, meta - 16);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister registerer) {
/* 272 */     for (int i = 0; i < this.woodNames.length; i++) {
/* 273 */       this.icons[i] = registerer.func_94245_a("terrafirmacraft:wood/WoodSheet/" + this.woodNames[i]);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
/* 279 */     return getCollisionBoundingBoxFromPoolIBlockAccess((IBlockAccess)world, x, y, z).func_72325_c(x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   private AxisAlignedBB getCollisionBoundingBoxFromPoolIBlockAccess(IBlockAccess blockAccess, int x, int y, int z) {
/* 284 */     Boolean isHorizontal = Boolean.valueOf(TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(x, y, z)));
/*     */ 
/*     */     
/* 287 */     double minX = 0.25D, minY = 0.0D, minZ = 0.25D;
/* 288 */     double maxX = 0.75D, maxY = 0.75D, maxZ = 0.75D;
/*     */     
/* 290 */     if (isHorizontal.booleanValue()) {
/*     */       
/* 292 */       minY = 0.5D;
/* 293 */       maxY = 1.0D;
/* 294 */       if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(x + 1, y, z)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(x + 1, y, z)))
/* 295 */         maxX = 1.0D; 
/* 296 */       if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(x - 1, y, z)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(x - 1, y, z)))
/* 297 */         minX = 0.0D; 
/* 298 */       if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(x, y, z + 1)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(x, y, z + 1)))
/* 299 */         maxZ = 1.0D; 
/* 300 */       if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(x, y, z - 1)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(x, y, z - 1))) {
/* 301 */         minZ = 0.0D;
/*     */       
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 307 */       minY = 0.0D;
/* 308 */       maxY = 1.0D;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 319 */     return AxisAlignedBB.func_72330_a(minX, minY, minZ, maxX, maxY, maxZ);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149719_a(IBlockAccess blockAccess, int x, int y, int z) {
/* 325 */     AxisAlignedBB aabb = getCollisionBoundingBoxFromPoolIBlockAccess(blockAccess, x, y, z);
/* 326 */     func_149676_a((float)aabb.field_72340_a, (float)aabb.field_72338_b, (float)aabb.field_72339_c, (float)aabb.field_72336_d, (float)aabb.field_72337_e, (float)aabb.field_72334_f);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149645_b() {
/* 332 */     if (TFCBlocks.isBlockVSupport(this)) {
/* 333 */       return TFCBlocks.woodSupportRenderIdV;
/*     */     }
/* 335 */     return TFCBlocks.woodSupportRenderIdH;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
/* 341 */     Boolean isHorizontal = Boolean.valueOf(TFCBlocks.isBlockHSupport(world.func_147439_a(x, y, z)));
/*     */ 
/*     */     
/* 344 */     double minX = 0.25D, minY = 0.0D, minZ = 0.25D;
/* 345 */     double maxX = 0.75D, maxY = 0.75D, maxZ = 0.75D;
/*     */ 
/*     */     
/* 348 */     if (isHorizontal.booleanValue()) {
/*     */       
/* 350 */       minY = 0.5D;
/* 351 */       maxY = 1.0D;
/* 352 */       if (TFCBlocks.isBlockVSupport(world.func_147439_a(x + 1, y, z)) || TFCBlocks.isBlockHSupport(world.func_147439_a(x + 1, y, z)))
/* 353 */         maxX = 1.0D; 
/* 354 */       if (TFCBlocks.isBlockVSupport(world.func_147439_a(x - 1, y, z)) || TFCBlocks.isBlockHSupport(world.func_147439_a(x - 1, y, z)))
/* 355 */         minX = 0.0D; 
/* 356 */       if (TFCBlocks.isBlockVSupport(world.func_147439_a(x, y, z + 1)) || TFCBlocks.isBlockHSupport(world.func_147439_a(x, y, z + 1)))
/* 357 */         maxZ = 1.0D; 
/* 358 */       if (TFCBlocks.isBlockVSupport(world.func_147439_a(x, y, z - 1)) || TFCBlocks.isBlockHSupport(world.func_147439_a(x, y, z - 1)))
/* 359 */         minZ = 0.0D; 
/* 360 */       if (TFCBlocks.isBlockVSupport(world.func_147439_a(x, y - 1, z))) {
/* 361 */         minY = 0.0D;
/*     */       }
/*     */     } else {
/*     */       
/* 365 */       minY = 0.0D;
/* 366 */       maxY = 1.0D;
/* 367 */       if (TFCBlocks.isBlockVSupport(world.func_147439_a(x + 1, y, z)) || TFCBlocks.isBlockHSupport(world.func_147439_a(x + 1, y, z)))
/* 368 */         maxX = 1.0D; 
/* 369 */       if (TFCBlocks.isBlockVSupport(world.func_147439_a(x - 1, y, z)) || TFCBlocks.isBlockHSupport(world.func_147439_a(x - 1, y, z)))
/* 370 */         minX = 0.0D; 
/* 371 */       if (TFCBlocks.isBlockVSupport(world.func_147439_a(x, y, z + 1)) || TFCBlocks.isBlockHSupport(world.func_147439_a(x, y, z + 1)))
/* 372 */         maxZ = 1.0D; 
/* 373 */       if (TFCBlocks.isBlockVSupport(world.func_147439_a(x, y, z - 1)) || TFCBlocks.isBlockHSupport(world.func_147439_a(x, y, z - 1))) {
/* 374 */         minZ = 0.0D;
/*     */       }
/*     */     } 
/* 377 */     return AxisAlignedBB.func_72330_a(x + minX, y + minY, z + minZ, x + maxX, y + maxY, z + maxZ);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149636_a(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {
/* 383 */     if (this == TFCBlocks.woodSupportH) {
/* 384 */       func_149642_a(world, i, j, k, new ItemStack(TFCBlocks.woodSupportV, 1, l));
/* 385 */     } else if (this == TFCBlocks.woodSupportH2) {
/* 386 */       func_149642_a(world, i, j, k, new ItemStack(TFCBlocks.woodSupportV2, 1, l));
/*     */     } else {
/* 388 */       func_149642_a(world, i, j, k, new ItemStack(this, 1, l));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_149637_q() {
/* 394 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/* 400 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149686_d() {
/* 406 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5) {
/* 413 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entity, ItemStack is) {
/* 419 */     super.func_149689_a(world, i, j, k, entity, is);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149695_a(World world, int i, int j, int k, Block l) {
/* 427 */     boolean isOtherHorizontal = TFCBlocks.isBlockHSupport(l);
/*     */     
/* 429 */     boolean isHorizontal = TFCBlocks.isBlockHSupport(world.func_147439_a(i, j, k));
/* 430 */     boolean isVertical = TFCBlocks.isBlockVSupport(world.func_147439_a(i, j, k));
/*     */     
/* 432 */     int meta = world.func_72805_g(i, j, k);
/*     */     
/* 434 */     if (isVertical && !isOtherHorizontal) {
/*     */ 
/*     */       
/* 437 */       if (!world.func_147439_a(i, j - 1, k).func_149662_c() && !TFCBlocks.isBlockVSupport(world.func_147439_a(i, j - 1, k)))
/*     */       {
/* 439 */         func_149636_a(world, (EntityPlayer)null, i, j, k, meta);
/* 440 */         world.func_147468_f(i, j, k);
/*     */       }
/*     */     
/* 443 */     } else if (isHorizontal) {
/*     */       
/* 445 */       boolean b1 = !isSupportConnected(world, i, j, k);
/* 446 */       if (b1) {
/*     */         
/* 448 */         func_149636_a(world, (EntityPlayer)null, i, j, k, meta);
/* 449 */         world.func_147468_f(i, j, k);
/*     */       }
/* 451 */       else if (TFCBlocks.isBlockVSupport(world.func_147439_a(i, j - 1, k))) {
/*     */         
/* 453 */         if (this == TFCBlocks.woodSupportH) {
/* 454 */           world.func_147465_d(i, j, k, TFCBlocks.woodSupportV, meta, 2);
/* 455 */         } else if (this == TFCBlocks.woodSupportH2) {
/* 456 */           world.func_147465_d(i, j, k, TFCBlocks.woodSupportV2, meta, 2);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_149707_d(World world, int x, int y, int z, int side) {
/* 464 */     Block downBlock = world.func_147439_a(x, y - 1, z);
/*     */ 
/*     */     
/* 467 */     if (!TFCBlocks.isBlockVSupport(downBlock)) {
/*     */       
/* 469 */       if (side == 0 && world.func_147437_c(x, y - 1, z))
/*     */       {
/* 471 */         return true;
/*     */       }
/* 473 */       if (side == 1 && downBlock.func_149662_c())
/*     */       {
/* 475 */         return true;
/*     */       }
/* 477 */       if (side == 2) {
/*     */         
/* 479 */         if (isNextToSupport(world, x, y, z) != 0 && hasSupportsInRange(world, x, y, z, 5)) {
/* 480 */           return true;
/*     */         }
/* 482 */       } else if (side == 3) {
/*     */         
/* 484 */         if (isNextToSupport(world, x, y, z) != 0 && hasSupportsInRange(world, x, y, z, 5)) {
/* 485 */           return true;
/*     */         }
/* 487 */       } else if (side == 4) {
/*     */         
/* 489 */         if (isNextToSupport(world, x, y, z) != 0 && hasSupportsInRange(world, x, y, z, 5)) {
/* 490 */           return true;
/*     */         }
/* 492 */       } else if (side == 5) {
/*     */         
/* 494 */         if (isNextToSupport(world, x, y, z) != 0 && hasSupportsInRange(world, x, y, z, 5)) {
/* 495 */           return true;
/*     */         }
/*     */       } 
/* 498 */     } else if (TFCBlocks.isBlockVSupport(downBlock) || downBlock.func_149662_c()) {
/*     */       
/* 500 */       if (side == 1 && world.func_147437_c(x, y, z))
/* 501 */         return true; 
/* 502 */       if (side == 2 && (TFCBlocks.isBlockVSupport(world.func_147439_a(x, y, z - 1)) || world.func_147439_a(x, y, z - 1).func_149662_c()) && world.func_147437_c(x, y, z - 1))
/* 503 */         return true; 
/* 504 */       if (side == 3 && (TFCBlocks.isBlockVSupport(world.func_147439_a(x, y, z + 1)) || world.func_147439_a(x, y, z + 1).func_149662_c()) && world.func_147437_c(x, y, z + 1))
/* 505 */         return true; 
/* 506 */       if (side == 4 && (TFCBlocks.isBlockVSupport(world.func_147439_a(x - 1, y, z)) || world.func_147439_a(x - 1, y, z).func_149662_c()) && world.func_147437_c(x - 1, y, z))
/* 507 */         return true; 
/* 508 */       if (side == 5 && (TFCBlocks.isBlockVSupport(world.func_147439_a(x + 1, y, z)) || world.func_147439_a(x + 1, y, z).func_149662_c()) && world.func_147437_c(x + 1, y, z)) {
/* 509 */         return true;
/*     */       }
/*     */     } 
/* 512 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockWoodSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */