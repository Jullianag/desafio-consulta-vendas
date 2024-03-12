package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.projections.SaleProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
/*
    @Query(nativeQuery = true, value = "SELECT tb_seller.id, tb_sales.date, tb_sales.amount, tb_seller.name " +
            "FROM tb_sales " +
            "INNER JOIN tb_seller ON tb_sales.seller_id = tb_seller.id " +
            "WHERE tb_sales.date BETWEEN :min AND :max " +
            "AND tb_seller.name LIKE CONCAT('%', :name, '%') ")

 */
    @Query(value = "SELECT obj " +
            "FROM Sale obj " +
            "WHERE obj.date BETWEEN :min AND :max " +
            "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))",
    countQuery = "SELECT COUNT(obj) " +
            "FROM Sale obj " +
            "JOIN obj.seller")
    Page<Sale> searchAll(LocalDate min, LocalDate max, String name, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT tb_seller.name, SUM(tb_sales.amount) AS total " +
            "FROM tb_sales " +
            "INNER JOIN tb_seller ON tb_sales.seller_id = tb_seller.id " +
            "WHERE tb_sales.date BETWEEN :min AND :max " +
            "GROUP BY tb_seller.name ")
    List<SaleProjection> searchSummary(LocalDate min, LocalDate max);

}
