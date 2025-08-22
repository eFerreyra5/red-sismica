package com.redsismica.model;
import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_usuario", nullable = false, unique = true)
    private String nombreUsuario;

    @Column(name = "contrasenia", nullable = false)
    private String contraseña;

    @OneToOne
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;

    //constructor vacio para JPA
    public Usuario() {}

    //constructor    
    public Usuario(String nombreUsuario, String contraseña, Empleado empleado) {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.empleado = empleado;
    }

    //getters-setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }  
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Empleado getEmpleado() {   //metodo 6 secuencia
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

}
