package guru.springframework.lombokbestpractices.mappers;


import guru.springframework.lombokbestpractices.entities.Beer;
import guru.springframework.lombokbestpractices.model.BeerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by jt, Spring Framework Guru.
 */
@Mapper
public interface BeerMapper {

    @Mapping(target = "beerOrderLines", ignore = true)
    Beer beerDtoToBeer(BeerDTO dto);

    BeerDTO beerToBeerDto(Beer beer);

}
