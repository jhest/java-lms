package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.courses.domain.PaidType.*;
import static nextstep.courses.domain.SessionStatus.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SessionTest {

    @DisplayName("강의는 시작일과 종료일을 가진다.")
    @Test
    void 강의_시작종료일() {
        Session session = new Session(LocalDateTime.of(2023, 12, 1, 0, 0, 0),
                LocalDateTime.of(2023, 12, 10, 0, 0, 0));
        String sessionPeriod = session.period();

        assertThat(sessionPeriod).isEqualTo("2023-12-01T00:00~2023-12-10T00:00");
    }

    @DisplayName("무료 강의는 최대 수강 인원 제한이 없다.")
    @Test
    void 무료강의_인원제한() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Session(FREE, 100, 100));
    }

    @DisplayName("유료 강의는 강의 최대 수강 인원을 초과할 수 없다.")
    @Test
    void 유료강의_최대인원() {
        Session session = new Session(0L, 0L, null, null, null, PAID, 100, 100, 100000L, APPLYING);
        Student student = new Student();
        long paidAmount = 100000L;

        assertThatIllegalArgumentException()
                .isThrownBy(() -> session.enroll(student, paidAmount));
    }

    @DisplayName("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.")
    @Test
    void 수강신청_성공_수강료() {
        Session session = new Session(0L, 0L, null, null, null, PAID, 100, 10, 100000L, APPLYING);
        Student student = new Student();
        long paidAmount = 100000L;
        SessionEnrollList enrolled= session.enroll(student, paidAmount);

        Student enrolledStudent = enrolled.getStudent();
        assertThat(enrolledStudent).isEqualTo(student);
    }

    @DisplayName("강의 수강신청은 강의 상태가 모집중일 때만 가능하다.")
    @Test
    void 강의상태_모집중() {
        Session session = new Session(0L, 0L, null, null, null, PAID, 100, 10, 100000L, APPLYING);
        Student student = new Student();
        long paidAmount = 100000L;
        SessionEnrollList enrolled= session.enroll(student, paidAmount);

        Student enrolledStudent = enrolled.getStudent();
        assertThat(enrolledStudent).isEqualTo(student);
    }

    @DisplayName("강의 수강신청은 강의 상태가 모집중일 때만 가능하다.")
    @Test
    void 강의상태_불가() {
        Session session1 = new Session(0L, 0L, null, null, null, FREE, null, null, null, PREPARING);
        Session session2 = new Session(0L, 0L, null, null, null, FREE, null, null, null, CLOSED);
        Student student = new Student();

        assertThatIllegalArgumentException()
                .isThrownBy(() -> session1.enroll(student, 0));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> session2.enroll(student, 0));
    }
}