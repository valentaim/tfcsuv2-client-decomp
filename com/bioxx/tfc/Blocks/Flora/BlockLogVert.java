/*     */ package com.bioxx.tfc.Blocks.Flora;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.BlockTerra;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
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
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ 
/*     */ public class BlockLogVert
/*     */   extends BlockTerra {
/*     */   protected String[] woodNames;
/*     */   
/*     */   public BlockLogVert() {
/*  29 */     super(Material.field_151575_d);
/*  30 */     this.woodNames = new String[16];
/*  31 */     System.arraycopy(Global.WOOD_ALL, 0, this.woodNames, 0, 16);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149636_a(World world, EntityPlayer entityplayer, int x, int y, int z, int meta) {
/*  38 */     boolean isAxeorSaw = false;
/*  39 */     boolean isHammer = false;
/*  40 */     ItemStack equip = entityplayer.func_71045_bC();
/*  41 */     if (equip != null) {
/*     */       
/*  43 */       int[] equipIDs = OreDictionary.getOreIDs(equip);
/*  44 */       for (int id : equipIDs) {
/*     */         
/*  46 */         String name = OreDictionary.getOreName(id);
/*  47 */         if (name.startsWith("itemAxe") || name.startsWith("itemSaw")) {
/*     */           
/*  49 */           isAxeorSaw = true;
/*     */           break;
/*     */         } 
/*  52 */         if (name.startsWith("itemHammer")) {
/*     */           
/*  54 */           isHammer = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*  59 */       if (isAxeorSaw)
/*     */       {
/*  61 */         super.func_149636_a(world, entityplayer, x, y, z, meta);
/*     */       }
/*  63 */       else if (isHammer)
/*     */       {
/*  65 */         EntityItem item = new EntityItem(world, x + 0.5D, y + 0.5D, z + 0.5D, new ItemStack(TFCItems.stick, 1 + world.field_73012_v.nextInt(3)));
/*  66 */         world.func_72838_d((Entity)item);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  71 */       world.func_147465_d(x, y, z, (Block)this, meta, 2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149692_a(int dmg) {
/*  78 */     return dmg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Item func_149650_a(int i, Random r, int j) {
/*  84 */     return TFCItems.logs;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int side, int meta) {
/*  91 */     return TFCBlocks.logNatural.func_149691_a(side, meta);
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
/* 102 */     for (int i = 0; i < this.woodNames.length; i++)
/* 103 */       list.add(new ItemStack((Block)this, 1, i)); 
/*     */   }
/*     */   
/*     */   public void func_149651_a(IIconRegister reg) {}
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Flora\BlockLogVert.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */