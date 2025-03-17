/*     */ package com.bioxx.tfc.Blocks.Vanilla;
/*     */ 
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockLeaves;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.IShearable;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockCustomLeaves
/*     */   extends BlockLeaves
/*     */   implements IShearable
/*     */ {
/*     */   protected int[][][] adjacentTreeBlocks;
/*     */   protected String[] woodNames;
/*     */   protected IIcon[] icons;
/*     */   protected IIcon[] iconsOpaque;
/*     */   
/*     */   public BlockCustomLeaves() {
/*  44 */     func_149675_a(false);
/*  45 */     this.woodNames = new String[16];
/*  46 */     System.arraycopy(Global.WOOD_ALL, 0, this.woodNames, 0, 16);
/*  47 */     this.icons = new IIcon[16];
/*  48 */     this.iconsOpaque = new IIcon[16];
/*  49 */     func_149675_a(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149666_a(Item item, CreativeTabs tabs, List list) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149655_b(IBlockAccess bAccess, int x, int y, int z) {
/*  62 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149720_d(IBlockAccess bAccess, int x, int y, int z) {
/*  68 */     return TerraFirmaCraft.proxy.foliageColorMultiplier(bAccess, x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149743_a(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_, List<AxisAlignedBB> p_149743_6_, Entity p_149743_7_) {
/*  75 */     if (p_149743_7_ instanceof EntityPlayer || p_149743_7_ instanceof net.minecraft.entity.passive.EntityCow || p_149743_7_ instanceof net.minecraft.entity.passive.EntityHorse || (p_149743_7_ != null && p_149743_7_
/*  76 */       .getClass().getSimpleName().toLowerCase().contains("ostri")))
/*  77 */       return;  AxisAlignedBB axisalignedbb1 = func_149668_a(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_);
/*     */     
/*  79 */     if (axisalignedbb1 != null && p_149743_5_.func_72326_a(axisalignedbb1))
/*     */     {
/*  81 */       p_149743_6_.add(axisalignedbb1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149670_a(World world, int x, int y, int z, Entity entity) {
/*  88 */     entity.field_70159_w *= 0.1D;
/*  89 */     entity.field_70179_y *= 0.1D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side) {
/*  96 */     Block block = world.func_147439_a(x, y, z);
/*     */ 
/*     */     
/*  99 */     if (side == 0 && this.field_149760_C > 0.0D)
/* 100 */       return true; 
/* 101 */     if (side == 1 && this.field_149756_F < 1.0D)
/* 102 */       return true; 
/* 103 */     if (side == 2 && this.field_149754_D > 0.0D)
/* 104 */       return true; 
/* 105 */     if (side == 3 && this.field_149757_G < 1.0D)
/* 106 */       return true; 
/* 107 */     if (side == 4 && this.field_149759_B > 0.0D)
/* 108 */       return true; 
/* 109 */     if (side == 5 && this.field_149755_E < 1.0D) {
/* 110 */       return true;
/*     */     }
/* 112 */     return !block.func_149662_c();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149674_a(World world, int x, int y, int z, Random rand) {
/* 118 */     func_149695_a(world, x, y, z, (Block)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void beginLeavesDecay(World world, int x, int y, int z) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149695_a(World world, int xOrig, int yOrig, int zOrig, Block b) {
/* 130 */     if (!world.field_72995_K) {
/*     */       
/* 132 */       int var6 = world.func_72805_g(xOrig, yOrig, zOrig);
/*     */       
/* 134 */       byte searchRadius = 4;
/* 135 */       int maxDist = searchRadius + 1;
/* 136 */       byte searchDistance = 11;
/* 137 */       int center = searchDistance / 2;
/* 138 */       this.adjacentTreeBlocks = (int[][][])null;
/* 139 */       if (this.adjacentTreeBlocks == null) {
/* 140 */         this.adjacentTreeBlocks = new int[searchDistance][searchDistance][searchDistance];
/*     */       }
/* 142 */       if (world.func_72904_c(xOrig - maxDist, yOrig - maxDist, zOrig - maxDist, xOrig + maxDist, yOrig + maxDist, zOrig + maxDist)) {
/*     */         
/* 144 */         for (int xd = -searchRadius; xd <= searchRadius; xd++) {
/*     */           
/* 146 */           int searchY = searchRadius - Math.abs(xd);
/* 147 */           for (int yd = -searchY; yd <= searchY; yd++) {
/*     */             
/* 149 */             int searchZ = searchY - Math.abs(yd);
/* 150 */             for (int zd = -searchZ; zd <= searchZ; zd++) {
/*     */               
/* 152 */               Block block = world.func_147439_a(xOrig + xd, yOrig + yd, zOrig + zd);
/*     */               
/* 154 */               if (block == TFCBlocks.logNatural || block == TFCBlocks.logNatural2) {
/* 155 */                 this.adjacentTreeBlocks[xd + center][yd + center][zd + center] = 0;
/* 156 */               } else if (block == this && var6 == world.func_72805_g(xOrig + xd, yOrig + yd, zOrig + zd)) {
/* 157 */                 this.adjacentTreeBlocks[xd + center][yd + center][zd + center] = -2;
/*     */               } else {
/* 159 */                 this.adjacentTreeBlocks[xd + center][yd + center][zd + center] = -1;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/* 164 */         for (int pass = 1; pass <= 4; pass++) {
/*     */           
/* 166 */           for (int i = -searchRadius; i <= searchRadius; i++) {
/*     */             
/* 168 */             int searchY = searchRadius - Math.abs(i);
/* 169 */             for (int yd = -searchY; yd <= searchY; yd++) {
/*     */               
/* 171 */               int searchZ = searchY - Math.abs(yd);
/* 172 */               for (int zd = -searchZ; zd <= searchZ; zd++) {
/*     */                 
/* 174 */                 if (this.adjacentTreeBlocks[i + center][yd + center][zd + center] == pass - 1) {
/*     */                   
/* 176 */                   if (this.adjacentTreeBlocks[i + center - 1][yd + center][zd + center] == -2) {
/* 177 */                     this.adjacentTreeBlocks[i + center - 1][yd + center][zd + center] = pass;
/*     */                   }
/* 179 */                   if (this.adjacentTreeBlocks[i + center + 1][yd + center][zd + center] == -2) {
/* 180 */                     this.adjacentTreeBlocks[i + center + 1][yd + center][zd + center] = pass;
/*     */                   }
/* 182 */                   if (this.adjacentTreeBlocks[i + center][yd + center - 1][zd + center] == -2) {
/* 183 */                     this.adjacentTreeBlocks[i + center][yd + center - 1][zd + center] = pass;
/*     */                   }
/* 185 */                   if (this.adjacentTreeBlocks[i + center][yd + center + 1][zd + center] == -2) {
/* 186 */                     this.adjacentTreeBlocks[i + center][yd + center + 1][zd + center] = pass;
/*     */                   }
/* 188 */                   if (this.adjacentTreeBlocks[i + center][yd + center][zd + center - 1] == -2) {
/* 189 */                     this.adjacentTreeBlocks[i + center][yd + center][zd + center - 1] = pass;
/*     */                   }
/* 191 */                   if (this.adjacentTreeBlocks[i + center][yd + center][zd + center + 1] == -2) {
/* 192 */                     this.adjacentTreeBlocks[i + center][yd + center][zd + center + 1] = pass;
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 200 */       int res = this.adjacentTreeBlocks[center][center][center];
/*     */       
/* 202 */       if (res < 0)
/*     */       {
/* 204 */         if (world.func_72938_d(xOrig, zOrig) != null) {
/* 205 */           destroyLeaves(world, xOrig, yOrig, zOrig);
/*     */         }
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void destroyLeaves(World world, int x, int y, int z) {
/* 212 */     world.func_147468_f(x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   private void removeLeaves(World world, int x, int y, int z) {
/* 217 */     func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
/* 218 */     if (world.field_73012_v.nextInt(100) < 30)
/* 219 */       func_149642_a(world, x, y, z, new ItemStack(TFCItems.stick, 1)); 
/* 220 */     world.func_147468_f(x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149745_a(Random rand) {
/* 226 */     return (rand.nextInt(20) != 0) ? 0 : 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Item func_149650_a(int i, Random rand, int j) {
/* 232 */     return Item.func_150898_a(TFCBlocks.sapling);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149690_a(World world, int x, int y, int z, int meta, float f, int i1) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149636_a(World world, EntityPlayer entityplayer, int i, int j, int k, int meta) {
/* 244 */     if (!world.field_72995_K) {
/*     */       
/* 246 */       ItemStack itemstack = entityplayer.field_71071_by.func_70448_g();
/* 247 */       int[] equipIDs = OreDictionary.getOreIDs(itemstack);
/* 248 */       for (int id : equipIDs) {
/*     */         
/* 250 */         String name = OreDictionary.getOreName(id);
/* 251 */         if (name.startsWith("itemScythe")) {
/*     */           
/* 253 */           for (int x = -1; x < 2; x++) {
/*     */             
/* 255 */             for (int z = -1; z < 2; z++) {
/*     */               
/* 257 */               for (int y = -1; y < 2; y++) {
/*     */                 
/* 259 */                 if (world.func_147439_a(i + x, j + y, k + z).func_149688_o() == Material.field_151584_j && entityplayer.field_71071_by
/* 260 */                   .func_70301_a(entityplayer.field_71071_by.field_70461_c) != null) {
/*     */                   
/* 262 */                   entityplayer.func_71064_a(StatList.field_75934_C[func_149682_b((Block)this)], 1);
/* 263 */                   entityplayer.func_71020_j(0.045F);
/* 264 */                   if (world.field_73012_v.nextInt(100) < 11) {
/* 265 */                     func_149642_a(world, i + x, j + y, k + z, new ItemStack(TFCItems.stick, 1));
/* 266 */                   } else if (world.field_73012_v.nextInt(100) < 4 && TFCOptions.enableSaplingDrops) {
/* 267 */                     dropSapling(world, i + x, j + y, k + z, meta);
/* 268 */                   }  removeLeaves(world, i + x, j + y, k + z);
/* 269 */                   super.func_149636_a(world, entityplayer, i + x, j + y, k + z, meta);
/*     */                   
/* 271 */                   itemstack.func_77972_a(1, (EntityLivingBase)entityplayer);
/* 272 */                   if (itemstack.field_77994_a == 0) {
/* 273 */                     entityplayer.field_71071_by.func_70299_a(entityplayer.field_71071_by.field_70461_c, null);
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 283 */       entityplayer.func_71064_a(StatList.field_75934_C[func_149682_b((Block)this)], 1);
/* 284 */       entityplayer.func_71020_j(0.025F);
/* 285 */       if (world.field_73012_v.nextInt(100) < 28) {
/* 286 */         func_149642_a(world, i, j, k, new ItemStack(TFCItems.stick, 1));
/* 287 */       } else if (world.field_73012_v.nextInt(100) < 6 && TFCOptions.enableSaplingDrops) {
/* 288 */         dropSapling(world, i, j, k, meta);
/*     */       } 
/* 290 */       super.func_149636_a(world, entityplayer, i, j, k, meta);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dropSapling(World world, int x, int y, int z, int meta) {
/* 297 */     if (meta != 9 && meta != 15) {
/* 298 */       func_149642_a(world, x, y, z, new ItemStack(func_149650_a(0, (Random)null, 0), 1, meta));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149692_a(int dmg) {
/* 304 */     return dmg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/* 310 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon func_149691_a(int side, int meta) {
/* 316 */     if (meta > this.woodNames.length - 1)
/* 317 */       meta = 0; 
/* 318 */     if (TerraFirmaCraft.proxy.getGraphicsLevel()) {
/* 319 */       return this.icons[meta];
/*     */     }
/* 321 */     return this.iconsOpaque[meta];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister iconRegisterer) {
/* 327 */     for (int i = 0; i < this.woodNames.length; i++) {
/*     */       
/* 329 */       this.icons[i] = iconRegisterer.func_94245_a("terrafirmacraft:wood/trees/" + this.woodNames[i] + " Leaves Fancy");
/* 330 */       this.iconsOpaque[i] = iconRegisterer.func_94245_a("terrafirmacraft:wood/trees/" + this.woodNames[i] + " Leaves");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] func_150125_e() {
/* 337 */     return (String[])this.woodNames.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
/* 343 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockCustomLeaves.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */