package org.example.softfinalproject.serviceTest;

import org.example.softfinalproject.dto.ExtraDto;
import org.example.softfinalproject.service.ExtraService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ExtraServiceTest {

    @Autowired
    private ExtraService extraService;

    @Test
    void getAllExtrasTest(){
        List<ExtraDto> extraDtoList = extraService.getAll();

        Assertions.assertNotNull(extraDtoList);
        Assertions.assertNotEquals(0, extraDtoList.size());

        for (ExtraDto extraDto: extraDtoList){
            Assertions.assertNotNull(extraDto);
            Assertions.assertNotNull(extraDto.getId());
            Assertions.assertNotNull(extraDto.getName());
            Assertions.assertNotNull(extraDto.getPrice());
        }
    }

    @Test
    void getExtraTest(){
        ExtraDto extraDto = extraService.getExtra(1L);

        Assertions.assertNotNull(extraDto);
        Assertions.assertNotNull(extraDto.getId());
        Assertions.assertNotNull(extraDto.getName());
        Assertions.assertNotNull(extraDto.getPrice());
    }

    @Test
    void addExtraTest(){
        ExtraDto extraDto = ExtraDto.builder().id(1L).name("name").price(100).build();

        ExtraDto addExtra = extraService.addExtra(extraDto);

        Assertions.assertNotNull(extraDto);
        Assertions.assertNotNull(extraDto.getId());
        Assertions.assertNotNull(extraDto.getName());
        Assertions.assertNotNull(extraDto.getPrice());

        Assertions.assertEquals(extraDto.getId(), addExtra.getId());
        Assertions.assertEquals(extraDto.getName(), addExtra.getName());
        Assertions.assertEquals(extraDto.getPrice(), addExtra.getPrice());
    }

    @Test
    void updateExtraTest(){
        ExtraDto extraDto = ExtraDto.builder().id(1L).name("name").price(100).build();

        ExtraDto updateExtra = extraService.updateExtra(extraDto.getId(), extraDto);

        Assertions.assertNotNull(updateExtra);
        Assertions.assertNotNull(updateExtra.getId());
        Assertions.assertNotNull(updateExtra.getName());
        Assertions.assertNotNull(updateExtra.getPrice());

        Assertions.assertEquals(extraDto.getId(), updateExtra.getId());
        Assertions.assertEquals(extraDto.getName(), updateExtra.getName());
        Assertions.assertEquals(extraDto.getPrice(), updateExtra.getPrice());
    }

    @Test
    void deleteExtraTest(){
        boolean deleteExtra = extraService.deleteExtra(1L);
        Assertions.assertTrue(deleteExtra);
    }
}
