package com.ballfish.utility;

import java.io.File;
import java.util.Scanner;

public class Reader {
    public static String Read(String path) {
        String result = "";
        if (!Checker.IsExist(path) || !Checker.CanRead(path)) {
            return null;
        }
        try {
            File f = new File(path);
            Scanner input = new Scanner(f);
            while (input.hasNext()) {
                result += input.nextLine();
            }
            input.close();
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    public static String Read(FileInfo fileInfo) {
        return Read(fileInfo.path);
    }
}
