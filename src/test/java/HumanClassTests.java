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

            String petr = new String ("Пётр".getBytes("windows1251"), StandardCharsets.UTF_8);
            Assertions.assertEquals(petr, human.getName());
            Assertions.assertEquals(40, human.getAge());
            Assertions.assertEquals(2, human.getChildren().size());
            Assertions.assertEquals("Olga", human.getChildren().get(0).getName());

        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
