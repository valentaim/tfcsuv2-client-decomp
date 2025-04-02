package com.bioxx.tfc.Blocks.Devices;

import com.bioxx.tfc.Blocks.BlockTerraContainer;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Textures;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.TileEntities.TEAnvil;
import com.bioxx.tfc.api.Crafting.AnvilReq;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;



public class BlockAnvil
  extends BlockTerraContainer
{
  private IIcon[] textureMapTop;
  private IIcon[] textureMapSide;
  private IIcon stoneAnvilIcon;
  private int anvilId;

  public BlockAnvil() {
    super(Material.field_151573_f);
    func_149647_a(TFCTabs.TFC_DEVICES);
  }


  public BlockAnvil(int offset) {
    this();
    this.anvilId = offset;
  }




  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List) {
    if (this == TFCBlocks.anvil)
    {
      for (int i = 1; i < 8; i++) {
        par3List.add(new ItemStack((Block)this, 1, i));
      }
    }
    if (this == TFCBlocks.anvil2)
    {
      for (int i = 0; i < 3; i++) {
        par3List.add(new ItemStack((Block)this, 1, i));
      }
    }
  }


  public int func_149692_a(int dmg) {
    return dmg & 0x7;
  }



  public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
    if (world.field_72995_K)
    {
      return true;
    }


    if ((TEAnvil)world.func_147438_o(i, j, k) != null)
    {



      entityplayer.openGui(TerraFirmaCraft.instance, 21, world, i, j, k);
    }
    return true;
  }




  public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
    int meta = par1World.func_72805_g(par2, par3, par4);
    int direction = getDirectionFromMetadata(meta);
    TileEntity te = par1World.func_147438_o(par2, par3, par4);

    if (te instanceof TEAnvil) {

      TEAnvil teAnvil = (TEAnvil)te;
      if (teAnvil.anvilTier != AnvilReq.STONE.Tier || this == TFCBlocks.anvil2) {

        if (direction == 0) {
          return AxisAlignedBB.func_72330_a(par2 + 0.2D, par3 + 0.0D, par4 + 0.0D, par2 + 0.8D, par3 + 0.6D, par4 + 1.0D);
        }
        return AxisAlignedBB.func_72330_a(par2 + 0.0D, par3 + 0.0D, par4 + 0.2D, par2 + 1.0D, par3 + 0.6D, par4 + 0.8D);
      }


      return AxisAlignedBB.func_72330_a(par2 + 0.0D, par3 + 0.0D, par4 + 0.0D, par2 + 1.0D, par3 + 1.0D, par4 + 1.0D);
    }

    return null;
  }



  public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
    int meta = world.func_72805_g(x, y, z);
    int direction = getDirectionFromMetadata(meta);
    TEAnvil te = (TEAnvil)world.func_147438_o(x, y, z);

    if (te.anvilTier != AnvilReq.STONE.Tier) {

      if (direction == 0) {

        func_149676_a(0.2F, 0.0F, 0.0F, 0.8F, 0.6F, 1.0F);
        return AxisAlignedBB.func_72330_a(x + 0.2D, y + 0.0D, z + 0.0D, x + 0.8D, y + 0.6D, z + 1.0D);
      }


      func_149676_a(0.0F, 0.0F, 0.2F, 1.0F, 0.6F, 0.8F);
      return AxisAlignedBB.func_72330_a(x + 0.0D, y + 0.0D, z + 0.2D, x + 1.0D, y + 0.6D, z + 0.8D);
    }



    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.9F, 1.0F);
    return AxisAlignedBB.func_72330_a(x + 0.0D, y + 0.0D, z + 0.0D, x + 1.0D, y + 0.8999999761581421D, z + 1.0D);
  }







  public void func_149719_a(IBlockAccess bAccess, int x, int y, int z) {
    int meta = bAccess.func_72805_g(x, y, z);
    int direction = getDirectionFromMetadata(meta);
    TEAnvil te = (TEAnvil)bAccess.func_147438_o(x, y, z);

    if (te.anvilTier != AnvilReq.STONE.Tier) {

      if (direction == 0) {
        func_149676_a(0.2F, 0.0F, 0.0F, 0.8F, 0.6F, 1.0F);
      } else {
        func_149676_a(0.0F, 0.0F, 0.2F, 1.0F, 0.6F, 0.8F);
      }
    } else {

      func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.9F, 1.0F);
    }
  }



  public IIcon func_149691_a(int i, int j) {
    int meta = getAnvilTypeFromMeta(j);

    if (j == 0 && this == TFCBlocks.anvil)
    {
      return this.stoneAnvilIcon;
    }


    if (i == 0 || i == 1) {
      return this.textureMapTop[meta];
    }
    return this.textureMapSide[meta];
  }



  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5) {
    return true;
  }



  public int func_149645_b() {
    return TFCBlocks.anvilRenderId;
  }



  public void func_149636_a(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {
    int type = getAnvilTypeFromMeta(l);
    if (this == TFCBlocks.anvil)
    {
      if (type == 0)
        return;
    }
    super.func_149636_a(world, entityplayer, i, j, k, type);
  }



  public void func_149690_a(World par1World, int x, int y, int z, int meta, float par6, int par7) {
    if (!par1World.field_72995_K) {
      func_149642_a(par1World, x, y, z, new ItemStack((Block)this, 1, meta));
    }
  }


  protected void func_149642_a(World par1World, int par2, int par3, int par4, ItemStack is) {
    if (!par1World.field_72995_K && par1World.func_82736_K().func_82766_b("doTileDrops")) {

      if (is.func_77960_j() == 0 && this == TFCBlocks.anvil)
        return;
      float f = 0.7F;
      double d0 = (par1World.field_73012_v.nextFloat() * f) + (1.0F - f) * 0.5D;
      double d1 = (par1World.field_73012_v.nextFloat() * f) + (1.0F - f) * 0.5D;
      double d2 = (par1World.field_73012_v.nextFloat() * f) + (1.0F - f) * 0.5D;
      EntityItem entityitem = new EntityItem(par1World, par2 + d0, par3 + d1, par4 + d2, is);
      entityitem.field_145804_b = 10;
      par1World.func_72838_d((Entity)entityitem);
    }
  }



  public boolean func_149662_c() {
    return false;
  }



  public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack is) {
    int meta = world.func_72805_g(i, j, k);
    int l = MathHelper.func_76128_c((entityliving.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
    byte byte0 = 0;
    if (l == 0)
      byte0 = 8;
    if (l == 1)
      byte0 = 0;
    if (l == 2)
      byte0 = 8;
    if (l == 3)
      byte0 = 0;
    byte0 = (byte)(byte0 + meta);

    world.func_72921_c(i, j, k, byte0, 3);

    TEAnvil te = (TEAnvil)world.func_147438_o(i, j, k);
    if (this == TFCBlocks.anvil) {
      te.anvilTier = (AnvilReq.getReqFromInt(meta)).Tier;
    } else if (this == TFCBlocks.anvil2) {
      te.anvilTier = (AnvilReq.getReqFromInt2(meta)).Tier;
    }
  }


  public void func_149749_a(World world, int x, int y, int z, Block block, int metadata) {
    TEAnvil var5 = (TEAnvil)world.func_147438_o(x, y, z);

    if (var5 != null)
    {
      for (int var6 = 0; var6 < var5.func_70302_i_(); var6++) {

        ItemStack var7 = var5.func_70301_a(var6);

        if (var7 != null) {

          float var8 = world.field_73012_v.nextFloat() * 0.8F + 0.1F;
          float var9 = world.field_73012_v.nextFloat() * 0.8F + 0.1F;


          for (float var10 = world.field_73012_v.nextFloat() * 0.8F + 0.1F; var7.field_77994_a > 0; world.func_72838_d((Entity)var12)) {

            int var11 = world.field_73012_v.nextInt(21) + 10;

            if (var11 > var7.field_77994_a)
              var11 = var7.field_77994_a;
            var7.field_77994_a -= var11;
            EntityItem var12 = new EntityItem(world, (x + var8), (y + var9), (z + var10), new ItemStack(var7.func_77973_b(), var11, var7.func_77960_j()));
            float var13 = 0.05F;
            var12.field_70159_w = ((float)world.field_73012_v.nextGaussian() * var13);
            var12.field_70181_x = ((float)world.field_73012_v.nextGaussian() * var13 + 0.2F);
            var12.field_70179_y = ((float)world.field_73012_v.nextGaussian() * var13);
            if (var7.func_77942_o())
              var12.func_92059_d().func_77982_d((NBTTagCompound)var7.func_77978_p().func_74737_b());
          }
        }
      }
    }
    super.func_149749_a(world, x, y, z, block, metadata);
  }



  public boolean func_149686_d() {
    return false;
  }


  public static int getAnvilTypeFromMeta(int j) {
    int l = 7;
    return j & l;
  }


  public static int getDirectionFromMetadata(int i) {
    int d = i >> 3;
    if (d == 1) {
      return 1;
    }
    return 0;
  }



  public TileEntity func_149915_a(World var1, int var2) {
    return (TileEntity)new TEAnvil();
  }



  public void func_149651_a(IIconRegister registerer) {
    this.textureMapTop = new IIcon[(this.anvilId == 0) ? 8 : 3];
    this.textureMapSide = new IIcon[(this.anvilId == 0) ? 8 : 3];
    for (int i = (this.anvilId == 0) ? 1 : 0; i < ((this.anvilId == 0) ? 8 : 3); i++) {

      this.textureMapTop[i] = registerer.func_94245_a("terrafirmacraft:devices/Anvil_" + (i + this.anvilId) + "_Top");
      this.textureMapSide[i] = registerer.func_94245_a("terrafirmacraft:devices/Anvil_" + (i + this.anvilId) + "_Side");
    }

    this.stoneAnvilIcon = registerer.func_94245_a("terrafirmacraft:rocks/Gabbro Raw");

    TFC_Textures.anvilHit = registerer.func_94245_a("terrafirmacraft:Anvil Hit");
    TFC_Textures.anvilHitHeavy = registerer.func_94245_a("terrafirmacraft:Anvil Hit Heavy");
    TFC_Textures.anvilHitMedium = registerer.func_94245_a("terrafirmacraft:Anvil Hit Medium");
    TFC_Textures.anvilHitLight = registerer.func_94245_a("terrafirmacraft:Anvil Hit Light");
    TFC_Textures.anvilDraw = registerer.func_94245_a("terrafirmacraft:Anvil Draw");
    TFC_Textures.anvilPunch = registerer.func_94245_a("terrafirmacraft:Anvil Punch");
    TFC_Textures.anvilBend = registerer.func_94245_a("terrafirmacraft:Anvil Bend");
    TFC_Textures.anvilUpset = registerer.func_94245_a("terrafirmacraft:Anvil Upset");
    TFC_Textures.anvilShrink = registerer.func_94245_a("terrafirmacraft:Anvil Shrink");
  }



  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
    return null;
  }



  @SideOnly(Side.CLIENT)
  public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
    return true;
  }



  @SideOnly(Side.CLIENT)
  public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
    return (world.func_147439_a(x, y, z) == this);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockAnvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */