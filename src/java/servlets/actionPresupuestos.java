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
import dao.PresupuestoDAO;
import dao.ProblemaDAO;
import java.util.List;
import negocio.Presupuesto;
import negocio.Problema;
import negocio.ProblemaUpdate;
import service.Servicios;
import serviceImpl.ServiciosImpl;

/**
 *
 * @author Sebas
 */
public class actionPresupuestos extends HttpServlet {

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
        ServiciosImpl serv = new ServiciosImpl();

        if ("POST".equals(request.getMethod())) {
            String body = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
            Gson params = new GsonBuilder().setDateFormat("DD/MM/YYYY").create();
            Presupuesto p = params.fromJson(body, Presupuesto.class);

            try {
                PresupuestoDAO.grabarPresupuesto(p);
                responseToConsumer = new Gson().toJson("{\"data\":\"Presupuesto guardado\"}");
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (ConexionException ex) {
                Logger.getLogger(actionPresupuestos.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al guardar presupuesto\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (AccesoException ex) {
                Logger.getLogger(actionPresupuestos.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al guardar presupuesto\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else if ("GET".equals(request.getMethod())) {

            List<Presupuesto> p = null;

            try {
                p = PresupuestoDAO.obtenerPresupuestosPorProblemaId(Long.parseLong(request.getParameter("problemaId")));
                responseToConsumer = new Gson().toJson(p);
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (ConexionException ex) {
                Logger.getLogger(actionPresupuestos.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al recuperar presupuestos\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (AccesoException ex) {
                Logger.getLogger(actionPresupuestos.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al recuperar presupuestos\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else if ("PUT".equals(request.getMethod())) {
            String body = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
            Gson params = new GsonBuilder().setDateFormat("DD/MM/YYYY").create();
            ProblemaUpdate p = params.fromJson(body, ProblemaUpdate.class);

            ServiciosImpl servicio = new ServiciosImpl();
            servicio.aceptarPresupuesto(p.getIdProblema(), p.getIdPresupuesto());
            
            responseToConsumer = new Gson().toJson("Presupuesto aceptado");
            response.setStatus(HttpServletResponse.SC_OK);
            
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

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
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
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT");
    }
}
