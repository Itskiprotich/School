/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import Alerts.AlertDisplay;
import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Keeprawteach
 */
public class ConfigFile {
    public static final String CONFIGURATION_FILE = "library.txt";

    public static void writePreferenceToFile(ConfigFile preferences) {
        Writer writer = null;
        try {
            Gson gson = new Gson();
            writer = new FileWriter(CONFIGURATION_FILE);
            gson.toJson(preferences, writer);

            AlertDisplay.showSimpleAlert("Success", "Settings updated");
        } catch (IOException ex) {
            Logger.getLogger(ConfigFile.class.getName()).log(Level.SEVERE, null, ex);
            AlertDisplay.showErrorMessage(ex, "Failed", "Cant save configuration file");
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                
            }
        }
    }
    
    String username;
    String password;
    
    int nDaysWithoutFine;
    float finePerDay;
    

    public ConfigFile() {
        nDaysWithoutFine = 14;
        finePerDay = 2;
        
        username ="Admin";
        setPassword ("12345");
    }

    public int getnDaysWithoutFine() {
        return nDaysWithoutFine;
    }

    public void setnDaysWithoutFine(int nDaysWithoutFine) {
        this.nDaysWithoutFine = nDaysWithoutFine;
    }

    public float getFinePerDay() {
        return finePerDay;
    }

    public void setFinePerDay(float finePerDay) {
        this.finePerDay = finePerDay;
    }
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }  

    private static void initUserConfig() {
        Writer writer = null;
        try {
            ConfigFile configFile = new ConfigFile();
            Gson gson = new Gson();
            writer = new FileWriter(CONFIGURATION_FILE);
            gson.toJson(configFile, writer);
        } catch (IOException ex) {
            Logger.getLogger(ConfigFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(ConfigFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static ConfigFile getPreferences() {
        Gson gson = new Gson();
        ConfigFile configFile = new ConfigFile();
        try {
            configFile = gson.fromJson(new FileReader(CONFIGURATION_FILE), ConfigFile.class);
        } catch (FileNotFoundException ex) {
            initUserConfig();
            Logger.getLogger(ConfigFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return configFile;
    }

    public void setPassword(String password) {
        if (password.length() < 16) {
            this.password = DigestUtils.shaHex(password);
        }else
            this.password = password;
    }
    
}
