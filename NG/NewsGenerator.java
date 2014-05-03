import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsGenerator
{
	private static final int MAX_SIZE = 20;

	private static String templateStrings0[] = { "Crime statistics are encouraging this week. ", "Crime statistics are sobering this week. ", };

	private static String templateStrings1[] = {

	"The <PRECINCT_NUMBER>th precinct saw a <PERCENTAGE> % decrease in the number of <CRIME_TYPE> compared to the same period last year. ",
			"<CRIME_TYPE> fell <PERCENTAGE> % in the <PRECINCT_NUMBER>th precinct compared to the same period last year. ",
			"There was a <PERCENTAGE> % drop in the number of <CRIME_TYPE> in the <PRECINCT_NUMBER>th precinct compared to the same period last year." };

	private static String templateStrings2[] = {

	"The <PRECINCT_NUMBER>th precinct saw a <PERCENTAGE> % increase in the number of <CRIME_TYPE> compared to the same period last year. ",
			"<CRIME_TYPE> rose <PERCENTAGE> % in the <PRECINCT_NUMBER>th precinct compared to the same period last year. ",
			"There was a <PERCENTAGE> % rise in the number of <CRIME_TYPE> in the <PRECINCT_NUMBER>th precinct compared to the same period last year." };

	private static String templateStrings3[] = { "<CRIME_TYPE> also registered a decrease in the <PRECINCT_NUMBER>th precinct. ",
			"<CRIME_TYPE> also fell in the <PRECINCT_NUMBER>th precinct. ", "<CRIME_TYPE> also plummeted in the <PRECINCT_NUMBER>th precinct. ", };

	private static String templateStrings4[] = { "<CRIME_TYPE> also registered an increase in the <PRECINCT_NUMBER>th precinct. ",
			"<CRIME_TYPE> also rose in the <PRECINCT_NUMBER>th precinct. ", "<CRIME_TYPE> also rocketed in the <PRECINCT_NUMBER>th precinct. ", };

	private static String templateStrings5[] = {

			"Overall in the <BOROUGH>, which the <PRECINCT_NUMBER>th precinct belongs to, the total number of <CRIME_TYPE> registered a <PERCENTAGE> % <CHANGE> in its levels during the same time last year. ",
			"Apart from its constituent <PRECINCT_NUMBER>th precinct, the <BOROUGH> has seen a <PERCENTAGE> % <CHANGE> in the total number of <CRIME_TYPE> during this week last year. ",
			"Looking beyond just the <PRECINCT_NUMBER>th precinct, the <BOROUGH> has seen a <PERCENTAGE> % <CHANGE> in the total number of <CRIME_TYPE> during this week last year. ", };

	private static String myOutputText = "";

	private static final Pattern DOUBLE_PATTERN = Pattern.compile("[\\x00-\\x20]*[+-]?(NaN|Infinity|((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)"
			+ "([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|"
			+ "(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))" + "[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*");

	public static boolean isFloat(String s)
	{
		return DOUBLE_PATTERN.matcher(s).matches();
	}

	public static boolean isInteger(String s)
	{
		try
		{
			Integer.parseInt(s);
		}
		catch (Exception e)
		{
			return false;
		}

		return true;
	}

	public static void processCrimeReports(ArrayList<CrimeReport> crimeReports)
	{

		/*
		 * Ask the user some basic questions to determine what type of news
		 * story should be generated
		 */
		System.out.println("Please select whether the news story should focus on rising crime rates or falling crime rates:\n");
		System.out.println("<1> Falling crime rates\n" + "<2> Rising crime rates\n");
		System.out.println("Choose 1 or 2:");

		Scanner scan = new Scanner(System.in);
		int newsStoryTrend = scan.nextInt();

		System.out.println("\nPlease specify the type of crime to higlight in the report:\n");
		System.out.println("<1> Murder\n" + "<2> Rape\n" + "<3> Robbery\n" + "<4> Fel. Assault\n" + "<5> Burglary\n" + "<6> Gr. larceny\n" + "<7> G.L.A.\n"
				+ "<8> Transit\n" + "<9> Housing\n" + "<10> Petit Larceny\n" + "<11> Misd. Assault\n" + "<12> Misd. Sex Crimes\n");
		int crimeType = scan.nextInt();

		for (CrimeReport cr : crimeReports)
		{
			if (cr.isPrecinctReport)
			{
				switch (crimeType)
				{
				// crime type: murder
					case 1:
						// if we want to highlight a falling crime rate
						if (newsStoryTrend == 1)
						{
							// if it's a valid double numerical value
							if (!Double.isNaN(cr.numMurdersWTD1yChange))
							{
								if (cr.numMurdersWTD1yChange < 0.0)
								{
									//select a random sentence from amongst similar sentences to report this
									int randInt = 0 + (int) (Math.random() * ((templateStrings1.length - 1) + 1));
									//// System.out.println(randInt);

									String temp1 = templateStrings1[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp2 = temp1.replaceAll("<PERCENTAGE>", Math.abs(cr.numMurdersWTD1yChange) + "");
									String outString = temp2.replaceAll("<CRIME_TYPE>", "murders");
									// System.out.print(templateStrings0[0]);
									myOutputText += templateStrings0[0];
									// System.out.println(outString);
									myOutputText += outString;

									//select a random sentence from amongst similar sentence to report on other similar trending crimes
									ArrayList<String> fallingCrimes = new ArrayList<String>();
									//if (cr.numMurdersWTD1yChange < 0.0)
									//fallingCrimes.add("murders");
									if (cr.numRapesWTD1yChange < 0.0)
										fallingCrimes.add("rapes");
									if (cr.numRobberiesWTD1yChange < 0.0)
										fallingCrimes.add("robberies");
									if (cr.numFelAssaultsWTD1yChange < 0.0)
										fallingCrimes.add("felony assaults");
									if (cr.numBurglariesWTD1yChange < 0.0)
										fallingCrimes.add("burglaries");
									if (cr.numGrandLarceniesWTD1yChange < 0.0)
										fallingCrimes.add("grand larcenies");
									if (cr.numGLAWTD1yChange < 0.0)
										fallingCrimes.add("automobile grand larcenies");
									if (cr.numTransitWTD1yChange < 0.0)
										fallingCrimes.add("transit crimes");
									if (cr.numHousingWTD1yChange < 0.0)
										fallingCrimes.add("housing crimes");
									if (cr.numPetitLarceniesWTD1yChange < 0.0)
										fallingCrimes.add("petit larcenies");
									if (cr.numMisdAssaultsWTD1yChange < 0.0)
										fallingCrimes.add("misdemeanor assaults");
									if (cr.numMisdSexCrimesWTD1yChange < 0.0)
										fallingCrimes.add("misdemeanor sex crimes");

									int randInt2 = 0 + (int) (Math.random() * ((templateStrings3.length - 1) + 1));
									String temp3 = templateStrings3[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp4 = "";
									for (String fallingCrime : fallingCrimes)
									{
										temp4 = temp4 + ", " + fallingCrime;
									}
									String outString2 = temp3.replaceAll("<CRIME_TYPE>", temp4);
									// System.out.println(outString2);
									myOutputText += outString2;

									provideBoroughLevelStats(crimeReports, 1, cr.precinct);

								}

							}
						}
						// if we want to highlight a rising crime rate
						else if (newsStoryTrend == 2)
						{
							// if it's a valid double numerical value
							if (!Double.isNaN(cr.numMurdersWTD1yChange))
							{
								if (cr.numMurdersWTD1yChange > 0.0)
								{
									//select a random sentence from amongst similar sentences to report this
									int randInt = 0 + (int) (Math.random() * ((templateStrings1.length - 1) + 1));
									//// System.out.println(randInt);

									String temp1 = templateStrings2[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp2 = temp1.replaceAll("<PERCENTAGE>", Math.abs(cr.numMurdersWTD1yChange) + "");
									String outString = temp2.replaceAll("<CRIME_TYPE>", "murders");
									// System.out.print(templateStrings0[1]);
									myOutputText += templateStrings0[1];
									// System.out.println(outString);
									myOutputText += outString;

									//select a random sentence from amongst similar sentence to report on other similar trending crimes
									ArrayList<String> risingCrimes = new ArrayList<String>();
									//if (cr.numMurdersWTD1yChange > 0.0)
									//risingCrimes.add("murders");
									if (cr.numRapesWTD1yChange > 0.0)
										risingCrimes.add("rapes");
									if (cr.numRobberiesWTD1yChange > 0.0)
										risingCrimes.add("robberies");
									if (cr.numFelAssaultsWTD1yChange > 0.0)
										risingCrimes.add("felony assaults");
									if (cr.numBurglariesWTD1yChange > 0.0)
										risingCrimes.add("burglaries");
									if (cr.numGrandLarceniesWTD1yChange > 0.0)
										risingCrimes.add("grand larcenies");
									if (cr.numGLAWTD1yChange > 0.0)
										risingCrimes.add("automobile grand larcenies");
									if (cr.numTransitWTD1yChange > 0.0)
										risingCrimes.add("transit crimes");
									if (cr.numHousingWTD1yChange > 0.0)
										risingCrimes.add("housing crimes");
									if (cr.numPetitLarceniesWTD1yChange > 0.0)
										risingCrimes.add("petit larcenies");
									if (cr.numMisdAssaultsWTD1yChange > 0.0)
										risingCrimes.add("misdemeanor assaults");
									if (cr.numMisdSexCrimesWTD1yChange > 0.0)
										risingCrimes.add("misdemeanor sex crimes");

									int randInt2 = 0 + (int) (Math.random() * ((templateStrings4.length - 1) + 1));
									String temp3 = templateStrings4[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp4 = "";
									for (String risingCrime : risingCrimes)
									{
										temp4 = temp4 + ", " + risingCrime;
									}
									String outString2 = temp3.replaceAll("<CRIME_TYPE>", temp4);
									// System.out.println(outString2);
									myOutputText += outString2;

									provideBoroughLevelStats(crimeReports, 1, cr.precinct);
								}

							}
						}
						break;

					// crime type: rape
					case 2:
						// if we want to highlight a falling crime rate
						if (newsStoryTrend == 1)
						{
							// if it's a valid double numerical value
							if (!Double.isNaN(cr.numRapesWTD1yChange))
							{
								if (cr.numRapesWTD1yChange < 0.0)
								{
									//select a random sentence from amongst similar sentences to report this
									int randInt = 0 + (int) (Math.random() * ((templateStrings1.length - 1) + 1));
									//// System.out.println(randInt);

									String temp1 = templateStrings1[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp2 = temp1.replaceAll("<PERCENTAGE>", Math.abs(cr.numRapesWTD1yChange) + "");
									String outString = temp2.replaceAll("<CRIME_TYPE>", "rapes");
									// System.out.print(templateStrings0[0]);
									myOutputText += templateStrings0[0];
									// System.out.println(outString);
									myOutputText += outString;

									//select a random sentence from amongst similar sentence to report on other similar trending crimes
									ArrayList<String> fallingCrimes = new ArrayList<String>();
									if (cr.numMurdersWTD1yChange < 0.0)
										fallingCrimes.add("murders");
									//if (cr.numRapesWTD1yChange < 0.0)
									//fallingCrimes.add("rapes");
									if (cr.numRobberiesWTD1yChange < 0.0)
										fallingCrimes.add("robberies");
									if (cr.numFelAssaultsWTD1yChange < 0.0)
										fallingCrimes.add("felony assaults");
									if (cr.numBurglariesWTD1yChange < 0.0)
										fallingCrimes.add("burglaries");
									if (cr.numGrandLarceniesWTD1yChange < 0.0)
										fallingCrimes.add("grand larcenies");
									if (cr.numGLAWTD1yChange < 0.0)
										fallingCrimes.add("automobile grand larcenies");
									if (cr.numTransitWTD1yChange < 0.0)
										fallingCrimes.add("transit crimes");
									if (cr.numHousingWTD1yChange < 0.0)
										fallingCrimes.add("housing crimes");
									if (cr.numPetitLarceniesWTD1yChange < 0.0)
										fallingCrimes.add("petit larcenies");
									if (cr.numMisdAssaultsWTD1yChange < 0.0)
										fallingCrimes.add("misdemeanor assaults");
									if (cr.numMisdSexCrimesWTD1yChange < 0.0)
										fallingCrimes.add("misdemeanor sex crimes");

									int randInt2 = 0 + (int) (Math.random() * ((templateStrings3.length - 1) + 1));
									String temp3 = templateStrings3[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp4 = "";
									for (String fallingCrime : fallingCrimes)
									{
										temp4 = temp4 + ", " + fallingCrime;
									}
									String outString2 = temp3.replaceAll("<CRIME_TYPE>", temp4);
									// System.out.println(outString2);
									myOutputText += outString2;

									provideBoroughLevelStats(crimeReports, 2, cr.precinct);
								}

							}
						}
						// if we want to highlight a rising crime rate
						else if (newsStoryTrend == 2)
						{
							// if it's a valid double numerical value
							if (!Double.isNaN(cr.numRapesWTD1yChange))
							{
								if (cr.numRapesWTD1yChange > 0.0)
								{
									//select a random sentence from amongst similar sentences to report this
									int randInt = 0 + (int) (Math.random() * ((templateStrings1.length - 1) + 1));
									//// System.out.println(randInt);

									String temp1 = templateStrings2[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp2 = temp1.replaceAll("<PERCENTAGE>", Math.abs(cr.numRapesWTD1yChange) + "");
									String outString = temp2.replaceAll("<CRIME_TYPE>", "rapes");
									// System.out.print(templateStrings0[1]);
									myOutputText += templateStrings0[1];
									// System.out.println(outString);
									myOutputText += outString;

									//select a random sentence from amongst similar sentence to report on other similar trending crimes
									ArrayList<String> risingCrimes = new ArrayList<String>();
									if (cr.numMurdersWTD1yChange > 0.0)
										risingCrimes.add("murders");
									//if (cr.numRapesWTD1yChange > 0.0)
									//risingCrimes.add("rapes");
									if (cr.numRobberiesWTD1yChange > 0.0)
										risingCrimes.add("robberies");
									if (cr.numFelAssaultsWTD1yChange > 0.0)
										risingCrimes.add("felony assaults");
									if (cr.numBurglariesWTD1yChange > 0.0)
										risingCrimes.add("burglaries");
									if (cr.numGrandLarceniesWTD1yChange > 0.0)
										risingCrimes.add("grand larcenies");
									if (cr.numGLAWTD1yChange > 0.0)
										risingCrimes.add("automobile grand larcenies");
									if (cr.numTransitWTD1yChange > 0.0)
										risingCrimes.add("transit crimes");
									if (cr.numHousingWTD1yChange > 0.0)
										risingCrimes.add("housing crimes");
									if (cr.numPetitLarceniesWTD1yChange > 0.0)
										risingCrimes.add("petit larcenies");
									if (cr.numMisdAssaultsWTD1yChange > 0.0)
										risingCrimes.add("misdemeanor assaults");
									if (cr.numMisdSexCrimesWTD1yChange > 0.0)
										risingCrimes.add("misdemeanor sex crimes");

									int randInt2 = 0 + (int) (Math.random() * ((templateStrings4.length - 1) + 1));
									String temp3 = templateStrings4[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp4 = "";
									for (String risingCrime : risingCrimes)
									{
										temp4 = temp4 + ", " + risingCrime;
									}
									String outString2 = temp3.replaceAll("<CRIME_TYPE>", temp4);
									// System.out.println(outString2);
									myOutputText += outString2;

									provideBoroughLevelStats(crimeReports, 2, cr.precinct);

								}

							}
						}
						break;

					// crime type: robbery
					case 3:
						// if we want to highlight a falling crime rate
						if (newsStoryTrend == 1)
						{
							// if it's a valid double numerical value
							if (!Double.isNaN(cr.numRobberiesWTD1yChange))
							{
								if (cr.numRobberiesWTD1yChange < 0.0)
								{
									//select a random sentence from amongst similar sentences to report this
									int randInt = 0 + (int) (Math.random() * ((templateStrings1.length - 1) + 1));
									//// System.out.println(randInt);

									String temp1 = templateStrings1[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp2 = temp1.replaceAll("<PERCENTAGE>", Math.abs(cr.numRobberiesWTD1yChange) + "");
									String outString = temp2.replaceAll("<CRIME_TYPE>", "robberies");
									// System.out.print(templateStrings0[0]);
									myOutputText += templateStrings0[0];
									// System.out.println(outString);
									myOutputText += outString;

									//select a random sentence from amongst similar sentence to report on other similar trending crimes
									ArrayList<String> fallingCrimes = new ArrayList<String>();
									if (cr.numMurdersWTD1yChange < 0.0)
										fallingCrimes.add("murders");
									if (cr.numRapesWTD1yChange < 0.0)
										fallingCrimes.add("rapes");
									//if (cr.numRobberiesWTD1yChange < 0.0)
									//fallingCrimes.add("robberies");
									if (cr.numFelAssaultsWTD1yChange < 0.0)
										fallingCrimes.add("felony assaults");
									if (cr.numBurglariesWTD1yChange < 0.0)
										fallingCrimes.add("burglaries");
									if (cr.numGrandLarceniesWTD1yChange < 0.0)
										fallingCrimes.add("grand larcenies");
									if (cr.numGLAWTD1yChange < 0.0)
										fallingCrimes.add("automobile grand larcenies");
									if (cr.numTransitWTD1yChange < 0.0)
										fallingCrimes.add("transit crimes");
									if (cr.numHousingWTD1yChange < 0.0)
										fallingCrimes.add("housing crimes");
									if (cr.numPetitLarceniesWTD1yChange < 0.0)
										fallingCrimes.add("petit larcenies");
									if (cr.numMisdAssaultsWTD1yChange < 0.0)
										fallingCrimes.add("misdemeanor assaults");
									if (cr.numMisdSexCrimesWTD1yChange < 0.0)
										fallingCrimes.add("misdemeanor sex crimes");

									int randInt2 = 0 + (int) (Math.random() * ((templateStrings3.length - 1) + 1));
									String temp3 = templateStrings3[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp4 = "";
									for (String fallingCrime : fallingCrimes)
									{
										temp4 = temp4 + ", " + fallingCrime;
									}
									String outString2 = temp3.replaceAll("<CRIME_TYPE>", temp4);
									// System.out.println(outString2);
									myOutputText += outString2;

									provideBoroughLevelStats(crimeReports, 3, cr.precinct);
								}

							}
						}
						// if we want to highlight a rising crime rate
						else if (newsStoryTrend == 2)
						{
							// if it's a valid double numerical value
							if (!Double.isNaN(cr.numRobberiesWTD1yChange))
							{
								if (cr.numRobberiesWTD1yChange > 0.0)
								{
									//select a random sentence from amongst similar sentences to report this
									int randInt = 0 + (int) (Math.random() * ((templateStrings1.length - 1) + 1));
									//// System.out.println(randInt);

									String temp1 = templateStrings2[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp2 = temp1.replaceAll("<PERCENTAGE>", Math.abs(cr.numRobberiesWTD1yChange) + "");
									String outString = temp2.replaceAll("<CRIME_TYPE>", "robberies");
									// System.out.print(templateStrings0[1]);
									myOutputText += templateStrings0[1];
									// System.out.println(outString);
									myOutputText += outString;

									//select a random sentence from amongst similar sentence to report on other similar trending crimes
									ArrayList<String> risingCrimes = new ArrayList<String>();
									if (cr.numMurdersWTD1yChange > 0.0)
										risingCrimes.add("murders");
									if (cr.numRapesWTD1yChange > 0.0)
										risingCrimes.add("rapes");
									//if (cr.numRobberiesWTD1yChange > 0.0)
									//risingCrimes.add("robberies");
									if (cr.numFelAssaultsWTD1yChange > 0.0)
										risingCrimes.add("felony assaults");
									if (cr.numBurglariesWTD1yChange > 0.0)
										risingCrimes.add("burglaries");
									if (cr.numGrandLarceniesWTD1yChange > 0.0)
										risingCrimes.add("grand larcenies");
									if (cr.numGLAWTD1yChange > 0.0)
										risingCrimes.add("automobile grand larcenies");
									if (cr.numTransitWTD1yChange > 0.0)
										risingCrimes.add("transit crimes");
									if (cr.numHousingWTD1yChange > 0.0)
										risingCrimes.add("housing crimes");
									if (cr.numPetitLarceniesWTD1yChange > 0.0)
										risingCrimes.add("petit larcenies");
									if (cr.numMisdAssaultsWTD1yChange > 0.0)
										risingCrimes.add("misdemeanor assaults");
									if (cr.numMisdSexCrimesWTD1yChange > 0.0)
										risingCrimes.add("misdemeanor sex crimes");

									int randInt2 = 0 + (int) (Math.random() * ((templateStrings4.length - 1) + 1));
									String temp3 = templateStrings4[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp4 = "";
									for (String risingCrime : risingCrimes)
									{
										temp4 = temp4 + ", " + risingCrime;
									}
									String outString2 = temp3.replaceAll("<CRIME_TYPE>", temp4);
									// System.out.println(outString2);
									myOutputText += outString2;

									provideBoroughLevelStats(crimeReports, 3, cr.precinct);

								}

							}
						}
						break;

					// crime type: fel. assault
					case 4:
						// if we want to highlight a falling crime rate
						if (newsStoryTrend == 1)
						{
							// if it's a valid double numerical value
							if (!Double.isNaN(cr.numFelAssaultsWTD1yChange))
							{
								if (cr.numFelAssaultsWTD1yChange < 0.0)
								{
									//select a random sentence from amongst similar sentences to report this
									int randInt = 0 + (int) (Math.random() * ((templateStrings1.length - 1) + 1));
									//// System.out.println(randInt);

									String temp1 = templateStrings1[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp2 = temp1.replaceAll("<PERCENTAGE>", Math.abs(cr.numFelAssaultsWTD1yChange) + "");
									String outString = temp2.replaceAll("<CRIME_TYPE>", "felony assaults");
									// System.out.print(templateStrings0[0]);
									myOutputText += templateStrings0[0];
									// System.out.println(outString);
									myOutputText += outString;

									//select a random sentence from amongst similar sentence to report on other similar trending crimes
									ArrayList<String> fallingCrimes = new ArrayList<String>();
									if (cr.numMurdersWTD1yChange < 0.0)
										fallingCrimes.add("murders");
									if (cr.numRapesWTD1yChange < 0.0)
										fallingCrimes.add("rapes");
									if (cr.numRobberiesWTD1yChange < 0.0)
										fallingCrimes.add("robberies");
									//if (cr.numFelAssaultsWTD1yChange < 0.0)
									//fallingCrimes.add("felony assaults");
									if (cr.numBurglariesWTD1yChange < 0.0)
										fallingCrimes.add("burglaries");
									if (cr.numGrandLarceniesWTD1yChange < 0.0)
										fallingCrimes.add("grand larcenies");
									if (cr.numGLAWTD1yChange < 0.0)
										fallingCrimes.add("automobile grand larcenies");
									if (cr.numTransitWTD1yChange < 0.0)
										fallingCrimes.add("transit crimes");
									if (cr.numHousingWTD1yChange < 0.0)
										fallingCrimes.add("housing crimes");
									if (cr.numPetitLarceniesWTD1yChange < 0.0)
										fallingCrimes.add("petit larcenies");
									if (cr.numMisdAssaultsWTD1yChange < 0.0)
										fallingCrimes.add("misdemeanor assaults");
									if (cr.numMisdSexCrimesWTD1yChange < 0.0)
										fallingCrimes.add("misdemeanor sex crimes");

									int randInt2 = 0 + (int) (Math.random() * ((templateStrings3.length - 1) + 1));
									String temp3 = templateStrings3[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp4 = "";
									for (String fallingCrime : fallingCrimes)
									{
										temp4 = temp4 + ", " + fallingCrime;
									}
									String outString2 = temp3.replaceAll("<CRIME_TYPE>", temp4);
									// System.out.println(outString2);
									myOutputText += outString2;

									provideBoroughLevelStats(crimeReports, 4, cr.precinct);
								}

							}
						}
						// if we want to highlight a rising crime rate
						else if (newsStoryTrend == 2)
						{
							// if it's a valid double numerical value
							if (!Double.isNaN(cr.numFelAssaultsWTD1yChange))
							{
								if (cr.numFelAssaultsWTD1yChange > 0.0)
								{
									//select a random sentence from amongst similar sentences to report this
									int randInt = 0 + (int) (Math.random() * ((templateStrings1.length - 1) + 1));
									//// System.out.println(randInt);

									String temp1 = templateStrings2[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp2 = temp1.replaceAll("<PERCENTAGE>", Math.abs(cr.numFelAssaultsWTD1yChange) + "");
									String outString = temp2.replaceAll("<CRIME_TYPE>", "felony assaults");
									// System.out.print(templateStrings0[1]);
									myOutputText += templateStrings0[1];
									// System.out.println(outString);
									myOutputText += outString;

									//select a random sentence from amongst similar sentence to report on other similar trending crimes
									ArrayList<String> risingCrimes = new ArrayList<String>();
									if (cr.numMurdersWTD1yChange > 0.0)
										risingCrimes.add("murders");
									if (cr.numRapesWTD1yChange > 0.0)
										risingCrimes.add("rapes");
									if (cr.numRobberiesWTD1yChange > 0.0)
										risingCrimes.add("robberies");
									//if (cr.numFelAssaultsWTD1yChange > 0.0)
									//risingCrimes.add("felony assaults");
									if (cr.numBurglariesWTD1yChange > 0.0)
										risingCrimes.add("burglaries");
									if (cr.numGrandLarceniesWTD1yChange > 0.0)
										risingCrimes.add("grand larcenies");
									if (cr.numGLAWTD1yChange > 0.0)
										risingCrimes.add("automobile grand larcenies");
									if (cr.numTransitWTD1yChange > 0.0)
										risingCrimes.add("transit crimes");
									if (cr.numHousingWTD1yChange > 0.0)
										risingCrimes.add("housing crimes");
									if (cr.numPetitLarceniesWTD1yChange > 0.0)
										risingCrimes.add("petit larcenies");
									if (cr.numMisdAssaultsWTD1yChange > 0.0)
										risingCrimes.add("misdemeanor assaults");
									if (cr.numMisdSexCrimesWTD1yChange > 0.0)
										risingCrimes.add("misdemeanor sex crimes");

									int randInt2 = 0 + (int) (Math.random() * ((templateStrings4.length - 1) + 1));
									String temp3 = templateStrings4[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp4 = "";
									for (String risingCrime : risingCrimes)
									{
										temp4 = temp4 + ", " + risingCrime;
									}
									String outString2 = temp3.replaceAll("<CRIME_TYPE>", temp4);
									// System.out.println(outString2);
									myOutputText += outString2;

									provideBoroughLevelStats(crimeReports, 4, cr.precinct);
								}

							}
						}
						break;

					// crime type: burglary
					case 5:
						// if we want to highlight a falling crime rate
						if (newsStoryTrend == 1)
						{
							// if it's a valid double numerical value
							if (!Double.isNaN(cr.numBurglariesWTD1yChange))
							{
								if (cr.numBurglariesWTD1yChange < 0.0)
								{
									//select a random sentence from amongst similar sentences to report this
									int randInt = 0 + (int) (Math.random() * ((templateStrings1.length - 1) + 1));
									//// System.out.println(randInt);

									String temp1 = templateStrings1[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp2 = temp1.replaceAll("<PERCENTAGE>", Math.abs(cr.numBurglariesWTD1yChange) + "");
									String outString = temp2.replaceAll("<CRIME_TYPE>", "burglaries");
									// System.out.print(templateStrings0[0]);
									myOutputText += templateStrings0[0];
									// System.out.println(outString);
									myOutputText += outString;

									//select a random sentence from amongst similar sentence to report on other similar trending crimes
									ArrayList<String> fallingCrimes = new ArrayList<String>();
									if (cr.numMurdersWTD1yChange < 0.0)
										fallingCrimes.add("murders");
									if (cr.numRapesWTD1yChange < 0.0)
										fallingCrimes.add("rapes");
									if (cr.numRobberiesWTD1yChange < 0.0)
										fallingCrimes.add("robberies");
									if (cr.numFelAssaultsWTD1yChange < 0.0)
										fallingCrimes.add("felony assaults");
									//if (cr.numBurglariesWTD1yChange < 0.0)
									//fallingCrimes.add("burglaries");
									if (cr.numGrandLarceniesWTD1yChange < 0.0)
										fallingCrimes.add("grand larcenies");
									if (cr.numGLAWTD1yChange < 0.0)
										fallingCrimes.add("automobile grand larcenies");
									if (cr.numTransitWTD1yChange < 0.0)
										fallingCrimes.add("transit crimes");
									if (cr.numHousingWTD1yChange < 0.0)
										fallingCrimes.add("housing crimes");
									if (cr.numPetitLarceniesWTD1yChange < 0.0)
										fallingCrimes.add("petit larcenies");
									if (cr.numMisdAssaultsWTD1yChange < 0.0)
										fallingCrimes.add("misdemeanor assaults");
									if (cr.numMisdSexCrimesWTD1yChange < 0.0)
										fallingCrimes.add("misdemeanor sex crimes");

									int randInt2 = 0 + (int) (Math.random() * ((templateStrings3.length - 1) + 1));
									String temp3 = templateStrings3[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp4 = "";
									for (String fallingCrime : fallingCrimes)
									{
										temp4 = temp4 + ", " + fallingCrime;
									}
									String outString2 = temp3.replaceAll("<CRIME_TYPE>", temp4);
									// System.out.println(outString2);
									myOutputText += outString2;

									provideBoroughLevelStats(crimeReports, 5, cr.precinct);
								}

							}
						}
						// if we want to highlight a rising crime rate
						else if (newsStoryTrend == 2)
						{
							// if it's a valid double numerical value
							if (!Double.isNaN(cr.numBurglariesWTD1yChange))
							{
								if (cr.numBurglariesWTD1yChange > 0.0)
								{
									//select a random sentence from amongst similar sentences to report this
									int randInt = 0 + (int) (Math.random() * ((templateStrings1.length - 1) + 1));
									//// System.out.println(randInt);

									String temp1 = templateStrings2[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp2 = temp1.replaceAll("<PERCENTAGE>", Math.abs(cr.numBurglariesWTD1yChange) + "");
									String outString = temp2.replaceAll("<CRIME_TYPE>", "burglaries");
									// System.out.print(templateStrings0[1]);
									myOutputText += templateStrings0[1];
									// System.out.println(outString);
									myOutputText += outString;

									//select a random sentence from amongst similar sentence to report on other similar trending crimes
									ArrayList<String> risingCrimes = new ArrayList<String>();
									if (cr.numMurdersWTD1yChange > 0.0)
										risingCrimes.add("murders");
									if (cr.numRapesWTD1yChange > 0.0)
										risingCrimes.add("rapes");
									if (cr.numRobberiesWTD1yChange > 0.0)
										risingCrimes.add("robberies");
									if (cr.numFelAssaultsWTD1yChange > 0.0)
										risingCrimes.add("felony assaults");
									//if (cr.numBurglariesWTD1yChange > 0.0)
									//risingCrimes.add("burglaries");
									if (cr.numGrandLarceniesWTD1yChange > 0.0)
										risingCrimes.add("grand larcenies");
									if (cr.numGLAWTD1yChange > 0.0)
										risingCrimes.add("automobile grand larcenies");
									if (cr.numTransitWTD1yChange > 0.0)
										risingCrimes.add("transit crimes");
									if (cr.numHousingWTD1yChange > 0.0)
										risingCrimes.add("housing crimes");
									if (cr.numPetitLarceniesWTD1yChange > 0.0)
										risingCrimes.add("petit larcenies");
									if (cr.numMisdAssaultsWTD1yChange > 0.0)
										risingCrimes.add("misdemeanor assaults");
									if (cr.numMisdSexCrimesWTD1yChange > 0.0)
										risingCrimes.add("misdemeanor sex crimes");

									int randInt2 = 0 + (int) (Math.random() * ((templateStrings4.length - 1) + 1));
									String temp3 = templateStrings4[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp4 = "";
									for (String risingCrime : risingCrimes)
									{
										temp4 = temp4 + ", " + risingCrime;
									}
									String outString2 = temp3.replaceAll("<CRIME_TYPE>", temp4);
									// System.out.println(outString2);
									myOutputText += outString2;

									provideBoroughLevelStats(crimeReports, 5, cr.precinct);
								}

							}
						}
						break;

					// crime type: grand larceny
					case 6:
						// if we want to highlight a falling crime rate
						if (newsStoryTrend == 1)
						{
							// if it's a valid double numerical value
							if (!Double.isNaN(cr.numGrandLarceniesWTD1yChange))
							{
								if (cr.numGrandLarceniesWTD1yChange < 0.0)
								{
									//select a random sentence from amongst similar sentences to report this
									int randInt = 0 + (int) (Math.random() * ((templateStrings1.length - 1) + 1));
									//// System.out.println(randInt);

									String temp1 = templateStrings1[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp2 = temp1.replaceAll("<PERCENTAGE>", Math.abs(cr.numGrandLarceniesWTD1yChange) + "");
									String outString = temp2.replaceAll("<CRIME_TYPE>", "grand larcenies");
									// System.out.print(templateStrings0[0]);
									myOutputText += templateStrings0[0];
									// System.out.println(outString);
									myOutputText += outString;

									//select a random sentence from amongst similar sentence to report on other similar trending crimes
									ArrayList<String> fallingCrimes = new ArrayList<String>();
									if (cr.numMurdersWTD1yChange < 0.0)
										fallingCrimes.add("murders");
									if (cr.numRapesWTD1yChange < 0.0)
										fallingCrimes.add("rapes");
									if (cr.numRobberiesWTD1yChange < 0.0)
										fallingCrimes.add("robberies");
									if (cr.numFelAssaultsWTD1yChange < 0.0)
										fallingCrimes.add("felony assaults");
									if (cr.numBurglariesWTD1yChange < 0.0)
										fallingCrimes.add("burglaries");
									//if (cr.numGrandLarceniesWTD1yChange < 0.0)
									//fallingCrimes.add("grand larcenies");
									if (cr.numGLAWTD1yChange < 0.0)
										fallingCrimes.add("automobile grand larcenies");
									if (cr.numTransitWTD1yChange < 0.0)
										fallingCrimes.add("transit crimes");
									if (cr.numHousingWTD1yChange < 0.0)
										fallingCrimes.add("housing crimes");
									if (cr.numPetitLarceniesWTD1yChange < 0.0)
										fallingCrimes.add("petit larcenies");
									if (cr.numMisdAssaultsWTD1yChange < 0.0)
										fallingCrimes.add("misdemeanor assaults");
									if (cr.numMisdSexCrimesWTD1yChange < 0.0)
										fallingCrimes.add("misdemeanor sex crimes");

									int randInt2 = 0 + (int) (Math.random() * ((templateStrings3.length - 1) + 1));
									String temp3 = templateStrings3[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp4 = "";
									for (String fallingCrime : fallingCrimes)
									{
										temp4 = temp4 + ", " + fallingCrime;
									}
									String outString2 = temp3.replaceAll("<CRIME_TYPE>", temp4);
									// System.out.println(outString2);
									myOutputText += outString2;

									provideBoroughLevelStats(crimeReports, 6, cr.precinct);
								}

							}
						}
						// if we want to highlight a rising crime rate
						else if (newsStoryTrend == 2)
						{
							// if it's a valid double numerical value
							if (!Double.isNaN(cr.numGrandLarceniesWTD1yChange))
							{
								if (cr.numGrandLarceniesWTD1yChange > 0.0)
								{
									//select a random sentence from amongst similar sentences to report this
									int randInt = 0 + (int) (Math.random() * ((templateStrings1.length - 1) + 1));
									//// System.out.println(randInt);

									String temp1 = templateStrings2[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp2 = temp1.replaceAll("<PERCENTAGE>", Math.abs(cr.numGrandLarceniesWTD1yChange) + "");
									String outString = temp2.replaceAll("<CRIME_TYPE>", "grand larcenies");
									// System.out.print(templateStrings0[1]);
									myOutputText += templateStrings0[1];
									// System.out.println(outString);
									myOutputText += outString;

									//select a random sentence from amongst similar sentence to report on other similar trending crimes
									ArrayList<String> risingCrimes = new ArrayList<String>();
									if (cr.numMurdersWTD1yChange > 0.0)
										risingCrimes.add("murders");
									if (cr.numRapesWTD1yChange > 0.0)
										risingCrimes.add("rapes");
									if (cr.numRobberiesWTD1yChange > 0.0)
										risingCrimes.add("robberies");
									if (cr.numFelAssaultsWTD1yChange > 0.0)
										risingCrimes.add("felony assaults");
									if (cr.numBurglariesWTD1yChange > 0.0)
										risingCrimes.add("burglaries");
									//if (cr.numGrandLarceniesWTD1yChange > 0.0)
									//risingCrimes.add("grand larcenies");
									if (cr.numGLAWTD1yChange > 0.0)
										risingCrimes.add("automobile grand larcenies");
									if (cr.numTransitWTD1yChange > 0.0)
										risingCrimes.add("transit crimes");
									if (cr.numHousingWTD1yChange > 0.0)
										risingCrimes.add("housing crimes");
									if (cr.numPetitLarceniesWTD1yChange > 0.0)
										risingCrimes.add("petit larcenies");
									if (cr.numMisdAssaultsWTD1yChange > 0.0)
										risingCrimes.add("misdemeanor assaults");
									if (cr.numMisdSexCrimesWTD1yChange > 0.0)
										risingCrimes.add("misdemeanor sex crimes");

									int randInt2 = 0 + (int) (Math.random() * ((templateStrings4.length - 1) + 1));
									String temp3 = templateStrings4[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp4 = "";
									for (String risingCrime : risingCrimes)
									{
										temp4 = temp4 + ", " + risingCrime;
									}
									String outString2 = temp3.replaceAll("<CRIME_TYPE>", temp4);
									// System.out.println(outString2);
									myOutputText += outString2;

									provideBoroughLevelStats(crimeReports, 6, cr.precinct);
								}

							}
						}
						break;

					// crime type: auto grand larceny
					case 7:
						// if we want to highlight a falling crime rate
						if (newsStoryTrend == 1)
						{
							// if it's a valid double numerical value
							if (!Double.isNaN(cr.numGLAWTD1yChange))
							{
								if (cr.numGLAWTD1yChange < 0.0)
								{
									//select a random sentence from amongst similar sentences to report this
									int randInt = 0 + (int) (Math.random() * ((templateStrings1.length - 1) + 1));
									//// System.out.println(randInt);

									String temp1 = templateStrings1[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp2 = temp1.replaceAll("<PERCENTAGE>", Math.abs(cr.numGLAWTD1yChange) + "");
									String outString = temp2.replaceAll("<CRIME_TYPE>", "automobile grand larcenies ");
									// System.out.print(templateStrings0[0]);
									myOutputText += templateStrings0[0];
									// System.out.println(outString);
									myOutputText += outString;

									//select a random sentence from amongst similar sentence to report on other similar trending crimes
									ArrayList<String> fallingCrimes = new ArrayList<String>();
									if (cr.numMurdersWTD1yChange < 0.0)
										fallingCrimes.add("murders");
									if (cr.numRapesWTD1yChange < 0.0)
										fallingCrimes.add("rapes");
									if (cr.numRobberiesWTD1yChange < 0.0)
										fallingCrimes.add("robberies");
									if (cr.numFelAssaultsWTD1yChange < 0.0)
										fallingCrimes.add("felony assaults");
									if (cr.numBurglariesWTD1yChange < 0.0)
										fallingCrimes.add("burglaries");
									if (cr.numGrandLarceniesWTD1yChange < 0.0)
										fallingCrimes.add("grand larcenies");
									//if (cr.numGLAWTD1yChange < 0.0)
									//fallingCrimes.add("automobile grand larcenies");
									if (cr.numTransitWTD1yChange < 0.0)
										fallingCrimes.add("transit crimes");
									if (cr.numHousingWTD1yChange < 0.0)
										fallingCrimes.add("housing crimes");
									if (cr.numPetitLarceniesWTD1yChange < 0.0)
										fallingCrimes.add("petit larcenies");
									if (cr.numMisdAssaultsWTD1yChange < 0.0)
										fallingCrimes.add("misdemeanor assaults");
									if (cr.numMisdSexCrimesWTD1yChange < 0.0)
										fallingCrimes.add("misdemeanor sex crimes");

									int randInt2 = 0 + (int) (Math.random() * ((templateStrings3.length - 1) + 1));
									String temp3 = templateStrings3[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp4 = "";
									for (String fallingCrime : fallingCrimes)
									{
										temp4 = temp4 + ", " + fallingCrime;
									}
									String outString2 = temp3.replaceAll("<CRIME_TYPE>", temp4);
									// System.out.println(outString2);
									myOutputText += outString2;

									provideBoroughLevelStats(crimeReports, 7, cr.precinct);
								}

							}
						}
						// if we want to highlight a rising crime rate
						else if (newsStoryTrend == 2)
						{
							// if it's a valid double numerical value
							if (!Double.isNaN(cr.numGLAWTD1yChange))
							{
								if (cr.numGLAWTD1yChange > 0.0)
								{
									//select a random sentence from amongst similar sentences to report this
									int randInt = 0 + (int) (Math.random() * ((templateStrings1.length - 1) + 1));
									//// System.out.println(randInt);

									String temp1 = templateStrings2[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp2 = temp1.replaceAll("<PERCENTAGE>", Math.abs(cr.numGLAWTD1yChange) + "");
									String outString = temp2.replaceAll("<CRIME_TYPE>", "automobile grand larcenies ");
									// System.out.print(templateStrings0[1]);
									myOutputText += templateStrings0[1];
									// System.out.println(outString);
									myOutputText += outString;

									//select a random sentence from amongst similar sentence to report on other similar trending crimes
									ArrayList<String> risingCrimes = new ArrayList<String>();
									if (cr.numMurdersWTD1yChange > 0.0)
										risingCrimes.add("murders");
									if (cr.numRapesWTD1yChange > 0.0)
										risingCrimes.add("rapes");
									if (cr.numRobberiesWTD1yChange > 0.0)
										risingCrimes.add("robberies");
									if (cr.numFelAssaultsWTD1yChange > 0.0)
										risingCrimes.add("felony assaults");
									if (cr.numBurglariesWTD1yChange > 0.0)
										risingCrimes.add("burglaries");
									if (cr.numGrandLarceniesWTD1yChange > 0.0)
										risingCrimes.add("grand larcenies");
									//if (cr.numGLAWTD1yChange > 0.0)
									//risingCrimes.add("automobile grand larcenies");
									if (cr.numTransitWTD1yChange > 0.0)
										risingCrimes.add("transit crimes");
									if (cr.numHousingWTD1yChange > 0.0)
										risingCrimes.add("housing crimes");
									if (cr.numPetitLarceniesWTD1yChange > 0.0)
										risingCrimes.add("petit larcenies");
									if (cr.numMisdAssaultsWTD1yChange > 0.0)
										risingCrimes.add("misdemeanor assaults");
									if (cr.numMisdSexCrimesWTD1yChange > 0.0)
										risingCrimes.add("misdemeanor sex crimes");

									int randInt2 = 0 + (int) (Math.random() * ((templateStrings4.length - 1) + 1));
									String temp3 = templateStrings4[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp4 = "";
									for (String risingCrime : risingCrimes)
									{
										temp4 = temp4 + ", " + risingCrime;
									}
									String outString2 = temp3.replaceAll("<CRIME_TYPE>", temp4);
									// System.out.println(outString2);
									myOutputText += outString2;

									provideBoroughLevelStats(crimeReports, 7, cr.precinct);
								}

							}
						}
						break;

					// crime type: transit
					case 8:
						// if we want to highlight a falling crime rate
						if (newsStoryTrend == 1)
						{
							// if it's a valid double numerical value
							if (!Double.isNaN(cr.numTransitWTD1yChange))
							{
								if (cr.numTransitWTD1yChange < 0.0)
								{
									//select a random sentence from amongst similar sentences to report this
									int randInt = 0 + (int) (Math.random() * ((templateStrings1.length - 1) + 1));
									//// System.out.println(randInt);

									String temp1 = templateStrings1[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp2 = temp1.replaceAll("<PERCENTAGE>", Math.abs(cr.numTransitWTD1yChange) + "");
									String outString = temp2.replaceAll("<CRIME_TYPE>", "transit crimes ");
									// System.out.print(templateStrings0[0]);
									myOutputText += templateStrings0[0];
									// System.out.println(outString);
									myOutputText += outString;

									//select a random sentence from amongst similar sentence to report on other similar trending crimes
									ArrayList<String> fallingCrimes = new ArrayList<String>();
									if (cr.numMurdersWTD1yChange < 0.0)
										fallingCrimes.add("murders");
									if (cr.numRapesWTD1yChange < 0.0)
										fallingCrimes.add("rapes");
									if (cr.numRobberiesWTD1yChange < 0.0)
										fallingCrimes.add("robberies");
									if (cr.numFelAssaultsWTD1yChange < 0.0)
										fallingCrimes.add("felony assaults");
									if (cr.numBurglariesWTD1yChange < 0.0)
										fallingCrimes.add("burglaries");
									if (cr.numGrandLarceniesWTD1yChange < 0.0)
										fallingCrimes.add("grand larcenies");
									if (cr.numGLAWTD1yChange < 0.0)
										fallingCrimes.add("automobile grand larcenies");
									//if (cr.numTransitWTD1yChange < 0.0)
									//fallingCrimes.add("transit crimes");
									if (cr.numHousingWTD1yChange < 0.0)
										fallingCrimes.add("housing crimes");
									if (cr.numPetitLarceniesWTD1yChange < 0.0)
										fallingCrimes.add("petit larcenies");
									if (cr.numMisdAssaultsWTD1yChange < 0.0)
										fallingCrimes.add("misdemeanor assaults");
									if (cr.numMisdSexCrimesWTD1yChange < 0.0)
										fallingCrimes.add("misdemeanor sex crimes");

									int randInt2 = 0 + (int) (Math.random() * ((templateStrings3.length - 1) + 1));
									String temp3 = templateStrings3[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp4 = "";
									for (String fallingCrime : fallingCrimes)
									{
										temp4 = temp4 + ", " + fallingCrime;
									}
									String outString2 = temp3.replaceAll("<CRIME_TYPE>", temp4);
									// System.out.println(outString2);
									myOutputText += outString2;

									provideBoroughLevelStats(crimeReports, 8, cr.precinct);
								}

							}
						}
						// if we want to highlight a rising crime rate
						else if (newsStoryTrend == 2)
						{
							// if it's a valid double numerical value
							if (!Double.isNaN(cr.numTransitWTD1yChange))
							{
								if (cr.numTransitWTD1yChange > 0.0)
								{
									//select a random sentence from amongst similar sentences to report this
									int randInt = 0 + (int) (Math.random() * ((templateStrings1.length - 1) + 1));
									//// System.out.println(randInt);

									String temp1 = templateStrings2[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp2 = temp1.replaceAll("<PERCENTAGE>", Math.abs(cr.numTransitWTD1yChange) + "");
									String outString = temp2.replaceAll("<CRIME_TYPE>", "transit crimes ");
									// System.out.print(templateStrings0[1]);
									myOutputText += templateStrings0[1];
									// System.out.println(outString);
									myOutputText += outString;

									//select a random sentence from amongst similar sentence to report on other similar trending crimes
									ArrayList<String> risingCrimes = new ArrayList<String>();
									if (cr.numMurdersWTD1yChange > 0.0)
										risingCrimes.add("murders");
									if (cr.numRapesWTD1yChange > 0.0)
										risingCrimes.add("rapes");
									if (cr.numRobberiesWTD1yChange > 0.0)
										risingCrimes.add("robberies");
									if (cr.numFelAssaultsWTD1yChange > 0.0)
										risingCrimes.add("felony assaults");
									if (cr.numBurglariesWTD1yChange > 0.0)
										risingCrimes.add("burglaries");
									if (cr.numGrandLarceniesWTD1yChange > 0.0)
										risingCrimes.add("grand larcenies");
									if (cr.numGLAWTD1yChange > 0.0)
										risingCrimes.add("automobile grand larcenies");
									//if (cr.numTransitWTD1yChange > 0.0)
									//risingCrimes.add("transit crimes");
									if (cr.numHousingWTD1yChange > 0.0)
										risingCrimes.add("housing crimes");
									if (cr.numPetitLarceniesWTD1yChange > 0.0)
										risingCrimes.add("petit larcenies");
									if (cr.numMisdAssaultsWTD1yChange > 0.0)
										risingCrimes.add("misdemeanor assaults");
									if (cr.numMisdSexCrimesWTD1yChange > 0.0)
										risingCrimes.add("misdemeanor sex crimes");

									int randInt2 = 0 + (int) (Math.random() * ((templateStrings4.length - 1) + 1));
									String temp3 = templateStrings4[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp4 = "";
									for (String risingCrime : risingCrimes)
									{
										temp4 = temp4 + ", " + risingCrime;
									}
									String outString2 = temp3.replaceAll("<CRIME_TYPE>", temp4);
									// System.out.println(outString2);
									myOutputText += outString2;

									provideBoroughLevelStats(crimeReports, 8, cr.precinct);
								}

							}
						}
						break;

					// crime type: housing
					case 9:
						// if we want to highlight a falling crime rate
						if (newsStoryTrend == 1)
						{
							// if it's a valid double numerical value
							if (!Double.isNaN(cr.numHousingWTD1yChange))
							{
								if (cr.numHousingWTD1yChange < 0.0)
								{
									//select a random sentence from amongst similar sentences to report this
									int randInt = 0 + (int) (Math.random() * ((templateStrings1.length - 1) + 1));
									//// System.out.println(randInt);

									String temp1 = templateStrings1[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp2 = temp1.replaceAll("<PERCENTAGE>", Math.abs(cr.numHousingWTD1yChange) + "");
									String outString = temp2.replaceAll("<CRIME_TYPE>", "housing crimes ");
									// System.out.print(templateStrings0[0]);
									myOutputText += templateStrings0[0];
									// System.out.println(outString);
									myOutputText += outString;

									//select a random sentence from amongst similar sentence to report on other similar trending crimes
									ArrayList<String> fallingCrimes = new ArrayList<String>();
									if (cr.numMurdersWTD1yChange < 0.0)
										fallingCrimes.add("murders");
									if (cr.numRapesWTD1yChange < 0.0)
										fallingCrimes.add("rapes");
									if (cr.numRobberiesWTD1yChange < 0.0)
										fallingCrimes.add("robberies");
									if (cr.numFelAssaultsWTD1yChange < 0.0)
										fallingCrimes.add("felony assaults");
									if (cr.numBurglariesWTD1yChange < 0.0)
										fallingCrimes.add("burglaries");
									if (cr.numGrandLarceniesWTD1yChange < 0.0)
										fallingCrimes.add("grand larcenies");
									if (cr.numGLAWTD1yChange < 0.0)
										fallingCrimes.add("automobile grand larcenies");
									if (cr.numTransitWTD1yChange < 0.0)
										fallingCrimes.add("transit crimes");
									//if (cr.numHousingWTD1yChange < 0.0)
									//fallingCrimes.add("housing crimes");
									if (cr.numPetitLarceniesWTD1yChange < 0.0)
										fallingCrimes.add("petit larcenies");
									if (cr.numMisdAssaultsWTD1yChange < 0.0)
										fallingCrimes.add("misdemeanor assaults");
									if (cr.numMisdSexCrimesWTD1yChange < 0.0)
										fallingCrimes.add("misdemeanor sex crimes");

									int randInt2 = 0 + (int) (Math.random() * ((templateStrings3.length - 1) + 1));
									String temp3 = templateStrings3[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp4 = "";
									for (String fallingCrime : fallingCrimes)
									{
										temp4 = temp4 + ", " + fallingCrime;
									}
									String outString2 = temp3.replaceAll("<CRIME_TYPE>", temp4);
									// System.out.println(outString2);
									myOutputText += outString2;

									provideBoroughLevelStats(crimeReports, 9, cr.precinct);
								}

							}
						}
						// if we want to highlight a rising crime rate
						else if (newsStoryTrend == 2)
						{
							// if it's a valid double numerical value
							if (!Double.isNaN(cr.numHousingWTD1yChange))
							{
								if (cr.numHousingWTD1yChange > 0.0)
								{
									//select a random sentence from amongst similar sentences to report this
									int randInt = 0 + (int) (Math.random() * ((templateStrings1.length - 1) + 1));
									//// System.out.println(randInt);

									String temp1 = templateStrings2[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp2 = temp1.replaceAll("<PERCENTAGE>", Math.abs(cr.numHousingWTD1yChange) + "");
									String outString = temp2.replaceAll("<CRIME_TYPE>", "housing crimes ");
									// System.out.print(templateStrings0[1]);
									myOutputText += templateStrings0[1];
									// System.out.println(outString);
									myOutputText += outString;

									//select a random sentence from amongst similar sentence to report on other similar trending crimes
									ArrayList<String> risingCrimes = new ArrayList<String>();
									if (cr.numMurdersWTD1yChange > 0.0)
										risingCrimes.add("murders");
									if (cr.numRapesWTD1yChange > 0.0)
										risingCrimes.add("rapes");
									if (cr.numRobberiesWTD1yChange > 0.0)
										risingCrimes.add("robberies");
									if (cr.numFelAssaultsWTD1yChange > 0.0)
										risingCrimes.add("felony assaults");
									if (cr.numBurglariesWTD1yChange > 0.0)
										risingCrimes.add("burglaries");
									if (cr.numGrandLarceniesWTD1yChange > 0.0)
										risingCrimes.add("grand larcenies");
									if (cr.numGLAWTD1yChange > 0.0)
										risingCrimes.add("automobile grand larcenies");
									if (cr.numTransitWTD1yChange > 0.0)
										risingCrimes.add("transit crimes");
									//if (cr.numHousingWTD1yChange > 0.0)
									//risingCrimes.add("housing crimes");
									if (cr.numPetitLarceniesWTD1yChange > 0.0)
										risingCrimes.add("petit larcenies");
									if (cr.numMisdAssaultsWTD1yChange > 0.0)
										risingCrimes.add("misdemeanor assaults");
									if (cr.numMisdSexCrimesWTD1yChange > 0.0)
										risingCrimes.add("misdemeanor sex crimes");

									int randInt2 = 0 + (int) (Math.random() * ((templateStrings4.length - 1) + 1));
									String temp3 = templateStrings4[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp4 = "";
									for (String risingCrime : risingCrimes)
									{
										temp4 = temp4 + ", " + risingCrime;
									}
									String outString2 = temp3.replaceAll("<CRIME_TYPE>", temp4);
									// System.out.println(outString2);
									myOutputText += outString2;

									provideBoroughLevelStats(crimeReports, 9, cr.precinct);

								}

							}
						}
						break;

					// crime type: petit larceny
					case 10:
						// if we want to highlight a falling crime rate
						if (newsStoryTrend == 1)
						{
							// if it's a valid double numerical value
							if (!Double.isNaN(cr.numPetitLarceniesWTD1yChange))
							{
								if (cr.numPetitLarceniesWTD1yChange < 0.0)
								{
									//select a random sentence from amongst similar sentences to report this
									int randInt = 0 + (int) (Math.random() * ((templateStrings1.length - 1) + 1));
									//// System.out.println(randInt);

									String temp1 = templateStrings1[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp2 = temp1.replaceAll("<PERCENTAGE>", Math.abs(cr.numPetitLarceniesWTD1yChange) + "");
									String outString = temp2.replaceAll("<CRIME_TYPE>", "petit larcenies ");
									// System.out.print(templateStrings0[0]);
									myOutputText += templateStrings0[0];
									// System.out.println(outString);
									myOutputText += outString;

									//select a random sentence from amongst similar sentence to report on other similar trending crimes
									ArrayList<String> fallingCrimes = new ArrayList<String>();
									if (cr.numMurdersWTD1yChange < 0.0)
										fallingCrimes.add("murders");
									if (cr.numRapesWTD1yChange < 0.0)
										fallingCrimes.add("rapes");
									if (cr.numRobberiesWTD1yChange < 0.0)
										fallingCrimes.add("robberies");
									if (cr.numFelAssaultsWTD1yChange < 0.0)
										fallingCrimes.add("felony assaults");
									if (cr.numBurglariesWTD1yChange < 0.0)
										fallingCrimes.add("burglaries");
									if (cr.numGrandLarceniesWTD1yChange < 0.0)
										fallingCrimes.add("grand larcenies");
									if (cr.numGLAWTD1yChange < 0.0)
										fallingCrimes.add("automobile grand larcenies");
									if (cr.numTransitWTD1yChange < 0.0)
										fallingCrimes.add("transit crimes");
									if (cr.numHousingWTD1yChange < 0.0)
										fallingCrimes.add("housing crimes");
									//if (cr.numPetitLarceniesWTD1yChange < 0.0)
									//fallingCrimes.add("petit larcenies");
									if (cr.numMisdAssaultsWTD1yChange < 0.0)
										fallingCrimes.add("misdemeanor assaults");
									if (cr.numMisdSexCrimesWTD1yChange < 0.0)
										fallingCrimes.add("misdemeanor sex crimes");

									int randInt2 = 0 + (int) (Math.random() * ((templateStrings3.length - 1) + 1));
									String temp3 = templateStrings3[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp4 = "";
									for (String fallingCrime : fallingCrimes)
									{
										temp4 = temp4 + ", " + fallingCrime;
									}
									String outString2 = temp3.replaceAll("<CRIME_TYPE>", temp4);
									// System.out.println(outString2);
									myOutputText += outString2;

									provideBoroughLevelStats(crimeReports, 10, cr.precinct);

								}

							}
						}
						// if we want to highlight a rising crime rate
						else if (newsStoryTrend == 2)
						{
							// if it's a valid double numerical value
							if (!Double.isNaN(cr.numPetitLarceniesWTD1yChange))
							{
								if (cr.numPetitLarceniesWTD1yChange > 0.0)
								{
									//select a random sentence from amongst similar sentences to report this
									int randInt = 0 + (int) (Math.random() * ((templateStrings1.length - 1) + 1));
									//// System.out.println(randInt);

									String temp1 = templateStrings2[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp2 = temp1.replaceAll("<PERCENTAGE>", Math.abs(cr.numPetitLarceniesWTD1yChange) + "");
									String outString = temp2.replaceAll("<CRIME_TYPE>", "petit larcenies ");
									// System.out.print(templateStrings0[1]);
									myOutputText += templateStrings0[1];
									// System.out.println(outString);
									myOutputText += outString;

									//select a random sentence from amongst similar sentence to report on other similar trending crimes
									ArrayList<String> risingCrimes = new ArrayList<String>();
									if (cr.numMurdersWTD1yChange > 0.0)
										risingCrimes.add("murders");
									if (cr.numRapesWTD1yChange > 0.0)
										risingCrimes.add("rapes");
									if (cr.numRobberiesWTD1yChange > 0.0)
										risingCrimes.add("robberies");
									if (cr.numFelAssaultsWTD1yChange > 0.0)
										risingCrimes.add("felony assaults");
									if (cr.numBurglariesWTD1yChange > 0.0)
										risingCrimes.add("burglaries");
									if (cr.numGrandLarceniesWTD1yChange > 0.0)
										risingCrimes.add("grand larcenies");
									if (cr.numGLAWTD1yChange > 0.0)
										risingCrimes.add("automobile grand larcenies");
									if (cr.numTransitWTD1yChange > 0.0)
										risingCrimes.add("transit crimes");
									if (cr.numHousingWTD1yChange > 0.0)
										risingCrimes.add("housing crimes");
									//if (cr.numPetitLarceniesWTD1yChange > 0.0)
									//risingCrimes.add("petit larcenies");
									if (cr.numMisdAssaultsWTD1yChange > 0.0)
										risingCrimes.add("misdemeanor assaults");
									if (cr.numMisdSexCrimesWTD1yChange > 0.0)
										risingCrimes.add("misdemeanor sex crimes");

									int randInt2 = 0 + (int) (Math.random() * ((templateStrings4.length - 1) + 1));
									String temp3 = templateStrings4[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp4 = "";
									for (String risingCrime : risingCrimes)
									{
										temp4 = temp4 + ", " + risingCrime;
									}
									String outString2 = temp3.replaceAll("<CRIME_TYPE>", temp4);
									// System.out.println(outString2);
									myOutputText += outString2;

									provideBoroughLevelStats(crimeReports, 10, cr.precinct);
								}

							}
						}
						break;

					// crime type: misd. assault
					case 11:
						// if we want to highlight a falling crime rate
						if (newsStoryTrend == 1)
						{
							// if it's a valid double numerical value
							if (!Double.isNaN(cr.numMisdAssaultsWTD1yChange))
							{
								if (cr.numMisdAssaultsWTD1yChange < 0.0)
								{
									//select a random sentence from amongst similar sentences to report this
									int randInt = 0 + (int) (Math.random() * ((templateStrings1.length - 1) + 1));
									//// System.out.println(randInt);

									String temp1 = templateStrings1[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp2 = temp1.replaceAll("<PERCENTAGE>", Math.abs(cr.numMisdAssaultsWTD1yChange) + "");
									String outString = temp2.replaceAll("<CRIME_TYPE>", "misdemeanor assaults ");
									// System.out.print(templateStrings0[0]);
									myOutputText += templateStrings0[0];
									// System.out.println(outString);
									myOutputText += outString;

									//select a random sentence from amongst similar sentence to report on other similar trending crimes
									ArrayList<String> fallingCrimes = new ArrayList<String>();
									if (cr.numMurdersWTD1yChange < 0.0)
										fallingCrimes.add("murders");
									if (cr.numRapesWTD1yChange < 0.0)
										fallingCrimes.add("rapes");
									if (cr.numRobberiesWTD1yChange < 0.0)
										fallingCrimes.add("robberies");
									if (cr.numFelAssaultsWTD1yChange < 0.0)
										fallingCrimes.add("felony assaults");
									if (cr.numBurglariesWTD1yChange < 0.0)
										fallingCrimes.add("burglaries");
									if (cr.numGrandLarceniesWTD1yChange < 0.0)
										fallingCrimes.add("grand larcenies");
									if (cr.numGLAWTD1yChange < 0.0)
										fallingCrimes.add("automobile grand larcenies");
									if (cr.numTransitWTD1yChange < 0.0)
										fallingCrimes.add("transit crimes");
									if (cr.numHousingWTD1yChange < 0.0)
										fallingCrimes.add("housing crimes");
									if (cr.numPetitLarceniesWTD1yChange < 0.0)
										fallingCrimes.add("petit larcenies");
									//if (cr.numMisdAssaultsWTD1yChange < 0.0)
									//fallingCrimes.add("misdemeanor assaults");
									if (cr.numMisdSexCrimesWTD1yChange < 0.0)
										fallingCrimes.add("misdemeanor sex crimes");

									int randInt2 = 0 + (int) (Math.random() * ((templateStrings3.length - 1) + 1));
									String temp3 = templateStrings3[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp4 = "";
									for (String fallingCrime : fallingCrimes)
									{
										temp4 = temp4 + ", " + fallingCrime;
									}
									String outString2 = temp3.replaceAll("<CRIME_TYPE>", temp4);
									// System.out.println(outString2);
									myOutputText += outString2;

									provideBoroughLevelStats(crimeReports, 11, cr.precinct);
								}

							}
						}
						// if we want to highlight a rising crime rate
						else if (newsStoryTrend == 2)
						{
							// if it's a valid double numerical value
							if (!Double.isNaN(cr.numMisdAssaultsWTD1yChange))
							{
								if (cr.numMisdAssaultsWTD1yChange > 0.0)
								{
									//select a random sentence from amongst similar sentences to report this
									int randInt = 0 + (int) (Math.random() * ((templateStrings1.length - 1) + 1));
									//// System.out.println(randInt);

									String temp1 = templateStrings2[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp2 = temp1.replaceAll("<PERCENTAGE>", Math.abs(cr.numMisdAssaultsWTD1yChange) + "");
									String outString = temp2.replaceAll("<CRIME_TYPE>", "misdemeanor assaults ");
									// System.out.print(templateStrings0[1]);
									myOutputText += templateStrings0[1];
									// System.out.println(outString);
									myOutputText += outString;

									//select a random sentence from amongst similar sentence to report on other similar trending crimes
									ArrayList<String> risingCrimes = new ArrayList<String>();
									if (cr.numMurdersWTD1yChange > 0.0)
										risingCrimes.add("murders");
									if (cr.numRapesWTD1yChange > 0.0)
										risingCrimes.add("rapes");
									if (cr.numRobberiesWTD1yChange > 0.0)
										risingCrimes.add("robberies");
									if (cr.numFelAssaultsWTD1yChange > 0.0)
										risingCrimes.add("felony assaults");
									if (cr.numBurglariesWTD1yChange > 0.0)
										risingCrimes.add("burglaries");
									if (cr.numGrandLarceniesWTD1yChange > 0.0)
										risingCrimes.add("grand larcenies");
									if (cr.numGLAWTD1yChange > 0.0)
										risingCrimes.add("automobile grand larcenies");
									if (cr.numTransitWTD1yChange > 0.0)
										risingCrimes.add("transit crimes");
									if (cr.numHousingWTD1yChange > 0.0)
										risingCrimes.add("housing crimes");
									if (cr.numPetitLarceniesWTD1yChange > 0.0)
										risingCrimes.add("petit larcenies");
									//if (cr.numMisdAssaultsWTD1yChange > 0.0)
									//risingCrimes.add("misdemeanor assaults");
									if (cr.numMisdSexCrimesWTD1yChange > 0.0)
										risingCrimes.add("misdemeanor sex crimes");

									int randInt2 = 0 + (int) (Math.random() * ((templateStrings4.length - 1) + 1));
									String temp3 = templateStrings4[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp4 = "";
									for (String risingCrime : risingCrimes)
									{
										temp4 = temp4 + ", " + risingCrime;
									}
									String outString2 = temp3.replaceAll("<CRIME_TYPE>", temp4);
									// System.out.println(outString2);
									myOutputText += outString2;

									provideBoroughLevelStats(crimeReports, 11, cr.precinct);
								}

							}
						}
						break;

					// crime type: misd. sex crimes
					case 12:
						// if we want to highlight a falling crime rate
						if (newsStoryTrend == 1)
						{
							// if it's a valid double numerical value
							if (!Double.isNaN(cr.numMisdSexCrimesWTD1yChange))
							{
								if (cr.numMisdSexCrimesWTD1yChange < 0.0)
								{
									//select a random sentence from amongst similar sentences to report this
									int randInt = 0 + (int) (Math.random() * ((templateStrings1.length - 1) + 1));
									//// System.out.println(randInt);

									String temp1 = templateStrings1[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp2 = temp1.replaceAll("<PERCENTAGE>", Math.abs(cr.numMisdSexCrimesWTD1yChange) + "");
									String outString = temp2.replaceAll("<CRIME_TYPE>", "misdemeanor sex crimes ");
									// System.out.print(templateStrings0[0]);
									myOutputText += templateStrings0[0];
									// System.out.println(outString);
									myOutputText += outString;

									//select a random sentence from amongst similar sentence to report on other similar trending crimes
									ArrayList<String> fallingCrimes = new ArrayList<String>();
									if (cr.numMurdersWTD1yChange < 0.0)
										fallingCrimes.add("murders");
									if (cr.numRapesWTD1yChange < 0.0)
										fallingCrimes.add("rapes");
									if (cr.numRobberiesWTD1yChange < 0.0)
										fallingCrimes.add("robberies");
									if (cr.numFelAssaultsWTD1yChange < 0.0)
										fallingCrimes.add("felony assaults");
									if (cr.numBurglariesWTD1yChange < 0.0)
										fallingCrimes.add("burglaries");
									if (cr.numGrandLarceniesWTD1yChange < 0.0)
										fallingCrimes.add("grand larcenies");
									if (cr.numGLAWTD1yChange < 0.0)
										fallingCrimes.add("automobile grand larcenies");
									if (cr.numTransitWTD1yChange < 0.0)
										fallingCrimes.add("transit crimes");
									if (cr.numHousingWTD1yChange < 0.0)
										fallingCrimes.add("housing crimes");
									if (cr.numPetitLarceniesWTD1yChange < 0.0)
										fallingCrimes.add("petit larcenies");
									if (cr.numMisdAssaultsWTD1yChange < 0.0)
										fallingCrimes.add("misdemeanor assaults");
									//if (cr.numMisdSexCrimesWTD1yChange < 0.0)
									//fallingCrimes.add("misdemeanor sex crimes");

									int randInt2 = 0 + (int) (Math.random() * ((templateStrings3.length - 1) + 1));
									String temp3 = templateStrings3[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp4 = "";
									for (String fallingCrime : fallingCrimes)
									{
										temp4 = temp4 + ", " + fallingCrime;
									}
									String outString2 = temp3.replaceAll("<CRIME_TYPE>", temp4);
									// System.out.println(outString2);
									myOutputText += outString2;

									provideBoroughLevelStats(crimeReports, 12, cr.precinct);

								}

							}
						}
						// if we want to highlight a rising crime rate
						else if (newsStoryTrend == 2)
						{
							// if it's a valid double numerical value
							if (!Double.isNaN(cr.numMisdSexCrimesWTD1yChange))
							{
								if (cr.numMisdSexCrimesWTD1yChange > 0.0)
								{
									//select a random sentence from amongst similar sentences to report this
									int randInt = 0 + (int) (Math.random() * ((templateStrings1.length - 1) + 1));
									//// System.out.println(randInt);

									String temp1 = templateStrings2[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp2 = temp1.replaceAll("<PERCENTAGE>", Math.abs(cr.numMisdSexCrimesWTD1yChange) + "");
									String outString = temp2.replaceAll("<CRIME_TYPE>", "misdemeanor sex crimes ");
									// System.out.print(templateStrings0[1]);
									myOutputText += templateStrings0[1];
									// System.out.println(outString);
									myOutputText += outString;

									//select a random sentence from amongst similar sentence to report on other similar trending crimes
									ArrayList<String> risingCrimes = new ArrayList<String>();
									if (cr.numMurdersWTD1yChange > 0.0)
										risingCrimes.add("murders");
									if (cr.numRapesWTD1yChange > 0.0)
										risingCrimes.add("rapes");
									if (cr.numRobberiesWTD1yChange > 0.0)
										risingCrimes.add("robberies");
									if (cr.numFelAssaultsWTD1yChange > 0.0)
										risingCrimes.add("felony assaults");
									if (cr.numBurglariesWTD1yChange > 0.0)
										risingCrimes.add("burglaries");
									if (cr.numGrandLarceniesWTD1yChange > 0.0)
										risingCrimes.add("grand larcenies");
									if (cr.numGLAWTD1yChange > 0.0)
										risingCrimes.add("automobile grand larcenies");
									if (cr.numTransitWTD1yChange > 0.0)
										risingCrimes.add("transit crimes");
									if (cr.numHousingWTD1yChange > 0.0)
										risingCrimes.add("housing crimes");
									if (cr.numPetitLarceniesWTD1yChange > 0.0)
										risingCrimes.add("petit larcenies");
									if (cr.numMisdAssaultsWTD1yChange > 0.0)
										risingCrimes.add("misdemeanor assaults");
									//if (cr.numMisdSexCrimesWTD1yChange > 0.0)
									//risingCrimes.add("misdemeanor sex crimes");

									int randInt2 = 0 + (int) (Math.random() * ((templateStrings4.length - 1) + 1));
									String temp3 = templateStrings4[randInt].replaceAll("<PRECINCT_NUMBER>", cr.precinct + "");
									String temp4 = "";
									for (String risingCrime : risingCrimes)
									{
										temp4 = temp4 + ", " + risingCrime;
									}
									String outString2 = temp3.replaceAll("<CRIME_TYPE>", temp4);
									// System.out.println(outString2);
									myOutputText += outString2;

									provideBoroughLevelStats(crimeReports, 12, cr.precinct);
								}

							}
						}
						break;

				}
				/*
				if (cr.isPrecinctReport)
				{

				}
				else if (cr.isBoroughReport)
				{

				}
				else if (cr.isCityReport)
				{

				}*/
			}
		}

	}

	public static void provideBoroughLevelStats(ArrayList<CrimeReport> crimeReports, int crimeToFocusOn, int precinct)
	{
		for (CrimeReport cr : crimeReports)
		{
			if (cr.isBoroughReport)
			{
				int randInt3;
				String temp10, temp11, temp12, temp13, temp14;
				//do something
				switch (crimeToFocusOn)
				{
					case 1:
						randInt3 = 0 + (int) (Math.random() * ((templateStrings5.length - 1) + 1));
						temp10 = templateStrings5[randInt3].replaceAll("<PRECINCT_NUMBER>", precinct + "");
						temp11 = temp10.replaceAll("<BOROUGH>", cr.borough);
						temp12 = temp11.replaceAll("<CRIME_TYPE>", "murders");
						temp13 = temp12.replaceAll("<PERCENTAGE>", Math.abs(cr.numMurdersWTD1yChange) + "");
						if (cr.numMurdersWTD1yChange < 0)
							temp14 = temp13.replaceAll("<CHANGE>", "fall");
						else if (cr.numMurdersWTD1yChange > 0)
							temp14 = temp13.replaceAll("<CHANGE>", "rise");
						else
							temp14 = temp13.replaceAll("<CHANGE>", "change");
						// System.out.println(temp14);
						myOutputText += temp14;

						break;

					case 2:
						randInt3 = 0 + (int) (Math.random() * ((templateStrings5.length - 1) + 1));
						temp10 = templateStrings5[randInt3].replaceAll("<PRECINCT_NUMBER>", precinct + "");
						temp11 = temp10.replaceAll("<BOROUGH>", cr.borough);
						temp12 = temp11.replaceAll("<CRIME_TYPE>", "rapes");
						temp13 = temp12.replaceAll("<PERCENTAGE>", Math.abs(cr.numRapesWTD1yChange) + "");
						if (cr.numRapesWTD1yChange < 0)
							temp14 = temp13.replaceAll("<CHANGE>", "fall");
						else if (cr.numRapesWTD1yChange > 0)
							temp14 = temp13.replaceAll("<CHANGE>", "rise");
						else
							temp14 = temp13.replaceAll("<CHANGE>", "change");
						// System.out.println(temp14);
						myOutputText += temp14;
						break;

					case 3:
						randInt3 = 0 + (int) (Math.random() * ((templateStrings5.length - 1) + 1));
						temp10 = templateStrings5[randInt3].replaceAll("<PRECINCT_NUMBER>", precinct + "");
						temp11 = temp10.replaceAll("<BOROUGH>", cr.borough);
						temp12 = temp11.replaceAll("<CRIME_TYPE>", "robberies");
						temp13 = temp12.replaceAll("<PERCENTAGE>", Math.abs(cr.numRobberiesWTD1yChange) + "");
						if (cr.numRobberiesWTD1yChange < 0)
							temp14 = temp13.replaceAll("<CHANGE>", "fall");
						else if (cr.numRobberiesWTD1yChange > 0)
							temp14 = temp13.replaceAll("<CHANGE>", "rise");
						else
							temp14 = temp13.replaceAll("<CHANGE>", "change");
						// System.out.println(temp14);
						myOutputText += temp14;
						break;

					case 4:
						randInt3 = 0 + (int) (Math.random() * ((templateStrings5.length - 1) + 1));
						temp10 = templateStrings5[randInt3].replaceAll("<PRECINCT_NUMBER>", precinct + "");
						temp11 = temp10.replaceAll("<BOROUGH>", cr.borough);
						temp12 = temp11.replaceAll("<CRIME_TYPE>", "felony assaults");
						temp13 = temp12.replaceAll("<PERCENTAGE>", Math.abs(cr.numFelAssaultsWTD1yChange) + "");
						if (cr.numFelAssaultsWTD1yChange < 0)
							temp14 = temp13.replaceAll("<CHANGE>", "fall");
						else if (cr.numFelAssaultsWTD1yChange > 0)
							temp14 = temp13.replaceAll("<CHANGE>", "rise");
						else
							temp14 = temp13.replaceAll("<CHANGE>", "change");
						// System.out.println(temp14);
						myOutputText += temp14;
						break;

					case 5:
						randInt3 = 0 + (int) (Math.random() * ((templateStrings5.length - 1) + 1));
						temp10 = templateStrings5[randInt3].replaceAll("<PRECINCT_NUMBER>", precinct + "");
						temp11 = temp10.replaceAll("<BOROUGH>", cr.borough);
						temp12 = temp11.replaceAll("<CRIME_TYPE>", "burglaries");
						temp13 = temp12.replaceAll("<PERCENTAGE>", Math.abs(cr.numBurglariesWTD1yChange) + "");
						if (cr.numBurglariesWTD1yChange < 0)
							temp14 = temp13.replaceAll("<CHANGE>", "fall");
						else if (cr.numBurglariesWTD1yChange > 0)
							temp14 = temp13.replaceAll("<CHANGE>", "rise");
						else
							temp14 = temp13.replaceAll("<CHANGE>", "change");
						// System.out.println(temp14);
						myOutputText += temp14;
						break;

					case 6:
						randInt3 = 0 + (int) (Math.random() * ((templateStrings5.length - 1) + 1));
						temp10 = templateStrings5[randInt3].replaceAll("<PRECINCT_NUMBER>", precinct + "");
						temp11 = temp10.replaceAll("<BOROUGH>", cr.borough);
						temp12 = temp11.replaceAll("<CRIME_TYPE>", "grand larcenies");
						temp13 = temp12.replaceAll("<PERCENTAGE>", Math.abs(cr.numGrandLarceniesWTD1yChange) + "");
						if (cr.numGrandLarceniesWTD1yChange < 0)
							temp14 = temp13.replaceAll("<CHANGE>", "fall");
						else if (cr.numGrandLarceniesWTD1yChange > 0)
							temp14 = temp13.replaceAll("<CHANGE>", "rise");
						else
							temp14 = temp13.replaceAll("<CHANGE>", "change");
						// System.out.println(temp14);
						myOutputText += temp14;
						break;

					case 7:
						randInt3 = 0 + (int) (Math.random() * ((templateStrings5.length - 1) + 1));
						temp10 = templateStrings5[randInt3].replaceAll("<PRECINCT_NUMBER>", precinct + "");
						temp11 = temp10.replaceAll("<BOROUGH>", cr.borough);
						temp12 = temp11.replaceAll("<CRIME_TYPE>", "automobile grand larcenies");
						temp13 = temp12.replaceAll("<PERCENTAGE>", Math.abs(cr.numGLAWTD1yChange) + "");
						if (cr.numGLAWTD1yChange < 0)
							temp14 = temp13.replaceAll("<CHANGE>", "fall");
						else if (cr.numGLAWTD1yChange > 0)
							temp14 = temp13.replaceAll("<CHANGE>", "rise");
						else
							temp14 = temp13.replaceAll("<CHANGE>", "change");
						// System.out.println(temp14);
						myOutputText += temp14;
						break;

					case 8:
						randInt3 = 0 + (int) (Math.random() * ((templateStrings5.length - 1) + 1));
						temp10 = templateStrings5[randInt3].replaceAll("<PRECINCT_NUMBER>", precinct + "");
						temp11 = temp10.replaceAll("<BOROUGH>", cr.borough);
						temp12 = temp11.replaceAll("<CRIME_TYPE>", "transit crimes");
						temp13 = temp12.replaceAll("<PERCENTAGE>", Math.abs(cr.numTransitWTD1yChange) + "");
						if (cr.numTransitWTD1yChange < 0)
							temp14 = temp13.replaceAll("<CHANGE>", "fall");
						else if (cr.numTransitWTD1yChange > 0)
							temp14 = temp13.replaceAll("<CHANGE>", "rise");
						else
							temp14 = temp13.replaceAll("<CHANGE>", "change");
						// System.out.println(temp14);
						myOutputText += temp14;
						break;

					case 9:
						randInt3 = 0 + (int) (Math.random() * ((templateStrings5.length - 1) + 1));
						temp10 = templateStrings5[randInt3].replaceAll("<PRECINCT_NUMBER>", precinct + "");
						temp11 = temp10.replaceAll("<BOROUGH>", cr.borough);
						temp12 = temp11.replaceAll("<CRIME_TYPE>", "housing crimes");
						temp13 = temp12.replaceAll("<PERCENTAGE>", Math.abs(cr.numHousingWTD1yChange) + "");
						if (cr.numHousingWTD1yChange < 0)
							temp14 = temp13.replaceAll("<CHANGE>", "fall");
						else if (cr.numHousingWTD1yChange > 0)
							temp14 = temp13.replaceAll("<CHANGE>", "rise");
						else
							temp14 = temp13.replaceAll("<CHANGE>", "change");
						// System.out.println(temp14);
						myOutputText += temp14;
						break;

					case 10:
						randInt3 = 0 + (int) (Math.random() * ((templateStrings5.length - 1) + 1));
						temp10 = templateStrings5[randInt3].replaceAll("<PRECINCT_NUMBER>", precinct + "");
						temp11 = temp10.replaceAll("<BOROUGH>", cr.borough);
						temp12 = temp11.replaceAll("<CRIME_TYPE>", "petit larcenies");
						temp13 = temp12.replaceAll("<PERCENTAGE>", Math.abs(cr.numPetitLarceniesWTD1yChange) + "");
						if (cr.numPetitLarceniesWTD1yChange < 0)
							temp14 = temp13.replaceAll("<CHANGE>", "fall");
						else if (cr.numPetitLarceniesWTD1yChange > 0)
							temp14 = temp13.replaceAll("<CHANGE>", "rise");
						else
							temp14 = temp13.replaceAll("<CHANGE>", "change");
						// System.out.println(temp14);
						myOutputText += temp14;
						break;

					case 11:
						randInt3 = 0 + (int) (Math.random() * ((templateStrings5.length - 1) + 1));
						temp10 = templateStrings5[randInt3].replaceAll("<PRECINCT_NUMBER>", precinct + "");
						temp11 = temp10.replaceAll("<BOROUGH>", cr.borough);
						temp12 = temp11.replaceAll("<CRIME_TYPE>", "misdemeanor assaults");
						temp13 = temp12.replaceAll("<PERCENTAGE>", Math.abs(cr.numMisdAssaultsWTD1yChange) + "");
						if (cr.numMisdAssaultsWTD1yChange < 0)
							temp14 = temp13.replaceAll("<CHANGE>", "fall");
						else if (cr.numMisdAssaultsWTD1yChange > 0)
							temp14 = temp13.replaceAll("<CHANGE>", "rise");
						else
							temp14 = temp13.replaceAll("<CHANGE>", "change");
						// System.out.println(temp14);
						myOutputText += temp14;
						break;

					case 12:
						randInt3 = 0 + (int) (Math.random() * ((templateStrings5.length - 1) + 1));
						temp10 = templateStrings5[randInt3].replaceAll("<PRECINCT_NUMBER>", precinct + "");
						temp11 = temp10.replaceAll("<BOROUGH>", cr.borough);
						temp12 = temp11.replaceAll("<CRIME_TYPE>", "misdemeanor sex crimes");
						temp13 = temp12.replaceAll("<PERCENTAGE>", Math.abs(cr.numMisdSexCrimesWTD1yChange) + "");
						if (cr.numMisdSexCrimesWTD1yChange < 0)
							temp14 = temp13.replaceAll("<CHANGE>", "fall");
						else if (cr.numMisdSexCrimesWTD1yChange > 0)
							temp14 = temp13.replaceAll("<CHANGE>", "rise");
						else
							temp14 = temp13.replaceAll("<CHANGE>", "change");
						// System.out.println(temp14);
						myOutputText += temp14;
						break;
				}

			}

		}
	}

	//Fix capitalization, redundant commas and similar formatting issues before writing news article to file
	public static String postProcessOutput(String outputText)
	{
		String str1 = outputText.replaceAll("\\.\\s*,\\s+", "\\. ");

		//from StackOverflow.com
		int pos = 0;
		boolean capitalize = true;
		StringBuilder sb = new StringBuilder(str1);
		while (pos < sb.length())
		{
			if (sb.charAt(pos) == '.')
			{
				capitalize = true;
			}
			else if (capitalize && !Character.isWhitespace(sb.charAt(pos)))
			{
				sb.setCharAt(pos, Character.toUpperCase(sb.charAt(pos)));
				capitalize = false;
			}
			pos++;
		}

		return sb.toString();

	}

	//removes the quotes and thousand's comma from integer strings obtained from the CSV
	public static String cleanInt(String str)
	{
		String str2 = str.replaceAll(",", "");
		String output = str2.replaceAll("\"", "");
		return output;
	}

	public static void main(String[] args)
	{

		/*
		 * I've adapted the following code on how to read a file in Java line by
		 * line from http://www.roseindia.net/java/beginners/java-read
		 * -file-line-by-line.shtml
		 */

		BufferedReader br;
		DataInputStream dis;
		FileInputStream fis;
		CrimeReport cr;

		// pattern to substitute an entry like "2,100" with 2100 in the CSV
		Pattern p0 = Pattern.compile("\"(\\d+)(,(\\d\\d\\d))+\"");
		Matcher m0 = p0.matcher("");

		Pattern p100 = Pattern.compile("(\\d+)(st|nd|rd|th)\\s+Precinct");
		Matcher m100 = p100.matcher("");

		Pattern p200 = Pattern.compile("(Brooklyn|Manhattan|Queens)\\s+(North|South)");
		Matcher m200 = p200.matcher("");

		Pattern p300 = Pattern.compile("Staten\\s+Island");
		Matcher m300 = p300.matcher("");

		Pattern p400 = Pattern.compile("Bronx");
		Matcher m400 = p400.matcher("");

		Pattern p1 = Pattern.compile("Report\\s+Covering\\s+the\\s+Week\\s+(\\d+)\\/(\\d+)\\/(\\d+)\\s+Through\\s+(\\d+)\\/(\\d+)\\/(\\d+)");
		Matcher m1 = p1.matcher("");

		Pattern p2 = Pattern.compile("Murder,(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+)");
		Matcher m2 = p2.matcher("");

		Pattern p3 = Pattern.compile("Rape,(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+)");
		Matcher m3 = p3.matcher("");

		Pattern p4 = Pattern.compile("Robbery,(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+)");
		Matcher m4 = p4.matcher("");

		Pattern p5 = Pattern.compile("Fel\\.\\s+Assault,(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+)");
		Matcher m5 = p5.matcher("");

		Pattern p6 = Pattern.compile("Burglary,(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+)");
		Matcher m6 = p6.matcher("");

		Pattern p7 = Pattern.compile("Gr\\.\\s+Larceny,(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+)");
		Matcher m7 = p7.matcher("");

		Pattern p8 = Pattern.compile("G\\.L\\.A\\.,(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+)");
		Matcher m8 = p8.matcher("");

		Pattern p9 = Pattern.compile("Transit,(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+)");
		Matcher m9 = p9.matcher("");

		Pattern p10 = Pattern.compile("Housing,(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+)");
		Matcher m10 = p10.matcher("");

		Pattern p11 = Pattern.compile("Petit\\s+Larceny,(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+)");
		Matcher m11 = p11.matcher("");

		Pattern p12 = Pattern.compile("Misd\\.\\s+Assault,(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+)");
		Matcher m12 = p12.matcher("");

		Pattern p13 = Pattern.compile("Misd\\.\\s+Sex\\s+Crimes,(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+),(\\S+)");
		Matcher m13 = p13.matcher("");

		// instantiate an array list to hold all the crime reports
		ArrayList<CrimeReport> crimeReports = new ArrayList<CrimeReport>();

		// iterate through all the files provided as command line arguments
		for (int index = 0; index < args.length; index++)
		{

			boolean isPrecinctReport = false;
			boolean isBoroughReport = false;
			boolean isCityReport = false;

			/*
			 * precinct set to -1 for borough-level and citywide report
			 * otherwise set to actual precinct
			 */
			int precinct = 0;

			/*
			 * For Queens and Brooklyn, we consider the north and south
			 * boroughs separately
			 * 
			 * borough is null for citywide report but has a valid value for
			 * the borough-level and precinct level reports
			 */
			String borough = null;

			Calendar startCal = null;
			Calendar endCal = null;

			// week to date stats for current year
			int numMurdersWTD = 0;
			int numRapesWTD = 0;
			int numRobberiesWTD = 0;
			int numFelAssaultsWTD = 0;
			int numBurglariesWTD = 0;
			int numGrandLarceniesWTD = 0;
			int numGLAWTD = 0;
			int numTransitWTD = 0;
			int numHousingWTD = 0;
			int numPetitLarceniesWTD = 0;
			int numMisdAssaultsWTD = 0;
			int numMisdSexCrimesWTD = 0;
			// int numShootingVicWTD = 0;
			// int numShootingIncWTD = 0;

			// week to date stats for previous year
			int numMurdersWTDPrevYear = 0;
			int numRapesWTDPrevYear = 0;
			int numRobberiesWTDPrevYear = 0;
			int numFelAssaultsWTDPrevYear = 0;
			int numBurglariesWTDPrevYear = 0;
			int numGrandLarceniesWTDPrevYear = 0;
			int numGLAWTDPrevYear = 0;
			int numTransitWTDPrevYear = 0;
			int numHousingWTDPrevYear = 0;
			int numPetitLarceniesWTDPrevYear = 0;
			int numMisdAssaultsWTDPrevYear = 0;
			int numMisdSexCrimesWTDPrevYear = 0;
			// int numShootingVicWTDPrevYear = 0;
			// int numShootingIncWTDPrevYear = 0;

			// 28 days stats for current year
			int numMurders28d = 0;
			int numRapes28d = 0;
			int numRobberies28d = 0;
			int numFelAssaults28d = 0;
			int numBurglaries28d = 0;
			int numGrandLarcenies28d = 0;
			int numGLA28d = 0;
			int numTransit28d = 0;
			int numHousing28d = 0;
			int numPetitLarcenies28d = 0;
			int numMisdAssaults28d = 0;
			int numMisdSexCrimes28d = 0;
			// int numShootingVic28d = 0;
			// int numShootingInc28d = 0;

			// 28 day stats for previous year
			int numMurders28dPrevYear = 0;
			int numRapes28dPrevYear = 0;
			int numRobberies28dPrevYear = 0;
			int numFelAssaults28dPrevYear = 0;
			int numBurglaries28dPrevYear = 0;
			int numGrandLarcenies28dPrevYear = 0;
			int numGLA28dPrevYear = 0;
			int numTransit28dPrevYear = 0;
			int numHousing28dPrevYear = 0;
			int numPetitLarcenies28dPrevYear = 0;
			int numMisdAssaults28dPrevYear = 0;
			int numMisdSexCrimes28dPrevYear = 0;
			// int numShootingVic28dPrevYear = 0;
			// int numShootingInc28dPrevYear = 0;

			// year to date stats for current year
			int numMurdersYTD = 0;
			int numRapesYTD = 0;
			int numRobberiesYTD = 0;
			int numFelAssaultsYTD = 0;
			int numBurglariesYTD = 0;
			int numGrandLarceniesYTD = 0;
			int numGLAYTD = 0;
			int numTransitYTD = 0;
			int numHousingYTD = 0;
			int numPetitLarceniesYTD = 0;
			int numMisdAssaultsYTD = 0;
			int numMisdSexCrimesYTD = 0;
			// int numShootingVicYTD = 0;
			// int numShootingIncYTD = 0;

			// year to date stats for previous year
			int numMurdersYTDPrevYear = 0;
			int numRapesYTDPrevYear = 0;
			int numRobberiesYTDPrevYear = 0;
			int numFelAssaultsYTDPrevYear = 0;
			int numBurglariesYTDPrevYear = 0;
			int numGrandLarceniesYTDPrevYear = 0;
			int numGLAYTDPrevYear = 0;
			int numTransitYTDPrevYear = 0;
			int numHousingYTDPrevYear = 0;
			int numPetitLarceniesYTDPrevYear = 0;
			int numMisdAssaultsYTDPrevYear = 0;
			int numMisdSexCrimesYTDPrevYear = 0;
			// int numShootingVicYTDPrevYear = 0;
			// int numShootingIncYTDPrevYear = 0;

			// murder percentage changes
			double numMurdersWTD1yChange = 0.0;
			double numMurders28d1yChange = 0.0;
			double numMurders1yChange = 0.0;
			double numMurders2yChange = 0.0;
			double numMurders5yChange = 0.0;
			double numMurders21yChange = 0.0;

			// rape percentage changes
			double numRapesWTD1yChange = 0.0;
			double numRapes28d1yChange = 0.0;
			double numRapes1yChange = 0.0;
			double numRapes2yChange = 0.0;
			double numRapes5yChange = 0.0;
			double numRapes21yChange = 0.0;

			// robberies percentage changes
			double numRobberiesWTD1yChange = 0.0;
			double numRobberies28d1yChange = 0.0;
			double numRobberies1yChange = 0.0;
			double numRobberies2yChange = 0.0;
			double numRobberies5yChange = 0.0;
			double numRobberies21yChange = 0.0;

			// felony assault percentage changes
			double numFelAssaultsWTD1yChange = 0.0;
			double numFelAssaults28d1yChange = 0.0;
			double numFelAssaults1yChange = 0.0;
			double numFelAssaults2yChange = 0.0;
			double numFelAssaults5yChange = 0.0;
			double numFelAssaults21yChange = 0.0;

			// 2-year, 5-year and 21-year percentage changes
			double numBurglariesWTD1yChange = 0.0;
			double numBurglaries28d1yChange = 0.0;
			double numBurglaries1yChange = 0.0;
			double numBurglaries2yChange = 0.0;
			double numBurglaries5yChange = 0.0;
			double numBurglaries21yChange = 0.0;

			// Gr Larceny percentage changes
			double numGrandLarceniesWTD1yChange = 0.0;
			double numGrandLarcenies28d1yChange = 0.0;
			double numGrandLarcenies1yChange = 0.0;
			double numGrandLarcenies2yChange = 0.0;
			double numGrandLarcenies5yChange = 0.0;
			double numGrandLarcenies21yChange = 0.0;

			// Auto Gr Larceny percentage changes
			double numGLAWTD1yChange = 0.0;
			double numGLA28d1yChange = 0.0;
			double numGLA1yChange = 0.0;
			double numGLA2yChange = 0.0;
			double numGLA5yChange = 0.0;
			double numGLA21yChange = 0.0;

			// Transit percentage changes
			double numTransitWTD1yChange = 0.0;
			double numTransit28d1yChange = 0.0;
			double numTransit1yChange = 0.0;
			double numTransit2yChange = 0.0;
			double numTransit5yChange = 0.0;
			double numTransit21yChange = 0.0;

			// 2-year, 5-year and 21-year percentage changes
			double numHousingWTD1yChange = 0.0;
			double numHousing28d1yChange = 0.0;
			double numHousing1yChange = 0.0;
			double numHousing2yChange = 0.0;
			double numHousing5yChange = 0.0;
			double numHousing21yChange = 0.0;

			// 2-year, 5-year and 21-year percentage changes
			double numPetitLarceniesWTD1yChange = 0.0;
			double numPetitLarcenies28d1yChange = 0.0;
			double numPetitLarcenies1yChange = 0.0;
			double numPetitLarcenies2yChange = 0.0;
			double numPetitLarcenies5yChange = 0.0;
			double numPetitLarcenies21yChange = 0.0;

			// 2-year, 5-year and 21-year percentage changes
			double numMisdAssaultsWTD1yChange = 0.0;
			double numMisdAssaults28d1yChange = 0.0;
			double numMisdAssaults1yChange = 0.0;
			double numMisdAssaults2yChange = 0.0;
			double numMisdAssaults5yChange = 0.0;
			double numMisdAssaults21yChange = 0.0;

			// 2-year, 5-year and 21-year percentage changes
			double numMisdSexCrimesWTD1yChange = 0.0;
			double numMisdSexCrimes28d1yChange = 0.0;
			double numMisdSexCrimes1yChange = 0.0;
			double numMisdSexCrimes2yChange = 0.0;
			double numMisdSexCrimes5yChange = 0.0;
			double numMisdSexCrimes21yChange = 0.0;

			try
			{
				fis = new FileInputStream(args[index]);
				dis = new DataInputStream(fis);
				br = new BufferedReader(new InputStreamReader(dis));

				String strLine;

				// Read the source file line by line
				// while ((strLine = br.readLine()) != null)

				// we are not going to consider older historical perspective
				// data in this project
				while ((strLine = br.readLine()).matches("^.*Historical perspective.*$") == false)
				//&& strLine != null

				{

					//find the precinct and borough
					m100.reset(strLine);
					if (m100.find())
					{
						isPrecinctReport = true;
						isBoroughReport = false;
						if (isInteger(m100.group(1)))
							precinct = Integer.parseInt(m100.group(1));
						//System.out.println(precinct);

						// determine the borough from the precinct
						if (precinct >= 40 && precinct <= 52)
						{
							borough = "Bronx";
						}
						else if (precinct >= 73 && precinct <= 94)
						{
							borough = "North Brooklyn";
						}
						else if (precinct >= 60 && precinct <= 78)
						{
							borough = "South Brooklyn";
						}
						else if (precinct >= 19 && precinct <= 34)
						{
							borough = "North Manhattan";
						}
						else if (precinct >= 104 && precinct <= 115)
						{
							borough = "North Queens";
						}
						else if (precinct >= 100 && precinct <= 113)
						{
							borough = "South Queens";
						}
						else if (precinct >= 120 && precinct <= 123)
						{
							borough = "Staten Island";
						}
					}
					//otherwise this may be a borough-level report
					else
					{

						m200.reset(strLine);
						m300.reset(strLine);
						m400.reset(strLine);

						if (m200.find())
						{
							isPrecinctReport = false;
							isBoroughReport = true;
							borough = m200.group(1) + " " + m200.group(2);
						}
						else if (m300.find())
						{
							isPrecinctReport = false;
							isBoroughReport = true;
							borough = m300.group();
						}
						else if (m400.find())
						{
							isPrecinctReport = false;
							isBoroughReport = true;
							borough = m400.group();
						}

					}

					m1.reset(strLine); // reset the input
					if (m1.find()) // if the date matcher finds the pattern
									// p1
					{

						int startMonth = Integer.parseInt(m1.group(1)) - 1;
						int startDate = Integer.parseInt(m1.group(2));
						int startYear = Integer.parseInt(m1.group(3));

						int endMonth = Integer.parseInt(m1.group(4)) - 1;
						int endDate = Integer.parseInt(m1.group(5));
						int endYear = Integer.parseInt(m1.group(6));

						startCal = new GregorianCalendar(startYear, startMonth, startDate);

						endCal = new GregorianCalendar(endYear, endMonth, endDate);

						SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");

					}

					// extract murder stats
					m2.reset(strLine);
					if (m2.find())
					{
						numMurdersWTD = Integer.parseInt(m2.group(1));
						numMurdersWTDPrevYear = Integer.parseInt(m2.group(2));
						if (isFloat(m2.group(3)))
							numMurdersWTD1yChange = Double.parseDouble(m2.group(3));
						else
							numMurdersWTD1yChange = Double.NaN;

						numMurders28d = Integer.parseInt(m2.group(4));
						numMurders28dPrevYear = Integer.parseInt(m2.group(5));
						if (isFloat(m2.group(6)))
							numMurders28d1yChange = Double.parseDouble(m2.group(6));
						else
							numMurders28d1yChange = Double.NaN;

						numMurdersYTD = Integer.parseInt(m2.group(7));
						numMurdersYTDPrevYear = Integer.parseInt(m2.group(8));
						if (isFloat(m2.group(9)))
							numMurders1yChange = Double.parseDouble(m2.group(9));
						else
							numMurders1yChange = Double.NaN;

						if (isFloat(m2.group(10)))
							numMurders2yChange = Double.parseDouble(m2.group(10));
						else
							numMurders2yChange = Double.NaN;

						if (isFloat(m2.group(11)))
							numMurders5yChange = Double.parseDouble(m2.group(11));
						else
							numMurders5yChange = Double.NaN;

						if (isFloat(m2.group(12)))
							numMurders21yChange = Double.parseDouble(m2.group(12));
						else
							numMurders21yChange = Double.NaN;
						/*
						System.out.println(numMurdersWTD1yChange + "," + numMurders28d1yChange + "," + numMurders1yChange + "," + numMurders2yChange + ","
								+ numMurders5yChange + "," + numMurders21yChange);
						*/
					}

					// extract rape stats
					m3.reset(strLine);
					if (m3.find())
					{
						numRapesWTD = Integer.parseInt(m3.group(1));
						numRapesWTDPrevYear = Integer.parseInt(m3.group(2));
						if (isFloat(m3.group(3)))
							numRapesWTD1yChange = Double.parseDouble(m3.group(3));
						else
							numRapesWTD1yChange = Double.NaN;

						numRapes28d = Integer.parseInt(m3.group(4));
						numRapes28dPrevYear = Integer.parseInt(m3.group(5));
						if (isFloat(m3.group(6)))
							numRapes28d1yChange = Double.parseDouble(m3.group(6));
						else
							numRapes28d1yChange = Double.NaN;

						numRapesYTD = Integer.parseInt(m3.group(7));
						numRapesYTDPrevYear = Integer.parseInt(m3.group(8));
						if (isFloat(m3.group(9)))
							numRapes1yChange = Double.parseDouble(m3.group(9));
						else
							numRapes1yChange = Double.NaN;

						if (isFloat(m3.group(10)))
							numRapes2yChange = Double.parseDouble(m3.group(10));
						else
							numRapes2yChange = Double.NaN;

						if (isFloat(m3.group(11)))
							numRapes5yChange = Double.parseDouble(m3.group(11));
						else
							numRapes5yChange = Double.NaN;

						if (isFloat(m3.group(12)))
							numRapes21yChange = Double.parseDouble(m3.group(12));
						else
							numRapes21yChange = Double.NaN;
						/*
						System.out.println(numRapesWTD1yChange + "," + numRapes28d1yChange + "," + numRapes1yChange + "," + numRapes2yChange + ","
								+ numRapes5yChange + "," + numRapes21yChange);
								*/
					}

					// extract robbery stats
					m4.reset(strLine);
					if (m4.find())
					{
						numRobberiesWTD = Integer.parseInt(m4.group(1));
						numRobberiesWTDPrevYear = Integer.parseInt(m4.group(2));
						if (isFloat(m4.group(3)))
							numRobberiesWTD1yChange = Double.parseDouble(m4.group(3));
						else
							numRobberiesWTD1yChange = Double.NaN;

						numRobberies28d = Integer.parseInt(m4.group(4));
						numRobberies28dPrevYear = Integer.parseInt(m4.group(5));
						if (isFloat(m4.group(6)))
							numRobberies28d1yChange = Double.parseDouble(m4.group(6));
						else
							numRobberies28d1yChange = Double.NaN;

						numRobberiesYTD = Integer.parseInt(m4.group(7));
						numRobberiesYTDPrevYear = Integer.parseInt(m4.group(8));
						if (isFloat(m4.group(9)))
							numRobberies1yChange = Double.parseDouble(m4.group(9));
						else
							numRobberies1yChange = Double.NaN;

						if (isFloat(m4.group(10)))
							numRobberies2yChange = Double.parseDouble(m4.group(10));
						else
							numRobberies2yChange = Double.NaN;

						if (isFloat(m4.group(11)))
							numRobberies5yChange = Double.parseDouble(m4.group(11));
						else
							numRobberies5yChange = Double.NaN;

						if (isFloat(m4.group(12)))
							numRobberies21yChange = Double.parseDouble(m4.group(12));
						else
							numRobberies21yChange = Double.NaN;
						/*
						System.out.println(numRobberiesWTD1yChange + "," + numRobberies28d1yChange + "," + numRobberies1yChange + "," + numRobberies2yChange
								+ "," + numRobberies5yChange + "," + numRobberies21yChange);*/

					}

					// extract felony assault stats
					m5.reset(strLine);
					if (m5.find())
					{
						numFelAssaultsWTD = Integer.parseInt(m5.group(1));
						numFelAssaultsWTDPrevYear = Integer.parseInt(m5.group(2));
						if (isFloat(m5.group(3)))
							numFelAssaultsWTD1yChange = Double.parseDouble(m5.group(3));
						else
							numFelAssaultsWTD1yChange = Double.NaN;

						numFelAssaults28d = Integer.parseInt(m5.group(4));
						numFelAssaults28dPrevYear = Integer.parseInt(m5.group(5));
						if (isFloat(m5.group(6)))
							numFelAssaults28d1yChange = Double.parseDouble(m5.group(6));
						else
							numFelAssaults28d1yChange = Double.NaN;

						numFelAssaultsYTD = Integer.parseInt(m5.group(7));
						numFelAssaultsYTDPrevYear = Integer.parseInt(m5.group(8));
						if (isFloat(m5.group(9)))
							numFelAssaults1yChange = Double.parseDouble(m5.group(9));
						else
							numFelAssaults1yChange = Double.NaN;

						if (isFloat(m5.group(10)))
							numFelAssaults2yChange = Double.parseDouble(m5.group(10));
						else
							numFelAssaults2yChange = Double.NaN;

						if (isFloat(m5.group(11)))
							numFelAssaults5yChange = Double.parseDouble(m5.group(11));
						else
							numFelAssaults5yChange = Double.NaN;

						if (isFloat(m5.group(12)))
							numFelAssaults21yChange = Double.parseDouble(m5.group(12));
						else
							numFelAssaults21yChange = Double.NaN;
						/*
						System.out.println(numFelAssaultsWTD1yChange + "," + numFelAssaults28d1yChange + "," + numFelAssaults1yChange + ","
								+ numFelAssaults2yChange + "," + numFelAssaults5yChange + "," + numFelAssaults21yChange);*/
					}

					// extract burglary stats
					m6.reset(strLine);
					if (m6.find())
					{
						numBurglariesWTD = Integer.parseInt(m6.group(1));
						numBurglariesWTDPrevYear = Integer.parseInt(m6.group(2));
						if (isFloat(m6.group(3)))
							numBurglariesWTD1yChange = Double.parseDouble(m6.group(3));
						else
							numBurglariesWTD1yChange = Double.NaN;

						numBurglaries28d = Integer.parseInt(m6.group(4));
						numBurglaries28dPrevYear = Integer.parseInt(m6.group(5));
						if (isFloat(m6.group(6)))
							numBurglaries28d1yChange = Double.parseDouble(m6.group(6));
						else
							numBurglaries28d1yChange = Double.NaN;

						numBurglariesYTD = Integer.parseInt(m6.group(7));
						numBurglariesYTDPrevYear = Integer.parseInt(m6.group(8));
						if (isFloat(m6.group(9)))
							numBurglaries1yChange = Double.parseDouble(m6.group(9));
						else
							numBurglaries1yChange = Double.NaN;

						if (isFloat(m6.group(10)))
							numBurglaries2yChange = Double.parseDouble(m6.group(10));
						else
							numBurglaries2yChange = Double.NaN;

						if (isFloat(m6.group(11)))
							numBurglaries5yChange = Double.parseDouble(m6.group(11));
						else
							numBurglaries5yChange = Double.NaN;

						if (isFloat(m6.group(12)))
							numBurglaries21yChange = Double.parseDouble(m6.group(12));
						else
							numBurglaries21yChange = Double.NaN;
						/*
						System.out.println(numBurglariesWTD1yChange + "," + numBurglaries28d1yChange + "," + numBurglaries1yChange + ","
								+ numBurglaries2yChange + "," + numBurglaries5yChange + "," + numBurglaries21yChange);*/
					}

					// extract gr. larceny stats
					m7.reset(strLine);
					if (m7.find())
					{
						numGrandLarceniesWTD = Integer.parseInt(m7.group(1));
						numGrandLarceniesWTDPrevYear = Integer.parseInt(m7.group(2));
						if (isFloat(m7.group(3)))
							numGrandLarceniesWTD1yChange = Double.parseDouble(m7.group(3));
						else
							numGrandLarceniesWTD1yChange = Double.NaN;

						numGrandLarcenies28d = Integer.parseInt(m7.group(4));
						numGrandLarcenies28dPrevYear = Integer.parseInt(m7.group(5));
						if (isFloat(m7.group(6)))
							numGrandLarcenies28d1yChange = Double.parseDouble(m7.group(6));
						else
							numGrandLarcenies28d1yChange = Double.NaN;

						numGrandLarceniesYTD = Integer.parseInt(m7.group(7));
						numGrandLarceniesYTDPrevYear = Integer.parseInt(m7.group(8));
						if (isFloat(m7.group(9)))
							numGrandLarcenies1yChange = Double.parseDouble(m7.group(9));
						else
							numGrandLarcenies1yChange = Double.NaN;

						if (isFloat(m7.group(10)))
							numGrandLarcenies2yChange = Double.parseDouble(m7.group(10));
						else
							numGrandLarcenies2yChange = Double.NaN;

						if (isFloat(m7.group(11)))
							numGrandLarcenies5yChange = Double.parseDouble(m7.group(11));
						else
							numGrandLarcenies5yChange = Double.NaN;

						if (isFloat(m7.group(12)))
							numGrandLarcenies21yChange = Double.parseDouble(m7.group(12));
						else
							numGrandLarcenies21yChange = Double.NaN;
						/*
						System.out.println(numGrandLarceniesWTD1yChange + "," + numGrandLarcenies28d1yChange + "," + numGrandLarcenies1yChange + ","
								+ numGrandLarcenies2yChange + "," + numGrandLarcenies5yChange + "," + numGrandLarcenies21yChange);*/
					}

					// extract auto gr. larceny stats
					m8.reset(strLine);
					if (m8.find())
					{
						numGLAWTD = Integer.parseInt(m8.group(1));
						numGLAWTDPrevYear = Integer.parseInt(m8.group(2));
						if (isFloat(m8.group(3)))
							numGLAWTD1yChange = Double.parseDouble(m8.group(3));
						else
							numGLAWTD1yChange = Double.NaN;

						numGLA28d = Integer.parseInt(m8.group(4));
						numGLA28dPrevYear = Integer.parseInt(m8.group(5));
						if (isFloat(m8.group(6)))
							numGLA28d1yChange = Double.parseDouble(m8.group(6));
						else
							numGLA28d1yChange = Double.NaN;

						numGLAYTD = Integer.parseInt(m8.group(7));
						numGLAYTDPrevYear = Integer.parseInt(m8.group(8));
						if (isFloat(m8.group(9)))
							numGLA1yChange = Double.parseDouble(m8.group(9));
						else
							numGLA1yChange = Double.NaN;

						if (isFloat(m8.group(10)))
							numGLA2yChange = Double.parseDouble(m8.group(10));
						else
							numGLA2yChange = Double.NaN;

						if (isFloat(m8.group(11)))
							numGLA5yChange = Double.parseDouble(m8.group(11));
						else
							numGLA5yChange = Double.NaN;

						if (isFloat(m8.group(12)))
							numGLA21yChange = Double.parseDouble(m8.group(12));
						else
							numGLA21yChange = Double.NaN;
						/*
						System.out.println(numGLAWTD1yChange + "," + numGLA28d1yChange + "," + numGLA1yChange + "," + numGLA2yChange + "," + numGLA5yChange
								+ "," + numGLA21yChange);*/
					}

					// extract transit crime stats
					m9.reset(strLine);
					if (m9.find())
					{
						numTransitWTD = Integer.parseInt(m9.group(1));
						numTransitWTDPrevYear = Integer.parseInt(m9.group(2));
						if (isFloat(m9.group(3)))
							numTransitWTD1yChange = Double.parseDouble(m9.group(3));
						else
							numTransitWTD1yChange = Double.NaN;

						numTransit28d = Integer.parseInt(m9.group(4));
						numTransit28dPrevYear = Integer.parseInt(m9.group(5));
						if (isFloat(m9.group(6)))
							numTransit28d1yChange = Double.parseDouble(m9.group(6));
						else
							numTransit28d1yChange = Double.NaN;

						numTransitYTD = Integer.parseInt(m9.group(7));
						numTransitYTDPrevYear = Integer.parseInt(m9.group(8));
						if (isFloat(m9.group(9)))
							numTransit1yChange = Double.parseDouble(m9.group(9));
						else
							numTransit1yChange = Double.NaN;

						if (isFloat(m9.group(10)))
							numTransit2yChange = Double.parseDouble(m9.group(10));
						else
							numTransit2yChange = Double.NaN;

						if (isFloat(m9.group(11)))
							numTransit5yChange = Double.parseDouble(m9.group(11));
						else
							numTransit5yChange = Double.NaN;

						if (isFloat(m9.group(12)))
							numTransit21yChange = Double.parseDouble(m9.group(12));
						else
							numTransit21yChange = Double.NaN;
						/*
						System.out.println(numTransitWTD1yChange + "," + numTransit28d1yChange + "," + numTransit1yChange + "," + numTransit2yChange + ","
								+ numTransit5yChange + "," + numTransit21yChange);*/
					}

					// extract housing crime stats
					m10.reset(strLine);
					if (m10.find())
					{
						numHousingWTD = Integer.parseInt(m10.group(1));
						numHousingWTDPrevYear = Integer.parseInt(m10.group(2));
						if (isFloat(m10.group(3)))
							numHousingWTD1yChange = Double.parseDouble(m10.group(3));
						else
							numHousingWTD1yChange = Double.NaN;

						numHousing28d = Integer.parseInt(m10.group(4));
						numHousing28dPrevYear = Integer.parseInt(m10.group(5));
						if (isFloat(m10.group(6)))
							numHousing28d1yChange = Double.parseDouble(m10.group(6));
						else
							numHousing28d1yChange = Double.NaN;

						numHousingYTD = Integer.parseInt(m10.group(7));
						numHousingYTDPrevYear = Integer.parseInt(m10.group(8));
						if (isFloat(m10.group(9)))
							numHousing1yChange = Double.parseDouble(m10.group(9));
						else
							numHousing1yChange = Double.NaN;

						if (isFloat(m10.group(10)))
							numHousing2yChange = Double.parseDouble(m10.group(10));
						else
							numHousing2yChange = Double.NaN;

						if (isFloat(m10.group(11)))
							numHousing5yChange = Double.parseDouble(m10.group(11));
						else
							numHousing5yChange = Double.NaN;

						if (isFloat(m10.group(12)))
							numHousing21yChange = Double.parseDouble(m10.group(12));
						else
							numHousing21yChange = Double.NaN;
						/*
						System.out.println(numHousingWTD1yChange + "," + numHousing28d1yChange + "," + numHousing1yChange + "," + numHousing2yChange + ","
								+ numHousing5yChange + "," + numHousing21yChange);*/
					}

					// extract petit larceny stats
					m11.reset(strLine);
					if (m11.find())
					{
						numPetitLarceniesWTD = Integer.parseInt(m11.group(1));
						numPetitLarceniesWTDPrevYear = Integer.parseInt(m11.group(2));
						if (isFloat(m11.group(3)))
							numPetitLarceniesWTD1yChange = Double.parseDouble(m11.group(3));
						else
							numPetitLarceniesWTD1yChange = Double.NaN;

						numPetitLarcenies28d = Integer.parseInt(m11.group(4));
						numPetitLarcenies28dPrevYear = Integer.parseInt(m11.group(5));
						if (isFloat(m11.group(6)))
							numPetitLarcenies28d1yChange = Double.parseDouble(m11.group(6));
						else
							numPetitLarcenies28d1yChange = Double.NaN;

						numPetitLarceniesYTD = Integer.parseInt(m11.group(7));
						numPetitLarceniesYTDPrevYear = Integer.parseInt(m11.group(8));
						if (isFloat(m11.group(9)))
							numPetitLarcenies1yChange = Double.parseDouble(m11.group(9));
						else
							numPetitLarcenies1yChange = Double.NaN;

						if (isFloat(m11.group(10)))
							numPetitLarcenies2yChange = Double.parseDouble(m11.group(10));
						else
							numPetitLarcenies2yChange = Double.NaN;

						if (isFloat(m11.group(11)))
							numPetitLarcenies5yChange = Double.parseDouble(m11.group(11));
						else
							numPetitLarcenies5yChange = Double.NaN;

						if (isFloat(m11.group(12)))
							numPetitLarcenies21yChange = Double.parseDouble(m11.group(12));
						else
							numPetitLarcenies21yChange = Double.NaN;
						/*
						System.out.println(numPetitLarceniesWTD1yChange + "," + numPetitLarcenies28d1yChange + "," + numPetitLarcenies1yChange + ","
								+ numPetitLarcenies2yChange + "," + numPetitLarcenies5yChange + "," + numPetitLarcenies21yChange);*/
					}

					// extract misd assault stats
					m12.reset(strLine);
					if (m12.find())
					{
						numMisdAssaultsWTD = Integer.parseInt(m12.group(1));
						numMisdAssaultsWTDPrevYear = Integer.parseInt(m12.group(2));
						if (isFloat(m12.group(3)))
							numMisdAssaultsWTD1yChange = Double.parseDouble(m12.group(3));
						else
							numMisdAssaultsWTD1yChange = Double.NaN;

						numMisdAssaults28d = Integer.parseInt(m12.group(4));
						numMisdAssaults28dPrevYear = Integer.parseInt(m12.group(5));
						if (isFloat(m12.group(6)))
							numMisdAssaults28d1yChange = Double.parseDouble(m12.group(6));
						else
							numMisdAssaults28d1yChange = Double.NaN;

						numMisdAssaultsYTD = Integer.parseInt(m12.group(7));
						numMisdAssaultsYTDPrevYear = Integer.parseInt(m12.group(8));
						if (isFloat(m12.group(9)))
							numMisdAssaults1yChange = Double.parseDouble(m12.group(9));
						else
							numMisdAssaults1yChange = Double.NaN;

						if (isFloat(m12.group(10)))
							numMisdAssaults2yChange = Double.parseDouble(m12.group(10));
						else
							numMisdAssaults2yChange = Double.NaN;

						if (isFloat(m12.group(11)))
							numMisdAssaults5yChange = Double.parseDouble(m12.group(11));
						else
							numMisdAssaults5yChange = Double.NaN;

						if (isFloat(m12.group(12)))
							numMisdAssaults21yChange = Double.parseDouble(m12.group(12));
						else
							numMisdAssaults21yChange = Double.NaN;
						/*
						System.out.println(numMisdAssaultsWTD1yChange + "," + numMisdAssaults28d1yChange + "," + numMisdAssaults1yChange + ","
								+ numMisdAssaults2yChange + "," + numMisdAssaults5yChange + "," + numMisdAssaults21yChange);*/
					}

					// extract misd sex crimes stats
					m13.reset(strLine);
					if (m13.find())
					{
						numMisdSexCrimesWTD = Integer.parseInt(m13.group(1));
						numMisdSexCrimesWTDPrevYear = Integer.parseInt(m13.group(2));
						if (isFloat(m13.group(3)))
							numMisdSexCrimesWTD1yChange = Double.parseDouble(m13.group(3));
						else
							numMisdSexCrimesWTD1yChange = Double.NaN;

						numMisdSexCrimes28d = Integer.parseInt(m13.group(4));
						numMisdSexCrimes28dPrevYear = Integer.parseInt(m13.group(5));
						if (isFloat(m13.group(6)))
							numMisdSexCrimes28d1yChange = Double.parseDouble(m13.group(6));
						else
							numMisdSexCrimes28d1yChange = Double.NaN;

						numMisdSexCrimesYTD = Integer.parseInt(m13.group(7));
						numMisdSexCrimesYTDPrevYear = Integer.parseInt(m13.group(8));
						if (isFloat(m13.group(9)))
							numMisdSexCrimes1yChange = Double.parseDouble(m13.group(9));
						else
							numMisdSexCrimes1yChange = Double.NaN;

						if (isFloat(m13.group(10)))
							numMisdSexCrimes2yChange = Double.parseDouble(m13.group(10));
						else
							numMisdSexCrimes2yChange = Double.NaN;

						if (isFloat(m13.group(11)))
							numMisdSexCrimes5yChange = Double.parseDouble(m13.group(11));
						else
							numMisdSexCrimes5yChange = Double.NaN;

						if (isFloat(m13.group(12)))
							numMisdSexCrimes21yChange = Double.parseDouble(m13.group(12));
						else
							numMisdSexCrimes21yChange = Double.NaN;
						/*
						System.out.println(numMisdSexCrimesWTD1yChange + "," + numMisdSexCrimes28d1yChange + "," + numMisdSexCrimes1yChange + ","
								+ numMisdSexCrimes2yChange + "," + numMisdSexCrimes5yChange + "," + numMisdSexCrimes21yChange);*/
					}

				}
				// Close the input stream
				dis.close();
			}
			catch (FileNotFoundException fException)
			{
				System.err.println("Error: The source file " + args[index] + " does not exist.");
				System.exit(1);
			}
			catch (IOException ioException)
			{
				System.err.println("Error: The source file " + args[index] + "could not be read.");
				System.exit(1);
			}

			/*
			 * create the new crime report object and add it to the
			 * crimeReports array list
			 */

			crimeReports.add(

			new CrimeReport(isPrecinctReport, isBoroughReport, isCityReport, precinct, borough, startCal, endCal, numMurdersWTD, numRapesWTD, numRobberiesWTD,
					numFelAssaultsWTD, numBurglariesWTD, numGrandLarceniesWTD, numGLAWTD, numTransitWTD, numHousingWTD, numPetitLarceniesWTD,
					numMisdAssaultsWTD, numMisdSexCrimesWTD, numMurdersWTDPrevYear, numRapesWTDPrevYear, numRobberiesWTDPrevYear, numFelAssaultsWTDPrevYear,
					numBurglariesWTDPrevYear, numGrandLarceniesWTDPrevYear, numGLAWTDPrevYear, numTransitWTDPrevYear, numHousingWTDPrevYear,
					numPetitLarceniesWTDPrevYear, numMisdAssaultsWTDPrevYear, numMisdSexCrimesWTDPrevYear, numMurders28d, numRapes28d, numRobberies28d,
					numFelAssaults28d, numBurglaries28d, numGrandLarcenies28d, numGLA28d, numTransit28d, numHousing28d, numPetitLarcenies28d,
					numMisdAssaults28d, numMisdSexCrimes28d, numMurders28dPrevYear, numRapes28dPrevYear, numRobberies28dPrevYear, numFelAssaults28dPrevYear,
					numBurglaries28dPrevYear, numGrandLarcenies28dPrevYear, numGLA28dPrevYear, numTransit28dPrevYear, numHousing28dPrevYear,
					numPetitLarcenies28dPrevYear, numMisdAssaults28dPrevYear, numMisdSexCrimes28dPrevYear, numMurdersYTD, numRapesYTD, numRobberiesYTD,
					numFelAssaultsYTD, numBurglariesYTD, numGrandLarceniesYTD, numGLAYTD, numTransitYTD, numHousingYTD, numPetitLarceniesYTD,
					numMisdAssaultsYTD, numMisdSexCrimesYTD, numMurdersYTDPrevYear, numRapesYTDPrevYear, numRobberiesYTDPrevYear, numFelAssaultsYTDPrevYear,
					numBurglariesYTDPrevYear, numGrandLarceniesYTDPrevYear, numGLAYTDPrevYear, numTransitYTDPrevYear, numHousingYTDPrevYear,
					numPetitLarceniesYTDPrevYear, numMisdAssaultsYTDPrevYear, numMisdSexCrimesYTDPrevYear, numMurdersWTD1yChange, numMurders28d1yChange,
					numMurders1yChange, numMurders2yChange, numMurders5yChange, numMurders21yChange, numRapesWTD1yChange, numRapes28d1yChange,
					numRapes1yChange, numRapes2yChange, numRapes5yChange, numRapes21yChange, numRobberiesWTD1yChange, numRobberies28d1yChange,
					numRobberies1yChange, numRobberies2yChange, numRobberies5yChange, numRobberies21yChange, numFelAssaultsWTD1yChange,
					numFelAssaults28d1yChange, numFelAssaults1yChange, numFelAssaults2yChange, numFelAssaults5yChange, numFelAssaults21yChange,
					numBurglariesWTD1yChange, numBurglaries28d1yChange, numBurglaries1yChange, numBurglaries2yChange, numBurglaries5yChange,
					numBurglaries21yChange, numGrandLarceniesWTD1yChange, numGrandLarcenies28d1yChange, numGrandLarcenies1yChange, numGrandLarcenies2yChange,
					numGrandLarcenies5yChange, numGrandLarcenies21yChange, numGLAWTD1yChange, numGLA28d1yChange, numGLA1yChange, numGLA2yChange,
					numGLA5yChange, numGLA21yChange, numTransitWTD1yChange, numTransit28d1yChange, numTransit1yChange, numTransit2yChange, numTransit5yChange,
					numTransit21yChange, numHousingWTD1yChange, numHousing28d1yChange, numHousing1yChange, numHousing2yChange, numHousing5yChange,
					numHousing21yChange, numPetitLarceniesWTD1yChange, numPetitLarcenies28d1yChange, numPetitLarcenies1yChange, numPetitLarcenies2yChange,
					numPetitLarcenies5yChange, numPetitLarcenies21yChange, numMisdAssaultsWTD1yChange, numMisdAssaults28d1yChange, numMisdAssaults1yChange,
					numMisdAssaults2yChange, numMisdAssaults5yChange, numMisdAssaults21yChange, numMisdSexCrimesWTD1yChange, numMisdSexCrimes28d1yChange,
					numMisdSexCrimes1yChange, numMisdSexCrimes2yChange, numMisdSexCrimes5yChange, numMisdSexCrimes21yChange)

			);

		}

		// iterate through the crime reports
		processCrimeReports(crimeReports);

		String myNews = postProcessOutput(myOutputText);
		//print the news article generated to the console
		System.out.println("\n\n\n\n" + myNews);

		//write the news article to file
		PrintWriter writer = null;
		try
		{
			writer = new PrintWriter("generated_news.txt", "UTF-8");
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.println(myNews);
		writer.close();
	}
}
