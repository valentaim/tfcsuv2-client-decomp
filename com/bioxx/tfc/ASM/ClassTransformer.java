package com.bioxx.tfc.ASM;

import com.bioxx.tfc.TFCASMLoadingPlugin;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.launchwrapper.IClassTransformer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.MethodNode;

public class ClassTransformer
  implements IClassTransformer
{
  public static final Logger LOG = LogManager.getLogger("TerraFirmaCraft ASM");
  protected Map<String, Patch> mcpMethodNodes = new HashMap<>();
  protected Map<String, Patch> obfMethodNodes = new HashMap<>();

  protected String mcpClassName;

  protected String obfClassName;

  public static int numInsertions;

  public byte[] transform(String name, String transformedName, byte[] bytes) {
    if (name.equals(this.obfClassName))
    {
      return transform(bytes);
    }
    if (name.equals(this.mcpClassName))
    {
      return transform(bytes);
    }

    return bytes;
  }



  protected byte[] transform(byte[] bytes) {
    ClassNode classNode = new ClassNode();
    ClassReader classReader = new ClassReader(bytes);
    classReader.accept((ClassVisitor)classNode, 0);
    LOG.info("Attempting to Transform: " + classNode.name + " | Found " + getMethodNodeList().size() + " injections");

    Iterator<MethodNode> methods = classNode.methods.iterator();
    while (methods.hasNext()) {

      MethodNode m = methods.next();
      if (getMethodNodeList().containsKey(m.name + " | " + m.desc)) {

        numInsertions = 0;
        Patch mPatch = getMethodNodeList().get(m.name + " | " + m.desc);
        List<InstrSet> instructions = mPatch.instructions;
        InstrSet target = null;
        if (!instructions.isEmpty()) {

          target = instructions.get(0);
        } else {
          LOG.error("Error in: {" + m.name + " | " + m.desc + "} No Instructions");
        }

        if (mPatch.opType == PatchOpType.Modify) {

          for (int index = 0; index < m.instructions.size() && !instructions.isEmpty(); index++)
          {
            numInsertions = 0;
            while (target != null)
            {
              if (target.startLine == -1) {
                performDirectOperation(m.instructions, target);
                instructions.remove(0);
              } else if (isLineNumber(m.instructions.get(index), target.startLine)) {
                performAnchorOperation(m.instructions, target, index);
                instructions.remove(0);
              } else {
                break;
              }




              if (!instructions.isEmpty()) {

                target = instructions.get(0);

                continue;
              }
              target = null;
            }

          }

        } else if (mPatch.opType == PatchOpType.Replace) {


          if (target != null && target.offset != -1)
          {
            for (int i = 0; i < m.instructions.size(); ) {

              if (i > target.offset) {
                m.instructions.remove(m.instructions.get(i)); continue;
              }  i++;
            }
          }

          for (int index = 0; index < m.instructions.size() && !instructions.isEmpty(); index++) {

            numInsertions = 0;
            while (target != null) {

              if (target.startLine == -1) {
                performDirectOperation(m.instructions, target);
                instructions.remove(0);
              } else if (isLineNumber(m.instructions.get(index), target.startLine)) {
                performAnchorOperation(m.instructions, target, index);
                instructions.remove(0);
              } else {
                break;
              }




              if (!instructions.isEmpty()) {

                target = instructions.get(0);

                continue;
              }
              target = null;
            }

            m.instructions.add((AbstractInsnNode)new InsnNode(177));
          }
        }
        LOG.info("Inserted: " + classNode.name + " : {" + m.name + " | " + m.desc + "}");
      }
    }
    LOG.info("Attempting to Transform: " + classNode.name + " Complete");
    ClassWriter writer = new ClassWriter(1);
    classNode.accept(writer);
    return writer.toByteArray();
  }


  private int findLine(InsnList methodList, int line) {
    for (int index = 0; index < methodList.size(); index++) {

      if (isLineNumber(methodList.get(index), line))
      {
        return index;
      }
    }
    return -1;
  }



























  private void performDirectOperation(InsnList methodInsn, InstrSet input) {
    AbstractInsnNode current = methodInsn.get(input.offset + numInsertions);
    switch (input.opType) {

      case InsertAfter:
        numInsertions += input.iList.size();
        methodInsn.insert(current, input.iList);
        break;
      case InsertBefore:
        numInsertions += input.iList.size();
        methodInsn.insertBefore(current, input.iList);
        break;
      case Remove:
        numInsertions--;
        methodInsn.remove(current);
        break;
      case Replace:
        if (current instanceof JumpInsnNode && input.iList.get(0) instanceof JumpInsnNode)
        {
          ((JumpInsnNode)input.iList.get(0)).label = ((JumpInsnNode)current).label;
        }
        methodInsn.insert(current, input.iList);
        methodInsn.remove(current);
        break;
    }
  }



  private void performAnchorOperation(InsnList methodInsn, InstrSet input, int anchor) {
    int otherAnchor;
    AbstractInsnNode other, current = methodInsn.get(anchor + input.offset + numInsertions);
    if (input.iList.get(0) instanceof JumpInsnNode)
    {
      input.iList.set(input.iList.get(0), (AbstractInsnNode)new JumpInsnNode(input.iList.get(0).getOpcode(), (LabelNode)current.getPrevious()));
    }
    switch (input.opType) {

      case InsertAfter:
        numInsertions += input.iList.size();
        methodInsn.insert(current, input.iList);
        break;
      case InsertBefore:
        numInsertions += input.iList.size();
        methodInsn.insertBefore(current, input.iList);
        break;
      case Remove:
        numInsertions--;
        methodInsn.remove(current);
        break;
      case Replace:
        methodInsn.insert(current, input.iList);
        methodInsn.remove(current);
        break;




      case Switch:
        otherAnchor = findLine(methodInsn, input.offsetLine);
        other = methodInsn.get(otherAnchor + input.offsetSwitch + numInsertions);
        methodInsn.remove(current);
        methodInsn.insert(other, current);
        current = methodInsn.get(anchor + input.offset + numInsertions);
        methodInsn.remove(other);
        methodInsn.insertBefore(current, other);
        break;
    }
  }




  protected Map<String, Patch> getMethodNodeList() {
    if (TFCASMLoadingPlugin.runtimeDeobf)
    {
      return this.obfMethodNodes;
    }
    return this.mcpMethodNodes;
  }


  private boolean isLineNumber(AbstractInsnNode current, int line) {
    if (current instanceof LineNumberNode) {

      int l = ((LineNumberNode)current).line;
      if (l == line)
      {
        return true;
      }
    }
    return false;
  }

























  public class InstrSet
  {
    public InsnList iList;
























    public int offset;























    public int startLine = -1;



    public ClassTransformer.InstrOpType opType;



    public int offsetSwitch = -1;
    public int offsetLine = -1;


    public InstrSet(InsnList list, int off, ClassTransformer.InstrOpType op) {
      this.iList = list;
      this.offset = off;
      this.opType = op;
    }

    public InstrSet(AbstractInsnNode node, int off, ClassTransformer.InstrOpType op) {
      this.iList = new InsnList();
      this.iList.add(node);
      this.offset = off;
      this.opType = op;
    }

    public InstrSet(InsnList list, int startLineNum, int off, ClassTransformer.InstrOpType op) {
      this.iList = list;
      this.startLine = startLineNum;
      this.offset = off;
      this.opType = op;
    }

    public InstrSet(AbstractInsnNode node, int startLineNum, int off, ClassTransformer.InstrOpType op) {
      this.iList = new InsnList();
      this.iList.add(node);
      this.startLine = startLineNum;
      this.offset = off;
      this.opType = op;
    }

    public InstrSet(int startLineNum, int off, int swLineNum, int swOffset) {
      this.startLine = startLineNum;
      this.offset = off;
      this.opType = ClassTransformer.InstrOpType.Switch;
      this.offsetSwitch = swOffset;
      this.offsetLine = swLineNum;
    }
  }



  public class Patch
  {
    public List<ClassTransformer.InstrSet> instructions;

    public ClassTransformer.PatchOpType opType = ClassTransformer.PatchOpType.Modify;


    public Patch(List<ClassTransformer.InstrSet> set) {
      this.instructions = set;
    }


    public Patch(List<ClassTransformer.InstrSet> set, ClassTransformer.PatchOpType op) {
      this.instructions = set;
      this.opType = op;
    }
  }

  public enum PatchOpType
  {
    Modify,
    Replace;
  }

  public enum InstrOpType
  {
    InsertAfter,
    InsertBefore,
    Switch,
    Replace,
    Remove;
  }

  public class JumpNode extends JumpInsnNode {
    public int line;

    public JumpNode(int opcode, LabelNode label) {
      super(opcode, label);
    }


    public JumpNode(int opcode, int labelLine) {
      super(opcode, null);
      this.line = labelLine;
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\ASM\ClassTransformer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */