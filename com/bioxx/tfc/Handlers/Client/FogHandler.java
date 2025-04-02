package com.bioxx.tfc.Handlers.Client;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.WeatherManager;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import org.lwjgl.opengl.GL11;





public class FogHandler
{
  private double lerpTime = 14.0D;

  private double lerpTimer;
  private boolean rainLast;
  private boolean snowLast;
  private boolean shouldLerp;
  private float fogEnd;
  private float fogStart;
  private float fogDensity;
  private float fogEndBegin;
  private float fogStartBegin;
  private float fogDensityBegin;
  private float fogEndFinish;
  private float fogStartFinish;
  private float fogDensityFinish;
  private float localWorldFog;
  private float snowStrength;
  private int renderRange = 17;



  public FogHandler() {
    this.fogDensity = 0.1F;
    this.fogDensityBegin = 0.1F;
    this.fogDensityFinish = 0.1F;
  }



  @SubscribeEvent
  public void renderFogHandler(EntityViewRenderEvent.RenderFogEvent event) {
    if (event.fogMode >= 0) {


      if (this.renderRange != (Minecraft.func_71410_x()).field_71474_y.field_151451_c || this.fogEnd < 16.0F) {

        this.renderRange = (Minecraft.func_71410_x()).field_71474_y.field_151451_c;


        this.fogStart = event.farPlaneDistance * 0.75F;
        this.fogEnd = event.farPlaneDistance;
      }


      if (this.shouldLerp) {

        this.lerpTimer += event.renderPartialTicks;

        if (this.lerpTimer >= this.lerpTime) {
          this.shouldLerp = false;
        }


        this.fogStart = lerp(this.fogStartBegin, this.fogStartFinish, this.lerpTime, this.lerpTimer);
        this.fogEnd = lerp(this.fogEndBegin, this.fogEndFinish, this.lerpTime, this.lerpTimer);
        this.fogDensity = lerp(this.fogDensityBegin, this.fogDensityFinish, this.lerpTime, this.lerpTimer);
      }

      calcFog(event);

      if ((int)event.entity.field_70163_u > 128) {

        GL11.glFogf(2914, Math.min(this.fogDensity, 0.3F));
        GL11.glFogf(2915, Math.max(this.fogStart, 8.0F));
        GL11.glFogf(2916, Math.max(this.fogEnd, 16.0F));
      }
    }
  }


  private void calcFog(EntityViewRenderEvent.RenderFogEvent event) {
    if (this.renderRange < 6)
      return;
    boolean raining = event.entity.field_70170_p.func_72896_J();
    float temp = TFC_Climate.getHeightAdjustedTemp(event.entity.field_70170_p, (int)event.entity.field_70165_t, (int)event.entity.field_70163_u, (int)event.entity.field_70161_v);
    float localFog = WeatherManager.getInstance().getLocalFog(event.entity.field_70170_p, (int)event.entity.field_70165_t, (int)event.entity.field_70163_u, (int)event.entity.field_70161_v);
    if (localFog != this.localWorldFog) {
      localFog = WeatherManager.getInstance().getLocalFog(event.entity.field_70170_p, (int)event.entity.field_70165_t, (int)event.entity.field_70163_u, (int)event.entity.field_70161_v);
    }
    if (raining && temp > 0.0F) {

      if (!this.rainLast || this.snowLast)
      {
        startLerp(600.0D);
        this.rainLast = true;
        this.snowLast = false;
        this.fogStartFinish = event.farPlaneDistance * 0.5F;
        this.fogEndFinish = event.farPlaneDistance * 0.75F;
        this.fogDensityFinish = 0.2F;
      }

    } else if (raining && temp <= 0.0F) {

      if (!this.snowLast)
      {
        startLerp(600.0D);
        this.rainLast = true;
        this.snowLast = true;
        this.snowStrength = WeatherManager.getInstance().getSnowStrength();
        this.fogStartFinish = event.farPlaneDistance * 0.1F;
        this.fogEndFinish = event.farPlaneDistance * (0.2F + 0.1F * this.snowStrength);
        this.fogDensityFinish = 0.3F;
      }
      else if (this.snowLast && WeatherManager.getInstance().getSnowStrength() != this.snowStrength)
      {
        startLerp(300.0D);
        this.snowStrength = WeatherManager.getInstance().getSnowStrength();
        this.fogStartFinish = event.farPlaneDistance * 0.1F;
        this.fogEndFinish = event.farPlaneDistance * (0.2F + 0.1F * this.snowStrength);
        this.fogDensityFinish = 0.3F;
      }

    } else if (localFog > 0.0F && localFog != this.localWorldFog) {

      startLerp(600.0D);
      this.localWorldFog = localFog;
      this.fogStartFinish = event.farPlaneDistance * 0.1F;
      this.fogEndFinish = event.farPlaneDistance * (0.6F - 0.3F * localFog);
      this.fogDensityFinish = 0.2F - 0.08F * localFog;
    }
    else if (!raining) {

      if (this.rainLast || (this.localWorldFog != 0.0F && localFog == 0.0F)) {

        startLerp(600.0D);
        this.localWorldFog = 0.0F;
        this.rainLast = false;
        this.snowLast = false;
        this.fogStartFinish = event.farPlaneDistance * 0.75F;
        this.fogEndFinish = event.farPlaneDistance;
        this.fogDensityFinish = 0.1F;
      }
      else if (!this.shouldLerp && this.localWorldFog == 0.0F) {

        this.fogStart = event.farPlaneDistance * 0.75F;
        this.fogEnd = event.farPlaneDistance;
        this.fogDensity = 0.1F;
      }
    }
  }



  public float lerp(float start, float target, double duration, double timeSinceStart) {
    float value = start;
    if (timeSinceStart > 0.0D && timeSinceStart < duration) {

      float range = target - start;
      float percent = (float)(timeSinceStart / duration);
      value = start + range * percent;
    }
    else if (timeSinceStart >= duration) {

      value = target;
    }
    return value;
  }


  private void startLerp(double ticks) {
    if (!this.shouldLerp) {

      this.shouldLerp = true;
      this.lerpTimer = 0.0D;
      this.lerpTime = ticks;
      this.fogStartBegin = this.fogStart;
      this.fogEndBegin = this.fogEnd;
      this.fogDensityBegin = this.fogDensity;
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Client\FogHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */