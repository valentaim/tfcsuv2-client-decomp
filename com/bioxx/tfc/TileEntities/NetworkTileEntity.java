/*     */ package com.bioxx.tfc.TileEntities;
/*     */ 
/*     */ import com.bioxx.tfc.Handlers.Network.AbstractPacket;
/*     */ import com.bioxx.tfc.Handlers.Network.DataBlockPacket;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ 
/*     */ public abstract class NetworkTileEntity
/*     */   extends TileEntity
/*     */ {
/*     */   public boolean shouldSendInitData = true;
/*     */   public EntityPlayer entityplayer;
/*  22 */   protected int broadcastRange = 256;
/*     */ 
/*     */   
/*     */   public abstract void handleInitPacket(NBTTagCompound paramNBTTagCompound);
/*     */   
/*     */   public void handleDataPacket(NBTTagCompound nbt) {}
/*     */   
/*     */   public void createDataNBT(NBTTagCompound nbt) {}
/*     */   
/*     */   public abstract void createInitNBT(NBTTagCompound paramNBTTagCompound);
/*     */   
/*     */   public DataBlockPacket createDataPacket() {
/*  34 */     return createDataPacket(createDataNBT());
/*     */   }
/*     */ 
/*     */   
/*     */   public DataBlockPacket createDataPacket(NBTTagCompound nbt) {
/*  39 */     return new DataBlockPacket(this.field_145851_c, this.field_145848_d, this.field_145849_e, nbt);
/*     */   }
/*     */ 
/*     */   
/*     */   private NBTTagCompound createDataNBT() {
/*  44 */     NBTTagCompound nbt = new NBTTagCompound();
/*  45 */     createDataNBT(nbt);
/*  46 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   private NBTTagCompound createInitNBT() {
/*  51 */     NBTTagCompound nbt = new NBTTagCompound();
/*  52 */     createInitNBT(nbt);
/*  53 */     return nbt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public double func_145833_n() {
/*  60 */     return 1024.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox() {
/*  67 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Packet func_145844_m() {
/*  73 */     if (this.shouldSendInitData)
/*  74 */       return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, createInitNBT()); 
/*  75 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
/*  81 */     handleInitPacket(pkt.func_148857_g());
/*  82 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */   }
/*     */ 
/*     */   
/*     */   public void broadcastPacketInRange() {
/*  87 */     int dim = this.field_145850_b.field_73011_w.field_76574_g;
/*  88 */     if (this.field_145850_b.field_72995_K) {
/*  89 */       TerraFirmaCraft.PACKET_PIPELINE.sendToServer((AbstractPacket)createDataPacket());
/*     */     } else {
/*  91 */       TerraFirmaCraft.PACKET_PIPELINE.sendToAllAround((AbstractPacket)createDataPacket(), new NetworkRegistry.TargetPoint(dim, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.broadcastRange));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void broadcastPacketInRange(AbstractPacket packet) {
/*  97 */     if (this.field_145850_b.field_72995_K) {
/*  98 */       TerraFirmaCraft.PACKET_PIPELINE.sendToServer(packet);
/*     */     } else {
/* 100 */       TerraFirmaCraft.PACKET_PIPELINE.sendToAllAround(packet, new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.field_76574_g, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.broadcastRange));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\NetworkTileEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */