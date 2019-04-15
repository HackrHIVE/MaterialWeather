package com.chirag.rawal.materialweather.POJO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class Main{
  @SerializedName("temp")
  @Expose
  private Double temp;
  @SerializedName("temp_min")
  @Expose
  private Double temp_min;
  @SerializedName("grnd_level")
  @Expose
  private Double grnd_level;
  @SerializedName("humidity")
  @Expose
  private Double humidity;
  @SerializedName("pressure")
  @Expose
  private Double pressure;
  @SerializedName("sea_level")
  @Expose
  private Double sea_level;
  @SerializedName("temp_max")
  @Expose
  private Double temp_max;
  public void setTemp(Double temp){
   this.temp=temp;
  }
  public Double getTemp(){
   return temp;
  }
  public void setTemp_min(Double temp_min){
   this.temp_min=temp_min;
  }
  public Double getTemp_min(){
   return temp_min;
  }
  public void setGrnd_level(Double grnd_level){
   this.grnd_level=grnd_level;
  }
  public Double getGrnd_level(){
   return grnd_level;
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
  public void setSea_level(Double sea_level){
   this.sea_level=sea_level;
  }
  public Double getSea_level(){
   return sea_level;
  }
  public void setTemp_max(Double temp_max){
   this.temp_max=temp_max;
  }
  public Double getTemp_max(){
   return temp_max;
  }
}