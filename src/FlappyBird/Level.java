package FlappyBird;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Level {
	public static final int BRONZE = 1;
	public static final int SLIVER = 2;
	public static final int GOLDEN = 3;
	public static final int DIAMOND = 4;
	BufferedImage bronzeImage,sliverImage,goldenImage,diamondImage;
	
	public int score;
	public Level() throws IOException {
		score = 0;
		bronzeImage = ImageIO.read(getClass().getResource("bronze.png"));
		sliverImage = ImageIO.read(getClass().getResource("silver.png"));
		goldenImage = ImageIO.read(getClass().getResource("gold.png"));
		diamondImage = ImageIO.read(getClass().getResource("diamonds.png"));
	}
	
	public int levelJudge(){
		int level = 0;
		if(score >= 2 && score<4) {
			level =BRONZE;
		}
		else if(score >= 4 && score <6) {
			level = SLIVER;
		}
		else if(score >=6 && score < 8){
			level = GOLDEN;
		}else if(score >= 8){
			level = DIAMOND;
		}
		return level;
	}
	
	
	
}
