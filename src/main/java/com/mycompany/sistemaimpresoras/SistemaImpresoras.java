
package com.mycompany.sistemaimpresoras;

/**
 *
 * @author 
 */

import javax.swing.*;
import java.awt.*;

public class SistemaImpresoras extends JFrame {
    private final ArbolBinario arbol;
    private JTextField idField, marcaField, pesoField;
    private JComboBox<String> tipoCombo;
    private JTextArea resultadoArea;

    public SistemaImpresoras() {
        
        arbol = new ArbolBinario();

        
        setTitle("Árbol Binario para gestion de Impresoras");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // inicializar los componentes
        initComponents();
    }

    private void initComponents() {
        // Entrada
        JPanel panelEntrada = new JPanel(new GridLayout(4, 2));
        panelEntrada.add(new JLabel("ID:"));
        idField = new JTextField();
        panelEntrada.add(idField);
        panelEntrada.add(new JLabel("Marca:"));
        marcaField = new JTextField();
        panelEntrada.add(marcaField);
        panelEntrada.add(new JLabel("Tipo de Impresora:"));
        tipoCombo = new JComboBox<>(new String[] { "Láser", "Inyección de tinta", "Matriz de puntos", "3D" });
        panelEntrada.add(tipoCombo);
        panelEntrada.add(new JLabel("Peso:"));
        pesoField = new JTextField();
        panelEntrada.add(pesoField);

        // Botones
        JPanel panelBotones = new JPanel();
        JButton insertarButton = new JButton("Insertar");
        JButton eliminarButton = new JButton("Eliminar");
        JButton buscarButton = new JButton("Buscar");
        JButton preOrdenButton = new JButton("Recorrido Pre-Orden");
        JButton inOrdenButton = new JButton("Recorrido In-Orden");
        JButton postOrdenButton = new JButton("Recorrido Post-Orden");
        JButton graficarButton = new JButton("Graficar Árbol");

        panelBotones.add(insertarButton);
        panelBotones.add(eliminarButton);
        panelBotones.add(buscarButton);
        panelBotones.add(preOrdenButton);
        panelBotones.add(inOrdenButton);
        panelBotones.add(postOrdenButton);
        panelBotones.add(graficarButton);

        // Resultados
        resultadoArea = new JTextArea(10, 50);
        resultadoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);

        // Agregar los paneles al frame principal
        add(panelEntrada, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Listeners para los botones (a implementar más adelante)
        insertarButton.addActionListener(e -> onInsertar());
        eliminarButton.addActionListener(e -> onEliminar());
        buscarButton.addActionListener(e -> onBuscar());
        preOrdenButton.addActionListener(e -> onRecorridoPreOrden());
        inOrdenButton.addActionListener(e -> onRecorridoInOrden());
        postOrdenButton.addActionListener(e -> onRecorridoPostOrden());
        graficarButton.addActionListener(e -> onGraficarArbol());
    }

