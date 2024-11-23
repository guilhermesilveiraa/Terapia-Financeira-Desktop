package com.terapia.financeira.model.entities;

import java.io.Serializable;
import java.util.Objects;


public class Goals implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String title;
    private Integer type;
    private double goalTotalValue;
    private double valueAchieved;

    public Goals(){

    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Goals(String title, Integer type, double goalTotalValue, double valueAchieved){
        setTitle(title);
        setGoalTotalValue(goalTotalValue);
        setValueAchieved(valueAchieved);
    }


    public Goals(Integer id, String title, Integer type, double goalTotalValue, double valueAchieved){
        this.id = id;
        this.title = title;
        this.type = type;
        this.goalTotalValue = goalTotalValue;
        this.valueAchieved = valueAchieved;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public Integer getType(){
        return type;
    }
    
    public void setType(Integer type){
        this.type = type;
    }

    public double getGoalTotalValue(){
        return goalTotalValue;
    }

    public void setGoalTotalValue(double  goalTotalValue){
        this.goalTotalValue = goalTotalValue;
    }

    public double getValueAchieved(){
        return valueAchieved;
    }

    public void setValueAchieved(double valueAchieved){
        this.valueAchieved = valueAchieved;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Mesma referência
        if (obj == null || getClass() != obj.getClass()) return false; // Classes diferentes
        Goals goals = (Goals) obj;
        return Objects.equals(title, goals.title) && 
            Objects.equals(type, goals.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, id);
    }

    
}
