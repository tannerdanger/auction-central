//package users;
//
//import auctiondata.Auction;
//import auctiondata.AuctionItem;
//import auctiondata.Scheduler;
//import storage.AuctionCalendar;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Scanner;
///**
// * Contact person class, saves relevent information to a Contact
// */
//
//import com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations.PrivateKeyResolver;
//public class ContactPerson extends User {
//	/**
//	 * serialized id
//	 */
//	private static final long serialVersionUID = -710092057770182337L;
//	private ArrayList<Auction> mySubmittedAuctions;
//
//	private String myOrgName;
//	private int myOrgID;
//
//	/*
//	 * The Contact Person's previous Auction
//	 */
//	private Auction myPriorAuction;
//
//    /*
//	 * The Contact Person's current Auction
//	 */
//	private Auction myCurrentAuction;
//	private Scheduler myScheduler;
//	private AuctionItem myAuctionItem;
//	/*
//	 * Constructor for Contact Person, Will take a First name, Last name, and email.
//	 * Will set the current and prior auction to null.
//	 */
//	public ContactPerson(String theFirst, String theLast, String theEmail) {
//		super(theFirst, theLast, theEmail); //pass basic ID values to User superclass
//		myPriorAuction = null;
//		myCurrentAuction = null;
//		//myScheduler = theScheduler; I think our idea is for this class to be static and call-able at any time so we don't need to declare it.
//	}
//
//	/*
//	 * Goes through the process of submitting an auction request.
//	 * Asks user for the date of the new auction.
//	 * Checks the validity of this date.
//	 * If valid, it lets the Contact Person know and then creates this auction and sets it to
//	 * 		the Contact Person's current auction.
//	 * 		Side note, this does not yet check for current auction as I'm not sure yet sure how we will
//	 * 		deal with transitioning current auctions to prior auctions yet.
//	 */
//	public void submitAuctionRequest() {
//		Scanner Scan = new Scanner(System.in);
//		System.out.println("When do you plan to host your auction?");
//		System.out.println("Please enter Time and Date (24Hour time (HH:MI); MM/DD/YYYY:");
//		String scannedLine = Scan.nextLine();
//
//		Scanner lineScan = new Scanner(scannedLine);
//	    lineScan.useDelimiter("/");
//
//	    int theMonth = lineScan.nextInt();
//		int theDay = lineScan.nextInt();
//		int theYear = lineScan.nextInt();
//
//		System.out.println("Validating your auction inventory sheet...");
//		LocalDateTime newDate = LocalDateTime.of(theYear, theMonth, theDay, 0,0);
//
//		if (Scheduler.isAuctionRequestValid(myPriorAuction, myCurrentAuction, newDate)) {
//			System.out.println("Auction Inventory Sheet confirmed.");
//			System.out.println("Your Auction is booked on " + newDate.toString());
//		//	Auction newAuction = new Auction();
//		//	newAuction.setAuctionDate(newDate);
//			Auction newAuction = new Auction(myOrgName, myOrgID, newDate, null);
//			setMyCurrentAuction(newAuction);
//		    mySubmittedAuctions.add(newAuction);
//            AuctionCalendar.addAuction(newAuction);
//
//			//TODO ITEM INVENTORY SHEET PRINTOUT
//			System.out.println("Here is your inventory sheet: ");
//		}
//
//
//		Scan.close();
//		lineScan.close();
//	}
//	public void displaySubmittedAuctions() {
//		for(Auction a : mySubmittedAuctions){
//			System.out.println(a.toString());
//		}
//	}
//	public void addInventoryItem(AuctionItem theItem) {
//		Scanner theScanner = new Scanner(System.in);
//		displaySubmittedAuctions();   // if this shows the list of auctions
//
//		System.out.println("Select the Auction ID, where you want to add your item.");
//	    int theAuctionID = theScanner.nextInt();
//
//	    for(int i = 0; i < mySubmittedAuctions.size(); i++){
//			if(theAuctionID == mySubmittedAuctions.get(i).getauctionID()) {
//				myCurrentAuction = mySubmittedAuctions.get(i);
//				myAuctionItem = theItem;
//				myCurrentAuction.addInventoryItem(myAuctionItem);
//				// changes
//			}
//		}
//
//	}
//
//	public Auction getMyCurrentAuction() {
//		return myCurrentAuction;
//	}
//
//	public void setMyOrgName(String myOrgName) {
//		this.myOrgName = myOrgName;
//		this.myOrgID = myOrgName.hashCode();
//	}
//
//    public int getMyOrgID() {
//        return myOrgID;
//    }
//
//    public String getMyOrgName() {
//        return myOrgName;
//    }
//    //TODO: Delete this after testing?
//    public void setMyCurrentAuction(Auction myCurrentAuction) {
//        this.myCurrentAuction = myCurrentAuction;
//    }
//}