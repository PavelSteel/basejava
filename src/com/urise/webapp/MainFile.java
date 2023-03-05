package com.urise.webapp;

import java.io.File;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) throws IOException {
        File dir = new File("C:\\Users\\Вероника\\Desktop\\Topjava\\basejava");

        for (File file : dir.listFiles()) {
            if (!file.isDirectory())
                System.out.println(file.getName());
            if (file.isDirectory()) {
                System.out.println(file.getAbsolutePath());
            }
        }
    }

}
