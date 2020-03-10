package videoservice.web.controller.servlets;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.ws.WebServiceRef;
import org.apache.commons.io.IOUtils;
import videoservice.web.soap.client.Video;
import videoservice.web.soap.client.VideoService;
import videoservice.web.soap.client.VideoWebService;

@WebServlet(name = "UpdateVideoServlet", urlPatterns = {"/UpdateVideoServlet"})
@MultipartConfig
public class UpdateVideoServlet extends HttpServlet {

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
            Video video = port.getVideoByID(id);
            request.setAttribute("video", video);
            request.getRequestDispatcher("edit.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
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

        try { // Call Web Service Operation
            VideoWebService port = service.getVideoWebServicePort();
            request.setCharacterEncoding("utf-8");
            String idString = request.getParameter("id");
            int id = Integer.parseInt(idString);
            String title = request.getParameter("title");
            Part filePart = request.getPart("file");
            Video video = port.getVideoByID(id);
            port.editVideo(video);
            if (!title.isEmpty() || !title.equals(video.getTitle())) {
                video.setTitle(title);
                port.editVideo(video);
            }
            if (filePart != null) {
                InputStream fileContent = filePart.getInputStream();
                byte[] file = IOUtils.toByteArray(fileContent);
                video.setFile(file);
                port.editVideo(video);
            }
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
