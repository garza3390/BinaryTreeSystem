
package com.mycompany.sistemaimpresoras;

/**
 *
 * @author
 */
public class ArbolBinario {
    private NodoImpresora raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    public void insertar(Impresora impresora) {
        raiz = insertarRecursivo(raiz, impresora);
    }

    private NodoImpresora insertarRecursivo(NodoImpresora actual, Impresora impresora) {
        if (actual == null) {
            return new NodoImpresora(impresora);
        }

        if (impresora.getId() < actual.getImpresora().getId()) {
            actual.setIzquierda(insertarRecursivo(actual.getIzquierda(), impresora));
        } else if (impresora.getId() > actual.getImpresora().getId()) {
            actual.setDerecha(insertarRecursivo(actual.getDerecha(), impresora));
        } else {
            // Id ya existe
            throw new IllegalArgumentException("Id ya existe en el árbol");
        }

        return actual;
    }

    public NodoImpresora buscar(int id) {
        return buscarRecursivo(raiz, id);
    }

    private NodoImpresora buscarRecursivo(NodoImpresora actual, int id) {
        if (actual == null) {
            return null;
        }
        if (id == actual.getImpresora().getId()) {
            return actual;
        }
        return id < actual.getImpresora().getId() 
            ? buscarRecursivo(actual.getIzquierda(), id) 
            : buscarRecursivo(actual.getDerecha(), id);
    }

    public void eliminar(int id) {
        raiz = eliminarRecursivo(raiz, id);
    }

    private NodoImpresora eliminarRecursivo(NodoImpresora actual, int id) {
        if (actual == null) {
            return null;
        }

        if (id == actual.getImpresora().getId()) {
            if (actual.getIzquierda() == null && actual.getDerecha() == null) {
                return null; 
            }
            if (actual.getDerecha() == null) {
                return actual.getIzquierda(); 
            }
            if (actual.getIzquierda() == null) {
                return actual.getDerecha(); 
            }
            // Nodo con dos hijos
            
            throw new IllegalArgumentException("No se puede eliminar un nodo con dos hijos.");
        }
        if (id < actual.getImpresora().getId()) {
            actual.setIzquierda(eliminarRecursivo(actual.getIzquierda(), id));
        } else {
            actual.setDerecha(eliminarRecursivo(actual.getDerecha(), id));
        }
        return actual;
    }

       public String recorridoPreOrden() {
        StringBuilder resultado = new StringBuilder();
        recorridoPreOrden(raiz, resultado);
        return resultado.toString();
    }

    private void recorridoPreOrden(NodoImpresora nodo, StringBuilder resultado) {
        if (nodo != null) {
            resultado.append(nodo.getImpresora().getId()).append(" - ");
            recorridoPreOrden(nodo.getIzquierda(), resultado);
            recorridoPreOrden(nodo.getDerecha(), resultado);
        }
    }

    public String recorridoInOrden() {
        StringBuilder resultado = new StringBuilder();
        recorridoInOrden(raiz, resultado);
        return resultado.toString();
    }

    private void recorridoInOrden(NodoImpresora nodo, StringBuilder resultado) {
        if (nodo != null) {
            recorridoInOrden(nodo.getIzquierda(), resultado);
            resultado.append(nodo.getImpresora().getId()).append(" - ");
            recorridoInOrden(nodo.getDerecha(), resultado);
        }
    }

    public String recorridoPostOrden() {
        StringBuilder resultado = new StringBuilder();
        recorridoPostOrden(raiz, resultado);
        return resultado.toString();
    }

    private void recorridoPostOrden(NodoImpresora nodo, StringBuilder resultado) {
        if (nodo != null) {
            recorridoPostOrden(nodo.getIzquierda(), resultado);
            recorridoPostOrden(nodo.getDerecha(), resultado);
            resultado.append(nodo.getImpresora().getId()).append(" - ");
        }
    }

    public NodoImpresora getRaiz() {
        return raiz;
    }
}

