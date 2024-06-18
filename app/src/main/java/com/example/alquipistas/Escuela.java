package com.example.alquipistas;

import java.util.Date;

public class Escuela {
    private int id;
    private int escuela_id;
    private int usuario_id;
    private int eliminado;
    private String nombre;
    private Date fecha_inicio;
    private Date fecha_fin;
    private Date inicio_inscripcion;
    private Date fin_inscripcion;
    private int edad_min;
    private int edad_max;
    private int categoria;
    private String nombre_categoria;



    public Escuela() {
    }

    // Constructor con todos los campos
    public Escuela(int id, int escuela_id, int usuario_id, int eliminado, String nombre,
                   Date fecha_inicio, Date fecha_fin, Date inicio_inscripcion,
                   Date fin_inscripcion, int edad_min, int edad_max, int categoria,
                   String nombre_categoria) {
        this.id = id;
        this.escuela_id = escuela_id;
        this.usuario_id = usuario_id;
        this.eliminado = eliminado;
        this.nombre = nombre;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.inicio_inscripcion = inicio_inscripcion;
        this.fin_inscripcion = fin_inscripcion;
        this.edad_min = edad_min;
        this.edad_max = edad_max;
        this.categoria = categoria;
        this.nombre_categoria = nombre_categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEscuela_id() {
        return escuela_id;
    }

    public void setEscuela_id(int escuela_id) {
        this.escuela_id = escuela_id;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getEliminado() {
        return eliminado;
    }

    public void setEliminado(int eliminado) {
        this.eliminado = eliminado;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Date getInicio_inscripcion() {
        return inicio_inscripcion;
    }

    public void setInicio_inscripcion(Date inicio_inscripcion) {
        this.inicio_inscripcion = inicio_inscripcion;
    }

    public Date getFin_inscripcion() {
        return fin_inscripcion;
    }

    public void setFin_inscripcion(Date fin_inscripcion) {
        this.fin_inscripcion = fin_inscripcion;
    }

    public int getEdad_min() {
        return edad_min;
    }

    public void setEdad_min(int edad_min) {
        this.edad_min = edad_min;
    }

    public int getEdad_max() {
        return edad_max;
    }

    public void setEdad_max(int edad_max) {
        this.edad_max = edad_max;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }
}
