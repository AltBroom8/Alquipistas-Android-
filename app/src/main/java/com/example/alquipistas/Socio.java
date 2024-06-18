package com.example.alquipistas;

public class Socio {
    private int id;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String codigoPostal;
    private String email;
    private String fNac;
    private String iban;
    private int socio;
    private Integer idTutor; // Puede ser null
    private String fAlta;
    private String fBaja; // Puede ser null
    private int alta;
    private String username;
    private String poblacion;

    // Constructor vacío
    public Socio() {
    }

    // Constructor con parámetros
    public Socio(int id, String nombre, String apellidos, String direccion, String codigoPostal, String email, String fNac, String iban, int socio, Integer idTutor, String fAlta, String fBaja, int alta, String username, String poblacion) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.email = email;
        this.fNac = fNac;
        this.iban = iban;
        this.socio = socio;
        this.idTutor = idTutor;
        this.fAlta = fAlta;
        this.fBaja = fBaja;
        this.alta = alta;
        this.username = username;
        this.poblacion = poblacion;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFNac() {
        return fNac;
    }

    public void setFNac(String fNac) {
        this.fNac = fNac;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public int getSocio() {
        return socio;
    }

    public void setSocio(int socio) {
        this.socio = socio;
    }

    public Integer getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(Integer idTutor) {
        this.idTutor = idTutor;
    }

    public String getFAlta() {
        return fAlta;
    }

    public void setFAlta(String fAlta) {
        this.fAlta = fAlta;
    }

    public String getFBaja() {
        return fBaja;
    }

    public void setFBaja(String fBaja) {
        this.fBaja = fBaja;
    }

    public int getAlta() {
        return alta;
    }

    public void setAlta(int alta) {
        this.alta = alta;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    @Override
    public String toString() {
        return "Socio{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", direccion='" + direccion + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", email='" + email + '\'' +
                ", fNac='" + fNac + '\'' +
                ", iban='" + iban + '\'' +
                ", socio=" + socio +
                ", idTutor=" + idTutor +
                ", fAlta='" + fAlta + '\'' +
                ", fBaja='" + fBaja + '\'' +
                ", alta=" + alta +
                ", username='" + username + '\'' +
                ", poblacion='" + poblacion + '\'' +
                '}';
    }
}
