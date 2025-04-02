package com.bioxx.tfc.Entities;

import com.bioxx.tfc.TileEntities.TEBarrel;
import com.bioxx.tfc.api.TFCBlocks;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;


public class EntityBarrel
  extends Entity
{
  public int fuse;
  public ItemStack origBarrel;
  private int gunpowder;
  
  public EntityBarrel(World par1World) {
    super(par1World);
    this.fuse = 60;
    
    this.field_70156_m = true;
    func_70105_a(0.98F, 0.98F);
    this.field_70129_M = this.field_70131_O / 2.0F;
  }

  
  public EntityBarrel(World world, double x, double y, double z, ItemStack is, int gunpowderCount) {
    this(world);
    this.gunpowder = gunpowderCount;
    func_70107_b(x, y, z);
    float f = (float)(Math.random() * Math.PI * 2.0D);
    this.field_70159_w = (-((float)Math.sin(f)) * 0.02F);
    this.field_70181_x = 0.20000000298023224D;
    this.field_70179_y = (-((float)Math.cos(f)) * 0.02F);
    this.field_70169_q = x;
    this.field_70167_r = y;
    this.field_70166_s = z;
    this.origBarrel = is;
    this.field_70180_af.func_75692_b(20, Integer.valueOf(this.origBarrel.func_77960_j()));
  }

  
  public void setFuse(int f) {
    this.fuse = f;
  }


  
  public void func_70071_h_() {
    this.fuse--;
    if (this.fuse <= 0) {
      
      if (!this.field_70170_p.field_72995_K)
        explode(); 
      func_70106_y();
    } 
    this.field_70170_p.func_72869_a("smoke", this.field_70165_t, this.field_70163_u + 0.5D, this.field_70161_v, (new Random()).nextFloat(), 1.0D, (new Random()).nextFloat());
  }


  
  public boolean func_85032_ar() {
    return true;
  }

  
  public void explode() {
    float f = this.gunpowder / 12.0F;
    f /= 2.2F;
    this.field_70170_p.func_72876_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, f, true);
  }


  
  protected void func_70088_a() {
    this.field_70180_af.func_75682_a(20, Integer.valueOf(0));
  }


  
  public boolean func_130002_c(EntityPlayer player) {
    int x = (int)Math.floor(this.field_70165_t), y = (int)Math.floor(this.field_70163_u), z = (int)Math.floor(this.field_70161_v);
    if (!this.field_70170_p.field_72995_K && this.field_70170_p.func_147465_d(x, y, z, TFCBlocks.barrel, getBarrelType() & 0xF, 2)) {
      
      TEBarrel te = (TEBarrel)this.field_70170_p.func_147438_o(x, y, z);
      te.readFromItemNBT(this.origBarrel.func_77978_p());
      func_70106_y();
    } 
    return true;
  }


  
  public boolean func_70067_L() {
    return !this.field_70128_L;
  }


  
  public AxisAlignedBB func_70114_g(Entity entity) {
    return this.field_70121_D;
  }

  
  public int getBarrelType() {
    return this.field_70180_af.func_75679_c(20);
  }


  
  protected void func_70037_a(NBTTagCompound nbt) {
    this.fuse = nbt.func_74771_c("Fuse");
    this.origBarrel = ItemStack.func_77949_a((NBTTagCompound)nbt.func_74781_a("item"));
    this.field_70180_af.func_75692_b(20, Integer.valueOf(this.origBarrel.func_77960_j()));
  }


  
  protected void func_70014_b(NBTTagCompound nbt) {
    nbt.func_74774_a("Fuse", (byte)this.fuse);
    nbt.func_74782_a("item", (NBTBase)this.origBarrel.func_77955_b(new NBTTagCompound()));
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\EntityBarrel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */