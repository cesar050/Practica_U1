package com.practica.controller.tda.stack;

import com.practica.controller.excepcion.ListEmptyException;
import com.practica.controller.excepcion.OverFlowException;
import com.practica.controller.tda.list.LinkedList;

public class StackOperation <E> extends LinkedList<E>{
    private Integer top;

    public StackOperation(Integer top) {
        this.top = top;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Boolean verify() {
        return getSize().intValue() <= top.intValue();
    }
    public void push(E data) throws OverFlowException, IndexOutOfBoundsException, ListEmptyException {
        if (verify()) {
            add(data, 0);
        } else {
            throw new OverFlowException("Pila llena");
        }
    }
    public E pop() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Pila vacia");
        } else {
            return deleteHeader();
        }
    }
}
