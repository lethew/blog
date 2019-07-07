package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author <a href="http://greeks.studio">吴昭</a>
 * @version blog@2019/6/24 23:34
 */
public class Test {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.baidu.com");
        for (int i = 0; i < 100; i++) {
            InputStream inputStream = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            inputStream.close();
        }
    }

    public void test() throws IOException{
        URL url = new URL("http://www.baidu.com");
        for (int i = 0; i < 100; i++) {
            InputStream inputStream = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            inputStream.close();
        }
    }
}
