
package com.jpasample.controllers;

import com.jpasample.dao.HiberDAO;
import com.jpasample.model.*;

import java.util.Collections;
import java.util.List;

import com.jpasample.service.TicketReturnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    HiberDAO dao;
    @Autowired
    TicketReturnService svc;


    //Spring secrurity
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String privateHome() {
        return "listalldata";
    }

    @ModelAttribute("status")
    public String getStatus() {
        return dao.pullStatus();
    }

    @RequestMapping("list.do")
    public ModelAndView showAll() {
        if (dao.currentUser().equals("admin")) {
            ModelAndView mv = new ModelAndView("listalldata");
            addObject(mv);
            return mv;
        }
        else return showTimeTable();
    }

    @RequestMapping("login.do")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("user", dao.currentUser());
        return mv;
    }

    @RequestMapping("showtimetable.do")
    public ModelAndView showTimeTable() {
        ModelAndView mv = new ModelAndView("guest");
        addObject(mv);
        return mv;
    }

    @RequestMapping("showroutes.do")
    public ModelAndView showRoutes() {
        ModelAndView mv = new ModelAndView("guest");
        addObject(mv);
        return mv;
    }

    @RequestMapping("loginto.do")
    public ModelAndView login(String user, String password) {
        for (Passenger p : dao.getAllPassengers()) {
            if (user.equals(p.getName()) && password.equals(p.getPassword()) && !p.getName().isEmpty()) {
                dao.setCurrentUser(p);
                return loginPassenger();
            }
        }
        if (user.toLowerCase().equals("admin") && password.toLowerCase().equals("admin")) {
            dao.setCurrentUser(new Passenger("admin"));
            return showAll();
        } else if (user.isEmpty() && password.isEmpty())
            return showTimeTable();
        else
            return login();
    }


    @RequestMapping("showPassengers.do")
    public ModelAndView showOne(long id) {
        ModelAndView mv = new ModelAndView("listalldata");
        Passenger p = dao.getPassengerById(id);
        mv.addObject("passengers", Collections.singletonList(p));
        mv.addObject("user", dao.currentUser());
        return mv;
    }


    @RequestMapping("addpassenger.do")
    public ModelAndView addPassenger(String name, long select_route_new_passenger) {
        ModelAndView mv = new ModelAndView("passengeradded");
        Passenger p = dao.addPassenger(name);
        if (dao.freePlaces(select_route_new_passenger)>0) {
            dao.addATicket(p.getId(), select_route_new_passenger);
            mv.addObject("tickets", dao.getAllTickets());
            mv.addObject("passengers", Collections.singletonList(p));
            mv.addObject("user", dao.currentUser());
            return mv;
        }
        else return noPlaces();
    }

    @RequestMapping("newpassenger.do")
    public ModelAndView addNewPassenger(String name, String password) {
        ModelAndView mv = new ModelAndView("user");
        Passenger p = dao.addPassengerWithPassword(name, password);
        dao.setCurrentUser(p);
        addObject(mv);
        return mv;
    }


    @RequestMapping("loginpassenger.do")
    public ModelAndView loginPassenger() {
        ModelAndView mv = new ModelAndView("user");
        addObject(mv);
        return mv;
    }

    @RequestMapping("registration.do")
    public ModelAndView Registration() {
        ModelAndView mv = new ModelAndView("registration");
        addObject(mv);
        return mv;
    }


    @RequestMapping("returnTicket.do")
    public ModelAndView returnTicket(long id) {
        ModelAndView mv = new ModelAndView("listalldata");
        dao.returnTicket(id);
        List<Ticket> t = dao.getAllTickets();
        mv.addObject("tickets", t);
        List<Passenger> p = dao.getAllPassengers();
        mv.addObject("persons", p);
        mv.addObject("user", dao.currentUser());
//        List<Route> r = dao.getAllRoutes();
//        mv.addObject("routes", r);
        return mv;
    }

    @RequestMapping("deletePassenger.do")
    public ModelAndView deletePassenger(long id) {
        ModelAndView mv = new ModelAndView("listalldata");
        dao.deletePassengerById(id);
        List<Passenger> p = dao.getAllPassengers();
        mv.addObject("passengers", p);
        List<Ticket> t = dao.getAllTickets();
        mv.addObject("tickets", t);
        List<Route> r = dao.getAllRoutes();
        mv.addObject("routes", r);
        mv.addObject("user", dao.currentUser());
        return mv;
    }

    @RequestMapping("deleteRoute.do")
    public ModelAndView deleteRoute(long id) {
        ModelAndView mv = new ModelAndView("listalldata");
        dao.deleteRouteById(id);
        List<Route> r = dao.getAllRoutes();
        mv.addObject("routes", r);
        List<Ticket> t = dao.getAllTickets();
        mv.addObject("tickets", t);
        List<Passenger> p = dao.getAllPassengers();
        mv.addObject("passengers", p);
        mv.addObject("user", dao.currentUser());
        return mv;
    }


    @RequestMapping("init.do")
    public ModelAndView init() {
        dao.init();
        ModelAndView mv = new ModelAndView("login");
        addObject(mv);
        return mv;
    }

    @RequestMapping("orderaticket.do")
    public ModelAndView ticketsOrder() {
        ModelAndView mv = new ModelAndView("ticketsorder");
        addObject(mv);
        return mv;
    }

    @RequestMapping("addticket.do")
    public ModelAndView addTicketToPassenger(long select_passenger, long select_route) {
        if (dao.freePlaces(select_route)>0) {
            dao.addATicket(select_passenger, select_route);
            return showAll();
        }
        else return noPlaces();
    }

    @RequestMapping("addacity.do")
    public ModelAndView addCity(String city) {
        ModelAndView mv = new ModelAndView("listalldata");
        City c = dao.addACity(city);
        mv.addObject("cities", Collections.singletonList(c));
        mv.addObject("user", dao.currentUser());
        return mv;
    }

    @RequestMapping("addaroute.do")
    public ModelAndView routeAdd() {
        ModelAndView mv = new ModelAndView("routeadded");
        addObject(mv);
        return mv;
    }
    @RequestMapping("addatrain.do")
    public ModelAndView trainAdd() {
        ModelAndView mv = new ModelAndView("trainadded");
        addObject(mv);
        return mv;
    }

    public ModelAndView noPlaces(){
        ModelAndView mv = new ModelAndView("noplaces");
        addObject(mv);
        return mv;
    }
    @RequestMapping("routeadd.do")
    public ModelAndView addRoute(long select_dispatch_city, long select_destination_city, int select_train_type) {
        dao.addARoute(select_dispatch_city, select_destination_city, select_train_type);
        return showAll();
    }

    @RequestMapping("newtrain.do")
    public ModelAndView addTrain(String model, int places) {
        dao.addATrain(model, places);
        return showAll();
    }

    @RequestMapping("buyaticket.do")
    public ModelAndView buyATicket(long select_route) {
        ModelAndView mv = new ModelAndView("user");
        Passenger p = dao.findCurrentPassenger();
        if (dao.freePlaces(select_route)>0) {
            dao.addATicket(p.getId(), select_route);
            addObject(mv);
            return mv;
        }
        else return noPlaces();
    }

    @RequestMapping("showmytickets.do")
    public ModelAndView showMyTickets() {
        ModelAndView mv = new ModelAndView("usertickets");
        mv.addObject("usertickets", dao.userTickets());
        addObject(mv);
        return mv;
    }

    @RequestMapping("requesttoreturnticket.do")
    public ModelAndView requestToReturnTicket(long select_ticket_to_return_id) {
        ModelAndView mv = new ModelAndView("user");
        Passenger p = dao.findCurrentPassenger();
        svc.requestToReturnTicket(select_ticket_to_return_id);
//        dao.requestToReturnTicket(select_ticket_to_return_id);
        addObject(mv);
        return mv;
    }

    @RequestMapping("acceptrequeststoreturntickets.do")
    public ModelAndView acceptRequestToReturnTickets(){
        ModelAndView mv = new ModelAndView("returntickets");
        mv.addObject("ticketstoreturn", svc.getAllTicketsToReturn());
        addObject(mv);
        return mv;
    }

    @RequestMapping("returnthisticket.do")
    public ModelAndView acceptToReturnTickets(long returned_ticket_id){
        ModelAndView mv = new ModelAndView("returntickets");
        svc.returnTicket(returned_ticket_id);
        svc.returnThisTicket(returned_ticket_id);
//        dao.returnThisTicket(returned_ticket_id);
//        dao.returnTicket(returned_ticket_id);
        mv.addObject("ticketstoreturn", svc.getAllTicketsToReturn());
        addObject(mv);
        return mv;
    }
    public void addObject(ModelAndView mav) {
        mav.addObject("passengers", dao.getAllPassengers());
        mav.addObject("cities", dao.getAllCities());
        mav.addObject("trains", dao.getAllTrains());
        mav.addObject("routes", dao.getAllRoutes());
        mav.addObject("tickets", dao.getAllTickets());
        mav.addObject("user", dao.currentUser());
        mav.addObject("trains", dao.getAllTrains());
    }
}
