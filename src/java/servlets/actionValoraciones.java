/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import excepciones.AccesoException;
import excepciones.ConexionException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.ValoracionDAO;
import java.util.List;
import negocio.Valoracion;

/**
 *
 * @author Sebas
 */
public class actionValoraciones extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String responseToConsumer = "";

        if ("POST".equals(request.getMethod())) {
            String body = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
            Gson params = new GsonBuilder().setDateFormat("DD/MM/YYYY").create();
            Valoracion val = params.fromJson(body, Valoracion.class);

            try {
                ValoracionDAO.grabarValoracion(val);
                responseToConsumer = new Gson().toJson("{\"data\":\"Se ha guardado la valoracion\"}");
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (ConexionException ex) {
                Logger.getLogger(actionClientes.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al guardar valoracion\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (AccesoException ex) {
                Logger.getLogger(actionClientes.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al guardar valoracion\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            
        
        } else if ("GET".equals(request.getMethod()) && request.getParameter("idValorado") != null
                && request.getParameter("userType") != null) {
            List<Valoracion> listaValoraciones;
            try {
                listaValoraciones = ValoracionDAO.obtenerValoraciones(Long.valueOf(request.getParameter("idValorado")), request.getParameter("userType"));
                responseToConsumer = new Gson().toJson(listaValoraciones);
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (ConexionException ex) {
                Logger.getLogger(actionClientes.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al recuperar las valoraciones\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (AccesoException ex) {
                Logger.getLogger(actionClientes.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al recuperar las valoraciones\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            
            
        } else if ("GET".equals(request.getMethod())) {
 
            Valoracion val;
            try {
                val = ValoracionDAO.obtenerValoracionPorId(Long.valueOf(request.getParameter("idValoracion")));
                responseToConsumer = new Gson().toJson(val);
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (ConexionException ex) {
                Logger.getLogger(actionClientes.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al recuperar la valoracion\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (AccesoException ex) {
                Logger.getLogger(actionClientes.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al recuperar la valoracion\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

        } else {
            responseToConsumer = new Gson().toJson("{\"error\":\"Metodo no correcto\"}");
            response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
        }

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(responseToConsumer);
        out.flush();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setAccessControlHeaders(response);
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setAccessControlHeaders(response);
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    //for Preflight
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        setAccessControlHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, X-Requested-With");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST");
    }
}
