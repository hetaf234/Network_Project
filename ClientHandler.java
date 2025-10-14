/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Network_Project;
import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author hetaf
 */

public  class ClientHandler implements Runnable {
    private final Socket client;
    private final BufferedReader in;
  private final PrintWriter out;
    private final ArrayList<ClientHandler> clients; // if you want to broadcast events
    private final Server server;             // shared state / API
    private String username = null;

    public ClientHandler(Socket c, ArrayList<ClientHandler> clients, Server server) throws IOException {
        this.client = c;
        this.clients = clients;
        this.server = server;
        this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.out = new PrintWriter(client.getOutputStream(), true);
    }// end of constructor 
   @Override
   
    public void run() {
        try {
            out.println("OK CONNECTED");
            String line;
            while ((line = in.readLine()) != null) {
                String resp = handle(line.trim());
                out.println(resp);
                // Optional broadcast example after reserve:
                // if (resp.startsWith("CONFIRMED ")) outToAll("EVENT REFRESH");
            }//end of while
        } // end of try
        catch (IOException e) {
            // client disconnected
        } // end of catch
        finally {
            out.close();
            try { in.close(); } catch (IOException ignore) {}
        }// end of finally
    }// end of run
    // ===== command router (REGISTER, LOGIN, SEARCH, RESERVE, LIST, CANCEL) =====
    private String handle(String cmd) {
        try {
            if (cmd.equals("PING")) return "PONG";

            if (cmd.startsWith("REGISTER ")) {
                String[] p = cmd.substring(9).split(" ");
                if (p.length != 2) return "ERROR message=RegisterFormat";
                String res = server.register(p[0], p[1]);
                if (res.startsWith("OK")) username = p[0];
                return res;
            }

            if (cmd.startsWith("LOGIN ")) {
                String[] p = cmd.substring(6).split(" ");
                if (p.length != 2) return "ERROR message=LoginFormat";
                String res = server.login(p[0], p[1]);
                if (res.startsWith("OK")) username = p[0];
                return res;
            }

            // auth needed from here
            if (username == null) return "ERROR message=NotAuthenticated";

            if (cmd.startsWith("SEARCH ")) {
                Map<String,String> kv = parseKV(cmd.substring(7));
                String type  = u(kv.get("type"));
                int seats    = toInt(kv.get("seats"), -1);
                LocalDate st = toDate(kv.get("start"));
                int days     = toInt(kv.get("days"), 0);
                if (st == null) return "ERROR message=BadDate";
                return server.search(type, seats, st, days);
            }

            if (cmd.startsWith("RESERVE ")) {
                Map<String,String> kv = parseKV(cmd.substring(8));
                String car   = val(kv.get("car"));
                LocalDate st = toDate(kv.get("start"));
                int days     = toInt(kv.get("days"), 0);
                if (st == null) return "ERROR message=BadDate";
                return server.reserve(username, car, st, days);
            }

            if (cmd.equals("LIST")) return server.listBookings(username);

            if (cmd.startsWith("CANCEL ")) {
                Map<String,String> kv = parseKV(cmd.substring(7));
                String bid = val(kv.get("bookingId"));
                return server.cancel(username, bid);
            }

            return "ERROR message=UnknownCommand";
        } catch (Exception e) {
            return "ERROR message=" + e.getClass().getSimpleName();
        }
    }

    // ===== lab-style broadcast helper (kept for compatibility) =====
    @SuppressWarnings("unused")
    private void outToAll(String msg) {
        synchronized (clients) {
            for (ClientHandler c : clients) c.out.println(msg);
        }
    }

    // ===== small parsing helpers =====
    private Map<String,String> parseKV(String s) {
        Map<String,String> m = new HashMap<>();
        for (String part : s.split(" ")) {
            if (part.isBlank()) continue;
            String[] kv = part.split("=", 2);
            if (kv.length == 2) m.put(kv[0], kv[1]);
        }
        return m;
    }
    private String u(String v) { return v == null ? "" : v.toUpperCase(); }
    private String val(String v) { return v == null ? "" : v; }
    private int toInt(String s, int def) { try { return Integer.parseInt(s); } catch (Exception e) { return def; } }
    private LocalDate toDate(String s) { try { return LocalDate.parse(s); } catch (Exception e) { return null; } }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

