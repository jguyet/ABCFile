package com.flagstone.transform.as3.abcfile.stack;

public class StackFactory {
    public static < E > IStackInt < E > getStack() {
        return SingletonHolder.getStack();
    }

    public static < E > IStackInt < E > getScopeStack() {
        return SingletonHolder.getScopestack();
    }

    public static < E > IStackInt < E > getJumpstack() {
        return SingletonHolder.getJumpstack();
    }

    public static < E > IStackInt < E > getLineStack() {
        return SingletonHolder.getLineStack();
    }

    private static class SingletonHolder < E > {
        public static final IStackInt stack = new ListStack();

        public static final IStackInt scopeStack = new ListStack();

        public static final IStackInt jumpStack = new ListStack();

        public static final IStackInt lineStack = new ListStack();

        public static < E > IStackInt < E > getStack() {
            return stack;
        }

        public static < E > IStackInt < E > getScopestack() {
            return scopeStack;
        }

        public static < E > IStackInt < E > getJumpstack() {
            return jumpStack;
        }

        public static < E > IStackInt < E > getLineStack() {
            return lineStack;
        }
    }

    public static void clearStackScope() {
        SingletonHolder.stack.clearAll();
        SingletonHolder.scopeStack.clearAll();
        SingletonHolder.jumpStack.clearAll();
        SingletonHolder.lineStack.clearAll();
    }
}