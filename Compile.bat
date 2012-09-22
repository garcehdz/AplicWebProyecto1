PATH=C:\Program Files\Java\jdk1.6.0_25\bin
::PATH=D:\Program Files\Java\jdk1.7.0_05\bin

::Data
javac -cp "tickets/WEB-INF/classes";"tickets/WEB-INF/lib/*" -d tickets/WEB-INF/classes src/data/CustomerFinder.java src/data/CustomerRowGateway.java src/data/EventFinder.java src/data/EventRowGateway.java src/data/EventTypeFinder.java src/data/EventTypeRowGateway.java src/data/LocationFinder.java src/data/LocationRowGateway.java src/data/LocationTypeFinder.java src/data/LocationTypeRowGateway.java src/data/NumberedLocationFinder.java src/data/NumberedLocationRowGateway.java src/data/PromoterFinder.java src/data/PromoterRowGateway.java src/data/TicketFinder.java src/data/TicketRowGateway.java src/data/UserFinder.java src/data/UserRowGateway.java

::Display
javac -cp "tickets/WEB-INF/classes";"tickets/WEB-INF/lib/*" -d tickets/WEB-INF/classes src/display/FrontController.java src/display/FrontCommand.java src/display/UnknownCommand.java

::Domain
javac -cp "tickets/WEB-INF/classes";"tickets/WEB-INF/lib/*" -d tickets/WEB-INF/classes src/domain/EventsDelete.java src/domain/EventsDetail.java src/domain/EventsFormNew.java src/domain/EventsInsert.java src/domain/EventsListAll.java src/domain/EventsListByPromoter.java src/domain/EventsUpdate.java src/domain/Login.java src/domain/PromotersDelete.java src/domain/PromotersDetail.java src/domain/PromotersInsert.java src/domain/PromotersList.java src/domain/PromotersUpdate.java src/domain/TicketsFormNew.java src/domain/TicketsInsert.java src/domain/TicketsInvoice.java

pause