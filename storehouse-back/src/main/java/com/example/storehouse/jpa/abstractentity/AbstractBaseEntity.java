package com.example.storehouse.jpa.abstractentity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@MappedSuperclass
// @Data и @NoArgs (и др. ломбок) можно тут почитать: https://urvanov.ru/2015/09/19/lombok-data/
@Data
@NoArgsConstructor
@Access(AccessType.FIELD)
public abstract class AbstractBaseEntity implements Persistable<Integer> {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Override
    public boolean isNew() {
        return null == getId();
    }
}
