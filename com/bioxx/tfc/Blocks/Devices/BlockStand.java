/*     */ package com.bioxx.tfc.Blocks.Devices;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.BlockTerraContainer;
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Textures;
/*     */ import com.bioxx.tfc.TileEntities.TEStand;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.Interfaces.IEquipable;
/*     */ import com.bioxx.tfc.api.Interfaces.IMultipleBlock;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.Explosion;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockStand
/*     */   extends BlockTerraContainer
/*     */   implements IMultipleBlock, IEquipable
/*     */ {
/*     */   private String[] woodNames;
/*     */   
/*     */   public BlockStand() {
/*  37 */     super(Material.field_151575_d);
/*  38 */     func_149647_a(TFCTabs.TFC_DECORATION);
/*  39 */     func_149676_a(0.125F, 0.0F, 0.125F, 0.875F, 1.0F, 0.875F);
/*  40 */     this.woodNames = new String[16];
/*  41 */     System.arraycopy(Global.WOOD_ALL, 0, this.woodNames, 0, 16);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister iconRegisterer) {
/*  47 */     this.field_149761_L = iconRegisterer.func_94245_a("terrafirmacraft:wood/BarrelHoop");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon func_149691_a(int side, int meta) {
/*  53 */     if (side == 0 || side == 1) {
/*  54 */       return TFC_Textures.invisibleTexture;
/*     */     }
/*  56 */     return this.field_149761_L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
/*  64 */     for (int i = 0; i < this.woodNames.length; i++) {
/*  65 */       list.add(new ItemStack((Block)this, 1, i));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149692_a(int dmg) {
/*  71 */     return dmg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack is) {
/*  83 */     super.func_149689_a(world, i, j, k, entityliving, is);
/*  84 */     TileEntity te = world.func_147438_o(i, j, k);
/*  85 */     if (te instanceof TEStand) {
/*     */       
/*  87 */       TEStand tes = (TEStand)te;
/*  88 */       tes.yaw = ((int)(entityliving.field_70177_z % 360.0F + 360.0F + 45.0F) / 90 * 90 % 360);
/*  89 */       if (tes.yaw % 180.0F == 0.0F)
/*  90 */         tes.yaw += 180.0F; 
/*  91 */       world.func_147449_b(i, j + 1, k, (Block)this);
/*  92 */       world.func_72921_c(i, j + 1, k, is.func_77960_j(), 0);
/*  93 */       world.func_147455_a(i, j + 1, k, (TileEntity)new TEStand());
/*  94 */       ((TEStand)world.func_147438_o(i, j + 1, k)).isTop = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149686_d() {
/* 101 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149645_b() {
/* 107 */     return TFCBlocks.standRenderId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149726_b(World par1World, int par2, int par3, int par4) {
/* 116 */     super.func_149726_b(par1World, par2, par3, par4);
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
/*     */   public void func_149723_a(World par1World, int par2, int par3, int par4, Explosion par5Explosion) {}
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
/*     */   public boolean func_149742_c(World par1World, int par2, int par3, int par4) {
/* 140 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5) {
/* 147 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity func_149915_a(World var1, int var2) {
/* 153 */     return (TileEntity)new TEStand();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Block getBlockTypeForRender() {
/* 159 */     return TFCBlocks.planks;
/*     */   }
/*     */ 
/*     */   
/*     */   public IEquipable.EquipType getEquipType(ItemStack is) {
/* 164 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEquippedRender() {
/* 169 */     GL11.glTranslatef(-0.0F, -0.8F, -0.63F);
/* 170 */     GL11.glScalef(1.8F, 1.8F, 1.8F);
/* 171 */     GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getTooHeavyToCarry(ItemStack is) {
/* 178 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */