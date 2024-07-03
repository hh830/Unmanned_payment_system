package org.techtown.home2;

public class ResultData {
    private String prdNm;
    private String brandNm;
    private String price;

    public ResultData(String prdNm, String brandNm, String price)
    {
        this.prdNm=prdNm;
        this.brandNm=brandNm;
        this.price=price;
    }
    public ResultData()
    {

    }

    public String getPrdNm()
    {
        return prdNm;
    }
    public void setPrdNm(String prdNm)
    {
        this.prdNm=prdNm;
    }
    public String getBrandNm()
    {
        return brandNm;
    }
    public void setBrandNm(String brandNm)
    {
        this.brandNm=brandNm;
    }
    public String getPrice()
    {
        return price;
    }
    public void setPrice(String price)
    {
        this.price=price;
    }
}