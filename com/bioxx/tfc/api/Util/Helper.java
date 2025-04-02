package com.bioxx.tfc.api.Util;

import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Interfaces.ISize;
import java.lang.reflect.Field;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;





public class Helper
{
  public static MovingObjectPosition getMouseOverObject(EntityLivingBase player, World world) {
    return getMovingObjectPositionFromPlayer(world, player, true);
  }


  public static double getReachDistance(World par1World, EntityLivingBase entity, boolean par3) {
    double var21 = 1.0D;
    if (entity.func_70694_bm() != null && entity.func_70694_bm().func_77973_b() instanceof ISize) {
      var21 *= (((ISize)entity.func_70694_bm().func_77973_b()).getReach(null)).multiplier;
    }
    else {

      var21 *= EnumItemReach.SHORT.multiplier;
    }
    return var21;
  }





  public static int stringToInt(String s) {
    int result = 0;
    for (char c : s.toCharArray())
    {
      result += (byte)c;
    }
    return result;
  }


  public static MovingObjectPosition getMovingObjectPositionFromPlayer(World world, EntityLivingBase entity, boolean scanFluids) {
    return getMovingObjectPositionFromPlayer(world, entity, scanFluids, 4);
  }


  public static MovingObjectPosition getMovingObjectPositionFromPlayer(World world, EntityLivingBase entity, boolean scanFluids, int reach) {
    float var4 = 1.0F;
    float var5 = entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * var4;
    float var6 = entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * var4;
    double var7 = entity.field_70169_q + (entity.field_70165_t - entity.field_70169_q) * var4;
    double var9 = entity.field_70167_r + (entity.field_70163_u - entity.field_70167_r) * var4 + 1.62D - entity.field_70129_M;
    double var11 = entity.field_70166_s + (entity.field_70161_v - entity.field_70166_s) * var4;
    Vec3 var13 = Vec3.func_72443_a(var7, var9, var11);
    float var14 = MathHelper.func_76134_b(-var6 * 0.017453292F - 3.1415927F);
    float var15 = MathHelper.func_76126_a(-var6 * 0.017453292F - 3.1415927F);
    float var16 = -MathHelper.func_76134_b(-var5 * 0.017453292F);
    float var17 = MathHelper.func_76126_a(-var5 * 0.017453292F);
    float var18 = var15 * var16;
    float var20 = var14 * var16;

    Vec3 var23 = var13.func_72441_c((var18 * reach), (var17 * reach), (var20 * reach));
    return world.func_72901_a(var13, var23, scanFluids);
  }






  public static float clampFloat(float par0, float par1, float par2) {
    return (par0 < par1) ? par1 : ((par0 > par2) ? par2 : par0);
  }


  public static float roundNumber(float input, float rounding) {
    int o = (int)(input * rounding);
    return o / rounding;
  }


  private static boolean usesSRG(Object obj, String srgName) {
    Field[] fields = obj.getClass().getFields();
    for (Field f : fields) {

      if (f.getName().equals(srgName))
        return true;
    }
    return false;
  }


  public static int getInteger(Object obj, String srgName, String obfName, String deobfName, boolean useDeobf) {
    Field f = null;

    try {
      if (!useDeobf) {
        f = obj.getClass().getDeclaredField(deobfName);
      } else if (usesSRG(obj, srgName)) {
        f = obj.getClass().getDeclaredField(srgName);
      } else {
        f = obj.getClass().getDeclaredField(obfName);
      }  f.setAccessible(true);
      return ((Integer)f.get(obj)).intValue();
    } catch (NoSuchFieldException e) {
      TerraFirmaCraft.LOG.catching(e);
    } catch (SecurityException e) {
      TerraFirmaCraft.LOG.catching(e);
    } catch (IllegalArgumentException e) {
      TerraFirmaCraft.LOG.catching(e);
    } catch (IllegalAccessException e) {
      TerraFirmaCraft.LOG.catching(e);
    }
    return 0;
  }


  public static boolean getBoolean(Object obj, String srgName, String obfName, String deobfName, boolean useDeobf) {
    Field f = null;

    try {
      if (!useDeobf) {
        f = obj.getClass().getDeclaredField(deobfName);
      } else if (usesSRG(obj, srgName)) {
        f = obj.getClass().getDeclaredField(srgName);
      } else {
        f = obj.getClass().getDeclaredField(obfName);
      }  f.setAccessible(true);
      return ((Boolean)f.get(obj)).booleanValue();
    } catch (NoSuchFieldException e) {
      TerraFirmaCraft.LOG.catching(e);
    } catch (SecurityException e) {
      TerraFirmaCraft.LOG.catching(e);
    } catch (IllegalArgumentException e) {
      TerraFirmaCraft.LOG.catching(e);
    } catch (IllegalAccessException e) {
      TerraFirmaCraft.LOG.catching(e);
    }
    return false;
  }


  public static Object getObject(Object obj, String srgName, String obfName, String deobfName, boolean useDeobf) {
    Field f = null;

    try {
      if (!useDeobf) {
        f = obj.getClass().getDeclaredField(deobfName);
      } else if (usesSRG(obj, srgName)) {
        f = obj.getClass().getDeclaredField(srgName);
      } else {
        f = obj.getClass().getDeclaredField(obfName);
      }  f.setAccessible(true);
      return f.get(obj);
    } catch (NoSuchFieldException e) {
      TerraFirmaCraft.LOG.catching(e);
    } catch (SecurityException e) {
      TerraFirmaCraft.LOG.catching(e);
    } catch (IllegalArgumentException e) {
      TerraFirmaCraft.LOG.catching(e);
    } catch (IllegalAccessException e) {
      TerraFirmaCraft.LOG.catching(e);
    }
    return null;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Util\Helper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */