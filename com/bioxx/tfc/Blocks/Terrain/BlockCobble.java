/*     */ package com.bioxx.tfc.Blocks.Terrain;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.BlockTerra;
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.Tools.IToolChisel;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockCobble
/*     */   extends BlockTerra
/*     */ {
/*     */   protected String[] names;
/*     */   protected IIcon[] icons;
/*     */   protected int looseStart;
/*     */   
/*     */   protected BlockCobble(Material material) {
/*  30 */     super(material);
/*  31 */     func_149647_a(TFCTabs.TFC_BUILDING);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149738_a(World p_149738_1_) {
/*  36 */     return 20;
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
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
/*  51 */     for (int i = 0; i < this.names.length; i++) {
/*  52 */       list.add(new ItemStack((Block)this, 1, i));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Item func_149650_a(int meta, Random random, int fortune) {
/*  59 */     if (meta > 7) {
/*  60 */       return TFCItems.looseRock;
/*     */     }
/*  62 */     return Item.func_150898_a((Block)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149745_a(Random rand) {
/*  71 */     return rand.nextInt(2) + 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
/*  77 */     ArrayList<ItemStack> ret = new ArrayList<>();
/*     */ 
/*     */     
/*  80 */     int count = (metadata > 7) ? func_149745_a(world.field_73012_v) : 1;
/*     */     
/*  82 */     for (int i = 0; i < count; i++) {
/*     */       
/*  84 */       Item item = func_149650_a(metadata, world.field_73012_v, fortune);
/*     */       
/*  86 */       if (item != null)
/*     */       {
/*  88 */         if (metadata > 7) {
/*     */ 
/*     */           
/*  91 */           int meta = this.looseStart + metadata % 8;
/*  92 */           ret.add(new ItemStack(item, 1, meta));
/*     */         } else {
/*     */           
/*  95 */           ret.add(new ItemStack(item, 1, func_149692_a(metadata)));
/*     */         }  } 
/*     */     } 
/*  98 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float func_149712_f(World world, int x, int y, int z) {
/* 108 */     if (world.func_72805_g(x, y, z) > 7) {
/* 109 */       return this.field_149782_v / 2.0F;
/*     */     }
/* 111 */     return this.field_149782_v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149692_a(int i) {
/* 120 */     return i & 0x7;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon func_149691_a(int i, int j) {
/* 126 */     if ((j & 0x7) >= this.icons.length)
/* 127 */       return this.icons[0]; 
/* 128 */     return this.icons[j & 0x7];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister iconRegisterer) {
/* 134 */     for (int i = 0; i < this.names.length; i++) {
/* 135 */       this.icons[i] = iconRegisterer.func_94245_a("terrafirmacraft:rocks/" + this.names[i] + " Cobble");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_149726_b(World world, int i, int j, int k) {
/* 141 */     world.func_147464_a(i, j, k, (Block)this, func_149738_a(world));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149664_b(World world, int i, int j, int k, int l) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149695_a(World world, int i, int j, int k, Block l) {
/* 152 */     world.func_147464_a(i, j, k, (Block)this, func_149738_a(world));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float par7, float par8, float par9) {
/* 161 */     boolean hasHammer = false;
/* 162 */     for (int i = 0; i < 9; i++) {
/*     */       
/* 164 */       if (entityplayer.field_71071_by.field_70462_a[i] != null && entityplayer.field_71071_by.field_70462_a[i].func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemHammer)
/* 165 */         hasHammer = true; 
/*     */     } 
/* 167 */     if (entityplayer.func_71045_bC() != null && entityplayer.func_71045_bC().func_77973_b() instanceof IToolChisel && hasHammer && !world.field_72995_K && ((IToolChisel)entityplayer
/* 168 */       .func_71045_bC().func_77973_b()).canChisel(entityplayer, x, y, z)) {
/*     */       
/* 170 */       Block id = world.func_147439_a(x, y, z);
/* 171 */       byte meta = (byte)world.func_72805_g(x, y, z);
/*     */       
/* 173 */       return ((IToolChisel)entityplayer.func_71045_bC().func_77973_b()).onUsed(world, entityplayer, x, y, z, id, meta, side, par7, par8, par9);
/*     */     } 
/* 175 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149674_a(World world, int i, int j, int k, Random random) {
/* 182 */     BlockCollapsible.updateTickCollapsible(world, i, j, k, random, (Block)this, true);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockCobble.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */