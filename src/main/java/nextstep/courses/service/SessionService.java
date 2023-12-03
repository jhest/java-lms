package nextstep.courses.service;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionEnroll;
import nextstep.courses.domain.session.SessionVO;
import nextstep.courses.domain.student.Student;
import nextstep.payments.domain.Payment;

public class SessionService {

    public Session makeSession(Long courseId, SessionVO sessionVO) {
        return new Session(
                sessionVO.getId(),
                courseId,
                sessionVO.getStartDate(),
                sessionVO.getEndDate(),
                sessionVO.getImageInfo(),
                sessionVO.getPaidType(),
                sessionVO.getTargetNumber(),
                sessionVO.getAppliedNumber(),
                sessionVO.getSessionFee(),
                sessionVO.getSessionStatus());
    }

    public SessionEnroll enrollStudent(Session session, Student student, Payment payment) {
        return new SessionEnroll(session, student, payment);
    }
}
