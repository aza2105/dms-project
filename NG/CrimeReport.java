import java.util.Calendar;

public class CrimeReport
{

	boolean isPrecinctReport;
	boolean isBoroughReport;
	boolean isCityReport;

	/*
	 * precinct set to -1 for borough-level and citywide report otherwise set to
	 * actual precinct
	 */
	int precinct;

	/*
	 * For Queens and Brooklyn, we consider the north and south boroughs
	 * separately
	 * 
	 * borough is null for citywide report but has a valid value for the
	 * borough-level and precinct level reports
	 */
	String borough;

	Calendar startCal;
	Calendar endCal;

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
	int numPetitLarceniesWTD;
	int numMisdAssaultsWTD;
	int numMisdSexCrimesWTD;
	// int numShootingVicWTD;
	// int numShootingIncWTD;

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
	int numPetitLarceniesWTDPrevYear;
	int numMisdAssaultsWTDPrevYear;
	int numMisdSexCrimesWTDPrevYear;
	// int numShootingVicWTDPrevYear;
	// int numShootingIncWTDPrevYear;

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
	int numPetitLarcenies28d;
	int numMisdAssaults28d;
	int numMisdSexCrimes28d;
	// int numShootingVic28d;
	// int numShootingInc28d;

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
	int numPetitLarcenies28dPrevYear;
	int numMisdAssaults28dPrevYear;
	int numMisdSexCrimes28dPrevYear;
	// int numShootingVic28dPrevYear;
	// int numShootingInc28dPrevYear;

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
	int numPetitLarceniesYTD;
	int numMisdAssaultsYTD;
	int numMisdSexCrimesYTD;
	// int numShootingVicYTD;
	// int numShootingIncYTD;

	// year to date stats for previous year
	int numMurdersYTDPrevYear;
	int numRapesYTDPrevYear;
	int numRobberiesYTDPrevYear;
	int numFelAssaultsYTDPrevYear;
	int numBurglariesYTDPrevYear;
	int numGrandLarceniesYTDPrevYear;
	int numGLAYTDPrevYear;
	int numTransitYTDPrevYear;
	int numHousingYTDPrevYear;
	int numPetitLarceniesYTDPrevYear;
	int numMisdAssaultsYTDPrevYear;
	int numMisdSexCrimesYTDPrevYear;
	// int numShootingVicYTDPrevYear;
	// int numShootingIncYTDPrevYear;

	// murder percentage changes
	double numMurdersWTD1yChange;
	double numMurders28d1yChange;
	double numMurders1yChange;
	double numMurders2yChange;
	double numMurders5yChange;
	double numMurders21yChange;

	// rape percentage changes
	double numRapesWTD1yChange;
	double numRapes28d1yChange;
	double numRapes1yChange;
	double numRapes2yChange;
	double numRapes5yChange;
	double numRapes21yChange;

	// robberies percentage changes
	double numRobberiesWTD1yChange;
	double numRobberies28d1yChange;
	double numRobberies1yChange;
	double numRobberies2yChange;
	double numRobberies5yChange;
	double numRobberies21yChange;

	// felony assault percentage changes
	double numFelAssaultsWTD1yChange;
	double numFelAssaults28d1yChange;
	double numFelAssaults1yChange;
	double numFelAssaults2yChange;
	double numFelAssaults5yChange;
	double numFelAssaults21yChange;

	// 2-year, 5-year and 21-year percentage changes
	double numBurglariesWTD1yChange;
	double numBurglaries28d1yChange;
	double numBurglaries1yChange;
	double numBurglaries2yChange;
	double numBurglaries5yChange;
	double numBurglaries21yChange;

	// Gr Larceny percentage changes
	double numGrandLarceniesWTD1yChange;
	double numGrandLarcenies28d1yChange;
	double numGrandLarcenies1yChange;
	double numGrandLarcenies2yChange;
	double numGrandLarcenies5yChange;
	double numGrandLarcenies21yChange;

	// Auto Gr Larceny percentage changes
	double numGLAWTD1yChange;
	double numGLA28d1yChange;
	double numGLA1yChange;
	double numGLA2yChange;
	double numGLA5yChange;
	double numGLA21yChange;

	// Transit percentage changes
	double numTransitWTD1yChange;
	double numTransit28d1yChange;
	double numTransit1yChange;
	double numTransit2yChange;
	double numTransit5yChange;
	double numTransit21yChange;

	// 2-year, 5-year and 21-year percentage changes
	double numHousingWTD1yChange;
	double numHousing28d1yChange;
	double numHousing1yChange;
	double numHousing2yChange;
	double numHousing5yChange;
	double numHousing21yChange;

	// 2-year, 5-year and 21-year percentage changes
	double numPetitLarceniesWTD1yChange;
	double numPetitLarcenies28d1yChange;
	double numPetitLarcenies1yChange;
	double numPetitLarcenies2yChange;
	double numPetitLarcenies5yChange;
	double numPetitLarcenies21yChange;

	// 2-year, 5-year and 21-year percentage changes
	double numMisdAssaultsWTD1yChange;
	double numMisdAssaults28d1yChange;
	double numMisdAssaults1yChange;
	double numMisdAssaults2yChange;
	double numMisdAssaults5yChange;
	double numMisdAssaults21yChange;

	// 2-year, 5-year and 21-year percentage changes
	double numMisdSexCrimesWTD1yChange;
	double numMisdSexCrimes28d1yChange;
	double numMisdSexCrimes1yChange;
	double numMisdSexCrimes2yChange;
	double numMisdSexCrimes5yChange;
	double numMisdSexCrimes21yChange;

	public CrimeReport(boolean isPrecinctReport, boolean isBoroughReport,
			boolean isCityReport, int precinct, String borough,
			Calendar startCal, Calendar endCal, int numMurdersWTD,
			int numRapesWTD, int numRobberiesWTD, int numFelAssaultsWTD,
			int numBurglariesWTD, int numGrandLarceniesWTD, int numGLAWTD,
			int numTransitWTD, int numHousingWTD, int numPetitLarceniesWTD,
			int numMisdAssaultsWTD, int numMisdSexCrimesWTD,
			int numMurdersWTDPrevYear, int numRapesWTDPrevYear,
			int numRobberiesWTDPrevYear, int numFelAssaultsWTDPrevYear,
			int numBurglariesWTDPrevYear, int numGrandLarceniesWTDPrevYear,
			int numGLAWTDPrevYear, int numTransitWTDPrevYear,
			int numHousingWTDPrevYear, int numPetitLarceniesWTDPrevYear,
			int numMisdAssaultsWTDPrevYear, int numMisdSexCrimesWTDPrevYear,
			int numMurders28d, int numRapes28d, int numRobberies28d,
			int numFelAssaults28d, int numBurglaries28d,
			int numGrandLarcenies28d, int numGLA28d, int numTransit28d,
			int numHousing28d, int numPetitLarcenies28d,
			int numMisdAssaults28d, int numMisdSexCrimes28d,
			int numMurders28dPrevYear, int numRapes28dPrevYear,
			int numRobberies28dPrevYear, int numFelAssaults28dPrevYear,
			int numBurglaries28dPrevYear, int numGrandLarcenies28dPrevYear,
			int numGLA28dPrevYear, int numTransit28dPrevYear,
			int numHousing28dPrevYear, int numPetitLarcenies28dPrevYear,
			int numMisdAssaults28dPrevYear, int numMisdSexCrimes28dPrevYear,
			int numMurdersYTD, int numRapesYTD, int numRobberiesYTD,
			int numFelAssaultsYTD, int numBurglariesYTD,
			int numGrandLarceniesYTD, int numGLAYTD, int numTransitYTD,
			int numHousingYTD, int numPetitLarceniesYTD,
			int numMisdAssaultsYTD, int numMisdSexCrimesYTD,
			int numMurdersYTDPrevYear, int numRapesYTDPrevYear,
			int numRobberiesYTDPrevYear, int numFelAssaultsYTDPrevYear,
			int numBurglariesYTDPrevYear, int numGrandLarceniesYTDPrevYear,
			int numGLAYTDPrevYear, int numTransitYTDPrevYear,
			int numHousingYTDPrevYear, int numPetitLarceniesYTDPrevYear,
			int numMisdAssaultsYTDPrevYear, int numMisdSexCrimesYTDPrevYear,
			double numMurdersWTD1yChange, double numMurders28d1yChange,
			double numMurders1yChange, double numMurders2yChange,
			double numMurders5yChange, double numMurders21yChange,
			double numRapesWTD1yChange, double numRapes28d1yChange,
			double numRapes1yChange, double numRapes2yChange,
			double numRapes5yChange, double numRapes21yChange,
			double numRobberiesWTD1yChange, double numRobberies28d1yChange,
			double numRobberies1yChange, double numRobberies2yChange,
			double numRobberies5yChange, double numRobberies21yChange,
			double numFelAssaultsWTD1yChange, double numFelAssaults28d1yChange,
			double numFelAssaults1yChange, double numFelAssaults2yChange,
			double numFelAssaults5yChange, double numFelAssaults21yChange,
			double numBurglariesWTD1yChange, double numBurglaries28d1yChange,
			double numBurglaries1yChange, double numBurglaries2yChange,
			double numBurglaries5yChange, double numBurglaries21yChange,
			double numGrandLarceniesWTD1yChange,
			double numGrandLarcenies28d1yChange,
			double numGrandLarcenies1yChange, double numGrandLarcenies2yChange,
			double numGrandLarcenies5yChange,
			double numGrandLarcenies21yChange, double numGLAWTD1yChange,
			double numGLA28d1yChange, double numGLA1yChange,
			double numGLA2yChange, double numGLA5yChange,
			double numGLA21yChange, double numTransitWTD1yChange,
			double numTransit28d1yChange, double numTransit1yChange,
			double numTransit2yChange, double numTransit5yChange,
			double numTransit21yChange, double numHousingWTD1yChange,
			double numHousing28d1yChange, double numHousing1yChange,
			double numHousing2yChange, double numHousing5yChange,
			double numHousing21yChange, double numPetitLarceniesWTD1yChange,
			double numPetitLarcenies28d1yChange,
			double numPetitLarcenies1yChange, double numPetitLarcenies2yChange,
			double numPetitLarcenies5yChange,
			double numPetitLarcenies21yChange,
			double numMisdAssaultsWTD1yChange,
			double numMisdAssaults28d1yChange, double numMisdAssaults1yChange,
			double numMisdAssaults2yChange, double numMisdAssaults5yChange,
			double numMisdAssaults21yChange,
			double numMisdSexCrimesWTD1yChange,
			double numMisdSexCrimes28d1yChange,
			double numMisdSexCrimes1yChange, double numMisdSexCrimes2yChange,
			double numMisdSexCrimes5yChange, double numMisdSexCrimes21yChange)
	{
		super();
		this.isPrecinctReport = isPrecinctReport;
		this.isBoroughReport = isBoroughReport;
		this.isCityReport = isCityReport;
		this.precinct = precinct;
		this.borough = borough;
		this.startCal = startCal;
		this.endCal = endCal;
		this.numMurdersWTD = numMurdersWTD;
		this.numRapesWTD = numRapesWTD;
		this.numRobberiesWTD = numRobberiesWTD;
		this.numFelAssaultsWTD = numFelAssaultsWTD;
		this.numBurglariesWTD = numBurglariesWTD;
		this.numGrandLarceniesWTD = numGrandLarceniesWTD;
		this.numGLAWTD = numGLAWTD;
		this.numTransitWTD = numTransitWTD;
		this.numHousingWTD = numHousingWTD;
		this.numPetitLarceniesWTD = numPetitLarceniesWTD;
		this.numMisdAssaultsWTD = numMisdAssaultsWTD;
		this.numMisdSexCrimesWTD = numMisdSexCrimesWTD;
		this.numMurdersWTDPrevYear = numMurdersWTDPrevYear;
		this.numRapesWTDPrevYear = numRapesWTDPrevYear;
		this.numRobberiesWTDPrevYear = numRobberiesWTDPrevYear;
		this.numFelAssaultsWTDPrevYear = numFelAssaultsWTDPrevYear;
		this.numBurglariesWTDPrevYear = numBurglariesWTDPrevYear;
		this.numGrandLarceniesWTDPrevYear = numGrandLarceniesWTDPrevYear;
		this.numGLAWTDPrevYear = numGLAWTDPrevYear;
		this.numTransitWTDPrevYear = numTransitWTDPrevYear;
		this.numHousingWTDPrevYear = numHousingWTDPrevYear;
		this.numPetitLarceniesWTDPrevYear = numPetitLarceniesWTDPrevYear;
		this.numMisdAssaultsWTDPrevYear = numMisdAssaultsWTDPrevYear;
		this.numMisdSexCrimesWTDPrevYear = numMisdSexCrimesWTDPrevYear;
		this.numMurders28d = numMurders28d;
		this.numRapes28d = numRapes28d;
		this.numRobberies28d = numRobberies28d;
		this.numFelAssaults28d = numFelAssaults28d;
		this.numBurglaries28d = numBurglaries28d;
		this.numGrandLarcenies28d = numGrandLarcenies28d;
		this.numGLA28d = numGLA28d;
		this.numTransit28d = numTransit28d;
		this.numHousing28d = numHousing28d;
		this.numPetitLarcenies28d = numPetitLarcenies28d;
		this.numMisdAssaults28d = numMisdAssaults28d;
		this.numMisdSexCrimes28d = numMisdSexCrimes28d;
		this.numMurders28dPrevYear = numMurders28dPrevYear;
		this.numRapes28dPrevYear = numRapes28dPrevYear;
		this.numRobberies28dPrevYear = numRobberies28dPrevYear;
		this.numFelAssaults28dPrevYear = numFelAssaults28dPrevYear;
		this.numBurglaries28dPrevYear = numBurglaries28dPrevYear;
		this.numGrandLarcenies28dPrevYear = numGrandLarcenies28dPrevYear;
		this.numGLA28dPrevYear = numGLA28dPrevYear;
		this.numTransit28dPrevYear = numTransit28dPrevYear;
		this.numHousing28dPrevYear = numHousing28dPrevYear;
		this.numPetitLarcenies28dPrevYear = numPetitLarcenies28dPrevYear;
		this.numMisdAssaults28dPrevYear = numMisdAssaults28dPrevYear;
		this.numMisdSexCrimes28dPrevYear = numMisdSexCrimes28dPrevYear;
		this.numMurdersYTD = numMurdersYTD;
		this.numRapesYTD = numRapesYTD;
		this.numRobberiesYTD = numRobberiesYTD;
		this.numFelAssaultsYTD = numFelAssaultsYTD;
		this.numBurglariesYTD = numBurglariesYTD;
		this.numGrandLarceniesYTD = numGrandLarceniesYTD;
		this.numGLAYTD = numGLAYTD;
		this.numTransitYTD = numTransitYTD;
		this.numHousingYTD = numHousingYTD;
		this.numPetitLarceniesYTD = numPetitLarceniesYTD;
		this.numMisdAssaultsYTD = numMisdAssaultsYTD;
		this.numMisdSexCrimesYTD = numMisdSexCrimesYTD;
		this.numMurdersYTDPrevYear = numMurdersYTDPrevYear;
		this.numRapesYTDPrevYear = numRapesYTDPrevYear;
		this.numRobberiesYTDPrevYear = numRobberiesYTDPrevYear;
		this.numFelAssaultsYTDPrevYear = numFelAssaultsYTDPrevYear;
		this.numBurglariesYTDPrevYear = numBurglariesYTDPrevYear;
		this.numGrandLarceniesYTDPrevYear = numGrandLarceniesYTDPrevYear;
		this.numGLAYTDPrevYear = numGLAYTDPrevYear;
		this.numTransitYTDPrevYear = numTransitYTDPrevYear;
		this.numHousingYTDPrevYear = numHousingYTDPrevYear;
		this.numPetitLarceniesYTDPrevYear = numPetitLarceniesYTDPrevYear;
		this.numMisdAssaultsYTDPrevYear = numMisdAssaultsYTDPrevYear;
		this.numMisdSexCrimesYTDPrevYear = numMisdSexCrimesYTDPrevYear;
		this.numMurdersWTD1yChange = numMurdersWTD1yChange;
		this.numMurders28d1yChange = numMurders28d1yChange;
		this.numMurders1yChange = numMurders1yChange;
		this.numMurders2yChange = numMurders2yChange;
		this.numMurders5yChange = numMurders5yChange;
		this.numMurders21yChange = numMurders21yChange;
		this.numRapesWTD1yChange = numRapesWTD1yChange;
		this.numRapes28d1yChange = numRapes28d1yChange;
		this.numRapes1yChange = numRapes1yChange;
		this.numRapes2yChange = numRapes2yChange;
		this.numRapes5yChange = numRapes5yChange;
		this.numRapes21yChange = numRapes21yChange;
		this.numRobberiesWTD1yChange = numRobberiesWTD1yChange;
		this.numRobberies28d1yChange = numRobberies28d1yChange;
		this.numRobberies1yChange = numRobberies1yChange;
		this.numRobberies2yChange = numRobberies2yChange;
		this.numRobberies5yChange = numRobberies5yChange;
		this.numRobberies21yChange = numRobberies21yChange;
		this.numFelAssaultsWTD1yChange = numFelAssaultsWTD1yChange;
		this.numFelAssaults28d1yChange = numFelAssaults28d1yChange;
		this.numFelAssaults1yChange = numFelAssaults1yChange;
		this.numFelAssaults2yChange = numFelAssaults2yChange;
		this.numFelAssaults5yChange = numFelAssaults5yChange;
		this.numFelAssaults21yChange = numFelAssaults21yChange;
		this.numBurglariesWTD1yChange = numBurglariesWTD1yChange;
		this.numBurglaries28d1yChange = numBurglaries28d1yChange;
		this.numBurglaries1yChange = numBurglaries1yChange;
		this.numBurglaries2yChange = numBurglaries2yChange;
		this.numBurglaries5yChange = numBurglaries5yChange;
		this.numBurglaries21yChange = numBurglaries21yChange;
		this.numGrandLarceniesWTD1yChange = numGrandLarceniesWTD1yChange;
		this.numGrandLarcenies28d1yChange = numGrandLarcenies28d1yChange;
		this.numGrandLarcenies1yChange = numGrandLarcenies1yChange;
		this.numGrandLarcenies2yChange = numGrandLarcenies2yChange;
		this.numGrandLarcenies5yChange = numGrandLarcenies5yChange;
		this.numGrandLarcenies21yChange = numGrandLarcenies21yChange;
		this.numGLAWTD1yChange = numGLAWTD1yChange;
		this.numGLA28d1yChange = numGLA28d1yChange;
		this.numGLA1yChange = numGLA1yChange;
		this.numGLA2yChange = numGLA2yChange;
		this.numGLA5yChange = numGLA5yChange;
		this.numGLA21yChange = numGLA21yChange;
		this.numTransitWTD1yChange = numTransitWTD1yChange;
		this.numTransit28d1yChange = numTransit28d1yChange;
		this.numTransit1yChange = numTransit1yChange;
		this.numTransit2yChange = numTransit2yChange;
		this.numTransit5yChange = numTransit5yChange;
		this.numTransit21yChange = numTransit21yChange;
		this.numHousingWTD1yChange = numHousingWTD1yChange;
		this.numHousing28d1yChange = numHousing28d1yChange;
		this.numHousing1yChange = numHousing1yChange;
		this.numHousing2yChange = numHousing2yChange;
		this.numHousing5yChange = numHousing5yChange;
		this.numHousing21yChange = numHousing21yChange;
		this.numPetitLarceniesWTD1yChange = numPetitLarceniesWTD1yChange;
		this.numPetitLarcenies28d1yChange = numPetitLarcenies28d1yChange;
		this.numPetitLarcenies1yChange = numPetitLarcenies1yChange;
		this.numPetitLarcenies2yChange = numPetitLarcenies2yChange;
		this.numPetitLarcenies5yChange = numPetitLarcenies5yChange;
		this.numPetitLarcenies21yChange = numPetitLarcenies21yChange;
		this.numMisdAssaultsWTD1yChange = numMisdAssaultsWTD1yChange;
		this.numMisdAssaults28d1yChange = numMisdAssaults28d1yChange;
		this.numMisdAssaults1yChange = numMisdAssaults1yChange;
		this.numMisdAssaults2yChange = numMisdAssaults2yChange;
		this.numMisdAssaults5yChange = numMisdAssaults5yChange;
		this.numMisdAssaults21yChange = numMisdAssaults21yChange;
		this.numMisdSexCrimesWTD1yChange = numMisdSexCrimesWTD1yChange;
		this.numMisdSexCrimes28d1yChange = numMisdSexCrimes28d1yChange;
		this.numMisdSexCrimes1yChange = numMisdSexCrimes1yChange;
		this.numMisdSexCrimes2yChange = numMisdSexCrimes2yChange;
		this.numMisdSexCrimes5yChange = numMisdSexCrimes5yChange;
		this.numMisdSexCrimes21yChange = numMisdSexCrimes21yChange;
	}

}
