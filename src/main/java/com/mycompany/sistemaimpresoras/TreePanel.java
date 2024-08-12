/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaimpresoras;

/**
 *
 * @author
 */
import javax.swing.*;
import java.awt.*;

class TreePanel extends JPanel {
    private final ArbolBinario arbol;

    public TreePanel(ArbolBinario arbol) {
        this.arbol = arbol;
        setPreferredSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (arbol.getRaiz() != null) {
            drawTree(g, arbol.getRaiz(), getWidth() / 2, 30, getWidth() / 4);
        }
    }

    private void drawTree(Graphics g, NodoImpresora nodo, int x, int y, int xOffset) {
        if (nodo == null) return;

        // Dibujar el nodo
        g.setColor(Color.BLACK);
        g.fillOval(x - 15, y - 15, 40, 40);
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(nodo.getImpresora().getId()), x - 10, y + 5);

        // Dibujar las conexiones
        if (nodo.getIzquierda() != null) {
            g.drawLine(x, y, x - xOffset, y + 50);
            drawTree(g, nodo.getIzquierda(), x - xOffset, y + 50, xOffset / 2);
        }
        if (nodo.getDerecha() != null) {
            g.drawLine(x, y, x + xOffset, y + 50);
            drawTree(g, nodo.getDerecha(), x + xOffset, y + 50, xOffset / 2);
        }
    }
}
