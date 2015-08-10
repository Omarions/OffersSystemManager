/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osm.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Omar
 */
@Entity
@Table(name = "offer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Offer_1.findAll", query = "SELECT o FROM Offer_1 o"),
    @NamedQuery(name = "Offer_1.findByRefNo", query = "SELECT o FROM Offer_1 o WHERE o.refNo = :refNo"),
    @NamedQuery(name = "Offer_1.findByDate", query = "SELECT o FROM Offer_1 o WHERE o.date = :date"),
    @NamedQuery(name = "Offer_1.findByProject", query = "SELECT o FROM Offer_1 o WHERE o.project = :project"),
    @NamedQuery(name = "Offer_1.findByClientId", query = "SELECT o FROM Offer_1 o WHERE o.clientId = :clientId"),
    @NamedQuery(name = "Offer_1.findByContactName", query = "SELECT o FROM Offer_1 o WHERE o.contactName = :contactName"),
    @NamedQuery(name = "Offer_1.findByStatus", query = "SELECT o FROM Offer_1 o WHERE o.status = :status")})
public class Offer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ref_no")
    private String refNo;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date offer_date;
    @Basic(optional = false)
    @Column(name = "project")
    private String project;
    @Basic(optional = false)
    @Column(name = "client_id")
    private int clientId;
    @Basic(optional = false)
    @Column(name = "contact_name")
    private String contactName;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "refNo")
    private Collection<ItemsOffers> itemsOffersCollection;

    public Offer() {
    }

    public Offer(String refNo) {
        this.refNo = refNo;
    }

    public Offer(String refNo, Date date, String project, int clientId, String contactName, String status) {
        this.refNo = refNo;
        this.offer_date = date;
        this.project = project;
        this.clientId = clientId;
        this.contactName = contactName;
        this.status = status;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public Date getOfferDate() {
        return offer_date;
    }

    public void setOfferDate(Date offerDate) {
        this.offer_date = offerDate;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<ItemsOffers> getItemsOffersCollection() {
        return itemsOffersCollection;
    }

    public void setItemsOffersCollection(Collection<ItemsOffers> itemsOffersCollection) {
        this.itemsOffersCollection = itemsOffersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (refNo != null ? refNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Offer)) {
            return false;
        }
        Offer other = (Offer) object;
        if ((this.refNo == null && other.refNo != null) || (this.refNo != null && !this.refNo.equals(other.refNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "osm.models.Offer_1[ refNo=" + refNo + " ]";
    }
    
}
