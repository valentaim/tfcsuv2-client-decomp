package com.bioxx.tfc.Entities.AI;

import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Entities.Mobs.EntityChickenTFC;
import com.bioxx.tfc.TileEntities.TENestBox;
import com.bioxx.tfc.api.Entities.IAnimal;
import com.bioxx.tfc.api.TFCBlocks;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;












public class EntityAIFindNest
  extends EntityAIBase
{
  private EntityCreature theCreature;
  private int currentTick;
  private World theWorld;
  private int movement;
  private final double field_75404_b;
  private int maxSittingTicks;
  private Map<String, Long> failureDepressionMap;
  private double compoundDistance;
  private int lastCheckedTick;
  private boolean end;
  private int sitableBlockX = -1;


  private int sitableBlockY = -1;


  private int sitableBlockZ = -1;


  public EntityAIFindNest(EntityAnimal eAnimal, double par2) {
    this.theCreature = (EntityCreature)eAnimal;

    this.field_75404_b = par2;
    this.theWorld = eAnimal.field_70170_p;
    this.failureDepressionMap = new HashMap<>();
    func_75248_a(5);
  }






  public boolean func_75250_a() {
    if (this.theCreature instanceof EntityChickenTFC && !(this.theCreature instanceof com.bioxx.tfc.Entities.Mobs.EntityPheasantTFC))
    {
      return (((EntityChickenTFC)this.theCreature).isAdult() && ((EntityChickenTFC)this.theCreature).getFamiliarity() >= 15 && this.theWorld
        .func_147439_a((int)this.theCreature.field_70165_t, (int)this.theCreature.field_70163_u, (int)this.theCreature.field_70161_v) != TFCBlocks.nestBox && this.theWorld
        .func_147439_a((int)this.theCreature.field_70165_t, (int)this.theCreature.field_70163_u - 1, (int)this.theCreature.field_70161_v) != TFCBlocks.nestBox &&
        getNearbySitableBlockDistance() && ((EntityChickenTFC)this.theCreature)
        .getGender() == IAnimal.GenderEnum.FEMALE);
    }











    return false;
  }



  public boolean func_75253_b() {
    if (this.theCreature.func_70092_e(this.sitableBlockX + 0.5D, this.sitableBlockY, this.sitableBlockZ + 0.5D) < 0.2D) {
      this.theCreature.func_70661_as().func_75499_g();
    }
    if (this.end) {

      this.end = false;
      return this.end;
    }
    return (this.currentTick <= this.maxSittingTicks && this.movement <= 60 && isSittableBlock(this.theCreature.field_70170_p, this.sitableBlockX, this.sitableBlockY, this.sitableBlockZ));
  }


  protected boolean getNearbySitableBlockDistance() {
    int i = (int)this.theCreature.field_70163_u;
    double d0 = 2.147483647E9D;

    for (int j = (int)this.theCreature.field_70165_t - 16; j < this.theCreature.field_70165_t + 16.0D; j++) {

      for (int k = (int)this.theCreature.field_70161_v - 16; k < this.theCreature.field_70161_v + 16.0D; k++) {

        for (int l = i; l < i + 4; l++) {

          if (isSittableBlock(this.theCreature.field_70170_p, j, l, k) && this.theCreature.field_70170_p.func_147437_c(j, l + 1, k)) {

            double d1 = this.theCreature.func_70092_e(j, l, k);

            if (d1 < d0) {

              this.sitableBlockX = j;
              this.sitableBlockY = l;
              this.sitableBlockZ = k;
              d0 = d1;
            }
          }
        }
      }
    }
    return (d0 < 2.147483647E9D);
  }





  public void func_75249_e() {
    this.theCreature.func_70661_as().func_75492_a(this.sitableBlockX + 0.5D, (this.sitableBlockY + 1), this.sitableBlockZ + 0.5D, this.field_75404_b);
    this.currentTick = 0;
    this.movement = 0;
    this.compoundDistance = 0.0D;
    this.lastCheckedTick = 0;
    this.end = false;
    this.maxSittingTicks = this.theCreature.func_70681_au().nextInt(this.theCreature.func_70681_au().nextInt(1200) + 1200) + 1200;
  }



  public void func_75246_d() {
    this.currentTick++;

    if (this.theCreature.func_70092_e(this.sitableBlockX, (this.sitableBlockY + 1), this.sitableBlockZ) > 1.0D) {

      this.theCreature.func_70661_as().func_75492_a(this.sitableBlockX + 0.5D, (this.sitableBlockY + 1), this.sitableBlockZ + 0.5D, this.field_75404_b);
      this.movement++;
      this.compoundDistance += this.theCreature.func_70011_f(this.theCreature.field_70142_S, this.theCreature.field_70137_T, this.theCreature.field_70136_U);
      if (this.currentTick - 40 > this.lastCheckedTick) {

        ArrayList<EntityChickenTFC> crowd = (ArrayList<EntityChickenTFC>)this.theCreature.field_70170_p.func_72872_a(EntityChickenTFC.class, this.theCreature.field_70121_D.func_72314_b(24.0D, 2.0D, 24.0D));
        ArrayList<EntityChickenTFC> invalid = new ArrayList<>();
        for (EntityChickenTFC chicken : crowd) {
          if (chicken.getGender().equals(IAnimal.GenderEnum.MALE) || chicken.func_70631_g_()) {
            invalid.add(chicken);
          }
        }
        crowd.removeAll(invalid);
        crowd.remove(this.theCreature);
        if (this.compoundDistance < 0.5D || crowd.size() >= 10)
        {
          this.failureDepressionMap.put(this.sitableBlockX + "," + this.sitableBlockY + "," + this.sitableBlockZ, Long.valueOf(TFC_Time.getTotalTicks() + 24000L));
          this.end = true;
        }
        else
        {
          this.lastCheckedTick = this.currentTick;
        }

      }
    } else {

      this.movement--;
    }
  }





  protected boolean isSittableBlock(World world, int x, int y, int z) {
    if (this.failureDepressionMap.containsKey(x + "," + y + "," + z)) {

      long time = ((Long)this.failureDepressionMap.get(x + "," + y + "," + z)).longValue();
      if (time > TFC_Time.getTotalTicks()) {
        return false;
      }
      this.failureDepressionMap.remove(new int[] { x, y, z });
    }

    Block block = world.func_147439_a(x, y, z);

    if (block == TFCBlocks.nestBox) {

      TENestBox tileentitynest = (TENestBox)world.func_147438_o(x, y, z);

      if (!tileentitynest.hasBird() || tileentitynest.getBird() == this.theCreature) {
        return true;
      }
    }
    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\AI\EntityAIFindNest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */