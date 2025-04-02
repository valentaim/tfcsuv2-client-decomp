package com.bioxx.tfc.Blocks.Devices;

import com.bioxx.tfc.Blocks.BlockTerraContainer;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.TileEntities.TEFirepit;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;






public class BlockFirepit
  extends BlockTerraContainer
{
  private IIcon textureOn;
  private IIcon textureOff;
  
  public BlockFirepit() {
    super(Material.field_151578_c);
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.1F, 1.0F);
  }


  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K) {
      
      ItemStack equippedItem = entityplayer.func_71045_bC();
      if (equippedItem != null) {
        
        Item item = entityplayer.func_71045_bC().func_77973_b();
        if (item instanceof com.bioxx.tfc.Items.Tools.ItemFirestarter || item instanceof net.minecraft.item.ItemFlintAndSteel)
        {
          if ((TEFirepit)world.func_147438_o(x, y, z) != null) {
            
            TEFirepit te = (TEFirepit)world.func_147438_o(x, y, z);
            if (te.fireTemp < 210.0F && te.fireItemStacks[5] != null) {
              
              te.fireTemp = 300.0F;
              if (item instanceof net.minecraft.item.ItemFlintAndSteel) {
                
                Random rand = new Random();
                world.func_72908_a(x + 0.5D, y + 0.5D, z + 0.5D, "fire.ignite", 1.0F, rand.nextFloat() * 0.4F + 0.8F);
              } 
              equippedItem.func_77972_a(1, (EntityLivingBase)entityplayer);
              world.func_72921_c(x, y, z, 1, 3);
              return true;
            } 
          } 
        }
      } 
      
      if ((TEFirepit)world.func_147438_o(x, y, z) != null) {
        entityplayer.openGui(TerraFirmaCraft.instance, 20, world, x, y, z);
      }
    } 
    return true;
  }


  
  public IIcon func_149691_a(int i, int j) {
    if (j > 0)
      return this.textureOn; 
    return this.textureOff;
  }


  
  public int func_149645_b() {
    return TFCBlocks.firepitRenderId;
  }


  
  public boolean func_149662_c() {
    return false;
  }


  
  public int func_149745_a(Random rand) {
    return 0;
  }


  
  public void func_149695_a(World world, int x, int y, int z, Block block) {
    if (!TFC_Core.isTopFaceSolid(world, x, y - 1, z)) {
      
      ((TEFirepit)world.func_147438_o(x, y, z)).ejectContents();
      world.func_147468_f(x, y, z);
      return;
    } 
  }


  
  public void func_149734_b(World world, int x, int y, int z, Random rand) {
    int meta = world.func_72805_g(x, y, z);
    if (meta >= 1) {
      
      if (rand.nextInt(24) == 0) {
        world.func_72908_a(x, y, z, "fire.fire", 0.4F + rand.nextFloat() / 2.0F, 0.7F + rand.nextFloat());
      }
      float f = x + 0.5F;
      float f1 = y + 0.1F + rand.nextFloat() * 6.0F / 16.0F;
      float f2 = z + 0.5F;
      
      float f4 = rand.nextFloat() * 0.6F;
      float f5 = rand.nextFloat() * -0.6F;
      float f6 = rand.nextFloat() * -0.6F;
      world.func_72869_a("smoke", (f + f4 - 0.3F), f1, (f2 + f5 + 0.3F), 0.0D, 0.0D, 0.0D);
      world.func_72869_a("flame", (f + f4 - 0.3F), f1, (f2 + f5 + 0.3F), 0.0D, 0.0D, 0.0D);
      world.func_72869_a("smoke", (f + f5 + 0.3F), f1, (f2 + f4 - 0.3F), 0.0D, 0.0D, 0.0D);
      world.func_72869_a("flame", (f + f5 + 0.3F), f1, (f2 + f4 - 0.3F), 0.0D, 0.0D, 0.0D);
      if (((TEFirepit)world.func_147438_o(x, y, z)).fireTemp > 550.0F) {
        
        world.func_72869_a("flame", (f + f5 + 0.3F), f1, (f2 + f6 + 0.2F), 0.0D, 0.0D, 0.0D);
        world.func_72869_a("flame", (f + f4 - 0.3F), f1, (f2 + f6 + 0.1F), 0.0D, 0.0D, 0.0D);
      } 
    } 
  }


  
  public boolean func_149686_d() {
    return false;
  }


  
  public int getLightValue(IBlockAccess world, int x, int y, int z) {
    int meta = world.func_72805_g(x, y, z);
    if (meta == 0)
      return 0; 
    if (meta == 1) {
      return 10;
    }
    return 15;
  }






  
  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    return null;
  }


  
  public void func_149636_a(World world, EntityPlayer entityplayer, int x, int y, int z, int meta) {
    eject(world, x, y, z);
  }


  
  public void func_149723_a(World world, int x, int y, int z, Explosion exp) {
    eject(world, x, y, z);
  }


  
  public void func_149664_b(World world, int x, int y, int z, int meta) {
    eject(world, x, y, z);
  }



  
  public void eject(World world, int x, int y, int z) {
    if (world.func_147438_o(x, y, z) instanceof TEFirepit) {
      
      TEFirepit te = (TEFirepit)world.func_147438_o(x, y, z);
      te.ejectContents();
      world.func_147475_p(x, y, z);
    } 
  }


  
  public boolean func_149655_b(IBlockAccess bAccess, int x, int y, int z) {
    return true;
  }


  
  public TileEntity func_149915_a(World world, int meta) {
    return (TileEntity)new TEFirepit();
  }


  
  public void func_149651_a(IIconRegister iconRegisterer) {
    this.textureOn = iconRegisterer.func_94245_a("terrafirmacraft:devices/Firepit On");
    this.textureOff = iconRegisterer.func_94245_a("terrafirmacraft:devices/Firepit Off");
  }


  
  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
    return null;
  }





  
  @SideOnly(Side.CLIENT)
  public String func_149702_O() {
    return "terrafirmacraft:devices/firepit";
  }






  
  public void func_149670_a(World world, int x, int y, int z, Entity entity) {
    if (world.func_72805_g(x, y, z) >= 1 && !entity.func_70045_F() && entity instanceof EntityLivingBase)
    {
      
      entity.func_70015_d(2);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockFirepit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */