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
@Service// 标识这是一个Spring服务层组件
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {
    //使用条件构建器
    /**
     * 根据条件查询学生列表（使用基类方法）
     * @param student 包含查询条件的学生对象
     * @return 符合条件的学生列表
     */
    @Override
    public List<Student> findByStudent(Student student) {
        //创建条件构建器
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        //name不为空 null,"","  "
        if (StringUtils.isNotBlank(student.getName())){
            // 添加姓名等值查询条件
            wrapper.eq(Student::getName,student.getName());
        }
        // 如果学生ID不为空
        if (student.getId() != null){
            // 添加OR条件的ID等值查询
            wrapper.or().eq(Student::getId,student.getId());
        }
        //根据条件查询结果
        // 调用基类方法执行查询
        return this.list(wrapper);
    }
    @Autowired// 注入学生Mapper
    private StudentMapper studentMapper;
    /**
     * 根据条件查询学生列表（直接使用Mapper）
     * @param student 包含查询条件的学生对象
     * @return 符合条件的学生列表
     */
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
        // 直接使用Mapper执行查询
        return studentMapper.selectList(wrapper);
    }
    /**
     * 根据ID删除学生
     * @param student 包含ID的学生对象
     * @return 是否删除成功
     */
    @Override
    public boolean removeStudentById(Student student) {
        // 创建更新条件构造器
        LambdaUpdateWrapper<Student> wrapper = new LambdaUpdateWrapper<>();
        // 设置ID等值条件
        wrapper.eq(Student::getId,student.getId());
        // 执行删除并返回结果（删除记录数>0表示成功）
        //return this.remove(wrapper);
        return studentMapper.delete(wrapper) > 0;
    }
    /**
     * 分页查询学生信息
     * @param student 包含查询条件的学生对象
     * @param pageNo 当前页码
     * @param pageSize 每页大小
     * @return 分页结果对象
     */
    @Override
    public Page<Student> selectByPage(Student student, int pageNo, int pageSize) {
        //创建条件构建器
        // 创建更新条件构造器（此处使用UpdateWrapper是因为可能有更新操作）
        LambdaUpdateWrapper<Student> wrapper = new LambdaUpdateWrapper<>();
        // 姓名模糊查询条件
        if (StringUtils.isNotBlank(student.getName()))//null ,"" ,"  "
            wrapper.like(Student::getName,student.getName());
        // ID等值查询条件
        if (student.getId() != null)
            wrapper.or().eq(Student::getId,student.getId());
        //创建page对象
        // 创建分页对象（当前页码，每页数量）
        Page<Student> page = new Page<>(pageNo,pageSize);
        //根据条件分页查询
        // 执行分页查询
        return studentMapper.selectPage(page,wrapper);
    }

    @Override
    public boolean updateStudent(Student student) {
        LambdaUpdateWrapper<Student> wrapper = new LambdaUpdateWrapper<>();
        if (StringUtils.isNotBlank(student.getName())){
            wrapper.eq(Student::getId,student.getId());
            wrapper.set(Student::getName,student.getName());
            wrapper.set(Student::getAge,student.getAge());
            wrapper.set(Student::getScore,student.getScore());
        }
        return studentMapper.update(null, wrapper) > 0;
    }
}
