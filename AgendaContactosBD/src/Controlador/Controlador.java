
package Controlador;

import Modelo.ControladorBaseDatos;
import Modelo.Persona;
import Vista.VistaAgenda;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;


public class Controlador implements ActionListener {
    
    private VistaAgenda vista;
    private Persona persona;
    private ControladorBaseDatos controlador;

    ArrayList<Persona> todasPersonas;
    int indiceActual = 0;
    int totalResultados = 0;

    public Controlador(VistaAgenda vista, Persona persona, ControladorBaseDatos controlador) {
        this.vista = vista;
        this.persona = persona;
        this.controlador = controlador;
    this.vista.btnAgregar.addActionListener(this);
    this.vista.txtNumeroAnterior.setText((String.valueOf(indiceActual)));
        this.vista.txtNumeroSiguiente.setText(String.valueOf(totalResultados));
    
        this.vista.btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
    
    }
            });
        this.vista.btnPresentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPresentarActionPerformed(evt);
            }
        });
        this.vista.btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarAnterior(evt);
            }
        });
        this.vista.btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarSiguiente(evt);
            }
        });
    }
    
            
    
        
    public void iniciar (){
        vista.setTitle("Agenda Contactos");
        vista.setLocationRelativeTo(null);
    }
    

    public void actionPerformed (ActionEvent e){
        
    controlador.baseDatos();
    persona.setId(vista.txtID.getText());
    persona.setNombre(vista.txtNombre.getText());
    persona.setApellido(vista.txtApellido.getText());
    persona.setEmail(vista.txtEmail.getText());
    persona.setTelefono(vista.txtTelefono.getText());
    
        try {
            controlador.agregarPersona(persona);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    
    }
    
     public void btnBuscarActionPerformed(ActionEvent e) {
        controlador.baseDatos();
        String apellido = vista.txtBuscar.getText();
        todasPersonas = controlador.buscarPersonaApellido(apellido);
        Persona persona = todasPersonas.get(indiceActual);
        setTextos(persona, indiceActual, todasPersonas.size());
    }

    public void btnPresentarActionPerformed(ActionEvent e) {
        controlador.baseDatos();
        todasPersonas = controlador.getPersonas();
        Persona persona = todasPersonas.get(indiceActual);
        setTextos(persona, indiceActual, todasPersonas.size());
    }

    public void btnMostrarAnterior(ActionEvent e) {
        int count = indiceActual - 1;
        if (count >= 0) {
            Persona persona = todasPersonas.get(count);
            setTextos(persona, count, totalResultados);
            indiceActual = indiceActual - 1;
        } else {
            System.out.println("fin de la lista");
        }
    }

    public void btnMostrarSiguiente(ActionEvent e) {
        int count = indiceActual + 1;
        totalResultados = todasPersonas.size();
        if (count < totalResultados) {
            Persona persona = todasPersonas.get(count);
            setTextos(persona, count, totalResultados);
            indiceActual = indiceActual + 1;
        } else {
            System.out.println("fin de la lista");
        }
    }

    public void setTextos(Persona persona, int indiceActual, int totalResultados) {
        vista.txtID.setText(persona.getId());
        vista.txtNombre.setText(persona.getNombre());
        vista.txtApellido.setText(persona.getApellido());
        vista.txtEmail.setText(persona.getEmail());
        vista.txtTelefono.setText(persona.getTelefono());
        vista.txtNumeroAnterior.setText(String.valueOf(indiceActual + 1));
        vista.txtNumeroSiguiente.setText(String.valueOf(totalResultados));
    }
    
    

  
    
    
   
    
}
