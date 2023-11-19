package edu.project3.renderer;

import edu.project3.model.Report;
import edu.project3.model.ReportColumn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

class MarkdownRendererTest {

    public static Stream<Arguments> renderSource() {
        return Stream.of(
            Arguments.of(
                new Report(
                    "Header",
                    List.of(
                        new ReportColumn(
                            "column1",
                            List.of("c1l1", "c1l2", "c1l3", "c1l4", "c1l5")
                        ),
                        new ReportColumn(
                            "column2",
                            List.of("c2l1", "c2l2", "c2l3", "c2l4", "c2l5")
                        ),
                        new ReportColumn(
                            "column3",
                            List.of("c3l1", "c3l2", "c3l3", "c3l4", "c3l5")
                        )
                    )
                ),
                """
                    #### Header

                    | column1 | column2 | column3 |
                    |:-------:|:-------:|:-------:|
                    |    c1l1 |    c2l1 |    c3l1 |
                    |    c1l2 |    c2l2 |    c3l2 |
                    |    c1l3 |    c2l3 |    c3l3 |
                    |    c1l4 |    c2l4 |    c3l4 |
                    |    c1l5 |    c2l5 |    c3l5 |
                    """
            )
        );
    }

    @ParameterizedTest
    @MethodSource("renderSource")
    @DisplayName("Basic test")
    void render_shouldReturnCorrectMarkdownString_whenNormalInput(Report report, String renderExpected) {
        assertThat(new MarkdownRenderer().render(report)).isEqualTo(renderExpected);
    }
}
