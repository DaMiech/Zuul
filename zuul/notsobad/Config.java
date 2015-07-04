package zuul.notsobad;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 *
 *  This class reads the config file at the start of the game and initializes the defined aliases.
 *
 *
 * Created by Michael on 03.07.2015.
 */
public class Config {
    private CommandSynonym aliases;

    public Config(){
        aliases = new CommandSynonym();
        this.readConfig();
    }

    private void readConfig() {

    }

    public CommandSynonym getAliases(){
        return  aliases;
    }

    public void   writeConfig(){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("zuulconfig.txt"), "utf-8"));
            for(ArrayList currentList : aliases.getSynonyms()){
                if(!currentList.contains("empty")){
                    for(Object currentAlias : currentList){
                        writer.write(currentAlias.toString()+" ");
                    }
                    writer.newLine();
                }
            }
        } catch (IOException ex) {
            //This exception should never occur
            System.out.println("A fatal error occurred while writing the config file.");
        } finally {
            try {writer.close();} catch (Exception ex) {
                //This exception should never occur
                System.out.println("A fatal error occurred while writing the config file.");
            }
        }
    }
}
