package zerobase.weather.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Diary;
import zerobase.weather.repository.DiaryRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class DiaryServiceTest {

    @Autowired
    DiaryService diaryService;

    @Autowired
    DiaryRepository diaryRepository;

    @Test
    void createDiarySuccess() {
        //given
        Diary nowDiary = new Diary();
        nowDiary.setId(1);
        nowDiary.setDate(LocalDate.parse("2023-06-21"));
        nowDiary.setText("today");
        nowDiary.setWeather("rain");

        //when
        diaryRepository.save(nowDiary);

        //then
        Optional<Diary> result = diaryRepository.findById(1);
        assertEquals(result.get().getText(), "today");
        assertEquals(result.get().getWeather(), "rain");
    }

    @Test
    void readDiary() {
        //given
        LocalDate date = LocalDate.parse("2023-06-21");

        //when
        List<Diary> result = diaryRepository.findAllByDate(date);

        //then
        assertEquals(result.get(0).getDate().compareTo(date), 0);
    }

    @Test
    void readDiaries() {
        //given
        LocalDate startDate = LocalDate.parse("2023-05-20");
        LocalDate endDate = LocalDate.parse("2023-06-20");

        //when
        List<Diary> result = diaryRepository.findAllByDateBetween(startDate, endDate);

        //then
        assertTrue(result.get(0).getDate().isBefore(LocalDate.parse("2023-06-20")));
        assertTrue(result.get(0).getDate().isAfter(LocalDate.parse("2023-05-20")));
    }

    @Test
    void updateDiary() {
        //given
        LocalDate date = LocalDate.parse("2023-06-03");
        String text = "um....";

        //when
        Diary nowDiary = diaryRepository.getFirstByDate(date);
        nowDiary.setText(text);
        diaryRepository.save(nowDiary);
        Diary result = diaryRepository.getFirstByDate(date);

        //then
        assertEquals(result.getText(), text);
    }


}