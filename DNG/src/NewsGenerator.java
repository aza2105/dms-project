import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class NewsGenerator
{
	static final int MAX_SIZE = 20;

	// takes a filename, extracts the relevant information from that file and
	// returns a crime report
	public static CrimeReport generateCrimeReport(String sourceFileName)
	{
		// return new CrimeReport();

		/*
		 * I've adapted the following code on how to read a file in Java line by
		 * line from http://www.roseindia.net/java/beginners/java-read
		 * -file-line-by-line.shtml
		 */
		// read the CSV file for this crime report
		BufferedReader br;
		DataInputStream dis;
		FileInputStream fis;
		CrimeReport cr;

		try
		{
			fis = new FileInputStream(sourceFileName);
			dis = new DataInputStream(fis);
			br = new BufferedReader(new InputStreamReader(dis));

			boolean isPrecinctReport;

			// precinct set to -1 for borough-level report
			// otherwise set to actual precinct
			int precinct;

			// For Queens and Brooklyn, we consider the north and south boroughs
			// separately
			String borough;

			// Date
			int currentYear;
			Date startDate;
			Date endDate;

			// week to date stats for current year
			int numMurdersWTD;
			int numRapesWTD;
			int numRobberiesWTD;
			int numFelAssaultsWTD;
			int numBurglariesWTD;
			int numGrandLarceniesWTD;
			int numGLAWTD;
			int numTransitWTD;
			int numHousingWTD;
			int numPetitLarceyWTD;
			int numMisdAssaultWTD;
			int numMisdSexCrimesWTD;
			int numShootingVicWTD;
			int numShootingIncWTD;

			// week to date stats for previous year
			int numMurdersWTDPrevYear;
			int numRapesWTDPrevYear;
			int numRobberiesWTDPrevYear;
			int numFelAssaultsWTDPrevYear;
			int numBurglariesWTDPrevYear;
			int numGrandLarceniesWTDPrevYear;
			int numGLAWTDPrevYear;
			int numTransitWTDPrevYear;
			int numHousingWTDPrevYear;
			int numPetitLarceyWTDPrevYear;
			int numMisdAssaultWTDPrevYear;
			int numMisdSexCrimesWTDPrevYear;
			int numShootingVicWTDPrevYear;
			int numShootingIncWTDPrevYear;

			// 28 days stats for current year
			int numMurders28d;
			int numRapes28d;
			int numRobberies28d;
			int numFelAssaults28d;
			int numBurglaries28d;
			int numGrandLarcenies28d;
			int numGLA28d;
			int numTransit28d;
			int numHousing28d;
			int numPetitLarcey28d;
			int numMisdAssault28d;
			int numMisdSexCrimes28d;
			int numShootingVic28d;
			int numShootingInc28d;

			// 28 day stats for previous year
			int numMurders28dPrevYear;
			int numRapes28dPrevYear;
			int numRobberies28dPrevYear;
			int numFelAssaults28dPrevYear;
			int numBurglaries28dPrevYear;
			int numGrandLarcenies28dPrevYear;
			int numGLA28dPrevYear;
			int numTransit28dPrevYear;
			int numHousing28dPrevYear;
			int numPetitLarcey28dPrevYear;
			int numMisdAssault28dPrevYear;
			int numMisdSexCrimes28dPrevYear;
			int numShootingVic28dPrevYear;
			int numShootingInc28dPrevYear;

			// year to date stats for current year
			int numMurdersYTD;
			int numRapesYTD;
			int numRobberiesYTD;
			int numFelAssaultsYTD;
			int numBurglariesYTD;
			int numGrandLarceniesYTD;
			int numGLAYTD;
			int numTransitYTD;
			int numHousingYTD;
			int numPetitLarceyYTD;
			int numMisdAssaultYTD;
			int numMisdSexCrimesYTD;
			int numShootingVicYTD;
			int numShootingIncYTD;

			// year to date stats for current year
			int numMurdersYTDPrevYear;
			int numRapesYTDPrevYear;
			int numRobberiesYTDPrevYear;
			int numFelAssaultsYTDPrevYear;
			int numBurglariesYTDPrevYear;
			int numGrandLarceniesYTDPrevYear;
			int numGLAYTDPrevYear;
			int numTransitYTDPrevYear;
			int numHousingYTDPrevYear;
			int numPetitLarceyYTDPrevYear;
			int numMisdAssaultYTDPrevYear;
			int numMisdSexCrimesYTDPrevYear;
			int numShootingVicYTDPrevYear;
			int numShootingIncYTDPrevYear;

			// if source filename has pct.csv in its name then this
			// is a precinct report
			if (sourceFileName.matches("^.*\\d+pct\\.csv$"))
			{
				isPrecinctReport = true;
			}
			else
				isPrecinctReport = false;

			String strLine;
			// Read the source file line by line
			while ((strLine = br.readLine()) != null)
			{
				// listFromFile.insert(new LinkNode(strLine));
				if (strLine
						.matches("Report Covering the Week  3/24/2014  Through  3/30/2014"))
				{

				}

			}
			// Close the input stream
			dis.close();
		}
		catch (FileNotFoundException fException)
		{
			System.err.println("Error: The source file " + sourceFileName
					+ " does not exist.");
			System.exit(1);
		}
		catch (IOException ioException)
		{
			System.err.println("Error: The source file " + sourceFileName
					+ "could not be read.");
			System.exit(1);
		}

	}

	public static void main(String[] args)
	{
		String[] completeSentences = new String[MAX_SIZE];

		// initialize the array of completeSentence
		completeSentences[0] = "Police records indicate that crime rates in <BOROUGH> are falling. ";
		completeSentences[1] = "In the past month, a total of <NUM_CRIMES> <CRIME_TYPE> have been reported in the <BOROUGH>. ";
		completeSentences[2] = "This represents a <PERCENTAGE_CHANGE> <DROP/RISE> in the number of <CRIME_TYPE> from last year. ";
		completeSentences[3] = "Meanwhile, in the <BOROUGH>, last week witnessed ";
		completeSentences[4] = "<NUM_CRIME> <CRIME_TYPE>";

		completeSentences[0] = "In the <PRECINCT_NUMBER> precinct, <CRIME_TYPE> <FELL/ROSE> from <LAST_YEAR_NUMBER> to <CURRENT_YEAR_NUMBER>";
		completeSentences[0] = "The number of <CRIME_TYPE> has <FALLEN/RISEN> from <BYGONE_YEAR_NUMBER> in <BYGONE_YEAR>"
				+ "to <CURREN_YEAR_NUMBER> in <CURRENT_YEAR>";
		completeSentences[0] = "More good news comes from the <PRECINCT_NUMBER> precinct. ";
		completeSentences[0] = "Police records state that the number of <CRIME_TYPE> is now <PERCENTAGE> <LOWER/HIGHER> than it was in <BYGONE_YEAR>. ";
		completeSentences[0] = "There were a total of <NUM_CRIME> complaints about <CRIME_TYPE> in the week from <START_DATE> through <END_DATE>. ";

		// a single argument is provided to main
		if (args.length == 1)
		{
			/*
			 * I've adapted the following code on how to read a file in Java
			 * line by line from
			 * http://www.roseindia.net/java/beginners/java-read
			 * -file-line-by-line.shtml
			 */
			BufferedReader br;
			DataInputStream dis;
			FileInputStream fis;

			String sourceFileName = args[0];

			try
			{
				fis = new FileInputStream(sourceFileName);
				dis = new DataInputStream(fis);
				br = new BufferedReader(new InputStreamReader(dis));

				String strLine;
				// Read the source file line by line
				while ((strLine = br.readLine()) != null)
				{
					// listFromFile.insert(new LinkNode(strLine));
				}
				// Close the input stream
				dis.close();
			}
			catch (FileNotFoundException fException)
			{
				System.err.println("Error: The source file " + sourceFileName
						+ " does not exist.");
				System.exit(1);
			}
			catch (IOException ioException)
			{
				System.err.println("Error: The source file " + sourceFileName
						+ "could not be read.");
				System.exit(1);
			}
		}

	}

}
