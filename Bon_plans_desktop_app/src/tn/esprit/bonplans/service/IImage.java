/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.service;

import java.io.File;
import java.sql.Timestamp;
import tn.esprit.bonplans.entity.Image;
import utils.service.GenericServiceInterface;

/**
 *
 * @author touir
 */
public interface IImage  extends GenericServiceInterface<Image>{
    
    public Timestamp getTimeStamp(Image image);
    public File getImageFile(Image image);
    
}
