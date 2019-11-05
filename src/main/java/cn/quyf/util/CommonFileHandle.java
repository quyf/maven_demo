package cn.quyf.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author quyf
 * @date 2019/1/24 11:34
 */
public class CommonFileHandle {

    public static void main(String[] args) throws Exception {
        String filePath = "e:\\img\\img_600x600.png";
        String destPath = "e:\\img\\img_400x300.png";
        //zoomImage(filePath, destPath, 400, 300);
        filePath = "e:\\img\\pic_01_3326x1536.jpg";
        destPath = "e:\\img\\pic_01_1195x552_1.jpg";
        zoomImage(filePath, destPath, 1195, 552);
        //destPath = "e:\\img\\pic_01_2148x992.jpg";
        //zoomImage(filePath, destPath, 2148, 992);
    }
    /*
     * 图片缩放,w，h为缩放的目标宽度和高度
     * src为源文件目录，dest为缩放后保存目录
     */
    public static void zoomImage(String src,String dest,int w,int h) throws Exception {
        double wr=0,hr=0;
        File srcFile = new File(src);
        File destFile = new File(dest);

        BufferedImage bufImg = ImageIO.read(srcFile); //读取图片
        Image temp = bufImg.getScaledInstance(w, h, BufferedImage.SCALE_SMOOTH );//设置缩放目标图片模板

        wr=w*1.0/bufImg.getWidth();     //获取缩放比例
        hr=h*1.0 / bufImg.getHeight();

        AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
        temp = ato.filter(bufImg, null);
        try {
            ImageIO.write((BufferedImage) temp,dest.substring(dest.lastIndexOf(".")+1), destFile); //写入缩减后的图片
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
