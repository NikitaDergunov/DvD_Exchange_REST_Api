package com.ndergunov.dvdexchange.template;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.ndergunov.dvdexchange.entity.TakenItem;
// Wrapper for taken item
@JsonAutoDetect
public class TakenItemTemplate {
    public String disk,previous,current;
    public TakenItemTemplate(TakenItem takenItem) {
        this.disk = takenItem.getDisk().getName();
        try {
        this.previous = takenItem.getPrevious().getLogin();
        }catch (NullPointerException ex){
            this.previous=null;
        }
        try{
        this.current = takenItem.getCurrent().getLogin();
        }catch (NullPointerException ex){
            this.current = null;
        }
    }
}
