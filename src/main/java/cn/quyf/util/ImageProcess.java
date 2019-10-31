package cn.quyf.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.*;
import java.util.Iterator;

public class ImageProcess {
	
	private static Log logs = LogFactory.getLog(ImageProcess.class);
	
	public static final String TMP_FILE_DIR = "e:\\img\\tmp\\";

	public static void main(String[] args) {
		try {
            String filePath = "e:\\img\\tmp\\pic_01_3326x1536.jpg";
            String destFile = "e:\\img\\tmp\\pic_01_1195x552_xxxxxx.jpg";
          ImageProcess.resize(new File(filePath), destFile, "1195x552");
            //ip = new ImageProcess("pic_01_2148x992.jpg");
           //ip.resize(TMP_FILE_DIR+filePath, 2148, 992);
           //ip.cut(241, 38, 180, 180);
           //System.out.println(ip.upload());
            System.out.println("ok");
		} catch (Exception e) {
			logs.error("系统异常", e);
		}
	}
	
	public ImageProcess(){
	}

    public static void resize(File srcFile, String destFile, String picSize) throws IOException {
        String[] wXh = picSize.split("x");

        int srcWidth = Integer.parseInt(wXh[0]);
        int srcHeight = Integer.parseInt(wXh[1]);
        resize(srcFile, destFile, srcWidth, srcHeight);
    }
    
    public static void resize(File srcFile, String destFile, int scaleWidth, int scaleHeight) throws IOException {
        //检查临时文件是否存在，不存在则创建
        File file = new File(TMP_FILE_DIR);
        if( !file.exists()){
            file.mkdirs();
        }

        BufferedImage image1 = ImageIO.read(srcFile);
        ImageScale is = new ImageScale();
        BufferedImage image2 = is.imageZoomOut(image1, scaleWidth, scaleHeight,false);

        FileOutputStream out = new FileOutputStream(destFile);
        ImageIO.write(image2, "jpg", out);
        out.close();
    }
    
	public void cut(String destFile, String subPath, int cutX1, int cutY1, int cutX2, int cutY2) throws IOException {
		ImageCut o = new ImageCut(destFile, cutX1,cutY1, cutX2, cutY2);
		o.setImageType("jpg");
		o.setSubpath(subPath);
		o.cut();
	}	
	
	public static class HeadPicFormatBean {
	    private Integer width;
	    private Integer height;
	    private Integer x;
	    private Integer y;
	    private Integer cutWidth;
	    private Integer cutHeight;

	    public Integer getWidth() {
	        return width;
	    }

	    public void setWidth(Integer width) {
	        this.width = width;
	    }

	    public Integer getHeight() {
	        return height;
	    }

	    public void setHeight(Integer height) {
	        this.height = height;
	    }

	    public Integer getX() {
	        return x;
	    }

	    public void setX(Integer x) {
	        this.x = x;
	    }

	    public Integer getY() {
	        return y;
	    }

	    public void setY(Integer y) {
	        this.y = y;
	    }

	    public Integer getCutWidth() {
	        return cutWidth;
	    }

	    public void setCutWidth(Integer cutWidth) {
	        this.cutWidth = cutWidth;
	    }

	    public Integer getCutHeight() {
	        return cutHeight;
	    }

	    public void setCutHeight(Integer cutHeight) {
	        this.cutHeight = cutHeight;
	    }
	}
	
}

class ImageScale {
	private static final Log logs = LogFactory.getLog(ImageScale.class);
    private int width;
    private int height;
    private int scaleWidth;
    double support = (double) 3.0;
    double[] contrib;
    double[] normContrib;
    double[] tmpContrib;
    int startContrib, stopContrib;
    int nDots;
    int nHalfDots;

    public BufferedImage imageZoomOut(BufferedImage srcBufferImage, int w, int h, boolean lockScale) {
        width = srcBufferImage.getWidth();
        height = srcBufferImage.getHeight();
        scaleWidth = w;
        if (lockScale) {
            h = w * height / width;
        }

        if (DetermineResultSize(w, h) == 1) {
            return srcBufferImage;
        }
        CalContrib();
        BufferedImage pbOut = HorizontalFiltering(srcBufferImage, w);
        BufferedImage pbFinalOut = VerticalFiltering(pbOut, h);
        return pbFinalOut;
    }

    /**
     * 决定图像尺寸
     */
    private int DetermineResultSize(int w, int h) {
        double scaleH, scaleV;
        scaleH = (double) w / (double) width;
        scaleV = (double) h / (double) height;
        // 需要判断一下scaleH，scaleV，不做放大操作
        if (scaleH >= 1.0 && scaleV >= 1.0) {
            return 1;
        }
        return 0;

    } // end of DetermineResultSize()

    private double Lanczos(int i, int inWidth, int outWidth, double Support) {
        double x;

        x = (double) i * (double) outWidth / (double) inWidth;

        return Math.sin(x * Math.PI) / (x * Math.PI) * Math.sin(x * Math.PI / Support) / (x * Math.PI / Support);

    } 

    //
    // Assumption: same horizontal and vertical scaling factor
    //
    private void CalContrib() {
        nHalfDots = (int) ((double) width * support / (double) scaleWidth);
        nDots = nHalfDots * 2 + 1;
        try {
            contrib = new double[nDots];
            normContrib = new double[nDots];
            tmpContrib = new double[nDots];
        } catch (Exception e) {
        	logs.error("init contrib,normContrib,tmpContrib.errorMsg=" + e.getMessage(), e);
        }

        int center = nHalfDots;
        contrib[center] = 1.0;

        double weight = 0.0;
        int i = 0;
        for (i = 1; i <= center; i++) {
            contrib[center + i] = Lanczos(i, width, scaleWidth, support);
            weight += contrib[center + i];
        }

        for (i = center - 1; i >= 0; i--) {
            contrib[i] = contrib[center * 2 - i];
        }

        weight = weight * 2 + 1.0;

        for (i = 0; i <= center; i++) {
            normContrib[i] = contrib[i] / weight;
        }

        for (i = center + 1; i < nDots; i++) {
            normContrib[i] = normContrib[center * 2 - i];
        }
    } // end of CalContrib()

    // 处理边缘
    private void CalTempContrib(int start, int stop) {
        double weight = 0;

        int i = 0;
        for (i = start; i <= stop; i++) {
            weight += contrib[i];
        }

        for (i = start; i <= stop; i++) {
            tmpContrib[i] = contrib[i] / weight;
        }

    } // end of CalTempContrib()

    private int GetRedValue(int rgbValue) {
        int temp = rgbValue & 0x00ff0000;
        return temp >> 16;
    }

    private int GetGreenValue(int rgbValue) {
        int temp = rgbValue & 0x0000ff00;
        return temp >> 8;
    }

    private int GetBlueValue(int rgbValue) {
        return rgbValue & 0x000000ff;
    }

    private int ComRGB(int redValue, int greenValue, int blueValue) {

        return (redValue << 16) + (greenValue << 8) + blueValue;
    }

    // 行水平滤波
    private int HorizontalFilter(BufferedImage bufImg, int startX, int stopX, int start, int stop, int y,
            double[] pContrib) {
        double valueRed = 0.0;
        double valueGreen = 0.0;
        double valueBlue = 0.0;
        int valueRGB = 0;
        int i, j;

        for (i = startX, j = start; i <= stopX; i++, j++) {
            valueRGB = bufImg.getRGB(i, y);

            valueRed += GetRedValue(valueRGB) * pContrib[j];
            valueGreen += GetGreenValue(valueRGB) * pContrib[j];
            valueBlue += GetBlueValue(valueRGB) * pContrib[j];
        }

        valueRGB = ComRGB(Clip((int) valueRed), Clip((int) valueGreen), Clip((int) valueBlue));
        return valueRGB;

    } // end of HorizontalFilter()

    // 图片水平滤波
    private BufferedImage HorizontalFiltering(BufferedImage bufImage, int iOutW) {
        int dwInW = bufImage.getWidth();
        int dwInH = bufImage.getHeight();
        int value = 0;
        BufferedImage pbOut = new BufferedImage(iOutW, dwInH, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < iOutW; x++) {

            int startX;
            int start;
            int X = (int) (((double) x) * ((double) dwInW) / ((double) iOutW) + 0.5);
            int y = 0;

            startX = X - nHalfDots;
            if (startX < 0) {
                startX = 0;
                start = nHalfDots - X;
            } else {
                start = 0;
            }

            int stop;
            int stopX = X + nHalfDots;
            if (stopX > (dwInW - 1)) {
                stopX = dwInW - 1;
                stop = nHalfDots + (dwInW - 1 - X);
            } else {
                stop = nHalfDots * 2;
            }

            if (start > 0 || stop < nDots - 1) {
                CalTempContrib(start, stop);
                for (y = 0; y < dwInH; y++) {
                    value = HorizontalFilter(bufImage, startX, stopX, start, stop, y, tmpContrib);
                    pbOut.setRGB(x, y, value);
                }
            } else {
                for (y = 0; y < dwInH; y++) {
                    value = HorizontalFilter(bufImage, startX, stopX, start, stop, y, normContrib);
                    pbOut.setRGB(x, y, value);
                }
            }
        }

        return pbOut;

    } // end of HorizontalFiltering()

    private int VerticalFilter(BufferedImage pbInImage, int startY, int stopY, int start, int stop, int x,
            double[] pContrib) {
        double valueRed = 0.0;
        double valueGreen = 0.0;
        double valueBlue = 0.0;
        int valueRGB = 0;
        int i, j;

        for (i = startY, j = start; i <= stopY; i++, j++) {
            valueRGB = pbInImage.getRGB(x, i);

            valueRed += GetRedValue(valueRGB) * pContrib[j];
            valueGreen += GetGreenValue(valueRGB) * pContrib[j];
            valueBlue += GetBlueValue(valueRGB) * pContrib[j];
        }

        valueRGB = ComRGB(Clip((int) valueRed), Clip((int) valueGreen), Clip((int) valueBlue));
        // System.out.println(valueRGB);
        return valueRGB;

    } // end of VerticalFilter()

    private BufferedImage VerticalFiltering(BufferedImage pbImage, int iOutH) {
        int iW = pbImage.getWidth();
        int iH = pbImage.getHeight();
        int value = 0;
        BufferedImage pbOut = new BufferedImage(iW, iOutH, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < iOutH; y++) {

            int startY;
            int start;
            int Y = (int) (((double) y) * ((double) iH) / ((double) iOutH) + 0.5);

            startY = Y - nHalfDots;
            if (startY < 0) {
                startY = 0;
                start = nHalfDots - Y;
            } else {
                start = 0;
            }

            int stop;
            int stopY = Y + nHalfDots;
            if (stopY > (int) (iH - 1)) {
                stopY = iH - 1;
                stop = nHalfDots + (iH - 1 - Y);
            } else {
                stop = nHalfDots * 2;
            }

            if (start > 0 || stop < nDots - 1) {
                CalTempContrib(start, stop);
                for (int x = 0; x < iW; x++) {
                    value = VerticalFilter(pbImage, startY, stopY, start, stop, x, tmpContrib);
                    pbOut.setRGB(x, y, value);
                }
            } else {
                for (int x = 0; x < iW; x++) {
                    value = VerticalFilter(pbImage, startY, stopY, start, stop, x, normContrib);
                    pbOut.setRGB(x, y, value);
                }
            }

        }

        return pbOut;

    } // end of VerticalFiltering()

    int Clip(int x) {
        if (x < 0)
            return 0;
        if (x > 255)
            return 255;
        return x;
    }
}


class ImageCut {
	private String srcpath;
	private String subpath;
	private String imageType;
	private int x;
	private int y;
	private int width;
	private int height;

	public ImageCut(String srcpath, int x, int y, int width, int height) {
		this.srcpath = srcpath;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * 生成缩略图 fromFileStr:原图片路径 saveToFileStr:缩略图路径 width:缩略图的宽 height:缩略图的高
	 */
	public static void saveImageAsJpg(String fromFileStr, String saveToFileStr,
			int width, int height, boolean equalProportion) throws Exception {
		BufferedImage srcImage;
		String imgType = "JPEG";
		if (fromFileStr.toLowerCase().endsWith(".png")) {
			imgType = "PNG";
		}
		File fromFile = new File(fromFileStr);
		File saveFile = new File(saveToFileStr);
		srcImage = ImageIO.read(fromFile);
		if (width > 0 || height > 0) {
			srcImage = resize(srcImage, width, height, equalProportion);
		}
		ImageIO.write(srcImage, imgType, saveFile);
	}

	/**
	 * 将原图片的BufferedImage对象生成缩略图 source：原图片的BufferedImage对象 targetW:缩略图的宽
	 * targetH:缩略图的高
	 */
	public static BufferedImage resize(BufferedImage source, int targetW,
			int targetH, boolean equalProportion) {
		int type = source.getType();
		BufferedImage target = null;
		double sx = (double) targetW / source.getWidth();
		double sy = (double) targetH / source.getHeight();
		// 这里想实现在targetW，targetH范围内实现等比例的缩放
		// 如果不需要等比例的缩放则下面的if else语句注释调即可
		if (equalProportion) {
			if (sx > sy) {
				sx = sy;
				targetW = (int) (sx * source.getWidth());
			} else {
				sy = sx;
				targetH = (int) (sx * source.getHeight());
			}
		}
		if (type == BufferedImage.TYPE_CUSTOM) {
			ColorModel cm = source.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(targetW,
					targetH);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else {
			target = new BufferedImage(targetW, targetH, type);
			Graphics2D g = target.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_RENDERING,
					RenderingHints.VALUE_RENDER_QUALITY);
			g.drawRenderedImage(source,
					AffineTransform.getScaleInstance(sx, sy));
			g.dispose();
		}
		return target;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getSrcpath() {
		return srcpath;
	}

	public void setSrcpath(String srcpath) {
		this.srcpath = srcpath;
	}

	public String getSubpath() {
		return subpath;
	}

	public void setSubpath(String subpath) {
		this.subpath = subpath;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public void cut() throws IOException {

		FileInputStream is = null;
		ImageInputStream iis = null;
		ByteArrayOutputStream out = null;

		try {
			is = new FileInputStream(srcpath);

			Iterator<ImageReader> it = ImageIO
					.getImageReadersByFormatName(this.imageType);
			ImageReader reader = it.next();

			iis = ImageIO.createImageInputStream(is);
			reader.setInput(iis, true);
			ImageReadParam param = reader.getDefaultReadParam();

			Rectangle rect = new Rectangle(x, y, width, height);

			param.setSourceRegion(rect);

			BufferedImage bi = reader.read(0, param);

			out = new ByteArrayOutputStream();
			ImageIO.write(bi, this.imageType, out);
			
		} finally {
			if (is != null)
				is.close();
			if (iis != null)
				iis.close();
			IOUtils.closeQuietly(out);
		}

	}
	
}