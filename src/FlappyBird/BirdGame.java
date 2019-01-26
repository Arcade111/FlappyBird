package FlappyBird;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

//	JFrame:窗体对象
//	JPanel:面板对象
public class BirdGame extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Bird bird;
	public Column column01, column02;
	public Ground ground;
//	public BufferedImage backgroundImage;
	// 分数
	public Level level;
	public BufferedImage startImage;
	public BufferedImage gameoverImage;
	public BufferedImage victorImage;
	// 三个常量和一个变量
	public int count;

	public  int state;// 游戏状态
	public  int skinSelected;// 选择第几套皮肤，默认第一套
	public static final int START = 0;
	public static final int RUNNING = 1;
	public static final int GAME_OVER = 2;
	public static final int GAME_WIN = 3;

	// 无参的构造函数
	public BirdGame() throws Exception {
		count = 1;
		level = new Level();
		skinSelected = level.levelJudge();
		bird = new Bird(skinSelected);
		column01 = new Column(1, skinSelected);
		column02 = new Column(2, skinSelected);
		ground = new Ground(skinSelected);
		state = START;
		startImage = ImageIO.read(getClass().getResource("start.png"));
		gameoverImage = ImageIO.read(getClass().getResource("gameover.png"));
		victorImage = ImageIO.read(getClass().getResource("victor.png"));
//		level.score = 0;
	}

//	重写paint方法实现绘制
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(ground.backgroundImage, 0, 0, null);
		// 柱子的中间点
		g.drawImage(column01.image, column01.x - column01.width / 2, column01.y - column01.height / 2, null);
		g.drawImage(column02.image, column02.x - column02.width / 2, column02.y - column02.height / 2, null);
		g.drawImage(ground.image, ground.x, ground.y, null);

		// 在paint方法中添加绘制分数的方法，FONT字体,样式自选为SANS_SERIF
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 35);// 定义好具体样式
		// setFont等于选择字体样式
		g.setFont(f);
//		g.setColor(Color.WHITE);
		// drawString：画字符串；+""是为了把score强转为string类型

		g.drawString("分数：", 20, 580);
		g.drawString("" + level.score, 130, 580);
		g.drawString("段位：", 210, 580);

		// 旋转（rotate）绘图坐标系，是API方法
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(-bird.alpha, bird.x, bird.y);
		g.drawImage(bird.image, bird.x - bird.width / 2, bird.y - bird.height / 2, null);
		g2.rotate(bird.alpha, bird.x, bird.y);

		// 根据状态来画图
