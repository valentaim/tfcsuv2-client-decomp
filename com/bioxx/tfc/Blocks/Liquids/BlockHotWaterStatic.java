package com.bioxx.tfc.Blocks.Liquids;

import com.bioxx.tfc.Effects.GasFX;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;



public class BlockHotWaterStatic
  extends BlockLiquidStatic
{
  public BlockHotWaterStatic(Fluid fluid, Material m, Block f) {
    super(fluid, m, f);
  }



  public int func_149720_d(IBlockAccess access, int x, int y, int z) {
    return this.fluidType.getColor();
  }



  @SideOnly(Side.CLIENT)
  public void func_149734_b(World world, int i, int j, int k, Random random) {
    if (world.func_147437_c(i - 1, j, k) || world.func_147437_c(i + 1, j, k) || world
      .func_147437_c(i, j, k - 1) || world.func_147437_c(i, j, k + 1) || world
      .func_147437_c(i, j + 1, k)) {

      double f = i + 0.5D;
      double f1 = j + 1.0D;
      double f2 = k + 0.5D;

      double f4 = (random.nextFloat() * -0.1F);
      double f5 = (random.nextFloat() * -0.1F);
      double f6 = (random.nextFloat() * -0.1F);

      (Minecraft.func_71410_x()).field_71452_i.func_78873_a((EntityFX)new GasFX(world, f, f1, f2, f4, f5, f6));
      f4 = (random.nextFloat() * -0.1F);
      f5 = (random.nextFloat() * -0.1F);
      f6 = (random.nextFloat() * -0.1F);
      (Minecraft.func_71410_x()).field_71452_i.func_78873_a((EntityFX)new GasFX(world, f, f1, f2, f4, f5, f6));
      f4 = (random.nextFloat() * -0.1F);
      f5 = (random.nextFloat() * -0.1F);
      f6 = (random.nextFloat() * -0.1F);
      (Minecraft.func_71410_x()).field_71452_i.func_78873_a((EntityFX)new GasFX(world, f, f1, f2, f4, f5, f6));
      f4 = (random.nextFloat() * -0.1F);
      f5 = (random.nextFloat() * -0.1F);
      f6 = (random.nextFloat() * -0.1F);
      (Minecraft.func_71410_x()).field_71452_i.func_78873_a((EntityFX)new GasFX(world, f, f1, f2, f4, f5, f6));
    }
  }



  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister registerer) {
    this.icons = new IIcon[] { registerer.func_94245_a("terrafirmacraft:water_still"), registerer.func_94245_a("terrafirmacraft:water_flow") };
    getFluid().setIcons(this.icons[0], this.icons[1]);
  }



  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    return (side != 0 && side != 1) ? this.icons[1] : this.icons[0];
  }



  public void func_149670_a(World world, int x, int y, int z, Entity entity) {
    if (entity instanceof EntityLivingBase) {

      EntityLivingBase e = (EntityLivingBase)entity;
      if (world.field_73012_v.nextInt(25) == 0 && e.func_110143_aJ() < e.func_110138_aP()) {

        float diff = e.func_110138_aP() - e.func_110143_aJ();
        e.func_70691_i(Math.max(diff * 0.001F, 1.0E-4F));
      }
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Liquids\BlockHotWaterStatic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */