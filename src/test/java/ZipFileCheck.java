import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFileCheck {

    @Test
    @DisplayName("Проверка PDF, XLSX и CSV файлов из example.zip")
    void checkingFilesInZip() throws IOException, CsvException {

        ClassLoader cl = this.getClass().getClassLoader();

        try (InputStream is = cl.getResourceAsStream("example.zip");
             ZipInputStream zis = new ZipInputStream(is)) {

            //парсинг и проверка CSV файла
            ZipEntry entryCSV = zis.getNextEntry();
            CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
            List<String[]> allLines = csvReader.readAll();
            Assertions.assertEquals("1.csv", entryCSV.getName());
            Assertions.assertArrayEquals(new String[]{"year",
                            "industry_code_ANZSIC",
                            "industry_name_ANZSIC",
                            "rme_size_grp",
                            "variable",
                            "value",
                            "unit"},
                    allLines.get(0));
            Assertions.assertEquals(16949, allLines.size());

            //парсинг и проверка XLSX файла
            ZipEntry entryXLSX = zis.getNextEntry();
            XLS xls = new XLS(zis);
            Assertions.assertEquals("Приложение 1 к Олимпийской образовательной программе (каждая группа - от 5 до 25 чел.)",
                    xls.excel.getSheetAt(0).getRow(0).getCell(1).getStringCellValue());


            //парсинг и проверка PDF файла
            ZipEntry entryPDF = zis.getNextEntry();
            PDF pdf = new PDF(zis);
            Assertions.assertTrue(pdf.text.contains("ОТЧЕТ ОБ УСТОЙЧИВОМ РАЗВИТИИ 2018"));
            Assertions.assertTrue(pdf.text.contains("ПАО «ГМК «Норильский никель»"));
            Assertions.assertTrue(pdf.text.contains("E-mail: gmk@nornik.ru"));

        }

    }
}
