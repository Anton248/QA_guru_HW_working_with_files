import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.nikolski.Human;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HumanClassTests {

    @Test
    @DisplayName("Читаем объект Human is Json и проверяем")
    void jsonToHumanTest() {
        ClassLoader cl = this.getClass().getClassLoader();
        try (InputStream is = cl.getResourceAsStream("human.json");
             InputStreamReader isr = new InputStreamReader(is)) {

            ObjectMapper mapper = new ObjectMapper();
            Human human = mapper.readValue(isr, Human.class);
//Тест проходит, если только перекодировать литерал "Пётр" из UTF-8 в Windows-1251. В настройках IDEA везде где только
//можно указал UTF-8. Литерал "Пётр" сравнивается с записью из файла human.json, который в UTF-8 (проверено).
//И файл HumanClassTests.java, где литерал записан, также в кодировке UTF-8 (проверено). Можно ли как-то от перекодировки избавиться?
            String petr = new String ("Пётр".getBytes(StandardCharsets.UTF_8), "windows-1251");
            Assertions.assertEquals(petr, human.getName());
            Assertions.assertEquals(40, human.getAge());
            Assertions.assertEquals(2, human.getChildren().size());
            Assertions.assertEquals("Olga", human.getChildren().get(0).getName());

        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
