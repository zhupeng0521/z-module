package com.module.zhupeng.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ReadByLine
 * <p>date : 2019-12-16</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
class ReadByLine {
    private String fileName;
    private List<String> arrayList = new ArrayList<>();

    // 初始化
    public ReadByLine(String fileName) {
        this.fileName = fileName;
    }

    public List<String> toArray() {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            File file = new File(fileName);
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String str;
            while ((str = br.readLine()) != null) {
                arrayList.add(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeFileReader(fr);
            closeBufferedReader(br);
        }

        return arrayList;
    }

    private void closeBufferedReader(BufferedReader fileReader) {
        try {
            if (null != fileReader) {
                fileReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeFileReader(FileReader fileReader) {
        try {
            if (null != fileReader) {
                fileReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
