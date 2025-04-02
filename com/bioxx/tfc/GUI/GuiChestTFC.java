package com.bioxx.tfc.GUI;

import com.bioxx.tfc.Containers.ContainerChestTFC;
import com.bioxx.tfc.Core.Player.PlayerInventory;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TileEntities.TEChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;







public class GuiChestTFC
  extends GuiContainer
{
  private IInventory upperChestInventory;
  private IInventory lowerChestInventory;
  private int inventoryRows;

  public GuiChestTFC(IInventory par1IInventory, IInventory chestInv, World world, int x, int y, int z) {
    super((Container)new ContainerChestTFC(par1IInventory, chestInv, world, x, y, z));

    TEChest chest = (TEChest)chestInv;

    this.upperChestInventory = par1IInventory;
    this.lowerChestInventory = chestInv;

    if (chest.field_145991_k != null)
    {
      this.lowerChestInventory = (IInventory)new InventoryLargeChest("Large chest", (IInventory)chest.field_145991_k, chestInv);
    }

    if (chest.field_145990_j != null)
    {
      this.lowerChestInventory = (IInventory)new InventoryLargeChest("Large chest", chestInv, (IInventory)chest.field_145990_j);
    }

    if (chest.field_145992_i != null)
    {
      this.lowerChestInventory = (IInventory)new InventoryLargeChest("Large chest", (IInventory)chest.field_145992_i, chestInv);
    }

    if (chest.field_145988_l != null)
    {
      this.lowerChestInventory = (IInventory)new InventoryLargeChest("Large chest", chestInv, (IInventory)chest.field_145988_l);
    }

    this.field_146291_p = false;
    short var3 = 222;
    int var4 = var3 - 108;
    this.inventoryRows = this.lowerChestInventory.func_70302_i_() / 9;
    this.field_147000_g = var4 + this.inventoryRows * 18;
  }





  protected void drawGuiContainerForegroundLayer() {
    this.field_146289_q.func_78276_b(TFC_Core.translate(this.lowerChestInventory.func_145825_b()), 8, 6, 4210752);
    this.field_146289_q.func_78276_b(TFC_Core.translate(this.upperChestInventory.func_145825_b()), 8, this.field_147000_g - 96 + 2, 4210752);
  }






  protected void func_146976_a(float par1, int par2, int par3) {
    TFC_Core.bindTexture(new ResourceLocation("terrafirmacraft", "textures/gui/gui_chest.png"));
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    int var5 = (this.field_146294_l - this.field_146999_f) / 2;
    int var6 = (this.field_146295_m - this.field_147000_g) / 2;
    func_73729_b(var5, var6, 0, 0, this.field_146999_f, this.inventoryRows * 18 + 17);
    func_73729_b(var5, var6 + this.inventoryRows * 18 + 17, 0, 126, this.field_146999_f, 96);

    PlayerInventory.drawInventory(this, this.field_146294_l, this.field_146295_m, this.field_147000_g - PlayerInventory.invYSize + 10);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiChestTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */