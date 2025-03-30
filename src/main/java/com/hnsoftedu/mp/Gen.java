package com.hnsoftedu.mp;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.nio.file.Paths;

public class Gen {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/MyBatis_DB?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC",
                            "root",
                            "wrx20040103")
                .globalConfig(builder -> builder
                        .author("王荣星")
                        .outputDir(Paths.get(System.getProperty("user.dir")) + "/src/main/java")
                        .commentDate("yyyy-MM-dd")
                )
                .packageConfig(builder -> builder
                        .parent("com.hnsoftedu")//自定义包
                        .entity("entity")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .xml("mapper.xml")
                )
                .strategyConfig(builder -> builder
                        .entityBuilder()
                        .enableLombok()
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
