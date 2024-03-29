package com.urise.webapp;

import java.io.File;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) throws IOException {
        File dir = new File("C:\\Users\\Вероника\\Desktop\\Topjava\\basejava");

        deepPrintDirectory(dir, "");
    }

    //Вывод всех файлов и папок в директории
    public static void deepPrintDirectory(File dir, String indents) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println(indents + "File: " + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println(indents + "Directory: " + file.getName());
                    deepPrintDirectory(file, indents + " ");
                }
            }
        }
    }

}
