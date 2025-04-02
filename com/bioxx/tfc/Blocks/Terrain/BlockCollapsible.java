package com.bioxx.tfc.Blocks.Terrain;

import com.bioxx.tfc.Blocks.BlockTerraContainer;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Entities.EntityFallingBlockTFC;
import com.bioxx.tfc.TileEntities.TEPartial;
import com.bioxx.tfc.api.Enums.TFCDirection;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCOptions;
import com.bioxx.tfc.api.Util.ByteCoord;
import com.bioxx.tfc.api.Util.CollapseData;
import com.bioxx.tfc.api.Util.CollapseList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;


public class BlockCollapsible
  extends BlockTerraContainer
{
  public Block dropBlock;
  public static boolean fallInstantly;

  protected BlockCollapsible(Material material, Block block) {
    super(material);
    this.dropBlock = block;
    func_149647_a(TFCTabs.TFC_BUILDING);
  }


  protected BlockCollapsible(Material material) {
    super(material);
    this.dropBlock = Blocks.field_150350_a;
    func_149647_a(TFCTabs.TFC_BUILDING);
  }


  public int[] getDropBlock(World world, int x, int y, int z) {
    int[] data = new int[2];
    data[0] = Block.func_149682_b(this.dropBlock);
    data[1] = world.func_72805_g(x, y, z);
    return data;
  }



  public static boolean canFallBelow(World world, int x, int y, int z) {
    Block block = world.func_147439_a(x, y, z);
    if (block.isAir((IBlockAccess)world, x, y, z)) return true;

    if (block == Blocks.field_150480_ab)
      return true;
    if (block == TFCBlocks.tallGrass)
      return true;
    if (block == TFCBlocks.torch)
      return true;
    if (block == TFCBlocks.smokeRack)
      return false;
    if (block == TFCBlocks.toolRack) {
      return false;
    }
    if (block == Blocks.field_150357_h)
      return false;
    if (block == TFCBlocks.charcoal)
      return false;
    if (block == TFCBlocks.molten) {
      return false;
    }
    Material material = block.func_149688_o();
    if (material == Material.field_151586_h || material == Material.field_151587_i) return true;


    if (!block.func_149662_c() && !block.func_149686_d() && !world.isSideSolid(x, y, z, ForgeDirection.UP)) return false;

    return (material == Material.field_151586_h || material == Material.field_151587_i);
  }


  public void dropCarvedStone(World world, int x, int y, int z) {
    if (world.func_147439_a(x + 1, y, z).func_149662_c())
      return;
    if (world.func_147439_a(x - 1, y, z).func_149662_c())
      return;
    if (world.func_147439_a(x, y, z + 1).func_149662_c())
      return;
    if (world.func_147439_a(x, y, z - 1).func_149662_c())
      return;
    if (world.func_147439_a(x, y + 1, z).func_149662_c())
      return;
    if (world.func_147439_a(x, y - 1, z).func_149662_c()) {
      return;
    }
    func_149642_a(world, x, y, z, new ItemStack((Block)this, 1, world.func_72805_g(x, y, z)));
    world.func_147468_f(x, y, z);
  }




























































  public static void updateTickCollapsible(World world, int i, int j, int k, Random random, Block us, boolean checkForSupport) {
    if (!world.field_72995_K) {
      if (canFallBelow(world, i, j - 1, k)) {
        if (!checkForSupport || !isNearSupport(world, i, j, k, 4, 0.0F).booleanValue()) {
          tryToFall(world, i, j, k, us);
        }
      } else {
        int airSides = 0;
        boolean[] sides = new boolean[4];
        if (world.func_147437_c(i + 1, j, k)) {
          airSides++;
          if (canFallBelow(world, i + 1, j - 1, k)) {
            sides[0] = true;
          }
        }

        if (world.func_147437_c(i, j, k + 1)) {
          airSides++;
          if (canFallBelow(world, i, j - 1, k + 1)) {
            sides[1] = true;
          }
        }

        if (world.func_147437_c(i - 1, j, k)) {
          airSides++;
          if (canFallBelow(world, i - 1, j - 1, k)) {
            sides[2] = true;
          }
        }

        if (world.func_147437_c(i, j, k - 1)) {
          airSides++;
          if (canFallBelow(world, i, j - 1, k - 1)) {
            sides[3] = true;
          }
        }

        if (airSides > 2 && (sides[0] || sides[1] || sides[2] || sides[3])) {
          if (checkForSupport && isNearSupport(world, i, j, k, 4, 0.0F).booleanValue()) {
            return;
          }

          int meta = world.func_72805_g(i, j, k);
          world.func_147468_f(i, j, k);

          int rng;
          for (rng = random.nextInt(4); !sides[rng]; rng = (rng + 1) % 4);



          switch (rng) {
            case 0:
              world.func_147465_d(i + 1, j, k, us, meta, 2);
              tryToFall(world, i + 1, j, k, us);
              break;
            case 1:
              world.func_147465_d(i, j, k + 1, us, meta, 2);
              tryToFall(world, i, j, k + 1, us);
              break;
            case 2:
              world.func_147465_d(i - 1, j, k, us, meta, 2);
              tryToFall(world, i - 1, j, k, us);
              break;
            case 3:
              world.func_147465_d(i, j, k - 1, us, meta, 2);
              tryToFall(world, i, j, k - 1, us);
              break;
          }
        }
      }
    }
  }

  public static Boolean isNearSupport(World world, int i, int j, int k, int range, float collapseChance) {
    if (world == null) return Boolean.valueOf(false);
    if (!world.func_72873_a(i, j, k, range + 1)) return Boolean.valueOf(true);

    for (int y = -1; y <= 1; ) { if (TFC_Core.isVertSupport(world.func_147439_a(i, j + y, k))) return Boolean.valueOf(true);  y++; }
     for (int x = -range; x <= range; x++) {
      for (int z = -range; z <= range; z++) {
        for (int m = -1; m <= 1; m++) {
          if (TFC_Core.isHorizSupport(world.func_147439_a(i + x, j + m, k + z)))
            if (world.field_73012_v.nextFloat() < collapseChance / 100.0F) { world.func_147468_f(i + x, j + m, k + z); }
            else { return Boolean.valueOf(true); }
        }
      }
    }  return Boolean.valueOf(false);
  }



  public Boolean isUnderLoad(World world, int i, int j, int k) {
    for (int x = 1; x <= TFCOptions.minimumRockLoad; x++) {

      if (!world.func_147439_a(i, j + x, k).func_149662_c())
        return Boolean.valueOf(false);
    }
    return Boolean.valueOf(true);
  }

  public Boolean tryToCollapse(World world, int x, int y, int z, float collapseChance) {
    if (world.field_72995_K) return Boolean.valueOf(false);

    int[] drop = getDropBlock(world, x, y, z);
    Block fallingBlock = Block.func_149729_e(drop[0]);
    if (fallingBlock == Blocks.field_150350_a) return Boolean.valueOf(false);
    if (world.func_147439_a(x, y, z) != Blocks.field_150357_h && world.func_147439_a(x, y, z) != fallingBlock) {

      int fallingBlockMeta = drop[1];
      if (canFallBelow(world, x, y - 1, z) && isUnderLoad(world, x, y, z).booleanValue() && !isNearSupport(world, x, y, z, 4, collapseChance).booleanValue()) {
        if (fallingBlock != null) {
          EntityFallingBlockTFC ent = new EntityFallingBlockTFC(world, (x + 0.5F), (y + 0.5F), (z + 0.5F), fallingBlock, fallingBlockMeta);
          if (this instanceof BlockStone) ent = new EntityFallingBlockTFC(world, (x + 0.5F), (y + 0.5F), (z + 0.5F), fallingBlock, fallingBlockMeta + 8);
          ent.aliveTimer = -5000;
          world.func_72838_d((Entity)ent);
          Random r = new Random((x * y + z));
          if (r.nextInt(100) > 90) world.func_72956_a((Entity)ent, "terrafirmacraft:rock.slide.long", 1.0F, 0.8F + r.nextFloat() / 2.0F);

        }
        if (world.func_147439_a(x, y, z) instanceof BlockOre && !TFCOptions.enableCaveInsDestroyOre) { TFC_Core.setBlockToAirWithDrops(world, x, y, z); }
        else { world.func_147468_f(x, y, z); }

        if (world.func_147439_a(x, y - 1, z) == TFCBlocks.stoneSlabs && ((TEPartial)world.func_147438_o(x, y - 1, z)).field_145854_h == this && ((TEPartial)world.func_147438_o(x, y - 1, z)).metaID == fallingBlockMeta) {
          world.func_147468_f(x, y - 1, z);
          if (world.func_147439_a(x, y - 2, z) == TFCBlocks.stoneSlabs && ((TEPartial)world.func_147438_o(x, y - 2, z)).field_145854_h == this && ((TEPartial)world.func_147438_o(x, y - 2, z)).metaID == fallingBlockMeta) {
            world.func_147468_f(x, y - 2, z);
            if (world.func_147439_a(x, y - 3, z) == TFCBlocks.stoneSlabs && ((TEPartial)world.func_147438_o(x, y - 3, z)).field_145854_h == this && ((TEPartial)world.func_147438_o(x, y - 3, z)).metaID == fallingBlockMeta) {
              world.func_147468_f(x, y - 3, z);
            }
          }
        }

        return Boolean.valueOf(true);
      }
      return Boolean.valueOf(false);
    }

    return Boolean.valueOf(false);
  }



  public static void tryToFall(World world, int x, int y, int z, Block block) {
    if (!world.field_72995_K) {
      int meta = world.func_72805_g(x, y, z);
      if (canFallBelow(world, x, y - 1, z) && y >= 0 && (block instanceof BlockSand || !isNearSupport(world, x, y, z, 4, 0.0F).booleanValue())) {
        if (!fallInstantly && world.func_72904_c(x - 32, y - 32, z - 32, x + 32, y + 32, z + 32))
        { if (!world.field_72995_K) {
            EntityFallingBlockTFC entityfallingblock = new EntityFallingBlockTFC(world, (x + 0.5F), (y + 0.5F), (z + 0.5F), block, meta);
            world.func_72838_d((Entity)entityfallingblock);
            if (block instanceof BlockCobble)
            { world.func_72956_a((Entity)entityfallingblock, "terrafirmacraft:rock.slide.short", 1.0F, 0.8F + world.field_73012_v.nextFloat() / 2.0F); }
            else { world.func_72956_a((Entity)entityfallingblock, "terrafirmacraft:dirt.slide.short", 1.0F, 0.8F + world.field_73012_v.nextFloat() / 2.0F); }

          }  }
        else { world.func_147468_f(x, y, z);
          for (; canFallBelow(world, x, y - 1, z) && y > 0; y--);
          if (y > 0) world.func_147465_d(x, y, z, block, meta, 2);
           }

      }
    }
  }




  public void func_149636_a(World world, EntityPlayer entityplayer, int x, int y, int z, int meta) {
    float softModifier = 0.1F;

    int finalCollapseRatio = (TFCOptions.initialCollapseRatio > 0) ? TFCOptions.initialCollapseRatio : 10;


    if (entityplayer != null) {

      entityplayer.func_71064_a(StatList.field_75934_C[func_149682_b((Block)this)], 1);
      entityplayer.func_71020_j(0.075F);
    }


    if (this == TFCBlocks.stoneSed) {
      finalCollapseRatio = (int)(finalCollapseRatio - finalCollapseRatio * softModifier);
    }

    if (TFCOptions.enableCaveIns && world.field_73012_v.nextInt(finalCollapseRatio) == 0) {


      int counter = 0;
      while (counter < 100) {

        int scanX = -4 + world.field_73012_v.nextInt(9);
        int scanY = -2 + world.field_73012_v.nextInt(5);
        int scanZ = -4 + world.field_73012_v.nextInt(9);
        if (world.func_147439_a(x + scanX, y + scanY, z + scanZ) instanceof BlockCollapsible && ((BlockCollapsible)world
          .func_147439_a(x + scanX, y + scanY, z + scanZ)).tryToCollapse(world, x + scanX, y + scanY, z + scanZ, 0.0F).booleanValue()) {

          triggerCollapse(world, entityplayer, x + scanX, y + scanY, z + scanZ, meta);
          return;
        }
        counter++;
      }
    }
  }












  public void triggerCollapse(World world, EntityPlayer entityplayer, int i, int j, int k, int meta) {
    int height = 4;
    int range = 5 + world.field_73012_v.nextInt(31);
    for (int y = -4; y <= 1; y++) {

      for (int x = -range; x <= range; x++) {

        for (int z = -range; z <= range; z++) {

          double distSqrd = Math.pow((i - i + x), 2.0D) + Math.pow((j - j + y), 2.0D) + Math.pow((k - k + z), 2.0D);

          if (world.field_73012_v.nextInt(100) < TFCOptions.propogateCollapseChance && distSqrd < 1225.0D)
          {
            if (world.func_147439_a(i + x, j + y, k + z) instanceof BlockCollapsible && ((BlockCollapsible)world.func_147439_a(i + x, j + y, k + z)).tryToCollapse(world, i + x, j + y, k + z, 1.0F).booleanValue()) {

              int done = 0;
              while (done < height) {

                done++;
                if (world.func_147439_a(i + x, j + y, k + z) instanceof BlockCollapsible && world.field_73012_v.nextInt(100) < TFCOptions.propogateCollapseChance) {
                  ((BlockCollapsible)world.func_147439_a(i + x, j + y, k + z)).tryToCollapse(world, i + x, j + y + done, k + z, 1.0F); continue;
                }  done = height;
              }
            }
          }
        }
      }
    }
  }







  public List<ByteCoord> getCollapseMap(World world, int i, int j, int k) {
    List<ByteCoord> map = new ArrayList<>();
    List<ByteCoord> checkedmap = new ArrayList<>();
    CollapseList<CollapseData> checkQueue = new CollapseList();
    float incrementChance = 2.5F;
    float incrementChanceDiag = 3.5F;








    map.add(new ByteCoord(0, 0, 0));

    checkQueue.add(new CollapseData(new ByteCoord(1, 0, 0), TFCOptions.propogateCollapseChance, TFCDirection.EAST));
    checkQueue.add(new CollapseData(new ByteCoord(-1, 0, 0), TFCOptions.propogateCollapseChance, TFCDirection.WEST));
    checkQueue.add(new CollapseData(new ByteCoord(1, 0, 1), TFCOptions.propogateCollapseChance, TFCDirection.NORTHEAST));
    checkQueue.add(new CollapseData(new ByteCoord(1, 0, -1), TFCOptions.propogateCollapseChance, TFCDirection.SOUTHEAST));
    checkQueue.add(new CollapseData(new ByteCoord(-1, 0, 1), TFCOptions.propogateCollapseChance, TFCDirection.NORTHWEST));
    checkQueue.add(new CollapseData(new ByteCoord(-1, 0, -1), TFCOptions.propogateCollapseChance, TFCDirection.SOUTHWEST));
    checkQueue.add(new CollapseData(new ByteCoord(0, 0, 1), TFCOptions.propogateCollapseChance, TFCDirection.SOUTH));
    checkQueue.add(new CollapseData(new ByteCoord(0, 0, -1), TFCOptions.propogateCollapseChance, TFCDirection.NORTH));

    while (checkQueue.peek() != null) {

      CollapseData block = (CollapseData)checkQueue.peek();
      if (!checkedmap.contains(block.coords) && world.field_73012_v.nextFloat() < block.collapseChance / 100.0F) {



        int worldX = block.coords.x + i;
        int worldY = block.coords.y + j;
        int worldZ = block.coords.z + k;
        int localX = block.coords.x;
        int localY = block.coords.y;
        int localZ = block.coords.z;
        if (world.func_147437_c(worldX, worldY, worldZ)) {
          checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 1, localZ + 0), block.collapseChance - 10.0F, TFCDirection.UP));
        } else if (world.func_147439_a(worldX, worldY, worldZ) instanceof BlockCollapsible && ((BlockCollapsible)world
          .func_147439_a(worldX, worldY, worldZ)).tryToCollapse(world, worldX, worldY, worldZ, block.collapseChance).booleanValue()) {

          map.add(block.coords);

          switch (block.direction) {


            case NORTH:
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ - 1), block.collapseChance - 2.5F, TFCDirection.NORTH));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 0), block.collapseChance - 2.5F, TFCDirection.EAST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 0), block.collapseChance - 2.5F, TFCDirection.WEST));
              break;


            case SOUTH:
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ + 1), block.collapseChance - 2.5F, TFCDirection.SOUTH));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 0), block.collapseChance - 2.5F, TFCDirection.EAST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 0), block.collapseChance - 2.5F, TFCDirection.WEST));
              break;


            case EAST:
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ + 1), block.collapseChance - 2.5F, TFCDirection.SOUTH));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 0), block.collapseChance - 2.5F, TFCDirection.EAST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ - 1), block.collapseChance - 2.5F, TFCDirection.NORTH));
              break;


            case WEST:
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ + 1), block.collapseChance - 2.5F, TFCDirection.SOUTH));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 0), block.collapseChance - 2.5F, TFCDirection.WEST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ - 1), block.collapseChance - 2.5F, TFCDirection.NORTH));
              break;


            case SOUTHEAST:
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ - 1), block.collapseChance - 3.5F, TFCDirection.SOUTHEAST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ + 1), block.collapseChance - 2.5F, TFCDirection.SOUTH));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 0), block.collapseChance - 2.5F, TFCDirection.EAST));
              break;


            case SOUTHWEST:
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ - 1), block.collapseChance - 3.5F, TFCDirection.SOUTHWEST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ + 1), block.collapseChance - 2.5F, TFCDirection.SOUTH));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 0), block.collapseChance - 2.5F, TFCDirection.WEST));
              break;


            case NORTHEAST:
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 1), block.collapseChance - 3.5F, TFCDirection.NORTHEAST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 0), block.collapseChance - 2.5F, TFCDirection.EAST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ - 1), block.collapseChance - 2.5F, TFCDirection.NORTH));
              break;


            case NORTHWEST:
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 1), block.collapseChance - 3.5F, TFCDirection.NORTHWEST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ - 1), block.collapseChance - 2.5F, TFCDirection.NORTH));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 0), block.collapseChance - 2.5F, TFCDirection.WEST));
              break;


            default:
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 0), block.collapseChance - 2.5F, TFCDirection.EAST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 0), block.collapseChance - 2.5F, TFCDirection.WEST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 1), block.collapseChance - 3.5F, TFCDirection.NORTHEAST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ - 1), block.collapseChance - 3.5F, TFCDirection.SOUTHEAST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 1), block.collapseChance - 3.5F, TFCDirection.NORTHWEST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ - 1), block.collapseChance - 3.5F, TFCDirection.SOUTHWEST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ + 1), block.collapseChance - 2.5F, TFCDirection.SOUTH));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ - 1), block.collapseChance - 2.5F, TFCDirection.NORTH));
              break;
          }

        }
      }
      checkedmap.add(block.coords);
      checkQueue.removeFirst();
    }
    return map;
  }



  public void func_149723_a(World world, int x, int y, int z, Explosion ex) {
    func_149636_a(world, (EntityPlayer)null, x, y, z, world.func_72805_g(x, y, z));
  }



  public boolean canBeReplacedByLeaves(IBlockAccess w, int x, int y, int z) {
    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockCollapsible.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */