import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsGenerator
{
	static final int MAX_SIZE = 20;

	// takes a bunch of filenames, extracts the relevant information from each
	// file and
	// returns an array of crime reports

	private static final Pattern DOUBLE_PATTERN = Pattern
			.compile("[\\x00-\\x20]*[+-]?(NaN|Infinity|((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)"
					+ "([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|"
					+ "(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))"
					+ "[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*");

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

	public static void processCrimeReports(ArrayList<CrimeReport> crimeReports,
			String[] templateSentences)
	{
		for (CrimeReport cr : crimeReports)
		{
			// if crime report is for a precinct
			if (cr.isPrecinctReport)
			{
				double percentageChange
				//if number of robberies has risen
				if (cr.numRobberiesWTD > cr.numRobberiesWTDPrevYear)
				{
					
				}
				else if (cr.numRobberiesWTD < cr.numRobberiesWTDPrevYear)
				{
					
				}
					
				
			}
			// otherwise it is for a borough
			else
			{

			}
		}

	}

	public static void main(String[] args)
	{

		String[] templateSentences = new String[MAX_SIZE];

		templateSentences[0] = "The number of robberies in the <PRECINCT_NUM> this week is <PERCENTAGE> <LOWER/HIGHER> than the same time last year";
		templateSentences[1] = "Other crimes that have seen a decline since the start of this year include <CRIMES>";

		templateSentences[10] = "Police records indicate that crime rates in <BOROUGH> are falling. ";
		templateSentences[11] = "In the past month, a total of <NUM_CRIMES> <CRIME_TYPE> have been reported in the <BOROUGH>. ";
		templateSentences[12] = "This represents a <PERCENTAGE_CHANGE> <DROP/RISE> in the number of <CRIME_TYPE> from last year. ";
		templateSentences[13] = "In the <BOROUGH>, last week witnessed ";
		templateSentences[14] = "<NUM_CRIME> <CRIME_TYPE>";

		templateSentences[15] = "In the <PRECINCT_NUMBER> precinct, <CRIME_TYPE> <FELL/ROSE> from <LAST_YEAR_NUMBER> to <CURRENT_YEAR_NUMBER>";
		templateSentences[16] = "The number of <CRIME_TYPE> has <FALLEN/RISEN> from <BYGONE_YEAR_NUMBER> in <BYGONE_YEAR>"
				+ "to <CURREN_YEAR_NUMBER> in <CURRENT_YEAR>";
		templateSentences[17] = "More good news comes from the <PRECINCT_NUMBER> precinct. ";
		templateSentences[18] = "Police records state that the number of <CRIME_TYPE> is now <PERCENTAGE> <LOWER/HIGHER> than it was in <BYGONE_YEAR>. ";
		templateSentences[19] = "There were a total of <NUM_CRIME> complaints about <CRIME_TYPE> in the week from <START_DATE> through <END_DATE>. ";

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

		Pattern p1 = Pattern
				.compile("Report\\s+Covering\\s+the\\s+Week\\s+(\\d+)\\/(\\d+)\\/(\\d+)\\s+Through\\s+(\\d+)\\/(\\d+)\\/(\\d+)");
		Matcher m1 = p1.matcher("");

		Pattern p2 = Pattern
				.compile("Murder,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),(\\S+)");
		Matcher m2 = p2.matcher("");

		Pattern p3 = Pattern
				.compile("Rape,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),(\\S+)");
		Matcher m3 = p3.matcher("");

		Pattern p4 = Pattern
				.compile("Robbery,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),(\\S+)");
		Matcher m4 = p4.matcher("");

		Pattern p5 = Pattern
				.compile("Fel\\.\\s+Assault,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),(\\S+)");
		Matcher m5 = p5.matcher("");

		Pattern p6 = Pattern
				.compile("Burglary,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),(\\S+)");
		Matcher m6 = p6.matcher("");

		Pattern p7 = Pattern
				.compile("Gr\\.\\s+Larceny,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),(\\S+)");
		Matcher m7 = p7.matcher("");

		Pattern p8 = Pattern
				.compile("G\\.L\\.A\\.,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),(\\S+)");
		Matcher m8 = p8.matcher("");

		Pattern p9 = Pattern
				.compile("Transit,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),(\\S+)");
		Matcher m9 = p9.matcher("");

		Pattern p10 = Pattern
				.compile("Housing,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),(\\S+)");
		Matcher m10 = p10.matcher("");

		Pattern p11 = Pattern
				.compile("Petit\\s+Larceny,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),(\\S+)");
		Matcher m11 = p11.matcher("");

		Pattern p12 = Pattern
				.compile("Misd\\.\\s+Assault,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),(\\S+)");
		Matcher m12 = p12.matcher("");

		Pattern p13 = Pattern
				.compile("Misd\\.\\s+Sex\\s+Crimes,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),(\\S+)");
		Matcher m13 = p13.matcher("");

		Pattern p14 = Pattern
				.compile("Shooting\\s+Vic.+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),(\\S+)");
		Matcher m14 = p14.matcher("");

		Pattern p15 = Pattern
				.compile("Shooting\\s+Inc.+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),\\S+,(\\S+),(\\S+),(\\S+)");
		Matcher m15 = p15.matcher("");

		Pattern p30 = Pattern.compile("^\\D+(\\d+)pct\\.csv$");
		Matcher m30 = p30.matcher("");

		// instantiate an array list to hold all the crime reports
		ArrayList<CrimeReport> crimeReports = new ArrayList<CrimeReport>();

		// iterate through all the files provided as command line arguments
		for (int index = 0; index < args.length; index++)
		{

			try
			{
				fis = new FileInputStream(args[index]);
				dis = new DataInputStream(fis);
				br = new BufferedReader(new InputStreamReader(dis));

				boolean isPrecinctReport;

				// precinct set to -1 for borough-level report
				// otherwise set to actual precinct
				int precinct = 0;

				// For Queens and Brooklyn, we consider the north and south
				// boroughs
				// separately
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

				// year to date stats for current year
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

				// 2-year, 5-year and 21-year percentage changes
				double numMurders2yChange = 0.0;
				double numMurders5yChange = 0.0;
				double numMurders21yChange = 0.0;

				// 2-year, 5-year and 21-year percentage changes
				double numRapes2yChange = 0.0;
				double numRapes5yChange = 0.0;
				double numRapes21yChange = 0.0;

				// 2-year, 5-year and 21-year percentage changes
				double numRobberies2yChange = 0.0;
				double numRobberies5yChange = 0.0;
				double numRobberies21yChange = 0.0;

				// 2-year, 5-year and 21-year percentage changes
				double numFelAssaults2yChange = 0.0;
				double numFelAssaults5yChange = 0.0;
				double numFelAssaults21yChange = 0.0;

				// 2-year, 5-year and 21-year percentage changes
				double numBurglaries2yChange = 0.0;
				double numBurglaries5yChange = 0.0;
				double numBurglaries21yChange = 0.0;

				// 2-year, 5-year and 21-year percentage changes
				double numGrandLarcenies2yChange = 0.0;
				double numGrandLarcenies5yChange = 0.0;
				double numGrandLarcenies21yChange = 0.0;

				// 2-year, 5-year and 21-year percentage changes
				double numGLA2yChange = 0.0;
				double numGLA5yChange = 0.0;
				double numGLA21yChange = 0.0;

				// 2-year, 5-year and 21-year percentage changes
				double numTransit2yChange = 0.0;
				double numTransit5yChange = 0.0;
				double numTransit21yChange = 0.0;

				// 2-year, 5-year and 21-year percentage changes
				double numHousing2yChange = 0.0;
				double numHousing5yChange = 0.0;
				double numHousing21yChange = 0.0;

				// 2-year, 5-year and 21-year percentage changes
				double numPetitLarcenies2yChange = 0.0;
				double numPetitLarcenies5yChange = 0.0;
				double numPetitLarcenies21yChange = 0.0;

				// 2-year, 5-year and 21-year percentage changes
				double numMisdAssaults2yChange = 0.0;
				double numMisdAssaults5yChange = 0.0;
				double numMisdAssaults21yChange = 0.0;

				// 2-year, 5-year and 21-year percentage changes
				double numMisdSexCrimes2yChange = 0.0;
				double numMisdSexCrimes5yChange = 0.0;
				double numMisdSexCrimes21yChange = 0.0;

				// 2-year, 5-year and 21-year percentage changes
				// double numShootingVic2yChange;
				// double numShootingVic5yChange;
				// double numShootingVic21yChange;

				// 2-year, 5-year and 21-year percentage changes
				// double numShootingInc2yChange;
				// double numShootingInc5yChange;
				// double numShootingInc21yChange;

				// if source filename has pct.csv in its name then this is a
				// precinct report
				m30.reset(args[index]);
				if (m30.find())
				{
					isPrecinctReport = true;
					if (isInteger(m30.group()))
						precinct = Integer.parseInt(m30.group());

				}
				else
					isPrecinctReport = false;

				String strLine;

				// Read the source file line by line
				while ((strLine = br.readLine()) != null)
				{
					/*
					 * StringBuffer stringBuffer = new StringBuffer();
					 * 
					 * m0.reset(strLine); while (m0.find()) {
					 * m0.appendReplacement(stringBuffer, m0.group(1) +
					 * m0.group(3));
					 * System.out.println(stringBuffer.toString()); }
					 */
					// m0.appendTail(stringBuffer);

					m1.reset(strLine); // reset the input
					if (m1.find()) // if the date matcher finds the pattern
									// p1
					{
						// System.out.println(m1.group(1));
						// System.out.println(m1.group(2));
						// System.out.println(m1.group(3));

						int startMonth = Integer.parseInt(m1.group(1)) - 1;
						int startDate = Integer.parseInt(m1.group(2));
						int startYear = Integer.parseInt(m1.group(3));

						int endMonth = Integer.parseInt(m1.group(4)) - 1;
						int endDate = Integer.parseInt(m1.group(5));
						int endYear = Integer.parseInt(m1.group(6));

						startCal = new GregorianCalendar(startYear, startMonth,
								startDate);

						endCal = new GregorianCalendar(endYear, endMonth,
								endDate);

						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy MM dd");

						System.out.println(sdf.format(startCal.getTime()));
						System.out.println(sdf.format(endCal.getTime()));

						// System.out.println(m1.group(4));
						// System.out.println(m1.group(5));
						// System.out.println(m1.group(6));

					}

					m2.reset(strLine);
					if (m2.find())
					{
						numMurdersWTD = Integer.parseInt(m2.group(1));
						numMurdersWTDPrevYear = Integer.parseInt(m2.group(2));

						numMurders28d = Integer.parseInt(m2.group(3));
						numMurders28dPrevYear = Integer.parseInt(m2.group(4));

						numMurdersYTD = Integer.parseInt(m2.group(5));
						numMurdersYTDPrevYear = Integer.parseInt(m2.group(6));

						System.out.println(numMurdersWTD + ","
								+ numMurdersWTDPrevYear + "," + numMurders28d
								+ "," + numMurders28dPrevYear + ","
								+ numMurdersYTD + "," + numMurdersYTDPrevYear);

						if (isFloat(m2.group(7)))
							numMurders2yChange = Double
									.parseDouble(m2.group(7));
						else
							numMurders2yChange = Double.NaN;

						if (isFloat(m2.group(8)))
							numMurders5yChange = Double
									.parseDouble(m2.group(8));
						else
							numMurders5yChange = Double.NaN;

						if (isFloat(m2.group(9)))
							numMurders21yChange = Double.parseDouble(m2
									.group(9));
						else
							numMurders21yChange = Double.NaN;

						System.out.println(numMurders2yChange + ","
								+ numMurders5yChange + ","
								+ numMurders21yChange);
					}

					m3.reset(strLine);
					if (m3.find())
					{
						numRapesWTD = Integer.parseInt(m3.group(1));
						numRapesWTDPrevYear = Integer.parseInt(m3.group(2));

						numRapes28d = Integer.parseInt(m3.group(3));
						numRapes28dPrevYear = Integer.parseInt(m3.group(4));

						numRapesYTD = Integer.parseInt(m3.group(5));
						numRapesYTDPrevYear = Integer.parseInt(m3.group(6));

						System.out.println(numRapesWTD + ","
								+ numRapesWTDPrevYear + "," + numRapes28d + ","
								+ numRapes28dPrevYear + "," + numRapesYTD + ","
								+ numRapesYTDPrevYear);

						if (isFloat(m3.group(7)))
							numRapes2yChange = Double.parseDouble(m3.group(7));
						else
							numRapes2yChange = Double.NaN;

						if (isFloat(m3.group(8)))
							numRapes5yChange = Double.parseDouble(m3.group(8));
						else
							numRapes5yChange = Double.NaN;

						if (isFloat(m3.group(9)))
							numRapes21yChange = Double.parseDouble(m3.group(9));
						else
							numRapes21yChange = Double.NaN;

						System.out.println(numRapes2yChange + ","
								+ numRapes5yChange + "," + numRapes21yChange);
					}

					m4.reset(strLine);
					if (m4.find())
					{
						// if (isInteger(m4.group(1)))
						numRobberiesWTD = Integer.parseInt(m4.group(1));

						// if (isInteger(m4.group(2)))
						numRobberiesWTDPrevYear = Integer.parseInt(m4.group(2));

						// if (isInteger(m4.group(3)))
						numRobberies28d = Integer.parseInt(m4.group(3));

						// if (isInteger(m4.group(4)))
						numRobberies28dPrevYear = Integer.parseInt(m4.group(4));

						// if (isInteger(m4.group(5)))
						numRobberiesYTD = Integer.parseInt(m4.group(5));

						// if (isInteger(m4.group(6)))
						numRobberiesYTDPrevYear = Integer.parseInt(m4.group(6));

						System.out.println(numRobberiesWTD + ","
								+ numRobberiesWTDPrevYear + ","
								+ numRobberies28d + ","
								+ numRobberies28dPrevYear + ","
								+ numRobberiesYTD + ","
								+ numRobberiesYTDPrevYear);

						if (isFloat(m4.group(7)))
							numRobberies2yChange = Double.parseDouble(m4
									.group(7));
						else
							numRobberies2yChange = Double.NaN;

						if (isFloat(m4.group(8)))
							numRobberies5yChange = Double.parseDouble(m4
									.group(8));
						else
							numRobberies5yChange = Double.NaN;

						if (isFloat(m4.group(9)))
							numRobberies21yChange = Double.parseDouble(m4
									.group(9));
						else
							numRobberies21yChange = Double.NaN;

						System.out.println(numRobberies2yChange + ","
								+ numRobberies5yChange + ","
								+ numRobberies21yChange);
					}

					m5.reset(strLine);
					if (m5.find())
					{
						numFelAssaultsWTD = Integer.parseInt(m5.group(1));
						numFelAssaultsWTDPrevYear = Integer.parseInt(m5
								.group(2));

						numFelAssaults28d = Integer.parseInt(m5.group(3));
						numFelAssaults28dPrevYear = Integer.parseInt(m5
								.group(4));

						numFelAssaultsYTD = Integer.parseInt(m5.group(5));
						numFelAssaultsYTDPrevYear = Integer.parseInt(m5
								.group(6));

						System.out.println(numFelAssaultsWTD + ","
								+ numFelAssaultsWTDPrevYear + ","
								+ numFelAssaults28d + ","
								+ numFelAssaults28dPrevYear + ","
								+ numFelAssaultsYTD + ","
								+ numFelAssaultsYTDPrevYear);

						if (isFloat(m5.group(7)))
							numFelAssaults2yChange = Double.parseDouble(m5
									.group(7));
						else
							numFelAssaults2yChange = Double.NaN;

						if (isFloat(m5.group(8)))
							numFelAssaults5yChange = Double.parseDouble(m5
									.group(8));
						else
							numFelAssaults5yChange = Double.NaN;

						if (isFloat(m5.group(9)))
							numFelAssaults21yChange = Double.parseDouble(m5
									.group(9));
						else
							numFelAssaults21yChange = Double.NaN;

						System.out.println(numFelAssaults2yChange + ","
								+ numFelAssaults5yChange + ","
								+ numFelAssaults21yChange);
					}

					m6.reset(strLine);
					if (m6.find())
					{
						numBurglariesWTD = Integer.parseInt(m6.group(1));
						numBurglariesWTDPrevYear = Integer
								.parseInt(m6.group(2));

						numBurglaries28d = Integer.parseInt(m6.group(3));
						numBurglaries28dPrevYear = Integer
								.parseInt(m6.group(4));

						numBurglariesYTD = Integer.parseInt(m6.group(5));
						numBurglariesYTDPrevYear = Integer
								.parseInt(m6.group(6));

						System.out.println(numBurglariesWTD + ","
								+ numBurglariesWTDPrevYear + ","
								+ numBurglaries28d + ","
								+ numBurglaries28dPrevYear + ","
								+ numBurglariesYTD + ","
								+ numBurglariesYTDPrevYear);

						if (isFloat(m6.group(7)))
							numBurglaries2yChange = Double.parseDouble(m6
									.group(7));
						else
							numBurglaries2yChange = Double.NaN;

						if (isFloat(m6.group(8)))
							numBurglaries5yChange = Double.parseDouble(m6
									.group(8));
						else
							numBurglaries5yChange = Double.NaN;

						if (isFloat(m6.group(9)))
							numBurglaries21yChange = Double.parseDouble(m6
									.group(9));
						else
							numBurglaries21yChange = Double.NaN;

						System.out.println(numBurglaries2yChange + ","
								+ numBurglaries5yChange + ","
								+ numBurglaries21yChange);
					}

					m7.reset(strLine);
					if (m7.find())
					{
						numGrandLarceniesWTD = Integer.parseInt(m7.group(1));
						numGrandLarceniesWTDPrevYear = Integer.parseInt(m7
								.group(2));

						numGrandLarcenies28d = Integer.parseInt(m7.group(3));
						numGrandLarcenies28dPrevYear = Integer.parseInt(m7
								.group(4));

						numGrandLarceniesYTD = Integer.parseInt(m7.group(5));
						numGrandLarceniesYTDPrevYear = Integer.parseInt(m7
								.group(6));

						System.out.println(numGrandLarceniesWTD + ","
								+ numGrandLarceniesWTDPrevYear + ","
								+ numGrandLarcenies28d + ","
								+ numGrandLarcenies28dPrevYear + ","
								+ numGrandLarceniesYTD + ","
								+ numGrandLarceniesYTDPrevYear);

						if (isFloat(m7.group(7)))
							numGrandLarcenies2yChange = Double.parseDouble(m7
									.group(7));
						else
							numGrandLarcenies2yChange = Double.NaN;

						if (isFloat(m7.group(8)))
							numGrandLarcenies5yChange = Double.parseDouble(m7
									.group(8));
						else
							numGrandLarcenies5yChange = Double.NaN;

						if (isFloat(m7.group(9)))
							numGrandLarcenies21yChange = Double.parseDouble(m7
									.group(9));
						else
							numGrandLarcenies21yChange = Double.NaN;

						System.out.println(numGrandLarcenies2yChange + ","
								+ numGrandLarcenies5yChange + ","
								+ numGrandLarcenies21yChange);
					}

					m8.reset(strLine);
					if (m8.find())
					{
						numGLAWTD = Integer.parseInt(m8.group(1));
						numGLAWTDPrevYear = Integer.parseInt(m8.group(2));

						numGLA28d = Integer.parseInt(m8.group(3));
						numGLA28dPrevYear = Integer.parseInt(m8.group(4));

						numGLAYTD = Integer.parseInt(m8.group(5));
						numGLAYTDPrevYear = Integer.parseInt(m8.group(6));

						System.out.println(numGLAWTD + "," + numGLAWTDPrevYear
								+ "," + numGLA28d + "," + numGLA28dPrevYear
								+ "," + numGLAYTD + "," + numGLAYTDPrevYear);

						if (isFloat(m8.group(7)))
							numGLA2yChange = Double.parseDouble(m8.group(7));
						else
							numGLA2yChange = Double.NaN;

						if (isFloat(m8.group(8)))
							numGLA5yChange = Double.parseDouble(m8.group(8));
						else
							numGLA5yChange = Double.NaN;

						if (isFloat(m8.group(9)))
							numGLA21yChange = Double.parseDouble(m8.group(9));
						else
							numGLA21yChange = Double.NaN;

						System.out.println(numGLA2yChange + ","
								+ numGLA5yChange + "," + numGLA21yChange);
					}

					// Transit
					m9.reset(strLine);
					if (m9.find())
					{
						numTransitWTD = Integer.parseInt(m9.group(1));
						numTransitWTDPrevYear = Integer.parseInt(m9.group(2));

						numTransit28d = Integer.parseInt(m9.group(3));
						numTransit28dPrevYear = Integer.parseInt(m9.group(4));

						numTransitYTD = Integer.parseInt(m9.group(5));
						numTransitYTDPrevYear = Integer.parseInt(m9.group(6));

						System.out.println(numTransitWTD + ","
								+ numTransitWTDPrevYear + "," + numTransit28d
								+ "," + numTransit28dPrevYear + ","
								+ numTransitYTD + "," + numTransitYTDPrevYear);

						if (isFloat(m9.group(7)))
							numTransit2yChange = Double
									.parseDouble(m9.group(7));
						else
							numTransit2yChange = Double.NaN;

						if (isFloat(m9.group(8)))
							numTransit5yChange = Double
									.parseDouble(m9.group(8));
						else
							numTransit5yChange = Double.NaN;

						if (isFloat(m9.group(9)))
							numTransit21yChange = Double.parseDouble(m9
									.group(9));
						else
							numTransit21yChange = Double.NaN;

						System.out.println(numTransit2yChange + ","
								+ numTransit5yChange + ","
								+ numTransit21yChange);
					}

					// Housing
					m10.reset(strLine);
					if (m10.find())
					{
						numHousingWTD = Integer.parseInt(m10.group(1));
						numHousingWTDPrevYear = Integer.parseInt(m10.group(2));

						numHousing28d = Integer.parseInt(m10.group(3));
						numHousing28dPrevYear = Integer.parseInt(m10.group(4));

						numHousingYTD = Integer.parseInt(m10.group(5));
						numHousingYTDPrevYear = Integer.parseInt(m10.group(6));

						System.out.println(numHousingWTD + ","
								+ numHousingWTDPrevYear + "," + numHousing28d
								+ "," + numHousing28dPrevYear + ","
								+ numHousingYTD + "," + numHousingYTDPrevYear);

						if (isFloat(m10.group(7)))
							numHousing2yChange = Double.parseDouble(m10
									.group(7));
						else
							numHousing2yChange = Double.NaN;

						if (isFloat(m10.group(8)))
							numHousing5yChange = Double.parseDouble(m10
									.group(8));
						else
							numHousing5yChange = Double.NaN;

						if (isFloat(m10.group(9)))
							numHousing21yChange = Double.parseDouble(m9
									.group(9));
						else
							numHousing21yChange = Double.NaN;

						System.out.println(numHousing2yChange + ","
								+ numHousing5yChange + ","
								+ numHousing21yChange);
					}

					// Petit Larceny
					m11.reset(strLine);
					if (m11.find())
					{
						numPetitLarceniesWTD = Integer.parseInt(m11.group(1));
						numPetitLarceniesWTDPrevYear = Integer.parseInt(m11
								.group(2));

						numPetitLarcenies28d = Integer.parseInt(m11.group(3));
						numPetitLarcenies28dPrevYear = Integer.parseInt(m11
								.group(4));

						numPetitLarceniesYTD = Integer.parseInt(m11.group(5));
						numPetitLarceniesYTDPrevYear = Integer.parseInt(m11
								.group(6));

						System.out.println(numPetitLarceniesWTD + ","
								+ numPetitLarceniesWTDPrevYear + ","
								+ numPetitLarcenies28d + ","
								+ numPetitLarcenies28dPrevYear + ","
								+ numPetitLarceniesYTD + ","
								+ numPetitLarceniesYTDPrevYear);

						if (isFloat(m11.group(7)))
							numPetitLarcenies2yChange = Double.parseDouble(m11
									.group(7));
						else
							numPetitLarcenies2yChange = Double.NaN;

						if (isFloat(m11.group(8)))
							numPetitLarcenies5yChange = Double.parseDouble(m11
									.group(8));
						else
							numPetitLarcenies5yChange = Double.NaN;

						if (isFloat(m11.group(9)))
							numPetitLarcenies21yChange = Double.parseDouble(m9
									.group(9));
						else
							numPetitLarcenies21yChange = Double.NaN;

						System.out.println(numPetitLarcenies2yChange + ","
								+ numPetitLarcenies5yChange + ","
								+ numPetitLarcenies21yChange);
					}

					// Misd Assault
					m12.reset(strLine);
					if (m12.find())
					{
						numMisdAssaultsWTD = Integer.parseInt(m12.group(1));
						numMisdAssaultsWTDPrevYear = Integer.parseInt(m12
								.group(2));

						numMisdAssaults28d = Integer.parseInt(m12.group(3));
						numMisdAssaults28dPrevYear = Integer.parseInt(m12
								.group(4));

						numMisdAssaultsYTD = Integer.parseInt(m12.group(5));
						numMisdAssaultsYTDPrevYear = Integer.parseInt(m12
								.group(6));

						System.out.println(numMisdAssaultsWTD + ","
								+ numMisdAssaultsWTDPrevYear + ","
								+ numMisdAssaults28d + ","
								+ numMisdAssaults28dPrevYear + ","
								+ numMisdAssaultsYTD + ","
								+ numMisdAssaultsYTDPrevYear);

						if (isFloat(m12.group(7)))
							numMisdAssaults2yChange = Double.parseDouble(m12
									.group(7));
						else
							numMisdAssaults2yChange = Double.NaN;

						if (isFloat(m12.group(8)))
							numMisdAssaults5yChange = Double.parseDouble(m12
									.group(8));
						else
							numMisdAssaults5yChange = Double.NaN;

						if (isFloat(m12.group(9)))
							numMisdAssaults21yChange = Double.parseDouble(m9
									.group(9));
						else
							numMisdAssaults21yChange = Double.NaN;

						System.out.println(numMisdAssaults2yChange + ","
								+ numMisdAssaults5yChange + ","
								+ numMisdAssaults21yChange);
					}

					// Misd Sex Crimes
					m13.reset(strLine);
					if (m13.find())
					{
						numMisdSexCrimesWTD = Integer.parseInt(m13.group(1));
						numMisdSexCrimesWTDPrevYear = Integer.parseInt(m13
								.group(2));

						numMisdSexCrimes28d = Integer.parseInt(m13.group(3));
						numMisdSexCrimes28dPrevYear = Integer.parseInt(m13
								.group(4));

						numMisdSexCrimesYTD = Integer.parseInt(m13.group(5));
						numMisdSexCrimesYTDPrevYear = Integer.parseInt(m13
								.group(6));

						System.out.println(numMisdSexCrimesWTD + ","
								+ numMisdSexCrimesWTDPrevYear + ","
								+ numMisdSexCrimes28d + ","
								+ numMisdSexCrimes28dPrevYear + ","
								+ numMisdSexCrimesYTD + ","
								+ numMisdSexCrimesYTDPrevYear);

						if (isFloat(m13.group(7)))
							numMisdSexCrimes2yChange = Double.parseDouble(m13
									.group(7));
						else
							numMisdSexCrimes2yChange = Double.NaN;

						if (isFloat(m13.group(8)))
							numMisdSexCrimes5yChange = Double.parseDouble(m13
									.group(8));
						else
							numMisdSexCrimes5yChange = Double.NaN;

						if (isFloat(m13.group(9)))
							numMisdSexCrimes21yChange = Double.parseDouble(m9
									.group(9));
						else
							numMisdSexCrimes21yChange = Double.NaN;

						System.out.println(numMisdSexCrimes2yChange + ","
								+ numMisdSexCrimes5yChange + ","
								+ numMisdSexCrimes21yChange);
					}

					// create the crime report object

					crimeReports
							.add(

							new CrimeReport(
									startCal,
									endCal,
									isPrecinctReport,
									precinct,
									borough,

									numMurdersWTD,
									numRapesWTD,
									numRobberiesWTD,
									numFelAssaultsWTD,
									numBurglariesWTD,
									numGrandLarceniesWTD,
									numGLAWTD,
									numTransitWTD,
									numHousingWTD,
									numPetitLarceniesWTD,
									numMisdAssaultsWTD,
									numMisdSexCrimesWTD,
									// numShootingVicWTD, numShootingIncWTD,
									numMurdersWTDPrevYear,
									numRapesWTDPrevYear,
									numRobberiesWTDPrevYear,
									numFelAssaultsWTDPrevYear,
									numBurglariesWTDPrevYear,
									numGrandLarceniesWTDPrevYear,
									numGLAWTDPrevYear,
									numTransitWTDPrevYear,
									numHousingWTDPrevYear,
									numPetitLarceniesWTDPrevYear,
									numMisdAssaultsWTDPrevYear,
									numMisdSexCrimesWTDPrevYear,
									// numShootingVicWTDPrevYear,
									// numShootingIncWTDPrevYear,
									numMurders28d,
									numRapes28d,
									numRobberies28d,
									numFelAssaults28d,
									numBurglaries28d,
									numGrandLarcenies28d,
									numGLA28d,
									numTransit28d,
									numHousing28d,
									numPetitLarcenies28d,
									numMisdAssaults28d,
									numMisdSexCrimes28d,
									// numShootingVic28d, numShootingInc28d,
									numMurders28dPrevYear,
									numRapes28dPrevYear,
									numRobberies28dPrevYear,
									numFelAssaults28dPrevYear,
									numBurglaries28dPrevYear,
									numGrandLarcenies28dPrevYear,
									numGLA28dPrevYear,
									numTransit28dPrevYear,
									numHousing28dPrevYear,
									numPetitLarcenies28dPrevYear,
									numMisdAssaults28dPrevYear,
									numMisdSexCrimes28dPrevYear,
									// numShootingVic28dPrevYear,
									// numShootingInc28dPrevYear,
									numMurdersYTD,
									numRapesYTD,
									numRobberiesYTD,
									numFelAssaultsYTD,
									numBurglariesYTD,
									numGrandLarceniesYTD,
									numGLAYTD,
									numTransitYTD,
									numHousingYTD,
									numPetitLarceniesYTD,
									numMisdAssaultsYTD,
									numMisdSexCrimesYTD,
									// numShootingVicYTD, numShootingIncYTD,
									numMurdersYTDPrevYear,
									numRapesYTDPrevYear,
									numRobberiesYTDPrevYear,
									numFelAssaultsYTDPrevYear,
									numBurglariesYTDPrevYear,
									numGrandLarceniesYTDPrevYear,
									numGLAYTDPrevYear,
									numTransitYTDPrevYear,
									numHousingYTDPrevYear,
									numPetitLarceniesYTDPrevYear,
									numMisdAssaultsYTDPrevYear,
									numMisdSexCrimesYTDPrevYear,
									// numShootingVicYTDPrevYear,
									// numShootingIncYTDPrevYear,
									numMurders2yChange, numMurders5yChange,
									numMurders21yChange, numRapes2yChange,
									numRapes5yChange, numRapes21yChange,
									numRobberies2yChange, numRobberies5yChange,
									numRobberies21yChange,
									numFelAssaults2yChange,
									numFelAssaults5yChange,
									numFelAssaults21yChange,
									numBurglaries2yChange,
									numBurglaries5yChange,
									numBurglaries21yChange,
									numGrandLarcenies2yChange,
									numGrandLarcenies5yChange,
									numGrandLarcenies21yChange, numGLA2yChange,
									numGLA5yChange, numGLA21yChange,
									numTransit2yChange, numTransit5yChange,
									numTransit21yChange, numHousing2yChange,
									numHousing5yChange, numHousing21yChange,
									numPetitLarcenies2yChange,
									numPetitLarcenies5yChange,
									numPetitLarcenies21yChange,
									numMisdAssaults2yChange,
									numMisdAssaults5yChange,
									numMisdAssaults21yChange,
									numMisdSexCrimes2yChange,
									numMisdSexCrimes5yChange,
									numMisdSexCrimes21yChange)

							);

					/*
					 * // Shooting Vic m14.reset(strLine); if (m14.find()) {
					 * numShootingVicWTD = Integer.parseInt(m14.group(1));
					 * numShootingVicWTDPrevYear = Integer.parseInt(m14
					 * .group(2));
					 * 
					 * numShootingVic28d = Integer.parseInt(m14.group(3));
					 * numShootingVic28dPrevYear = Integer.parseInt(m14
					 * .group(4));
					 * 
					 * numShootingVicYTD = Integer.parseInt(m14.group(5));
					 * numShootingVicYTDPrevYear = Integer.parseInt(m14
					 * .group(6));
					 * 
					 * System.out.println(numShootingVicWTD + "," +
					 * numShootingVicWTDPrevYear + "," + numShootingVic28d + ","
					 * + numShootingVic28dPrevYear + "," + numShootingVicYTD +
					 * "," + numShootingVicYTDPrevYear);
					 * 
					 * if (isFloat(m14.group(7))) numShootingVic2yChange =
					 * Double.parseDouble(m14 .group(7)); else
					 * numShootingVic2yChange = Double.NaN;
					 * 
					 * if (isFloat(m14.group(8))) numShootingVic5yChange =
					 * Double.parseDouble(m14 .group(8)); else
					 * numShootingVic5yChange = Double.NaN;
					 * 
					 * if (isFloat(m14.group(9))) numShootingVic21yChange =
					 * Double.parseDouble(m9 .group(9)); else
					 * numShootingVic21yChange = Double.NaN;
					 * 
					 * System.out.println(numShootingVic2yChange + "," +
					 * numShootingVic5yChange + "," + numShootingVic21yChange);
					 * }
					 * 
					 * // Shooting Inc m15.reset(strLine); if (m15.find()) {
					 * numShootingIncWTD = Integer.parseInt(m15.group(1));
					 * numShootingIncWTDPrevYear = Integer.parseInt(m15
					 * .group(2));
					 * 
					 * numShootingInc28d = Integer.parseInt(m15.group(3));
					 * numShootingInc28dPrevYear = Integer.parseInt(m15
					 * .group(4));
					 * 
					 * numShootingIncYTD = Integer.parseInt(m15.group(5));
					 * numShootingIncYTDPrevYear = Integer.parseInt(m15
					 * .group(6));
					 * 
					 * System.out.println(numShootingIncWTD + "," +
					 * numShootingIncWTDPrevYear + "," + numShootingInc28d + ","
					 * + numShootingInc28dPrevYear + "," + numShootingIncYTD +
					 * "," + numShootingIncYTDPrevYear);
					 * 
					 * if (isFloat(m15.group(7))) numShootingInc2yChange =
					 * Double.parseDouble(m15 .group(7)); else
					 * numShootingInc2yChange = Double.NaN;
					 * 
					 * if (isFloat(m15.group(8))) numShootingInc5yChange =
					 * Double.parseDouble(m15 .group(8)); else
					 * numShootingInc5yChange = Double.NaN;
					 * 
					 * if (isFloat(m15.group(9))) numShootingInc21yChange =
					 * Double.parseDouble(m9 .group(9)); else
					 * numShootingInc21yChange = Double.NaN;
					 * 
					 * System.out.println(numShootingInc2yChange + "," +
					 * numShootingInc5yChange + "," + numShootingInc21yChange);
					 * }
					 */

				}
				// Close the input stream
				dis.close();
			}
			catch (FileNotFoundException fException)
			{
				System.err.println("Error: The source file " + args[index]
						+ " does not exist.");
				System.exit(1);
			}
			catch (IOException ioException)
			{
				System.err.println("Error: The source file " + args[index]
						+ "could not be read.");
				System.exit(1);
			}
		}

		// iterate through the crime reports

	}
}
