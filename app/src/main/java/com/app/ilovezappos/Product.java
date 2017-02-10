package com.app.ilovezappos;

/**
 * Created by ketkitrimukhe on 2/5/17.
 */

// Data binding for product details
public class Product {

    private String productName;
    private String brandName;
    private String productUrl;
    private String thumbnailImageUrl;
    private int styleId;
//  private int colorId;
//  private int productId;
//  private double originalPrice;
//  private double percentOff;
    private String price;

    public String getProductName() {
        return productName;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

//    public int getStyleId() {
//        return styleId;
//    }
//
//    public int getColorId() {
//        return colorId;
//    }
//

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public void setThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    public void setPrice(String price) {
        this.price = price;
    }

//    public int getProductId() {
//        return productId;
//    }
//
//    public double getOriginalPrice() {
//        return originalPrice;
//    }
//
//    public double getPercentOff() {
//        return percentOff;
//    }

    public String getPrice() {
        return price;
    }
}
