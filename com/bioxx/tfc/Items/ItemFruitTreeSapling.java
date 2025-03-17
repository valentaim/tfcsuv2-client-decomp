/*     */ package com.bioxx.tfc.Items;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.TileEntities.TEFruitTreeWood;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemFruitTreeSapling
/*     */   extends ItemTerra
/*     */ {
/*     */   private IIcon[] icons;
/*     */   
/*     */   public ItemFruitTreeSapling() {
/*  29 */     func_77656_e(0);
/*  30 */     func_77627_a(true);
/*  31 */     func_77637_a(TFCTabs.TFC_FOODS);
/*  32 */     this.metaNames = Global.FRUIT_META_NAMES;
/*  33 */     this.icons = new IIcon[this.metaNames.length];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
/*  40 */     if (side == 1 && world.func_147439_a(x, y, z).func_149721_r() && world.func_147439_a(x, y, z).func_149662_c() && 
/*  41 */       TFC_Core.isSoil(world.func_147439_a(x, y, z)) && world
/*  42 */       .func_147437_c(x, y + 1, z) && !world.field_72995_K) {
/*     */ 
/*     */       
/*  45 */       int damage = stack.func_77960_j();
/*  46 */       if (damage >= this.metaNames.length) {
/*     */         
/*  48 */         damage -= 8;
/*  49 */         stack.func_77964_b(damage);
/*     */       } 
/*  51 */       world.func_147465_d(x, y + 1, z, TFCBlocks.fruitTreeWood, damage, 2);
/*     */       
/*  53 */       ((TEFruitTreeWood)world.func_147438_o(x, y + 1, z)).setTrunk(true);
/*  54 */       ((TEFruitTreeWood)world.func_147438_o(x, y + 1, z)).setHeight(0);
/*  55 */       ((TEFruitTreeWood)world.func_147438_o(x, y + 1, z)).initBirth();
/*     */       
/*  57 */       stack.field_77994_a--;
/*  58 */       return true;
/*     */     } 
/*     */     
/*  61 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
/*  67 */     for (int i = 0; i < this.metaNames.length; i++)
/*     */     {
/*  69 */       list.add(new ItemStack(this, 1, i));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_94581_a(IIconRegister registerer) {
/*  76 */     for (int i = 0; i < this.metaNames.length; i++) {
/*  77 */       this.icons[i] = registerer.func_94245_a("terrafirmacraft:wood/fruit trees/" + this.metaNames[i] + " Sapling");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public IIcon func_77617_a(int meta) {
/*  83 */     return this.icons[meta];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77650_f(ItemStack par1ItemStack) {
/*  91 */     int damage = par1ItemStack.func_77960_j();
/*  92 */     if (damage >= this.metaNames.length) {
/*     */       
/*  94 */       damage -= 8;
/*  95 */       par1ItemStack.func_77964_b(damage);
/*     */     } 
/*  97 */     return func_77617_a(damage);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_77647_b(int i) {
/* 103 */     return i;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemFruitTreeSapling.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */