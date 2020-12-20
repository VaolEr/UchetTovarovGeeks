package com.example.storehouse.model;

import com.example.storehouse.model.abstractentity.AbstractNamedEntity;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Storehouses")
@Table(name = "storehouses")
@NoArgsConstructor
public class Storehouse extends AbstractNamedEntity {

    @OneToMany(mappedBy = "storehouse", cascade = CascadeType.ALL)
    private Set<ItemStorehouse> itemStorehouses = new HashSet<>();

}
