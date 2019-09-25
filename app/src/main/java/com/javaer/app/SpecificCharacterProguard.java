package com.javaer.app;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class SpecificCharacterProguard {

    private char[] chars;
    private String fileName;
    private int len = 10000;

    private List<String> list = new ArrayList<>();


    /**
     * @param fileName 文件名
     * @param chars    混淆字符
     */
    public SpecificCharacterProguard(String fileName, char... chars) {
        this.chars = chars;
        this.fileName = fileName;
        len = Math.max(8, (int) (Math.log(10000) / Math.log(chars.length) * 1.5));
        System.out.println("最大长度：" + len);
    }

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                StringBuilder builder = new StringBuilder();
//                int length = 0;
//                for (char i = 0600; i < '\u06ff'; i++){
//                    builder.append(i).append(" ");
//                    length += 1;
//
//                }
//                System.out.print("长度" + length + "===>" + builder.toString());

                File file = new File(fileName);
                if (file.exists()){
                    file.delete();
                    System.out.println("文件已存在,先删除");
                }

                for (int i = 0; i < 3000; i++) {
                    int len = (int) (2 + Math.random() * 4);
                    StringBuilder k = new StringBuilder();
                    for (int i1 = 0; i1 < len; i1++) {
                        k.append(getRadomChar(i1 == 0));
                    }
                    String s = k.toString();
                    if (list.contains(s)) {
                        i--;
                        continue;
                    }
                    list.add(s);
                    FileUtils.appendFile(fileName, s);
                }
            }
        }).start();
    }

    private char getRadomChar(boolean firstChar) {
        char aChar = chars[(int) (Math.random() * chars.length)];
        if (firstChar && (aChar >= '0' && aChar <= '9')) {
            return getRadomChar(firstChar);
        }
        return aChar;
    }
}
