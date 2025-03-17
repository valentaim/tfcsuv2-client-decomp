/*     */ package com.bioxx.tfc.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.TileEntities.TEIngotPile;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.particle.EffectRenderer;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class BlockIngotPile
/*     */   extends BlockTerraContainer
/*     */ {
/*  29 */   private Random random = new Random();
/*     */ 
/*     */   
/*     */   public BlockIngotPile() {
/*  33 */     super(Material.field_151573_f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister iconRegisterer) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
/*  44 */     if (world.field_72995_K) {
/*     */       
/*  46 */       world.func_147438_o(x, y, z).func_145829_t();
/*  47 */       return true;
/*     */     } 
/*     */ 
/*     */     
/*  51 */     if ((TEIngotPile)world.func_147438_o(x, y, z) != null) {
/*     */ 
/*     */       
/*  54 */       TEIngotPile tileentityingotpile = (TEIngotPile)world.func_147438_o(x, y, z);
/*     */       
/*  56 */       if (tileentityingotpile.func_70301_a(0) == null) {
/*     */         
/*  58 */         world.func_147468_f(x, y, z);
/*  59 */         return false;
/*     */       } 
/*     */       
/*  62 */       if (!entityplayer.func_70093_af() && tileentityingotpile.func_70301_a(0) != null) {
/*     */         
/*  64 */         if ((tileentityingotpile.func_70301_a(0)).field_77994_a > 0) {
/*  65 */           tileentityingotpile.injectContents(0, -1);
/*     */         }
/*  67 */         world.func_72838_d((Entity)new EntityItem(world, tileentityingotpile.field_145851_c, (tileentityingotpile.field_145848_d + 1), tileentityingotpile.field_145849_e, new ItemStack(tileentityingotpile
/*  68 */                 .func_70301_a(0).func_77973_b(), 1, tileentityingotpile.func_70301_a(0).func_77960_j())));
/*  69 */         world.func_147460_e(x, y + 1, z, (Block)this);
/*     */         
/*  71 */         if ((tileentityingotpile.func_70301_a(0)).field_77994_a < 1) {
/*  72 */           world.func_147468_f(x, y, z);
/*     */         }
/*  74 */         world.func_147471_g(x, y, z);
/*     */       } 
/*     */     } 
/*     */     
/*  78 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void combineIngotsDown(World world, int x, int y, int z) {
/*  84 */     TEIngotPile teip = (TEIngotPile)world.func_147438_o(x, y, z);
/*  85 */     TEIngotPile teipBottom = (TEIngotPile)world.func_147438_o(x, y - 1, z);
/*     */     
/*  87 */     int bottomSize = (teipBottom.func_70301_a(0)).field_77994_a;
/*  88 */     int topSize = (teip.func_70301_a(0)).field_77994_a;
/*     */     
/*  90 */     if (bottomSize < 64) {
/*     */       
/*  92 */       bottomSize += topSize;
/*  93 */       int m2 = 0;
/*  94 */       if (bottomSize > 64) {
/*     */         
/*  96 */         m2 = bottomSize - 64;
/*  97 */         bottomSize = 64;
/*     */       } 
/*  99 */       teipBottom.storage[0] = new ItemStack(teipBottom.storage[0].func_77973_b(), bottomSize, teipBottom.storage[0].func_77960_j());
/*     */       
/* 101 */       if (m2 > 0) {
/*     */         
/* 103 */         teip.injectContents(0, m2 - topSize);
/* 104 */         world.func_147460_e(x, y + 1, z, (Block)this);
/* 105 */         world.func_147471_g(teip.field_145851_c, teip.field_145848_d, teip.field_145849_e);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 110 */         teip.storage[0] = null;
/* 111 */         world.func_147468_f(x, y, z);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void combineIngotsUp(World world, int x, int y, int z) {
/* 118 */     TEIngotPile teip = (TEIngotPile)world.func_147438_o(x, y + 1, z);
/* 119 */     TEIngotPile teipBottom = (TEIngotPile)world.func_147438_o(x, y, z);
/*     */     
/* 121 */     int bottomSize = (teipBottom.func_70301_a(0)).field_77994_a;
/* 122 */     int topSize = (teip.func_70301_a(0)).field_77994_a;
/*     */     
/* 124 */     if (bottomSize < 64) {
/*     */       
/* 126 */       bottomSize += topSize;
/* 127 */       int m2 = 0;
/* 128 */       if (bottomSize > 64) {
/*     */         
/* 130 */         m2 = bottomSize - 64;
/* 131 */         bottomSize = 64;
/*     */       } 
/* 133 */       teipBottom.storage[0] = new ItemStack(teipBottom.storage[0].func_77973_b(), bottomSize, teipBottom.storage[0].func_77960_j());
/*     */       
/* 135 */       if (m2 > 0) {
/*     */         
/* 137 */         teip.injectContents(0, m2 - topSize);
/* 138 */         world.func_147460_e(x, y + 2, z, (Block)this);
/* 139 */         world.func_147471_g(teip.field_145851_c, teip.field_145848_d, teip.field_145849_e);
/*     */       }
/*     */       else {
/*     */         
/* 143 */         world.func_147468_f(x, y + 1, z);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
/* 155 */     TEIngotPile te = (TEIngotPile)world.func_147438_o(x, y, z);
/*     */     
/* 157 */     if (te != null && te.func_70301_a(0) != null) {
/* 158 */       return AxisAlignedBB.func_72330_a(x, y + 0.0D, z + 0.0D, x + 1.0D, y + (((te.func_70301_a(0)).field_77994_a + 7) / 8) * 0.125D, z + 1.0D);
/*     */     }
/*     */     
/* 161 */     return AxisAlignedBB.func_72330_a(x, y + 0.0D, z + 0.0D, x + 1.0D, y + 0.25D, z + 1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
/* 169 */     TEIngotPile te = (TEIngotPile)world.func_147438_o(x, y, z);
/*     */     
/* 171 */     if (te.func_70301_a(0) != null) {
/* 172 */       return AxisAlignedBB.func_72330_a(x, y + 0.0D, z + 0.0D, x + 1.0D, y + (((te.func_70301_a(0)).field_77994_a + 7) / 8) * 0.125D, z + 1.0D);
/*     */     }
/* 174 */     return AxisAlignedBB.func_72330_a(x, y + 0.0D, z + 0.0D, x + 1.0D, y + 0.25D, z + 1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149719_a(IBlockAccess bAccess, int x, int y, int z) {
/* 182 */     TEIngotPile te = (TEIngotPile)bAccess.func_147438_o(x, y, z);
/*     */     
/* 184 */     if (te.func_70301_a(0) != null) {
/* 185 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, (float)((((te.func_70301_a(0)).field_77994_a + 7) / 8) * 0.125D), 1.0F);
/*     */     } else {
/* 187 */       func_149676_a(0.0F, 0.0F, 0.0F, 0.0F, 0.25F, 0.0F);
/*     */     } 
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
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
/* 216 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
/* 223 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149645_b() {
/* 229 */     return 22;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStack(World world, TEIngotPile tt) {
/* 234 */     if (world.func_147438_o(tt.field_145851_c, tt.field_145848_d, tt.field_145849_e) instanceof TEIngotPile) {
/*     */       
/* 236 */       TEIngotPile te = (TEIngotPile)world.func_147438_o(tt.field_145851_c, tt.field_145848_d, tt.field_145849_e);
/*     */       
/* 238 */       return (te.func_70301_a(0) != null) ? (te.func_70301_a(0)).field_77994_a : 0;
/*     */     } 
/*     */     
/* 241 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149636_a(World world, EntityPlayer entityplayer, int x, int y, int z, int side) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/* 252 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack is) {
/* 258 */     super.func_149689_a(world, x, y, z, entityliving, is);
/* 259 */     int meta = world.func_72805_g(x, y, z);
/*     */     
/* 261 */     int l = MathHelper.func_76128_c((entityliving.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
/* 262 */     byte byte0 = 0;
/* 263 */     if (l == 0)
/* 264 */       byte0 = 8; 
/* 265 */     if (l == 1)
/* 266 */       byte0 = 0; 
/* 267 */     if (l == 2)
/* 268 */       byte0 = 8; 
/* 269 */     if (l == 3) {
/* 270 */       byte0 = 0;
/*     */     }
/* 272 */     byte0 = (byte)(byte0 + meta);
/* 273 */     world.func_72921_c(x, y, z, byte0, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149749_a(World world, int x, int y, int z, Block b, int meta) {
/* 279 */     TEIngotPile te = (TEIngotPile)world.func_147438_o(x, y, z);
/* 280 */     if (te != null) {
/*     */       
/* 282 */       for (int var6 = 0; var6 < te.func_70302_i_(); var6++) {
/*     */         
/* 284 */         ItemStack var7 = te.func_70301_a(var6);
/*     */         
/* 286 */         if (var7 != null) {
/*     */           
/* 288 */           float var8 = this.random.nextFloat() * 0.8F + 0.1F;
/* 289 */           float var9 = this.random.nextFloat() * 0.8F + 0.1F;
/*     */ 
/*     */           
/* 292 */           for (float var10 = this.random.nextFloat() * 0.8F + 0.1F; var7.field_77994_a > 0; world.func_72838_d((Entity)var12)) {
/*     */             
/* 294 */             int var11 = this.random.nextInt(21) + 10;
/*     */             
/* 296 */             if (var11 > var7.field_77994_a) {
/* 297 */               var11 = var7.field_77994_a;
/*     */             }
/* 299 */             var7.field_77994_a -= var11;
/* 300 */             EntityItem var12 = new EntityItem(world, (x + var8), (y + var9), (z + var10), new ItemStack(var7.func_77973_b(), var11, var7.func_77960_j()));
/* 301 */             float var13 = 0.05F;
/* 302 */             var12.field_70159_w = ((float)this.random.nextGaussian() * var13);
/* 303 */             var12.field_70181_x = ((float)this.random.nextGaussian() * var13 + 0.2F);
/* 304 */             var12.field_70179_y = ((float)this.random.nextGaussian() * var13);
/*     */             
/* 306 */             if (var7.func_77942_o())
/* 307 */               var12.func_92059_d().func_77982_d((NBTTagCompound)var7.func_77978_p().func_74737_b()); 
/*     */           } 
/*     */         } 
/*     */       } 
/* 311 */       super.func_149749_a(world, x, y, z, b, meta);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Item func_149650_a(int metadata, Random rand, int fortune) {
/* 318 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149725_f(World world, int i, int j, int k, int meta) {
/* 324 */     if (!world.field_72995_K) {
/*     */       
/* 326 */       TEIngotPile te = (TEIngotPile)world.func_147438_o(i, j, k);
/* 327 */       if (te != null && te.func_70301_a(0) != null) {
/*     */         
/* 329 */         EntityItem ei = new EntityItem(world, i, j, k, te.func_70301_a(0));
/* 330 */         world.func_72838_d((Entity)ei);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149686_d() {
/* 338 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getAnvilTypeFromMeta(int j) {
/* 343 */     int l = 7;
/* 344 */     return j & l;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDirectionFromMetadata(int i) {
/* 349 */     int d = i >> 3;
/*     */     
/* 351 */     if (d == 1) {
/* 352 */       return 1;
/*     */     }
/* 354 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity func_149915_a(World world, int meta) {
/* 360 */     return (TileEntity)new TEIngotPile();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block block) {
/* 366 */     if (!world.field_72995_K)
/*     */     {
/* 368 */       if (!world.isSideSolid(x, y - 1, z, ForgeDirection.UP) && world.func_147438_o(x, y, z) instanceof TEIngotPile) {
/*     */         
/* 370 */         TEIngotPile ingotPile = (TEIngotPile)world.func_147438_o(x, y, z);
/* 371 */         Item ingot = (ingotPile.storage[0] != null) ? ingotPile.storage[0].func_77973_b() : null;
/*     */         
/* 373 */         if (world.func_147439_a(x, y - 1, z) == this && world.func_147438_o(x, y - 1, z) instanceof TEIngotPile) {
/*     */           
/* 375 */           TEIngotPile lowerPile = (TEIngotPile)world.func_147438_o(x, y - 1, z);
/* 376 */           Item lowerIngot = (lowerPile.storage[0] != null) ? lowerPile.storage[0].func_77973_b() : null;
/*     */           
/* 378 */           if (ingot == lowerIngot) {
/* 379 */             combineIngotsDown(world, x, y, z);
/*     */           }
/* 381 */         } else if (world.func_147439_a(x, y + 1, z) == this && world.func_147438_o(x, y + 1, z) instanceof TEIngotPile) {
/*     */           
/* 383 */           TEIngotPile upperPile = (TEIngotPile)world.func_147438_o(x, y + 1, z);
/* 384 */           Item upperIngot = (upperPile.storage[0] != null) ? upperPile.storage[0].func_77973_b() : null;
/*     */           
/* 386 */           if (ingot == upperIngot) {
/* 387 */             combineIngotsUp(world, x, y, z);
/*     */           }
/*     */         } else {
/*     */           
/* 391 */           ingotPile.ejectContents();
/* 392 */           world.func_147468_f(x, y, z);
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
/* 402 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockIngotPile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */