package com.bioxx.tfc.Entities.Mobs;

import com.bioxx.tfc.Core.TFC_Achievements;
import com.bioxx.tfc.api.Interfaces.IInnateArmor;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.StatBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityCreeperTFC
  extends EntityCreeper
  implements IInnateArmor {
  public EntityCreeperTFC(World par1World) {
    super(par1World);
  }



  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(500.0D);
  }



  public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
    Entity entity = par1DamageSource.func_76346_g();
    if (entity != null && entity instanceof EntityPlayer && ((EntityPlayer)entity)
      .func_70694_bm() != null && ((EntityPlayer)entity).func_70694_bm().func_77973_b().equals(TFCItems.stick)) {
      ((EntityPlayer)entity).func_71029_a((StatBase)TFC_Achievements.achPokeCreeper);
    }
    return super.func_70097_a(par1DamageSource, par2);
  }


  public int getCrushArmor() {
    return 1000;
  }

  public int getSlashArmor() {
    return 0;
  }

  public int getPierceArmor() {
    return -335;
  }



  public boolean func_70601_bi() {
    int x = MathHelper.func_76128_c(this.field_70165_t);
    int y = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
    int z = MathHelper.func_76128_c(this.field_70161_v);
    Block b = this.field_70170_p.func_147439_a(x, y, z);

    if (b == TFCBlocks.leaves || b == TFCBlocks.leaves2 || b == TFCBlocks.thatch) {
      return false;
    }
    return super.func_70601_bi();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\Mobs\EntityCreeperTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */