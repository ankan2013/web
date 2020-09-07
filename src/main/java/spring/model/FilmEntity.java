package spring.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "film", schema = "public", catalog = "postgres")
public class FilmEntity {
    private int filmId;
    private String name;
    private String info;
    private Set<DiskEntity> disks = new HashSet<>();

    public FilmEntity(){}

    public FilmEntity(String name, String info) {
        this.name = name;
        this.info = info;
    }

    @Id
    @GenericGenerator(name = "keygen", strategy = "increment")
    @GeneratedValue(generator = "keygen")
    @Column(name = "film_id", nullable = false)
    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "info", nullable = false)
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilmEntity that = (FilmEntity) o;

        if (filmId != that.filmId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;;

        return true;
    }

    @Override
    public int hashCode() {
        int result = filmId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "disk_film",
            joinColumns = { @JoinColumn (name = "film_id") },
            inverseJoinColumns = { @JoinColumn (name = "disk_id") }
    )
    public Set<DiskEntity> getDisks () {
        return disks;
    }

    public void setDisks(Set<DiskEntity> disks) {
        this.disks = disks;
    }
}