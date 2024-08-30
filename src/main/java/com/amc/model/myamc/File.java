package com.amc.model.myamc;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.amc.enums.DocType;

import lombok.Data;

@Data
@Entity(name = "file")
public class File {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  private UUID id;

  @Column()
  private String img;

  @Column(name = "type", nullable = false)
  @Enumerated(EnumType.STRING)
  private DocType type;

  @ManyToOne
  @JoinColumn(name = "sinistre", referencedColumnName = "id")
  private Sinistre sinistre;
}
