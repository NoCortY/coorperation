package com.objectspace.coorperation.util;

/**
 * @author NoCortY 路径工具 依据不同的操作系统，不同的环境 获取不同的路径
 */
public class PathUtil {
    //获取不同系统的路径分隔符
    private static String seperator = System.getProperty("file.separator");
    public static String getImgBasePath() {
        // 获取操作系统名
        String os = System.getProperty("os.name");
        String basePath="";
        if(os.toLowerCase().startsWith("win")) {
            basePath = "G:/Program/gitHub/Coorperation/FileRepository/images";
        }else {
            basePath="/home/DataRepository/images";
        }
        basePath = basePath.replace("/", seperator);
        return basePath;
    }
    /**
     * 	获取头像路径
     * @param userId
     * @return 路径
     */
    public static String getUserProfilePath(Integer userId) {
        String imagePath="/userprofile/"+userId+"/";
        return imagePath.replace("/", seperator);
    }

    /**
     * 	获取供需信息详情图路径
     * @param infomationId
     * @return 供需信息详情图路径
     */
    public static String getInfomationImgPath(Integer infomationId) {
        String imagePath = "/infomationimg/"+infomationId+"/";
        return imagePath.replace("/", seperator);
    }
}
