import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import java.io.*;
public class AI extends MIDlet
{
	Display display;
	MainCanvas mc;
	public AI(){
		display=Display.getDisplay(this);
		mc=new MainCanvas();
		display.setCurrent(mc);
	}
	public void startApp(){
	}
	public void destroyApp(boolean unc){
	}
	public void pauseApp(){
	}
}
class MainCanvas extends Canvas
{   Image currentImg,downImg,upImg,leftImg,rightImg;
    public MainCanvas()
	{
		try
		{   
			downImg=Image.createImage("/sayo10.png");
			leftImg=Image.createImage("/sayo12.png");
		    upImg=Image.createImage("/sayo14.png");
			rightImg=Image.createImage("/sayo16.png");
			currentImg=downImg;
		}
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public void paint(Graphics g){
		g.setColor(0,0,0);
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(currentImg,120,120,0);
       
	}
	public void keyPressed(int keyCode){       //实现人物的转向
	int action=getGameAction(keyCode);
	if(action==LEFT){
		currentImg=rightImg;
	} 
	if(action==RIGHT){
	currentImg=leftImg;
	}
	if(action==UP){
	currentImg=upImg;
	}
	if(action==DOWN){
	currentImg=downImg;
	}
	repaint();
	}
}