package christmas.domain;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;

public class InputView {

    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        while (true) {
            if (Validator.validateDate(input)) {
                break;
            } else {
                System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                input = Console.readLine();
            }
        }
        return Integer.parseInt(input);
    }

}
