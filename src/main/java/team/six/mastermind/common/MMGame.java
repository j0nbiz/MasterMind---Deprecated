package team.six.mastermind.common;

import java.io.IOException;
import java.util.Random;

/**
 *
 * @author j0nbiz
 */
public class MMGame {
    
    private MMPacket answer;
    
    public MMGame() throws IOException {
        this.answer = seedAnswer();
    }

    public MMGame(MMPacket answer) {
        this.answer = answer;
    }

    private MMPacket seedAnswer() throws IOException {
        Random ran = new Random();
        
        // Return packet with random components
        return new MMPacket((byte) (ran.nextInt(7) + 1), (byte) (ran.nextInt(7) + 1), (byte) (ran.nextInt(7) + 1), (byte) (ran.nextInt(7) + 1));
    }
    
    public MMPacket getAnswer(){
        return answer;
    }
}
