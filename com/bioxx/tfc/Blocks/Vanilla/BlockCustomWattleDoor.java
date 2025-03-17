/*     */ package com.bioxx.tfc.Blocks.Vanilla;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.BlockTerra;
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
/*     */ public class BlockCustomWattleDoor
/*     */   extends BlockTerra
/*     */ {
/*     */   private int woodType;
/*  31 */   private String[] woodNames = new String[] { "Wattle Door Lower", "Wattle Door Upper" };
/*     */ 
/*     */   
/*  34 */   private IIcon[] icons = new IIcon[2];
/*     */   
/*     */   public BlockCustomWattleDoor(int woodId) {
/*  37 */     super(Material.field_151575_d);
/*  38 */     func_149711_c(1.0F);
/*     */     
/*  40 */     float var3 = 0.5F;
/*  41 */     float var4 = 1.0F;
/*  42 */     func_149676_a(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var4, 0.5F + var3);
/*  43 */     setWoodType(woodId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int par2) {
/*  50 */     return this.icons[getWoodType()];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149673_e(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/*  57 */     if (par5 != 1 && par5 != 0) {
/*     */       
/*  59 */       int meta = getFullMetadata(par1IBlockAccess, par2, par3, par4);
/*  60 */       int rotation = meta & 0x3;
/*  61 */       boolean flag = ((meta & 0x4) != 0);
/*  62 */       boolean flag1 = false;
/*  63 */       boolean flag2 = ((meta & 0x8) != 0);
/*     */       
/*  65 */       if (flag) {
/*     */         
/*  67 */         if (rotation == 0 && par5 == 2) {
/*  68 */           flag1 = !flag1;
/*  69 */         } else if (rotation == 1 && par5 == 5) {
/*  70 */           flag1 = !flag1;
/*  71 */         } else if (rotation == 2 && par5 == 3) {
/*  72 */           flag1 = !flag1;
/*  73 */         } else if (rotation == 3 && par5 == 4) {
/*  74 */           flag1 = !flag1;
/*     */         } 
/*     */       } else {
/*     */         
/*  78 */         if (rotation == 0 && par5 == 5) {
/*  79 */           flag1 = !flag1;
/*  80 */         } else if (rotation == 1 && par5 == 3) {
/*  81 */           flag1 = !flag1;
/*  82 */         } else if (rotation == 2 && par5 == 4) {
/*  83 */           flag1 = !flag1;
/*  84 */         } else if (rotation == 3 && par5 == 2) {
/*  85 */           flag1 = !flag1;
/*     */         } 
/*  87 */         if ((meta & 0x10) != 0) {
/*  88 */           flag1 = !flag1;
/*     */         }
/*     */       } 
/*  91 */       return this.icons[getWoodType() + (flag1 ? this.woodNames.length : 0) + (flag2 ? 1 : 0)];
/*     */     } 
/*     */ 
/*     */     
/*  95 */     return this.icons[getWoodType()];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister registerer) {
/* 102 */     this.icons = new IIcon[this.woodNames.length * 2];
/* 103 */     for (int i = 0; i < this.woodNames.length; i++) {
/*     */       
/* 105 */       this.icons[i] = registerer.func_94245_a("terrafirmacraft:wood/doors/" + this.woodNames[i]);
/* 106 */       this.icons[i + this.woodNames.length] = (IIcon)new IconFlipped(this.icons[i], true, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/* 113 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149655_b(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 119 */     int var5 = getFullMetadata(par1IBlockAccess, par2, par3, par4);
/* 120 */     return ((var5 & 0x4) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149686_d() {
/* 126 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149645_b() {
/* 132 */     return 7;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB func_149633_g(World par1World, int par2, int par3, int par4) {
/* 139 */     func_149719_a((IBlockAccess)par1World, par2, par3, par4);
/* 140 */     return super.func_149633_g(par1World, par2, par3, par4);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
/* 146 */     func_149719_a((IBlockAccess)par1World, par2, par3, par4);
/* 147 */     return super.func_149668_a(par1World, par2, par3, par4);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149719_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 153 */     setDoorRotation(getFullMetadata(par1IBlockAccess, par2, par3, par4));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDoorOrientation(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 161 */     return getFullMetadata(par1IBlockAccess, par2, par3, par4) & 0x3;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDoorOpen(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 166 */     return ((getFullMetadata(par1IBlockAccess, par2, par3, par4) & 0x4) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setDoorRotation(int par1) {
/* 171 */     float var2 = 0.1875F;
/* 172 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
/* 173 */     int var3 = par1 & 0x3;
/* 174 */     boolean var4 = ((par1 & 0x4) != 0);
/* 175 */     boolean var5 = ((par1 & 0x10) != 0);
/*     */     
/* 177 */     if (var3 == 0) {
/*     */       
/* 179 */       if (var4) {
/*     */         
/* 181 */         if (!var5) {
/* 182 */           func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
/*     */         } else {
/* 184 */           func_149676_a(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
/*     */         } 
/*     */       } else {
/*     */         
/* 188 */         func_149676_a(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
/*     */       }
/*     */     
/* 191 */     } else if (var3 == 1) {
/*     */       
/* 193 */       if (var4) {
/*     */         
/* 195 */         if (!var5) {
/* 196 */           func_149676_a(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */         } else {
/* 198 */           func_149676_a(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
/*     */         } 
/*     */       } else {
/*     */         
/* 202 */         func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
/*     */       }
/*     */     
/* 205 */     } else if (var3 == 2) {
/*     */       
/* 207 */       if (var4) {
/*     */         
/* 209 */         if (!var5) {
/* 210 */           func_149676_a(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
/*     */         } else {
/* 212 */           func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
/*     */         } 
/*     */       } else {
/*     */         
/* 216 */         func_149676_a(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */       }
/*     */     
/* 219 */     } else if (var3 == 3) {
/*     */       
/* 221 */       if (var4) {
/*     */         
/* 223 */         if (!var5) {
/* 224 */           func_149676_a(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
/*     */         } else {
/* 226 */           func_149676_a(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */         } 
/*     */       } else {
/*     */         
/* 230 */         func_149676_a(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
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
/* 241 */     if (this.field_149764_J == Material.field_151573_f)
/*     */     {
/* 243 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 247 */     int var10 = getFullMetadata((IBlockAccess)par1World, par2, par3, par4);
/* 248 */     int var11 = var10 & 0x7;
/* 249 */     var11 ^= 0x4;
/*     */     
/* 251 */     if ((var10 & 0x8) == 0) {
/*     */       
/* 253 */       par1World.func_72921_c(par2, par3, par4, var11, 3);
/* 254 */       par1World.func_147458_c(par2, par3, par4, par2, par3, par4);
/*     */     }
/*     */     else {
/*     */       
/* 258 */       par1World.func_72921_c(par2, par3 - 1, par4, var11, 3);
/* 259 */       par1World.func_147458_c(par2, par3 - 1, par4, par2, par3, par4);
/*     */     } 
/*     */     
/* 262 */     par1World.func_72889_a(par5EntityPlayer, 1003, par2, par3, par4, 0);
/* 263 */     return true;
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
/*     */   public void onPoweredBlockChange(World par1World, int par2, int par3, int par4, boolean par5) {}
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
/*     */   public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5) {
/* 300 */     int var6 = par1World.func_72805_g(par2, par3, par4);
/*     */     
/* 302 */     if ((var6 & 0x8) == 0) {
/*     */       
/* 304 */       boolean var7 = false;
/*     */       
/* 306 */       if (par1World.func_147439_a(par2, par3 + 1, par4) != this) {
/*     */         
/* 308 */         par1World.func_147468_f(par2, par3, par4);
/* 309 */         var7 = true;
/*     */       } 
/*     */       
/* 312 */       if (!World.func_147466_a((IBlockAccess)par1World, par2, par3 - 1, par4)) {
/*     */         
/* 314 */         par1World.func_147468_f(par2, par3, par4);
/* 315 */         var7 = true;
/*     */         
/* 317 */         if (par1World.func_147439_a(par2, par3 + 1, par4) == this) {
/* 318 */           par1World.func_147468_f(par2, par3 + 1, par4);
/*     */         }
/*     */       } 
/* 321 */       if (var7) {
/*     */         
/* 323 */         if (!par1World.field_72995_K) {
/* 324 */           func_149697_b(par1World, par2, par3, par4, var6, 0);
/*     */         }
/*     */       } else {
/*     */         
/* 328 */         boolean var8 = (par1World.func_72864_z(par2, par3, par4) || par1World.func_72864_z(par2, par3 + 1, par4));
/* 329 */         if ((var8 || par5.func_149744_f()) && par5 != this) {
/* 330 */           onPoweredBlockChange(par1World, par2, par3, par4, var8);
/*     */         }
/*     */       } 
/*     */     } else {
/*     */       
/* 335 */       if (par1World.func_147439_a(par2, par3 - 1, par4) != this) {
/* 336 */         par1World.func_147468_f(par2, par3, par4);
/*     */       }
/* 338 */       if (par5 != this) {
/* 339 */         func_149695_a(par1World, par2, par3 - 1, par4, par5);
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
/* 350 */     func_149719_a((IBlockAccess)par1World, par2, par3, par4);
/* 351 */     return super.func_149731_a(par1World, par2, par3, par4, par5Vec3, par6Vec3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149742_c(World par1World, int par2, int par3, int par4) {
/* 360 */     return (par3 >= 255) ? false : ((World.func_147466_a((IBlockAccess)par1World, par2, par3 - 1, par4) && super.func_149742_c(par1World, par2, par3, par4) && super.func_149742_c(par1World, par2, par3 + 1, par4)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149656_h() {
/* 370 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFullMetadata(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 378 */     int var7, var8, var5 = par1IBlockAccess.func_72805_g(par2, par3, par4);
/* 379 */     boolean var6 = ((var5 & 0x8) != 0);
/*     */ 
/*     */ 
/*     */     
/* 383 */     if (var6) {
/*     */       
/* 385 */       var7 = par1IBlockAccess.func_72805_g(par2, par3 - 1, par4);
/* 386 */       var8 = var5;
/*     */     }
/*     */     else {
/*     */       
/* 390 */       var7 = var5;
/* 391 */       var8 = par1IBlockAccess.func_72805_g(par2, par3 + 1, par4);
/*     */     } 
/*     */     
/* 394 */     boolean var9 = ((var8 & 0x1) != 0);
/* 395 */     return var7 & 0x7 | (var6 ? 8 : 0) | (var9 ? 16 : 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public Item func_149694_d(World par1World, int par2, int par3, int par4) {
/* 402 */     return (this.field_149764_J == Material.field_151573_f) ? Items.field_151139_aw : Items.field_151135_aq;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
/* 409 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
/* 416 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149643_k(World world, int x, int y, int z) {
/* 425 */     return getWoodType() / 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
/* 434 */     ArrayList<ItemStack> ret = new ArrayList<>();
/*     */ 
/*     */     
/* 437 */     if ((metadata & 0x8) == 0) {
/*     */ 
/*     */ 
/*     */       
/* 441 */       Block block = world.func_147439_a(x, y + 1, z);
/* 442 */       if (block != null && (block instanceof BlockCustomDoor || block == Blocks.field_150350_a))
/*     */       {
/*     */ 
/*     */         
/* 446 */         int damageValue = func_149643_k(world, x, y, z);
/* 447 */         ret.add(new ItemStack(TFCItems.doorWattle, 1, 0));
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 454 */       Block block = world.func_147439_a(x, y - 1, z);
/* 455 */       if (block instanceof BlockCustomDoor) {
/*     */ 
/*     */ 
/*     */         
/* 459 */         int damageValue = func_149643_k(world, x, y, z);
/* 460 */         ret.add(new ItemStack(TFCItems.doorWattle, 1, 0));
/*     */       } 
/*     */     } 
/*     */     
/* 464 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
/* 470 */     int damageValue = func_149643_k(world, x, y, z);
/* 471 */     return new ItemStack(TFCItems.doorWattle, 1, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWoodType() {
/* 476 */     return this.woodType;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setWoodType(int woodType) {
/* 481 */     this.woodType = woodType;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockCustomWattleDoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */