package com.flagstone.transform.as3.abcfile.stack;

public abstract interface IStackInt < E > {
    public abstract E push(E paramE);

    public abstract E peek()
    throws EmptyStackException;

    public abstract E pop()
    throws EmptyStackException;

    public abstract void dup()
    throws EmptyStackException;

    public abstract void swap()
    throws EmptyStackException;

    public abstract boolean empty();

    public abstract void clearAll();

    public abstract int size();

    public abstract E get(int paramInt);

    public abstract E front();

    public abstract void set(int paramInt, E paramE);
}