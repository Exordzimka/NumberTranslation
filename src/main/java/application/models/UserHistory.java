package application.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "user_history")
public class UserHistory
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private NumberTranslationUser numberTranslationUser;
    private String beforeTranslate;

    public void setNumberTranslationUser(NumberTranslationUser numberTranslationUser) {
        this.numberTranslationUser = numberTranslationUser;
    }

    public void setBeforeTranslate(String beforeTranslate) {
        this.beforeTranslate = beforeTranslate;
    }

    public void setAfterTranslate(String afterTranslate) {
        this.afterTranslate = afterTranslate;
    }

    private String afterTranslate;
    private LocalDateTime dateTime;

    public void setId(long id)
    {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public NumberTranslationUser getNumberTranslationUser()
    {
        return numberTranslationUser;
    }

    public String getBeforeTranslate()
    {
        return beforeTranslate;
    }

    public String getAfterTranslate()
    {
        return afterTranslate;
    }

    public LocalDateTime getDateTime(){return dateTime;}

    public void setDateTime(LocalDateTime dateTime){
        this.dateTime = dateTime;
    }

    public UserHistory()
    {
    }

    public UserHistory(NumberTranslationUser numberTranslationUser, String beforeTranslate, String afterTranslate)
    {
        this.numberTranslationUser = numberTranslationUser;
        this.beforeTranslate = beforeTranslate;
        this.afterTranslate = afterTranslate;
    }
}
