public class TheWave{
	
	WaveNode head;

	TheWave(){
		int numNodes = 20;
		int xInc = 15;
		int xPos = 10;
		head = new WaveNode(xPos,0);
		xPos = xPos + xInc;
		WaveNode current = head;
		for (int i = 1; i < numNodes; i++) {
			WaveNode wave = new WaveNode(xPos,0);
			xPos = xPos + xInc;
			wave.prev = current;
			current.next = wave;
			current = wave;
		}
	}

	public void pulse(){
		head.oldForwardHeight = 40;
	}

	public void draw(){
		WaveNode current = head;
		while (current != null) {
			current.update();
			System.out.println("forwardHeight: " + current.forwardHeight + " backwardHeight: " + current.backwardHeight);
			current = current.next;
		}
		current = head;
		while (current != null) {
			current.draw();
			current = current.next;
		}
	}
}