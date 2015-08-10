/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osm.interfaces;

import java.sql.Date;
import java.util.List;
import osm.models.Offer;

/**
 *
 * @author Omar
 */
public interface IOfferController {
    public Offer getOfferByRefNo(String refNo);
   
   public Offer getOfferBySubject(String subject);
   
   public Offer getOfferByDate(Date date);
   
   public List<Offer> getOffersByPeriod(Date from, Date to);
   
   public List<Offer> getOffersByAmount(double minAmount);
   
   public List<Offer> getOffersByStatus(String status);
   
   public boolean addOffer(Offer newOffer);
   
   public Offer editOffer(Offer oldOffer, Offer newOffer);
   
   public boolean RemoveOffer(String refNo);
}
