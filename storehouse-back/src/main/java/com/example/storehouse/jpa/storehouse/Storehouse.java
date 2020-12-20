package com.example.storehouse.jpa.storehouse;

import com.example.storehouse.jpa.abstractentity.AbstractNamedEntity;
import com.example.storehouse.jpa.item.ItemStorehouse;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Storehouses")
@Table(name = "storehouses")
@NoArgsConstructor
public class Storehouse extends AbstractNamedEntity {

    @OneToMany(mappedBy = "storehouse", cascade = CascadeType.ALL)
    private Set<ItemStorehouse> itemStorehouses = new HashSet<>();

}
