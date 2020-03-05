public class WaveNode{
	
	int forwardHeight = 0;
	int backwardHeight = 0;
	int xPos;
	int yPos;
	int oldForwardHeight = 0;
	int oldBackwardHeight = 0;

	WaveNode prev = null;
	WaveNode next = null;

	public WaveNode(int xPos, int yPos){
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public void update(){
		if (next != null) {
			next.forwardHeight = oldForwardHeight;
		}else{
			backwardHeight = oldForwardHeight;
		}
		if (prev != null) {
			prev.backwardHeight = oldBackwardHeight;
		}else {
			forwardHeight = oldBackwardHeight;
		}
	}

	public void draw(){


		//draw new
		oldForwardHeight = forwardHeight;
		oldBackwardHeight = backwardHeight;
	}
}