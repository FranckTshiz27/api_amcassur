package com.amc.model.myamc;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "cotp")
@Entity
@Data
public class COtp {
    @Id
    private String username;
    private String code;
    private boolean enabled;
    private long datecreate;
}
