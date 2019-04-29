package cn.tedu.store.mapper;

import cn.tedu.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictMapperTestCase {

    @Autowired
    private DistrictMapper mapper;

    @Test
    public void findListByParent() {
        String parent = "86";
        List<District> data = mapper.findListByParent(parent);
        System.err.println("BEGIN:");
        for (District district : data) {
            System.err.println(district);
        }
        System.err.println("END.");
    }

    @Test
    public void findByCode() {
        String code = "330000";
        District data = mapper.findByCode(code);
        System.err.println(data);
    }

}









