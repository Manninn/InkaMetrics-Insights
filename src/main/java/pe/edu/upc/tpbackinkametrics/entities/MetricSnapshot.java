package pe.edu.upc.tpbackinkametrics.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "metric_snapshot")
public class MetricSnapshot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "amount", nullable = false)
    private int amount;

    @ManyToOne
    @JoinColumn(name = "broadcast_id", nullable = false)
    private Broadcast broadcast;

    public MetricSnapshot() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Broadcast getBroadcast() {
        return broadcast;
    }

    public void setBroadcast(Broadcast broadcast) {
        this.broadcast = broadcast;
    }
}
