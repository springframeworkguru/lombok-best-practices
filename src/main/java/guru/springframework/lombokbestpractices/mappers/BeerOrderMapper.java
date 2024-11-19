package guru.springframework.lombokbestpractices.mappers;


import guru.springframework.lombokbestpractices.entities.BeerOrder;
import guru.springframework.lombokbestpractices.entities.BeerOrderLine;
import guru.springframework.lombokbestpractices.model.BeerOrderDTO;
import guru.springframework.lombokbestpractices.model.BeerOrderLineDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by jt, Spring Framework Guru.
 */
@Mapper
public interface BeerOrderMapper {


    @Mapping(target = "beerOrder", ignore = true)
    BeerOrderLine beerOrderLineDtoToBeerOrderLine(BeerOrderLineDTO beerOrderLineDTO);

    BeerOrderLineDTO beerOrderLineToBeerOrderLineDto(BeerOrderLine beerOrderLine);

    BeerOrder beerOrderDtoToBeerOrder(BeerOrderDTO beerOrder);

    BeerOrderDTO beerOrderToBeerOrderDto(BeerOrder beerOrder);
}
