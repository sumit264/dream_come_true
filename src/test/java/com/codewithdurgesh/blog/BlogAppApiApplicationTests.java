package com.codewithdurgesh.blog;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codewithdurgesh.blog.repositories.UserRepo;

@SpringBootTest
class BlogAppApiApplicationTests {
	private Calculator c=new Calculator();
	
	@Autowired
	UserRepo userRepo;

	@Test
	void contextLoads() {
	}
	
	@Test
	@Disabled
	void testSum() {
		
		int expectedResult=17;
		
		int actualResult = c.doSum(12, 3, 2);
		
		assertThat(actualResult).isEqualTo(expectedResult);
	}
	
	
	@Test
	void testProduct() {
		
		int expectedResult=36;
		
		int actualResult = c.doProduct(12, 3);
		
		assertThat(actualResult).isEqualTo(expectedResult);
	}
	
	@Test
	void testCompareNum() {
		
		Boolean actualResult = c.compareTwoNum(3, 3);
		assertThat(actualResult).isTrue();
	}
	
	
@Test
	public void proxyclasscreationatruntime() {
		
		String className= this.userRepo.getClass().getName();
		String PackageName=this.userRepo.getClass().getPackageName();
		
		System.out.println("Class Name "+className);

		System.out.println("Package Name "+PackageName);
	}

}
