package com.bioxx.tfc.Blocks.Devices;

import com.bioxx.tfc.Blocks.BlockTerraContainer;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.TileEntities.TEChest;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;



public class BlockChestTFC
  extends BlockTerraContainer
{
  private String[] woodNames;
  
  public BlockChestTFC() {
    super(Material.field_151575_d);
    func_149647_a(TFCTabs.TFC_DECORATION);
    func_149676_a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
    this.woodNames = Global.WOOD_ALL;
  }


  
  public TileEntity func_149915_a(World world, int var2) {
    return (TileEntity)new TEChest();
  }


  
  public int func_149643_k(World world, int x, int y, int z) {
    TileEntity te = world.func_147438_o(x, y, z);
    if (te instanceof TEChest)
      return ((TEChest)te).type; 
    return 0;
  }










  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    return new ArrayList<>();
  }


  
  public Item func_149650_a(int metadata, Random rand, int fortune) {
    return null;
  }



  
  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List) {
    for (int i = 0; i < this.woodNames.length; i++) {
      par3List.add(new ItemStack((Block)this, 1, i));
    }
  }

  
  public void func_149725_f(World world, int i, int j, int k, int meta) {
    if (!world.field_72995_K && world.func_82736_K().func_82766_b("doTileDrops")) {
      
      int damageValue = func_149643_k(world, i, j, k);
      EntityItem ei = new EntityItem(world, i, j, k, new ItemStack(TFCBlocks.chest, 1, damageValue));
      world.func_72838_d((Entity)ei);
    } 
  }


  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
    if (world.field_72995_K)
    {
      return true;
    }

    
    IInventory iinventory = getInventory(world, x, y, z);
    
    if (iinventory != null) {
      player.openGui(TerraFirmaCraft.instance, 29, world, x, y, z);
    }
    return true;
  }






  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemStack) {
    byte chestSide = 0;
    int facingDir = MathHelper.func_76128_c((player.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
    int secFacingDir = MathHelper.func_76128_c((player.field_70177_z * 4.0F / 360.0F)) & 0x3;
    
    int facingN = 0;
    int facingE = 1;
    int facingS = 2;
    int facingW = 3;
    
    byte sideN = 2;
    byte sideS = 3;
    byte sideE = 5;
    byte sideW = 4;
    
    if (facingDir == 0) chestSide = 2; 
    if (facingDir == 1) chestSide = 5; 
    if (facingDir == 2) chestSide = 3; 
    if (facingDir == 3) chestSide = 4;
    
    ForgeDirection adjDirection = getAdjacentChestDirection((IBlockAccess)world, x, y, z, itemStack.func_77960_j());
    if (adjDirection == ForgeDirection.UNKNOWN) {
      
      world.func_72921_c(x, y, z, chestSide, 3);
    }
    else {
      
      switch (adjDirection) {
        
        case NORTH:
        case SOUTH:
          if (chestSide == 2 || chestSide == 3) {
            
            if (secFacingDir == 1 || secFacingDir == 0) chestSide = 5; 
            if (secFacingDir == 3 || secFacingDir == 2) chestSide = 4;
          
          } 
          break;

        
        default:
          if (chestSide == 5 || chestSide == 4) {
            
            chestSide = 2;
            if (secFacingDir == 0 || secFacingDir == 3) chestSide = 2; 
            if (secFacingDir == 2 || secFacingDir == 1) chestSide = 3;
          
          } 
          break;
      } 
      
      world.func_72921_c(x, y, z, chestSide, 3);
      world.func_72921_c(x + adjDirection.offsetX, y, z + adjDirection.offsetZ, chestSide, 3);
    } 
    
    if (itemStack.func_82837_s()) {
      ((TEChest)world.func_147438_o(x, y, z)).func_145976_a(itemStack.func_82833_r());
    }
  }

  
  public boolean func_149662_c() {
    return false;
  }


  
  public boolean func_149686_d() {
    return false;
  }


  
  public int func_149645_b() {
    return TFCBlocks.chestRenderId;
  }



  
  public void func_149719_a(IBlockAccess access, int x, int y, int z) {
    TEChest chest = (TEChest)access.func_147438_o(x, y, z);
    if (chest != null) {
      
      ForgeDirection adjDirection = getAdjacentChestDirection(access, x, y, z, chest.type);
      switch (adjDirection) {
        
        case NORTH:
          func_149676_a(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
          return;
        case SOUTH:
          func_149676_a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
          return;
        case EAST:
          func_149676_a(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
          return;
        case WEST:
          func_149676_a(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
          return;
      } 
      
      func_149676_a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
    } 
  }




  
  public void func_149726_b(World world, int x, int y, int z) {
    super.func_149726_b(world, x, y, z);
  }


  
  public void unifyAdjacentChests(World world, int x, int y, int z) {}

  
  private boolean isChestOfType(IBlockAccess world, int x, int y, int z, int type) {
    if (world.func_147439_a(x, y, z) == this) {
      
      TEChest chest = (TEChest)world.func_147438_o(x, y, z);
      if (chest != null) return (chest.type == type); 
    } 
    return false;
  }

  
  private ForgeDirection getAdjacentChestDirection(IBlockAccess world, int x, int y, int z, int type) {
    ForgeDirection[] dirs = { ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST };
    for (ForgeDirection dir : dirs) {
      
      if (isChestOfType(world, x + dir.offsetX, y, z + dir.offsetZ, type))
        return dir; 
    } 
    return ForgeDirection.UNKNOWN;
  }






  
  public boolean func_149705_a(World world, int x, int y, int z, int side, ItemStack itemStack) {
    int type = itemStack.func_77960_j();
    
    ForgeDirection adjDirection = ForgeDirection.UNKNOWN;
    ForgeDirection[] dirs = { ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST };
    for (ForgeDirection dir : dirs) {
      
      if (isChestOfType((IBlockAccess)world, x + dir.offsetX, y, z + dir.offsetZ, type)) {
        
        if (adjDirection != ForgeDirection.UNKNOWN) {
          return false;
        }
        adjDirection = dir;
      } 
    } 
    if (adjDirection == ForgeDirection.UNKNOWN)
    {
      
      return true;
    }


    
    ForgeDirection doubleDirection = getAdjacentChestDirection((IBlockAccess)world, x + adjDirection.offsetX, y, z + adjDirection.offsetZ, type);
    
    return (doubleDirection == ForgeDirection.UNKNOWN);
  }

  
  public IInventory getInventory(World world, int x, int y, int z) {
    TEChest tEChest4, tEChest3, tEChest2, tEChest8, tEChest7, tEChest6, chest = (TEChest)world.func_147438_o(x, y, z);
    
    if (chest == null)
    {
      
      return null;
    }
    if (world.isSideSolid(x, y + 1, z, ForgeDirection.DOWN))
    {
      
      return null;
    }
    
    ForgeDirection adjDirection = getAdjacentChestDirection((IBlockAccess)world, x, y, z, chest.type);
    if (adjDirection == ForgeDirection.UNKNOWN)
    {
      
      return (IInventory)chest;
    }


    
    TEChest adjChest = (TEChest)world.func_147438_o(x + adjDirection.offsetX, y, z + adjDirection.offsetZ);
    switch (adjDirection)
    
    { case NORTH:
        tEChest4 = chest;
        tEChest8 = adjChest;














        
        return (IInventory)new InventoryLargeChest("container.chestDouble", (IInventory)tEChest4, (IInventory)tEChest8);case SOUTH: tEChest7 = chest; tEChest3 = adjChest; return (IInventory)new InventoryLargeChest("container.chestDouble", (IInventory)tEChest3, (IInventory)tEChest7);case EAST: tEChest6 = chest; tEChest2 = adjChest; return (IInventory)new InventoryLargeChest("container.chestDouble", (IInventory)tEChest2, (IInventory)tEChest6); }  TEChest tEChest1 = chest; TEChest tEChest5 = adjChest; return (IInventory)new InventoryLargeChest("container.chestDouble", (IInventory)tEChest1, (IInventory)tEChest5);
  }




  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister register) {
    this.field_149761_L = register.func_94245_a("planks_oak");
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockChestTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */