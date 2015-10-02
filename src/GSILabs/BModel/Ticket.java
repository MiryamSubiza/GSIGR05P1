/*
 * This document is part of the lab material for the subject:
 * Gestion de Sistemas de Informacion
 * to be released at the
 * Universidad Publica de Navarra
 * during the first semester of the Academic Year 2015-2016
 */

package GSILabs.BModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author subiza.79082
 * @author izu.78236
 * @version 1.0 (18/09/2015)
 */
public class Ticket {
    
    private Event event; //Puede ser un Concierto, un Festival o una Exposición
    //Clave: Identificador para una persona
    //Valor: False si no ha entrado y True si ha entrado al evento
    private HashMap <Integer,Boolean> people; 
    //Cantidad de personas que pueden acceder al evento con esta entrada (una o varias)
    private int numberOfPeople;
    //True si el ticket ha sido vendido a un cliente, false en caso contrario
    private boolean sold;
    
    public Ticket (Event event, AtomicInteger atomicInteger, int numberOfPeople) {
        
        this.event = event;
        people = new HashMap();
        for (int i = 1; i <= numberOfPeople; i++) {
            people.put(atomicInteger.getAndIncrement(), false);
        }
        this.numberOfPeople = numberOfPeople;
        this.sold = false;
        
    }
    
    public void setEvent (Event event) {
        this.event = event;
    }
    
    public Event getEvent () {
        return event;
    }
    
    public void setNumberOfPeople (int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }
    
    public int getNumberOfPeople () {
        return numberOfPeople;
    }
    
    public void setSold (boolean sold) {
        this.sold = sold;
    }
    
    public boolean isSold () {
        return sold;
    }
    
    //Devuelve true si la entrada contiene este identificador
    //y false en caso contrario
    public boolean checkIdentifierInTicket (int identifier) {
        return people.containsKey(identifier);
    }
    
    //Devuelve true si el identificador ha sido utilizado
    //y false en caso contrario
    public boolean checkIdentifierIsUsed (int identifier) {
        return (boolean)people.get(identifier); //Le pasas una Key y te devuelve el Value
    }
    
    //Devuelve verdadero si el id no había sido usado previamente
    public boolean setIDUsed (int id) {
        if (!people.get(id)) { //No había sido usado
            people.put(id, true);
            return true;
        }
        else return false;
    }
    
    //Devuelve un array de enteros con los identificadores que hay en este ticket
    public int[] getIdentifiers () {
        Set ids = people.keySet();
        int[] idsArray;
        idsArray = new int[people.size()];
        Iterator i = ids.iterator();
        int idAux;
        int j = 0;
        while (i.hasNext()) {
            idAux = (int)i.next();
            idsArray[j] = idAux;
            j++;
        }
        return idsArray;
    }
    
    @Override
    public String toString() {
        String availability;
        if (sold) availability = "Sold";
        else availability = "Not sold";
        return "TICKET\nName of the event: " + event.getName() + 
                "\nNumber of people: " + numberOfPeople + "\nAvailability: "
                + availability + "\n";
    }
    
}