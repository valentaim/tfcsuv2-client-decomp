package com.bioxx.tfc.ASM.Transform;

import com.bioxx.tfc.ASM.ClassTransformer;
import java.util.ArrayList;
import java.util.List;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.MethodInsnNode;











public class TF_RenderGlobal
  extends ClassTransformer
{
  public TF_RenderGlobal() {
    List<ClassTransformer.InstrSet> nodes = new ArrayList<>();
    InsnList list = new InsnList();
    list.add((AbstractInsnNode)new LineNumberNode(445, new LabelNode()));
    list.add((AbstractInsnNode)new MethodInsnNode(184, "com/bioxx/tfc/ClientOverrides", "loadRenderers", "()V"));
    nodes.add(new ClassTransformer.InstrSet(this, list, 445, ClassTransformer.InstrOpType.InsertBefore));
    
    this.mcpMethodNodes.put("loadRenderers | ()V", new ClassTransformer.Patch(this, nodes, ClassTransformer.PatchOpType.Modify));
    this.obfMethodNodes.put("a | ()V", new ClassTransformer.Patch(this, nodes, ClassTransformer.PatchOpType.Modify));
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\ASM\Transform\TF_RenderGlobal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */