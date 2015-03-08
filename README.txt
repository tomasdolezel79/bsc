BSC HOMEWORK

Požadavky
	Java SDK 8
	Maven
	Eclipse STS
	
Build
	Součástí projektu je Maven pom.xml, pomocí kterého se vytvoří spustitelný jar.
		mvn clean package
	
Spuštění
	java -jar homework-0.0.1-SNAPSHOT.jar
	případně 
	java -jar homework-0.0.1-SNAPSHOT.jar ${NAZEV_SOUBORU}
	
Popis činnosti
	Program po spuštění očekává na vstupu některý z příkazů:
		exit    - ukončí program
		quit 	- ukončí program
		print	- vytiskne na obrazovku tabulku dle zadání
		
	nebo zadání tříznakové zkratky měny a částky. Je možné zadat i více záznamů najednou.
	Například:
		USD 100
		
		nebo
		
		USD 100 CZK 50 HRK -22.5
		
		nebo
		
		123USD (na pořadí nezáleží)
		
	Správně zadaná hodnota je potvrzena, špatně zadaná hodnota je odmítnuta.
	
	Program každou minutu vypisuje do konzole tabulku zadaných hodnot, sečtených podle 
	zkratky měny.
	
Optional bonus question
	Pokud je dostupný kurzovní lístek, vypisuje se přepočet na USD. 
	(Kurzovní lístek se stáhne sám z CNB)
	
	
Poznámky k řešení
	Během setkání zaznělo, že projekt má být vytvořen pomocí Java SE, proto jsem 
	nepoužil Spring, ani další běžně využívané knihovny.
	