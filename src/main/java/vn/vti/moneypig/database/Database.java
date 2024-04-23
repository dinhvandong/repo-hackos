package vn.vti.moneypig.database;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.vti.moneypig.models.AppConfig;
import vn.vti.moneypig.models.CategoryGroup;
import vn.vti.moneypig.models.Command;
import vn.vti.moneypig.models.User;
import vn.vti.moneypig.repositories.AppConfigRepository;
import vn.vti.moneypig.repositories.CategoryGroupRepository;
import vn.vti.moneypig.repositories.CommandRepository;
import vn.vti.moneypig.repositories.UserRepository;
import vn.vti.moneypig.security.PasswordEncoder;
import vn.vti.moneypig.utils.DateUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class Database {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, AppConfigRepository appConfigRepository,
                                   CategoryGroupRepository categoryGroupRepository, CommandRepository commandRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                User user = new User();
                user.setId(1L);
                user.setUsername("admin");
                user.setEmail("admin@gmail.com");
                user.setPhone("84965741051");
                user.setPassword(PasswordEncoder.getInstance().encodePassword("A123456a@"));
                user.setStatus(1);
                user.setGender(1);
                user.setDateOfBirth(20011010);
                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                String formattedDate = currentDate.format(formatter);
                long longDate = Long.parseLong(formattedDate);
                user.setCreatedDate(longDate);
                if(userRepository.findAll().isEmpty())
                    userRepository.insert(user);
                CategoryGroup categoryGroup1 = new CategoryGroup();
                categoryGroup1.setId(1L);
                categoryGroup1.setCode("EXPENSE");
                categoryGroup1.setName("Expense");
                categoryGroup1.setStatus(1);
                categoryGroup1.setCreatedDate(longDate);

                CategoryGroup categoryGroup2 = new CategoryGroup();
                categoryGroup2.setId(2L);
                categoryGroup2.setCode("MONEY");
                categoryGroup2.setName("Money");
                categoryGroup2.setStatus(1);
                categoryGroup2.setCreatedDate(longDate);

                CategoryGroup categoryGroup3 = new CategoryGroup();

                categoryGroup3.setId(3L);
                categoryGroup3.setCode("DEPT");
                categoryGroup3.setName("Dept");
                categoryGroup3.setStatus(1);
                categoryGroup3.setCreatedDate(longDate);

                if(categoryGroupRepository.findAll().isEmpty()){
                    categoryGroupRepository.insert(categoryGroup1);
                    categoryGroupRepository.insert(categoryGroup2);
                    categoryGroupRepository.insert(categoryGroup3);
                }
                AppConfig appConfig = new AppConfig();
                appConfig.setId(1L);
                appConfig.setActive(0);
                if(appConfigRepository.findAll().isEmpty())
                    appConfigRepository.insert(appConfig);


                Command command = new Command();
                command.setId(1L);
                command.setValue(0);
                command.setTimeUTC(DateUtils.getCurrentTimeUTC());
                command.setTime(DateUtils.getCurrentDateYYYYMMDDHHmmss());
                System.out.println("UTC:"+DateUtils.getCurrentTimeUTC());
                commandRepository.deleteAll();
                if(commandRepository.findAll().isEmpty()){
                    commandRepository.insert(command);
                }
//                if(serviceRepository.findAll().isEmpty()){
//                    Service service1 = new Service();
//                    service1.setId(1L);
//                    service1.setServiceCode("C-HA123");
//                    service1.setServiceName("ABC");
//                    service1.setServiceType("HHH");
//                    serviceRepository.insert(service1);
//                }
            }
        };
    }
    
}
