package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionEnrollList {

    private Session session;
    private Student student;
    private LocalDateTime enrolledDate;

    public SessionEnrollList() {
    }

    public SessionEnrollList(Session session, Student student, LocalDateTime enrolledDate) {
        this.session = session;
        this.student = student;
        this.enrolledDate = enrolledDate;
    }

    public Session getSession() {
        return session;
    }

    public Student getStudent() {
        return student;
    }
}
