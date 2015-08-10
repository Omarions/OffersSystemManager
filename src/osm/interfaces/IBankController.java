/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osm.interfaces;

import osm.models.Bank;

/**
 *
 * @author Omar
 */
public interface IBankController {
     
    public Bank getBankByID(int bankID);
   
   public boolean addBank(Bank newBank);
   
   public Bank editOffer(Bank oldBank, Bank newBank);
   
   public boolean RemoveCompany(int bankID);

}
