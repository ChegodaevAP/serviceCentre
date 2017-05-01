package ServiceCentre.model;

import javax.persistence.*;

@Entity
@Table(name = "REPORTS")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    private byte[] file;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "file", columnDefinition = "BLOB NOT NULL")
    public byte[] getFile() { return file; }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
