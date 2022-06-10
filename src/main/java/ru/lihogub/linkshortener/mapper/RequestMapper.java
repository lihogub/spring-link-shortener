package ru.lihogub.linkshortener.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.lihogub.linkshortener.dto.RequestDto;
import ru.lihogub.linkshortener.entity.Request;

import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RequestMapper {
    @Mapping(source = "details.ip", target = "ip")
    @Mapping( source = "details.date", target = "timestamp")
    RequestDto requestToRequestDto(Request request);

    List<RequestDto> requestListToRequestDtoList(List<Request> requestList);

    default long map(Date date) {
        return date == null ? 0L : date.getTime();
    }
}
