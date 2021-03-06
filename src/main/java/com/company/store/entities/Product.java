package com.company.store.entities;


import java.util.List;
import java.util.Map;

public class Product implements Comparable<Product>{

    private int product_id;
    private int parent_id;
    private String name;
    private Map<ProductAttribute, ProductParameter> parameters;
    private List<ProductParameter> params;
    private boolean isCategory;

    public Product() {
    }

    public Product(int product_id, int parent_id, String name, Map<ProductAttribute, ProductParameter> parameters,
                   boolean isCategory) {
        this.product_id = product_id;
        this.parent_id = parent_id;
        this.name = name;
        this.parameters = parameters;
        this.isCategory = isCategory;
    }

    public Product(int product_id, int parent_id, String name, boolean isCategory) {
        this.product_id = product_id;
        this.parent_id = parent_id;
        this.name = name;
    }

    public int getId() {
        return product_id;
    }

    public void setId(int product_id) {
        this.product_id = product_id;
    }

    public int getParentId() {
        return parent_id;
    }

    public void setParentId(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map getParameters() {
        return parameters;
    }

    public void setParameters(Map<ProductAttribute, ProductParameter> parameters) {
        this.parameters = parameters;
    }

    public boolean isCategory() {
        return isCategory;
    }

    public void setCategory(boolean category) {
        isCategory = category;
    }

    public List<ProductParameter> getParams() {
        return params;
    }

    public void setParams(List<ProductParameter> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "Product: " +
                "\n product_id: " + product_id +
                "\n parent_id: " + parent_id +
                "\n name: " + name +
                "\n is category: " + isCategory;
    }

    @Override
    public int compareTo(Product o) {
        return this.name.compareTo(o.getName());
    }
}
