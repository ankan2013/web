package spring.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ordr", schema = "public", catalog = "postgres")
public class OrdrEntity {
    private int ordrId;
    private Boolean isReturned;
    private Date requestTime;
    private Date returnTime;
    private DiskEntity disk;
    private ClientEntity client;
    private List<FilmEntity> films;

    public OrdrEntity(){}

    public OrdrEntity(Boolean isReturned, Date requestTime, Date returnTime) {
        this.isReturned = isReturned;
        this.requestTime = requestTime;
        this.returnTime = returnTime;
    }

    @Id
    @GenericGenerator(name = "keygen", strategy = "increment")
    @GeneratedValue(generator = "keygen")
    @Column(name = "ordr_id", nullable = false)
    public int getOrdrId() {
        return ordrId;
    }

    public void setOrdrId(int ordrId) {
        this.ordrId = ordrId;
    }

    @Basic
    @Column(name = "is_returned", nullable = false)
    public Boolean getIsReturned() {
        return isReturned;
    }

    public void setIsReturned(Boolean isReturned) {
        this.isReturned = isReturned;
    }

    @Basic
    @Column(name = "request_time", nullable = false)
    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    @Basic
    @Column(name = "return_time", nullable = false)
    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdrEntity that = (OrdrEntity) o;

        if (ordrId != that.ordrId) return false;
        if (requestTime != null ? !requestTime.equals(that.requestTime) : that.requestTime != null) return false;
        if (returnTime != null ? !returnTime.equals(that.returnTime) : that.returnTime != null) return false;
        if (isReturned != that.isReturned) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ordrId;
        result = 31 * result + (requestTime != null ? requestTime.hashCode() : 0);
        result = 31 * result + (returnTime != null ? returnTime.hashCode() : 0);
        result = 31 * result + isReturned.hashCode();
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    @ManyToOne
    @JoinColumn(name = "disk_id", referencedColumnName = "disk_id")
    public DiskEntity getDisk() {
        return disk;
    }

    public void setDisk(DiskEntity disk) {
        this.disk = disk;
    }

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "ordr_film",
            joinColumns = { @JoinColumn (name = "film_id") },
            inverseJoinColumns = { @JoinColumn (name = "ordr_id") }
    )
    public List<FilmEntity> getFilms() {
        return films;
    }

    public void setFilms(List<FilmEntity> films) {
        this.films = films;
    }

}