package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamUtil {
    public static String parseInputStreamToString(InputStream in) throws IOException {
        String temp="";
        int index;
        StringBuffer sb=new StringBuffer();
        BufferedReader br=new BufferedReader(new InputStreamReader(in));
        while ((temp=br.readLine()) != null) {
            if ((index = temp.indexOf("eof")) != -1) {
                sb.append(temp.substring(0, index));
                break;
            }
            sb.append(temp);
        }
        return sb.toString();
    }
}
