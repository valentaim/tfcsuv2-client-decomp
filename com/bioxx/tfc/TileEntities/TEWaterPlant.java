/*    */ package com.bioxx.tfc.TileEntities;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.network.NetworkManager;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TEWaterPlant
/*    */   extends NetworkTileEntity
/*    */ {
/*    */   private Block blockType;
/*    */   
/*    */   public void setBlock(Block block) {
/* 23 */     if (block.func_149662_c()) {
/* 24 */       this.blockType = block;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canUpdate() {
/* 31 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public AxisAlignedBB getRenderBoundingBox() {
/* 38 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
/*    */   }
/*    */ 
/*    */   
/*    */   public Block getBlockFromType() {
/* 43 */     if (!this.field_145850_b.field_72995_K) {
/* 44 */       return this.blockType;
/*    */     }
/* 46 */     return this.blockType;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_145839_a(NBTTagCompound nbttagcompound) {
/* 52 */     super.func_145839_a(nbttagcompound);
/* 53 */     this.blockType = Block.func_149729_e(nbttagcompound.func_74762_e("block"));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_145841_b(NBTTagCompound nbttagcompound) {
/* 59 */     super.func_145841_b(nbttagcompound);
/* 60 */     nbttagcompound.func_74768_a("block", Block.func_149682_b(this.blockType));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Packet func_145844_m() {
/* 66 */     NBTTagCompound nbt = new NBTTagCompound();
/* 67 */     func_145841_b(nbt);
/* 68 */     return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbt);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
/* 74 */     func_145839_a(pkt.func_148857_g());
/*    */   }
/*    */ 
/*    */   
/*    */   public void handleInitPacket(NBTTagCompound nbt) {
/* 79 */     this.blockType = Block.func_149729_e(nbt.func_74762_e("blockType"));
/*    */   }
/*    */ 
/*    */   
/*    */   public void createInitNBT(NBTTagCompound nbt) {
/* 84 */     nbt.func_74768_a("blockType", Block.func_149682_b(this.blockType));
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEWaterPlant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */