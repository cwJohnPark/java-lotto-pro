package lotto;

import static lotto.view.LottoWinPrize.FIVE_MATCHES;
import static lotto.view.LottoWinPrize.FOUR_MATCHES;
import static lotto.view.LottoWinPrize.ONE_MATCHES;
import static lotto.view.LottoWinPrize.SIX_MATCHES;
import static lotto.view.LottoWinPrize.THREE_MATCHES;
import static lotto.view.LottoWinPrize.TWO_MATCHES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;

import org.assertj.core.util.Lists;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.domain.LottoTicket;
import lotto.view.LottoWinPrize;

class LottoTicketTest {

	@ParameterizedTest
	@MethodSource("중복된_로또번호_생성")
	void 로또_티켓에_중복된_로또_번호를_입력할_경우_예외를_던진다(List<Integer> 로또_번호_입력) {
		assertThatThrownBy(() -> LottoTicket.of(로또_번호_입력))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@MethodSource("갯수가_무효한_로또번호_생성")
	void 로또_티켓에_입력_갯수가_유효하지_않으면_예외를_던진다(List<Integer> 로또_번호_입력) {
		assertThatThrownBy(() -> LottoTicket.of(로또_번호_입력))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@ParameterizedTest
	@MethodSource("두개의_로또_티켓_입력")
	void 두개의_로또_티켓을_비교하여_일치하는_숫자_갯수를_알_수_있다(LottoTicket 로또티켓, LottoTicket 비교_로또티켓, LottoWinPrize 예상_당첨_결과) {
		LottoWinPrize 당첨_결과 = 로또티켓.match(비교_로또티켓);
		assertThat(당첨_결과).isEqualTo(예상_당첨_결과);
	}

	private static Stream<Arguments> 두개의_로또_티켓_입력() {
		return Stream.of(
			Arguments.of(LottoTicket.of(6, 5, 4, 3, 2, 7), LottoTicket.of(6, 5, 4, 3, 2, 1), FIVE_MATCHES),
			Arguments.of(LottoTicket.of(1, 2, 3, 4, 5, 6), LottoTicket.of(1, 7, 8, 9, 10, 11), ONE_MATCHES),
			Arguments.of(LottoTicket.of(1, 2, 3, 4, 5, 6), LottoTicket.of(1, 2, 7, 8, 9, 10), TWO_MATCHES),
			Arguments.of(LottoTicket.of(1, 2, 3, 4, 5, 6), LottoTicket.of(1, 2, 3, 7, 8, 9), THREE_MATCHES),
			Arguments.of(LottoTicket.of(1, 2, 3, 4, 5, 6), LottoTicket.of(1, 2, 3, 4, 8, 9), FOUR_MATCHES),
			Arguments.of(LottoTicket.of(1, 2, 3, 4, 5, 6), LottoTicket.of(1, 2, 3, 4, 5, 9), FIVE_MATCHES),
			Arguments.of(LottoTicket.of(1, 2, 3, 4, 5, 6), LottoTicket.of(1, 2, 3, 4, 5, 6), SIX_MATCHES)
		);
	}

	private static Stream<Arguments> 중복된_로또번호_생성() {
		return Stream.of(
			Arguments.of(Lists.newArrayList(1, 2, 3, 4, 5, 5)),
			Arguments.of(Lists.newArrayList(1, 1, 1, 1, 1, 1))
		);
	}

	private static Stream<Arguments> 갯수가_무효한_로또번호_생성() {
		return Stream.of(
			Arguments.of(Lists.newArrayList()),
			Arguments.of(Lists.newArrayList(1)),
			Arguments.of(Lists.newArrayList(1, 2, 3, 4, 5, 6, 7)),
			Arguments.of(Lists.newArrayList(45, 44, 43, 42, 41, 40, 39))
		);
	}
}
