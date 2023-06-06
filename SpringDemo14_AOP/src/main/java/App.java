import com.itstudy.config.SpringConfig;
import com.itstudy.dao.BookDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookDao bookDao = ctx.getBean(BookDao.class);
        /*bookDao.update();

        System.out.println("==========");

        int i = bookDao.select();
        System.out.println(i);*/

        //System.out.println(bookDao.findName("tom"));

        System.out.println(bookDao.confirmPassword("234 "));


    }

}
