/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team.six.mastermind.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import team.six.mastermind.client.MMClient;
import team.six.mastermind.server.MMServer;

/**
 *
 * @author 1437203
 */
public class MMServerApp {
    
    // Create a server instance
    public static void main(String[] args) throws IOException, InterruptedException {
        MMServer server = new MMServer(new ServerSocket(50000));       
    }
}
