package com.flagstone.transform.as3.abcfile.stack;

import java.util.ArrayList;
import java.util.List;

public class ListStack < E >
    implements IStackInt < E > {
        private List < E > stackList;

        public ListStack() {
            this.stackList = new ArrayList();
        }

        public void dup() throws EmptyStackException {
            if (empty()) {
                throw new EmptyStackException();
            }
            this.stackList.add(this.stackList.get(this.stackList.size() - 1));
        }

        public boolean empty() {
            if (this.stackList.size() == 0) {
                return true;
            }
            return false;
        }

        public E peek() throws EmptyStackException {
            if (empty()) {
                throw new EmptyStackException();
            }
            return (E) this.stackList.get(this.stackList.size() - 1);
        }

        public E pop() throws EmptyStackException {
            if (empty()) {
                throw new EmptyStackException();
            }
            return (E) this.stackList.remove(this.stackList.size() - 1);
        }

        public E push(E obj) {
            this.stackList.add(obj);
            return obj;
        }

        public void swap() throws EmptyStackException {
            E first = pop();
            E second = pop();

            push(first);
            push(second);
        }

        public void clearAll() {
            this.stackList.clear();
        }

        public int size() {
            return this.stackList.size();
        }

        public E get(int posId) {
            return (E) this.stackList.get(posId);
        }

        public E front() {
            if (!this.stackList.isEmpty()) {
                return (E) get(0);
            }
            return null;
        }

        public void set(int posId, E value) {
            this.stackList.set(posId, value);
        }
    }