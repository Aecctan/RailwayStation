package com.jpasample.service;
import com.jpasample.dao.HiberDAO;
import com.jpasample.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;


@Service
public class TicketReturnService {

    @Autowired
    HiberDAO dao;

    public TicketReturnService(){
    }

    private ArrayList<Ticket> ticketsToReturn = new ArrayList<>();

    @Transactional
    public void addTicket(Ticket ticket){
        ticketsToReturn.add(ticket);
    }

    @Transactional
    public List<Ticket> getAllTicketsToReturn() {
        return this.ticketsToReturn;
    }

    @Transactional
    public void requestToReturnTicket(long id)
    {
        Ticket t = dao.findATicket(id);
        if (t!=null && !ticketsToReturn.contains(t))
        {
            addTicket(t);
        }
    }
    @Transactional
    public void returnThisTicket(long id){
        Ticket ticketToRemove = new Ticket();
        for (Ticket t : ticketsToReturn) {
            if (t.getId() == id)
                ticketToRemove = t;
        }
        ticketsToReturn.remove(ticketToRemove);
    }

    @Transactional
    public void returnTicket(long id) {
        dao.removeTicket(dao.findATicket(id));
    }

}
