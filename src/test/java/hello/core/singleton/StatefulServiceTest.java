package hello.core.singleton;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceTest() {
       ApplicationContext ac  = new AnnotationConfigApplicationContext(TestConfig.class);
      StatefulService statefulService1 = ac.getBean(StatefulService.class);
      StatefulService statefulService2 = ac.getBean(StatefulService.class);

      statefulService1.order("userA",10000);
      statefulService2.order("userB",20000);

      int price = statefulService1.getPrice();
      System.out.println("price = "+price);

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{
        @Bean
        public StatefulService stateService(){
            return new StatefulService();
        }
    }
}