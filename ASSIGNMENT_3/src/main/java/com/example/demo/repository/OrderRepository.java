package com.example.demo.repository;

import com.example.demo.model.Customer;
import com.example.demo.model.DeliveryNote;
import com.example.demo.model.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = {"saleDetailList"})
    List<Order> findAll();

    List<Order> findAllByDateLessThanEqualAndDateGreaterThanEqual(Date deliveryNoteStart, Date deliveryNoteEnd);

    Order findOrderById(Long orderId);
}
