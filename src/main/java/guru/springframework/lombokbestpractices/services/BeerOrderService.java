package guru.springframework.lombokbestpractices.services;


import guru.springframework.lombokbestpractices.entities.BeerOrder;
import guru.springframework.lombokbestpractices.model.BeerOrderCreateDTO;
import guru.springframework.lombokbestpractices.model.BeerOrderDTO;
import guru.springframework.lombokbestpractices.model.BeerOrderUpdateDTO;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface BeerOrderService {

    Optional<BeerOrderDTO> getById(UUID beerOrderId);

    Page<BeerOrderDTO> listOrders(Integer pageNumber, Integer pageSize);

    BeerOrder createOrder(BeerOrderCreateDTO beerOrderCreateDTO);

    BeerOrderDTO updateOrder(UUID beerOrderId, BeerOrderUpdateDTO beerOrderUpdateDTO);

    void deleteOrder(UUID beerOrderId);
}
