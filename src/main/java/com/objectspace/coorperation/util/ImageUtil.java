package com.objectspace.coorperation.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
/**
* @Description: 图片处理工具，基于Thumbnail
* @Author: NoCortY
* @Date: 2019/10/4
*/
public class ImageUtil {
    private static final String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();
    /**
     * @Description: 生成用户头像（暂未使用此方法）
     * @Param:  [thumbnail文件,targetAddr目标地址]
     * @return: 文件地址
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    public static String generateUserProfile(CommonsMultipartFile thumbnail, String targetAddr) throws IOException {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        Thumbnails.of(thumbnail.getInputStream()).size(512, 512).outputQuality(0.7).toFile(dest);
        return relativeAddr;
    }


    /**
     * @Description: 生成带水印的图片文件
     * @Param: [thumbnail, targetAddr]
     * @return: java.lang.String 路径
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    public static String generateInfomationDescImg(CommonsMultipartFile thumbnail, String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnail.getInputStream()).size(790, 866)
                    .watermark(Positions.CENTER, ImageIO.read(new File(basePath + "/WaterMark.png")), 0.5f)
                    .outputQuality(0.8).toFile(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return relativeAddr;
    }

    /**
     * @Description:  创建目标路径上所涉及到的目录 /home/work/ 一路自动创建
     * @Param: [targetAddr]
     * @return: void
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    private static void makeDirPath(String targetAddr) {
        // TODO Auto-generated method stub
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }


    /**
     * @Description:  获取输入文件流的扩展名
     * @Param: [thumbnail]
     * @return: java.lang.String
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    private static String getFileExtension(CommonsMultipartFile thumbnail) {
        // TODO Auto-generated method stub
        String originalFileName = thumbnail.getOriginalFilename();
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }
    /**
     * @Description: 生成随机文件名,当前年月日小时分钟秒钟+五位随机数
     * @Param: []
     * @return: java.lang.String
     * @Author: NoCortY
     * @Date: 2019/10/4
     */
    private static String getRandomFileName() {
        // TODO Auto-generated method stub
        // 获取随机五位数
        int rannum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }
}
