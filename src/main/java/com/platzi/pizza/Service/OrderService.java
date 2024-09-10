package com.platzi.pizza.Service;

import com.platzi.pizza.Persistence.entity.OrderEntity;
import com.platzi.pizza.Persistence.projection.OrderSummary;
import com.platzi.pizza.Persistence.repository.OrderRepository;
import com.platzi.pizza.Service.dto.RamdomOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final String DELIVERY ="D";
    private final String CARRYOUT ="C";
    private final String ON_SITE = "S";

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll() {
       return this.orderRepository.findAll();
    }

    public List<OrderEntity> getTodayOrders(){
        LocalDateTime today = LocalDate.of(2024,7,3).atTime(0, 0);
        return this.orderRepository.findAllByDateAfter(today);
    }

    public List<OrderEntity> getOutsideOrders(){

        List<String> methods = Arrays.asList(DELIVERY,CARRYOUT);
        return this.orderRepository.findAllByMethodIn(methods);
    }

    public List<OrderEntity> getCustomerOrders(String idCustomer) {
        return this.orderRepository.findCustomerOrders(idCustomer);
    }

    public OrderSummary getSummary(int orderId){
        return this.orderRepository.findSummary(orderId);
    }

    @Transactional
    public boolean saveRamdomOrder(RamdomOrderDto ramdomOrderDto) {
        return this.orderRepository.saveRandomOrder(ramdomOrderDto.getIdCustomer(),ramdomOrderDto.getMethod());
    }

}
