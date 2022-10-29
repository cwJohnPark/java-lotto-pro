package lotto;

import lotto.domain.AutoLottoTicketGenerator;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.LottoVendor;
import lotto.view.LastWeekWinLottoTicketView;
import lotto.view.LottoPurchaseView;
import lotto.view.LottoWinResultView;
import money.Money;

public class LottoApplication {

	private static final Money LOTTO_PRICE = Money.wons(1000);

	public static void main(String[] args) {
		LottoPurchaseView lottoPurchaseView = new LottoPurchaseView(
			new LottoVendor(LOTTO_PRICE, new AutoLottoTicketGenerator()));

		LastWeekWinLottoTicketView lastWeekWinLottoTicketView = new LastWeekWinLottoTicketView();
		LottoWinResultView lottoWinResultView = new LottoWinResultView(LOTTO_PRICE);

		LottoTickets purchaseLottoTickets = lottoPurchaseView.purchaseLotto();
		LottoTicket lastWeekWinLottoTicket = lastWeekWinLottoTicketView.getLastWeekWinLotto();
		lottoWinResultView.printWinResult(purchaseLottoTickets, lastWeekWinLottoTicket);
	}
}
