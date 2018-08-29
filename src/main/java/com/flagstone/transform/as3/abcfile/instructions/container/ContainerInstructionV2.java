package com.flagstone.transform.as3.abcfile.instructions.container;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import com.flagstone.transform.as3.abcfile.AbcFile;
import com.flagstone.transform.as3.abcfile.instruction.IInstruction;
import com.flagstone.transform.as3.abcfile.instruction.IInstructionBaseJump;
import com.flagstone.transform.as3.abcfile.instruction.Instruction;
import com.flagstone.transform.as3.abcfile.instruction.InstructionApplyType;
import com.flagstone.transform.as3.abcfile.instruction.InstructionAstypeExtd;
import com.flagstone.transform.as3.abcfile.instruction.InstructionAstypelate;
import com.flagstone.transform.as3.abcfile.instruction.InstructionCheckfilter;
import com.flagstone.transform.as3.abcfile.instruction.InstructionDeletepropertyExtd;
import com.flagstone.transform.as3.abcfile.instruction.InstructionFindpropertyExtd;
import com.flagstone.transform.as3.abcfile.instruction.InstructionFindpropstrictExtd;
import com.flagstone.transform.as3.abcfile.instruction.InstructionGetdescendantsExtd;
import com.flagstone.transform.as3.abcfile.instruction.InstructionGetglobalscope;
import com.flagstone.transform.as3.abcfile.instruction.InstructionGetglobalslot;
import com.flagstone.transform.as3.abcfile.instruction.InstructionGetlexExtd;
import com.flagstone.transform.as3.abcfile.instruction.InstructionGetpropertyExtd;
import com.flagstone.transform.as3.abcfile.instruction.InstructionGetscopeobject;
import com.flagstone.transform.as3.abcfile.instruction.InstructionGetslot;
import com.flagstone.transform.as3.abcfile.instruction.InstructionGetsuper;
import com.flagstone.transform.as3.abcfile.instruction.InstructionHasnext;
import com.flagstone.transform.as3.abcfile.instruction.InstructionHasnext2;
import com.flagstone.transform.as3.abcfile.instruction.InstructionInitpropertyExtd;
import com.flagstone.transform.as3.abcfile.instruction.InstructionInstanceof;
import com.flagstone.transform.as3.abcfile.instruction.InstructionKill;
import com.flagstone.transform.as3.abcfile.instruction.InstructionNewfunctionExtd;
import com.flagstone.transform.as3.abcfile.instruction.InstructionNextname;
import com.flagstone.transform.as3.abcfile.instruction.InstructionNextvalue;
import com.flagstone.transform.as3.abcfile.instruction.InstructionNop;
import com.flagstone.transform.as3.abcfile.instruction.InstructionNot;
import com.flagstone.transform.as3.abcfile.instruction.InstructionPopscope;
import com.flagstone.transform.as3.abcfile.instruction.InstructionReturnvalue;
import com.flagstone.transform.as3.abcfile.instruction.InstructionReturnvoid;
import com.flagstone.transform.as3.abcfile.instruction.InstructionSetglobalslot;
import com.flagstone.transform.as3.abcfile.instruction.InstructionSetpropertyExtd;
import com.flagstone.transform.as3.abcfile.instruction.InstructionSetslot;
import com.flagstone.transform.as3.abcfile.instruction.InstructionSetsuperExtd;
import com.flagstone.transform.as3.abcfile.instruction.InstructioneTypeof;
import com.flagstone.transform.as3.abcfile.instruction.Opcode;
import com.flagstone.transform.as3.abcfile.instruction.arithmetic.InstructionAdd;
import com.flagstone.transform.as3.abcfile.instruction.arithmetic.InstructionAdd_i;
import com.flagstone.transform.as3.abcfile.instruction.arithmetic.InstructionDeclocal;
import com.flagstone.transform.as3.abcfile.instruction.arithmetic.InstructionDeclocal_i;
import com.flagstone.transform.as3.abcfile.instruction.arithmetic.InstructionDecrement;
import com.flagstone.transform.as3.abcfile.instruction.arithmetic.InstructionDecrement_i;
import com.flagstone.transform.as3.abcfile.instruction.arithmetic.InstructionDivide;
import com.flagstone.transform.as3.abcfile.instruction.arithmetic.InstructionEquals;
import com.flagstone.transform.as3.abcfile.instruction.arithmetic.InstructionGreaterequals;
import com.flagstone.transform.as3.abcfile.instruction.arithmetic.InstructionGreaterthan;
import com.flagstone.transform.as3.abcfile.instruction.arithmetic.InstructionIn;
import com.flagstone.transform.as3.abcfile.instruction.arithmetic.InstructionInclocal;
import com.flagstone.transform.as3.abcfile.instruction.arithmetic.InstructionInclocal_i;
import com.flagstone.transform.as3.abcfile.instruction.arithmetic.InstructionIncrement;
import com.flagstone.transform.as3.abcfile.instruction.arithmetic.InstructionIncrement_i;
import com.flagstone.transform.as3.abcfile.instruction.arithmetic.InstructionIstypeExtd;
import com.flagstone.transform.as3.abcfile.instruction.arithmetic.InstructionIstypelate;
import com.flagstone.transform.as3.abcfile.instruction.arithmetic.InstructionLessequals;
import com.flagstone.transform.as3.abcfile.instruction.arithmetic.InstructionLessthan;
import com.flagstone.transform.as3.abcfile.instruction.arithmetic.InstructionModulo;
import com.flagstone.transform.as3.abcfile.instruction.arithmetic.InstructionMultiply;
import com.flagstone.transform.as3.abcfile.instruction.arithmetic.InstructionMultiply_i;
import com.flagstone.transform.as3.abcfile.instruction.arithmetic.InstructionNegate;
import com.flagstone.transform.as3.abcfile.instruction.arithmetic.InstructionNegate_i;
import com.flagstone.transform.as3.abcfile.instruction.arithmetic.InstructionStrictequals;
import com.flagstone.transform.as3.abcfile.instruction.arithmetic.InstructionSubtract;
import com.flagstone.transform.as3.abcfile.instruction.arithmetic.InstructionSubtract_i;
import com.flagstone.transform.as3.abcfile.instruction.bit_manipulation.InstructionBitand;
import com.flagstone.transform.as3.abcfile.instruction.bit_manipulation.InstructionBitnot;
import com.flagstone.transform.as3.abcfile.instruction.bit_manipulation.InstructionBitor;
import com.flagstone.transform.as3.abcfile.instruction.bit_manipulation.InstructionBitxor;
import com.flagstone.transform.as3.abcfile.instruction.bit_manipulation.InstructionLshift;
import com.flagstone.transform.as3.abcfile.instruction.bit_manipulation.InstructionRshift;
import com.flagstone.transform.as3.abcfile.instruction.bit_manipulation.InstructionUrshift;
import com.flagstone.transform.as3.abcfile.instruction.control_transfer.InstructionIfeq;
import com.flagstone.transform.as3.abcfile.instruction.control_transfer.InstructionIffalse;
import com.flagstone.transform.as3.abcfile.instruction.control_transfer.InstructionIfge;
import com.flagstone.transform.as3.abcfile.instruction.control_transfer.InstructionIfgt;
import com.flagstone.transform.as3.abcfile.instruction.control_transfer.InstructionIfle;
import com.flagstone.transform.as3.abcfile.instruction.control_transfer.InstructionIflt;
import com.flagstone.transform.as3.abcfile.instruction.control_transfer.InstructionIfne;
import com.flagstone.transform.as3.abcfile.instruction.control_transfer.InstructionIfnge;
import com.flagstone.transform.as3.abcfile.instruction.control_transfer.InstructionIfngt;
import com.flagstone.transform.as3.abcfile.instruction.control_transfer.InstructionIfnle;
import com.flagstone.transform.as3.abcfile.instruction.control_transfer.InstructionIfnlt;
import com.flagstone.transform.as3.abcfile.instruction.control_transfer.InstructionIfstricteq;
import com.flagstone.transform.as3.abcfile.instruction.control_transfer.InstructionIfstrictne;
import com.flagstone.transform.as3.abcfile.instruction.control_transfer.InstructionIftrue;
import com.flagstone.transform.as3.abcfile.instruction.control_transfer.InstructionJump;
import com.flagstone.transform.as3.abcfile.instruction.control_transfer.InstructionLabel;
import com.flagstone.transform.as3.abcfile.instruction.control_transfer.InstructionLookupswitch;
import com.flagstone.transform.as3.abcfile.instruction.conversion.InstructionCoerceExtd;
import com.flagstone.transform.as3.abcfile.instruction.conversion.InstructionCoerce_a;
import com.flagstone.transform.as3.abcfile.instruction.conversion.InstructionCoerce_s;
import com.flagstone.transform.as3.abcfile.instruction.conversion.InstructionConvert_b;
import com.flagstone.transform.as3.abcfile.instruction.conversion.InstructionConvert_d;
import com.flagstone.transform.as3.abcfile.instruction.conversion.InstructionConvert_i;
import com.flagstone.transform.as3.abcfile.instruction.conversion.InstructionConvert_o;
import com.flagstone.transform.as3.abcfile.instruction.conversion.InstructionConvert_s;
import com.flagstone.transform.as3.abcfile.instruction.conversion.InstructionConvert_u;
import com.flagstone.transform.as3.abcfile.instruction.debugging.InstructionDebug;
import com.flagstone.transform.as3.abcfile.instruction.debugging.InstructionDebugfileExtd;
import com.flagstone.transform.as3.abcfile.instruction.debugging.InstructionDebugline;
import com.flagstone.transform.as3.abcfile.instruction.exception.InstructionNewcatchExtd;
import com.flagstone.transform.as3.abcfile.instruction.exception.InstructionThrow;
import com.flagstone.transform.as3.abcfile.instruction.invocation.InstructionCall;
import com.flagstone.transform.as3.abcfile.instruction.invocation.InstructionCallmethodExtd;
import com.flagstone.transform.as3.abcfile.instruction.invocation.InstructionCallpropertyExtd;
import com.flagstone.transform.as3.abcfile.instruction.invocation.InstructionCallproplexExtd;
import com.flagstone.transform.as3.abcfile.instruction.invocation.InstructionCallpropvoidExtd;
import com.flagstone.transform.as3.abcfile.instruction.invocation.InstructionCallstaticExtd;
import com.flagstone.transform.as3.abcfile.instruction.invocation.InstructionCallsuperExtd;
import com.flagstone.transform.as3.abcfile.instruction.invocation.InstructionCallsupervoidExtd;
import com.flagstone.transform.as3.abcfile.instruction.load.InstructionGetlocal;
import com.flagstone.transform.as3.abcfile.instruction.load.InstructionGetlocal_0;
import com.flagstone.transform.as3.abcfile.instruction.load.InstructionGetlocal_1;
import com.flagstone.transform.as3.abcfile.instruction.load.InstructionGetlocal_2;
import com.flagstone.transform.as3.abcfile.instruction.load.InstructionGetlocal_3;
import com.flagstone.transform.as3.abcfile.instruction.load.InstructionSetlocal;
import com.flagstone.transform.as3.abcfile.instruction.load.InstructionSetlocal_0;
import com.flagstone.transform.as3.abcfile.instruction.load.InstructionSetlocal_1;
import com.flagstone.transform.as3.abcfile.instruction.load.InstructionSetlocal_2;
import com.flagstone.transform.as3.abcfile.instruction.load.InstructionSetlocal_3;
import com.flagstone.transform.as3.abcfile.instruction.memory.InstructionLoadShortIndirect;
import com.flagstone.transform.as3.abcfile.instruction.object_creation.InstructionConstruct;
import com.flagstone.transform.as3.abcfile.instruction.object_creation.InstructionConstructpropExtd;
import com.flagstone.transform.as3.abcfile.instruction.object_creation.InstructionConstructsuper;
import com.flagstone.transform.as3.abcfile.instruction.object_creation.InstructionDxnsExtd;
import com.flagstone.transform.as3.abcfile.instruction.object_creation.InstructionDxnslate;
import com.flagstone.transform.as3.abcfile.instruction.object_creation.InstructionNewactivation;
import com.flagstone.transform.as3.abcfile.instruction.object_creation.InstructionNewarray;
import com.flagstone.transform.as3.abcfile.instruction.object_creation.InstructionNewclassExtd;
import com.flagstone.transform.as3.abcfile.instruction.object_creation.InstructionNewobject;
import com.flagstone.transform.as3.abcfile.instruction.stack_management.InstructionDup;
import com.flagstone.transform.as3.abcfile.instruction.stack_management.InstructionPop;
import com.flagstone.transform.as3.abcfile.instruction.stack_management.InstructionPushbyte;
import com.flagstone.transform.as3.abcfile.instruction.stack_management.InstructionPushdoubleExtd;
import com.flagstone.transform.as3.abcfile.instruction.stack_management.InstructionPushfalse;
import com.flagstone.transform.as3.abcfile.instruction.stack_management.InstructionPushintExtd;
import com.flagstone.transform.as3.abcfile.instruction.stack_management.InstructionPushnamespaceExtd;
import com.flagstone.transform.as3.abcfile.instruction.stack_management.InstructionPushnan;
import com.flagstone.transform.as3.abcfile.instruction.stack_management.InstructionPushnull;
import com.flagstone.transform.as3.abcfile.instruction.stack_management.InstructionPushscope;
import com.flagstone.transform.as3.abcfile.instruction.stack_management.InstructionPushshort;
import com.flagstone.transform.as3.abcfile.instruction.stack_management.InstructionPushstringExtd;
import com.flagstone.transform.as3.abcfile.instruction.stack_management.InstructionPushtrue;
import com.flagstone.transform.as3.abcfile.instruction.stack_management.InstructionPushuintExtd;
import com.flagstone.transform.as3.abcfile.instruction.stack_management.InstructionPushundefined;
import com.flagstone.transform.as3.abcfile.instruction.stack_management.InstructionPushwith;
import com.flagstone.transform.as3.abcfile.instruction.stack_management.InstructionSwap;
import com.flagstone.transform.as3.abcfile.structures.ExceptionInfo;
import com.flagstone.transform.as3.abcfile.structures.MethodBodyInfo;
import com.flagstone.transform.as3.abcfile.utils.ABCLogger;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class ContainerInstructionV2
implements IContainerInstruction {
    public static final ABCLogger LOGGER = new ABCLogger(ContainerInstructionV2.class);

    private MethodBodyInfo methodBodyInfo;
    private List < IInstruction > allInstructions;
    private Map < Integer, Integer > mapInstructions = new HashMap();

    private ArrayList < ExceptionInfo > exceptions;
    private ArrayList < IInstruction > exceptionsFrom = new ArrayList();
    private ArrayList < IInstruction > exceptionsTo = new ArrayList();
    private ArrayList < IInstruction > exceptionsTarget = new ArrayList();

    public ContainerInstructionV2(List < IInstruction > instructions, MethodBodyInfo methodBodyInfo) {
        this.allInstructions = instructions;
        this.methodBodyInfo = methodBodyInfo;
        this.exceptions = methodBodyInfo.getException_info();
        updateMapInstructions();

        initExceptions();
        updateExceptionsOffsetAll();
        updateSwitchOffsetAll();
        updateJumpOffsetAll();
        updateIncomingInstructions();
    }

    public void setJumpTarget(IInstruction instruction, IInstruction target) {
        if ((instruction instanceof IInstructionBaseJump)) {
            ((IInstructionBaseJump) instruction).setTarget(target);
            updateAll();
        }
    }

    public int moveInstructionTo(IInstruction instruction, int posId) {
        if (posId != this.allInstructions.indexOf(instruction)) {
            if (posId < this.allInstructions.indexOf(instruction)) {
                this.allInstructions.remove(instruction);
                this.allInstructions.add(posId, instruction);
            } else {
                this.allInstructions.add(posId, instruction);
                this.allInstructions.remove(instruction);
            }
            updateAll();
        } else {
            return -1;
        }

        return 0;
    }

    public void addInstruction(IInstruction instruction) {
        this.allInstructions.add(instruction);
        updateAll(instruction);
    }

    public void addInstruction(IInstruction instruction, int posId) {
        this.allInstructions.add(posId, instruction);
        updateAll(instruction);
    }

    public void addInstructions(List < IInstruction > instructions) {
        instructions.addAll(instructions);
        updateAll(instructions);
    }

    public void addInstructions(List < IInstruction > instructions, int posId) {
        instructions.addAll(posId, instructions);
        updateAll(instructions);
    }

    public int removeInstruction(int posId, boolean force) {
        return removeInstruction((IInstruction) this.allInstructions.get(posId), force);
    }

    public int removeInstruction(IInstruction instruction, boolean force) {
        int posId = this.allInstructions.indexOf(instruction);
        if (posId >= 0) {
            boolean isExceptionTarget = !checkExceptionTargets(posId, posId).isEmpty();

            Set < IInstruction > sourceInstructions = checkJumpTargets(posId, posId);

            if ((isExceptionTarget) || (!sourceInstructions.isEmpty())) {
                if (force) {
                    LOGGER.info("Targets to this instruction, be carefull !!!");

                    if (sourceInstructions != null) {
                        for (IInstruction ins: sourceInstructions) {
                            removeInstruction(ins, true);
                        }
                    }
                } else {
                    return -1;
                }
            }

            posId = this.allInstructions.indexOf(instruction);
            this.allInstructions.remove(posId);
            updateAll(instruction);
        }
        return 0;
    }

    public List < IInstruction > removeInstructions(List < IInstruction > instructions, boolean force) {
        List < IInstruction > notRemoved = new ArrayList();

        for (IInstruction insToRemove: instructions) {
            if (removeInstruction(insToRemove, force) < 0) {
                notRemoved.add(insToRemove);
            }
        }

        return notRemoved;
    }

    public List < IInstruction > removeInstructions(int[] posId, boolean force) {
        List < IInstruction > notRemoved = new ArrayList();

        List < IInstruction > instructionsToRemove = new ArrayList();
        int[] arrayOfInt;
        int j = (arrayOfInt = posId).length;
        for (int i = 0; i < j; i++) {
            int o = arrayOfInt[i];
            instructionsToRemove.add((IInstruction) this.allInstructions.get(o));
        }

        for (IInstruction insToRemove: instructionsToRemove) {
            if (removeInstruction(insToRemove, force) < 0) {
                notRemoved.add(insToRemove);
            }
        }

        return notRemoved;
    }

    public int setInstruction(IInstruction instruction, int posId) {
        this.allInstructions.set(posId, instruction);
        updateAll(instruction);
        return 0;
    }

    public List < IInstruction > getInstructions() {
        return this.allInstructions;
    }

    public void setInstructionDate(int id, byte[] date) {
        ((IInstruction) this.allInstructions.get(id)).setDate(date);
        updateAll((IInstruction) this.allInstructions.get(id));
    }

    private void updateAll() {
        updateMapInstructions();
        updateJumpOffsetAll();
        updateSwitchOffsetAll();
        updateIncomingInstructions();
        updateExceptionsOffsetAll();
    }

    private void updateAll(IInstruction instruction) {
        updateMapInstructions();
        updateJumpOffsetAll();

        updateSwitchOffsetAll();
        updateIncomingInstructions();
        updateExceptionsOffsetAll();
    }

    private void updateAll(List < IInstruction > instructions) {
        updateMapInstructions();
        updateJumpOffsetAll();

        updateIncomingInstructions();
        updateSwitchOffsetAll();
        updateExceptionsOffsetAll();
    }

    private void updateJumpOffsetAll() {
        for (IInstruction ins: this.allInstructions) {
            if (((ins instanceof IInstructionBaseJump)) && (((IInstructionBaseJump) ins).getTarget() != null)) {
                IInstructionBaseJump insJump = (IInstructionBaseJump) ins;
                insJump.setOffset(insJump.getTarget().getPosition() - ins.getPosition() - 4);
            } else if (((ins instanceof IInstructionBaseJump)) && (ins.getPosition() != -1)) {
                IInstructionBaseJump insJump = (IInstructionBaseJump) ins;

                Integer id = (Integer) this.mapInstructions.get(Integer.valueOf(ins.getPosition() + insJump.getOffset() + 4));

                if (id == null) {
                    LOGGER.info("No target id for this jump:" + ins + " set 0 offset");
                    insJump.setOffset(0);
                    id = (Integer) this.mapInstructions.get(Integer.valueOf(ins.getPosition() + insJump.getOffset() + 4));
                }
                IInstruction insTarget = (IInstruction) this.allInstructions.get(id.intValue());
                if (insTarget != null) {
                    insJump.setTarget(insTarget);
                } else {
                    LOGGER.info(" ins null in update jump");
                }
            }
        }
    }

    private void updateSwitchOffsetAll() {
        for (IInstruction ins: this.allInstructions) {

            if (((ins instanceof InstructionLookupswitch)) && (((InstructionLookupswitch) ins).targets.size() > 0)) {
                InstructionLookupswitch insSwitch = (InstructionLookupswitch) ins;
                insSwitch.case_offsets.clear();
                for (int i = 0; i < insSwitch.targets.size(); i++) {

                    int pos = ((IInstruction) insSwitch.targets.get(i)).getPosition() - insSwitch.position;

                    insSwitch.case_offsets.add(Integer.valueOf(pos));
                }
            } else if ((ins instanceof InstructionLookupswitch)) {
                InstructionLookupswitch insSwitch = (InstructionLookupswitch) ins;
                for (Iterator < Integer > p = insSwitch.targetsPos.iterator(); p.hasNext();) {
                    int pos = ((Integer) p.next()).intValue();
                    Integer posId = (Integer) this.mapInstructions.get(Integer.valueOf(pos));
                    if (posId != null) {
                        insSwitch.targets.add((IInstruction) this.allInstructions.get(posId.intValue()));

                        ((IInstruction) this.allInstructions.get(posId.intValue())).addIncomingInstruction(insSwitch);
                    } else {
                        LOGGER.error("Nie znaleziono ofsset w switch");
                    }
                }
            }
        }
    }

    private void updateMapInstructions() {
        this.mapInstructions.clear();
        int pos = 0;
        for (IInstruction ins: this.allInstructions) {
            int id = this.allInstructions.indexOf(ins);
            this.mapInstructions.put(Integer.valueOf(pos), Integer.valueOf(id));
            ins.setPosition(pos);
            try {
                pos += ins.getSize();
            } catch (Exception e) {
                LOGGER.error("Blad pobierania rozmiaru instrukcji", e);
            }
        }
    }

    private void updateIncomingInstructions() {
        updateIncomingInstructions(this.allInstructions);
    }

    private void updateIncomingInstructions(List < IInstruction > instructions) {
        for (IInstruction ins: instructions) {
            ins.getIncomingInstruction().clear();
            if ((ins instanceof InstructionLookupswitch)) {
                ((InstructionLookupswitch) ins).case_offsets.clear();
            }
        }

        for (IInstruction ins: instructions) {
            if (((ins instanceof IInstructionBaseJump)) && (((IInstructionBaseJump) ins).getTarget() != null)) {
                int id = this.allInstructions.indexOf(((IInstructionBaseJump) ins).getTarget());
                if (id != -1) {
                    ((IInstruction) this.allInstructions.get(id)).addIncomingInstruction(ins);
                } else {
                    LOGGER.error("Nie ma takeij instrukcji z id " + id);
                }
            } else if (((ins instanceof IInstructionBaseJump)) && (((IInstructionBaseJump) ins).getOffset() != -1)) {
                Integer id = (Integer) this.mapInstructions.get(Integer.valueOf(ins.getPosition() + ((IInstructionBaseJump) ins).getOffset() + 4));
                if (id != null) {
                    ((IInstruction) this.allInstructions.get(id.intValue())).addIncomingInstruction(ins);
                } else {
                    System.err.println("Nie znaleziono ofsset w updateIncomingInstructions");
                }
            } else if ((ins instanceof InstructionLookupswitch)) {
                InstructionLookupswitch insSwitch = (InstructionLookupswitch) ins;
                for (IInstruction instarget: insSwitch.targets) {
                    int pos = instarget.getPosition() - insSwitch.position;

                    insSwitch.case_offsets.add(Integer.valueOf(pos));
                }
            }
        }
    }

    private void initExceptions() {
        for (ExceptionInfo exc: this.exceptions) {
            IInstruction from;

            try {
                from = (IInstruction) this.allInstructions.get(((Integer) this.mapInstructions.get(Integer.valueOf(exc.getFrom()))).intValue());
            } catch (Exception e) {
                LOGGER.error("ContainerInstructionV2 - Blad exception");
                from = (IInstruction) this.allInstructions.get(((Integer) this.mapInstructions.get(Integer.valueOf(exc.getFrom() + 1))).intValue());
            }
            IInstruction to;
            try {
                to = (IInstruction) this.allInstructions.get(((Integer) this.mapInstructions.get(Integer.valueOf(exc.getTo()))).intValue());
            } catch (Exception e) {
                LOGGER.error("ContainerInstructionV2 - Blad exception");
                to = (IInstruction) this.allInstructions.get(((Integer) this.mapInstructions.get(Integer.valueOf(exc.getTo() + 1))).intValue());
            }
            IInstruction target;
            try {
                target = (IInstruction) this.allInstructions.get(((Integer) this.mapInstructions.get(Integer.valueOf(exc.getTarget()))).intValue());
            } catch (Exception e) {
                LOGGER.error("ContainerInstructionV2 - Blad exception");
                target = (IInstruction) this.allInstructions.get(((Integer) this.mapInstructions.get(Integer.valueOf(exc.getTarget() + 1))).intValue());
            }

            this.exceptionsFrom.add(from);
            this.exceptionsTo.add(to);
            this.exceptionsTarget.add(target);
        }
    }

    private void updateExceptionsOffsetAll() {
        int i = 0;
        for (ExceptionInfo exc: this.exceptions) {
            exc.setFrom(((IInstruction) this.exceptionsFrom.get(i)).getPosition());
            exc.setTo(((IInstruction) this.exceptionsTo.get(i)).getPosition());
            exc.setTarget(((IInstruction) this.exceptionsTarget.get(i)).getPosition());
            i++;
        }
    }

    public Set < IInstruction > checkTargets(int from, int to) {
        Set < IInstruction > targets = new HashSet();

        if (from < to) {
            targets.addAll(checkExceptionTargets(new CopyOnWriteArrayList(this.allInstructions.subList(from, to + 1))));
            Set < IInstruction > tmpSet = checkJumpTargets(new CopyOnWriteArrayList(this.allInstructions.subList(from, to + 1)));
            targets.addAll(tmpSet);
        } else {
            targets.addAll(checkExceptionTargets(new CopyOnWriteArrayList(this.allInstructions.subList(to, from + 1))));
            targets.addAll(checkJumpTargets(new CopyOnWriteArrayList(this.allInstructions.subList(to, from + 1))));
        }
        return targets;
    }

    public Set < IInstruction > checkTargets(IInstruction from, IInstruction to) {
        return checkTargets(getPosId(from), getPosId(to));
    }

    public Set < IInstruction > checkTargets(Collection < IInstruction > instructions) {
        Set < IInstruction > targets = new HashSet();
        targets.addAll(checkExceptionTargets(instructions));
        targets.addAll(checkJumpTargets(instructions));
        return targets;
    }

    public boolean checkTargets(int[] posId) {
        if ((checkExceptionTargets(posId)) || (!checkJumpTargets(posId).isEmpty())) {
            return true;
        }
        return false;
    }

    public Set < IInstruction > checkJumpTargets(int from, int to) {
        Set < IInstruction > jumps = new HashSet();
        for (int i = from; i <= to; i++) {
            if (((IInstruction) this.allInstructions.get(i)).getIncomingInstruction().size() > 0) {
                jumps.addAll(((IInstruction) this.allInstructions.get(i)).getIncomingInstruction());
            }
        }

        return jumps;
    }

    public Set < IInstruction > checkJumpTargets(IInstruction from, IInstruction to) {
        int toPosId;
        int fromPosId;
        if (this.allInstructions.indexOf(from) < this.allInstructions.indexOf(to)) {
            fromPosId = this.allInstructions.indexOf(from);
            toPosId = this.allInstructions.indexOf(to);
        } else {
            toPosId = this.allInstructions.indexOf(from);
            fromPosId = this.allInstructions.indexOf(to);
        }

        return checkJumpTargets(fromPosId, toPosId);
    }

    public Set < IInstruction > checkJumpTargets(Collection < IInstruction > instructions) {
        Set < IInstruction > jumps = new HashSet();
        for (IInstruction instruction: instructions) {
            if (instruction.getIncomingInstruction().size() > 0) {
                jumps.addAll(instruction.getIncomingInstruction());
            }
        }
        return jumps;
    }

    public Set < IInstruction > checkJumpTargets(int[] posId) {
        Set < IInstruction > jumps = new HashSet();
        int[] arrayOfInt;
        int j = (arrayOfInt = posId).length;
        for (int i = 0; i < j; i++) {
            int id = arrayOfInt[i];
            if (((IInstruction) this.allInstructions.get(id)).getIncomingInstruction().size() > 0) {
                jumps.addAll(((IInstruction) this.allInstructions.get(id)).getIncomingInstruction());
            }
        }
        return jumps;
    }

    public Set < IInstruction > checkExceptionTargets(int from, int to) {
        Set < IInstruction > exceptionTargets = new HashSet();
        ArrayList < Integer > targets = getExceptionTargets();
        for (Iterator localIterator = targets.iterator(); localIterator.hasNext();) {
            int targ = ((Integer) localIterator.next()).intValue();
            if ((targ >= from) && (targ <= to)) {
                exceptionTargets.add((IInstruction) this.allInstructions.get(targ));
            }
            if ((targ >= to) && (targ <= from)) {
                exceptionTargets.add((IInstruction) this.allInstructions.get(targ));
            }
        }
        return exceptionTargets;
    }

    public Set < IInstruction > checkExceptionTargets(Collection < IInstruction > instructions) {
        Set < IInstruction > insTarget = new HashSet();
        Collection < IInstruction > targets = getExceptionInstructionsTargets();
        for (IInstruction instruction: instructions) {
            if (targets.contains(instruction)) {
                insTarget.add(instruction);
            }
        }
        return insTarget;
    }

    public boolean checkExceptionTargets(int[] posId) {
        ArrayList < Integer > targets = getExceptionTargets();
        int j;
        int i;
        int[] arrayOfInt = posId;
        j = arrayOfInt.length;
        i = 0;
        for (Iterator localIterator = targets.iterator(); localIterator.hasNext();) {
            int targ = ((Integer) localIterator.next()).intValue();

            int id = arrayOfInt[i];
            if (targ == id) {
                return true;
            }
            i++;
        }

        return false;
    }

    private Collection < IInstruction > getExceptionInstructionsTargets() {
        List < IInstruction > targets = new ArrayList();
        IInstruction ins;
        for (int j = 0; j < this.allInstructions.size(); j++) {
            ins = (IInstruction) this.allInstructions.get(j);

            if ((ins instanceof InstructionLookupswitch)) {
                InstructionLookupswitch insSwitch = (InstructionLookupswitch) ins;

                for (IInstruction in: insSwitch.targets) {
                    targets.add( in );
                }
            }
        }

        for (IInstruction in: this.exceptionsFrom) {
            targets.add( in );
        }
        for (IInstruction in: this.exceptionsTo) {
            targets.add( in );
        }

        for (IInstruction in: this.exceptionsTarget) {
            targets.add( in );
        }

        return targets;
    }

    private ArrayList < Integer > getExceptionTargets() {
        ArrayList < Integer > targets = new ArrayList();
        IInstruction ins;
        for (int j = 0; j < this.allInstructions.size(); j++) {
            ins = (IInstruction) this.allInstructions.get(j);

            if ((ins instanceof InstructionLookupswitch)) {
                InstructionLookupswitch insSwitch = (InstructionLookupswitch) ins;

                for (IInstruction in: insSwitch.targets) {
                    targets.add(Integer.valueOf(this.allInstructions.indexOf( in )));
                }
            }
        }

        for (IInstruction in: this.exceptionsFrom) {
            targets.add(Integer.valueOf(this.allInstructions.indexOf( in )));
        }
        for (IInstruction in: this.exceptionsTo) {
            targets.add(Integer.valueOf(this.allInstructions.indexOf( in )));
        }

        for (IInstruction in: this.exceptionsTarget) {
            targets.add(Integer.valueOf(this.allInstructions.indexOf( in )));
        }

        return targets;
    }

    public byte[] getBytes() throws Exception {
        ByteArrayOutputStream byteDate = new ByteArrayOutputStream();

        for (IInstruction ins: this.allInstructions) {
            byteDate.write(ins.getBytes());
        }
        return byteDate.toByteArray();
    }

    public static IContainerInstruction czytajKod(MethodBodyInfo method, AbcFile abc) throws Exception {
        ByteBufferFlash bbufm = ByteBufferFlash.allocate(method.getCode_length());
        bbufm.put(method.getCode());
        bbufm.rewind();
        return readCode(bbufm, method.getException_info(), method, abc);
    }

    public static IContainerInstruction czytajKod2(MethodBodyInfo method, AbcFile abc) throws Exception {
        ByteBufferFlash bbufm = ByteBufferFlash.allocate(method.getCode_length());
        bbufm.put(method.getCode());
        bbufm.rewind();
        return readCode(bbufm, method.getException_info(), method, abc);
    }

    public static IContainerInstruction readCode(ByteBufferFlash bbuf, ArrayList < ExceptionInfo > exception_info, MethodBodyInfo method, AbcFile abc) throws Exception {
        List < IInstruction > instructions = new ArrayList();
        bbuf.position(0);

        while (bbuf.position() < bbuf.capacity()) {
            int position = bbuf.position();
            int opcode = bbuf.unsignedByte();

            switch (Opcode.getOpcode(opcode)) {

                case OP_add:
                case OP_add_d:
                    instructions.add(new InstructionNop(position));
                    break;
                case OP_add_i:
                    instructions.add(new InstructionThrow(position));
                    break;
                case OP_applytype:
                    instructions.add(new InstructionGetsuper(bbuf, position));
                    break;

                case OP_astype:
                    instructions.add(new InstructionSetsuperExtd(bbuf, position, abc.getConstant_pool()));
                    break;

                case OP_astypelate:
                    instructions.add(new InstructionDxnsExtd(bbuf, position, abc.getConstant_pool()));
                    break;
                case OP_bitand:
                    instructions.add(new InstructionDxnslate(position));
                    break;
                case OP_bitnot:
                    instructions.add(new InstructionKill(bbuf, position));
                    break;
                case OP_bitor:
                    instructions.add(new InstructionLabel(position));
                    break;
                case OP_bitxor:
                    instructions.add(new InstructionIfnlt(bbuf, position));
                    break;
                case OP_bkpt:
                    instructions.add(new InstructionIfnle(bbuf, position));
                    break;
                case OP_bkptline:
                    instructions.add(new InstructionIfngt(bbuf, position));
                    break;
                case OP_call:
                    instructions.add(new InstructionIfnge(bbuf, position));
                    break;
                case OP_callinterface:
                    instructions.add(new InstructionJump(bbuf, position));
                    break;
                case OP_callmethod:
                    instructions.add(new InstructionIftrue(bbuf, position));
                    break;
                case OP_callproperty:
                    instructions.add(new InstructionIffalse(bbuf, position));
                    break;
                case OP_callproplex:
                    instructions.add(new InstructionIfeq(bbuf, position));
                    break;
                case OP_callpropvoid:
                    instructions.add(new InstructionIfne(bbuf, position));
                    break;
                case OP_callstatic:
                    instructions.add(new InstructionIflt(bbuf, position));
                    break;
                case OP_callsuper:
                    instructions.add(new InstructionIfle(bbuf, position));
                    break;
                case OP_callsuperid:
                    instructions.add(new InstructionIfgt(bbuf, position));
                    break;
                case OP_callsupervoid:
                    instructions.add(new InstructionIfge(bbuf, position));
                    break;
                case OP_checkfilter:
                    instructions.add(new InstructionIfstricteq(bbuf, position));
                    break;
                case OP_coerce:
                    instructions.add(new InstructionIfstrictne(bbuf, position));
                    break;
                case OP_coerce_a:
                    instructions.add(new InstructionLookupswitch(bbuf, position));
                    break;
                case OP_coerce_b:
                    instructions.add(new InstructionPushwith(position));
                    break;
                case OP_coerce_d:
                    instructions.add(new InstructionPopscope(position));
                    break;
                case OP_coerce_i:
                    instructions.add(new InstructionNextname(position));
                    break;
                case OP_coerce_o:
                    instructions.add(new InstructionHasnext(position));
                    break;
                case OP_coerce_s:
                    instructions.add(new InstructionPushnull(position));
                    break;
                case OP_coerce_u:
                    instructions.add(new InstructionPushundefined(position));
                    break;

                case OP_concat:
                case OP_construct:
                    instructions.add(new InstructionNextvalue(position));
                    break;
                case OP_constructprop:
                    instructions.add(new InstructionPushbyte(bbuf, position));
                    break;
                case OP_constructsuper:
                    instructions.add(new InstructionPushshort(bbuf, position));
                    break;
                case OP_convert_b:
                    instructions.add(new InstructionPushtrue(position));
                    break;
                case OP_convert_d:
                    instructions.add(new InstructionPushfalse(position));
                    break;
                case OP_convert_i:
                    instructions.add(new InstructionPushnan(position));
                    break;
                case OP_convert_o:
                    instructions.add(new InstructionPop(position));
                    break;
                case OP_convert_s:
                    instructions.add(new InstructionDup(position));
                    break;
                case OP_convert_u:
                    instructions.add(new InstructionSwap(position));
                    break;

                case OP_debug:
                    instructions.add(new InstructionPushstringExtd(bbuf, position, abc.getConstant_pool()));
                    break;

                case OP_debugfile:
                    instructions.add(new InstructionPushintExtd(bbuf, position, abc.getConstant_pool()));
                    break;

                case OP_debugline:
                    instructions.add(new InstructionPushuintExtd(bbuf, position, abc.getConstant_pool()));
                    break;

                case OP_declocal:
                    instructions.add(new InstructionPushdoubleExtd(bbuf, position, abc.getConstant_pool()));
                    break;
                case OP_declocal_i:
                    instructions.add(new InstructionPushscope(position));
                    break;

                case OP_decrement:
                    instructions.add(new InstructionPushnamespaceExtd(bbuf, position, abc.getConstant_pool()));
                    break;
                case OP_decrement_i:
                    instructions.add(new InstructionHasnext2(bbuf, position));
                    break;

                case OP_dxnslate:
                    instructions.add(new InstructionNewfunctionExtd(bbuf, position, abc));
                    break;
                case OP_equals:
                    instructions.add(new InstructionCall(bbuf, position));
                    break;
                case OP_esc_xattr:
                    instructions.add(new InstructionConstruct(bbuf, position));
                    break;

                case OP_esc_xelem:
                    instructions.add(new InstructionCallmethodExtd(bbuf, position, abc));
                    break;

                case OP_finddef:
                    instructions.add(new InstructionCallstaticExtd(bbuf, position, abc));
                    break;

                case OP_findproperty:
                    instructions.add(new InstructionCallsuperExtd(bbuf, position, abc.getConstant_pool()));
                    break;

                case OP_findpropstrict:
                    instructions.add(new InstructionCallpropertyExtd(bbuf, position, abc.getConstant_pool()));
                    break;
                case OP_getdescendants:
                    instructions.add(new InstructionReturnvoid(position));
                    break;
                case OP_getglobalscope:
                    instructions.add(new InstructionReturnvalue(position));
                    break;
                case OP_getglobalslot:
                    instructions.add(new InstructionConstructsuper(bbuf, position));
                    break;

                case OP_getlex:
                    instructions.add(new InstructionConstructpropExtd(bbuf, position, abc.getConstant_pool()));
                    break;

                case OP_getlocal:
                case OP_getlocal0:
                    instructions.add(new InstructionCallproplexExtd(bbuf, position, abc.getConstant_pool()));
                    break;

                case OP_getlocal1:
                case OP_getlocal2:
                    instructions.add(new InstructionCallsupervoidExtd(bbuf, position, abc.getConstant_pool()));
                    break;

                case OP_getlocal3:
                    instructions.add(new InstructionCallpropvoidExtd(bbuf, position, abc.getConstant_pool()));
                    break;
                case OP_getpropertylate:
                    instructions.add(new InstructionNewobject(bbuf, position));
                    break;
                case OP_getscopeobject:
                    instructions.add(new InstructionNewarray(bbuf, position));
                    break;
                case OP_getslot:
                    instructions.add(new InstructionNewactivation(position));
                    break;

                case OP_getsuper:
                    instructions.add(new InstructionNewclassExtd(bbuf, position, abc));
                    break;

                case OP_greaterequals:
                    instructions.add(new InstructionGetdescendantsExtd(bbuf, position, abc.getConstant_pool()));
                    break;

                case OP_greaterthan:
                    instructions.add(new InstructionNewcatchExtd(bbuf, position, abc));
                    break;

                case OP_hasnext:
                    instructions.add(new InstructionFindpropstrictExtd(bbuf, position, abc.getConstant_pool()));
                    break;

                case OP_hasnext2:
                    instructions.add(new InstructionFindpropertyExtd(bbuf, position, abc.getConstant_pool()));
                    break;
                case OP_ifeq:
                    bbuf.unsigned32int();
                    int size = bbuf.position() - position;
                    byte[] dane = new byte[size - 1];
                    bbuf.position(position + 1);
                    bbuf.get(dane);
                    instructions.add(new Instruction(Opcode.OP_finddef, dane, position));
                    break;

                case OP_iffalse:
                    instructions.add(new InstructionGetlexExtd(bbuf, position, abc.getConstant_pool()));
                    break;

                case OP_ifge:
                    instructions.add(new InstructionSetpropertyExtd(bbuf, position, abc.getConstant_pool()));
                    break;
                case OP_ifgt:
                    instructions.add(new InstructionGetlocal(bbuf, position));
                    break;
                case OP_ifle:
                    instructions.add(new InstructionSetlocal(bbuf, position));
                    break;
                case OP_iflt:
                    instructions.add(new InstructionGetglobalscope(position));
                    break;
                case OP_ifne:
                    instructions.add(new InstructionGetscopeobject(bbuf, position));
                    break;

                case OP_ifnge:
                    instructions.add(new InstructionGetpropertyExtd(bbuf, position, abc.getConstant_pool()));
                    break;

                case OP_ifngt:
                case OP_ifnle:
                    instructions.add(new InstructionInitpropertyExtd(bbuf, position, abc.getConstant_pool()));
                    break;

                case OP_ifnlt:
                case OP_ifstricteq:
                    instructions.add(new InstructionDeletepropertyExtd(bbuf, position, abc.getConstant_pool()));
                    break;

                case OP_ifstrictne:
                case OP_iftrue:
                    instructions.add(new InstructionGetslot(bbuf, position));
                    break;
                case OP_in:
                    instructions.add(new InstructionSetslot(bbuf, position));
                    break;
                case OP_inclocal:
                    instructions.add(new InstructionGetglobalslot(bbuf, position));
                    break;
                case OP_inclocal_i:
                    instructions.add(new InstructionSetglobalslot(bbuf, position));
                    break;
                case OP_increment:
                    instructions.add(new InstructionConvert_s(position));
                    break;

                case OP_increment_i:
                case OP_initproperty:
                case OP_instanceof:
                    instructions.add(new InstructionConvert_i(position));
                    break;
                case OP_istype:
                    instructions.add(new InstructionConvert_u(position));
                    break;
                case OP_istypelate:
                    instructions.add(new InstructionConvert_d(position));
                    break;
                case OP_jump:
                    instructions.add(new InstructionConvert_b(position));
                    break;
                case OP_kill:
                    instructions.add(new InstructionConvert_o(position));
                    break;
                case OP_label:
                    instructions.add(new InstructionCheckfilter(position));
                    break;

                case OP_lessequals:
                    instructions.add(new InstructionCoerceExtd(bbuf, position, abc.getConstant_pool()));
                    break;

                case OP_lessthan:
                case OP_lf32:
                    instructions.add(new InstructionCoerce_a(position));
                    break;

                case OP_lf64:
                case OP_li16:
                case OP_li32:
                    instructions.add(new InstructionCoerce_s(position));
                    break;

                case OP_li8:
                    instructions.add(new InstructionAstypeExtd(bbuf, position, abc.getConstant_pool()));
                    break;
                case OP_lookupswitch:
                    instructions.add(new InstructionAstypelate(position));
                    break;

                case OP_lshift:
                case OP_modulo:
                case OP_multiply:
                    instructions.add(new InstructionNegate(position));
                    break;
                case OP_multiply_i:
                    instructions.add(new InstructionIncrement(position));
                    break;
                case OP_negate:
                    instructions.add(new InstructionInclocal(bbuf, position));
                    break;
                case OP_negate_i:
                    instructions.add(new InstructionDecrement(position));
                    break;
                case OP_newactivation:
                    instructions.add(new InstructionDeclocal(bbuf, position));
                    break;
                case OP_newarray:
                    instructions.add(new InstructioneTypeof(position));
                    break;
                case OP_newcatch:
                    instructions.add(new InstructionNot(position));
                    break;
                case OP_newclass:
                    instructions.add(new InstructionBitnot(position));
                    break;

                case OP_newfunction:
                case OP_newobject:
                case OP_nextname:
                    instructions.add(new InstructionAdd(position));
                    break;
                case OP_nextvalue:
                    instructions.add(new InstructionSubtract(position));
                    break;
                case OP_nop:
                    instructions.add(new InstructionMultiply(position));
                    break;
                case OP_not:
                    instructions.add(new InstructionDivide(position));
                    break;
                case OP_pop:
                    instructions.add(new InstructionModulo(position));
                    break;
                case OP_popscope:
                    instructions.add(new InstructionLshift(position));
                    break;
                case OP_pushbyte:
                    instructions.add(new InstructionRshift(position));
                    break;
                case OP_pushconstant:
                    instructions.add(new InstructionUrshift(position));
                    break;
                case OP_pushdouble:
                    instructions.add(new InstructionBitand(position));
                    break;
                case OP_pushfalse:
                    instructions.add(new InstructionBitor(position));
                    break;
                case OP_pushint:
                    instructions.add(new InstructionBitxor(position));
                    break;
                case OP_pushnamespace:
                    instructions.add(new InstructionEquals(position));
                    break;
                case OP_pushnan:
                    instructions.add(new InstructionStrictequals(position));
                    break;
                case OP_pushnull:
                    instructions.add(new InstructionLessthan(position));
                    break;
                case OP_pushscope:
                    instructions.add(new InstructionLessequals(position));
                    break;
                case OP_pushshort:
                    instructions.add(new InstructionGreaterthan(position));
                    break;
                case OP_pushstring:
                    instructions.add(new InstructionGreaterequals(position));
                    break;
                case OP_pushtrue:
                    instructions.add(new InstructionInstanceof(position));
                    break;

                case OP_pushuint:
                    instructions.add(new InstructionIstypeExtd(bbuf, position, abc.getConstant_pool()));
                    break;
                case OP_pushundefined:
                    instructions.add(new InstructionIstypelate(position));
                    break;
                case OP_pushwith:
                    instructions.add(new InstructionIn(position));
                    break;
                case OP_returnvalue:
                    instructions.add(new InstructionIncrement_i(position));
                    break;
                case OP_returnvoid:
                    instructions.add(new InstructionDecrement_i(position));
                    break;
                case OP_rshift:
                    instructions.add(new InstructionInclocal_i(bbuf, position));
                    break;
                case OP_setglobalslot:
                    instructions.add(new InstructionDeclocal_i(bbuf, position));
                    break;
                case OP_setlocal:
                    instructions.add(new InstructionNegate_i(position));
                    break;
                case OP_setlocal0:
                    instructions.add(new InstructionAdd_i(position));
                    break;
                case OP_setlocal1:
                    instructions.add(new InstructionSubtract_i(position));
                    break;
                case OP_setlocal2:
                    instructions.add(new InstructionMultiply_i(position));
                    break;
                case OP_setlocal3:
                    instructions.add(new InstructionGetlocal_0(position));
                    break;
                case OP_setproperty:
                    instructions.add(new InstructionGetlocal_1(position));
                    break;
                case OP_setpropertylate:
                    instructions.add(new InstructionGetlocal_2(position));
                    break;
                case OP_setslot:
                    instructions.add(new InstructionGetlocal_3(position));
                    break;
                case OP_setsuper:
                    instructions.add(new InstructionSetlocal_0(position));
                    break;
                case OP_strictequals:
                    instructions.add(new InstructionSetlocal_1(position));
                    break;
                case OP_subtract:
                    instructions.add(new InstructionSetlocal_2(position));
                    break;
                case OP_subtract_i:
                    instructions.add(new InstructionSetlocal_3(position));
                    break;
                case OP_swap:
                    instructions.add(new InstructionDebug(bbuf, position));
                    break;
                case OP_throw:
                    instructions.add(new InstructionDebugline(bbuf, position));
                    break;

                case OP_typeof:
                    instructions.add(new InstructionDebugfileExtd(bbuf, position, abc.getConstant_pool()));
                    break;
                case OP_getproperty:
                    instructions.add(new InstructionApplyType(bbuf, position));
                    break;

                case OP_divide:
                    instructions.add(new InstructionLoadShortIndirect(position));
                    break;
                case OP_deleteproperty:
                case OP_deletepropertylate:
                case OP_dup:
                case OP_dxns:
                default:
                    LOGGER.error(String.format("UNKNOWN OPCODE 0x%s in method id: %d", new Object[] {
                        Integer.toString(opcode, 16).toUpperCase(), Integer.valueOf(method.getId())
                    }));
                    int sizeOther = bbuf.position() - position;

                    byte[] daneOther = new byte[sizeOther - 1];
                    bbuf.position(position + 1);
                    bbuf.get(daneOther);
                    instructions.add(new Instruction(Opcode.getOpcode(opcode), daneOther, position));
            }

        }

        IContainerInstruction container = new ContainerInstructionV2(instructions, method);

        return container;
    }

    public MethodBodyInfo getMethodBodyInfo() {
        return this.methodBodyInfo;
    }

    public void setMethodBodyInfo(MethodBodyInfo methodBodyInfo) {
        this.methodBodyInfo = methodBodyInfo;
    }

    public int getPosId(IInstruction instruction) {
        return this.allInstructions.indexOf(instruction);
    }

    public void moveInstructionsTo(int fromId, int toId, int to) {
        if (fromId <= to) {
            Collections.rotate(this.allInstructions.subList(fromId, to), -1 * (toId + 1 - fromId));
        } else {
            Collections.rotate(this.allInstructions.subList(to, toId + 1), toId + 1 - fromId);
        }
        updateAll();
    }

    public void glueJump(IInstructionBaseJump insSourceJump, IInstructionBaseJump insDestinationJump) {
        if (this.allInstructions.indexOf(insSourceJump) < this.allInstructions.indexOf(insDestinationJump)) {
            moveInstructionsTo(this.allInstructions.indexOf(insDestinationJump), this.allInstructions.size() - 1, this.allInstructions.indexOf(insSourceJump) + 1);
        } else {
            moveInstructionsTo(this.allInstructions.indexOf(insDestinationJump), this.allInstructions.size() - 1, this.allInstructions.indexOf(insSourceJump) + 1);
        }
    }

    public void glueJump(IInstructionBaseJump insSourceJump) {
        if (this.allInstructions.indexOf(insSourceJump) < this.allInstructions.indexOf(insSourceJump.getTarget())) {
            moveInstructionsTo(this.allInstructions.indexOf(insSourceJump.getTarget()), this.allInstructions.size() - 1, this.allInstructions.indexOf(insSourceJump) + 1);
        } else {
            moveInstructionsTo(this.allInstructions.indexOf(insSourceJump.getTarget()), this.allInstructions.indexOf(insSourceJump.getTarget()) - 1, this.allInstructions.indexOf(insSourceJump) + 1);
        }
    }

    public String toString() {
        return String.format("Instructions in container: %s", new Object[] {
            this.allInstructions
        });
    }

    public IInstruction getInstructionByOffset(int offset) {
        int posId = ((Integer) this.mapInstructions.get(Integer.valueOf(offset))).intValue();
        return (IInstruction) this.allInstructions.get(posId);
    }

    public Integer getPosId(int offset) {
        return (Integer) this.mapInstructions.get(Integer.valueOf(offset));
    }
}