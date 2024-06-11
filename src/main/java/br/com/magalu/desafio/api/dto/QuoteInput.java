package br.com.magalu.desafio.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Digits;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author Pedro Henrique Veiga
 * @created 05/06/24
 * @lastModified 05/06/24
 */

@AllArgsConstructor
public class QuoteInput implements Serializable {


    public QuoteInput() {
        //Construtor vazio
    }

    @JsonProperty(value = "userId")
    private Integer userId;

    private String name;

    private Integer orderId;

    private Integer productId;

    private Double productValue;

    @JsonFormat(shape=JsonFormat.Shape.NUMBER_INT, pattern="yyyyMMdd", locale = "pt-br", timezone = "American/São_Paulo")
    private Date dateBuy;


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
        if (!(o instanceof QuoteInput)) return false;
        QuoteInput quoteInput = (QuoteInput) o;
        return Objects.equals(userId, quoteInput.userId) && Objects.equals(name, quoteInput.name) && Objects.equals(orderId, quoteInput.orderId) && Objects.equals(productId, quoteInput.productId) && Objects.equals(productValue, quoteInput.productValue) && Objects.equals(dateBuy, quoteInput.dateBuy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, orderId, productId, productValue, dateBuy);
    }

    @Override
    public String toString() {
        return "QuoteInput{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", productValue=" + productValue +
                ", dateBuy=" + dateBuy +
                '}';
    }
}
