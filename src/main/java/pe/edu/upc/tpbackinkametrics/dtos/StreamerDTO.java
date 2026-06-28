package pe.edu.upc.tpbackinkametrics.dtos;

import java.time.LocalDate;

public class StreamerDTO {
    private int id;
    private String nickname;
    private String gender;
    private LocalDate registrationDate;
    private int regionId;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }

    public int getRegionId() { return regionId; }
    public void setRegionId(int regionId) { this.regionId = regionId; }
}
