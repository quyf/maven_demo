package cn.quyf.util;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author quyf
 * @date 2019/10/31 9:07
 */
public class VideoFrameDemo {

    /***
     * 根据视频资源路径获取视频资源帧截图
     * @param playPath 网络视频视频路径
     * @param num 第几帧
     * @param localFilePath 本地文件路径
     * @param format png/jpg等格式 
     * @param width 图片的宽
     * @param height 图片的高
     */
    public void getVideoChapterCoverName(String playPath, int num, File localFilePath, String format, int width, int height) throws Exception {
        FFmpegFrameGrabber frameGrabber = FFmpegFrameGrabber.createDefault(playPath);
        frameGrabber.start();

        int i = 0;
        Frame frame = null;

        while (i < num) {
            frame = frameGrabber.grabImage();
            if ((i > num) && (frame.image != null)) {
                break;
            }
            i++;
        }
        // 截取的帧图片
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage srcImage = converter.getBufferedImage(frame);

        BufferedImage thumbnailImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        thumbnailImage.getGraphics().drawImage(srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

        ImageIO.write(thumbnailImage, format, localFilePath);
        frameGrabber.stop();
    }
}
