package com.gdu.myapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import com.gdu.myapp.dto.UserDto;
import com.gdu.myapp.mapper.UserMapper;



// @ContextConfiguration + @WebApplicationContext
@SpringJUnitWebConfig(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})

// 테스트 수행 순서 (메소드 이름 순)
@TestMethodOrder(MethodOrderer.MethodName.class)

class UserMapperTest {

  @Autowired
  private  UserMapper userMapper;
  
  @Test
  public void 테스트02_삽입() throws Exception {
    
    
   assertEquals(1, userMapper.insertUser(new UserDto()));
    
  }

 

}