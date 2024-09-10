package com.platzi.pizza.Persistence.audit;

import com.platzi.pizza.Persistence.entity.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;

public class AuditPizzaListener {

    private PizzaEntity currentvalue;

    @PostLoad
    public void postLoad(PizzaEntity entity){
        System.out.println("Post Load");
        this.currentvalue = SerializationUtils.clone(entity);
    }


    @PostPersist
    @PostUpdate
    public void onPostPresist(PizzaEntity entity){
        System.out.println("Post Presist or Update");
        System.out.println("Old value: "+ this.currentvalue);
        System.out.println("new value: "+ entity.toString());
    }

    @PreRemove

    public  void onPreDelete(PizzaEntity entity){
        System.out.println(entity.toString());
    }
}
