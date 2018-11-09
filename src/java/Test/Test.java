package test;

import dao.ClienteDAO;
import dao.ProblemaDAO;
import dao.RubroDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import negocio.Cliente;
import negocio.Presupuesto;
import negocio.Problema;
import negocio.Rubro;
import negocio.Trabajo;

public class Test {

    public static void main(String[] args) throws ConexionException, AccesoException, ParseException {

        
        //CREAR CLIENTE
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//        String dateInString = "30-07-1986";
//
//        Date fecha = formatter.parse(dateInString);
//        Cliente cliente = new Cliente("sebastian", "Roidzaid", "seba", "seba", "32523092", fecha, 1562701114, "sebastian.roidzaid@gmail.com", "pepe", 345, "Moron", "Buenos aires");
//        ClienteDAO.grabarCliente(cliente);
        

        //CREAR RUBRO
//        Rubro rubro = new Rubro("Jardineria");
//        RubroDAO.grabarRubro(rubro);

       
        //CREAR PROBLEMA
//       Rubro rubro2 = RubroDAO.obtenerRubroPorId(1);
//       Cliente cliente2 = ClienteDAO.obtenerClientePorId(1L);
//       Problema problema = new Problema(cliente2, "CORTAR EL PASTO", "Necesito cortar el pasto de todo mi fondo", 200, 5000, "Oeste", rubro2, null, null);
//       ProblemaDAO.grabarProblema(problema);

        //CREAR PRESUPUESTO
//        Presupuesto presupuesto = new Presupuesto()
        

    }

}
