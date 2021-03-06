package cn.edu.nju.software.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by xmc1993 on 2017/5/12.
 */
public final class UploadFileUtil {
    private static final Logger logger = LoggerFactory.getLogger(UploadFileUtil.class);

    //资源服务器的base url
    public static final String SOURCE_BASE_URL = "http://120.27.219.173/source";

    public static final String URL_BASE = "/source";

    public static final String DIR_BASE = "/data";


    private UploadFileUtil() {
    }

    /**
     * 获得服务器上存储项目文件的根目录
     *
     * @return
     */
    public static String getBaseUrl() {
        return System.getenv().get("STORY_HOME") != null ? System.getenv().get("STORY_HOME") : "/data";
    }

    /**
     * 获取文件的后缀
     *
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName) {
        if (StringUtil.isEmpty(fileName)) {
            return null;
        }
        String res = fileName.substring(fileName.lastIndexOf(".") + 1);
        return res;
    }


    /**
     * 将action下的临时文件拷贝到项目文件目录下
     *
     * @param uploadFile
     * @param desPath
     * @param fileName
     * @return
     */
    public static boolean mvFile(MultipartFile uploadFile, String desPath, String fileName) {
        File desFile = new File(new File(desPath), fileName);// 根据parent抽象路径名和child路径名字符串创建一个新File实例。
        if (!desFile.getParentFile().exists()) {// 判断路径"/image/#{businessId}"是否存在
            desFile.getParentFile().mkdirs();
        }
        try {
            if (!desFile.exists()) {
                desFile.createNewFile();
            }
            uploadFile.transferTo(desFile);
            desFile.setExecutable(true, false);
            desFile.setReadable(true, false);
            desFile.setWritable(true, false);
        } catch (IOException e) {
            logger.error("文件上传失败！");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 删除服务器上的文件
     *
     * @return
     */
    public static boolean deleteFile(String fileUri) {
        if (StringUtil.isEmpty(fileUri)) return true;
        File file = new File(fileUri);
        if (file.exists()) {
            return file.delete();
        }
        return true;
    }


    public static String getRealPathFromUrl(String url) {
        if (StringUtil.isEmpty(url)) return null;
        int index = url.indexOf(URL_BASE);
        String result = url.substring(index + URL_BASE.length());
        result = DIR_BASE + result;
        return result;
    }


    /**
     * 根据数据库中的url删除服务器中的文件
     *
     * @param url
     * @return
     */
    public static boolean deleteFileByUrl(String url) {
        if (StringUtil.isEmpty(url)) return true;
        String fileUri = getRealPathFromUrl(url);
        return deleteFile(fileUri);
    }

    public static void main(String[] args) {
        String result = getRealPathFromUrl("http://120.27.219.173/source/cover/XKpa5J5vOj9rQVnn.jpg");
        System.out.println(result);
    }


}
