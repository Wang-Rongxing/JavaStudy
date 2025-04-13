package com.hnsoftedu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hnsoftedu.entity.Student;
import com.hnsoftedu.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 王荣星
 * @since 2025-03-14
 */
@RestController
@CrossOrigin//容许跨域
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService iStudentService;
    //从数据库查询student
    @RequestMapping("/list")
    public List<Student> find(){
        return iStudentService.list();
    }
    //id查询
    @RequestMapping("/getById")
    public Student getById(Integer id){
        return iStudentService.getById(id);
    }
    //增加数据
    @RequestMapping("/save")
    public boolean save(@RequestBody Student student){
        return iStudentService.save(student);
    }
    //删除
    @RequestMapping("/delete")
    public boolean delete(Student student){
        return iStudentService.removeById(student.getId());
    }
    /*复杂查询：条件构建器
     */
    @RequestMapping("/findByStudent")
    public List<Student> getByStudent(Student student){
        return iStudentService.findByStudent(student);
    }
    @RequestMapping("/removeById")
    public boolean removeById(Student student){
        return iStudentService.removeStudentById(student);
    }
    //mysql为例，进行分页查询
    //查询 第二页的数据（每页3个数据）
    @RequestMapping("/page")
    public Page<Student> findByPage(){
        //创建page long current：第几页，long size：每一页的大小
        Page<Student> studentPage = new Page<Student>(1,3);
        return iStudentService.page(studentPage);
    }
    @RequestMapping("/page2")
    public Map<String, Object> findByPage2(){
        //创建page long current：第几页，long size：每一页的大小
        Page<Student> studentPage = new Page<Student>(1,3);
        Page<Student> result = iStudentService.page(studentPage);
        HashMap<String, Object> map = new HashMap<>();
        map.put("list",result.getRecords());
        map.put("total",result.getTotal());
        map.put("pageNo",result.getCurrent());
        map.put("pagesize",result.getSize());
        return map;
    }
    /*
    属性名	类型	默认值	描述
records	List<T>	emptyList	查询数据列表   limit ?,?  limit 20,10  从20条后的10条数据
pages               查询列表总页数
total	Long	0	查询列表总记录数
size	Long	10	每页显示条数，默认 10
current	Long	1	当前页
orders	List<OrderItem>	emptyList	排序字段信息
optimizeCountSql	boolean	true	自动优化 COUNT SQL
optimizeJoinOfCountSql	boolean	true	自动优化 COUNT SQL 是否把 join 查询部分移除
searchCount	boolean	true	是否进行 count 查询
maxLimit	Long		单页分页条数限制
countId	String		XML 自定义 count 查询的 statementId
     */
    //通用动态分页查询 ：条件，current,size
    @RequestMapping("/page3")
    public Page<Student> findByPage3(Student student,int pageNo,int pageSize){
        return iStudentService.selectByPage(student,pageNo,pageSize);
    }
    //修改功能
    @RequestMapping("/updateStudent")
    public boolean updateStudent(@RequestBody Student student){//@RequestBody数据封装成json
        return iStudentService.updateStudent(student);
    }
}
