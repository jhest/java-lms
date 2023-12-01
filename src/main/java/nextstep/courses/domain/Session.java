package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import static nextstep.courses.domain.PaidType.*;
import static nextstep.courses.domain.SessionStatus.*;

public class Session {

    private Long id;
    private Long courseId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private ImageInfo imageInfo;
    private PaidType paidType;
    private Integer targetNumber;
    private Integer appliedNumber;
    private Long sessionFee;
    private SessionStatus sessionStatus;

    public Session() {
    }

    public Session(PaidType paidType, Integer targetNumber, Integer appliedNumber) {
        if (paidType.equals(FREE) && targetNumber != null) {
            throw new IllegalArgumentException("무료 강의는 최대 수강 인원 제한이 없습니다.");
        }

        this.id = 0L;
        this.courseId = 0L;
        this.startDate = null;
        this.endDate = null;
        this.imageInfo = null;
        this.paidType = paidType;
        this.targetNumber = targetNumber;
        this.appliedNumber = null;
        this.sessionFee = null;
        this.sessionStatus = APPLYING;
    }

    public Session(LocalDateTime startDate, LocalDateTime endDate) {
        this.id = 0L;
        this.courseId = 0L;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageInfo = null;
        this.paidType = null;
        this.targetNumber = null;
        this.appliedNumber = null;
        this.sessionFee = null;
        this.sessionStatus = APPLYING;
    }

    public Session(Long id, Long courseId, LocalDateTime startDate, LocalDateTime endDate, ImageInfo imageInfo, PaidType paidType, Integer targetNumber, Integer appliedNumber, Long sessionFee, SessionStatus sessionStatus) {
        this.id = id;
        this.courseId = courseId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageInfo = imageInfo;
        this.paidType = paidType;
        this.targetNumber = targetNumber;
        this.appliedNumber = appliedNumber;
        this.sessionFee = sessionFee;
        this.sessionStatus = sessionStatus;
    }

    public String period() {
        String startDate = String.valueOf(this.startDate);
        String endDate = String.valueOf(this.endDate);

        return startDate + "~" + endDate;
    }

    public boolean isOpen() {
        return sessionStatus.equals(APPLYING);
    }

    public SessionEnrollList enroll(Student student, long paidAmount) {
        if (paidType.equals(PAID) && Objects.equals(targetNumber, appliedNumber)) {
            throw new IllegalArgumentException("유료 강의는 강의 최대 수강 인원을 초과할 수 없습니다.");
        }
        if (paidType.equals(PAID) && paidAmount != sessionFee) {
            throw new IllegalArgumentException("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능합니다.");
        }
        if (sessionStatus != APPLYING) {
            throw new IllegalArgumentException("강의 수강신청은 강의 상태가 모집중일 때만 가능합니다.");
        }
        return new SessionEnrollList(this, student, LocalDateTime.now());
    }
}
