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
class MainCanvas extends Canvas implements Runnable
{   
	Thread thread;
	Image currentImg;
	Image bossImg;
	Image heroImg[][]=new Image[4][3];
	int heroX,heroY;
	int bossX,bossY;
	int flag;
    public MainCanvas()
	{
		try
		{   
			for(int i=0;i<heroImg.length;i++)
			{ 
				for(int j=0;j<heroImg[i].length;j++)
				{
		         heroImg[i][j]=Image.createImage("/sayo"+i+j+".png");
                }
		    }
			bossImg=Image.createImage("/zuzu000.png");
			currentImg=heroImg[3][1];
			heroX=100;heroY=120;
			bossX=0;bossY=0;
            flag=1;
            thread=new Thread(this);
			thread.start();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public void run()
	{
	    while(true)
		{
		 try
		 {
		  Thread.sleep(200);
		 }
		 catch(InterruptedException e)
		 {
          e.printStackTrace(); 
		 }
		 if(bossX<heroX)
		 {
		  bossX++;
		 }
			else
			{
			 bossX--;
			}
		 if(bossY<heroY)
			{
		     bossY++;
		    }
			else
			{
			 bossY--;
			}
			repaint();
		}
	}
	public void paint(Graphics g){
		g.setColor(254,234,255);
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(currentImg,heroX,heroY,0);
		g.drawImage(bossImg,bossX,bossY,0);
	}
	public void changePicAndMove(int dir)
	{
	 if(flag==1)
		{
		currentImg=heroImg[dir][0];
		flag++;
		}
	 else if(flag==2)
		{
		currentImg=heroImg[dir][2];
		flag=1;
	    }
	}
	public void keyPressed(int keyCode){
	int action=getGameAction(keyCode);

	if(action==LEFT&&heroX>1){
	     changePicAndMove(0);
	     heroX=heroX-11;
	    } 

	if(action==RIGHT&&heroX<221){
	    changePicAndMove(1);
	    heroX=heroX+11; 
	    }

	if(action==UP&&heroY>1){
	    changePicAndMove(2);
	    heroY=heroY-11;
	    }

	if(action==DOWN&&heroY<254){
	    changePicAndMove(3);
	    heroY=heroY+11;
	    }
		repaint();
	}
}