package com.cyzy.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

// 生成  验证码  和  验证码图片  的类
public final class ImageUtil {
	
	private static final char[] chars = { '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J',
            'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'
    };
	
	private static final int SIZE = 4;      // 验证码图片中文字的个数
	private static final int LINES = 3;     // 验证码图片中的干扰线的数量
	private static final int WIDTH = 80;    // 验证码图片的宽度
	private static final int HEIGHT = 34;   // 验证码图片的高度
	private static final int FONT_SIZE = 19;// 验证码图片中文字的大小

	/**
	 * 生成验证码和验证码图片的方法，并封装在Map中。 
	 * 
	 * 其中Map的key是验证码，Map的value是验证码图片。
	 */
	public static Map<String, BufferedImage> createImage() {
		
		StringBuffer sb = new StringBuffer();
		
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		Graphics graphic = image.getGraphics();
		graphic.setColor(Color.LIGHT_GRAY);  
		graphic.fillRect(0, 0, WIDTH, HEIGHT);
		Random ran = new Random();
		// 画随机字符
		for (int i = 1; i <= SIZE; i++) {
			int r = ran.nextInt(chars.length);
			graphic.setColor(getRandomColor());
			graphic.setFont(new Font(null, Font.BOLD + Font.ITALIC, FONT_SIZE));
			graphic.drawString(chars[r] + "", (i - 1) * WIDTH / SIZE,
					HEIGHT / 2);
			sb.append(chars[r]);// 将字符保存，存入Session
		}
		// 画干扰线
		for (int i = 1; i <= LINES; i++) {
			graphic.setColor(getRandomColor());
			graphic.drawLine(ran.nextInt(WIDTH), ran.nextInt(HEIGHT), ran
					.nextInt(WIDTH), ran.nextInt(HEIGHT));
		}
		Map<String, BufferedImage> map = new HashMap<String, BufferedImage>();
		map.put(sb.toString(), image);
		return map;
	}

	
	
	/**
	 * 将图片传入输入流的方法
	 */
	public static InputStream getInputStream(BufferedImage image)
			throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
		encoder.encode(image);
		byte[] imageBts = bos.toByteArray();
		InputStream in = new ByteArrayInputStream(imageBts);
		return in;
	}

	
	// 产生随机颜色的方法
	private static Color getRandomColor() {
		
		Random ran = new Random();   // 随机函数
		// 产生随机的RGB颜色
		Color color = new Color(ran.nextInt(256), ran.nextInt(256), ran
				.nextInt(256));
		return color;
	}
}