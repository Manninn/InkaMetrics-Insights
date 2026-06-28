package pe.edu.upc.tpbackinkametrics.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "monitored_channel")
public class MonitoredChannel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "channel_id", nullable = false)
    private Channel channel;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = true)
    private Company company;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
