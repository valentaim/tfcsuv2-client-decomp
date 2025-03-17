/*     */ package com.bioxx.tfc.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Textures;
/*     */ import com.bioxx.tfc.TileEntities.TEWorldItem;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import growthcraft.rice.util.RiceBlockCheck;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ 
/*     */ public class BlockWorldItem
/*     */   extends BlockTerraContainer
/*     */ {
/*     */   public BlockWorldItem() {
/*  30 */     super(Material.field_151594_q);
/*  31 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.05F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149655_b(IBlockAccess bAccess, int x, int y, int z) {
/*  37 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149725_f(World world, int x, int y, int z, int meta) {
/*  48 */     if (!world.field_72995_K) {
/*     */       
/*  50 */       TileEntity te = world.func_147438_o(x, y, z);
/*  51 */       if (te instanceof IInventory) {
/*  52 */         IInventory inv = (IInventory)te;
/*  53 */         for (int i = 0; i < inv.func_70302_i_(); i++) {
/*  54 */           if (inv.func_70301_a(i) != null) {
/*  55 */             EntityItem ei = new EntityItem(world, x + 0.5D, y + 0.5D, z + 0.5D, inv.func_70301_a(i));
/*  56 */             inv.func_70299_a(i, null);
/*  57 */             ei.field_70159_w = 0.0D;
/*  58 */             ei.field_70181_x = 0.0D;
/*  59 */             ei.field_70179_y = 0.0D;
/*  60 */             world.func_72838_d((Entity)ei);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*  65 */     super.func_149725_f(world, x, y, z, meta);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Item func_149650_a(int metadata, Random rand, int fortune) {
/*  71 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
/*  77 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
/*  83 */     if (!world.field_72995_K)
/*  84 */       return world.func_147468_f(x, y, z); 
/*  85 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/*  91 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block block) {
/*  97 */     if (world.func_147437_c(x, y - 1, z)) {
/*     */       
/*  99 */       world.func_147468_f(x, y, z);
/*     */       return;
/*     */     } 
/* 102 */     Block cblock = world.func_147439_a(x, y - 1, z);
/* 103 */     if (!RiceBlockCheck.isPaddy(cblock) && 
/* 104 */       !cblock.isSideSolid((IBlockAccess)world, x, y - 1, z, ForgeDirection.UP)) {
/*     */       
/* 106 */       world.func_147468_f(x, y, z);
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149686_d() {
/* 114 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
/* 120 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
/* 126 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
/* 132 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
/* 138 */     return AxisAlignedBB.func_72330_a(x, y, z, (x + 1), y + 0.25D, (z + 1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister reg) {
/* 145 */     this.field_149761_L = TFC_Textures.invisibleTexture;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createTileEntity(World world, int meta) {
/* 151 */     return (TileEntity)new TEWorldItem();
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockWorldItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */