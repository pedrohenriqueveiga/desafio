package br.com.magalu.desafio.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonPropertyOrder({"product_id","value"})
public class ProductDTO {

    @JsonProperty(value = "product_id")
    private Integer productId;
    private double value;

    public ProductDTO(Integer productId, double value) {
        this.productId = productId;
        this.value = value;
    }

    public Integer getProductId() {
        return productId;
    }

    public double getValue() {
        return value;
    }


    @Override
    public String toString() {
        return "ProductDTO{" +
                "productId=" + productId +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDTO)) return false;
        ProductDTO that = (ProductDTO) o;
        return Double.compare(that.value, value) == 0 && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, value);
    }
}
