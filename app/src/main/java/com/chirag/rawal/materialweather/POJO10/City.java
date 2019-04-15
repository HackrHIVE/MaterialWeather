package com.chirag.rawal.materialweather.POJO10;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class City{
  @SerializedName("country")
  @Expose
  private String country;
  @SerializedName("coord")
  @Expose
  private Coord coord;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("population")
  @Expose
  private Integer population;
  public void setCountry(String country){
   this.country=country;
  }
  public String getCountry(){
   return country;
  }
  public void setCoord(Coord coord){
   this.coord=coord;
  }
  public Coord getCoord(){
   return coord;
  }
  public void setName(String name){
   this.name=name;
  }
  public String getName(){
   return name;
  }
  public void setId(Integer id){
   this.id=id;
  }
  public Integer getId(){
   return id;
  }
  public void setPopulation(Integer population){
   this.population=population;
  }
  public Integer getPopulation(){
   return population;
  }
}