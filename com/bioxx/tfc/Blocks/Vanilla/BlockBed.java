package com.bioxx.tfc.Blocks.Vanilla;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.WorldGen.TFCBiome;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;




public class BlockBed
  extends BlockDirectional
{
  public static final int[][] FOOT_HEAD_BLOCKMAP = new int[][] { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
  
  @SideOnly(Side.CLIENT)
  private IIcon[] bedEndIcons;
  @SideOnly(Side.CLIENT)
  private IIcon[] bedSideIcons;
  @SideOnly(Side.CLIENT)
  private IIcon[] bedTopIcons;
  
  public BlockBed() {
    super(Material.field_151577_b);
    setBounds();
    func_149647_a(TFCTabs.TFC_DEVICES);
  }





  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
    if (world.field_72995_K)
    {
      return true;
    }

    
    int i1 = world.func_72805_g(x, y, z);
    
    if (!isBlockHeadOfBed(i1)) {
      
      int j1 = func_149895_l(i1);
      x += FOOT_HEAD_BLOCKMAP[j1][0];
      z += FOOT_HEAD_BLOCKMAP[j1][1];
      
      if (world.func_147439_a(x, y, z) != this) {
        return true;
      }
      i1 = world.func_72805_g(x, y, z);
    } 
    
    if (world.field_73011_w.func_76567_e() && world.func_72807_a(x, z) != TFCBiome.HELL) {
      
      if (isBedOccupied(i1)) {
        
        EntityPlayer entityplayer1 = null;
        Iterator<EntityPlayer> iterator = world.field_73010_i.iterator();
        
        while (iterator.hasNext()) {
          
          EntityPlayer entityplayer2 = iterator.next();
          
          if (entityplayer2.func_70608_bn()) {
            
            ChunkCoordinates chunkcoordinates = entityplayer2.field_71081_bT;
            
            if (chunkcoordinates.field_71574_a == x && chunkcoordinates.field_71572_b == y && chunkcoordinates.field_71573_c == z)
            {
              entityplayer1 = entityplayer2;
            }
          } 
        } 
        
        if (entityplayer1 != null) {
          
          TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("tile.bed.occupied", new Object[0]));
          return true;
        } 
        
        setBedOccupied(world, x, y, z, false);
      } 

      
      EntityPlayer.EnumStatus enumstatus = player.func_71018_a(x, y, z);

      
      if (enumstatus == EntityPlayer.EnumStatus.OK) {

        
        setBedOccupied(world, x, y, z, true);
        return true;
      } 

      
      if (enumstatus == EntityPlayer.EnumStatus.NOT_POSSIBLE_NOW) {
        TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("tile.bed.noSleep", new Object[0]));
      } else if (enumstatus == EntityPlayer.EnumStatus.NOT_SAFE) {
        TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("tile.bed.notSafe", new Object[0]));
      } 
      return true;
    } 


    
    double d0 = x + 0.5D;
    double d1 = y + 0.5D;
    double d2 = z + 0.5D;
    world.func_147468_f(x, y, z);
    int k1 = func_149895_l(i1);
    x += FOOT_HEAD_BLOCKMAP[k1][0];
    z += FOOT_HEAD_BLOCKMAP[k1][1];
    
    if (world.func_147439_a(x, y, z) == this) {
      
      world.func_147468_f(x, y, z);
      d0 = (d0 + x + 0.5D) / 2.0D;
      d1 = (d1 + y + 0.5D) / 2.0D;
      d2 = (d2 + z + 0.5D) / 2.0D;
    } 
    
    world.func_72885_a((Entity)null, (x + 0.5F), (y + 0.5F), (z + 0.5F), 5.0F, true, true);
    return true;
  }




  
  public boolean isBed(IBlockAccess world, int x, int y, int z, EntityLivingBase player) {
    World w = (World)world;
    if (!w.field_72995_K && player != null) {
      ((EntityPlayer)player).field_71076_b = 50;
    }
    return true;
  }







  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int par1, int par2) {
    if (par1 == 0)
    {
      return TFCBlocks.planks.func_149733_h(par1);
    }

    
    int k = func_149895_l(par2);
    int l = Direction.field_71584_h[k][par1];
    int i1 = isBlockHeadOfBed(par2) ? 1 : 0;
    return ((i1 != 1 || l != 2) && (i1 != 0 || l != 3)) ? ((l != 5 && l != 4) ? this.bedTopIcons[i1] : this.bedSideIcons[i1]) : this.bedEndIcons[i1];
  }








  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister par1IconRegister) {
    this.bedTopIcons = new IIcon[] { par1IconRegister.func_94245_a("terrafirmacraft:straw bed_feet_top"), par1IconRegister.func_94245_a("terrafirmacraft:straw bed_head_top") };
    this.bedEndIcons = new IIcon[] { par1IconRegister.func_94245_a("terrafirmacraft:straw bed_feet_end"), par1IconRegister.func_94245_a("terrafirmacraft:straw bed_head_end") };
    this.bedSideIcons = new IIcon[] { par1IconRegister.func_94245_a("terrafirmacraft:straw bed_feet_side"), par1IconRegister.func_94245_a("terrafirmacraft:straw bed_head_side") };
  }













  
  public int func_149645_b() {
    return 14;
  }





  
  public boolean func_149686_d() {
    return false;
  }






  
  public boolean func_149662_c() {
    return false;
  }





  
  public void func_149719_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    setBounds();
  }






  
  public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5) {
    int i1 = par1World.func_72805_g(par2, par3, par4);
    int j1 = func_149895_l(i1);
    
    if (isBlockHeadOfBed(i1)) {
      
      if (par1World.func_147439_a(par2 - FOOT_HEAD_BLOCKMAP[j1][0], par3, par4 - FOOT_HEAD_BLOCKMAP[j1][1]) != this)
      {
        par1World.func_147468_f(par2, par3, par4);
      }
    }
    else if (par1World.func_147439_a(par2 + FOOT_HEAD_BLOCKMAP[j1][0], par3, par4 + FOOT_HEAD_BLOCKMAP[j1][1]) != this) {
      
      par1World.func_147468_f(par2, par3, par4);
      
      if (!par1World.field_72995_K)
      {
        func_149697_b(par1World, par2, par3, par4, i1, 0);
      }
    } 
  }





  
  public Item func_149650_a(int par1, Random par2Random, int par3) {
    return null;
  }




  
  private void setBounds() {
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.5625F, 1.0F);
  }




  
  public static boolean isBlockHeadOfBed(int par0) {
    return ((par0 & 0x8) != 0);
  }




  
  public static boolean isBedOccupied(int par0) {
    return ((par0 & 0x4) != 0);
  }




  
  public static void setBedOccupied(World par0World, int par1, int par2, int par3, boolean par4) {
    int l = par0World.func_72805_g(par1, par2, par3);
    
    if (par4) {
      l |= 0x4;
    } else {
      l &= 0xFFFFFFFB;
    } 
    par0World.func_72921_c(par1, par2, par3, l, 4);
  }




  
  public static ChunkCoordinates getNearestEmptyChunkCoordinates(World par0World, int par1, int par2, int par3, int par4) {
    int i1 = par0World.func_72805_g(par1, par2, par3);
    int j1 = BlockDirectional.func_149895_l(i1);
    
    for (int k1 = 0; k1 <= 1; k1++) {
      
      int l1 = par1 - FOOT_HEAD_BLOCKMAP[j1][0] * k1 - 1;
      int i2 = par3 - FOOT_HEAD_BLOCKMAP[j1][1] * k1 - 1;
      int j2 = l1 + 2;
      int k2 = i2 + 2;
      
      for (int l2 = l1; l2 <= j2; l2++) {
        
        for (int i3 = i2; i3 <= k2; i3++) {
          
          if (World.func_147466_a((IBlockAccess)par0World, l2, par2 - 1, i3) && !par0World.func_147439_a(l2, par2, i3).func_149688_o().func_76218_k() && !par0World.func_147439_a(l2, par2 + 1, i3).func_149688_o().func_76218_k()) {
            
            if (par4 <= 0) {
              return new ChunkCoordinates(l2, par2, i3);
            }
            par4--;
          } 
        } 
      } 
    } 
    
    return null;
  }





  
  public void func_149690_a(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {
    if (!isBlockHeadOfBed(par5)) {
      super.func_149690_a(par1World, par2, par3, par4, par5, par6, 0);
    }
  }

  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> ret = new ArrayList<>();
    
    ret.add(new ItemStack(TFCItems.hide, 1, 2));
    ret.add(new ItemStack(TFCBlocks.thatch, 2, 0));
    return ret;
  }






  
  public int func_149656_h() {
    return 1;
  }





  
  public void func_149681_a(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {
    if (par6EntityPlayer.field_71075_bZ.field_75098_d && isBlockHeadOfBed(par5)) {
      
      int i1 = func_149895_l(par5);
      par2 -= FOOT_HEAD_BLOCKMAP[i1][0];
      par4 -= FOOT_HEAD_BLOCKMAP[i1][1];
      
      if (par1World.func_147439_a(par2, par3, par4) == this)
        par1World.func_147468_f(par2, par3, par4); 
    } 
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockBed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */