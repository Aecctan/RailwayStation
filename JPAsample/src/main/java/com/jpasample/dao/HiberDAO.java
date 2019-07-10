package com.jpasample.dao;
import com.jpasample.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.swing.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;


@Repository
public class HiberDAO {

    private ArrayList<Ticket> ticketsToReturn = new ArrayList<>();
    @Transactional
    public List<Ticket> getAllTicketsToReturn() {
        return this.ticketsToReturn;
    }

    public void returnThisTicket(long id){
        Ticket ticketToRemove = new Ticket();
        for (Ticket t : ticketsToReturn) {
            if (t.getId() == id)
                ticketToRemove = t;
        }
        ticketsToReturn.remove(ticketToRemove);
    }
    @PersistenceContext
    EntityManager em;

//    private Random r = new Random();
    private String lastStatus;
    public User user = new User(" ");
    
    public HiberDAO() {
    }


    @Transactional
    public Passenger addRandomPassenger() {
        Passenger p = new Passenger();
        p.setName("Passenger");
        em.persist(p);
        lastStatus = "Человек добавлен!";
        return p;
    }

    @Transactional
    public Passenger addPassenger(String name) {
        Passenger p = new Passenger(name);
        em.persist(p);
        return p;
    }

    @Transactional
    public Passenger addPassengerWithPassword(String name, String password) {
        Passenger p = new Passenger(name, password);
        em.persist(p);
        return p;
    }


    @Transactional
    public List<Passenger> getAllPassengers() {
        List<Passenger> res = em.createQuery("select p from Passenger p",Passenger.class).getResultList();
        return res;
    }

    @Transactional
    public List<City> getAllCities() {
        List<City> res = em.createQuery("select c from City c",City.class).getResultList();
        return res;
    }

    @Transactional
    public List<Train> getAllTrains() {
        List<Train> res = em.createQuery("select t from Train t",Train.class).getResultList();
        return res;
    }

    @Transactional
    public List<Route> getAllRoutes() {
        List<Route> res = em.createQuery("select r from Route r",Route.class).getResultList();
        return res;
    }

    @Transactional
    public List<Ticket> getAllTickets() {
        List<Ticket> res = em.createQuery("select t from Ticket t",Ticket.class).getResultList();
        return res;
    }

    @Transactional
    public Passenger getPassengerById(long id) {
        Passenger p = em.find(Passenger.class, id);
        p.getTickets().toString();
        return p;
    }


    @Transactional
    public void init() {
        Passenger pas;
        City ufa;
        City voronezh;
        City moskow;
        Train train1;
        Train train2;
        Train train3;
        Route route;

        em.createQuery("delete from Passenger pas where pas.id>0").executeUpdate();
        em.createQuery("delete from Route route where route.id>0").executeUpdate();
        em.createQuery("delete from City city where city.id>0").executeUpdate();
        em.createQuery("delete from Train train where train.id>0").executeUpdate();

        train1 = new Train("Skoryi", 5);
        em.persist(train1);
        train2 = new Train("Podzemnyi", 200);
        em.persist(train2);
        train3 = new Train("Plackart", 100);
        em.persist(train3);
        voronezh = new City("Voronezh");
        em.persist(voronezh);
        moskow = new City("Moskow");
        em.persist(moskow);
        ufa = new City("Ufa");
        em.persist(ufa);
        route = new Route(ufa, voronezh, train1);
        em.persist(route);
        route = new Route(moskow, ufa, train2);
        em.persist(route);
        route = new Route(voronezh, moskow, train3);
        em.persist(route);

        pas = new Passenger("Victor");
        em.persist(pas);
        pas.getTickets();
        pas = new Passenger("Timur");
        em.persist(pas);
        pas.getTickets();
        pas = new Passenger("Gennadiy");
        em.persist(pas);
        pas.getTickets();



    }

    /// проблема с многопоточным доступом! 
    public String pullStatus() {
        return lastStatus;
    }


    @Transactional
    public void returnTicket(long id) {
        Ticket t = em.find(Ticket.class, id);
        int flag = 0;
        int ind = 0;
        for (Ticket ticket : t.getOwner().getTickets()) {
            if (ticket.getId() == id)
                flag = ind;
            ind ++;
        }
        t.getOwner().getTickets().remove(flag);
        removeTicket(t);
    }

    @Transactional
    public void deletePassengerById(long id) {
        Passenger p = em.find(Passenger.class, id);
        if (p.getTickets()!=null)
            for (Ticket t : p.getTickets()) {
                removeTicket(t);
            }
        em.remove(p);
    }

    @Transactional
    public void deleteRouteById(long id) {
        Route r = em.find(Route.class, id);
        em.remove(r);
    }


    @Transactional
    public void addATicket(long idP, long idR) {
        Ticket t = new Ticket();
        Passenger p = em.find(Passenger.class, idP);
        Route r = em.find(Route.class, idR);
        {
            if (r.getTrain().getPlaces() > 0) {
                t.setRoute(r);
                addANewTicket(p, t);
            }
        }
    }

    @Transactional
    public City addACity(String city)
    {
        City c = new City(city);
        em.persist(c);
        return c;
    }

    @Transactional
    public Route addARoute(long dispatch_city, long destination_city, long train_id)
    {
        City dispatch = em.find(City.class, dispatch_city);
        City destination = em.find(City.class, destination_city);
        Train train = em.find(Train.class, train_id);
        Route r = new Route(dispatch, destination, train);
        em.persist(r);
        return r;
    }
    @Transactional
    public Train addATrain(String model, int places)
    {
        Train train = new Train(model, places);
        em.persist(train);
        return train;
    }
    @Transactional
    public int freePlaces(long id)
    {
        Route route = em.find(Route.class, id);
        return route.getTrain().getPlaces();
    }
    @ModelAttribute
    public String currentUser()
    {
        return user.getName();
    }
    @ModelAttribute
    public void setCurrentUser(Passenger p)
    {
        user.setPassword(p.getPassword());
        user.setId(p.getId());
        user.setName(p.getName());
    }
    @ModelAttribute
    public String currentUserPassword()
    {
        return user.getPassword();
    }
    @ModelAttribute
    public void setCurrentUserPassword(String pass)
    {
        user.setPassword(pass);
    }

    @Transactional
    public List<Ticket> userTickets()
    {
        Passenger p = findCurrentPassenger();
        long id = p.getId();
        List<Ticket> res = em.createQuery("select t from Ticket t where t.owner.id = :idP", Ticket.class).setParameter("idP", id).getResultList();
            return res;
    }

    @Transactional
    public Passenger findCurrentPassenger()
    {
        Passenger currentPassenger = new Passenger();
        for (Passenger p : getAllPassengers())
        {
            if (p.getId()==user.getId())
                currentPassenger = p;
        }
        return currentPassenger;
    }

    @Transactional
    public Ticket findATicket(long id)
    {
        Ticket t = em.find(Ticket.class, id);
        return t;
    }
    @Transactional
    public void requestToReturnTicket(long id)
    {
        Ticket t = em.find(Ticket.class, id);
        int flag = 0;
        if (t!=null)
        {
            for (Ticket ticket : ticketsToReturn) {
                if (ticket.getId() == t.getId())
                    flag = 1;
            }
            if (flag == 0)
                ticketsToReturn.add(t);
        }
    }

    @Transactional
    public void removeTicket (Ticket t)
    {
        t.getRoute().getTrain().correctPlaces(1);
        returnTicket(t.getId());
    }

    @Transactional
    public void addANewTicket(Passenger p, Ticket t)
    {
        t.getRoute().getTrain().correctPlaces(-1);
        t.setOwner(p);
        p.addTicket(t);
    }
}