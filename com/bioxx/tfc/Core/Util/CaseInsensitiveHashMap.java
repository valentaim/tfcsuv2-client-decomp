package com.bioxx.tfc.Core.Util;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;









public class CaseInsensitiveHashMap<V>
  extends HashMap<String, V>
{
  public CaseInsensitiveHashMap(int initialCapacity, float loadFactor) {
    super(initialCapacity, loadFactor);
  }


  public CaseInsensitiveHashMap(int initialCapacity) {
    super(initialCapacity);
  }



  public CaseInsensitiveHashMap() {}



  public CaseInsensitiveHashMap(Map<? extends String, ? extends V> m) {
    super((Map)toLowercase(m));
  }



  private static Map<String, Object> toLowercase(Map<? extends String, ?> m) {
    ImmutableMap.Builder<String, Object> temp = ImmutableMap.builder();



    for (Map.Entry<? extends String, ?> entry : m.entrySet())
    {
      temp.put(toLowercase(entry.getKey()), entry.getValue());
    }
    return (Map<String, Object>)temp.build();
  }


  private static String toString(Object o) {
    if (o == null) return null;
    return o.toString();
  }


  private static String toLowercase(String key) {
    if (Strings.isNullOrEmpty(key)) return key;
    return key.toLowerCase(Locale.ENGLISH);
  }




  public V get(Object key) {
    return get(toString(key));
  }



  public V put(String key, V value) {
    return super.put(toLowercase(key), value);
  }



  public void putAll(Map<? extends String, ? extends V> m) {
    super.putAll((Map)toLowercase(m));
  }



  public V remove(Object key) {
    return remove(toString(key));
  }



  public V get(String key) {
    return super.get(toLowercase(key));
  }


  public V remove(String key) {
    return super.remove(toLowercase(key));
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Util\CaseInsensitiveHashMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */