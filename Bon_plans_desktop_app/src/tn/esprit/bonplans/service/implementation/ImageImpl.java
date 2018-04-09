/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.service.implementation;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import tn.esprit.bonplans.entity.Image;
import tn.esprit.bonplans.service.IImage;
import utils.CacheHandler;
import utils.Converter;
import utils.database.DatabaseHandler;
import utils.service.GenericServiceImplementation;

/**
 *
 * @author touir
 */
public class ImageImpl extends GenericServiceImplementation<Image> implements IImage{
    
    public ImageImpl(){
        super(Image.class);
    }
    
    public Timestamp getTimeStamp(Image image){
        String sql = "SELECT timestamp FROM image WHERE idImage = ?";
        List<Map<String, Object>> result = DatabaseHandler.select(sql, new Object[]{image.getIdImage()});
        if(result != null && !result.isEmpty()){
            return (Timestamp) result.get(0).get("timestamp");
        }
        return null;
    }
    
    public File getImageFile(Image image) {
        String idCachedImage = image.getIdImage()+"_"+image.getNomImage();
        Timestamp lastTimestamp = getTimeStamp(image);
        if(CacheHandler.isCachedFile(idCachedImage) && CacheHandler.isLastVersion(idCachedImage,lastTimestamp)){
            return CacheHandler.getCachedFileByName(idCachedImage);
        }
        else{
            CacheHandler.addToCache(image.getImageBytes(), idCachedImage, image.getTimestamp());
            return CacheHandler.getCachedFileByName(idCachedImage);
        }
        
    }

    @Override
    public Image save(Image entity) {
        String sql = "INSERT INTO image(nomImage,imageBytes) VALUES(?,?)";
        if(DatabaseHandler.update(
                        sql, 
                        new Object[]{entity.getNomImage(), entity.getImageBytes()}))
        {
            sql = "SELECT * FROM image WHERE nomImage = ? AND imageBytes = ? ORDER BY idImage DESC";
            List<Map<String, Object>> result = DatabaseHandler.select(sql, new Object[]{entity.getNomImage(), entity.getImageBytes()});
            if(result != null && !result.isEmpty()){
                return Converter.convertMapToEntity(result.get(0), Image.class);
            }
        }
        return null;
    }

    @Override
    public Image update(Image entity) {
        String sql = "UPADE image SET nomImage = ?, imageBytes = ? WHERE idImage = ?";
        if(DatabaseHandler.update(
                        sql, 
                        new Object[]{entity.getNomImage(), entity.getImageBytes(), entity.getIdImage()}))
        {
            sql = "SELECT * FROM image WHERE idImage = ?";
            List<Map<String, Object>> result = DatabaseHandler.select(sql, new Object[]{entity.getIdImage()});
            if(result != null && !result.isEmpty()){
                return Converter.convertMapToEntity(result.get(0), Image.class);
            }
        }
        return null;
    }
    
    
}
