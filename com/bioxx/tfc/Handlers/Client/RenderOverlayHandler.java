package com.bioxx.tfc.Handlers.Client;

import com.bioxx.tfc.Core.Player.FoodStatsTFC;
import com.bioxx.tfc.Core.Player.InventoryPlayerTFC;
import com.bioxx.tfc.Core.Player.PlayerInfo;
import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Items.ItemQuiver;
import com.bioxx.tfc.WorldGen.DataLayer;
import com.bioxx.tfc.api.TFCAttributes;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFCOptions;
import com.bioxx.tfc.api.Tools.ChiselManager;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import java.awt.Color;
import java.lang.reflect.Field;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;









public class RenderOverlayHandler
{
  public static ResourceLocation tfcicons = new ResourceLocation("terrafirmacraft", "textures/gui/icons.png");
  private FontRenderer fontrenderer;
  public int recordTimer;
  private final Field _recordPlayingUpFor = ReflectionHelper.findField(GuiIngame.class, new String[] { "recordPlayingUpFor", "field_73845_h" });
  private final Field _recordPlaying = ReflectionHelper.findField(GuiIngame.class, new String[] { "recordPlaying", "field_73838_g" });



  @SubscribeEvent
  public void renderText(RenderGameOverlayEvent.Chat event) {
    if ((Minecraft.func_71410_x()).field_71442_b.func_78758_h()) {
      event.posY += 4;
    } else {
      event.posY -= 12;
    }
  }

