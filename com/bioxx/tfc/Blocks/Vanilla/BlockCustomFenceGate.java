/*     */ package com.bioxx.tfc.Blocks.Vanilla;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.TileEntities.TEFenceGate;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.Interfaces.IMultipleBlock;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockFenceGate;
/*     */ import net.minecraft.block.ITileEntityProvider;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockCustomFenceGate
/*     */   extends BlockFenceGate
/*     */   implements ITileEntityProvider, IMultipleBlock
/*     */ {
/*     */   public String[] woodNames;
/*     */   public IIcon[] icons;
/*     */   
/*     */   public BlockCustomFenceGate() {
/*  38 */     this.woodNames = new String[16];
/*  39 */     System.arraycopy(Global.WOOD_ALL, 0, this.woodNames, 0, 16);
/*  40 */     this.icons = new IIcon[this.woodNames.length];
/*  41 */     func_149647_a(TFCTabs.TFC_DEVICES);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
/*  47 */     int direction = 0;
/*  48 */     boolean open = false;
/*  49 */     TileEntity te = world.func_147438_o(x, y, z);
/*     */     
/*  51 */     if (te instanceof TEFenceGate) {
/*     */       
/*  53 */       direction = ((TEFenceGate)te).getDirection();
/*  54 */       open = ((TEFenceGate)te).getOpen();
/*     */     } 
/*     */     
/*  57 */     if (open) {
/*  58 */       return null;
/*     */     }
/*  60 */     return (direction != 2 && direction != 0) ? AxisAlignedBB.func_72330_a((x + 0.375F), y, z, (x + 0.625F), (y + 1.5F), (z + 1)) : AxisAlignedBB.func_72330_a(x, y, (z + 0.375F), (x + 1), (y + 1.5F), (z + 0.625F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List) {
/*  67 */     for (int i = 0; i < this.woodNames.length; i++) {
/*  68 */       par3List.add(new ItemStack((Block)this, 1, i));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister iconRegisterer) {
/*  74 */     for (int i = 0; i < this.woodNames.length; i++) {
/*  75 */       this.icons[i] = iconRegisterer.func_94245_a("terrafirmacraft:wood/" + this.woodNames[i] + " Plank");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149645_b() {
/*  81 */     return TFCBlocks.fenceGateRenderId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon func_149691_a(int par1, int par2) {
/*  87 */     return this.icons[par2];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149692_a(int par1) {
/*  93 */     return par1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon func_149673_e(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
/*  99 */     return this.icons[par1IBlockAccess.func_72805_g(par2, par3, par4)];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149655_b(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 105 */     if (par1IBlockAccess.func_147438_o(par3, par3, par4) instanceof TEFenceGate)
/* 106 */       return ((TEFenceGate)par1IBlockAccess.func_147438_o(par2, par3, par4)).getOpen(); 
/* 107 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149719_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
/* 113 */     int l = ((TEFenceGate)par1IBlockAccess.func_147438_o(par2, par3, par4)).getDirection();
/*     */     
/* 115 */     if (l != 2 && l != 0) {
/*     */       
/* 117 */       func_149676_a(0.375F, 0.0F, 0.0F, 0.625F, 1.0F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/* 121 */       func_149676_a(0.0F, 0.0F, 0.375F, 1.0F, 1.0F, 0.625F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
/* 128 */     int l = (MathHelper.func_76128_c((par5EntityLivingBase.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3) % 4;
/* 129 */     ((TEFenceGate)par1World.func_147438_o(par2, par3, par4)).setDirection((byte)l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
/* 137 */     TEFenceGate te = (TEFenceGate)par1World.func_147438_o(par2, par3, par4);
/*     */     
/* 139 */     if (te.getOpen()) {
/* 140 */       te.setOpen(false);
/*     */     } else {
/* 142 */       te.setOpen(true);
/*     */     } 
/* 144 */     par1World.func_72889_a(par5EntityPlayer, 1003, par2, par3, par4, 0);
/* 145 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block block) {
/* 151 */     TEFenceGate te = (TEFenceGate)world.func_147438_o(x, y, z);
/* 152 */     boolean flag = world.func_72864_z(x, y, z);
/*     */     
/* 154 */     if (flag || (block.func_149688_o() != Material.field_151579_a && block.func_149744_f()))
/*     */     {
/* 156 */       if (flag && !te.getOpen()) {
/*     */         
/* 158 */         te.setOpen(true);
/* 159 */         world.func_72889_a((EntityPlayer)null, 1003, x, y, z, 0);
/*     */       }
/* 161 */       else if (!flag && te.getOpen()) {
/*     */         
/* 163 */         te.setOpen(false);
/* 164 */         world.func_72889_a((EntityPlayer)null, 1003, x, y, z, 0);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity func_149915_a(World var1, int var2) {
/* 173 */     return (TileEntity)new TEFenceGate();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Block getBlockTypeForRender() {
/* 179 */     return TFCBlocks.fenceGate;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess bAccess, int x, int y, int z, int side) {
/* 186 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
/* 192 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockCustomFenceGate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */