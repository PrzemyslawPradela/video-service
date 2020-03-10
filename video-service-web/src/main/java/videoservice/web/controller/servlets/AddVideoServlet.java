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

@WebServlet(name = "AddVideoServlet", urlPatterns = {"/AddVideoServlet"})
@MultipartConfig
public class AddVideoServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "http://localhost:8080/video-service-soap/VideoService?wsdl")
    private VideoService service;

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
            request.setCharacterEncoding("UTF-8");
            String title = request.getParameter("title");
            Part filePart = request.getPart("file");
            InputStream fileContent = filePart.getInputStream();
            byte[] fileAsByteArray = IOUtils.toByteArray(fileContent);
            Video video = new Video();
            video.setTitle(title);
            video.setFile(fileAsByteArray);
            port.addVideo(video);
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
