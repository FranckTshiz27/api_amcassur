package com.amc.model.myamc;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity(name = "sinistre")
public class Sinistre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    private String immatriculation;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sinistre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<File> files;

    private double latitude;
    private double longitude;

    @Column(name = "phone_number", length = 25)
    String phoneNumber;
    @Column(name = "dec_sinistre")
    String decSinistre;

    @CreationTimestamp
    private Date createdat;

    @UpdateTimestamp
    private Date updatedat;

    // @Column(name = "is_viewed")
    // private boolean isViewed;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Account user;

    // @JsonIgnore
    // @OneToMany(fetch = FetchType.LAZY, mappedBy = "sinistre", cascade =
    // CascadeType.ALL, orphanRemoval = true)
    // private List<TreatementStatus> treatementStatus;
}
