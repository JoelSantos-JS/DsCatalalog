package com.devsuperior.DsCatalog.dto;

import com.devsuperior.DsCatalog.model.Category;

public class CategoryDTO {

    private long id;
    private String name;

    public CategoryDTO() {

    }

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
