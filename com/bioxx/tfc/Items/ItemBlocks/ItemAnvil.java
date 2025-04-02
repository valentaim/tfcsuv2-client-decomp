package com.bioxx.tfc.Items.ItemBlocks;

import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.Interfaces.IEquipable;
import com.bioxx.tfc.api.Interfaces.ISmeltable;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;


public abstract class ItemAnvil
  extends ItemTerraBlock
  implements ISmeltable, IEquipable
{
  public ItemAnvil(Block par1) {
    super(par1);
  }


  public EnumSize getSize(ItemStack is) {
    return EnumSize.HUGE;
  }


  public EnumWeight getWeight(ItemStack is) {
    return EnumWeight.HEAVY;
  }



  public short getMetalReturnAmount(ItemStack is) {
    return 1400;
  }



  public boolean isSmeltable(ItemStack is) {
    return true;
  }



  public ISmeltable.EnumTier getSmeltTier(ItemStack is) {
    return ISmeltable.EnumTier.TierI;
  }



  public IEquipable.EquipType getEquipType(ItemStack is) {
    return IEquipable.EquipType.BACK;
  }



  public void onEquippedRender() {
    GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
    GL11.glTranslatef(-0.5F, -0.5F, -0.3F);
  }



  public boolean getTooHeavyToCarry(ItemStack is) {
    return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemAnvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */