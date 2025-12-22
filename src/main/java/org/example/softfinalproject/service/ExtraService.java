package org.example.softfinalproject.service;

import lombok.AllArgsConstructor;
import org.example.softfinalproject.dto.ExtraDto;
import org.example.softfinalproject.entity.Extra;
import org.example.softfinalproject.mapper.ExtraMapper;
import org.example.softfinalproject.repository.ExtraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExtraService {

    private final ExtraRepository extraRepository;
    private final ExtraMapper extraMapper;

    public List<ExtraDto> getAll(){
        return extraMapper.toDtoList(extraRepository.findAll());
    }

    public ExtraDto getExtra(Long id){
        return extraMapper.toDto(extraRepository.findById(id).orElseThrow());
    }

    public ExtraDto addExtra(ExtraDto extraDto){
        return extraMapper.toDto(extraRepository.save(extraMapper.toEntity(extraDto)));
    }

    public ExtraDto updateExtra(Long id, ExtraDto extraDto){
        Extra update = extraRepository.findById(id).orElseThrow();

        update.setName(extraDto.getName());
        update.setPrice(extraDto.getPrice());

        return extraMapper.toDto(extraRepository.save(update));
    }

    public boolean deleteExtra(Long id){
        extraRepository.deleteById(id);
        return true;
    }

}
