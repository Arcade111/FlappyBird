package FlappyBird;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Column {
	BufferedImage image;
	public int x, y;// 长、宽
	public int width, height;// 宽、高
	public int gap;// 柱子间的缝隙
	public int distance;// 距离，两根柱子间的距离
	Random random = new Random();
	public int skinSelected;

	// 构造器：初始化数据，n代表第几个柱子
	public Column(int n,int s) throws IOException {
		image = ImageIO.read(getClass().getResource("../img"+s+"/column.png"));
		width = image.getWidth();
		height = image.getHeight();
		// 实际间隙是固定好了的
		gap = 144;
		// 距离
		distance = 245;
		// 两个柱子相距795
		x = 550 + (n - 1) * distance;
		// 柱子上下浮动，
		y = random.nextInt(218) + 132;
//		y = (int)(Math.random() * 218 +250);
	}// 构造器结束

	// Cloumn类中添加方法step
	public void run() {
		x--;
		if (x == -width / 2) {
			//-width/2,使柱子移动不那么突兀
			x = distance * 2 - width / 2;
			y = random.nextInt(218) + 132;
		}
	}// step方法结束
}// Column类结束
