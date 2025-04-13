package com.hnsoftedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hnsoftedu.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 王荣星
 * @since 2025-03-14
 */
public interface IStudentService extends IService<Student> {
    //有两个findBytudent方法，两者的结果是一样的，但返回形式不一样
    List<Student> findByStudent(Student student);
    List<Student> findByStudent1(Student student);
    boolean removeStudentById(Student student);
    Page<Student> selectByPage(Student student, int pageNo, int pageSize);
    boolean updateStudent(Student student);
}
