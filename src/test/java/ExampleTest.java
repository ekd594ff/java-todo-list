import org.homework.step3.view.InputView;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ExampleTest {

    @Test
    void test() {
        InputView iv = new InputView();
        iv.getDeadline();
    }
}
