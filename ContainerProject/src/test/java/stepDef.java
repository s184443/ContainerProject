

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDef {
	
	Journey j;
	Journey j1;
	Journey j2;
	Journey j3;
	Database database = new Database();
	Set<Journey> matches = new HashSet<Journey>();
	String search;
	String newloc;
	String loc;
	
//	Container c;
	String destination;
	String content;
	String company;
	String origin;
	
// Container 2;
	String destination2;
	String content2;
	String company2;
	String origin2;
	
	
	client client;
	client client2;
	client client3;
	
	int temp;
	int hum;
	int pressure;
	

	tempDataMap tdm1 = new tempDataMap();
	responseObject response1;
	responseObject response2;
	responseObject response3;
	
	Container c1;
	
	//Simulator variables
	Simulator sim = new Simulator();
	int seed;
	String companyName;
	String name;
	String mail;
	String address;
	int days;
	
	//1
	@Given("a new client company name {string}, address {string}, email {string}, contact person {string}")
	public void a_new_client_company_name_address_email_contact_person(String company, String address, String email, String name) {
	    this.companyName = company;
	    this.name = name;
	    this.mail = email;
	    this.address = address;
	}

	@When("client is registered")
	public void client_is_registered() {
		client= database.createClient(companyName, address, mail, name);

	  
	}

	@Then("client exists on database")
	public void client_exists_on_database() {
		assertEquals(database.exists(client), true);
	}
	
	
	//2
	@When("client wants to update email to {string}")
	public void client_wants_to_update_email_to(String string) {
	    client.updateEmail(string);
	}

	@When("client wants to update adress to {string}")
	public void client_wants_to_update_adress_to(String string) {
	    client.updateAddress(string);
	}

	@When("client wants to update contact person to {string}")
	public void client_wants_to_update_contact_person_to(String string) {
	    client.updateName(string);
	}

	@Then("client has been updated")
	public void clients_email_has_been_updated() {
		assertEquals(client.getAddress(), "dongleland");
		assertEquals(client.getEmail(), "ex@dtu.dk");
		assertEquals(client.getName(), "jimmyjon");
	}

	
	

	Set<client> results;
	@When("string {string} apears as client infornmation")
	public void string_apears_as_client_infornmation(String string) {
		 results =database.search(string);
	}

	@Then("a list of possible clients is returned")
	public void a_list_of_possible_clients_is_returned() {
		
		assertEquals(results.size(),2);
		
	}

	

	

//	SCENARIO 1
	
	
	@Given("a new port of origin {string}")
	public void a_new_port_of_origin(String origin) {
		this.origin=origin;
		
	}

	@Given("a new port of destination {string}")
	public void a_new_port_of_destination(String destination) {
		this.destination=destination;
	}

	@Given("content {string}")
	public void content(String content) {
		this.content=content;
	}
	
	@Given("company {string}")
	public void company(String company) {
		this.company=company;
		
	}
	
	@When("registered")
	public void registered() {
		j = database.createJourney(origin, destination, content, company);
		 
	}

	@Then("a unique Id is created")
	public void a_unique_Id_is_created() {
		
		assertEquals(j.getId(), "CPHRIO20");
	}
	
	@Then("the origin and destination is correct")
	public void the_origin_and_destination_is_correct() {
	    assertEquals(j.getOrigin(), "CPH");
	    assertEquals(j.getDestination(), "RIO");
	}
	
	@Then("content and company is correct")
	public void content_and_company_is_correct() {
	    assertEquals(j.getContainerList().get(0).getContent(), "BANANA");
	    assertEquals(j.getContainerList().get(0).getCompany(), "DK");
	}
	
	@Then("journey is in database")
	public void journey_is_in_database() {
		assertEquals(database.getJourney().size(), 1);
		
	}
	

//	SCENARIO 2
	
	@Given("a new journey {string} {string} {string} {string}")
	public void a_new_journey(String origin, String destination, String content, String company) {
		this.origin = origin;
		this.destination = destination;
		this.content = content;
		this.company = company;

	}

	@Given("a new journey with {string} {string} {string} {string}")
	public void a_new_journey_with(String origin, String destination, String content, String company) {
		this.origin2 = origin;
		this.destination2 = destination;
		this.content2 = content;
		this.company2 = company;

	}

	@When("journeys are registered")
	public void journeys_are_registered() {
		j1 = database.createJourney(origin, destination, content, company);
		j2 = database.createJourney(origin2, destination2, content2, company2);
	}

	@Then("journeys exist on database")
	public void journeys_exist_on_database() {
	    assertEquals(database.getJourney().size(), 2);
	}
	
	
//	SCENARIO 3
	
	@Then("only one journey is created")
	public void only_one_journey_is_created() {
	    assertEquals(database.getJourney().size(), 1);
	}

	@Then("the journey has two containers")
	public void the_journey_has_two_containers() {
	    assertEquals(database.getJourney().get(0).getContainerList().size(), 2);
	}

// SCENARIO 4
	
	@Given("a new journey j1 {string} {string} {string} {string}")
	public void a_new_journey_j1(String origin, String destination, String content, String company) {
		j1 = database.createJourney(origin, destination, content, company);


	}
	
	
	@Given("a new journey j2 {string} {string} {string} {string}")
	public void a_new_journey_j2(String origin, String destination, String content, String company) {
		j2 = database.createJourney(origin, destination, content, company);

		
	}

	@Given("a keyword {string}")
	public void a_keyword(String keyword) {
	    this.search = keyword;
	    
	}

	@When("it matches for the keyword")
	public void it_matches_for_the_keyword() {
	    matches = database.findUsingLoop(search, database.getJourney());
	}

	@Then("the empty list matches is returned")
	public void the_empty_list_matches_is_returned() {
		assertEquals(matches.size(), 0);

	}

//	SCENARIO 5

	@Then("a filtered list matches containing j2 is returned")
	public void a_filtered_list_matches_containing_j2_is_returned() {
		assertEquals(matches.contains(j2), true);
		assertEquals(matches.size(), 1);
	}

//	SCENARIO 6
	
	@Then("a filtered list matches containing j1 is returned")
	public void a_filtered_list_matches_containing_j1_is_returned() {
		assertEquals(matches.contains(j1), true);
		assertEquals(matches.size(), 1);
	}

//	SCENARIO 7

	@When("the journeys current location is found")
	public void the_journeys_current_location_is_found() {
	    loc = j1.getCurrentLocation();
	}

	@Then("it should be returned to the customer")
	public void it_should_be_returned_to_the_customer() {
		assertEquals(loc, "CPH");
	}
	
//	SCENARIO 8
	
	@Given("a new position {string}")
	public void a_new_position(String string) {
		newloc = "fra";
	}

	@When("the journeys current location is updated")
	public void the_journeys_current_location_is_updated() {
		j1.updateCurrentLocation(newloc);
	}

	@Then("the current location should be updated")
	public void the_current_location_should_be_updated() {
		assertEquals(j1.getCurrentLocation(), "FRA");
	}

	
// SCENARIO 9
	
	@When("the journey is over")
	public void the_journey_is_over() {
		database.endOfJourney(j1);
	}

	@Then("the journey is put into history")
	public void the_journey_is_put_into_history() {
		assertEquals(database.getJourney().size(), 0);
		assertEquals(database.getHistory().size(), 1);
		
	}

	@Then("the containers are emtied")
	public void the_containers_are_emtied() {
	    assertEquals(j1.getContainerList().get(0).getContent(), "empty");
	    assertEquals(j1.getContainerList().get(0).getCompany(), "empty");
	}

	@Then("the containers are stored in the warehouse")
	public void the_containers_are_stored_in_the_warehouse() {
	    assertEquals(j1.getContainerList().get(0).getCurrentLocation(), "container warehouse");
	    assertEquals(database.getContainerWarehouse().size(), 1);
	}
	
//SCENARIO 10
	
	@When("assigning containers")
	public void assigning_containers() {

	}

	@Then("the container should be taken from containerwarehouse")
	public void the_container_should_be_taken_from_containerwarehouse() {
		assertEquals(j1.getContainerList().get(0).getContainerId(), "C30");
		assertEquals(j2.getContainerList().get(0).getContainerId(), "C30");
		assertEquals(database.getContainerWarehouse().size(), 0);
	}
	
	// Getting Measurements
	
	// 1
	
	@Given("a new j3 {string} {string} {string} {string}")
	public void a_new_j3(String origin, String destination, String content, String company) {
	    // Write code here that turns the phrase above into concrete actions
	    j3 = database.createJourney(origin, destination, content, company);
	    c1 = j3.getContainerList().get(0);
	}

    @Given("uniqueID is first time occurrence")
    public void specificid_is_first_time_occurrence() 
    {tdm1.doesKeyExist(c1.getUniqueID());}
	    
	    
    @Given("temp {int} and hum {int} and pressure {int}")
    public void temp_and_hum_and_pressure(Integer temperature, Integer humidity, Integer atmosphericpressure) 
    {temp = temperature;
     hum = humidity;
     pressure = atmosphericpressure;}
	    
    @When("add all internal measurements to containers")
    public void add_all_internal_measurements_to_containers() 
    {c1.getTempList().add(temp);
     c1.getHumList().add(hum);
     c1.getPressureList().add(pressure);}
	    
    @Then("transfer data from tempDataMap to dataMap")
    public void transfer_data_from_tempDataMap_to_dataMap() 
    {tdm1.dataMap.put(c1.getUniqueID() + "," + "Atmospheric Pressure",c1.getPressureList());
     tdm1.dataMap.put(c1.getUniqueID() + "," + "Air Humidity",c1.getHumList());
     tdm1.dataMap.put(c1.getUniqueID() + "," + "Internal Temperature",c1.getTempList());  
     response1 = new responseObject(200, "data stored");}
	    
    @Then("Display confirmation message of added measurements")
    public void display_confirmation_message_of_added_measurements() 
    {assertEquals(response1.getErrorMessage(),"data stored");}    
	    
	    
    @Given("a datapoint with temp {int} and hum {int} and pressure {int}")
    public void a_datapoint_with_temp_and_hum_and_pressure(Integer temperature, Integer humidity, Integer atmosphericpressure) 
    {c1.getTempList().add(temperature);
     c1.getHumList().add(humidity);
     c1.getPressureList().add(atmosphericpressure);}
    
    @When("retrieve all internal measurements from containers")
    public void retrieve_all_internal_measurements_from_containers() 
    {c1.getExpandedUniqueIDAll(c1.getUniqueID());
     response2 = new responseObject(210, "data retrieved");}
    
    @Then("display confirmation message of retrieved measurements")
    public void display_confirmation_message_of_retrieved_measurements() 
    {assertEquals(response2.getErrorMessage(),"data retrieved");}
    
    
    @Then("display error message telling that no data is available for this uniqueID")
    public void display_error_message_telling_that_no_data_is_available_for_this_uniqueID() 
    {if (c1.isEmpty()) 
    {response3 = new responseObject(220, "no data retrieved");}
    assertEquals(response3.getErrorMessage(),"no data retrieved");}
	    
	    
	//Simulator scenarios
	    
	    
	//1
	@Given("a seed {int}")
	public void a_seed(int seed) {
	    this.seed = seed;
	}
	
	@When("A company is choosen randomly")
	public void a_company_is_choosen_randomly() {
	    companyName = sim.companySelection(seed);
	}
	
	@Then("that company will be Netto")
	public void that_campany_will_be_Netto() {
	    assertEquals(companyName, "Netto");
	}
	
	@Then("Netto is removed from the list of companies")
	public void netto_is_removed_from_the_list_of_companies() {
	    assertEquals(sim.getCompanies().size(), 15);
	}
	
	
	
	//2
	@When("A name and email is choosen randomly")
	public void a_name_and_email_is_choosen_randomly() {
	   name = sim.nameSelection(seed);
	   mail = sim.emailCreation(companyName, name);
	}

	@Then("that name will be")
	public void that_name_will_be() {
	    assertEquals(name, "Bill Silva");
	    assertEquals(mail, "Bill.Silva@Netto.com");
	}
	
	
	
	//3
	@When("An address is choosen randomly")
	public void an_address_is_choosen_randomly() {
	    address = sim.addressSelection(seed);
	}

	@Then("that address will be")
	public void that_address_will_be() {
	    assertEquals(address, "Kings Street 100");
	}

	@Then("that address will be removed from the list of addresses")
	public void that_address_will_be_removed_from_the_list_of_addresses() {
	    assertEquals(sim.getAddress().size(), 15);
	}
	
	
	
	//4
	@When("a client is created")
	public void a_client_is_created() {
		company = sim.companySelection(seed);
		address = sim.addressSelection(seed);
		name = sim.nameSelection(seed);
		mail = sim.emailCreation(name, company);
		client = database.createClient(company, address, mail, name);
	}

	@Then("that client should be in the database")
	public void that_client_should_be_in_the_database() {
	    assertEquals(database.getClients().size(), 1);
	}
	
	
	
	//5
	@Given("a client with seed {int}")
	public void a_client_with_seed(Integer seed) {
		company = sim.companySelection(seed);
		address = sim.addressSelection(seed);
		name = sim.nameSelection(seed);
		mail = sim.emailCreation(name, company);
		client = database.createClient(company, address, mail, name);
	}

	@When("a client is choosen with seed {int}")
	public void a_client_is_choosen_with_seed(int seed) {
	    client = sim.clientSelection(database, seed);
	}

	@Then("that clientId will be")
	public void that_clientId_will_be() {
		assertEquals(client.getCompany(), "Nestle");
	    assertEquals(client.getId(), 69423);
	}
	
	
	
	//6
	@When("a content is choosen")
	public void a_content_is_choosen() {
	    content = sim.contentSelection(seed);
	}

	@Then("that content will be bananas")
	public void that_content_will_be_bananas() {
	    assertEquals(content, "bananas");
	}

	
	
	//7
	@When("a origin is choosen")
	public void a_origin_is_choosen() {
	    origin = sim.originSelection(seed);
	}

	@Then("that origin will be CPH")
	public void that_origin_will_be_CPH() {
	    assertEquals(origin, "CPH");
	}
	
	
	
	//8
	@When("a destination is choosen")
	public void a_destination_is_choosen() {
	    destination = sim.destinationSelection(seed, origin);
	}

	@Then("that destination will be RIX")
	public void that_destination_will_be_RIX() {
	    assertEquals(destination, "RIX");
	}

	
	
	//9
	@When("a journey is created")
	public void a_journey_is_created() {
	    database.createJourney(origin, destination, content, company);
	}

	@Then("that journey should be in the database")
	public void that_journey_should_be_in_the_database() {
	    assertEquals(database.getJourney().size(), 1);
	    assertEquals(database.getJourney().get(0).getId(),"CPHRIX4");
	}
	
	// Data Simulation
	
	// 1
	@Given("a Journey {string} {string} {string} {string}")
	public void a_Journey(String origin, String destination, String content, String company) {
		j = database.createJourney(origin, destination, content, company);
		c1 = j.getContainerList().get(0);
	}

	@When("data is generated")
	public void data_is_generated() {
	    temp = sim.temperatureInitialization(seed);
	    pressure = sim.pressureInitialization(seed);
	    hum = sim.humidityInitialization(seed);
	}
	
	@When("data is added to the Journey")
	public void data_is_added_to_the_Journey() {
	    database.updateData(j, c1, temp, pressure, hum);
	}

	@Then("the data will be stored in the Container")
	public void the_data_will_be_stored_in_the_Container() {
	    assertEquals(c1.getTempList().size(), 1);
	    assertEquals(c1.getPressureList().size(), 1);
	    assertEquals(c1.getHumList().size(), 1);
	    assertEquals(c1.getTempList().get(0).toString(), "17");
	    assertEquals(c1.getPressureList().get(0).toString(), "992");
	    assertEquals(c1.getHumList().get(0).toString(), "62");
	}

	@Then("the data has the specific values")
	public void the_data_has_the_specific_values() {
	    assertEquals(temp, 17);
	    assertEquals(pressure, 992);
	    assertEquals(hum, 62);
	}
	
	
	//2
	
	@When("data is generated based on previous data")
	public void data_is_generated_based_on_previous_data() {
	    temp = sim.temperatureGenerated(c1, seed);
	    pressure = sim.pressureGenerated(c1, seed);
	    hum = sim.humidityGenerated(c1, seed);
	    database.updateData(j, c1, temp, pressure, hum);
	}

	@Then("the data is the following")
	public void the_data_is_the_following() {
	    assertEquals(temp, 14);
	    assertEquals(pressure, 1024);
	    assertEquals(hum, 59);
	}
	
	@Then("data was added to container")
	public void data_was_added_to_container() {
		assertEquals(c1.getTempList().size(), 2);
	    assertEquals(c1.getPressureList().size(), 2);
	    assertEquals(c1.getHumList().size(), 2);
	    System.out.println(c1.getTempList());
	    System.out.println(c1.getHumList());
	}
	
	// 3
	
	@When("data is simulated and updated thrice with the following seeds {int} {int} {int}")
	public void data_is_simulated_and_updated_thrice_with_the_following_seeds(Integer seed1, Integer seed2, Integer seed3) {
	    sim.simulateData(database, seed1);
	    sim.simulateData(database, seed2);
	    sim.simulateData(database, seed3);
	}

	@Then("the there will be three datapoints of each type stored in the container")
	public void the_there_will_be_three_datapoints_of_each_type_stored_in_the_container() {
	    assertEquals(j.getContainerList().get(0).getTempList().size(), 3);
	}
	
	@Then("the three different data points are the following")
	public void the_three_different_data_points_are_the_following() {
		c1 = j.getContainerList().get(0);
	    assertEquals(c1.getTempList(), new ArrayList<Integer>(Arrays.asList(17,20,18)));
	    assertEquals(c1.getPressureList(), new ArrayList<Integer>(Arrays.asList(992,970,963)));
	    assertEquals(c1.getHumList(), new ArrayList<Integer>(Arrays.asList(62,65,63)));
	}
	
	// Updated Location
	
	// 1
	
	@Given("a client is created with seed {int}")
	public void a_client_is_created_with_seed(Integer seed) {
	    sim.clientCreation(database, seed);
	}
	
	@Given("a Journey is created")
	public void a_Journey_is_created() {
	    sim.journeyCreation(database, seed);
	    j = database.getJourney().get(0);
	}

	@Given("amount of days {int}")
	public void amount_of_days(Integer days) {
	    this.days = days;
	}

	@When("days pass by and location gets updated")
	public void days_pass_by_and_location_gets_updated() {
	    for (int i = 0; i<days; i++) {
	    	sim.updateLocation(database);
	    }
	}

	@Then("the current Location should be in transition")
	public void the_current_Location_should_be_in_transition() {
	    assertEquals(j.getCurrentLocation(), "IN TRANSIT FROM CPH TO RIX");
	}
	
	// 2
	
	@Then("the current location should be destination")
	public void the_current_location_should_be_destination() {
		assertEquals(j.getCurrentLocation(), j.getDestination());
		assertEquals(database.getJourney().size(), 0);
		assertEquals(database.getHistory().size(), 1);
	}
	
	// Simulation Test
	
	// 1
	
	@When("simulation is running")
	public void simulation_is_running() {
	    sim.Simulation(database, days);
	}

	@Then("the amount of clients in the system should be {int}")
	public void the_amount_of_clients_in_the_system_should_be(Integer int1) {
	    assertEquals(database.getClients().size(), 4);
	}

	@Then("the sum of the containers in all the active journeys and in the past journeys is {int}")
	public void the_sum_of_the_containers_in_all_the_active_journeys_and_in_the_past_journeys_is(Integer int1) {
		int sum = 0;
	    for (Journey j : database.getJourney()) {
	    	sum = sum + j.getContainerList().size();
	    }
	    for (Journey j : database.getHistory()) {
	    	sum = sum + j.getContainerList().size();
	    }
	    assertEquals(sum, 10);
	}
}






