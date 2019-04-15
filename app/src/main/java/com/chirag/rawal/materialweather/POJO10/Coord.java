package com.chirag.rawal.materialweather.POJO10;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class Coord{
  @SerializedName("lon")
  @Expose
  private Double lon;
  @SerializedName("lat")
  @Expose
  private Double lat;
  public void setLon(Double lon){
   this.lon=lon;
  }
  public Double getLon(){
   return lon;
  }
  public void setLat(Double lat){
   this.lat=lat;
  }
  public Double getLat(){
   return lat;
  }
}