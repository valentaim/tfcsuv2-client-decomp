package com.bioxx.tfc.api;



public class Armor
{
  public static Armor[] armorArray = new Armor[256];
  public static Armor leather = new Armor(0, 200, 250, 300, "Leather");
  public static Armor leatherQuiver = new Armor(10, 0, 0, 0, "Leather Quiver");
  public static Armor copperPlate = new Armor(1, 400, 400, 250, "Copper");
  public static Armor bismuthBronzePlate = new Armor(2, 600, 400, 330, "Bismuth Bronze");
  public static Armor blackBronzePlate = new Armor(3, 400, 600, 330, "Black Bronze");
  public static Armor bronzePlate = new Armor(4, 500, 500, 330, "Bronze");
  public static Armor wroughtIronPlate = new Armor(5, 800, 800, 528, "Wrought Iron");
  public static Armor steelPlate = new Armor(6, 1000, 1200, 660, "Steel");
  public static Armor blackSteelPlate = new Armor(7, 2000, 1800, 1320, "Black Steel");
  public static Armor blueSteelPlate = new Armor(8, 2000, 2000, 2500, "Blue Steel");
  public static Armor redSteelPlate = new Armor(9, 2000, 2500, 2000, "Red Steel");
  
  private int armorRatingPiercing;
  private int armorRatingSlashing;
  private int armorRatingCrushing;
  public String metaltype;
  public int[] baseDurability = new int[] { 2500, 3750, 3000, 2500, 0 };
  
  public int armorId;
  
  public Armor(int id, int piercing, int slashing, int crushing, String material) {
    armorArray[id] = this;
    this.armorId = id;
    this.armorRatingPiercing = piercing;
    this.armorRatingSlashing = slashing;
    this.armorRatingCrushing = crushing;
    this.metaltype = material;
  }

  
  public Armor(int id, int piercing, int slashing, int crushing, int[] dura, String material) {
    armorArray[id] = this;
    this.armorId = id;
    this.armorRatingPiercing = piercing;
    this.armorRatingSlashing = slashing;
    this.armorRatingCrushing = crushing;
    this.metaltype = material;
    this.baseDurability = (int[])dura.clone();
  }

  
  public int getDurability(int slot) {
    return this.baseDurability[slot];
  }

  
  public int getPiercingAR() {
    return this.armorRatingPiercing;
  }

  
  public int getSlashingAR() {
    return this.armorRatingSlashing;
  }

  
  public int getCrushingAR() {
    return this.armorRatingCrushing;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Armor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */