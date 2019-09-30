package com.objectspace.coorperation.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 图片处理工具
 *
 * @author NoCortY
 */
public class ImageUtil {
    private static final String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();

    public static String generateUserProfile(CommonsMultipartFile thumbnail, String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnail.getInputStream()).size(700, 700).outputQuality(0.7).toFile(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return relativeAddr;
    }

    /**
     *	生成图片文件
     * @param thumbnail 图片文件
     * @param targetAddr 相对地址
     * @return 图片存储路径
     */
    public static String generateInfomationImg(CommonsMultipartFile thumbnail, String targetAddr) {
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
     *	创建目标路径上所涉及到的目录 /home/work/ 一路自动创建
     *
     * @param targetAddr
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
     * 	 获取输入文件流的扩展名
     *
     * @param thumbnail
     * @return
     */
    private static String getFileExtension(CommonsMultipartFile thumbnail) {
        // TODO Auto-generated method stub
        String originalFileName = thumbnail.getOriginalFilename();
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    /**
     * 生成随机文件名,当前年月日小时分钟秒钟+五位随机数
     *
     * @return 随机名
     */
    private static String getRandomFileName() {
        // TODO Auto-generated method stub
        // 获取随机五位数
        int rannum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }
}
