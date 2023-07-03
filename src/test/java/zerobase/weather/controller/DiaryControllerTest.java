package zerobase.weather.controller;

import org.junit.jupiter.api.DisplayName;
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
    private DiaryService diaryService;

    @Test
    @DisplayName("deleteDiary")
    void deleteDiary() {
        diaryService.deleteDiary(LocalDate.parse("2023-06-21"));
    }

}