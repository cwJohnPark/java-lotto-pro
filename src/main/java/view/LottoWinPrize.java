package view;

import money.Money;

public enum LottoWinPrize {
	THREE_MATCHES(3, Money.wons(5_000)),
	FOUR_MATCHES(4, Money.wons(5_000)),
	FIVE_MATCHES(5, Money.wons(1_500_000)),
	SIX_MATCHES(6, Money.wons(2_000_000_000));

	public final int matchCount;
	public final Money prize;

	LottoWinPrize(int matchCount, Money prize) {
		this.matchCount = matchCount;
		this.prize = prize;
	}
}