//		if (level.score == 0 && state == START) {
//		} else if (state == GAME_OVER) {
//		}
		switch (state) {
		case START:
		g.drawImage(startImage, 0, 0, null);
			break;
		case GAME_OVER:
		g.drawImage(gameoverImage, 0, 0, null);
			break;
		default:
			break;
		}

		// 根据分数来画图
		switch (level.levelJudge()) {
		case 0:
			g.drawString("您暂无等级！", 110, 70);
			g.drawString("无", 320, 580);
			break;
		case 1:
			g.drawImage(level.bronzeImage, 320, 540, null);
			g.drawString("青铜段位！", 140, 70);
			break;
		case 2:
			g.drawImage(level.sliverImage, 320, 540, null);
			g.drawString("白银段位！", 140, 70);
			break;
		case 3:
			g.drawImage(level.goldenImage, 320, 540, null);
			g.drawString("黄金段位！", 140, 70);
			break;
		case 4:
			g.drawImage(level.diamondImage, 320, 540, null);
			g.drawString("钻石段位！", 140, 70);
			break;
		default:
			break;
		}
		if(level.score == 10) {
			g.drawImage(victorImage, 0, 0, null);
		}
	}// paint方法结束

	// BirdGame方法中添加方法action（）
	public void action() throws Exception {
		// MouseListenr鼠标监听器
		// MouseAdapter鼠标适配器
		// mousePressed是鼠标点击触发的事件
		MouseListener listener = new MouseAdapter() {
			// Mouse Pressed
			@Override
			public void mousePressed(MouseEvent e) {
				// 鸟向上飞扬
				// e.get里有很多方法

				try {
					if (level.levelJudge() == 0) {
						if (state == START) {
							state = RUNNING;
						} else if (state == RUNNING) {
							bird.flappy();
						} else if (state == GAME_OVER) {
							level = new Level();
							ground = new Ground(0);
							bird = new Bird(0);
							column01 = new Column(1, 0);
							column02 = new Column(2, 0);
							state = START;
						}
					} else if (level.levelJudge() == 1) {
						if (level.score == 2 && count == 1) {
							System.out.println("换皮肤1");
							ground = new Ground(1);
							bird = new Bird(1);
							column01 = new Column(1, 1);
							column02 = new Column(2, 1);
							count ++;
							state = RUNNING;
						}else if (state == RUNNING) {
							bird.flappy();
						} else if (state == GAME_OVER) {
							level = new Level();
							ground = new Ground(0);
							bird = new Bird(0);
							column01 = new Column(1, 0);
							column02 = new Column(2, 0);
							count = 1;
							state = START;
						}
					}
					else if(level.levelJudge() == 2) {
						if (level.score == 4 && count == 2) {
							System.out.println("换皮肤2");
							ground = new Ground(2);
							bird = new Bird(2);
							column01 = new Column(1, 2);
							column02 = new Column(2, 2);
							count ++;
							state = RUNNING;
						} else if (state == RUNNING) {
							bird.flappy();
						} else if (state == GAME_OVER) {
							level = new Level();
							ground = new Ground(0);
							bird = new Bird(0);
							column01 = new Column(1, 0);
							column02 = new Column(2, 0);
							count = 1;
							state = START;
						}
					}
					else if(level.levelJudge() == 3) {
						if (level.score == 6 && count == 3) {
							System.out.println("换皮肤3");
							ground = new Ground(3);
							bird = new Bird(3);
							column01 = new Column(1, 3);
							column02 = new Column(2, 3);
							count ++;
							state = RUNNING;
						} else if (state == RUNNING) {
							bird.flappy();
						} else if (state == GAME_OVER) {
							level = new Level();
							ground = new Ground(0);
							bird = new Bird(0);
							column01 = new Column(1, 0);
							column02 = new Column(2, 0);
							count = 1;
							state = START;
						}
					}
					else if(level.levelJudge() == 4) {
						if (level.score == 8 && count == 4) {
							System.out.println("换皮肤4");
							ground = new Ground(4);
							bird = new Bird(4);
							column01 = new Column(1, 4);
							column02 = new Column(2, 4);
							count ++;
							state = RUNNING;
						} else if (state == RUNNING) {
							bird.flappy();
						} else if (state == GAME_OVER) {
							level = new Level();
							ground = new Ground(0);
							bird = new Bird(0);
							column01 = new Column(1, 0);
							column02 = new Column(2, 0);
							count = 1;
							state = START;
						}
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		};
		addMouseListener(listener);

		// 死循环一直运行游戏，特殊情况...
		while (true) {
			switch (state) {
			case START:
				bird.fly();
				ground.run();
				break;
			case RUNNING:
				bird.fly();
				ground.run();
				column01.run();
				column02.run();
				bird.run();
				if (bird.groundHit(ground) || bird.columnHit(column01) || bird.columnHit(column02)) {
					state = GAME_OVER;
				}
				if (state != GAME_OVER) {
					if (bird.x == column01.x || bird.x == column02.x) {
						level.score++;
					}
				}
				break;
			default:
				break;
			}
			// 线程休眠
			Thread.sleep(20);
			// 休眠后重新画图
			repaint();
		}
	}

//	主函数入口
	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame("Flappy Bird！");
		BirdGame game01 = new BirdGame();
		frame.add(game01);
		frame.setSize(440, 670);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 程序退出即关闭
		frame.setLocationRelativeTo(null);// 设置窗口位置，默认居中？
		game01.action();
	}
}
