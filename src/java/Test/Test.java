package test;

import excepciones.AccesoException;
import excepciones.ConexionException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import negocio.Cliente;

public class Test {

    public static void main(String[] args) throws ConexionException, AccesoException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dateInString = "30-07-1986";

        try {

            Date fecha = formatter.parse(dateInString);
//            Cliente cliente = new Cliente("DNI", "32523092", "sebastian", "Roidzaid", fecha, 1562701114, "sebastian.roidzaid@gmail.com", "pepe 345", "Moron");
//            cliente.guardar();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
