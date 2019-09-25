package com.javaer.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MyCreate implements Runnable{

    String path = "/Users/javaer/StudioProjects/PlusBoyLoader/proguard.txt";

    private static List<String> strings = new LinkedList<>();

    private static Set<String> results = new HashSet<>();

    @Override
    public void run() {
        File file = new File(path);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String str;
            while ((str = br.readLine()) != null){
                strings.add(str);
                System.out.println("===>"+str);
            }
            br.close();

            createStr(strings);

        }catch (Throwable throwable){
            throwable.printStackTrace();
        }

    }

    public static void combination(char[] cs) {
        int len = 12;
        boolean[] choosed = new boolean[len];
        char[] cache = new char[len];
        int result = len;
        int index = 0;
        while(true) {
            index = 0;
            while (choosed[index]) {
                choosed[index] = false;
                ++result;
                if (++index == len) {
                    return;
                }
            }
            choosed[index] = true;
            cache[--result] = cs[index];

            results.add(new String(cache).substring(result));
            System.out.println("长度:" + results.size());

        }


    }

    public static void create(char[] chars){


    }

    private static void createStr(List<String> set){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < set.size(); i++){
            for (int j = 0; j < set.size(); j++){
                String s = set.get(i) + set.get(j);
                results.add(s);
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream("proguard.txt");
            for (String s : strings){
                fos.write(s.getBytes());
                fos.write('\n');
            }
            for (String string : results){
                fos.write(string.getBytes());
                fos.write('\n');
            }
            fos.flush();
            fos.close();

            System.out.println("最终结果===>"+results.size());
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public static void start(){
        new Thread(new MyCreate()).start();
    }
}
