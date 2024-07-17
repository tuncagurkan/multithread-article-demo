package com.article.multithreadbatch.util;


import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class CsvFileGenerateUtility<T> {


    public File generateCsv(List<T> records, Class<T> clazz, String filePath) throws IOException {
        Writer writer = null;
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                try {
                    file.createNewFile();
                    file.setWritable(true);
                    file.setExecutable(true);
                } catch (Exception e) {
                    log.error("An error occurred create file.", e);
                    throw new FileNotFoundException();
                }
            }
            writer = new FileWriter(file.getAbsolutePath());
            writer.append(buildHeader(clazz));
            StatefulBeanToCsv<T> sbc =
                    new StatefulBeanToCsvBuilder<T>(writer).withSeparator(";".toCharArray()[0]).build();
            sbc.write(records);
            return file;
        } catch (Exception e) {
            log.error("Error occurred on write process generateCsv.", e);
            throw new FileNotFoundException();
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    private String buildHeader(Class dtoClass) {
        return Arrays.stream(dtoClass.getDeclaredFields())
                .filter(f -> f.getAnnotation(CsvBindByPosition.class) != null && f.getAnnotation(CsvBindByName.class) != null)
                .sorted(Comparator.comparing(f -> f.getAnnotation(CsvBindByPosition.class).position()))
                .map(f -> f.getAnnotation(CsvBindByName.class).column())
                .collect(Collectors.joining(";")) + "\n";
    }


}
