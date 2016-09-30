/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team.six.mastermind.common;

import java.net.*;
import java.io.*; 

/**
 *
 * @author 1437203
 */
public class MMPacket {
    // Bytes to be sent
    private byte mmBytes[] = new byte[4];
    
    public MMPacket(byte comp1, byte comp2, byte comp3, byte comp4) throws IOException{
        // Putting components into byte array
        mmBytes[0] = comp1;
        mmBytes[1] = comp2;
        mmBytes[2] = comp3;
        mmBytes[3] = comp4;
    }

    public byte[] getBytes(){
        return mmBytes;
    }
}
