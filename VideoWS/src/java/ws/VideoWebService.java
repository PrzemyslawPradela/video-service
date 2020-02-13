package ws;

import entities.Video;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

@WebService(serviceName = "VideoWebService")
public class VideoWebService {

    private final Session session;

    public VideoWebService() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    /**
     * Web service operation
     *
     * @param id
     * @return video
     */
    @WebMethod(operationName = "getVideoByID")
    public Video getVideoByID(@WebParam(name = "id") int id) {
        Video video = null;
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Video as video where video.id=" + id);
            video = (Video) query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
        }

        return video;
    }

    /**
     * Web service operation
     *
     * @return list of videos
     */
    @WebMethod(operationName = "getVideos")
    public List<Video> getVideos() {
        List<Video> videosWithBase64Video = null;
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Video");
            List<Video> videos = query.list();
            for (Video video : videos) {
                Blob blob = video.getVideo();
                int blobLength = (int) blob.length();
                byte[] blobAsBytes = blob.getBytes(1, blobLength);
                blob.free();
                String base64String = Base64.getEncoder().encodeToString(blobAsBytes);
                video.setVideoBase64String(base64String);
                videosWithBase64Video.add(video);
            }
            tx.commit();
        } catch (SQLException e) {
        }

        return videosWithBase64Video;
    }

    /**
     * Web service operation
     *
     * @param video
     */
    @WebMethod(operationName = "addVideo")
    @Oneway
    public void addVideo(@WebParam(name = "video") Video video) {
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(video);
            tx.commit();
        } catch (Exception e) {
        }
    }

    /**
     * Web service operation
     *
     * @param video
     */
    @WebMethod(operationName = "editVideo")
    @Oneway
    public void editVideo(@WebParam(name = "video") Video video) {
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(video);
            tx.commit();
        } catch (Exception e) {
        }
    }

    /**
     * Web service operation
     *
     * @param id
     */
    @WebMethod(operationName = "removeVideoByID")
    @Oneway
    public void removeVideoByID(@WebParam(name = "id") int id) {
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Video as video where video.id=" + id);
            Video video = (Video) query.uniqueResult();
            session.delete(video);
            tx.commit();
        } catch (Exception e) {
        }
    }
}
