package domain;

import data.*;
import display.FrontCommand;
import java.util.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 * Controla el evento de insercion de factura
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class TicketsInsert extends FrontCommand
{
	public void process() throws ServletException, IOException
	{
		try
		{
			prepareCustomer();
			int ticketCode = prepareTicket();
			response.sendRedirect("tickets/domain.TicketsInvoice?code=" + ticketCode);
		}
		catch(Exception e)
		{
			request.setAttribute("error", e.getMessage());
			forward("/error.jsp");
		}
	}

	public void prepareCustomer() throws Exception
	{
		CustomerFinder customerFinder = (CustomerFinder)context.getBean("CustomerFinder");
		CustomerRowGateway customer = customerFinder.find(request.getParameter("txtCostumerId"));
		if (customer == null)
		{
			customer = customerFinder.create();
			customer.setId(request.getParameter("txtCostumerId"));
			customer.setName(request.getParameter("txtCostumerName"));
			customer.setAddress(request.getParameter("txtCostumerAddress"));
			customer.setTelephone(request.getParameter("txtCostumerTelephone"));
			customer.setCardNumber(request.getParameter("txtCostumerCardNumber"));
			customer.setCardType(request.getParameter("txtCostumerCardType"));
			customer.insert();
		}
		else
		{
			customer.setName(request.getParameter("txtCostumerName"));
			customer.setAddress(request.getParameter("txtCostumerAddress"));
			customer.setTelephone(request.getParameter("txtCostumerTelephone"));
			customer.setCardNumber(request.getParameter("txtCostumerCardNumber"));
			customer.setCardType(request.getParameter("txtCostumerCardType"));
			customer.update();
		}
	}
	
	public int prepareTicket() throws Exception
	{
		int ticketCode = 0;
		TicketRowGateway ticket = getTicket();
		EventRowGateway event = getEvent(ticket.getEvent());
		LocationRowGateway location = getLocation(ticket.getLocationType(), ticket.getEvent());
		if (ticket.getQuantity() + getNumberOfTicketsByEventAndLocation(ticket.getEvent(), ticket.getLocationType()) <= location.getQuantity())
		{
			ticket.setAmount(ticket.getQuantity() * location.getPrice());
			ticketCode = ticket.insert();
			if (location.getNumbered())
			{
				insertNumberedTickets(ticketCode, location.getQuantity(), ticket.getQuantity());
			}
			request.setAttribute("ticket", ticketCode);
		}
		return ticketCode;
	}
	
	public void insertNumberedTickets(int ticketId, int limit, int needed) throws Exception
	{
		NumberedLocationFinder numberedLocationFinder = (NumberedLocationFinder)context.getBean("NumberedLocationFinder");
		NumberedLocationRowGateway numberedLocation;
		int offset = Integer.parseInt(request.getParameter("txtNumberedTicked"));
		int up = offset, down = offset - 1;
		while (needed > 0)
		{
			if (up >=1 && up <= limit)
			{
				numberedLocation = numberedLocationFinder.create();
				numberedLocation.setTicketId(ticketId);
				numberedLocation.setLocationNumber(up);
				numberedLocation.insert();
				needed--;
				up++;
				if (needed <= 0)
				{
					break;
				}
			}
			if (down >= 1 && down <= limit)
			{
				numberedLocation = numberedLocationFinder.create();
				numberedLocation.setTicketId(ticketId);
				numberedLocation.setLocationNumber(down);
				numberedLocation.insert();
				needed--;
				down--;
			}
		}
	}
	
	public EventRowGateway getEvent(int eventId) throws Exception
	{
		EventFinder eventFinder = (EventFinder)context.getBean("EventFinder");
		return eventFinder.find(eventId);
	}
	
	public LocationRowGateway getLocation(int locationTypeId, int eventId) throws Exception
	{
		LocationFinder locationFinder = (LocationFinder)context.getBean("LocationFinder");
		return locationFinder.find(locationTypeId, eventId);
	}
	
	public TicketRowGateway getTicket() throws Exception
	{
		TicketFinder ticketFinder = (TicketFinder)context.getBean("TicketFinder");
		TicketRowGateway ticket = ticketFinder.create();
		ticket.setLocationType(Integer.parseInt(request.getParameter("txtLocationTypeId")));
		ticket.setEvent(Integer.parseInt(request.getParameter("txtTicketEventId")));
		ticket.setCustomer(request.getParameter("txtCostumerId"));
		ticket.setDate((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(Calendar.getInstance().getTime()));
		ticket.setQuantity(Integer.parseInt(request.getParameter("txtTicketQuantity")));
		return ticket;
	}
	
	public int getNumberOfTicketsByEventAndLocation(int eventId, int locationId) throws Exception
	{
		int numberOfTickets = 0;
		TicketFinder ticketFinder = (TicketFinder)context.getBean("TicketFinder");
		List<TicketRowGateway> tickets = ticketFinder.findByEventAndLocation(eventId, locationId);
		for (TicketRowGateway ticket : tickets) 
		{
			numberOfTickets += ticket.getQuantity();
		}
		return numberOfTickets;
	}
}