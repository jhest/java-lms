package nextstep.courses.service;

import nextstep.courses.domain.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("courseService")
public class CourseService {

    @Resource
    private CourseRepository courseRepository;

    @Resource
    private SessionRepository sessionRepository;

    @Resource
    private StudentRepository studentRepository;

    @Transactional
    public void makeSession() {

    }

    @Transactional
    public void enrollStudent() {

    }
}
