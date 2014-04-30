import java.util.Calendar;

public class CrimeReport
{

	boolean isPrecinctReport;

	// precinct set to -1 for borough-level report
	// otherwise set to actual precinct
	int precinct;

	// For Queens and Brooklyn, we consider the north and south
	// boroughs
	// separately
	String borough;

	Calendar startCal, endCal;

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
	int numPetitLarceniesWTDPrevYear;
	int numMisdAssaultsWTDPrevYear;
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
	int numPetitLarcenies28d;
	int numMisdAssaults28d;
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
	int numPetitLarcenies28dPrevYear;
	int numMisdAssaults28dPrevYear;
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
	int numPetitLarceniesYTD;
	int numMisdAssaultsYTD;
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
	int numPetitLarceniesYTDPrevYear;
	int numMisdAssaultsYTDPrevYear;
	int numMisdSexCrimesYTDPrevYear;
	// int numShootingVicYTDPrevYear;
	// int numShootingIncYTDPrevYear;

	// 2-year, 5-year and 21-year percentage changes
	double numMurders2yChange;
	double numMurders5yChange;
	double numMurders21yChange;

	// 2-year, 5-year and 21-year percentage changes
	double numRapes2yChange;
	double numRapes5yChange;
	double numRapes21yChange;

	// 2-year, 5-year and 21-year percentage changes
	double numRobberies2yChange;
	double numRobberies5yChange;
	double numRobberies21yChange;

	// 2-year, 5-year and 21-year percentage changes
	double numFelAssaults2yChange;
	double numFelAssaults5yChange;
	double numFelAssaults21yChange;

	// 2-year, 5-year and 21-year percentage changes
	double numBurglaries2yChange;
	double numBurglaries5yChange;
	double numBurglaries21yChange;

	// 2-year, 5-year and 21-year percentage changes
	double numGrandLarcenies2yChange;
	double numGrandLarcenies5yChange;
	double numGrandLarcenies21yChange;

	// 2-year, 5-year and 21-year percentage changes
	double numGLA2yChange;
	double numGLA5yChange;
	double numGLA21yChange;

	// 2-year, 5-year and 21-year percentage changes
	double numTransit2yChange;
	double numTransit5yChange;
	double numTransit21yChange;

	// 2-year, 5-year and 21-year percentage changes
	double numHousing2yChange;
	double numHousing5yChange;
	double numHousing21yChange;

	// 2-year, 5-year and 21-year percentage changes
	double numPetitLarcenies2yChange;
	double numPetitLarcenies5yChange;
	double numPetitLarcenies21yChange;

	// 2-year, 5-year and 21-year percentage changes
	double numMisdAssaults2yChange;
	double numMisdAssaults5yChange;
	double numMisdAssaults21yChange;

	// 2-year, 5-year and 21-year percentage changes
	double numMisdSexCrimes2yChange;
	double numMisdSexCrimes5yChange;
	double numMisdSexCrimes21yChange;

	// 2-year, 5-year and 21-year percentage changes
	// double numShootingVic2yChange;
	// double numShootingVic5yChange;
	// double numShootingVic21yChange;

	// 2-year, 5-year and 21-year percentage changes
	// double numShootingInc2yChange;
	// double numShootingInc5yChange;
	// double numShootingInc21yChange;

	public CrimeReport(
			Calendar startCal,
			Calendar endCal,
			boolean isPrecinctReport,
			int precinct,
			String borough,

			int numMurdersWTD,
			int numRapesWTD,
			int numRobberiesWTD,
			int numFelAssaultsWTD,
			int numBurglariesWTD,
			int numGrandLarceniesWTD,
			int numGLAWTD,
			int numTransitWTD,
			int numHousingWTD,
			int numPetitLarceniesWTD,
			int numMisdAssaultsWTD,
			int numMisdSexCrimesWTD,
			// int numShootingVicWTD, int numShootingIncWTD,
			int numMurdersWTDPrevYear,
			int numRapesWTDPrevYear,
			int numRobberiesWTDPrevYear,
			int numFelAssaultsWTDPrevYear,
			int numBurglariesWTDPrevYear,
			int numGrandLarceniesWTDPrevYear,
			int numGLAWTDPrevYear,
			int numTransitWTDPrevYear,
			int numHousingWTDPrevYear,
			int numPetitLarceniesWTDPrevYear,
			int numMisdAssaultsWTDPrevYear,
			int numMisdSexCrimesWTDPrevYear,
			// int numShootingVicWTDPrevYear, int numShootingIncWTDPrevYear,
			int numMurders28d,
			int numRapes28d,
			int numRobberies28d,
			int numFelAssaults28d,
			int numBurglaries28d,
			int numGrandLarcenies28d,
			int numGLA28d,
			int numTransit28d,
			int numHousing28d,
			int numPetitLarcenies28d,
			int numMisdAssaults28d,
			int numMisdSexCrimes28d,
			// int numShootingVic28d, int numShootingInc28d,
			int numMurders28dPrevYear,
			int numRapes28dPrevYear,
			int numRobberies28dPrevYear,
			int numFelAssaults28dPrevYear,
			int numBurglaries28dPrevYear,
			int numGrandLarcenies28dPrevYear,
			int numGLA28dPrevYear,
			int numTransit28dPrevYear,
			int numHousing28dPrevYear,
			int numPetitLarcenies28dPrevYear,
			int numMisdAssaults28dPrevYear,
			int numMisdSexCrimes28dPrevYear,
			// int numShootingVic28dPrevYear, int numShootingInc28dPrevYear,
			int numMurdersYTD,
			int numRapesYTD,
			int numRobberiesYTD,
			int numFelAssaultsYTD,
			int numBurglariesYTD,
			int numGrandLarceniesYTD,
			int numGLAYTD,
			int numTransitYTD,
			int numHousingYTD,
			int numPetitLarceniesYTD,
			int numMisdAssaultsYTD,
			int numMisdSexCrimesYTD,
			// int numShootingVicYTD, int numShootingIncYTD,
			int numMurdersYTDPrevYear,
			int numRapesYTDPrevYear,
			int numRobberiesYTDPrevYear,
			int numFelAssaultsYTDPrevYear,
			int numBurglariesYTDPrevYear,
			int numGrandLarceniesYTDPrevYear,
			int numGLAYTDPrevYear,
			int numTransitYTDPrevYear,
			int numHousingYTDPrevYear,
			int numPetitLarceniesYTDPrevYear,
			int numMisdAssaultsYTDPrevYear,
			int numMisdSexCrimesYTDPrevYear,
			// int numShootingVicYTDPrevYear, int numShootingIncYTDPrevYear,
			double numMurders2yChange, double numMurders5yChange,
			double numMurders21yChange, double numRapes2yChange,
			double numRapes5yChange, double numRapes21yChange,
			double numRobberies2yChange, double numRobberies5yChange,
			double numRobberies21yChange, double numFelAssaults2yChange,
			double numFelAssaults5yChange, double numFelAssaults21yChange,
			double numBurglaries2yChange, double numBurglaries5yChange,
			double numBurglaries21yChange, double numGrandLarcenies2yChange,
			double numGrandLarcenies5yChange,
			double numGrandLarcenies21yChange, double numGLA2yChange,
			double numGLA5yChange, double numGLA21yChange,
			double numTransit2yChange, double numTransit5yChange,
			double numTransit21yChange, double numHousing2yChange,
			double numHousing5yChange, double numHousing21yChange,
			double numPetitLarcenies2yChange, double numPetitLarcenies5yChange,
			double numPetitLarcenies21yChange, double numMisdAssaults2yChange,
			double numMisdAssaults5yChange, double numMisdAssaults21yChange,
			double numMisdSexCrimes2yChange, double numMisdSexCrimes5yChange,
			double numMisdSexCrimes21yChange
	// double numShootingVic2yChange,
	// double numShootingVic5yChange, double numShootingVic21yChange,
	// double numShootingInc2yChange, double numShootingInc5yChange,
	// double numShootingInc21yChange
	)
	{
		super();
		this.startCal = startCal;
		this.endCal = endCal;
		this.isPrecinctReport = isPrecinctReport;
		this.precinct = precinct;
		this.borough = borough;

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
		// this.numShootingVicWTD = numShootingVicWTD;
		// this.numShootingIncWTD = numShootingIncWTD;
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
		// this.numShootingVicWTDPrevYear = numShootingVicWTDPrevYear;
		// this.numShootingIncWTDPrevYear = numShootingIncWTDPrevYear;
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
		// this.numShootingVic28d = numShootingVic28d;
		// this.numShootingInc28d = numShootingInc28d;
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
		// this.numShootingVic28dPrevYear = numShootingVic28dPrevYear;
		// this.numShootingInc28dPrevYear = numShootingInc28dPrevYear;
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
		// this.numShootingVicYTD = numShootingVicYTD;
		// this.numShootingIncYTD = numShootingIncYTD;
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
		// this.numShootingVicYTDPrevYear = numShootingVicYTDPrevYear;
		// this.numShootingIncYTDPrevYear = numShootingIncYTDPrevYear;
		this.numMurders2yChange = numMurders2yChange;
		this.numMurders5yChange = numMurders5yChange;
		this.numMurders21yChange = numMurders21yChange;
		this.numRapes2yChange = numRapes2yChange;
		this.numRapes5yChange = numRapes5yChange;
		this.numRapes21yChange = numRapes21yChange;
		this.numRobberies2yChange = numRobberies2yChange;
		this.numRobberies5yChange = numRobberies5yChange;
		this.numRobberies21yChange = numRobberies21yChange;
		this.numFelAssaults2yChange = numFelAssaults2yChange;
		this.numFelAssaults5yChange = numFelAssaults5yChange;
		this.numFelAssaults21yChange = numFelAssaults21yChange;
		this.numBurglaries2yChange = numBurglaries2yChange;
		this.numBurglaries5yChange = numBurglaries5yChange;
		this.numBurglaries21yChange = numBurglaries21yChange;
		this.numGrandLarcenies2yChange = numGrandLarcenies2yChange;
		this.numGrandLarcenies5yChange = numGrandLarcenies5yChange;
		this.numGrandLarcenies21yChange = numGrandLarcenies21yChange;
		this.numGLA2yChange = numGLA2yChange;
		this.numGLA5yChange = numGLA5yChange;
		this.numGLA21yChange = numGLA21yChange;
		this.numTransit2yChange = numTransit2yChange;
		this.numTransit5yChange = numTransit5yChange;
		this.numTransit21yChange = numTransit21yChange;
		this.numHousing2yChange = numHousing2yChange;
		this.numHousing5yChange = numHousing5yChange;
		this.numHousing21yChange = numHousing21yChange;
		this.numPetitLarcenies2yChange = numPetitLarcenies2yChange;
		this.numPetitLarcenies5yChange = numPetitLarcenies5yChange;
		this.numPetitLarcenies21yChange = numPetitLarcenies21yChange;
		this.numMisdAssaults2yChange = numMisdAssaults2yChange;
		this.numMisdAssaults5yChange = numMisdAssaults5yChange;
		this.numMisdAssaults21yChange = numMisdAssaults21yChange;
		this.numMisdSexCrimes2yChange = numMisdSexCrimes2yChange;
		this.numMisdSexCrimes5yChange = numMisdSexCrimes5yChange;
		this.numMisdSexCrimes21yChange = numMisdSexCrimes21yChange;

	}

}
