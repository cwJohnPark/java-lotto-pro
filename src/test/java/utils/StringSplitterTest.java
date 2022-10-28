package utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class StringSplitterTest {


	@ParameterizedTest
	@ValueSource(strings = {"", " "})
	void 빈_문자열을_전달할_경우_빈_리스트를_반환한다(String 빈_문자열) {
		SplitStrings 빈_리스트 = StringSplitter.split(빈_문자열);
		assertThat(빈_리스트.isEmpty()).isTrue();
	}
	@Test
	void Null을_전달할_경우_빈_리스트를_반환한다() {
		SplitStrings 빈_리스트 = StringSplitter.split(null);
		assertThat(빈_리스트.isEmpty()).isTrue();
	}

	@ParameterizedTest
	@MethodSource("provideDefaultDelimiter")
	void 기본_구분자를_입력하여_문자열을_분리한다(String 문자열, SplitStrings 분리_결과_리스트) {
		SplitStrings 분리된_문자열 = StringSplitter.split(문자열);
		assertThat(분리된_문자열).isEqualTo(분리_결과_리스트);
	}

	@ParameterizedTest
	@MethodSource("provideCustomDelimiterNumbers")
	void 커스텀_구분자를_입력하여_문자열을_분리한다(String 문자열, SplitStrings 분리_결과_리스트) {
		SplitStrings 분리된_문자열 = StringSplitter.split(문자열);
		assertThat(분리된_문자열).isEqualTo(분리_결과_리스트);
	}

	private static Stream<Arguments> provideDefaultDelimiter() {
		return Stream.of(
			Arguments.of("1:2:3", SplitStrings.of("1", "2", "3")),
			Arguments.of("1,2,3", SplitStrings.of("1", "2", "3"))
		);
	}

	private static Stream<Arguments> provideCustomDelimiterNumbers() {
		return Stream.of(
			Arguments.of("//;\n1;2;4", SplitStrings.of("1", "2", "4")),
			Arguments.of("//@\n1@2@4", SplitStrings.of("1", "2", "4")),
			Arguments.of("//#\n1#2#4", SplitStrings.of("1", "2", "4"))
		);
	}
}
