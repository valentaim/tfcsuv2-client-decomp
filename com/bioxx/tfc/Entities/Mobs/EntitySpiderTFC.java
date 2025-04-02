package com.bioxx.tfc.Entities.Mobs;

import com.bioxx.tfc.api.Enums.EnumDamageType;
import com.bioxx.tfc.api.Interfaces.ICausesDamage;
import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;


public class EntitySpiderTFC
  extends EntitySpider
  implements ICausesDamage
{
  public EntitySpiderTFC(World par1World) {
    super(par1World);
  }



  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(100.0D);
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1800.0D);
  }



  public EnumDamageType getDamageType() {
    return EnumDamageType.PIERCING;
  }



  public void func_70636_d() {
    super.func_70636_d();
    if (this.field_70153_n != null && this.field_70153_n instanceof EntitySkeleton && !this.field_70170_p.field_72995_K) {

      EntitySkeleton es = (EntitySkeleton)this.field_70153_n;
      es.func_110145_l(es.field_70154_o);
      es.func_70106_y();
    }
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


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\Mobs\EntitySpiderTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */