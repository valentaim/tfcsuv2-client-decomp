/*    */ package com.bioxx.tfc.api;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ 
/*    */ 
/*    */ public class TFCAttributes
/*    */ {
/*  9 */   public static final UUID OVERBURDENED_UUID = UUID.fromString("772A6B8D-DA3E-4C1C-8813-96EA6097278D");
/* 10 */   public static final AttributeModifier OVERBURDENED = (new AttributeModifier(OVERBURDENED_UUID, "Overburdened speed penalty", -1.0D, 2)).func_111168_a(false);
/* 11 */   public static final UUID THIRSTY_UUID = UUID.fromString("772A6B8D-DA3E-4C1C-9999-96EA6097278D");
/* 12 */   public static final AttributeModifier THIRSTY = (new AttributeModifier(THIRSTY_UUID, "Thirsty speed penalty", -0.3D, 2)).func_111168_a(false);
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\TFCAttributes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */