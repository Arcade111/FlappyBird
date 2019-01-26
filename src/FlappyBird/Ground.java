package FlappyBird;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Ground {
	BufferedImage image,backgroundImage;
	public int x,y;//
	public int width,height;
	
	public Ground(int skinSelected) throws IOException {
		backgroundImage = ImageIO.read(getClass().getResource("../img" + skinSelected + "/bg.png"));
		image = ImageIO.read(getClass().getResource("../img"+skinSelected+"/ground.png"));
		width = image.getWidth();
		height = image.getHeight();
		x = 0;
		y =500;
	}//地面构造器结束
	
	//Ground类中添加方法，是地面移动一步
	public void run() {
		x --;
		if(x == -130) {
			x = 0;
		}
	}

}//Ground类结束
