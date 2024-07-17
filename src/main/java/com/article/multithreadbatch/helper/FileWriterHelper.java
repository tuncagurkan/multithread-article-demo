package com.article.multithreadbatch.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;

@Component
@Slf4j
public abstract class FileWriterHelper {
    @Value("${output.directoryPath}")
    private static String outputDirectoryPath;

    public static String getFileName() {
        return outputDirectoryPath + "/" + "multithread-demo_" + LocalDate.now() + ".csv";
    }

    public static void deleteFileIfExist(String fileName) {
        String outputFilePath = outputDirectoryPath + "/" + fileName;
        File file = new File(outputFilePath);
        if (file.exists()) {
            file.delete();
            log.info("deleted existing file. Filename:{}", fileName);
        }
    }
}
