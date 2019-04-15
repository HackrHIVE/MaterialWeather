package com.chirag.rawal.materialweather.POJO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/**
 * Awesome Pojo Generator
 * */
public class CurrentDayData{
  @SerializedName("dt")
  @Expose
  private Integer dt;
  @SerializedName("coord")
  @Expose
  private Coord coord;
  @SerializedName("weather")
  @Expose
  private List<Weather> weather;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("cod")
  @Expose
  private Integer cod;
  @SerializedName("main")
  @Expose
  private Main main;
  @SerializedName("clouds")
  @Expose
  private Clouds clouds;
  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("sys")
  @Expose
  private Sys sys;
  @SerializedName("base")
  @Expose
  private String base;
  @SerializedName("wind")
  @Expose
  private Wind wind;
  public void setDt(Integer dt){
   this.dt=dt;
  }
  public Integer getDt(){
   return dt;
  }
  public void setCoord(Coord coord){
   this.coord=coord;
  }
  public Coord getCoord(){
   return coord;
  }
  public void setWeather(List<Weather> weather){
   this.weather=weather;
  }
  public List<Weather> getWeather(){
   return weather;
  }
  public void setName(String name){
   this.name=name;
  }
  public String getName(){
   return name;
  }
  public void setCod(Integer cod){
   this.cod=cod;
  }
  public Integer getCod(){
   return cod;
  }
  public void setMain(Main main){
   this.main=main;
  }
  public Main getMain(){
   return main;
  }
  public void setClouds(Clouds clouds){
   this.clouds=clouds;
  }
  public Clouds getClouds(){
   return clouds;
  }
  public void setId(Integer id){
   this.id=id;
  }
  public Integer getId(){
   return id;
  }
  public void setSys(Sys sys){
   this.sys=sys;
  }
  public Sys getSys(){
   return sys;
  }
  public void setBase(String base){
   this.base=base;
  }
  public String getBase(){
   return base;
  }
  public void setWind(Wind wind){
   this.wind=wind;
  }
  public Wind getWind(){
   return wind;
  }
}