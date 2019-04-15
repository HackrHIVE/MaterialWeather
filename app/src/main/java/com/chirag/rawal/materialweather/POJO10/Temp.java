package com.chirag.rawal.materialweather.POJO10;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class Temp{
  @SerializedName("min")
  @Expose
  private Double min;
  @SerializedName("max")
  @Expose
  private Double max;
  @SerializedName("eve")
  @Expose
  private Double eve;
  @SerializedName("night")
  @Expose
  private Double night;
  @SerializedName("day")
  @Expose
  private Double day;
  @SerializedName("morn")
  @Expose
  private Double morn;
  public void setMin(Double min){
   this.min=min;
  }
  public Double getMin(){
   return min;
  }
  public void setMax(Double max){
   this.max=max;
  }
  public Double getMax(){
   return max;
  }
  public void setEve(Double eve){
   this.eve=eve;
  }
  public Double getEve(){
   return eve;
  }
  public void setNight(Double night){
   this.night=night;
  }
  public Double getNight(){
   return night;
  }
  public void setDay(Double day){
   this.day=day;
  }
  public Double getDay(){
   return day;
  }
  public void setMorn(Double morn){
   this.morn=morn;
  }
  public Double getMorn(){
   return morn;
  }
}