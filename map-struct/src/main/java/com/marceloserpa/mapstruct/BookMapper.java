package com.marceloserpa.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class );

    //@Mapping(target = "seatCount", source = "numberOfSeats")

    BookDTO bookToBookDto(Book book);


}
