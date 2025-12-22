package org.example.softfinalproject.mapper;

import org.example.softfinalproject.dto.ExtraDto;
import org.example.softfinalproject.entity.Extra;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExtraMapper {
    ExtraDto toDto(Extra extra);
    Extra toEntity(ExtraDto extraDto);

    List<ExtraDto> toDtoList(List<Extra> extras);
}
