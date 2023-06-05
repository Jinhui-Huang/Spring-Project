import com.itstudy.config.SpringConfig;
import com.itstudy.domain.Account;
import com.itstudy.service.AccountService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class AppTestForMybatis {
    public static void main(String[] args) {
        //加载spring主配置文件
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);

        AccountService accountService = ctx.getBean(AccountService.class);

        Account account1 = new Account();
        account1.setUsername("tom2");
        account1.setName("汤姆2");
        account1.setImage("2.jpg");
        account1.setGender((short) 1);
        account1.setJob((short) 2);
        account1.setEntrydate(LocalDate.of(2010, 1, 1));
        account1.setCreateTime(LocalDateTime.now());
        account1.setUpdateTime(LocalDateTime.now());

        accountService.insert(account1);

        List<Account> accountList = accountService.findAll();
        accountList.stream().forEach(System.out::println);

    }
}
