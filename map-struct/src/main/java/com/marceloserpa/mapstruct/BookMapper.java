package com.marceloserpa.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class );

    @Mapping(target = "pages", source = "numberOfPages")
    @Mapping(target = "publicationDate", source = "publicationDate", dateFormat = "yyyy/MM/dd")
    BookDTO bookToBookDto(Book book);


}
