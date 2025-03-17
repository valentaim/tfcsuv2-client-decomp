/*    */ package com.bioxx.tfc.Blocks.Terrain;
/*    */ 
/*    */ import com.bioxx.tfc.Blocks.BlockTerra;
/*    */ import com.bioxx.tfc.api.Tools.IToolChisel;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class BlockSmooth
/*    */   extends BlockTerra
/*    */ {
/*    */   protected String[] names;
/*    */   protected IIcon[] icons;
/*    */   
/*    */   protected BlockSmooth(Material material) {
/* 26 */     super(material);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
/* 39 */     for (int i = 0; i < this.names.length; i++) {
/* 40 */       list.add(new ItemStack((Block)this, 1, i));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_149692_a(int i) {
/* 49 */     return i;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IIcon func_149691_a(int i, int j) {
/* 55 */     if ((j & 0x7) >= this.icons.length)
/* 56 */       return this.icons[0]; 
/* 57 */     return this.icons[j & 0x7];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_149651_a(IIconRegister iconRegisterer) {
/* 63 */     for (int i = 0; i < this.names.length; i++) {
/* 64 */       this.icons[i] = iconRegisterer.func_94245_a("terrafirmacraft:rocks/" + this.names[i] + " Smooth");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float par7, float par8, float par9) {
/* 73 */     boolean hasHammer = false;
/* 74 */     for (int i = 0; i < 9; i++) {
/* 75 */       if (entityplayer.field_71071_by.field_70462_a[i] != null && entityplayer.field_71071_by.field_70462_a[i].func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemHammer)
/* 76 */         hasHammer = true; 
/*    */     } 
/* 78 */     if (entityplayer.func_71045_bC() != null && entityplayer.func_71045_bC().func_77973_b() instanceof IToolChisel && hasHammer && !world.field_72995_K && ((IToolChisel)entityplayer
/* 79 */       .func_71045_bC().func_77973_b()).canChisel(entityplayer, x, y, z)) {
/*    */       
/* 81 */       Block id = world.func_147439_a(x, y, z);
/* 82 */       byte meta = (byte)world.func_72805_g(x, y, z);
/*    */       
/* 84 */       return ((IToolChisel)entityplayer.func_71045_bC().func_77973_b()).onUsed(world, entityplayer, x, y, z, id, meta, side, par7, par8, par9);
/*    */     } 
/* 86 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockSmooth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */