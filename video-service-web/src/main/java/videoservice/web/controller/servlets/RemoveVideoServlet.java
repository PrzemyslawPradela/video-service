package videoservice.web.controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import videoservice.web.soap.client.VideoService;
import videoservice.web.soap.client.VideoWebService;

@WebServlet(name = "RemoveVideoServlet", urlPatterns = {"/RemoveVideoServlet"})
public class RemoveVideoServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "http://localhost:8080/video-service-soap/VideoService?wsdl")
    private VideoService service;

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

        try { // Call Web Service Operation
            VideoWebService port = service.getVideoWebServicePort();
            String idString = request.getParameter("id");
            int id = Integer.parseInt(idString);
            port.removeVideoByID(id);
            response.sendRedirect("index.jsp");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
