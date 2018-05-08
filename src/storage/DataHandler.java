package storage;
 
import auctiondata.*;
import users.*;
 
import java.io.*;
import java.time.LocalDateTime;
 
/**
 * A class that allows the program to handle data through serialization and creating
 * sample data for testing as necessary.
 * @author Tanner Brown
 * @version 6 May 2018
 *
 */
public class DataHandler {
    private static final String USERDB_FILE_NAME = "users.ser";
    private static final String AUCTIONDB_FILE_NAME = "calendar.ser";
 
 
    AuctionCalendar myAuctionCalendar;
    UserDB myUserDB;
 
    /**
     * Constructs data by serializing or initializing new data as necessary.
     */
    public DataHandler(){
        if(new File(USERDB_FILE_NAME).exists()
                && new File(AUCTIONDB_FILE_NAME).exists())
            deserialize();
            //Else, initialize with sample data.
        else
            initializeData();
 
        createAdditionalSampleData();
    }
 
    /**
     * A method for developers to add sample data to be serialized as necessary.
     */
    private void createAdditionalSampleData() {
 
 
 
    }
 
    /**
     * Deserializes userDB and auctionCalendar data.
     */
    public void deserialize(){
        Boolean badData = false;
        try {
            FileInputStream fileIn = new FileInputStream(USERDB_FILE_NAME);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            myUserDB = (UserDB)objectIn.readObject();
            objectIn.close();
        } catch (IOException e) {
            System.out.println("IOException user "
                    + "deserialization is caught. Initializing Sample Data.");
            initializeData();
            badData = true;
        } catch (ClassNotFoundException e){
            System.out.println("User database "
                    + "class not found. Initializing Sample Data.");
            initializeData();
            badData = true;
        }
        if(!badData) {
            try {
                FileInputStream fileIn = new FileInputStream(AUCTIONDB_FILE_NAME);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                myAuctionCalendar = (AuctionCalendar) objectIn.readObject();
                objectIn.close();
            } catch (IOException e) {
                System.out.println("IOException Auction Data "
                        + "deserialization is caught. Using Sample Data");
 
            } catch (ClassNotFoundException e) {
                System.out.println("Auction Database class "
                        + "not found. Using Sample Data");
 
            }
        }
    }
   
    /**
     * Initializes new data in case serialization fails.
     */
    public void initializeData(){
 
        myUserDB = new UserDB();
        myAuctionCalendar = new AuctionCalendar();
        //create users
        User contactUser =
                new ContactPerson("Contact", "McContact", "contact@contact.com");
        ((ContactPerson) contactUser).setMyOrgName("Pat's Pneumonic Penguin Preservation");
        User bidderUser =
                new Bidder("Bidder","McBidder", "bidder@bidder.com");
 
        //create auctions
        Auction auction1 =
                new Auction(((ContactPerson) contactUser).getMyOrgName(),
                ((ContactPerson) contactUser).getMyOrgID(),
                LocalDateTime.of(2018, 05, 30, 10, 00),
                null);
 
        Auction auction2 = new Auction("#SaveTheDoDo", "#SaveTheDoDo".hashCode(),
                LocalDateTime.of(2018, 05, 30, 15, 00),
                null);
 
        AuctionItem item1 = new AuctionItem(20.00, "Penguin Pre-Breathers");
        AuctionItem item2 = new AuctionItem(50.00, "I'm out of clever names");
        auction1.addInventoryItem(item1);
        auction1.addInventoryItem(item2);
 
        ((ContactPerson) contactUser).setMyCurrentAuction(auction1);
        myAuctionCalendar.addAuction(auction1);
        myAuctionCalendar.addAuction(auction2);
        myUserDB.addUser(bidderUser);
        myUserDB.addUser(contactUser);
    }
   
    /**
     * Serializes the data to be used in the future.
     */
    public void serialize(){
 
//Delete old files
        //new File(USERDB_FILE_NAME).delete();
        //new File(AUCTIONDB_FILE_NAME).delete();
        //Serialize users
        try{
            FileOutputStream fileOut = new FileOutputStream(USERDB_FILE_NAME);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(myUserDB);
            fileOut.close();
            objectOut.close();
        }catch (IOException e){
            System.out.println("IOException Serializing User DB");
        }
 
        //Serialize Auctions
        try{
            FileOutputStream fileOut = new FileOutputStream(AUCTIONDB_FILE_NAME);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(myAuctionCalendar);
            fileOut.close();
            objectOut.close();
        }catch (IOException e){
            System.out.println("IOException Serializing Auction DB");
        }
    }
    /**
     * Getter for an auction Calendar
     * @return the auctionCalendar
     */
    public AuctionCalendar getMyAuctionCalendar() {
        return myAuctionCalendar;
    }
    /**
     * Getter for a user database.
     * @return the userDB.
     */
    public UserDB getMyUserDB() {
        return myUserDB;
    }
}