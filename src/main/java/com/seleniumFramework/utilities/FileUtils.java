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
    	logger.info("Attempting to read file: {}", filePath);
    	try {
    		String content = new String(Files.readAllBytes(Paths.get(filePath)));
            logger.info("Successfully read file: {}", filePath);
            return content;
        } catch (IOException e) {
            logger.error("Failed to read file: {}", filePath, e);
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
    	 logger.info("Attempting to write to file: {}", filePath);
    	 try {
             Files.write(Paths.get(filePath), content.getBytes());
             logger.info("Successfully wrote to file: {}", filePath);
         } catch (IOException e) {
             logger.error("Failed to write to file: {}", filePath, e);
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
    	logger.info("Attempting to delete file: {}", filePath);
    	try {
            Files.deleteIfExists(Paths.get(filePath));
            logger.info("Successfully deleted file: {}", filePath);
        } catch (IOException e) {
            logger.error("Failed to delete file: {}", filePath, e);
            e.printStackTrace();
        }
    }
}