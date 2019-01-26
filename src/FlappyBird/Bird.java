package FlappyBird;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Bird {
		BufferedImage image;
	 	public int x,y;//鸟的初始位置
	 	public int width,height;
	 	public int size;//鸟的大小，用于碰撞测验
//	 	在Bird类中额增加属性，用于计算鸟的位置
	 	public double g;//重力加速度
	 	public double t;//两次位置的间隔时间
	 	public double v0;//初始上抛速度
	 	public double speed;//当前的上抛速度
	 	public double s;//经过时间t的位移(竖直方向上的)，s = v0*t + 0.5*g*t*t
	 	public double alpha;//鸟的倾角，鸟的弧度旋转
//	 	public int skinSelected;
//	 	定义一组（数组）图片，是鸟的动画帧
	 	BufferedImage[] images;
	 	//动画帧数组元素的下标位置
	 	public int index;
	 	
	 	//构造器,参数s代表第几套皮肤
	 	public Bird(int skinSelect) throws Exception{
//	 		System.out.println("Bird.s="+s);
	 		image = ImageIO.read(getClass().getResource("../img"+skinSelect+"/0.png"));
	 		width = image.getWidth();
	 		height = image.getHeight();
	 		x = 132;
	 		y = 280;
	 		size = 40;
	 		g = 3;
	 		v0 = 10;
	 		//t需要根据帧数所改变
	 		t = 0.25;
	 		speed = v0;
	 		s = 0;
	 		alpha = 0;
	 		//创建数组，创建8个元素的数组
	 		//是8个空位置，没有图片对象
	 		//8个位置的序列号： 0 1 2 3 4 5 6 7
	 		images = new BufferedImage[8];
	 		for(int i = 0;i<8;i++) {//"../img1/"+i+".png"
	 			images[i] = ImageIO.read(getClass().getResource("../img"+skinSelect+"/"+i+".png"));
	 		}
	 		index = 0;
	 	}//构造器结束
	 	
	 	//fly方法，通过轮播图片来模仿鸟的运动
	 	public void fly() {
	 		index ++;
	 		//每20ms后调用这个方法，除12后变成了240ms换一张图片
	 		image = images[(index / 12) % 8];
	 	}
	 	
	 	//run方法
	 	public void run() {
	 		//定义局部变量等于当前速度
	 		double v0 = speed;
	 		//计算本次的运动位移
	 		s = v0*t - g*t*t/2;
	 		//计算鸟的坐标位置
	 		y = y - (int)s;
//	 		调用Java API提供的反正切函数，计算倾角,截成8份：每一份对应一张帧图，移动效果好
	 		alpha = Math.atan(s/8);
	 		//计算下次的速度
	 		double v = v0 - g*t;
	 		speed = v;
	 		
	 	}
	 	
//	 	flappy方法：设置初始速度，重新向上飞
	 	public void flappy() {
	 		speed = v0;
	 	}
	 	
	 	/*
	 	 * 	在Bird类中添加hit方法
	 	 * 	检测当前鸟是否碰到地面
	 	 * 	如果是返回true，表示发生碰撞
	 	 * 	否则返回false，表示没有碰撞
	 	 */
	 	public boolean groundHit (Ground ground) {
	 		if(y + size/2 > ground.y) {
	 			return true;
	 		}
	 		return false;
	 	}
	 	
	 	/*
	 	 * 	在Bird类中添加hit方法
	 	 * 	检测当前鸟是否碰到柱子
	 	 * 	如果是返回true，表示发生碰撞
	 	 * 	否则返回false，表示没有碰撞
	 	 */
	 	public boolean columnHit (Column column) {
	 		//先检查是否在柱子的范围以内
	 		if(x>column.x-column.width/2-size/2 && x<column.x+column.width/2+size/2) {
	 			if(y>column.y-column.gap/2+size/2 && y<column.y+column.gap/2-size/2) {
		 			return false;
		 		}
	 			return true;
	 		}
	 		return false;
	 	}
	 	
}//Bird类结束
