package me.glaremasters.superoof.updater;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by GlareMasters
 * Date: 7/21/2018
 * Time: 5:37 PM
 */
public class SpigotUpdater {

    private final int project;
    private URL checkURL;
    private String newVersion;
    private final JavaPlugin plugin;

    public SpigotUpdater(JavaPlugin plugin, int projectID) {
        this.plugin = plugin;
        this.newVersion = plugin.getDescription().getVersion();
        this.project = projectID;

        try {
            this.checkURL = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + projectID);
        } catch (MalformedURLException ex) {
            System.out.println("Could not check for plugin update.");
        }
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    /**
     * Check for the latest version of the plugin
     * @return the latest version of the plugin
     * @throws Exception the exception
     */
    public String getLatestVersion() throws Exception {
        URLConnection con = checkURL.openConnection();
        this.newVersion = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
        return newVersion;
    }

    /**
     * Get the URL of the plugin
     * @return URL of plugin
     */
    public String getResourceURL() {
        return "https://www.spigotmc.org/resources/" + project;
    }

    /**
     * Check for updates
     * @return if plugin version is the latest plugin version
     * @throws Exception I/O Exception
     */
    public boolean checkForUpdates() throws Exception {
        URLConnection con = checkURL.openConnection();
        this.newVersion = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
        return !plugin.getDescription().getVersion().equals(newVersion);
    }

}