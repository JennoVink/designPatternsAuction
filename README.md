# designPatternsAuction
Automated auction where the bidders are bots. There are several design patterns used in this auction: factory, observer, decorator and virtual proxy.

Dutch description:
Veiling simulator
Hierin worden producten (products) geveild aan kopers (buyers). Kopers weten dat er een nieuw product is en een nieuwe bieding via een observer pattern. De products komen weg uit een factory. Deze factory kan producten maken met een decorator pattern. Daarnaast wordt er een image getoond voor elk product die verkocht gaat worden. Deze image heeft ook een placeholder image (‘please wait for the product’). Hiervoor gaat een virtual proxy gebruikt worden. Elk product heeft een initiële prijs (startprijs), deze wordt berekend door alle prijzen van de 'decoraties' op te tellen. Eventueel gaat een MVC pattern een rol spelen in de frontend van de applicatie.
