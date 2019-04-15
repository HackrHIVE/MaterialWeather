package com.chirag.rawal.materialweather.POJO10;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Awesome Pojo Generator
 * */
public class Sizteen{
  @SerializedName("city")
  @Expose
  private City city;
  @SerializedName("cnt")
  @Expose
  private Integer cnt;
  @SerializedName("cod")
  @Expose
  private Integer cod;
  @SerializedName("message")
  @Expose
  private Double message;
  @SerializedName("list")
  @Expose
  private ArrayList<com.chirag.rawal.materialweather.POJO10.List> list;
  public void setCity(City city){
   this.city=city;
  }
  public City getCity(){
   return city;
  }
  public void setCnt(Integer cnt){
   this.cnt=cnt;
  }
  public Integer getCnt(){
   return cnt;
  }
  public void setCod(Integer cod){
   this.cod=cod;
  }
  public Integer getCod(){
   return cod;
  }
  public void setMessage(Double message){
   this.message=message;
  }
  public Double getMessage(){
   return message;
  }
  public void setList(ArrayList<com.chirag.rawal.materialweather.POJO10.List> list){
   this.list=list;
  }
  public ArrayList<List> getList(){
   return list;
  }
}