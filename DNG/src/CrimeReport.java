import java.util.Date;

public class CrimeReport
{

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

	public CrimeReport(boolean isPrecinctReport, int precinct, String borough,
			int currentYear, Date startDate, Date endDate, int numMurdersWTD,
			int numRapesWTD, int numRobberiesWTD, int numFelAssaultsWTD,
			int numBurglariesWTD, int numGrandLarceniesWTD, int numGLAWTD,
			int numTransitWTD, int numHousingWTD, int numPetitLarceyWTD,
			int numMisdAssaultWTD, int numMisdSexCrimesWTD,
			int numShootingVicWTD, int numShootingIncWTD,
			int numMurdersWTDPrevYear, int numRapesWTDPrevYear,
			int numRobberiesWTDPrevYear, int numFelAssaultsWTDPrevYear,
			int numBurglariesWTDPrevYear, int numGrandLarceniesWTDPrevYear,
			int numGLAWTDPrevYear, int numTransitWTDPrevYear,
			int numHousingWTDPrevYear, int numPetitLarceyWTDPrevYear,
			int numMisdAssaultWTDPrevYear, int numMisdSexCrimesWTDPrevYear,
			int numShootingVicWTDPrevYear, int numShootingIncWTDPrevYear,
			int numMurders28d, int numRapes28d, int numRobberies28d,
			int numFelAssaults28d, int numBurglaries28d,
			int numGrandLarcenies28d, int numGLA28d, int numTransit28d,
			int numHousing28d, int numPetitLarcey28d, int numMisdAssault28d,
			int numMisdSexCrimes28d, int numShootingVic28d,
			int numShootingInc28d, int numMurders28dPrevYear,
			int numRapes28dPrevYear, int numRobberies28dPrevYear,
			int numFelAssaults28dPrevYear, int numBurglaries28dPrevYear,
			int numGrandLarcenies28dPrevYear, int numGLA28dPrevYear,
			int numTransit28dPrevYear, int numHousing28dPrevYear,
			int numPetitLarcey28dPrevYear, int numMisdAssault28dPrevYear,
			int numMisdSexCrimes28dPrevYear, int numShootingVic28dPrevYear,
			int numShootingInc28dPrevYear, int numMurdersYTD, int numRapesYTD,
			int numRobberiesYTD, int numFelAssaultsYTD, int numBurglariesYTD,
			int numGrandLarceniesYTD, int numGLAYTD, int numTransitYTD,
			int numHousingYTD, int numPetitLarceyYTD, int numMisdAssaultYTD,
			int numMisdSexCrimesYTD, int numShootingVicYTD,
			int numShootingIncYTD, int numMurdersYTDPrevYear,
			int numRapesYTDPrevYear, int numRobberiesYTDPrevYear,
			int numFelAssaultsYTDPrevYear, int numBurglariesYTDPrevYear,
			int numGrandLarceniesYTDPrevYear, int numGLAYTDPrevYear,
			int numTransitYTDPrevYear, int numHousingYTDPrevYear,
			int numPetitLarceyYTDPrevYear, int numMisdAssaultYTDPrevYear,
			int numMisdSexCrimesYTDPrevYear, int numShootingVicYTDPrevYear,
			int numShootingIncYTDPrevYear)
	{
		super();
		this.isPrecinctReport = isPrecinctReport;
		this.precinct = precinct;
		this.borough = borough;
		this.currentYear = currentYear;
		this.startDate = startDate;
		this.endDate = endDate;
		this.numMurdersWTD = numMurdersWTD;
		this.numRapesWTD = numRapesWTD;
		this.numRobberiesWTD = numRobberiesWTD;
		this.numFelAssaultsWTD = numFelAssaultsWTD;
		this.numBurglariesWTD = numBurglariesWTD;
		this.numGrandLarceniesWTD = numGrandLarceniesWTD;
		this.numGLAWTD = numGLAWTD;
		this.numTransitWTD = numTransitWTD;
		this.numHousingWTD = numHousingWTD;
		this.numPetitLarceyWTD = numPetitLarceyWTD;
		this.numMisdAssaultWTD = numMisdAssaultWTD;
		this.numMisdSexCrimesWTD = numMisdSexCrimesWTD;
		this.numShootingVicWTD = numShootingVicWTD;
		this.numShootingIncWTD = numShootingIncWTD;
		this.numMurdersWTDPrevYear = numMurdersWTDPrevYear;
		this.numRapesWTDPrevYear = numRapesWTDPrevYear;
		this.numRobberiesWTDPrevYear = numRobberiesWTDPrevYear;
		this.numFelAssaultsWTDPrevYear = numFelAssaultsWTDPrevYear;
		this.numBurglariesWTDPrevYear = numBurglariesWTDPrevYear;
		this.numGrandLarceniesWTDPrevYear = numGrandLarceniesWTDPrevYear;
		this.numGLAWTDPrevYear = numGLAWTDPrevYear;
		this.numTransitWTDPrevYear = numTransitWTDPrevYear;
		this.numHousingWTDPrevYear = numHousingWTDPrevYear;
		this.numPetitLarceyWTDPrevYear = numPetitLarceyWTDPrevYear;
		this.numMisdAssaultWTDPrevYear = numMisdAssaultWTDPrevYear;
		this.numMisdSexCrimesWTDPrevYear = numMisdSexCrimesWTDPrevYear;
		this.numShootingVicWTDPrevYear = numShootingVicWTDPrevYear;
		this.numShootingIncWTDPrevYear = numShootingIncWTDPrevYear;
		this.numMurders28d = numMurders28d;
		this.numRapes28d = numRapes28d;
		this.numRobberies28d = numRobberies28d;
		this.numFelAssaults28d = numFelAssaults28d;
		this.numBurglaries28d = numBurglaries28d;
		this.numGrandLarcenies28d = numGrandLarcenies28d;
		this.numGLA28d = numGLA28d;
		this.numTransit28d = numTransit28d;
		this.numHousing28d = numHousing28d;
		this.numPetitLarcey28d = numPetitLarcey28d;
		this.numMisdAssault28d = numMisdAssault28d;
		this.numMisdSexCrimes28d = numMisdSexCrimes28d;
		this.numShootingVic28d = numShootingVic28d;
		this.numShootingInc28d = numShootingInc28d;
		this.numMurders28dPrevYear = numMurders28dPrevYear;
		this.numRapes28dPrevYear = numRapes28dPrevYear;
		this.numRobberies28dPrevYear = numRobberies28dPrevYear;
		this.numFelAssaults28dPrevYear = numFelAssaults28dPrevYear;
		this.numBurglaries28dPrevYear = numBurglaries28dPrevYear;
		this.numGrandLarcenies28dPrevYear = numGrandLarcenies28dPrevYear;
		this.numGLA28dPrevYear = numGLA28dPrevYear;
		this.numTransit28dPrevYear = numTransit28dPrevYear;
		this.numHousing28dPrevYear = numHousing28dPrevYear;
		this.numPetitLarcey28dPrevYear = numPetitLarcey28dPrevYear;
		this.numMisdAssault28dPrevYear = numMisdAssault28dPrevYear;
		this.numMisdSexCrimes28dPrevYear = numMisdSexCrimes28dPrevYear;
		this.numShootingVic28dPrevYear = numShootingVic28dPrevYear;
		this.numShootingInc28dPrevYear = numShootingInc28dPrevYear;
		this.numMurdersYTD = numMurdersYTD;
		this.numRapesYTD = numRapesYTD;
		this.numRobberiesYTD = numRobberiesYTD;
		this.numFelAssaultsYTD = numFelAssaultsYTD;
		this.numBurglariesYTD = numBurglariesYTD;
		this.numGrandLarceniesYTD = numGrandLarceniesYTD;
		this.numGLAYTD = numGLAYTD;
		this.numTransitYTD = numTransitYTD;
		this.numHousingYTD = numHousingYTD;
		this.numPetitLarceyYTD = numPetitLarceyYTD;
		this.numMisdAssaultYTD = numMisdAssaultYTD;
		this.numMisdSexCrimesYTD = numMisdSexCrimesYTD;
		this.numShootingVicYTD = numShootingVicYTD;
		this.numShootingIncYTD = numShootingIncYTD;
		this.numMurdersYTDPrevYear = numMurdersYTDPrevYear;
		this.numRapesYTDPrevYear = numRapesYTDPrevYear;
		this.numRobberiesYTDPrevYear = numRobberiesYTDPrevYear;
		this.numFelAssaultsYTDPrevYear = numFelAssaultsYTDPrevYear;
		this.numBurglariesYTDPrevYear = numBurglariesYTDPrevYear;
		this.numGrandLarceniesYTDPrevYear = numGrandLarceniesYTDPrevYear;
		this.numGLAYTDPrevYear = numGLAYTDPrevYear;
		this.numTransitYTDPrevYear = numTransitYTDPrevYear;
		this.numHousingYTDPrevYear = numHousingYTDPrevYear;
		this.numPetitLarceyYTDPrevYear = numPetitLarceyYTDPrevYear;
		this.numMisdAssaultYTDPrevYear = numMisdAssaultYTDPrevYear;
		this.numMisdSexCrimesYTDPrevYear = numMisdSexCrimesYTDPrevYear;
		this.numShootingVicYTDPrevYear = numShootingVicYTDPrevYear;
		this.numShootingIncYTDPrevYear = numShootingIncYTDPrevYear;
	}

}
