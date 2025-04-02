package com.bioxx.tfc.Handlers.Network;

import com.bioxx.tfc.Core.Player.Anvil_Skills;
import com.bioxx.tfc.Core.Player.FoodStatsTFC;
import com.bioxx.tfc.Core.Player.PlayerInventory;
import com.bioxx.tfc.Core.Player.SkillStats;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TerraFirmaCraft;
import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;



public class PlayerUpdatePacket
  extends AbstractPacket
{
  private byte flag;
  private float stomachLevel;
  private float waterLevel;
  private long soberTime;
  private float nutrFruit;
  private float nutrVeg;
  private float nutrGrain;
  private float nutrProtein;
  private float nutrDairy;
  private SkillStats playerSkills;
  private String skillName;
  private int skillLevel;
  private boolean craftingTable;
  private Map<String, Integer> skillMap = new HashMap<>();

  private Anvil_Skills adv_skills = new Anvil_Skills();
  private Anvil_Skills.Anvil_Skill adv_skill = null;


  public PlayerUpdatePacket() {}

  public PlayerUpdatePacket(EntityPlayer p, int f) {
    this.flag = (byte)f;
    if (this.flag == 0) {

      FoodStatsTFC fs = TFC_Core.getPlayerFoodStats(p);
      this.stomachLevel = fs.stomachLevel;
      this.waterLevel = fs.waterLevel;
      this.soberTime = fs.soberTime;
      this.nutrFruit = fs.nutrFruit;
      this.nutrVeg = fs.nutrVeg;
      this.nutrGrain = fs.nutrGrain;
      this.nutrProtein = fs.nutrProtein;
      this.nutrDairy = fs.nutrDairy;
    }
    else if (this.flag == 2) {

      this.craftingTable = p.getEntityData().func_74767_n("craftingTable");
    }
    else if (this.flag == 3) {

      this.playerSkills = TFC_Core.getSkillStats(p);
    }
  }






  public PlayerUpdatePacket(int f, String name, int lvl, EntityPlayer p) {
    this.flag = (byte)f;
    if (this.flag == 1) {

      this.skillName = name;
      this.skillLevel = lvl;


      this.playerSkills = TFC_Core.getSkillStats(p);
      this.adv_skill = this.playerSkills.adv_skills.getSkill(name);
    }
  }



  public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
    buffer.writeByte(this.flag);
    if (this.flag == 0) {

      buffer.writeFloat(this.stomachLevel);
      buffer.writeFloat(this.waterLevel);
      buffer.writeLong(this.soberTime);
      buffer.writeFloat(this.nutrFruit);
      buffer.writeFloat(this.nutrVeg);
      buffer.writeFloat(this.nutrGrain);
      buffer.writeFloat(this.nutrProtein);
      buffer.writeFloat(this.nutrDairy);
    }
    else if (this.flag == 1) {

      ByteBufUtils.writeUTF8String(buffer, this.skillName);
      buffer.writeInt(this.skillLevel);
      if (this.adv_skill != null) for (byte i = 0; i < this.adv_skill.size(); ) { buffer.writeDouble(this.adv_skill.getTierData(i)); i = (byte)(i + 1); }


    } else if (this.flag == 2) {

      buffer.writeBoolean(this.craftingTable);
    }
    else if (this.flag == 3) {

      this.playerSkills.toOutBuffer(buffer);
    }
  }







  public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
    this.flag = buffer.readByte();
    if (this.flag == 0) {

      this.stomachLevel = buffer.readFloat();
      this.waterLevel = buffer.readFloat();
      this.soberTime = buffer.readLong();
      this.nutrFruit = buffer.readFloat();
      this.nutrVeg = buffer.readFloat();
      this.nutrGrain = buffer.readFloat();
      this.nutrProtein = buffer.readFloat();
      this.nutrDairy = buffer.readFloat();
    }
    else if (this.flag == 1) {

      this.skillName = ByteBufUtils.readUTF8String(buffer);
      this.skillLevel = buffer.readInt();
      this.adv_skill = this.adv_skills.getSkill(this.skillName);
      if (this.adv_skill != null) for (byte i = 0; i < this.adv_skill.size(); ) { this.adv_skill.setTierData(i, buffer.readDouble()); i = (byte)(i + 1); }


    } else if (this.flag == 2) {

      this.craftingTable = buffer.readBoolean();
    }
    else if (this.flag == 3) {

      this.skillMap.clear();


      int size = buffer.readInt();
      for (int l = 0; l < size; l++) {

        String name = ByteBufUtils.readUTF8String(buffer);
        int lvl = buffer.readInt();
        this.skillMap.put(name, Integer.valueOf(lvl));
        Anvil_Skills.Anvil_Skill as = this.adv_skills.getSkill(name);
        if (as != null) { byte i; for (i = 0; i < as.size(); ) { as.setTierData(i, buffer.readDouble()); i = (byte)(i + 1); }
           }

      }
    }
  }





  public void handleClientSide(EntityPlayer player) {
    if (this.flag == 0) {

      FoodStatsTFC fs = TFC_Core.getPlayerFoodStats(player);
      fs.stomachLevel = this.stomachLevel;
      fs.waterLevel = this.waterLevel;
      fs.soberTime = this.soberTime;
      fs.nutrFruit = this.nutrFruit;
      fs.nutrVeg = this.nutrVeg;
      fs.nutrGrain = this.nutrGrain;
      fs.nutrProtein = this.nutrProtein;
      fs.nutrDairy = this.nutrDairy;
      TFC_Core.setPlayerFoodStats(player, fs);
    }
    else if (this.flag == 1) {

      this.playerSkills = TFC_Core.getSkillStats(player);
      if (this.adv_skill != null) this.playerSkills.adv_skills.setSkill(this.skillName, this.adv_skill);
      this.playerSkills.setSkillSave(this.skillName, this.skillLevel);
    }
    else if (this.flag == 2) {

      if (this.craftingTable && !player.getEntityData().func_74764_b("craftingTable"))
      {
        player.getEntityData().func_74757_a("craftingTable", this.craftingTable);
        PlayerInventory.upgradePlayerCrafting(player);
      }

    } else if (this.flag == 3) {

      this.playerSkills = TFC_Core.getSkillStats(player);
      this.playerSkills.adv_skills = this.adv_skills;
      for (String skill : this.skillMap.keySet())
      {
        this.playerSkills.setSkillSave(skill, ((Integer)this.skillMap.get(skill)).intValue());
      }
      this.skillMap.clear();
    }
  }







  public void handleServerSide(EntityPlayer player) {
    if (this.flag == 4) {

      AbstractPacket pkt = new PlayerUpdatePacket(player, 3);
      TerraFirmaCraft.PACKET_PIPELINE.sendTo(pkt, (EntityPlayerMP)player);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Network\PlayerUpdatePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */