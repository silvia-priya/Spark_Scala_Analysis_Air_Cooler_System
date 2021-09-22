# Spark_Scala_Analysis_Air_Cooler_System
This exercise helps to understand how efficiently we can perform join between two files after converting them to RDD.


File Semantics:-
=================

Buiding.csv

1. BuildingID -- Unique ID for every building
2. BuildingMgr -- Manager of the building
3. BuildingAge -- Age of the building
4. HVACproduct -- Product identifier
5. Country -- Country where the product is installed.

HVAC.csv

1.Date -- Date at which the temperature is recorded
2.Time -- Time at which the temperature is recroded
3.TargetTemp -- Final temperature recorded after applying the cooling agent
4.ActualTemp -- Actual temperatur set
5.System -- System id
6.SystemAge -- Age of the system
7.BuildingID -- Unique ID for every building


The building ID is the unique column and the above two files needs to be joined using the common field BuildingID

Use Cases:
===========

1.Find out the top 2 countries topped where the delta temperature(difference between actual and target temperature) is >5 
2.Find out the top 2 countries topped where the delta temperature(difference between actual and target temperature) is <5


