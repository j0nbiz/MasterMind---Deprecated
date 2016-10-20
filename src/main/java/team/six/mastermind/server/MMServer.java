/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team.six.mastermind.server;

import java.net.*;
import java.io.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import team.six.mastermind.client.MMClient;
import team.six.mastermind.common.MMGame;
import team.six.mastermind.common.MMPacket;

/**
 *
 * @author 1437203
 */
public class MMServer {
    private final Logger log = LoggerFactory.getLogger(getClass().getName());
    
    private ServerSocket serverSocket;
    
    private static final int BUFFSIZE = 4; // Packet holds 4 components
    private byte[] byteBuffer = new byte[BUFFSIZE];
    private int bytesRcvd;
    private int totalBytesRcvd = 0;

    public MMServer(ServerSocket serversock) throws IOException {
        this.serverSocket = serversock;
        
        // Automaticaly start the server
        this.start();
    }

    public void start() throws IOException {
        log.info("Server initialized!");
        log.info("");
        
        // TESTING: making temp client to test server interaction in one runtime
        MMClient clienttest = new MMClient(new Socket("localhost", 50000));
        
        // Block here until connection is made with client
        Socket client = serverSocket.accept();
        
        // Create packet to interpret response
        MMPacket packet = new MMPacket();
        
        int counter = 0;
        
        for (;;) {
            // TESTING: sending req
            clienttest.sendGuess(new MMPacket((byte) 5, (byte) 5, (byte) 5, (byte) 5));
            clienttest.sendStartReq();
            clienttest.sendGuess(new MMPacket((byte) 2, (byte) 4, (byte) 6, (byte) 7));
            
            while(totalBytesRcvd < byteBuffer.length){
                // Check for client disconnection and throw exception
                if((bytesRcvd = client.getInputStream().read(byteBuffer, totalBytesRcvd, byteBuffer.length - totalBytesRcvd)) == -1) {
                    throw new SocketException("Connection was interrupted!");
                }
                totalBytesRcvd += bytesRcvd;
                
                // Fill current packet
                packet.decode(byteBuffer);
                
                // TESTING
                log.info("Got new packet:");
                log.info("Components: " + packet.toString());
                log.info("");
                
                // Check for new game request
                if(packet.equals(new MMPacket((byte) 0, (byte) 0, (byte) 0, (byte) 0))){
                    MMGame clientGame = new MMGame();
                   
                    log.info("Game created! (Answer = " + clientGame.getAnswer().toString() + ")");
                    log.info(""); 
                }
            }
            totalBytesRcvd = 0;
            counter++;
            
            if(counter == 3){
                clienttest.disconnect();
            }
            
        }
    }
}
