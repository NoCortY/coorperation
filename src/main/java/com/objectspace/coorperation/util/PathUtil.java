package com.objectspace.coorperation.util;

/**
 * @author NoCortY
 */
/**
* @Description: 路径工具 依据不同的操作系统，不同的环境 获取不同的路径
* @Author: NoCortY
* @Date: 2019/10/4
*/
public class PathUtil {
    //获取不同系统的路径分隔符
    private static String seperator = System.getProperty("file.separator");

    /**
     * @Description:  获取基础路径
     * @Param: []
     * @return: java.lang.String
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    public static String getImgBasePath() {
        // 获取操作系统名
        String os = System.getProperty("os.name");
        String basePath="";
        if(os.toLowerCase().startsWith("win")) {
            //PC路径
            //basePath = "G:/";
            //平板路径
            basePath = "C:/Users/nocor/Desktop/coorperationImg/";
        }else {
            basePath="/WorkSpace/Data/coorperationImg/";
        }
        basePath = basePath.replace("/", seperator);
        return basePath;
    }

    /**
     * @Description:  获取头像路径
     * @Param: [userId]
     * @return: java.lang.String
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    public static String getUserProfilePath(Integer userId) {
        String imagePath="/userprofile/"+userId+"/";
        return imagePath.replace("/", seperator);
    }

    public static String getInformationImgPath(Integer informationId){
        String imagePath = "/informationImg/"+informationId+"/";
        return imagePath.replace("/",seperator);
    }
    /**
     * @Description:  获取供需信息详情图路径
     * @Param: [infomationId]
     * @return: java.lang.String
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    public static String getInformationDescImgPath(Integer infomationId) {
        String imagePath = "/informationDescImg/"+infomationId+"/";
        return imagePath.replace("/", seperator);
    }
}
