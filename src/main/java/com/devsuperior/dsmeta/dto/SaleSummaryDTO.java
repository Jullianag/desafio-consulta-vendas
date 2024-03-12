package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SaleProjection;

public class SaleSummaryDTO {

    private String name;
    private Double amount;

    public SaleSummaryDTO(String name, Double amount) {
        this.name = name;
        this.amount = amount;
    }

    public SaleSummaryDTO(SaleProjection projection) {
        name = projection.getName();
        amount = projection.getTotal();
    }

    public String getName() {
        return name;
    }

    public Double getAmount() {
        return amount;
    }
}

