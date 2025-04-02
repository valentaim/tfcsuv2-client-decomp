package com.bioxx.tfc.Items;

import com.bioxx.tfc.Core.Player.PlayerInfo;
import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.TFC_ItemHeat;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;





public class ItemMeltedMetal
  extends ItemTerra
{
  public ItemMeltedMetal() {
    func_77656_e(101);
    func_77637_a(TFCTabs.TFC_MATERIALS);
    setFolder("ingots/");
    setWeight(EnumWeight.MEDIUM);
    setSize(EnumSize.SMALL);
  }


  
  public void func_94581_a(IIconRegister registerer) {
    this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + func_77658_a().replace("item.", "").replace("Weak ", "").replace("HC ", ""));
  }



  
  public int getItemStackLimit(ItemStack is) {
    if (isDamaged(is) || (is.func_77942_o() && TFC_ItemHeat.hasTemp(is)))
    {
      return 1;
    }
    
    return super.getItemStackLimit(is);
  }


  
  public void addItemInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
    if (is.func_77960_j() > 1)
    {
      arraylist.add(TFC_Core.translate("gui.units") + ": " + (100 - is.func_77960_j()) + " / 100");
    }
  }


  
  public void func_77663_a(ItemStack is, World world, Entity entity, int i, boolean isSelected) {
    super.func_77663_a(is, world, entity, i, isSelected);
    if (is.func_77942_o()) {

      
      if (TFC_ItemHeat.hasTemp(is) && TFC_ItemHeat.getTemp(is) >= TFC_ItemHeat.isCookable(is)) {
        
        if (is.func_77960_j() == 0) {
          is.func_77964_b(1);
        }
      }
      else if (is.func_77960_j() == 1) {
        is.func_77964_b(0);
      }
    
    }
    else if (is.func_77960_j() == 1) {
      is.func_77964_b(0);
    } 
  }


  
  public boolean isDamaged(ItemStack stack) {
    return (stack.func_77960_j() > 1);
  }

  
  public void addExtraInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
    if (TFC_Core.showShiftInformation()) {
      
      arraylist.add(TFC_Core.translate("gui.Help"));
      arraylist.add(TFC_Core.translate("gui.MeltedMetal.Inst0"));
    }
    else {
      
      arraylist.add(TFC_Core.translate("gui.ShowHelp"));
    } 
  }


  
  public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer entityplayer) {
    if (itemstack.field_77994_a <= 0) {
      itemstack.field_77994_a = 1;
    }
    
    PlayerInfo pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(entityplayer);
    pi.specialCraftingType = itemstack.func_77946_l();
    
    entityplayer.field_71071_by.func_70299_a(entityplayer.field_71071_by.field_70461_c, null);
    entityplayer.openGui(TerraFirmaCraft.instance, 38, world, (int)entityplayer.field_70165_t, (int)entityplayer.field_70163_u, (int)entityplayer.field_70161_v);
    
    return itemstack;
  }

  
  public boolean onEntityItemUpdate(EntityItem entityItem) {
    return false;
  }


  
  public boolean hasCustomEntity(ItemStack stack) {
    return true;
  }

  
  public Entity createEntity(World world, Entity entity, ItemStack itemstack) {
    EntityTossMeltedIngotItem customIngotEntity = null;
    List<EntityPlayer> players = world.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a(entity.field_70165_t - 1.0D, entity.field_70163_u - 1.0D, entity.field_70161_v - 1.0D, entity.field_70165_t + 1.0D, entity.field_70163_u + 1.0D, entity.field_70161_v + 1.0D));
    
    if (players.size() > 0 && players.get(0) != null) {
      EntityPlayer p = players.get(0);
      customIngotEntity = new EntityTossMeltedIngotItem(world, p.field_70165_t, p.field_70163_u - 0.30000001192092896D + p.func_70047_e(), p.field_70161_v, itemstack);
      customIngotEntity.field_145804_b = 40;
      
      float var5 = 0.1F;
      
      var5 = 0.3F;
      customIngotEntity.field_70159_w = (-MathHelper.func_76126_a(p.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(p.field_70125_A / 180.0F * 3.1415927F) * var5);
      customIngotEntity.field_70179_y = (MathHelper.func_76134_b(p.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(p.field_70125_A / 180.0F * 3.1415927F) * var5);
      customIngotEntity.field_70181_x = (-MathHelper.func_76126_a(p.field_70125_A / 180.0F * 3.1415927F) * var5 + 0.1F);
      var5 = 0.02F;
      float var6 = world.field_73012_v.nextFloat() * 3.1415927F * 2.0F;
      var5 *= world.field_73012_v.nextFloat();
      customIngotEntity.field_70159_w += Math.cos(var6) * var5;
      customIngotEntity.field_70181_x += ((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.1F);
      customIngotEntity.field_70179_y += Math.sin(var6) * var5;
    } else {
      customIngotEntity = new EntityTossMeltedIngotItem(world, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, itemstack);
      customIngotEntity.field_145804_b = 40;
    } 
    
    return (Entity)customIngotEntity;
  }
  
  private class EntityTossMeltedIngotItem
    extends EntityItem {
    public EntityTossMeltedIngotItem(World p_i1711_1_) {
      super(p_i1711_1_);
    }
    
    public EntityTossMeltedIngotItem(World world, double posX, double posY, double posZ, ItemStack itemStack) {
      super(world, posX, posY, posZ, itemStack);
    }
    
    public void func_70071_h_() {
      if (this.field_70170_p.func_82737_E() % 10L == 0L) {
        ItemStack ingot = func_92059_d();
        if (TFC_ItemHeat.hasTemp(ingot)) {
          Block block = this.field_70170_p.func_147439_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v));
          if (TFC_Core.isWater(block)) {
            TFC_ItemHeat.removeTempTag(ingot);
            func_85030_a("random.fizz", 0.4F, 2.0F + this.field_70146_Z.nextFloat() * 0.4F);
            func_92058_a(ingot);
          } 
        } 
      } 
      super.func_70071_h_();
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemMeltedMetal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */