package com.hnsoftedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hnsoftedu.entity.Student;
import com.hnsoftedu.mapper.StudentMapper;
import com.hnsoftedu.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王荣星
 * @since 2025-03-14
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {
    //使用条件构建器
    @Override
    public List<Student> findByStudent(Student student) {
        //创建条件构建器
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        //name不为空 null,"","  "
        if (StringUtils.isNotBlank(student.getName())){
            wrapper.eq(Student::getName,student.getName());
        }
        if (student.getId() != null){
            wrapper.or().eq(Student::getId,student.getId());
        }
        //根据条件查询结果
        return this.list(wrapper);
    }
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public List<Student> findByStudent1(Student student) {
        //创建条件构建器
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        //name不为空 null,"","  "
        if (StringUtils.isNotBlank(student.getName())){
            wrapper.eq(Student::getName,student.getName());
        }
        if (student.getId() != null){
            wrapper.or().eq(Student::getId,student.getId());
        }
        //根据条件查询结果
        return studentMapper.selectList(wrapper);
    }

    @Override
    public boolean removeStudentById(Student student) {
        LambdaUpdateWrapper<Student> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Student::getId,student.getId());
        //return this.remove(wrapper);
        return studentMapper.delete(wrapper)>0?true:false;
    }

    @Override
    public Page<Student> selectByPage(Student student, int pageNo, int pageSize) {
        //创建条件构建器
        LambdaUpdateWrapper<Student> wrapper = new LambdaUpdateWrapper<>();
        if (StringUtils.isNotBlank(student.getName()))//null ,"" ,"  "
            wrapper.like(Student::getName,student.getName());
        if (student.getId() != null)
            wrapper.or().eq(Student::getId,student.getId());
        //创建page对象
        Page<Student> page = new Page<>(pageNo,pageSize);
        //根据条件分页查询
        return studentMapper.selectPage(page,wrapper);
    }
}