  @SubscribeEvent
  public void render(RenderGameOverlayEvent.Pre event) {
    GuiIngameForge.renderFood = false;


    if (event.type != RenderGameOverlayEvent.ElementType.CROSSHAIRS) {
      return;
    }

    GuiIngameForge.right_height += 10;

    ScaledResolution sr = event.resolution;
    Minecraft mc = Minecraft.func_71410_x();
    EntityClientPlayerMP entityClientPlayerMP = mc.field_71439_g;
    InventoryPlayer playerInventory = ((EntityPlayer)entityClientPlayerMP).field_71071_by;
    PlayerInfo playerInfo = PlayerManagerTFC.getInstance().getClientPlayer();

    int healthRowHeight = sr.func_78328_b() - 40;
    int armorRowHeight = healthRowHeight - 10;
    int mid = sr.func_78326_a() / 2;

    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    TFC_Core.bindTexture(tfcicons);


    if (TFCOptions.enableToolModeIndicator && playerInventory.func_70448_g() != null && playerInfo != null) {

      Item currentItem = playerInventory.func_70448_g().func_77973_b();

      if (currentItem instanceof com.bioxx.tfc.Items.Tools.ItemCustomHoe) {

        int mode = playerInfo.hoeMode;
        drawTexturedModalRect(mid + 95, sr.func_78328_b() - 21, 0 + 20 * mode, 38, 20, 20);
      }
      else if (currentItem instanceof com.bioxx.tfc.Items.Tools.ItemChisel) {

        boolean hasHammer = false;

        for (int i = 0; i < 9; i++) {

          if (playerInventory.field_70462_a[i] != null && playerInventory.field_70462_a[i].func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemHammer) {

            hasHammer = true;
            break;
          }
        }
        if (hasHammer) {

          int mode = playerInfo.chiselMode;
          TFC_Core.bindTexture(ChiselManager.getInstance().getResourceLocation(mode));
          drawTexturedModalRect(mid + 95, sr.func_78328_b() - 21, ChiselManager.getInstance().getTextureU(mode), ChiselManager.getInstance().getTextureV(mode), 20, 20);
          TFC_Core.bindTexture(tfcicons);
        }
      }
    }


    ItemStack quiverStack = getQuiver();
    Item quiver = (quiverStack != null) ? quiverStack.func_77973_b() : null;
    if (quiver instanceof ItemQuiver) {

      this.fontrenderer = mc.field_71466_p;



      int xPos = sr.func_78326_a() - 19;
      int yPos = sr.func_78328_b() - 34;

      boolean leftSide = false;

      String pos = TFCOptions.quiverHUDPosition;

      if ("topright".equalsIgnoreCase(pos)) {

        xPos = sr.func_78326_a() - 19;
        yPos = 1;
        leftSide = false;
      }
      else if ("right".equalsIgnoreCase(pos)) {

        xPos = sr.func_78326_a() - 19;
        yPos = (sr.func_78328_b() - 34) / 2;
        leftSide = false;
      }
      else if ("bottomright".equalsIgnoreCase(pos)) {

        xPos = sr.func_78326_a() - 19;
        yPos = sr.func_78328_b() - 34;
        leftSide = false;
      }
      else if ("topleft".equalsIgnoreCase(pos)) {

        xPos = 1;
        yPos = 1;
        leftSide = true;
      }
      else if ("left".equalsIgnoreCase(pos)) {

        xPos = 1;
        yPos = (sr.func_78328_b() - 34) / 2;
        leftSide = true;
      }

      drawTexturedModalRect(xPos, yPos, 0, 78, 16, 16);
      drawTexturedModalRect(xPos, yPos + 17, 0, 94, 16, 16);

      if (leftSide) {

        this.fontrenderer.func_78276_b(Integer.toString(getQuiverArrows()), xPos + 19, yPos + 4, Color.white.getRGB());
        this.fontrenderer.func_78276_b(Integer.toString(getQuiverJavelins()), xPos + 19, yPos + 21, Color.white.getRGB());
      }
      else {

        int arrowOffset = this.fontrenderer.func_78256_a(String.valueOf(getQuiverArrows())) + 1;
        int javOffset = this.fontrenderer.func_78256_a(String.valueOf(getQuiverJavelins())) + 1;

        this.fontrenderer.func_78276_b(Integer.toString(getQuiverArrows()), xPos - arrowOffset, yPos + 4, Color.white.getRGB());
        this.fontrenderer.func_78276_b(Integer.toString(getQuiverJavelins()), xPos - javOffset, yPos + 21, Color.white.getRGB());
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      TFC_Core.bindTexture(tfcicons);
    }

    if (mc.field_71442_b.func_78763_f()) {


      drawTexturedModalRect(mid - 91, healthRowHeight, 0, 0, 90, 10);
      float maxHealth = entityClientPlayerMP.func_110138_aP();
      float percentHealth = Math.min(entityClientPlayerMP.func_110143_aJ() / maxHealth, 1.0F);
      drawTexturedModalRect(mid - 91, healthRowHeight, 0, 10, (int)(90.0F * percentHealth), 10);


      FoodStatsTFC foodstats = TFC_Core.getPlayerFoodStats((EntityPlayer)entityClientPlayerMP);
      float foodLevel = foodstats.getFoodLevel();


      float waterLevel = foodstats.waterLevel;

      float percentFood = Math.min(foodLevel / foodstats.getMaxStomach((EntityPlayer)entityClientPlayerMP), 1.0F);
      float percentWater = Math.min(waterLevel / foodstats.getMaxWater((EntityPlayer)entityClientPlayerMP), 1.0F);

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      drawTexturedModalRect(mid + 1, healthRowHeight, 0, 20, 90, 5);
      if (playerInfo != null && playerInfo.guishowFoodRestoreAmount) {

        float percentFood2 = Math.min(percentFood + playerInfo.guiFoodRestoreAmount / foodstats.getMaxStomach((EntityPlayer)entityClientPlayerMP), 1.0F);
        GL11.glColor4f(0.0F, 0.6F, 0.0F, 0.3F);
        drawTexturedModalRect(mid + 1, healthRowHeight, 0, 25, (int)(90.0F * percentFood2), 5);
      }
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

      drawTexturedModalRect(mid + 1, healthRowHeight, 0, 25, (int)(90.0F * percentFood), 5);

      drawTexturedModalRect(mid + 1, healthRowHeight + 5, 90, 20, 90, 5);
      drawTexturedModalRect(mid + 1, healthRowHeight + 5, 90, 25, (int)(90.0F * percentWater), 5);


      String healthString = (int)Math.min(entityClientPlayerMP.func_110143_aJ(), maxHealth) + "/" + (int)maxHealth;
      mc.field_71466_p.func_78276_b(healthString, mid - 45 - mc.field_71466_p.func_78256_a(healthString) / 2, healthRowHeight + 2, Color.white.getRGB());
      if (entityClientPlayerMP.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111127_a(TFCAttributes.OVERBURDENED_UUID) != null) {
        mc.field_71466_p.func_78276_b(TFC_Core.translate("gui.overburdened"), mid - mc.field_71466_p.func_78256_a(TFC_Core.translate("gui.overburdened")) / 2, healthRowHeight - 20, Color.red.getRGB());
      }
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      TFC_Core.bindTexture(new ResourceLocation("minecraft:textures/gui/icons.png"));


      if (!(((EntityPlayer)entityClientPlayerMP).field_70154_o instanceof net.minecraft.entity.EntityLiving)) {

        int cap = 0;
        cap = entityClientPlayerMP.func_71050_bK();
        int left = mid - 91;

        if (cap > 0) {

          short barWidth = 182;
          int filled = (int)(((EntityPlayer)entityClientPlayerMP).field_71106_cc * (barWidth + 1));
          int top = sr.func_78328_b() - 29;
          drawTexturedModalRect(left, top, 0, 64, barWidth, 5);
          if (filled > 0) {
            drawTexturedModalRect(left, top, 0, 69, filled, 5);
          }
        }
        if (((EntityPlayer)entityClientPlayerMP).field_71068_ca > 0) {

          this.fontrenderer = mc.field_71466_p;
          boolean flag1 = false;
          int color = flag1 ? 16777215 : 8453920;
          String text = Integer.toString(((EntityPlayer)entityClientPlayerMP).field_71068_ca);
          int x = (sr.func_78326_a() - this.fontrenderer.func_78256_a(text)) / 2;
          int y = sr.func_78328_b() - 30;
          this.fontrenderer.func_78276_b(text, x + 1, y, 0);
          this.fontrenderer.func_78276_b(text, x - 1, y, 0);
          this.fontrenderer.func_78276_b(text, x, y + 1, 0);
          this.fontrenderer.func_78276_b(text, x, y - 1, 0);
          this.fontrenderer.func_78276_b(text, x, y, color);
        }


        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      }


      if (mc.field_71462_r instanceof com.bioxx.tfc.GUI.GuiScreenHorseInventoryTFC) {

        this.recordTimer = 0;

        try {
          this._recordPlayingUpFor.setInt(mc.field_71456_v, 0);
        } catch (Exception e) {

          throw new RuntimeException(e);
        }
      }


      if (((EntityPlayer)entityClientPlayerMP).field_70154_o instanceof EntityLivingBase) {

        GuiIngameForge.renderHealthMount = false;
        TFC_Core.bindTexture(tfcicons);
        EntityLivingBase mount = (EntityLivingBase)((EntityPlayer)entityClientPlayerMP).field_70154_o;
        drawTexturedModalRect(mid + 1, armorRowHeight, 90, 0, 90, 10);
        double mountMaxHealth = mount.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b();
        double mountCurrentHealth = mount.func_110143_aJ();
        float mountPercentHealth = (float)Math.min(mountCurrentHealth / mountMaxHealth, 1.0D);
        drawTexturedModalRect(mid + 1, armorRowHeight, 90, 10, (int)(90.0F * mountPercentHealth), 10);

        String mountHealthString = (int)Math.min(mountCurrentHealth, mountMaxHealth) + "/" + (int)mountMaxHealth;
        mc.field_71466_p.func_78276_b(mountHealthString, mid + 47 - mc.field_71466_p.func_78256_a(mountHealthString) / 2, armorRowHeight + 2, Color.white.getRGB());
        renderDismountOverlay(mc, mid, sr.func_78328_b(), event.partialTicks);
      }

      TFC_Core.bindTexture(new ResourceLocation("minecraft:textures/gui/icons.png"));
    }
  }








  protected void renderDismountOverlay(Minecraft mc, int midpoint, int height, float partialTicks) {
    if (this.recordTimer == 0) {

      try {

        this.recordTimer = this._recordPlayingUpFor.getInt(mc.field_71456_v);
        this._recordPlayingUpFor.setInt(mc.field_71456_v, 0);
      }
      catch (Exception e) {

        throw new RuntimeException(e);
      }
    }

    if (this.recordTimer > 0) {

      float hue = this.recordTimer - partialTicks;
      int opacity = (int)(hue * 256.0F / 20.0F);
      if (opacity > 255) {
        opacity = 255;
      }
      if (opacity > 0) {

        try {

          String recordPlaying = (String)this._recordPlaying.get(mc.field_71456_v);

          GL11.glPushMatrix();
          GL11.glTranslatef(midpoint, (height - 48), 0.0F);
          GL11.glEnable(3042);
          OpenGlHelper.func_148821_a(770, 771, 1, 0);
          mc.field_71466_p.func_78276_b(recordPlaying, -mc.field_71466_p.func_78256_a(recordPlaying) / 2, -12, 0xFFFFFF | opacity << 24);
          GL11.glDisable(3042);
          GL11.glPopMatrix();
        }
        catch (Exception e) {

          throw new RuntimeException(e);
        }
      }

      this.recordTimer--;
    }
  }


  @SubscribeEvent
  public void renderText(RenderGameOverlayEvent.Text event) {
    Minecraft mc = Minecraft.func_71410_x();
    if (mc.field_71474_y.field_74330_P || TFCOptions.enableDebugMode) {

      EntityClientPlayerMP entityClientPlayerMP = mc.field_71439_g;
      int xCoord = (int)((EntityPlayer)entityClientPlayerMP).field_70165_t;
      int yCoord = (int)((EntityPlayer)entityClientPlayerMP).field_70163_u;
      int zCoord = (int)((EntityPlayer)entityClientPlayerMP).field_70161_v;
      DataLayer evt = TFC_Climate.getCacheManager((World)mc.field_71441_e).getEVTLayerAt(xCoord, zCoord);
      event.left.add(String.format("rain: %.0f, temp: %.2f, average bio temp: %.2f, evt: %.3f", new Object[] {
              Float.valueOf(TFC_Climate.getRainfall((World)mc.field_71441_e, xCoord, yCoord, zCoord)),
              Float.valueOf(TFC_Climate.getHeightAdjustedTemp((World)mc.field_71441_e, xCoord, yCoord, zCoord)),
              Float.valueOf(TFC_Climate.getBioTemperatureHeight((World)mc.field_71441_e, xCoord, yCoord, zCoord)),
              Float.valueOf(evt.floatdata1)
            }));
      if (TFCOptions.enableDebugMode) {

        event.left.add("Stability: " + TFC_Climate.getStability((World)mc.field_71441_e, xCoord, zCoord) + ", Drainage: " +
            TFC_Climate.getCacheManager((World)mc.field_71441_e).getDrainageLayerAt(xCoord, zCoord).getName() + ", pH: " +
            TFC_Climate.getCacheManager((World)mc.field_71441_e).getPHLayerAt(xCoord, zCoord).getName());
        event.left.add("Rock Layers: " + TFC_Climate.getCacheManager((World)mc.field_71441_e).getRockLayerAt(xCoord, zCoord, 0).getName() + ", " +
            TFC_Climate.getCacheManager((World)mc.field_71441_e).getRockLayerAt(xCoord, zCoord, 1).getName() + ", " +
            TFC_Climate.getCacheManager((World)mc.field_71441_e).getRockLayerAt(xCoord, zCoord, 2).getName());
        event.left.add("Tree Layers: " + TFC_Climate.getCacheManager((World)mc.field_71441_e).getTreeLayerAt(xCoord, zCoord, 0).getName() + ", " +
            TFC_Climate.getCacheManager((World)mc.field_71441_e).getTreeLayerAt(xCoord, zCoord, 1).getName() + ", " +
            TFC_Climate.getCacheManager((World)mc.field_71441_e).getTreeLayerAt(xCoord, zCoord, 2).getName());
      }
    }
  }


  private ItemStack getQuiver() {
    Minecraft mc = Minecraft.func_71410_x();
    EntityClientPlayerMP entityClientPlayerMP = mc.field_71439_g;
    ItemStack quiver = null;
    if (((EntityPlayer)entityClientPlayerMP).field_71071_by instanceof InventoryPlayerTFC) {
      quiver = ((InventoryPlayerTFC)((EntityPlayer)entityClientPlayerMP).field_71071_by).extraEquipInventory[0];
    }
    return quiver;
  }


  private int getQuiverArrows() {
    return ((ItemQuiver)TFCItems.quiver).getQuiverArrows(getQuiver());
  }


  private int getQuiverJavelins() {
    return ((ItemQuiver)TFCItems.quiver).getQuiverJavelins(getQuiver());
  }


  public void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6) {
    float f = 0.00390625F;
    float f1 = 0.00390625F;
    Tessellator tessellator = Tessellator.field_78398_a;
    tessellator.func_78382_b();
    tessellator.func_78374_a((par1 + 0), (par2 + par6), 0.0D, ((par3 + 0) * f), ((par4 + par6) * f1));
    tessellator.func_78374_a((par1 + par5), (par2 + par6), 0.0D, ((par3 + par5) * f), ((par4 + par6) * f1));
    tessellator.func_78374_a((par1 + par5), (par2 + 0), 0.0D, ((par3 + par5) * f), ((par4 + 0) * f1));
    tessellator.func_78374_a((par1 + 0), (par2 + 0), 0.0D, ((par3 + 0) * f), ((par4 + 0) * f1));
    tessellator.func_78381_a();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Client\RenderOverlayHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */