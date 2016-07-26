

This can be run for 3 separate services : 1. Catalogue(for product catalogue service) 2. Price (For pricing server) and 3. ui(Web application)

How to use?
1. Compile and Create jar using maven as this is a maven project.
2. Go to Jar location and execute as below (command line execution).
	java -jar [jar-name] [options]
	jar-name : Name of jar created
	options : Decide which service to be make up. 
		values should be 1.'catalogue' 2.'price' 3.'ui'. 
		Order of execution: 1. Catalogue 2. Price 3. ui

You should see servers being registered in the log output.

As per the yaml cnfiguration, below are the 3 services url:
Catalogue Service : http://localhost:8093
Pricing Service: http://localhost:8094
Web Service: http://localhost:8095
