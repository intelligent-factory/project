package com.Template;

import com.example.mes.mesApplication;
import com.example.mes.template.entity.EquipmentTemplate;
import com.example.mes.template.mapper.TemplateMapper;
import com.example.mes.template.service.TemplateService;
import com.example.mes.template.vo.EquipmentTemplateVO;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = mesApplication.class)
public class TemplateTest {
    @Autowired
    TemplateService templateService;

    @Autowired
    TemplateMapper templateMapper;

    @Test
    public void equipmentTemplateAddTest(){

    }
}
