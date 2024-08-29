
import java.util.LinkedList;

public class ProgramNode extends Node{

	// It has four linkedlist, one for FunctionNode, and one for StartBlocks, and one for 
	//EndBlocks and one for blocks
	private LinkedList <FunctionDefinitionNode> FunctionNode =  new LinkedList<FunctionDefinitionNode>();
	private LinkedList <BlockNode> StartBlocks = new LinkedList <BlockNode>();
	private LinkedList <BlockNode> EndBlocks = new LinkedList <BlockNode>();
	private LinkedList <BlockNode> Blocks = new LinkedList <BlockNode>();
	
	
	
	
	
	//Assessors
	public void AddFunctionNode (FunctionDefinitionNode function){
		FunctionNode.add(function);
	}
	
	public void AddStartBlocks (BlockNode blockNode){
		 StartBlocks.add(blockNode);
	}
	
	public void AddEndBlocks (BlockNode blockNode){
		EndBlocks.add(blockNode);
	}
	
	public void AddBlocks (BlockNode blockNode){
		Blocks.add(blockNode);
	}
	
	public LinkedList <FunctionDefinitionNode> FunctionNode (){
		return FunctionNode;
	}
	
	public LinkedList <BlockNode> GetAddStartBlocks (){
		return StartBlocks;
	}
	
	public LinkedList <BlockNode> GetAddEndBlocks (){
		return EndBlocks;
	}
	
	public LinkedList <BlockNode> GetAddBlocks (){
		return Blocks;
	}
	
	
	@Override
	 public String toString() {
		return FunctionNode.toString()+ StartBlocks.toString() + EndBlocks.toString() + Blocks.toString() ;
	}
	

}
