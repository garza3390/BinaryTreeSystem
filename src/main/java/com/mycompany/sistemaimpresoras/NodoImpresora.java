/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaimpresoras;

/**
 *
 * @author
 */
public class NodoImpresora {
    private final Impresora impresora;
    private NodoImpresora izquierda;
    private NodoImpresora derecha;

    public NodoImpresora(Impresora impresora) {
        this.impresora = impresora;
        this.izquierda = null;
        this.derecha = null;
    }

    public Impresora getImpresora() {
        return impresora;
    }

    public NodoImpresora getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(NodoImpresora izquierda) {
        this.izquierda = izquierda;
    }

    public NodoImpresora getDerecha() {
        return derecha;
    }

    public void setDerecha(NodoImpresora derecha) {
        this.derecha = derecha;
    }
}

