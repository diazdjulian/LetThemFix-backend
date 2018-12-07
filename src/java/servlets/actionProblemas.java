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
import com.google.gson.reflect.TypeToken;
import dao.PresupuestoDAO;
import dao.ProblemaDAO;
import dao.TrabajoDAO;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import negocio.Problema;
import negocio.ProblemaRequest;
import negocio.Trabajo;

/**
 *
 * @author Sebas
 */
public class actionProblemas extends HttpServlet {

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
            ProblemaRequest p = params.fromJson(body, ProblemaRequest.class);
           
            try {
                ProblemaDAO.grabarProblema(p);
                responseToConsumer = new Gson().toJson("{\"data\":\"Problema guardado\"}");
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (ConexionException ex) {
                Logger.getLogger(actionProblemas.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al guardar usuario\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (AccesoException ex) {
                Logger.getLogger(actionProblemas.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al guardar usuario\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else if ("GET".equals(request.getMethod())) {
            List<Problema> p = null;

            try {
                if (request.getParameter("problemaId") != null) {
                    Problema aux;
                    try {
                        aux = ProblemaDAO.obtenerProblemaPorId(Integer.parseInt(request.getParameter("problemaId")));
                        p = new LinkedList<Problema>();
                        p.add(aux);
                    } catch (ConexionException ex) {
                        Logger.getLogger(actionProblemas.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (AccesoException ex) {
                        Logger.getLogger(actionProblemas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (request.getParameter("clienteId") != null) {
                    try {
                        p = ProblemaDAO.obtenerProblemasPorCliente(Integer.parseInt(request.getParameter("clienteId")));
                    } catch (ConexionException ex) {
                        Logger.getLogger(actionProblemas.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (AccesoException ex) {
                        Logger.getLogger(actionProblemas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (request.getParameter("idProfesional") != null) {
                    try {
                        p = ProblemaDAO.obtenerProblemasPorProfesional(Integer.parseInt(request.getParameter("idProfesional")));
                    } catch (ConexionException ex) {
                        Logger.getLogger(actionProblemas.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (AccesoException ex) {
                        Logger.getLogger(actionProblemas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (request.getParameter("idProfesionalPosibles") != null) {
                    try {
                        p = ProblemaDAO.obtenerPosiblesProblemasPorProfesional(Integer.parseInt(request.getParameter("idProfesionalPosibles")));
                    } catch (ConexionException ex) {
                        Logger.getLogger(actionProblemas.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (AccesoException ex) {
                        Logger.getLogger(actionProblemas.class.getName()).log(Level.SEVERE, null, ex);
                    }
//                } else if (request.getParameter("idProfesionalRubros") != null) {
//                    try {
//                        p = ProblemaDAO.obtenerProblemasPorRubros(Integer.parseInt(request.getParameter("clienteId")));
//                    } catch (ConexionException ex) {
//                        Logger.getLogger(actionProblemas.class.getName()).log(Level.SEVERE, null, ex);
//                    } catch (AccesoException ex) {
//                        Logger.getLogger(actionProblemas.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                } else {
                    p = ProblemaDAO.obtenerProblemas();
                }
                responseToConsumer = new Gson().toJson(p);
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (ConexionException ex) {
                Logger.getLogger(actionProblemas.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al recuperar usuario\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (AccesoException ex) {
                Logger.getLogger(actionProblemas.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al recuperar usuario\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else if ("PUT".equals(request.getMethod())) {
            String body = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
            Gson params = new GsonBuilder().setDateFormat("DD/MM/YYYY").create();
            Trabajo t = params.fromJson(body, Trabajo.class);
           
            try {
                TrabajoDAO.actualizarTrabajo(t);
                responseToConsumer = new Gson().toJson("Problema actualizado");
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (ConexionException ex) {
                Logger.getLogger(actionProblemas.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al guardar usuario\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (AccesoException ex) {
                Logger.getLogger(actionProblemas.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al guardar usuario\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else if ("DELETE".equals(request.getMethod())) {
            String body = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
            Type type = new TypeToken<Map<String, String>>(){}.getType();
            Map<String, String> myMap;
            Gson gson = new Gson();
            myMap = gson.fromJson(body, type);
           
            try {
                boolean habilitadoAEliminar = ProblemaDAO.eliminarProblema(Integer.valueOf(myMap.get("idProblema")));
                if (habilitadoAEliminar) {
                    PresupuestoDAO.eliminarSegunProblema(Integer.valueOf(myMap.get("idProblema")));
                }

                responseToConsumer = new Gson().toJson(habilitadoAEliminar);
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (ConexionException ex) {
                Logger.getLogger(actionProblemas.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al guardar usuario\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (AccesoException ex) {
                Logger.getLogger(actionProblemas.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al guardar usuario\"}");
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
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setAccessControlHeaders(response);
        processRequest(request, response);
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
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
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
    }
}
