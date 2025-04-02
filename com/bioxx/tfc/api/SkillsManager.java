package com.bioxx.tfc.api;

import java.util.ArrayList;
import java.util.List;

public class SkillsManager
{
  private List<Skill> skillsArray = new ArrayList<>();
  
  public static SkillsManager instance = new SkillsManager();






  
  public void registerSkill(String name, int rate) {
    this.skillsArray.add(new Skill(name, rate));
  }

  
  public List<Skill> getSkillsArray() {
    return this.skillsArray;
  }

  
  public Skill getSkill(String name) {
    for (Skill s : this.skillsArray) {
      if (s.skillName.equalsIgnoreCase(name))
        return s; 
    }  return null;
  }
  
  public class Skill
  {
    public String skillName;
    public int skillRate;
    
    public Skill(String n, int r) {
      this.skillName = n;
      this.skillRate = r;
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\SkillsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */