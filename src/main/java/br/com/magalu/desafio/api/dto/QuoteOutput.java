package br.com.magalu.desafio.api.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@JsonPropertyOrder({"user_id","name","orders"})
public class QuoteOutput {

    @JsonProperty(value = "user_id")
    private Integer userId;
    private String name;
    private List<OrderDTO> orders;

    public QuoteOutput(){
        //Construtor vazio.
    }

    public QuoteOutput(Integer userId, String name) {
        this.userId = userId;
        this.name = name;
        this.orders = new ArrayList<>();
    }
    public void addOrders(List<OrderDTO> list){
        orders = list;

    }

    public void addQuote(QuoteInput input) {
        OrderDTO orderDTOGrouped = orders.stream()
                .filter(o -> o.getOrderId().equals(input.getOrderId()))
                .findFirst()
                .orElseGet(() -> {
                    OrderDTO newOrderDTO = new OrderDTO(input.getOrderId(), input.getDateBuy());
                    orders.add(newOrderDTO);
                    return newOrderDTO;
                });
        orderDTOGrouped.addProduct(input.getProductId(), input.getProductValue());
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void ComparatorQuote() {
        Collections.sort(this.orders, Comparator.comparingInt(OrderDTO::getOrderId));
    }

}
