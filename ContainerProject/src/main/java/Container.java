import java.util.ArrayList;
import java.util.Scanner;

public class Container {
	private String containerId;
	private String content;
	private String company;
	private String id; //journeyId
	private String currentLocation;
	
    //Take input and store data
    private ArrayList<Integer> pressureList = new ArrayList<Integer>();
    private ArrayList<Integer> humList = new ArrayList<Integer>();
    private ArrayList<Integer> tempList = new ArrayList<Integer>(); 
    
    private String containerID;
    private String journeyID;
    private String uniqueID = containerID + "," + journeyID;   
    private String holdID1;
    private String holdID2;
    private String holdID3;
    private ArrayList <String> outPressure = new ArrayList<String>(); 
    private ArrayList <String> outHum = new ArrayList<String>(); 
    private ArrayList <String> outTemp = new ArrayList<String>();
    
	
	private static int cCounter = 0;
	
	public Container( String content, String company, String id) {
		this.id = id;
		this.content = content;
		this.company = company;
		this.containerId = "C"+ cCounter;
		
		cCounter++;
	}
    public boolean isEmpty() {
    	if (tempList.size() == 0 && humList.size() == 0 && pressureList.size() == 0) {
    		return true;
    	}
    	else { 
    		return false;
    	}
    }
    
    //add recieve data method, that checks if its empty or else returns all the data
    // if an entry is empty, then you tell the user that there is no data present, 
    // else you display the data. 
    //make one for each and make a big one that connects the data. 
    
    
    public ArrayList<Integer> getPressureList() {
		return pressureList;
	}


	public ArrayList<Integer> getHumList() {
		return humList;
	}
	
	public ArrayList<Integer> getTempList() {
		return tempList;
	}

	public void setContainerID(String containerID) {
        this.containerID = containerID;
    }

	public void setJourneyID(String journeyID) {
		this.journeyID = journeyID;
	}

	public void getExpandedUniqueIDAll(String uniqueID) {
        holdID1 = uniqueID + "," + "pressure";
        holdID2 = uniqueID + "," + "hum";
        holdID3 = uniqueID + "," + "temp";
        outPressure.add(holdID1);
        outHum.add(holdID2);
        outTemp.add(holdID3);
    }
    
    public String getUniqueID() {
		return uniqueID;
	}
	public String getContainerId() {
		return containerId;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}



}
