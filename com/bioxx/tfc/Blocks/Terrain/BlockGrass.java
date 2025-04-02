package com.bioxx.tfc.Blocks.Terrain;

import com.bioxx.tfc.Blocks.BlockTerra;
import com.bioxx.tfc.Core.ColorizerGrassTFC;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Textures;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.WorldGen.Generators.WorldGenLooseRocks;
import com.bioxx.tfc.WorldGen.Generators.WorldGenSaplings;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCOptions;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.util.ForgeDirection;





public class BlockGrass
  extends BlockTerra
{
  protected int textureOffset;
  @SideOnly(Side.CLIENT)
  public IIcon grassTopTexture;
  @SideOnly(Side.CLIENT)
  public IIcon iconSnowSide;
  @SideOnly(Side.CLIENT)
  public IIcon iconGrassSideOverlay;
  
  public BlockGrass() {
    super(Material.field_151577_b);
    func_149675_a(true);
    func_149647_a(TFCTabs.TFC_BUILDING);
  }

  
  public BlockGrass(int texOff) {
    this();
    this.textureOffset = texOff;
    func_149647_a(TFCTabs.TFC_BUILDING);
  }




  
  public void func_149666_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
    Boolean addToCreative = Boolean.valueOf(true);
    
    if (addToCreative.booleanValue()) {
      int count;
      
      if (this.textureOffset == 0) { count = 16; }
      else { count = Global.STONE_ALL.length - 16; }
      
      for (int i = 0; i < count; i++) {
        list.add(new ItemStack(item, 1, i));
      }
    } 
  }
  
  public static IIcon getIconSideOverlay() {
    return ((BlockGrass)TFCBlocks.grass).iconGrassSideOverlay;
  }


  
  public int func_149692_a(int dmg) {
    return dmg;
  }


  
  public void func_149651_a(IIconRegister registerer) {
    this.grassTopTexture = registerer.func_94245_a("terrafirmacraft:GrassTop");
    
    this.iconSnowSide = registerer.func_94245_a("terrafirmacraft:snow");
    this.iconGrassSideOverlay = registerer.func_94245_a("terrafirmacraft:GrassSide");
    
    TFC_Textures.invisibleTexture = registerer.func_94245_a("terrafirmacraft:Invisible");
  }





  
  public IIcon func_149691_a(int side, int meta) {
    if (side == 1)
      return this.grassTopTexture; 
    if (side == 0) {
      return TFC_Textures.invisibleTexture;
    }
    return this.iconGrassSideOverlay;
  }





  
  public IIcon func_149673_e(IBlockAccess access, int x, int y, int z, int side) {
    if (side == 1)
      return this.grassTopTexture; 
    if (side == 0)
      return TFC_Textures.invisibleTexture; 
    if (side == 2) {
      
      if (TFCOptions.enableBetterGrass && TFC_Core.isGrass(access.func_147439_a(x, y - 1, z - 1))) {
        return isSnow(access, x, y - 1, z - 1) ? Blocks.field_150433_aE.func_149733_h(0) : this.grassTopTexture;
      }
    } else if (side == 3) {
      
      if (TFCOptions.enableBetterGrass && TFC_Core.isGrass(access.func_147439_a(x, y - 1, z + 1))) {
        return isSnow(access, x, y - 1, z + 1) ? Blocks.field_150433_aE.func_149733_h(0) : this.grassTopTexture;
      }
    } else if (side == 4) {
      
      if (TFCOptions.enableBetterGrass && TFC_Core.isGrass(access.func_147439_a(x - 1, y - 1, z))) {
        return isSnow(access, x - 1, y - 1, z) ? Blocks.field_150433_aE.func_149733_h(0) : this.grassTopTexture;
      }
    } else if (side == 5) {
      
      if (TFCOptions.enableBetterGrass && TFC_Core.isGrass(access.func_147439_a(x + 1, y - 1, z))) {
        return isSnow(access, x + 1, y - 1, z) ? Blocks.field_150433_aE.func_149733_h(0) : this.grassTopTexture;
      }
    } 
    return this.iconGrassSideOverlay;
  }


  
  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess access, int x, int y, int z, int side) {
    if (side == 0) {
      return false;
    }
    return super.func_149646_a(access, x, y, z, side);
  }

  
  private boolean isSnow(IBlockAccess access, int x, int y, int z) {
    Material material = access.func_147439_a(x, y, z).func_149688_o();
    return (material == Material.field_151597_y || material == Material.field_151596_z);
  }







  
  public int func_149720_d(IBlockAccess bAccess, int x, int y, int z) {
    return TerraFirmaCraft.proxy.grassColorMultiplier(bAccess, x, y, z);
  }


  
  public int func_149645_b() {
    return TFCBlocks.grassRenderId;
  }


  
  public int func_149635_D() {
    double d0 = 0.5D;
    double d1 = 1.0D;
    return ColorizerGrassTFC.getGrassColor(d0, d1);
  }





  
  public int func_149741_i(int par1) {
    return func_149635_D();
  }





  
  public void func_149674_a(World world, int i, int j, int k, Random rand) {
    if (!world.field_72995_K) {
      
      int meta = world.func_72805_g(i, j, k);
      if (world.func_147439_a(i, j + 1, k) == Blocks.field_150433_aE && !TFC_Core.isDryGrass((Block)this)) {
        
        world.func_147465_d(i, j, k, TFC_Core.getTypeForDryGrassFromSoil((Block)this), meta, 2);
      }
      else if (world.func_147439_a(i, j + 1, k).isSideSolid((IBlockAccess)world, i, j + 1, k, ForgeDirection.DOWN)) {
        
        world.func_147465_d(i, j, k, TFC_Core.getTypeForDirtFromGrass((Block)this), meta, 2);
      }
      else if (world.func_72937_j(i, j + 1, k)) {
        
        spreadGrass(world, i, j, k, rand);
        
        float rain = TFC_Climate.getRainfall(world, i, j + 1, k);
        float temp = TFC_Climate.getHeightAdjustedTemp(world, i, j + 1, k);
        
        if (temp > 20.0F && TFC_Core.isGrass((Block)this) && !TFC_Core.isDryGrass((Block)this) && world.func_147439_a(i, j + 1, k).func_149688_o() != Material.field_151586_h && world.func_147437_c(i, j + 1, k)) {
          
          if (rand.nextInt((int)((16800.0F - rain) / 4.0F)) == 0 && temp > 20.0F)
          { world.func_147465_d(i, j + 1, k, TFCBlocks.tallGrass, (world.field_73012_v.nextInt(30) == 0) ? 1 : 0, 2); }
          else if (rand.nextInt(15000) == 0) { (new WorldGenSaplings()).generate(world, rand, i, j, k); }
        
        } else if (Vec3.func_72443_a(i, j, k).func_72445_d((world.func_72861_E()).field_71574_a, (world.func_72861_E()).field_71572_b, (world.func_72861_E()).field_71573_c) < 9000000.0D && 
          temp < 15.0F) {
          
          if (rand.nextInt(20000) == 0 && !WorldGenLooseRocks.rocksNearby(world, i, j, k)) (new WorldGenLooseRocks()).generateRocks(world, rand, i, j, k); 
          if (rand.nextInt(20000) == 0) (new WorldGenLooseRocks()).generateSticks(world, rand, i, j, k);
        
        } 
        
        boolean nearWater = false;
        
        for (int y = 0; y < 2 && !nearWater; y++) {
          
          for (int x = -4; x < 5 && !nearWater; x++) {
            
            for (int z = -4; z < 5 && !nearWater; z++) {
              
              if (j < 250 && j > 144 && world.func_72899_e(i + x, j - y, k + z) && world.func_147439_a(i + x, j - y, k + z).func_149688_o() == Material.field_151586_h) {
                nearWater = true;
              }
            } 
          } 
        } 
        if (TFC_Core.isGrass((Block)this) && !TFC_Core.isDryGrass((Block)this) && !nearWater && rain < 500.0F) {
          
          world.func_147465_d(i, j, k, TFC_Core.getTypeForDryGrassFromSoil((Block)this), meta, 2);
        }
        else if (TFC_Core.isGrass((Block)this) && TFC_Core.isDryGrass((Block)this) && (nearWater || rain >= 500.0F) && world.func_147439_a(i, j + 1, k) != Blocks.field_150433_aE) {
          
          world.func_147465_d(i, j, k, TFC_Core.getTypeForGrassFromSoil((Block)this), meta, 2);
        } 
      } 
      
      world.func_147471_g(i, j, k);
    } 
  }

  
  public void spreadGrass(World world, int i, int j, int k, Random rand) {
    for (int var6 = 0; var6 < 4; var6++) {
      
      int x = i + rand.nextInt(3) - 1;
      int z = k + rand.nextInt(3) - 1;
      if (world.func_72899_e(x, 144, z)) {
        
        int y = getTopSolidBlock(world, x, z);
        
        if (y > 0) {
          
          Block id = world.func_147439_a(x, y, z);
          int meta = world.func_72805_g(x, y, z);

          
          if (TFC_Core.isDirt(id) && rand.nextInt(10) == 0) {
            world.func_147465_d(x, y, z, TFC_Core.getTypeForGrassWithRainByBlock(id, TFC_Climate.getRainfall(world, x, y, z)), meta, 2);
          } else if (TFC_Core.isClay(id) && rand.nextInt(10) == 0) {
            world.func_147465_d(x, y, z, TFC_Core.getTypeForClayGrass(meta), meta, 2);
          } else if (TFC_Core.isPeat(id) && rand.nextInt(10) == 0) {
            world.func_147465_d(x, y, z, TFCBlocks.peatGrass, 0, 2);
          } 
        } 
      } 
    } 
  }



  
  public int getTopSolidBlock(World world, int xCoord, int zCoord) {
    Chunk chunk = world.func_72938_d(xCoord, zCoord);
    int x = xCoord;
    int z = zCoord;
    int y = chunk.func_76625_h() + 15;
    xCoord &= 0xF;
    
    for (zCoord &= 0xF; y > 0; y--) {
      
      Block block = chunk.func_150810_a(xCoord, y, zCoord);
      Material material = block.func_149688_o();

      
      if (block.func_149662_c() && material
        .func_76218_k() && block
        .func_149686_d() && material != Material.field_151584_j && material != Material.field_151586_h && material
        
        .func_76230_c() && 
        !block.isFoliage((IBlockAccess)world, x, y, z) && (block
        .isSideSolid((IBlockAccess)world, x, y, z, ForgeDirection.UP) || block.isSideSolid((IBlockAccess)world, x, y, z, ForgeDirection.DOWN))) return y;
    
    } 
    return -1;
  }


  
  public void func_149724_b(World world, int x, int y, int z, Entity entity) {
    if (!world.field_72995_K && this != TFCBlocks.clayGrass2 && this != TFCBlocks.clayGrass && this != TFCBlocks.peatGrass) {
      
      Random r = new Random();
      if (BlockCollapsible.canFallBelow(world, x, y - 1, z) && r.nextInt(10) == 0 && !BlockCollapsible.isNearSupport(world, x, y, z, 4, 0.0F).booleanValue()) {
        
        int meta = world.func_72805_g(x, y, z);
        world.func_147465_d(x, y, z, TFC_Core.getTypeForDirtFromGrass((Block)this), meta, 2);
      } 
    } 
  }





  
  public Item func_149650_a(int metadata, Random rand, int fortune) {
    return TFC_Core.getTypeForDirtFromGrass((Block)this).func_149650_a(metadata, rand, fortune);
  }


  
  public void func_149695_a(World world, int x, int y, int z, Block b) {
    if (world.func_147437_c(x, y - 1, z) && !BlockCollapsible.isNearSupport(world, x, y, z, 4, 0.0F).booleanValue()) {
      
      int meta = world.func_72805_g(x, y, z);
      world.func_147465_d(x, y, z, TFC_Core.getTypeForDirtFromGrass((Block)this), meta, 2);
      world.func_147464_a(x, y, z, TFC_Core.getTypeForDirtFromGrass((Block)this), 5);
    } 
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */