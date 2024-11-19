package guru.springframework.lombokbestpractices.services;


import guru.springframework.lombokbestpractices.controllers.NotFoundException;
import guru.springframework.lombokbestpractices.entities.BeerOrder;
import guru.springframework.lombokbestpractices.entities.BeerOrderLine;
import guru.springframework.lombokbestpractices.mappers.BeerOrderMapper;
import guru.springframework.lombokbestpractices.model.BeerOrderCreateDTO;
import guru.springframework.lombokbestpractices.model.BeerOrderDTO;
import guru.springframework.lombokbestpractices.model.BeerOrderUpdateDTO;
import guru.springframework.lombokbestpractices.repositories.BeerOrderRepository;
import guru.springframework.lombokbestpractices.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
@Service
public class BeerOrderServiceJPA implements BeerOrderService {

    private final BeerOrderRepository beerOrderRepository;
    private final BeerOrderMapper beerOrderMapper;
    private final BeerRepository beerRepository;

    public BeerOrderServiceJPA(BeerOrderRepository beerOrderRepository, BeerOrderMapper beerOrderMapper, BeerRepository beerRepository) {
        this.beerOrderRepository = beerOrderRepository;
        this.beerOrderMapper = beerOrderMapper;
        this.beerRepository = beerRepository;
    }

    @Override
    public void deleteOrder(UUID beerOrderId) {
        if (beerOrderRepository.existsById(beerOrderId)) {
            beerOrderRepository.deleteById(beerOrderId);
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public BeerOrderDTO updateOrder(UUID beerOrderId, BeerOrderUpdateDTO beerOrderUpdateDTO) {
        val order = beerOrderRepository.findById(beerOrderId).orElseThrow(NotFoundException::new);

        order.setCustomerRef(beerOrderUpdateDTO.getCustomerRef());

        beerOrderUpdateDTO.getBeerOrderLines().forEach(beerOrderLine -> {

        if (beerOrderLine.getBeerId() != null) {
                val foundLine = order.getBeerOrderLines().stream()
                        .filter(beerOrderLine1 -> beerOrderLine1.getId().equals(beerOrderLine.getId()))
                        .findFirst().orElseThrow(NotFoundException::new);
                foundLine.setBeer(beerRepository.findById(beerOrderLine.getBeerId()).orElseThrow(NotFoundException::new));
                foundLine.setOrderQuantity(beerOrderLine.getOrderQuantity());
                foundLine.setQuantityAllocated(beerOrderLine.getQuantityAllocated());
            } else {
                order.getBeerOrderLines().add(BeerOrderLine.builder()
                        .beer(beerRepository.findById(beerOrderLine.getBeerId()).orElseThrow(NotFoundException::new))
                        .orderQuantity(beerOrderLine.getOrderQuantity())
                        .quantityAllocated(beerOrderLine.getQuantityAllocated())
                        .build());
            }
        });


        return beerOrderMapper.beerOrderToBeerOrderDto(beerOrderRepository.save(order));
    }

    @Override
    public BeerOrder createOrder(BeerOrderCreateDTO beerOrderCreateDTO) {

        Set<BeerOrderLine> beerOrderLines = new HashSet<>();

        beerOrderCreateDTO.getBeerOrderLines().forEach(beerOrderLine -> {
            beerOrderLines.add(BeerOrderLine.builder()
                    .beer(beerRepository.findById(beerOrderLine.getBeerId()).orElseThrow(NotFoundException::new))
                    .orderQuantity(beerOrderLine.getOrderQuantity())
                    .build());
        });

        return beerOrderRepository.save(BeerOrder.builder()
                .beerOrderLines(beerOrderLines)
                .customerRef(beerOrderCreateDTO.getCustomerRef())
                .build());
    }

    @Override
    public Optional<BeerOrderDTO> getById(UUID beerOrderId) {
        return Optional.ofNullable(beerOrderMapper.beerOrderToBeerOrderDto(beerOrderRepository.findById(beerOrderId)
                .orElse(null)));
    }

    @Override
    public Page<BeerOrderDTO> listOrders(Integer pageNumber, Integer pageSize) {

        if (pageNumber == null || pageNumber < 0) {
            pageNumber = 0;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = 25;
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        return beerOrderRepository.findAll(pageRequest).map(beerOrderMapper::beerOrderToBeerOrderDto);
    }
}












