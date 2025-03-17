/*    */ package com.bioxx.tfc.Blocks.Liquids;
/*    */ 
/*    */ import com.bioxx.tfc.Effects.GasFX;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.particle.EntityFX;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fluids.Fluid;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockHotWaterStatic
/*    */   extends BlockLiquidStatic
/*    */ {
/*    */   public BlockHotWaterStatic(Fluid fluid, Material m, Block f) {
/* 25 */     super(fluid, m, f);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_149720_d(IBlockAccess access, int x, int y, int z) {
/* 31 */     return this.fluidType.getColor();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149734_b(World world, int i, int j, int k, Random random) {
/* 38 */     if (world.func_147437_c(i - 1, j, k) || world.func_147437_c(i + 1, j, k) || world
/* 39 */       .func_147437_c(i, j, k - 1) || world.func_147437_c(i, j, k + 1) || world
/* 40 */       .func_147437_c(i, j + 1, k)) {
/*    */       
/* 42 */       double f = i + 0.5D;
/* 43 */       double f1 = j + 1.0D;
/* 44 */       double f2 = k + 0.5D;
/*    */       
/* 46 */       double f4 = (random.nextFloat() * -0.1F);
/* 47 */       double f5 = (random.nextFloat() * -0.1F);
/* 48 */       double f6 = (random.nextFloat() * -0.1F);
/*    */       
/* 50 */       (Minecraft.func_71410_x()).field_71452_i.func_78873_a((EntityFX)new GasFX(world, f, f1, f2, f4, f5, f6));
/* 51 */       f4 = (random.nextFloat() * -0.1F);
/* 52 */       f5 = (random.nextFloat() * -0.1F);
/* 53 */       f6 = (random.nextFloat() * -0.1F);
/* 54 */       (Minecraft.func_71410_x()).field_71452_i.func_78873_a((EntityFX)new GasFX(world, f, f1, f2, f4, f5, f6));
/* 55 */       f4 = (random.nextFloat() * -0.1F);
/* 56 */       f5 = (random.nextFloat() * -0.1F);
/* 57 */       f6 = (random.nextFloat() * -0.1F);
/* 58 */       (Minecraft.func_71410_x()).field_71452_i.func_78873_a((EntityFX)new GasFX(world, f, f1, f2, f4, f5, f6));
/* 59 */       f4 = (random.nextFloat() * -0.1F);
/* 60 */       f5 = (random.nextFloat() * -0.1F);
/* 61 */       f6 = (random.nextFloat() * -0.1F);
/* 62 */       (Minecraft.func_71410_x()).field_71452_i.func_78873_a((EntityFX)new GasFX(world, f, f1, f2, f4, f5, f6));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149651_a(IIconRegister registerer) {
/* 70 */     this.icons = new IIcon[] { registerer.func_94245_a("terrafirmacraft:water_still"), registerer.func_94245_a("terrafirmacraft:water_flow") };
/* 71 */     getFluid().setIcons(this.icons[0], this.icons[1]);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_149691_a(int side, int meta) {
/* 78 */     return (side != 0 && side != 1) ? this.icons[1] : this.icons[0];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_149670_a(World world, int x, int y, int z, Entity entity) {
/* 84 */     if (entity instanceof EntityLivingBase) {
/*    */       
/* 86 */       EntityLivingBase e = (EntityLivingBase)entity;
/* 87 */       if (world.field_73012_v.nextInt(25) == 0 && e.func_110143_aJ() < e.func_110138_aP()) {
/*    */         
/* 89 */         float diff = e.func_110138_aP() - e.func_110143_aJ();
/* 90 */         e.func_70691_i(Math.max(diff * 0.001F, 1.0E-4F));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Liquids\BlockHotWaterStatic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */