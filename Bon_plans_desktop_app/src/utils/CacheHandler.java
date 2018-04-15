/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author touir
 */
public class CacheHandler {
    
    private static final String cacheFolderURL = "./cache";
    private static File cacheFolder;
    private static Map<String,Timestamp> mapOfCachedFileNames;
    
    private CacheHandler(){}
    
    private static void deleteFolder(File folder){
        File[] files = folder.listFiles();
        if(files!=null) { //some JVMs return null for empty dirs
            for(File f: files) {
                if(f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }
    
    private static boolean initCache(){
        try{
            if(cacheFolder == null){
                cacheFolder = new File(cacheFolderURL);
                if(!cacheFolder.exists()){
                    cacheFolder.mkdir();
                }
                mapOfCachedFileNames = new HashMap<>();
                for(File file : cacheFolder.listFiles()){
                    if(file.isFile()){
                        file.delete();
                    }
                    else{
                        deleteFolder(file);
                    }
                }
            }
            return true;
        }
        catch(Exception e){
            LogHandler.handleException(e);
            return false;
        }
        
    }
    
    public static List<File> getAllCachedFiles(){
        if(initCache()){
            List<File> result = new ArrayList<>();
            for(File file : cacheFolder.listFiles()){
                if(file.isFile()){
                    result.add(file);
                }
            }
            return result;
        }
        else{
            return new ArrayList<>();
        }
    }
    
    public static File getCachedFileByName(String name){
        try{
            if(initCache()){
                return new File(cacheFolderURL+"/"+name);
            }
        }
        catch(Exception e){
            LogHandler.handleException(e);
        }
        return null;
    }
    
    public static boolean isCachedFile(String name){
        if(initCache()){
            return mapOfCachedFileNames.containsKey(name);
        }
        return false;
    }
    
    public static boolean addToCache(byte[] fileBinary, String fileName, Timestamp timestamp){
        try{
            if(initCache()){
                File file = new File(cacheFolderURL+"/"+fileName);
                BufferedOutputStream bs = 
                        new BufferedOutputStream(
                                new FileOutputStream(file)
                        );
                bs.write(fileBinary);
                bs.close();
                mapOfCachedFileNames.put(fileName,timestamp);
                return true;
            }
        }
        catch(Exception e){
            LogHandler.handleException(e);
        }
        return false;
    }
    
    public static boolean isLastVersion(String idFile, Timestamp timestamp){
        Timestamp cachedTimestamp = mapOfCachedFileNames.get(idFile);
        return (cachedTimestamp.compareTo(timestamp) == 0);
    }
}
