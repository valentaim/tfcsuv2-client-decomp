package com.bioxx.tfc.Core.Metal;

import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.api.Metal;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;








public class Alloy
{
  public Alloy(Metal type, EnumTier tier) {
    this();
    this.outputType = type;
    this.outputAmount = 0.0F;
    this.furnaceTier = tier;
  }


  public Alloy(Metal type, float am) {
    this();
    this.outputType = type;
    this.outputAmount = am;
  }



  public List<AlloyMetal> alloyIngred = new ArrayList<>();
  public Metal outputType;
  public float outputAmount;

  public void addIngred(AlloyMetal am) {
    this.alloyIngred.add(am);
  }
  protected EnumTier furnaceTier;
  public Alloy() {}
  public void addIngred(Metal e, float m) {
    this.alloyIngred.add(new AlloyMetal(e, m));
  }


  public void addIngred(Metal e, float min, float max) {
    this.alloyIngred.add(new AlloyMetalCompare(e, min, max));
  }


  public boolean matches(Alloy a) {
    Iterator<AlloyMetal> iter = a.alloyIngred.iterator();
    boolean matches = true;
    while (iter.hasNext() && matches) {

      AlloyMetal am = iter.next();
      matches = searchForAlloyMetal(am);
    }
    return matches;
  }


  public Alloy matches(List<AlloyMetal> a) {
    Iterator<AlloyMetal> iter = a.iterator();
    boolean matches = true;
    int amount = 0;
    while (iter.hasNext() && matches) {

      AlloyMetal am = iter.next();
      matches = searchForAlloyMetal(am);
      amount = (int)(amount + am.metal);
    }

    if (!matches) {
      return null;
    }
    return new Alloy(this.outputType, amount);
  }


  public boolean searchForAlloyMetal(AlloyMetal am) {
    Iterator<AlloyMetal> iter = this.alloyIngred.iterator();
    while (iter.hasNext()) {

      AlloyMetalCompare amc = (AlloyMetalCompare)iter.next();
      if (amc.compare(am))
        return true;
    }
    return false;
  }


  public float getPercentForMetal(Metal m) {
    Iterator<AlloyMetal> iter = this.alloyIngred.iterator();

    while (iter.hasNext()) {

      AlloyMetal amc = iter.next();
      if (amc.metalType == m)
        return amc.metal;
    }
    return 0.0F;
  }

  public enum EnumTier
  {
    TierI(1, "Pit Kiln"),
    TierII(2, "Beehive Kiln"),
    TierIII(3, "Bloomery"),
    TierIV(4, "Blast Furnace"),
    TierV(5, "Crucible"),
    TierVI(6), TierVII(7), TierVIII(8), TierIX(9), TierX(10);

    public final int tier;

    public final String name;

    EnumTier(int t) {
      this.tier = t;
      this.name = name();
    }


    EnumTier(int t, String n) {
      this.tier = t;
      this.name = n;
    }



    public String toString() {
      return this.name;
    }
  }



  public void toPacket(DataOutputStream dos) {
    try {
      if (this.outputType != null) {
        dos.writeUTF(this.outputType.name);
      } else {
        dos.writeUTF("Unknown");
      }
      dos.writeFloat(this.outputAmount);
      dos.writeInt(this.alloyIngred.size());
      for (int i = 0; i < this.alloyIngred.size(); i++)
      {
        AlloyMetal am = this.alloyIngred.get(i);
        dos.writeUTF(am.metalType.name);
        dos.writeFloat(am.metal);
      }

    } catch (IOException e) {

      TerraFirmaCraft.LOG.catching(e);
    }
  }



  public Alloy fromPacket(DataInputStream dis) {
    try {
      this.outputType = MetalRegistry.instance.getMetalFromString(dis.readUTF());
      this.outputAmount = dis.readFloat();
      int size = dis.readInt();
      for (int i = 0; i < size; i++)
      {
        AlloyMetal am = new AlloyMetal(MetalRegistry.instance.getMetalFromString(dis.readUTF()), dis.readFloat());
        this.alloyIngred.add(am);
      }

    } catch (IOException e) {

      TerraFirmaCraft.LOG.catching(e);
    }
    return this;
  }


  public void toNBT(NBTTagCompound nbt) {
    nbt.func_74778_a("outputType", this.outputType.name);
    nbt.func_74776_a("outputAmount", this.outputAmount);
    NBTTagList nbtlist = new NBTTagList();
    for (int i = 0; i < this.alloyIngred.size(); i++) {

      NBTTagCompound nbt1 = new NBTTagCompound();
      AlloyMetal am = this.alloyIngred.get(i);
      nbt1.func_74778_a("metalType", am.metalType.name);
      nbt1.func_74776_a("amount", am.metal);
      nbtlist.func_74742_a((NBTBase)nbt1);
    }
    nbt.func_74782_a("metalList", (NBTBase)nbtlist);
  }


  public Alloy fromNBT(NBTTagCompound nbt) {
    this.outputType = MetalRegistry.instance.getMetalFromString(nbt.func_74779_i("outputType"));
    this.outputAmount = nbt.func_74760_g("outputAmount");
    NBTTagList nbtlist = nbt.func_150295_c("metalList", 10);
    for (int i = 0; i < nbtlist.func_74745_c(); i++) {

      NBTTagCompound nbt1 = nbtlist.func_150305_b(i);
      AlloyMetal am = new AlloyMetal(MetalRegistry.instance.getMetalFromString(nbt1.func_74779_i("metalType")), nbt1.func_74760_g("amount"));
      this.alloyIngred.add(am);
    }
    return this;
  }


  public EnumTier getFurnaceTier() {
    return this.furnaceTier;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Metal\Alloy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */