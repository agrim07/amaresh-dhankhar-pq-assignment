package org.payconiq.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CURRENTPRICE")
    private BigDecimal currentPrice;

    @Column(name = "TIMESTAMP")
    private String timestamp;

    public Stock() {   }

    public Stock(Long id, String name, BigDecimal currentPrice, String timestamp) {
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @PrePersist
    @PreUpdate
    public void insertUpdatePreEvent() {
        if(null == timestamp) {
            timestamp = String.valueOf(System.currentTimeMillis());
        }
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", currentPrice=" + currentPrice +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
