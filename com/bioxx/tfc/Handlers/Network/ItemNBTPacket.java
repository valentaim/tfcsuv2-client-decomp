/*     */ package com.bioxx.tfc.Handlers.Network;
/*     */ 
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemNBTPacket
/*     */   extends AbstractPacket
/*     */ {
/*  21 */   private NBTTagCompound tags = new NBTTagCompound();
/*  22 */   private List<String> tagNames = new LinkedList<>();
/*  23 */   private List<String> removeNames = new LinkedList<>();
/*     */ 
/*     */   
/*     */   public ItemNBTPacket(NBTTagCompound nbt) {
/*  27 */     this();
/*  28 */     this.tags = nbt;
/*     */   }
/*     */   public ItemNBTPacket() {}
/*     */   
/*     */   public ItemNBTPacket(NBTTagCompound nbt, List<String> acceptedTagNames) {
/*  33 */     this();
/*  34 */     this.tagNames = acceptedTagNames;
/*  35 */     for (String tagName : this.tagNames) {
/*  36 */       this.tags.func_74782_a(tagName, nbt.func_74781_a(tagName));
/*     */     }
/*     */   }
/*     */   
/*     */   public ItemNBTPacket(NBTTagCompound nbt, List<String> acceptedTagNames, List<String> removeTagNames) {
/*  41 */     this();
/*  42 */     this.tagNames = acceptedTagNames;
/*  43 */     for (String tagName : this.tagNames)
/*  44 */       this.tags.func_74782_a(tagName, nbt.func_74781_a(tagName)); 
/*  45 */     this.removeNames = removeTagNames;
/*     */   }
/*     */   
/*     */   public ItemNBTPacket addAcceptedTag(String name) {
/*  49 */     if (!this.removeNames.contains(name) && !this.tagNames.contains(name))
/*  50 */       this.tagNames.add(name); 
/*  51 */     return this;
/*     */   }
/*     */   
/*     */   public ItemNBTPacket removeAcceptedTag(String name) {
/*  55 */     this.tagNames.remove(name);
/*  56 */     return this;
/*     */   }
/*     */   
/*     */   public ItemNBTPacket addRemoveTag(String name) {
/*  60 */     if (!this.removeNames.contains(name))
/*  61 */       this.removeNames.add(name); 
/*  62 */     return this;
/*     */   }
/*     */   
/*     */   public ItemNBTPacket removeRemoveTag(String name) {
/*  66 */     this.removeNames.remove(name);
/*  67 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
/*  72 */     PacketBuffer pb = new PacketBuffer(buffer);
/*  73 */     NBTTagCompound tags = this.tags;
/*     */     
/*  75 */     for (String tagName : this.removeNames) {
/*  76 */       tags.func_82580_o(tagName);
/*     */     }
/*     */     try {
/*  79 */       pb.func_150786_a(tags);
/*     */       
/*  81 */       pb.writeInt(this.tagNames.size());
/*  82 */       for (String tagName : this.tagNames) {
/*  83 */         pb.func_150785_a(tagName);
/*     */       }
/*  85 */       pb.writeInt(this.removeNames.size());
/*  86 */       for (String tagName : this.removeNames)
/*  87 */         pb.func_150785_a(tagName); 
/*  88 */     } catch (Exception e) {
/*  89 */       TerraFirmaCraft.LOG.catching(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
/*  95 */     PacketBuffer pb = new PacketBuffer(buffer);
/*     */     
/*     */     try {
/*  98 */       this.tags = pb.func_150793_b();
/*     */       
/* 100 */       int size = pb.readInt(); int i;
/* 101 */       for (i = 0; i < size; i++) {
/* 102 */         this.tagNames.add(pb.func_150789_c(256));
/*     */       }
/* 104 */       size = pb.readInt();
/* 105 */       for (i = 0; i < size; i++)
/* 106 */         this.removeNames.add(pb.func_150789_c(256)); 
/* 107 */     } catch (Exception e) {
/* 108 */       TerraFirmaCraft.LOG.catching(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleClientSide(EntityPlayer player) {
/* 116 */     ItemStack stack = player.field_71071_by.func_70448_g();
/*     */     
/* 118 */     if (stack != null) {
/*     */       NBTTagCompound stackNBT;
/* 120 */       if (stack.func_77942_o()) {
/*     */         
/* 122 */         stackNBT = stack.field_77990_d;
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 129 */         stackNBT = new NBTTagCompound();
/*     */       } 
/*     */       
/* 132 */       for (String tagName : this.tagNames)
/* 133 */         stackNBT.func_74782_a(tagName, this.tags.func_74781_a(tagName)); 
/* 134 */       for (String tagName : this.removeNames)
/* 135 */         stackNBT.func_82580_o(tagName); 
/* 136 */       player.field_71071_by.func_70448_g().func_77982_d(stackNBT);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleServerSide(EntityPlayer player) {
/* 144 */     ItemStack stack = player.field_71071_by.func_70448_g();
/*     */     
/* 146 */     if (stack != null) {
/*     */       NBTTagCompound stackNBT;
/* 148 */       if (stack.func_77942_o()) {
/*     */         
/* 150 */         stackNBT = stack.field_77990_d;
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 157 */         stackNBT = new NBTTagCompound();
/*     */       } 
/* 159 */       for (String tagName : this.tagNames)
/* 160 */         stackNBT.func_74782_a(tagName, this.tags.func_74781_a(tagName)); 
/* 161 */       for (String tagName : this.removeNames)
/* 162 */         stackNBT.func_82580_o(tagName); 
/* 163 */       player.field_71071_by.func_70448_g().func_77982_d(stackNBT);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Network\ItemNBTPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */