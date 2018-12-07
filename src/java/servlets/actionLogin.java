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
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.Cliente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import dao.ClienteDAO;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import dao.ProfesionalDAO;
/**
 *
 * @author Sebas
 */
public class actionLogin extends HttpServlet {

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
            Type type = new TypeToken<Map<String, String>>(){}.getType();
            Map<String, String> myMap;
            Gson gson = new Gson();
            myMap = gson.fromJson(body, type);
            Object user = null;

            try {
                user = ClienteDAO.login(myMap.get("email"), myMap.get("password"));
                if (user == null) {
                    user = ProfesionalDAO.login(myMap.get("email"), myMap.get("password"));
                }
                if (user != null ){
                    responseToConsumer = new Gson().toJson(user);
                    response.setStatus(HttpServletResponse.SC_OK);
                } else {
                    responseToConsumer = new Gson().toJson("{\"error\":\"Combinación usuario/contraseña incorrecta\"}");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }
            } catch (ConexionException ex) {
                Logger.getLogger(actionLogin.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al logearse\"}");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (AccesoException ex) {
                Logger.getLogger(actionLogin.class.getName()).log(Level.SEVERE, null, ex);
                responseToConsumer = new Gson().toJson("{\"error\":\"Error al logearse\"}");
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
        resp.setHeader("Access-Control-Allow-Methods", "POST");
    }
}
