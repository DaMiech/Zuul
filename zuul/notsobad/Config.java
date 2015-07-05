package zuul.notsobad;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 *
 *  This class reads the config file at the start of the game and initializes the defined aliases.
 *  It also overwrites the old config file with a new one after the user finishes playing.
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
        try(BufferedReader reader = new BufferedReader(new FileReader("zuulconfig.txt"))) {
            String line = reader.readLine();
            StringTokenizer tokenizer;
            while (line != null) {
                tokenizer = new StringTokenizer(line);
                this.processTokenizedLine(tokenizer);
                line = reader.readLine();
            }
        }catch(Exception exc){
            System.out.println("Unable to read config file. "+exc.getMessage());
        }
    }

    private void processTokenizedLine(final StringTokenizer tokenizer) {
        if(tokenizer.hasMoreTokens()){
            String command = tokenizer.nextToken();
            if(aliases.matchStringtoCommands(command) != Commands.EMPTY){
                while (tokenizer.hasMoreTokens()){
                    aliases.addCommand(command, tokenizer.nextToken());
                }
            }
        }
    }

    public CommandSynonym getAliases(){
        return  aliases;
    }

    public void   writeConfig(){
        BufferedWriter writer = null;
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
            System.out.println("A fatal error occurred while writing the config file. "+ex.getMessage());
        } finally {
            try {writer.close();} catch (Exception ex) {
                System.out.println("A fatal error occurred while writing the config file. "+ex.getMessage());
            }
        }
    }
}
