package com.example.storehouse.model;

import com.example.storehouse.model.abstractentity.AbstractBaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "ItemStorehouse")
@Table(name = "items_storehouses")
public class ItemStorehouse extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "storehouse_id")
    private Storehouse storehouse;

    @Column(name = "qty")
    private int quantity;

    public ItemStorehouse(Storehouse storehouse, int quantity) {
        this.storehouse = storehouse;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ItemStorehouse that = (ItemStorehouse) o;
        return id == that.id && quantity == that.quantity && item.equals(that.item) && storehouse.equals(that.storehouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, item, storehouse, quantity);
    }
}
