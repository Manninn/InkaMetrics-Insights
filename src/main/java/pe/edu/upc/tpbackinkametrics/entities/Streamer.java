package pe.edu.upc.tpbackinkametrics.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "streamer")
public class Streamer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nickname", nullable = false, length = 100)
    private String nickname;

    @Column(name = "gender", nullable = false, length = 100)
    private String gender;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @JsonIgnore
    @OneToMany(mappedBy = "streamer")
    private List<Channel> channels;

    public Streamer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
