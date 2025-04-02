package com.bioxx.tfc.Blocks.Terrain;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.TileEntities.TEOre;
import com.bioxx.tfc.WorldGen.DataLayer;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFCOptions;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;



public class BlockOre
  extends BlockCollapsible
{
  public String[] blockNames = Global.ORE_METAL;
  protected IIcon[] icons; public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) { if (TFCOptions.enableDebugMode && world.field_72995_K) {
      int metadata = world.func_72805_g(x, y, z); TerraFirmaCraft.LOG.info("Meta = " + func_149739_a() + ":" + metadata); TEOre te = (TEOre)world.func_147438_o(x, y, z); if (te != null)
        TerraFirmaCraft.LOG.info("Ore  BaseID = " + te.baseBlockID + "| BaseMeta =" + te.baseBlockMeta); 
    }  return false; } public BlockOre(Material mat) { super(mat);






























































    
    this.icons = new IIcon[this.blockNames.length]; func_149675_a(true); func_149647_a(null); }
  public int[] getDropBlock(World world, int x, int y, int z) { int[] data = { -1, -1 }; DataLayer dl = TFC_Climate.getCacheManager(world).getRockLayerAt(x, z, TFC_Core.getRockLayerFromHeight(world, x, y, z)); if (dl != null) { BlockStone stone = null; if (dl.block instanceof BlockStone)
        stone = (BlockStone)dl.block;  if (stone != null) {
        data[0] = Block.func_149682_b(stone.dropBlock); data[1] = dl.data2;
      }  }
     return data; } public void func_149651_a(IIconRegister iconRegisterer) { for (int i = 0; i < this.blockNames.length; i++)
      this.icons[i] = iconRegisterer.func_94245_a("terrafirmacraft:ores/" + this.blockNames[i] + " Ore");  } public int func_149692_a(int dmg) { if (dmg == 14 || dmg == 15)
      return 0;  return dmg; }
  public int quantityDropped(int meta, int fortune, Random random) { if (meta == 14 || meta == 15)
      return 1 + random.nextInt(2);  return 1; }
  public IIcon func_149691_a(int side, int meta) { if (meta >= this.icons.length)
      return this.icons[0];  return this.icons[meta]; }
  public int func_149645_b() { return TFCBlocks.oreRenderId; }



  
  public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z) {
    if (!world.field_72995_K) {
      
      boolean dropOres = false;
      boolean hasHammer = false;
      int meta = world.func_72805_g(x, y, z);
      boolean isCoal = (meta == 14 || meta == 15);
      ItemStack itemstack = null;
      if (player != null) {
        
        TFC_Core.addPlayerExhaustion(player, 0.001F);
        player.func_71064_a(StatList.field_75934_C[func_149682_b((Block)this)], 1);
        dropOres = player.func_146099_a((Block)this);
        ItemStack heldItem = player.func_71045_bC();
        if (heldItem != null) {
          
          int[] itemIDs = OreDictionary.getOreIDs(heldItem);
          for (int id : itemIDs) {
            
            String name = OreDictionary.getOreName(id);
            if (name.startsWith("itemHammer")) {
              
              hasHammer = true;
              
              break;
            } 
          } 
        } 
      } 
      if (player == null || dropOres) {
        
        if (isCoal) {
          itemstack = new ItemStack(TFCItems.coal, 1 + world.field_73012_v.nextInt(2));
        } else {
          
          TEOre te = (TEOre)world.func_147438_o(x, y, z);
          int ore = getOreGrade(te, meta);
          itemstack = new ItemStack(TFCItems.oreChunk, 1, func_149692_a(ore));
        }
      
      } else if (hasHammer && !isCoal) {
        itemstack = new ItemStack(TFCItems.smallOreChunk, 1, meta);
      } 
      if (itemstack != null)
        func_149642_a(world, x, y, z, itemstack); 
    } 
    return world.func_147468_f(x, y, z);
  }



  
  public void func_149636_a(World world, EntityPlayer entityplayer, int x, int y, int z, int meta) {}



  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> ret = new ArrayList<>();
    TEOre te = (TEOre)world.func_147438_o(x, y, z);
    int ore = getOreGrade(te, metadata);
    
    int count = quantityDropped(metadata, fortune, world.field_73012_v);
    for (int i = 0; i < count; i++) {
      ItemStack itemstack;
      
      if (metadata == 14 || metadata == 15) {
        itemstack = new ItemStack(TFCItems.coal);
      } else {
        itemstack = new ItemStack(TFCItems.oreChunk, 1, func_149692_a(ore));
      } 
      ret.add(itemstack);
    } 
    return ret;
  }

  
  public static Item getDroppedItem(int meta) {
    if (meta == 14 || meta == 15) {
      return TFCItems.coal;
    }
    return TFCItems.smallOreChunk;
  }


  
  public boolean func_149659_a(Explosion exp) {
    return false;
  }


  
  public void func_149723_a(World world, int x, int y, int z, Explosion exp) {
    world.func_147468_f(x, y, z);
  }


  
  public void onBlockExploded(World world, int x, int y, int z, Explosion exp) {
    if (!world.field_72995_K) {
      ItemStack itemstack;
      TEOre te = (TEOre)world.func_147438_o(x, y, z);
      Random random = new Random();
      
      int meta = world.func_72805_g(x, y, z);
      int ore = getOreGrade(te, meta);
      
      if (meta == 14 || meta == 15) {
        itemstack = new ItemStack(TFCItems.coal, 1 + random.nextInt(2));
      } else {
        itemstack = new ItemStack(TFCItems.oreChunk, 1, ore);
      } 
      func_149642_a(world, x, y, z, itemstack);
      func_149723_a(world, x, y, z, exp);
    } 
  }

  
  public int getOreGrade(TEOre te, int ore) {
    if (te != null) {
      
      int grade = te.extraData & 0x7;
      if (grade == 1) {
        ore += 35;
      } else if (grade == 2) {
        ore += 49;
      } 
    }  return ore;
  }


  
  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
    return null;
  }


  
  public TileEntity createTileEntity(World w, int meta) {
    return (TileEntity)new TEOre();
  }


  
  public void func_149674_a(World world, int x, int y, int z, Random rand) {
    if (!world.field_72995_K) {
      scanVisible(world, x, y, z);
    }
  }
  
  public void scanVisible(World world, int x, int y, int z) {
    if (!world.field_72995_K) {
      TileEntity te = world.func_147438_o(x, y, z);
      if (!(te instanceof TEOre)) {
        TerraFirmaCraft.LOG.warn("TE was not ore on call of BlockOre.scanVisible({}, {}, {}, {})", new Object[] { world, Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(z) });
        
        return;
      } 
      if ((((TEOre)te).extraData & 0x8) == 0 && y < 255 && y > 0 && world.func_72899_e(x, y - 1, z) && world.func_72899_e(x, y + 1, z) && world.func_72899_e(x - 1, y, z) && world.func_72899_e(x + 1, y, z) && world.func_72899_e(x, y, z - 1) && world.func_72899_e(x, y, z + 1) && (!world.func_147439_a(x, y - 1, z).func_149662_c() || !world.func_147439_a(x, y + 1, z).func_149662_c() || !world.func_147439_a(x - 1, y, z).func_149662_c() || !world.func_147439_a(x + 1, y, z).func_149662_c() || !world.func_147439_a(x, y, z - 1).func_149662_c() || !world.func_147439_a(x, y, z + 1).func_149662_c())) {
        ((TEOre)te).setVisible();
      }
    } 
  }




  
  public void func_149695_a(World world, int x, int y, int z, Block b) {
    if (!world.field_72995_K)
    {
      scanVisible(world, x, y, z);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockOre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */