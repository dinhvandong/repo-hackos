package vn.vti.moneypig.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.vti.moneypig.models.CategoryGroup;
import vn.vti.moneypig.models.Service;
import vn.vti.moneypig.models.User;
import vn.vti.moneypig.repositories.CategoryGroupRepository;
import vn.vti.moneypig.repositories.ServiceRepository;
import vn.vti.moneypig.repositories.UserRepository;
import vn.vti.moneypig.security.PasswordEncoder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Configuration
public class Database {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository,
                                   CategoryGroupRepository categoryGroupRepository) {
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
