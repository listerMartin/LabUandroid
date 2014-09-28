package edu.ucuccs.LabUandroid;

public class RoomsTask {

	private int id;
	private String lab;
	private String pc;
	
	public RoomsTask(String lab, String pc){
		this.lab = lab;
		this.pc = pc;
	}
	public RoomsTask(){
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLab() {
		return lab;
	}
	public void setLab(String lab) {
		this.lab = lab;
	}
	public String getPC() {
		return pc;
	}
	public void setPC(String pc) {
		this.pc = pc;
	}

}
