package com.bioxx.tfc.Containers.Slots;

import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Interfaces.ISize;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class SlotSize
  extends Slot
{
  protected EnumSize size = EnumSize.MEDIUM;
  private List<Item> excpetions = new ArrayList<>();
  private List<Item> inclusions = new ArrayList<>();

  
  public SlotSize(IInventory iinventory, int i, int j, int k) {
    super(iinventory, i, j, k);
  }


  
  public boolean func_75214_a(ItemStack itemstack) {
    boolean except = this.excpetions.contains(itemstack.func_77973_b());
    boolean include = (this.inclusions.contains(itemstack.func_77973_b()) || this.inclusions.isEmpty());
    if (itemstack.func_77973_b() instanceof ISize && (((ISize)itemstack.func_77973_b()).getSize(itemstack)).stackSize >= this.size.stackSize && !except && include)
      return true; 
    if (!(itemstack.func_77973_b() instanceof ISize) && !except && include)
      return true; 
    return false;
  }

  
  public SlotSize setSize(EnumSize s) {
    this.size = s;
    return this;
  }

  
  public SlotSize addItemException(Item... ex) {
    for (int i = 0; i < ex.length; i++)
      this.excpetions.add(ex[i]); 
    return this;
  }

  
  public SlotSize addItemInclusion(Item... ex) {
    for (int i = 0; i < ex.length; i++)
      this.inclusions.add(ex[i]); 
    return this;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\Slots\SlotSize.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */