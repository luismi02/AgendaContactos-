
package agendacontactosbd;

import Controlador.Controlador;
import Modelo.ControladorBaseDatos;
import Modelo.Persona;
import Vista.VistaAgenda;


public class AgendaContactosBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ControladorBaseDatos controladorbase = new ControladorBaseDatos();
        Persona persona = new Persona();
        VistaAgenda vista= new VistaAgenda();
        Controlador controlar =new Controlador(vista, persona, controladorbase);
        controlar.iniciar();
        vista.setVisible(true);
        
        
    }
    
}
