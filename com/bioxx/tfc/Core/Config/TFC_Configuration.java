package com.bioxx.tfc.Core.Config;

import com.bioxx.tfc.TerraFirmaCraft;
import java.io.File;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;







public class TFC_Configuration
  extends Configuration
{
  public TFC_Configuration(File file) {
    super(file, null);
  }















  public int getInt(String name, String category, int defaultValue, int minValue, int maxValue, String comment, String langKey) {
    Property prop = get(category, name, defaultValue);
    prop.setLanguageKey(langKey);
    prop.comment = comment + " [range: " + minValue + " ~ " + maxValue + ", default: " + defaultValue + "]";
    prop.setMinValue(minValue);
    prop.setMaxValue(maxValue);
    if (prop.getInt(defaultValue) < minValue || prop.getInt(defaultValue) > maxValue) {

      TerraFirmaCraft.LOG.warn("An invalid value has been entered for " + name + " in the config file. Reverting to the default value.");
      prop.set(defaultValue);
      return defaultValue;
    }
    return prop.getInt(defaultValue);
  }















  public float getFloat(String name, String category, float defaultValue, float minValue, float maxValue, String comment, String langKey) {
    Property prop = get(category, name, Float.toString(defaultValue), name);
    prop.setLanguageKey(langKey);
    prop.comment = comment + " [range: " + minValue + " ~ " + maxValue + ", default: " + defaultValue + "]";
    prop.setMinValue(minValue);
    prop.setMaxValue(maxValue);

    try {
      if (Float.parseFloat(prop.getString()) < minValue || Float.parseFloat(prop.getString()) > maxValue) {

        TerraFirmaCraft.LOG.warn("An invalid value has been entered for " + name + " in the config file. Reverting to the default value.");
        prop.set(defaultValue);
        return defaultValue;
      }
      return Float.parseFloat(prop.getString());
    }
    catch (Exception e) {

      TerraFirmaCraft.LOG.catching(e);

      return defaultValue;
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Config\TFC_Configuration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */