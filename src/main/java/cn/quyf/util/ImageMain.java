package cn.quyf.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author quyf
 * @date 2019/1/12 17:10
 */
public class ImageMain {

    public static void main(String[] args) throws Exception {
        String coverUrl = "https://lrtest.lrts.me/7b0554c5300344c3ad2abb06fad7b2e3_600x600.jpg";
        String filePath = "e:\\hello32.png";
        //FileOutputStream fos = new FileOutputStream(new File(filePath));
        //IOUtils.copy(, fos);
        //download(coverUrl,"hello3.png", "e:\\");
        System.out.println(getCorpUrl("https://lrtest.lrts.me/44af1478eefd4c69a0ff5fd82afa95ca_600x600.jpg","300x300"));
        
    }

    private static String getCorpUrl(String coverNetworkUrl, String picSize) {
        String[] wXh = picSize.split("x");

        int srcWidth = Integer.parseInt(wXh[0]);
        int srcHeight = Integer.parseInt(wXh[1]);

        StringBuilder cmdKey = new StringBuilder(coverNetworkUrl);
        /**普通图片封面裁决类型方案*/
        if( srcWidth >0 && srcWidth >= srcHeight){
            //图片宽大于高度，则需要定高等比缩放后裁剪为指定大小
            cmdKey.append("?imageMogr/v2/auto-orient/thumbnail/x" + srcHeight + "/crop/!" + srcWidth + "x" + srcHeight + "a0a0");
        }else{
            //图片宽小于高度，则需要定宽等比缩放后裁剪为指定大小
            cmdKey.append("?imageMogr/v2/auto-orient/thumbnail/" + srcWidth + "/crop/!" + srcWidth + "x" + srcHeight + "a0a0");
        }
        cmdKey.append("&"+System.currentTimeMillis());
        return cmdKey.toString();
    }
    
    public static void download(String coverUrl, String fileName, String savePath) {
        URL url = null;
        OutputStream os = null;
        URLConnection con = null;
        InputStream is = null;
        try {
            url = new URL(coverUrl);
            con = url.openConnection();
            con.setConnectTimeout(5 * 1000);
            is = con.getInputStream();
            byte[] bs = new byte[1024];
            int len;
            File sf = new File(savePath);
            if (!sf.exists()) {
                sf.mkdirs();
            }
            os = new FileOutputStream(sf.getPath() + "\\" + fileName);
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if(os != null)
                    os.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                if(is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
