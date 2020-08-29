package spring.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "disk", schema = "public", catalog = "postgres")
public class DiskEntity {
    private int diskId;
    private String name;
    private String type;
    private Integer price;
    private Set<FilmEntity> films = new HashSet<>();

    public DiskEntity(){}

    public DiskEntity(String name, String type, Integer price) {
        this.type = type;
        this.price = price;
    }

    @Id
    @GenericGenerator(name = "keygen", strategy = "increment")
    @GeneratedValue(generator = "keygen")
    @Column(name = "disk_id", nullable = false)
    public int getDiskId() {
        return diskId;
    }

    public void setDiskId(int diskId) {
        this.diskId = diskId;
    }

    @Basic
    @Column(name = "type", nullable = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price", nullable = false, length = 20, columnDefinition = "integer default 0")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiskEntity that = (DiskEntity) o;

        if (diskId != that.diskId) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = diskId;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "disk_film",
            joinColumns = { @JoinColumn (name = "disk_id") },
            inverseJoinColumns = { @JoinColumn (name = "film_id") }
    )
    public Set<FilmEntity> getFilms () {
        return films;
    }

    public void setFilms(Set<FilmEntity> films) {
        this.films = films;
    }
}