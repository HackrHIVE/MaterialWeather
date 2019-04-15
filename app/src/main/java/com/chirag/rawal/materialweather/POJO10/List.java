package com.chirag.rawal.materialweather.POJO10;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Awesome Pojo Generator
 * */
public class List{
  @SerializedName("dt")
  @Expose
  private Integer dt;
  @SerializedName("temp")
  @Expose
  private Temp temp;
  @SerializedName("deg")
  @Expose
  private Double deg;
  @SerializedName("weather")
  @Expose
  private ArrayList<Weather> weather;
  @SerializedName("humidity")
  @Expose
  private Double humidity;
  @SerializedName("pressure")
  @Expose
  private Double pressure;
  @SerializedName("clouds")
  @Expose
  private Double clouds;
  @SerializedName("speed")
  @Expose
  private Double speed;
  public void setDt(Integer dt){
   this.dt=dt;
  }
  public Integer getDt(){
   return dt;
  }
  public void setTemp(Temp temp){
   this.temp=temp;
  }
  public Temp getTemp(){
   return temp;
  }
  public void setDeg(Double deg){
   this.deg=deg;
  }
  public Double getDeg(){
   return deg;
  }
  public void setWeather(ArrayList<com.chirag.rawal.materialweather.POJO10.Weather> weather){
   this.weather=weather;
  }
  public ArrayList<com.chirag.rawal.materialweather.POJO10.Weather> getWeather(){
   return weather;
  }
  public void setHumidity(Double humidity){
   this.humidity=humidity;
  }
  public Double getHumidity(){
   return humidity;
  }
  public void setPressure(Double pressure){
   this.pressure=pressure;
  }
  public Double getPressure(){
   return pressure;
  }
  public void setClouds(Double clouds){
   this.clouds=clouds;
  }
  public Double getClouds(){
   return clouds;
  }
  public void setSpeed(Double speed){
   this.speed=speed;
  }
  public Double getSpeed(){
   return speed;
  }
}