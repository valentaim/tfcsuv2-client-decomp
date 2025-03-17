/*     */ package com.bioxx.tfc.Blocks.Terrain;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.BlockTerra;
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockGravel
/*     */   extends BlockTerra
/*     */ {
/*     */   protected IIcon[] icons;
/*     */   protected int textureOffset;
/*     */   
/*     */   public BlockGravel(int texOff) {
/*  32 */     super(Material.field_151578_c);
/*  33 */     func_149647_a(TFCTabs.TFC_BUILDING);
/*  34 */     this.textureOffset = texOff;
/*  35 */     func_149675_a(true);
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
/*  46 */     Boolean addToCreative = Boolean.valueOf(true);
/*     */     
/*  48 */     if (addToCreative.booleanValue()) {
/*     */       int count;
/*     */       
/*  51 */       if (this.textureOffset == 0) { count = 16; }
/*  52 */       else { count = Global.STONE_ALL.length - 16; }
/*     */       
/*  54 */       for (int i = 0; i < count; i++) {
/*  55 */         list.add(new ItemStack(item, 1, i));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149692_a(int dmg) {
/*  62 */     return dmg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
/*  68 */     ArrayList<ItemStack> ret = new ArrayList<>();
/*     */     
/*  70 */     if (fortune > 3)
/*     */     {
/*  72 */       fortune = 3;
/*     */     }
/*     */     
/*  75 */     if (world.field_73012_v.nextInt(10 - fortune * 3) == 0) {
/*     */       
/*  77 */       ret.add(new ItemStack(Items.field_151145_ak, 1));
/*     */     }
/*     */     else {
/*     */       
/*  81 */       ret.add(new ItemStack(Item.func_150898_a((Block)this), 1, func_149692_a(metadata)));
/*     */     } 
/*  83 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon func_149673_e(IBlockAccess bAccess, int x, int y, int z, int side) {
/*  89 */     int meta = bAccess.func_72805_g(x, y, z);
/*  90 */     if (meta >= this.icons.length) return this.icons[this.icons.length - 1]; 
/*  91 */     return this.icons[meta];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon func_149691_a(int side, int meta) {
/*  97 */     if (meta >= this.icons.length) return this.icons[this.icons.length - 1]; 
/*  98 */     return this.icons[meta];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister registerer) {
/* 104 */     int count = (this.textureOffset == 0) ? 16 : (Global.STONE_ALL.length - 16);
/* 105 */     this.icons = new IIcon[count];
/* 106 */     for (int i = 0; i < count; i++) {
/* 107 */       this.icons[i] = registerer.func_94245_a("terrafirmacraft:soil/Gravel " + Global.STONE_ALL[i + this.textureOffset]);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_149726_b(World world, int x, int y, int z) {
/* 113 */     world.func_147464_a(x, y, z, (Block)this, func_149738_a(world));
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149738_a(World world) {
/* 118 */     return 20;
/*     */   }
/*     */   
/*     */   public void func_149674_a(World world, int i, int j, int k, Random random) {
/* 122 */     BlockCollapsible.updateTickCollapsible(world, i, j, k, random, (Block)this, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block b) {
/* 128 */     if (!world.field_72995_K) {
/*     */       
/* 130 */       BlockCollapsible.tryToFall(world, x, y, z, (Block)this);
/* 131 */       world.func_147464_a(x, y, z, (Block)this, func_149738_a(world));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockGravel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */