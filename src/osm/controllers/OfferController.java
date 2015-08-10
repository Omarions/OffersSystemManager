/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osm.controllers;

import java.sql.Date;
import java.util.List;
import osm.interfaces.IOfferController;
import osm.models.Offer;

/**
 *
 * @author Omar
 */
public class OfferController implements IOfferController{
    
   @Override
   public Offer getOfferByRefNo(String refNo){
       //TODO
       //return offer that has sepcified RefNo.
       return null;
   } 
   
   @Override
   public Offer getOfferBySubject(String subject){
       //TODO
       //return offer that has sepcified subject.
       return null;
   }
   
   @Override
   public Offer getOfferByDate(Date date){
       //TODO
       //return offer that has sepcified date.
       return null;
   }
   
   @Override
   public List<Offer> getOffersByPeriod(Date from, Date to){
       //TODO
       //return list of offers in sepcified period.
       return null;
   }
   
   @Override
   public List<Offer> getOffersByAmount(double minAmount){
       //TODO
       //return list of offers that has min total amount.
       return null;
   } 
   
   @Override
   public List<Offer> getOffersByStatus(String status){
       //TODO
       //return list of offers that has sepcified status.
       return null;
   }
   
   @Override
   public boolean addOffer(Offer newOffer){
       //TODO
       //add a new offer to db and return true if added successfuly and false if not.
       
       return false;
   }
   
   @Override
   public Offer editOffer(Offer oldOffer, Offer newOffer){
       //TODO
       //edit an old offer with the new one and return the new offer.
       return null;
   }
   
   @Override
   public boolean RemoveOffer(String refNo){
       //TODO
       //remove an sepecified offer with RefNo. return true if successful.
       return false;
   }
}
