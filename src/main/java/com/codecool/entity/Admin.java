package com.codecool.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter@NoArgsConstructor
@Entity
@SuperBuilder
@Table
public class Admin extends User {
    private String securityLevelDescription;
}
