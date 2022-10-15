
package Classes;

import java.io.IOException;

public class Person extends Player{
    public int score;
    public Person(char letter, String name)
    {
        this.letter = letter;
        this.name = name;
        try{
        this.score = Scoreboard.getPlayerScore(name);
        }
        catch(IOException e){
        }
   }
    public void setLetter(char letter) {
        this.letter = letter;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
