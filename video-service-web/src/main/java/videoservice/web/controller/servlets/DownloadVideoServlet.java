package videoservice.web.controller.servlets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.Normalizer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import videoservice.web.soap.client.Video;
import videoservice.web.soap.client.VideoService;
import videoservice.web.soap.client.VideoWebService;

@WebServlet(name = "DownloadVideoServlet", urlPatterns = {"/DownloadVideoServlet"})
public class DownloadVideoServlet extends HttpServlet {

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
            byte[] file = video.getFile();
            String title = video.getTitle();
            String filename = Normalizer.normalize(title.replaceAll(" ", "_").toLowerCase().concat(".mp4"), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
            OutputStream out;
            try (InputStream in = new ByteArrayInputStream(file)) {
                out = response.getOutputStream();
                int i;
                while ((i = in.read()) != -1) {
                    out.write(i);
                }
            }
            out.close();
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
