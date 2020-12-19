package com.example.storehouse.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

@MappedSuperclass
// @Data и @NoArgs (и др. ломбок) можно тут почитать: https://urvanov.ru/2015/09/19/lombok-data/
@Data
@NoArgsConstructor
@Access(AccessType.FIELD)
// В дальнейшем - про class AbstractPersistable<PK> можно почитать и попробовать
// применить взамен данного, разделив на Base (с id) и Named (+ name)
// если в схему БД добавятся сущности без колонки name
public abstract class AbstractNamedEntity implements Persistable<Integer> {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @NotNull
    @NotBlank
    @Column(name = "name", nullable = false) //для наглядности
    protected String name;

    @Override
    public boolean isNew() {
        return null == getId();
    }

}
