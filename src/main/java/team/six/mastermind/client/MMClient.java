/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team.six.mastermind.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author 1437203
 */
public class MMClient {
    private Socket mmServer;
    OutputStream out;
    
    private static final int BUFFSIZE = 32;
    private byte[] incPackets = new byte[BUFFSIZE];
    private int incPacketsSize;

    public MMClient(Socket mmServer) throws IOException {
        this.mmServer = mmServer;
        this.out = mmServer.getOutputStream();
    }

    public void sendStartReq() throws IOException {
        OutputStream out = mmServer.getOutputStream();

        // Send connection request
        out.write(0x00000000);
        
        mmServer.close();
    }
}
