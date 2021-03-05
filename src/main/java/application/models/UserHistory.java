package application.models;

import javax.persistence.*;

@Entity
@Table(name = "user_history")
public class UserHistory
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private NumberTranslationUser numberTranslationUser;
    private String beforeTranslate;
    private String afterTranslate;

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
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