    private void onInsertar() {
        try {
            
            int id = Integer.parseInt(idField.getText().trim());
            String marca = marcaField.getText().trim();
            String tipo = (String) tipoCombo.getSelectedItem();
            double peso = Double.parseDouble(pesoField.getText().trim());

            
            if (marca.isEmpty() || tipo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            
            Impresora nuevaImpresora = new Impresora(id, marca, tipo, peso);

            
            if (arbol.buscar(id) == null) {  // Verificar si el Id ya existe
                arbol.insertar(nuevaImpresora);
                resultadoArea.append("Impresora con ID " + id + " insertada correctamente.\n");

                // Limpiar los campos de entrada después de la inserción
                idField.setText("");
                marcaField.setText("");
                pesoField.setText("");
                tipoCombo.setSelectedIndex(0);
            } else {
               
                JOptionPane.showMessageDialog(this, "El Id " + id + " ya existe en el árbol.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            
            JOptionPane.showMessageDialog(this, "Por favor ingrese un valor numérico válido para el ID y el peso.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (HeadlessException e) {
            
            JOptionPane.showMessageDialog(this, "Ocurrió un error al intentar insertar la impresora.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


private void onEliminar() {
    try {
        
        int id = Integer.parseInt(idField.getText().trim());

        
        NodoImpresora nodoAEliminar = arbol.buscar(id);

        if (nodoAEliminar == null) {
            
            JOptionPane.showMessageDialog(this, "El Id " + id + " no se encontró en el árbol.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
          
            if (nodoAEliminar.getIzquierda() != null && nodoAEliminar.getDerecha() != null) {
                
                resultadoArea.append("No se puede eliminar el nodo con Id " + id + " porque tiene dos hijos.\n");
            } else {
                
                arbol.eliminar(id);
                resultadoArea.append("Nodo con Id " + id + " eliminado correctamente.\n");

                // Limpiar el campo de entrada después de la eliminación
                idField.setText("");
            }
        }
    } catch (NumberFormatException e) {
       
        JOptionPane.showMessageDialog(this, "Por favor ingrese un valor numérico válido para el ID.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (HeadlessException e) {
        
        JOptionPane.showMessageDialog(this, "Ocurrió un error al intentar eliminar el nodo.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}


private void onBuscar() {
    try {
    
        int id = Integer.parseInt(idField.getText().trim());

        // Buscar el nodo en el árbol
        NodoImpresora nodoBuscado = arbol.buscar(id);

        if (nodoBuscado == null) {
            
            resultadoArea.append("El nodo con Id " + id + " no se encontró en el árbol.\n");
        } else {
           
            Impresora impresora = nodoBuscado.getImpresora();
            resultadoArea.append("Información de la impresora con Id " + id + ":\n");
            resultadoArea.append("Marca: " + impresora.getMarca() + "\n");
            resultadoArea.append("Tipo: " + impresora.getTipo() + "\n");
            resultadoArea.append("Peso: " + impresora.getPeso() + " kg\n");
            resultadoArea.append("-----------------------------\n");

            // Limpiar el campo de entrada después de la búsqueda
            idField.setText("");
        }
    } catch (NumberFormatException e) {
       
        JOptionPane.showMessageDialog(this, "Por favor ingrese un valor numérico válido para el ID.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
       
        JOptionPane.showMessageDialog(this, "Ocurrió un error al intentar buscar la impresora.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}


private void onRecorridoPreOrden() {
    if (arbol.getRaiz() == null) {
       
        resultadoArea.setText("El árbol está vacío.\n");
    } else {
        
        resultadoArea.setText("Recorrido Pre-Orden: \n");

        
        String recorrido = arbol.recorridoPreOrden();
        
        if (!recorrido.isEmpty()) {
            resultadoArea.setText(recorrido.trim() + "\n");
        } else {
            resultadoArea.append("\n");
        }
    }
}


private void onRecorridoInOrden() {
    if (arbol.getRaiz() == null) {
      
        resultadoArea.setText("El árbol está vacío.\n");
    } else {

        resultadoArea.setText("Recorrido In-Orden: \n");

        // Realizar el recorrido en orden y mostrar los resultados
        String recorrido = arbol.recorridoInOrden();
       
        if (!recorrido.isEmpty()) {
            resultadoArea.setText(recorrido.trim() + "\n");
        } else {
            resultadoArea.append("\n");
        }
    }
}


private void onRecorridoPostOrden() {
    if (arbol.getRaiz() == null) {
        // Si el árbol está vacío, mostrar un mensaje en el área de resultados
        resultadoArea.setText("El árbol está vacío.\n");
    } else {
       
        resultadoArea.setText("Recorrido Post-Orden: \n");

     
        String recorrido = arbol.recorridoPostOrden();
       
        if (!recorrido.isEmpty()) {
            resultadoArea.setText(recorrido.trim() + "\n");
        } else {
            resultadoArea.append("\n");
        }
    }
}


private void onGraficarArbol() {
   
    TreePanel treePanel = new TreePanel(arbol);
    
   
    JFrame graficarFrame = new JFrame("Gráfico del Árbol de Impresoras");
    graficarFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    graficarFrame.getContentPane().add(new JScrollPane(treePanel));
    graficarFrame.pack();
    graficarFrame.setVisible(true);
}


    public static void main(String[] args) {
        // Crear y mostrar el GUI
        SistemaImpresoras frame = new SistemaImpresoras();
        frame.setVisible(true);
    }
}
