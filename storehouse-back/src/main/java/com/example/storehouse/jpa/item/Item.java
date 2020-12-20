package com.example.storehouse.jpa.item;

import com.example.storehouse.jpa.abstractentity.AbstractNamedEntity;
import com.example.storehouse.jpa.category.Category;
import com.example.storehouse.jpa.supplier.Supplier;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

@Entity(name = "Item")
@Table(name = "items")
@Getter
@Setter
@NoArgsConstructor
public class Item extends AbstractNamedEntity {

    @NotNull
    @NotBlank
    @Column(name = "sku")
    private String sku;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) // тоже можно будет обсудить, пока пусть так
    @JsonManagedReference
    private Supplier supplier;


    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) // тоже можно будет обсудить, пока пусть так
    @JsonManagedReference
    private Category category;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Set<ItemStorehouse> itemStorehouses;

}
