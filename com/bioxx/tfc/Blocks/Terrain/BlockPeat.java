/*    */ package com.bioxx.tfc.Blocks.Terrain;
/*    */ 
/*    */ import com.bioxx.tfc.Blocks.BlockTerra;
/*    */ import com.bioxx.tfc.Core.TFCTabs;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockPeat
/*    */   extends BlockTerra
/*    */ {
/*    */   public BlockPeat() {
/* 22 */     super(Material.field_151578_c);
/* 23 */     func_149647_a(TFCTabs.TFC_BUILDING);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149666_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
/* 33 */     list.add(new ItemStack((Block)this, 1, 0));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_149651_a(IIconRegister registerer) {
/* 39 */     this.field_149761_L = registerer.func_94245_a("terrafirmacraft:soil/Peat");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_149742_c(World world, int x, int y, int z) {
/* 45 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public String func_149702_O() {
/* 55 */     return "terrafirmacraft:peat";
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockPeat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */