
public class Timer {
	private long startTime = 0;
	private long sec = 0;
	private long min = 0;
	private long hour = 0;
	private long mills = 0;
	
	private boolean rolling;
	
	public Timer(){
		
	}
	
	public void start(){
		rolling = true;
		startTime = System.currentTimeMillis();
		sec = 0;
		min = 0;
		hour = 0; 
		mills = 0;
	}
	
	public void update(){
		if(rolling){
			mills = System.currentTimeMillis() - startTime;
			sec = (mills / 1000) % 60;
			min = (mills / (1000 * 60)) % 60;
			hour = (mills / (1000 * 60 * 60)) % 24;
		}
	}
	
	public void stop(){
		update();
		rolling = false;
	}
	
	public void resume(){
		rolling = true;
	}
	
	public void restart(){
		start();
	}
	
	public String toString(){
		update();
		return String.format("%02d:%02d:%02d.%01d", hour, min, sec, mills % 1000);
	}
}
