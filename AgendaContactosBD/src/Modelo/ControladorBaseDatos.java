
package Modelo;

import java.sql.*;
import java.util.ArrayList;

public class ControladorBaseDatos {

    String URL = "";
    String Usuario;
    String Clave;

    Connection conexion;
 PreparedStatement seleccionarPersonas;
    PreparedStatement seleccionarPersonasPorApellido;
    
 
   
    PreparedStatement insertarPersona;

    public void baseDatos() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            System.out.println("Driver Java DB cargado");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: No se encuentra en el Driver");
        }
        conexion = null;
        String urlbd = "jdbc:derby://localhost:1527//AgendaContactos";
        try {
            conexion = DriverManager.getConnection(urlbd, "administrador", "administradoragenda");
            System.out.println("Conexion establecida a la base de datos");
        } catch (SQLException ex) {
            System.out.println("No se pudo establecer conexion a la Base de datos" + ex);
        }

    }

    public void agregarPersona(Persona persona) throws SQLException {
        

        try {
            Statement stm = conexion.createStatement();
            insertarPersona = conexion.prepareStatement("INSERT INTO CONTACTOS (id,nombre,apellido,email,telefono) VALUES (?,?,?,?,?)");
            insertarPersona.setString(1, persona.getId());
            insertarPersona.setString(2, persona.getNombre());
            insertarPersona.setString(3, persona.getApellido());
            insertarPersona.setString(4, persona.getEmail());
            insertarPersona.setString(5, persona.getTelefono());

            insertarPersona.executeUpdate();

            System.out.println("Datos Ingresados correctamente");
        } catch (SQLException ex) {
            System.out.println("Error al ingresar datos" + ex);

        }

    }

  public ArrayList buscarPersonaApellido(String apellido){

   ArrayList<Persona> encontrado = new ArrayList<Persona>();
        try {
            String query = "Select * from CONTACTOS where LOWER(apellido) LIKE LOWER('" + apellido + "%')";
            seleccionarPersonasPorApellido = conexion.prepareStatement(query);
            ResultSet resultado = seleccionarPersonasPorApellido.executeQuery();
            if (resultado.next() == true) {
                Persona personaEncontrada = new Persona();
                String id = resultado.getString("id");
                String nb = resultado.getString("nombre");
                String ap = resultado.getString("apellido");
                String email = resultado.getString("email");
                String tlf = resultado.getString("telefono");
                personaEncontrada.setId(id);
                personaEncontrada.setNombre(nb);
                personaEncontrada.setApellido(ap);
                personaEncontrada.setEmail(email);
                personaEncontrada.setTelefono(tlf);
                encontrado.add(personaEncontrada);

            } else {
                System.out.println("No existe");
            }

        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la consulta" + ex);
        }
        return encontrado;
  } 
  
    public ArrayList getPersonas() {
        ArrayList<Persona> personas = new ArrayList<Persona>();
        try {
            String query = "Select id , nombre ,apellido, email ,telefono from contactos";
            seleccionarPersonas = conexion.prepareStatement(query);
            ResultSet resultado = seleccionarPersonas.executeQuery();
            while (resultado.next()) {
                Persona personaEncontrada = new Persona();
                String id = resultado.getString("id");
                String nb = resultado.getString("nombre");
                String ap = resultado.getString("apellido");
                String email = resultado.getString("email");
                String tlf = resultado.getString("telefono");

                personaEncontrada.setId(id);
                personaEncontrada.setNombre(nb);
                personaEncontrada.setApellido(ap);
                personaEncontrada.setEmail(email);
                personaEncontrada.setTelefono(tlf);
                personas.add(personaEncontrada);
            }

            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la consulta" + ex);
        }
        return personas;
    }
    
    
}
