package br.com.magalu.desafio.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author Pedro Henrique Veiga
 * @created 06/06/24
 * @lastModified 06/06/24
 */
@AllArgsConstructor
@Entity
@Table(name = "QUOTE")
public class QuoteEntity {

    public QuoteEntity() {
        //Construtor vazio
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @JsonProperty(value = "userId")
    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ORDER_ID")
    private Integer orderId;

    @Column(name = "PRODUCT_ID")
    private Integer productId;

    @Column(name = "PRODUCT_VALUE")
    private Double productValue;


    @JsonFormat(shape=JsonFormat.Shape.NUMBER_INT, pattern="yyyyMMdd", locale = "pt-br", timezone = "American/São_Paulo")
    @Column(name = "DATE_BUY",columnDefinition = "TIMESTAMP")
    private Date dateBuy;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Double getProductValue() {
        return productValue;
    }

    public void setProductValue(Double productValue) {
        this.productValue = productValue;
    }

    public Date getDateBuy() {
        return dateBuy;
    }

    public void setDateBuy(Date dateBuy) {
        this.dateBuy = dateBuy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuoteEntity)) return false;
        QuoteEntity that = (QuoteEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(name, that.name) && Objects.equals(orderId, that.orderId) && Objects.equals(productId, that.productId) && Objects.equals(productValue, that.productValue) && Objects.equals(dateBuy, that.dateBuy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, name, orderId, productId, productValue, dateBuy);
    }

    @Override
    public String toString() {
        return "QuoteEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", productValue=" + productValue +
                ", dateBuy=" + dateBuy +
                '}';
    }


}