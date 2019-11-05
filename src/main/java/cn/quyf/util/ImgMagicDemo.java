package cn.quyf.util;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;

import java.io.IOException;

/**
 * 需要再本地安装imagemagick
 * @author quyf
 * @date 2019/1/24 10:52
 */
public class ImgMagicDemo {
    public static void main(String[] args) throws InterruptedException, IOException, IM4JavaException {
        ConvertCmd cmd = new ConvertCmd();
        IMOperation opt = new IMOperation();
        //opt.addImage("e:\\img_600x600.png");
        opt.resize(300,300);
        //opt.addImage("e:\\img_300x300.png");
        cmd.run(opt,"e:\\img_600x600.png","e:\\img_300x300.png");
    }
}
