package edu.ucuccs.LabUandroid;

public class PCtask {
	private int id;
	private String labname;
	private String pcname;
	private String comment;
	private String equipment;

	public PCtask(int id, String labname, String pcname, String comment,String equipment) {
		this.id = id;
		this.labname = labname;
		this.pcname = pcname;
		this.comment = comment;
		this.equipment = equipment;
	}
	

	public PCtask(String labname, String pcname,String comment,String equipment) {
		this.labname = labname;
		this.pcname = pcname;
		this.comment = comment;
		this.equipment = equipment;
	}

	public PCtask(int id) {
		this.id = id;
	}
	public PCtask(int id, String labname, String pcname) {
		this.id = id;
		this.labname = labname;
		this.pcname = pcname;
	}
	public PCtask( String labname, String pcname) {
		
		this.labname = labname;
		this.pcname = pcname;
	}

	public PCtask() {
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabname() {
		return labname;
	}

	public void setLabname(String labname) {
		this.labname = labname;
	}

	public String getPCname() {
		return pcname;
	}

	public void setPCname(String pcname) {
		this.pcname = pcname;
	}
	public String getcomment() {
		return comment;
	}

	public void setcomment(String comment) {
		this.comment = comment;
	}
	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
}
