package videoservice.soap.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VIDEO",
        schema = "ROOT"
)
public class Video implements java.io.Serializable {

    private int id;
    private String title;
    private byte[] file;
    private String videoBase64String;

    public Video() {
    }

    public Video(int id, String title, byte[] file) {
        this.id = id;
        this.title = title;
        this.file = file;
    }

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "TITLE", nullable = false, length = 1024)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "FILE", nullable = false)
    public byte[] getFile() {
        return this.file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getVideoBase64String() {
        return videoBase64String;
    }

    public void setVideoBase64String(String videoBase64String) {
        this.videoBase64String = videoBase64String;
    }

}
