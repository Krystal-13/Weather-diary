package zerobase.weather.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Memo;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
// Test 에 붙힌 @Transactional 이 붙어있으면 다 롤백되어진다.
class JpaMemoRepositoryTest {

    @Autowired
    JpaMemoRepository jpaMemoRepository;

    @Test
    void insertMemoTest() {
        //given
        Memo newMemo = new Memo(10, "this is jpa memo");
        //when
        jpaMemoRepository.save(newMemo);
        //then
        List<Memo> memoList = jpaMemoRepository.findAll();
        assertTrue(memoList.size() > 0);
    }

    @Test
    void findByIdTest() {
        //given
        Memo newMemo = new Memo(11, "jpa");
        //when
        //jpaMemoRepository.save(newMemo);
        Memo memo = jpaMemoRepository.save(newMemo);
        System.out.println(memo.getId());
        //then
        //Optional<Memo> result = jpaMemoRepository.findById(11);`
        // -> mysql 에 키값인 id 를 만드는것을 넘겼으므로.. java id 값과 mysql id 값이 다를수도 있음
        Optional<Memo> result = jpaMemoRepository.findById(memo.getId());
        assertEquals(result.get().getText(), "jpa");
    }

}