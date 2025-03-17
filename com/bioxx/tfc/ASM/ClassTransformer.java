/*     */ package com.bioxx.tfc.ASM;
/*     */ 
/*     */ import com.bioxx.tfc.TFCASMLoadingPlugin;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.launchwrapper.IClassTransformer;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.objectweb.asm.ClassReader;
/*     */ import org.objectweb.asm.ClassVisitor;
/*     */ import org.objectweb.asm.ClassWriter;
/*     */ import org.objectweb.asm.tree.AbstractInsnNode;
/*     */ import org.objectweb.asm.tree.ClassNode;
/*     */ import org.objectweb.asm.tree.InsnList;
/*     */ import org.objectweb.asm.tree.InsnNode;
/*     */ import org.objectweb.asm.tree.JumpInsnNode;
/*     */ import org.objectweb.asm.tree.LabelNode;
/*     */ import org.objectweb.asm.tree.LineNumberNode;
/*     */ import org.objectweb.asm.tree.MethodNode;
/*     */ 
/*     */ public class ClassTransformer
/*     */   implements IClassTransformer
/*     */ {
/*  26 */   public static final Logger LOG = LogManager.getLogger("TerraFirmaCraft ASM");
/*  27 */   protected Map<String, Patch> mcpMethodNodes = new HashMap<>();
/*  28 */   protected Map<String, Patch> obfMethodNodes = new HashMap<>();
/*     */   
/*     */   protected String mcpClassName;
/*     */   
/*     */   protected String obfClassName;
/*     */   
/*     */   public static int numInsertions;
/*     */   
/*     */   public byte[] transform(String name, String transformedName, byte[] bytes) {
/*  37 */     if (name.equals(this.obfClassName))
/*     */     {
/*  39 */       return transform(bytes);
/*     */     }
/*  41 */     if (name.equals(this.mcpClassName))
/*     */     {
/*  43 */       return transform(bytes);
/*     */     }
/*     */     
/*  46 */     return bytes;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected byte[] transform(byte[] bytes) {
/*  52 */     ClassNode classNode = new ClassNode();
/*  53 */     ClassReader classReader = new ClassReader(bytes);
/*  54 */     classReader.accept((ClassVisitor)classNode, 0);
/*  55 */     LOG.info("Attempting to Transform: " + classNode.name + " | Found " + getMethodNodeList().size() + " injections");
/*     */     
/*  57 */     Iterator<MethodNode> methods = classNode.methods.iterator();
/*  58 */     while (methods.hasNext()) {
/*     */       
/*  60 */       MethodNode m = methods.next();
/*  61 */       if (getMethodNodeList().containsKey(m.name + " | " + m.desc)) {
/*     */         
/*  63 */         numInsertions = 0;
/*  64 */         Patch mPatch = getMethodNodeList().get(m.name + " | " + m.desc);
/*  65 */         List<InstrSet> instructions = mPatch.instructions;
/*  66 */         InstrSet target = null;
/*  67 */         if (!instructions.isEmpty()) {
/*     */           
/*  69 */           target = instructions.get(0);
/*     */         } else {
/*  71 */           LOG.error("Error in: {" + m.name + " | " + m.desc + "} No Instructions");
/*     */         } 
/*     */         
/*  74 */         if (mPatch.opType == PatchOpType.Modify) {
/*     */           
/*  76 */           for (int index = 0; index < m.instructions.size() && !instructions.isEmpty(); index++)
/*     */           {
/*  78 */             numInsertions = 0;
/*  79 */             while (target != null)
/*     */             {
/*  81 */               if (target.startLine == -1) {
/*  82 */                 performDirectOperation(m.instructions, target);
/*  83 */                 instructions.remove(0);
/*  84 */               } else if (isLineNumber(m.instructions.get(index), target.startLine)) {
/*  85 */                 performAnchorOperation(m.instructions, target, index);
/*  86 */                 instructions.remove(0);
/*     */               } else {
/*     */                 break;
/*     */               } 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*  94 */               if (!instructions.isEmpty()) {
/*     */                 
/*  96 */                 target = instructions.get(0);
/*     */                 
/*     */                 continue;
/*     */               } 
/* 100 */               target = null;
/*     */             }
/*     */           
/*     */           }
/*     */         
/* 105 */         } else if (mPatch.opType == PatchOpType.Replace) {
/*     */ 
/*     */           
/* 108 */           if (target != null && target.offset != -1)
/*     */           {
/* 110 */             for (int i = 0; i < m.instructions.size(); ) {
/*     */               
/* 112 */               if (i > target.offset) {
/* 113 */                 m.instructions.remove(m.instructions.get(i)); continue;
/* 114 */               }  i++;
/*     */             } 
/*     */           }
/*     */           
/* 118 */           for (int index = 0; index < m.instructions.size() && !instructions.isEmpty(); index++) {
/*     */             
/* 120 */             numInsertions = 0;
/* 121 */             while (target != null) {
/*     */               
/* 123 */               if (target.startLine == -1) {
/* 124 */                 performDirectOperation(m.instructions, target);
/* 125 */                 instructions.remove(0);
/* 126 */               } else if (isLineNumber(m.instructions.get(index), target.startLine)) {
/* 127 */                 performAnchorOperation(m.instructions, target, index);
/* 128 */                 instructions.remove(0);
/*     */               } else {
/*     */                 break;
/*     */               } 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 136 */               if (!instructions.isEmpty()) {
/*     */                 
/* 138 */                 target = instructions.get(0);
/*     */                 
/*     */                 continue;
/*     */               } 
/* 142 */               target = null;
/*     */             } 
/*     */             
/* 145 */             m.instructions.add((AbstractInsnNode)new InsnNode(177));
/*     */           } 
/*     */         } 
/* 148 */         LOG.info("Inserted: " + classNode.name + " : {" + m.name + " | " + m.desc + "}");
/*     */       } 
/*     */     } 
/* 151 */     LOG.info("Attempting to Transform: " + classNode.name + " Complete");
/* 152 */     ClassWriter writer = new ClassWriter(1);
/* 153 */     classNode.accept(writer);
/* 154 */     return writer.toByteArray();
/*     */   }
/*     */ 
/*     */   
/*     */   private int findLine(InsnList methodList, int line) {
/* 159 */     for (int index = 0; index < methodList.size(); index++) {
/*     */       
/* 161 */       if (isLineNumber(methodList.get(index), line))
/*     */       {
/* 163 */         return index;
/*     */       }
/*     */     } 
/* 166 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void performDirectOperation(InsnList methodInsn, InstrSet input) {
/* 196 */     AbstractInsnNode current = methodInsn.get(input.offset + numInsertions);
/* 197 */     switch (input.opType) {
/*     */       
/*     */       case InsertAfter:
/* 200 */         numInsertions += input.iList.size();
/* 201 */         methodInsn.insert(current, input.iList);
/*     */         break;
/*     */       case InsertBefore:
/* 204 */         numInsertions += input.iList.size();
/* 205 */         methodInsn.insertBefore(current, input.iList);
/*     */         break;
/*     */       case Remove:
/* 208 */         numInsertions--;
/* 209 */         methodInsn.remove(current);
/*     */         break;
/*     */       case Replace:
/* 212 */         if (current instanceof JumpInsnNode && input.iList.get(0) instanceof JumpInsnNode)
/*     */         {
/* 214 */           ((JumpInsnNode)input.iList.get(0)).label = ((JumpInsnNode)current).label;
/*     */         }
/* 216 */         methodInsn.insert(current, input.iList);
/* 217 */         methodInsn.remove(current);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void performAnchorOperation(InsnList methodInsn, InstrSet input, int anchor) {
/*     */     int otherAnchor;
/* 226 */     AbstractInsnNode other, current = methodInsn.get(anchor + input.offset + numInsertions);
/* 227 */     if (input.iList.get(0) instanceof JumpInsnNode)
/*     */     {
/* 229 */       input.iList.set(input.iList.get(0), (AbstractInsnNode)new JumpInsnNode(input.iList.get(0).getOpcode(), (LabelNode)current.getPrevious()));
/*     */     }
/* 231 */     switch (input.opType) {
/*     */       
/*     */       case InsertAfter:
/* 234 */         numInsertions += input.iList.size();
/* 235 */         methodInsn.insert(current, input.iList);
/*     */         break;
/*     */       case InsertBefore:
/* 238 */         numInsertions += input.iList.size();
/* 239 */         methodInsn.insertBefore(current, input.iList);
/*     */         break;
/*     */       case Remove:
/* 242 */         numInsertions--;
/* 243 */         methodInsn.remove(current);
/*     */         break;
/*     */       case Replace:
/* 246 */         methodInsn.insert(current, input.iList);
/* 247 */         methodInsn.remove(current);
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case Switch:
/* 254 */         otherAnchor = findLine(methodInsn, input.offsetLine);
/* 255 */         other = methodInsn.get(otherAnchor + input.offsetSwitch + numInsertions);
/* 256 */         methodInsn.remove(current);
/* 257 */         methodInsn.insert(other, current);
/* 258 */         current = methodInsn.get(anchor + input.offset + numInsertions);
/* 259 */         methodInsn.remove(other);
/* 260 */         methodInsn.insertBefore(current, other);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Map<String, Patch> getMethodNodeList() {
/* 269 */     if (TFCASMLoadingPlugin.runtimeDeobf)
/*     */     {
/* 271 */       return this.obfMethodNodes;
/*     */     }
/* 273 */     return this.mcpMethodNodes;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isLineNumber(AbstractInsnNode current, int line) {
/* 278 */     if (current instanceof LineNumberNode) {
/*     */       
/* 280 */       int l = ((LineNumberNode)current).line;
/* 281 */       if (l == line)
/*     */       {
/* 283 */         return true;
/*     */       }
/*     */     } 
/* 286 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class InstrSet
/*     */   {
/*     */     public InsnList iList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int offset;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 364 */     public int startLine = -1;
/*     */ 
/*     */ 
/*     */     
/*     */     public ClassTransformer.InstrOpType opType;
/*     */ 
/*     */ 
/*     */     
/* 372 */     public int offsetSwitch = -1;
/* 373 */     public int offsetLine = -1;
/*     */ 
/*     */     
/*     */     public InstrSet(InsnList list, int off, ClassTransformer.InstrOpType op) {
/* 377 */       this.iList = list;
/* 378 */       this.offset = off;
/* 379 */       this.opType = op;
/*     */     }
/*     */     
/*     */     public InstrSet(AbstractInsnNode node, int off, ClassTransformer.InstrOpType op) {
/* 383 */       this.iList = new InsnList();
/* 384 */       this.iList.add(node);
/* 385 */       this.offset = off;
/* 386 */       this.opType = op;
/*     */     }
/*     */     
/*     */     public InstrSet(InsnList list, int startLineNum, int off, ClassTransformer.InstrOpType op) {
/* 390 */       this.iList = list;
/* 391 */       this.startLine = startLineNum;
/* 392 */       this.offset = off;
/* 393 */       this.opType = op;
/*     */     }
/*     */     
/*     */     public InstrSet(AbstractInsnNode node, int startLineNum, int off, ClassTransformer.InstrOpType op) {
/* 397 */       this.iList = new InsnList();
/* 398 */       this.iList.add(node);
/* 399 */       this.startLine = startLineNum;
/* 400 */       this.offset = off;
/* 401 */       this.opType = op;
/*     */     }
/*     */     
/*     */     public InstrSet(int startLineNum, int off, int swLineNum, int swOffset) {
/* 405 */       this.startLine = startLineNum;
/* 406 */       this.offset = off;
/* 407 */       this.opType = ClassTransformer.InstrOpType.Switch;
/* 408 */       this.offsetSwitch = swOffset;
/* 409 */       this.offsetLine = swLineNum;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public class Patch
/*     */   {
/*     */     public List<ClassTransformer.InstrSet> instructions;
/*     */     
/* 419 */     public ClassTransformer.PatchOpType opType = ClassTransformer.PatchOpType.Modify;
/*     */ 
/*     */     
/*     */     public Patch(List<ClassTransformer.InstrSet> set) {
/* 423 */       this.instructions = set;
/*     */     }
/*     */ 
/*     */     
/*     */     public Patch(List<ClassTransformer.InstrSet> set, ClassTransformer.PatchOpType op) {
/* 428 */       this.instructions = set;
/* 429 */       this.opType = op;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum PatchOpType
/*     */   {
/* 435 */     Modify,
/* 436 */     Replace;
/*     */   }
/*     */   
/*     */   public enum InstrOpType
/*     */   {
/* 441 */     InsertAfter,
/* 442 */     InsertBefore,
/* 443 */     Switch,
/* 444 */     Replace,
/* 445 */     Remove;
/*     */   }
/*     */   
/*     */   public class JumpNode extends JumpInsnNode {
/*     */     public int line;
/*     */     
/*     */     public JumpNode(int opcode, LabelNode label) {
/* 452 */       super(opcode, label);
/*     */     }
/*     */ 
/*     */     
/*     */     public JumpNode(int opcode, int labelLine) {
/* 457 */       super(opcode, null);
/* 458 */       this.line = labelLine;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\ASM\ClassTransformer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */