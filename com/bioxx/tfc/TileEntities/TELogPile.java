package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Core.Vector3f;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCOptions;
import java.util.ArrayDeque;
import java.util.Queue;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;







public class TELogPile
  extends TileEntity
  implements IInventory
{
  public ItemStack[] storage = new ItemStack[4];
  private int logPileOpeners = 0;
  public int fireTimer = 100;
  public boolean isOnFire;
  private Queue<Vector3f> blocksToBeSetOnFire;
  
  public void addContents(int index, ItemStack is) {
    if (this.storage[index] == null)
    {
      this.storage[index] = is;
    }
  }

  
  public ItemStack takeLog(int slot) {
    if (this.storage[slot] == null) {
      return null;
    }
    
    ItemStack is = this.storage[slot].func_77946_l();
    is.field_77994_a = 1;
    (this.storage[slot]).field_77994_a--;
    if ((this.storage[slot]).field_77994_a == 0)
      this.storage[slot] = null; 
    if (getNumberOfLogs() == 0)
      this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e); 
    return is;
  }


  
  public void clearContents() {
    this.storage[0] = null;
    this.storage[1] = null;
    this.storage[2] = null;
    this.storage[3] = null;
  }


  
  public void func_70305_f() {
    this.logPileOpeners--;
    if (this.logPileOpeners == 0 && this.storage[0] == null && this.storage[1] == null && this.storage[2] == null && this.storage[3] == null) {
      
      extinguishFire();
      this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    } 
  }

  
  public boolean contentsMatch(int index, ItemStack is) {
    return (this.storage[index] != null && this.storage[index]
      .func_77973_b() == is.func_77973_b() && this.storage[index]
      .func_77960_j() == is.func_77960_j() && (this.storage[index]).field_77994_a < this.storage[index]
      .func_77976_d() && (this.storage[index]).field_77994_a + 1 <= 
      func_70297_j_());
  }

  
  public int getNumberOfLogs() {
    int[] count = new int[4];
    count[0] = (this.storage[0] != null) ? (this.storage[0]).field_77994_a : 0;
    count[1] = (this.storage[1] != null) ? (this.storage[1]).field_77994_a : 0;
    count[2] = (this.storage[2] != null) ? (this.storage[2]).field_77994_a : 0;
    count[3] = (this.storage[3] != null) ? (this.storage[3]).field_77994_a : 0;
    return count[0] + count[1] + count[2] + count[3];
  }


  
  public ItemStack func_70298_a(int slot, int amount) {
    if (this.storage[slot] != null) {

      
      if ((this.storage[slot]).field_77994_a <= amount) {
        
        ItemStack itemStack = this.storage[slot];
        this.storage[slot] = null;
        return itemStack;
      } 
      
      if ((this.storage[slot]).field_77994_a == 0) {
        this.storage[slot] = null;
      }
      ItemStack is = this.storage[slot].func_77979_a(amount);
      return is;
    } 
    
    return null;
  }

  
  public void ejectContents() {
    for (int i = 0; i < func_70302_i_(); i++) {
      
      if (this.storage[i] != null)
      {
        this.field_145850_b.func_72838_d((Entity)new EntityItem(this.field_145850_b, this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, this.storage[i]));
      }
    } 
    extinguishFire();
  }


  
  public int func_70297_j_() {
    return 4;
  }


  
  public String func_145825_b() {
    return "Log Pile";
  }


  
  public int func_70302_i_() {
    return this.storage.length;
  }


  
  public ItemStack func_70301_a(int slot) {
    return this.storage[slot];
  }


  
  public ItemStack func_70304_b(int slot) {
    return null;
  }

  
  public void injectContents(int index, int count) {
    if (this.storage[index] != null)
    {
      this.storage[index] = new ItemStack(this.storage[index].func_77973_b(), (this.storage[index]).field_77994_a + count, this.storage[index].func_77960_j());
    }
  }


  
  public boolean func_70300_a(EntityPlayer entityplayer) {
    return false;
  }


  
  public void func_70295_k_() {
    this.logPileOpeners++;
  }


  
  public void func_70299_a(int slot, ItemStack is) {
    this.storage[slot] = is;
    if (is != null && is.field_77994_a > func_70297_j_()) {
      is.field_77994_a = func_70297_j_();
    }
  }

  
  public boolean canUpdate() {
    return false;
  }


  
  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
    this.storage = new ItemStack[func_70302_i_()];
    this.isOnFire = nbt.func_74767_n("isOnFire");
    this.fireTimer = nbt.func_74762_e("fireTimer");
    for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
      
      NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
      byte byte0 = nbttagcompound1.func_74771_c("Slot");
      if (byte0 >= 0 && byte0 < this.storage.length) {
        this.storage[byte0] = ItemStack.func_77949_a(nbttagcompound1);
      }
    } 
  }

  
  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    nbt.func_74757_a("isOnFire", this.isOnFire);
    nbt.func_74768_a("fireTimer", this.fireTimer);
    NBTTagList nbttaglist = new NBTTagList();
    for (int i = 0; i < this.storage.length; i++) {
      
      if (this.storage[i] != null) {
        
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.func_74774_a("Slot", (byte)i);
        this.storage[i].func_77955_b(nbttagcompound1);
        nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
      } 
    } 
    nbt.func_74782_a("Items", (NBTBase)nbttaglist);
  }


  
  public Packet func_145844_m() {
    NBTTagCompound nbt = new NBTTagCompound();
    func_145841_b(nbt);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbt);
  }


  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    func_145839_a(pkt.func_148857_g());
  }



  
  public boolean func_145818_k_() {
    return false;
  }


  
  public boolean func_94041_b(int slot, ItemStack is) {
    return false;
  }

  
  public void lightNeighbors() {
    if (!this.isOnFire) {
      return;
    }
    this.blocksToBeSetOnFire = new ArrayDeque<>();
    
    Block block = this.field_145850_b.func_147439_a(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e);

    
    if (Blocks.field_150480_ab.getFlammability(block) > 0) this.field_145850_b.func_147471_g(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e);
    
    block = this.field_145850_b.func_147439_a(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e);

    
    if (Blocks.field_150480_ab.getFlammability(block) > 0) this.field_145850_b.func_147471_g(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e);
    
    block = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1);

    
    if (Blocks.field_150480_ab.getFlammability(block) > 0) this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1);
    
    block = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1);

    
    if (Blocks.field_150480_ab.getFlammability(block) > 0) this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1);
    
    block = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
    if (!TFC_Core.isValidCharcoalPitCover(block)) {
      this.blocksToBeSetOnFire.add(new Vector3f(this.field_145851_c, (this.field_145848_d + 1), this.field_145849_e));
    }
    block = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
    if (!TFC_Core.isValidCharcoalPitCover(block)) {
      this.blocksToBeSetOnFire.add(new Vector3f(this.field_145851_c, (this.field_145848_d - 1), this.field_145849_e));
    }
    setOnFire(this.blocksToBeSetOnFire);
  }

  
  private void setOnFire(Queue<Vector3f> blocksOnFire) {
    while (blocksOnFire.size() > 0) {
      
      Vector3f blockOnFire = blocksOnFire.poll();
      if (this.field_145850_b.func_147439_a((int)blockOnFire.x, (int)blockOnFire.y, (int)blockOnFire.z) != Blocks.field_150480_ab) {
        
        this.field_145850_b.func_147449_b((int)blockOnFire.x, (int)blockOnFire.y, (int)blockOnFire.z, (Block)Blocks.field_150480_ab);
        this.field_145850_b.func_147471_g((int)blockOnFire.x, (int)blockOnFire.y, (int)blockOnFire.z);
      } 
    } 
  }

  
  public void extinguishFire() {
    if (this.isOnFire) {
      
      if (this.field_145850_b.func_147439_a(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) == Blocks.field_150480_ab) {
        
        this.field_145850_b.func_147468_f(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e);
        this.field_145850_b.func_147471_g(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e);
      } 
      if (this.field_145850_b.func_147439_a(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) == Blocks.field_150480_ab) {
        
        this.field_145850_b.func_147468_f(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e);
        this.field_145850_b.func_147471_g(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e);
      } 
      if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1) == Blocks.field_150480_ab) {
        
        this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1);
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1);
      } 
      if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) == Blocks.field_150480_ab) {
        
        this.field_145850_b.func_147468_f(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e - 1);
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1);
      } 
      if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) == Blocks.field_150480_ab) {
        
        this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
      } 
      if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == Blocks.field_150480_ab) {
        
        this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
      } 
      this.isOnFire = false;
    } 
  }

  
  public void activateCharcoal() {
    this.fireTimer = (int)TFC_Time.getTotalHours();
    this.isOnFire = true;

    
    spreadFire(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e);
    spreadFire(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e);
    spreadFire(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
    spreadFire(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
    spreadFire(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1);
    spreadFire(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1);
    
    lightNeighbors();
  }

  
  private void spreadFire(int x, int y, int z) {
    if (this.field_145850_b.func_147439_a(x, y, z) == TFCBlocks.logPile && this.field_145850_b.func_147438_o(x, y, z) instanceof TELogPile) {
      
      TELogPile te = (TELogPile)this.field_145850_b.func_147438_o(x, y, z);
      if (!te.isOnFire)
      {
        te.activateCharcoal();
      }
    } 
  }

  
  public void createCharcoal(int x, int y, int z, boolean forceComplete) {
    if (this.field_145850_b.func_147439_a(x, y, z) == TFCBlocks.logPile) {
      
      TELogPile te = (TELogPile)this.field_145850_b.func_147438_o(x, y, z);
      if (te.isOnFire && (te.fireTimer + TFCOptions.charcoalPitBurnTime < (float)TFC_Time.getTotalHours() || forceComplete)) {
        
        int count = te.getNumberOfLogs();
        te.clearContents();
        float percent = (25 + this.field_145850_b.field_73012_v.nextInt(26));
        count = (int)(count * percent / 100.0F);
        if (count == 0 && Math.random() < 0.7D) { this.field_145850_b.func_147468_f(x, y, z); }
        else if (count > 0) { this.field_145850_b.func_147465_d(x, y, z, TFCBlocks.charcoal, count, 2); }
        
        createCharcoal(x + 1, y, z, forceComplete); createCharcoal(x - 1, y, z, forceComplete);
        createCharcoal(x, y + 1, z, forceComplete); createCharcoal(x, y - 1, z, forceComplete);
        createCharcoal(x, y, z + 1, forceComplete); createCharcoal(x, y, z - 1, forceComplete);
        
        this.field_145850_b.func_147460_e(x, y, z, TFCBlocks.charcoal);
      } 
    } 
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TELogPile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */