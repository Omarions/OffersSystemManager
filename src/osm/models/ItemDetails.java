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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "item_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemDetails.findAll", query = "SELECT i FROM ItemDetails i"),
    @NamedQuery(name = "ItemDetails.findByItemDetailsId", query = "SELECT i FROM ItemDetails i WHERE i.itemDetailsId = :itemDetailsId"),
    @NamedQuery(name = "ItemDetails.findByDescription", query = "SELECT i FROM ItemDetails i WHERE i.description = :description"),
    @NamedQuery(name = "ItemDetails.findByPrice", query = "SELECT i FROM ItemDetails i WHERE i.price = :price")})
public class ItemDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "item_details_id")
    private Integer itemDetailsId;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "price")
    private double price;
    @JoinColumn(name = "item_id", referencedColumnName = "item_id")
    @ManyToOne(optional = false)
    private int itemId;

    public ItemDetails() {
    }

    public ItemDetails(Integer itemDetailsId) {
        this.itemDetailsId = itemDetailsId;
    }

    public ItemDetails(Integer itemDetailsId, String description, double price) {
        this.itemDetailsId = itemDetailsId;
        this.description = description;
        this.price = price;
    }

    public Integer getItemDetailsId() {
        return itemDetailsId;
    }

    public void setItemDetailsId(Integer itemDetailsId) {
        this.itemDetailsId = itemDetailsId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemDetailsId != null ? itemDetailsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemDetails)) {
            return false;
        }
        ItemDetails other = (ItemDetails) object;
        if ((this.itemDetailsId == null && other.itemDetailsId != null) || (this.itemDetailsId != null && !this.itemDetailsId.equals(other.itemDetailsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "osm.models.ItemDetails[ itemDetailsId=" + itemDetailsId + " ]";
    }
    
}
