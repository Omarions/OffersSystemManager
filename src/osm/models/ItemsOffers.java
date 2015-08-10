/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osm.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Omar
 */
@Entity
@Table(name = "items_offers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemsOffers.findAll", query = "SELECT i FROM ItemsOffers i"),
    @NamedQuery(name = "ItemsOffers.findByQuantity", query = "SELECT i FROM ItemsOffers i WHERE i.quantity = :quantity"),
    @NamedQuery(name = "ItemsOffers.findByTax", query = "SELECT i FROM ItemsOffers i WHERE i.tax = :tax"),
    @NamedQuery(name = "ItemsOffers.findByItemsOffersId", query = "SELECT i FROM ItemsOffers i WHERE i.itemsOffersId = :itemsOffersId")})
public class ItemsOffers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "quantity")
    private int quantity;
    @Basic(optional = false)
    @Column(name = "tax")
    private double tax;
    @Id
    @Basic(optional = false)
    @Column(name = "items_offers_id")
    private Integer itemsOffersId;
    @JoinColumn(name = "item_id", referencedColumnName = "item_id")
    @ManyToOne(optional = false)
    private Item itemId;
    @JoinColumn(name = "ref_no", referencedColumnName = "ref_no")
    @ManyToOne(optional = false)
    private Offer refNo;

    public ItemsOffers() {
    }

    public ItemsOffers(Integer itemsOffersId) {
        this.itemsOffersId = itemsOffersId;
    }

    public ItemsOffers(Integer itemsOffersId, int quantity, double tax) {
        this.itemsOffersId = itemsOffersId;
        this.quantity = quantity;
        this.tax = tax;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public Integer getItemsOffersId() {
        return itemsOffersId;
    }

    public void setItemsOffersId(Integer itemsOffersId) {
        this.itemsOffersId = itemsOffersId;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
    }

    public Offer getRefNo() {
        return refNo;
    }

    public void setRefNo(Offer refNo) {
        this.refNo = refNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemsOffersId != null ? itemsOffersId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemsOffers)) {
            return false;
        }
        ItemsOffers other = (ItemsOffers) object;
        if ((this.itemsOffersId == null && other.itemsOffersId != null) || (this.itemsOffersId != null && !this.itemsOffersId.equals(other.itemsOffersId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "osm.models.ItemsOffers[ itemsOffersId=" + itemsOffersId + " ]";
    }
    
}
