package cn.foreversong.snail.sf.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/19
 * Time: 7:42
 * Description: 流操作工具
 */
public final class StreamUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamUtil.class);

    /**
     * 从流中获取字符串
     * @param is 输入流
     * @return 字符串
     */
    public static  String getString(InputStream is){
        StringBuffer sb = new StringBuffer();
        try{
            BufferedReader reader  = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
        } catch (Exception e) {
            LOGGER.error("get string failure",e);
            throw new RuntimeException();
        }
        return sb.toString();
    }

    /**
     * 将输入流复制到输出流
     * @param inputStream 输入流
     * @param outputStream 输出流
     */
    public static void copyStream(InputStream inputStream, OutputStream outputStream){
        try{
            int length;
            byte[] buffer = new byte[4*1024];
            while ((length = inputStream.read(buffer,0, buffer.length)) != -1){
                outputStream.write(buffer,0,length);
            }
        } catch (IOException e) {
            LOGGER.error("copy stream failure",e);
            throw new RuntimeException(e);
        }finally {
            try{
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                LOGGER.error("close stream failure",e);
            }
        }
    }


}