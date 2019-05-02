package CoreJava;

public enum MaintenanceType {
	REPAIR("Repair"), 
	REPLACE("Replace"), 
	SERVICE("Service"), 
	LUBRICATION("Lubrication");
	
	private final String name;   
	
	private MaintenanceType(String desc) {
        name = desc;
    }
	
	public String getName() {
		return this.name;
	}
	
	public String getCode() {
		return this.toString();
	}

}
