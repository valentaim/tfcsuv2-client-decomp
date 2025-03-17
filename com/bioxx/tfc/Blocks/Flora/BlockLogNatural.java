/*     */ package com.bioxx.tfc.Blocks.Flora;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.BlockTerra;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.Explosion;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ 
/*     */ public class BlockLogNatural
/*     */   extends BlockTerra
/*     */ {
/*     */   protected String[] woodNames;
/*  31 */   private int searchDist = 10;
/*     */   
/*     */   private static int damage;
/*     */   private static int logs;
/*     */   private boolean isStone;
/*     */   public IIcon[] sideIcons;
/*     */   public IIcon[] innerIcons;
/*     */   public IIcon[] rotatedSideIcons;
/*     */   
/*     */   public BlockLogNatural() {
/*  41 */     super(Material.field_151575_d);
/*  42 */     func_149675_a(true);
/*  43 */     this.woodNames = new String[16];
/*  44 */     System.arraycopy(Global.WOOD_ALL, 0, this.woodNames, 0, 16);
/*  45 */     this.sideIcons = new IIcon[this.woodNames.length];
/*  46 */     this.innerIcons = new IIcon[this.woodNames.length];
/*  47 */     this.rotatedSideIcons = new IIcon[this.woodNames.length];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149674_a(World world, int x, int y, int z, Random rand) {
/*  53 */     if (!world.field_72995_K)
/*     */     {
/*  55 */       if (!world.func_147439_a(x, y - 1, z).func_149662_c())
/*     */       {
/*  57 */         if (noLogsNearby(world, x + 1, y, z) && noLogsNearby(world, x - 1, y, z) && 
/*  58 */           noLogsNearby(world, x, y, z + 1) && noLogsNearby(world, x, y, z - 1) && 
/*  59 */           noLogsNearby(world, x + 1, y, z + 1) && noLogsNearby(world, x + 1, y, z - 1) && 
/*  60 */           noLogsNearby(world, x - 1, y, z + 1) && noLogsNearby(world, x - 1, y, z - 1) && 
/*  61 */           noLogsNearby(world, x + 1, y - 1, z) && noLogsNearby(world, x - 1, y - 1, z) && 
/*  62 */           noLogsNearby(world, x, y - 1, z + 1) && noLogsNearby(world, x, y - 1, z - 1) && 
/*  63 */           noLogsNearby(world, x + 1, y - 1, z + 1) && noLogsNearby(world, x + 1, y - 1, z - 1) && 
/*  64 */           noLogsNearby(world, x - 1, y - 1, z + 1) && noLogsNearby(world, x - 1, y - 1, z - 1)) {
/*  65 */           world.func_147465_d(x, y, z, Blocks.field_150350_a, 0, 2);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean noLogsNearby(World world, int x, int y, int z) {
/*  72 */     return (world.func_72899_e(x, y, z) && world.func_147439_a(x, y, z) != this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
/*  83 */     for (int i = 0; i < this.woodNames.length; i++) {
/*  84 */       list.add(new ItemStack((Block)this, 1, i));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_149712_f(World world, int x, int y, int z) {
/*  90 */     return this.field_149782_v;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean checkOut(World world, int x, int y, int z, int meta) {
/*  95 */     return (world.func_147439_a(x, y, z) == this && world.func_72805_g(x, y, z) == meta);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149692_a(int dmg) {
/* 101 */     return dmg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int side, int meta) {
/* 108 */     if (side == 0 || side == 1)
/* 109 */       return this.innerIcons[meta]; 
/* 110 */     return this.sideIcons[meta];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister reg) {
/* 116 */     for (int i = 0; i < this.woodNames.length; i++) {
/*     */       
/* 118 */       this.sideIcons[i] = reg.func_94245_a("terrafirmacraft:wood/trees/" + this.woodNames[i] + " Log");
/* 119 */       this.innerIcons[i] = reg.func_94245_a("terrafirmacraft:wood/trees/" + this.woodNames[i] + " Log Top");
/* 120 */       this.rotatedSideIcons[i] = reg.func_94245_a("terrafirmacraft:wood/trees/" + this.woodNames[i] + " Log Side");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149636_a(World world, EntityPlayer entityplayer, int x, int y, int z, int meta) {
/* 128 */     boolean isAxe = false;
/* 129 */     boolean isHammer = false;
/* 130 */     ItemStack equip = entityplayer.func_71045_bC();
/* 131 */     if (!world.field_72995_K)
/*     */     {
/* 133 */       if (equip != null) {
/*     */         
/* 135 */         int[] equipIDs = OreDictionary.getOreIDs(equip);
/* 136 */         for (int id : equipIDs) {
/*     */           
/* 138 */           String name = OreDictionary.getOreName(id);
/* 139 */           if (name.startsWith("itemAxe")) {
/*     */             
/* 141 */             isAxe = true;
/* 142 */             if (name.startsWith("itemAxeStone")) {
/*     */               
/* 144 */               this.isStone = true;
/*     */               
/*     */               break;
/*     */             } 
/* 148 */           } else if (name.startsWith("itemHammer")) {
/*     */             
/* 150 */             isHammer = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 155 */         if (isAxe) {
/*     */           
/* 157 */           damage = -1;
/* 158 */           processTree(world, x, y, z, meta, equip);
/*     */           
/* 160 */           if (damage + equip.func_77960_j() > equip.func_77958_k()) {
/*     */             
/* 162 */             int ind = entityplayer.field_71071_by.field_70461_c;
/* 163 */             entityplayer.field_71071_by.func_70299_a(ind, null);
/* 164 */             world.func_147465_d(x, y, z, (Block)this, meta, 2);
/*     */           } else {
/*     */             
/* 167 */             equip.func_77972_a(damage, (EntityLivingBase)entityplayer);
/*     */           } 
/* 169 */           int smallStack = logs % 16;
/* 170 */           func_149642_a(world, x, y, z, new ItemStack(TFCItems.logs, smallStack, func_149692_a(meta)));
/* 171 */           logs -= smallStack;
/*     */ 
/*     */           
/* 174 */           while (logs > 0)
/*     */           {
/* 176 */             func_149642_a(world, x, y, z, new ItemStack(TFCItems.logs, 16, func_149692_a(meta)));
/* 177 */             logs -= 16;
/*     */           }
/*     */         
/*     */         }
/* 181 */         else if (isHammer) {
/*     */           
/* 183 */           EntityItem item = new EntityItem(world, x + 0.5D, y + 0.5D, z + 0.5D, new ItemStack(TFCItems.stick, 1 + world.field_73012_v.nextInt(3)));
/* 184 */           world.func_72838_d((Entity)item);
/*     */         } 
/*     */       } else {
/*     */         
/* 188 */         world.func_147465_d(x, y, z, (Block)this, meta, 2);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_149681_a(World world, int x, int y, int z, int side, EntityPlayer entityplayer) {
/* 195 */     int meta = world.func_72805_g(x, y, z);
/* 196 */     func_149636_a(world, entityplayer, x, y, z, meta);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149718_j(World world, int x, int y, int z) {
/* 202 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
/* 208 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149723_a(World world, int x, int y, int z, Explosion ex) {
/* 214 */     processTree(world, x, y, z, world.func_72805_g(x, y, z), (ItemStack)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   private void processTree(World world, int x, int y, int z, int meta, ItemStack is) {
/* 226 */     boolean[][][] checkArray = new boolean[this.searchDist * 2 + 1][256][this.searchDist * 2 + 1];
/* 227 */     scanLogs(world, x, y, z, meta, checkArray, (byte)0, (byte)0, (byte)0, is);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Item func_149650_a(int i, Random random, int j) {
/* 233 */     return TFCItems.logs;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block block) {
/* 239 */     int meta = world.func_72805_g(x, y, z);
/* 240 */     boolean check = false;
/* 241 */     for (int h = -2; h <= 2; h++) {
/*     */       
/* 243 */       for (int g = -2; g <= 2; g++) {
/*     */         
/* 245 */         for (int f = -2; f <= 2; f++) {
/*     */           
/* 247 */           if (world.func_147439_a(x + h, y + g, z + f) == this && world.func_72805_g(x + h, y + g, z + f) == meta)
/* 248 */             check = true; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 252 */     if (!check) {
/*     */       
/* 254 */       world.func_147468_f(x, y, z);
/* 255 */       func_149642_a(world, x, y, z, new ItemStack(TFCItems.logs, 1, meta));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void scanLogs(World world, int i, int j, int k, int meta, boolean[][][] checkArray, byte x, byte y, byte z, ItemStack stack) {
/* 262 */     if (y >= 0 && j + y < 256) {
/*     */       
/* 264 */       int offsetX = 0, offsetY = 0, offsetZ = 0;
/* 265 */       checkArray[x + this.searchDist][y][z + this.searchDist] = true;
/*     */       
/* 267 */       for (offsetX = -3; offsetX <= 3; offsetX++) {
/*     */         
/* 269 */         for (offsetZ = -3; offsetZ <= 3; offsetZ++) {
/*     */           
/* 271 */           for (offsetY = 0; offsetY <= 2; offsetY++) {
/*     */             
/* 273 */             if (Math.abs(x + offsetX) <= this.searchDist && j + y + offsetY < 256 && Math.abs(z + offsetZ) <= this.searchDist)
/*     */             {
/* 275 */               if (checkOut(world, i + x + offsetX, j + y + offsetY, k + z + offsetZ, meta) && (offsetX != 0 || offsetY != 0 || offsetZ != 0) && !checkArray[x + offsetX + this.searchDist][y + offsetY][z + offsetZ + this.searchDist])
/*     */               {
/*     */                 
/* 278 */                 scanLogs(world, i, j, k, meta, checkArray, (byte)(x + offsetX), (byte)(y + offsetY), (byte)(z + offsetZ), stack);
/*     */               }
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/* 284 */       damage++;
/* 285 */       if (stack != null) {
/*     */         
/* 287 */         if (damage + stack.func_77960_j() <= stack.func_77958_k())
/*     */         {
/* 289 */           world.func_147465_d(i + x, j + y, k + z, Blocks.field_150350_a, 0, 2);
/* 290 */           if (!this.isStone || world.field_73012_v.nextInt(10) != 0)
/* 291 */             logs++; 
/* 292 */           if (logs >= 16) {
/*     */             
/* 294 */             func_149642_a(world, i + x, j + y, k + z, new ItemStack(TFCItems.logs, 16, func_149692_a(meta)));
/* 295 */             logs -= 16;
/*     */           } 
/* 297 */           notifyLeaves(world, i + x, j + y, k + z);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 302 */         world.func_147468_f(i, j, k);
/* 303 */         logs++;
/* 304 */         if (logs >= 16) {
/*     */           
/* 306 */           func_149642_a(world, i, j, k, new ItemStack(TFCItems.logs, 16, func_149692_a(meta)));
/* 307 */           logs -= 16;
/*     */         } 
/* 309 */         notifyLeaves(world, i + x, j + y, k + z);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void notifyLeaves(World world, int x, int y, int z) {
/* 316 */     world.func_147460_e(x + 1, y, z, Blocks.field_150350_a);
/* 317 */     world.func_147460_e(x - 1, y, z, Blocks.field_150350_a);
/* 318 */     world.func_147460_e(x, y, z + 1, Blocks.field_150350_a);
/* 319 */     world.func_147460_e(x, y, z - 1, Blocks.field_150350_a);
/* 320 */     world.func_147460_e(x, y + 1, z, Blocks.field_150350_a);
/* 321 */     world.func_147460_e(x, y - 1, z, Blocks.field_150350_a);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Flora\BlockLogNatural.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */