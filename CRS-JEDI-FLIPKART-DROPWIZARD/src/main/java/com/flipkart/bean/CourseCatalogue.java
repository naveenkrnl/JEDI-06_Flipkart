package com.flipkart.bean;

/**
 * Course Catalogue Bean Class
 */
public class CourseCatalogue {

    private String catalogId;
    private String name;

    /**
     * Method to get catalog Id
     *
     * @return catalogId
     */
    public String getCatalogId() {
        return catalogId;
    }

    /**
     * Method to set catalog Id
     *
     * @param catalogId Catalog Id of Course Catalogue
     */
    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    /**
     * Method to get Name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to set name
     *
     * @param name Name of Course Catalogue
     */
    public void setName(String name) {
        this.name = name;
    }

}
