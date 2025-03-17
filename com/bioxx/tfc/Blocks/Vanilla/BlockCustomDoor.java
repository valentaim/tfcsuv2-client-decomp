/*     */ package com.bioxx.tfc.Blocks.Vanilla;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.BlockTerra;
/*     */ import com.bioxx.tfc.Core.Recipes;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.particle.EffectRenderer;
/*     */ import net.minecraft.client.renderer.IconFlipped;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockCustomDoor
/*     */   extends BlockTerra
/*     */ {
/*     */   private int woodType;
/*  34 */   private String[] woodNames = new String[] { "Oak Door Lower", "Oak Door Upper", "Aspen Door Lower", "Aspen Door Upper", "Birch Door Lower", "Birch Door Upper", "Chestnut Door Lower", "Chestnut Door Upper", "Douglas Fir Door Lower", "Douglas Fir Door Upper", "Hickory Door Lower", "Hickory Door Upper", "Maple Door Lower", "Maple Door Upper", "Ash Door Lower", "Ash Door Upper", "Pine Door Lower", "Pine Door Upper", "Sequoia Door Lower", "Sequoia Door Upper", "Spruce Door Lower", "Spruce Door Upper", "Sycamore Door Lower", "Sycamore Door Upper", "White Cedar Door Lower", "White Cedar Door Upper", "White Elm Door Lower", "White Elm Door Upper", "Willow Door Lower", "Willow Door Upper", "Kapok Door Lower", "Kapok Door Upper", "Acacia Door Lower", "Acacia Door Upper" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   private IIcon[] icons = new IIcon[Global.WOOD_ALL.length * 2];
/*     */   
/*     */   public BlockCustomDoor(int woodId) {
/*  45 */     super(Material.field_151575_d);
/*  46 */     func_149711_c(3.0F);
/*     */     
/*  48 */     float var3 = 0.5F;
/*  49 */     float var4 = 1.0F;
/*  50 */     func_149676_a(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var4, 0.5F + var3);
/*  51 */     setWoodType(woodId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int par2) {
/*  58 */     return this.icons[getWoodType()];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149673_e(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/*  65 */     if (par5 != 1 && par5 != 0) {
/*     */       
/*  67 */       int meta = getFullMetadata(par1IBlockAccess, par2, par3, par4);
/*  68 */       int rotation = meta & 0x3;
/*  69 */       boolean flag = ((meta & 0x4) != 0);
/*  70 */       boolean flag1 = false;
/*  71 */       boolean flag2 = ((meta & 0x8) != 0);
/*     */       
/*  73 */       if (flag) {
/*     */         
/*  75 */         if (rotation == 0 && par5 == 2) {
/*  76 */           flag1 = !flag1;
/*  77 */         } else if (rotation == 1 && par5 == 5) {
/*  78 */           flag1 = !flag1;
/*  79 */         } else if (rotation == 2 && par5 == 3) {
/*  80 */           flag1 = !flag1;
/*  81 */         } else if (rotation == 3 && par5 == 4) {
/*  82 */           flag1 = !flag1;
/*     */         } 
/*     */       } else {
/*     */         
/*  86 */         if (rotation == 0 && par5 == 5) {
/*  87 */           flag1 = !flag1;
/*  88 */         } else if (rotation == 1 && par5 == 3) {
/*  89 */           flag1 = !flag1;
/*  90 */         } else if (rotation == 2 && par5 == 4) {
/*  91 */           flag1 = !flag1;
/*  92 */         } else if (rotation == 3 && par5 == 2) {
/*  93 */           flag1 = !flag1;
/*     */         } 
/*  95 */         if ((meta & 0x10) != 0) {
/*  96 */           flag1 = !flag1;
/*     */         }
/*     */       } 
/*  99 */       return this.icons[getWoodType() + (flag1 ? this.woodNames.length : 0) + (flag2 ? 1 : 0)];
/*     */     } 
/*     */ 
/*     */     
/* 103 */     return this.icons[getWoodType()];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister registerer) {
/* 110 */     this.icons = new IIcon[this.woodNames.length * 2];
/* 111 */     for (int i = 0; i < this.woodNames.length; i++) {
/*     */       
/* 113 */       this.icons[i] = registerer.func_94245_a("terrafirmacraft:wood/doors/" + this.woodNames[i]);
/* 114 */       this.icons[i + this.woodNames.length] = (IIcon)new IconFlipped(this.icons[i], true, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/* 121 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149655_b(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 127 */     int var5 = getFullMetadata(par1IBlockAccess, par2, par3, par4);
/* 128 */     return ((var5 & 0x4) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149686_d() {
/* 134 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149645_b() {
/* 140 */     return 7;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB func_149633_g(World par1World, int par2, int par3, int par4) {
/* 147 */     func_149719_a((IBlockAccess)par1World, par2, par3, par4);
/* 148 */     return super.func_149633_g(par1World, par2, par3, par4);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
/* 154 */     func_149719_a((IBlockAccess)par1World, par2, par3, par4);
/* 155 */     return super.func_149668_a(par1World, par2, par3, par4);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149719_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 161 */     setDoorRotation(getFullMetadata(par1IBlockAccess, par2, par3, par4));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDoorOrientation(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 169 */     return getFullMetadata(par1IBlockAccess, par2, par3, par4) & 0x3;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDoorOpen(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 174 */     return ((getFullMetadata(par1IBlockAccess, par2, par3, par4) & 0x4) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setDoorRotation(int par1) {
/* 179 */     float var2 = 0.1875F;
/* 180 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
/* 181 */     int var3 = par1 & 0x3;
/* 182 */     boolean var4 = ((par1 & 0x4) != 0);
/* 183 */     boolean var5 = ((par1 & 0x10) != 0);
/*     */     
/* 185 */     if (var3 == 0) {
/*     */       
/* 187 */       if (var4) {
/*     */         
/* 189 */         if (!var5) {
/* 190 */           func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
/*     */         } else {
/* 192 */           func_149676_a(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
/*     */         } 
/*     */       } else {
/*     */         
/* 196 */         func_149676_a(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
/*     */       }
/*     */     
/* 199 */     } else if (var3 == 1) {
/*     */       
/* 201 */       if (var4) {
/*     */         
/* 203 */         if (!var5) {
/* 204 */           func_149676_a(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */         } else {
/* 206 */           func_149676_a(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
/*     */         } 
/*     */       } else {
/*     */         
/* 210 */         func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
/*     */       }
/*     */     
/* 213 */     } else if (var3 == 2) {
/*     */       
/* 215 */       if (var4) {
/*     */         
/* 217 */         if (!var5) {
/* 218 */           func_149676_a(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
/*     */         } else {
/* 220 */           func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
/*     */         } 
/*     */       } else {
/*     */         
/* 224 */         func_149676_a(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */       }
/*     */     
/* 227 */     } else if (var3 == 3) {
/*     */       
/* 229 */       if (var4) {
/*     */         
/* 231 */         if (!var5) {
/* 232 */           func_149676_a(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
/*     */         } else {
/* 234 */           func_149676_a(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */         } 
/*     */       } else {
/*     */         
/* 238 */         func_149676_a(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149699_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) {}
/*     */ 
/*     */   
/*     */   public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
/* 249 */     if (this.field_149764_J == Material.field_151573_f)
/*     */     {
/* 251 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 255 */     int var10 = getFullMetadata((IBlockAccess)par1World, par2, par3, par4);
/* 256 */     int var11 = var10 & 0x7;
/* 257 */     var11 ^= 0x4;
/*     */     
/* 259 */     if ((var10 & 0x8) == 0) {
/*     */       
/* 261 */       par1World.func_72921_c(par2, par3, par4, var11, 3);
/* 262 */       par1World.func_147458_c(par2, par3, par4, par2, par3, par4);
/*     */     }
/*     */     else {
/*     */       
/* 266 */       par1World.func_72921_c(par2, par3 - 1, par4, var11, 3);
/* 267 */       par1World.func_147458_c(par2, par3 - 1, par4, par2, par3, par4);
/*     */     } 
/*     */     
/* 270 */     par1World.func_72889_a(par5EntityPlayer, 1003, par2, par3, par4, 0);
/* 271 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onPoweredBlockChange(World par1World, int par2, int par3, int par4, boolean par5) {
/* 277 */     int var6 = getFullMetadata((IBlockAccess)par1World, par2, par3, par4);
/* 278 */     boolean var7 = ((var6 & 0x4) != 0);
/*     */     
/* 280 */     if (var7 != par5) {
/*     */       
/* 282 */       int var8 = var6 & 0x7;
/* 283 */       var8 ^= 0x4;
/*     */       
/* 285 */       if ((var6 & 0x8) == 0) {
/*     */         
/* 287 */         par1World.func_72921_c(par2, par3, par4, var8, 3);
/* 288 */         par1World.func_147458_c(par2, par3, par4, par2, par3, par4);
/*     */       }
/*     */       else {
/*     */         
/* 292 */         par1World.func_72921_c(par2, par3 - 1, par4, var8, 3);
/* 293 */         par1World.func_147458_c(par2, par3 - 1, par4, par2, par3, par4);
/*     */       } 
/*     */       
/* 296 */       par1World.func_72889_a((EntityPlayer)null, 1003, par2, par3, par4, 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5) {
/* 307 */     int var6 = par1World.func_72805_g(par2, par3, par4);
/*     */     
/* 309 */     if ((var6 & 0x8) == 0) {
/*     */       
/* 311 */       boolean var7 = false;
/*     */       
/* 313 */       if (par1World.func_147439_a(par2, par3 + 1, par4) != this) {
/*     */         
/* 315 */         par1World.func_147468_f(par2, par3, par4);
/* 316 */         var7 = true;
/*     */       } 
/*     */       
/* 319 */       if (!World.func_147466_a((IBlockAccess)par1World, par2, par3 - 1, par4)) {
/*     */         
/* 321 */         par1World.func_147468_f(par2, par3, par4);
/* 322 */         var7 = true;
/*     */         
/* 324 */         if (par1World.func_147439_a(par2, par3 + 1, par4) == this) {
/* 325 */           par1World.func_147468_f(par2, par3 + 1, par4);
/*     */         }
/*     */       } 
/* 328 */       if (var7) {
/*     */         
/* 330 */         if (!par1World.field_72995_K) {
/* 331 */           func_149697_b(par1World, par2, par3, par4, var6, 0);
/*     */         }
/*     */       } else {
/*     */         
/* 335 */         boolean var8 = (par1World.func_72864_z(par2, par3, par4) || par1World.func_72864_z(par2, par3 + 1, par4));
/* 336 */         if ((var8 || par5.func_149744_f()) && par5 != this) {
/* 337 */           onPoweredBlockChange(par1World, par2, par3, par4, var8);
/*     */         }
/*     */       } 
/*     */     } else {
/*     */       
/* 342 */       if (par1World.func_147439_a(par2, par3 - 1, par4) != this) {
/* 343 */         par1World.func_147468_f(par2, par3, par4);
/*     */       }
/* 345 */       if (par5 != this) {
/* 346 */         func_149695_a(par1World, par2, par3 - 1, par4, par5);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MovingObjectPosition func_149731_a(World par1World, int par2, int par3, int par4, Vec3 par5Vec3, Vec3 par6Vec3) {
/* 357 */     func_149719_a((IBlockAccess)par1World, par2, par3, par4);
/* 358 */     return super.func_149731_a(par1World, par2, par3, par4, par5Vec3, par6Vec3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149742_c(World par1World, int par2, int par3, int par4) {
/* 367 */     return (par3 >= 255) ? false : ((World.func_147466_a((IBlockAccess)par1World, par2, par3 - 1, par4) && super.func_149742_c(par1World, par2, par3, par4) && super.func_149742_c(par1World, par2, par3 + 1, par4)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149656_h() {
/* 377 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFullMetadata(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 385 */     int var7, var8, var5 = par1IBlockAccess.func_72805_g(par2, par3, par4);
/* 386 */     boolean var6 = ((var5 & 0x8) != 0);
/*     */ 
/*     */ 
/*     */     
/* 390 */     if (var6) {
/*     */       
/* 392 */       var7 = par1IBlockAccess.func_72805_g(par2, par3 - 1, par4);
/* 393 */       var8 = var5;
/*     */     }
/*     */     else {
/*     */       
/* 397 */       var7 = var5;
/* 398 */       var8 = par1IBlockAccess.func_72805_g(par2, par3 + 1, par4);
/*     */     } 
/*     */     
/* 401 */     boolean var9 = ((var8 & 0x1) != 0);
/* 402 */     return var7 & 0x7 | (var6 ? 8 : 0) | (var9 ? 16 : 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public Item func_149694_d(World par1World, int par2, int par3, int par4) {
/* 409 */     return (this.field_149764_J == Material.field_151573_f) ? Items.field_151139_aw : Items.field_151135_aq;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
/* 416 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
/* 423 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149643_k(World world, int x, int y, int z) {
/* 432 */     return getWoodType() / 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
/* 441 */     ArrayList<ItemStack> ret = new ArrayList<>();
/*     */ 
/*     */     
/* 444 */     if (this == TFCBlocks.wattleDoor) {
/*     */ 
/*     */       
/* 447 */       if ((metadata & 0x8) == 0)
/*     */       {
/*     */ 
/*     */         
/* 451 */         Block block = world.func_147439_a(x, y + 1, z);
/* 452 */         if (block != null && (block instanceof BlockCustomDoor || block == Blocks.field_150350_a))
/*     */         {
/*     */ 
/*     */           
/* 456 */           int damageValue = func_149643_k(world, x, y, z);
/* 457 */           ret.add(new ItemStack(TFCItems.doorWattle, 1, 0));
/*     */         
/*     */         }
/*     */       
/*     */       }
/*     */       else
/*     */       {
/* 464 */         Block block = world.func_147439_a(x, y - 1, z);
/* 465 */         if (block instanceof BlockCustomDoor)
/*     */         {
/*     */ 
/*     */           
/* 469 */           int damageValue = func_149643_k(world, x, y, z);
/* 470 */           ret.add(new ItemStack(TFCItems.doorWattle, 1, 0));
/*     */         
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 477 */     else if ((metadata & 0x8) == 0) {
/*     */ 
/*     */ 
/*     */       
/* 481 */       Block block = world.func_147439_a(x, y + 1, z);
/* 482 */       if (block != null && (block instanceof BlockCustomDoor || block == Blocks.field_150350_a))
/*     */       {
/*     */ 
/*     */         
/* 486 */         int damageValue = func_149643_k(world, x, y, z);
/* 487 */         ret.add(new ItemStack(Recipes.doors[damageValue], 1, 0));
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 494 */       Block block = world.func_147439_a(x, y - 1, z);
/* 495 */       if (block instanceof BlockCustomDoor) {
/*     */ 
/*     */ 
/*     */         
/* 499 */         int damageValue = func_149643_k(world, x, y, z);
/* 500 */         ret.add(new ItemStack(Recipes.doors[damageValue], 1, 0));
/*     */       } 
/*     */     } 
/*     */     
/* 504 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
/* 510 */     int damageValue = func_149643_k(world, x, y, z);
/*     */     
/* 512 */     if (this == TFCBlocks.wattleDoor)
/*     */     {
/* 514 */       return new ItemStack(TFCItems.doorWattle, 1, 0);
/*     */     }
/*     */     
/* 517 */     return new ItemStack(Recipes.doors[damageValue], 1, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWoodType() {
/* 522 */     return this.woodType;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setWoodType(int woodType) {
/* 527 */     this.woodType = woodType;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockCustomDoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */