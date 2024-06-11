package br.com.magalu.desafio.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@JsonPropertyOrder({"order_id","total","date","products"})
public class OrderDTO {

    @JsonProperty(value = "order_id")
    private Integer orderId;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", locale = "pt-br", timezone = "American/São_Paulo")
    private Date date;
    private double total;
    private List<ProductDTO> products;


    public OrderDTO(Integer orderId, Date date) {
        this.orderId = orderId;
        this.date = date;
        this.total = 0.0;
        this.products = new ArrayList<>();

    }

    public void addProduct(int produtoId, double valorProduto) {
        this.products.add(new ProductDTO(produtoId, valorProduto));
        this.total += valorProduto;
    }

    public Integer getOrderId() {
        return orderId;
    }


    public Date getDate() {
        return date;
    }


    public List<ProductDTO> getProducts() {
        return products;
    }


    public double getTotal() {
        BigDecimal totalBig = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP);
        return totalBig.doubleValue();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDTO)) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Double.compare(orderDTO.total, total) == 0 && Objects.equals(orderId, orderDTO.orderId) && Objects.equals(date, orderDTO.date) && Objects.equals(products, orderDTO.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, date, total, products);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId=" + orderId +
                ", date=" + date +
                ", total=" + total +
                ", products=" + products +
                '}';
    }
}
