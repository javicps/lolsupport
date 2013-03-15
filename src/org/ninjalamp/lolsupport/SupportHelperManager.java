package org.ninjalamp.lolsupport;

public class SupportHelperManager {

	public static final int LEONA = 0;
	public static final int JANNA = 1;
	public static final int LULU = 2;
	public static final int NUNU = 3;
	public static final int SONA = 4;
	public static final int SORAKA = 5;
	public static final int TARIC = 6;
	public static final int ZYRA = 7;
	public static final int ALISTAR = 8;
	public static final int BLITZCRANK = 9;
	public static final int FIDDLESTICKS = 10;
	public static final int NAMI = 11;

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
	public static final int TWITCH = 13;

	public static final String[] SUPPORT_LIST = { "LEONA", "JANNA", "LULU", "NUNU", "SONA", "SORAKA", "TARIC", "ZYRA", "ALISTAR", "BLITZCRANK", "FIDDLESTICKS", "NAMI" };

	public static final int[][] BEST_SUPPORTS_FOR_ALLY_ADC = { { SONA, TARIC, LULU }, { LEONA, LULU, SONA }, { TARIC, JANNA, SONA }, { ALISTAR, NUNU, SORAKA },
			{ NUNU, ZYRA, NAMI }, { NUNU, LULU, ZYRA }, { NAMI, TARIC, SONA }, { TARIC, NAMI, LULU }, { TARIC, LULU, LEONA }, { ZYRA, LULU, NUNU },
			{ LEONA, NUNU, BLITZCRANK }, { JANNA, NAMI, NUNU }, { SORAKA, NAMI, TARIC }, {LEONA, BLITZCRANK, LULU} };

	public static final int[][] BEST_SUPPORTS_FOR_ENEMY_ADC = { { LULU, FIDDLESTICKS, SORAKA }, { LULU, FIDDLESTICKS, SONA }, { TARIC, ZYRA, NUNU },
			{ LULU, FIDDLESTICKS, SONA }, { LULU, BLITZCRANK, LEONA }, { TARIC, SORAKA, BLITZCRANK }, { FIDDLESTICKS, ZYRA, NUNU }, { FIDDLESTICKS, ZYRA, BLITZCRANK }, 
			{ NUNU, LULU, LEONA }, { BLITZCRANK, LEONA, ZYRA }, { NUNU, JANNA, LULU }, { BLITZCRANK, LEONA, TARIC }, { SORAKA, FIDDLESTICKS, TARIC }, { LEONA, BLITZCRANK, LULU } };

	public static final int[][] BEST_SUPPORTS_FOR_ENEMY_SUPPORT = { { ALISTAR, JANNA, NUNU }, { BLITZCRANK, TARIC, NAMI }, { SORAKA, LEONA, BLITZCRANK }, { SONA, FIDDLESTICKS, NAMI },
			{ LEONA, TARIC, ZYRA }, { NAMI, LEONA, TARIC }, { NUNU, ALISTAR, ZYRA }, { NUNU, SORAKA, NAMI }, { LULU, JANNA, ZYRA }, { LEONA, TARIC, ALISTAR }, { LEONA, LULU, BLITZCRANK },
			{ TARIC, LULU, LEONA } };

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
