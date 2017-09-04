package hemi.xmu.jobs;


import java.io.*;

/**
 * Created by Vanguard on 2017/4/10.
 */
public class Util {
    // 工具类中的方法都是静态方式访问的因此将构造器私有不允许创建对象——好习惯
    private Util() {
        //throw new AssertionError();
    }

    /**
     * 实现文件复制
     *
     * @param source
     * @param target
     * @throws IOException
     */
    public static void fileCopy(String source, String target) throws IOException {
        try (InputStream in = new FileInputStream(source)) {
            try (OutputStream out = new FileOutputStream(target)) {
                byte[] buffer = new byte[4096];
                int bytesToRead;
                while ((bytesToRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesToRead);
                }
            }
        }
    }

    /**
     * 统计给定文件中给定字符串的出现次数
     * @param source
     * @param word
     * @return
     */
    public static int countWordInFile(String source, String word) {
        int counter = 0;
        try (FileReader fr = new FileReader(source)) {
            try (BufferedReader br = new BufferedReader(fr)) {
                String line;
                while ((line = br.readLine()) != null) {
                    int index;
                    while (line.length() >= word.length() && (index = line.indexOf(word)) >= 0) {
                        counter++;
                        line = line.substring(index + word.length());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return counter;
    }

}
