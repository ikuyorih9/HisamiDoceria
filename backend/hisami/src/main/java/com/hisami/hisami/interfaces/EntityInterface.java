package com.hisami.hisami.interfaces;

import java.util.List;
import java.util.Optional;

public interface EntityInterface<T, DTO, Id> {
    public T create(DTO object);

    public Optional<T> find(Id id);

    public boolean exists(Id id);

    public List<T> list();

    public void delete(Id id);
}
