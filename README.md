dms-project
===========
Abdullah Al-Syed and Jayson Ng

In order to run the News Generator, please run the java program from the command line as follows. The first argument is the precinct file (in CSV format) and the last argument is the borough file. The precinct has to be from the same borough. The output news is both printed to the screen and written to the file generated_news.txt. A sample session is illustrated below:

$ javac NewsGenerator.java
$ java NewsGenerator cs040pct.csv cspbbx.csv 
Please select whether the news story should focus on rising crime rates or falling crime rates:

<1> Falling crime rates
<2> Rising crime rates

Choose 1 or 2:
1

Please specify the type of crime to higlight in the report:

<1> Murder
<2> Rape
<3> Robbery
<4> Fel. Assault
<5> Burglary
<6> Gr. larceny
<7> G.L.A.
<8> Transit
<9> Housing
<10> Petit Larceny
<11> Misd. Assault
<12> Misd. Sex Crimes

5




Crime statistics are encouraging this week. There was a 66.7 % drop in the number of burglaries in the 40th precinct compared to the same period last year. Felony assaults, housing crimes, misdemeanor assaults also plummeted in the 40th precinct. Looking beyond just the 40th precinct, the Bronx has seen a 6.5 % fall in the total number of burglaries during this week last year. 
