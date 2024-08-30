package com.amc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PeriodePaginationAndImmatriculationDTO {
    private Date dateDebut;
    private Date dateFin;
    private int pageIndex;
    private int pageSize;
    String immatriculation;
}
