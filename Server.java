/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Network_Project;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author hetaf
 */
public class Server {
   
   
    // carId -> Car
    final Map<String, Car> cars = new ConcurrentHashMap<>();
    // bookingId -> Reservation
    final Map<String, Reservation> bookingsById = new ConcurrentHashMap<>();
     // availability index: date -> set of carIds booked that day
    final Map<LocalDate, Set<String>> occupancyByDate = new ConcurrentHashMap<>();
    
    /* username -> list of bookingIds
    IN PHASE 2
    final Map<String, List<String>> userBookings = new ConcurrentHashMap<>();
   
     username -> password
    final Map<String, String> users = new ConcurrentHashMap<>();
    */
    //============ START HERE=========
       public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.seedInventory(); // 20 cars (10 AUTO, 10 MANUAL)
        try (ServerSocket serverSocket = new ServerSocket(9090)) { // same port as lab
            System.out.println("Waiting for client connection");
            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("Connected to client");
                ClientHandler clientThread = new ClientHandler(client, clients, server); // pass shared server
                synchronized (clients) { clients.add(clientThread); }
                new Thread(clientThread).start();
            } // while
        } // try-with-resources
    } // main
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   
}//end of class 
