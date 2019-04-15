package com.chirag.rawal.materialweather.POJO10;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class Weather{
  @SerializedName("icon")
  @Expose
  private String icon;
  @SerializedName("description")
  @Expose
  private String description;
  @SerializedName("main")
  @Expose
  private String main;
  @SerializedName("id")
  @Expose
  private Integer id;
  public void setIcon(String icon){
   this.icon=icon;
  }
  public String getIcon(){
   return icon;
  }
  public void setDescription(String description){
   this.description=description;
  }
  public String getDescription(){
   return description;
  }
  public void setMain(String main){
   this.main=main;
  }
  public String getMain(){
   return main;
  }
  public void setId(Integer id){
   this.id=id;
  }
  public Integer getId(){
   return id;
  }
}