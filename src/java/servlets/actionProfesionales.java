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
import dao.ClienteDAO;
import dao.ProfesionalDAO;
import dao.TrabajoDAO;
import java.util.List;
import negocio.Profesional;
import negocio.Trabajo;

/**
 *
 * @author Sebas
 */
public class actionProfesionales extends HttpServlet {

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
            Profesional p = params.fromJson(body, Profesional.class);

            try {
                ProfesionalDAO.grabarProfesional(p);
                responseToConsumer = new Gson().toJson("Profesional guardado");
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (ConexionException ex) {
                Logger.getLogger(actionProfesionales.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al guardar profesional\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (AccesoException ex) {
                Logger.getLogger(actionProfesionales.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al guardar profesional\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else if ("GET".equals(request.getMethod()) && (request.getParameter("checkUsuario") != null
                || request.getParameter("checkEmail") != null || request.getParameter("checkNroFiscal") != null)) {
            try {
                boolean habilitado = true;
                
                if (request.getParameter("checkUsuario") != null) {
                    habilitado = ProfesionalDAO.datoExiste(request.getParameter("checkUsuario"), null, null);
                    if (habilitado) {
                        habilitado = ClienteDAO.datoExiste(request.getParameter("checkUsuario"), null, null); 
                    }
                } else if (request.getParameter("checkEmail") != null) {
                    habilitado = ProfesionalDAO.datoExiste(null, request.getParameter("checkEmail"), null);
                    if (habilitado) {
                        habilitado = ClienteDAO.datoExiste(null, request.getParameter("checkEmail"), null); 
                    }
                } else if (request.getParameter("checkNroFiscal") != null) {
                    habilitado = ProfesionalDAO.datoExiste(null, null, request.getParameter("checkNroFiscal"));
                    if (habilitado) {
                        habilitado = ClienteDAO.datoExiste(null, null, request.getParameter("checkNroFiscal")); 
                    }
                }

                responseToConsumer = new Gson().toJson(habilitado);
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (ConexionException ex) {
                Logger.getLogger(actionClientes.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al guardar\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (AccesoException ex) {
                Logger.getLogger(actionClientes.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al guardar\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else if ("GET".equals(request.getMethod()) && request.getParameter("idProfesionalCierre") != null) {
            try {
                boolean habilitadoACerrarCuenta = true;
                int trabajosActivos = ProfesionalDAO.habilitadoACerrarCuenta(Long.valueOf(request.getParameter("idProfesionalCierre")));
                
                if (trabajosActivos > 1) {
                    habilitadoACerrarCuenta = false;
                }
                
                responseToConsumer = new Gson().toJson(habilitadoACerrarCuenta);
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (ConexionException ex) {
                Logger.getLogger(actionClientes.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al guardar\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (AccesoException ex) {
                Logger.getLogger(actionClientes.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al guardar\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else if ("GET".equals(request.getMethod()) && request.getParameter("idProfesionalProblemasActivos") != null) {
            try {
                int trabajosActivos = 0;
                List<Trabajo> profeionalTrabajos = TrabajoDAO.obtenerTrabajosPorProfesional(Integer.valueOf(request.getParameter("idProfesionalProblemasActivos")));
                
                if (profeionalTrabajos != null) {
                    for(Trabajo trabajo : profeionalTrabajos) {
                        if (!"D".equals(trabajo.getEstado())) {
                            trabajosActivos = trabajosActivos + 1;
                        }
                    }
                }
                
                responseToConsumer = new Gson().toJson(trabajosActivos);
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (ConexionException ex) {
                Logger.getLogger(actionClientes.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al guardar\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (AccesoException ex) {
                Logger.getLogger(actionClientes.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al guardar\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else if ("GET".equals(request.getMethod()) && request.getParameter("idProfesionalEliminar") != null) {
            try {
                ProfesionalDAO.bajaProfesional(Long.valueOf(request.getParameter("idProfesionalEliminar")));
                responseToConsumer = new Gson().toJson("{\"data\":\"Profesional dado de baja\"}");
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (ConexionException ex) {
                Logger.getLogger(actionProfesionales.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al guardar profesional\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (AccesoException ex) {
                Logger.getLogger(actionProfesionales.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al guardar profesional\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else if ("PUT".equals(request.getMethod())) {
            String body = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
            Gson params = new GsonBuilder().setDateFormat("DD/MM/YYYY").create();
            Profesional p = params.fromJson(body, Profesional.class);

            try {
                ProfesionalDAO.actualizarProfesional(p, p.getIdProfesional());
                responseToConsumer = new Gson().toJson("{\"data\":\"Profesional actualizado\"}");
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (ConexionException ex) {
                Logger.getLogger(actionProfesionales.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al guardar profesional\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (AccesoException ex) {
                Logger.getLogger(actionProfesionales.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al guardar profesional\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else if ("GET".equals(request.getMethod())) {
            String nroFiscal = request.getParameter("nroFiscal");
            Profesional p;
            try {
                p = ProfesionalDAO.obtenerProfesionalPorNroFiscal(nroFiscal);
                responseToConsumer = new Gson().toJson(p);
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (ConexionException ex) {
                Logger.getLogger(actionProfesionales.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al recuperar profesional\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (AccesoException ex) {
                Logger.getLogger(actionProfesionales.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al recuperar profesional\"}");
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setAccessControlHeaders(response);
        processRequest(request, response);
    }

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
    public String getServletInfo() {
        return "Short description";
    }

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
