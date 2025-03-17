/*     */ package com.bioxx.tfc.Blocks.Vanilla;
/*     */ 
/*     */ import com.bioxx.tfc.Core.ColorizerFoliageTFC;
/*     */ import com.bioxx.tfc.Core.ColorizerGrassTFC;
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockTallGrass;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.IShearable;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ 
/*     */ public class BlockCustomTallGrass
/*     */   extends BlockTallGrass
/*     */   implements IShearable
/*     */ {
/*  35 */   private static final String[] META_NAMES = new String[] { "tallgrass", "fern", "shortgrass" };
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon[] icons;
/*     */ 
/*     */   
/*     */   public BlockCustomTallGrass() {
/*  42 */     float var3 = 0.4F;
/*  43 */     func_149676_a(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.8F, 0.5F + var3);
/*  44 */     func_149647_a(TFCTabs.TFC_DECORATION);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149666_a(Item item, CreativeTabs tab, List<ItemStack> list) {
/*  50 */     for (int i = 0; i < META_NAMES.length; i++)
/*     */     {
/*  52 */       list.add(new ItemStack(item, 1, i));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149635_D() {
/*  59 */     double var1 = 0.5D;
/*  60 */     double var3 = 1.0D;
/*  61 */     return ColorizerGrassTFC.getGrassColor(var1, var3);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149741_i(int par1) {
/*  67 */     return (par1 == 0) ? 16777215 : ColorizerFoliageTFC.getFoliageColorBasic();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149720_d(IBlockAccess bAccess, int x, int y, int z) {
/*  73 */     return TerraFirmaCraft.proxy.grassColorMultiplier(bAccess, x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Item func_149650_a(int metadata, Random rand, int fortune) {
/*  79 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149679_a(int i, Random rand) {
/*  85 */     return 1 + rand.nextInt(i * 2 + 1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149636_a(World world, EntityPlayer player, int i, int j, int k, int l) {
/*  91 */     super.func_149636_a(world, player, i, j, k, l);
/*     */     
/*  93 */     ItemStack is = player.field_71071_by.func_70448_g();
/*  94 */     int[] equipIDs = OreDictionary.getOreIDs(is);
/*     */     
/*  96 */     for (int id : equipIDs) {
/*     */       
/*  98 */       String name = OreDictionary.getOreName(id);
/*  99 */       if (name.startsWith("itemKnife")) {
/*     */         
/* 101 */         createStraw(world, player, i, j, k);
/* 102 */         is.func_77972_a(1, (EntityLivingBase)player);
/* 103 */         if (is.field_77994_a == 0)
/* 104 */           player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null); 
/*     */         break;
/*     */       } 
/* 107 */       if (name.startsWith("itemScythe")) {
/*     */ 
/*     */         
/* 110 */         createStraw(world, player, i, j, k);
/*     */         
/* 112 */         for (int x = -1; x < 2; x++) {
/*     */           
/* 114 */           for (int z = -1; z < 2; z++) {
/*     */             
/* 116 */             if (world.func_147439_a(i + x, j, k + z) == this) {
/*     */               
/* 118 */               createStraw(world, player, i + x, j, k + z);
/* 119 */               is.func_77972_a(1, (EntityLivingBase)player);
/* 120 */               if (is.field_77994_a == 0)
/* 121 */                 player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null); 
/* 122 */               world.func_147468_f(i + x, j, k + z);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void createStraw(World world, EntityPlayer player, int i, int j, int k) {
/* 133 */     EntityItem ei = new EntityItem(world, (i + 0.5F), (j + 0.5F), (k + 0.5F), new ItemStack(TFCItems.straw, 1));
/* 134 */     world.func_72838_d((Entity)ei);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
/* 140 */     ArrayList<ItemStack> ret = new ArrayList<>();
/*     */     
/* 142 */     ItemStack item = getSeeds(world.field_73012_v);
/* 143 */     if (item != null)
/* 144 */       ret.add(item); 
/* 145 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
/* 151 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
/* 157 */     ArrayList<ItemStack> ret = new ArrayList<>();
/* 158 */     ret.add(new ItemStack((Block)this, 1, world.func_72805_g(x, y, z)));
/* 159 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canThisPlantGrowOnThisBlock(Block block) {
/* 164 */     return TFC_Core.isSoil(block);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149718_j(World world, int x, int y, int z) {
/* 173 */     return ((world.func_72883_k(x, y, z) >= 8 || world
/* 174 */       .func_72937_j(x, y, z)) && 
/* 175 */       canThisPlantGrowOnThisBlock(world.func_147439_a(x, y - 1, z)));
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack getSeeds(Random r) {
/* 180 */     ItemStack is = null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 222 */     return is;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister register) {
/* 229 */     this.icons = new IIcon[META_NAMES.length];
/* 230 */     for (int i = 0; i < this.icons.length; i++)
/*     */     {
/* 232 */       this.icons[i] = register.func_94245_a(((i > 1) ? "terrafirmacraft:plants/" : "") + META_NAMES[i]);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int side, int meta) {
/* 240 */     if (meta >= this.icons.length) meta = 0; 
/* 241 */     return this.icons[meta];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149674_a(World w, int x, int y, int z, Random rand) {
/* 248 */     float temp = TFC_Climate.getHeightAdjustedTemp(w, x, y, z);
/*     */     
/* 250 */     if (!w.field_72995_K && w.func_72957_l(x, y, z) < 7 && temp > 12.77D) {
/*     */       
/* 252 */       if (w.field_73012_v.nextInt(Math.max((int)(160.0F / (temp - 4.0F)), 1)) < 2) {
/*     */         
/* 254 */         float vol = 0.1F + w.field_73012_v.nextFloat() * 0.2F;
/* 255 */         float pitch = temp / 100.0F * 2.0F + 0.5F + vol;
/* 256 */         w.func_72908_a(x, y, z, "terrafirmacraft:mob.cricket", vol, pitch);
/*     */       } 
/*     */       
/* 259 */       if (rand.nextInt(8) == 0) {
/* 260 */         w.func_147464_a(x, y, z, (Block)this, 5);
/*     */       }
/*     */     } 
/*     */     
/* 264 */     super.func_149674_a(w, x, y, z, rand);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockCustomTallGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */