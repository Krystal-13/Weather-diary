package zerobase.weather.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;

@SpringBootTest
@Transactional
class DiaryControllerTest {

    @Autowired
    DiaryController diaryController;

    @Autowired
    DiaryService diaryService;


    @Test
    void deleteDiary() {
        diaryService.deleteDiary(LocalDate.parse("2023-06-21"));
    }

}