package com.chirag.rawal.materialweather.POJO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class Wind{
  @SerializedName("deg")
  @Expose
  private Double deg;
  @SerializedName("speed")
  @Expose
  private Double speed;
  public void setDeg(Double deg){
   this.deg=deg;
  }
  public Double getDeg(){
   return deg;
  }
  public void setSpeed(Double speed){
   this.speed=speed;
  }
  public Double getSpeed(){
   return speed;
  }
}