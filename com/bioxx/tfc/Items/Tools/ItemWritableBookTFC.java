package com.bioxx.tfc.Items.Tools;

import com.bioxx.tfc.Items.ItemTerra;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;




public class ItemWritableBookTFC
  extends ItemTerra
{
  public ItemWritableBookTFC() {
    this.stackable = false;
  }
  
  public ItemWritableBookTFC(String tex) {
    this.stackable = false;
    func_77637_a(null);
  }












  
  public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer entityplayer) {
    return par1ItemStack;
  }

  
  public static boolean validBookTagContents(NBTTagCompound par0NBTTagCompound) {
    if (!validBookTagPages(par0NBTTagCompound))
    {
      return false;
    }
    if (!par0NBTTagCompound.func_74764_b("title"))
    {
      return false;
    }

    
    String var1 = par0NBTTagCompound.func_74779_i("title");
    return (var1 != null && var1.length() <= 16) ? par0NBTTagCompound.func_74764_b("author") : false;
  }






  
  public boolean func_77651_p() {
    return true;
  }


  
  public String func_77653_i(ItemStack par1ItemStack) {
    if (par1ItemStack.func_77942_o()) {
      
      NBTTagCompound var2 = par1ItemStack.func_77978_p();
      NBTTagString var3 = (NBTTagString)var2.func_74781_a("title");
      
      if (var3 != null)
      {
        return var3.toString();
      }
    } 
    
    return super.func_77653_i(par1ItemStack);
  }

  
  public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
    if (par1ItemStack.func_77942_o()) {
      
      NBTTagCompound var5 = par1ItemStack.func_77978_p();
      NBTTagString var6 = (NBTTagString)var5.func_74781_a("author");
      
      if (var6 != null)
      {
        par3List.add("§7" + String.format(StatCollector.func_74837_a("book.byAuthor", new Object[] { var6.func_150285_a_() }), new Object[0]));
      }
    } 
    super.func_77624_a(par1ItemStack, par2EntityPlayer, par3List, par4);
  }

  
  public static boolean validBookTagPages(NBTTagCompound par0NBTTagCompound) {
    if (par0NBTTagCompound == null)
    {
      return false;
    }
    if (!par0NBTTagCompound.func_74764_b("pages"))
    {
      return false;
    }

    
    NBTTagList var1 = (NBTTagList)par0NBTTagCompound.func_74781_a("pages");
    for (int var2 = 0; var2 < var1.func_74745_c(); var2++) {
      
      String var3 = var1.func_150307_f(var2);
      if (var3.isEmpty())
        return false; 
      if (var3.length() > 256)
        return false; 
    } 
    return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemWritableBookTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */