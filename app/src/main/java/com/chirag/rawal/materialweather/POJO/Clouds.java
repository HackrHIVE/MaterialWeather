package com.chirag.rawal.materialweather.POJO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class Clouds{
  @SerializedName("all")
  @Expose
  private Integer all;
  public void setAll(Integer all){
   this.all=all;
  }
  public Integer getAll(){
   return all;
  }
}