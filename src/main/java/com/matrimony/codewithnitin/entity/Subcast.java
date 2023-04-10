package com.matrimony.codewithnitin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subcast")
public class Subcast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subcastid;

    
    private String subcastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cast_id")
    private Cast cast;
    
    private String active;

	private String description;	

    // getters and setters
}


