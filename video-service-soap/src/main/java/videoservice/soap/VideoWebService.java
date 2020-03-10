package videoservice.soap;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import videoservice.soap.entities.Video;
import videoservice.soap.utils.HibernateUtil;

@WebService(serviceName = "VideoService")
public class VideoWebService {

    /**
     * Web service operation
     *
     * @param id
     * @return video
     */
    @WebMethod(operationName = "getVideoByID")
    public Video getVideoByID(@WebParam(name = "id") int id) {
        Video video = new Video();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Video as video where video.id=" + id);
            video = (Video) query.uniqueResult();
            byte[] bytes = video.getFile();
            String base64String = Base64.getEncoder().encodeToString(bytes);
            video.setVideoBase64String(base64String);
            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return video;
    }

    /**
     * Web service operation
     *
     * @return all videos
     */
    @WebMethod(operationName = "getVideos")
    public List<Video> getVideos() {
        List<Video> videosWithBase64Video = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Video");
            List<Video> videos = query.list();
            videos.stream().map((video) -> {
                byte[] bytes = video.getFile();
                String base64String = Base64.getEncoder().encodeToString(bytes);
                video.setVideoBase64String(base64String);
                return video;
            }).forEachOrdered((video) -> {
                videosWithBase64Video.add(video);
            });
            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
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
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();
            session.save(video);
            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
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
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();
            session.update(video);
            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
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
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Video as video where video.id=" + id);
            Video video = (Video) query.uniqueResult();
            session.delete(video);
            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }
}
