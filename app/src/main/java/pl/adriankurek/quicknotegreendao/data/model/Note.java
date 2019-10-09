package pl.adriankurek.quicknotegreendao.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

@Entity(nameInDb = "notes")
public class Note {
    @Id(autoincrement = true)
    private Long id;
    private String contents;
    private Date creationDate;

    public Note() {
    }

    @Generated(hash = 1369292830)
    public Note(Long id, String contents, Date creationDate) {
        this.id = id;
        this.contents = contents;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContents() {
        return this.contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
