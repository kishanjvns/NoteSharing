package com.tech.kj.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Contacts extends BaseEntity{
    @Column(unique = true)
    private String contact;
    private boolean isPrimary;

    private boolean isVerified;
    @ManyToOne
    private Users user;
}
