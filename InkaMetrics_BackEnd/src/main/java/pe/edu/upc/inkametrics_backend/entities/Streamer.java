package pe.edu.upc.inkametrics_backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "streamer")
public class Streamer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idStreamer;

    @Column(name = "gicknameStreamer", nullable = false)
    private String nicknameStreamer;

    @Column(name = "genderStreamer", nullable = false)
    private String gender_Streamer;

    @Column(name = "registrationDateApp",nullable = false)
    private LocalDate registrationDateApp;

    public Streamer() {
    }

    public Streamer(int idStreamer, String nicknameStreamer, String gender_Streamer, LocalDate registrationDateApp) {
        this.idStreamer = idStreamer;
        this.nicknameStreamer = nicknameStreamer;
        this.gender_Streamer = gender_Streamer;
        this.registrationDateApp = registrationDateApp;
    }

    public int getIdStreamer() {
        return idStreamer;
    }

    public void setIdStreamer(int idStreamer) {
        this.idStreamer = idStreamer;
    }

    public String getNicknameStreamer() {
        return nicknameStreamer;
    }

    public void setNicknameStreamer(String nicknameStreamer) {
        this.nicknameStreamer = nicknameStreamer;
    }

    public String getGender_Streamer() {
        return gender_Streamer;
    }

    public void setGender_Streamer(String gender_Streamer) {
        this.gender_Streamer = gender_Streamer;
    }

    public LocalDate getRegistrationDateApp() {
        return registrationDateApp;
    }

    public void setRegistrationDateApp(LocalDate registrationDateApp) {
        this.registrationDateApp = registrationDateApp;
    }
}
