package com.yura.lampak.store.model.dao;


import com.yura.lampak.store.model.entities.ProductAttribute;

import java.util.Collection;

public interface CategoryAttributeDAO {

    Collection<ProductAttribute> getAttributesForCategory(int product_id);
    ProductAttribute getAttributeById(int attr_id);

    boolean saveAttribute(ProductAttribute productAttribute);
    boolean removeAttribute(int attr_id);
}
