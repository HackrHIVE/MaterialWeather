package com.chirag.rawal.materialweather.POJO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class Sys{
  @SerializedName("country")
  @Expose
  private String country;
  @SerializedName("sunrise")
  @Expose
  private Integer sunrise;
  @SerializedName("sunset")
  @Expose
  private Integer sunset;
  @SerializedName("message")
  @Expose
  private Double message;
  public void setCountry(String country){
   this.country=country;
  }
  public String getCountry(){
   return country;
  }
  public void setSunrise(Integer sunrise){
   this.sunrise=sunrise;
  }
  public Integer getSunrise(){
   return sunrise;
  }
  public void setSunset(Integer sunset){
   this.sunset=sunset;
  }
  public Integer getSunset(){
   return sunset;
  }
  public void setMessage(Double message){
   this.message=message;
  }
  public Double getMessage(){
   return message;
  }
}