import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

public class ZipFileCheck {

    @Test
    @DisplayName("Проверка PDF, XLSX и CSV файлов из example.zip")
    void checkingFilesInZip() throws IOException {

        ClassLoader cl = this.getClass().getClassLoader();

        try (InputStream is = cl.getResourceAsStream("example.zip");
             ZipInputStream zis = new ZipInputStream(is)) {




        }




    }
}
