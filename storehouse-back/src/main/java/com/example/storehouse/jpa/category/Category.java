package com.example.storehouse.jpa.category;

import com.example.storehouse.jpa.abstractentity.AbstractNamedEntity;
import com.example.storehouse.jpa.item.Item;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Category")
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category extends AbstractNamedEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    // этот момент можно будет обдумать, стоит ли реализовывать обработку "вложенных" изменений
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonBackReference
    private List<Item> items;
}
