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
import team.six.mastermind.common.MMPacket;

/**
 *
 * @author 1437203
 */
public class MMServer {
    private final Logger log = LoggerFactory.getLogger(getClass().getName());
    
    private ServerSocket serverSocket;
    
    private static final int BUFFSIZE = 1;
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
        
        boolean once = true;
        
        // TESTING: making temp client to test server interaction in one runtime
        MMClient clienttest = new MMClient(new Socket("localhost", 50000));
        
        // Block here until connection is made with client
        Socket client = serverSocket.accept();
        
        for (;;) {
            // TESTING: sending req
            clienttest.sendStartReq();
            
            while(totalBytesRcvd < byteBuffer.length){
                // Check for client disconnection and throw exception
                if((bytesRcvd = client.getInputStream().read(byteBuffer, totalBytesRcvd, byteBuffer.length - totalBytesRcvd)) == -1) {
                    throw new SocketException("Connection was interrupted!");
                }
                totalBytesRcvd += bytesRcvd;
                
                // TESTING
                log.info("Got new packets:");
                log.info(byteBuffer.toString());   
            }
            totalBytesRcvd = 0;
            
            clienttest.disconnect();
            
            // Check for new game request
            
            
            // Check for client game interaction
            
            // Check for client disconnection

            // TESTING: making client disconnecting
        }
    }
}
