/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team.six.mastermind.server;

import java.net.*;
import java.io.*;
import java.util.*;
import team.six.mastermind.client.MMClient;
import team.six.mastermind.common.MMPacket;

/**
 *
 * @author 1437203
 */
public class MMServer {

    private ServerSocket serverSocket;
    private List<Socket> clientSockets;
    
    private static final int BUFFSIZE = 32;
    private byte[] incPackets = new byte[BUFFSIZE];
    private int incPacketsSize;

    public MMServer(ServerSocket serversock) throws IOException {
        this.serverSocket = serversock;
        
        // Automaticaly start the server
        this.start();
    }

    public void start() throws IOException {
        System.out.println("Server initialized!");
        System.out.println();
        
        boolean once = true;
        
        
        for (;;) {
            
            
            // Check for potential new connections
            Socket client = serverSocket.accept();
            
            // Check for new game request
            while ((incPacketsSize = client.getInputStream().read(incPackets)) != -1) {
                //Add new connection to list if requested
                if(client.getInputStream().read() == 0x00000000){
                    clientSockets.add(client);
                    // TODO: Send a response
                    System.out.println("Client added: " + client.getInetAddress());
                    System.out.println("Clients connected: " + clientSockets.size());
                    System.out.println();
                }
                else{
                    break;
                }
            }
            
            /*
            // Act upon all current connections
            for(Socket conn: clientSockets){
                
                // Listen
                while ((incPacketsSize = conn.getInputStream().read(incPackets)) != -1) {
                    // Fill buffer
                }
                
                // Interpret message
                
                // Reply message
            }*/
            
            // Check for disconnections
            
            
            // clntSock.close();
            if(once){
                MMClient clienttest = new MMClient(new Socket("192.168.0.176", 50000));
                once = false;
            }
        }
    }
}
