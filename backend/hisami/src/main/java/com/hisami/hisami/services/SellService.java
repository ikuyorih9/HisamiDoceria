package com.hisami.hisami.services;

import java.util.List;

import com.hisami.hisami.dto.SellDTO;
import com.hisami.hisami.entities.Id.SellId;
import com.hisami.hisami.entities.Sell;
import com.hisami.hisami.interfaces.EntityInterface;

public interface SellService extends EntityInterface<Sell, SellDTO, SellId> {
    public List<Sell> getSellByProduct(String barcode);
}
