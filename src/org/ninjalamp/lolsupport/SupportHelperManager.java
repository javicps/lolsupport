package org.ninjalamp.lolsupport;

public class SupportHelperManager {

	public static final int LEONA = 0;
	public static final int JANNA = 1;
	public static final int LULU = 2;
	public static final int LUX = 3;
	public static final int NUNU = 4;
	public static final int SONA = 5;
	public static final int SORAKA = 6;
	public static final int TARIC = 7;
	public static final int ZYRA = 8;
	public static final int ALISTAR = 9;
	public static final int BLITZCRANK = 10;
	public static final int FIDDLESTICKS = 11;

	public static final int EZREAL = 0;
	public static final int CORKI = 1;
	public static final int GRAVES = 2;
	public static final int VAYNE = 3;
	public static final int KOGMAW = 4;
	public static final int CAITLYN = 5;
	public static final int MISSFORTUNE = 6;
	public static final int DRAVEN = 7;
	public static final int SIVIR = 8;
	public static final int VARUS = 9;
	public static final int TRISTANA = 10;
	public static final int ASHE = 11;
	public static final int URGOT = 12;

	public static final String[] SUPPORT_LIST = { "LEONA", "JANNA", "LULU", "LUX", "NUNU", "SONA", "SORAKA", "TARIC", "ZYRA", "ALISTAR", "BLITZCRANK", "FIDDLESTICKS" };

	public static final int[][] BEST_SUPPORTS_FOR_ALLY_ADC = { { SONA, LUX, LULU }, { LEONA, SONA, BLITZCRANK }, { TARIC, SONA, LUX }, { ALISTAR, NUNU, SORAKA },
			{ NUNU, ZYRA, ALISTAR }, { NUNU, ZYRA, LULU }, { SONA, TARIC, JANNA }, { TARIC, LULU, ALISTAR }, { TARIC, ALISTAR, JANNA }, { SONA, LULU, NUNU },
			{ LEONA, NUNU, ALISTAR }, { JANNA, SONA, ZYRA }, { SORAKA, NUNU, JANNA } };

	public static final int[][] BEST_SUPPORTS_FOR_ENEMY_ADC = { { TARIC, SONA, SORAKA }, { SONA, FIDDLESTICKS, BLITZCRANK }, { TARIC, BLITZCRANK, FIDDLESTICKS },
			{ LULU, SONA, NUNU }, { NUNU, BLITZCRANK, SONA }, { TARIC, SORAKA, BLITZCRANK }, { ZYRA, NUNU, TARIC }, { ZYRA, BLITZCRANK, FIDDLESTICKS }, { ALISTAR, NUNU, SONA },
			{ BLITZCRANK, TARIC, ZYRA }, { NUNU, JANNA, LULU }, { BLITZCRANK, LUX, TARIC }, { SORAKA, FIDDLESTICKS, TARIC } };

	public static final int[][] BEST_SUPPORTS_FOR_ENEMY_SUPPORT = { { NUNU, LULU, JANNA }, { LULU, TARIC, LUX }, { SORAKA, SONA, BLITZCRANK }, { ALISTAR, BLITZCRANK, NUNU },
			{ SONA, LULU, SORAKA }, { ZYRA, TARIC, BLITZCRANK }, { ALISTAR, SONA, LUX }, { NUNU, ALISTAR, ZYRA }, { BLITZCRANK, NUNU, SORAKA }, { JANNA, ZYRA, FIDDLESTICKS },
			{ LEONA, TARIC, SONA }, { SORAKA, BLITZCRANK, LUX } };

	private static final int[] BASE_SCOREBOARD = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private static int[] supportScoreBoard;

	public static String getSupportName(int index) {
		return SUPPORT_LIST[index];
	}

	public static void resetScoreBoard() {
		supportScoreBoard = BASE_SCOREBOARD;
		for (int i = 0; i < supportScoreBoard.length; i++) {
			supportScoreBoard[i] = 0;
		}
	}

	private static void calculateBestSupportForAllyAdc(int ally_adc) {
		int[] bestSupports = BEST_SUPPORTS_FOR_ALLY_ADC[ally_adc];
		supportScoreBoard[bestSupports[0]] += 5;
		supportScoreBoard[bestSupports[1]] += 3;
		supportScoreBoard[bestSupports[2]] += 2;
	}

	private static void calculateBestSupportForEnemyAdc(int enemy_adc) {
		int[] bestSupports = BEST_SUPPORTS_FOR_ENEMY_ADC[enemy_adc];
		supportScoreBoard[bestSupports[0]] += 5;
		supportScoreBoard[bestSupports[1]] += 3;
		supportScoreBoard[bestSupports[2]] += 2;
	}

	private static void calculateBestSupportForEnemySupport(int enemy_support) {
		int[] bestSupports = BEST_SUPPORTS_FOR_ENEMY_SUPPORT[enemy_support];
		supportScoreBoard[bestSupports[0]] += 3;
		supportScoreBoard[bestSupports[1]] += 2;
		supportScoreBoard[bestSupports[2]] += 1;
	}

	private static void removeEnemySupportFromChoices(int supportId) {
		supportScoreBoard[supportId] = 0;
	}

	public static int[] getBestSupport(int ally_adc, int enemy_adc, int enemy_support) {
		resetScoreBoard();
		int bestSupports[] = new int[3];
		if (ally_adc > 0) {
			calculateBestSupportForAllyAdc(ally_adc - 1);
		}
		if (enemy_adc > 0) {
			calculateBestSupportForEnemyAdc(enemy_adc - 1);
		}
		if (enemy_support > 0) {
			calculateBestSupportForEnemySupport(enemy_support - 1);
			removeEnemySupportFromChoices(enemy_support - 1);
		}
		bestSupports[0] = getBestSupportFromScoreBoard();
		bestSupports[1] = getBestSupportFromScoreBoard();
		bestSupports[2] = getBestSupportFromScoreBoard();
		return bestSupports;
	}

	private static int getBestSupportFromScoreBoard() {
		int maximum = 0;
		int i = 1;
		for (i = 1; i < supportScoreBoard.length; i++) {
			if (supportScoreBoard[i] > supportScoreBoard[maximum]) {
				maximum = i;
			}
		}
		supportScoreBoard[maximum] = 0;
		return maximum;
	}

}
