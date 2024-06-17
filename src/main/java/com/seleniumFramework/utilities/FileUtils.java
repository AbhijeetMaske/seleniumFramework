package com.seleniumFramework.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileUtils {

	private static final Logger logger = LogManager.getLogger(FileUtils.class);
	
	/********************************************************************************************
     * Reads the content of a file.
     *
     * @param filePath the path of the file to read
     * @return the content of the file as a string
     * @throws IOException if an I/O error occurs
     *
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public static String readFile(String filePath) throws IOException {
    	try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            logger.error("Failed to read file: " + e.getMessage());
            throw e;
        }
    }
 
    /********************************************************************************************
     * Writes content to a file, creating the file if it doesn't exist.
     *
     * @param filePath the path of the file to write
     * @param content  the content to write to the file
     * @throws IOException if an I/O error occurs
     *
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public static void writeFile(String filePath, String content) throws IOException {
    	try {
            Files.write(Paths.get(filePath), content.getBytes());
        } catch (IOException e) {
            logger.error("Failed to write to file: " + e.getMessage());
            throw e;
        }
    }
    
    /********************************************************************************************
     * Deletes a file if it exists.
     *
     * @param filePath the path of the file to delete
     *
     * @author Abhijeet Maske Created June 27,2023
     * @version 1.0 June 27,2023
     ********************************************************************************************/
    public static void deleteFile(String filePath) {
    	try {
            Files.deleteIfExists(Paths.get(filePath));
        } catch (IOException e) {
            logger.error("Failed to delete file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}