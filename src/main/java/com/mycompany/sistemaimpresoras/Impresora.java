package com.mycompany.sistemaimpresoras;

/**
 *
 * @author
 */
public class Impresora {
    private int id;
    private String marca;
    private String tipo;
    private double peso;

    public Impresora(int id, String marca, String tipo, double peso) {
        this.id = id;
        this.marca = marca;
        this.tipo = tipo;
        this.peso = peso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (tipo.equals("Láser") || tipo.equals("Inyección de tinta") || 
            tipo.equals("Matriz de puntos") || tipo.equals("3D")) {
            this.tipo = tipo;
        } else {
            throw new IllegalArgumentException("Tipo de impresora no válido");
        }
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Impresora [ID=" + id + ", Marca=" + marca + 
               ", Tipo=" + tipo + ", Peso=" + peso + " kg]";
    }
}
