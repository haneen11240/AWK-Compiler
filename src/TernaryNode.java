

public class TernaryNode extends Node {
	
	Node express;
	Node True;
	Node False;
	
	public TernaryNode(Node express, Node True, Node False) {
		this.express = express;
		this.True = True;
		this.False = False;
		
	}
	

	@Override
	public String toString() {
		return express.toString()+"?" + True.toString()+":"+ False.toString();
	}


	public Node getExpress() {
		return express;
	}


	public void setExpress(Node express) {
		this.express = express;
	}


	public Node getTrue() {
		return True;
	}


	public void setTrue(Node true1) {
		True = true1;
	}


	public Node getFalse() {
		return False;
	}


	public void setFalse(Node false1) {
		False = false1;
	}

}
